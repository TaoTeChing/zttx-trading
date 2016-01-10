/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.HelpInfo;

/**
 * 帮助内容 服务接口
 * <p>File：HelpInfoService.java </p>
 * <p>Title: HelpInfoService </p>
 * <p>Description:HelpInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface HelpInfoService extends GenericServiceApi<HelpInfo>
{
    /**
     *  查询帮助内容索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param helpInfo
     * @return  {@link Long}
     * @throws BusinessException
     */
    Long findHelpToSolrCount(HelpInfo helpInfo) throws BusinessException;
    
    /**
     * 查询帮助内容索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param helpInfo
     * @param pagination
     * @return {@link List}
     * @throws BusinessException
     */
    List<HelpInfo> findHelpToSolr(HelpInfo helpInfo, Pagination pagination) throws BusinessException;
    
    /**
     * 根据帮助类别编号取帮助信息
     * @param cateId
     * @param pagination
     * @return {@link List}
     */
    List<HelpInfo> getInfosByHelpCateId(String cateId, Pagination pagination);
    
    /**
     * 分页查询 （支撑接口调用）
     * @param page
     * @param searchBean
     * @return
     */
    PaginateResult<HelpInfo> selectByClient(Pagination page, HelpInfo searchBean);
    
    /**
     * 保存帮助信息
     * @param helpInfo
     */
    HelpInfo saveByClient(HelpInfo helpInfo) throws BusinessException;
}
