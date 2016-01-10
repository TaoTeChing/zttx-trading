/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.model.UserOnlineServiceModel;
import com.zttx.web.module.common.entity.UserOnlineService;
/**
 * 客服在线信息表 服务接口
 * <p>File：UserOnlineServiceService.java </p>
 * <p>Title: UserOnlineServiceService </p>
 * <p>Description:UserOnlineServiceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface UserOnlineServiceService extends GenericServiceApi<UserOnlineService>{

    /**
     * 根据用户编号获取在线客服信息
     * @param userId
     * @return
     */
    UserOnlineService findUserOnlineService(String userId);

    /**
     * 根据在线客服编号获取在线客服详情
     * @param userOnlineService
     * @return
     */
    UserOnlineServiceModel fillUserOnlineServiceDetail(UserOnlineServiceModel userOnlineService);

    /**
     * 保存
     * @param userOnlineService
     */
    void save(UserOnlineServiceModel userOnlineService);
}
