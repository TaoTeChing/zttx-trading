/*
 * @(#)AbstractMemcache.java 2014-1-8 下午2:26:53
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.core;

import java.util.Map;
import java.util.Set;

import net.rubyeye.xmemcached.GetsResponse;

import com.zttx.web.consts.CacheEnumConst;

/**
 * <p>File：AbstractMemcache.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午2:26:53</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public interface GenericMemcache
{
    /**
     * 将对象放入缓存
     * @param key 缓存键
     * @param object 缓存值
     * @return boolean 是否成功(true：成功，false：失败)
     */
    public boolean put(String key, Object object);
    
    /**
     * 将对象放入缓存并指定过期时间
     * @param key 缓存键
     * @param object 缓存值
     * @param expireSecond 过期时间，单位为秒
     * @return boolean 是否成功(true：成功，false：失败)
     */
    public boolean put(String key, Object object, int expireSecond);
    
    /**
     * 将对象缓存在指定命名空间中
     * @param ns 命名空间
     * @param key 缓存键
     * @param object 缓存值
     * @param expireSecend 过期时间
     * @return 夏铭
     */
    public boolean put(CacheEnumConst ns, String key, Object object, int expireSecend);
    
    /**
     * 根据缓存键从缓存中读取缓存值
     * @param key 缓存键
     * @return T 读取的缓存对象
     */
    public Object get(String key);
    
    /**
     * 根据缓存键从缓存中读取缓存值
     * @param key
     * @return
     */
    public Map<String, Object> get(Set<String> key);
    
    /**
     * 从指定命名空间中读取缓存值
     * @param ns 命名空间
     * @param key 缓存键
     * @param defaultValue 默认值, 当找不到缓存时, 返回的默认值
     * @return
     */
    public Object get(CacheEnumConst ns, String key, Object defaultValue);
    
    /**
     * 将缓存键为key的缓存值以entity进行替换
     * @param key 缓存键
     * @param object 将要缓存的对象
     * @return boolean 是否成功(true：成功，false：失败)
     */
    public boolean replace(String key, Object object);
    
    /**
     * 将缓存键为key的缓存值以entity进行替换，并指定过期时间
     * @param key 缓存键
     * @param object 将要缓存的对象
     * @param expireSecond 过期时间，单位为秒
     * @return boolean 是否成功(true：成功，false：失败)
     */
    public boolean replace(String key, Object object, int expireSecond);
    
    /**
     * 根据key传删除缓存对象
     * @param key 缓存键
     * @return boolean 是否成功(true：成功，false：失败)
     */
    public boolean remove(String key);
    
    /**
     * 从缓存中删除Set集合中所有key
     * @param keyPrefix
     * @return
     */
    public boolean delGroupKeySet(String keyPrefix);
    
    /**
     * 从缓存中删除Set集合中指定的key
     * @param keyPrefix
     * @param key
     * @return
     */
    public boolean delGroupKeySet(String keyPrefix, String key);
    
    /**
     * 将key和value存入缓存，并将key放入key为keyPrefix的组里
     * @param keyPrefix
     * @param key
     * @param value
     * @return
     */
    public boolean putGroupKeySet(String keyPrefix, String key, Object value);
    
    /**
     * 将key和value存入缓存，并将key放入key为keyPrefix的组里
     * @param keyPrefix
     * @param key
     * @param value
     * @param expireSecond
     * @return
     */
    public boolean putGroupKeySet(String keyPrefix, String key, Object value, int expireSecond);
    
    /**
     * 根据缓存键从缓存中读取带版本号的缓存值
     * @param key 缓存键
     * @return T 读取的缓存对象
     */
    public GetsResponse<Object> gets(String key);
    
    /**
     * 根据缓存版本号修改缓存值
     * @param key
     * @param expireSecond
     * @param object
     * @param cas
     * @author 施建波
     */
    public Boolean cas(String key, int expireSecond, Object object, Long cas);
    
    /**
     * 开启缓存锁
     * @param cacheKey
     * @param num
     * @author 施建波
     */
    public Boolean lockCache(String cacheKey, Integer num);
    
    /**
     * 关闭缓存锁
     * @param cacheKey      缓存KEY
     * @author 施建波
     */
    public void unlockCache(String cacheKey);
    
    /**
     * 批量开启缓存锁
     * @param preCacheKey
     * @param idArr
     * @return
     * @author 张昌苗
     */
    public Boolean lockBatCache(String preCacheKey, String[] idArr);
    
    /**
     * 批量关闭缓存锁
     * @param preCacheKey
     * @param idArr
     * @author 张昌苗
     */
    public void unlockBatCache(String preCacheKey, String[] idArr);
}
