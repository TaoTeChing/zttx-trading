/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.WebDefTemplate;

/**
 * 页面模版定义 服务接口
 * <p>File：WebDefTemplateService.java </p>
 * <p>Title: WebDefTemplateService </p>
 * <p>Description:WebDefTemplateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface WebDefTemplateService extends GenericServiceApi<WebDefTemplate>
{
    /**
     * 根据KEY获取静态HTML内容
     * @param key
     * @return
     */
    WebDefTemplate findByKey(String key) throws BusinessException;
    
    /**
     * 保存模板
     * @param webDefTemplate
     * refrenceId null 新增
     * refrenceId not null 修改
     * @throws BusinessException
     */
    void save(WebDefTemplate webDefTemplate) throws BusinessException;
    
    /**
     * 验证模块Key是否已经存在
     *
     * @param modelKey key
     * @param refrenceId
     *  not null 需要排除自身
     *  null 不需要排除
     * @return true 存在  false 不存在
     * @author 章旭楠
     */
    boolean isModelKeyExist(String modelKey, String refrenceId);
}
