package com.jite.pay.model.entity;

/** 收款方 **/
public class Payee {
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
     * 收款账户类型(1：对公,2：对私)
     */
    private Integer accountType;

    /**
     * 收款方开户行
     */
    private String bankName;

    /**
     * 收款银行所在省
     */
    private String bankProvince;

    /**
     * 收款银行所在市
     */
    private String bankCity;

    /**
     * 收款银行所属支行
     */
    private String bankBranchName;

    /**
     * 银行支行联行号
     */
    private String bankCode;

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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
