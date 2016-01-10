/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerRead;

/**
 * 经销商消息阅读 服务接口
 * <p>File：DealerReadService.java </p>
 * <p>Title: DealerReadService </p>
 * <p>Description:DealerReadService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerReadService extends GenericServiceApi<DealerRead>
{
    /**
     * 阅读所有消息
     * @param dealerId 经销商ID
     */
    void saveRead(String dealerId);
    
    /**
     * 阅读多条消息
     * @param dealerId 经销商ID
     * @param msgIdList 消息ID列表
     */
    void saveRead(String dealerId, List<String> msgIdList);
    
    /**
     * 阅读一条消息
     * @param dealerId 经销商ID
     * @param msgId 消息ID
     */
    void saveRead(String dealerId, String msgId);
    
    /**
     * 判断与经销商相关的已读记录是否已存在
     *
     * @param dealerId 经销商ID
     * @param msgId    消息ID
     * @return Boolean true：存在， false：不存在
     * @author 章旭楠
     */
    Boolean isExistDealerRead(String dealerId, String msgId);
}
