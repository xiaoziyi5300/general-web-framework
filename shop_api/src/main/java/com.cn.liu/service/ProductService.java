package com.cn.liu.service;

import com.cn.liu.base.PageRequstParams;
import com.cn.liu.dto.Product;
import com.github.pagehelper.Page;

/**
 * @author lzf
 * @date 2018-05-23
 * @desc
 */
public interface ProductService {

    public void save(Product product);

    public void update(Product product);

    public void delete(int pId);

    Page<Product> queryListByPage(PageRequstParams pageRequstParams);
}
