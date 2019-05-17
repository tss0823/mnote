/*
 * 
 * 
 * 
 * 
 */

package com.loong.mnote.dal.domain;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="role_auth_res")
@ApiModel("角色资源")
public class RoleAuthRes extends BaseEntity {
    

    @ApiModelProperty(notes = "角色ID")
    @Column(length = 20,nullable = false)
    private Long roleId;

    @ApiModelProperty(notes = "权限资源ID")
    @Column(length = 20,nullable = false)
    private Long authResId;



    public RoleAuthRes(){
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthResId() {
        return authResId;
    }

    public void setAuthResId(Long authResId) {
        this.authResId = authResId;
    }
}