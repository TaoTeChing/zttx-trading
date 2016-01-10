/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.EmailValidate;
/**
 * 邮件验证 服务接口
 * <p>File：EmailValidateService.java </p>
 * <p>Title: EmailValidateService </p>
 * <p>Description:EmailValidateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface EmailValidateService extends GenericServiceApi<EmailValidate>{

    /**
     * 创建邮箱认证记录
     * @param emailValidate
     * @return
     * @author 李星
     */
    void create(EmailValidate emailValidate) throws BusinessException;
    
    /**
     * 检验邮箱并更新数据
     * @param emailAddr
     * @return
     * @author 李星
     */
    EmailValidate verifyAndUpdate(String refrenceId, String emailAddr) throws BusinessException;
}
