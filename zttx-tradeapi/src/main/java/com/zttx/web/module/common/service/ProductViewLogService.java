/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.ProductViewLog;
import com.zttx.web.module.dealer.model.ProductFilter;

import java.util.List;
import java.util.Map;

/**
 * 商品浏览历史记录 服务接口
 * <p>File：ProductViewLogService.java </p>
 * <p>Title: ProductViewLogService </p>
 * <p>Description:ProductViewLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductViewLogService extends GenericServiceApi<ProductViewLog>{

    /**
     * 分页获取经销商浏览商品信息
     * @param pagination
     * @param filter
     * @return
     * @author 易永耀
     */
    PaginateResult<Map<String,Object>> selectViewLogProductPage(Pagination pagination, ProductFilter filter) throws BusinessException;
    /**
     * 获取经销商浏览商品信息 品牌分类
     * @return
     * @author 易永耀
     */
    List<Map<String,Object>> selectViewLogCata(String dealerId);

    /**
     * 经销商 浏览记录 批量/单一 删除
     * @param productViewLogsId
     * @return
     */
    int  batchRemoveHistoryProduct(List<String> productViewLogsId);

    /**
     * 新增  浏览记录
      * @param productViewLog
     */
    ProductViewLog saveProductViewLog(ProductViewLog productViewLog);
}
