/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.module.common.entity.DataDict;
import com.zttx.web.module.common.mapper.DataDictMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 数据字典 服务实现类
 * <p>File：DataDict.java </p>
 * <p>Title: DataDict </p>
 * <p>Description:DataDict </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DataDictServiceImpl extends GenericServiceApiImpl<DataDict> implements DataDictService
{
    private DataDictMapper dataDictMapper;
    
    @Autowired(required = true)
    public DataDictServiceImpl(DataDictMapper dataDictMapper)
    {
        super(dataDictMapper);
        this.dataDictMapper = dataDictMapper;
    }
    
    @Override
    public PaginateResult<DataDict> searchByClient(Pagination page, DataDict searchBean)
    {
        // page.setOrderBy(" a.dictOrder desc,a.createTime desc ");
        searchBean.setPage(page);
        return new PaginateResult(searchBean.getPage(), dataDictMapper.searchByClient(searchBean));
    }
    
    @Override
    public void save(DataDict dataDict)
    {
        if (this.isDictCodeExist(dataDict.getDictCode(), dataDict.getRefrenceId())) { throw new IllegalArgumentException("字典编码已经存在！"); }
        if (StringUtils.isBlank(dataDict.getRefrenceId()))
        {
            dataDict.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dataDict.setCreateTime(CalendarUtils.getCurrentLong());
            dataDictMapper.insertSelective(dataDict);
        }
        else
        {
            dataDict.setUpdateTime(CalendarUtils.getCurrentLong());
            dataDictMapper.updateByPrimaryKeySelective(dataDict);
        }
    }
    
    /**
     * 是否已经存在dictCode
     * @param dictCode 代码
     * @param refrenceId 主键（排除自身） 可以null
     * @return Boolean true:存在  false：不存在
     */
    @Override
    public Boolean isDictCodeExist(String dictCode, String refrenceId)
    {
        return this.dataDictMapper.countByDictCode(dictCode, refrenceId) > 0;
    }
}
