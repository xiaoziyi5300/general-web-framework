package com.cn.service;

import com.cn.model.Menu;

import java.util.List;

/**
 * @author lzf
 * desc
 * date 2018/5/26-20:00
 */
public interface MenuService {

    void save(Menu menu);

    void update(Menu menu);

    List<Menu> queryAllList();

    Menu queryById(String id);

    List<Menu> queryMenuList();
}
