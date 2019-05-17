package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("用户注册参数")
public class UserRegisterParam extends BaseDomain {


    @NotNull(message="注册类型不能为空")
    @ApiModelProperty(notes = "注册类型（0 手机，1 邮箱）",required = true)
    private Integer registerType;

//    @NotBlank(message="区号不能为空")
    @ApiModelProperty(notes = "区号",required = true)
    private String areaCode;

    @NotNull(message="用户类型不能为空")
    @ApiModelProperty(notes = "用户类型",required = true)
    private Integer userType;

//    @NotBlank(message="手机号码不能为空")
    @ApiModelProperty(notes = "手机号码",required = true)
    private String mobile;

//    @NotBlank(message="手机号码不能为空")
    @ApiModelProperty(notes = "邮箱地址",required = true)
    private String email;

    @NotBlank(message="短信验证码不能为空")
    @ApiModelProperty(notes = "短信验证码",required = true)
    private String checkCode;

    @NotBlank(message="密码不能为空")
    @ApiModelProperty(notes = "密码",required = true)
    private String pwd;

    @ApiModelProperty(notes = "别人邀请码",required = true)
    private String shareInviteCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getShareInviteCode() {
        return shareInviteCode;
    }

    public void setShareInviteCode(String shareInviteCode) {
        this.shareInviteCode = shareInviteCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }


    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
