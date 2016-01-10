/*
 * @(#)DecorateHeaderDao 2014/3/22 10:52
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.support.RequestDataValueProcessor;

/**
 * <p>File：CSRFRequestDataValueProcessor</p>
 * <p>Title: </p>
 * <p>Description: 用于配合 spring <form:form> 标签 在渲染时加入 csrf token 隐藏域</p>
 * <p>Copyright: Copyright (c) 2014 2014/3/22 10:52</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class CSRFRequestDataValueProcessor implements RequestDataValueProcessor
{
    private CSRFTokenManager csrfTokenManager;
    
    public void setCsrfTokenManager(CSRFTokenManager csrfTokenManager)
    {
        this.csrfTokenManager = csrfTokenManager;
    }
    
    @Override
    public String processAction(HttpServletRequest request, String action)
    {
        return action;
    }
    
    @Override
    public String processFormFieldValue(HttpServletRequest request, String name, String value, String type)
    {
        return value;
    }
    
    @Override
    public Map<String, String> getExtraHiddenFields(HttpServletRequest request)
    {
        Map<String, String> hiddenFields = new HashMap<>();
        hiddenFields.put(CSRFTokenManager.CSRF_TOKEN_NAME, csrfTokenManager.getTokenForRequest(request));
        return hiddenFields;
    }
    
    @Override
    public String processUrl(HttpServletRequest request, String url)
    {
        return url;
    }
}
