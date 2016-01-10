/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;

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
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandImgcate;
import com.zttx.web.module.brand.service.BrandImgcateService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 品牌商图片分类信息 控制器
 * <p>File：BrandImgcateController.java </p>
 * <p>Title: BrandImgcateController </p>
 * <p>Description:BrandImgcateController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandImgcate")
public class BrandImgcateController extends GenericController
{
    @Autowired(required = true)
    private BrandImgcateService brandImgcateService;
    
    @RequestMapping(value = "/imgcate")
    @RequiresPermissions("brand:center")
    public String listImgCate(Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        BrandImgcate imgCate = new BrandImgcate();
        imgCate.setBrandId(brandId);
        imgCate.setCateDefault(false);// 不显示默认分类
        // imgCate.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);默认查有效的
        List<BrandImgcate> list = brandImgcateService.findList(imgCate);
        model.addAttribute("listCate", list);
        return "/brand/list_imgcate";
    }
    
    /**
     * 批量增加(更新)图片分类
     * @param cateName
     * @param uuid
     * @param level
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/imgcate/add", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage save(String[] cateName, String[] uuid, String[] level) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        brandImgcateService.saveOrUpdate(cateName, uuid, brandId, level);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 批量删除分类，并删除其子集
     * @param ids  String[]
     * @return
     */
    @RequestMapping(value = "/imgcate/delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage delete(String[] ids) throws BusinessException
    {
        if (null != ids && ids.length > 0)
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            this.brandImgcateService.deleteBatch(ids, brandId);
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
