/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zttx.web.security.shiro.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.zttx.sdk.consts.CacheConst;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * <p>File：RedisCacheManager.java </p>
 * <p>Title: 自定义授权缓存管理类 </p>
 * <p>Description: RedisCacheManager </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 09:52</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class RedisCacheManager implements CacheManager
{
    // fast lookup by name map
    private static final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();
    
    /**
     * 清空缓存
     */
    public static void clear()
    {
        if (null != caches && caches.size() > 0)
        {
            for (Cache cache : caches.values())
            {
                cache.clear();
            }
        }
    }
    
    /**
     * 取缓存对象
     *
     * @param name
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException
    {
        Cache cache = caches.get(name);
        if (null == cache)
        {
            // create a new cache instance
            cache = new RedisCache<>(CacheConst.REDIS_TRADE_SHIRO_CACHE_PREFIX);
            // add it to the cache collection
            caches.put(name, cache);
        }
        return cache;
    }
}
