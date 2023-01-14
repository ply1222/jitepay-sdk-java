package com.jite.pay.model.request;

/** 查询订单 */
public class QueryOrderRequest {
    /**
     * 商户订单号
     */
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
