/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandCard;
import com.zttx.web.module.brand.model.BrandCardModel;

/**
 * 品牌商证书信息 服务接口
 * <p>File：BrandCardService.java </p>
 * <p>Title: BrandCardService </p>
 * <p>Description:BrandCardService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandCardService extends GenericServiceApi<BrandCard>
{
    /**
     * 分页查询 品牌商证书 列表
     * @param page
     * @param brandId
     * @return
     */
    PaginateResult<BrandCard> search(Pagination page, String brandId);
    
    /**
     * 获取品牌商证书
     *
     * @param refrenceId
     * @param brandId
     * @return
     */
    BrandCard getEntity(String refrenceId, String brandId);
    
    /**
     * 保存品牌商证书图片
     * @param newCard
     * @param oldCard
     * @throws BusinessException
     */
    void saveImage(BrandCardModel newCard, BrandCard oldCard) throws BusinessException;
    
    /**
     * 保存品牌商证书
     * @param newCard
     * @param oldCard
     */
    void save(BrandCardModel newCard, BrandCard oldCard);
}
