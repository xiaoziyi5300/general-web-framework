package com.cn.liu.util;


import com.cn.liu.constant.CommonConstant;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author lzf
 * desc
 * date 2018/5/27-13:54
 */
public class StrUtil {

    static final BASE64Decoder decoder = new BASE64Decoder();
    static final BASE64Encoder encoder = new BASE64Encoder();

    //加密
    public static String encodeStr(String str) {
        try {
            return encoder.encode((CommonConstant.TOKEN_KEY + str).getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    //解密
    public static String dencodeStr(String str) {
        try {
            return new String(decoder.decodeBuffer(str), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }


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

    /**
     * 含有下划线转为驼峰字段
     *
     * @param column
     * @return
     */
    public static String columnToProperty(String column) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (column == null || isEmpty(column)) {
            // 没必要转换
            return "";
        } else if (!column.contains("_")) {
            // 不含下划线，仅将首字母小写
            return column.substring(0, 1).toLowerCase() + column.substring(1);
        } else {
            // 用下划线将原始字符串分割
            String[] columns = column.split("_");
            for (String columnSplit : columns) {
                // 跳过原始字符串中开头、结尾的下换线或双重下划线
                if (isEmpty(column)) {
                    continue;
                }
                // 处理真正的驼峰片段
                if (result.length() == 0) {
                    // 第一个驼峰片段，全部字母都小写
                    result.append(columnSplit.toLowerCase());
                } else {
                    // 其他的驼峰片段，首字母大写
                    result.append(columnSplit.substring(0, 1).toUpperCase()).append(columnSplit.substring(1).toLowerCase());
                }
            }
            return result.toString();
        }
    }
}
