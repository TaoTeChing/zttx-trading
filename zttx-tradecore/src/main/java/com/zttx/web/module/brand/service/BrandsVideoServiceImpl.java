/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandsVideo;
import com.zttx.web.module.brand.mapper.BrandsVideoMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌模特视频 服务实现类
 * <p>File：BrandsVideo.java </p>
 * <p>Title: BrandsVideo </p>
 * <p>Description:BrandsVideo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandsVideoServiceImpl extends GenericServiceApiImpl<BrandsVideo> implements BrandsVideoService
{

    private BrandsVideoMapper brandsVideoMapper;

    @Autowired(required = true)
    public BrandsVideoServiceImpl(BrandsVideoMapper brandsVideoMapper)
    {
        super(brandsVideoMapper);
        this.brandsVideoMapper = brandsVideoMapper;
    }
    
    /**
	 * 搜索品牌模特视频 
	 * @author 陈建平
	 * @param filter
	 * @return
	 */
    @Override
	public List<Map<String,Object>> findBrandsVideoMap(BrandsVideo filter){
		return brandsVideoMapper.findBrandsVideoMap(filter);
	}
}
