package com.loong.mnote.common.util;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import static com.loong.mnote.common.util.secure.Coder.decryptBASE64;
import static com.loong.mnote.common.util.secure.Coder.encryptBASE64;

/**
 * @author: sam
 * @date: 2019-02-18 21:05
 */
public class RSAUtils {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @
     */
    public static String sign(byte[] data, String privateKey) {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(data);
            return encryptBASE64(signature.sign());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @
     */
    public static boolean verify(byte[] data, String publicKey, String sign) {

        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(data);

            // 验证签名是否正常
            return signature.verify(decryptBASE64(sign));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        try{
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            int inputLen = data.length;
            int offLen = 0;//偏移量
            int i = 0;
            ByteArrayOutputStream bops = new ByteArrayOutputStream();
            while(inputLen - offLen > 0){
                byte [] cache;
                if(inputLen - offLen > 128){
                    cache = cipher.doFinal(data, offLen,128);
                }else{
                    cache = cipher.doFinal(data, offLen,inputLen - offLen);
                }
                bops.write(cache);
                i++;
                offLen = 128 * i;
            }
            bops.close();
            return bops.toByteArray();
//            return cipher.doFinal(data);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        try{
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);

            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            int inputLen = data.length;
            int offLen = 0;//偏移量
            int i = 0;
            ByteArrayOutputStream bops = new ByteArrayOutputStream();
            while(inputLen - offLen > 0){
                byte [] cache;
                if(inputLen - offLen > 128){
                    cache = cipher.doFinal(data, offLen,128);
                }else{
                    cache = cipher.doFinal(data, offLen,inputLen - offLen);
                }
                bops.write(cache);
                i++;
                offLen = 128 * i;
            }
            bops.close();
            return bops.toByteArray();

//            return cipher.doFinal(data);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);

        try{
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);

            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            int inputLen = data.length;
            int offLen = 0;//偏移量
            int i = 0;
            ByteArrayOutputStream bops = new ByteArrayOutputStream();
            while(inputLen - offLen > 0){
                byte [] cache;
                if(inputLen - offLen > 117){
                    cache = cipher.doFinal(data, offLen,117);
                }else{
                    cache = cipher.doFinal(data, offLen,inputLen - offLen);
                }
                bops.write(cache);
                i++;
                offLen = 117 * i;
            }
            bops.close();
            return bops.toByteArray();
//            return cipher.doFinal(data);

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        try{
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            int inputLen = data.length;
            int offLen = 0;//偏移量
            int i = 0;
            ByteArrayOutputStream bops = new ByteArrayOutputStream();
            while(inputLen - offLen > 0){
                byte [] cache;
                if(inputLen - offLen > 117){
                    cache = cipher.doFinal(data, offLen,117);
                }else{
                    cache = cipher.doFinal(data, offLen,inputLen - offLen);
                }
                bops.write(cache);
                i++;
                offLen = 117 * i;
            }
            bops.close();
            return bops.toByteArray();

//            return cipher.doFinal(data);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @
     */
    public static Map<String, Object> initKey() {
        try{
            KeyPairGenerator keyPairGen = KeyPairGenerator
                    .getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(1024);

            KeyPair keyPair = keyPairGen.generateKeyPair();

            // 公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

            // 私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, Object> keyMap = new HashMap<>(2);

            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}