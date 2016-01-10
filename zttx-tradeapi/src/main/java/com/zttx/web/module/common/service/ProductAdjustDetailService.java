/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductAdjustDetail;

/**
 * 产品调价明细表 服务接口
 * <p>File：ProductAdjustDetailService.java </p>
 * <p>Title: ProductAdjustDetailService </p>
 * <p>Description:ProductAdjustDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductAdjustDetailService extends GenericServiceApi<ProductAdjustDetail> {
    /**
     * 查找此产品的调价记录数量
     * @param productId
     * @return
     */
    Integer countByProductId(String brandId,String productId);
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
    PaginateResult<Map<String, Object>> search(ProductAdjustDetail detail)throws BusinessException;
}
