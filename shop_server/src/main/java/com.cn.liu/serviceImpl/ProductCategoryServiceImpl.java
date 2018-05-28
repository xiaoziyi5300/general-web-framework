package com.cn.liu.serviceImpl;

import com.cn.liu.dto.ProductCategory;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.mapper.ProductCategoryMapper;
import com.cn.liu.model.ProductCategoryModel;
import com.cn.liu.service.ProductCategoryService;
import com.cn.liu.util.BeanUtil;
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
        ProductCategoryModel model = BeanUtil.mapper(productCategory, ProductCategoryModel.class);
        if (checkBean(model)) {
            throw new BusinessException("商品类目重复");
        }
        productCategoryMapper.insertSelective(model);
    }

    @Override
    public void update(ProductCategory productCategory) {
        ProductCategoryModel model = BeanUtil.mapper(productCategory, ProductCategoryModel.class);
        if (checkBean(model)) {
            throw new BusinessException("商品类目重复");
        }
        productCategoryMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void deleteById(int id) {
        productCategoryMapper.deleteById(id);
    }

    private boolean checkBean(ProductCategoryModel productCategoryModel) {
        int count = productCategoryMapper.checkBean(productCategoryModel);
        if (count > 0) {
            return false;
        }
        return true;
    }
}
