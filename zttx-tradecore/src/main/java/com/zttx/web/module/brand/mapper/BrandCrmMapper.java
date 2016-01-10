/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandCrm;

/**
 * 品牌商更新信息表CRM 持久层接口
 * <p>File：BrandCrmDao.java </p>
 * <p>Title: BrandCrmDao </p>
 * <p>Description:BrandCrmDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandCrmMapper extends GenericMapper<BrandCrm>
{
	/**
     * 通过接口获取品牌商更新信息
     * @author 陈建平
     * @param filter
     * @return
     */
    List<BrandCrm> selectBrandCrmByClient(BrandCrm filter);
}
