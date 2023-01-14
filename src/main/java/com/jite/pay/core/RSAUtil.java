package com.jite.pay.core;

import com.jite.pay.exception.ValidationException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Signature;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

/**
 * RSA工具类
 * 密钥格式:私钥采用pkcs8_rsa_private_key.pem，公钥采用rsa_public_key.pem
 */
public class RSAUtil {
    /**
     * 签名算法名称
     * RSA:  密钥长度不限制，默认长度1024，推荐长度2048
     * RSA2: 密钥长度至少为 2048
     */
    public final String KEY_ALGORITHM = "RSA";

    /**
     * 加密算法
     */
    public final String OAEP_ALGORITHM = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

    /**
     * 标准签名算法名称 SHA1WithRSA SHA256withRSA
     */
    public String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * RSA密钥长度,默认密钥长度是1024,密钥长度必须是64的倍数，在512到65536位之间
     */
    public int KEY_SIZE = 2048;

    /**
     * 获取公钥的key
     */
    public String PUBLIC_KEY = "publicKey";

    /**
     * 获取私钥的key
     */
    public String PRIVATE_KEY = "privateKey";

    /**
     * 证书序列号
     */
    public String SERIAL_NO = "serialNo";

    public String CHARSET = "UTF-8";

    private String serialNo;

    private String publicKey;

    private String privateKey;

    public RSAUtil() {
    }

    public RSAUtil(String serialNo, String publicKey, String privateKey) {
        this.serialNo = serialNo;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getSerialNo() {
        return serialNo;
    }

    /**
     * 生成公、私钥
     * 根据需要返回String或byte[]类型
     *
     * @return Map
     */
    public Map<String, String> getKeyPair(int size) {
        Map<String, String> keyPairMap = new HashMap<>();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);

            //初始化密钥长度
            SecureRandom random = new SecureRandom();
            byte[] nonce = new byte[16];
            random.nextBytes(nonce);
            String serialNo = new String(nonce);

            random.setSeed(serialNo.getBytes());
            keyPairGenerator.initialize(size, random);

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            //获取公、私钥值
            String publicKeyValue = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyValue = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            //存入
            keyPairMap.put(SERIAL_NO, serialNo);
            keyPairMap.put(PUBLIC_KEY, publicKeyValue);
            keyPairMap.put(PRIVATE_KEY, privateKeyValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyPairMap;
    }

    /**
     * 解码PublicKey
     *
     * @param key
     * @return
     */
    public PublicKey getPublicKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(requireNonNull(key));
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解码PrivateKey
     *
     * @param key
     * @return
     */
    public PrivateKey getPrivateKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取私匙
    public PrivateKey getPrivateKeyFromPem(String privateKeyPem) throws Exception {
        return getPrivateKey(getKeyFromPem(privateKeyPem));
    }

    // 获取公钥
    public PublicKey getPublicKeyFromPem(String publicKeyPem) throws Exception {
        return getPublicKey(getKeyFromPem(publicKeyPem));
    }

    private String getKeyFromPem(String publicKeyPem) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(publicKeyPem));
        StringBuilder str = new StringBuilder();
        String s = br.readLine();
        while (s.charAt(0) != '-') {
            str.append(s).append("\r");
            s = br.readLine();
        }
        return str.toString();
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @return 密文
     */
    public String encrypt(String str) {
        try {
            RSAPublicKey pubKey = (RSAPublicKey) getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(OAEP_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("当前Java环境不支持RSA v1.5/OAEP", e);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("无效的证书", e);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException("加密原串的长度不能超过214字节");
        }
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @return 明文
     * @throws BadPaddingException 解密过程中的异常信息
     */
    public String decrypt(String str) throws BadPaddingException {
        try {
            RSAPrivateKey priKey = (RSAPrivateKey) getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(OAEP_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] inputByte = Base64.getDecoder().decode(str);
            return new String(cipher.doFinal(inputByte), StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new RuntimeException("当前Java环境不支持RSA v1.5/OAEP", e);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("无效的私钥", e);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            throw new BadPaddingException("解密失败");
        }
    }

    /**
     * 私钥加密
     *
     * @param data          加密前的字符串
     * @param privateKeyStr base64编码后的私钥
     * @return base64编码后后的字符串
     * @throws Exception
     */
    public String encryptByPrivateKey(String data, String privateKeyStr) throws Exception {
        PrivateKey privateKey = getPrivateKey(privateKeyStr);
        //返回转换指定算法的KeyFactory对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        //用私钥初始化此Cipher对象（加密模式）
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        //对数据加密
        byte[] encrypt = cipher.doFinal(data.getBytes());
        //返回base64编码后的字符串
        return Base64.getEncoder().encodeToString(encrypt);
    }

    /**
     * 公钥解密
     *
     * @param data         解密前的字符串
     * @param publicKeyStr base64编码后的公钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decryptByPublicKey(String data, String publicKeyStr) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyStr);
        //根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        //用公钥初始化此Cipher对象（解密模式）
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        //对数据解密
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
        //返回字符串
        return new String(decrypt);
    }

    /**
     * 签名
     *
     * @param requestData 请求参数
     * @return
     */
    public String sign(String requestData) {
        String signature = null;
        try {
            PrivateKey key = getPrivateKey(privateKey);
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(key);
            Sign.update(requestData.getBytes(CHARSET));
            signature = Base64.getEncoder().encodeToString(Sign.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    /**
     * 验签
     *
     * @param requestData 请求参数
     * @param signature   签名
     * @return
     */
    public boolean verifySign(String requestData, String signature) {
        boolean result;
        try {
            PublicKey key = getPublicKey(publicKey);
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(key);
            verifySign.update(requestData.getBytes(CHARSET));
            result = verifySign.verify(Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            throw new ValidationException(e.getMessage(), e.getCause());
        }
        return result;
    }
}
