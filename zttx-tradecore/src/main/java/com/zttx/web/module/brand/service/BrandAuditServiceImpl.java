/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandAudit;
import com.zttx.web.module.brand.mapper.BrandAuditMapper;

/**
 * 品牌商审核日志 服务实现类
 * <p>File：BrandAudit.java </p>
 * <p>Title: BrandAudit </p>
 * <p>Description:BrandAudit </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandAuditServiceImpl extends GenericServiceApiImpl<BrandAudit> implements BrandAuditService
{

    private BrandAuditMapper brandAuditMapper;

    @Autowired(required = true)
    public BrandAuditServiceImpl(BrandAuditMapper brandAuditMapper)
    {
        super(brandAuditMapper);
        this.brandAuditMapper = brandAuditMapper;
    }
}
