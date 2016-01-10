/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerApply;
/**
 * 经销商加盟申请 服务接口
 * <p>File：DealerApplyService.java </p>
 * <p>Title: DealerApplyService </p>
 * <p>Description:DealerApplyService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerApplyService extends GenericServiceApi<DealerApply>{

    /*
    * 终止合作
    *
    */
    public void insertStopApply(String uuid, String brandId) throws BusinessException;

    /**
     * 新增申请家么加盟
     * @param dealerApply
     * @return
     * @author 易永耀
     */
    Integer addDealerApply(DealerApply dealerApply);

    /**
     * 撤消申请
     *
     * @param refrenceId
     */
    public void delApply(String refrenceId);
}
