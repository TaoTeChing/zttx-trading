/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerFeed;
import com.zttx.web.module.dealer.mapper.DealerFeedMapper;

/**
 * 经销商反馈信息 服务实现类
 * <p>File：DealerFeed.java </p>
 * <p>Title: DealerFeed </p>
 * <p>Description:DealerFeed </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerFeedServiceImpl extends GenericServiceApiImpl<DealerFeed> implements DealerFeedService
{

    private DealerFeedMapper dealerFeedMapper;

    @Autowired(required = true)
    public DealerFeedServiceImpl(DealerFeedMapper dealerFeedMapper)
    {
        super(dealerFeedMapper);
        this.dealerFeedMapper = dealerFeedMapper;
    }
    
    @Override
    public PaginateResult<DealerFeed> listByBrandIdAndBrandsId(Pagination pagination, String brandId, String brandsId)
    {
        List<DealerFeed> list = dealerFeedMapper.findByBrandIdAndBrandsId(pagination, brandId, brandsId);
        return new PaginateResult(pagination, list);
    }
}
