/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductSaleCount;
/**
 * ProductSaleCount 服务接口
 * <p>File：ProductSaleCountService.java </p>
 * <p>Title: ProductSaleCountService </p>
 * <p>Description:ProductSaleCountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductSaleCountService extends GenericServiceApi<ProductSaleCount>{
    /**
     * 处理产品月销售统计
     * @param brandId
     * @param brandsId
     * @param productId
     */
    void modifyProductSaleCount(String brandId,String brandsId,String productId)throws BusinessException;
}
