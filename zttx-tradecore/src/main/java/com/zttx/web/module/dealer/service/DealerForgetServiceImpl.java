/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerForget;
import com.zttx.web.module.dealer.mapper.DealerForgetMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 取回密码日志 服务实现类
 * <p>File：DealerForget.java </p>
 * <p>Title: DealerForget </p>
 * <p>Description:DealerForget </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerForgetServiceImpl extends GenericServiceApiImpl<DealerForget> implements DealerForgetService
{

    private DealerForgetMapper dealerForgetMapper;

    @Autowired(required = true)
    public DealerForgetServiceImpl(DealerForgetMapper dealerForgetMapper)
    {
        super(dealerForgetMapper);
        this.dealerForgetMapper = dealerForgetMapper;
    }
}
