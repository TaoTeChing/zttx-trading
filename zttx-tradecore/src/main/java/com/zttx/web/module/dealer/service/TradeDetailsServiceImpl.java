/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.TradeDetails;
import com.zttx.web.module.dealer.mapper.TradeDetailsMapper;

/**
 * 交易明细表 服务实现类
 * <p>File：TradeDetails.java </p>
 * <p>Title: TradeDetails </p>
 * <p>Description:TradeDetails </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class TradeDetailsServiceImpl extends GenericServiceApiImpl<TradeDetails> implements TradeDetailsService
{
    private TradeDetailsMapper tradeDetailsMapper;
    
    @Autowired(required = true)
    public TradeDetailsServiceImpl(TradeDetailsMapper tradeDetailsMapper)
    {
        super(tradeDetailsMapper);
        this.tradeDetailsMapper = tradeDetailsMapper;
    }
}
