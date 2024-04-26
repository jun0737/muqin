package com.open.jun.muqin.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/***
 *@description: 编码工具类(包括加解密)
 *@author: jun
 *version: 1.0
 *time: 2023/3/19 23:05
 **/
public final class EncodedUtil {

    public final static String DEFAULT_PARTTERN = "UTF-8";

    /**
     * 普通加密MD5
     * @param src 待加密字符串
     * @return
     */
    public String selfMd5(String src) {

        if(JunStringUtils.isBlack(src)) {
            return JunStringUtils.EMPTY;
        }

        return DigestUtils.md5Hex(src.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 加盐MD5
     * @param src
     * @param salt
     * @return
     */
    public String sefMd5Salt(String src,Long salt) {

        if(JunStringUtils.isBlack(src)) {
            return JunStringUtils.EMPTY;
        }

        src += salt;

        return DigestUtils.md5Hex(src.getBytes(StandardCharsets.UTF_8));

    }

    /**
     *
     * @param src 待加密字符串
     * @param key 密钥
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public String aesEncryption(String src,String key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");

        byte[] byteContent = src.getBytes(StandardCharsets.UTF_8);

        // 初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));

        // 加密
        byte[] result = cipher.doFinal(byteContent);

        //通过Base64转码返回
        String s = Base64.getEncoder().encodeToString(result);
        return s;
    }

    /**
     * AES
     * @param encrypted
     * @param key
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String aesDecrypt(String encrypted, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        //实例化
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");

        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));

        //执行操作
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(encrypted));

        String s = new String(result, StandardCharsets.UTF_8);
        return s;
    }

}
