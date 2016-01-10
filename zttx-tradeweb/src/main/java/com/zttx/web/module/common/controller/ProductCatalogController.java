/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zttx.goods.module.dto.ProductBaseInfoModel;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.ProductCatalog;
import com.zttx.web.module.common.model.MenuTree;
import com.zttx.web.module.common.model.ProductCatalogModel;
import com.zttx.web.module.common.model.ProductCateModel;
import com.zttx.web.module.common.service.ProductCatalogService;
import com.zttx.web.module.common.service.ProductCateService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 产品分类信息 控制器
 * <p>File：ProductCatalogController.java </p>
 * <p>Title: ProductCatalogController </p>
 * <p>Description:ProductCatalogController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/productCatalog")
public class ProductCatalogController extends GenericController
{
    @Autowired(required = true)
    private ProductCatalogService    productCatalogService;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired
    private ProductCateService       productCateService;
    
    // 品牌商分类设置管理页面
    @RequestMapping(value = "")
    public String catalog(ProductCatalog productCatalog, HttpServletRequest request, Model model) throws BusinessException
    {
        String uuid = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String brandsId = productCatalog.getBrandsId();
        List<BrandsInfoModel> brandesInfoList = brandesInfoService.getCooperatedBrandes(uuid);
        model.addAttribute("brandesInfoList", brandesInfoList);
        if (null != brandesInfoList && !brandesInfoList.isEmpty())
        {
            if (StringUtils.isBlank(brandsId))
            {
                brandsId = brandesInfoList.get(0).getRefrenceId();
            }
            List<MenuTree> catalogList = productCatalogService.getCatalogTreeList(uuid, brandsId);
            model.addAttribute("catalogList", catalogList);
        }
        model.addAttribute("brandsId", brandsId);
        return "brand/create_catalog";
    }
    
    // 品牌商产品分类管理页面
    @RequestMapping(value = "/list")
    public String catalogList(ProductCatalog productCatalog, HttpServletRequest request, Model model) throws BusinessException
    {
        String uuid = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String brandsId = productCatalog.getBrandsId();
        List<BrandsInfoModel> brandesInfoList = brandesInfoService.getCooperatedBrandes(uuid);
        model.addAttribute("brandesInfoList", brandesInfoList);
        if (!CollectionUtils.isEmpty(brandesInfoList))
        {
            if (StringUtils.isBlank(brandsId))
            {
                brandsId = brandesInfoList.get(0).getRefrenceId();
            }
            List<ProductCatalog> cataList = productCatalogService.getCatalogList(uuid, brandsId);
            Map<String, ProductCatalog> cataMap = Maps.newLinkedHashMap();
            if (null != cataList && !cataList.isEmpty())
            {
                for (ProductCatalog item : cataList)
                {
                    Short cateLevel = item.getCateLevel();
                    cataMap.put(item.getRefrenceId(), item);
                    if (2 == cateLevel.intValue())
                    {
                        cataMap.remove(item.getParentId());
                    }
                }
            }
            List<MenuTree> catalogList = MenuTree.getCatalogTree(cataList);
            ;
            model.addAttribute("cataMap", cataMap);
            model.addAttribute("catalogList", JSON.toJSONString(catalogList));
        }
        model.addAttribute("brandsId", brandsId);
        return "brand/list_catalog";
    }
    
    // 保存分类
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object insertCatalog(ProductCatalogModel productCatalog, HttpServletRequest request) throws BusinessException
    {
        if (StringUtils.isBlank(productCatalog.getBrandsId())) { return super.getJsonMessage(CommonConst.PARAM_NULL); }
        String uuid = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        try
        {
            productCatalog.setBrandId(uuid);
            productCatalogService.insertCatalog(productCatalog, request);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    // 分类列表
    @RequestMapping(value = "/data")
    @ResponseBody
    public Object productCataList(Pagination pagination, ProductBaseInfoModel productInfo, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        productInfo.setBrandId(brandId);
        productInfo.setPage(pagination);
        PaginateResult<ProductBaseInfoModel> page = productInfoDubboConsumer.findByBaseInfoModel(productInfo);
        return this.getJsonMessage(ExceptionConst.SUCCESS, page);
    }
    
    @ResponseBody
    @RequestMapping(value = "/setProduct", method = RequestMethod.POST)
    public JsonMessage setDealer(ProductCateModel productCate, HttpServletRequest request) throws BusinessException
    {
        String uuid = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        productCate.setBrandId(uuid);
        productCateService.updateProductCate(productCate);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
