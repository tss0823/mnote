package com.loong.mnote.boss.controller;


import com.loong.mnote.common.constants.SystemConstant;
import com.loong.mnote.common.domain.Pager;
import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.enums.SmsCodeTypeEnum;
import com.loong.mnote.common.enums.UserTypeEnum;
import com.loong.mnote.common.util.BeanUtils;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.dal.domain.User;
import com.loong.mnote.dal.param.IdParam;
import com.loong.mnote.dal.param.UserAdminLoginParam;
import com.loong.mnote.dal.param.UserEditParam;
import com.loong.mnote.dal.query.UserQuery;
import com.loong.mnote.service.UserService;
import com.loong.mnote.service.component.ImgCodeComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 *
 * @author: sam
 * @date: 2018-12-19 12:02
 */
@RestController
@RequestMapping(path = "/user/")
@Api( description="后台用户管理 (author:Sam)")
@Validated
public class UserController {

    @Autowired
    private ImgCodeComponent imgCodeComponent;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取图形验证码", nickname = "sam@mnote.com", response = WebResult.class)
    @GetMapping(path = "getSmsCode", produces = "application/json")
    public Object getSmsCode(HttpServletRequest request) {
        String deviceId = request.getHeader(SystemConstant.DEVICE_ID);
        String imgCode = imgCodeComponent.getImgCode(SmsCodeTypeEnum.LOGIN.getCode(), UserTypeEnum.BOSS.getCode(), deviceId);
        return WebResultBuilder.successResult(imgCode);
    }

    @ApiOperation(value = "用户登录", response = WebResult.class)
    @PostMapping(path = "login", produces = "application/json")
    public Object login(@RequestBody UserAdminLoginParam userAdminLoginParam, HttpServletRequest request) {

        String deviceId = request.getHeader(SystemConstant.DEVICE_ID);
        User user = userService.loginByPwd(UserTypeEnum.BOSS.getCode(), userAdminLoginParam.getAccountNo(), userAdminLoginParam.getPwd(), userAdminLoginParam.getImgCode(), deviceId);
        String token = userService.resetLoginToken(user);
        return WebResultBuilder.successResult(token);
    }


    @ApiOperation(value = "用户注销", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "logout", produces = "application/json")
    public Object logout(HttpServletRequest request) {
        userService.clearLoginUser(request);
        return WebResultBuilder.successResult();
    }

    @ApiOperation(value = "保存用户",response = WebResult.class)
    @PostMapping(path = "save",produces = "application/json")
    public Object save(@Valid @RequestBody UserEditParam userEditParam) {
        User user = BeanUtils.beanCopy(userEditParam,User.class);
        user.setType(UserTypeEnum.BOSS.getCode());
        userService.saveUser(user);
        return WebResultBuilder.successResult(user);
    }

    @ApiOperation(value = "删除用户",response = WebResult.class)
    @PostMapping(path = "del",produces = "application/json")
    public Object del(@RequestBody IdParam idParam) {
        userService.deleteById(idParam.getId());
        return WebResultBuilder.successResult();
    }

    @ApiOperation(value = "获取用户信息", nickname = "sam@mnote.com", response = WebResult.class)
    @GetMapping(path = "getLoginUserInfo", produces = "application/json")
    public Object getLoginUserInfo(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return WebResultBuilder.successResult(user);
    }

    @ApiOperation(value = "用户列表",response = WebResult.class)
    @GetMapping(path = "list",produces = "application/json")
    public Object list(UserQuery query) {
        query.setType(UserTypeEnum.BOSS.getCode());
        Pager<User> page = userService.findByPage(query);
        return WebResultBuilder.successResult(page);
    }

    @ApiOperation(value = "用户详情",response = WebResult.class)
    @GetMapping(path = "detail",produces = "application/json")
    public Object detail(@ApiParam(value = "用户ID", required = true, example = "1") @Valid @RequestParam Long id) {
        User user = userService.findById(id);
        return WebResultBuilder.successResult(user);
    }

}
