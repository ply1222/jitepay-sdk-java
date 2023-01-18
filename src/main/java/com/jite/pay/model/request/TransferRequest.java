package com.jite.pay.model.request;

import com.jite.pay.model.entity.Payee;
import com.jite.pay.model.entity.UserInfo;

import java.math.BigDecimal;

/** 转账 */
public class TransferRequest {
    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 商户付款单号
     */
    private String outTradeNo;

    /**
     * 付款金额
     */
    private BigDecimal amount;

    /**
     * 转账备注
     */
    private String remark;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 收款方信息
     */
    private Payee payee;

    /**
     * 支付超时时间
     */
    private String timeExpire;

    /**
     * 用户信息
     */
    private UserInfo userInfo;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
