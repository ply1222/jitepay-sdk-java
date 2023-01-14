package com.jite.pay.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.jite.pay.model.entity.Bankcard;

/**
 * 转账
 **/
public class TransferResponse {
    /**
     * 商户号
     */
    private Long mchid;

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
     * 收款方账号
     */
    private String identity;

    /**
     * 收款方账号类型: ALIPAY_ACCOUNT, WECHAT_ACCOUNT, BANKCARD_ACCOUNT
     */
    @JSONField(name = "identity_type")
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
    @JSONField(name = "id_card_num")
    private String idCardNum;

    /**
     * 银行信息
     */
    private Bankcard bankcard;

    /**
     * 代付金额
     */
    private String amount;

    /**
     * 付款说明
     */
    private String description;

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
