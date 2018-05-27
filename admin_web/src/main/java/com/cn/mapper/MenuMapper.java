package com.cn.mapper;

import com.cn.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> queryAllList();

    Menu queryById(@Param("menuId") String id);

    List<Menu> queryMenuList();

    int queryByName(@Param("menuName") String menuName, @Param("id") Integer id);

    int queryByNameAndParentId(@Param("menuName") String menuName, @Param("parentId") Integer parentId, @Param("id") Integer id);

}