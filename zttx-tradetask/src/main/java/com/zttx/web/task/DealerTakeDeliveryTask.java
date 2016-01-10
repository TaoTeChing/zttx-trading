/*
 * @(#)ConfirmTakeDeliveryTask.java 2014-6-21 上午8:41:19
 * Copyright 2014 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.task;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.LoggerUtils;

/**
 * <p>File：ConfirmTakeDeliveryTask.java</p>
 * <p>Title: 品牌商全部发货后 经销商逾期未确认,系统自动确认收货</p>
 * <p>Title: 终端商付清全部款项后，品牌商逾期未发货订单自动退款</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-6-21 上午8:41:19</p>
 * <p>Company: 8637.com</p>
 *
 * @author 郭小亮
 * @version 1.0
 */
public class DealerTakeDeliveryTask extends AbstractQuartzBean
{
    private static final long         serialVersionUID   = 4303590274027195272L;
    
    private static final Logger       logger             = LoggerFactory.getLogger(DealerTakeDeliveryTask.class);
    
    private static DealerOrderService dealerOrderService = null;
    
    private static DealerOrderMapper  dealerOrderMapper  = null;
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行自动确认收货和自动退款");
        try
        {
            // 初始化
            initialize(getApplicationContext(context));
            // 品牌商全部发货后 经销商逾期未确认,系统自动确认收货
            autoTakeDeliveryForDealer(CalendarUtils.getCurrentLong());
            // 终端商付清全部款项后，品牌商逾期未发货订单自动退款
            //autoRefundForBrand(CalendarUtils.getCurrentLong());
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, "自动确认收货和自动退款调度执行异常" + e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束执行自动确认收货和自动退款");
    }
    
    /**
     * 终端商付清全部款项后，品牌商逾期未发货订单自动退款
     * @param deliveryTime
     */
    private void autoRefundForBrand(Long deliveryTime)
    {
        LoggerUtils.logInfo(logger, "开始品牌商逾期未发货订单自动退款调度任务");
        DealerOrderModel params = new DealerOrderModel();
        try
        {
            params.setOrderStatusStr(String.valueOf(DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER));
            params.setOutActTime(deliveryTime);
            params.setPayState(DealerConstant.DealerOrder.PAY_STATE_ALL);
            Long rowNums = null;
            rowNums = dealerOrderMapper.getTaskDealerOrderCount(params);
            int pageSize = 100;
            Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
            LoggerUtils.logInfo(logger, "品牌商逾期未发货订单自动退款待处理记录数:" + rowNums + ",每批次处理" + pageSize + "条,共" + pageCounts + "批次");
            Pagination page = new Pagination(1, pageSize);
            // 对此赋值有疑问 请 参考DealerOrderDaoImpl.getSqlStr方法
            params.setOrderStatusStr("8");
            for (Long currentPage = 0l; currentPage < pageCounts; currentPage++)
            {
                LoggerUtils.logDebug(logger, "品牌商逾期未发货订单自动退款处理第:" + currentPage + "批");
                params.setDealerOrderType(DealerConstant.DealerOrder.ORDER_TYPE_CASH);
                PaginateResult<Map<String, Object>> result = dealerOrderService.getTaskDealerOrderList(page, params);
                if (result != null && result.getList() != null)
                {
                    List<Map<String, Object>> piList = result.getList();
                    for (Map<String, Object> mp : piList)
                    {
                        dealerOrderService.confirmRefund(String.valueOf(mp.get("refrenceId")));
                    }
                }
                page.setCurrentPage(currentPage.intValue());
            }
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, "调度品牌商逾期未发货订单自动退款自动退款执行异常" + e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束品牌商逾期未发货订单自动退款调度任务");
    }
    
    /**
     * 品牌商全部发货后 经销商逾期未确认,系统自动确认收货
     * @param deliveryTime
     */
    private void autoTakeDeliveryForDealer(Long deliveryTime)
    {
        LoggerUtils.logInfo(logger, "开始经销商逾期未确认,系统自动确认收货调度任务");
        DealerOrderModel params = new DealerOrderModel();
        
        params.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE); // 等待收货状态
        params.setOrderStatusStr("3");// 等待收货状态对应的类型值，注意该值不等于数据库里的值.
        params.setOutActTime(deliveryTime);
        params.setDealerOrderType(DealerConstant.DealerOrder.ORDER_TYPE_CASH);
        Long rowNums = dealerOrderMapper.getDealerOrderCount(params);
        int pageSize = 100;
        Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
        LoggerUtils.logInfo(logger, " 经销商逾期未确认,系统自动确认收货待处理记录数:" + rowNums + ",每批次处理" + pageSize + "条,共" + pageCounts + "批次");
        Pagination page = new Pagination(1, pageSize);
        for (Long currentPage = 0l; currentPage < pageCounts; currentPage++)
        {
            LoggerUtils.logInfo(logger, "经销商逾期未确认,系统自动确认收货处理第:" + currentPage + "批");
            PaginateResult<Map<String, Object>> result = dealerOrderService.getDealerOrderList(page, params);
            if (result != null && result.getList() != null)
            {
                List<Map<String, Object>> piList = result.getList();
                for (Map<String, Object> pi : piList)
                {
                    //Object obj = pi.get("refundStatus");
                    String orderId = String.valueOf(pi.get("refrenceId"));
                    String dealerId = String.valueOf(pi.get("dealerId"));
                    if (StringUtils.isNotBlank(orderId) && StringUtils.isNotBlank(dealerId))
                    {
                    	try
                        {
                    		dealerOrderService.confirmReceive(orderId, dealerId, null);
                        }
                    	catch (Exception e)
                        {
                            LoggerUtils.logError(logger, "调度经销商逾期未确认,系统自动确认收货执行异常：" + e.getLocalizedMessage());
                        }
                    }
                }
            }
            page.setCurrentPage(currentPage.intValue());
        }
        LoggerUtils.logInfo(logger, "结束经销商逾期未确认,系统自动确认收货调度任务");
    }
    
    /**
     * 初始化调度服务
     *
     * @param applicationContext
     */
    protected void initialize(ApplicationContext applicationContext)
    {
        if (null == dealerOrderService) dealerOrderService = applicationContext.getBean(DealerOrderService.class);
        if (null == dealerOrderMapper) dealerOrderMapper = applicationContext.getBean(DealerOrderMapper.class);
    }
}
