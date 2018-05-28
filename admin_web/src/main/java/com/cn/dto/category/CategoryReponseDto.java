package com.cn.dto.category;

import com.cn.liu.base.ReponseDto;
import com.cn.liu.dto.ProductCategory;

import java.util.List;

/**
 * @author lzf
 * @date 2018-05-28
 * @desc
 */
public class CategoryReponseDto extends ReponseDto {

    private List<ProductCategory> list;

    private ProductCategory productCategory;

    public List<ProductCategory> getList() {
        return list;
    }

    public void setList(List<ProductCategory> list) {
        this.list = list;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
