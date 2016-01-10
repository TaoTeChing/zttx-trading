/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerVisited;
import com.zttx.web.module.dealer.model.DealerVisitedModel;
import com.zttx.web.module.dealer.model.DealerVisitedView;

import java.util.Map;

/**
 * 经销商浏览记录 服务接口
 * <p>File：DealerVisitedService.java </p>
 * <p>Title: DealerVisitedService </p>
 * <p>Description:DealerVisitedService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerVisitedService extends GenericServiceApi<DealerVisited>{
    /**
     * 分页查询
     * @param dealerVisitedModel
     * @param pagination
     * @return
     */
    PaginateResult<Map<String,Object>> getDealerVisitedsBy(DealerVisitedModel dealerVisitedModel, Pagination pagination);

    /**
     * 查询浏览过我的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    public PaginateResult<Map<String, Object>> search(Pagination pagination, DealerVisitedView info);

}
