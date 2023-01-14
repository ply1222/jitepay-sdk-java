package com.jite.pay.model.request;

/** 查询转账 **/
public class QueryTransferOrderRequest {
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
