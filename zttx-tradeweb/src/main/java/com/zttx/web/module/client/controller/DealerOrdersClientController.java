/*
 * @(#)DealerOrdersClientController 2014/5/6 16:46
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.List;
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
import com.zttx.sdk.consts.CommonConst;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.module.dealer.entity.DealerOrders;
import com.zttx.web.module.dealer.service.DealerOrdersService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerOrdersClientController</p>
 * <p>Title: 经销商订单项信息</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/5/6 16:46</p>
 * <p>Company: 8637.com</p>
 * @author 李星
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/orders")
public class DealerOrdersClientController extends GenericController
{
    @Autowired
    private DealerOrdersService dealerOrdersService;
    
    /**
     * 根据订单编号获取订单项信息
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李星
     */
    @ResponseBody
    @RequestMapping(value = "/dealerOrders_crm", method = RequestMethod.POST)
    public JsonMessage getDealerOrders_crm(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String orderId = MapUtils.getString(map, "orderId");
        DealerOrders dealerOrders = new DealerOrders();
        dealerOrders.setOrderId(orderId);
        List<DealerOrders> result = dealerOrdersService.getDealerOrders(dealerOrders);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
