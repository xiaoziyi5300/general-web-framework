package com.cn.liu.enumeration;

/**
 * @author lzf
 * desc
 * date 2018/5/5-11:55
 */
public enum UserEnum {
    USER_ACCOUNT_ERROR("101","账户或密码错误"),
    USER_SUCCESS("110","登录成功"),
    ;

    private String key;

    private String value;

    UserEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
