/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.ProductEditAuditLog;
/**
 * 产品修改审核日志 服务接口
 * <p>File：ProductEditAuditLogService.java </p>
 * <p>Title: ProductEditAuditLogService </p>
 * <p>Description:ProductEditAuditLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ProductEditAuditLogService extends GenericServiceApi<ProductEditAuditLog>{
    /**
     * 根据产品id查找审核日志
     * @param log
     * @return
     */
    PaginateResult<Map<String, Object>> searchLogList(ProductEditAuditLog log);
}
