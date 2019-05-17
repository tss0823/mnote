package com.loong.mnote.common.domain;


import io.swagger.annotations.ApiParam;

/**
 * Created by shengshan.tang on 2015/11/27 at 14:12
 */
public class BaseQuery extends BaseDomain {

    @ApiParam(value = "是否删除",example = "false")
    private Boolean delState = false;

    @ApiParam(value = "ID")
    private Long id;

    @ApiParam(value = "用户ID")
    private Long userId;

    @ApiParam(value = "页大小")
    private int pageSize = 10; // default 10

    @ApiParam(value = "第几页")
    private int pageIndex = 1; // 第几页

    @ApiParam(value = "排序字段（多个都好隔开）")
    private String orderByColumn = "id";  //排序字段多个用逗号隔开

    @ApiParam(value = "排序类型desc,asc（多个都好隔开）")
    private String ascOrDesc = "desc"; //排序类型多个用逗号隔开

    @ApiParam(value = "限制条数")
    private Integer resultLimit;

    public Boolean getDelState() {
        return delState;
    }

    public void setDelState(Boolean delState) {
        this.delState = delState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getAscOrDesc() {
        return ascOrDesc;
    }

    public void setAscOrDesc(String ascOrDesc) {
        this.ascOrDesc = ascOrDesc;
    }

    public Integer getResultLimit() {
        return resultLimit;
    }

    public void setResultLimit(Integer resultLimit) {
        this.resultLimit = resultLimit;
    }
}