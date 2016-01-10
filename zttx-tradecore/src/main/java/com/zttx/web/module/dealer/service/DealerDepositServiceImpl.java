/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerDeposit;
import com.zttx.web.module.dealer.mapper.DealerDepositMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 押金支付记录 服务实现类
 * <p>File：DealerDeposit.java </p>
 * <p>Title: DealerDeposit </p>
 * <p>Description:DealerDeposit </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerDepositServiceImpl extends GenericServiceApiImpl<DealerDeposit> implements DealerDepositService
{

    private DealerDepositMapper dealerDepositMapper;

    @Autowired(required = true)
    public DealerDepositServiceImpl(DealerDepositMapper dealerDepositMapper)
    {
        super(dealerDepositMapper);
        this.dealerDepositMapper = dealerDepositMapper;
    }
}
