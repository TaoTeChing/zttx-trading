/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.brand.entity.BrandAdvice;
import com.zttx.web.module.brand.mapper.BrandAdviceMapper;

/**
 * 品牌商优化建议 服务实现类
 * <p>File：BrandAdvice.java </p>
 * <p>Title: BrandAdvice </p>
 * <p>Description:BrandAdvice </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandAdviceServiceImpl extends GenericServiceApiImpl<BrandAdvice> implements BrandAdviceService
{
    private BrandAdviceMapper brandAdviceMapper;
    
    @Autowired(required = true)
    public BrandAdviceServiceImpl(BrandAdviceMapper brandAdviceMapper)
    {
        super(brandAdviceMapper);
        this.brandAdviceMapper = brandAdviceMapper;
    }
    
    /**
     * 保存建议
     *
     * @param brandAdvice
     */
    @Override
    public void save(BrandAdvice brandAdvice)
    {
        if (StringUtils.isBlank(brandAdvice.getRefrenceId()))
        {
            brandAdvice.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandAdvice.setCreateTime(CalendarUtils.getCurrentLong());
            brandAdviceMapper.insertSelective(brandAdvice);
        }
    }
}
