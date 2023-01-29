package com.jite.pay.service;

/**
 * 通知请求参数
 */
public class NotificationParam {
    private final String serialNumber;
    private final String signature;
    private final String message;
    private final String body;
    private final String signType;

    private NotificationParam(String serialNumber, String signature, String message, String body, String signType) {
        this.serialNumber = serialNumber;
        this.signature = signature;
        this.message = message;
        this.body = body;
        // 回调报文头新增Pay-Signature-Type，默认为PAY-SHA256-RSA2048
        if (signType == null || signType.isEmpty()) {
            this.signType = "PAY-SHA256-RSA2048";
        } else {
            this.signType = signType;
        }
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getMessage() {
        return message;
    }

    public String getSignature() {
        return signature;
    }

    public String getBody() {
        return body;
    }

    public String getSignType() {
        return signType;
    }

    public static class Builder {

        String signType;
        private String serialNumber;
        private String timestamp;
        private String nonce;
        private String signature;
        private String body;

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder nonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        public Builder signature(String signature) {
            this.signature = signature;
            return this;
        }

        public Builder signType(String signType) {
            this.signType = signType;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public NotificationParam build() {
            String message = timestamp + "\n" + nonce + "\n" + body + "\n";
            return new NotificationParam(serialNumber, signature, message, body, signType);
        }
    }
}
