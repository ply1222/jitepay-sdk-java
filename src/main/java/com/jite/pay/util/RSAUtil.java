package com.jite.pay.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA工具类 密钥格式:PKCS8
 */
/**
 * RSA工具类 密钥格式:PKCS8
 */
public class RSAUtil {
    /**
     * 证书算法
     */
    private static final String KEY_ALGORITHM = "RSA";//SHA256withRSA

    /**
     * 加密算法
     */
    private static final String OAEP_ALGORITHM = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * 密钥长度 512 1024 2048 3072 4096
     */
    private static final int KEY_SIZE = 2048;

    /**
     * 获取公钥的key
     */
    public static final String PUBLIC_KEY = "publicKey";

    /**
     * 获取私钥的key
     */
    public static final String PRIVATE_KEY = "privateKey";

    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) throws Exception {
        Map<String, String> keyPairMap = getKeyPair(2048);
        System.out.println("生成公、私钥测试：" + keyPairMap);

        String publicKey = keyPairMap.get(PUBLIC_KEY);
        String privateKey = keyPairMap.get(PRIVATE_KEY);

        String str = "alpha=001&beta=002&gamma=003";
        System.out.println("===开始RSA加密测试===");
        String encrypt = encrypt(str, publicKey);
        System.out.println(encrypt);

        System.out.println("===开始RSA解密测试===");
        String decrypt = decrypt(encrypt, privateKey);
        System.out.println(decrypt);

        System.out.println("===开始RSA公、私钥测试===");
        String sign = sign(privateKey, str);
        System.out.println(sign);
        System.out.println(verifySign(publicKey, str, sign));
    }

    /**
     * 生成公、私钥
     * 根据需要返回String或byte[]类型
     *
     * @return Map
     */
    public static Map<String, String> getKeyPair(int keySize) {
        Map<String, String> keyPairMap = new HashMap<>();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            //初始化密钥长度
            if (keySize != 2048) {
                keyPairGenerator.initialize(keySize);
            } else {
                keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
            }

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            //获取公、私钥值
            String publicKeyValue = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String privateKeyValue = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            //存入
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
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(key);
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
    public static PrivateKey getPrivateKey(String key) {
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

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws IllegalBlockSizeException 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws IllegalBlockSizeException {
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
            throw new IllegalBlockSizeException("加密原串的长度不能超过214字节");
        }
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws BadPaddingException 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws BadPaddingException {
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
     * 签名
     *
     * @param private_key 私钥
     * @param requestData 请求参数
     * @return
     */
    public static String sign(String private_key, String requestData) {
        String signature = null;
        try {
            PrivateKey privateKey = getPrivateKey(private_key);
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
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
     * @param public_key  公钥
     * @param requestData 请求参数
     * @param signature   签名
     * @return
     */
    public static boolean verifySign(String public_key, String requestData, String signature) {
        boolean verifySignSuccess = false;
        try {
            PublicKey publicKey = getPublicKey(public_key);
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(requestData.getBytes(CHARSET));
            verifySignSuccess = verifySign.verify(Base64.getDecoder().decode(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verifySignSuccess;
    }
}
