/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandLevelService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 经销商等级 控制器
 * <p>File：BrandLevelController.java </p>
 * <p>Title: BrandLevelController </p>
 * <p>Description:BrandLevelController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandLevel")
public class BrandLevelController extends GenericController
{
    @Autowired(required = true)
    private BrandLevelService  brandLevelService;
    
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    @Autowired
    private DealerJoinService  dealerJoinService;
    
    /**
     * 等级管理页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/level")
    public String level(Model model)
    {
        try
        {
            UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
            String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
            model.addAttribute("brandesInfos", brandesInfoService.getCooperatedBrandes(brandId));
        }
        catch (BusinessException e)
        {
            e.printStackTrace();
        }
        return "/brand/center_dealerlevel_manager";
    }
    
    /**
     * 保存管理等级
     *
     * @param request
     * @param brandLevel
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/add")
    public JsonMessage add(HttpServletRequest request, BrandLevel brandLevel) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        if (StringUtils.isBlank(brandLevel.getBrandsId()) || StringUtils.isBlank(brandLevel.getLevelName()) || StringUtils.isBlank(brandLevel.getLevelMark())) { throw new BusinessException(
                CommonConst.PARAM_NULL); }
        String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
        brandLevel.setBrandId(brandId);
        brandLevelService.addBrandLevel(brandLevel);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 修改管理等级
     *
     * @param brandLevel
     * @return
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/modify")
    public JsonMessage modify(HttpServletRequest request, BrandLevel brandLevel) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
        brandLevel.setBrandId(brandId);
        brandLevelService.modifyBrandLevel(brandLevel);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 返回等级列表数据
     *
     * @param brandsId
     * @param page
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/list")
    public JsonMessage list(Pagination page, String brandsId) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
        PaginateResult<BrandLevel> paginateResult = brandLevelService.getBrandLevelsBy(brandId, brandsId, page);
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 返回等级列表数据
     *
     * @param brandsId
     * @param page
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "level/list")
    public JsonMessage levelList(Pagination page, String brandsId) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        PaginateResult<BrandLevel> paginateResult = this.brandLevelService.getBrandLevelsBy(brandId, brandsId, page);
        this.brandLevelService.fillBrands(paginateResult.getList());
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 设置等级
     * @param dealerJoin
     * @return
     * @author 施建波
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "level/setDealer")
    public JsonMessage levelSetDealer(DealerJoin dealerJoin, String[] idAry) throws BusinessException
    {
        if (ArrayUtils.isEmpty(idAry) || StringUtils.isBlank(dealerJoin.getBrandsId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        dealerJoin.setBrandId(brandId);
        dealerJoinService.updateDealerLevel(dealerJoin, idAry);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 删除管理等级
     *
     * @param refrenceId
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/delete")
    public JsonMessage delete(HttpServletRequest request, String refrenceId) throws BusinessException
    {
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
        brandLevelService.delBrandLevelById(brandId, refrenceId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 设置等级页
     *
     * @param brandsId
     * @param request
     * @param model
     * @param page
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/setup")
    public String setLevel(String brandsId, HttpServletRequest request, Model model, Pagination page) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
        List<BrandsInfoModel> brandesInfoList = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandesInfoList", brandesInfoList);
        if (StringUtils.isNotBlank(brandsId))
        {
            List<BrandLevel> levelList = brandLevelService.getBrandLevelsBy(brandId, brandsId);
            model.addAttribute("levelList", levelList);
        }
        return "brand/list_level_dealer";
    }
    
    /**
     * 获取合作经绡商
     *
     * @param pagination
     * @param dealerJoin
     * @param request
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/dealerList")
    public JsonMessage dealerList(Pagination pagination, DealerJoin dealerJoin, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
        dealerJoin.setBrandId(brandId);
        PaginateResult<Map<String, Object>> page = dealerJoinService.queryDealerJoinPage(pagination, dealerJoin);
        return this.getJsonMessage(CommonConst.SUCCESS, page);
    }
    
    /**
     * 设置等级
     *
     * @param dealerJoin
     * @param idAry
     * @return
     * @throws BusinessException
     * @author 施建波
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/setDealer")
    public JsonMessage setDealer(DealerJoin dealerJoin, String[] idAry) throws BusinessException
    {
        if (ArrayUtils.isEmpty(idAry) || StringUtils.isBlank(dealerJoin.getBrandsId())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = brandLevelService.getBrandParentRefrenceId(userPrincipal.getRefrenceId());
        dealerJoin.setBrandId(brandId);
        dealerJoinService.updateDealerLevel(dealerJoin, idAry);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
