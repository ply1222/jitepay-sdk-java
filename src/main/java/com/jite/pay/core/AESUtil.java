package com.jite.pay.core;

import com.jite.pay.exception.DecryptionException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * AEAD_AES_256_GCM
 */
public class AESUtil {
    static final int KEY_LENGTH_BYTE = 32;
    static final int TAG_LENGTH_BIT = 128;
    static final String CIPHER_TRANSFORM = "AES/GCM/NoPadding";
    static final String ALGORITHM_AES = "AES";
    private final String aesKey;

    public AESUtil(String aesKey) {
        this.aesKey = aesKey;
    }

    /**
     * 加密
     *
     * @param plaintext      加密数据
     * @param nonce          随机串
     * @param associatedData 附加数据
     * @return byte[]
     */
    public String encrypt(String associatedData, String nonce, String plaintext) throws
            GeneralSecurityException, IOException {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), ALGORITHM_AES);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes());
            return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 解密
     *
     * @param ciphertext     数据密文
     * @param nonce          随机串
     * @param associatedData 附加数据
     * @return byte[]
     */
    public String decrypt(String associatedData, String nonce, String ciphertext) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORM);
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), ALGORITHM_AES);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes());
            return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DecryptionException(e.getMessage());
        }
    }

    /**
     * 密钥
     *
     * @return String
     */
    public static String generateKey() {
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

    private static SecretKey keyStrToSecretKey(String keyStr) {
        byte[] key = Base64.getDecoder().decode(keyStr);
        if (key.length != KEY_LENGTH_BYTE) {
            throw new IllegalArgumentException("无效的Key，长度必须为32个字节");
        }
        return new SecretKeySpec(key, ALGORITHM_AES);
    }
}