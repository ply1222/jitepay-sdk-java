package com.jite.pay.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AEAD_AES_256_GCM
 */
public class AesUtil {
    static final int KEY_LENGTH_BYTE = 32;
    static final int TAG_LENGTH_BIT = 128;
    static final String CIPHER_TRANSFORM = "AES/GCM/NoPadding";
    static final String ALGORITHM_AES = "AES";

    public static void main(String[] args) {
        try {
            String originalText = "Plain text to be encrypted by AEAD-AES-256-GCM in Java";
            String associatedData = "Associated Data";

            String key = key();
            String nonce = nonce();
            System.out.println("key Text:  " + key);
            System.out.println("nonce Text:  " + nonce);

            System.out.println("Original Text:  " + originalText);
            byte[] cipherText = encrypt(originalText, key, nonce, associatedData);
            System.out.println("Encrypted Text: " + bytesToHex(cipherText));

            String plainText = decrypt(cipherText, key, nonce, associatedData);
            System.out.println("Decrypted Text: " + plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 密钥
     *
     * @return String
     */
    public static String key() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES);
            keyGenerator.init(KEY_LENGTH_BYTE * 8);
            SecretKey key = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 随机串
     *
     * @return String
     */
    public static String nonce() {
        byte[] nonce = new byte[16];
        SecureRandom sRandom = new SecureRandom();
        sRandom.nextBytes(nonce);
        return bytesToHex(nonce);
    }

    /**
     * 加密
     *
     * @param plaintext      加密数据
     * @param keyStr         密钥
     * @param nonce          随机串
     * @param associatedData 附加数据
     * @return byte[]
     * @throws Exception
     */
    public static byte[] encrypt(String plaintext, String keyStr, String nonce, String associatedData) throws Exception {
        SecretKey key = keyStrToSecretKey(keyStr);
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), ALGORITHM_AES);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
        cipher.updateAAD(associatedData.getBytes());
        return Base64.getEncoder().encode(cipher.doFinal(plaintext.getBytes()));
    }

    /**
     * 解密
     *
     * @param ciphertext     数据密文
     * @param keyStr         密钥
     * @param nonce          随机串
     * @param associatedData 附加数据
     * @return byte[]
     * @throws Exception
     */
    public static String decrypt(byte[] ciphertext, String keyStr, String nonce, String associatedData) throws Exception {
        SecretKey key = keyStrToSecretKey(keyStr);
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), ALGORITHM_AES);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        cipher.updateAAD(associatedData.getBytes());
        return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), StandardCharsets.UTF_8);
    }

    private static SecretKey keyStrToSecretKey(String keyStr) {
        if (keyStr.length() != 44) {
            throw new IllegalArgumentException("无效的Key");
        }
        byte[] key = Base64.getDecoder().decode(keyStr);
        if (key.length != KEY_LENGTH_BYTE) {
            throw new IllegalArgumentException("无效的Key，长度必须为32个字节");
        }
        return new SecretKeySpec(key, "AES");
    }

    /**
     * 字节数组转换成十六进制字符串
     * @param bytes 字节
     * @return String
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}