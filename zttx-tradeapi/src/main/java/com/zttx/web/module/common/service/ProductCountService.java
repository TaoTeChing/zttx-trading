/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductCount;

/**
 * 产品计数信息 服务接口
 * <p>File：ProductCountService.java </p>
 * <p>Title: ProductCountService </p>
 * <p>Description:ProductCountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductCountService extends GenericServiceApi<ProductCount>
{
    /**
     * 根据品牌商编号,品牌id和产品编号修改统计信息
     * @param brandId
     * @param productId
     * @throws BusinessException
     */
    void modifyProductCount(String brandId, String brandsId, String productId) throws BusinessException;
    
    /**
     * 最前一个小时类变更过的所有产品统计信息
     * @return {@link List}
     * @throws BusinessException
     */
    List<String> getProductCountMaps() throws BusinessException;
}
