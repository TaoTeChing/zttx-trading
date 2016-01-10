/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductEditDetail;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.fronts.model.SolrFilter;

/**
 * 商品信息表 服务接口
 * <p>File：ProductInfoService.java </p>
 * <p>Title: ProductInfoService </p>
 * <p>Description:产品信息现由商品中心实现，这里只为重建solr服务而提供查询服务 </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductInfoService
{
    /**
     * solr产品搜索接口
     * @param filter
     * @param pagination
     * @return {@link PaginateResult<Map<String, Object>>}
     * @throws BusinessException
     */
    PaginateResult<Map<String, Object>> searchAllProduct(SolrFilter filter, Pagination pagination) throws BusinessException;
    
    /**
     *查询产品索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param productInfo
     * @return {@link Long}
     */
    Long findProductToSolrCount(ProductInfo productInfo) throws BusinessException;
    
    /**
     * 查询产品索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param productInfo
     * @param pagination
     * @return  {@link List}
     * @throws BusinessException
     */
    List<ProductInfo> findProductToSolr(ProductInfo productInfo, Pagination pagination) throws BusinessException;


    /**
     * 查询产品索引有关的数据
     * <p>
     *    listMaps 包含有产品ID的集合
     * </p>
     * @return  {@link List}
     * @throws BusinessException
     */
    List<ProductInfo> findProductToSolr(List<String> listMaps) throws BusinessException;

    /**
     * 查询所有产品基础信息
     * 包括产品ID，品牌ID,品牌商ID
     * @return
     */
    List<Map<String, Object>> findAllProductBaseInfo();
    
    /**
     * 审批货号颜色尺码变更更新相关信息
     * @param detail
     */
    void executeChangeProductInfo(ProductEditDetail detail) throws BusinessException;
}
