/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandsCount;

/**
 * 品牌计数信息 服务接口
 * <p>File：BrandsCountService.java </p>
 * <p>Title: BrandsCountService </p>
 * <p>Description:BrandsCountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandsCountService extends GenericServiceApi<BrandsCount>{
	
	/**
     * 修改BrandsCount 统计数
     * @author 陈建平
     * @param brandsId
     * @param countTypeName
     * @param isUpdateSolr true:修改BrandsCount并修改品牌索引
     * @return
     * @throws BusinessException
     */
    public BrandsCount modifyBrandsCount(String brandsId, String[] countTypeName, boolean isUpdateSolr) throws BusinessException;
    
    /**
     * 修改品牌统计信息
     * @param brandsId
     * @param countTypeName
     * @return
     */
    BrandsCount modifyBrandsCount(String brandsId, String[] countTypeName)throws BusinessException;
    /**
     * 根据品牌商和品牌查找统计信息
     * @param brandId
     * @param brandsId
     * @return
     */
    BrandsCount findByBrandIdAndBrandsId(String brandId,String brandsId);

    /**
     * 取一段时间内有过变更的品牌统计信息集合
     * @return id 集合
     */
    List<String> getBrandsCountUpdatedIn(long time);
}
