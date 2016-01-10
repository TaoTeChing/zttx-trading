/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandDeal;

import java.util.List;
import java.util.Map;

/**
 * 品牌主营品类 持久层接口
 * <p>File：BrandDealDao.java </p>
 * <p>Title: BrandDealDao </p>
 * <p>Description:BrandDealDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandDealMapper extends GenericMapper<BrandDeal>
{
	/**
	 * 通过品牌商ID和品牌来ID来查询对应的所有品类
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 * @return
	 */
    List<Integer> findDealNoBy(String brandId, String brandsId);
    
    /**
     * 根据品牌商编号、品牌编号、删除标志
     * @author 陈建平
     * @param brandId
     * @param brandsId
     * @param delState
     * @return
     */
    List<BrandDeal> findByBrandsId(String brandId, String brandsId, Boolean delState);
    
    /**
     * 获取品类名称列表
     * @param brandsId 品牌编号
     * @return
     * @author 施建波
     */
    List<Map<String, Object>> getBrandDealNameList(String brandsId);


    public List<BrandDeal> selectBrandDealsByBrandesId(String brandesId);
}
