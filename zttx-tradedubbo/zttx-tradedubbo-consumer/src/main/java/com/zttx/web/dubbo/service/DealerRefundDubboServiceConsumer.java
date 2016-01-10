/*
 * @(#)DealerRefundDubboServiceConsumer.java 2015-11-26 上午9:33:32
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.erp.module.dubbo.service.provider.DealerRefundDubboService;
import com.zttx.erp.module.refund.model.RefundChangeModel;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：DealerRefundDubboServiceConsumer.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-11-26 上午9:33:32</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Component
public class DealerRefundDubboServiceConsumer
{
    public static final Logger    logger = LoggerFactory.getLogger(DealerRefundDubboServiceConsumer.class);
   
    @Autowired
    private DealerRefundDubboService  dealerRefundDubboService ;
    /**
     * 返点--增加接收交易平台推送修改经销商返点信息的dubbo接口（所有参数都不能为空）
     * 参数：productId、List<String> dealerIds、返点价、返点比例、startDate(生效时间Long(精确到天))
     * @param productId
     * @param zttxDealerIds
     * @param skuPriceMap  返点价 map key:skuId value:refundPrice(返点价)
     * @param refundPercent 返点比例
     * @param startDate 生效时间Long(精确到天))
     */
    public void createRefundChange(String productId,List<String> zttxDealerIds,
                            Map<String,Double> skuPriceMap,Double refundPercent,Long startDate )throws BusinessException{
        try
        {
            dealerRefundDubboService.createRefundChange(productId,zttxDealerIds,skuPriceMap,refundPercent,startDate);
        }
        catch (BusinessException e)
        {
            logger.error("createRefundChange error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }

    /**
     * 返点--增加接收交易平台取消商品返点的dubbo接口
     * 将表DealerRefundInfoChanges(经销商返点信息变动表)中对应的未生效的返点商品（productId）做标记删除处理。
     * @param productId
     */
    public void removeRefundChange(String productId)throws BusinessException{
        try
        {
            dealerRefundDubboService.removeRefundChange(productId);
        }
        catch (BusinessException e)
        {
            logger.error("removeRefundChange error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }
    
    /**
     * 取消经销商产品返点
     * @param zttxDealerId
     * @param productIds
     * @throws BusinessException
     */
    public void removeRefundChange(String zttxDealerId,Set<String> productIds)throws BusinessException{
        try
        {
            dealerRefundDubboService.removeRefundChange(zttxDealerId,productIds);
        }
        catch (BusinessException e)
        {
            logger.error("removeRefundChange error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }

    /**
     * 返点--增加接收交易平台推送修改经销商返点信息的dubbo接口（所有参数都不能为空）
     * @param changeModelList
     * 参数：productId、List<String> dealerIds、返点价、返点比例、startDate(生效时间Long(精确到天))
     *   productId
     *   zttxDealerIds
     *   skuPriceMap  返点价 map key:skuId value:refundPrice(返点价)
     *   refundPercent 返点比例
     *   startDate 生效时间Long(精确到天))
     */
    public void createRefundChange(List<RefundChangeModel> changeModelList) throws BusinessException{
        try
        {
            dealerRefundDubboService.createRefundChange(changeModelList);
        }
        catch (BusinessException e)
        {
            logger.error("createRefundChange error！", e.getLocalizedMessage());
            throw new BusinessException(e.getLocalizedMessage());
        }
    }

}
