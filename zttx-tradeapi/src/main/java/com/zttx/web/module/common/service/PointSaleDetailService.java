/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.PointSaleDetail;

import java.util.List;
import java.util.Map;

/**
 * 返点财务帐销售明细表 服务接口
 * <p>File：PointSaleDetailService.java </p>
 * <p>Title: PointSaleDetailService </p>
 * <p>Description:PointSaleDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface PointSaleDetailService extends GenericServiceApi<PointSaleDetail> {
    /**
     * 分页查询 erp 返点销售明细数据
     * @author 易永耀
     * @param pointSaleDetail 明细数据
     * @param sumMap 明细数据和
     * @return
     */
    PaginateResult selectPointSaleDetailPage(Pagination page,PointSaleDetail pointSaleDetail, Map<String, Object> sumMap) throws BusinessException;

    /**
     * 批量插入
     * @param listPointSaleDetail
     */
    void insertPointSaleDetailBatch(List<PointSaleDetail> listPointSaleDetail) throws BusinessException;
}
