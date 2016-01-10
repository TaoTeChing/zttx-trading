/*
 * @(#)ProductCostPriceDubboConsumer.java 2015-9-16 下午4:31:00
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zttx.erp.module.dubbo.model.SkuCostPriceModel;
import com.zttx.erp.module.dubbo.service.provider.ProductCostPriceDubboService;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.consts.CommonConst;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：ProductCostPriceDubboConsumer.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-16 下午4:31:00</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Component
public class ProductCostPriceDubboConsumer
{
    @Autowired(required = false)
    private ProductCostPriceDubboService productCostPriceDubboService;
    
    public void pushCostPriceChanges(List<ProductSku> skuList) throws BusinessException
    {
        Set<SkuCostPriceModel> model = new HashSet<SkuCostPriceModel>();
        try
        {
            for (int i = 0; i < skuList.size(); i++)
            {
                SkuCostPriceModel skucost = new SkuCostPriceModel();
                skucost.setSkuId(Long.parseLong(skuList.get(i).getRefrenceId()));
                skucost.setCostPrice(skuList.get(i).getProductSkuPrice().getCreditPrice().doubleValue());
                model.add(skucost);
            }
            productCostPriceDubboService.saveCostPriceChanges(model);
        }
        catch (BusinessException e)
        {
            throw new BusinessException(CommonConst.FAIL);
        }
    }
}
