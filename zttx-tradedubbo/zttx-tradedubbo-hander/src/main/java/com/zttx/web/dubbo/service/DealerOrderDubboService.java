/*
 * @(#)BrandsCountDubboService.java 2015-8-22 下午6:13:06
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.alibaba.fastjson.JSONArray;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerOrder;

import java.util.List;
import java.util.Map;

/**
 * <p>File：DealerOrderDubboService.java</p>
 * <p>Title: DealerOrderDubboService</p>
 * <p>Description:DealerOrderDubboService</p>
 * <p>Copyright: Copyright (c) 2015年9月6日</p>
 * <p>Company: 8637.com</p>
 * @author txsb
 * @version 1.0
 */
public interface DealerOrderDubboService
{
    /**
     * 查询经营商订单信息分页显示
     * @param pagination
     * @param dealerOrder
     * @return
     */
    PaginateResult<Map<String, Object>> getDealerOrderList(Pagination pagination, DealerOrderModel dealerOrder);

    /**
     * 订单搜索
     * @param dealerOrder
     * @param pagination
     * @return
     */
    List<Map<String,Object>> search(DealerOrder dealerOrder, Pagination pagination)throws BusinessException;
    /**
     * 查询订单给erp用
     * @param dealerOrder
     * @return
     */
    List<Map<String,Object>> findDealerOrderForDealerErp(DealerOrder dealerOrder)throws BusinessException;

    /**
     * 品牌ERP订单发货询问接口
     *
     * @param map
     * @return
     */
    public JSONArray ordersAskBrandErp(Map<String, String> map) throws BusinessException;
}
