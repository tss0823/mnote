package com.loong.mnote.web.controller;


import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.enums.LoginTypeEnum;
import com.loong.mnote.common.enums.RegisterTypeEnum;
import com.loong.mnote.common.enums.UserTypeEnum;
import com.loong.mnote.common.exception.BizException;
import com.loong.mnote.common.util.BeanUtils;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.dal.domain.User;
import com.loong.mnote.dal.param.UpdatePwdParam;
import com.loong.mnote.dal.param.UserInfoEditParam;
import com.loong.mnote.dal.param.UserLoginParam;
import com.loong.mnote.dal.param.UserRegisterParam;
import com.loong.mnote.service.UserService;
import com.loong.mnote.service.component.MailComponent;
import com.loong.mnote.service.component.SmsComponent;
import com.loong.mnote.web.form.param.UpdateEmailParam;
import com.loong.mnote.web.form.param.UpdateForgetPwdByEmailParam;
import com.loong.mnote.web.form.param.UpdateForgetPwdBySmsParam;
import com.loong.mnote.web.form.param.UpdateMobileParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: sam
 * @date: 2018-12-19 12:02
 */
@RestController
@RequestMapping(path = "/user/")
@Api(description = "用户模块 (author:Sam)")
@Validated
public class UserController {

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private MailComponent mailComponent;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户注册（手机，邮箱）", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "register", produces = "application/json")
    public Object register(@Valid @RequestBody UserRegisterParam userRegisterParam, HttpServletResponse response) {
        //validate
        Integer registerType = userRegisterParam.getRegisterType();
        if (registerType == RegisterTypeEnum.MOBILE.getCode()) {
            if (StringUtils.isBlank(userRegisterParam.getAreaCode())) {
                throw new BizException("手机号码区号不能为空");
            }
            if (StringUtils.isBlank(userRegisterParam.getMobile())) {
                throw new BizException("手机号码不能为空");
            }
        } else {
            if (StringUtils.isBlank(userRegisterParam.getEmail())) {
                throw new BizException("邮箱地址不能为空");
            }
        }

        userRegisterParam.setUserType(UserTypeEnum.MEMBER.getCode());
        User user = userService.register(userRegisterParam);
        //设置登录用户
        String token = userService.resetLoginToken(user);
        return WebResultBuilder.successResult(token);
    }

    @ApiOperation(value = "用户登录", response = WebResult.class)
    @PostMapping(path = "login", produces = "application/json")
    public Object login(@Valid @RequestBody UserLoginParam userLoginParam) {
        Integer loginType = userLoginParam.getLoginType();
        int userType = UserTypeEnum.MEMBER.getCode();
        String accountNo = userLoginParam.getAccountNo();
        String checkCode = userLoginParam.getCheckCode();
        String openId = userLoginParam.getOpenId();
        String areaCode = userLoginParam.getAreaCode();
        String pwd = userLoginParam.getPwd();
        String mobile = userLoginParam.getMobile();
        String email = userLoginParam.getEmail();
        User user = null;
        if (loginType == LoginTypeEnum.PWD.getCode()) {
            if (StringUtils.isEmpty(pwd)) {
                throw new BizException("密码不能为空");
            }
            user = userService.loginByPwd(userType, accountNo, pwd);
        } else if (loginType == LoginTypeEnum.CHECK_CODE.getCode()) {
            Integer checkCodeType = userLoginParam.getCheckCodeType();
            if(checkCodeType == null){
                throw new BizException("验证码校验类型不能空");
            }
            if(checkCodeType == RegisterTypeEnum.MOBILE.getCode()){  //mobile
                user = userService.loginBySmsCode(userType,areaCode,mobile,checkCode);

            }else{  //email
                user = userService.loginByEmailCode(userType,email,checkCode);
            }
        } else if (loginType == LoginTypeEnum.FACEBOOK.getCode()) {
            user = userService.loginByFacebook(userType, openId, areaCode, accountNo, checkCode, null);

        } else if (loginType == LoginTypeEnum.WECHAT.getCode()) {
            user = userService.loginByWechat(userType, openId, areaCode, accountNo, checkCode, null);
        }
        //设置登录用户
        String token = userService.resetLoginToken(user);
        return WebResultBuilder.successResult(token);
    }

    @ApiOperation(value = "用户信息修改", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "update", produces = "application/json")
    public Object update(@Valid @RequestBody UserInfoEditParam userInfoEditParam, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        User dbUser = userService.findById(loginUser.getId());
        BeanUtils.beanCopy(userInfoEditParam, dbUser);
        userService.save(dbUser);
        return WebResultBuilder.successResult(dbUser);
    }

    @ApiOperation(value = "获取用户信息", nickname = "sam@mnote.com", response = WebResult.class)
    @GetMapping(path = "getLoginUserInfo", produces = "application/json")
    public Object getLoginUserInfo(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return WebResultBuilder.successResult(user);
    }


    @ApiOperation(value = "用户注销", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "logout", produces = "application/json")
    public Object logout(HttpServletRequest request) {
        userService.clearLoginUser(request);
        return WebResultBuilder.successResult();
    }

    @ApiOperation(value = "用户密码修改", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "updatePwd", produces = "application/json")
    public Object updatePwd(@RequestBody UpdatePwdParam updatePwdParam ,HttpServletRequest request) {

        User user = userService.getLoginUser(request);
        userService.updatePwd(user.getId(), updatePwdParam.getOldPwd(), updatePwdParam.getNewPwd());
        return WebResultBuilder.successResult();
    }

    @ApiOperation(value = "修改忘记密码（短信）", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "updateForgetLoginPwdBySms", produces = "application/json")
    public Object updateForgetLoginPwdBySms(@RequestBody UpdateForgetPwdBySmsParam param) {

        userService.updateForgetLoginPwdBySmsCode(UserTypeEnum.MEMBER.getCode(), param.getAreaCode(), param.getMobile(), param.getCheckCode(), param.getPwd());
        return WebResultBuilder.successResult();
    }


    @ApiOperation(value = "修改忘记密码（邮箱）", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "updateForgetLoginPwdByEmail", produces = "application/json")
    public Object updateForgetLoginPwdByEmail(@RequestBody UpdateForgetPwdByEmailParam param) {

        userService.updateForgetLoginPwdByEmailCode(UserTypeEnum.MEMBER.getCode(), param.getEmail(), param.getEmail(), param.getPwd());
        return WebResultBuilder.successResult();
    }


    @ApiOperation(value = "修改手机号码", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "updateMobile", produces = "application/json")
    public Object updateMobile(@RequestBody UpdateMobileParam param,HttpServletRequest request) {

        User user = userService.getLoginUser(request);
        userService.updateMobile(user.getId(),param.getAreaCode() , param.getMobile(), param.getCheckCode());
        return WebResultBuilder.successResult();
    }

    @ApiOperation(value = "修改邮箱", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "updateEmail", produces = "application/json")
    public Object updateEmail(@RequestBody UpdateEmailParam param, HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        userService.updateEmail(user.getId(), param.getEmail(), param.getCheckCode());
        return WebResultBuilder.successResult();
    }

    @ApiOperation(value = "获取手机验证码", nickname = "sam@mnote.com", response = WebResult.class)
    @GetMapping(path = "getSmsCode", produces = "application/json")
    public Object getSmsCode(@ApiParam(value = "类型(详细见 /common/enumList key=smsCodeType)", required = true, example = "0") @RequestParam Integer type,
                             @ApiParam(value = "国家区号", required = true, example = "63") @RequestParam String areaCode,
                             @ApiParam(value = "手机号码", required = true, example = "97746736352") @RequestParam String mobile) {
        String smsCode = smsComponent.getSmsCode(type, UserTypeEnum.MEMBER.getCode(), areaCode, mobile);
        return WebResultBuilder.successResult(smsCode);
    }

    @ApiOperation(value = "获取邮箱验证码", nickname = "sam@mnote.com", response = WebResult.class)
    @GetMapping(path = "getEmailCode", produces = "application/json")
    public Object getEmailCode(@ApiParam(value = "类型(详细见 /common/enumList key=smsCodeType)", required = true, example = "0") @RequestParam Integer type,
                               @ApiParam(value = "邮箱地址", required = true, example = "test@mnote.com") @RequestParam String email) {
        String emailCode = mailComponent.getEmailCode(type, UserTypeEnum.MEMBER.getCode(), email);
        return WebResultBuilder.successResult(emailCode);
    }



}
