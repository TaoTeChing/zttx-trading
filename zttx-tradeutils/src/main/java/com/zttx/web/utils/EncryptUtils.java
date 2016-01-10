/*
 * @(#)EncryptUtils.java 2014-1-8 下午1:11:41
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.zttx.web.consts.ApplicationConst;

/**
 * <p>File：EncryptUtils.java</p>
 * <p>Title: 系统DES对称加密算法，加密、解密处理工具类</p>
 * <p>Description:主要功能：DES明文字符串加密，DES密文字符串解密</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:11:41</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class EncryptUtils
{
    private static final Logger   logger                = Logger.getLogger(EncryptUtils.class);
    
    private static final String   CHARSET               = ApplicationConst.LANGUAGE_UT;
    
    private static final String   PARSE_KEY             = "Zttx";
    
    private static final String   ENCRYPT_NAME          = "DES";
    
    private static final String   RSA                   = "RSA";
    
    public static final String    SHA_256               = "SHA-256";
    
    public static final String    MD5                   = "MD5";
    
    // RSA Padding算法
    private static final String   RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";
    
    // BouncyCastle加密实现
    private static final Provider provider              = new BouncyCastleProvider();
    
    // 私有构造器，防止类的实例化
    private EncryptUtils()
    {
        super();
    }
    
    /**
     * <p>返回经过加密的字符串</p>
     * @param password 要加密码的明文字符串
     * @param algorithm 加密运算法则(可以是MD5、MD2、SHA-256、SHA-1等等)
     * @return String 加密后的字符串
     */
    public static String encrypt(String password, String algorithm)
    {
        String result = null;
        byte[] unencodedPassword = password.getBytes(Charset.forName(CHARSET));
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error(e);
        }
        if (null != md)
        {
            md.update(unencodedPassword);
            byte[] encodedPassword = md.digest();
            StringBuffer buf = new StringBuffer();
            int iLen = encodedPassword.length;
            for (int i = 0; i < iLen; i++)
            {
                if ((encodedPassword[i] & 0xff) < 0x10) buf.append("0");
                buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }
            result = buf.toString();
        }
        return result;
    }
    
    public static String desEncrypt(String strMing)
    {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        // BASE64Encoder base64en = new BASE64Encoder();
        try
        {
            byteMing = strMing.getBytes(ApplicationConst.LANGUAGE_UT);
            byteMi = encryptByte(byteMing);
            // strMi = base64en.encode(byteMi);
            strMi = Base64.encodeBase64String(byteMi);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        finally
        {
            // base64en = null;
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }
    
    public static String desDecrypt(String strMi)
    {
        // BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try
        {
            // byteMi = base64De.decodeBuffer(strMi);
            byteMi = Base64.decodeBase64(strMi);
            byteMing = decryptByte(byteMi);
            strMing = new String(byteMing, ApplicationConst.LANGUAGE_UT);
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error(e);
        }
        finally
        {
            // base64De = null;
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }
    
    private static byte[] encryptByte(byte[] byteS)
    {
        byte[] byteFina = null;
        Cipher cipher;
        try
        {
            cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, generatorKey(PARSE_KEY));
            byteFina = cipher.doFinal(byteS);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        finally
        {
            cipher = null;
        }
        return byteFina;
    }
    
    private static byte[] decryptByte(byte[] byteD)
    {
        Cipher cipher;
        byte[] byteFina = null;
        try
        {
            cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.DECRYPT_MODE, generatorKey(PARSE_KEY));
            byteFina = cipher.doFinal(byteD);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        finally
        {
            cipher = null;
        }
        return byteFina;
    }
    
    private static Key generatorKey(String parseKey)
    {
        Key key = null;
        KeyGenerator generator = null;
        try
        {
            generator = KeyGenerator.getInstance(ENCRYPT_NAME);
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error(e);
        }
        if (null != generator)
        {
            // generator.init(new SecureRandom(parseKey.getBytes()));//Linux及Solaris下异常
            SecureRandom secureRandom = null;
            try
            {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            }
            catch (NoSuchAlgorithmException e)
            {
                logger.error(e);
            }
            secureRandom.setSeed(parseKey.getBytes(Charset.forName(CHARSET)));
            generator.init(secureRandom);
            key = generator.generateKey();
            generator = null;
        }
        return key;
    }
    
    /**
     * 判断密码是否匹配
     * @param originalPwd
     * @param salt
     * @param encryptPwd
     * @return
     */
    public static boolean matchPassword(String originalPwd, String salt, String encryptPwd)
    {
        String encrypt = encrypt(new StringBuilder(originalPwd).append(salt).toString(), ApplicationConst.ENCRYPT);
        return encryptPwd.equals(encrypt);
    }
    
    /**
     * 跟具给定长度生成 公匙与私匙密匙对，公匙用于RSA算法加密，私匙用于RSA算法解密
     * 
     * @param keyLength 密匙长度
     * @return KeyPair 公匙与私匙密匙对
     */
    public static KeyPair generateRsaKeypair(int keyLength)
    {
        KeyPairGenerator kpg;
        try
        {
            kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            KeyPair keyPair = kpg.generateKeyPair();
            return keyPair;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 根据RSA私匙解密
     * 
     * @param encrypted 加密后的字节数据
     * @param privateKey RSA私匙
     * @return byte[] 解密后的字节数据
     */
    public static byte[] decryptByPrivateKey(byte[] encrypted, PrivateKey privateKey)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING, provider);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(64);
            int j = 0;
            while (encrypted.length - j * blockSize > 0)
            {
                outputStream.write(cipher.doFinal(encrypted, j * blockSize, blockSize));
                j++;
            }
            return outputStream.toByteArray();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 加密给定字节
     * 
     * @param 等待加密的字节数据
     * @param RSA公匙
     * @return byte[] 加密后的字节数据
     */
    public static byte[] encryptByPublicKey(byte[] original, PublicKey publicKey) throws Exception
    {
        try
        {
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING, provider);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(original);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Return public RSA key modulus
     * 
     * @param keyPair RSA keys
     * @return modulus value as hex string
     */
    public static String getPublicKeyHexModulus(PublicKey publicKey)
    {
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        return rsaPublicKey.getModulus().toString(16);
    }
    
    /**
     * Return public RSA key exponent
     * 
     * @param keyPair RSA keys
     * @return public exponent value as hex string
     */
    public static String getPublicKeyHexExponent(KeyPair keyPair)
    {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return publicKey.getPublicExponent().toString(16);
    }
    
    /**
     * 还原简单16进制加密的 加密串
     * @param encryString 加密串
     * @return String 明文
     * @author 
     */
    public static String getDecString(String encryString)
    {
        String ming = null;
        if (!StringUtils.isBlank(encryString))
        {
            int lenth = encryString.length();
            String lastStr = encryString.substring(0, lenth - 2);
            String reverStr = StringUtils.reverse(lastStr);
            ming = EncodeUtils.hexToString(reverStr, ApplicationConst.LANGUAGE_UT);
        }
        return ming;
    }
    
    /**
     * 进行简单的16进制加密  明文-16进制转码-反转-异或
     * @param ming 明文
     * @return String 加密串
     * @author 
     */
    public static String getEncryString(String ming)
    {
        String encryCode = null;
        if (!StringUtils.isBlank(ming))
        {
            String str_16 = EncodeUtils.stringToHex(ming, ApplicationConst.LANGUAGE_UT);
            String reverStr = StringUtils.reverse(str_16);
            encryCode = reverStr + EncodeUtils.getXorString(reverStr, ApplicationConst.LANGUAGE_UT);
        }
        return encryCode;
    }
}
