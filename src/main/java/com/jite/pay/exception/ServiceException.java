package com.jite.pay.exception;

import com.alibaba.fastjson.JSONObject;

/** 发送HTTP请求成功，返回异常时抛出。例如返回状态码小于200或大于等于300、返回体参数不完整。 */
public class ServiceException extends PayException {
    private static final long serialVersionUID = -7174975090366956652L;

    private final int httpStatusCode;
    private final String responseBody;
    private String errorCode;
    private String errorMessage;

    /**
     * 返回状态码小于200或大于300调用
     *
     * @param httpStatusCode http状态码
     * @param responseBody http返回体
     */
    public ServiceException(int httpStatusCode, String responseBody) {
        super(String.format("Wrong HttpStatusCode[%d]%nhttResponseBody[%.1024s]", httpStatusCode, responseBody));
        this.httpStatusCode = httpStatusCode;
        this.responseBody = responseBody;
        if (responseBody != null && !responseBody.isEmpty()) {
            JSONObject jsonObject = JSONObject.parseObject(responseBody);
            this.errorCode = jsonObject.getString("code");
            this.errorMessage = jsonObject.getString("msg");
        }
    }

    /**
     * 获取序列化版本UID
     *
     * @return UID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 获取HTTP状态码
     *
     * @return HTTP状态码
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * 获取HTTP返回体
     *
     * @return HTTP返回体
     */
    public String getResponseBody() {
        return responseBody;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
