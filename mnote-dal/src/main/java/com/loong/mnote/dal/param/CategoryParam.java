package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yuan
 */
@ApiModel
public class CategoryParam extends BaseDomain {

    @ApiModelProperty(notes = "类目ID",example = "1")
    private Long id;

    @ApiModelProperty(notes = "用户ID",example = "1")
    private Long userId;

    @ApiModelProperty(notes = "国际化标识",example = "zh")
    private String local;

    @ApiModelProperty(notes = "类目名称",example = "国际")
    private String name;

    @ApiModelProperty(notes = "类目描述",example = "国际新闻")
    private String desc;

    @ApiModelProperty(notes = "上一级类目ID")
    private Long parentId;

    @ApiModelProperty(notes = "排序")
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
