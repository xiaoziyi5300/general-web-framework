package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lzf
 * desc
 * date 2018/5/26-12:33
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /****
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/index");
        return mv;
    }
}
