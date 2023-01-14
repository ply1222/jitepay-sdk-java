package com.jite.pay.model.request;

/** 查询退款  **/
public class QueryRefundOrderRequest {
    /**
     * 商户退款单号
     */
    private String outRefundNo;

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }
}
