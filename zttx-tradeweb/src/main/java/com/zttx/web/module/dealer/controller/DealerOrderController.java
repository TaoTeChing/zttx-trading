/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.entity.ProductSkuPrice;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandContactService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.model.DealerOrderActionModel;
import com.zttx.web.module.dealer.model.OrderModel;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.CrmClient;
import com.zttx.web.utils.ListUtils;
import com.zttx.web.utils.OrderUtils;

/**
 * 经销商订单信息 控制器
 * <p>File：DealerOrderController.java </p>
 * <p>Title: DealerOrderController </p>
 * <p>Description:DealerOrderController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerOrder")
public class DealerOrderController extends GenericController
{
    private final static Logger         logger = LoggerFactory.getLogger(DealerOrderController.class);
    
    @Autowired(required = true)
    private DealerOrderService          dealerOrderService;
    
    @Autowired
    private DealerJoinService           dealerJoinService;
    
    @Autowired
    private DataDictValueService        dataDictValueService;
    
    @Autowired
    private DealerOrdersService         dealerOrdersService;
    
    @Autowired
    private DealerOrderActionService    dealerOrderActionService;
    
    @Autowired
    private BrandInfoService            brandInfoService;
    
    @Autowired
    private BrandContactService         brandContactService;
    
    @Autowired
    private DealerOrderPayService       dealerOrderPayService;
    
    @Autowired
    private DealerShoperService         dealerShoperService;
    
    @Autowired
    private ProductSkuInfoDubboConsumer productSkuInfoDubboConsumer;
    
    @Autowired
    private UserInfoService             userInfoService;
    
    @Autowired
    private DealerInfoService           dealerInfoService;
    
    @Autowired
    private OrderPayService             orderPayService;
    
    @Autowired
    private TelCodeService              telCodeService;
    
    @Autowired
    private SmsTemplateService          smsTemplateService;
    
    @Autowired
    private SmsSendService              smsSendService;
    
    @Autowired
    private CrmClient                   crmClient;
    
    /* ========================================= 订单付款 end ================================================ */
    /* ========================================= 经销商 订单 [@author易永耀] begin================================================ */
    private static void preProcessFilter(DealerOrder filter)
    {
        // 日期先后处理
        if (filter.getStartTime() != null && filter.getEndTime() != null && filter.getStartTime().after(filter.getEndTime()))
        {
            Date tmp = filter.getStartTime();
            filter.setStartTime(filter.getEndTime());
            filter.setEndTime(tmp);
        }
        if (null != filter.getOrderId() && !StringUtils.isNumeric(filter.getOrderId().toString()))
        {
            filter.setOrderId(null);
        }
    }
    
    /* ========================================= 结算页面生成订单 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/addOrder")
    public JsonMessage addDealerOrder(String addressId, String[] dealerShoperIds, String remarks, String freight)
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            Map<String, String> remarksMap = str2Map(remarks);
            Map<String, String> freightMap = str2Map(freight);
            validateParam(dealerId, addressId, dealerShoperIds, remarksMap, freightMap);
            List<DealerOrder> orders = dealerOrderService.addDealerOrder(dealerId, addressId, dealerShoperIds, remarksMap, freightMap);
            // 新支付系统下单
            String[] orderIdArr = OrderUtils.getOrderIdArr(orders);
            if (orderIdArr == null) { throw new BusinessException(CommonConst.FAIL.code, "生成订单失败"); }
            crmClient.sendRTX(CrmClient.SEND_RTX_TYPE_ORDER, dealerId);
            return this.getJsonMessage(CommonConst.SUCCESS, orderIdArr);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 无购物车，直接生成订单
     * @param addressId
     * @param remarks
     * @param freight
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/addOrderNoShoper")
    public JsonMessage addOrderNoShoper(String productId, Short productType, String addressId, String remarks, String freight,
            @RequestParam(value = "skuIds") List<String> skuIds, @RequestParam(value = "purchaseNum") List<Integer> purchaseNums)
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            Map<String, String> remarksMap = str2Map(remarks);
            Map<String, String> freightMap = str2Map(freight);
            validateParam(dealerId, addressId, remarksMap, freightMap);
            DealerShoper dealerShoper = dealerShoperService.productToBalance(productId, productType, skuIds, purchaseNums, dealerId);// 抽象生成购物车
            String orderId = dealerOrderService.addDealerOrder(dealerId, addressId, dealerShoper, remarksMap, freightMap);
            crmClient.sendRTX(CrmClient.SEND_RTX_TYPE_ORDER, dealerId);
            return this.getJsonMessage(CommonConst.SUCCESS, new String[]{orderId});
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 校验参数
     * @param dealerId
     * @param addressId
     * @param remarksMap
     * @param freightMap
     * @throws BusinessException
     */
    private void validateParam(String dealerId, String addressId, Map<String, String> remarksMap, Map<String, String> freightMap) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.USER_NOT_LOGIN); }
        if (StringUtils.isBlank(addressId)) { throw new BusinessException(CommonConst.ADDRESS_NOT_EXIST); }
        if (null == remarksMap || remarksMap.isEmpty()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        if (null == freightMap || freightMap.isEmpty()) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        for (String remark : remarksMap.values())
        {
            if (remark != null && remark.length() > 500) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR.code, "留言长度不能超过500字"); }
        }
        // 运费检验规则不明
    }
    
    /**
     * 校验参数
     * @param dealerId
     * @param addressId
     * @param dealerShoperIds
     * @param remarksMap
     * @param freightMap
     */
    private void validateParam(String dealerId, String addressId, String[] dealerShoperIds, Map<String, String> remarksMap, Map<String, String> freightMap)
            throws BusinessException
    {
        validateParam(dealerId, addressId, remarksMap, freightMap);
        if (null == dealerShoperIds || dealerShoperIds.length == 0) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
    }
    
    private Map<String, String> str2Map(String str)
    {
        return arr2Map(StringUtils.split(str, "|", 2));
    }
    
    private Map<String, String> arr2Map(String[] splitArr)
    {
        Map<String, String> result = Maps.newHashMap();
        if (null != splitArr)
        {
            for (String str : splitArr)
            {
                String[] _val = StringUtils.split(str, "\\$", 2);
                if (_val != null && _val.length > 1) result.put(_val[0].replace("-", ""), _val[1].replace("-", ""));
            }
        }
        return result;
    }
    
    /* ========================================= 结算页面生成订单 end ================================================ */
    /* ========================================= 支付订单页面 [@author易永耀] begin================================================ */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/payment")
    public String payment(String[] orderIds, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<DealerOrder> cashOrders = new ArrayList<DealerOrder>(); // 现款订单
        List<DealerOrder> creditOrders = new ArrayList<DealerOrder>(); // 授信订单
        for (String orderId : orderIds)
        {
            DealerOrder dealerOrder = dealerOrderService.getDealerOrder(orderId, dealerId);
            if (DealerConstant.DealerOrder.ORDER_TYPE_POINT != dealerOrder.getDealerOrderType())// 排除返点类型的订单
            {
                if (dealerOrder.getPayState() == DealerConstant.DealerOrder.PAY_STATE_ALL && dealerOrder.getFrePayState() == DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED) { throw new BusinessException(
                        CommonConst.ORDER_HAS_PAY); }
                if (dealerOrder.getDealerOrderType().equals(DealerConstant.DealerOrder.ORDER_TYPE_CASH))
                {
                    cashOrders.add(dealerOrder);
                }
                if (dealerOrder.getDealerOrderType().equals(DealerConstant.DealerOrder.ORDER_TYPE_CREDIT))
                {
                    Map<String, Object> creditMap = dealerOrderService.selectCreditCurrent(dealerOrder);
                    dealerOrder.setCreditCurrent((BigDecimal) creditMap.get("creditCurrent"));
                    dealerOrder.setIsPaid(MapUtils.getBoolean(creditMap, "isPaid"));
                    dealerOrder.setPayCash((BigDecimal) creditMap.get("payCash"));
                    creditOrders.add(dealerOrder);
                }
            }
        }
        model.addAttribute("normalOrders", cashOrders);
        model.addAttribute("distriOrders", creditOrders);
        return ListUtils.isNotEmpty(cashOrders) || ListUtils.isNotEmpty(creditOrders) ? "dealer/order_payment" : "redirect:/dealer/dealerOrder/order";
        // return "dealer/order_payment";
    }
    
    /* ========================================= 支付订单 end ================================================ */
    /* ========================================= 订单付款 [@author易永耀] begin================================================ */
    // 授信订单付款，授信额度检测
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/payment/creditValid")
    public JsonMessage validCreditMoney(String[] distriOrderIds, String[] creditMoney) throws BusinessException
    {
        if (null != distriOrderIds && null != creditMoney && distriOrderIds.length == creditMoney.length)
        {
            for (int i = 0; i < distriOrderIds.length; i++)
            {
                String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
                DealerOrder dealerOrder = dealerOrderService.getDealerOrder(distriOrderIds[i], dealerId);
                if (dealerOrder.getPayState() == DealerConstant.DealerOrder.PAY_STATE_ALL && dealerOrder.getFrePayState() == DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED) { throw new BusinessException(
                        CommonConst.ORDER_HAS_PAY); }
                DealerJoin dealerJoin = dealerJoinService.selectDealerJoinByDealerIdAndBrandsId(dealerId, dealerOrder.getBrandsId());
                if (new BigDecimal(creditMoney[i]).compareTo(dealerJoin.getCreditAmount().subtract(dealerJoin.getCreditCurrent())) != 0) { return this
                        .getJsonMessage(CommonConst.CHANGE_CREDIT_CURRENT); }
            }
            return this.getJsonMessage(CommonConst.SUCCESS);
        }
        return this.getJsonMessage(CommonConst.FAIL, "无授信订单，无法校验");
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/payment/confirm")
    public String paymentConfirm(String[] normalOrderIds, String[] distriOrderIds) throws BusinessException
    {
        ArrayList<String> orderIdArr = new ArrayList<String>();
        ArrayList<String> payAmountArr = new ArrayList<String>();
        // 普通订单
        if (normalOrderIds != null)
        {
            for (int i = 0; i < normalOrderIds.length; i++)
            {
                orderIdArr.add(normalOrderIds[i]);
                payAmountArr.add("0");
            }
        }
        // 铺货订单
        if (distriOrderIds != null)
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            for (int i = 0; i < distriOrderIds.length; i++)
            {
                DealerOrder dealerOrder = dealerOrderService.getDealerOrder(distriOrderIds[i], dealerId);
                Map<String, Object> creditMap = dealerOrderService.selectCreditCurrent(dealerOrder);
                if (((BigDecimal) creditMap.get("payCash")).compareTo(BigDecimal.ZERO) == 0) // 授信订单支付金额为0（全额授信订单无需付款） 包邮，或是到付授信订单
                {
                    this.updateOrder(distriOrderIds[i]);
                }
                else
                {
                    orderIdArr.add(distriOrderIds[i]);
                    payAmountArr.add(creditMap.get("payCash").toString());
                }
            }
        }
        // 都是授信订单无运费全额支付
        if (orderIdArr.size() == 0) { return "redirect:/dealer/dealerOrder/order"; }
        return "redirect:/dealer/orderPay/pay?orderIdArr=" + org.apache.commons.lang3.StringUtils.join(orderIdArr.toArray(new String[0]), ",") + "&payAmountArr="
                + org.apache.commons.lang3.StringUtils.join(payAmountArr.toArray(new String[0]), ",");
    }
    
    private void updateOrder(String distriOrderId) throws BusinessException
    {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(distriOrderId))
        {
            DealerOrder dealerOrder = dealerOrderService.findModelById(distriOrderId);
            DealerJoin dealerJoin = dealerJoinService.selectDealerJoinByDealerIdAndBrandsId(dealerOrder.getDealerId(), dealerOrder.getBrandsId());
            if (dealerJoin.getCreditAmount().subtract(dealerJoin.getCreditCurrent()).compareTo(dealerOrder.getProductPrice()) != -1) // 包邮，或是到付授信订单
            {
                dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_ALL);
                dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER); // 状态修改为待发货
                dealerOrder.setFrePayState(DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED); // 运费为已付
                dealerOrder.setProductPriceWhenPay(dealerOrder.getProductPrice()); // 设置付款时的总货款
                dealerOrderService.updateByPrimaryKey(dealerOrder);
                // 更新已用授信金额
                dealerJoin.setCreditCurrent(dealerJoin.getCreditCurrent().add(dealerOrder.getProductPrice()));
                dealerJoinService.updateByPrimaryKey(dealerJoin);
                DealerOrderAction mas = new DealerOrderAction();
                mas.setActCode("orderpay");
                mas.setActContent("支付全额授信货款(无运费)：" + dealerOrder.getProductPrice() + "元");
                mas.setActName("付款(全额授信货款)");
                mas.setCreateTime(CalendarUtils.getCurrentLong());
                mas.setOrderId(dealerOrder.getRefrenceId());
                mas.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                mas.setUserId(dealerOrder.getDealerId());
                mas.setUserName(dealerOrder.getDealerName());
                dealerOrderActionService.insert(mas);
                // 通知品牌商发货短信提醒 您有一笔来自**终端商的订单****，请及时发货。如需查看详情请登录【8637.com】";
                String smsContent = "您有一笔来自" + dealerOrder.getDealerName() + "终端商的订单" + dealerOrder.getOrderId() + "，请及时发货。如需查看详情请登录【8637. com】";
                smsSendService.sendSmsToUser(dealerOrder.getBrandId(), smsContent);
            }
            else
            {
                throw new BusinessException(CommonConst.FAIL.getCode(), "授信额度不足，无法付款");
            }
        }
    }
    
    /**
     * 经销商订单：现款订单，授信订单
     * @param pagination
     * @param dealerOrder
     * @param model
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/order")
    public String order(Pagination pagination, DealerOrder dealerOrder, Model model) throws BusinessException
    {
        preProcessFilter(dealerOrder);
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerOrder.setDealerId(dealerId);
        if (null == dealerOrder.getOrderMuiltStatus())
        {
            dealerOrder.setOrderMuiltStatus(0);
        }
        List<Map<String, Object>> joinList = dealerJoinService.findByDealerId(dealerId, DealerConstant.DealerJoin.COOPERATED);
        DataDictValue ddv = dataDictValueService.findDictValue("complaintJoinDay", "complaintJoinDay");
        PaginateResult<Map<String, Object>> pageResult = dealerOrderService.selectDealerOrderPage(dealerOrder, pagination);
        Map<String, Object> dealerOrderType = dealerOrderService.selectDealerOrderType(dealerId);
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("dictValue", null == ddv ? "" : ddv.getDictValue());
        model.addAttribute("orderStatus", dataDictValueService.findByDictCode(DataDictConstant.ORDER_STATUS));
        model.addAttribute("orderType", dataDictValueService.findByDictCode(DataDictConstant.ORDER_TYPE)); // 订单状态分类
        model.addAttribute("dealerOrder", dealerOrder);
        model.addAttribute("joinList", joinList); // 品牌分类
        model.addAttribute("dealerOrderType", dealerOrderType);
        return "dealer/order_index";
    }
    
    /**
     * 订单详情
     * @param orderId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/orderDetail/{orderId}")
    public String orderDetail(@PathVariable String orderId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerOrder dealerOrder = dealerOrderService.getDealerOrder(orderId, dealerId);
        if (dealerOrder == null) { throw new BusinessException("订单不存在!"); }
        List<DealerOrders> dealerOrdersList = null;
        BrandInfo brandInfo = null;
        BrandContact brandContact = null;
        List<DealerOrderAction> dealerOrderAction = null;
        if (null != dealerOrder.getRefrenceId() && null != dealerOrder.getDealerId() && null != dealerOrder.getOrderId())
        {
            dealerOrdersList = dealerOrdersService.selectDealerOrders(dealerOrder.getRefrenceId(), dealerOrder.getDealerId());
            dealerOrderAction = dealerOrderActionService.findByOrderId(dealerOrder.getRefrenceId());
            brandInfo = brandInfoService.selectByPrimaryKey(dealerOrder.getBrandId());
            brandContact = brandContactService.findByBrandId(dealerOrder.getBrandId());
        }
        List<OrderModel> orderModels = dealerOrdersService.convertDealerOrdersToOrderModel(dealerOrdersList);
        model.addAttribute("orderModels", orderModels);
        model.addAttribute("dealerOrder", dealerOrder);
        model.addAttribute("brandContact", brandContact);
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("orderType", dealerOrder.getDealerOrderType());
        // 增加支付记录
        List<DealerOrderPay> orderPayList = Lists.newArrayList();
        if (null != dealerOrder.getDealerOrderType() && DealerConstant.DealerOrder.ORDER_TYPE_CREDIT == dealerOrder.getDealerOrderType().intValue())
        {
            orderPayList = dealerOrderPayService.selectDealerOrderPayList(orderId);
        }
        model.addAttribute("orderPayList", orderPayList);
        model.addAttribute("dealerOrderAction", dealerOrderAction);
        return "dealer/order_details";
    }
    
    /* ========================================= 经销商 订单 end ================================================ */
    /* ========================================= 复制订单 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/copyOrder/{orderId}", method = RequestMethod.POST)
    public JsonMessage copyOrder(@PathVariable String orderId)
    {
        try
        {
            // 2015/11/25 复制订单 即 重新加车
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            DealerOrder _dealerOrder = dealerOrderService.getDealerOrder(orderId, dealerId);
            Map<String, Object> validMap = dealerOrderService.isValid(_dealerOrder);
            if (_dealerOrder == null || !MapUtils.getBoolean(validMap, "valid", false)) { throw new BusinessException("订单失效!"); } // 校验订单是否有效：品牌是否有效，合作关系是否存在
            _dealerOrder.setDealerOrderType(MapUtils.getShort(validMap, "orderType"));
            _dealerOrder.setDiscount(new BigDecimal(MapUtils.getString(validMap, "disCount")));
            Map<String, DealerShoper> mapModel = this.transDealerOrderToDealerShoper(_dealerOrder);
            if (mapModel.size() == 0) { throw new BusinessException(DealerConst.ORDER_DATA_ERROR); } // 订单存在,订单项不存在,复制订单时,购物车无数据
            dealerShoperService.saveCountShopersToShoper(dealerId, mapModel);
            return this.getJsonMessage(CommonConst.SUCCESS.code, "成功复制订单产品到进货单!");
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 将订单转成购物车
     * @param _dealerOrder 购物车
     * @return Map key : productId, value: DealerShoper
     * @throws BusinessException
     */
    protected Map<String, DealerShoper> transDealerOrderToDealerShoper(DealerOrder _dealerOrder) throws BusinessException
    {
        List<DealerOrders> dealerOrderLists = dealerOrdersService.selectDealerOrders(_dealerOrder.getRefrenceId(), _dealerOrder.getDealerId());
        Map<String, DealerShoper> mapModel = Maps.newHashMap();
        for (DealerOrders dealerOrders : dealerOrderLists)
        {
            String productId = dealerOrders.getProductId();
            if (mapModel.get(productId) == null)
            {
                DealerShoper dealerShoper = new DealerShoper();
                dealerShoper.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                dealerShoper.setDealerId(dealerOrders.getDealerId());
                dealerShoper.setBrandId(dealerOrders.getBrandId());
                dealerShoper.setBrandsId(dealerOrders.getBrandsId());
                dealerShoper.setProductId(dealerOrders.getProductId());
                dealerShoper.setCreateTime(CalendarUtils.getCurrentLong());
                dealerShoper.setUpdateTime(dealerShoper.getCreateTime());
                dealerShoper.setDiscount(_dealerOrder.getDiscount());
                dealerShoper.setSourceId("0");
                dealerShoper.setDelFlag(false);
                dealerShoper
                        .setProductType(_dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_POINT ? DealerConstant.DealerShoper.PRODUCTTYPE_POINT
                                : (_dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT ? DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT
                                        : DealerConstant.DealerShoper.PRODUCTTYPE_CASH));
                DealerShopers dealerShopers = this.setDealerShopers(dealerShoper, dealerOrders);
                dealerShoper.getDealerShopersList().add(dealerShopers);
                mapModel.put(productId, dealerShoper);
            }
            else
            {
                DealerShoper dealerShoper = mapModel.get(productId);
                DealerShopers dealerShopers = this.setDealerShopers(dealerShoper, dealerOrders);
                dealerShoper.getDealerShopersList().add(dealerShopers);
            }
        }
        // this.transNotInOrdersSkuToShopers(mapModel); // 复制订单时将订单中不存在的sku但在产品的sku中存在的加入到购物车中
        return mapModel;
    }
    
    protected DealerShopers setDealerShopers(DealerShoper dealerShoper, DealerOrders dealerOrders) throws BusinessException
    {
        ProductSku productSku = productSkuInfoDubboConsumer.findProductSku(dealerOrders.getProductSkuId());
        if (productSku == null) { throw new BusinessException(ProductErrorConst.PRODUCT_SKU_NOT_NULL); }
        DealerShopers dealerShopers = new DealerShopers();
        dealerShopers.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        dealerShopers.setProductId(dealerShoper.getProductId());
        dealerShopers.setShoperId(dealerShoper.getRefrenceId());
        dealerShopers.setProductSkuId(dealerOrders.getProductSkuId());
        // 库存量大于订单量 复制订单为订单的购买数量 否则为最大库存数
        dealerShopers.setPurchaseNum(productSku.getQuantity().compareTo(dealerOrders.getQuantity()) > -1 ? dealerOrders.getQuantity() : productSku.getQuantity());
        dealerShopers.setCreateTime(CalendarUtils.getCurrentLong());
        dealerShopers.setProductType(dealerShoper.getProductType());
        dealerShopers.setProductSkuPrice(dealerShoper.getProductType() == DealerConstant.DealerShoper.PRODUCTTYPE_POINT ? productSku.getProductSkuPrice().getPointPrice()
                : (dealerShoper.getProductType() == DealerConstant.DealerShoper.PRODUCTTYPE_CREDIT ? productSku.getProductSkuPrice().getCreditPrice() : productSku
                        .getProductSkuPrice().getDirectPrice()));
        dealerShopers.setDelFlag(false);
        return dealerShopers;
    }
    
    private void transNotInOrdersSkuToShopers(Map<String, DealerShoper> mapModel) throws BusinessException
    {
        for (Map.Entry<String, DealerShoper> entry : mapModel.entrySet())
        {
            String productId = entry.getKey();
            DealerShoper dealerShoper = entry.getValue();
            List<String> productSkuIdList = new ArrayList<>();
            for (DealerShopers dealerShopers : dealerShoper.getDealerShopersList())
            {
                productSkuIdList.add(dealerShopers.getProductSkuId());
            }
            // 获取已经含有的dealerShoper.getDealerShopersList() 的sku外的所有sku
            List<ProductSkuPrice> productSkuPriceList = productSkuInfoDubboConsumer.findSkuPriceByProductId(productId);
            if (!productSkuPriceList.isEmpty())
            {
                for (ProductSkuPrice SkuPrice : productSkuPriceList)
                {
                    if (productSkuIdList.contains(SkuPrice.getProductSkuId())) // 将已经存在在进货单的sku 排除
                    {
                        continue;
                    }
                    DealerShopers dealerShopers = new DealerShopers();
                    dealerShopers.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                    dealerShopers.setProductId(dealerShoper.getProductId());
                    dealerShopers.setShoperId(dealerShoper.getRefrenceId());
                    dealerShopers.setProductSkuId(SkuPrice.getProductSkuId());
                    dealerShopers.setProductType(dealerShoper.getProductType());
                    dealerShopers.setProductSkuPrice(dealerShoper.getProductType() == 0 ? SkuPrice.getDirectPrice() : SkuPrice.getCreditPrice());
                    dealerShopers.setPurchaseNum(0);
                    dealerShopers.setCreateTime(dealerShoper.getCreateTime());
                    dealerShopers.setUpdateTime(dealerShoper.getUpdateTime());
                    dealerShopers.setDelFlag(false);
                    dealerShoper.getDealerShopersList().add(dealerShopers);
                }
            }
        }
    }
    
    /* ========================================= 复制订单 end ================================================ */
    /**
     * 关闭订单
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/close")
    public JsonMessage closeOrder(HttpServletRequest request, DealerOrderActionModel dealerOrderAction) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerOrderAction.setUserId(dealerId);
        dealerOrderActionService.saveCloseOrder(dealerOrderAction, "dealer");
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 支付订单页面
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/payment/{refrenceId}")
    public String paymentOrder(@PathVariable String refrenceId, HttpServletRequest request, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerOrder dealerOrder = dealerOrderService.getDealerOrder(refrenceId, dealerId);
        if (null == dealerOrder) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR.getCode(), "订单不存在"); }
        // 货款全付且运费未付
        if (dealerOrder.getPayState() == DealerConstant.DealerOrder.PAY_STATE_ALL && dealerOrder.getFrePayState() != DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED) { return "redirect:/dealer/orderPay/pay?orderIdArr="
                + refrenceId; }
        // orderPayService.executeClosePay(refrenceId);
        // 普通订单 付货款
        if (dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_CASH) { return "redirect:/dealer/orderPay/pay?orderIdArr=" + refrenceId; }
        // 铺货订单 未付过款的
        if (dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT) { return "redirect:/dealer/dealerOrder/payment?orderIds=" + refrenceId; }
        return "redirect:/dealer/orderPay/pay?orderIdArr=" + refrenceId;
    }
    
    /**
     * 订单确认收货
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/receive/confirm/{refrenceId}")
    public JsonMessage confirmReceive(@PathVariable String refrenceId, String telcode, String payword, HttpServletRequest request) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        String telphone = dealerUserm.getUserMobile();
        // bug #4990 确认收货验证码设为关闭，确认收货时报错：验证码错误 [修改人：章旭楠]
        // if (ExceptionConst.SUCCESS !=
        // telCodeService.verifyAndUpdate(telphone, "001", telcode)) { throw new
        // BusinessException(CommonConst.VERIFY_CODE_FAILURE); }
        DealerInfo dealerInfo = dealerInfoService.findById(dealerId);
        if (null != dealerInfo && dealerInfo.getRcvSmsVerify()// 判断验证码设置 是否开启
                && ExceptionConst.SUCCESS != telCodeService.verifyAndUpdate(telphone, "001", telcode)) { throw new BusinessException(CommonConst.VERIFY_CODE_FAILURE); }
        // bug #4990 END
        orderPayService.executeValidPayPwd(dealerId, payword);
        this.dealerOrderService.confirmReceive(refrenceId, dealerId, payword);
        // 确认收货后发送手机短信提醒品牌商
        DealerOrder dealerOrder = dealerOrderService.getDealerOrder(refrenceId, dealerId);
        SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_DEALER_CONFIRM_RECEIVE);
        if (smsTemplate != null && org.apache.commons.lang3.StringUtils.isNotEmpty(smsTemplate.getContent()))
        {
            String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("").format(new Date()), dealerOrder.getOrderId(),
                    OrderUtils.getGoodsPaidAmount(dealerOrder), OrderUtils.getTotalAmount(dealerOrder));
            smsSendService.sendSmsToUser(dealerOrder.getBrandId(), smsContent);
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 铺货订单的确认收货,未使用
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/receive/confirm2/{refrenceId}")
    public JsonMessage confirmReceive2(@PathVariable String refrenceId, String telcode, String payword, HttpServletRequest request) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        DealerInfo dealerInfo = dealerInfoService.findById(dealerId);
        String telphone = dealerUserm.getUserMobile();
        // bug #4990 确认收货验证码设为关闭，确认收货时报错：验证码错误 [修改人：章旭楠]
        // if (dealerInfo.isRcvSmsVerify()
        // && ExceptionConst.SUCCESS != telCodeService.verifyAndUpdate(
        // telphone, "001", telcode))
        // {
        // throw new BusinessException(CommonConst.VERIFY_CODE_FAILURE);
        // }
        if (null != dealerInfo && dealerInfo.getRcvSmsVerify()// 判断验证码设置 是否开启
                && ExceptionConst.SUCCESS != telCodeService.verifyAndUpdate(telphone, "001", telcode)) { throw new BusinessException(CommonConst.VERIFY_CODE_FAILURE); }
        // bug #4990 END
        orderPayService.executeValidPayPwd(dealerId, payword);
        this.dealerOrderService.confirmReceive2(refrenceId, dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 订单收货页面
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/receive/{refrenceId}")
    public String view(HttpServletRequest request, @PathVariable String refrenceId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        DealerOrder _dealerOrder = dealerOrderService.getDealerOrder(refrenceId, dealerId);
        List<DealerOrders> dealerOrdersList = null;
        DealerOrder dealerOrder = null;
        BrandInfo brandInfo = null;
        BrandContact brandContact = null;
        List<DealerOrderAction> dealerOrderAction = null;
        if (null != _dealerOrder.getRefrenceId() && null != _dealerOrder.getDealerId() && null != _dealerOrder.getOrderId())
        {
            dealerOrdersList = dealerOrdersService.selectDealerOrders(_dealerOrder.getRefrenceId(), _dealerOrder.getDealerId());
            dealerOrder = dealerOrderService.getByOrderIdAndDealerId(_dealerOrder.getOrderId(), _dealerOrder.getDealerId());
            dealerOrderAction = dealerOrderActionService.findByOrderId(_dealerOrder.getRefrenceId());
            brandInfo = brandInfoService.selectByPrimaryKey(dealerOrder.getBrandId());
            brandContact = brandContactService.findByBrandId(dealerOrder.getBrandId());
        }
        DealerInfo dealerInfo = dealerInfoService.findById(dealerId);
        List<OrderModel> orderModels = dealerOrdersService.convertDealerOrdersToOrderModel(dealerOrdersList);
        model.addAttribute("dealerInfo", dealerInfo);
        model.addAttribute("orderModels", orderModels);
        model.addAttribute("dealerOrder", dealerOrder);
        model.addAttribute("brandContact", brandContact);
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("dealerUserm", dealerUserm);
        /*
         * model.addAttribute("payword",
         * dealerPaywordService.getDealerPayword(dealerId));
         */
        model.addAttribute("payword", orderPayService.isPaymentPwdExists(dealerId));
        // brandInfo.getComName()
        model.addAttribute("dealerOrderAction", dealerOrderAction);
        return "dealer/order_receive_confirm";
    }
}
