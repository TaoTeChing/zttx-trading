/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zttx.web.security.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;

import com.google.common.collect.Sets;
import com.zttx.sdk.consts.CacheConst;
import com.zttx.sdk.utils.DateUtils;
import com.zttx.sdk.utils.JedisUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * <p>File：RedisSessionDAO.java </p>
 * <p>Title:  自定义授权会话实现类 </p>
 * <p>Description: RedisSessionDAO </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 09:52</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class RedisSessionDAO extends AbstractSessionDAO implements CustomSessionDAO
{
    private static final Logger logger         = LoggerFactory.getLogger(RedisSessionDAO.class);
    
    public static final String  SESSION_GROUPS = CacheConst.REDIS_TRADE_SHIRO_SESSION_PREFIX;
    
    public static final String  SESSION_PREFIX = "session_";
    
    @Override
    public void update(Session session) throws UnknownSessionException
    {
        if (session == null || session.getId() == null) { return; }
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            String key = String.valueOf(SESSION_PREFIX + session.getId());
            byte[] keyByte = JedisUtils.getBytesKey(key);
            jedis.set(keyByte, JedisUtils.toBytes(session));
            // 设置超期时间
            int timeoutSeconds = (int) (session.getTimeout() / 1000);
            jedis.expire(keyByte, timeoutSeconds);
            // 获取登录者编号
            UserPrincipal principal = null;
            SimplePrincipalCollection collection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != collection)
            {
                principal = (UserPrincipal) collection.getPrimaryPrincipal();
            }
            String principalId = principal != null ? principal.getRefrenceId() : StringUtils.EMPTY;
            jedis.hset(SESSION_GROUPS, key, principalId + "|" + session.getTimeout() + "|" + session.getLastAccessTime().getTime());
        }
        catch (Exception e)
        {
            logger.error("update {} {}", session.getId(), e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
    }
    
    /**
     * 清空会话及缓存
     */
    public static void clean()
    {
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            Map<String, String> map = jedis.hgetAll(SESSION_GROUPS);
            jedis.del(SESSION_GROUPS);
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                jedis.del(JedisUtils.getBytesKey(entry.getKey()));
                if (logger.isDebugEnabled())
                {
                    logger.debug("remove session {} ", entry.getKey());
                }
            }
        }
        catch (Exception e)
        {
            logger.error("getActiveSessions", e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
    }
    
    @Override
    public void delete(Session session)
    {
        if (session == null || session.getId() == null) { return; }
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            String key = String.valueOf(SESSION_PREFIX + session.getId());
            jedis.hdel(SESSION_GROUPS, key);
            jedis.del(JedisUtils.getBytesKey(key));
            logger.debug("delete {} ", session.getId());
        }
        catch (Exception e)
        {
            logger.error("delete {} ", session.getId(), e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
    }
    
    @Override
    public Collection<Session> getActiveSessions()
    {
        return getActiveSessions(true);
    }
    
    /**
     * 获取活动会话
     * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave)
    {
        return getActiveSessions(includeLeave, null, null);
    }
    
    /**
     * 获取活动会话
     * @param includeLeave 是否包括离线（最后访问时间大于3分钟为离线会话）
     * @param principal 根据登录者对象获取活动会话
     * @param filterSession 不为空，则过滤掉（不包含）这个会话。
     * @return
     */
    @Override
    public Collection<Session> getActiveSessions(boolean includeLeave, Object principal, Session filterSession)
    {
        Set<Session> sessions = Sets.newHashSet();
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            Map<String, String> map = jedis.hgetAll(SESSION_GROUPS);
            for (Map.Entry<String, String> e : map.entrySet())
            {
                if (StringUtils.isNotBlank(e.getKey()) && StringUtils.isNotBlank(e.getValue()))
                {
                    String[] ss = StringUtils.split(e.getValue(), "|");
                    if (ss != null && ss.length == 3)
                    {
                        SimpleSession session = new SimpleSession();
                        session.setId(e.getKey());
                        session.setAttribute("principalId", ss[0]);
                        session.setTimeout(Long.valueOf(ss[1]));
                        session.setLastAccessTime(new Date(Long.valueOf(ss[2])));
                        try
                        {
                            // 验证SESSION
                            session.validate();
                            boolean isActiveSession = false;
                            // 不包括离线并符合最后访问时间小于等于3分钟条件。
                            if (includeLeave || DateUtils.pastMinutes(session.getLastAccessTime()) <= 3)
                            {
                                isActiveSession = true;
                            }
                            // 过滤掉的SESSION
                            if (filterSession != null && filterSession.getId().equals(session.getId()))
                            {
                                isActiveSession = false;
                            }
                            if (isActiveSession)
                            {
                                sessions.add(session);
                            }
                        }
                        // SESSION验证失败
                        catch (Exception e2)
                        {
                            jedis.hdel(SESSION_GROUPS, e.getKey());
                            if (jedis.exists(e.getKey())) jedis.del(e.getKey());
                        }
                    }
                    // 存储的SESSION不符合规则
                    else
                    {
                        jedis.hdel(SESSION_GROUPS, e.getKey());
                        if (jedis.exists(e.getKey())) jedis.del(e.getKey());
                    }
                }
                // 存储的SESSION无Value
                else if (StringUtils.isNotBlank(e.getKey()))
                {
                    jedis.hdel(SESSION_GROUPS, e.getKey());
                    if (jedis.exists(e.getKey())) jedis.del(e.getKey());
                }
            }
            logger.info("getActiveSessions size: {} ", sessions.size());
        }
        catch (Exception e)
        {
            logger.error("getActiveSessions", e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return sessions;
    }
    
    @Override
    protected Serializable doCreate(Session session)
    {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.update(session);
        return sessionId;
    }
    
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        Session session = null;
        ShardedJedis jedis = null;
        try
        {
            jedis = JedisUtils.getResource();
            String key = String.valueOf(SESSION_PREFIX + sessionId);
            session = (Session) JedisUtils.toObject(jedis.get(JedisUtils.getBytesKey(key)));
        }
        catch (Exception e)
        {
            logger.error("doReadSession {} {}", sessionId, e);
        }
        finally
        {
            JedisUtils.returnResource(jedis);
        }
        return session;
    }
    
    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException
    {
        try
        {
            return super.readSession(sessionId);
        }
        catch (UnknownSessionException e)
        {
            return null;
        }
    }
}
