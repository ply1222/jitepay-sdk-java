package com.jite.pay.model.response;

import com.alibaba.fastjson.annotation.JSONField;

/** 退款订单 **/
public class RefundOrderResponse {

    /*
     * 支付退款单号
     */
    @JSONField(name = "refund_id")
    private String refundId;

    /*
     * 商户退款单号
     */
    @JSONField(name = "out_refund_no")
    private String outRefundNo;

    /*
     * 支付订单号
     */
    @JSONField(name = "transaction_id")
    private String transactionId;

    /*
      商户订单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /*
     * 退款成功时间
     */
    @JSONField(name = "success_time")
    private String successTime;

    /*
     * 退款创建时间
     */
    @JSONField(name = "create_time")
    private String createTime;

    /*
     * 退款状态
     */
    private String status;

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
