package com.cn.liu.service;

import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.dto.ProductCategory;

import java.util.List;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
public interface ProductCategoryService {

    public void save(ProductCategory productCategory);

    public void update(ProductCategory productCategory);

    public void deleteById(int id);

    PageBean<ProductCategory> queryListByPage(PageRequstParams pageRequstParams);

    List<ProductCategory> queryCategoryList(int parentId);
}
