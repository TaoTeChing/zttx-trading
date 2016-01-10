package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：BrandActivityService.java </p>
 * <p>Title: BrandActivityService </p>
 * <p>Description: BrandActivityService </p>
 * <p>Copyright: Copyright (c) 十月 10，2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
public interface BrandActivityService
{
    /**
     * 推送调价数据给支撑系统
     * @param brandId 品牌商ID
     * @param productId 产品ID
     * @param newProductSkuList 客户端设置的sku价格明细
     * @throws BusinessException
     */
    void sendAdjustPriceToBoss(String brandId, String productId, List<ProductSku> newProductSkuList) throws BusinessException;
}
