/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandStore;

/**
 * 品牌商品牌仓库信息 持久层接口
 * <p>File：BrandStoreDao.java </p>
 * <p>Title: BrandStoreDao </p>
 * <p>Description:BrandStoreDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandStoreMapper extends GenericMapper<BrandStore>
{
    /**
     * 查找BrandStore
     * @param filter
     * @return
     */
    BrandStore findBrandStore(BrandStore filter);
}
