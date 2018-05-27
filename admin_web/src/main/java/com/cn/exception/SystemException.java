package com.cn.exception;

/**
 * @author lzf
 * desc
 * date 2018/5/27-14:34
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String errorCode, String message) {
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
