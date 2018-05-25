package com.cn.liu.exception;

/**
 * @author lzf
 * desc 业务异常
 * date 2018/5/18-21:21
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
