package com.cn.util;

import com.cn.liu.dto.User;
import com.cn.liu.util.JackSonUtil;
import com.cn.liu.util.RedisUtil;
import com.cn.liu.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lzf
 * desc
 * date 2018/5/12-11:35
 */
public class CookieUtil {

    @Autowired
    private RedisUtil redisUtil;

    /***
     * 设置token
     * @param
     * @param response
     */
    public static void setToken(String token, HttpServletResponse response) throws Exception {
        Cookie cookies = new Cookie("token", token);
        cookies.setMaxAge(3600 * 6);
        cookies.setPath("/");
        response.addCookie(cookies);
    }

    /***
     * 获取用户登录信息
     * @param request
     * @return
     * @throws Exception
     */
    public static String getUserInfo(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if ("token".equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }
}
