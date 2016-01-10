/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerFavbrand;
import com.zttx.web.module.dealer.mapper.DealerFavbrandMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 经销商收藏的品牌商 服务实现类
 * <p>File：DealerFavbrand.java </p>
 * <p>Title: DealerFavbrand </p>
 * <p>Description:DealerFavbrand </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerFavbrandServiceImpl extends GenericServiceApiImpl<DealerFavbrand> implements DealerFavbrandService
{

    private DealerFavbrandMapper dealerFavbrandMapper;

    @Autowired(required = true)
    public DealerFavbrandServiceImpl(DealerFavbrandMapper dealerFavbrandMapper)
    {
        super(dealerFavbrandMapper);
        this.dealerFavbrandMapper = dealerFavbrandMapper;
    }
}
