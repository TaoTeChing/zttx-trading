/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.UserOnlineServiceDetail;
/**
 * 客服在线信息详情表 服务接口
 * <p>File：UserOnlineServiceDetailService.java </p>
 * <p>Title: UserOnlineServiceDetailService </p>
 * <p>Description:UserOnlineServiceDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface UserOnlineServiceDetailService extends GenericServiceApi<UserOnlineServiceDetail>{
    List<UserOnlineServiceDetail> getByOnlineService(String onlineRefrenceId);
}
