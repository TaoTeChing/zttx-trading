/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandCert;
/**
 * 品牌证书 服务接口
 * <p>File：BrandCertService.java </p>
 * <p>Title: BrandCertService </p>
 * <p>Description:BrandCertService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandCertService extends GenericServiceApi<BrandCert>{

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
