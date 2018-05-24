package com.cn.liu.base;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc 分页请求对象
 */
public class PageRequestBean {
    /*
   当前页
    */
    private Integer page = 1;
    /*
    每页条数
     */
    private Integer rows = 50;

    /*
    排序列
     */
    private String sort;
    /*
    排序类型
     */
    private String order;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
