package com.cn.service.impl;

import com.cn.liu.exception.BusinessException;
import com.cn.liu.util.BeanUtil;
import com.cn.liu.util.ListUtils;
import com.cn.mapper.RoleAndMenuMapper;
import com.cn.mapper.RoleMapper;
import com.cn.model.Role;
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.model.RoleAndMenu;
import com.cn.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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
        List<Role> roleModelList = roleMapper.selectByList();
        PageBean pageBean = new PageBean();
        pageBean.setRows(BeanUtil.mapper(roleModelList, Role.class));
        pageBean.setTotal(totalCount());
        return pageBean;
    }

    @Override
    @Transactional
    public void save(Role role) {
        role.setDeleteMark(1);
        if (!checkRoleName(role.getRoleName())) {
            throw new BusinessException("角色名称已经使用了");
        }
        roleMapper.insertSelective(role);
        if (ListUtils.isNotEmpty(role.getMenuIds())) {
            roleAndMenuMapper.save(getList(role.getId(), role.getMenuIds()));
        }
    }

    @Override
    public void update(Role role) {
        if (!checkRoleName(role.getRoleName())) {
            throw new BusinessException("角色名称已经使用了");
        }
        roleMapper.updateByPrimaryKeySelective(role);
        roleAndMenuMapper.deleteByRoleId(role.getId().toString());
        if (ListUtils.isNotEmpty(role.getMenuIds())) {
            roleAndMenuMapper.save(getList(role.getId(), role.getMenuIds()));
        }
    }

    @Override
    public Role queryById(String rId) {
        Role role = roleMapper.selectByPrimaryKey(Integer.parseInt(rId));
        List<RoleAndMenu> ls = roleAndMenuMapper.queryListByRoleId(rId);
        List<String> list = new ArrayList<>();
        if (ListUtils.isNotEmpty(ls)) {
            for (RoleAndMenu r : ls) {
                list.add(r.getMenuId().toString());
            }
        }
        role.setMenuIds(list);
        return role;
    }

    @Override
    public void delete(String rId) {
        roleMapper.deleteByPrimaryKey(Integer.parseInt(rId));
        roleAndMenuMapper.deleteByRoleId(rId);
    }

    private int totalCount() {
        return roleMapper.totalCount();
    }

    private List<RoleAndMenu> getList(Integer roleId, List<String> ls) {
        List<RoleAndMenu> list = new ArrayList<>();
        RoleAndMenu roleAndMenu;
        for (String s : ls) {
            roleAndMenu = new RoleAndMenu();
            roleAndMenu.setRoleId(roleId);
            roleAndMenu.setMenuId(Integer.parseInt(s));
            list.add(roleAndMenu);
        }
        return list;
    }

    private boolean checkRoleName(String roleName) {
        int count = roleMapper.queryRoleByName(roleName);
        if (count > 0) {
            return false;
        }
        return true;
    }
}
