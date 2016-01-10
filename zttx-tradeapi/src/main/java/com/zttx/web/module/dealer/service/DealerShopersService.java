/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;


import com.alibaba.fastjson.JSONObject;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerShopers;

import java.util.List;

/**
 * 购物详细信息表 服务接口
 * <p>File：DealerShopersService.java </p>
 * <p>Title: DealerShopersService </p>
 * <p>Description:DealerShopersService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerShopersService extends GenericServiceApi<DealerShopers>{
    /**
     * 根据 购物车 主表Id 获取购物车副表 中购物车单详细sku信息
     * @param shoperId
     * @return
     * @author 易永耀
     */
    List<DealerShopers> selectDealerShopersBy(String shoperId,short productType);

    /**
     * 批量更新购物车中的 购买数量 和总价钱
     * @param jsonObjectList
     * @return
     */
    int batchUpdateShopers(List<JSONObject> jsonObjectList) throws BusinessException;

    /**
     * 根据 skuId 和shoperId 获取购物车单sku单个信息详情
     * @param shoperId
     * @param productSkuId
     * @return
     * @author 易永耀
     */
    DealerShopers selectDealerShopersBy(String shoperId, String productSkuId);
}
