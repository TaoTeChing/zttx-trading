/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerShopEnvTemp;

/**
 * 经销店铺 临时信息 服务接口
 * <p>File：DealerShopEnvTempService.java </p>
 * <p>Title: DealerShopEnvTempService </p>
 * <p>Description:DealerShopEnvTempService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerShopEnvTempService extends GenericServiceApi<DealerShopEnvTemp>
{
    /**
     * 分页查询经销商临时信息
     * @param dealerShopEnvTemp 查询条件
     * @return
     */
    PaginateResult<DealerShopEnvTemp> searchByClient(DealerShopEnvTemp dealerShopEnvTemp);
    
    /**
     * 更新店铺状态
     * @param refrenceId
     * @param status
     * @param auditUser
     * @param auditIp
     * @throws BusinessException
     */
    void updateDealerShopEnvTempStatus(String refrenceId, int status, String auditUser, String auditIp) throws BusinessException;
}
