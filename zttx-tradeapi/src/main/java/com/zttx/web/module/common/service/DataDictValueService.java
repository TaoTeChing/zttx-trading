/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.DataDictValue;

/**
 * 数据字典值 服务接口
 * <p>File：DataDictValueService.java </p>
 * <p>Title: DataDictValueService </p>
 * <p>Description:DataDictValueService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DataDictValueService extends GenericServiceApi<DataDictValue>
{
    /**
     * 添加字典值
     * @param dataDictValue
     *   <p>dictid 不能为空</p>
     *   <p>dictValueName 不能为空</p>
     *   <p>dictValue 不能为空</p>
     *   <p>dictOrder 不能为空</p>
     * @throws BusinessException
     */
    void insertDataDictValue(DataDictValue dataDictValue) throws BusinessException;
    
    /**
     * 更新字典值
     * @param dataDictValue
     * @throws BusinessException
     */
    void updateDataDictValue(DataDictValue dataDictValue) throws BusinessException;
    
    /**
     * 根据 dictCode 的值 查询所有字典可选项
     * @author 章旭楠
     * @param dictCode
     * @return 返回根据dictOrder从小到大排序的List集合, 不存在则返回 empty list
     */
    List<DataDictValue> findByDictCode(String dictCode);
    
    /**
     * 根据 dictId 的值 查询所有字典可选项
     * @author 章旭楠
     * @param dictId
     * @return 返回根据dictOrder从小到大排序的List集合, 不存在则返回 empty list
     */
    List<DataDictValue> findByDictId(String dictId);
    
    /**
     * 更改delState值
     * @param valueId
     * @param delState
     * @throws BusinessException
     */
    void updateDelState(String valueId, Boolean delState) throws BusinessException;
    
    /**
     * 根据 dictCode 的值 查询所有字典中的dictValue ：dictValueName
     * @param dictCode
     * @return Map<String, String>
     * @author 章旭楠
     */
    Map<String, String> findMapByDictCode(String dictCode);
    
    /**
     * 通过字典编码和字典值名称,查询字典值
     * @param dictCode
     * @param dictValueName
     * @return
     * @author 章旭楠
     */
    DataDictValue findDictValue(String dictCode, String dictValueName);
    
    /**
     * 通过字典编码和字典值,查询字典值名称
     * @param dictCode
     * @param dictValue
     * @return
     * @author 周光暖
     */
    String findDictValueName(String dictCode, String dictValue);
    
    /**
     * 指定字典编号下，是否存在字典值
     * @param dictid
     * @return
     * @author 周光暖
     */
    Boolean hasDictValue(String dictid);
    
    /**
     * 列表（分页）
     * @param page
     * @param searchBean
     * @return
     */
    PaginateResult<DataDictValue> searchByClient(Pagination page, DataDictValue searchBean);
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     *
     * @author 章旭楠
     * @throws Exception
     */
    void save(DataDictValue dataDictValue) throws BusinessException;
    
    /**
     * 查询唯一的值（内部支撑调用）
     * @param dictCode
     * @return
     * @author 周光暖
     */
    Integer getSingleDictValue(String dictCode);
}
