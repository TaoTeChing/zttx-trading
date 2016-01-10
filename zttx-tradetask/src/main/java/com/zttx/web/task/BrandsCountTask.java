package com.zttx.web.task;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.brand.service.BrandsCountService;
import com.zttx.web.search.solrj.BrandeSolrHandler;
import com.zttx.web.utils.LoggerUtils;

/**
 * <p>File：BrandsCountTask.java </p>
 * <p>Title: BrandsCountTask </p>
 * <p>Description: 每一个小统计收藏量和点击量变更过的品牌,并更新品牌索引 </p>
 * <p>Copyright: Copyright (c) 十一月 12，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
public class BrandsCountTask extends AbstractQuartzBean
{
    private static final Logger       logger             = LoggerFactory.getLogger(BrandsCountTask.class);
    
    private static BrandsCountService brandsCountService = null;
    
    private static BrandeSolrHandler  brandeSolrHandler  = null;
    
    private static BrandesInfoService brandesInfoService = null;
    
    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行 BrandsCountTask");
        try
        {
            initialize(getApplicationContext(jobexecutioncontext));
            processBrandsCountTask();
        }
        catch (SchedulerException e)
        {
            LoggerUtils.logError(logger, "调度执行异常" + e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束执行 BrandsCountTask");
    }
    
    /**
     * 处理品牌统计信息
     */
    protected void processBrandsCountTask()
    {
        try
        {
            List<String> brandesIdList = brandsCountService.getBrandsCountUpdatedIn(3600000L);
            List<BrandesInfo> biList = brandesInfoService.findBrandesInfoToSolr(brandesIdList);
            brandeSolrHandler.addBrandsInfoList(biList);
        }
        catch (Exception e)
        {
            LoggerUtils.logError(logger, "处理品牌统计信息异常" + e.getLocalizedMessage());
        }
    }
    
    /**
     * 初始化调度服务
     *
     * @param applicationContext 上下文
     */
    protected void initialize(ApplicationContext applicationContext)
    {
        if (null == brandsCountService) brandsCountService = applicationContext.getBean(BrandsCountService.class);
        if (null == brandeSolrHandler) brandeSolrHandler = applicationContext.getBean(BrandeSolrHandler.class);
        if (null == brandesInfoService) brandesInfoService = applicationContext.getBean(BrandesInfoService.class);
    }
}
