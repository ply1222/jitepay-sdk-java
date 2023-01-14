package com.jite.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.Config;
import com.jite.pay.core.HttpClient;
import com.jite.pay.model.request.QueryRefundOrderRequest;
import com.jite.pay.model.request.RefundOrderRequest;
import com.jite.pay.model.response.RefundOrderResponse;

import static java.util.Objects.requireNonNull;

public class RefundService {

    private final HttpClient httpClient;

    private RefundService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /** JsapiService构造器 */
    public static class Builder {

        private HttpClient httpClient;

        /**
         * 设置请求配置，以该配置构造默认的httpClient，若未调用httpClient()方法，则必须调用该方法
         *
         * @param config 请求配置
         * @return Builder
         */
        public Builder config(Config config) {
            this.httpClient = new HttpClient(config);
            return this;
        }

        /**
         * 设置自定义httpClient，若未调用config()，则必须调用该方法
         *
         * @param httpClient httpClient
         * @return Builder
         */
        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        /**
         * 构造服务
         *
         * @return JsapiService
         */
        public RefundService build() {
            return new RefundService(httpClient);
        }
    }

    /**
     * 申请退款
     * @param request RefundOrderRequest
     * @return RefundOrderResponse
     */
    public RefundOrderResponse create(RefundOrderRequest request) {
        String requestPath = "https://api.jitepay.com/v1/refund/domestic/refunds";
        String response = httpClient.httpPost(requestPath, JSONObject.toJSONString(request));
        return JSONObject.parseObject(response, RefundOrderResponse.class);
    }

    /**
     * 查询单笔退款
     * @param request QueryRefundOrderRequest
     */
    public RefundOrderResponse query(QueryRefundOrderRequest request) {
        String requestPath = "https://api.jitepay.com/v1/refund/domestic/refunds/{out_refund_no}";
        requestPath = requestPath.replace("{" + "out_refund_no" + "}", requireNonNull(request.getOutRefundNo()));
        String response = httpClient.httpGet(requestPath);
        return JSONObject.parseObject(response, RefundOrderResponse.class);
    }
}
