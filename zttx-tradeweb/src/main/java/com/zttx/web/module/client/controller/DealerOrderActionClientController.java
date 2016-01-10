/*
 * @(#)DealerOrderActionClientController.java 2014 10 29 13:29:18
 * Copyright 2014 李飞欧, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.entity.DealerOrderAction;
import com.zttx.web.module.dealer.service.DealerOrderActionService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerOrderActionClientController.java</p>
 * <p>Title: 经销商订单操作历史记录</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014 10 29 13:29:18</p>
 * <p>Company: 8637.com</p>
 * @author 李星
 * @version 2.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerOrderAction")
public class DealerOrderActionClientController extends GenericController
{
    @Autowired
    private DealerOrderActionService dealerOrderActionService;
    
    /**
     * 同步全部订单操作历史记录
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李星
     */
    @ResponseBody
    @RequestMapping(value = "/allAction_crm", method = RequestMethod.POST)
    public JsonMessage allAction_crm(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination();
        BeanUtils.populate(page, map);
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        BeanUtils.populate(dealerOrderAction, map);
        dealerOrderAction.setCreateTime(MapUtils.getLong(map, "createTime"));
        PaginateResult<DealerOrderAction> result = dealerOrderActionService.getDealerOrderActionList4Boss(dealerOrderAction, page);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 获取订单历史记录用于crm
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李星
     */
    @ResponseBody
    @RequestMapping(value = "/orderAction_crm", method = RequestMethod.POST)
    public JsonMessage orderAction_crm(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        List<DealerOrderAction> result = dealerOrderActionService.findByOrderId(MapUtils.getString(map, "orderId"));
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
