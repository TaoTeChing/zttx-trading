/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.WebDefTmpLog;
import com.zttx.web.module.common.mapper.WebDefTmpLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 页面模版定义日志 服务实现类
 * <p>File：WebDefTmpLog.java </p>
 * <p>Title: WebDefTmpLog </p>
 * <p>Description:WebDefTmpLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class WebDefTmpLogServiceImpl extends GenericServiceApiImpl<WebDefTmpLog> implements WebDefTmpLogService
{

    private WebDefTmpLogMapper webDefTmpLogMapper;

    @Autowired(required = true)
    public WebDefTmpLogServiceImpl(WebDefTmpLogMapper webDefTmpLogMapper)
    {
        super(webDefTmpLogMapper);
        this.webDefTmpLogMapper = webDefTmpLogMapper;
    }
}
