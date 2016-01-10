/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.ProductEditAuditLog;
import com.zttx.web.module.common.mapper.ProductEditAuditLogMapper;

/**
 * 产品修改审核日志 服务实现类
 * <p>File：ProductEditAuditLog.java </p>
 * <p>Title: ProductEditAuditLog </p>
 * <p>Description:ProductEditAuditLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductEditAuditLogServiceImpl extends GenericServiceApiImpl<ProductEditAuditLog> implements ProductEditAuditLogService
{

    private ProductEditAuditLogMapper productEditAuditLogMapper;

    @Autowired(required = true)
    public ProductEditAuditLogServiceImpl(ProductEditAuditLogMapper productEditAuditLogMapper)
    {
        super(productEditAuditLogMapper);
        this.productEditAuditLogMapper = productEditAuditLogMapper;
    }

    @Override
    public PaginateResult<Map<String, Object>> searchLogList(ProductEditAuditLog log)
    {
        List<Map<String,Object>> list=productEditAuditLogMapper.searchLogList(log);
        PaginateResult<Map<String, Object>> result=new PaginateResult<Map<String, Object>>();
        result.setList(list);
        result.setPage(log.getPage());
        return result;
    }
}
