package com.zttx.web.task;

import java.io.Serializable;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import com.zttx.web.module.dealer.service.DealerRefundService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.LoggerUtils;

/**
 * 经销商退款信息调度器
 * 本调度器包括：申请退货，等待品牌商确认、品牌商确认退款超时、 经销商填写物流信息超时三大调度任务
 * Created by stone on 2014/12/29.
 *
 * @author 王仕貴
 */
public class DealerRefundTask extends AbstractQuartzBean implements Serializable
{
    private static final long   serialVersionUID = -1283188935933182259L;
    
    private static final Logger logger           = LoggerFactory.getLogger(DealerRefundTask.class);
    
    /**
     * 经销商退款服务
     */
    private DealerRefundService dealerRefundService;
    
    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行经销商退款信息调度");
        ApplicationContext applicationContext = null;
        try
        {
            Long preformTime = CalendarUtils.getCurrentLong();
            Pagination page = new Pagination(100);
            applicationContext = super.getApplicationContext(jobexecutioncontext);
            if (dealerRefundService == null)
            {
                dealerRefundService = applicationContext.getBean(DealerRefundService.class);
            }
            // 退款状态:
            // 1：申请退款等待处理
            // 2：同意退货等待发货
            // 3：退货已发货
            // 4：拒绝退款
            // 5：拒绝退货
            // 6：退款关闭
            // 7: 取消退款
            // 9：同意退款
            // 10：同意退货退款
            DealerRefund dealerRefund = new DealerRefund();
            dealerRefund.setNextActTime(preformTime);
            PaginateResult<DealerRefundModel> result = dealerRefundService.getDealerRefund(preformTime, page);
            if (result != null && result.getList() != null)
            {
                List<DealerRefundModel> updateAutoAgreeReturnBoths = Lists.newArrayList();
                List<DealerRefundModel> updateLogisTimeoutTasks = Lists.newArrayList();
                List<DealerRefundModel> updateAutoAgreeReturns = Lists.newArrayList();
                for (DealerRefundModel refund : result.getList())
                {
                    if (refund.getRefundState().shortValue() == 1) updateAutoAgreeReturnBoths.add(refund);
                    else if (refund.getRefundState().shortValue() == 2) updateLogisTimeoutTasks.add(refund);
                    else updateAutoAgreeReturns.add(refund);
                }
                this.updateAutoAgreeReturnBoth(updateAutoAgreeReturnBoths, Boolean.TRUE);
                this.updateLogisTimeoutTask(updateLogisTimeoutTasks);
                this.updateAutoAgreeReturn(updateAutoAgreeReturns);
            }
        }
        catch (SchedulerException e)
        {
            LoggerUtils.logError(logger, "调度执行异常", e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "经销商退款信息调度执行结束");
    }
    
    /**
     * 申请退货，等待品牌商确认超时调度
     * <p>
     * 将服务层的FOR循环提取出来，规避大事务造成的性能问题
     * </p>
     *
     * @param dealerRefundList
     * @param isSystemJob
     * @author 王仕貴
     */
    private void updateAutoAgreeReturnBoth(List<DealerRefundModel> dealerRefundList, Boolean isSystemJob)
    {
        for (DealerRefundModel dealerRefund : dealerRefundList)
        {
            try
            {
                if (dealerRefund.getNeedRefund()) dealerRefundService.updateAutoAgreeReturnBoth(dealerRefund, isSystemJob);
            }
            catch (Exception e)
            {
                LoggerUtils.logError(logger, "退款单：" + dealerRefund.getRefrenceId() + "，申请退货，等待品牌商确认超时", e.getLocalizedMessage());
            }
        }
    }
    
    /**
     * 经销商填写物流信息超时调度
     * <p>
     * 将服务层的FOR循环提取出来，规避大事务造成的性能问题
     * </p>
     *
     * @param dealerRefunds
     * @author 王仕貴
     */
    private void updateLogisTimeoutTask(List<DealerRefundModel> dealerRefunds)
    {
        for (DealerRefundModel dealerRefund : dealerRefunds)
        {
            try
            {
                dealerRefundService.updateLogisTimeoutTask(dealerRefund);
            }
            catch (Exception e)
            {
                LoggerUtils.logError(logger, "退款单：" + dealerRefund.getRefrenceId() + "，经销商填写物流信息超时调度执行失败", e.getLocalizedMessage());
            }
        }
    }
    
    /**
     * 品牌商确认退款超时退款调度
     * <p>
     * 将服务层的FOR循环提取出来，规避大事务造成的性能问题
     * </p>
     *
     * @param dealerRefunds
     * @throws BusinessException
     * @author 王仕貴
     */
    private void updateAutoAgreeReturn(List<DealerRefundModel> dealerRefunds)
    {
        for (DealerRefundModel dealerRefund : dealerRefunds)
        {
            try
            {
                dealerRefundService.updateAutoAgreeReturn(dealerRefund);
            }
            catch (Exception e)
            {
                LoggerUtils.logError(logger, "退款单：" + dealerRefund.getRefrenceId() + "，品牌商确认退款超时退款调度执行失败", e.getLocalizedMessage());
            }
        }
    }
}
