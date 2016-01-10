/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerFavtag;
import com.zttx.web.module.dealer.mapper.DealerFavtagMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 经销商收藏标签 服务实现类
 * <p>File：DealerFavtag.java </p>
 * <p>Title: DealerFavtag </p>
 * <p>Description:DealerFavtag </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerFavtagServiceImpl extends GenericServiceApiImpl<DealerFavtag> implements DealerFavtagService
{

    private DealerFavtagMapper dealerFavtagMapper;

    @Autowired(required = true)
    public DealerFavtagServiceImpl(DealerFavtagMapper dealerFavtagMapper)
    {
        super(dealerFavtagMapper);
        this.dealerFavtagMapper = dealerFavtagMapper;
    }
}
