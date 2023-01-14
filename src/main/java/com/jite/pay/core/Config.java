package com.jite.pay.core;

/**
 * 调用支付服务的所需配置
 */
public class Config {

    private final String merchantId;

    private final String serialNumber;

    private final String publicKey;

    private final String privateKey;

    private final String apiKey;

    public Config(Builder builder) {
        this.merchantId = builder.merchantId;
        this.serialNumber = builder.serialNumber;
        this.publicKey = builder.publicKey;
        this.privateKey = builder.privateKey;
        this.apiKey = builder.apiKey;
    }

    /**
     * JsapiService构造器
     */
    public static class Builder {

        private String merchantId;

        private String serialNumber;

        private String publicKey;

        private String privateKey;

        private String apiKey;

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return self();
        }

        public Builder publicKey(String publicKey) {
            this.publicKey = publicKey;
            return self();
        }

        public Builder privateKey(String privateKey) {
            this.privateKey = privateKey;
            return self();
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return self();
        }

        protected Builder self() {
            return this;
        }

        /**
         * 构造服务
         *
         * @return JsapiService
         */
        public Config build() {
            return new Config(this);
        }
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getApiKey() {
        return apiKey;
    }
}
