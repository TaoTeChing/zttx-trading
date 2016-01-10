/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerShopInfo;
import com.zttx.web.module.dealer.mapper.DealerShopInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 终端商微店信息 服务实现类
 * <p>File：DealerShopInfo.java </p>
 * <p>Title: DealerShopInfo </p>
 * <p>Description:DealerShopInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerShopInfoServiceImpl extends GenericServiceApiImpl<DealerShopInfo> implements DealerShopInfoService
{

    private DealerShopInfoMapper dealerShopInfoMapper;

    @Autowired(required = true)
    public DealerShopInfoServiceImpl(DealerShopInfoMapper dealerShopInfoMapper)
    {
        super(dealerShopInfoMapper);
        this.dealerShopInfoMapper = dealerShopInfoMapper;
    }
}
