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
public class UpdateEmailParam extends BaseDomain {

    @NotBlank(message="邮箱地址不能为空")
    @ApiModelProperty(notes = "邮箱地址",example = "test@mnote.com",required = true)
    private  String email;


    @NotBlank(message="邮箱验证码不能为空")
    @ApiModelProperty(notes = "邮箱验证码",example = "1234",required = true)
    private String checkCode;

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
}
