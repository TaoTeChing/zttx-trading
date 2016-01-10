/*
 * @(#)ClientApiInterceptor 2014/6/10 16:34
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.module.common.entity.ClientKey;
import com.zttx.web.module.common.service.ClientKeyService;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：ClientApiInterceptor.java </p>
 * <p>Title: RPC 远程调用拦截器 </p>
 * <p>Description: ClientApiInterceptor </p>
 * <p>Copyright: Copyright (c) 2014 08/10/2015 14:31</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class ClientApiInterceptor extends HandlerInterceptorAdapter
{
    private static final Logger logger = LoggerFactory.getLogger(ClientApiInterceptor.class);
    
    @Autowired
    private ClientKeyService    clientKeyService;
    
    /**
     * 数据验证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        if (!request.getMethod().equalsIgnoreCase("POST")) { throw new BusinessException(ClientConst.ILLEGAL_REQUEST); }
        if (logger.isInfoEnabled())
        {
            logger.info("Client IP:" + IPUtil.getOriginalIpAddr(request));
            StringBuilder sb = new StringBuilder(request.getScheme());
            sb.append("://").append(request.getServerName()).append(":");
            sb.append(request.getServerPort());
            sb.append(request.getContextPath());
            sb.append(request.getRequestURI());
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString))
            {
                sb.append("?").append(queryString);
            }
            logger.info("Request url:" + sb.toString());
            Enumeration<?> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements())
            {
                String paramName = (String) parameterNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                StringBuilder valuesString = new StringBuilder(paramName).append(": ");
                for (String paramValue : paramValues)
                {
                    valuesString.append(paramValue);
                }
                logger.info(valuesString.toString());
            }
        }
        // 用户KEY验证
        String userKey = StringUtils.trimToEmpty(request.getParameter(ClientParameter.USER_KEY));
        if (StringUtils.isBlank(userKey)) { throw new BusinessException(ClientConst.KEYERROR); }
        ClientKey clientKey = clientKeyService.findByKey(userKey);
        if (clientKey == null) { throw new BusinessException(ClientConst.KEYERROR); }
        if (clientKey.getUserLimit())
        {
            // 如果需要白名单验证
            Boolean bol = clientKeyService.isExistNetAddress(clientKey.getUserKey(), clientKey.getAccessType(), IPUtil.getIpAddr(request));
            if (!bol) throw new BusinessException(ClientConst.KEYERROR);
        }
        // 异或校验验证
        String userDes = StringUtils.trimToEmpty(request.getParameter(ClientParameter.USER_DES));
        if (StringUtils.isBlank(userDes)) throw new BusinessException(ClientConst.DESERROR);
        // 数据长度验证
        String lenStr = StringUtils.trimToEmpty(request.getParameter(ClientParameter.DATA_LEN));
        if (!StringUtils.isNumeric(lenStr)) throw new BusinessException(ClientConst.LENERROR);
        int dataLen = Integer.parseInt(lenStr);
        String data = request.getParameter(ClientParameter.DATA);
        if (!ParameterUtils.validateClientDataLength(dataLen, data)) throw new BusinessException(ClientConst.LENERROR);
        if (!ParameterUtils.validateClientData(userDes, userKey, dataLen)) throw new BusinessException(ClientConst.DESERROR);
        return true;
    }
}
