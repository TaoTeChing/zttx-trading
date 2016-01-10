/*
 * @(#)OrderPayController.java 2014-12-1 上午8:51:54
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.pay.remoting.model.PayOrderDetails;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.ZttxConst;

import com.zttx.web.module.common.service.OrderPayService;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.security.OnLineUserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>File：OrderPayController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-1 上午8:51:54</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
@Controller
public class OrderPayController extends GenericController
{
    private final static Logger logger = LoggerFactory.getLogger(OrderPayController.class);
    
    @Autowired
    private OrderPayService     orderPayService;

    /**
     * 支付订单
     * @param orderIdArr
     * @param payAmountArr
     * @param orderType
     * @param request
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/dealer/orderPay/pay")
    public String dealerPay(String[] orderIdArr, BigDecimal[] payAmountArr, Short orderType, HttpServletRequest request, Model model) throws BusinessException
    {
        logger.info("支付参数： " + request.getQueryString());
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<PayOrderDetails> payOrderDetailsList = orderPayService.synPay(dealerId, orderIdArr, payAmountArr, orderType);
        return toPost(model, payOrderDetailsList);
    }
    
    private String toPost(Model model, List<PayOrderDetails> payOrderDetailsList)
    {
        StringBuffer payIdsBuffer = new StringBuffer();
        for (PayOrderDetails payOrderDetails : payOrderDetailsList)
        {
            if (payIdsBuffer.length() > 0)
            {
                payIdsBuffer.append(CommonConstant.OrderPay.SPLIT_PAY_ID);
            }
            payIdsBuffer.append(payOrderDetails.getId());
        }
        // 跳转地址
        String action = ZttxConst.PAYAPI_WEBURL + "/user/pay";
        model.addAttribute("action", action);
        // 跳转参数
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("orders", payIdsBuffer.toString());
        model.addAttribute("dataMap", dataMap);
        // 暂时改为get方式
        model.addAttribute("method", "get");
        return "common/post";
    }
}
