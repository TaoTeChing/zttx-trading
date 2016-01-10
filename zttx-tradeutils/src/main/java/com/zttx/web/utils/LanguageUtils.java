/*
 * @(#)LanguageUtils.java 2014-2-26 上午11:46:18
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

/**
 * <p>File：LanguageUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-26 上午11:46:18</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class LanguageUtils
{
    // 私有构造器
    private LanguageUtils()
    {
        super();
    }
    
    /**  
     * 半角转全角  
     * @param input 要转换的字符串
     * @return String 转换后的字符串
     */
    public static String banToQuan(String input)
    {
        if (null == input) return null;
        else
        {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++)
            {
                if (c[i] == ' ')
                {
                    c[i] = '\u3000';
                }
                else if (c[i] < '\177')
                {
                    c[i] = (char) (c[i] + 65248);
                }
            }
            return new String(c);
        }
    }
    
    /**  
     * 全角转半角 
     * @param input 要转换的字符串  
     * @return String 转换后的字符串
     */
    public static String quanToBan(String input)
    {
        if (null == input) return null;
        else
        {
            char c[] = input.toCharArray();
            for (int i = 0; i < c.length; i++)
            {
                if (c[i] == '\u3000')
                {
                    c[i] = ' ';
                }
                else if (c[i] > '\uFF00' && c[i] < '\uFF5F')
                {
                    c[i] = (char) (c[i] - 65248);
                }
            }
            String returnString = new String(c);
            return returnString;
        }
    }
}
