/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductWeightInfo;

/**
 * ProductWeightInfo 持久层接口
 * <p>File：ProductWeightInfoDao.java </p>
 * <p>Title: ProductWeightInfoDao </p>
 * <p>Description:ProductWeightInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductWeightInfoMapper extends GenericMapper<ProductWeightInfo>
{
    /**
     * 查询
     * @param searchBean
     * @return
     */
    List<ProductWeightInfo> searchByClient(ProductWeightInfo searchBean);
}
