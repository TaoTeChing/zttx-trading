/*
 * @(#)DecorateService.java 2015-8-25 下午1:20:56
 * Copyright 2015 郭小亮, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.zttx.web.module.exhibition.entity.DecorateConfig;


/**
 * <p>File：DecorateService.java</p>
 * <p>Title:品牌装修 </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-25 下午1:20:56</p>
 * <p>Company: 8637.com</p>
 * @author 郭小亮
 * @version 1.0
 */
public interface DecorateService
{
    /**
     * @param brandId
     * @param brandsId
     * @param tagId
     * @return
     */
    public String getConfigsHtml(String brandId, String brandsId, Short tagId);

    /**
     * 获取 DecorateConfig 配置 构成的html
     * @param configList
     * @return
     */
    String getConfigsHtml(List<DecorateConfig> configList);

    /**
     * 查找 品牌 对应位置的配置
     * @param brandId
     * @param brandsId
     * @param tagId
     * @param delState
     * @return
     */
    List<DecorateConfig> findConfigs(String brandId, String brandsId,
            Short tagId, boolean delState);

    /**
     * @param brandId
     * @param brandsId
     */
    public void saveForRelease(String brandId, String brandsId)  throws InvocationTargetException, IllegalAccessException;

    /**
     * @param brandId
     * @param brandesId
     * @param tagId
     * @return
     */
    public String getConfigsHtmlView(String brandId, String brandesId,
            Short tagId) throws IllegalAccessException, InvocationTargetException;
}
