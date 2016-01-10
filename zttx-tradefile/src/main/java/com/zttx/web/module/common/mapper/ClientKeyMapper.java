/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.mapper;

import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.ClientKey;
import org.apache.ibatis.annotations.Param;

/**
 * API接入码 持久层接口
 * <p>File：ClientKeyDao.java </p>
 * <p>Title: ClientKeyDao </p>
 * <p>Description:ClientKeyDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ClientKeyMapper extends GenericMapper<ClientKey>
{
    /**
     * 通过key 查找
     * @param accessType
     * @param key String 用户key
     * @return ClientKey
     */
    ClientKey findByKey(@Param("accessType") String accessType, @Param("key") String key);
    
    /**
     * 是否存在白名单中
     * @param key 用户key
     * @param accessType 访问类型
     * @param ip  Integer 客户端IP
     * @return Boolean true 是  false 否
     */
    ClientKey isExistNetAddress(@Param("key") String key, @Param("accessType") String accessType, @Param("ip") Integer ip);
}
