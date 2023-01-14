package com.jite.pay.model.request;

import com.jite.pay.model.entity.RefundAmount;
import com.jite.pay.model.entity.RefundGoodsDetail;

import java.util.List;

/** 申请退款 */
public class RefundOrderRequest {
    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 退款原因
     */
    private String reason;

    /**
     * 退款结果回调url
     */
    private String notifyUrl;

    /**
     * 金额信息
     */
    private RefundAmount amount;

    /**
     * 退款商品
     */
    private List<RefundGoodsDetail> goodsDetail;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public RefundAmount getAmount() {
        return amount;
    }

    public void setAmount(RefundAmount amount) {
        this.amount = amount;
    }

    public List<RefundGoodsDetail> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<RefundGoodsDetail> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }
}
