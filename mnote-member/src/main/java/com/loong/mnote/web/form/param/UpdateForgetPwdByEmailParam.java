package com.loong.mnote.web.form.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author: sam
 * @date: 2019-02-14 16:23
 */
@ApiModel("用户修改密码参数")
public class UpdateForgetPwdByEmailParam extends BaseDomain {

    @NotBlank(message="邮箱地址不能为空")
    @ApiModelProperty(notes = "邮箱地址",example = "test@mnote.com",required = true)
    private  String email;


    @NotBlank(message="邮箱验证码不能为空")
    @ApiModelProperty(notes = "邮箱验证码",example = "1234",required = true)
    private String checkCode;


    @NotBlank(message="密码不能为空")
    @ApiModelProperty(notes = "密码",example = "123456",required = true)
    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
