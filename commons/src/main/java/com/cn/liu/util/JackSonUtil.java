package com.cn.liu.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author lzf
 * desc
 * date 2018/5/12-11:44
 */
public class JackSonUtil {


    /***
     * 序列化
     * @param resultClass
     * @return
     * @throws Exception
     */
    public static String getSting(Object resultClass)throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(resultClass);
    }

    /***
     * 反序列化
     * @param json
     * @param resultClass
     * @param <T>
     * @return
     * @throws IOException
     */
    public  static <T> T getObject(String json, Class<T> resultClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, resultClass);
    }


    public static void main(String[] args)throws Exception {
        TestClass t = new TestClass();
        t.setUserName("张三");
        t.setPassWord("123");
        System.out.println(getSting(t));
    }
}

class TestClass{
    private String userName;
    private String passWord;
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
}
