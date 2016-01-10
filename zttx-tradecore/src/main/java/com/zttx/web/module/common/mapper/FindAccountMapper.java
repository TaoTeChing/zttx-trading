/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.FindAccount;

/**
 * 忘记登录账户 持久层接口
 * <p>File：FindAccountDao.java </p>
 * <p>Title: FindAccountDao </p>
 * <p>Description:FindAccountDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface FindAccountMapper extends GenericMapper<FindAccount>
{

}
