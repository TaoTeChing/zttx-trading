/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.fronts.entity.JoinInfo;

/**
 * 加盟入驻信息 持久层接口
 * <p>File：JoinInfoDao.java </p>
 * <p>Title: JoinInfoDao </p>
 * <p>Description:JoinInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface JoinInfoMapper extends GenericMapper<JoinInfo>
{

}
