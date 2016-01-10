/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.common.entity.ProductInfo;

/**
 * 商品信息表 持久层接口
 * <p>File：ProductInfoDao.java </p>
 * <p>Title: ProductInfoDao </p>
 * <p>Description:ProductInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductInfoMapper
{
    /**
     * 查询产品索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param productInfo
     */
    Long findProductToSolrCount(ProductInfo productInfo);

    /**
     *根据产品编号集统计
     * @param list
     * @return
     */
    List<ProductInfo> findProductToSolrByIds(List<String> list);
    
    /**
     * 查询产品索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param productInfo
     * @param pagination
     * @return  {@link List}
     */
    List<ProductInfo> findProductToSolr(@Param("productInfo") ProductInfo productInfo, @Param("page") Pagination pagination);
    
    /**
     * 查询所有产品基础信息
     * 包括产品ID，品牌ID,品牌商ID
     * @return
     */
    List<Map<String, Object>> findAllProductBaseInfo();
}
