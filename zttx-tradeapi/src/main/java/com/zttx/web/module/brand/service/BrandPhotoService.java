/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandPhoto;
/**
 * 品牌形象照片 服务接口
 * <p>File：BrandPhotoService.java </p>
 * <p>Title: BrandPhotoService </p>
 * <p>Description:BrandPhotoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandPhotoService extends GenericServiceApi<BrandPhoto>{
	
	/**
	 * 根据品牌商编号，品牌编号，删除标志
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 * @param delState
	 * @return
	 */
	List<BrandPhoto> findByBrandIdAndBrandsId(String brandId, String brandsId, Boolean delState);
	
	/**
     * 根据品牌商编号，品牌编号 查询
     * @author 郭小亮
     * @param brandId
     * @param brandsId
     * @param delState
     * @return
     */
    List<BrandPhoto> findByBrandIdAndBrandsId(String brandId, String brandsId);
    

}
