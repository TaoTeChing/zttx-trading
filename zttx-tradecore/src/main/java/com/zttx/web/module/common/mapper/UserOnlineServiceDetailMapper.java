/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import java.util.List;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.common.entity.UserOnlineServiceDetail;

/**
 * 客服在线信息详情表 持久层接口
 * <p>File：UserOnlineServiceDetailDao.java </p>
 * <p>Title: UserOnlineServiceDetailDao </p>
 * <p>Description:UserOnlineServiceDetailDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface UserOnlineServiceDetailMapper extends GenericMapper<UserOnlineServiceDetail>
{
    List<UserOnlineServiceDetail> getByOnlineService(String onlineRefrenceId);
}
