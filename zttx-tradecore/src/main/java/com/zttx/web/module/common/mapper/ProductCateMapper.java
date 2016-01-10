/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductCate;

/**
 * 产品所属分类 持久层接口
 * <p>File：ProductCateDao.java </p>
 * <p>Title: ProductCateDao </p>
 * <p>Description:ProductCateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductCateMapper extends GenericMapper<ProductCate>
{
    /**
     * 根据产品获取分类关系列表
     * @param productId
     * @return
     */
    List<ProductCate> getCateList(String productId);
    /**
     * 批量删除
     * @param idArray
     */
    void deleteCateBatch(@Param("idArray")String[] idArray);
    /**
     * 根据品牌商和产品获取分类关系
     * @param brandId
     * @param productId
     * @return
     */
    List<ProductCate> findByBrandIdAndProductId(@Param("brandId")String brandId,@Param("productId")String productId);
    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<String> ids);
    
    /**
     * 根据产品ID取产品所属分类
     * @param productId
     * @return
     */
    List<ProductCate> getProductCatesByProductId(String productId);
    
    /**
     * 根据产品id删除
     * @param productId
     */
    void deleteByProductId(String productId);
    /**
     * 根据分类id获取分类关系数量
     * @param catalogId
     * @return
     */
    Integer countByCatalogId(String catalogId);
}
