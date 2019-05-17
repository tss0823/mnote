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

@Entity(name="role")
@ApiModel("角色")
public class Role extends BaseEntity {
    

    @ApiModelProperty(notes = "名称")
    @Column(length = 50,nullable = false)
    private String name;



    public Role(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}