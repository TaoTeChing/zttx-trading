/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.SmsTemplate;
/**
 * 短信模板 服务接口
 * <p>File：SmsTemplateService.java </p>
 * <p>Title: SmsTemplateService </p>
 * <p>Description:SmsTemplateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface SmsTemplateService extends GenericServiceApi<SmsTemplate>{

    /**
     * 根据短信模块Key查询
     *
     * @param smsKey
     * @return
     */
    SmsTemplate getBySmsKey(String smsKey);

    /**
     * 分页获取
     * @param template
     * @param pagination
     * @return
     */
    PaginateResult<SmsTemplate> pageSearch(SmsTemplate template, Pagination pagination);
    
    /**
     * 保存或修改短信模板
     * @param template
     * @return
     * @author 李星
     * @throws BusinessException
     */
    SmsTemplate saveOrUpdate(SmsTemplate template) throws BusinessException;
}
