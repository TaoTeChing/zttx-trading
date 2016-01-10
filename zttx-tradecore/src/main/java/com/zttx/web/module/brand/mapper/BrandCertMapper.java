/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandCert;

/**
 * 品牌证书 持久层接口
 * <p>File：BrandCertDao.java </p>
 * <p>Title: BrandCertDao </p>
 * <p>Description:BrandCertDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandCertMapper extends GenericMapper<BrandCert>
{
	/**
	 * 根据品牌id获取品牌证书
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 * @param delState
	 * @return
	 */
    List<BrandCert> findByBrandsId(String brandId, String brandsId, Boolean delState);
    
    /**
     * 根据品牌商ID和品牌ID删除品牌认证书
     * @author 陈建平
     * @param brandId
     * @param brandsId
     */
    void deleteByBrandIdAndBrandsId(String brandId, String brandsId);
}
