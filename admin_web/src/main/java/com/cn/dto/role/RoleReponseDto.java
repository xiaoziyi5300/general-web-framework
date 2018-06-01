package com.cn.dto.role;

import com.cn.liu.base.ReponseDto;
import com.cn.model.Role;

/**
 * @author lzf
 * @date 2018-05-31
 * @desc
 */
public class RoleReponseDto extends ReponseDto {

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
