/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.annotation.DataSource;
import com.zttx.sdk.db.DataSourceEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.WebDefTemplate;
import com.zttx.web.module.common.entity.WebDefTmpLog;
import com.zttx.web.module.common.mapper.WebDefTemplateMapper;

/**
 * 页面模版定义 服务实现类
 * <p>File：WebDefTemplate.java </p>
 * <p>Title: WebDefTemplate </p>
 * <p>Description:WebDefTemplate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WebDefTemplateServiceImpl extends GenericServiceApiImpl<WebDefTemplate> implements WebDefTemplateService
{
    private WebDefTemplateMapper webDefTemplateMapper;
    
    @Autowired
    private WebDefTmpLogService  webDefTmpLogService;
    
    @Autowired(required = true)
    public WebDefTemplateServiceImpl(WebDefTemplateMapper webDefTemplateMapper)
    {
        super(webDefTemplateMapper);
        this.webDefTemplateMapper = webDefTemplateMapper;
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public WebDefTemplate findByKey(String key) throws BusinessException
    {
        return webDefTemplateMapper.findByKey(key);
    }
    
    /**
     * 保存模板
     *
     * @param webDefTemplate refrenceId null 新增
     *                       refrenceId not null 修改
     * @throws BusinessException
     */
    @Override
    public void save(WebDefTemplate webDefTemplate) throws BusinessException
    {
        if (this.isModelKeyExist(webDefTemplate.getModelKey(), webDefTemplate.getRefrenceId())) { throw new BusinessException(ClientConst.MODELKEY_EXITS); }
        if (StringUtils.isBlank(webDefTemplate.getRefrenceId()))
        {
            webDefTemplate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            webDefTemplate.setCreateTime(CalendarUtils.getCurrentLong());
            this.insertSelective(webDefTemplate);
        }
        else
        {
            WebDefTemplate oldWebDefTemplate = this.selectByPrimaryKey(webDefTemplate.getRefrenceId());
            if (oldWebDefTemplate == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            this.saveWebDefTmpLog(oldWebDefTemplate);
            webDefTemplate.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(webDefTemplate);
        }
    }
    
    /**
     * 保存模板操作日志
     * @param webDefTemplate
     */
    private void saveWebDefTmpLog(WebDefTemplate webDefTemplate)
    {
        WebDefTmpLog webDefTmpLog = new WebDefTmpLog();
        webDefTmpLog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        webDefTmpLog.setModelKey(webDefTemplate.getModelKey());
        webDefTmpLog.setHtmlText(webDefTemplate.getHtmlText());
        webDefTmpLog.setCreateTime(CalendarUtils.getCurrentLong());
        webDefTmpLogService.insertSelective(webDefTmpLog);
    }
    
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
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public boolean isModelKeyExist(String modelKey, String refrenceId)
    {
        return webDefTemplateMapper.countByKey(modelKey, refrenceId) > 0;
    }
}
