package com.cn.mapper;

import com.cn.model.RoleAndMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAndMenuMapper {
    int insert(RoleAndMenu record);

    int insertSelective(RoleAndMenu record);

    void deleteByRoleId(String rId);

    void save(@Param("list") List<RoleAndMenu> roleAndMenuList);

    List<RoleAndMenu> queryListByRoleId(String rId);
}