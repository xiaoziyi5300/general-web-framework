package com.cn.liu.service;

import com.cn.liu.base.PageReponseBean;
import com.cn.liu.base.PageRequestBean;
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

    PageReponseBean<Product> queryListByPage(PageRequestBean pageRequestBean);
}
