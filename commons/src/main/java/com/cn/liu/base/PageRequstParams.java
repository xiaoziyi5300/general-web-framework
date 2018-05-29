package com.cn.liu.base;

import java.io.Serializable;

/**
 * @author lzf
 * @date 2018-05-28
 * @desc
 */
public class PageRequstParams implements Serializable {

    private int rows;//页面大小
    private int page;//页码
    private String sort;//排序列名
    private String sortOrder;//排位命令（desc，asc）
    private int parentId;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
