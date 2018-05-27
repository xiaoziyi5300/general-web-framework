package com.cn.service.impl;

import com.cn.exception.SystemException;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.util.StrUtil;
import com.cn.mapper.MenuMapper;
import com.cn.model.Menu;
import com.cn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (!checkbean(menu)) {
            throw new SystemException("菜单名称重复");
        }
        menuMapper.insertSelective(menu);
    }

    @Override
    public void update(Menu menu) {
        if (!checkbean(menu)) {
            throw new SystemException("菜单名称重复");
        }
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public List<Menu> queryAllList() {
        return menuMapper.queryAllList();
    }

    @Override
    public Menu queryById(String id) {
        if (StrUtil.isEmpty(id)) {
            return null;
        }
        return menuMapper.queryById(id);
    }

    @Override
    public List<Menu> queryMenuList() {
        return menuMapper.queryMenuList();
    }

    private boolean checkbean(Menu menu) {
        int count = 0;
        if (CommonConstant.INTEGER_ONE == menu.getIsParent()) {
            count = menuMapper.queryByName(menu.getMenuName(), menu.getId());
        } else {
            count = menuMapper.queryByNameAndParentId(menu.getMenuName(), menu.getParentId(), menu.getId());
        }
        if (count > 0) {
            return false;
        }
        return true;
    }
}
