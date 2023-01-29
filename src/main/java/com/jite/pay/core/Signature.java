package com.jite.pay.core;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Signature {
    private final String schema = "PAY-SHA256-RSA2048";

    private String merchantId;

    private RSAUtil signer;

    public Signature() {
    }

    public Signature(Config config) {
        this.signer = new RSAUtil(config.getSerialNumber(), config.getPublicKey(), config.getPrivateKey());
    }

    public Signature(String merchantId, RSAUtil signer) {
        this.merchantId = merchantId;
        this.signer = signer;
    }

    public void setPublicKey(String publicKey) {
        this.signer = new RSAUtil(null, publicKey, null);
    }

    public String getSchema() {
        return schema;
    }

    public String getAuthorization(String uri, String httpMethod, String signBody) {
        return getSchema() + " " + getToken(uri, httpMethod, signBody);
    }

    /**
     * 签名生成
     *
     * @param method 请求方式
     * @param url    请求地址
     * @param body   请求主体 GET请求为空
     * @return String
     */
    private String getToken(String url, String method, String body) {
        String nonceStr = getNonceStr();
        long timestamp = getTimestamp();
        String message = buildMessage(method, url, timestamp, nonceStr, body);
        String signature = signer.sign(message);
        return "mchid=\"" + merchantId + "\","
                + "nonce_str=\"" + nonceStr + "\","
                + "timestamp=\"" + timestamp + "\","
                + "serial_no=\"" + signer.getSerialNo() + "\","
                + "signature=\"" + signature + "\"";
    }

    /**
     * 验证签名
     *
     * @param method 请求方式
     * @param url    请求地址
     * @param body   请求主体
     * @param map    请求Authorization头
     * @return Boolean
     */
    public Boolean verifyToken(String method, String url, String body, Map<String, String> map) {
        String data = buildMessage(method, url, Long.parseLong(map.get("timestamp")), map.get("nonce_str"), body);
        return signer.verifySign(data, map.get("signature"));
    }

    /**
     * 构造验签名串
     *
     * @param method    请求方式
     * @param url       请求地址
     * @param timestamp 时间戳
     * @param nonceStr  随机数
     * @param body      请求主体
     * @return String
     */
    private String buildMessage(String method, String url, long timestamp, String nonceStr, String body) {
        return method + "\n"
                + url + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + body + "\n";
    }

    /**
     * 将字符串转换为Map
     *
     * @param str Map字符串
     * @return Map
     */
    public Map<String, String> parseToken(String str) {
        str = str.substring(schema.length() + 1);
        String[] strArr = str.split(",");
        Map<String, String> map = new HashMap<>();
        for (String string : strArr) {
            String key = string.split("=")[0];
            String value = string.split("=")[1];
            String key1 = key.trim();
            String value1 = value.trim().replace("\"", "");
            map.put(key1, value1);
        }
        return map;
    }

    /**
     * 签名生成
     *
     * @param timestamp    应答时间戳 Pay-Timestamp
     * @param nonce        应答随机串 Pay-Nonce
     * @param responseBody 应答主体
     * @return String
     */
    public String getSignature(String timestamp, String nonce, String responseBody) {
        String data = buildNotify(timestamp, nonce, responseBody);
        return signer.sign(data);
    }

    /**
     * 签名验证
     *
     * @param message   验签名串
     * @param signature 应答签名
     * @return
     */
    public Boolean verify(String message, String signature) {
        return signer.sign(message).equals(signature);
    }

    /**
     * 构造验签名串
     * header: Pay-Nonce,Pay-Signature,Pay-Timestamp,Pay-Serial
     *
     * @param timestamp    应答时间戳 Pay-Timestamp
     * @param nonce        应答随机串 Pay-Nonce
     * @param responseBody 应答主体
     * @return String
     */
    private String buildNotify(String timestamp, String nonce, String responseBody) {
        return timestamp + "\n"
                + nonce + "\n"
                + responseBody + "\n";
    }

    /**
     * 获取时间戳
     *
     * @return long
     */
    public long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 随机串
     *
     * @return String
     */
    public String getNonceStr() {
        byte[] nonce = new byte[16];
        SecureRandom sRandom = new SecureRandom();
        sRandom.nextBytes(nonce);
        return bytesToHex(nonce);
    }

    /**
     * 字节数组转换成十六进制字符串
     * @param bytes 字节
     * @return String
     */
    public String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
