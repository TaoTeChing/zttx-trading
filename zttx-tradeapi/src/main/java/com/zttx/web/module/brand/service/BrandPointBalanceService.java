/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandPointBalance;
import com.zttx.web.module.brand.model.BrandPointBalanceModel;
import com.zttx.web.module.dealer.entity.DealerOrder;

/**
 * 扣点表 服务接口
 * <p>File：BrandPointBalanceService.java </p>
 * <p>Title: BrandPointBalanceService </p>
 * <p>Description:BrandPointBalanceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandPointBalanceService extends GenericServiceApi<BrandPointBalance>{

    /**
     * 计算扣点金额
     *
     * @param order   订单实体
     * @param payment 当前支付金额
     * @return
     * @author 施建波
     */
    public BigDecimal getPointBalance(DealerOrder order, BigDecimal payment) throws BusinessException;
    
    /**
     * 搜索扣点
     * @author 陈建平
     * @param filter
     * @return
     */
    List<BrandPointBalance> findByBrandPointBalanceModel(BrandPointBalanceModel filter);
    
    /**
     * 修改扣点
     * @param brandsId 品牌ID
     * @param joinForm 1:现款  2：授信
     * @param point 扣点值
     * @param operName 操作人
     * @author 张昌苗
     */
    void executeChangePoint(String brandsId, String[] joinFormArr, String[] pointArr, String operName) throws BusinessException;
    
    /**
     * 修改扣点
     * @author 陈建平
     * @param brandsId
     * @param joinForm
     * @param point
     * @param operName
     * @throws BusinessException
     */
    void executeChangePoint(String brandsId, String joinForm, Double point, String operName) throws BusinessException;
    
    /**
     * 获取扣点
     * @author 陈建平
     * @param brandsId
     * @param joinForm
     * @return
     */
    BrandPointBalance findPointBalance(String brandsId, Short joinForm);
    
    /**
     * 扣点数据
     * @author 陈建平
     * @param brandsId
     * @return
     */
    Map<String, Object> findPointData(String brandsId);
    
    /**
     * 分页获取扣点
     * @author 陈建平
     * @param filter
     * @param page
     * @return
     */
    PaginateResult<Map<String, Object>> searchPointData(BrandPointBalanceModel filter, Pagination page);
    
    /**
     * 初始化扣点值
     * @author 陈建平
     * @param joinForm
     * @param point
     */
    void saveInitBrandPointBalance(Short joinForm,Double point);
}
