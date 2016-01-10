/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerBank;

/**
 * 经销商银行卡信息 服务接口
 * <p>File：DealerBankService.java </p>
 * <p>Title: DealerBankService </p>
 * <p>Description:DealerBankService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerBankService extends GenericServiceApi<DealerBank>
{
    /**
     * 修改银行卡审核状态
     * @param refrenceId
     * @param dealerId
     * @param checkState
     * @throws BusinessException
     */
    void updataCheckState(String refrenceId, String dealerId, Short checkState) throws BusinessException;

    /**
     * 分页查询 （接口）
     * @param searchBean
     * @param page
     * @return
     */
    PaginateResult<DealerBank> searchByClient(DealerBank searchBean, Pagination page);
}
