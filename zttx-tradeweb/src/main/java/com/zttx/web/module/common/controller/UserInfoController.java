/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;

/**
 * 用户信息 控制器
 * <p>File：UserInfoController.java </p>
 * <p>Title: UserInfoController </p>
 * <p>Description:UserInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/userInfo")
public class UserInfoController extends GenericController
{
    @Autowired(required = true)
    private UserInfoService userInfoService;

    
    @RequestMapping("/list")
    public String listUser(){
        return "common/list_user_info";
    }
    
    @RequestMapping("/data")
    @ResponseBody
    public JsonMessage data(Pagination pagination, UserInfo userInfo, HttpServletRequest request){
        userInfo.setPage(pagination);
        List<UserInfo> userInfoList=userInfoService.findList(userInfo);
        PaginateResult<UserInfo> result = new PaginateResult<UserInfo>(userInfo.getPage(), userInfoList);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
