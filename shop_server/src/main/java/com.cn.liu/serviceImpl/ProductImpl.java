package com.cn.liu.serviceImpl;

import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageReponseBean;
import com.cn.liu.base.PageRequestBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.dto.Product;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.mapper.ProductMapper;
import com.cn.liu.model.ProductModel;
import com.cn.liu.service.ProductService;
import com.cn.liu.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzf
 * @date 2018-05-23
 * @desc
 */
@Service("productService")
public class ProductImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void save(Product product) {
        if (selectProductByName(product.getProductName()) > 0) {
            throw new BusinessException("商品名称已经存在");
        }
        ProductModel model = new ProductModel();
        BeanUtils.copyProperties(product, model);
        productMapper.insertSelective(model);
    }

    @Override
    public void update(Product product) {
        if (selectProductByName(product.getProductName()) > 0) {
            throw new BusinessException("商品名称已经存在");
        }
        ProductModel model = new ProductModel();
        //BeanUtils.copyProperties(product,model );
        productMapper.updateByPrimaryKeySelective(BeanUtil.mapper(product, ProductModel.class));
    }

    @Override
    public void delete(int pId) {
        productMapper.deleteByPrimaryKey(pId);
    }

    @Override
    public PageBean<Product> queryListByPage(PageRequstParams pageRequstParams) {
        PageHelper.startPage(pageRequstParams.getPage(), pageRequstParams.getRows());
        List<ProductModel> productModelList = productMapper.selectByList();
        PageBean pageBean = new PageBean();
        pageBean.setTotal(dataTotalCount());
        pageBean.setRows(BeanUtil.mapper(productModelList, Product.class));
        return pageBean;
    }

    public int dataTotalCount() {
        return productMapper.dataTotalCount();
    }

    private int selectProductByName(String name) {
        return productMapper.selectProductByName(name);
    }
}
