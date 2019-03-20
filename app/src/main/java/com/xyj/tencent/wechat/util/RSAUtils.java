package com.xyj.tencent.wechat.util;


import org.myapache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class RSAUtils {

    //加密算法
    private static final String RSA = "RSA";
    //签名算法
    private static final String SIGN = "MD5withRSA";
    //公钥
    public static final String PUBLIC_KEY = "RSA_PUBLIC_KEY";
    //私钥
    public static final String PRIVATE_KEY = "RSA_PRIVATE_KEY";
    //最大明文长度
    private static final int MAX_ENCRYPT_BLOCK = 117;
    //最大密文长度
    private static final int MAX_DECRYPT_BLOCK = 238;
    //Base64
    private static final Base64 base64 = new Base64();

    /**
     * 私钥解密
     */
    public static String decryptByPrivateKey(String encode, String privateKey) throws Exception {
        byte[] data = Base64.decodeBase64(encode);

        return new String(decryptByPrivateKey(data, privateKey));
    }

    /**
     * 公钥解密
     */
    public static String decryptByPublicKey(String encode, String publicKey) throws Exception {
        byte[] data = base64.decode(encode);

        return new String(decrypteByPublicKey(data, publicKey));
    }

    /**
     * 私钥加密
     */
    public static String encryptByPrivateKey(String str, String privateKey) throws Exception {
        byte[] data = str.getBytes();

        return base64.encodeAsString(encryptByPrivateKey(data, privateKey));
    }

    /**
     * 公钥加密
     */
    public static String encryptyPublicKey(String str, String publicKey) throws Exception {
        byte[] data = str.getBytes();
        return base64.encodeAsString(encryptByPublicKey(data, publicKey));
    }

    /**
     * 校验两个加密内容明文是否相同
     */
    public static boolean equalsByPrivateKey(String str1, String str2, String privateKey) throws Exception {
        String decryptStr1 = decryptByPrivateKey(str1, privateKey);
        String decryptStr2 = decryptByPrivateKey(str2, privateKey);

        return decryptStr1.equals(decryptStr2);
    }

    /**
     * 校验两个加密内容明文是否相同
     */
    public static boolean equalsByPublicKey(String str1, String str2, String publicKey) throws Exception {
        String decryptStr1 = decryptByPublicKey(str1, publicKey);
        String decryptStr2 = decryptByPublicKey(str2, publicKey);

        return decryptStr1.equals(decryptStr2);
    }

    /**
     * 用私钥对加密信息生成数字签名
     */
    public static String sign(String encode, String privateKey) throws Exception {
        byte[] data = base64.decode(encode);

        return sign(data, privateKey);
    }

    /**
     * 用私钥对加密信息生成数字签名
     */
    public static String sign(byte[] bytes, String privateKey) throws Exception {
        try{
            byte[] privateKeyByte = base64.decode(privateKey.getBytes());

            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);

            Signature signature = Signature.getInstance(SIGN);
            signature.initSign(privateK);
            signature.update(bytes);

            return base64.encodeAsString(signature.sign());
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 校验数字签名
     */
    public static boolean verfiy(String encode, String publicKey, String sign) throws Exception {
        byte[] data = base64.decode(encode);

        return verify(data, publicKey, sign);
    }

    /**
     * 校验数字签名
     */
    public static boolean verify(byte[] bytes, String publicKey, String sign) throws Exception {
        try{
            byte[] publicKeyByte = base64.decode(publicKey.getBytes());

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicK = keyFactory.generatePublic(keySpec);

            Signature signature = Signature.getInstance(SIGN);
            signature.initVerify(publicK);
            signature.update(bytes);

            return signature.verify(base64.decode(sign.getBytes()));
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 私钥解密
     */
    public static byte[] decryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        try{
            byte[] privateKeyByte = base64.decode(privateKey.getBytes());

            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateK);

            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;

            //对数据分段解密
            while(inputLen - offSet > 0){
                if(inputLen - offSet > MAX_DECRYPT_BLOCK){
                    cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
                }else{
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }

            byte[] decryptedData = out.toByteArray();
            out.close();

            return decryptedData;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 公钥解密
     */
    public static byte[] decrypteByPublicKey(byte[] data, String publicKey) throws Exception {
        try{
            byte[] publickeyByte = base64.decode(publicKey.getBytes());

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publickeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key publicK = keyFactory.generatePublic(x509KeySpec);

            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicK);
            int inputLen = data.length;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;

            //对数据分段解密
            while(inputLen - offSet > 0){
                if(inputLen - offSet > MAX_DECRYPT_BLOCK){
                    cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
                }else{
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }

            byte[] decryptedData = out.toByteArray();
            out.close();

            return decryptedData;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 公钥加密
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        try{
            byte[] publickeyByte = base64.decode(publicKey.getBytes());

            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publickeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key publicK = keyFactory.generatePublic(x509KeySpec);

            //对数据加密
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicK);

            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;

            //对数据分段加密
            while(inputLen - offSet > 0){
                if(inputLen - offSet > MAX_ENCRYPT_BLOCK){
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                }else{
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }

            byte[] encryptedData = out.toByteArray();
            out.close();

            return encryptedData;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 私钥加密
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
        try{
            byte[] privatekeyByte = base64.decode(privateKey.getBytes());

            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privatekeyByte);
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, privateK);

            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;

            //对数据分段加密
            while(inputLen - offSet > 0){
                if(inputLen - offSet > MAX_ENCRYPT_BLOCK){
                    cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                }else{
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }

            byte[] encryptedData = out.toByteArray();
            out.close();

            return encryptedData;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 校验两个加密内容明文是否相同
     */
    public static boolean equalsByPrivateKey(byte[] data1, byte[] data2, String privateKey) throws Exception {
        String decryptData1 = decryptByPrivateKey(base64.encodeAsString(data1), privateKey);
        String decryptData2 = decryptByPrivateKey(base64.encodeAsString(data2), privateKey);

        return decryptData1.equals(decryptData2);
    }

    /**
     * 校验两个加密内容明文是否相同
     */
    public static boolean equalsByPublicKey(byte[] data1, byte[] data2, String publicKey) throws Exception {
        String decryptData1 = decryptByPublicKey(base64.encodeAsString(data1), publicKey);
        String decryptData2 = decryptByPublicKey(base64.encodeAsString(data2), publicKey);

        return decryptData1.equals(decryptData2);
    }

    /**
     * 生成密钥对
     */
    public static Map<String, Object> getKeyPair() throws Exception {
        try{
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
            keyPairGen.initialize(1024);

            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            Map<String, Object> keyMap = new HashMap<String, Object>(2);
            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);

            return keyMap;
        }catch(Exception e){
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 获取私钥
     */
    public static String getPrivateKey(Map<String, Object> keyMap){
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return base64.encodeAsString(key.getEncoded());
    }

    /**
     * 获取公钥
     */
    public static String getPublicKey(Map<String, Object> keyMap){
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return base64.encodeAsString(key.getEncoded());
    }
}
