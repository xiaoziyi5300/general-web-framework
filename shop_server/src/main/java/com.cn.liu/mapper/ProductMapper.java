package com.cn.liu.mapper;

import com.cn.liu.model.ProductModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductModel record);

    int insertSelective(ProductModel record);

    ProductModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductModel record);

    int updateByPrimaryKey(ProductModel record);

    int selectProductByName(@Param("name") String name);

    int dataTotalCount();

    List<ProductModel> selectByList();

}