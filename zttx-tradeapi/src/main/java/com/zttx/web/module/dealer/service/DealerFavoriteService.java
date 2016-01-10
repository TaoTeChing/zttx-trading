/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerFavorite;
import com.zttx.web.module.dealer.model.ProductFilter;

import java.util.List;
import java.util.Map;

/**
 * 经销商产品收藏 服务接口
 * <p>File：DealerFavoriteService.java </p>
 * <p>Title: DealerFavoriteService </p>
 * <p>Description:DealerFavoriteService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerFavoriteService extends GenericServiceApi<DealerFavorite>{
    /**
     * 经销商 产品收藏夹 分页查询
     * @param productFilter
     * @return
     * @author 易永耀
     */
    PaginateResult<Map<String,Object>> selectProductFavoritePage(Pagination page,ProductFilter productFilter) throws BusinessException;

    /**
     * 判断当前商品收藏是否存在
     * @param dealerFavorite
     * @return
     */
    Boolean isProductExists(DealerFavorite dealerFavorite);

    /**
     * 经销商 产品收藏夹 品牌分类
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectFavoriteCata(String dealerId);

    /**
     * 加入收藏夹
     * @param dealerFavorite
     * @author 易永耀
     */
    void addDealerFavorite(DealerFavorite dealerFavorite) throws BusinessException;

    /**
     * 删除收藏夹
     * @param dealerFavorite
     * @author 易永耀
     */
    void removeDealerFavorite(DealerFavorite dealerFavorite);
}
