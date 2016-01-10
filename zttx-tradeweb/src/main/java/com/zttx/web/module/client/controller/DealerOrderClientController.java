/*
 * @(#)DealerOrderController 2014/4/18 8:49
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerOrderClientController</p>
 * <p>Title: 经销商订单信息RPC接口类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/18 8:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author 李星
 * @version 2.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/order")
public class DealerOrderClientController extends GenericController
{
    @Autowired
    private DealerOrderService  dealerOrderService;
    

    /**
     * 同步全部订单跟订单项
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李星
     */
    @ResponseBody
    @RequestMapping(value = "/allOrder_crm", method = RequestMethod.POST)
    public JsonMessage allOrder_crm(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination();
        BeanUtils.populate(page, map);
        DealerOrder dealerOrder = new DealerOrder();
        BeanUtils.populate(dealerOrder, map);
        dealerOrder.setUpdateTime(MapUtils.getLong(map, "updateTime"));
        PaginateResult<DealerOrderModel> result = dealerOrderService.getDealerOrderList4Boss(dealerOrder, page);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 获取订单信息
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李星
     */
    @ResponseBody
    @RequestMapping(value = "/dealerOrder_crm", method = RequestMethod.POST)
    public JsonMessage getDealerOrder_crm(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        Integer currentPage = MapUtils.getInteger(map, "currentPage");
        Pagination pagaination = new Pagination();
        pagaination.setPageSize(pageSize == null ? 10 : pageSize);
        pagaination.setCurrentPage(currentPage == null ? 1 : currentPage);
        DealerOrderModel dealerOrder = new DealerOrderModel();
        BeanUtils.populate(dealerOrder, map);
        int type = 2;// 表示crm支撑系统调用
        PaginateResult<DealerOrderModel> result = dealerOrderService.getDealerOrders(dealerOrder, pagaination, type);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 订单统计
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/orderCount_crm", method = RequestMethod.POST)
    public JsonMessage orderCount_crm(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerOrderModel dealerOrder = new DealerOrderModel();
        BeanUtils.populate(dealerOrder, map);
        Map<String, Object> result = dealerOrderService.findOrderCount(dealerOrder);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 修改运费跟优惠价格
     * @param request
     * @param param
     * @return
     * @throws Exception
     * @author 李星
     */
    @ResponseBody
    @RequestMapping(value = "/updateCost_crm", method = RequestMethod.POST)
    public JsonMessage updateCost_crm(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerOrder dealerOrder = new DealerOrder();
        String adjustPrice = MapUtils.getString(map, "adjustPrice");
        String adjustFreight = MapUtils.getString(map, "adjustFreight");
        dealerOrder.setAdjustPrice(
                StringUtils.isBlank(adjustPrice) ? null : (new BigDecimal(adjustPrice).multiply(new BigDecimal("-1"))).setScale(2, BigDecimal.ROUND_HALF_UP));
        dealerOrder.setAdjustFreight(StringUtils.isBlank(adjustFreight) ? null : (new BigDecimal(adjustFreight)).setScale(2, BigDecimal.ROUND_HALF_UP));
        dealerOrder.setRefrenceId(MapUtils.getString(map, "refrenceId"));
        dealerOrderService.updateOrderCost(dealerOrder);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
