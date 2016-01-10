/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.web.module.common.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.web.module.common.mapper.ProductImageMapper;

import java.util.List;

/**
 * 品牌商产品图片信息 服务实现类
 * <p>File：ProductImage.java </p>
 * <p>Title: ProductImage </p>
 * <p>Description:ProductImage </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductImageServiceImpl implements ProductImageService
{
    @Autowired(required = true)
    private ProductImageMapper productImageMapper;
    
    @Override
    public List<ProductImage> getProductImagesByProductId(String productId)
    {
        return productImageMapper.getProductImagesByProductId(productId);
    }
}
