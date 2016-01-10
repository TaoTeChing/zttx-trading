/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandLogistics;

/**
 * 品牌商物流公司 持久层接口
 * <p>File：BrandLogisticsDao.java </p>
 * <p>Title: BrandLogisticsDao </p>
 * <p>Description:BrandLogisticsDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandLogisticsMapper extends GenericMapper<BrandLogistics>
{

    /**
     * 根据品牌商ID查找
     * @param brandId
     * @return
     */
    List<BrandLogistics> listByBrandId(@Param("brandId") String brandId);
}
