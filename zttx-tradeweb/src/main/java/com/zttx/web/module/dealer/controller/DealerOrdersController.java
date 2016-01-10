/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandContactService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrderAction;
import com.zttx.web.module.dealer.entity.DealerOrders;
import com.zttx.web.module.dealer.service.DealerOrderActionService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.module.dealer.service.DealerOrdersService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商订单项信息 控制器
 * <p>File：DealerOrdersController.java </p>
 * <p>Title: DealerOrdersController </p>
 * <p>Description:DealerOrdersController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/order")
public class DealerOrdersController extends GenericController
{
    @Autowired(required = true)
    private DealerOrdersService      dealerOrdersService;
    
    @Autowired
    private DealerOrderService       dealerOrderService;
    
    @Autowired
    private DealerOrderActionService dealerOrderActionService;
    
    @Autowired
    private BrandInfoService         brandInfoService;
    
    @Autowired
    private BrandContactService      brandContactService;
    
    /**
     * 订单详情打印页
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/print/{refrenceId}")
    public String printDealerOrderDetail(@PathVariable String refrenceId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerOrder _dealerOrder = dealerOrderService.getDealerOrder(refrenceId, dealerId);
        if (_dealerOrder == null) { throw new BusinessException("订单不存在!"); }
        List<DealerOrders> dealerOrders = null;
        DealerOrder dealerOrder = null;
        BrandInfo brandInfo = null;
        BrandContact brandContact = null;
        List<DealerOrderAction> dealerOrderAction = null;
        if (null != _dealerOrder.getRefrenceId() && null != _dealerOrder.getDealerId() && null != _dealerOrder.getOrderId())
        {
            dealerOrders = dealerOrdersService.selectDealerOrders(_dealerOrder.getRefrenceId(), _dealerOrder.getDealerId());
            dealerOrder = dealerOrderService.getByOrderIdAndDealerId(_dealerOrder.getOrderId(), _dealerOrder.getDealerId());
            dealerOrderAction = dealerOrderActionService.selectDealerOrderAction(_dealerOrder.getDealerId(), _dealerOrder.getRefrenceId());
            brandInfo = brandInfoService.selectByPrimaryKey(dealerOrder.getBrandId());
            brandContact = brandContactService.findByBrandId(dealerOrder.getBrandId());
        }
        model.addAttribute("dealerOrders", dealerOrders);
        model.addAttribute("dealerOrder", dealerOrder);
        model.addAttribute("brandContact", brandContact);
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("dealerOrderAction", dealerOrderAction);
        return "dealer/order_details_print";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/attribute.json")
    public JsonMessage getProductAttrbute(@RequestParam String orderId, String productId)
    {
        String dealerId = OnLineUserUtils.getPrincipal().getRefrenceId();
        List<Map<String, Object>> maps = dealerOrdersService.getDealerSkuMap(dealerId, orderId, productId);
        return this.getJsonMessage(CommonConst.SUCCESS, maps);
    }
}
