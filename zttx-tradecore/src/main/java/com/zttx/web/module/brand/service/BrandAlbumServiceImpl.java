/*
 * @(#)BrandAlbumServiceImpl.java 2015-8-26 下午1:46:33
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.web.module.brand.mapper.BrandAlbumMapper;
import com.zttx.web.module.brand.mapper.BrandCardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandAlbum;

/**
 * <p>File：BrandAlbumServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-26 下午1:46:33</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
@Service
public class BrandAlbumServiceImpl extends GenericServiceApiImpl<BrandAlbum>
        implements BrandAlbumService
{
    private BrandAlbumMapper brandAlbumMapper;
    
    @Autowired(required = true)
    public BrandAlbumServiceImpl(BrandAlbumMapper brandAlbumMapper)
    {
        super(brandAlbumMapper);
        this.brandAlbumMapper = brandAlbumMapper;
    }

    @Override
    public List<BrandAlbum> findByBrandAndBrands(String brandId, String brandesId)
    {
        BrandAlbum entity = new BrandAlbum();
        entity.setBrandId(brandId);
        entity.setBrandsId(brandesId);
        return brandAlbumMapper.findList(entity);
    }
}
