package com.jite.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.Config;
import com.jite.pay.core.HttpClient;
import com.jite.pay.core.RSAUtil;
import com.jite.pay.exception.ValidationException;
import com.jite.pay.model.entity.Payee;
import com.jite.pay.model.request.QueryTransferOrderRequest;
import com.jite.pay.model.request.TransferRequest;
import com.jite.pay.model.response.TransferResponse;

import static java.util.Objects.requireNonNull;

public class TransferService {
    private final HttpClient httpClient;

    private final RSAUtil signer;

    private TransferService(HttpClient httpClient, RSAUtil signer) {
        this.httpClient = httpClient;
        this.signer = signer;
    }

    /**
     * JsapiService构造器
     */
    public static class Builder {

        private HttpClient httpClient;

        private RSAUtil signer;

        /**
         * 设置请求配置，以该配置构造默认的httpClient，若未调用httpClient()方法，则必须调用该方法
         *
         * @param config 请求配置
         * @return Builder
         */
        public Builder config(Config config) {
            this.httpClient = new HttpClient(config);
            this.signer = new RSAUtil(config.getSerialNumber(), config.getPublicKey(), config.getPrivateKey());
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
        public TransferService build() {
            return new TransferService(httpClient, signer);
        }
    }

    /**
     * 创建
     *
     * @param request TransferRequest
     * @return TransferResponse
     */
    public TransferResponse create(TransferRequest request) {
        String requestPath = "https://api.jitepay.com/v1/transfer/promotion/transfer";
        // 订单号
        if (request.getOutTradeNo() == null || request.getOutTradeNo().isEmpty()) {
            throw new ValidationException("'outTradeNo' must not be null");
        }

        // 收款人信息
        Payee payee = request.getPayee();
        if (payee == null) {
            throw new ValidationException("'payee' must not be null");
        }
        // 类型
        if (payee.getIdentityType() == null || payee.getIdentityType().isEmpty()) {
            throw new ValidationException("'identityType' must not be null");
        }

        // 账号
        if (payee.getIdentity() == null || payee.getIdentity().isEmpty()) {
            throw new ValidationException("'identity' must not be null");
        }
        payee.setIdentity(signer.encrypt(payee.getIdentity()));

        // 真实姓名
        if (payee.getName() == null || payee.getName().isEmpty()) {
            throw new ValidationException("'name' must not be null");
        }
        payee.setName(signer.encrypt(payee.getName()));

        // 手机号码
        if (payee.getMobile() != null && !payee.getMobile().isEmpty()) {
            payee.setMobile(signer.encrypt(payee.getMobile()));
        }

        // 身份证号码
        if (payee.getIdCardNum() != null && !payee.getIdCardNum().isEmpty()) {
            payee.setIdCardNum(signer.encrypt(payee.getIdCardNum()));
        }
        request.setPayee(payee);

        // 金额
        if (request.getAmount() == null) {
            throw new ValidationException("'amount' must not be null");
        }

        // 描述
        if (request.getRemark() == null || request.getRemark().isEmpty()) {
            throw new ValidationException("'remark' must not be null");
        }

        String response = httpClient.httpPost(requestPath, JSONObject.toJSONString(request));
        return JSONObject.parseObject(response, TransferResponse.class);
    }

    /**
     * 查询转账
     *
     * @param request QueryTransferOrderRequest
     * @return TransferResponse
     */
    public TransferResponse query(QueryTransferOrderRequest request) {
        String requestPath = "http://192.168.1.100:8181/v1/transfer/promotion/transfer/{out_trade_no}";
        requestPath = requestPath.replace("{" + "out_trade_no" + "}", requireNonNull(request.getOutTradeNo()));
        String response = httpClient.httpGet(requestPath);
        return JSONObject.parseObject(response, TransferResponse.class);
    }
}
