package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("用户登录参数")
public class UserLoginParam extends BaseDomain {

    @NotNull(message="登录类型不能为空")
    @ApiModelProperty(notes = "登录类型(详见枚举 LoginTypeEnum)",required = true)
    private Integer loginType;

//    @NotNull(message="登录类型不能为空")
    @ApiModelProperty(notes = "验证码登录类型",required = true)
    private Integer checkCodeType;


    @NotBlank(message="账号不能为空")
    @ApiModelProperty(notes = "账号（邮箱，手机号，用户名）",required = true)
    private String accountNo;

    //@NotBlank(message="区号不能为空")
    @ApiModelProperty(notes = "区号")
    private String areaCode;

    //@NotBlank(message="区号不能为空")
    @ApiModelProperty(notes = "手机号码")
    private String mobile;

    @ApiModelProperty(notes = "邮箱地址")
    private String email;

    @NotNull(message="密码不能为空")
    @ApiModelProperty(notes = "密码")
    private String pwd;

    @ApiModelProperty(notes = "OpenId")
    private String openId;

    @ApiModelProperty(notes = "验证码")
    private String checkCode;

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Integer getCheckCodeType() {
        return checkCodeType;
    }

    public void setCheckCodeType(Integer checkCodeType) {
        this.checkCodeType = checkCodeType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
