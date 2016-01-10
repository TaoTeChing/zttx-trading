/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandDefMenu;
import com.zttx.web.module.brand.mapper.BrandDefMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌商自定义导航 服务实现类
 * <p>File：BrandDefMenu.java </p>
 * <p>Title: BrandDefMenu </p>
 * <p>Description:BrandDefMenu </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandDefMenuServiceImpl extends GenericServiceApiImpl<BrandDefMenu> implements BrandDefMenuService
{

    private BrandDefMenuMapper brandDefMenuMapper;

    @Autowired(required = true)
    public BrandDefMenuServiceImpl(BrandDefMenuMapper brandDefMenuMapper)
    {
        super(brandDefMenuMapper);
        this.brandDefMenuMapper = brandDefMenuMapper;
    }
}
