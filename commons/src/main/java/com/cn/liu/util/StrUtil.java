package com.cn.liu.util;

/**
 * @author lzf
 * desc
 * date 2018/5/27-13:54
 */
public class StrUtil {

    public static boolean isNotEmpty(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        if (isNotEmpty(str)) {
            return false;
        }
        return true;
    }
}
