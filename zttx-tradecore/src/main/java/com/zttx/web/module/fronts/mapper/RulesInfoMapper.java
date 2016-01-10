/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.RulesInfo;

/**
 * 规则内容信息 持久层接口
 * <p>File：RulesInfoDao.java </p>
 * <p>Title: RulesInfoDao </p>
 * <p>Description:RulesInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface RulesInfoMapper extends GenericMapper<RulesInfo>
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
    List<RulesInfo> findRulesToSolr(@Param("rulesInfo") RulesInfo rulesInfo, @Param("page") Pagination pagination) throws BusinessException;
    
    /**
     * 根据分类编号取规则信息
     * @param cateId
     * @param pagination
     * @return {@link List}
     */
    List<RulesInfo> getRulesInfoByCateKey(@Param("cateId") String cateId, @Param("page") Pagination pagination);
    
    /**
     * 取最新的规则信息
     * @param pagination
     * @return  {@link List}
     */
    List<RulesInfo> getNewRulesInfo(@Param("page") Pagination pagination);
    
    /**
     * 根据类型统计文章数量
     * @param cateId 类型id
     * @return 数量
     */
    int countArticleByCateId(String cateId);
    
    List<RulesInfo> selectByClient(@Param("page") Pagination page, @Param("searchBean") RulesInfo searchBean);
}
