/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.CommonConst;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.brand.entity.BrandesWeightInfo;
import com.zttx.web.module.brand.mapper.BrandesWeightInfoMapper;

/**
 * 品牌权重信息 服务实现类
 * <p>File：BrandesWeightInfo.java </p>
 * <p>Title: BrandesWeightInfo </p>
 * <p>Description:BrandesWeightInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 章旭楠
 * @version 1.0
 */
@Service
public class BrandesWeightInfoServiceImpl extends GenericServiceApiImpl<BrandesWeightInfo> implements BrandesWeightInfoService
{
    private BrandesWeightInfoMapper brandesWeightInfoMapper;
    
    @Autowired(required = true)
    public BrandesWeightInfoServiceImpl(BrandesWeightInfoMapper brandesWeightInfoMapper)
    {
        super(brandesWeightInfoMapper);
        this.brandesWeightInfoMapper = brandesWeightInfoMapper;
    }
    
    @Override
    public void save(BrandesWeightInfo brandesWeightInfo) throws BusinessException
    {
        if (StringUtils.isNotBlank(brandesWeightInfo.getRefrenceId()))
        {
            BrandesWeightInfo oldBrandesWeightInfo = brandesWeightInfoMapper.selectByPrimaryKey(brandesWeightInfo.getRefrenceId());
            if (oldBrandesWeightInfo == null) { throw new BusinessException(CommonConst.FAIL); }
            brandesWeightInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            brandesWeightInfoMapper.updateByPrimaryKeySelective(brandesWeightInfo);
        }
        else
        {
            brandesWeightInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandesWeightInfo.setCreateTime(CalendarUtils.getCurrentLong());
            brandesWeightInfoMapper.insertSelective(brandesWeightInfo);
        }
    }

    @Override
    public PaginateResult<BrandesWeightInfo> searchByClient(BrandesWeightInfo searchBean, Pagination page) {
        searchBean.setPage(page);
        return new PaginateResult<>(searchBean.getPage(),brandesWeightInfoMapper.searchByClient(searchBean));
    }
}
