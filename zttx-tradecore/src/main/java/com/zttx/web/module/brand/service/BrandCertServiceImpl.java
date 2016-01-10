/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandCert;
import com.zttx.web.module.brand.mapper.BrandCertMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌证书 服务实现类
 * <p>File：BrandCert.java </p>
 * <p>Title: BrandCert </p>
 * <p>Description:BrandCert </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandCertServiceImpl extends GenericServiceApiImpl<BrandCert> implements BrandCertService
{

    private BrandCertMapper brandCertMapper;

    @Autowired(required = true)
    public BrandCertServiceImpl(BrandCertMapper brandCertMapper)
    {
        super(brandCertMapper);
        this.brandCertMapper = brandCertMapper;
    }
    
    /**
	 * 根据品牌id获取品牌证书
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 * @param delState
	 * @return
	 */
    @Override
    public List<BrandCert> findByBrandsId(String brandId, String brandsId, Boolean delState){
    	return brandCertMapper.findByBrandsId(brandId, brandsId, delState);
    }
    
    /**
     * 根据品牌商ID和品牌ID删除品牌认证书
     * @author 陈建平
     * @param brandId
     * @param brandsId
     */
    @Override
    public void deleteByBrandIdAndBrandsId(String brandId, String brandsId){
    	brandCertMapper.deleteByBrandIdAndBrandsId(brandId, brandsId);
    }
}
