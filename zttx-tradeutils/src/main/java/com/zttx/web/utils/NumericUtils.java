/*
 * @(#)NumericUtils.java 2014-4-15 下午4:28:44
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

/**
 * <p>File：NumericUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-15 下午4:28:44</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class NumericUtils
{
    private NumericUtils()
    {
        super();
    }
    
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE      = 10;
    
    /**
     * 默认货币类型小数保留位数
     */
    public static final int  CURRENCY_SCALE_NUM = 2;
    
    /**
     * 将34.22%格式的字符串转为0.3422
     * @param string 百分比格式的字符串
     * @param len 精确位数
     * @return Double 转化后的double
     */
    public static Double stringToDouble(String string, int len)
    {
        DecimalFormat df = new DecimalFormat("###.####");
        string = StringUtils.trimToEmpty(string);
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumIntegerDigits(3);// 小数点前面最多显示几位的
        nf.setMaximumFractionDigits(len);
        Double d = 0D;
        try
        {
            d = (Double) nf.parse(string);
            d = Double.parseDouble(df.format(d));
        }
        catch (ParseException e)
        {
        }
        catch (ClassCastException e)
        {
        }
        return d;
    }
    
    /**
     * 将Double转百分比格式的字符串输出
     * @param d Double
     * @param len 精确位数
     * @return String 百分比格式的字符串
     */
    public static String doubleToString(Double d, int len)
    {
        if (null == d) d = 0D;
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumIntegerDigits(3);// 小数点前面最多显示几位的
        nf.setMaximumFractionDigits(len);// 小数点后面最多显示几位
        nf.setMinimumFractionDigits(len);
        return nf.format(d);
    }
    
    /**
     * Double对象类型格式化处理
     * @param d Double
     * @param len 小数点后精确的位数
     * @return double　格式化后的double
     */
    public static double formatDouble(Double d, int len)
    {
        if (null == d) return 0;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(len);
        String result = nf.format(d);
        return Double.parseDouble(result);
    }
    
    /**
     * Double转字符串
     * @param d Double
     * @param defValue d为null时的默认值
     * @return String String
     */
    public static String doubleToString(Double d, String defValue)
    {
        String result = defValue;
        if (null != d) result = Double.toString(d);
        return result;
    }
    
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }
    
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    
    /**
     * 提供精确的加法运算。 适合大金额的运算
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }
    
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    
    /**
     * 提供精确的减法运算。   此个可以进行大额的金额运算
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }
    
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale)
    {
        if (scale < 0) { throw new IllegalArgumentException("The scale must be a positive integer or zero"); }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale)
    {
        if (scale < 0) { throw new IllegalArgumentException("The scale must be a positive integer or zero"); }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(BigDecimal v, int scale)
    {
        if (scale < 0) { throw new IllegalArgumentException("The scale must be a positive integer or zero"); }
        BigDecimal b = new BigDecimal(v.toString());
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 默认的金额计算，小数点后四舍五入保留留两位
     * @param bigDecimal 需要保留两位小数的的货币金额
     * @return 保留两位的金额数字
     * @author 夏铭
     */
    public static BigDecimal roundCurrency(BigDecimal bigDecimal)
    {
        Preconditions.checkNotNull(bigDecimal);
        return bigDecimal.setScale(NumericUtils.CURRENCY_SCALE_NUM, RoundingMode.HALF_UP);
    }
    
    /**
     * 整数null转换为零
     * @param integer Integer
     * @return int 转换后的整数
     */
    public static int nullToZero(Integer integer)
    {
        return nullToDefault(integer, 0);
    }
    
    /**
     * 整数null转换为指定的整数值
     * @param integer Integer
     * @param idefault 指定的整数值，为null时返回该值
     * @return int 转换后的整数值
     */
    public static int nullToDefault(Integer integer, int idefault)
    {
        int iResult = idefault;
        if (null != integer) iResult = integer.intValue();
        return iResult;
    }
    
    /**
     * 把数字字符串转为BigDecimal对象
     * @param doubleValueArr
     * @return
     * @author 张昌苗
     */
    public static BigDecimal[] parseBigDecimalArr(String[] doubleValueArr)
    {
        BigDecimal[] bigDecimalValueArr = new BigDecimal[doubleValueArr.length];
        for (int i = 0; i < doubleValueArr.length; i++)
        {
            bigDecimalValueArr[i] = new BigDecimal(doubleValueArr[i]);
        }
        return bigDecimalValueArr;
    }
    
    /**
     * 把数字字符串转为Short对象
     * @param shortValueArr
     * @return
     * @author 张昌苗
     */
    public static Short[] parseShortArr(String[] shortValueArr)
    {
        Short[] arr = new Short[shortValueArr.length];
        for (int i = 0; i < shortValueArr.length; i++)
        {
            arr[i] = new Short(shortValueArr[i]);
        }
        return arr;
    }
}
