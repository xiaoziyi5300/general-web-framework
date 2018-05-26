package com.cn.service.impl;

import com.cn.mapper.MenuMapper;
import com.cn.model.Menu;
import com.cn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lzf
 * desc
 * date 2018/5/26-20:05
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void save(Menu menu) {
        menuMapper.insertSelective(menu);
    }

    @Override
    public void update(Menu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }
}
