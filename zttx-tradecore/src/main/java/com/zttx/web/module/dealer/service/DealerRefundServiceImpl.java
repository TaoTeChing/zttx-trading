/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zttx.pay.remoting.crm.TransferApi;
import com.zttx.sdk.bean.EnumDescribable;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandAddress;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.mapper.BrandAddressMapper;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandPointBalanceService;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.entity.UserOperationLog;
import com.zttx.web.module.common.mapper.DataDictValueMapper;
import com.zttx.web.module.common.model.TransferModel;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.mapper.*;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.LoggerUtils;
import com.zttx.web.utils.NumericUtils;
import com.zttx.web.utils.OrderUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 经销商退款信息 服务实现类
 * <p>File：DealerRefund.java </p>
 * <p>Title: DealerRefund </p>
 * <p>Description:DealerRefund </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerRefundServiceImpl extends GenericServiceApiImpl<DealerRefund> implements DealerRefundService
{
    private Logger                   logger        = LoggerFactory.getLogger(DealerRefundServiceImpl.class);
    
    private static final Integer[]   DEALERCOUNT_COLUMNS = {DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT,
            DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT};
    
    private static final Integer[]   BRANDCOUNT_COLUMNS  = {BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT};
    
    @Autowired
    private OrderPayService          orderPayService;
    
    @Autowired
    private BrandCountService        brandCountService;
    
    @Autowired
    private DealerCountService       dealerCountService;
    
    @Autowired
    private DealerOrderMapper        dealerOrderMapper;
    
    @Autowired
    private BrandPointBalanceService brandPointBalanceService;
    
    @Autowired
    private DealerRefReplyMapper     dealerRefReplyMapper;
    
    @Autowired
    private DealerOrderActionMapper  dealerOrderActionMapper;
    
    @Autowired
    private DataDictValueMapper      dataDictValueMapper;
    
    @Autowired
    private BrandAddressMapper       brandAddressMapper;
    
    @Autowired
    private RegionalService          regionalService;
    
    @Autowired
    private DealerClearingMapper     dealerClearingMapper;
    
    @Autowired
    private DealerOrderService       dealerOrderService;
    
    @Autowired
    private UserInfoService          userInfoService;
    
    @Autowired
    private TransferApi              transferApi;
    
    @Autowired
    private DealerClearingService    dealerClearingService;
    
    @Autowired
    private RefundNumberService      refundNumberService;
    
    @Autowired
    private DealerRefAttachtMapper   dealerRefAttachtMapper;
    
    @Autowired
    private DealerCountMapper        dealerCountMapper;

    @Autowired
    private DealerInfoService        dealerInfoService;

    @Autowired
    private DealerRefAttachtService  dealerRefAttachtService;

    @Autowired
    private BrandInfoService         brandInfoService;

    @Autowired
    private PayApiService            payApiService;

    private DealerRefundMapper       dealerRefundMapper;

    @Autowired(required = true)
    public DealerRefundServiceImpl(DealerRefundMapper dealerRefundMapper)
    {
        super(dealerRefundMapper);
        this.dealerRefundMapper = dealerRefundMapper;
    }
    
    @Override
    public DealerRefund getEntityByOrderNumber(Long orderNumber)
    {
        return dealerRefundMapper.getEntityByOrderNumber(orderNumber);
    }
    
    @Override
    public DealerRefundModel getEntityById(@Param("refrenceId") String refrenceId)
    {
        return dealerRefundMapper.getEntityById(refrenceId);
    }
    
    @Override
    // 同意退款(仅退款/退货退款)
    public void updateAgreeReturn(String refrenceId, String brandId, String pwd) throws BusinessException
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        DealerOrder dealerOrder = dealerOrderMapper.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
        if (null == dealerRefund || !dealerRefund.getBrandId().equals(brandId)) { throw new BusinessException(DealerConst.NOT_REFUND); }
        orderPayService.executeValidPayPwd(brandId, pwd); // 检验支付密码
        Short state = dealerRefund.getRefundState();
        // /已经同意退款、退货退款 没有下一个操作
        dealerRefundMapper.updateNextActTime(refrenceId, null);
        // 同意退款给终端商
        if (state.equals(DealerConstant.DealerRefund.WAIT_HANDLE) && dealerRefund.getNeedRefund() == false)
        {
            AgreeDealerRefund(dealerOrder, dealerRefund, false);
        }
        // 确认收到退货，退款给终端商
        else if (state.equals(DealerConstant.DealerRefund.SHIPED) && dealerRefund.getNeedRefund())
        {
            AgreeDealerReturn(dealerOrder, dealerRefund, false);
        }
        else
        {
            throw new BusinessException(DealerConst.CANNOT_SCOPE);
        }
        // crm免登陆品牌商后台操作--同意退款申请
        /*
         * todo BrandCRMLog
         * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
         * if (StringUtils.isNotBlank(crm_userId))
         * {
         * BrandCRMLog brandCRMLog = new BrandCRMLog();
         * brandCRMLog.setOperatorId(crm_userId);
         * brandCRMLog.setBrandesId(dealerOrder.getBrandsId());
         * brandCRMLog.setBrandesName(dealerOrder.getBrandsName());
         * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
         * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.AGREE); // 同意退款申请
         * brandCRMLog.setOperationDetails("同意'仅退款'申请:'订单单号" + dealerOrder.getOrderId() + "',退款" + dealerRefund.getRefundAmount() + "元';");
         * brandCRMLogService.addCRMLog(request, brandCRMLog);
         * }
         */
    }

    @Override
    public BigDecimal getAllDebtMoney(String brandId, String dealerId) throws BusinessException
    {
        DealerClearing dealerClearing = new DealerClearing();
        dealerClearing.setDealerId(dealerId);
        dealerClearing.setBrandId(brandId);
        /**当期欠付款*/
        Map<String, Object> dealerClearingMap = dealerClearingService.selectDealerClearing(null, dealerClearing);
        Map<String, Object> recordSumResult = MapUtils.getMap(dealerClearingMap, "recordSumResult");
        if (null != recordSumResult.get("allDebtPrice")) { return (BigDecimal) recordSumResult.get("allDebtPrice"); }
        return BigDecimal.ZERO;
    }
    
    // 订单同意退款给终端商
    private void AgreeDealerRefund(DealerOrder dealerOrder, DealerRefundModel dealerRefund, boolean isSystem) throws BusinessException
    {
        /*
         * 修改退款中的退款状态为同意退款 更新经销商，品牌商的针对退款的统计数据
         * 如果申请了客服介入，并且客服的介入状态不是纠纷关闭，则统一修改退款和订单中的客服介入状态为：处理完毕
         * 修改TradeDetails的状态和金额,修改订单的状态outNextTime, 添加退款操作历史记录 给终端商打款
         * 如果需要退款给品牌商，则给品牌商打款
         * 更新订单状态中的退款状态为同意退款 新增订单操作历史记录
         */
        dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_REFUND);
        dealerOrder.setRefundStatus(DealerConstant.DealerRefund.AGREE_REFUND);
        dealerRefund.setNextActTime(null);
        dealerOrder.setOutActTime(null);
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerRefundMapper.updateRefundState(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.AGREE_REFUND);
        updateBrandOrDealerCount(dealerOrder, dealerRefund);
        dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.AGREE_REFUND);
        BigDecimal pointBalance = new BigDecimal(0);
        BigDecimal payment = dealerOrder.getPayment();
        if (DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED == dealerOrder.getFrePayState().shortValue())
        {
            payment = payment.add(dealerOrder.getAdjustFreight());
        }
        BigDecimal refundAmount = dealerRefund.getRefundAmount() != null ? dealerRefund.getRefundAmount() : BigDecimal.ZERO;
        if (payment.compareTo(refundAmount) > 0)
        {
            pointBalance = brandPointBalanceService.getPointBalance(dealerOrder, null);
        }
        // TradeDetail操作
        //2.0版全部退款，部分退款都改为交易完成
        /*if (isAllRefund(dealerOrder, dealerRefund))
        {
            // 如果全部退款，则改为退款关闭
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            // todo 删除拿样日志
            *//* productSampleLogDao.updateDelState(dealerOrder.getRefrenceId(), BrandConstant.DEL_STATE_DELETED); *//*
        }
        else
        {*/
            // 如果部分退款则改为交易完成
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        /*}*/
        // 如果申请了客服介入，只要介入状态不是纠纷关闭，则是介入状态改成纠纷处理完毕
        if (dealerRefund.getSerProStatus() != null && dealerRefund.getSerProStatus() != DealerConstant.DealerOrder.SER_STATUS_COMPLETED)
        {
            // 修改客服介入处理状态
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
        // 退款操作（到支付平台）
        BigDecimal refundAmountReal = dealerRefund.getRefundAmount();
        orderPayService.executeRefund(dealerOrder.getRefrenceId(), refundAmountReal, pointBalance);
        dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT);
        brandCountService.modifyBrandCount(dealerOrder.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // 操作记录
        this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "同意了退款申请"));
        // 新增订单操作记录
        this.insertDealerOrderAction(DealerConstant.DealerOrderAction.AGREEREFUND, DealerConstant.DealerOrderAction.AGREEREFUND_NAME,
                ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "同意了退款申请"), dealerRefund.getOrderId(), dealerRefund.getBrandId(),
                dealerRefund.getBrandName());
    }
    
    // 是否全额退款
    private boolean isAllRefund(DealerOrder dealerOrder, DealerRefund dealerRefund)
    {
        boolean flag = false;
        BigDecimal refundAmount = orderPayService.countRefundAmount(dealerOrder);// 关闭发货,调价等因素造成的退款额度
        refundAmount.add(dealerRefund.getRefundAmount());
        BigDecimal orderAllAmount = orderPayService.getOldProdcutPrice(dealerOrder);
        if (null != dealerOrder.getAdjustFreight() && dealerOrder.getFrePayState().shortValue() == DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED)
        {
            orderAllAmount.add(dealerOrder.getAdjustFreight());
        }
        if (orderAllAmount.compareTo(refundAmount) <= 0) // 全额退款
        {
            flag = true;
        }
        return flag;
    }
    
    /*
     * 更新品牌商，终端商，统计数据
     */
    private void updateBrandOrDealerCount(DealerOrder dealerOrder, DealerRefund dealerRefund) throws BusinessException
    {
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
        brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
        dealerCountService.modifyDealerCount(dealerRefund.getDealerId(), countTypeList.toArray(new Integer[]{}));
        Short orderStatus = dealerOrder.getOrderStatus();
        if (DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER == orderStatus.shortValue())
        {
            countTypeList.clear();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT);
            brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
        }
        else if (DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE == orderStatus.shortValue())
        {
            countTypeList.clear();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_WAITCONFIRECOUNT);
            brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
        }
    }
    
    // 退款操作记录
    private DealerRefReply insertDealerRefReply(DealerRefund dealerRefund, String userId, String userName, String replyContent)
    {
        DealerRefReply dealerRefReply = new DealerRefReply();
        dealerRefReply.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerRefReply.setRefundId(dealerRefund.getRefrenceId());
        dealerRefReply.setUserId(userId);
        dealerRefReply.setUserName(userName);
        dealerRefReply.setReplyContent(replyContent);
        dealerRefReply.setCreateTime(CalendarUtils.getCurrentLong());
        dealerRefReplyMapper.insert(dealerRefReply);
        return dealerRefReply;
    }
    
    /**
     * 新增订单操作记录
     *
     * @param actCode
     * @param actName
     * @param actContent
     * @param orderId
     * @param userId
     * @param userName
     */
    private void insertDealerOrderAction(String actCode, String actName, String actContent, String orderId, String userId, String userName)
    {
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrderAction.setActCode(actCode);
        dealerOrderAction.setActName(actName);
        dealerOrderAction.setActContent(actContent);
        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrderAction.setOrderId(orderId);
        dealerOrderAction.setUserId(userId);
        dealerOrderAction.setUserName(userName);
        dealerOrderActionMapper.insert(dealerOrderAction);
        /*
         * UserOperationLog userOperationLog = new UserOperationLog();
         * userOperationLog.setObjectId(orderId);
         * userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
         * userOperationLog.setEvent(actContent);
         * userOperationLogService.addUserOperationLog(request, userOperationLog);
         */
    }
    
    // 现款订单同意退货并且退款给终端商，
    private void AgreeDealerReturn(DealerOrder dealerOrder, DealerRefundModel dealerRefund, boolean isSystem) throws BusinessException
    {
        /*
         * 操作内容 修改退款和订单的退款状态为同意退款退货
         * 修改TradeDetails的状态和金额和DealerOrder的订单状态和金额outNextTime
         * 如果申请了客服介入，并且客服的介入状态不是纠纷关闭，则统一修改退款和订单中的客服介入状态为：处理完毕
         * 给退款给终端商，如果退给品牌商的钱大于0 给品牌商打款 增加退款操作历史记录 增加订单操作历史记录
         */
        // 需要退货的退款
        dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
        dealerOrder.setRefundStatus(DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
        dealerRefund.setNextActTime(null);
        dealerOrder.setOutActTime(null);
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerRefundMapper.updateRefundState(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
        updateBrandOrDealerCount(dealerOrder, dealerRefund);
        dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
        BigDecimal pointBalance = new BigDecimal(0);
        BigDecimal payment = dealerOrder.getPayment();
        if (DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED == dealerOrder.getFrePayState().shortValue())
        {
            payment = payment.add(dealerOrder.getAdjustFreight());
        }
        BigDecimal refundAmount = dealerRefund.getRefundAmount() != null ? dealerRefund.getRefundAmount() : BigDecimal.ZERO;
        if (payment.compareTo(refundAmount) > 0)
        {
            pointBalance = brandPointBalanceService.getPointBalance(dealerOrder, null);
        }
        //全部退款，部分退款订单状态都改为交易完成
        /*if (isAllRefund(dealerOrder, dealerRefund))
        {
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
        }
        else
        {*/
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        /*}*/
        // 如果申请了客服介入，只要介入状态不是纠纷关闭，则是介入状态改成纠纷处理完毕
        if (dealerRefund.getSerProStatus() != null && dealerRefund.getSerProStatus() != DealerConstant.DealerOrder.SER_STATUS_COMPLETED)
        {
            // 修改客服介入处理状态
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
        // 退款操作（到支付平台）
        BigDecimal refundAmountReal = dealerRefund.getRefundAmount();
        orderPayService.executeRefund(dealerOrder.getRefrenceId(), refundAmountReal, pointBalance);
        dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
        // 操作记录
        this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                ((isSystem == true) ? "操作超时，系统收货并同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "已收货并同意了退款申请"));
        // 新增订单操作记录
        this.insertDealerOrderAction(DealerConstant.DealerOrderAction.AGREEREFUND, DealerConstant.DealerOrderAction.AGREEREFUND_NAME,
                ((isSystem == true) ? "操作超时，系统收货并同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "已收货并同意了退款申请"), dealerRefund.getOrderId(), dealerRefund.getBrandId(),
                dealerRefund.getBrandName());
    }
    
    // 拒绝退款
    @Override
    public void refuseReturn(String refrenceId, String brandId, String remark) throws BusinessException
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        if (null == dealerRefund || !brandId.equals(dealerRefund.getBrandId())) { throw new BusinessException(DealerConst.NOT_REFUND); }
        if (dealerRefund.getRefundState().equals(DealerConstant.DealerRefund.WAIT_HANDLE))
        {
            // refundState
            dealerRefund.setBrandMark(remark);
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);
            // dealerModRefund 修改退款协议的时间
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", dealerRefund.getNeedRefund() == true ? "dealerModReturn"
                    : "dealerModRefund");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            Long nextActTime = CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount);
            dealerRefund.setNextActTime(nextActTime);
            dealerRefundMapper.refuseRefund(refrenceId, DealerConstant.DealerRefund.NOT_REFUND, nextActTime);

            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.NOT_REFUND);
            dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
            // 2014-6-20 上午10:22:21 施建波 拒绝退款退货的同时把客户介入状态修改为ＮＵＬＬ
            // 2014-06-21 14:00 曾宪乐，客服介入状态，不允许被品牌商修改
            // dealerOrderDao.updateRefundStatutsCusJoin(dealerRefund.getOrderId(),
            // DealerConstant.DealerRefund.NOT_REFUND, null);
            String refundReason = "，拒绝理由：" + dealerRefund.getBrandMark();
            this.insertDealerRefReply(dealerRefund, dealerRefund.getBrandId(), dealerRefund.getBrandName(),
                    "【" + dealerRefund.getBrandName() + "】" + (dealerRefund.getNeedRefund() == true ? "拒绝了退货申请" + refundReason : "拒绝了退款申请" + refundReason));
            // 新增订单操作记录
            this.insertDealerOrderAction("refuseReturn", (dealerRefund.getNeedRefund() == true ? "拒绝退货申请" : "拒绝退款申请"), "【" + dealerRefund.getBrandName() + "】"
                    + (dealerRefund.getNeedRefund() == true ? "拒绝了退货申请" + refundReason : "拒绝了退款申请" + refundReason), dealerRefund.getOrderId(), dealerRefund.getBrandId(),
                    dealerRefund.getBrandName());
        }
        else
        {
            throw new BusinessException(DealerConst.CANNOT_SCOPE);
        }
        // crm免登陆品牌商后台操作--拒绝退款申请 (工厂店与普通 订单)
        // todo 日志：BrandCRMLog
        /*
         * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
         * DealerOrder dealerOrder = dealerOrderDao.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
         * if (StringUtils.isNotBlank(crm_userId))
         * {
         * BrandCRMLog brandCRMLog = new BrandCRMLog();
         * brandCRMLog.setOperatorId(crm_userId);
         * if (dealerOrder != null)
         * {
         * brandCRMLog.setBrandesId(dealerOrder.getBrandsId());
         * brandCRMLog.setBrandesName(dealerOrder.getBrandsName());
         * }
         * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
         * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.REFUSE); // 拒绝退款申请
         * if (dealerOrder == null)
         * {
         * brandCRMLog.setOperationDetails("拒绝退款申请:'退款单号" + dealerRefund.getOrderNumber() + "','退款:" + dealerRefund.getRefundAmount() + "元';");
         * }
         * else
         * {
         * brandCRMLog.setOperationDetails("拒绝退款申请:'订单号" + dealerOrder.getOrderId() + "','退款:" + dealerRefund.getRefundAmount() + "元';");
         * }
         * brandCRMLogService.addCRMLog(request, brandCRMLog);
         * }
         */
    }
    
    /**
     * 品牌商操作超时，自动同意退货
     *
     * @param dealerRefundList
     * @param isSystemJob
     * @throws BusinessException
     */
    @Deprecated
    @Override
    public void updateAutoAgreeReturnBoth(List<DealerRefundModel> dealerRefundList, Boolean isSystemJob) throws BusinessException
    {
        DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", "dealerReturnDay");
        int amount = Integer.valueOf(dataDictValue.getDictValue());
        for (DealerRefundModel dealerRefund : dealerRefundList)
        {
            String refrenceId = dealerRefund.getRefrenceId();
            if (null == dealerRefund || !dealerRefund.getRefrenceId().equals(refrenceId)) { throw new BusinessException(DealerConst.NOT_REFUND); }
            Short state = dealerRefund.getRefundState();
            if (state.equals(DealerConstant.DealerRefund.WAIT_HANDLE) && dealerRefund.getNeedRefund())
            {
                // 增加终端商退货处理时间
                Long nextActTime = CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount);
                dealerRefund.setNextActTime(nextActTime);
                String brandRecAddr = dealerRefund.getBrandRecAddr();
                if (StringUtils.isBlank(brandRecAddr)) brandRecAddr = getFullDeaultBrandAddressStr(dealerRefund);
                dealerRefundMapper.updateAgreeReturnBoth1(refrenceId, DealerConstant.DealerRefund.WAIT_SHIP, brandRecAddr, nextActTime,CalendarUtils.getCurrentLong());
                if (!dealerRefund.getFactoryStore())
                {
                    dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.WAIT_SHIP);
                    dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
                }
                // 操作记录
                String brandReAddr = "品牌商收货地址:" + brandRecAddr;
                String replayContent = "";
                if (isSystemJob)
                {
                    replayContent = "【" + dealerRefund.getBrandName() + "】操作超时，系统同意退货申请" + "  " + brandReAddr;
                }
                else
                {
                    replayContent = "【" + dealerRefund.getBrandName() + "】同意了退货申请" + "  " + brandReAddr;
                }
                this.insertDealerRefReply(dealerRefund, dealerRefund.getBrandId(), dealerRefund.getBrandName(), replayContent);
                // 新增订单操作记录
                this.insertDealerOrderAction(DealerConstant.DealerOrderAction.AGREEREFUND, DealerConstant.DealerOrderAction.AGREEREFUND_NAME, replayContent,
                        dealerRefund.getOrderId(), dealerRefund.getBrandId(), dealerRefund.getBrandName());
            }
            else
            {
                throw new BusinessException(DealerConst.CANNOT_SCOPE);
            }
            // crm免登陆品牌商后台操作--同意退款申请(退货退款)
            // todo 日志:BrandCRMLog
            /*
             * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
             * DealerOrder dealerOrder = dealerOrderDao.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
             * if (StringUtils.isNotBlank(crm_userId))
             * {
             * BrandCRMLog brandCRMLog = new BrandCRMLog();
             * brandCRMLog.setOperatorId(crm_userId);
             * brandCRMLog.setBrandesId(dealerOrder.getBrandsId());
             * brandCRMLog.setBrandesName(dealerOrder.getBrandsName());
             * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
             * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.AGREE); // 同意退款申请
             * brandCRMLog.setOperationDetails("同意'退货退款'申请:'普通订单" + dealerRefund.getOrderNumber() + "',退款" + dealerRefund.getRefundAmount() + "元';");
             * brandCRMLogService.addCRMLog(request, brandCRMLog);
             * }
             */
        }
    }
    
    /**
     * 获取品牌商默认地址
     * @param dealerRefund
     * @return
     */
    private String getFullDeaultBrandAddressStr(DealerRefund dealerRefund)
    {
        String brandRecAddr = "品牌商尚未设置收货地址，请尽快与品牌商联系";
        BrandAddress defaultBrandAddress = null;
        if (StringUtils.isNotBlank(dealerRefund.getOrderId()))
        {
            DealerOrder dealerOrder = dealerOrderMapper.selectByPrimaryKey(dealerRefund.getOrderId());
            if (null != dealerOrder)
            {
                // 根据品牌查找收货地址
                defaultBrandAddress = brandAddressMapper.getDefaultAddress(dealerOrder.getBrandsId());
                if (null == defaultBrandAddress)
                {
                    defaultBrandAddress = brandAddressMapper.getFirstAddress(dealerOrder.getBrandsId());
                }
            }
        }
        if (null == defaultBrandAddress)
        {
            if (StringUtils.isNotBlank(dealerRefund.getBrandId()))
            {
                // 根据品牌商查找收货地址
                defaultBrandAddress = brandAddressMapper.getDefaultBrandAddressByBrandId(dealerRefund.getBrandId());
                if (null == defaultBrandAddress) defaultBrandAddress = brandAddressMapper.getFirstBrandAddressByBrandId(dealerRefund.getBrandId());
            }
        }
        if (null != defaultBrandAddress) brandRecAddr = buildAddressStr(defaultBrandAddress);
        return brandRecAddr;
    }
    
    private String buildAddressStr(BrandAddress defaultBrandAddress)
    {
        StringBuilder sb = new StringBuilder(defaultBrandAddress.getUserName());
        sb.append("&nbsp;").append(regionalService.getFullNameByAreaNo(defaultBrandAddress.getAreaNo(), RegionalService.REGIONAL_SPLIT_CODE));
        sb.append("&nbsp;").append(defaultBrandAddress.getStreet());
        if (StringUtils.isNotBlank(defaultBrandAddress.getUserTel()))
        {
            sb.append("&nbsp;").append(defaultBrandAddress.getUserTel());
        }
        if (StringUtils.isNotBlank(defaultBrandAddress.getUserMobile()))
        {
            sb.append("&nbsp;").append(defaultBrandAddress.getUserMobile());
        }
        return sb.toString();
    }
    
    // 拒绝退货
    @Override
    public void refuseRefund(String refrenceId, String brandId, String remark) throws BusinessException
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        if (null == dealerRefund || !brandId.equals(dealerRefund.getBrandId())) { throw new BusinessException(DealerConst.NOT_REFUND); }
        if (dealerRefund.getRefundState().equals(DealerConstant.DealerRefund.WAIT_HANDLE) || dealerRefund.getRefundState().equals(DealerConstant.DealerRefund.SHIPED))
        {
            // refundState
            dealerRefund.setBrandMark(remark);
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", dealerRefund.getNeedRefund() == true ? "dealerModReturn"
                    : "dealerModRefund");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            Long nextActTime = CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount);
            dealerRefund.setNextActTime(nextActTime);
            dealerRefundMapper.refuseRefund(refrenceId, DealerConstant.DealerRefund.NOT_RETURN, nextActTime);
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.NOT_RETURN);
            dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
            String refundReason = "，拒绝理由：" + dealerRefund.getBrandMark();
            this.insertDealerRefReply(dealerRefund, dealerRefund.getBrandId(), dealerRefund.getBrandName(),
                    "【" + dealerRefund.getBrandName() + "】" + (dealerRefund.getNeedRefund() == true ? "拒绝了退货申请" + refundReason : "拒绝了退款申请" + refundReason));
            // 新增订单操作记录
            this.insertDealerOrderAction("refuseRefund", (dealerRefund.getNeedRefund() == true ? "拒绝退货申请" : "拒绝退款申请"), "【" + dealerRefund.getBrandName() + "】"
                    + (dealerRefund.getNeedRefund() == true ? "拒绝了退货申请" + refundReason : "拒绝了退款申请" + refundReason), dealerRefund.getOrderId(), dealerRefund.getBrandId(),
                    dealerRefund.getBrandName());
        }
        else
        {
            throw new BusinessException(DealerConst.CANNOT_SCOPE);
        }
        // crm免登陆品牌商后台操作--拒绝退货申请
        // todo 日志:BrandCRMLog
        /*
         * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
         * DealerOrder dealerOrder = dealerOrderDao.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
         * if (StringUtils.isNotBlank(crm_userId))
         * {
         * BrandCRMLog brandCRMLog = new BrandCRMLog();
         * brandCRMLog.setOperatorId(crm_userId);
         * brandCRMLog.setBrandesId(dealerOrder.getBrandsId());
         * brandCRMLog.setBrandesName(dealerOrder.getBrandsName());
         * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
         * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.REFUSE); // 拒绝退货申请
         * brandCRMLog.setOperationDetails("拒绝退货申请:'订单单号" + dealerRefund.getOrderNumber() + "',退款" + dealerRefund.getRefundAmount() + "元';");
         * brandCRMLogService.addCRMLog(request, brandCRMLog);
         * }
         */
    }
    
    @Override
    public void updateFactoryAgreeReturn(String refrenceId, String brandId, String pwd) throws BusinessException
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        if (null == dealerRefund || !dealerRefund.getBrandId().equals(brandId)) { throw new BusinessException(DealerConst.NOT_REFUND); }
        orderPayService.executeValidPayPwd(brandId, pwd); // 检验支付密码
        Short state = dealerRefund.getRefundState();
        if (null == dealerRefund.getOrderId()) { throw new BusinessException("退款单没有关联到订单"); }
        BigDecimal debtMoneyDeductible = dealerRefund.getRefundAmount(); // 欠收抵扣应付款
        BigDecimal cashMoney = BigDecimal.ZERO; // 需退余额现金
        DealerOrder dealerOrder = dealerOrderMapper.selectByPrimaryKey(dealerRefund.getOrderId());
        BigDecimal debtMoney = orderPayService.countCretditAmountUsed(dealerOrder); // 欠付款
        if (dealerRefund.getRefundAmount().compareTo(debtMoney) == 1)
        {
            // 如果退款金额大于欠收金额
            debtMoneyDeductible = debtMoney;
            cashMoney = dealerRefund.getRefundAmount().subtract(debtMoney);
        }

        // /已经同意退款、退货退款 没有下一个操作
        dealerRefundMapper.updateNextActTime(refrenceId, null);
        // 同意退款给终端商
        if (state.equals(DealerConstant.DealerRefund.WAIT_HANDLE) && dealerRefund.getNeedRefund() == false)
        {
            try {
                agreeDealerRefund(dealerRefund, debtMoneyDeductible, cashMoney, false);
            }catch (Exception e){
                logger.error(e.getLocalizedMessage());
            }

        }
        // 确认收到退货，退款给终端商
        else if (state.equals(DealerConstant.DealerRefund.SHIPED) && dealerRefund.getNeedRefund())
        {
            try {
            agreeDealerReturn(dealerRefund, debtMoneyDeductible, cashMoney, false);
            }catch (Exception e){
                logger.error(e.getLocalizedMessage());
            }
        }
        else
        {
            throw new BusinessException(DealerConst.CANNOT_SCOPE);
        }
        // todo BrandCRMLog crm免登陆品牌商后台操作--同意工厂店退款申请
        /*
         * String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
         * if (StringUtils.isNotBlank(crm_userId))
         * {
         * BrandCRMLog brandCRMLog = new BrandCRMLog();
         * brandCRMLog.setOperatorId(crm_userId);
         * brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.ORDER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
         * brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.AGREE); // 同意退款申请
         * brandCRMLog.setOperationDetails("同意'退款'申请:'退款单号" + dealerRefund.getRefundId() + "',退款" + dealerRefund.getRefundAmount() + "元';");
         * brandCRMLogService.addCRMLog(request, brandCRMLog);
         * }
         */
    }

    @Override
    public void updateTotalAgreeReturn(String refrenceId, String brandId, String pwd) throws BusinessException
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        if (null == dealerRefund || !dealerRefund.getBrandId().equals(brandId)) { throw new BusinessException(DealerConst.NOT_REFUND); }
        DealerOrder dealerOrderFilter = new DealerOrder();
        dealerOrderFilter.setDealerId(dealerRefund.getDealerId());
        dealerOrderFilter.setBrandId(brandId);
        Map<String, Object> detailResult = dealerOrderService.getDealerOrderReportDetailMap(null, dealerOrderFilter);
        BigDecimal debtMoney = (BigDecimal) MapUtils.getObject((Map) detailResult.get("sumResult"), "allDebtPrice", BigDecimal.ZERO);// 总欠付款
         //仅退款不退货
        if (dealerRefund.getRefundState().equals(DealerConstant.DealerRefund.WAIT_HANDLE) && dealerRefund.getNeedRefund() == false)
        {
            agreeDealerRefundNotNeed(debtMoney, dealerRefund, pwd, brandId);
        }else if(dealerRefund.getRefundState().equals(DealerConstant.DealerRefund.SHIPED) && dealerRefund.getNeedRefund()) //退货退款
        {
            agreeDealerRefundNeed(debtMoney, dealerRefund, pwd, brandId);
        }
        else
        {
            throw new BusinessException(DealerConst.CANNOT_SCOPE);
        }
        //dealerRefundMapper.updateNextActTime(refrenceId, null);
        if (dealerRefund.getSerProStatus() != null && dealerRefund.getSerProStatus() != DealerConstant.DealerOrder.SER_STATUS_COMPLETED)
        {
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
        }
        Boolean isSystem = false;
        this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(), ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "同意了退款申请"));
    }

   private void agreeDealerRefundNotNeed(BigDecimal debtMoney, DealerRefundModel dealerRefund ,String pwd,String brandId) throws BusinessException {
       //退款大于欠付款
       if (debtMoney.compareTo(BigDecimal.ZERO) == 1 && dealerRefund.getRefundAmount().compareTo(debtMoney) == 1)
       {
           //直接付款(退款-当期欠付)   ---退款状态为  直接支付与应收抵欠付同时存在
           BigDecimal cashMoney = dealerRefund.getRefundAmount().subtract(debtMoney);
           TransferModel transferModel = new TransferModel();
           transferModel.setToUserId(dealerRefund.getDealerId());
           transferModel.setTitle("总账退款(应收抵欠付+直接支付)");
           transferModel.setAmount(cashMoney);
           transferModel.setPayPwd(pwd);
           payApiService.executeRefund(brandId, transferModel); // 转账后，会修改当期应付款支付状态为已付，同时修改实时用掉的授信额度
           dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_REFUND);
           dealerRefund.setReachAmount(debtMoney);  //应收抵欠付金额
           dealerRefund.setNextActTime(null);
           dealerRefund.setReachStatus(DealerConstant.DealerRefund.PAYMENT_REACH_AND_BALANCE);
           dealerRefundMapper.updateByPrimaryKey(dealerRefund);
       }
       else if (dealerRefund.getRefundAmount().compareTo(debtMoney)!= 1 && debtMoney.compareTo(BigDecimal.ZERO) !=0)   //退款小于等于应付 --退款状态为 应收抵欠付
       {
           //应收抵退款
           dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_REFUND);
           dealerRefund.setNextActTime(null);
           dealerRefund.setReachAmount(dealerRefund.getRefundAmount());
           dealerRefund.setReachStatus(DealerConstant.DealerRefund.PAYMENT_REACH);
           dealerRefundMapper.updateByPrimaryKey(dealerRefund);

       }else // 直接支付
       {
           if(debtMoney.compareTo(BigDecimal.ZERO)!=0){throw new BusinessException(CommonConst.FAIL.getCode(),"欠付款金额不为0，无法直接支付");}
           //直接付款
           BigDecimal cashMoney = dealerRefund.getRefundAmount();
           TransferModel transferModel = new TransferModel();
           transferModel.setToUserId(dealerRefund.getDealerId());
           transferModel.setTitle("总账退款(直接付款)");
           transferModel.setAmount(cashMoney);
           transferModel.setPayPwd(pwd);
           payApiService.executeRefund(brandId, transferModel); // 转账后，会修改当期应付款支付状态为已付，同时修改实时用掉的授信额度
           dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_REFUND);
           dealerRefund.setReachStatus(DealerConstant.DealerRefund.PAYMENT_GENERAL);
           dealerRefund.setNextActTime(null);
           dealerRefundMapper.updateByPrimaryKey(dealerRefund);
       }
   }
    public void agreeDealerRefundNeed(BigDecimal debtMoney, DealerRefundModel dealerRefund ,String pwd,String brandId)throws BusinessException
    {
        //退款大于欠付款
        if (debtMoney.compareTo(BigDecimal.ZERO) == 1 && dealerRefund.getRefundAmount().compareTo(debtMoney) == 1)
        {
            //直接付款(退款-当期欠付)   ---退款状态为  直接支付与应收抵欠付同时存在
            BigDecimal cashMoney = dealerRefund.getRefundAmount().subtract(debtMoney);
            TransferModel transferModel = new TransferModel();
            transferModel.setToUserId(dealerRefund.getDealerId());
            transferModel.setTitle("总账退款(应收抵欠付+直接支付)");
            transferModel.setAmount(cashMoney);
            transferModel.setPayPwd(pwd);
            payApiService.executeRefund(brandId, transferModel); // 转账后，会修改当期应付款支付状态为已付，同时修改实时用掉的授信额度
            dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
            dealerRefund.setNextActTime(null);
            dealerRefund.setReachAmount(debtMoney);  //应收抵欠付金额
            dealerRefund.setReachStatus(DealerConstant.DealerRefund.PAYMENT_REACH_AND_BALANCE);
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);
        }
        else if (dealerRefund.getRefundAmount().compareTo(debtMoney)!= 1 && debtMoney.compareTo(BigDecimal.ZERO) !=0)   //应收与退款相等 --退款状态为 应收抵欠付
        {
            //应收抵退款
            dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
            dealerRefund.setReachAmount(dealerRefund.getRefundAmount());
            dealerRefund.setReachStatus(DealerConstant.DealerRefund.PAYMENT_REACH);
            dealerRefund.setNextActTime(null);
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);

        }else // 直接支付
        {
            if(debtMoney.compareTo(BigDecimal.ZERO)!=0){throw new BusinessException(CommonConst.FAIL.getCode(),"欠付款金额不为0，无法直接支付");}
            //直接付款
            BigDecimal cashMoney = dealerRefund.getRefundAmount();
            TransferModel transferModel = new TransferModel();
            transferModel.setToUserId(dealerRefund.getDealerId());
            transferModel.setTitle("总账退款(直接付款)");
            transferModel.setAmount(cashMoney);
            transferModel.setPayPwd(pwd);
            payApiService.executeRefund(brandId, transferModel); // 转账后，会修改当期应付款支付状态为已付，同时修改实时用掉的授信额度
            dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
            dealerRefund.setReachStatus(DealerConstant.DealerRefund.PAYMENT_GENERAL);
            dealerRefund.setNextActTime(null);
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);
        }
    }



       /* else
        {
            debtMoney = dealerRefund.getRefundAmount();
        }
        Short state = dealerRefund.getRefundState();
        // /已经同意退款、退货退款 没有下一个操作
        dealerRefundMapper.updateNextActTime(refrenceId, null);
        // 同意退款给终端商
        if (state.equals(DealerConstant.DealerRefund.WAIT_HANDLE) && dealerRefund.getNeedRefund() == false)
        {
            agreeDealerTotalRefund(dealerRefund, debtMoney, false);
        }
        // 确认收到退货，退款给终端商
        else if (state.equals(DealerConstant.DealerRefund.SHIPED) && dealerRefund.getNeedRefund())
        {
            agreeDealerTotalReturn(dealerRefund, debtMoney, false);
        }
        else
        {
            throw new BusinessException(DealerConst.CANNOT_SCOPE);
        }*/

    // 同意退款
    private void agreeDealerTotalRefund(DealerRefund dealerRefund, BigDecimal debtMoney, boolean isSystem) throws BusinessException
    {
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(dealerRefund.getBrandId());
        if (null == brandInfo) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); }
        dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_REFUND);
        dealerRefund.setNextActTime(null);
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerRefundMapper.updateRefundState4Factory(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.AGREE_REFUND, debtMoney,DealerConstant.DealerRefund.PAYMENT_REACH);
        // 如果申请了客服介入，只要介入状态不是纠纷关闭，则是介入状态改成纠纷处理完毕
        if (dealerRefund.getSerProStatus() != null && dealerRefund.getSerProStatus() != DealerConstant.DealerOrder.SER_STATUS_COMPLETED)
        {
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
        }
        // 操作记录
        this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + brandInfo.getComName() + "】" + "同意了退款申请"));
        //processFactoryOrder(dealerRefund.getBrandId(), dealerRefund.getDealerId(), debtMoney);
    }
    
    // 同意退货并且退款
    private void agreeDealerTotalReturn(DealerRefund dealerRefund, BigDecimal debtMoney, boolean isSystem) throws BusinessException
    {
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(dealerRefund.getBrandId());
        if (null == brandInfo) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); }
        dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
        dealerRefund.setNextActTime(null);
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerRefundMapper.updateRefundState4Factory(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND, debtMoney,
                DealerConstant.DealerRefund.PAYMENT_REACH);
        // 如果申请了客服介入，只要介入状态不是纠纷关闭，则是介入状态改成纠纷处理完毕
        if (dealerRefund.getSerProStatus() != null && dealerRefund.getSerProStatus() != DealerConstant.DealerOrder.SER_STATUS_COMPLETED)
        {
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
        }
        // 操作记录
        this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                ((isSystem == true) ? "操作超时，系统收货并同意了退款申请" : "【" + brandInfo.getComName() + "】" + "已收货并同意了退款申请"));
        //processFactoryOrder(dealerRefund.getBrandId(), dealerRefund.getDealerId(), debtMoney);
    }

    @Override
    public DealerRefund getByRefundIdAndBrandId(Long refundId, String brandId)
    {
        return dealerRefundMapper.getByRefundIdAndBrandId(refundId, brandId);
    }
    
    @Override
    public PaginateResult<DealerRefund> factoryStoreDealerRefund(DealerRefund dealerRefund, Pagination page) throws BusinessException
    {
        dealerRefund.setPage(page);
        List list = dealerRefundMapper.factoryStoreDealerRefund(dealerRefund);
        return new PaginateResult(page, list);
    }
    
    // 授信同意退款
    private void agreeDealerRefund(DealerRefundModel dealerRefund, BigDecimal debtMoney, BigDecimal cash, boolean isSystem) throws BusinessException
    {
        dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_REFUND);
        dealerRefund.setNextActTime(null);
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        short state = DealerConstant.DealerRefund.PAYMENT_REACH;
        if (null != cash && cash.compareTo(BigDecimal.ZERO) > 0) state = DealerConstant.DealerRefund.PAYMENT_REACH_AND_BALANCE;
        dealerRefundMapper.updateRefundState4Factory(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.AGREE_REFUND, debtMoney, state);
        DealerOrder dealerOrder = dealerOrderMapper.selectByPrimaryKey(dealerRefund.getOrderId());
        dealerOrder.setRefundStatus(DealerConstant.DealerRefund.AGREE_REFUND);
        dealerOrder.setOutActTime(null);
        updateBrandOrDealerCount(dealerOrder, dealerRefund);
        dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.AGREE_REFUND);
        if (null != dealerOrder.getPayment() && dealerOrder.getPayment().compareTo(BigDecimal.ZERO) > 0 && dealerOrder.getPayment().compareTo(cash) > 0)
        {
            dealerRefund.setComPayment(dealerOrder.getPayment().subtract(cash));
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);
        }
        /*if (isAllRefund(dealerOrder, dealerRefund))
        {
            // 如果全部退款，则改为退款关闭
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            // todo 删除拿样日志
            *//* productSampleLogDao.updateDelState(dealerOrder.getRefrenceId(), BrandConstant.DEL_STATE_DELETED); *//*
        }
        else
        {*/
            // 如果部分退款则改为交易完成
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        /*}*/
        // 如果申请了客服介入，只要介入状态不是纠纷关闭，则是介入状态改成纠纷处理完毕
        if (dealerRefund.getSerProStatus() != null && dealerRefund.getSerProStatus() != DealerConstant.DealerOrder.SER_STATUS_COMPLETED)
        {
            // 修改客服介入处理状态
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
        // 退款操作（到支付平台）
        orderPayService.executeRefund(dealerOrder.getRefrenceId(), dealerRefund.getRefundAmount(), BigDecimal.ZERO);
        // 操作记录
        this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "同意了退款申请"));
        // 新增订单操作记录
        this.insertDealerOrderAction(DealerConstant.DealerOrderAction.AGREEREFUND, DealerConstant.DealerOrderAction.AGREEREFUND_NAME,
                ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "同意了退款申请"), dealerRefund.getOrderId(), dealerRefund.getBrandId(),
                dealerRefund.getBrandName());
        // 改为总账中实现
        // @Deprecated processFactoryOrder(dealerRefund.getBrandId(), dealerRefund.getDealerId(), debtMoney);
    }
    
    // 授信同意退货并且退款
    private void agreeDealerReturn(DealerRefundModel dealerRefund, BigDecimal debtMoney, BigDecimal cash, boolean isSystem) throws BusinessException
    {
        dealerRefund.setRefundState(DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND);
        dealerRefund.setNextActTime(null);
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        short state = DealerConstant.DealerRefund.PAYMENT_REACH;
        if (null != cash && cash.compareTo(BigDecimal.ZERO) > 0) state = DealerConstant.DealerRefund.PAYMENT_REACH_AND_BALANCE;
        dealerRefundMapper.updateRefundState4Factory(dealerRefund.getRefrenceId(), DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND, debtMoney, state);
        DealerOrder dealerOrder = dealerOrderMapper.selectByPrimaryKey(dealerRefund.getOrderId());
        dealerOrder.setRefundStatus(DealerConstant.DealerRefund.AGREE_REFUND);
        dealerOrder.setOutActTime(null);
        updateBrandOrDealerCount(dealerOrder, dealerRefund);
        dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.AGREE_REFUND);
        if (null != dealerOrder.getPayment() && dealerOrder.getPayment().compareTo(BigDecimal.ZERO) > 0 && dealerOrder.getPayment().compareTo(cash) > 0)
        {
            dealerRefund.setComPayment(dealerOrder.getPayment().subtract(cash));
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);
        }
        /*if (isAllRefund(dealerOrder, dealerRefund))
        {
            // 如果全部退款，则改为退款关闭
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_CLOSED);
            // todo 删除拿样日志
            *//* productSampleLogDao.updateDelState(dealerOrder.getRefrenceId(), BrandConstant.DEL_STATE_DELETED); *//*
        }
        else
        {*/
            // 如果部分退款则改为交易完成
            dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
            dealerOrderMapper.updateOrderStatus(dealerRefund.getOrderId(), DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        /*}*/
        // 如果申请了客服介入，只要介入状态不是纠纷关闭，则是介入状态改成纠纷处理完毕
        if (dealerRefund.getSerProStatus() != null && dealerRefund.getSerProStatus() != DealerConstant.DealerOrder.SER_STATUS_COMPLETED)
        {
            // 修改客服介入处理状态
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
        // 退款操作（到支付平台）
        orderPayService.executeRefund(dealerOrder.getRefrenceId(), dealerRefund.getRefundAmount(), BigDecimal.ZERO);
        // 操作记录
        this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "同意了退款申请"));
        // 新增订单操作记录
        this.insertDealerOrderAction(DealerConstant.DealerOrderAction.AGREEREFUND, DealerConstant.DealerOrderAction.AGREEREFUND_NAME,
                ((isSystem == true) ? "操作超时，系统同意了退款申请" : "【" + dealerRefund.getBrandName() + "】" + "同意了退款申请"), dealerRefund.getOrderId(), dealerRefund.getBrandId(),
                dealerRefund.getBrandName());
        // 改为总账中实现
        // processFactoryOrder(dealerRefund.getBrandId(), dealerRefund.getDealerId(), debtMoney);
    }
    
    /**
     * @Deprecated
     * 改为总账中处理
     * 根据抵用金额依次处理未完全支付的订单
     * @param brandId
     * @param dealerId
     * @param debtMoney
     * @author Playguy
     */
    @Deprecated
    private void processFactoryOrder(String brandId, String dealerId, BigDecimal debtMoney)
    {
        // 取出未结算的金额
        BigDecimal unClearAmount = dealerClearingMapper.getUnClearAmount(brandId, dealerId);
        // 取出待分摊的订单
        List<DealerOrderModel> dealerOrders = dealerOrderMapper.listFactoryStoreUnpay(brandId, dealerId);
        for (DealerOrderModel dealerOrder : dealerOrders)
        {
            if (debtMoney.compareTo(BigDecimal.ZERO) == 1) // 当抵用金额大于零时
            {
                Boolean freightFlag = Boolean.FALSE;
                // 订单总金额 = 货款金额 + 运费
                BigDecimal totalAmount = OrderUtils.getTotalAmount(dealerOrder);
                // 结算总金额 = 订单总金额 + 已部份结算的金额
                BigDecimal clearToalAmount = totalAmount.add(dealerOrder.getClearingAmount());
                // 已支付金额 = 已支付货款 + 已支付运费
                BigDecimal paidAmount = dealerOrder.getPayment();
                // 当运费已支付过时，已支付的金额还须加上运费金额
                if (dealerOrder.getFrePayState().equals(Short.valueOf("1")))
                {
                    paidAmount = paidAmount.add(dealerOrder.getAdjustFreight());
                    freightFlag = Boolean.TRUE;
                }
                // 比较未结算的金额和当前订单的总金额
                if (unClearAmount.compareTo(clearToalAmount) == 0 || unClearAmount.compareTo(clearToalAmount) == 1)
                {
                    // 当未结算的金额大于或等于订单金额时，未结算金额须减去当前订单的总金额和部份结算的金额
                    unClearAmount = unClearAmount.subtract(clearToalAmount);
                }
                else
                {
                    // 当未结算金额小下订单金额时，订单金额须减去未结算金额
                    totalAmount = totalAmount.subtract(unClearAmount);
                    // 未结算的金额设置为零
                    unClearAmount = BigDecimal.ZERO;
                }
                // 未支付的金额 = 订单总金额 - 已支付的金额
                BigDecimal unPaidAmount = totalAmount.subtract(paidAmount);
                // 当订单支付状态为完全支付或订单状态为已完成事跳出当前循环
                if (dealerOrder.getPayState().equals(DealerConstant.DealerOrder.PAY_STATE_ALL)
                        || dealerOrder.getOrderStatus().equals(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED))
                {
                    continue;
                }
                // 当未结算金额等于零时，说明预留的分滩金额已用完
                if (unClearAmount.compareTo(BigDecimal.ZERO) == 0)
                {
                    // 如果抵用金额大于或等于未支付的金额
                    if (debtMoney.compareTo(unPaidAmount) == 0 || debtMoney.compareTo(unPaidAmount) == 1)
                    {
                        BigDecimal amount = dealerOrder.getFrePayState().equals(Short.valueOf("0")) ? dealerOrder.getAdjustFreight() : BigDecimal.ZERO;
                        BigDecimal offset = unPaidAmount.subtract(amount);// 抵扣金额
                        BigDecimal productAmount = dealerOrder.getProductPrice().subtract(offset);
                        // 当已支付的金额大于新的订单金额时过程
                        if (paidAmount.compareTo(productAmount) == 1) continue;
                        if (null == dealerOrder.getOffSetAmount() || dealerOrder.getOffSetAmount().compareTo(dealerOrder.getOffSetAmount().add(debtMoney)) != 0)
                        {
                            UserOperationLog userOperationLog = new UserOperationLog();
                            userOperationLog.setObjectId(dealerOrder.getRefrenceId());
                            userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                            userOperationLog.setEvent("【修改订单抵扣金额：修改前：" + (dealerOrder.getOffSetAmount() == null ? "" : dealerOrder.getOffSetAmount()) + " 修改后："
                                    + new java.text.DecimalFormat("0.00").format(dealerOrder.getOffSetAmount().add(debtMoney)) + "，订单号：" + dealerOrder.getOrderId() + "】");
                            //userOperationLogService.addUserOperationLog(request, userOperationLog);
                        }
                        dealerOrder.setOffSetAmount(dealerOrder.getOffSetAmount().add(offset));// 抵扣金额
                        dealerOrder.setProductPrice(productAmount);// 调整订单金额
                        dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_ALL);
                        dealerOrder.setFrePayState(DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED);
                        dealerOrderMapper.updateByPrimaryKey(dealerOrder);
                        // 新增订单操作记录
                        this.insertDealerOrderAction(DealerConstant.DealerOrderAction.ORDERPAY, DealerConstant.DealerOrderAction.ORDERPAY_NAME, "欠收抵应付:" + unPaidAmount
                                + ",货款已付清", dealerOrder.getRefrenceId(), dealerOrder.getBrandId(), dealerOrder.getBrandName());
                        debtMoney = debtMoney.subtract(unPaidAmount);
                    }
                    else
                    {
                        if (freightFlag)
                        {// 当运费已支付时
                            BigDecimal productAmount = dealerOrder.getProductPrice().subtract(debtMoney);
                            // 当已支付的金额大于新的订单金额时过程
                            if (paidAmount.compareTo(productAmount) == 1) continue;
                            if (null == dealerOrder.getOffSetAmount() || dealerOrder.getOffSetAmount().compareTo(dealerOrder.getOffSetAmount().add(debtMoney)) != 0)
                            {
                                UserOperationLog userOperationLog = new UserOperationLog();
                                userOperationLog.setObjectId(dealerOrder.getRefrenceId());
                                userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                                userOperationLog.setEvent("【修改订单抵扣金额：修改前：" + (dealerOrder.getOffSetAmount() == null ? "" : dealerOrder.getOffSetAmount()) + " 修改后："
                                        + new java.text.DecimalFormat("0.00").format(dealerOrder.getOffSetAmount().add(debtMoney)) + "，订单号：" + dealerOrder.getOrderId()
                                        + "】");
                                //userOperationLogService.addUserOperationLog(request, userOperationLog);
                            }
                            dealerOrder.setOffSetAmount(dealerOrder.getOffSetAmount().add(debtMoney));
                            dealerOrder.setProductPrice(productAmount);
                            dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_PART);
                            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
                            // 新增订单操作记录
                            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.ORDERPAY, DealerConstant.DealerOrderAction.ORDERPAY_NAME, "欠收抵应付:" + debtMoney,
                                    dealerOrder.getRefrenceId(), dealerOrder.getBrandId(), dealerOrder.getBrandName());
                        }
                        else
                        {
                            // 当抵用金额大于或等于运费
                            if (debtMoney.compareTo(dealerOrder.getAdjustFreight()) == 0 || debtMoney.compareTo(dealerOrder.getAdjustFreight()) == 1)
                            {
                                dealerOrder.setFrePayState(DealerConstant.DealerOrder.FRE_PAY_STATE_PAYED);
                                debtMoney = debtMoney.subtract(dealerOrder.getAdjustPrice());
                            }
                            BigDecimal productAmount = dealerOrder.getProductPrice().subtract(debtMoney);
                            // 当已支付的金额大于新的订单金额时过程
                            if (paidAmount.compareTo(productAmount) == 1) continue;
                            if (null == dealerOrder.getOffSetAmount() || dealerOrder.getOffSetAmount().compareTo(dealerOrder.getOffSetAmount().add(debtMoney)) != 0)
                            {
                                UserOperationLog userOperationLog = new UserOperationLog();
                                userOperationLog.setObjectId(dealerOrder.getRefrenceId());
                                userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
                                userOperationLog.setEvent("【修改订单抵扣金额：修改前：" + (dealerOrder.getOffSetAmount() == null ? "" : dealerOrder.getOffSetAmount()) + " 修改后："
                                        + new java.text.DecimalFormat("0.00").format(dealerOrder.getOffSetAmount().add(debtMoney)) + "，订单号：" + dealerOrder.getOrderId()
                                        + "】");
                                //userOperationLogService.addUserOperationLog(request, userOperationLog);
                            }
                            dealerOrder.setOffSetAmount(dealerOrder.getOffSetAmount().add(debtMoney));
                            dealerOrder.setProductPrice(productAmount);
                            dealerOrder.setPayState(DealerConstant.DealerOrder.PAY_STATE_PART);
                            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
                            // 新增订单操作记录
                            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.ORDERPAY, DealerConstant.DealerOrderAction.ORDERPAY_NAME, "欠收抵应付:" + debtMoney,
                                    dealerOrder.getRefrenceId(), dealerOrder.getBrandId(), dealerOrder.getBrandName());
                        }
                        debtMoney = BigDecimal.ZERO;
                    }
                }
            }
            else
            {
                break;
            }
        }
    }
    
    @Override
    public PaginateResult<DealerRefund> getDealerRefundList4Boss(DealerRefundModel dealerRefund, Pagination page)
    {
        dealerRefund.setPage(page);
        List<DealerRefund> list = dealerRefundMapper.getDealerRefundList4Boss(dealerRefund);
        PaginateResult<DealerRefund> result = new PaginateResult(page, list);
        return result;
    }
    
    /**
     * 品牌商操作超时，自动同意退款（系统操作）
     *
     * @param dealerRefund
     * @throws BusinessException
     */
    @Override
    public void updateAutoAgreeReturn(DealerRefundModel dealerRefund) throws BusinessException
    {
        if (null != dealerRefund.getNextActTime() && dealerRefund.getNextActTime() - CalendarUtils.getCurrentLong() < 0)
        {
            String refrenceId = dealerRefund.getRefrenceId();
            DealerOrder dealerOrder = dealerOrderMapper.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
            if (null == dealerOrder || null == dealerRefund || !dealerRefund.getRefrenceId().equals(refrenceId)) { throw new BusinessException(DealerConst.NOT_REFUND); }
            Short state = dealerRefund.getRefundState();
            // 申请退款(无退货)到期品牌商为确认退款
            if (state.equals(DealerConstant.DealerRefund.WAIT_HANDLE) && dealerRefund.getNeedRefund() == false)
            {
                dealerRefundMapper.updateNextActTime(refrenceId, null);
                AgreeDealerRefund(dealerOrder, dealerRefund, true);
            }
            // 同意退货等待发货
            else if (DealerConstant.DealerRefund.WAIT_SHIP == state.shortValue())
            {
                dealerRefundMapper.updateRefundState(refrenceId, DealerConstant.DealerRefund.CLOSS_REFUND);
                dealerOrder.setRefundStatus(DealerConstant.DealerRefund.CLOSS_REFUND);
                List<Integer> countTypeList = Lists.newArrayList();
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
                brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
                countTypeList.clear();
                countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
                dealerCountService.modifyDealerCount(dealerRefund.getDealerId(), countTypeList.toArray(new Integer[]{}));
                // 操作记录
                this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(), "发货操作超时，退款自动关闭");
                // 新增订单操作记录
                this.insertDealerOrderAction(DealerConstant.DealerOrderAction.CLOSE, DealerConstant.DealerOrderAction.CLOSE_TXT, "发货操作超时，退款自动关闭",
                        dealerRefund.getOrderId(), dealerRefund.getBrandId(), dealerRefund.getBrandName());
            }
            // 退货已发货（经销商发货）
            else if (DealerConstant.DealerRefund.SHIPED == state.shortValue())
            {
                dealerRefundMapper.updateNextActTime(refrenceId, null);
                AgreeDealerReturn(dealerOrder, dealerRefund, true);
            }
            // 拒绝退款（品牌商拒绝退款） // 品牌商拒绝退货 退货 终端商又逾期没有申请 客服介入，系统自动关闭退款
            else if ((DealerConstant.DealerRefund.NOT_REFUND == state.shortValue() || DealerConstant.DealerRefund.NOT_RETURN == state.shortValue())
                    && dealerRefund.getSerProStatus() == null)
            {
                dealerRefundMapper.updateNextActTime(refrenceId, null);
                dealerRefundMapper.updateRefundState(refrenceId, DealerConstant.DealerRefund.CLOSS_REFUND);
                dealerOrder.setRefundStatus(DealerConstant.DealerRefund.CLOSS_REFUND);
                List<Integer> countTypeList = Lists.newArrayList();
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
                brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
                countTypeList.clear();
                countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
                dealerCountService.modifyDealerCount(dealerRefund.getDealerId(), countTypeList.toArray(new Integer[]{}));
                // 操作记录
                this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(), "【" + dealerRefund.getBrandName() + "】" + "操作超时，系统自动关闭退款");
                // 新增订单操作记录
                this.insertDealerOrderAction(DealerConstant.DealerOrderAction.CLOSE, DealerConstant.DealerOrderAction.CLOSE_TXT, "操作超时，退款自动关闭", dealerRefund.getOrderId(),
                        dealerRefund.getBrandId(), dealerRefund.getBrandName());
            }
            // 拒绝退款（品牌商拒绝退款） // 品牌商拒绝退货 退货 终端商又逾期 有申请 客服介入，系统自动关闭退款
            else if ((DealerConstant.DealerRefund.NOT_REFUND == state.shortValue() || DealerConstant.DealerRefund.NOT_RETURN == state.shortValue())
                    && dealerRefund.getSerProStatus() != null)
            {
                dealerRefundMapper.updateNextActTime(refrenceId, null);
                dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), DealerConstant.DealerOrder.SER_STATUS_COMPLETED);
                dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_COMPLETED);
                dealerOrderMapper.updateByPrimaryKey(dealerOrder);
                dealerRefundMapper.updateRefundState(refrenceId, DealerConstant.DealerRefund.CLOSS_REFUND);
                dealerOrder.setRefundStatus(DealerConstant.DealerRefund.CLOSS_REFUND);
                List<Integer> countTypeList = Lists.newArrayList();
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
                brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
                countTypeList.clear();
                countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
                dealerCountService.modifyDealerCount(dealerRefund.getDealerId(), countTypeList.toArray(new Integer[]{}));
                // 操作记录
                this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(), "【" + dealerRefund.getBrandName() + "】" + "操作超时，系统自动关闭退款");
                // 新增订单操作记录
                this.insertDealerOrderAction(DealerConstant.DealerOrderAction.CLOSE, DealerConstant.DealerOrderAction.CLOSE_TXT, "操作超时，退款自动关闭", dealerRefund.getOrderId(),
                        dealerRefund.getBrandId(), dealerRefund.getBrandName());
            }
            // 拒绝退款（品牌商拒绝退款） 客服已介入 客服处理中
            else if (DealerConstant.DealerRefund.WAIT_HANDLE == state.shortValue() && dealerRefund.getCusJoin() != null ? dealerRefund.getCusJoin() : Boolean.FALSE
                    && Short.valueOf("2").equals(dealerRefund.getSerProStatus()))
            {
                dealerRefundMapper.updateRefundStatutsCusJoin(refrenceId, DealerConstant.DealerRefund.AGREE_REFUND, DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
                dealerRefundMapper.updateNextActTime(refrenceId, null);
                AgreeDealerRefund(dealerOrder, dealerRefund, true);
            }
            // 拒绝退货（品牌商拒绝退货） 客服已介入 客服处理中
            else if (DealerConstant.DealerRefund.SHIPED == state.shortValue() && dealerRefund.getCusJoin() != null ? dealerRefund.getCusJoin() : Boolean.FALSE
                    && Short.valueOf("2").equals(dealerRefund.getSerProStatus()))
            {
                dealerRefundMapper.updateNextActTime(refrenceId, null);
                AgreeDealerReturn(dealerOrder, dealerRefund, true);
            }
            else
            {
                LoggerUtils.logInfo(logger, "订单" + dealerOrder.getOrderId() + DealerConst.CANNOT_SCOPE.message + ",订单id：" + dealerOrder.getOrderId());
                throw new BusinessException(DealerConst.CANNOT_SCOPE);
            }
            if (dealerOrder.getOrderStatus().intValue() == DealerConstant.DealerOrder.ORDER_STATUS_CLOSED
                    || dealerOrder.getOrderStatus().intValue() == DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED)
            {
                dealerOrder.setOutActTime(null);
            }
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
    }
    
    @Override
    public PaginateResult<DealerRefundModel> getDealerRefund(Long nextActTime, Pagination page)
    {
        return new PaginateResult(page, dealerRefundMapper.getDealerRefund(nextActTime, page));
    }
    
    /**
     * 品牌商操作超时，自动同意退货
     *
     * @param dealerRefund
     * @param isSystemJob
     * @throws BusinessException
     */
    @Override
    public void updateAutoAgreeReturnBoth(DealerRefundModel dealerRefund, Boolean isSystemJob) throws BusinessException
    {
        DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", "dealerReturnDay");
        int amount = Integer.valueOf(dataDictValue.getDictValue());
        String refrenceId = dealerRefund.getRefrenceId();
        if (null == dealerRefund) { throw new BusinessException(DealerConst.NOT_REFUND); }
        Short state = dealerRefund.getRefundState();
        if (state.equals(DealerConstant.DealerRefund.WAIT_HANDLE) && dealerRefund.getNeedRefund())
        {
            // 增加终端商退货处理时间
            Long nextActTime = CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount);
            dealerRefund.setNextActTime(nextActTime);
            String brandRecAddr = dealerRefund.getBrandRecAddr();
            if (StringUtils.isBlank(brandRecAddr)) brandRecAddr = getFullDeaultBrandAddressStr(dealerRefund);
            dealerRefundMapper.updateAgreeReturnBoth1(refrenceId, DealerConstant.DealerRefund.WAIT_SHIP, brandRecAddr, nextActTime,CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.WAIT_SHIP);
            // 更新订单修改时间
            dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
            // 操作记录
            String brandReAddr = "品牌商收货地址:" + brandRecAddr;
            String replayContent = "";
            if (isSystemJob)
            {
                replayContent = "【" + dealerRefund.getBrandName() + "】操作超时，系统同意退货申请" + "  " + brandReAddr;
            }
            else
            {
                replayContent = "【" + dealerRefund.getBrandName() + "】同意了退货申请" + "  " + brandReAddr;
            }
            this.insertDealerRefReply(dealerRefund, dealerRefund.getBrandId(), dealerRefund.getBrandName(), replayContent);
            // 新增订单操作记录
            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.AGREEREFUND, DealerConstant.DealerOrderAction.AGREEREFUND_NAME, replayContent,
                    dealerRefund.getOrderId(), dealerRefund.getBrandId(), dealerRefund.getBrandName());
        }
        else
        {
            throw new BusinessException(DealerConst.CANNOT_SCOPE);
        }
    }
    
    @Override
    public void updateLogisTimeoutTask(DealerRefundModel dealerRefund) throws BusinessException
    {
        if (null != dealerRefund.getNextActTime() && dealerRefund.getNextActTime() - CalendarUtils.getCurrentLong() < 0)
        {
            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
            brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
            dealerCountService.modifyDealerCount(dealerRefund.getDealerId(), countTypeList.toArray(new Integer[]{}));
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.CANCEL_REFUND);
            // 修改订单修改时间
            dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
            dealerRefund.setRefundState(DealerConstant.DealerRefund.CANCEL_REFUND);
            dealerRefund.setRemark("填写物流信息超时，系统自动关闭终端商退款退货申请。");
            dealerRefund.setReturnTime(CalendarUtils.getCurrentLong());
            dealerRefundMapper.updateByPrimaryKey(dealerRefund);
            // 增加操作记录
            this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(), "填写物流信息超时，系统自动关闭终端商退款退货申请。");
            // 新增订单操作记录
            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.LOGIS_TIME_OUT, DealerConstant.DealerOrderAction.LOGIS_TIME_OUT_NAME,
                    "【" + dealerRefund.getDealerName() + "】" + "填写物流信息超时，系统自动关闭终端商退款退货申请。", dealerRefund.getOrderId(), dealerRefund.getDealerId(),
                    dealerRefund.getDealerName());
        }
    }
    
    @Override
    public PaginateResult<DealerRefundModel> selectAllCusJoin(DealerRefundModel dealerRefund, Pagination page)
    {
        dealerRefund.setPage(page);
        if (StringUtils.isNotEmpty(dealerRefund.getApplyTimeStart()))
        {
            dealerRefund.setApplyTimeStart("" + CalendarUtils.getLongFromTime(dealerRefund.getApplyTimeStart() + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        }
        if (StringUtils.isNotEmpty(dealerRefund.getApplyTimeEnd()))
        {
            dealerRefund.setApplyTimeEnd("" + CalendarUtils.getLongFromTime(dealerRefund.getApplyTimeEnd() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        }
        List list = dealerRefundMapper.selectAllCusJoin(dealerRefund);
        return new PaginateResult<>(page, list);
    }
    
    @Override
    public void updateSerProStatusResult(DealerRefund dealerRefund)
    {
        if (StringUtils.isBlank(dealerRefund.getRefrenceId()) || null == dealerRefund.getSerProStatus()) { throw new IllegalArgumentException(); }
        Set<Short> list = Sets.newHashSet();
        list.add(DealerConstant.DealerOrder.SER_STATUS_WAIT);
        list.add(DealerConstant.DealerOrder.SER_STATUS_INVOLVED);
        list.add(DealerConstant.DealerOrder.SER_STATUS_PROCESSED);
        list.add(DealerConstant.DealerOrder.SER_STATUS_COMPLETED);
        list.add(DealerConstant.DealerOrder.SER_STATUS_CLOSED);
        if (!list.contains(dealerRefund.getSerProStatus())) { throw new IllegalArgumentException(); }
        // /等待客服介入，并且是拒绝退款或者拒绝退货才能处理
        if (dealerRefund.getSerProStatus() == DealerConstant.DealerOrder.SER_STATUS_CLOSED
                && (dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_REFUND || dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_RETURN))
        {
            dealerRefund.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_WAIT);
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), dealerRefund.getSerProStatus());
        }
    }
    
    // 客服介入
    @Override
    public EnumDescribable updateCusjoining(DealerRefund dealerRefund)
    {
        if (StringUtils.isBlank(dealerRefund.getRefrenceId())) { return CommonConst.ORDER_REFRENCE_ID_NULL; }
        if (dealerRefund.getSerProStatus() == DealerConstant.DealerOrder.SER_STATUS_CLOSED)
        {
            DealerOrder dealerOrder = dealerOrderMapper.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
            dealerRefund.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_WAIT);
            dealerRefundMapper.updateSerProStatus(dealerRefund.getRefrenceId(), dealerRefund.getSerProStatus());
            dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_WAIT);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
        else
        {
            return CommonConst.ORDER_REFUND_STATUS_ERROR;
        }
        return CommonConst.SUCCESS;
    }
    
    @Override
    public EnumDescribable updateRefuseJoin(DealerRefund dealerRefund)
    {
        if (StringUtils.isBlank(dealerRefund.getRefrenceId())) { return CommonConst.ORDER_REFRENCE_ID_NULL; }
        // 取消介入
        if (dealerRefund.getSerProStatus() == DealerConstant.DealerOrder.SER_STATUS_WAIT)
        {
            dealerRefund.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_COMPLETED);
            dealerRefundMapper.updateRefuseJoin(dealerRefund);
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), dealerRefund.getRefundState());
            DealerOrder dealerOrder = dealerOrderMapper.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
            dealerRefundMapper.updateRefundState(dealerRefund.getRefrenceId(), dealerRefund.getRefundState());
            dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_COMPLETED);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
        return CommonConst.SUCCESS;
    }
    
    @Override
    public EnumDescribable updateModify(DealerRefundModel dealerRefund)
    {
        if (StringUtils.isBlank(dealerRefund.getRefrenceId()) || null == dealerRefund.getSerProStatus()) { return CommonConst.ORDER_REFRENCE_ID_NULL; }
        // 修改介入,等待客服介和纠纷介入处理中，
        if (dealerRefund.getSerProStatus() == DealerConstant.DealerOrder.SER_STATUS_WAIT
                || dealerRefund.getSerProStatus() == DealerConstant.DealerOrder.SER_STATUS_INVOLVED)
        {
            dealerRefund.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_INVOLVED);
            String time = CalendarUtils.getStringTime(dealerRefund.getNextActTime(), "yyyy-MM-dd");
            dealerRefund.setSerProResult("客服已经处理：请品牌商在" + time + "之前，将" + dealerRefund.getRefundAmount() + " 打给终端商");
            int num = dealerRefund.getUpdateNum();
            dealerRefund.setUpdateNum(num + 1);
            dealerRefundMapper.updateModify(dealerRefund);
            dealerRefundMapper.updateRefundState(dealerRefund.getRefrenceId(), dealerRefund.getRefundState());
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), dealerRefund.getRefundState());
            DealerOrder dealerOrder = dealerOrderMapper.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), dealerRefund.getBrandId());
            dealerOrder.setSerProStatus(DealerConstant.DealerOrder.SER_STATUS_INVOLVED);
            dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
            this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                    "【" + dealerRefund.getBrandName() + "】" + "【" + time + "】" + "之后,系统会将" + "【" + dealerRefund.getRefundAmount() + "】" + "元退款达到[您/终端商]的账户中");
        }
        return CommonConst.SUCCESS;
    }

    @Override
    public void updateRefundProcess(String refrenceId, String dealerId) throws BusinessException
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        if (null != dealerRefund && dealerRefund.getDealerId().equals(dealerId))
        {
            short state = dealerRefund.getRefundState();
            if (state == DealerConstant.DealerRefund.WAIT_HANDLE || state == DealerConstant.DealerRefund.NOT_REFUND || state == DealerConstant.DealerRefund.NOT_RETURN)
            {
                // brandCountDao.updateRefundCount(dealerRefund.getBrandId(),
                // -1);
                // dealerCountDao.updateRefundCount(dealerRefund.getDealerId(),
                // -1);
                dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.CANCEL_REFUND);
                dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
                // DealerOrder dealerOrder =
                // dealerOrderDao.getDealerOrder(dealerRefund.getOrderId(),
                // dealerId);
                /*
                 * if(dealerOrder.getOrderStatus()!=null&&dealerOrder.getOrderStatus
                 * (
                 * ).intValue()==DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE
                 * ){
                 * dealerCountDao.updateDealerCount(DealerConstant.DealerCount
                 * .DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT, dealerId, 1); }
                 */
                dealerRefund.setRefundState(DealerConstant.DealerRefund.CANCEL_REFUND);
                dealerRefund.setRemark((dealerRefund.getNeedRefund() == true ? "撤消了退款退货申请" : "撤消了退款申请"));
                dealerRefundMapper.updateByPrimaryKey(dealerRefund);
                List<Integer> countTypeList = Lists.newArrayList();
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
                brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
                countTypeList.clear();
                countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
                countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT);
                dealerCountService.modifyDealerCount(dealerRefund.getDealerId(), countTypeList.toArray(new Integer[]{}));
                // 增加操作记录
                this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                        "【" + dealerRefund.getDealerName() + "】" + (dealerRefund.getNeedRefund() == true ? "撤消了退款退货申请" : "撤消了退款申请"));
                // 新增订单操作记录
                this.insertDealerOrderAction(DealerConstant.DealerOrderAction.APPLYREFUND, (dealerRefund.getNeedRefund() == true ? "撤消退款退货申请" : "撤消退款申请"),
                        "【" + dealerRefund.getDealerName() + "】" + (dealerRefund.getNeedRefund() == true ? "撤消了退款退货申请" : "撤消了退款申请"), dealerRefund.getOrderId(),
                        dealerRefund.getDealerId(), dealerRefund.getDealerName());
            }
        }
    }
    
    @Override
    public boolean validateRefundAmount(BigDecimal refundAmount, BigDecimal totalAmount, BigDecimal freight) throws BusinessException
    {
        try
        {
            refundAmount = null == refundAmount ? new BigDecimal(0) : refundAmount;
            totalAmount = null == totalAmount ? new BigDecimal(0) : totalAmount;
            freight = null == freight ? new BigDecimal(0) : freight;
            if (refundAmount.compareTo(totalAmount.add(freight)) == 1) // 退款金额超过订货金额和物流费用的总和
            { return false; }
        }
        catch (Exception e)
        {
            throw new BusinessException(DealerConst.REFUND_AMOUNT_APPLY_ERROR);
        }
        return true;
    }
    
    @Override
    public void addApply(DealerRefundModel dealerRefund, List<DealerRefAttacht> dealerRefAttachts, UserInfo dealerUserm) throws BusinessException
    {
        try
        {
            String dealerId = dealerUserm.getRefrenceId();
            String userName = dealerUserm.getUserName();
            // 增加品牌商退款处理时间
            // brandConRefund 品牌商同意退款的时间
            // brandConReturn 品牌商同意退货的时间
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate",
                    dealerRefund.getNeedRefund() == true ? "brandConReturn" : "brandConRefund");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            dealerRefund.setNextActTime(CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount));
            /* dealerRefund.setFactoryStore(false); */
            // 新增退款信息
            add(dealerRefund, dealerUserm.getRefrenceId());
            // 更新 终端商订单DealerOrder 的 refundStatus 字段
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), dealerRefund.getRefundState());
            dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT);
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT);
            dealerCountService.modifyDealerCount(dealerId, countTypeList.toArray(new Integer[]{}));
            countTypeList.clear();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT);
            brandCountService.modifyBrandCount(dealerRefund.getBrandId(), countTypeList.toArray(new Integer[]{}));
            // 操作记录
            String refundReason = "，退款原因：" + dealerRefund.getRefundReason() + ";退款说明: " + dealerRefund.getRefundMark() + ",申请退款金额：" + dealerRefund.getRefundAmount() + "元";
            DealerRefReply dealerRefReply = this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                    "【" + dealerRefund.getDealerName() + "】" + (dealerRefund.getNeedRefund() == true ? "申请了退款退货" + refundReason : "申请了退款" + refundReason));
            // 添加退款附件记录
            addList(dealerRefAttachts, dealerRefund.getRefrenceId(), dealerId, userName, dealerRefReply.getRefrenceId());
            // 新增订单操作记录
            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.APPLYREFUND, DealerConstant.DealerOrderAction.APPLYREFUND_NAME,
                    "【" + dealerRefund.getDealerName() + "】" + (dealerRefund.getNeedRefund() == true ? "申请了退款退货" + refundReason : "申请了退款" + refundReason),
                    dealerRefund.getOrderId(), dealerRefund.getDealerId(), dealerRefund.getDealerName());
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, e.getLocalizedMessage());
            throw new BusinessException(DealerConst.FAILURE);
        }
        // crm免登陆经销商后台操作-申请退款
        // String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
        // if (StringUtils.isNotBlank(crm_userId))
        // {
        // DealerOrder dealerOrder = dealerOrderCache.findById(dealerRefund.getOrderId());
        // DealerCrmLog dealerCrmLog = new DealerCrmLog();
        // dealerCrmLog.setOperatorId(crm_userId);
        // dealerCrmLog.setBrandesId(dealerOrder.getBrandsId());
        // dealerCrmLog.setBeOperationName(CrmConstant.crmToDealerOptionModel.GENERAL_ORDER);
        // dealerCrmLog.setOperation(CrmConstant.CrmDealerOptionName.APPLY); // 申请退款
        // dealerCrmLog.setOperationDetails("申请退款:'普通订单:订单单号" + dealerRefund.getOrderNumber() + "','申请退款金额:" + dealerRefund.getRefundAmount() + "元';");
        // dealerCrmLogService.addCRMLog(request, dealerCrmLog);
        // }
    }
    
    public void addList(List<DealerRefAttacht> dealerRefAttachts, String refundId, String userId, String userName, String replyId)
    {
        if (null != dealerRefAttachts && !dealerRefAttachts.isEmpty())
        {
            for (DealerRefAttacht dealerRefAttacht : dealerRefAttachts)
            {
                dealerRefAttacht.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerRefAttacht.setDelFlag(false);
                dealerRefAttacht.setCreateTime(CalendarUtils.getCurrentLong());
                dealerRefAttacht.setRefundId(refundId);
                dealerRefAttacht.setUserId(userId);
                dealerRefAttacht.setUserName(userName);
                dealerRefAttacht.setReplyId(replyId);
                dealerRefAttachtMapper.insert(dealerRefAttacht);
            }
        }
    }
    
    private DealerRefund add(DealerRefundModel dealerRefund, String dealerId)
    {
        dealerRefund.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerRefund.setRefundId(com.zttx.web.utils.SerialnoUtils.buildRefundSN(refundNumberService.execute()));
        dealerRefund.setRefundState(DealerConstant.DealerRefund.WAIT_HANDLE);
        dealerRefund.setUpdateNum(1);
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        DealerOrder dealerOrder = dealerOrderMapper.getByOrderIdAndDealerId(dealerRefund.getOrderNumber(), dealerId);
        dealerRefund.setOrderId(dealerOrder.getRefrenceId());
        dealerRefund.setDealerId(dealerOrder.getDealerId());
        dealerRefund.setDealerName(dealerOrder.getDealerName());
        dealerRefund.setBrandId(dealerOrder.getBrandId());
        dealerRefund.setBrandName(dealerOrder.getBrandName());
        if (dealerOrder.getDealerOrderType() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT)
        {
            dealerRefund.setFactoryStore(true);
            dealerRefund.setTotalAmount(dealerOrder.getPayment().add(dealerOrder.getAdjustFreight()));
            BigDecimal creditMonty = dealerOrder.getProductPrice().subtract(dealerOrder.getPayment()); // 授信额度
            dealerRefund.setComPayment(dealerRefund.getTotalAmount());
            if (dealerRefund.getRefundAmount().compareTo(creditMonty) > 0)
            {
                BigDecimal cashRefund = dealerRefund.getRefundAmount().subtract(creditMonty); // 需退的余额现金
                dealerRefund.setComPayment(dealerRefund.getTotalAmount().subtract(cashRefund));
            }
        }
        else
        {
            dealerRefund.setFactoryStore(false);
            dealerRefund.setTotalAmount(dealerOrder.getPayment().add(dealerOrder.getAdjustFreight()));
            dealerRefund.setComPayment(NumericUtils.sub(dealerRefund.getTotalAmount().toString(), dealerRefund.getRefundAmount().toString()));
        }
        dealerRefund.setApplyTime(CalendarUtils.getCurrentLong());
        dealerRefund.setReachAmount(BigDecimal.ZERO);
        dealerRefund.setReachStatus(Short.valueOf("0"));
        dealerRefund.setCreateTime(CalendarUtils.getCurrentLong());
        dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerRefundMapper.insert(dealerRefund);
        return dealerRefund;
    }
    
    @Override
    public void updateApply(DealerRefundModel dealerRefund, List<DealerRefAttacht> dealerRefAttachts, UserInfo dealerUserm) throws BusinessException
    {
        try
        {
            String dealerId = dealerUserm.getRefrenceId();
            String userName = dealerUserm.getUserName();
            // 添加终端商退款信息记录
            // 增加品牌商退款处理时间
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate",
                    dealerRefund.getNeedRefund() == true ? "brandConReturn" : "brandConRefund");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            dealerRefund.setNextActTime(CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount));
            DealerRefund old = update(dealerRefund, dealerUserm.getRefrenceId());
            /* DealerOrder dealerOrder = dealerOrderMapper.getDealerOrder(dealerRefund.getOrderId(), dealerId); */
            DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndDealerId(dealerRefund.getOrderNumber(), dealerId);
            // 订单 是全部分货状态 同时未发生退款的订单 才可以
            if (dealerOrder.getOrderStatus() != null && dealerOrder.getOrderStatus().shortValue() == DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE
                    && dealerOrder.getRefundStatus() == null)
            {
                dealerCountMapper.updateDealerCount(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT, dealerId, -1);
            }
            // 更新 终端商订单DealerOrder 的 refundStatus 字段
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), dealerRefund.getRefundState());
            dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
            // 操作记录
            dealerRefund = dealerRefundMapper.getEntityById(old.getRefrenceId());
            String refundReason = "，退款原因：" + dealerRefund.getRefundReason() + ";退款说明: " + dealerRefund.getRefundMark() + ",申请退款金额：" + dealerRefund.getRefundAmount() + "元";
            DealerRefReply dealerRefReply = this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                    "【" + dealerRefund.getDealerName() + "】" + (dealerRefund.getNeedRefund() == true ? "修改了退款退货申请" + refundReason : "修改了退款申请" + refundReason));
            // 添加退款附件记录
            addList(dealerRefAttachts, dealerRefund.getRefrenceId(), dealerId, userName, dealerRefReply.getRefrenceId());
            // 新增订单操作记录
            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.APPLYREFUND, (dealerRefund.getNeedRefund() == true ? "修改退款退货申请" : "修改退款申请"),
                    "【" + dealerRefund.getDealerName() + "】" + (dealerRefund.getNeedRefund() == true ? "修改了退款退货申请" + refundReason : "修改了退款申请" + refundReason),
                    dealerRefund.getOrderId(), dealerRefund.getDealerId(), dealerRefund.getDealerName());
        }
        catch (Exception e)
        {
            throw new BusinessException(DealerConst.FAILURE);
        }
    }
    
    private DealerRefund update(DealerRefund dealerRefund, String dealerId) throws Exception
    {
        DealerRefund old = dealerRefundMapper.getEntityByOrderNumber(dealerRefund.getOrderNumber());
        if (null == dealerId || !dealerId.equals(old.getDealerId())) { throw new RuntimeException(); }
        old.setRefundState(DealerConstant.DealerRefund.WAIT_HANDLE);
        old.setUpdateNum(old.getUpdateNum() + 1);
        if (old.getUpdateNum() > 3) // TODO N 更新次数上限 暂定，后期走配置文件
        { throw new RuntimeException(); }
        old.setUpdateTime(CalendarUtils.getCurrentLong());
        old.setReceived(dealerRefund.getReceived());
        old.setRefundReason(dealerRefund.getRefundReason());
        old.setRefundAmount(dealerRefund.getRefundAmount());
        old.setRefundMark(dealerRefund.getRefundMark());
        old.setNextActTime(dealerRefund.getNextActTime());
        old.setComPayment(NumericUtils.sub(old.getTotalAmount().toString(), dealerRefund.getRefundAmount().toString()));
        dealerRefundMapper.updateByPrimaryKey(old);
        return old;
    }
    
    @Override
    public void updateDeliver(DealerRefundModel dealerRefund, String dealerId)
    {
        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(dealerRefund.getRefrenceId()) && StringUtils.isNotBlank(dealerRefund.getLogisticsName())
                && StringUtils.isNotBlank(dealerRefund.getTransNum()))
        {
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", "brandConfirmGoods");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            Long nextActTime = CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount);
            dealerRefund.setNextActTime(nextActTime);
            dealerRefundMapper.returnDeliver(dealerRefund, dealerId);
            // 操作记录
            dealerRefund = dealerRefundMapper.getEntityById(dealerRefund.getRefrenceId());
            dealerOrderMapper.updateRefundStatus(dealerRefund.getOrderId(), DealerConstant.DealerRefund.SHIPED);
            dealerOrderMapper.updateOrderTime(dealerRefund.getOrderId(), CalendarUtils.getCurrentLong());
            String refundReason = "，物流公司：" + dealerRefund.getLogisticsName() + "，物流单号：" + dealerRefund.getTransNum();
            this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                    "【" + dealerRefund.getDealerName() + "】" + "提交了退货物流信息" + refundReason);
            // 新增订单操作记录
            this.insertDealerOrderAction("returnDeliver", "提交退货物流信息", "【" + dealerRefund.getDealerName() + "】" + "提交了退货物流信息" + refundReason, dealerRefund.getOrderId(),
                    dealerRefund.getDealerId(), dealerRefund.getDealerName());
        }
    }
    
    @Override
    public void updateDeliverFactory(DealerRefundModel dealerRefund, String dealerId)
    {
        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(dealerRefund.getRefrenceId()) && StringUtils.isNotBlank(dealerRefund.getLogisticsName())
                && StringUtils.isNotBlank(dealerRefund.getTransNum()))
        {
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", "brandConfirmGoods");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            Long nextActTime = CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount);
            dealerRefund.setNextActTime(nextActTime);
            dealerRefundMapper.returnDeliver(dealerRefund, dealerId);
            // 操作记录
            dealerRefund = dealerRefundMapper.getEntityById(dealerRefund.getRefrenceId());
            String refundReason = "，物流公司：" + dealerRefund.getLogisticsName() + "，物流单号：" + dealerRefund.getTransNum();
            this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(),
                    "【" + dealerRefund.getDealerName() + "】" + "提交了退货物流信息" + refundReason);
        }
    }
    
    @Override
    public void appserjoin(String refrenceId, String dealerId)
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        if (dealerRefund != null)
        {
            dealerRefundMapper.appserJoin(CalendarUtils.getCurrentDayLong(), refrenceId, dealerId);
            // 增加操作记录
            this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(), "【" + dealerRefund.getDealerName() + "】" + "申请了客服介入");
            // 新增订单操作记录
            this.insertDealerOrderAction("appserjoin", "申请客服介入", "【" + dealerRefund.getDealerName() + "】" + "申请了客服介入", dealerRefund.getOrderId(),
                    dealerRefund.getDealerId(), dealerRefund.getDealerName());
            // 修改订单客服介入状态
            DealerOrder dealerOrder = dealerOrderMapper.selectByPrimaryKey(dealerRefund.getOrderId());
            dealerOrder.setSerProStatus((short) 5);
            dealerOrderMapper.updateByPrimaryKey(dealerOrder);
        }
    }

    @Override
    public void appserjoinFactory(String refrenceId, String dealerId)
    {
        DealerRefundModel dealerRefund = dealerRefundMapper.getEntityById(refrenceId);
        if (dealerRefund != null)
        {
            dealerRefundMapper.appserJoin(CalendarUtils.getCurrentDayLong(), refrenceId, dealerId);
            // 增加操作记录
            this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerRefund.getDealerName(), "【" + dealerRefund.getDealerName() + "】" + "申请了客服介入");
            // 新增订单操作记录
            this.insertDealerOrderAction("appserjoin", "申请客服介入", "【" + dealerRefund.getDealerName() + "】" + "申请了客服介入", dealerRefund.getOrderId(),
                    dealerRefund.getDealerId(), dealerRefund.getDealerName());
        }
    }

    @Override
    public PaginateResult<DealerRefund> selectDealerRefund(DealerRefund dealerRefund, Pagination page) {
        if(null != dealerRefund && StringUtils.isNotBlank(dealerRefund.getDealerId()) && StringUtils.isNotBlank(dealerRefund.getBrandId()))
        {
            dealerRefund.setPage(page);
    
            List<DealerRefund> dealerRefundList =  dealerRefundMapper.selectDealerRefund(dealerRefund);
            PaginateResult result = new PaginateResult(page,dealerRefundList);
            return result;
        }
        return null;
    }

    @Override
    public Map<String, Object> selectRefundTypeList(String dealerId, String brandId) {
        if(StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(brandId))
        {
          List<Map<String,Object>> mapList =  dealerRefundMapper.selectRefundTypeList(dealerId, brandId);
            if(null !=mapList && !mapList.isEmpty())
            {
                Map<String,Object> resultMap = Maps.newHashMap();
                HashMap<String,String> needRefundMap = Maps.newHashMap();
                HashMap<String,String> refundStateMap = Maps.newHashMap();
                for (Map<String, Object> map : mapList)
                {
                    if(MapUtils.getBoolean(map, "needRefund", false))
                    {
                        needRefundMap.put("退款退货", "1");
                    }else{
                        needRefundMap.put("仅退款", "0");
                    }
                   switch (MapUtils.getIntValue(map,"refundState"))
                   {
                       case 1:refundStateMap.put("等待处理", "1"); continue;
                       case 2:refundStateMap.put("处理中", "23"); continue;
                       case 3:refundStateMap.put("处理中", "23"); continue;
                       case 4:refundStateMap.put("拒绝退款", "4"); continue;
                       case 5:refundStateMap.put("拒绝退货", "5"); continue;
                       case 6:refundStateMap.put("退款关闭", "6"); continue;
                       case 9:refundStateMap.put("处理完成", "109"); continue;
                       case 10:refundStateMap.put("处理完成", "109"); continue;
                       case 7:refundStateMap.put("已撤销", "7"); continue;
                   }
                }
                resultMap.put("needRefund",needRefundMap);
                resultMap.put("refundState",refundStateMap);
                return resultMap;
            }
            return null;
        }
        return null;
    }

    @Override
    public Map<String, Object> getDealerRefundInfoProcessing(String dealerId, String brandId) {
        if(StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(brandId))
        {
           return dealerRefundMapper.getDealerRefundInfoProcessing(dealerId,brandId);
        }
        return null;
    }
    
    @Override
    public void saveFactoryDealerRefund(DealerRefund dealerRefund, List<DealerRefAttacht> dealerRefAttachts, String dealerId, String brandId) throws BusinessException
    {
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
        if (dealerInfo == null) { throw new BusinessException(CommonConst.DEALER_INFO_NULL); }
        if (StringUtils.isBlank(dealerRefund.getRefrenceId()))
        {
            dealerRefund.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerRefund.setRefundId(com.zttx.web.utils.SerialnoUtils.buildRefundSN(refundNumberService.execute()));
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", dealerRefund.getNeedRefund() == true ? "brandConReturn"
                    : "brandConRefund");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            dealerRefund.setNextActTime(CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount));
            dealerRefund.setRefundState(DealerConstant.DealerRefund.WAIT_HANDLE);
            dealerRefund.setUpdateNum(1);
            dealerRefund.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerRefund.setFactoryStore(true);
            dealerRefund.setOrderId("");
            dealerRefund.setOrderNumber(0l);
            dealerRefund.setDealerId(dealerId);
            dealerRefund.setBrandId(brandId);
            dealerRefund.setTotalAmount(new BigDecimal(0));
            dealerRefund.setComPayment(new BigDecimal(0));
            dealerRefund.setReachStatus(Short.valueOf("0"));
            dealerRefund.setReachAmount(BigDecimal.ZERO);
            dealerRefund.setApplyTime(CalendarUtils.getCurrentLong());
            dealerRefund.setCreateTime(CalendarUtils.getCurrentLong());
            dealerRefundMapper.insertSelective(dealerRefund);
            dealerCountService.modifyDealerCount(dealerId, DEALERCOUNT_COLUMNS);
            brandCountService.modifyBrandCount(dealerRefund.getBrandId(), BRANDCOUNT_COLUMNS);
            // 操作记录
            String refundReason = "，退款原因：" + dealerRefund.getRefundReason() + ";退款说明: " + dealerRefund.getRefundMark() + ",申请退款金额：" + dealerRefund.getRefundAmount() + "元";
            DealerRefReply dealerRefReply = this.insertDealerRefReply(dealerRefund, dealerRefund.getDealerId(), dealerInfo.getDealerName(),
                    "【" + dealerInfo.getDealerName() + "】" + (dealerRefund.getNeedRefund() == true ? "申请了退款退货" + refundReason : "申请了退款" + refundReason));
            // 添加退款附件记录
            dealerRefAttachtService.insertList(dealerRefAttachts, dealerRefund.getRefrenceId(), dealerId, dealerInfo.getDealerName(), dealerRefReply.getRefrenceId());
            // 新增订单操作记录
            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.APPLYREFUND, DealerConstant.DealerOrderAction.APPLYREFUND_NAME, "【" + dealerInfo.getDealerName()
                    + "】" + (dealerRefund.getNeedRefund() == true ? "申请了退款退货" + refundReason : "申请了退款" + refundReason), dealerRefund.getOrderId(),
                    dealerRefund.getDealerId(), dealerInfo.getDealerName());
        }
        else
        {
            // 添加终端商退款信息记录
            // 增加品牌商退款处理时间
            DataDictValue dataDictValue = dataDictValueMapper.findByDictCodeAndDictValueName("RefundDate", dealerRefund.getNeedRefund() == true ? "brandConReturn"
                    : "brandConRefund");
            int amount = Integer.valueOf(dataDictValue.getDictValue());
            dealerRefund.setNextActTime(CalendarUtils.addDay(CalendarUtils.getCurrentLong(), amount));
            DealerRefund old = dealerRefundMapper.getByRefundIdAndBrandId(dealerRefund.getRefundId(), dealerRefund.getBrandId());
            if (null == dealerId || !dealerId.equals(old.getDealerId())) { throw new RuntimeException(); }
            old.setRefundState(DealerConstant.DealerRefund.WAIT_HANDLE);
            old.setUpdateNum(old.getUpdateNum() + 1);
            if (old.getUpdateNum() > 3) // TODO N 更新次数上限 暂定，后期走配置文件
            { throw new BusinessException("更新次数上限达到上限"); }
            old.setUpdateTime(CalendarUtils.getCurrentLong());
            old.setReceived(dealerRefund.getReceived());
            old.setRefundReason(dealerRefund.getRefundReason());
            old.setRefundAmount(dealerRefund.getRefundAmount());
            old.setRefundMark(dealerRefund.getRefundMark());
            old.setNextActTime(dealerRefund.getNextActTime());
            dealerRefundMapper.updateByPrimaryKeySelective(old);
            // 订单 是全部分货状态 同时未发生退款的订单 才可以
            // 更新 终端商订单DealerOrder 的 refundStatus 字段
            // 操作记录
            String refundReason = "，退款原因：" + old.getRefundReason() + ";退款说明: " + old.getRefundMark() + ",申请退款金额：" + old.getRefundAmount() + "元";
            DealerRefReply dealerRefReply = this.insertDealerRefReply(old, old.getDealerId(), dealerInfo.getDealerName(), "【" + dealerInfo.getDealerName() + "】"
                    + (old.getNeedRefund() == true ? "修改了退款退货申请" + refundReason : "修改了退款申请" + refundReason));
            // 添加退款附件记录
            dealerRefAttachtService.insertList(dealerRefAttachts, old.getRefrenceId(), dealerId, dealerInfo.getDealerName(), dealerRefReply.getRefrenceId());
            // 新增订单操作记录
            this.insertDealerOrderAction(DealerConstant.DealerOrderAction.APPLYREFUND, (old.getNeedRefund() == true ? "修改退款退货申请" : "修改退款申请"), "【" + dealerInfo.getDealerName()
                    + "】" + (old.getNeedRefund() == true ? "修改了退款退货申请" + refundReason : "修改了退款申请" + refundReason), "", old.getDealerId(), dealerInfo.getDealerName());
        }
    }
}
