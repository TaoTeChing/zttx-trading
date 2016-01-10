/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandDeal;

import java.util.List;
import java.util.Map;
/**
 * 品牌主营品类 服务接口
 * <p>File：BrandDealService.java </p>
 * <p>Title: BrandDealService </p>
 * <p>Description:BrandDealService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandDealService extends GenericServiceApi<BrandDeal>{

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
    
    /**
     * 修改品牌经营类目
     * @author 陈建平
     * @param brandId
     * @param brandesId
     * @param dealNos
     * @param createIp
     */
    void updateBrandDeal(String brandId, String brandesId, String dealNos, Integer createIp);


    /**
     * 根据品牌id获取类目
     *
     * @param brandesId
     * @return
     */
    public List<BrandDeal> selectBrandDealsByBrandesId(String brandesId);
}
