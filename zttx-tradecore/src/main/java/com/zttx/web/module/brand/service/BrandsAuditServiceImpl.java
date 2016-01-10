/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandsAudit;
import com.zttx.web.module.brand.mapper.BrandsAuditMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌审核日志 服务实现类
 * <p>File：BrandsAudit.java </p>
 * <p>Title: BrandsAudit </p>
 * <p>Description:BrandsAudit </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandsAuditServiceImpl extends GenericServiceApiImpl<BrandsAudit> implements BrandsAuditService
{

    private BrandsAuditMapper brandsAuditMapper;

    @Autowired(required = true)
    public BrandsAuditServiceImpl(BrandsAuditMapper brandsAuditMapper)
    {
        super(brandsAuditMapper);
        this.brandsAuditMapper = brandsAuditMapper;
    }
}
