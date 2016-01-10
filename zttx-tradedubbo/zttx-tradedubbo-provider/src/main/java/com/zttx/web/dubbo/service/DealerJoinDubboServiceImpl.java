/*
 * @(#)DealerJoinDubboServiceImpl.java 2015-9-2 上午10:28:05
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.dealer.service.DealerJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>File：DealerJoinDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:28:05</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class DealerJoinDubboServiceImpl implements DealerJoinDubboService
{
    @Autowired
    private  DealerJoinService dealerJoinService;
    
    @Override
    public List<Map<String,Object>> getCoorperatedBrands(String dealerId)
    {
        List<Map<String,Object>> list = dealerJoinService.findByDealerId(dealerId, DealerConstant.DealerJoin.COOPERATED);
        return list;
    }
}
