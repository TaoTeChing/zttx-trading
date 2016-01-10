/*
 * @(#)GenericMemcacheImpl.java 2014-2-19 下午1:20:36
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.core;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientCallable;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.zttx.web.consts.CacheEnumConst;

/**
 * <p>File：GenericMemcacheImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-19 下午1:20:36</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class GenericMemcacheImpl implements GenericMemcache
{
    private static final Logger logger = Logger.getLogger(GenericMemcacheImpl.class);
    
    private MemcachedClient     memcachedClient;
    
    public void setMemcachedClient(MemcachedClient memcachedClient)
    {
        this.memcachedClient = memcachedClient;
    }
    
    @Override
    public boolean put(String key, Object object)
    {
        return this.put(key, object, 0);
    }
    
    @Override
    public boolean put(String key, Object object, int expireSecond)
    {
        boolean bool = false;
        try
        {
            bool = memcachedClient.set(key, expireSecond, object);
        }
        catch (TimeoutException e)
        {
            logger.error(e);
        }
        catch (InterruptedException e)
        {
            logger.error(e);
        }
        catch (MemcachedException e)
        {
            logger.error(e);
        }
        return bool;
    }
    
    @Override
    public boolean put(CacheEnumConst ns, String key, Object object, int expireSecend)
    {
        try
        {
            this.memcachedClient.withNamespace(ns.getKey(), new MemcachedClientCallable<Void>()
            {
                @Override
                public Void call(MemcachedClient client) throws MemcachedException, InterruptedException, TimeoutException
                {
                    return null;
                }
            });
        }
        catch (Exception e)
        {
            logger.error(e);
            return false;
        }
        return true;
    }
    
    @Override
    public Object get(String key)
    {
        Object object = null;
        try
        {
            object = memcachedClient.get(key);
        }
        catch (TimeoutException e)
        {
            logger.error(e);
        }
        catch (InterruptedException e)
        {
            logger.error(e);
        }
        catch (MemcachedException e)
        {
            logger.error(e);
        }
        return object;
    }
    
    @Override
    public Map<String, Object> get(Set<String> key)
    {
        Map<String, Object> object = null;
        try
        {
            object = memcachedClient.get(key);
        }
        catch (TimeoutException e)
        {
            logger.error(e);
        }
        catch (InterruptedException e)
        {
            logger.error(e);
        }
        catch (MemcachedException e)
        {
            logger.error(e);
        }
        return object;
    }
    
    @Override
    public Object get(CacheEnumConst ns, final String key, Object defaultValue)
    {
        Object object = null;
        try
        {
            object = this.memcachedClient.withNamespace(ns.getKey(), new MemcachedClientCallable<Object>()
            {
                @Override
                public Object call(MemcachedClient client) throws MemcachedException, InterruptedException, TimeoutException
                {
                    return client.get(key);
                }
            });
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        if (object == null && defaultValue != null) return defaultValue;
        return object;
    }
    
    @Override
    public boolean replace(String key, Object object)
    {
        return this.replace(key, object, 0);
    }
    
    @Override
    public boolean replace(String key, Object object, int expireSecond)
    {
        boolean bool = false;
        try
        {
            bool = memcachedClient.replace(key, 0, object);
        }
        catch (TimeoutException e)
        {
            logger.error(e);
        }
        catch (InterruptedException e)
        {
            logger.error(e);
        }
        catch (MemcachedException e)
        {
            logger.error(e);
        }
        return bool;
    }
    
    @Override
    public boolean remove(String key)
    {
        boolean bool = false;
        try
        {
            bool = memcachedClient.delete(key);
        }
        catch (TimeoutException e)
        {
            logger.error(e);
        }
        catch (InterruptedException e)
        {
            logger.error(e);
        }
        catch (MemcachedException e)
        {
            logger.error(e);
        }
        return bool;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean delGroupKeySet(String keyPrefix)
    {
        Set<String> keySet = (Set<String>) get(keyPrefix);
        if (keySet != null) for (String key : keySet)
            remove(key);
        return Boolean.TRUE;
    }
    
    @Override
    public boolean delGroupKeySet(String keyPrefix, String key)
    {
        if (null == key) return Boolean.FALSE;
        Set<String> keySet = (Set<String>) get(keyPrefix);
        if (keySet != null) for (String str : keySet)
            if (key.equals(str))
            {
                remove(key);
                break;
            }
        return Boolean.TRUE;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean putGroupKeySet(String keyPrefix, String key, Object value)
    {
        if (value == null || key == null || "".equals(key)) return false;
        if (put(keyPrefix + key, value))
        {
            Set<String> keySet = (Set<String>) get(keyPrefix);
            if (keySet == null) keySet = new HashSet<String>();
            keySet.add(keyPrefix + key);
            return put(keyPrefix, keySet);
        }
        return true;
    }
    
    @Override
    public boolean putGroupKeySet(String keyPrefix, String key, Object value, int expireSecond)
    {
        if (value == null || key == null || "".equals(key)) return false;
        if (put(keyPrefix + key, value, expireSecond))
        {
            Set<String> keySet = (Set<String>) get(keyPrefix);
            if (keySet == null) keySet = new HashSet<String>();
            keySet.add(keyPrefix + key);
            return put(keyPrefix, keySet);
        }
        return true;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.general.core.GenericMemcache#gets(java.lang.String)
     */
    @Override
    public GetsResponse<Object> gets(String key)
    {
        GetsResponse<Object> object = null;
        try
        {
            object = memcachedClient.gets(key);
        }
        catch (TimeoutException e)
        {
            logger.error(e);
        }
        catch (InterruptedException e)
        {
            logger.error(e);
        }
        catch (MemcachedException e)
        {
            logger.error(e);
        }
        return object;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.general.core.GenericMemcache#cas(java.lang.String, int, java.lang.Object, java.lang.Long)
     */
    @Override
    public Boolean cas(String key, int expireSecond, Object object, Long cas)
    {
        boolean bool = false;
        try
        {
            bool = memcachedClient.cas(key, expireSecond, object, cas);
        }
        catch (TimeoutException e)
        {
            logger.error(e);
        }
        catch (InterruptedException e)
        {
            logger.error(e);
        }
        catch (MemcachedException e)
        {
            logger.error(e);
        }
        return bool;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.core.GenericMemcache#lockCache(java.lang.String)
     */
    @Override
    public Boolean lockCache(String cacheKey, Integer num)
    {
        Integer expireSecond = 5 * 60;
        cacheKey = getLockCacheName(cacheKey);
        Object cacheObj = get(cacheKey);
        if (null == cacheObj) put(cacheKey, 0, expireSecond);
        num = (null == num) ? 1 : num;
        for (int i = 0; i < num; i++)
        {
            GetsResponse<Object> obj = gets(cacheKey);
            Integer number = Integer.parseInt(obj.getValue().toString());
            if (number.intValue() == 0) if (cas(cacheKey, expireSecond, 1, obj.getCas())) return true;
            try
            {
                Thread.sleep(50);
            }
            catch (Exception e)
            {
                return false;
            }
        }
        return false;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.core.GenericMemcache#unlockCache(java.lang.String)
     */
    @Override
    public void unlockCache(String cacheKey)
    {
        cacheKey = getLockCacheName(cacheKey);
        this.remove(cacheKey);
    }
    
    private String getLockCacheName(String cacheKey)
    {
        return cacheKey + "_synchro";
    }
    
    @Override
    public Boolean lockBatCache(String preCacheKey, String[] idArr)
    {
        if (ArrayUtils.isEmpty(idArr)) return true;
        int i = 0;
        try
        {
            for (; i < idArr.length; i++)
            {
                Boolean isLock = lockCache(preCacheKey + idArr[i], null);
                if (!isLock) break;
            }
        }
        catch (Exception e)
        {
        }
        if (i == idArr.length) return true;
        else
        {
            for (int j = 0; j < i; j++)
                unlockCache(preCacheKey + idArr[j]);
            return false;
        }
    }
    
    @Override
    public void unlockBatCache(String preCacheKey, String[] idArr)
    {
        for (String element : idArr)
            unlockCache(preCacheKey + element);
    }
}
