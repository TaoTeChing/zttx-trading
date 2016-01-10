/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.brand.entity.BrandPhoto;
import com.zttx.web.module.brand.entity.BrandRoom;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandRoomModel;
import com.zttx.web.module.brand.service.BrandPhotoService;
import com.zttx.web.module.brand.service.BrandRoomService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌商展厅信息 控制器
 * <p>File：BrandRoomController.java </p>
 * <p>Title: BrandRoomController </p>
 * <p>Description:BrandRoomController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandRoom")
public class BrandRoomController extends GenericBaseController
{
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    @Autowired
    private BrandRoomService   brandRoomService;
    
    @Autowired
    private BrandPhotoService  brandPhotoService;
    
    /**
     * 映射地址
     * @return
     * 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/execute")
    public String execute(String id, HttpServletRequest request, Model model)
    {
        String brandId = null;
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        }
        catch (BusinessException e)
        {
        }
        if (StringUtils.isNotBlank(brandId))
        {
            String brandStates = "" + BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED + "," + BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED;
            List<BrandesInfo> brandes = brandesInfoService.listBrandStates(brandId, brandStates);
            model.addAttribute("fileUrl", ZttxConst.FILEAPI_WEBURL);
            model.addAttribute("brandes", brandes);
            if (StringUtils.isNotBlank(id) && !brandes.isEmpty())
            {
                BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(id);
                BrandRoom brandRoom = brandRoomService.selectByPrimaryKey(id);
                model.addAttribute("brandRoom", brandRoom);
                model.addAttribute("id", id);
                model.addAttribute("brandesInfo", brandesInfo);
                if (null != brandesInfo)
                {
                    List<BrandPhoto> photoList = brandPhotoService.findByBrandIdAndBrandsId(brandesInfo.getBrandId(), brandesInfo.getRefrenceId());
                    model.addAttribute("photoList", photoList);
                }
            }
        }
        return "brand/edit_brandRoom";
    }
    
    /**
     * 保存资料
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(BrandRoomModel brandRoom, HttpServletRequest request)
    {
        String brandId = this.getCurrentLoginBrandId();
        String userId = this.getCurrentLoginUserId();
        List<Map<String, String>> validatorList = validator(brandRoom);
        if (null != validatorList && !validatorList.isEmpty()) { return this.getJsonMessage(CommonConst.FORM_PARAMS_VALID_ERR, validatorList); }
        try
        {
            brandRoom.setUserId(userId);
            brandRoom.setIp(IPUtil.getIpAddr(request));
            brandRoomService.saveOrUpdate(request, brandRoom, brandId);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    private List<Map<String, String>> validator(BrandRoomModel brandRoom)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "brandsId";
        if (StringUtils.isBlank(brandRoom.getBrandsId()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("品牌")));
        }
        brandRoom.setRefrenceId(brandRoom.getBrandsId());
        name = "roomName";
        String msgName = "展厅名称";
        Integer words = 64;
        if (StringUtils.isBlank(brandRoom.getRoomName()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr(msgName)));
        }
        else
        {
            if (!ValidateUtils.isRange(brandRoom.getRoomName(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr(msgName, words)));
            }
        }
        name = "logoImage";
        if (StringUtils.isBlank(brandRoom.getLogoImage()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("品牌LOGO")));
        }
        name = "photoError";
        if (ArrayUtils.isEmpty(brandRoom.getPhotoImgPaths()))
        {
            validatorList.add(this.getErrMsgMap(name, "至少需要一张企业产品形象图"));
        }
        name = "subMarkError";
        words = 1024;
        if (StringUtils.isNotBlank(brandRoom.getBrandSubMark()))
        {
            if (!ValidateUtils.isRange(brandRoom.getBrandSubMark(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr("品牌文字介绍", words)));
            }
        }
        name = "brandMark";
        msgName = "品牌介绍";
        words = 60000;
        if (StringUtils.isBlank(brandRoom.getBrandMark()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr(msgName)));
        }
        else
        {
            if (!ValidateUtils.isRange(brandRoom.getBrandMark(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr(msgName, words)));
            }
        }
        return validatorList;
    }
}
