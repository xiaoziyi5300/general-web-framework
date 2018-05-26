package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lzf
 * desc
 * date 2018/5/26-15:08
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/menu/menuList");
        return mv;
    }
}
