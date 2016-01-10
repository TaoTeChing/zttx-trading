/*
 * @(#)BrandFreightTemplateController.java 2014-12-22 上午11:26:28
 * Copyright 2014 江枫林, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.model.BrandFreightParamModel;
import com.zttx.web.module.brand.service.BrandFreightTemplateService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * <p>File：BrandFreightTemplateController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-22 上午11:26:28</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.BRAND + "/freightTemplate")
public class BrandFreightTemplateController extends GenericController
{
    @Autowired
    private BrandFreightTemplateService brandFreightTemplateService;
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    /**
     * 1.自定义模板页面
     * 2.推荐模板页面  XXX
     * @author 江枫林
     */
    @RequestMapping("/list")
    @RequiresPermissions("brand:center")
    public String list(Short isRecommend, String productIds, HttpServletRequest request, Model model) throws BusinessException
    {
        // 传递给页面做"新增模板" "复制模板" 隐藏
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        model.addAttribute("templateNumber", brandFreightTemplateService.getTemplateNumber(brandId));
        model.addAttribute("isRecommend", null == isRecommend ? 0 : isRecommend);
        model.addAttribute("productIds", productIds);
        return "brand/list_brandFreightTemplate";
    }
    
    /**
     * 3.模板数据（分页）
     * @author 江枫林
     * @throws BusinessException 
     */
    @RequestMapping(value = "/listData")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage listData(BrandFreightTemplate filter, Pagination pagination, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        if (StringUtils.isBlank(brandId) || null == filter.getIsRecommend()) { throw new BusinessException(CommonConst.PARAM_NULL); }
        filter.setBrandId(brandId);
        filter.setPage(pagination);
        List<BrandFreightTemplate> paginateResult = brandFreightTemplateService.findTemplateData(filter);
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 4.新增页面
     * 5.修改页面
     * @author 江枫林
     */
    @RequiresPermissions("brand:center")
    @RequestMapping("/get")
    public String get(String templateId, String productIds, HttpServletRequest request, Model model, String isRecommend) throws BusinessException
    {
        // 该处判断用于推荐模板复制时 (先修改后保存) 传递参数,目的是为了在修改保存时,使其走保存而不走修改
        if (StringUtils.isNotBlank(isRecommend) && isRecommend.equals("1"))
        {
            model.addAttribute("isRecommend", isRecommend);
        }
        if (StringUtils.isNotBlank(templateId))
        {
            UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
            String brandId = userPrincipal.getRefrenceId();
            BrandFreightParamModel paramModel = brandFreightTemplateService.getTempalateAndDetailData(templateId, brandId);
            model.addAttribute("paramModel", paramModel);
        }
        model.addAttribute("productIds", productIds);
        return "brand/edit_brandFreightTemplate";
    }
    
    /**
     * 6.保存
     * @author 江枫林 yyy
     */
    @RequestMapping(value = "/saveBrandFreight")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage saveBrandFreight(BrandFreightParamModel paramModel, HttpServletRequest request, String isRecommend) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        // 用于推荐模板的复制(修改,保存)保存时,走保存而不走修改,(走修改会发现数据库中没有该tempalteId)
        if (StringUtils.isNotBlank(isRecommend) && isRecommend.equals("1"))
        {
            paramModel.getTemplate().setRefrenceId(null);
        }
        // 控制品牌商下模板数 唯一 (必须要templateId判空来确定是修改保存还是新增保存)
        if (StringUtils.isBlank(paramModel.getTemplate().getRefrenceId()) && brandFreightTemplateService.getTemplateNumber(brandId) > 0) { throw new BusinessException(
                CommonConst.TEMPLATENUMBER_NOTONLY); }
        String templateId = brandFreightTemplateService.insertBrandFreight(paramModel, brandId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 10.删除模板
     * @author 江枫林
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage delete(String templateId, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        brandFreightTemplateService.deleteTemplate(templateId, brandId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 应用模板
     * @author 江枫林
     */
    @RequestMapping(value = "/apply")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage apply(String templateId, String productIds) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        productInfoDubboConsumer.updateProductFreTemplate(brandId, productIds, templateId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
