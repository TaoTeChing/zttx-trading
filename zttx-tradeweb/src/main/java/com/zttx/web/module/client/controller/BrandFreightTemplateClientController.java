/*
 * @(#)BrandFreightTemplateClientController.java 2014-12-22 下午3:40:17
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.model.BrandFreightParamModel;
import com.zttx.web.module.brand.service.BrandFreightTemplateService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：BrandFreightTemplateClientController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-22 下午3:40:17</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/freightTemplate")
public class BrandFreightTemplateClientController extends GenericController
{
    @Autowired
    private BrandFreightTemplateService brandFreightTemplateService;
    
    /**
     * 1. 模板数据（分页）
     * @author 张昌苗
     */
    @RequestMapping(value = "/listData")
    @ResponseBody
    public JsonMessage listData(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String name = MapUtils.getString(map, "name");
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        BrandFreightTemplate filter = new BrandFreightTemplate();
        filter.setIsRecommend(BrandConstant.BrandFreightTemplate.IS_RECOMMEND_YES);
        filter.setName(name);
        filter.setPage(page);
        List<BrandFreightTemplate> resultList = brandFreightTemplateService.findTemplateData(filter);
        return this.getJsonMessage(CommonConst.SUCCESS, new PaginateResult<>(page, resultList));
    }
    
    /**
     * 2. 单个模板数据
     * @author 张昌苗
     */
    @RequestMapping(value = "/getData")
    @ResponseBody
    public JsonMessage getData(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String templateId = MapUtils.getString(map, "templateId");
        String brandId = MapUtils.getString(map, "brandId");
        if (StringUtils.isBlank(templateId)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        BrandFreightParamModel paramModel = brandFreightTemplateService.getTempalateAndDetailData(templateId, brandId);
        return this.getJsonMessage(CommonConst.SUCCESS, paramModel);
    }
    
    /**
     * 3. 保存模板
     * @author 张昌苗
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public JsonMessage save(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandFreightParamModel paramModel = new BrandFreightParamModel();
        try
        {
            BeanWrapper bw = new BeanWrapperImpl(paramModel);
            bw.setAutoGrowNestedPaths(true);
            bw.setPropertyValues(new MutablePropertyValues(map), true, false);
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(), e.getMessage());
        }
        brandFreightTemplateService.insertBrandFreight(paramModel, null);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 4. 删除模板
     * @author 张昌苗
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonMessage delete(ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String templateId = MapUtils.getString(map, "templateId");
        brandFreightTemplateService.deleteTemplate(templateId, null);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
