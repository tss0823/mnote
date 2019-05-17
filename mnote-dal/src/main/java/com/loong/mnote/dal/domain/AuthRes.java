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

@Entity(name="auth_res")
@ApiModel("权限资源")
public class AuthRes extends BaseEntity {
    

    @ApiModelProperty(notes = "编码")
    @Column(length = 20,nullable = false)
    private String code;

    @ApiModelProperty(notes = "名称")
    @Column(length = 20,nullable = false)
    private String name;

    @ApiModelProperty(notes = "URL")
    @Column(length = 100,nullable = false)
    private String url;

    @ApiModelProperty(notes = "模板URL")
    @Column(length = 100)
    private String tplUrl;

    @ApiModelProperty(notes = "是否菜单(0：否，1：是)")
    @Column(length = 1,nullable = false)
    private Boolean menu;

    @ApiModelProperty(notes = "是否仅展示（0：否，1：是）")
    @Column(length = 1,nullable = false)
    private Boolean display;

    @ApiModelProperty(notes = "动作ID")
    @Column(length = 1)
    private String actionId;

    @ApiModelProperty(notes = "图标")
    @Column(length = 100)
    private String icon;

    @ApiModelProperty(notes = "级别")
    @Column(length = 1,nullable = false)
    private Integer level;

    @ApiModelProperty(notes = "状态")
    @Column(length = 1,nullable = false)
    private Integer status;

    @ApiModelProperty(notes = "父ID")
    @Column(length = 20,nullable = false)
    private Long parentId;

    @ApiModelProperty(notes = "排序")
    @Column(length = 4,nullable = false)
    private Integer orderBy;


    @ApiModelProperty(notes = "应用类型")
    @Column(length = 10)
    private String appType;


    @ApiModelProperty(notes = "是否需要校验")
    @Column(length = 1)
    private Boolean authCheck;


    public AuthRes(){
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTplUrl() {
        return tplUrl;
    }

    public void setTplUrl(String tplUrl) {
        this.tplUrl = tplUrl;
    }

    public Boolean getMenu() {
        return menu;
    }

    public void setMenu(Boolean menu) {
        this.menu = menu;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Boolean getAuthCheck() {
        return authCheck;
    }

    public void setAuthCheck(Boolean authCheck) {
        this.authCheck = authCheck;
    }
}