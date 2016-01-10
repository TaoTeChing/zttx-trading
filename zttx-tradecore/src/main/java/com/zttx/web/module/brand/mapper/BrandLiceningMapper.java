/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandLicening;

/**
 * 品牌授权证书 持久层接口
 * <p>File：BrandLiceningDao.java </p>
 * <p>Title: BrandLiceningDao </p>
 * <p>Description:BrandLiceningDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandLiceningMapper extends GenericMapper<BrandLicening>
{

	/**
	 * 根据品牌商编号和品牌编号批量修改删除标志为删除状态
	 * @author 陈建平
	 * @param brandId
	 * @param brandesId
	 */
    void updateDelState(@Param("delFlag")Boolean delFlag,@Param("brandId")String brandId, @Param("brandesId")String brandesId);
    
    /**
     * 根据品牌商编号、品牌编号、删除标志
     * @author 陈建平
     * @param brandId
     * @param brandsId
     * @param delState
     * @return
     */
    List<BrandLicening> findByBrandsId(String brandId, String brandsId, Boolean delState);
}
