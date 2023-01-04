package com.jite.pay.model.entity;


//证书管理
public class Secret {

    private String publicKey;//RSA公钥

    private String privateKey;//RSA私钥

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

}
