/*
 * @(#)RegularUtils.java 2014-1-8 下午1:14:19
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>File：RegularUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:14:19</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class RegularUtils
{
    // 私有构造器，防止类的实例化
    private RegularUtils()
    {
        super();
    }
    
    /**
     * 获取同级下一个编号
     * @param no 编号
     * @return
     */
    public static Integer getNextNo(Integer no)
    {
        Integer result = 0;
        if (no % 100 > 0)
        {
            result = no + 1;
        }
        else if (no % 10000 > 0)
        {
            result = Integer.valueOf(no / 100) * 100 + 100;
        }
        else
        {
            result = Integer.valueOf(no / 10000) * 10000 + 10000;
        }
        return result;
    }
    
    /**
     * 获取同级下一个编号
     * @param no        编号
     * @param figure    位数
     * @return
     * @author 施建波
     */
    public static Integer getNextNo(Integer no, Integer figure)
    {
        Integer result = 0;
        if (null != no && null != figure)
        {
            String noStr = no.toString();
            Integer len = noStr.length();
            if (len % figure == 0)
            {
                String r = StringUtils.repeat("0", figure);
                noStr = no.toString().replaceAll("(" + r + ")+$", "");
                result = Integer.parseInt(StringUtils.rightPad(String.valueOf(Long.parseLong(noStr) + 1), len, "0"));
            }
        }
        return result;
    }
    
    /**
     * 取上一级的编号，如：310102的上级编码为310100
     * @param no
     * @param figure
     * @return
     * @author 张昌苗
     */
    public static Integer getPreNo(Integer no, Integer figure)
    {
        Integer result = 0;
        if (null != no && null != figure)
        {
            Integer js = (int) Math.pow(10, figure);
            result = no / js * js;
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        // System.out.println(RegularUtils.getNextNo(101000001 , 3));
        // System.out.println(RegularUtils.getNextNo(111100));
        System.out.println(getPreNo(315104, 2));
    }
}
