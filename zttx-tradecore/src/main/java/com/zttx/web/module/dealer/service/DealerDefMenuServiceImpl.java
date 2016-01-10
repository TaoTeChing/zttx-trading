/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.dealer.entity.DealerDefMenu;
import com.zttx.web.module.dealer.mapper.DealerDefMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 经销商自定义导航 服务实现类
 * <p>File：DealerDefMenu.java </p>
 * <p>Title: DealerDefMenu </p>
 * <p>Description:DealerDefMenu </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerDefMenuServiceImpl extends GenericServiceApiImpl<DealerDefMenu> implements DealerDefMenuService
{

    private DealerDefMenuMapper dealerDefMenuMapper;

    @Autowired(required = true)
    public DealerDefMenuServiceImpl(DealerDefMenuMapper dealerDefMenuMapper)
    {
        super(dealerDefMenuMapper);
        this.dealerDefMenuMapper = dealerDefMenuMapper;
    }
}
