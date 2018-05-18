package com.cn.liu.service;

import com.cn.liu.dto.User;

/**
 * @author lzf
 * desc
 * date 2018/5/4-20:25
 */
public interface UserService {

    void save(User user);
    void update(User user);
    void deleteById(String id);
    User login(String userName, String passWord);
}
