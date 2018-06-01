package com.cn.service;

import com.cn.model.Role;
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;

/**
 * @author lzf
 * @date 2018-05-31
 * @desc
 */
public interface RoleService {
    PageBean<Role> queryListByPage(PageRequstParams pageRequstParams);

    void save(Role role);

    void update(Role role);

    Role queryById(String rId);

    void delete(String rId);
}
