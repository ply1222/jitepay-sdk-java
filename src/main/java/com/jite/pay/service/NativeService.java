package com.jite.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.Config;
import com.jite.pay.core.HttpClient;
import com.jite.pay.model.request.CloseOrderRequest;
import com.jite.pay.model.request.PrepayRequest;
import com.jite.pay.model.request.QueryOrderRequest;
import com.jite.pay.model.response.NativeResponse;
import com.jite.pay.model.response.OrderResponse;

import static java.util.Objects.requireNonNull;

public class NativeService {

    private final HttpClient httpClient;

    private NativeService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

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
        public NativeService build() {
            return new NativeService(httpClient);
        }
    }

    /**
     * 创建订单
     * @param request PrepayRequest
     */
    public NativeResponse prepay(PrepayRequest request) {
        String requestPath = "https://api.jitepay.com/v1/pay/transactions/native";
        String response = httpClient.httpPost(requestPath, JSONObject.toJSONString(request));
        return JSONObject.parseObject(response, NativeResponse.class);
    }

    /**
     * 查询订单
     * @param request QueryOrderRequest
     * @return OrderResponse
     */
    public OrderResponse queryOrder(QueryOrderRequest request) {
        String requestPath = "https://api.jitepay.com/v1/pay/transactions/out-trade-no/{out_trade_no}";
        requestPath = requestPath.replace("{" + "out_trade_no" + "}", requireNonNull(request.getOutTradeNo()));
        String response = httpClient.httpGet(requestPath);
        return JSONObject.parseObject(response, OrderResponse.class);
    }

    /**
     * 关闭订单
     * @param request CloseOrderRequest
     */
    public void closeOrder(CloseOrderRequest request) {
        String requestPath = "https://api.jitepay.com/v1/pay/transactions/out-trade-no/{out_trade_no}/close";
        requestPath = requestPath.replace("{" + "out_trade_no" + "}", requireNonNull(request.getOutTradeNo()));
        httpClient.httpGet(requestPath);
    }
}
