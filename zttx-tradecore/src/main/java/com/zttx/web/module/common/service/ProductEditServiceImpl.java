/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.ProductEdit;
import com.zttx.web.module.common.mapper.ProductEditMapper;

/**
 * 产品修改 服务实现类
 * <p>File：ProductEdit.java </p>
 * <p>Title: ProductEdit </p>
 * <p>Description:ProductEdit </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ProductEditServiceImpl extends GenericServiceApiImpl<ProductEdit> implements ProductEditService
{

    private ProductEditMapper productEditMapper;

    @Autowired(required = true)
    public ProductEditServiceImpl(ProductEditMapper productEditMapper)
    {
        super(productEditMapper);
        this.productEditMapper = productEditMapper;
    }

    @Override
    public PaginateResult<Map<String, Object>> searchProList(Integer state, ProductBaseInfo info)
    {
        List<Map<String, Object>> list=productEditMapper.searchProList(state,info,info.getPage());
        PaginateResult<Map<String, Object>> result=new PaginateResult<Map<String, Object>>();
        result.setList(list);
        result.setPage(info.getPage());
        return result;
    }
}
