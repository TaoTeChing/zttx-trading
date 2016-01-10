/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.mapper.BrandInfoMapper;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.module.brand.service.BrandMessageService;
import com.zttx.web.module.common.entity.UserOperationLog;
import com.zttx.web.module.common.service.OrderPayService;
import com.zttx.web.module.common.service.UserOperationLogService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerOrderAction;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderActionMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.module.dealer.mapper.TradeDetailsMapper;
import com.zttx.web.module.dealer.model.DealerOrderActionModel;
import com.zttx.web.utils.CalendarUtils;

/**
 * 订单操作历史记录 服务实现类
 * <p>File：DealerOrderAction.java </p>
 * <p>Title: DealerOrderAction </p>
 * <p>Description:DealerOrderAction </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerOrderActionServiceImpl extends GenericServiceApiImpl<DealerOrderAction> implements DealerOrderActionService
{
    @Autowired
    private BrandInfoMapper         brandInfoMapper;
    
    @Autowired
    private DealerInfoMapper        dealerInfoMapper;
    
    @Autowired
    private DealerOrderMapper       dealerOrderMapper;
    
    @Autowired
    private BrandMessageService     brandMessageService;
    
    @Autowired
    private DealerMessageService    dealerMessageService;
    
    @Autowired
    private TradeDetailsMapper      tradeDetailsMapper;
    
    @Autowired
    private BrandCountService       brandCountService;
    
    @Autowired
    private DealerCountService      dealerCountService;
    
    @Autowired
    private OrderPayService         orderPayService;
    
    @Autowired
    private HttpServletRequest      request;
    
    @Autowired
    private UserOperationLogService userOperationLogService;

    private DealerOrderActionMapper dealerOrderActionMapper;

    @Autowired(required = true)
    public DealerOrderActionServiceImpl(DealerOrderActionMapper dealerOrderActionMapper)
    {
        super(dealerOrderActionMapper);
        this.dealerOrderActionMapper = dealerOrderActionMapper;
    }
    
    @Override
    public List<DealerOrderAction> findByOrderId(String orderId)
    {
        return dealerOrderActionMapper.findByOrderId(orderId);
    }
    
    @Override
    public void saveCloseOrder(DealerOrderActionModel dealerOrderAction, String operatorRole) throws BusinessException
    {
        /* String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID); */
        if (null == dealerOrderAction) { throw new BusinessException(DealerConst.DEALER_NOTEXIST); }
        DealerOrder dealerOrder = dealerOrderMapper.selectByPrimaryKey(dealerOrderAction.getOrderId());
        if (dealerOrder == null) { throw new BusinessException(DealerConst.ORDER_NOT_EXIST); }
        // 支付订单关闭
        orderPayService.executeCloseOrder(dealerOrderAction.getOrderId());
        /**BrandCRMLog日志**/
        /*
         * String userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.USERID);
         * Integer userType = brandUsermService.getUserType(userId);
         * if (StringUtils.isNotBlank(crm_userId) && null != userType) {
         * // crm免登陆品牌商后台操作-关闭订单
         * if (userType == UserAccountConst.BRAND) {
         * BrandCRMLog brandCRMLog = new BrandCRMLog();
         * brandCRMLog.setOperatorId(crm_userId);
         * brandCRMLog.setBrandesId(dealerOrder.getBrandsId());
         * brandCRMLog.setBrandesName(dealerOrder.getBrandsName());
         * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3(order):交易管理,4:终端商管理)
         * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.CLOSE); // 关闭订单
         * brandCRMLog.setOperationDetails("关闭订单:'订单号:" + dealerOrder.getOrderId() + "';");
         * brandCRMLogService.addCRMLog(request, brandCRMLog);
         * } else { // 经销商
         * DealerCrmLog dealerCrmLog = new DealerCrmLog();
         * dealerCrmLog.setOperatorId(crm_userId);
         * dealerCrmLog.setBrandesId(dealerOrder.getBrandsId());
         * dealerCrmLog.setBrandesName(dealerOrder.getBrandsName());
         * dealerCrmLog.setBeOperationName(dealerOrder.getDealerOrderType() != 2 ? CrmConstant.crmToDealerOptionModel.GENERAL_ORDER
         * : CrmConstant.crmToDealerOptionModel.FACTORY_ORDER);
         * dealerCrmLog.setOperation(CrmConstant.CrmDealerOptionName.CLOSE); // 关闭订单
         * if (dealerOrder.getDealerOrderType() != 2) {
         * dealerCrmLog.setOperationDetails("关闭普通订单:'订单号:" + dealerOrder.getOrderId() + "';");
         * } else {
         * dealerCrmLog.setOperationDetails("关闭工厂店订单:'订单号:" + dealerOrder.getOrderId() + "';");
         * }
         * dealerCrmLogService.addCRMLog(request, dealerCrmLog);
         * }
         * }
         */
        if ("brand".endsWith(operatorRole))
        {
            if (!dealerOrder.getBrandId().equals(dealerOrderAction.getUserId())) { throw new BusinessException(DealerConst.ORDER_NOT_EXIST); }
        }
        else if ("dealer".endsWith(operatorRole))
        {
            if (!dealerOrder.getDealerId().equals(dealerOrderAction.getUserId())) { throw new BusinessException(DealerConst.ORDER_NOT_EXIST); }
        }
        if(null!=dealerOrder.getDealerOrderType()&&dealerOrder.getDealerOrderType()==DealerConstant.DealerOrder.ORDER_TYPE_POINT){
        	if(DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER != dealerOrder.getOrderStatus().shortValue()){
        		throw new BusinessException(DealerConst.ORDER_STATE_ERROR);
        	}
        }else{
        	if (DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY != dealerOrder.getOrderStatus().shortValue()) { throw new BusinessException(DealerConst.ORDER_STATE_ERROR); }
        }
        // 先查询该状态下是否已经存在了
        if (dealerOrderActionMapper.isExist(dealerOrderAction.getUserId(), dealerOrderAction.getOrderId(), DealerConstant.DealerOrderAction.CLOSE))
        {
            throw new BusinessException(DealerConst.ORDER_STATE_EXIST);
        }
        else
        {
            String userName = "";
            if ("brand".endsWith(operatorRole))
            {
                BrandInfo brandInfo = brandInfoMapper.selectByPrimaryKey(dealerOrderAction.getUserId());
                userName = brandInfo.getComName();
            }
            else if ("dealer".endsWith(operatorRole))
            {
                DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerOrderAction.getUserId());
                userName = dealerInfo.getDealerName();
            }
            else if ("system".endsWith(operatorRole))
            {
                userName = "系统";
                dealerOrderAction.setUserId(dealerOrder.getBrandId()); // 字段非空，系统任务 以品牌商的id填充
            }
            // 修改订单更新时间
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
            /**删除拿样日志**/
            /* productSampleLogMapper.updateDelState(dealerOrder.getRefrenceId(), BrandConstant.DEL_STATE_DELETED); */
            dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerOrderAction.setActCode(DealerConstant.DealerOrderAction.CLOSE);
            DealerOrderActionEnum action = DealerOrderActionEnum.values()[dealerOrderAction.getCode()];
            String message = action.getMessage();
            dealerOrderAction.setActContent(new StringBuffer(DealerConstant.DealerOrderAction.CLOSE_TXT).append(message).toString());
            dealerOrderAction.setActName(DealerConstant.DealerOrderAction.CLOSE_NAME);
            dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
            dealerOrderAction.setUserName(userName);
            dealerOrderActionMapper.insert(dealerOrderAction);
            UserOperationLog userOperationLog = new UserOperationLog();
            userOperationLog.setObjectId(dealerOrderAction.getOrderId());
            userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
            userOperationLog.setEvent(dealerOrderAction.getActContent());
            /**用户操作日志**/
            userOperationLogService.addUserOperationLog(request, userOperationLog);
            // 增加修改 TradeDetails的状态
            tradeDetailsMapper.updateTradeDetails(dealerOrderAction.getOrderId(), DealerConstant.TradeDetails.TRADE_CLOSE);
            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITPAYCOUNT);
            brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_BALANCECOUNT);
            dealerCountService.modifyDealerCount(dealerOrder.getDealerId(), countTypeList.toArray(new Integer[]{}));
            if (dealerOrder.getIsAdvancePayment())
            {
                countTypeList.clear();
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREORDERCOUNT);
                brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
            }
            String title = "来自" + userName + "的信息";
            // 向经销商发送站内信
            if ("brand".endsWith(operatorRole))
            {
                brandMessageService.sendDealerMessage(dealerOrder.getBrandId(), dealerOrder.getDealerId(), title, message);
            }
            // 向品牌商发送站内信
            if ("dealer".endsWith(operatorRole))
            {
                dealerMessageService.sendBrandMessage(dealerOrder.getDealerId(), dealerOrder.getBrandId(), title, message);
            }
        }
    }

    @Override
    public List<DealerOrderAction> selectDealerOrderAction(String dealerId, String orderId) {
        if(null!=dealerId &&  null != orderId)
        {
            return dealerOrderActionMapper.selectDealerOrderAction(dealerId,orderId);
        }
        return null;
    }
    
    @Override
    public PaginateResult<DealerOrderAction> getDealerOrderActionList4Boss(DealerOrderAction dealerOrderAction, Pagination page)
    {
        dealerOrderAction.setPage(page);
        List<DealerOrderAction> list = dealerOrderActionMapper.getDealerOrderActionList4Boss(dealerOrderAction);
        PaginateResult<DealerOrderAction> pageResult = new PaginateResult<>(page, list);
        return pageResult;
    }
}
