/*
 * 
 * 
 * 
 * 
 */

package com.loong.mnote.service.component.auth;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;

@ApiModel("权限资源")
public class AuthResObj extends BaseEntity {


    private String code;

    private String name;

    private String url;

    private String tplUrl;

    private Boolean menu;

    private Integer level;

    private Long parentId;

    private Integer orderBy;


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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    @Override
    public String toString() {
        return new StringBuffer().append(url).toString();
    }
}