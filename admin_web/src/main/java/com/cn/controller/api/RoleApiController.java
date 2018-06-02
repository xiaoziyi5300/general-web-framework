package com.cn.controller.api;

import com.alibaba.fastjson.JSON;
import com.cn.controller.BaseController;
import com.cn.dto.role.RoleReponseDto;
import com.cn.dto.role.ZtreeReponseDto;
import com.cn.liu.util.JackSonUtil;
import com.cn.liu.util.ListUtils;
import com.cn.liu.util.StrUtil;
import com.cn.model.Menu;
import com.cn.model.Role;
import com.cn.liu.base.PageBean;
import com.cn.liu.base.PageRequstParams;
import com.cn.liu.base.ReponseDto;
import com.cn.liu.constant.CommonConstant;
import com.cn.liu.dto.User;
import com.cn.service.MenuService;
import com.cn.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lzf
 * @date 2018-05-31
 * @desc
 */
@RestController
@RequestMapping("/api/role")
public class RoleApiController extends BaseController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /***
     * 角色
     * @param pageRequstParams
     * @return
     */
    @RequestMapping(value = "/queryListByPage", method = RequestMethod.POST)
    public PageBean<Role> queryListByPage(PageRequstParams pageRequstParams) {
        return roleService.queryListByPage(pageRequstParams);
    }

    /***
     * 保存或修改
     * @param request
     * @param strData
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ReponseDto save(HttpServletRequest request, String strData) throws Exception {
        ReponseDto dto = new ReponseDto();
        if (StrUtil.isNotEmpty(strData)) {
            User user = getUserInfo(request);
            Date now = new Date();
            Role role = JackSonUtil.getObject(strData, Role.class);
            if (role.getId() == null) {
                role.setCreateDate(now);
                role.setCreateUserId(user.getId());
                role.setCreateUserName(user.getUserName());
                roleService.save(role);
            } else {
                role.setModifyDate(now);
                role.setModifyUserId(user.getId());
                role.setModifyUserName(user.getUserName());
                roleService.update(role);
            }
        }
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        return dto;
    }

    /***
     *
     * @param rId
     * @return
     */
    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public RoleReponseDto queryById(String rId) {
        RoleReponseDto dto = new RoleReponseDto();
        dto.setRole(roleService.queryById(rId));
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        return dto;
    }

    /***
     * 删除
     * @param rId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ReponseDto delete(String rId) {
        RoleReponseDto dto = new RoleReponseDto();
        roleService.delete(rId);
        dto.setStatus(CommonConstant.SUCCESS_STATUS);
        dto.setMessage(CommonConstant.SUCCESS_MESSAGE);
        return dto;
    }

    /***
     * 分装菜单数据到ztree
     * @return
     */
    @RequestMapping(value = "/ZtreeReponseDto")
    public List<ZtreeReponseDto> selectTreeRoleList() {
        List<ZtreeReponseDto> list = new ArrayList<>();
        List<Menu> menuList = menuService.queryMenuList();
        ZtreeReponseDto dto = null;
        ZtreeReponseDto dto1 = null;
        List<ZtreeReponseDto> secondList = null;
        if (ListUtils.isNotEmpty(menuList)) {
            for (Menu m : menuList) {
                dto = new ZtreeReponseDto();
                dto.setId(m.getId());
                dto.setName(m.getMenuName());
                dto.setIsParent(true);
                dto.setpId(0);
                List<Menu> secondMenuList = m.getChildList();
                if (ListUtils.isNotEmpty(secondMenuList)) {
                    secondList = new ArrayList<>();
                    for (Menu second : secondMenuList) {
                        dto1 = new ZtreeReponseDto();
                        dto1.setId(second.getId());
                        dto1.setName(second.getMenuName());
                        dto1.setIsParent(false);
                        dto1.setpId(m.getId());
                        secondList.add(dto1);
                    }
                }
                dto.setChildren(secondList);
                list.add(dto);
                secondList = null;
            }
        }
        return list;
    }
}
