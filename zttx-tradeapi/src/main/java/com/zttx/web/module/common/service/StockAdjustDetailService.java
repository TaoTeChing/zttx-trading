/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.StockAdjustDetail;

import java.util.List;
import java.util.Map;

/**
 * 产品库存调整记录表（该表由调度执行生成） 服务接口
 * <p>File：StockAdjustDetailService.java </p>
 * <p>Title: StockAdjustDetailService </p>
 * <p>Description:StockAdjustDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface StockAdjustDetailService extends GenericServiceApi<StockAdjustDetail> {
    /**
     * 按日期分组分页查询 返点财务库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @param sumMap
     * @return
     */
    PaginateResult selectStockAdjustDetailGroupByDatePage(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap) throws BusinessException;
    /**
     * 按产品分组分页查询 返点财务库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @param sumMap
     * @return
     */
    PaginateResult selectStockAdjustDetailGroupByProductPage(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap) throws BusinessException;

    /**
     * 按产品sku分页查询 返点库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @param sumMap
     * @return
     */
    PaginateResult selectStockAdjustDetailBySku(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap) throws BusinessException;
    /**
     * 按日期和类别sku分页查询 返点库存情况
     * @author 易永耀
     * @param page
     * @param stockAdjustDetail
     * @param sumMap
     * @return
     */
    PaginateResult selectStockAdjustDetailByTimeAndSource(Pagination page, StockAdjustDetail stockAdjustDetail, Map<String, Object> sumMap) throws BusinessException;

    /**
     * 查询 返点库存详细 产品sku分类 产品sku详情头信息
     * @author 易永耀
     * @param stockAdjustDetail
     * @return
     *   brandsName   string
     *   productTitle   string
     *   productNo      string
     *   productSkuName string
     *   type  Map<>
     *
     */
    Map<String,Object> selectHeadData(StockAdjustDetail stockAdjustDetail) throws BusinessException;

    /**
     * 插入数据
     * @author 易永耀
     * @param type
     * @param objectList
     *    type = 0 发货 object : DealerOrders
     *    type = 1 销售 object : PointSaleDetail
     *    type = 2 退货 object : PointRefund
     *    type = 3 授信转返点 object:CreditToPoint
     */
      <T> void addStockAdjustDetail(Integer type,List<T> objectList) throws BusinessException;


}
