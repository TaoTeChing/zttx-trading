/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.fronts.entity.ArticleCate;

import java.util.List;

/**
 * 网站资讯类别 持久层接口
 * <p>File：ArticleCateDao.java </p>
 * <p>Title: ArticleCateDao </p>
 * <p>Description:ArticleCateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ArticleCateMapper extends GenericMapper<ArticleCate>
{
    /**
     * 根据层级查询
     * @param cateLevel
     * @return
     */
    List<ArticleCate> findByLevel(java.lang.Short cateLevel);
    
    /**
    * 根据父ID查询
    * @param parentId
    * @return
    */
    List<ArticleCate> findByParentId(String parentId);
}
