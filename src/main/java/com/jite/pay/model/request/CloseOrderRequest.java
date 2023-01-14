package com.jite.pay.model.request;

/** 关闭订单 **/
public class CloseOrderRequest {
    /**
     * 商户号订单号
     */
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
