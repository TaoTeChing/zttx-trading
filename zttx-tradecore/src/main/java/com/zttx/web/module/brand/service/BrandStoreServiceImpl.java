/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandStore;
import com.zttx.web.module.brand.mapper.BrandStoreMapper;

/**
 * 品牌商品牌仓库信息 服务实现类
 * <p>File：BrandStore.java </p>
 * <p>Title: BrandStore </p>
 * <p>Description:BrandStore </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandStoreServiceImpl extends GenericServiceApiImpl<BrandStore> implements BrandStoreService
{

    private BrandStoreMapper brandStoreMapper;

    @Autowired(required = true)
    public BrandStoreServiceImpl(BrandStoreMapper brandStoreMapper)
    {
        super(brandStoreMapper);
        this.brandStoreMapper = brandStoreMapper;
    }
    
    @Override
    public List<BrandStore> listBrandStore(String brandsId)
    {
        BrandStore filter = new BrandStore();
        filter.setBrandsId(brandsId);
        return brandStoreMapper.findList(filter);
    }
    
    @Override
    public List<BrandStore> listBrandStore(String brandsId, String brandId)
    {
        BrandStore filter = new BrandStore();
        filter.setBrandsId(brandsId);
        filter.setBrandId(brandId);
        return brandStoreMapper.findList(filter);
    }
}
