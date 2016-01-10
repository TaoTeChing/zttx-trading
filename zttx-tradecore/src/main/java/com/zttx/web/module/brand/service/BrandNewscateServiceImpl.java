/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandNewscate;
import com.zttx.web.module.brand.mapper.BrandNewscateMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌资讯类别 服务实现类
 * <p>File：BrandNewscate.java </p>
 * <p>Title: BrandNewscate </p>
 * <p>Description:BrandNewscate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandNewscateServiceImpl extends GenericServiceApiImpl<BrandNewscate> implements BrandNewscateService
{

    private BrandNewscateMapper brandNewscateMapper;

    @Autowired(required = true)
    public BrandNewscateServiceImpl(BrandNewscateMapper brandNewscateMapper)
    {
        super(brandNewscateMapper);
        this.brandNewscateMapper = brandNewscateMapper;
    }
}
