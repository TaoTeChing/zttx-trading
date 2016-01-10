/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.exhibition.entity.DecorateHeader;

/**
 * 展厅头部装修 持久层接口
 * <p>File：DecorateHeaderDao.java </p>
 * <p>Title: DecorateHeaderDao </p>
 * <p>Description:DecorateHeaderDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DecorateHeaderMapper extends GenericMapper<DecorateHeader>
{
    List<DecorateHeader> findByBrandIdAndBrandsId(@Param("brandId")String brandId, @Param("brandsId")String brandsId);
}
