package com.cn.liu.util;

import java.util.List;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
public class ListUtils {


    public static boolean isNotEmpty(List list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(List list) {
        if (isNotEmpty(list)) {
            return false;
        }
        return true;
    }
}
