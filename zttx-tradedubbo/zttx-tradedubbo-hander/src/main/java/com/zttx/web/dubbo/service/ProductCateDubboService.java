/*
 * @(#)ProductCateService.java 2015-8-19 下午4:56:40
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.List;

import com.zttx.web.module.common.entity.ProductCate;

/**
 * <p>File：ProductCateService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-19 下午4:56:40</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface ProductCateDubboService
{
    void deleteByProductId(String productId);
    
    void insertBatch(List<ProductCate> cateList);
}
