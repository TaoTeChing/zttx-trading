/*
 * @(#)OrderPayCallbackController.java 2014-11-28 上午11:47:33
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.math.BigDecimal;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.pay.remoting.api.SignatureValidator;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.model.OrderPayCallbackModel;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrderPay;
import com.zttx.web.module.dealer.service.DealerOrderPayService;
import com.zttx.web.module.dealer.service.DealerOrderService;

/**
 * <p>File：OrderPayCallbackController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-28 上午11:47:33</p>
 * <p>Company: 8637.com</p>
 *
 * @author 张昌苗
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.COMMON + "/orderPayCallback")
public class OrderPayCallbackController
{
    private final static Logger     logger = LoggerFactory.getLogger(OrderPayCallbackController.class);
    
    @Autowired
    private SignatureValidator      signatureValidator;
    
    @Autowired
    private OrderPayCallbackService orderPayCallbackService;
    
    @Autowired
    private DealerOrderPayService   dealerOrderPayService;

    @Autowired
    private SmsTemplateService      smsTemplateService;
    
    @Autowired
    private DealerOrderService      dealerOrderService;
    
    @Autowired
    private SmsSendService          smsSendService;
    
    /**
     * 支付系统付款回调
     *
     * @author 张昌苗
     */
    @RequestMapping(value = "view")
    public String view(OrderPayCallbackModel orderPayCallbackModel, HttpServletRequest request, HttpServletResponse response, Model model) throws BusinessException
    {
        validator(request, orderPayCallbackModel);
        dealWith(orderPayCallbackModel);
        List<DealerOrderPay> dealerOrderPayList = getDealerOrderPayList(orderPayCallbackModel);
        BigDecimal[] amounts = orderPayCallbackModel.getAmounts();
        if (ArrayUtils.isNotEmpty(amounts))
        {
            BigDecimal amountTotle = BigDecimal.ZERO;
            for (BigDecimal amount : amounts)
            {
                amountTotle = amountTotle.add(amount);
            }
            orderPayCallbackModel.setAmount(amountTotle);
        }
        // 发送手机短信提醒
        int i = 0;
        for (DealerOrderPay dealerOrderPay : dealerOrderPayList)
        {
            DealerOrder dealerOrder = dealerOrderService.findModelById(dealerOrderPay.getOrderId());
            if (null == dealerOrder) break;
            SmsTemplate smsTemplate = null;
            if (i == 0)
            {// 支付金额一次性发送
             // 发送付款成功短信提醒 " %s 账户%s付款%s元，当前账户余额%s元。如需查看详情请登录【8637.com】";
                smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_DEALER_PLAYD_ORDER);
                if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
                {
                    String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()), dealerOrder.getDealerName(),
                            orderPayCallbackModel.getAmount());
                    smsSendService.sendSmsToUser(dealerOrder.getDealerId(), smsContent);
                }
            }
            //if (DealerConstant.DealerOrder.ORDER_TYPE_CREDIT != dealerOrder.getDealerOrderType().shortValue())
            //{
                // 通知发货短信提醒 "%s %s终端商对%s品牌订单%s已付款%s元，请及时发货。如需查看详情请登录【8637.com】";
                smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_BRAND_NOTICE_SENDGOODS);
                if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
                {
                    String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()), dealerOrder.getDealerName(),
                            dealerOrder.getBrandsName(), dealerOrder.getOrderId(), dealerOrderPay.getPayAmount());
                    smsSendService.sendSmsToUser(dealerOrder.getBrandId(), smsContent);
                }
            //}
            i++;
        }
        model.addAttribute("orderPayCallbackModel", orderPayCallbackModel);
        model.addAttribute("dealerOrderPayList", dealerOrderPayList);
        return "common/callback_orderPay";
    }
    
    /**
     * 支付系统付款回调
     *
     * @author 张昌苗
     */
    @RequestMapping(value = "back")
    @ResponseBody
    public String back(OrderPayCallbackModel orderPayCallbackModel, HttpServletRequest request, HttpServletResponse response) throws BusinessException
    {
        try
        {
            validator(request, orderPayCallbackModel);
            dealWith(orderPayCallbackModel);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return "";
        }
        return "1";
    }
    
    private void dealWith(OrderPayCallbackModel model) throws BusinessException
    {
        if (null != model.getId() && null != model.getState() && null != model.getAmount())
        {
            try
            {
                orderPayCallbackService.executeDealWith(model.getId(), model.getState(), model.getAmount());
            }
            catch (BusinessException e)
            {
                logger.error("支付ID（{}）存在问题：", model.getId(), e);
            }
        }
        else if (null != model.getIds() && null != model.getStates() && null != model.getAmounts() && model.getIds().length == model.getStates().length
                && model.getIds().length == model.getAmounts().length)
        {
            for (int i = 0; i < model.getIds().length; i++)
            {
                if (null == model.getIds()[i] || null == model.getStates()[i]
                        || null == model.getAmounts()[i]) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
            }
            for (int i = 0; i < model.getIds().length; i++)
            {
                try
                {
                    orderPayCallbackService.executeDealWith(model.getIds()[i], model.getStates()[i], model.getAmounts()[i]);
                }
                catch (BusinessException e)
                {
                    logger.error("支付ID（{}）存在问题：", model.getIds()[i], e);
                }
            }
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
    }
    
    private List<DealerOrderPay> getDealerOrderPayList(OrderPayCallbackModel model) throws BusinessException
    {
        List<DealerOrderPay> list = Lists.newArrayList();
        if (null != model.getId())
        {
            DealerOrderPay dealerOrderPay = dealerOrderPayService.findByIdWithOrder(model.getId());
            list.add(dealerOrderPay);
        }
        else if (null != model.getIds())
        {
            for (int i = 0; i < model.getIds().length; i++)
            {
                DealerOrderPay dealerOrderPay = dealerOrderPayService.findByIdWithOrder(model.getIds()[i]);
                list.add(dealerOrderPay);
            }
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        return list;
    }
    
    private void validator(HttpServletRequest request, OrderPayCallbackModel orderPayCallbackModel) throws BusinessException
    {
        try
        {
            String queryStr = request.getQueryString();
            logger.info("支付系统回调参数：" + queryStr);
            signatureValidator.validateQueryString(queryStr);
        }
        catch (SignatureException e)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(), e.getMessage());
        }
    }
}
