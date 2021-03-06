package com.cn.liu.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author lzf
 * @date 2018-05-28
 * @desc 分页对象
 */
public class PageBean<T> implements Serializable {

    private int total;
    private List<T> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
