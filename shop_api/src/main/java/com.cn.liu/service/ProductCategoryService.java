package com.cn.liu.service;

import com.cn.liu.dto.ProductCategory;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
public interface ProductCategoryService {

    public void save(ProductCategory productCategory);

    public void update(ProductCategory productCategory);

    public void deleteById(int id);
}
