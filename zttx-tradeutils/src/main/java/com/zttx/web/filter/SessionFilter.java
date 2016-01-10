/*
 * @(#)SessionFilter 2014/5/8 13:30
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.utils.CookieUtils;

/**
 * 生成交易平台的 sessionId， 用于与 memcached 配合实现交易平台自己的session管理
 *
 * <p>File：SessionFilter</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/5/8 13:30</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class SessionFilter extends OncePerRequestFilter
{
    public static final String SESSION_ID_NAME = "8637_OPERATION_ID";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        synchronized (request)
        {
            String sessionId = CookieUtils.get(request, SESSION_ID_NAME);
            if (StringUtils.isBlank(sessionId))
            {
                sessionId = (String) request.getAttribute(SESSION_ID_NAME);
                if (StringUtils.isBlank(sessionId))
                {
                    sessionId = SerialnoUtils.buildPrimaryKey();
                    CookieUtils.put(request, response, SESSION_ID_NAME, sessionId);
                    request.setAttribute(SESSION_ID_NAME, sessionId);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
    
    @Override
    public void destroy()
    {
    }
}
