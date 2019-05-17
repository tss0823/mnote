package com.loong.mnote.dal.domain;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 *
 * @Description: 类目
 *
 * @Author: zheng.yuan
 * @Date: 2019-01-11
 **/
@Entity(name = "category")
@ApiModel("类目表")
@Table(indexes = {@Index(name = "name_unique_index",columnList = "name",unique = true)})
public class Category extends BaseEntity {


    @ApiModelProperty(notes = "国际化标识")
    @Column(length = 20, nullable = false)
    private String local;

    @ApiModelProperty(notes = "用户ID")
    @Column(length = 20, nullable = false)
    private Long userId;

    @ApiModelProperty(notes = "类目名称")
    @Column(length = 20, nullable = false)
    private String name;

    @ApiModelProperty(notes = "类目描述")
    @Column(name="[desc]", length = 64, nullable = false)
    private String desc;

    @ApiModelProperty(notes = "上一级类目ID")
    @Column(length = 20, nullable = false)
    private Long parentId;

    @ApiModelProperty(notes = "排序")
    @Column(length = 4, nullable = false)
    private Integer sort;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
