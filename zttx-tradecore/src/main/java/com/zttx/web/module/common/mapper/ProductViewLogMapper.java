/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.ProductViewLog;
import com.zttx.web.module.dealer.model.ProductFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品浏览历史记录 持久层接口
 * <p>File：ProductViewLogDao.java </p>
 * <p>Title: ProductViewLogDao </p>
 * <p>Description:ProductViewLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductViewLogMapper extends GenericMapper<ProductViewLog>
{

    /**
     * 经销商  我的授权产品库  我的浏览记录
     * @param filter
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectViewLogProductList(ProductFilter filter);

    /**
     * 经销商  我的授权产品库  我的浏览记录 品牌分类
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectViewLogCata(String dealerId);

    /**
     * 根据产品id查找产品查看数
     * @param product
     * @return
     */
    Integer countViewLogByProduct(String productId);

    /**
     * 根据产品Id，用户Id， 查询是否有浏览记录
     * @param productId
     * @param userId
     * @return
     */
    ProductViewLog selectProductViewLog(@Param("productId") String productId, @Param("userId") String userId);
}
