package com.jite.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.jite.pay.core.AESUtil;
import com.jite.pay.core.Config;
import com.jite.pay.core.RSAUtil;
import com.jite.pay.core.Signature;
import com.jite.pay.exception.MessageException;
import com.jite.pay.exception.ValidationException;
import com.jite.pay.model.entity.Notification;
import com.jite.pay.model.entity.Resource;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static java.util.Objects.requireNonNull;

public class NotificationParser {

    private final Signature signature;

    private final AESUtil aes;

    public NotificationParser(Config config) {
        RSAUtil signer = new RSAUtil(config.getSerialNumber(), config.getPublicKey(), config.getPrivateKey());
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
    public <T> T parse(NotificationParam notificationParam, Class<T> decryptObjectClass) {
        //验证数据
        validateRequest(notificationParam);

        //解密数据
        return decryptData(notificationParam, requireNonNull(decryptObjectClass));
    }

    private void validateRequest(NotificationParam notificationParam) {
        if (notificationParam == null) {
            throw new ValidationException(
                    "Verify Pay notification parameters, requestParam is null.");
        }
        if (notificationParam.getSignType() == null) {
            throw new ValidationException(
                    String.format(
                            "Verify Pay notification parameters, signType is empty" + ".RequestParam[%s]",
                            notificationParam));
        }
        if (notificationParam.getSerialNumber() == null) {
            throw new ValidationException(
                    String.format(
                            "Verify Pay notification parameters, serialNumber is empty"
                                    + ".RequestParam[%s]",
                            notificationParam));
        }
        if (notificationParam.getMessage() == null) {
            throw new ValidationException(
                    String.format(
                            "Verify Pay notification parameters, message is empty" + ".RequestParam[%s]",
                            notificationParam));
        }
        if (notificationParam.getSignature() == null) {
            throw new ValidationException(
                    String.format(
                            "Verify Pay notification parameters, signature is empty" + ".RequestParam[%s]",
                            notificationParam));
        }
        if (!signature.verify(notificationParam.getMessage(), notificationParam.getSignature())) {
            throw new ValidationException(
                    String.format(
                            "Processing Pay notification,signature verification failed,"
                                    + "signType[%s]\tserial[%s]\tmessage[%s]\tsign[%s]",
                            notificationParam.getSignType(),
                            notificationParam.getSerialNumber(),
                            notificationParam.getMessage(),
                            notificationParam.getSignature()));
        }
    }

    private void validateNotification(Notification notification) {
        if (notification == null) {
            throw new MessageException(
                    "The notification obtained by parsing the Pay notification is null.");
        }
        Resource resource = notification.getResource();
        if (resource == null) {
            throw new MessageException(
                    String.format(
                            "The resource obtained by parsing the Pay notification is null"
                                    + ".Notification[%s]",
                            notification));
        }
        if (resource.getAlgorithm() == null) {
            throw new MessageException(
                    String.format(
                            "The algorithm obtained by parsing the Pay notification is empty.Notification[%s]",
                            notification));
        }
        if (resource.getCiphertext() == null) {
            throw new MessageException(
                    String.format(
                            "The ciphertext obtained by parsing the Pay notification is empty.Notification[%s]",
                            notification));
        }
        if (resource.getNonce() == null) {
            throw new MessageException(
                    String.format(
                            "The nonce obtained by parsing the Pay notification is empty.Notification[%s]",
                            notification));
        }
    }

    private <T> T decryptData(NotificationParam notificationParam, Class<T> decryptObjectClass) {
        Notification notification = JSONObject.parseObject(notificationParam.getBody(), Notification.class);
        validateNotification(notification);

        String associatedData = notification.getResource().getAssociatedData();
        String nonce = notification.getResource().getNonce();
        String ciphertext = notification.getResource().getCiphertext();
        String plaintext = aes.decrypt(associatedData, nonce, ciphertext);
        return JSONObject.parseObject(plaintext, decryptObjectClass);
    }
}
