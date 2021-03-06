/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.ProductEditAuditLog;

/**
 * 产品修改审核日志 持久层接口
 * <p>File：ProductEditAuditLogDao.java </p>
 * <p>Title: ProductEditAuditLogDao </p>
 * <p>Description:ProductEditAuditLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductEditAuditLogMapper extends GenericMapper<ProductEditAuditLog>
{
    /**
     * 根据产品id查找审核日志
     * @param log
     * @return
     */
    List<Map<String,Object>> searchLogList(ProductEditAuditLog log);
}
