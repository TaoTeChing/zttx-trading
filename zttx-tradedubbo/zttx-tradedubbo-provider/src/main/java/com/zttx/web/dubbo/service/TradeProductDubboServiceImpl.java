/*
 * @(#)TradeProductDubboServiceImpl.java 2015-9-2 上午10:23:16
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>File：TradeProductDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:23:16</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class TradeProductDubboServiceImpl implements TradeProductDubboService
{
    @Autowired
    private DealerInfoService        dealerInfoService;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired
    private DealerJoinService        dealerJoinService;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Override
    public List<String> getAuthorizedProducts2(String dealerId, String brandsId)
    {
        if (StringUtils.isBlank(dealerId) || StringUtils.isBlank(brandsId)) { return null; }
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
        if (null == dealerInfo) { return null; }
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandsId);
        if (null == brandesInfo) { return null; }
        List<String> list = null;
        DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerId, brandsId);
        if (null != dealerJoin)
        {
            // 获取已付押金
            BigDecimal paidAmount = dealerJoin.getPaidAmount();
            if (null == paidAmount)
            {
                paidAmount = BigDecimal.ZERO;
            }
            // 获取总押金
            BigDecimal totalAmount = dealerJoin.getDepositTotalAmount();
            if (null == totalAmount)
            {
                totalAmount = BigDecimal.ZERO;
            }
            if (dealerJoin.getIsPaid() || totalAmount.compareTo(paidAmount) < 1)
            {
                list = productInfoDubboConsumer.findProductByBrandesIdAndDealerId(brandsId, dealerId);
            }
            else
            {
                return null;
            }
        }
        return list;
    }
}
