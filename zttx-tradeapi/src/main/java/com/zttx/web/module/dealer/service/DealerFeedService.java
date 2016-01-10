/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerFeed;
/**
 * 经销商反馈信息 服务接口
 * <p>File：DealerFeedService.java </p>
 * <p>Title: DealerFeedService </p>
 * <p>Description:DealerFeedService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerFeedService extends GenericServiceApi<DealerFeed>{

    /**
     * 根据品牌商ID和品牌ID获取经销商反馈信息
     * @param pagination
     * @param brandId
     * @param brandsId
     * @return
     */
    PaginateResult<DealerFeed> listByBrandIdAndBrandsId(Pagination pagination, String brandId, String brandsId);
}
