/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.PointSaleSum;

import java.util.List;
import java.util.Map;

/**
 * 返点类型产品门店销售统计和表 服务接口
 * <p>File：PointSaleSumService.java </p>
 * <p>Title: PointSaleSumService </p>
 * <p>Description:PointSaleSumService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface PointSaleSumService extends GenericServiceApi<PointSaleSum>{
    /**
     * 分页求和 erp 返点价数据日结总和
     * @param page
     * @param pointSaleSum
     * @param sumMap
     * @return
     */
    PaginateResult selectPointSaleSumPage(Pagination page, PointSaleSum pointSaleSum, Map<String, Object> sumMap) throws BusinessException;

    /**
     * 更具品牌商id统计 该品牌商与所有和他有返点财务帐关系的经销商的返点财务帐总帐
     * @author易永耀
     * @param page
     * @param pointSaleSum  filter
     * @param sumMap    总账的总和
     * @return
     */
    PaginateResult countDealersPointFinancial(Pagination page, PointSaleSum pointSaleSum, Map<String, Object> sumMap) throws BusinessException;

    /**
     * 批量查找该经销商给品牌商们的付款 资金与个人信息
     * @param brandIds
     * @param dealerId
     * @return
     *
     */
    List<Map<String,Object>> selectNoPayPoint(List<String> brandIds, String dealerId) throws BusinessException;

    /**
     * 批量支付 返点欠付款
     * @author 易永耀
     * @param dealerId
     */
    void executePayPoint(List<String> brandsId, String dealerId,String password,String userName) throws BusinessException;

    /**
     * 获取经销商给品牌商正在支付中的货款
     * @author 易永耀
     * @param dealerId
     * @param brandIds
     * @return
     */
    Map<String,Object> selectPayIng(String dealerId, String... brandIds) throws BusinessException;
}
