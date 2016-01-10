/*
 * @(#)BrandUsermClientController.java 2014 12 4 16:13:27
 * Copyright 2014 李飞欧, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：BrandUsermClientController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014 12 4 16:13:27</p>
 * <p>Company: 8637.com</p>
 * @author 李飞欧
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandUserm")
public class BrandUsermClientController extends GenericController
{
    @Autowired
    private UserInfoService userInfoService;
    
    /**
     * 同步全部用户信息
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/allUserm_crm", method = RequestMethod.POST)
    public JsonMessage allOrder_crm(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        Pagination page = new Pagination();
        page.setPageSize(pageSize == null ? 10 : pageSize);
        page.setCurrentPage(currentPage == null ? 1 : currentPage);
        UserInfo filter = new UserInfo();
        filter.setRegisterTime(MapUtils.getLong(map, "registerTime"));
        PaginateResult<UserInfo> result = new PaginateResult<UserInfo>(page,userInfoService.findUserInfo(filter));
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
