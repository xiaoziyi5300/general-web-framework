package com.cn.liu.exception;

/**
 * @author lzf
 * @date 2018-05-24
 * @desc
 */
public class RPCException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorCode;

    public RPCException(String message) {
        super(message);
    }

    public RPCException(String errorCode, String message) {
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
