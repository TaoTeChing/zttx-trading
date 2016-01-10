/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.exhibition.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.exhibition.entity.DecorateConfig;
import com.zttx.web.module.exhibition.entity.DecorateConfigLog;
/**
 * 展厅自定义模块配置 服务接口
 * <p>File：DecorateConfigService.java </p>
 * <p>Title: DecorateConfigService </p>
 * <p>Description:DecorateConfigService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DecorateConfigService extends GenericServiceApi<DecorateConfig>{

    /**
     * @param brandId
     * @param brandsId
     * @param tagId
     * @param delState
     * @return
     */
    List<DecorateConfigLog> findConfigLogs(String brandId, String brandsId,
            Short tagId, Boolean delState);

    /**
     * @param brandId
     * @param brandsId
     * @return
     */
    DecorateConfigLog findBrandConfigLog(String brandId, String brandsId);

    /**
     * @param brandId
     * @param brandsId
     * @return
     */
    DecorateConfigLog insertInitLog(String brandId, String brandsId);

    /**
     * @param brandId
     * @param brandsId
     * @param b
     */
    void updateBrandConfigLogState(String brandId, String brandsId, Boolean delState);

    /**
     * @param brandId
     * @param brandsId
     * @param configId
     */
    void deleteConfigLog(String brandId, String brandsId, String configId);

    /**
     * @param brandId
     * @param brandsId
     * @param preIds
     */
    void updateConfigLogShowOrder(String brandId, String brandsId,
            List<String> preIds);

    /**
     * @param log
     * @param preIds
     * @param nextIds
     * @return
     */
    DecorateConfigLog saveConfigLog(DecorateConfigLog log, List<String> preIds,
            List<String> nextIds);

}
