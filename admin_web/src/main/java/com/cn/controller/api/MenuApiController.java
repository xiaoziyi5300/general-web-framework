package com.cn.controller.api;

import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.model.Menu;
import com.cn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzf
 * desc
 * date 2018/5/26-19:58
 */
@RestController
@RequestMapping("/api/menu")
public class MenuApiController {


    @Autowired
    private MenuService menuService;

    /***
     * 保存或修改
     * @param menu
     * @return
     */
    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST)
    public ReponseDto saveMenu(Menu menu) {
        ReponseDto reponseDto = new ReponseDto();
        if (menu.getId() == null) {
            menuService.save(menu);
        } else {
            menuService.update(menu);
        }
        reponseDto.setMessage("操作成功");
        reponseDto.setStatus(CommonConstant.SUCCESS_STATUS);
        return reponseDto;
    }
}
