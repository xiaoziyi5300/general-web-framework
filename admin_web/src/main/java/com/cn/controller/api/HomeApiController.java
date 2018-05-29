package com.cn.controller.api;

import com.cn.dto.HomeReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.dto.User;
import com.cn.liu.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lzf
 * desc
 * date 2018/5/26-12:35
 */
@RestController
@RequestMapping("/api/home")
public class HomeApiController {

    private static Logger logger = Logger.getLogger(HomeApiController.class);

    @Autowired(required = false)
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HomeReponseDto login(String userName, String passWord) {
        HomeReponseDto dto = new HomeReponseDto();
        User user = userService.login(userName, passWord);
        if (user == null) {
            dto.setMessage("用户名或密码错误");
            dto.setStatus(CommonConstant.FAIL_STATUS);
        } else {
            dto.setStatus(CommonConstant.SUCCESS_STATUS);
            dto.setUserName(userName);
            dto.setMessage("登陆成功");
        }
        return dto;
    }

    /***
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/login.jsp");
    }
}
