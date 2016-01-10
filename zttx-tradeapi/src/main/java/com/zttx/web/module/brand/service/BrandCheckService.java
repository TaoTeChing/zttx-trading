/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.brand.entity.BrandCheck;
import com.zttx.web.module.dealer.model.DealerJoinModel;

/**
 * 品牌商审核经销商加盟申请日志 服务接口
 * <p>File：BrandCheckService.java </p>
 * <p>Title: BrandCheckService </p>
 * <p>Description:BrandCheckService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandCheckService extends GenericServiceApi<BrandCheck>{

    /**
     * 保存品牌验证信息
     *
     * @param dealerJoin
     * @return
     */
    public BrandCheck insertBrandCheck(DealerJoinModel dealerJoin);

}
