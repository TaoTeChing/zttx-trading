/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandVisual;

/**
 * 品牌视觉信息 持久层接口
 * <p>File：BrandVisualDao.java </p>
 * <p>Title: BrandVisualDao </p>
 * <p>Description:BrandVisualDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandVisualMapper extends GenericMapper<BrandVisual>
{
    /**
     * 通过品牌商id和品牌id查询
     * @param brandVisual
     * @return
     */
    List<BrandVisual> findByBrandIdAndBrandesId(BrandVisual brandVisual);
}
