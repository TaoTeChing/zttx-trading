/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerAdvice;
import com.zttx.web.module.dealer.mapper.DealerAdviceMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 经销商优化建议 服务实现类
 * <p>File：DealerAdvice.java </p>
 * <p>Title: DealerAdvice </p>
 * <p>Description:DealerAdvice </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerAdviceServiceImpl extends GenericServiceApiImpl<DealerAdvice> implements DealerAdviceService
{

    private DealerAdviceMapper dealerAdviceMapper;

    @Autowired(required = true)
    public DealerAdviceServiceImpl(DealerAdviceMapper dealerAdviceMapper)
    {
        super(dealerAdviceMapper);
        this.dealerAdviceMapper = dealerAdviceMapper;
    }
}
