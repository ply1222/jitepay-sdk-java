package com.jite.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.AESUtil;
import com.jite.pay.core.Config;
import com.jite.pay.core.RSAUtil;
import com.jite.pay.core.Signature;
import com.jite.pay.exception.MessageException;
import jakarta.servlet.http.HttpServletRequest;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class NotificationService {

    private Signature signature;

    private AESUtil aes;

    public NotificationService() {
    }

    public NotificationService(Config config) {
        RSAUtil signer = new RSAUtil(config.getSerialNumber(), config.getPublicKey(),config.getPrivateKey());
        this.signature = new Signature(config.getMerchantId(), signer);
        this.aes = new AESUtil(config.getApiKey());
    }

    public String encrypt(String associatedData, String nonce, String ciphertext) throws GeneralSecurityException, IOException {
        return aes.encrypt(associatedData, nonce, ciphertext);
    }

    /**
     * 验签并解密报文
     *
     * @return JSONObject
     */
    public JSONObject parse(HttpServletRequest request) throws Exception {
        try {
            //获取请求头
            String pay_nonce = request.getHeader("Pay-Nonce");
            String pay_signature = request.getHeader("Pay-Signature");
            String pay_timestamp = request.getHeader("Pay-Timestamp");
            String pay_serial = request.getHeader("Pay-Serial");
            if (pay_nonce == null || pay_nonce.isEmpty() || pay_signature == null || pay_signature.isEmpty() ||
                    pay_timestamp == null || pay_timestamp.isEmpty() || pay_serial == null || pay_serial.isEmpty()) {
                throw new MessageException("PARAM_ERROR");
            }
            //获取请求主体
            String responseBody = requestBody(request);
            if (responseBody == null || responseBody.isEmpty()) {
                throw new MessageException("PARAM_ERROR");
            }

            //验证签名
            if (signature.verifySignature(pay_timestamp, pay_nonce, responseBody, pay_signature)) {
                //解密数据
                JSONObject obj = JSONObject.parseObject(responseBody);
                JSONObject res = obj.getJSONObject("resource");
                String ciphertext = res.getString("ciphertext");
                String associatedData = res.getString("associated_data");
                String nonce = res.getString("nonce");
                String result = aes.decrypt(associatedData, nonce, ciphertext);
                obj.put("result", JSONObject.parse(result));
                return obj;
            }
            return null;
        } catch (Exception e) {
            throw new MessageException("PARAM_ERROR");
        }
    }

    /**
     * 获取请求主体
     *
     * @return String
     */
    private String requestBody(HttpServletRequest request) {
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                sb.append(inputStr);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
