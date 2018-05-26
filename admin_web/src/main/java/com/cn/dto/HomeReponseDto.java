package com.cn.dto;

import com.cn.liu.base.ReponseDto;

/**
 * @author lzf
 * desc
 * date 2018/5/26-12:36
 */
public class HomeReponseDto extends ReponseDto {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
