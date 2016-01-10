/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductCate;
import com.zttx.web.module.common.model.ProductCateModel;

import java.util.List;

/**
 * 产品所属分类 服务接口
 * <p>File：ProductCateService.java </p>
 * <p>Title: ProductCateService </p>
 * <p>Description:ProductCateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductCateService extends GenericServiceApi<ProductCate>
{
    String getCateIds(String productId);
    void updateProductCate(ProductCateModel productCate)throws BusinessException;

    /**
     * 根据产品ID取产品所属分类
     * @param productId
     * @return
     */
    List<ProductCate> getProductCatesByProductId(String productId);
}
