package com.cn.interceptor;

import com.cn.liu.dto.User;
import com.cn.liu.util.StrUtil;
import com.cn.util.CookieUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lzf
 * @date 2018-05-30
 * @desc 拦截器
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("SecurityInterceptor --->  " + request.getRequestURI());
        response.addHeader("Access-Control-Allow-Origin", "*");
        String cookie = CookieUtil.getUserInfo(request);
        if (StrUtil.isNotEmpty(cookie)) {
            return true;
        }
        System.out.println("未登录用户");
        response.sendRedirect("/500.jsp");
        return false;
    }
}
