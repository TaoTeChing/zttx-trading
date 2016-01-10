/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandesWeightInfo;

/**
 * 品牌权重信息 持久层接口
 * <p>File：BrandesWeightInfoDao.java </p>
 * <p>Title: BrandesWeightInfoDao </p>
 * <p>Description:BrandesWeightInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 章旭楠
 * @version 1.0
 */
@MyBatisDao
public interface BrandesWeightInfoMapper extends GenericMapper<BrandesWeightInfo>
{
    /**
     * 查询品牌权重信息
     * @param searchBean
     * @return
     */
    List<BrandesWeightInfo> searchByClient(BrandesWeightInfo searchBean);
}
