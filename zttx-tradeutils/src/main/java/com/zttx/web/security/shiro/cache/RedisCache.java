package com.zttx.web.security.shiro.cache;

import com.google.common.collect.Sets;
import com.zttx.sdk.utils.JedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * <p>File：RedisCache.java </p>
 * <p>Title: 自定义REDIS缓存 </p>
 * <p>Description:RedisCache </p>
 * <p>Copyright: Copyright (c) 15/10/16 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class RedisCache<K, V> implements Cache<K, V>
{
    private Logger logger       = LoggerFactory.getLogger(getClass());
    
    private String cacheKeyName = null;
    
    public RedisCache(String cacheKeyName)
    {
        this.cacheKeyName = cacheKeyName;
    }
    
    @Override
    public V get(K key) throws CacheException
    {
        if (key == null) { return null; }
        V value = null;
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            value = (V) JedisUtils.toObject(jedis.hget(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key)));
        }
        catch (Exception e)
        {
            logger.error("get {} {} {}", cacheKeyName, key, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return value;
    }
    
    @Override
    public V put(K key, V value) throws CacheException
    {
        if (key == null) { return null; }
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            jedis.hset(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key), JedisUtils.toBytes(value));
            logger.debug("put {} {} = {}", cacheKeyName, key, value);
        }
        catch (Exception e)
        {
            logger.error("put {} {}", cacheKeyName, key, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return value;
    }
    
    @Override
    public V remove(K key) throws CacheException
    {
        V value = null;
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            value = (V) JedisUtils.toObject(jedis.hget(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key)));
            jedis.hdel(JedisUtils.getBytesKey(cacheKeyName), JedisUtils.getBytesKey(key));
            logger.debug("remove {} {}", cacheKeyName, key);
        }
        catch (Exception e)
        {
            logger.warn("remove {} {}", cacheKeyName, key, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return value;
    }
    
    @Override
    public void clear() throws CacheException
    {
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            jedis.del(JedisUtils.getBytesKey(cacheKeyName));
            if (logger.isDebugEnabled())
            {
                logger.debug("clear shiro cache key {}", cacheKeyName);
            }
        }
        catch (Exception e)
        {
            logger.error("clear shiro cache key {} {}", cacheKeyName, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
    }
    
    @Override
    public int size()
    {
        int size = 0;
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            size = jedis.hlen(JedisUtils.getBytesKey(cacheKeyName)).intValue();
            logger.debug("size {} {} ", cacheKeyName, size);
            return size;
        }
        catch (Exception e)
        {
            logger.error("clear {}", cacheKeyName, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return size;
    }
    
    @Override
    public Set<K> keys()
    {
        Set<K> keys = Sets.newHashSet();
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            Set<byte[]> set = jedis.hkeys(JedisUtils.getBytesKey(cacheKeyName));
            for (byte[] key : set)
            {
                keys.add((K) key);
            }
            logger.debug("keys {} {} ", cacheKeyName, keys);
            return keys;
        }
        catch (Exception e)
        {
            logger.error("keys {}", cacheKeyName, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return keys;
    }
    
    @Override
    public Collection<V> values()
    {
        Collection<V> vals = Collections.emptyList();
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            Collection<byte[]> col = jedis.hvals(JedisUtils.getBytesKey(cacheKeyName));
            for (byte[] val : col)
            {
                vals.add((V) val);
            }
            logger.debug("values {} {} ", cacheKeyName, vals);
            return vals;
        }
        catch (Exception e)
        {
            logger.error("values {}", cacheKeyName, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return vals;
    }
}