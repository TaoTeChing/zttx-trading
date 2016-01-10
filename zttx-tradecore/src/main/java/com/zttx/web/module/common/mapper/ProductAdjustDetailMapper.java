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
import com.zttx.web.module.common.entity.ProductAdjustDetail;

/**
 * 产品调价明细表 持久层接口
 * <p>File：ProductAdjustDetailDao.java </p>
 * <p>Title: ProductAdjustDetailDao </p>
 * <p>Description:ProductAdjustDetailDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductAdjustDetailMapper extends GenericMapper<ProductAdjustDetail>
{
    /**
     * 查找此产品的调价记录数量
     * @param brandId
     * @param productId
     * @return
     */
    Integer countByProductId(@Param("brandId")String brandId, @Param("productId")String productId);
    /**
     * 根据skuid获取最近返价记录
     * @param skuId
     * @return
     */
    ProductAdjustDetail findBySkuIdLastDetail(String skuId);
    /**
     * 查询调价记录
     * @param detail
     * @return
     */
    List<Map<String, Object>> search(ProductAdjustDetail detail);
}
