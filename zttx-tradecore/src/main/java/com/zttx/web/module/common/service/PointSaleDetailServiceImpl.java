/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.StockAdjustDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.web.module.common.entity.PointSaleDetail;
import com.zttx.web.module.common.mapper.PointSaleDetailMapper;

import java.util.List;
import java.util.Map;

/**
 * 返点财务帐销售明细表 服务实现类
 * <p>File：PointSaleDetail.java </p>
 * <p>Title: PointSaleDetail </p>
 * <p>Description:PointSaleDetail </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class PointSaleDetailServiceImpl extends GenericServiceApiImpl<PointSaleDetail> implements PointSaleDetailService
{

    private PointSaleDetailMapper pointSaleDetailMapper;
    @Autowired
    private StockAdjustDetailService stockAdjustDetailService;

    @Autowired(required = true)
    public PointSaleDetailServiceImpl(PointSaleDetailMapper pointSaleDetailMapper)
    {
        super(pointSaleDetailMapper);
        this.pointSaleDetailMapper = pointSaleDetailMapper;
    }

    @Override
    public PaginateResult selectPointSaleDetailPage(Pagination page,PointSaleDetail pointSaleDetail, Map<String, Object> sumMap) throws BusinessException {
        if(StringUtils.isBlank(pointSaleDetail.getBrandId())||StringUtils.isBlank(pointSaleDetail.getDealerId())||null==pointSaleDetail.getErpTime())
        {
            throw new BusinessException("参数异常：brandId、dealerId或time为空!");
        }
        List<Map<String,Object>> pointSaleDetailMapList = pointSaleDetailMapper.selectPointSaleDetailList(page,pointSaleDetail);
        PaginateResult paginateResult = new PaginateResult(page,pointSaleDetailMapList);
        sumMap.putAll(pointSaleDetailMapper.countPointSaleDetailListSum(pointSaleDetail));
        return paginateResult;
    }

    @Override
    public void insertPointSaleDetailBatch(List<PointSaleDetail> listPointSaleDetail) throws BusinessException {
        if(null == listPointSaleDetail || listPointSaleDetail.isEmpty()){throw new BusinessException(CommonConst.PARAM_NULL);}
        //返点财务帐 销售数量统计
        pointSaleDetailMapper.insertBatch(listPointSaleDetail);
        try {
            stockAdjustDetailService.addStockAdjustDetail(StockAdjustDetail.TYPE_SALE, listPointSaleDetail);
        }catch (Exception e){
            throw new BusinessException(CommonConst.FAIL.getCode(),"返点财务帐统计销售数据异常："+e.getLocalizedMessage());
        }
    }
}
