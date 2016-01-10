/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.DataDict;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.mapper.DataDictMapper;
import com.zttx.web.module.common.mapper.DataDictValueMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 数据字典值 服务实现类
 * <p>File：DataDictValue.java </p>
 * <p>Title: DataDictValue </p>
 * <p>Description:DataDictValue </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DataDictValueServiceImpl extends GenericServiceApiImpl<DataDictValue> implements DataDictValueService
{
    private DataDictValueMapper dataDictValueMapper;
    
    @Autowired
    private DataDictMapper      dataDictMapper;
    
    @Autowired(required = true)
    public DataDictValueServiceImpl(DataDictValueMapper dataDictValueMapper)
    {
        super(dataDictValueMapper);
        this.dataDictValueMapper = dataDictValueMapper;
    }
    
    /**
     * 添加字典值
     * @param dataDictValue 自动设置refrenceId,createTime,delState
     * @throws BusinessException
     */
    @Override
    public void insertDataDictValue(DataDictValue dataDictValue) throws BusinessException
    {
        dataDictValue.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dataDictValue.setCreateTime(CalendarUtils.getCurrentLong());
        String dictId = dataDictValue.getDictid();
        DataDict dataDict = dataDictMapper.selectByPrimaryKey(dictId);
        if (dataDict == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        dataDictValue.setDictCode(dataDict.getDictCode());
        dataDictValue.setDictid(dataDict.getRefrenceId());
        super.insertSelective(dataDictValue);
    }
    
    /**
     * 更新字典值
     * @param dataDictValue
     * @throws BusinessException
     */
    @Override
    public void updateDataDictValue(DataDictValue dataDictValue) throws BusinessException
    {
        String id = dataDictValue.getRefrenceId();
        DataDictValue dbValue = this.selectByPrimaryKey(id);
        if (dbValue == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        dbValue.setDictOrder(dataDictValue.getDictOrder());
        dbValue.setDictValue(dataDictValue.getDictValue());
        dbValue.setDictValueName(dataDictValue.getDictValueName());
        dbValue.setUpdateTime(CalendarUtils.getCurrentLong());
        dbValue.setRemark(dataDictValue.getRemark());
        if (dataDictValue.getDelFlag() != null)
        {
            dbValue.setDelFlag(dataDictValue.getDelFlag());
        }
        this.updateByPrimaryKeySelective(dbValue);
    }
    
    /**
     * 根据 dictCode 的值 查询所有字典可选项
     * @author 章旭楠
     * @param dictCode
     * @return 返回根据dictOrder从小到大排序的List集合, 不存在则返回 empty list
     */
    @Override
    public List<DataDictValue> findByDictCode(String dictCode)
    {
        List<DataDictValue> list = Lists.newArrayList();
        if (StringUtils.isNotBlank(dictCode))
        {
            DataDict dataDict = dataDictMapper.findByDictCode(dictCode);
            if (dataDict == null) { return list; }
            DataDictValue dataDictValue = new DataDictValue();
            dataDictValue.setDictid(dataDict.getRefrenceId());
            list = dataDictValueMapper.findList(dataDictValue);
        }
        return list;
    }
    
    /**
     * 根据 dictId 的值 查询所有字典可选项
     * @author 章旭楠
     * @param dictId
     * @return 返回根据dictOrder从小到大排序的List集合, 不存在则返回 empty list
     */
    @Override
    public List<DataDictValue> findByDictId(String dictId)
    {
        List<DataDictValue> list = Lists.newArrayList();
        if (StringUtils.isNotBlank(dictId))
        {
            DataDictValue dataDictValue = new DataDictValue();
            dataDictValue.setDictid(dictId);
            list = dataDictValueMapper.findList(dataDictValue);
        }
        return list;
    }
    
    /**
     * 更改delState值
     * @param valueId
     * @param delState
     * @throws BusinessException
     */
    @Override
    public void updateDelState(String valueId, Boolean delState) throws BusinessException
    {
        DataDictValue dbValue = dataDictValueMapper.selectByPrimaryKey(valueId);
        if (dbValue == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        dbValue.setDelFlag(delState);
        dbValue.setUpdateTime(CalendarUtils.getCurrentLong());
        dataDictValueMapper.updateByPrimaryKeySelective(dbValue);
    }
    
    /**
     * 根据 dictCode 的值 查询所有字典中的dictValue ：dictValueName
     * @param dictCode
     * @return Map<String, String>
     * @author 章旭楠
     */
    @Override
    public Map<String, String> findMapByDictCode(String dictCode)
    {
        Map<String, String> map = Maps.newHashMap();
        List<DataDictValue> list = this.findByDictCode(dictCode);
        for (DataDictValue dataDictValue : list)
        {
            map.put(dataDictValue.getDictValue(), dataDictValue.getDictValueName());
        }
        return map;
    }
    
    /**
     * 通过字典编码和字典值名称,查询字典值
     * @param dictCode 不能为null
     * @param dictValueName 不能为null
     * @return 查不到就返回null
     * @author 徐志勇
     */
    @Override
    public DataDictValue findDictValue(String dictCode, String dictValueName)
    {
        DataDictValue dataDictValue = null;
        if (StringUtils.isNotBlank(dictCode) && StringUtils.isNotBlank(dictValueName))
        {
            DataDict dataDict = dataDictMapper.findByDictCode(dictCode);
            if (dataDict == null) { return dataDictValue; }
            dataDictValue = new DataDictValue();
            dataDictValue.setDictid(dataDict.getRefrenceId());
            dataDictValue.setDictValueName(dictValueName);
            List<DataDictValue> dataDictValues = dataDictValueMapper.findList(dataDictValue);
            if (null != dataDictValues && dataDictValues.size() == 1)
            {
                dataDictValue = dataDictValues.get(0);
            }
            else
            {
                dataDictValue = null;
            }
        }
        return dataDictValue;
    }
    
    /**
     * 通过字典编码和字典值,查询字典值名称
     * @param dictCode
     * @param dictValue
     * @return
     * @author 周光暖
     */
    @Override
    public String findDictValueName(String dictCode, String dictValue)
    {
        DataDictValue dataDictValue = new DataDictValue();
        if (StringUtils.isNotBlank(dictCode) && StringUtils.isNotBlank(dictValue))
        {
            DataDict dataDict = dataDictMapper.findByDictCode(dictCode);
            if (dataDict == null) { return ""; }
            dataDictValue = new DataDictValue();
            dataDictValue.setDictid(dataDict.getRefrenceId());
            dataDictValue.setDictValue(dictValue);
            List<DataDictValue> dataDictValues = dataDictValueMapper.findList(dataDictValue);
            if (null != dataDictValues && dataDictValues.size() == 1)
            {
                dataDictValue = dataDictValues.get(0);
            }
        }
        return dataDictValue.getDictValueName();
    }
    
    /**
     * 指定字典编号下，是否存在字典值
     * @param dictId
     * @return
     * @author 周光暖
     */
    @Override
    public Boolean hasDictValue(String dictId)
    {
        DataDictValue dataDictValue = new DataDictValue();
        dataDictValue.setDictid(dictId);
        long count = dataDictValueMapper.countBySelective(dataDictValue);
        return count > 0;
    }
    
    /**
     * 列表（分页）
     * @param page
     * @param searchBean
     * @return
     */
    @Override
    public PaginateResult<DataDictValue> searchByClient(Pagination page, DataDictValue searchBean)
    {
        // dictCode 冗余，转 dictId
        if (StringUtils.isNotBlank(searchBean.getDictCode()))
        {
            DataDict dataDict = dataDictMapper.findByDictCode(searchBean.getDictCode());
            if (dataDict != null)
            {
                searchBean.setDictid(dataDict.getRefrenceId());
                searchBean.setDictCode(null);
            }
        }
        searchBean.setPage(page);
        return new PaginateResult(searchBean.getPage(), dataDictValueMapper.findList(searchBean));
    }
    
    @Override
    public void save(DataDictValue dataDictValue) throws BusinessException
    {
        DataDict dataDict = dataDictMapper.selectByPrimaryKey(dataDictValue.getDictid());
        if (null == dataDict) { throw new BusinessException(ClientConst.PARAMERROR.getCode(), "字典编号不存在！"); }
        if (StringUtils.isBlank(dataDictValue.getRefrenceId()))
        {
            dataDictValue.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dataDictValue.setCreateTime(CalendarUtils.getCurrentLong());
            dataDictValue.setDictCode(dataDict.getDictCode());// 冗余字段
            dataDictValueMapper.insertSelective(dataDictValue);
        }
        else
        {
            dataDictValue.setUpdateTime(CalendarUtils.getCurrentLong());
            dataDictValueMapper.updateByPrimaryKeySelective(dataDictValue);
        }
    }
    
    /**
     * 查询唯一的值（内部支撑调用）
     *
     * @param dictCode 数据字典编号
     * @return Integer
     * @author 周光暖
     */
    @Override
    public Integer getSingleDictValue(String dictCode)
    {
        Integer value = null;
        List<DataDictValue> dataDicts = this.findByDictCode(dictCode);
        if (null != dataDicts && !dataDicts.isEmpty())
        {
            value = dataDicts.get(0).getDictValue() == null ? null : Integer.parseInt(dataDicts.get(0).getDictValue());
        }
        return value;
    }
}
