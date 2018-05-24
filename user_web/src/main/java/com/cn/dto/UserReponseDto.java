package com.cn.dto;

import com.cn.liu.base.ReponseDto;
import com.cn.liu.dto.User;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
public class UserReponseDto extends ReponseDto {

    private String userName;
    private String userIcon;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
}
