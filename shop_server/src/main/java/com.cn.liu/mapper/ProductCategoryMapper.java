package com.cn.liu.mapper;


import com.cn.liu.model.ProductCategoryModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategoryModel record);

    int insertSelective(ProductCategoryModel record);

    ProductCategoryModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductCategoryModel record);

    int updateByPrimaryKey(ProductCategoryModel record);

    int checkBean(@Param("productCategoryModel") ProductCategoryModel productCategoryModel);

    void deleteById(int id);

    int queryToatalCount();

    List<ProductCategoryModel> selectByList();
}