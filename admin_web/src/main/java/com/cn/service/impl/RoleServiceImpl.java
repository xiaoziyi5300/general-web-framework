package com.cn.service.impl;

import com.cn.liu.dto.ProductCategory;
import com.cn.liu.util.BeanUtil;
import com.cn.liu.util.ListUtils;
import com.cn.mapper.RoleAndMenuMapper;
import com.cn.mapper.RoleMapper;
import com.cn.model.Role;
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lzf
 * @date 2018-05-31
 * @desc
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleAndMenuMapper roleAndMenuMapper;

    @Override
    public PageBean<Role> queryListByPage(PageRequstParams pageRequstParams) {
        PageHelper.startPage(pageRequstParams.getPage(), pageRequstParams.getRows());
        List<Role> productCategoryModelList = roleMapper.selectByList();
        PageBean pageBean = new PageBean();
        pageBean.setRows(BeanUtil.mapper(productCategoryModelList, ProductCategory.class));
        pageBean.setTotal(totalCount());
        return pageBean;
    }

    @Override
    @Transactional
    public void save(Role role) {
        role.setDeleteMark(1);
        roleMapper.insertSelective(role);
        if (ListUtils.isNotEmpty(role.getMenuIds())) {
            roleAndMenuMapper.save(role.getId(), role.getMenuIds());
        }
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
        roleAndMenuMapper.deleteByRoleId(role.getId().toString());
        if (ListUtils.isNotEmpty(role.getMenuIds())) {
            roleAndMenuMapper.save(role.getId(), role.getMenuIds());
        }
    }

    @Override
    public Role queryById(String rId) {
        return roleMapper.selectByPrimaryKey(Integer.parseInt(rId));
    }

    @Override
    public void delete(String rId) {
        roleMapper.deleteByPrimaryKey(Integer.parseInt(rId));
        roleAndMenuMapper.deleteByRoleId(rId);
    }

    private int totalCount() {
        return roleMapper.totalCount();
    }
}
