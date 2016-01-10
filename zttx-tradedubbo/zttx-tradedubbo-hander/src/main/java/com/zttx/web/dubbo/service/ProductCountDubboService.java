/*
 * @(#)ProductCountDubboService.java 2015-8-19 下午8:21:53
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.web.module.common.entity.ProductCount;

/**
 * <p>File：ProductCountDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-19 下午8:21:53</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface ProductCountDubboService
{
    /**
     * 根据产品id获取产品统计
     * @param productId
     * @return
     */
    ProductCount getByProductId(String productId);
    /**
     * 跟新产品统计
     * @param count
     */
    void update(ProductCount count);
    /**
     * 插入产品统计对象
     * @param count
     */
    void insert(ProductCount count);
}
