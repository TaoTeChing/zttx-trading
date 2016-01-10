/*
 * @(#)ListUtils.java 2014-5-12 下午5:23:49
 * Copyright 2014 施建波, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * <p>File：ListUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-12 下午5:23:49</p>
 * <p>Company: 8637.com</p>
 * @author 施建波
 * @version 1.0
 */
public class ListUtils
{

    //集合判空
    public static Boolean isNotEmpty(List list)
    {
        if(null!=list && list.size()>0)
        {
            return true;
        }
        return false;
    }
    //集合判等
    public static Boolean isSizeEqual(List listOne,List listTwo)
    {
        if(isNotEmpty(listTwo)&&isNotEmpty(listOne))
        {
            return listOne.size()==listTwo.size()?true:false;
        }
        return false;
    }

    // 克隆LIST中的对象
    @SuppressWarnings("unchecked")
    public static <E> List<E> clone(List<E> dbValueList)
    {
        try
        {
            if (!CollectionUtils.isEmpty(dbValueList))
            {
                List<E> cloneList = Lists.newArrayList();
                for (Object obj : dbValueList)
                {
                    cloneList.add((E) BeanUtils.cloneBean(obj));
                }
                return cloneList;
            }
        }
        catch (Exception e)
        {
        }
        return Lists.newArrayList();
    }
    
    /***
     * 将集合转换为字符串,元素之间以','分隔。<br>
     * @param list Collection
     * @return String 转换后的字符串
     */
    public static String join2String(Collection list)
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            if (i != 0) sb.append(",");
            sb.append((String) it.next());
            i++;
        }
        return sb.toString();
    }
    
    /***
     * 将集合转换为字符串,每个元素添加''号,元素之间以','分隔。<br>
     * @param list Collection
     * @return String 转换后的字符串
     */
    public static String join2String2(Collection list)
    {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            if (i != 0) sb.append(",");
            sb.append("'" + (String) it.next() + "'");
            i++;
        }
        return sb.toString();
    }
}
