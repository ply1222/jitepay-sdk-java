package com.jite.pay.model.entity;

import com.alibaba.fastjson.annotation.JSONField;

/** 通知数据 */
public class Resource {
    private String algorithm;

    private String ciphertext;

    @JSONField(name = "associated_data")
    private String associatedData;

    private String nonce;

    @JSONField(name = "original_type")
    private String originalType;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getAssociatedData() {
        return associatedData;
    }

    public void setAssociatedData(String associatedData) {
        this.associatedData = associatedData;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType;
    }
}
