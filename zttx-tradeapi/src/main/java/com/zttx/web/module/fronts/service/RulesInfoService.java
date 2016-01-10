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
import com.zttx.web.module.fronts.entity.RulesInfo;

/**
 * 规则内容信息 服务接口
 * <p>File：RulesInfoService.java </p>
 * <p>Title: RulesInfoService </p>
 * <p>Description:RulesInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface RulesInfoService extends GenericServiceApi<RulesInfo>
{
    /**
     *  查询规则内容索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param rulesInfo
     * @return  {@link Long}
     * @throws BusinessException
     */
    Long findRulesToSolrCount(RulesInfo rulesInfo) throws BusinessException;
    
    /**
     * 查询规则内容索引有关的数据
     * <p>
     *    productInfo 为null时返回所有数据
     * </p>
     * @param rulesInfo
     * @param pagination
     * @return {@link List}
     * @throws BusinessException
     */
    List<RulesInfo> findRulesToSolr(RulesInfo rulesInfo, Pagination pagination) throws BusinessException;
    
    /**
     * 根据分类编号取规则信息
     * @param cateId
     * @param pagination
     * @return {@link List}
     */
    List<RulesInfo> getRulesInfoByCateKey(String cateId, Pagination pagination);
    
    /**
     * 取最新的规则信息
     * @param pagination
     * @return  {@link List}
     */
    List<RulesInfo> getNewRulesInfo(Pagination pagination);
    
    /**
     * 该类型下面是否存在文章
     * @param cateId
     * @return
     */
    boolean hasArticle(String cateId);
    
    /**
     * 保存（提供给接口）
     * @param rulesInfo 规则信息
     * @throws BusinessException
     */
    RulesInfo saveByClient(RulesInfo rulesInfo) throws BusinessException;
    
    /**
     * 分页查询（提供给接口）
     * @param page 分页对象
     * @param searchBean 查询条件
     * @return PaginateResult
     */
    PaginateResult<RulesInfo> selectByClient(Pagination page, RulesInfo searchBean);
}
