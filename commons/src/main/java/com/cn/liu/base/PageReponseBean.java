package com.cn.liu.base;

import java.util.List;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc 分页返回对象
 */
public class PageReponseBean<T> {

    //当前页码
    private int pageNum;
    //每页显示多少条
    private int pageSize;
    //总记录数
    private int totalCount;
    //总页数
    private int totalPage;
    //数据
    private List<T> list;
    //开始索引
    private int startIndex;
    private int start;
    private int end;

    public PageReponseBean(int pageNum, int pageSize, int totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        if (totalCount % pageSize == 0) {
            this.totalPage = totalCount / pageSize;
        } else {
            this.totalPage = totalCount / pageSize + 1;
        }
        this.startIndex = (pageNum - 1) * pageSize;
        this.start = 1;
        this.end = 5;
        if (totalPage <= 5) {
            this.end = this.totalPage;
        } else {
            this.start = pageNum - 2;
            this.end = pageNum + 2;
            if (start < 0) {
                this.start = 1;
                this.end = 5;
            }
            if (end > this.totalPage) {
                this.end = totalPage;
                this.start = end - 5;
            }
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
