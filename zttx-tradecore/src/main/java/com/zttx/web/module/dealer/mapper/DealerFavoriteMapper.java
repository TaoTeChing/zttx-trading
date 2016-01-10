/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.dealer.entity.DealerFavorite;
import com.zttx.web.module.dealer.model.ProductFilter;

import java.util.List;
import java.util.Map;

/**
 * 经销商产品收藏 持久层接口
 * <p>File：DealerFavoriteDao.java </p>
 * <p>Title: DealerFavoriteDao </p>
 * <p>Description:DealerFavoriteDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerFavoriteMapper extends GenericMapper<DealerFavorite>
{

    /**
     * 统计符合条件的 dealerFavorite个数 
     * @param dealerFavorite
     * @return
     */
    Integer countDealerFavorite(DealerFavorite dealerFavorite);
    /**
     * 根据 dealerId 查询 该经销商 收藏的产品，以及产品的状态
     * @param productFilter
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectProductFavoriteList(ProductFilter productFilter);

    /**
     * 经销商是否收藏了该产品
     * @param dealerId
     * @param productId
     * @return
     */
    boolean isExist(String dealerId, String productId);

    /**
     * 收藏产品品牌分类
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectFavoriteCata(String dealerId);
}
