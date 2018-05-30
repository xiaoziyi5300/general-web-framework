package com.cn.controller.api;

import com.alibaba.fastjson.JSON;
import com.cn.controller.BaseController;
import com.cn.dto.menu.MenuReponseDto;
import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.util.ListUtils;
import com.cn.liu.util.RedisUtil;
import com.cn.model.Menu;
import com.cn.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzf
 * desc
 * date 2018/5/26-19:58
 */
@RestController
@RequestMapping("/api/menu")
public class MenuApiController extends BaseController {


    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisUtil redisUtil;

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
        reponseDto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        reponseDto.setStatus(CommonConstant.SUCCESS_STATUS);
        return reponseDto;
    }

    /***
     * 获取所有已经启用的一级菜单
     * @return
     */
    @RequestMapping(value = "/queryAllList", method = RequestMethod.POST)
    public MenuReponseDto queryAllList() {
        MenuReponseDto menuReponseDto = new MenuReponseDto();
        menuReponseDto.setList(menuService.queryAllList());
        menuReponseDto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        menuReponseDto.setStatus(CommonConstant.SUCCESS_STATUS);
        return menuReponseDto;
    }

    /***
     * 根据ID获取详情信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public MenuReponseDto queryById(String id) {
        MenuReponseDto menuReponseDto = new MenuReponseDto();
        menuReponseDto.setMenu(menuService.queryById(id));
        menuReponseDto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        menuReponseDto.setStatus(CommonConstant.SUCCESS_STATUS);
        return menuReponseDto;
    }

    /***
     * 递归查询系统菜单
     * @return
     */
    @RequestMapping("/queryMenuList")
    public List<Menu> queryMenuList() {
        List<Menu> menuList = null;
        if (redisUtil.get("menuList") == null) {
            menuList = menuService.queryMenuList();
            redisUtil.set("menuList", JSON.toJSONString(menuList), 86400L);
        } else {
            menuList = JSON.parseArray(redisUtil.get("menuList").toString(), Menu.class);
        }
        return menuList;
    }
}
