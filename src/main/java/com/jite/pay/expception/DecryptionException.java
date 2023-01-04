package com.jite.pay.expception;

/** 解密异常 **/
public class DecryptionException extends PayException {
    private static final long serialVersionUID = 1L;

    public DecryptionException(String message) {
        super(message);
    }

    public DecryptionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
