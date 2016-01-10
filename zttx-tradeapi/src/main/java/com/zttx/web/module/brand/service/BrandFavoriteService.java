/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandFavorite;
import com.zttx.web.module.brand.model.BrandFavoriteView;

import java.util.Map;

/**
 * 品牌商收藏的经销商 服务接口
 * <p>File：BrandFavoriteService.java </p>
 * <p>Title: BrandFavoriteService </p>
 * <p>Description:BrandFavoriteService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandFavoriteService extends GenericServiceApi<BrandFavorite>{

    /**
     * 收藏的经销商列表
     *
     * @param pagination
     * @param  info
     * @return
     */
    public PaginateResult<Map<String, Object>> search(Pagination pagination, BrandFavoriteView info);

    /**
     * 判断是否已收藏
     *
     * @param brandId
     * @param dealerId
     * @return
     */
    public Boolean isCollected(String brandId, String dealerId) ;

    /**
     * 收藏终端商
     * @param brandId
     * @param dealerId
     * @throws BusinessException
     */
    public void executeCollect(String brandId, String dealerId) throws BusinessException;

    /**
     * 取消收藏
     *
     * @param brandId
     * @param dealerId
     * @throws BusinessException
     */
    public void executeUnCollect(String brandId, String dealerId) throws BusinessException;


}
