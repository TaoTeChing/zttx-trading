/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandPaylog;
import com.zttx.web.module.brand.mapper.BrandPaylogMapper;

/**
 * 支付密码修改历史 服务实现类
 * <p>File：BrandPaylog.java </p>
 * <p>Title: BrandPaylog </p>
 * <p>Description:BrandPaylog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandPaylogServiceImpl extends GenericServiceApiImpl<BrandPaylog> implements BrandPaylogService
{
    private BrandPaylogMapper brandPaylogMapper;
    
    @Autowired(required = true)
    public BrandPaylogServiceImpl(BrandPaylogMapper brandPaylogMapper)
    {
        super(brandPaylogMapper);
        this.brandPaylogMapper = brandPaylogMapper;
    }
}
