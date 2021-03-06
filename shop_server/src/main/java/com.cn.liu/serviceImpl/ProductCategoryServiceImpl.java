package com.cn.liu.serviceImpl;

import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.dto.ProductCategory;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.mapper.ProductCategoryMapper;
import com.cn.liu.model.ProductCategoryModel;
import com.cn.liu.service.ProductCategoryService;
import com.cn.liu.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!checkBean(model)) {
            throw new BusinessException("商品类目重复");
        }
        model.setDeleteMark(1);
        productCategoryMapper.insertSelective(model);
    }

    @Override
    public void update(ProductCategory productCategory) {
        ProductCategoryModel model = BeanUtil.mapper(productCategory, ProductCategoryModel.class);
        if (!checkBean(model)) {
            throw new BusinessException("商品类目重复");
        }
        model.setDeleteMark(1);
        productCategoryMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void deleteById(int id) {
        productCategoryMapper.deleteById(id);
    }

    @Override
    public PageBean<ProductCategory> queryListByPage(PageRequstParams pageRequstParams) {
        PageHelper.startPage(pageRequstParams.getPage(), pageRequstParams.getRows());
        List<ProductCategoryModel> productCategoryModelList = productCategoryMapper.selectByList(pageRequstParams.getParentId());
        PageBean pageBean = new PageBean();
        pageBean.setRows(BeanUtil.mapper(productCategoryModelList, ProductCategory.class));
        pageBean.setTotal(queryToatalCount(pageRequstParams.getParentId()));
        return pageBean;
    }

    @Override
    public List<ProductCategory> queryCategoryList(int parentId) {
        return BeanUtil.mapper(productCategoryMapper.queryCategoryList(parentId), ProductCategory.class);
    }

    @Override
    public ProductCategory queryById(String cId) {
        return BeanUtil.mapper(productCategoryMapper.selectByPrimaryKey(Integer.parseInt(cId)), ProductCategory.class);
    }

    private int queryToatalCount(int parentId) {
        return productCategoryMapper.queryToatalCount(parentId);
    }

    private boolean checkBean(ProductCategoryModel productCategoryModel) {
        int count = productCategoryMapper.checkBean(productCategoryModel);
        if (count > 0) {
            return false;
        }
        return true;
    }
}
