/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandLevel;

/**
 * 经销商等级 持久层接口
 * <p>File：BrandLevelDao.java </p>
 * <p>Title: BrandLevelDao </p>
 * <p>Description:BrandLevelDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandLevelMapper extends GenericMapper<BrandLevel>
{
    

    /**
     * 通过品牌id 和品牌商id来查询品牌级别信息
     * @param brandId
     * @param brandsId
     * @param page
     * @return
     */
    List<BrandLevel> getBrandLevelsBy(@Param("brandId")String brandId, @Param("brandsId")String brandsId,@Param("page") Pagination page);
}
