/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerClearingPay;
import com.zttx.web.module.dealer.mapper.DealerClearingPayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DealerClearingPay 服务实现类
 * <p>File：DealerClearingPay.java </p>
 * <p>Title: DealerClearingPay </p>
 * <p>Description:DealerClearingPay </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerClearingPayServiceImpl extends GenericServiceApiImpl<DealerClearingPay> implements DealerClearingPayService
{

    private DealerClearingPayMapper dealerClearingPayMapper;

    @Autowired(required = true)
    public DealerClearingPayServiceImpl(DealerClearingPayMapper dealerClearingPayMapper)
    {
        super(dealerClearingPayMapper);
        this.dealerClearingPayMapper = dealerClearingPayMapper;
    }
}
