package com.jite.pay.exception;

/** 发送HTTP请求失败时抛出。例如构建请求参数失败、发送请求失败、I/O错误等。包含请求信息。 */
public class HttpException extends PayException {
    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
