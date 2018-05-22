package com.cn.liu.dto;


import com.cn.liu.base.BaseModel;

import java.io.Serializable;

/**
 * @author lzf
 * desc 用户
 * date 2018/5/4-20:19
 */
public class User extends BaseModel implements Serializable{

    private int id;
    private String userName;
    private String passWord;
    private String headIcon;
    private String nickName;
    private String mobilePhone;
    private String email;
    private String isSale;//是否为卖家
    private String isAccount;//是否为会员

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }
}
