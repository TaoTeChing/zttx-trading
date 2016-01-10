/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandTemplate;
import com.zttx.web.module.brand.mapper.BrandTemplateMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌预订模板 服务实现类
 * <p>File：BrandTemplate.java </p>
 * <p>Title: BrandTemplate </p>
 * <p>Description:BrandTemplate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandTemplateServiceImpl extends GenericServiceApiImpl<BrandTemplate> implements BrandTemplateService
{

    private BrandTemplateMapper brandTemplateMapper;

    @Autowired(required = true)
    public BrandTemplateServiceImpl(BrandTemplateMapper brandTemplateMapper)
    {
        super(brandTemplateMapper);
        this.brandTemplateMapper = brandTemplateMapper;
    }

    @Override
    public List<BrandTemplate> listBrandTemplate(String brandId, String brandsId)
    {
        return brandTemplateMapper.listBrandTemplate(brandId,brandsId);
    }
}
