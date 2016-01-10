/*
 * @(#)SellDaySynTask.java 2015-3-27 下午1:33:01
 * Copyright 2015 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.task;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.dubbo.service.SellOrderServiceDubboConsumer;
import com.zttx.web.module.dealer.entity.DealerClearing;
import com.zttx.web.module.dealer.service.DealerClearingService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.LoggerUtils;
import com.zttx.web.utils.PageModel;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>File：SellErpDaySynTask.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-3-27 下午1:33:01</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class SellErpDaySynTask extends AbstractQuartzBean implements Serializable
{
    private final static Logger logger = LoggerFactory.getLogger(SellErpDaySynTask.class);
    
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        LoggerUtils.logInfo(logger, "开始执行SellDaySynTask");
        try
        {
            ApplicationContext applicationContext = getApplicationContext(jobExecutionContext);
            DealerClearingService dealerClearingService = applicationContext.getBean(DealerClearingService.class);
            SellOrderServiceDubboConsumer sellOrderDubboService = applicationContext.getBean(SellOrderServiceDubboConsumer.class);
            try
            {
                Pagination pagination = new Pagination(1, 100);
                boolean isFirst = true;
                List<String> sellDayIdList = Lists.newArrayList();
                while (isFirst || pagination.getHasNextPage())
                {
                    List<DealerClearing> listDealerClearing = Lists.newArrayList();
                    isFirst = false;
                    PaginateResult<Map<String, Object>> result = sellOrderDubboService.getBrandSellDay(pagination);
                    if (null != result)
                    {
                        for (Map<String, Object> map : result.getList())
                        {
                            DealerClearing dealerClearing = new DealerClearing();
                            String dealerId = map.get("dealerId") == null ? "" : map.get("dealerId").toString();
                            String brandId = map.get("supplierId") == null ? "" : map.get("supplierId").toString();
                            String brandsId = MapUtils.getString(map, "brandId");
                            if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(brandId) && null != map.get("dayTime"))
                            {
                                // 判断是否已经同步过数据
                                dealerClearing.setDealerId(dealerId);
                                dealerClearing.setBrandId(brandId);
                                dealerClearing.setBrandsId(brandsId);
                                dealerClearing.setClearingTime(Long.parseLong(map.get("dayTime").toString()));
                                DealerClearing oldObj = dealerClearingService.selectDealerClearingSelective(dealerClearing);
                                if (null == oldObj)
                                {
                                    dealerClearing = dealerClearingService.create(dealerId, brandId, brandsId, Long.parseLong(map.get("dayTime").toString()), map);
                                }
                                else
                                {
                                    sellDayIdList.add(map.get("sellDayId").toString());
                                }
                                if (null != dealerClearing)
                                {
                                    listDealerClearing.add(dealerClearing);
                                    sellDayIdList.add(map.get("sellDayId").toString());
                                }
                            }
                        }
                    }
                    if (null != listDealerClearing && !listDealerClearing.isEmpty())
                    {
                        dealerClearingService.insertBatch(listDealerClearing);
                    }
                    pagination = result.getPage();
                    pagination.setCurrentPage(pagination.getCurrentPage() + 1);
                }
                if (null != sellDayIdList && !sellDayIdList.isEmpty())
                {
                    PageModel<String> pageMode = new PageModel<String>(sellDayIdList, 1000);
                    for (int page = 1; page <= pageMode.getTotalPages(); page++)
                    {
                        sellOrderDubboService.setBrandSellDaySuccess(pageMode.getObjects(page));
                    }
                }
            }
            catch (BusinessException e)
            {
                LoggerUtils.logError(logger, e.getLocalizedMessage());
            }
            LoggerUtils.logInfo(logger, "结束同步指定日期ERP销售数据");
        }
        catch (SchedulerException e)
        {
            LoggerUtils.logError(logger, "调度执行异常" + e.getLocalizedMessage());
        }
        LoggerUtils.logInfo(logger, "结束执行SellDaySynTask");
    }
}
