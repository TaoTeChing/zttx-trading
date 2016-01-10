/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandTemplate;

/**
 * 品牌预订模板 持久层接口
 * <p>File：BrandTemplateDao.java </p>
 * <p>Title: BrandTemplateDao </p>
 * <p>Description:BrandTemplateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandTemplateMapper extends GenericMapper<BrandTemplate>
{
    List<BrandTemplate> listBrandTemplate(@Param("brandId")String brandId, @Param("brandsId")String brandsId);
}
