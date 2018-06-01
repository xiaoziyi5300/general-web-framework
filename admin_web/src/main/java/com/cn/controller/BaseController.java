package com.cn.controller;

import com.cn.liu.constant.CommonConstant;
import com.cn.liu.dto.User;
import com.cn.liu.exception.BusinessException;
import com.cn.liu.util.JackSonUtil;
import com.cn.liu.util.RedisUtil;
import com.cn.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzf
 * @date 2018-05-30
 * @desc
 */
public class BaseController {

    @Autowired
    private RedisUtil redisUtil;

    public User getUserInfo(HttpServletRequest request) {
        try {
            String cookie = CookieUtil.getUserInfo(request);
            User user = JackSonUtil.getObject(redisUtil.get(CommonConstant.TOKEN_KEY + cookie).toString(), User.class);
            if (user == null) {
                throw new BusinessException("用户未登录");
            }
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
