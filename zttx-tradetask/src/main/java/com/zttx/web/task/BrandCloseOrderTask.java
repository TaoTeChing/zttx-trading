package com.zttx.web.task;

import java.io.Serializable;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.UserOperationLogConst;
import com.zttx.web.module.common.entity.UserOperationLog;
import com.zttx.web.module.common.service.UserOperationLogService;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.model.DealerOrderActionModel;
import com.zttx.web.module.dealer.service.DealerOrderActionService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.LoggerUtils;

/**
 * <p>File：BrandCloseOrderTask.java</p>
 * <p>Title: 经销商付款超时，品牌商自动关闭订单 </p>
 * <p>Description:   </p>
 * <p>Copyright: Copyright (c) 2014 2014-5-23 下午16:58:46</p>
 * <p>Company: 8637.com</p>
 * @author 李星
 * @version 1.0
 */
public class BrandCloseOrderTask extends AbstractQuartzBean implements Serializable
{
    private static final long               serialVersionUID         = 7569016730339611080L;
    
    private static final Logger             logger                   = LoggerFactory.getLogger(BrandCloseOrderTask.class);
    
    /**
     * 订单服务
     */
    private static DealerOrderService       dealerOrderService       = null;
    
    private static DealerOrderActionService dealerOrderActionService = null;
    
    private static UserOperationLogService  userOperationLogService  = null;
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行订单自动关闭任务");
        try
        {
            initialize(getApplicationContext(context));
            processCloseDealerOrder();
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, "执行订单自动关闭任务失败：", e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束执行订单自动关闭任务");
    }
    
    protected void processCloseDealerOrder()
    {
        Long outTime = CalendarUtils.getCurrentLong();
        Long rowNums = dealerOrderService.countByOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY, outTime);
        int pageSize = 1000;
        Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
        Pagination page = new Pagination();
        page.setPageSize(pageSize);
        LoggerUtils.logInfo(logger, "订单自动关闭任务待处理记录数:" + rowNums + ",每批次处理" + pageSize + "条,共" + pageCounts + "批次");
        for (Long currentPage = 0l; currentPage < pageCounts; currentPage++)
        {
            LoggerUtils.logInfo(logger, "订单自动关闭任务处理第:" + currentPage + "批");
            page.setCurrentPage(1);
            PaginateResult<DealerOrder> orderList = dealerOrderService.getListByOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_PAY, outTime, page);
            if (null != orderList && null != orderList.getList() && !orderList.getList().isEmpty())
            {
                for (DealerOrder dealerOrder : orderList.getList())
                {
                    try
                    {
                        DealerOrderActionModel dealerOrderAction = new DealerOrderActionModel();
                        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                        dealerOrderAction.setOrderId(dealerOrder.getRefrenceId());
                        dealerOrderAction.setUserId(dealerOrder.getBrandId());
                        dealerOrderAction.setUserName("系统");
                        dealerOrderAction.setActCode(DealerConstant.DealerOrderAction.CLOSE);
                        dealerOrderAction.setActContent(DealerConstant.DealerOrderAction.CLOSE_TXT + "终端商付款超时，系统自动关闭。");
                        dealerOrderAction.setActName(DealerConstant.DealerOrderAction.CLOSE_NAME);
                        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
                        dealerOrderAction.setCode(7);
                        dealerOrderActionService.saveCloseOrder(dealerOrderAction, "system");
                        buildUserOperationLog(dealerOrderAction);
                    }
                    catch (Exception e)
                    {
                        LoggerUtils.logError(logger, new StringBuffer("订单自动关闭任务-订单:").append(dealerOrder.getRefrenceId()).append(",终端商付款超时，系统自动关闭错误。").toString(),
                                e.getLocalizedMessage());
                    }
                }
            }
            page.setCurrentPage(currentPage.intValue());
        }
    }
    
    /**
     * @param dealerOrderAction
     */
    private void buildUserOperationLog(DealerOrderActionModel dealerOrderAction)
    {
        UserOperationLog userOperationLog = new UserOperationLog();
        userOperationLog.setObjectId(dealerOrderAction.getOrderId());
        userOperationLog.setUserName(UserOperationLogConst.TYPE_USERNAME_TASK);
        userOperationLog.setType(UserOperationLogConst.TYPE_DEALERORDER_PRICE);
        userOperationLog.setEvent(dealerOrderAction.getActContent());
        userOperationLogService.addUserOperationLog(null, userOperationLog);
    }
    
    /**
     * 初始化调度服务
     *
     * @param applicationContext
     */
    protected void initialize(ApplicationContext applicationContext)
    {
        if (null == dealerOrderService) dealerOrderService = applicationContext.getBean(DealerOrderService.class);
        if (null == dealerOrderActionService) dealerOrderActionService = applicationContext.getBean(DealerOrderActionService.class);
        if (null == userOperationLogService) userOperationLogService = applicationContext.getBean(UserOperationLogService.class);
    }
}
