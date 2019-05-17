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

@Entity(name="outer_user")
@ApiModel("第三方用户")
public class OuterUser extends BaseEntity {

    @ApiModelProperty(notes = "用户ID")
    @Column(length = 20,nullable = false)
    private Long userId;
        
    @ApiModelProperty(notes = "昵称")
    @Column(length = 100,nullable = false)
    private String nickname;
        
    @ApiModelProperty(notes = "绑定类型")
    @Column(length = 4,nullable = false)
    private Integer bindType;
        
    @ApiModelProperty(notes = "状态")
    @Column(length = 4,nullable = false)
    private Integer status;
        
    @ApiModelProperty(notes = "头像")
    @Column(length = 200,nullable = false)
    private String avatar;
        
    @ApiModelProperty(notes = "OpenId")
    @Column(length = 50,nullable = false)
    private String openId;
        
    
    public OuterUser(){
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}