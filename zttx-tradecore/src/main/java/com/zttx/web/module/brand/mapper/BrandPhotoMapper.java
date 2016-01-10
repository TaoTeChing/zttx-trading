/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandPhoto;

/**
 * 品牌形象照片 持久层接口
 * <p>File：BrandPhotoDao.java </p>
 * <p>Title: BrandPhotoDao </p>
 * <p>Description:BrandPhotoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandPhotoMapper extends GenericMapper<BrandPhoto>
{
    /**
     * 获取品牌的最新企业图片信息
     * @param brandId
     * @param brandsId
     * @return
     */
    BrandPhoto findBrandPhoto(@Param("brandId") String brandId, @Param("brandesId") String brandesId);
	
    /**
	 * 根据品牌商编号，品牌编号，删除标志
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 * @param delState
	 * @return
	 */
	List<BrandPhoto> findByBrandsId(String brandId, String brandsId, Boolean delState);
	
}
