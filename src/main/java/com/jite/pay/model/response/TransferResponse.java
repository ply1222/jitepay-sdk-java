package com.jite.pay.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.jite.pay.model.entity.Payee;

/**
 * 转账
 **/
public class TransferResponse {
    /**
     * 商户号
     */
    private Long mchid;

    /**
     * 商户号
     */
    private Long appid;

    /**
     * 付款单号
     */
    @JSONField(name = "payment_no")
    private String paymentNo;

    /**
     * 商户付款单号
     */
    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 收款方
     */
    private Payee payee;

    /**
     * 代付金额
     */
    private String amount;

    /**
     * 付款说明
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    @JSONField(name = "create_time")
    private String createTime;

    /**
     * 成功时间
     */
    @JSONField(name = "success_time")
    private String successTime;

    public Long getMchid() {
        return mchid;
    }

    public void setMchid(Long mchid) {
        this.mchid = mchid;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }
}
