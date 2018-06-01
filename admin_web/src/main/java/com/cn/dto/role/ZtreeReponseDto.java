package com.cn.dto.role;

import java.util.List;

/**
 * @author lzf
 * @date 2018-06-01
 * @desc
 */
public class ZtreeReponseDto {

    private String name;

    private int id;

    private int pId;

    private boolean isParent;

    private boolean open = true;

    private List<ZtreeReponseDto> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<ZtreeReponseDto> getChildren() {
        return children;
    }

    public void setChildren(List<ZtreeReponseDto> children) {
        this.children = children;
    }
}
