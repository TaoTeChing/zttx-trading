/*
 * @(#)DecorateHeaderDao 2014/3/22 10:52
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.zttx.web.security.RedisSessionManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.zttx.web.security.utils.IdGen;

/**
 * <p>File：CSRFRequestDataValueProcessor</p>
 * <p>Title: </p>
 * <p>Description: csrf token 生成工具 </p>
 * <p>Copyright: Copyright (c) 2014 2014/3/22 10:52</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public final class CSRFTokenManager
{
    private static final Logger LOGGER     = Logger.getLogger(CSRFTokenManager.class);
    
    /**
     * The token parameter name
     */
    public static final String  CSRF_TOKEN_NAME = "csrfToken";
    
    private RedisSessionManager redisSessionManager;
    
    public void setRedisSessionManager(RedisSessionManager redisSessionManager)
    {
        this.redisSessionManager = redisSessionManager;
    }
    
    public String getTokenForRequest(HttpServletRequest request)
    {
        String token;
        synchronized (request)
        {
            token = redisSessionManager.getString(request, RedisSessionManager.SessionKey.CSRF_KEY);
            if (StringUtils.isNotBlank(token))
            {// 更新会话时间
                redisSessionManager.expire(request, RedisSessionManager.SessionKey.CSRF_KEY);
            }
            else
            {
                token = IdGen.uuid();
                LOGGER.info("查找不到 csrf token, 重新生成; token:" + token);
                redisSessionManager.put(request, RedisSessionManager.SessionKey.CSRF_KEY, token);
            }
        }
        return token;
    }
    
    /**
     * 移出CSRF TOKEN_NAME
     * @param request
     */
    public void removeTokenForRequest(HttpServletRequest request)
    {
        RedisSessionManager.remove(request, RedisSessionManager.SessionKey.CSRF_KEY);
    }
    
    /**
     * Extracts the token value from the session
     * 
     * @param request
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request)
    {
        String token = request.getHeader(CSRF_TOKEN_NAME);
        if (StringUtils.isBlank(token)) token = request.getParameter(CSRF_TOKEN_NAME);
        return token;
    }
}
