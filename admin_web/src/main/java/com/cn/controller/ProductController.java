package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lzf
 * desc
 * date 2018/6/3-16:12
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/product/index");
        return mv;
    }
}
