/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.web.consts.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandAlbum;
import com.zttx.web.module.brand.entity.BrandVisual;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandAlbumService;
import com.zttx.web.module.brand.service.BrandVisualService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌视觉信息 控制器
 * <p>File：BrandVisualController.java </p>
 * <p>Title: BrandVisualController </p>
 * <p>Description:BrandVisualController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandVisual")
public class BrandVisualController extends GenericBaseController
{
    @Autowired(required = true)
    private BrandVisualService brandVisualService;
    
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    @Autowired
    private BrandAlbumService  brandAlbumService;
    
    /**
     * 品牌视觉首页
     * @param request
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/visual")
    public String index(String id, HttpServletRequest request, Model model) throws BusinessException
    {
        String brandId = super.getCurrentLoginBrandId();
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED);
        List<BrandesInfo> brandes = brandesInfoService.listByBrandStates(brandId, brandStates);
        model.addAttribute("brandes", brandes);
        if (StringUtils.isNotBlank(id) && !brandes.isEmpty())
        {
            BrandVisual brandVisual = brandVisualService.findByBrandsIdAndBrandId(brandId, id);
            model.addAttribute("brandVisual", brandVisual);
            List<BrandAlbum> brandAlbums = brandAlbumService.findByBrandAndBrands(brandId, id);
            if (!CollectionUtils.isEmpty(brandAlbums))
            {
                for (BrandAlbum item : brandAlbums)
                {
                    item.setChangeImagePath(item.getImageName());
                }
            }
            model.addAttribute("brandAlbums", brandAlbums);
            model.addAttribute("id", id);
        }
        return "brand/manage_brandPerfect";
    }
    
    /**
     * 提交保存
     * 
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/visual/save", method = RequestMethod.POST)
    public JsonMessage save(BrandVisual brandVisual, HttpServletRequest request, Model model, @RequestParam(value = "images", required = false) String[] images,
            @RequestParam(value = "photoName", required = false) String[] photoNames) throws BusinessException
    {
        String brandId = this.getCurrentLoginBrandId();
        List<Map<String, String>> validatorList = validator(brandVisual);
        if (null != validatorList && !validatorList.isEmpty()) { return this.getJsonMessage(CommonConst.FORM_PARAMS_VALID_ERR, validatorList); }
        brandVisual.setBrandId(brandId);
        brandVisual.setCreateIp(IPUtil.getIpAddr(request));
        brandVisualService.save(brandVisual, images, photoNames, request);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 预览
     * @param brandVisual
     * @param request
     * @return
     */
    @RequestMapping(value = "/visual/view")
    public String view(BrandVisual brandVisual, HttpServletRequest request, Model model)
    {
        model.addAttribute("brandVisual", brandVisual);
        return "brand/Vision_view";
    }
    
    private List<Map<String, String>> validator(BrandVisual brandVisual)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "brandsId";
        if (StringUtils.isBlank(brandVisual.getBrandsId()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("品牌")));
        }
        name = "video";
        Integer max = 128;
        if (StringUtils.isNotBlank(brandVisual.getVedioFile()))
        {
            if (!ValidateUtils.isRange(brandVisual.getVedioFile(), true, max))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr("视频地址", max)));
            }
            else
            {
                if (!ValidateUtils.isUrl(brandVisual.getVedioFile(), true, max))
                {
                    validatorList.add(this.getErrMsgMap(name, "不是有效的视频地址"));
                }
            }
        }
        name = "flash";
        if (StringUtils.isNotBlank(brandVisual.getThreeFile()))
        {
            if (!ValidateUtils.isRange(brandVisual.getThreeFile(), true, max))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr("3D动画展厅地址", max)));
            }
            else
            {
                if (!ValidateUtils.isUrl(brandVisual.getThreeFile(), true, max))
                {
                    validatorList.add(this.getErrMsgMap(name, "不是有效的3D动画展厅地址"));
                }
            }
        }
        return validatorList;
    }
}
