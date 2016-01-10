/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandDoccate;

/**
 * 品牌商文档类别信息 持久层接口
 * <p>File：BrandDoccateDao.java </p>
 * <p>Title: BrandDoccateDao </p>
 * <p>Description:BrandDoccateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandDoccateMapper extends GenericMapper<BrandDoccate>
{
	/**
	 * 根据ID批量删除
	 * @author 陈建平
	 * @param refrenceIdAry
	 * @return
	 */
    void updateBrandDoccateDelFlag(List<String> refrenceIds);
}
