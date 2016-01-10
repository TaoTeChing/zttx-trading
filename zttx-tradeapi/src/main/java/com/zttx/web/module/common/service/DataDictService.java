/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.DataDict;

/**
 * 数据字典 服务接口
 * <p>File：DataDictService.java </p>
 * <p>Title: DataDictService </p>
 * <p>Description:DataDictService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DataDictService extends GenericServiceApi<DataDict>
{
    /**
     * 列表（分页）
     * @param page
     * @param searchBean
     * @return
     */
    PaginateResult<DataDict> searchByClient(Pagination page, DataDict searchBean);
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     * @param dataDict  dictName ,dictCode (必填)
     * @author 周光暖
     */
    void save(DataDict dataDict);
    
    /**
     * 是否已经存在dictCode
     * @param dictCode 代码
     * @param refrenceId 主键（排除自身） 可以null
     * @return Boolean true:存在  false：不存在
     */
    Boolean isDictCodeExist(String dictCode, String refrenceId);
}
