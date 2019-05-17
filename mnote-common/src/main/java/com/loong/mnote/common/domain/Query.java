package com.loong.mnote.common.domain;

import com.loong.mnote.common.exception.BizException;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: lhd
 * @date: 2018/12/27 11:43
 */
public class Query<T> implements Serializable {
    private static final long serialVersionUID = 7920389935124024017L;

    /**
     * 游标
     */
    private int offset = 0;
    /**
     * 页码
     */
    private int pageIndex = 1;
    /**
     * 分页大小
     */
    private int pageSize = 10;
    /**
     * 入参
     */
    private T data;

    public Query() {
    }

    public Query(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex != null ? pageIndex : this.pageIndex;
        if (this.pageIndex < 1) {
            this.pageIndex = 1;
        }
        this.pageSize = pageSize != null ? pageSize : this.pageSize;
        if (this.pageSize < 1) {
            this.pageSize = 10;
        }
        BigDecimal off = new BigDecimal((this.pageIndex - 1)).multiply(new BigDecimal((this.pageSize)));
        BigDecimal max = new BigDecimal(Integer.MAX_VALUE);
        if (off.compareTo(max) == 1) {
            throw new BizException("QUERY_PAGE_BORDER", "分页越界非法");
        }
        this.offset = (this.pageIndex - 1) * this.pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
