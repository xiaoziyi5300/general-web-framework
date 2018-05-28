package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lzf
 * @date 2018-05-28
 * @desc 商品类目
 */
@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {

    /***
     * 商品类目主页
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/productCategory/index");
        return mv;
    }
}
