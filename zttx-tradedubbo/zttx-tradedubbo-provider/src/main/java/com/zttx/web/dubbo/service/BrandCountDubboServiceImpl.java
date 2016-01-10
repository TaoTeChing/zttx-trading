/*
 * @(#)BrandCountDubboServiceImpl.java 2015-8-24 上午10:32:59
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.service.BrandCountService;
import org.springframework.stereotype.Service;

/**
 * <p>File：BrandCountDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-24 上午10:32:59</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class BrandCountDubboServiceImpl implements BrandCountDubboService
{
    @Autowired
    private BrandCountService brandCountServcie;

    @Override
    public void modifyBrandCountCache(String brandId, Integer[] countType)throws BusinessException
    {
        brandCountServcie.modifyBrandCount(brandId, countType);        
    }
}
