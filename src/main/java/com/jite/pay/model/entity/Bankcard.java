package com.jite.pay.model.entity;

public class Bankcard {
    /**
     * 收款账户类型(1：对公,2：对私)
     */
    private Integer accountType;

    /**
     * 收款方开户行
     */
    private String bankName;

    /**
     * 省
     */
    private String bankProvince;

    /**
     * 市
     */
    private String bankCity;

    /**
     * 支行
     */
    private String bankBranchName;

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
}
