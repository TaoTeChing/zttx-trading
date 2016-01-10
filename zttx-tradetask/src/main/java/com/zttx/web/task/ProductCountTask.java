package com.zttx.web.task;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.service.ProductCountService;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.search.solrj.ProductSolrHandler;
import com.zttx.web.utils.LoggerUtils;

/**
 * <p>File：ProductCountTask.java</p>
 * <p>Title: 产品统计调度任务</p>
 * <p>Description:每一个小统计收藏量和点击量变更过的产品,并更新产品索引</p>
 * <p>Copyright: Copyright (c) 2015-09-06 下午13:10:19</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class ProductCountTask extends AbstractQuartzBean
{
    private static final Logger        logger              = LoggerFactory.getLogger(ProductCountTask.class);
    
    private static ProductCountService productCountService = null;
    
    private static ProductInfoService  productInfoService  = null;
    
    private static ProductSolrHandler  productSolrHandler  = null;
    
    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行 ProductCountTask");
        try
        {
            initialize(getApplicationContext(jobexecutioncontext));
            processProductCounts();
        }
        catch (SchedulerException e)
        {
            LoggerUtils.logError(logger, "调度执行异常" + e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束执行 ProductCountTask");
    }
    
    /**
     * 处理产品统计信息
     */
    protected void processProductCounts()
    {
        try
        {
            List<String> mapList = productCountService.getProductCountMaps();
            List<ProductInfo> productInfoList = productInfoService.findProductToSolr(mapList);
            productSolrHandler.addProductInfoList(productInfoList);
        }
        catch (BusinessException e)
        {
            LoggerUtils.logError(logger, "处理产品统计信息异常" + e.getLocalizedMessage());
        }
    }
    
    /**
     * 初始化调度服务
     *
     * @param applicationContext
     */
    protected void initialize(ApplicationContext applicationContext)
    {
        if (null == productCountService) productCountService = applicationContext.getBean(ProductCountService.class);
        if (null == productInfoService) productInfoService = applicationContext.getBean(ProductInfoService.class);
        if (null == productSolrHandler) productSolrHandler = applicationContext.getBean(ProductSolrHandler.class);
    }
}
