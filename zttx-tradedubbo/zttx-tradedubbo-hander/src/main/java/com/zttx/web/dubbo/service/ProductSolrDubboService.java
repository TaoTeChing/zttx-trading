package com.zttx.web.dubbo.service;

import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：ProductSolrDubboService.java </p>
 * <p>Title: 提供操作产品SOLR搜索引擎的DUBBO服务 </p>
 * <p>Description: ProductSolrDubboService </p>
 * <p>Copyright: Copyright (c) 15/9/7</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface ProductSolrDubboService
{
    /**
     * 移出产品索引
     * @param productId
     * @throws BusinessException
     */
    void delProductInfo(String productId) throws BusinessException;
    
    /**
     * 添加或修改产品索引
     * @param productId
     * @throws BusinessException
     */
    void addProductInfo(String productId) throws BusinessException;
}
