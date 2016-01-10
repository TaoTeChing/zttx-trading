/*
 * @(#)ProductCountDubboServiceImpl.java 2015-8-19 下午8:22:10
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.zttx.web.module.common.entity.ProductCount;
import com.zttx.web.module.common.service.ProductCountService;
import org.springframework.stereotype.Service;

/**
 * <p>File：ProductCountDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-19 下午8:22:10</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class ProductCountDubboServiceImpl implements ProductCountDubboService
{
    @Autowired
    private ProductCountService productCountService;
    
    @Override
    public ProductCount getByProductId(String productId)
    {
        
        return productCountService.selectByPrimaryKey(productId);
    }

    @Override
    public void update(ProductCount count)
    {
        productCountService.updateByPrimaryKeySelective(count);        
    }

    @Override
    public void insert(ProductCount count)
    {
        productCountService.insert(count);        
    }
}
