/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerShopEnvImgTemp;
import com.zttx.web.module.dealer.mapper.DealerShopEnvImgTempMapper;

/**
 * 经销店铺 临时图片 服务实现类
 * <p>File：DealerShopEnvImgTemp.java </p>
 * <p>Title: DealerShopEnvImgTemp </p>
 * <p>Description:DealerShopEnvImgTemp </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerShopEnvImgTempServiceImpl extends GenericServiceApiImpl<DealerShopEnvImgTemp> implements DealerShopEnvImgTempService
{
    private DealerShopEnvImgTempMapper dealerShopEnvImgTempMapper;
    
    @Autowired(required = true)
    public DealerShopEnvImgTempServiceImpl(DealerShopEnvImgTempMapper dealerShopEnvImgTempMapper)
    {
        super(dealerShopEnvImgTempMapper);
        this.dealerShopEnvImgTempMapper = dealerShopEnvImgTempMapper;
    }
    
    @Override
    public List<DealerShopEnvImgTemp> getDealerShopEnvImgTempByShopEnvId(String shopEnvId)
    {
        DealerShopEnvImgTemp dealerShopEnvImgTempFilter = new DealerShopEnvImgTemp();
        dealerShopEnvImgTempFilter.setShopEnvId(shopEnvId);
        return dealerShopEnvImgTempMapper.findList(dealerShopEnvImgTempFilter);
    }
}
