/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandLogistics;
import com.zttx.web.module.brand.mapper.BrandLogisticsMapper;

/**
 * 品牌商物流公司 服务实现类
 * <p>File：BrandLogistics.java </p>
 * <p>Title: BrandLogistics </p>
 * <p>Description:BrandLogistics </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandLogisticsServiceImpl extends GenericServiceApiImpl<BrandLogistics> implements BrandLogisticsService
{

    private BrandLogisticsMapper brandLogisticsMapper;

    @Autowired(required = true)
    public BrandLogisticsServiceImpl(BrandLogisticsMapper brandLogisticsMapper)
    {
        super(brandLogisticsMapper);
        this.brandLogisticsMapper = brandLogisticsMapper;
    }

    @Override
    public List<BrandLogistics> listByBrandId(String brandId) {
        return brandLogisticsMapper.listByBrandId(brandId);
    }
    
    @Override
    public Boolean isExits(String brandId, String logisName)
    {
        List<BrandLogistics> list = brandLogisticsMapper.listByBrandId(brandId);
        Iterator<BrandLogistics> it = list.iterator();
        while (it.hasNext())
        {
            BrandLogistics brandLogistics = it.next();
            if (brandLogistics.getLogisticName().equals(logisName)) { return true; }
        }
        return false;
    }
}
