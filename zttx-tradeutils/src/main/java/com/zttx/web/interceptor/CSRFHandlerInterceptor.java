/*
 * @(#)DecorateHeaderDao 2014/3/22 10:52
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <p>File：CSRFHandlerInterceptor</p>
 * <p>Title: </p>
 * <p>Description: 用于配合 spring <form:form> 标签防止 CSRF 攻击 </form:form></p>
 * <p>Copyright: Copyright (c) 2014 2014/3/22 10:52</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class CSRFHandlerInterceptor extends HandlerInterceptorAdapter
{
    private CSRFTokenManager csrfTokenManager;
    
    public void setCsrfTokenManager(CSRFTokenManager csrfTokenManager)
    {
        this.csrfTokenManager = csrfTokenManager;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // 只拦截POST请求
        if (request.getMethod().equalsIgnoreCase("POST"))
        {
            // 检测 CSRF 令牌
            String sessionToken = csrfTokenManager.getTokenForRequest(request);
            String requestToken = csrfTokenManager.getTokenFromRequest(request);
            if (StringUtils.equals(sessionToken, requestToken))
            {
                return true;
            }
            else
            {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad or missing CSRF value");
                return false;
            }
        }
        else
        {
            return true;
        }
    }
}
