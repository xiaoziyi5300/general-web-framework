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

}
