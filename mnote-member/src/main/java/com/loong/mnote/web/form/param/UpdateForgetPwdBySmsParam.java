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
public class UpdateForgetPwdBySmsParam extends BaseDomain {

    @NotBlank(message="国家区号不能为空")
    @ApiModelProperty(notes = "国家区号",example = "63",required = true)
    private  String areaCode;


    @NotBlank(message="手机号码不能为空")
    @ApiModelProperty(notes = "手机号码",example = "123456",required = true)
    private String mobile;

    @NotBlank(message="短信验证码不能为空")
    @ApiModelProperty(notes = "短信验证码",example = "3234",required = true)
    private String checkCode;

    @NotBlank(message="密码不能为空")
    @ApiModelProperty(notes = "密码",example = "123456",required = true)
    private String pwd;

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
}
