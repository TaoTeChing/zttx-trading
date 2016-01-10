/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import java.util.List;
import java.util.Map;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.brand.entity.BrandNetimg;
import com.zttx.web.module.brand.mapper.BrandNetimgMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 品牌商经销网络图片信息 服务实现类
 * <p>File：BrandNetimg.java </p>
 * <p>Title: BrandNetimg </p>
 * <p>Description:BrandNetimg </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandNetimgServiceImpl extends GenericServiceApiImpl<BrandNetimg> implements BrandNetimgService
{

    private BrandNetimgMapper brandNetimgMapper;

    @Autowired(required = true)
    public BrandNetimgServiceImpl(BrandNetimgMapper brandNetimgMapper)
    {
        super(brandNetimgMapper);
        this.brandNetimgMapper = brandNetimgMapper;
    }

    @Override
    public List<Map<String,Object>> getBrandNetimgList(String networkId)
    {
        return brandNetimgMapper.getBrandNetimgList(networkId);
    }
}
