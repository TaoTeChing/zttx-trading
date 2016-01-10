/*
 * @(#)BusinessExceptionResolver 2014/4/15 11:18
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zttx.sdk.bean.EnumDescribable;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：BusinessExceptionResolver</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/15 11:18</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class BusinessExceptionResolver extends AbstractBaseExceptionResolver
{
    private static final Logger LOGGER = Logger.getLogger(BusinessExceptionResolver.class);
    
    @Override
    protected boolean isSupportedException(Exception ex)
    {
        return ex instanceof BusinessException;
    }
    
    @Override
    protected ModelAndView doNormalResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception ex)
    {
        BusinessException businessException = (BusinessException) ex;
        // LOGGER.error(ex.getMessage(), ex);
        LOGGER.error(ExceptionUtils.getStackTrace(ex));
        ModelAndView mav = this.getModelAndView(request);
        EnumDescribable errorCodeDescribable = businessException.getErrorCode();
        if (errorCodeDescribable == null)
        {
            mav.addObject("code", ExceptionConst.FAILURE);
            mav.addObject("message", businessException.getMessage());
        }
        else
        {
            mav.addObject("code", errorCodeDescribable.getCode());
            mav.addObject("message", errorCodeDescribable.getMessage());
        }
        return mav;
    }
    
    @Override
    protected ModelAndView doAjaxResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception ex)
    {
        BusinessException businessException = (BusinessException) ex;
        HttpMessageConverter<Object> messageConverter = getJsonMessageConverter();
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        outputMessage.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
        try
        {
            JsonMessage jsonMessage = new JsonMessage();
            EnumDescribable errorCode = businessException.getErrorCode();
            if (errorCode == null)
            {
                jsonMessage.setCode(businessException.getCode() != null ? businessException.getCode() : ExceptionConst.FAILURE);
                jsonMessage.setMessage(businessException.getMessage());
            }
            else
            {
                jsonMessage.setCode(errorCode.getCode());
                jsonMessage.setMessage(errorCode.getMessage());
            }
            messageConverter.write(jsonMessage, MediaType.APPLICATION_JSON, outputMessage);
            return new ModelAndView();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private ModelAndView getModelAndView(HttpServletRequest request)
    {
        String uri = request.getRequestURI();
        if (uri.startsWith("/client/agentMobile") || uri.startsWith("/agentMobile")) return new ModelAndView("agent/mobile/agentMobile_error");
        return new ModelAndView("error/500");
    }
}
