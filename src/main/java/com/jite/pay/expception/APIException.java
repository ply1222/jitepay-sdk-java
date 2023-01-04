package com.jite.pay.expception;

/**
 * 自定义异常
 */
public class APIException extends RuntimeException {
    private Integer code;

    private String message;

    public APIException(String message) {
        this.code = 422;
        this.message = message;
    }

    public APIException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
}
