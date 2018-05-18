package com.cn.liu.base;

/**
 * @author lzf
 * desc 统一返回对象
 * date 2018/5/5-11:46
 */
public class ReponseDto {

    private String message;
    private String status;
    private String code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
