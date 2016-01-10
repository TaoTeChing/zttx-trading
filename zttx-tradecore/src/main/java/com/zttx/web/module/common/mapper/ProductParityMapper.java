/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductParity;

/**
 * 产品比价表 持久层接口
 * <p>File：ProductParityDao.java </p>
 * <p>Title: ProductParityDao </p>
 * <p>Description:ProductParityDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductParityMapper extends GenericMapper<ProductParity>
{
    /**
     * 根据比价列查找比价信息
     * @param columnId
     * @return
     */
    ProductParity findByParityColumnIdAndProductId(@Param("parityId")String columnId,@Param("productId")String productId);
    /**
     * 根据产品id查找比价信息
     * @param productId
     * @return
     */
    List<Map<String,Object>> findParityModel(ProductParity parity);
}
