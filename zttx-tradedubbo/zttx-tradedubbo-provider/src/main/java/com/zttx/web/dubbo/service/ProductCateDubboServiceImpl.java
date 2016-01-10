/*
 * @(#)ProductCateServiceImpl.java 2015-8-19 下午4:57:43
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.web.module.common.entity.ProductCate;
import com.zttx.web.module.common.mapper.ProductCateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>File：ProductCateServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-19 下午4:57:43</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class ProductCateDubboServiceImpl implements ProductCateDubboService
{
    @Autowired
    private ProductCateMapper productCateMapper;

    @Override
    public void deleteByProductId(String productId)
    {
        productCateMapper.deleteByProductId(productId);
    }

    @Override
    public void insertBatch(List<ProductCate> cateList)
    {
        productCateMapper.insertBatch(cateList);
    }
}
