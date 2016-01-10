/*
 * @(#)ProductEditDubboServiceImpl.java 2015-9-23 下午3:30:01
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.JsonMessageUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.ProductEditAuditLog;
import com.zttx.web.module.common.entity.ProductEditDetail;
import com.zttx.web.module.common.service.ProductEditAuditLogService;
import com.zttx.web.module.common.service.ProductEditDetailService;
import com.zttx.web.module.common.service.ProductEditService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：ProductEditDubboServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-23 下午3:30:01</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Service
public class ProductEditDubboServiceImpl implements ProductEditDubboService
{
    @Autowired
    private ProductEditService         productEditService;
    
    @Autowired
    private ProductEditDetailService   productEditDetailService;
    
    @Autowired
    private ProductEditAuditLogService productEditAuditLogService;
    
    @Override
    public JsonMessage proList(Integer state, ProductBaseInfo info) throws BusinessException
    {
        PaginateResult<Map<String, Object>> paginateResult = productEditService.searchProList(state, info);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }

    @Override
    public JsonMessage editList(ProductEditDetail editDetail) throws BusinessException
    {
        if (StringUtils.isBlank(editDetail.getProductId())) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        PaginateResult<Map<String, Object>> paginateResult = productEditDetailService.searchEditList(editDetail);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }

    @Override
    public JsonMessage logList(ProductEditAuditLog log) throws BusinessException
    {
        if (StringUtils.isBlank(log.getProductId())) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        PaginateResult<Map<String, Object>> paginateResult = productEditAuditLogService.searchLogList(log);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }

    @Override
    public JsonMessage audit(String editId, Integer isPass, String result, String operateId, String operateName) throws BusinessException
    {
        if (StringUtils.isBlank(editId) || null == isPass || isPass < 0 || isPass > 1 || (isPass == 0 && StringUtils.isBlank(result)) || StringUtils.isBlank(operateId)
                || StringUtils.isBlank(operateName)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        if (isPass == 1)
        {
            this.productEditDetailService.executePassAudit(editId, operateId, operateName);
        }
        else
        {
            this.productEditDetailService.executeUnPassAudit(editId, result, operateId, operateName);
        }
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
    }
}
