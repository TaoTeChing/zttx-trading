/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerGroom;

import java.util.List;
import java.util.Map;

/**
 * 推荐给品牌商的经销商 服务接口
 * <p>File：DealerGroomService.java </p>
 * <p>Title: DealerGroomService </p>
 * <p>Description:DealerGroomService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerGroomService extends GenericServiceApi<DealerGroom>{


    /**
     * 推荐的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    public PaginateResult<Map<String, Object>> search(Pagination pagination, DealerGroom info) ;
    
    /**
     * 查询指定品牌商所有推荐的终端商
     *
     * @param fitler
     * @return
     */
    List<DealerGroom> listDealerGrooms(DealerGroom fitler);
}
