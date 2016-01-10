/*
 * @(#)WebInfoCountTask.java 2014-9-11 下午1:27:19
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.task;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.brand.service.BrandsCountService;
import com.zttx.web.module.common.service.ProductCountService;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.module.common.service.ProductSaleCountService;
import com.zttx.web.module.dealer.service.DealerCountService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.utils.LoggerUtils;

/**
 * <p>File：WebInfoCountTask.java</p>
 * <p>Title: 交易平台品牌商统计、品牌统计、终端商统计、产品统计</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015-09-06 下午13:10:19</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class WebInfoCountTask extends AbstractQuartzBean implements Serializable
{
    private static final long          serialVersionUID    = 1L;
    
    private static final Logger        logger              = LoggerFactory.getLogger(WebInfoCountTask.class);
    
    /**
     * 品牌商服务
     */
    private static BrandInfoService    brandInfoService    = null;
    
    /**
     * 品牌服务
     */
    private static BrandesInfoService  brandesInfoService  = null;
    
    /**
     * 产品服务
     */
    private static ProductInfoService  productInfoService  = null;
    
    /**
    * 终端商服务
    */
    private static DealerInfoService   dealerInfoService   = null;
    
    /**
     * 品牌商统计服务
     */
    private static BrandCountService   brandCountService   = null;
    
    /**
     * 终端商统计服务
     */
    private static DealerCountService  dealerCountService  = null;
    
    /**
     * 品牌统计服务
     */
    private static BrandsCountService  brandsCountService  = null;
    
    /**
     * 产品统计服务
     */
    private static ProductCountService productCountService = null;
    
    @Override
    protected void executeInternal(JobExecutionContext jobexecutioncontext) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行 WebInfoCountTask");
        try
        {
            initialize(getApplicationContext(jobexecutioncontext));
            processBrandCount();
            processBrandsCount();
            processProductCount();
            processDealerCount();
        }
        catch (SchedulerException e)
        {
            LoggerUtils.logError(logger, "调度执行异常" + e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束执行 WebInfoCountTask");
    }
    
    /**
     * 处理品牌商统计信息
     */
    protected void processBrandCount()
    {
        try
        {
            LoggerUtils.logDebug(logger, "开始处理品牌商统计信息");
            List<Map<String, Object>> brandInfos = brandInfoService.findAllBrandBaseInfo();
            Integer[] countType = new Integer[]{BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT, // 合作中的经销商
                    BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT, // 申请中的经销商
                    BrandConstant.BrandCountConst.BRANDCOUNT_INVITECOUNT, // 邀请中的经销商
                    BrandConstant.BrandCountConst.BRANDCOUNT_WAITPAYCOUNT, // 等待付款订单数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_PREORDERCOUNT, // 预订产品订单数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_WAITSENDCOUNT, // 待发货订单数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_WAITCONFIRECOUNT, // 已发货订单数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_REFUNDCOUNT, // 退款订单数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT, // 已铺货产品数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT, // 紧张库存产品数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT, // 库存缺货产品数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT, // 预订铺货产品数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_VIEWDEALERCOUNT, // 查看经销商联系信息数量
                    BrandConstant.BrandCountConst.BRANDCOUNT_BRANDSCOUNT // 品牌数量
            };
            for (Map<String, Object> brandInfo : brandInfos)
            {
                brandCountService.modifyBrandCount(MapUtils.getString(brandInfo, "refrenceId"), countType);
            }
        }
        catch (BusinessException e)
        {
            LoggerUtils.logError(logger, "处理品牌商统计信息出错：", e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束处理品牌商统计信息");
    }
    
    /**
     * 处理品牌统计信息
     */
    protected void processBrandsCount()
    {
        LoggerUtils.logInfo(logger, "开始处理品牌统计信息");
        try
        {
            List<Map<String, Object>> brandInfos = brandesInfoService.findAllBrandsBaseInfo();
            String[] countTypeName = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT, // 合作中的经销商
                    BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT, // 申请中的经销商
                    BrandConstant.BrandsCountConst.BRANDSCOUNT_INVITECOUNT,// 邀请中的经销商
                    BrandConstant.BrandsCountConst.BRANDSCOUNT_ORDERCOUNT,// 发货订单数量
                    BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT,// 产品数量
                    BrandConstant.BrandsCountConst.BRANDSCOUNT_FAVNUM // 收藏数量
            };
            for (Map<String, Object> brandInfo : brandInfos)
            {
                brandsCountService.modifyBrandsCount(MapUtils.getString(brandInfo, "refrenceId"), countTypeName);
            }
        }
        catch (BusinessException e)
        {
            LoggerUtils.logError(logger, "处理品牌统计信息出错：", e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束处理品牌统计信息");
    }
    
    /**
     * 处理产品统计信息
     */
    protected void processProductCount()
    {
        try
        {
            LoggerUtils.logInfo(logger, "开始处理产品统计信息");
            List<Map<String, Object>> products = productInfoService.findAllProductBaseInfo();
            for (Map<String, Object> info : products)
            {
                productCountService.modifyProductCount(MapUtils.getString(info, "brandId"), MapUtils.getString(info, "brandsId"), MapUtils.getString(info, "refrenceId"));
            }
        }
        catch (BusinessException e)
        {
            LoggerUtils.logError(logger, "处理产品统计信息出错：", e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束处理产品统计信息");
    }
    
    /**
     * 处理终端商统计信息
     */
    protected void processDealerCount()
    {
        LoggerUtils.logInfo(logger, "开始处理终端商统计信息");
        try
        {
            List<Map<String, Object>> products = dealerInfoService.findAllDealerBaseInfo();
            Integer[] countType = new Integer[]{DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT, // 已经加盟的品牌数
                    DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT,// 申请中的品牌数
                    DealerConstant.DealerCount.DEALERCOUNT_COLUMN_INVITECOUNT, // 邀请中的品牌数
                    DealerConstant.DealerCount.DEALERCOUNT_COLUMN_BALANCECOUNT,// 待付款订单数量
                    DealerConstant.DealerCount.DEALERCOUNT_COLUMN_WAITCONFIREMCOUNT, // 待收货订单数量
                    DealerConstant.DealerCount.DEALERCOUNT_COLUMN_REFUNDCOUNT, // 退款中的订单数
                    DealerConstant.DealerCount.DEALERCOUNT_COLUMN_SYSMESSAGECOUNT, // 未读系统消息
            };
            for (Map<String, Object> info : products)
            {
                dealerCountService.modifyDealerCount(MapUtils.getString(info, "refrenceId"), countType);
            }
        }
        catch (BusinessException e)
        {
            LoggerUtils.logError(logger, "处理终端商统计信息出错：", e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束处理终端商统计信息");
    }
    
    /**
     * 初始化调度服务
     *
     * @param applicationContext
     */
    protected void initialize(ApplicationContext applicationContext)
    {
        if (null == brandInfoService) brandInfoService = applicationContext.getBean(BrandInfoService.class);
        if (null == brandesInfoService) brandesInfoService = applicationContext.getBean(BrandesInfoService.class);
        if (null == productInfoService) productInfoService = applicationContext.getBean(ProductInfoService.class);
        if (null == dealerInfoService) dealerInfoService = applicationContext.getBean(DealerInfoService.class);
        if (null == brandCountService) brandCountService = applicationContext.getBean(BrandCountService.class);
        if (null == dealerCountService) dealerCountService = applicationContext.getBean(DealerCountService.class);
        if (null == brandsCountService) brandsCountService = applicationContext.getBean(BrandsCountService.class);
        if (null == productCountService) productCountService = applicationContext.getBean(ProductCountService.class);
    }
}
