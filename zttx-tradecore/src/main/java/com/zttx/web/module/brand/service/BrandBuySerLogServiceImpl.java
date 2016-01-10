/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandBuySerLog;
import com.zttx.web.module.brand.mapper.BrandBuySerLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 品牌商购买的服务记录 服务实现类
 * <p>File：BrandBuySerLog.java </p>
 * <p>Title: BrandBuySerLog </p>
 * <p>Description:BrandBuySerLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandBuySerLogServiceImpl extends GenericServiceApiImpl<BrandBuySerLog> implements BrandBuySerLogService
{

    private BrandBuySerLogMapper brandBuySerLogMapper;

    @Autowired(required = true)
    public BrandBuySerLogServiceImpl(BrandBuySerLogMapper brandBuySerLogMapper)
    {
        super(brandBuySerLogMapper);
        this.brandBuySerLogMapper = brandBuySerLogMapper;
    }

    @Override
    public List<BrandBuySerLog> listWithException(String brandId, String[] idArr) throws BusinessException {
        List<BrandBuySerLog> list = Lists.newArrayList();
        for (int i = 0; i < idArr.length; i++)
        {
            BrandBuySerLog obj = brandBuySerLogMapper.findById(brandId, idArr[i]);
            if (null == obj) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            list.add(obj);
        }
        return list;
    }
}
