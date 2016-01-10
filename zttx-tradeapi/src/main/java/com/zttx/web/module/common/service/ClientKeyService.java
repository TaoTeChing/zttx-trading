/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.ClientKey;

/**
 * API接入码 服务接口
 * <p>File：ClientKeyService.java </p>
 * <p>Title: ClientKeyService </p>
 * <p>Description:ClientKeyService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface ClientKeyService extends GenericServiceApi<ClientKey>
{
    /**
     * 通过key 查找
     * @param key String 用户key
     * @return ClientKey
     */
    ClientKey findByKey(String key);
    
    /**
     * 是否存在白名单中
     * @param key 用户key
     * @param accessType 访问类型
     * @param ip  Integer 客户端IP
     * @return Boolean true 是  false 否
     */
    Boolean isExistNetAddress(String key, String accessType, Integer ip);
}
