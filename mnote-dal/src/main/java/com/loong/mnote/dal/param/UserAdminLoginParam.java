package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author: sam
 * @date: 2019-02-14 16:06
 */
@ApiModel("用户登录参数")
public class UserAdminLoginParam  extends BaseDomain {

    @NotBlank(message="账号不能为空")
    @ApiModelProperty(notes = "账号",example = "admin",required = true)
    private String accountNo;

    @NotBlank(message="密码不能为空")
    @ApiModelProperty(notes = "密码",example = "123456",required = true)
    private String pwd;

    @NotBlank(message="图形验证码不能为空")
    @ApiModelProperty(notes = "图形验证码",example = "1234",required = true)
    private String imgCode;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }
}
