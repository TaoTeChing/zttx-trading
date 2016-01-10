/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandAddress;
import com.zttx.web.module.brand.entity.BrandStore;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandAddressModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandAddressService;
import com.zttx.web.module.brand.service.BrandStoreService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * 品牌商发货地址信息 控制器
 * <p>File：BrandAddressController.java </p>
 * <p>Title: BrandAddressController </p>
 * <p>Description:BrandAddressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandAddress")
public class BrandAddressController extends GenericController
{
    @Autowired(required = true)
    private BrandAddressService brandAddressService;
    
    @Autowired
    private BrandStoreService   brandStoreService;
    
    @Autowired
    private BrandesInfoService  brandesInfoService;
    
    /**
     * 新增地址提交表单页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "execute", method = RequestMethod.GET)
    public String execute(HttpServletRequest request, @RequestParam(required = false) String id, @RequestParam(required = false) String brandsId, Model model)
            throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String branderId = userPrincipal.getRefrenceId();
        model.addAttribute("brandsId", brandsId);
        // 老地址修改
        if (null != id)
        {
            BrandAddress oldAddress = brandAddressService.findByIdWithCheck(id, branderId);
            List<BrandStore> stores;
            if (oldAddress == null)
            {
                stores = Collections.<BrandStore> emptyList();
            }
            else
            {
                stores = brandStoreService.listBrandStore(oldAddress.getBrandsId());
            }
            model.addAttribute("brandsName", brandesInfoService.selectByPrimaryKey(oldAddress.getBrandsId()).getBrandsName());
            model.addAttribute("id", id);
            model.addAttribute("oldAddress", oldAddress);
            model.addAttribute("stores", stores);
            Integer areaNo = oldAddress.getAreaNo();
            model.addAttribute("province", areaNo / 10000 + "0000");
            model.addAttribute("city", areaNo / 100 + "00");
            return "brand/update_brandAddress";
        }
        // 新增地址
        List<Map<String, Object>> storeList = Lists.newArrayList();
        List<BrandsInfoModel> brands = brandesInfoService.getCooperatedBrandes(branderId);
        model.addAttribute("brandes", brands);
        // 仓库信息
        for (BrandesInfo brandsInfo : brands)
        {
            Map<String, Object> map = Maps.newHashMap();
            map.put("brandsId", brandsInfo.getRefrenceId());
            map.put("stores", brandStoreService.listBrandStore(branderId, brandsInfo.getRefrenceId()));
            storeList.add(map);
        }
        model.addAttribute("storeList", storeList);
        model.addAttribute("addressPage",
                brandAddressService.listAddressMap(new Pagination(ApplicationConst.DEFAULT_CURRENT_PAGE, ApplicationConst.DEFAULT_PAGE_SIZE), branderId));
        return "brand/create_brandAddress";
    }
    
    /**
     * ajax 分页请求
     *
     * @param pagination 当前页码, 不指定默认为第一页
     * @param request
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "list.json", method = RequestMethod.GET)
    public JsonMessage listAddress(Pagination pagination, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        PaginateResult<Map<String, Object>> result = brandAddressService.listAddressMap(pagination, brandId);
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, result);
        return jsonMessage;
    }
    
    /**
     * 设置默认地址
     * @param addressId
     * @param request
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "setDefault", method = RequestMethod.POST)
    public JsonMessage setDefault(String addressId, HttpServletRequest request) throws BusinessException
    {
        String branderId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        brandAddressService.updateDefault(addressId, branderId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 保存新地址
     *
     * @param request
     * @param brandAddress
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, BrandAddressModel brandAddress) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        brandAddress.setBrandId(userPrincipal.getRefrenceId());
        brandAddressService.insertAddress(brandAddress);
        return "redirect:execute";
    }
    
    /**
     * 更新品牌商地址
     * @param request
     * @param addressModel
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, BrandAddressModel addressModel) throws BusinessException
    {
        String branderId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        addressModel.setBrandId(branderId);
        brandAddressService.updateAddress(addressModel);
        return "redirect:execute";
    }
    
    /**
     * 删除品牌商地址
     * @param request
     * @param addressId
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMessage delete(HttpServletRequest request, String addressId) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        brandAddressService.deleteByIdAndBranderId(addressId, brandId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
