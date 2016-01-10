/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.WebDefTmpLog;

/**
 * 页面模版定义日志 持久层接口
 * <p>File：WebDefTmpLogDao.java </p>
 * <p>Title: WebDefTmpLogDao </p>
 * <p>Description:WebDefTmpLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface WebDefTmpLogMapper extends GenericMapper<WebDefTmpLog>
{

}
