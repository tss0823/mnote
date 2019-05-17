package com.loong.mnote.common.domain;

import java.util.List;

public class Pager<T> extends BaseDomain {
    private static final long serialVersionUID = 1038184723205915125L;

    /**
     * 游标
     */
    private int offset;
    /**
     * 页码
     */
    private int pageIndex;
    /**
     * 分页大小
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 结果
     */
    protected List<T> dataList;


    public Pager(int offset) {
        this.setOffset(offset);
    }

    public Pager(int offset, int pageSize) {
        this.setPageSize(pageSize);
        this.setOffset(offset);
    }

    public Pager(int offset, int pageSize, long total) {
        this.setPageSize(pageSize);
        this.setOffset(offset);
        this.setTotal(total);
    }

    public Pager() {
        super();
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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        this.totalPage = (this.total - 1) / this.pageSize + 1;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        if (offset <= 0) {
            offset = 1;
        }
        this.pageIndex = (offset - 1) * pageSize;
        this.offset = offset;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "offset=" + offset +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", dataList=" + dataList +
                '}';
    }
}