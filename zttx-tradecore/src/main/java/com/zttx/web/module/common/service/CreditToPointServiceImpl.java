/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.CreditToPoint;
import com.zttx.web.module.common.mapper.CreditToPointMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * erp铺货变返点sku详细信息表 服务实现类
 * <p>File：CreditToPoint.java </p>
 * <p>Title: CreditToPoint </p>
 * <p>Description:CreditToPoint </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class CreditToPointServiceImpl extends GenericServiceApiImpl<CreditToPoint>implements CreditToPointService
{
    private CreditToPointMapper creditToPointMapper;
    
    @Autowired(required = true)
    public CreditToPointServiceImpl(CreditToPointMapper creditToPointMapper)
    {
        super(creditToPointMapper);
        this.creditToPointMapper = creditToPointMapper;
    }
    

    @Override
    public PaginateResult<Map<String, Object>> selectCreditToPointPage(Pagination page, CreditToPoint creditToPoint, Map<String, Object> sumMap) throws BusinessException
    {
        if (null == creditToPoint || creditToPoint.getBrandId() == null|| StringUtils.isBlank(creditToPoint.getDealerId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        creditToPoint.setPage(page);
        List<Map<String, Object>> mapList = creditToPointMapper.selectCreditToPointList(creditToPoint);
        PaginateResult paginateResult = new PaginateResult(page,mapList);
        creditToPoint.setPage(null);
        sumMap.putAll(creditToPointMapper.countCreditToPointListSum(creditToPoint));
        return paginateResult;
    }
}
