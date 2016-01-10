package com.zttx.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * aes加密工具，用于和crm后台直接登录品牌商的通信加密
 * @author 夏铭
 * @since 2014-07-11
 * @version 1.0.0
 */
public abstract class AesUtil
{
    private static final BouncyCastleProvider PROVIDER          = new BouncyCastleProvider();
    
    private static final String               AES               = "AES/CBC/PKCS5Padding";
    
    private static final String               AES_KEY           = "AES";
    
    private static final String               UTF8              = "UTF-8";
    
    private static final String               DEFAULT_KEY_STR   = AesUtil.class.getName();
    
    private static byte[]                     iv                = {0x38, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31, 0x38, 0x37, 0x36, 0x35, 0x34, 0x33, 0x32, 0x31};
    
    private static final IvParameterSpec      IV_PARAMETER_SPEC = new IvParameterSpec(iv);
    
    static
    {
        Security.addProvider(PROVIDER);
    }
    
    /**
     * 加密
     * @param strToEnc
     * @return
     * @throws Exception
     */
    public static final String aesEncrypt(final String strToEnc) throws Exception
    {
        return aesEncrypt(strToEnc, DEFAULT_KEY_STR);
    }
    
    /**
     * 解密
     * @param strToDec
     * @return
     * @throws Exception
     */
    public static final String aesDecrypt(final String strToDec) throws Exception
    {
        return aesDecrypt(strToDec, DEFAULT_KEY_STR);
    }
    
    /**
     * 加密
     * @param strToEnc 要加密的信息
     * @param keyStr 加密过程中使用的 key 值
     * @return
     * @throws Exception
     */
    public static final String aesEncrypt(final String strToEnc, final String keyStr) throws Exception
    {
        Key key = getKey(keyStr, 128, AES_KEY);
        Cipher cipher = Cipher.getInstance(AES, PROVIDER);
        cipher.init(Cipher.ENCRYPT_MODE, key, IV_PARAMETER_SPEC);
        byte[] enc = cipher.doFinal(strToEnc.getBytes(UTF8));
        String encryptedValue = Base64.encodeBase64String(enc);
        return encryptedValue;
    }
    
    /**
     * 解密
     * @param strToDec 要解密的信息
     * @param keyStr 解密过程中使用的key值
     * @return
     * @throws Exception
     */
    public static final String aesDecrypt(final String strToDec, final String keyStr) throws Exception
    {
        Key key = getKey(keyStr, 128, AES_KEY);
        Cipher cipher = Cipher.getInstance(AES, PROVIDER);
        cipher.init(Cipher.DECRYPT_MODE, key, IV_PARAMETER_SPEC);
        byte[] decordedValue = Base64.decodeBase64(strToDec.getBytes(UTF8));
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue, UTF8);
        return decryptedValue;
    }

    /**
     * 分离crm中传过来的登录令牌
     *
     * @param code
     * @return
     */
    public static final String[] separateCode(String code)
    {
        StringBuilder branderId = new StringBuilder();
        StringBuilder random = new StringBuilder();
        for (int i = 0; i < 32; i++)
        {
            random.append(code.charAt(i * 2));
            branderId.append(code.charAt(i * 2 + 1));
        }
        return new String[]{branderId.toString(), random.toString()};
    }
    
    private static Key getKey(String keyStr, int bit, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(keyStr.getBytes(UTF8));
        keyGenerator.init(bit, sr);
        return keyGenerator.generateKey();
    }
    
    public static void main(String[] args) throws Exception
    {
        String str = "天下商帮夏铭@#,.a1b";
        String out = AesUtil.aesDecrypt(AesUtil.aesEncrypt(str));
        System.out.println(out);
    }
}
