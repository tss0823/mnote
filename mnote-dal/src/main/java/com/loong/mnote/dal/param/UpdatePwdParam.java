package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author: sam
 * @date: 2019-02-14 16:23
 */
@ApiModel("用户修改密码参数")
public class UpdatePwdParam  extends BaseDomain {

    @NotBlank(message="旧密码不能为空")
    @ApiModelProperty(notes = "旧密码",example = "123456",required = true)
    private String oldPwd;


    @NotBlank(message="新密码不能为空")
    @ApiModelProperty(notes = "新密码",example = "123456",required = true)
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
