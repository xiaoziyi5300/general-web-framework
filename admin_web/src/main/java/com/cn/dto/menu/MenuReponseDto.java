package com.cn.dto.menu;

import com.cn.liu.base.ReponseDto;
import com.cn.model.Menu;

import java.util.List;

/**
 * @author lzf
 * desc
 * date 2018/5/27-13:43
 */
public class MenuReponseDto extends ReponseDto {

    private List<Menu> list;

    private Menu menu;

    public List<Menu> getList() {
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
