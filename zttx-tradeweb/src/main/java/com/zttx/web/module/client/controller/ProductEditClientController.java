/*
 * @(#)ProductEditController.java 2015-9-29 上午11:27:16
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.JsonMessageUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.ProductEditAuditLog;
import com.zttx.web.module.common.entity.ProductEditDetail;
import com.zttx.web.module.common.service.ProductEditAuditLogService;
import com.zttx.web.module.common.service.ProductEditDetailService;
import com.zttx.web.module.common.service.ProductEditService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：ProductEditController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-29 上午11:27:16</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@RequestMapping(ApplicationConst.CLIENT +"/product/edit")
@Controller
public class ProductEditClientController extends GenericController
{
    @Autowired
    private ProductEditService         productEditService;
    
    @Autowired
    private ProductEditDetailService   productEditDetailService;
    
    @Autowired
    private ProductEditAuditLogService productEditAuditLogService;
    
    /**
     * 获取产品产品审核信息
     * 参数
     * state:0未审核，1已审核
     * info.brandsName:品牌名称
     * info.productNo:产品货号
     * info.pagination：分页
     * @param state
     * @param info
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/proList")
    public JsonMessage proList(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Integer state=ParameterUtils.getIntegerValue(map, "state", false);
        ProductBaseInfo info=new ProductBaseInfo();
        info.setBrandsName(map.get("brandsName"));
        info.setProductNo(map.get("productNo"));
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        info.setPage(page);
        PaginateResult<Map<String, Object>> paginateResult = productEditService.searchProList(state, info);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }

  
    /**
     * 根据审核状态和产品id查找审核详情
     * @param editDetail
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/editList")
    public JsonMessage editList(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        String productId = MapUtils.getString(map, "productId");
        Short state = MapUtils.getShort(map, "state");
        if (StringUtils.isBlank(productId)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        ProductEditDetail filter = new ProductEditDetail();
        filter.setProductId(productId);
        filter.setState(state);
        filter.setPage(page);
        PaginateResult<Map<String, Object>> paginateResult = productEditDetailService.searchEditList(filter);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }

    /**
     * 根据产品id查找审核日志
     * @param log
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/logList")
    public JsonMessage logList(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        String productId = MapUtils.getString(map, "productId");
        if (StringUtils.isBlank(productId)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        ProductEditAuditLog filter = new ProductEditAuditLog();
        filter.setProductId(productId);
        filter.setPage(page);
        PaginateResult<Map<String, Object>> paginateResult = productEditAuditLogService.searchLogList(filter);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }

    /**
     * 审核通过或不通过
     * @param editId
     * @param isPass
     * @param result
     * @param operateId
     * @param operateName
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/audit")
    public JsonMessage audit(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String editId = MapUtils.getString(map, "editId");
        Integer isPass = MapUtils.getInteger(map, "isPass");
        String result = MapUtils.getString(map, "result");
        String operateId = MapUtils.getString(map, "operateId");
        String operateName = MapUtils.getString(map, "operateName");
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
