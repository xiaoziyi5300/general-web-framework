package com.cn.liu.serviceImpl;

import com.cn.liu.dto.ProductCategory;
import com.cn.liu.mapper.ProductCategoryMapper;
import com.cn.liu.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public void save(ProductCategory productCategory) {

    }

    @Override
    public void update(ProductCategory productCategory) {

    }

    @Override
    public void deleteById(int id) {

    }
}
