package com.jite.pay.model.request;

import com.jite.pay.model.entity.Bankcard;

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
     * 收款方账号
     */
    private String identity;

    /**
     * 收款方账号类型: ALIPAY_ACCOUNT, WECHAT_ACCOUNT, BANKCARD_ACCOUNT
     */
    private String identityType;

    /**
     * 收款方用户名
     */
    private String name;

    /**
     * 收款方手机号码
     */
    private String mobile;

    /**
     * 收款方身份证号码
     */
    private String idCardNum;

    /**
     * 银行信息
     */
    private Bankcard bankcard;

    /**
     * 付款金额
     */
    private String amount;

    /**
     * 付款说明
     */
    private String description;

    /**
     * 回调地址
     */
    private String notifyUrl;

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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public Bankcard getBankcard() {
        return bankcard;
    }

    public void setBankcard(Bankcard bankcard) {
        this.bankcard = bankcard;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
