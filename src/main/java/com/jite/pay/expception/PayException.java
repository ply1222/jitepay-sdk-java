package com.jite.pay.expception;

/** 支付异常基类 **/
public class PayException extends RuntimeException {

    private static final long serialVersionUID = -5896431877288268263L;

    public PayException(String message) {
        super(message);
    }

    public PayException(String message, Throwable cause) {
        super(message, cause);
    }
}
