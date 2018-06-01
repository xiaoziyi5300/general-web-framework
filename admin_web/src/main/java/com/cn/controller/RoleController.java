package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lzf
 * @date 2018-05-31
 * @desc 角色
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    /***
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/role/index");
        return mv;
    }
}
