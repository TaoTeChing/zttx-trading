/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.Adjustment;
/**
 * 调价信息主表 服务接口
 * <p>File：AdjustmentService.java </p>
 * <p>Title: AdjustmentService </p>
 * <p>Description:AdjustmentService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface AdjustmentService extends GenericServiceApi<Adjustment>{

    /**
     * 是否存在
     * @param dealerId
     * @param brandId
     * @param brandAdjustId
     * @return
     */
    Boolean isExitAdjustment(String dealerId, String brandId, String brandAdjustId);
}
