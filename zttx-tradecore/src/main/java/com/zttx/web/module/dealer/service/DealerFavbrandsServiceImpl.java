/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerFavbrands;
import com.zttx.web.module.dealer.mapper.DealerFavbrandsMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 经销商收藏的品牌 服务实现类
 * <p>File：DealerFavbrands.java </p>
 * <p>Title: DealerFavbrands </p>
 * <p>Description:DealerFavbrands </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerFavbrandsServiceImpl extends GenericServiceApiImpl<DealerFavbrands> implements DealerFavbrandsService
{

    private DealerFavbrandsMapper dealerFavbrandsMapper;

    @Autowired(required = true)
    public DealerFavbrandsServiceImpl(DealerFavbrandsMapper dealerFavbrandsMapper)
    {
        super(dealerFavbrandsMapper);
        this.dealerFavbrandsMapper = dealerFavbrandsMapper;
    }
}
