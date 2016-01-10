/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandRead;

/**
 * 品牌商消息阅读 服务接口
 * <p>File：BrandReadService.java </p>
 * <p>Title: BrandReadService </p>
 * <p>Description:BrandReadService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandReadService extends GenericServiceApi<BrandRead>
{
    /**
     * 阅读多条消息
     * @param brandId 品牌商ID
     * @param msgIdList 消息ID列表
     * @author 章旭楠
     */
    void saveRead(String brandId, List<String> msgIdList);
    
    /**
     * 阅读一条消息
     * @param brandId 品牌商ID
     * @param msgId 消息ID
     * @author 章旭楠
     */
    void saveRead(String brandId, String msgId);

    /**
     * 判断与品牌商相关的已读记录是否已存在
     * @param brandId 品牌商ID
     * @param msgId 消息ID
     * @return Boolean true：存在， false：不存在
     * @author 章旭楠
     */
    Boolean isExistBrandRead(String brandId, String msgId);
    

}
