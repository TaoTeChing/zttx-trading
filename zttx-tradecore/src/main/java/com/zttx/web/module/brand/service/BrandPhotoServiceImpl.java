/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandPhoto;
import com.zttx.web.module.brand.mapper.BrandPhotoMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌形象照片 服务实现类
 * <p>File：BrandPhoto.java </p>
 * <p>Title: BrandPhoto </p>
 * <p>Description:BrandPhoto </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandPhotoServiceImpl extends GenericServiceApiImpl<BrandPhoto> implements BrandPhotoService
{

    private BrandPhotoMapper brandPhotoMapper;

    @Autowired(required = true)
    public BrandPhotoServiceImpl(BrandPhotoMapper brandPhotoMapper)
    {
        super(brandPhotoMapper);
        this.brandPhotoMapper = brandPhotoMapper;
    }
    
    /**
	 * 根据品牌商编号，品牌编号，删除标志
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 * @param delState
	 * @return
	 */
    @Override
	public List<BrandPhoto> findByBrandIdAndBrandsId(String brandId, String brandsId, Boolean delState){
		return brandPhotoMapper.findByBrandsId(brandId, brandsId, delState);
	}

    @Override
    public List<BrandPhoto> findByBrandIdAndBrandsId(String brandId,
            String brandsId)
    {
        BrandPhoto params = new BrandPhoto();
        params.setBrandesId(brandsId);
        params.setBrandId(brandId);        
        return brandPhotoMapper.findList(params);
    }
}
