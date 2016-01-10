package com.zttx.web.task;

import java.io.Serializable;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zttx.pay.remoting.api.PayOrderApi;
import com.zttx.pay.remoting.model.PayOrderDetails;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.common.model.OrderPayCallbackModel;
import com.zttx.web.module.common.service.OrderPayCallbackService;
import com.zttx.web.module.dealer.entity.DealerOrderPay;
import com.zttx.web.module.dealer.service.DealerOrderPayService;
import com.zttx.web.utils.LoggerUtils;

/**
 * <p>File：BrandCloseOrderTask.java</p>
 * <p>Title: 同步 结算平台订单状态 到交易平台 </p>
 * <p>Description:   </p>
 * <p>Copyright: Copyright (c) 2015 2015-9-14 下午16:18:00</p>
 * <p>Company: 8637.com</p>
 * @author 李星
 * @version 1.0
 */
public class CheckOrderPayTask extends AbstractQuartzBean implements Serializable
{
    private final static Logger            logger                  = LoggerFactory.getLogger(CheckOrderPayTask.class);
    
    private static OrderPayCallbackService orderPayCallbackService = null;
    
    private static DealerOrderPayService   dealerOrderPayService   = null;
    
    private static PayOrderApi             payOrderApi             = null;
    
    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行同步 结算平台订单状态 调度");
        try
        {
            initialize(getApplicationContext(jobexecutioncontext));
            List<DealerOrderPay> dealerOrderPayList = dealerOrderPayService.findAllUnPay();
            if (dealerOrderPayList != null && dealerOrderPayList.size() > 0)
            {
                for (DealerOrderPay item : dealerOrderPayList)
                {
                    try
                    {
                        PayOrderDetails payOrderDetails = payOrderApi.queryOrder(item.getPayId(), CommonConstant.OrderPay.PAY_MERCHANT_ID);
                        if (payOrderDetails != null)
                        {
                            LoggerUtils.logInfo(logger, "订单ID:" + item.getOrderId() + " 订单号:" + item.getOrderNo());
                            OrderPayCallbackModel orderPayCallbackModel = new OrderPayCallbackModel();
                            orderPayCallbackModel.setState(payOrderDetails.getTranState());
                            orderPayCallbackModel.setAmount(payOrderDetails.getAmount());
                            orderPayCallbackModel.setId(payOrderDetails.getId());
                            this.dealWith(orderPayCallbackModel, orderPayCallbackService);
                        }
                    }
                    catch (Exception e)
                    {
                        LoggerUtils.logError(logger, " 订单ID:" + item.getOrderId()+"与结算同步状态异常,"+e.getLocalizedMessage());
                    }
                }
            }
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束同步 结算平台订单状态 调度");
    }
    
    void dealWith(OrderPayCallbackModel model, OrderPayCallbackService orderPayCallbackService) throws BusinessException
    {
        if (null != model.getId() && null != model.getState() && null != model.getAmount())
        {
            orderPayCallbackService.executeDealWith(model.getId(), model.getState(), model.getAmount());
        }
    }
    
    public static void initialize(ApplicationContext applicationContext)
    {
        if (null == orderPayCallbackService) orderPayCallbackService = applicationContext.getBean(OrderPayCallbackService.class);
        if (null == dealerOrderPayService) dealerOrderPayService = applicationContext.getBean(DealerOrderPayService.class);
        if (null == payOrderApi) payOrderApi = applicationContext.getBean(PayOrderApi.class);
    }
}
