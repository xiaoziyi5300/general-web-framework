package com.cn.liu.service;

import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.dto.Product;

/**
 * @author lzf
 * @date 2018-05-23
 * @desc
 */
public interface ProductService {

    public void save(Product product);

    public void update(Product product);

    public void delete(int pId);

    PageBean<Product> queryListByPage(PageRequstParams pageRequstParams);
}
