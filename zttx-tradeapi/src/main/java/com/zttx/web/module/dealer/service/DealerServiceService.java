/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerService;
/**
 * 经销商商客服信息 服务接口
 * <p>File：DealerServiceService.java </p>
 * <p>Title: DealerServiceService </p>
 * <p>Description:DealerServiceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerServiceService extends GenericServiceApi<DealerService>{
	
	/**
	 * 根据经销商商编号获取客服信息
	 * @author 陈建平
	 * @param brandId
	 * @return
	 */
    DealerService getByDealerId(String dealerId);

    /**
     * 根据客服ID修改所有该客服信息
     * @author 陈建平
     * @param brandService
     */
    void updateDealerServiceByUserId(DealerService dealerService);
}
