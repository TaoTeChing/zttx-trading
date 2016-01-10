/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerShopers;

/**
 * 购物详细信息表 持久层接口
 * <p>File：DealerShopersDao.java </p>
 * <p>Title: DealerShopersDao </p>
 * <p>Description:DealerShopersDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerShopersMapper extends GenericMapper<DealerShopers>
{
    /**
     * 根据结算类型 即时查询sku 价格
     * @param skuId
     * @param productType
     * @return
     */
    DealerShopers selectSkuPrice2DealerShopers(@Param("skuId") String skuId, @Param("productType") short productType);
    
    /**
     * 根据 购物车 主表Id 获取购物车副表 中购物车单详细sku信息
     * @param shoperId
     * @return
     * @author 易永耀
     */
    List<DealerShopers> selectDealerShopersBy(@Param("shoperId") String shoperId, @Param("productType") short productType);
    
    /**
     * 根据购物车 主表主键 查其主表相关的副表 的sku购买总数，购买的总价钱
     * @param shoperId
     * @return
     * @author 易永耀
     */
    Map<String, Object> selectDealerShopersSumBy(String shoperId);
    
    /**
     * 根据skuid和购物车id查找购物车详细信息
     * @param skuId
     * @param shoperId
     * @return
     */
    DealerShopers getByproductSkuIdAndDealerShopId(@Param("skuId") String skuId, @Param("shopId") String shoperId);
    
    /**
     * 批量删除
     * @param shoperId
     */
    void deleteBatch(@Param("shoperId") String shoperId);
}
