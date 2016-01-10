/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.brand.entity.BrandCheck;

/**
 * 品牌商审核经销商加盟申请日志 持久层接口
 * <p>File：BrandCheckDao.java </p>
 * <p>Title: BrandCheckDao </p>
 * <p>Description:BrandCheckDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandCheckMapper extends GenericMapper<BrandCheck>
{
    /**
     * 查询加盟申请
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    BrandCheck findByDealerIdBrandIdBrandsId(String dealerId,String brandId, String brandsId);
}
