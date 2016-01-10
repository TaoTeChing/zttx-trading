/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.RegionalCode;
import com.zttx.web.module.common.mapper.RegionalCodeMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 地区编码与常规描述的转换表 服务实现类
 * <p>File：RegionalCode.java </p>
 * <p>Title: RegionalCode </p>
 * <p>Description:RegionalCode </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RegionalCodeServiceImpl extends GenericServiceApiImpl<RegionalCode> implements RegionalCodeService
{
    private RegionalCodeMapper regionalCodeMapper;
    
    @Autowired(required = true)
    public RegionalCodeServiceImpl(RegionalCodeMapper regionalCodeMapper)
    {
        super(regionalCodeMapper);
        this.regionalCodeMapper = regionalCodeMapper;
    }
    
    @Override
    public void saveByClient(RegionalCode regionalCode) throws BusinessException
    {
        if (this.isExistsCode(regionalCode.getRefrenceId(), regionalCode.getAreaNos())) { throw new BusinessException(CommonConst.DATA_EXISTS.code, "地区编码已存在"); }
        if (this.isExistsName(regionalCode.getRefrenceId(), regionalCode.getAreaName())) { throw new BusinessException(CommonConst.DATA_EXISTS.code, "地区名称已存在"); }
        if (!StringUtils.isBlank(regionalCode.getRefrenceId()))
        {
            RegionalCode regionalCode_ = regionalCodeMapper.selectByPrimaryKey(regionalCode.getRefrenceId());
            if (null == regionalCode_) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            regionalCode.setUpdateTime(CalendarUtils.getCurrentLong());
            regionalCodeMapper.updateByPrimaryKeySelective(regionalCode);
        }
        else
        {
            regionalCode.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            regionalCode.setCreateTime(CalendarUtils.getCurrentLong());
            regionalCode.setUpdateTime(CalendarUtils.getCurrentLong());
            regionalCodeMapper.insertSelective(regionalCode);
        }
    }
    
    @Override
    public boolean isExistsName(String refrenceId, String areaName)
    {
        return regionalCodeMapper.countBy(refrenceId, null, areaName) > 0;
    }
    
    @Override
    public boolean isExistsCode(String refrenceId, String areaNos)
    {
        return regionalCodeMapper.countBy(refrenceId, areaNos, null) > 0;
    }
    
    @Override
    public RegionalCode getRegionalCode(String refrenceId, String areaNos, String areaName) throws BusinessException
    {
        if (StringUtils.isEmpty(refrenceId) && StringUtils.isEmpty(areaNos) && StringUtils.isEmpty(areaName)) { throw new BusinessException("参数不能全为空"); }
        return regionalCodeMapper.selectRegionalCode(refrenceId, areaNos, areaName);
    }
}
