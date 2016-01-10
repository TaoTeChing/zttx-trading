/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.Suggestions;
import com.zttx.web.module.common.service.SuggestionsService;
import com.zttx.web.security.RedisSessionManager;

/**
 * 意见和建议 控制器
 * <p>File：SuggestionsController.java </p>
 * <p>Title: SuggestionsController </p>
 * <p>Description:SuggestionsController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/suggestion")
public class SuggestionsController extends GenericController
{
    @Autowired(required = true)
    private SuggestionsService  suggestionsService;
    
    @Autowired
    private RedisSessionManager redisSessionManager;
    
    /**
     * 保存留言
     * @param request 
     * @param suggestion
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @RequestMapping("/add")
    @ResponseBody
    public JsonMessage save(HttpServletRequest request, Suggestions suggestion) throws BusinessException
    {
        String sessionCaptext = getSessionCaptext(request);
        String requestCaptcha = request.getParameter("captcha");
        // 由于公用的验证码生成器放入缓存中的验证没有统一大小写，所以这里统一转换。
        if (sessionCaptext != null) sessionCaptext = sessionCaptext.toLowerCase();
        if (requestCaptcha != null) requestCaptcha = requestCaptcha.toLowerCase();
        if (!requestCaptcha.equals(sessionCaptext)) { return getJsonMessage(CommonConst.VALID_ERR); }
        JsonMessage json = new JsonMessage(CommonConst.SUCCESS);
        if (this.beanValidator(json, suggestion))
        {
            suggestion.setCreateTime(CalendarUtils.getCurrentTime());
            suggestion.setUpdateTime(CalendarUtils.getCurrentTime());
            suggestionsService.insert(suggestion);
        }
        return json;
    }
    
    /**
     * 取出群集会话中的验证码，随后清除
     * @param request
     * @return
     */
    protected String getSessionCaptext(HttpServletRequest request)
    {
        String sessionCaptext = redisSessionManager.getString(request, RedisSessionManager.SessionKey.CAPTCHA);
        RedisSessionManager.remove(request, RedisSessionManager.SessionKey.CAPTCHA);
        return sessionCaptext;
    }
}
