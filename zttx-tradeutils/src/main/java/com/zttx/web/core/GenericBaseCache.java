/*
 * @(#)GenericBaseCache.java 2014-3-27 下午2:37:33
 * Copyright 2014 吕海斌, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.core;

import java.util.HashSet;
import java.util.Iterator;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>File：GenericBaseCache.java</p>
 * <p>Title: 缓存层 (Dao + Cache) 业务逻辑不应出现该层 存在问题:1 业务层 需要多个数据层协同完成 此时发生回滚 会导致数据库数据未更新,但缓存已被更新(即产生了脏数据) 2.业务层使用缓存(作用减弱)</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-27 下午2:37:33</p>
 * <p>Company: 8637.com</p>
 * @author 吕海斌  鲍建明修改
 * 	
 * @version 1.0
 */
public abstract class GenericBaseCache implements BaseCache
{
    // 缓存KEY的前缀
    private String            cacheKey      = null;
    
    // 缓存组的前缀 (保留)
    private String            groupKey      = null;
    
    protected Logger          logger        = null;
    
    // 子类申明一下 就表示禁缓存
    protected static boolean  disabledCache = true;
    
    protected int             THREE_TIME    = 60 * 30;
    
    protected int             ONE_TIME      = 60 * 60 * 1;
    
    protected int             ONE_DAY       = 60 * 60 * 24;
    
    protected int             THREE_DAY     = ONE_DAY * 3;
    
    protected int             ONE_WEEK      = ONE_DAY * 7;
    
    protected int             ONE_MONTH     = ONE_DAY * 30;
    
    @Autowired
    protected GenericMemcache genericMemcache;
    
    public GenericBaseCache()
    {
        Class<?> clazz = this.getClass();
        this.logger = Logger.getLogger(clazz);
        this.cacheKey = clazz.getName() + "_";
        this.groupKey = clazz.getName();
    }
    
    private HashSet<String> getGroupKeys()
    {
        @SuppressWarnings("unchecked")
        HashSet<String> keyGroupSet = (HashSet<String>) genericMemcache.get(groupKey);
        if (keyGroupSet == null) keyGroupSet = new HashSet<String>();
        return keyGroupSet;
    }
    
    /**
     * 存入缓存 不放入分组
     * @param key
     * @param object
     * @param expireSecond
     * @return
     */
    protected boolean putIntoCache(String key, Object object, int expireSecond)
    {
        if (disabledCache) return true; // 对象为空,缓存存入失败
        if (null == object) { return false; }
        if (!genericMemcache.put(cacheKey + key, object, expireSecond))
        {
            logger.debug("存入缓存失败: " + cacheKey + key + " = >" + ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE));
            return false;
        }
        putGroupKeys(cacheKey + key);
        return true;
    }
    
    /**
     * disabledCache 全局控制开关 对其无效
     * @param key
     * @param object
     * @param expireSecond
     * @return
     */
    protected boolean putIntoExpireCache(String key, Object object, int expireSecond)
    {
        // 对象为空,缓存存入失败
        if (null == object) { return false; }
        if (!genericMemcache.put(cacheKey + key, object, expireSecond))
        {
            logger.debug("存入缓存失败: " + cacheKey + key + " = >" + ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE));
            return false;
        }
        putGroupKeys(cacheKey + key);
        return true;
    }
    
    /**
     * disabledCache 全局控制开关 对其无效
     * @param key
     * @return
     */
    protected Object getFromExpireCache(String key)
    {
        Object object = genericMemcache.get(cacheKey + key);
        if (object == null) logger.debug("缓存数据不存: " + cacheKey + key);
        return object;
    }
    
    /**
     * 清空 当前组下的 所有缓存 
     * @return
     */
    public boolean clearFromCache()
    {
        HashSet<String> keyGroupSet = getGroupKeys();
        for (String string : keyGroupSet)
            genericMemcache.remove(string);
        genericMemcache.remove(groupKey);
        return true;
    }
    
    /**
     * 存入缓存
     * @param key
     * @param object
     * @return
     */
    protected boolean putIntoCache(String key, Object object)
    {
        if (disabledCache) return true; // 对象为空,缓存存入失败
        if (null == object) { return false; }
        if (!genericMemcache.put(cacheKey + key, object))
        {
            logger.debug("存入缓存失败: " + cacheKey + key + " = >" + ToStringBuilder.reflectionToString(object, ToStringStyle.SHORT_PREFIX_STYLE));
            return false;
        }
        putGroupKeys(cacheKey + key);
        return true;
    }
    
    /**
     * 取出缓存
     * @param key
     * @return
     */
    protected Object getFromCache(String key)
    {
        if (disabledCache) return null;
        Object object = genericMemcache.get(cacheKey + key);
        if (object == null)
        {
            logger.debug("缓存数据不存: " + cacheKey + key);
        }
        return object;
    }
    
    /**
     * 移除缓存
     * @param key
     * @return
     */
    protected boolean delFromCache(String key)
    {
        if (disabledCache) return true;
        if (!genericMemcache.remove(cacheKey + key))
        {
            logger.debug("移除缓存失败: " + cacheKey + key);
            return false;
        }
        delGroupKeys(cacheKey + key);
        return true;
    }
    
    /**
     * 清空 当前组下的 所有缓存 
     * @return
     */
    public boolean clearCache()
    {
        if (disabledCache) return true;
        HashSet<String> keyGroupSet = getGroupKeys();
        for (Iterator<String> itor = keyGroupSet.iterator(); itor.hasNext();)
        {
            genericMemcache.remove(itor.next());
        }
        genericMemcache.remove(groupKey);
        return true;
    }
    
    /**
     * 在增 删 改 单个 修改操作的同时 进行调用 内部需要实现 相关集合缓存的清理工作
     * @return
     */
    public abstract boolean clearCacheForCollect();
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.core.BaseCache#clear(java.lang.String)
     */
    @Override
    public boolean clear(String key)
    {
        if (!genericMemcache.remove(cacheKey + key))
        {
            logger.debug("移除缓存失败: " + cacheKey + key);
            return false;
        }
        return true;
    }
    
    void putGroupKeys(String key)
    {
        HashSet<String> keyGroupSet = getGroupKeys();
        keyGroupSet.add(key);
        genericMemcache.put(groupKey, keyGroupSet);
    }
    
    void delGroupKeys(String key)
    {
        HashSet<String> keyGroupSet = getGroupKeys();
        for (Iterator<String> itor = keyGroupSet.iterator(); itor.hasNext();)
        {
            if (itor.next().contains(key))
            {
                itor.remove();
            }
        }
        genericMemcache.put(groupKey, keyGroupSet);
    }
}
