/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerShopEnvImgTemp;

/**
 * 经销店铺 临时图片 服务接口
 * <p>File：DealerShopEnvImgTempService.java </p>
 * <p>Title: DealerShopEnvImgTempService </p>
 * <p>Description:DealerShopEnvImgTempService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerShopEnvImgTempService extends GenericServiceApi<DealerShopEnvImgTemp>
{
    /**
     * 查询临时店铺登记的图片信息
     * @param shopEnvId
     * @return
     */
    List<DealerShopEnvImgTemp> getDealerShopEnvImgTempByShopEnvId(String shopEnvId);
}
