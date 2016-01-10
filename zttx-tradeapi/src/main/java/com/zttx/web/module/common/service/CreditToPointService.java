/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.CreditToPoint;

import java.util.Map;

/**
 * erp铺货变返点sku详细信息表 服务接口
 * <p>File：CreditToPointService.java </p>
 * <p>Title: CreditToPointService </p>
 * <p>Description:CreditToPointService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface CreditToPointService extends GenericServiceApi<CreditToPoint> {
    /**
     * 分页查询 授信转返点数据 并不分页求和
     * @author 易永耀
     * @param page
     * @param creditToPoint
     * @param sumMap  数据累计和
     * @return
     */
    PaginateResult<Map<String,Object>> selectCreditToPointPage(Pagination page, CreditToPoint creditToPoint, Map<String, Object> sumMap) throws BusinessException;
}
