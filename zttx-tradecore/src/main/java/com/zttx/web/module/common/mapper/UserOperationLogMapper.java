/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.UserOperationLog;

/**
 * 用户操作日志 持久层接口
 * <p>File：UserOperationLogDao.java </p>
 * <p>Title: UserOperationLogDao </p>
 * <p>Description:UserOperationLogDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface UserOperationLogMapper extends GenericMapper<UserOperationLog>
{

}
