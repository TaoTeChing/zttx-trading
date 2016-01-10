/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerShoper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 经销商购物车 持久层接口
 * <p>File：DealerShoperDao.java </p>
 * <p>Title: DealerShoperDao </p>
 * <p>Description:DealerShoperDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerShoperMapper extends GenericMapper<DealerShoper>
{
    Long getShoperCountByUserId(String userId);

    /**
     * 根据产品Id与经销商id 获取购物车信息 确保唯一性 用于判断该产品是否已经存在该经销商购物车中
     * @param dealerId
     * @param productId
     * @return
     * @author 易永耀
     */
    DealerShoper selectDealerShoperBy(String dealerId, String productId);
    /**
     * 获取 该经销商 购物车有效车单 isHome 是否是首页的购物车
     * @authro 易永耀
     */
    List<DealerShoper> selectDealerShoperByDealerId(@Param("dealerId") String dealerId, @Param("isHome") Boolean isHome);

    /**
     * 删除 dealerId 下的所有车单
     * @param dealerId
     * @return
     * @author 易永耀
     */
    int deleteDealerShoperAndShopers(String dealerId);
    /**
     * 根据 shopId 获取已经加车并将结算车单
     * @param shopIds
     * @return
     * @author 易永耀
     */
    List<DealerShoper> selectDealerShoperList(String... shopIds);
}
