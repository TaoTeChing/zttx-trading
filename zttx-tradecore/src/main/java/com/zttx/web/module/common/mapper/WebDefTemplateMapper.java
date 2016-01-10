/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.WebDefTemplate;

/**
 * 页面模版定义 持久层接口
 * <p>File：WebDefTemplateDao.java </p>
 * <p>Title: WebDefTemplateDao </p>
 * <p>Description:WebDefTemplateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface WebDefTemplateMapper extends GenericMapper<WebDefTemplate>
{
    /**
     * 根据KEY获取静态HTML内容
     * @param key
     * @return
     */
    WebDefTemplate findByKey(String key) throws BusinessException;
    
    /**
     * 统计  判断是否存在
     * @param modelKey
     * @param refrenceId 允许null
     */
    int countByKey(@Param("modelKey") String modelKey, @Param("refrenceId") String refrenceId);
}
