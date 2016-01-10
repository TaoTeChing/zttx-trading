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
import com.zttx.web.module.fronts.entity.HelpInfo;

/**
 * 帮助内容 持久层接口
 * <p>File：HelpInfoDao.java </p>
 * <p>Title: HelpInfoDao </p>
 * <p>Description:HelpInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface HelpInfoMapper extends GenericMapper<HelpInfo>
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
    List<HelpInfo> findHelpToSolr(@Param("helpInfo") HelpInfo helpInfo, @Param("page") Pagination pagination) throws BusinessException;
    
    /**
     * 根据帮助类别编号取帮助信息
     * @param cateId
     * @param pagination
     * @return {@link List}
     */
    List<HelpInfo> getInfosByHelpCateId(@Param("cateId") String cateId, @Param("page") Pagination pagination);
    
    /**
     * 分页查询
     * @param searchBean
     * @return
     */
    List<HelpInfo> selectByClient(HelpInfo searchBean);
}
