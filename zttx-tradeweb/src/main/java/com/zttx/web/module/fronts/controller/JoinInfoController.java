/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.IPUtil;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.module.fronts.entity.JoinInfo;
import com.zttx.web.module.fronts.service.JoinInfoService;

/**
 * 加盟入驻信息 控制器
 * <p>File：JoinInfoController.java </p>
 * <p>Title: JoinInfoController </p>
 * <p>Description:JoinInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/fronts/joinInfo")
public class JoinInfoController extends GenericController
{
    @Autowired(required = true)
    private JoinInfoService joinInfoService;
    
    /**
     * 保存加盟信息
     * @param joinInfo 加盟信息
     * @return JsonMessage
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(JoinInfo joinInfo, HttpServletRequest request) throws BusinessException
    {
        joinInfo.setCreateIp(IPUtil.getIpAddr(request));
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, joinInfo))
        {
            joinInfoService.save(joinInfo);
        }
        return jsonMessage;
    }
}
