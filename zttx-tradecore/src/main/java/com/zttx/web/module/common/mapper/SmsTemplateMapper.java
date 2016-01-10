/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.SmsTemplate;

/**
 * 短信模板 持久层接口
 * <p>File：SmsTemplateDao.java </p>
 * <p>Title: SmsTemplateDao </p>
 * <p>Description:SmsTemplateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface SmsTemplateMapper extends GenericMapper<SmsTemplate>
{
    /**
     * 根据短信模块Key查询
     * @param smsKey
     * @return
     * @author 李星
     */
    SmsTemplate getBySmsKey(@Param("smsKey") String smsKey);
    
    /**
     * 条件查询
     * @param template
     * @return
     * @author 李星
     */
    List<SmsTemplate> pageSearch(SmsTemplate template);
}
