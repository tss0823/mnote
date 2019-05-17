package com.loong.mnote.dal.query;

import com.loong.mnote.common.domain.BaseQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * @author yuan
 */
@ApiModel
public class CategoryQuery extends BaseQuery {

    @ApiParam(value = "国际化标识",example = "1")
    private String local;

    @ApiParam(value = "用户Id",example = "1")
    private Long userId;

    @ApiParam(value = "类目名称",example = "2")
    private String name;

    @ApiParam(value = "类目描述",example = "3")
    private String desc;

    @ApiParam(value = "上一级类目ID",example = "0")
    private Long parentId;

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

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
