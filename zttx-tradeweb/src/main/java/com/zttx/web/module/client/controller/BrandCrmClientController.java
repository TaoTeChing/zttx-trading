/*
 * @(#)BrandCrmController.java 2014-5-30 上午10:45:03
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandCrm;
import com.zttx.web.module.brand.model.BrandCrmModel;
import com.zttx.web.module.brand.service.BrandCrmService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：BrandCrmController.java</p>
 * <p>Title: 品牌商更新信息表CRM</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-30 上午10:45:03</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/crm")
public class BrandCrmClientController extends GenericController
{
    @Autowired
    private BrandCrmService brandCrmService;
    
    /**
     * 保存
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @author 周光暖
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage save(HttpServletRequest request, ClientParameter param) throws BusinessException, IllegalAccessException, InvocationTargetException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandCrmModel brandCrmModel = new BrandCrmModel();
        BeanUtils.populate(brandCrmModel, map);
        brandCrmService.insertByClient(brandCrmModel);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 搜索未获取的数据
     * @param param
     * @param request
     * @return
     * @author 周光暖
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage search(ClientParameter param, HttpServletRequest request)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        BrandCrm filter = new BrandCrm();
        filter.setJsonType(MapUtils.getShort(map, "jsonType"));
        filter.setPage(page);
        PaginateResult<BrandCrm> result = new PaginateResult<BrandCrm>(page,brandCrmService.selectBrandCrmByClient(filter));
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 修改已获取状态
     * @param param
     * @param request
     * @return
     * @throws BusinessException
     * @author 周光暖
     */
    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage updateState(ClientParameter param, HttpServletRequest request) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        if (StringUtils.isBlank(refrenceId)) { 
        	throw new BusinessException(CommonConst.PARAM_NULL); 
        }
        BrandCrm brandCrm = brandCrmService.selectByPrimaryKey(refrenceId);
        if (brandCrm == null) { 
        	throw new BusinessException(CommonConst.DATA_NOT_EXISTS); 
        }
        if (ClientConstant.BRANDSTATE_GET != brandCrm.getBrandState()) {
        	brandCrm.setBrandState(ClientConstant.BRANDSTATE_GET);
        	brandCrmService.updateByPrimaryKey(brandCrm);
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
