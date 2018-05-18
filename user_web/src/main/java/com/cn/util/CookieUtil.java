package com.cn.util;

import com.cn.liu.dto.User;
import com.cn.liu.util.JackSonUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lzf
 * desc
 * date 2018/5/12-11:35
 */
public class CookieUtil {

    /***
     * 设置token
     * @param user
     * @param response
     */
    public static void setToken(User user, HttpServletResponse response)throws Exception{
        Cookie cookies = new Cookie("user", JackSonUtil.getSting(user));
        cookies.setMaxAge(3600*6);
        response.addCookie(cookies);
    }

    /***
     * 获取用户登录信息
     * @param request
     * @return
     * @throws Exception
     */
    public static User getUserInfo(HttpServletRequest request)throws Exception{
        Cookie[] cookies = request.getCookies();
        for(Cookie c :cookies ){
            return JackSonUtil.getObject(c.getValue(),User.class);
        }
        return null;
    }
}
