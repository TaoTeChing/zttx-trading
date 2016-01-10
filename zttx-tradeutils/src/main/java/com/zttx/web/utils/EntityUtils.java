package com.zttx.web.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericEntity;

/**
 * EntityUtils
 * 实体类工具类
 * Created by 李星 on 2015/8/17.
 */
public class EntityUtils
{
    private static final BigDecimal BIGDECIMAL_ZERO = new BigDecimal("0");
    
    private static final BigDecimal BIGDECIMAL_DEFAULT = null;
    
    private static final Date       DATE_DEFAULT       = null;
    static
    {
        // 注册默认值
        BigDecimalConverter bigDecimalConverter = new BigDecimalConverter(BIGDECIMAL_DEFAULT);
        DateConverter dateConverter = new DateConverter(DATE_DEFAULT);
        ConvertUtils.register(bigDecimalConverter, java.math.BigDecimal.class);
        ConvertUtils.register(dateConverter, java.util.Date.class);
    }

    /**
     * 赋值,复制属性
     * @param dest 目的Entity
     * @param orig 源Entity
     */
    public static void copyProperties(GenericEntity dest, GenericEntity orig)
    {
        try
        {
            if (null != dest && null != orig) BeanUtils.copyProperties(dest, orig);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 通过Entity构造Model,同时给其赋值,复制Entity属性给Model
     * @param clazz Model对象的Class
     * @param orig 源Entity
     * @param <E>
     * @return
     */
    public static <E extends GenericEntity> E buildModelByEntity(Class<E> clazz, GenericEntity orig)
    {
        try
        {
            GenericEntity model = clazz.newInstance();
            copyProperties(model, orig);
            return (E) model;
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 根据Entity集合构造Model集合,并一一赋值
     * @param clazz
     * @param entityList
     * @param <E>
     * @return
     */
    public static <E extends GenericEntity> List<E> buildModelListByEntityList(Class<E> clazz, List<GenericEntity> entityList)
    {
        List<E> result = Lists.newArrayList();
        for (GenericEntity entity : entityList)
        {
            try
            {
                E model = buildModelByEntity(clazz, entity);
                result.add(model);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}
