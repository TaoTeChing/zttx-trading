/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.exhibition.entity.DecorateGlobal;

/**
 * 展厅装修全局配置 持久层接口
 * <p>File：DecorateGlobalDao.java </p>
 * <p>Title: DecorateGlobalDao </p>
 * <p>Description:DecorateGlobalDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DecorateGlobalMapper extends GenericMapper<DecorateGlobal>
{
    List<DecorateGlobal> findByBrandIdAndBrandsId(@Param("brandId")String brandId, @Param("brandsId")String brandsId);
}
