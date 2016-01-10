/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandContact;

/**
 * 品牌商联系方式 持久层接口
 * <p>File：BrandContactDao.java </p>
 * <p>Title: BrandContactDao </p>
 * <p>Description:BrandContactDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandContactMapper extends GenericMapper<BrandContact>
{
    /**
     * 根据品牌商编号获取品牌商联系方式
     * @param brandId
     * @return
     */
    BrandContact findByBrandId(String brandId);
}
