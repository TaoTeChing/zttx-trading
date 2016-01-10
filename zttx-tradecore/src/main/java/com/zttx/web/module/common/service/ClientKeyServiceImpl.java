/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.consts.ClientKeyConst;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.ClientKey;
import com.zttx.web.module.common.mapper.ClientKeyMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * API接入码 服务实现类
 * <p>File：ClientKey.java </p>
 * <p>Title: ClientKey </p>
 * <p>Description:ClientKey </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ClientKeyServiceImpl extends GenericServiceApiImpl<ClientKey> implements ClientKeyService
{
    private ClientKeyMapper clientKeyMapper;
    
    @Autowired(required = true)
    public ClientKeyServiceImpl(ClientKeyMapper clientKeyMapper)
    {
        super(clientKeyMapper);
        this.clientKeyMapper = clientKeyMapper;
    }
    
    @Override
    public ClientKey findByKey(String key)
    {
        return clientKeyMapper.findByKey(ClientKeyConst.TRADING_WEB_CLIENT_KEY, key);
    }
    
    @Override
    public Boolean isExistNetAddress(String key, String accessType, Integer ip)
    {
        ClientKey clientKey = clientKeyMapper.isExistNetAddress(key, accessType, ip);
        if (null != clientKey) return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
