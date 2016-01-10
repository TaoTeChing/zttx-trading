/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.ProductWeightInfo;
import com.zttx.web.module.common.mapper.ProductWeightInfoMapper;

/**
 * ProductWeightInfo 服务实现类
 * <p>File：ProductWeightInfo.java </p>
 * <p>Title: ProductWeightInfo </p>
 * <p>Description:ProductWeightInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductWeightInfoServiceImpl extends GenericServiceApiImpl<ProductWeightInfo> implements ProductWeightInfoService
{

    private ProductWeightInfoMapper productWeightInfoMapper;

    @Autowired(required = true)
    public ProductWeightInfoServiceImpl(ProductWeightInfoMapper productWeightInfoMapper)
    {
        super(productWeightInfoMapper);
        this.productWeightInfoMapper = productWeightInfoMapper;
    }

    @Override
    public PaginateResult<ProductWeightInfo> searchByClient(ProductWeightInfo searchBean, Pagination page) {
        searchBean.setPage(page);
        return new PaginateResult<>(searchBean.getPage(),productWeightInfoMapper.searchByClient(searchBean));
    }
}
