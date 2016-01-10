/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandesInfoRegional;
import com.zttx.web.module.brand.model.BrandesInfoRegionalModel;
import com.zttx.web.module.brand.service.BrandesInfoRegionalService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * 品牌商品牌地区列表 控制器
 * <p>File：BrandesInfoRegionalController.java </p>
 * <p>Title: BrandesInfoRegionalController </p>
 * <p>Description:BrandesInfoRegionalController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandesInfoRegional")
public class BrandesInfoRegionalController extends GenericController
{
    @Autowired(required = true)
    private BrandesInfoRegionalService brandesInfoRegionalService;
    
    @Autowired
    private BrandesInfoService         brandesInfoService;
    
    /**
     * 品牌区域设置
     * @author chenjp
     * @param id 品牌ID
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/view")
    @RequiresPermissions("brand:center")
    public String view(String id, HttpServletRequest request, Model model)
    {
        UserPrincipal user = OnLineUserUtils.getPrincipal();
        if (null == user || StringUtils.isNotBlank(user.getRefrenceId()))
        {
            String brandStates = "" + BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED + "," + BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED;
            List<BrandesInfo> brandes = brandesInfoService.listBrandStates(user.getRefrenceId(), brandStates);
            model.addAttribute("brandes", brandes);
            if (StringUtils.isNotBlank(id) && !brandes.isEmpty())
            {
                BrandesInfo brandesInfo = brandesInfoService.findByBrandIdAndBrandsId(user.getRefrenceId(), id);
                model.addAttribute("brandesInfo", brandesInfo);
                model.addAttribute("id", id);
                BrandesInfoRegionalModel searchBean = new BrandesInfoRegionalModel();
                searchBean.setBrandId(user.getRefrenceId());
                searchBean.setBrandesId(id);
                List<BrandesInfoRegional> regionalList = brandesInfoRegionalService.getBrandesInfoRegionalList(searchBean);
                model.addAttribute("regionalList", regionalList);
            }
        }
        return "brand/edit_brandesInfoRegional";
    }
    
    /**
     * 保存品牌区域
     * @author chenjp
     * @param request
     * @param brandesInfoRegionalModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(HttpServletRequest request, BrandesInfoRegionalModel brandesInfoRegionalModel) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getPrincipal();
        if (null == user || StringUtils.isBlank(user.getRefrenceId())) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "请先登录"); }
        brandesInfoRegionalModel.setBrandId(user.getRefrenceId());
        BrandesInfo brandesInfo = brandesInfoService.findByBrandIdAndBrandsId(user.getRefrenceId(), brandesInfoRegionalModel.getBrandesId());
        if (StringUtils.isBlank(brandesInfoRegionalModel.getBrandesId()) || null == brandesInfo) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "请先选择品牌"); }
        if (brandesInfo.getBrandState() != BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "该品牌未合作无法设置"); }
        /** 允许用户界面上不选择任何一个区域
        if (ArrayUtils.isEmpty(brandesInfoRegionalModel.getAreaAry())){
            return this.getJsonMessage(CommonConst.FORM_PARAMS_VALID_ERR.getCode(), "至少选择一个地区");
        }
         */
        brandesInfoRegionalService.deleteBrandesInfoRegional(brandesInfoRegionalModel.getBrandesId());
        brandesInfoRegionalService.insertBrandesInfoRegional(brandesInfoRegionalModel);
        return this.getJsonMessage(CommonConst.SUCCESS.getCode());
    }
}
