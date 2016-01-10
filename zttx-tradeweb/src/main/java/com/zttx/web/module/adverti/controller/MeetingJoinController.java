/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.adverti.controller;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CommonConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.adverti.entity.MeetingJoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zttx.sdk.core.GenericController;
import com.zttx.web.module.adverti.service.MeetingJoinService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 交易会活动报名 控制器
 * <p>File：MeetingJoinController.java </p>
 * <p>Title: MeetingJoinController </p>
 * <p>Description:MeetingJoinController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/adverti/meetingJoin")
public class MeetingJoinController extends GenericController
{
    @Autowired(required = true)
    private MeetingJoinService meetingJoinService;
    
    /**
     * 保存活动报名
     * @param entity
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(MeetingJoin entity) throws BusinessException
    {
        JsonMessage json = new JsonMessage(CommonConst.SUCCESS);
        if (this.beanValidator(json, entity))
        {
            meetingJoinService.save(entity);
        }
        return json;
    }
}
