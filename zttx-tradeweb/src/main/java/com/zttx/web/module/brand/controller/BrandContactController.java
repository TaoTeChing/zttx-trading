/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.service.BrandContactService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.entity.UserOnlineService;
import com.zttx.web.module.common.model.UserOnlineServiceModel;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.common.service.UserOnlineServiceService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.EntityUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌商联系方式 控制器
 * <p>File：BrandContactController.java </p>
 * <p>Title: BrandContactController </p>
 * <p>Description:BrandContactController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/contact")
public class BrandContactController extends GenericController
{
    @Autowired(required = true)
    private BrandContactService      brandContactService;
    
    @Autowired
    private UserOnlineServiceService userOnlineServiceService;
    
    @Autowired
    private UserInfoService          userInfoService;
    
    /**
     * 品牌商联系方式管理
     *
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("brand:center")
    public String brandContactInfo(HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        if (userPrincipal == null || (userPrincipal.getUserType().intValue() != 0)) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); }
        UserInfo brandUserm = userInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
        BrandContact brandContact = this.brandContactService.findByBrandId(brandUserm.getRefrenceId());
        model.addAttribute("brandUserm", brandUserm);
        model.addAttribute("brandContact", brandContact);
        UserOnlineService userOnlineService = userOnlineServiceService.findUserOnlineService(brandUserm.getRefrenceId());
        UserOnlineServiceModel onlineServiceModel = new UserOnlineServiceModel();
        EntityUtils.copyProperties(onlineServiceModel, userOnlineService);
        this.userOnlineServiceService.fillUserOnlineServiceDetail(onlineServiceModel);
        model.addAttribute("userOnlineService", onlineServiceModel);
        return "brand/info_brandContact";
    }
    
    /**
     * 保存
     *
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage save(BrandContact brandContact, UserOnlineServiceModel userOnlineService, HttpServletRequest request, Model model) throws BusinessException
    {
        // 验证
        if (!super.beanValidatorNoProperty(model, brandContact)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        List<String> list = verify(brandContact, userOnlineService);
        if (CollectionUtils.isNotEmpty(list)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        BrandContact old = this.brandContactService.findByBrandId(brandId);
        this.brandContactService.updateFile(brandContact, old);
        // 保存客服资料
        if (userOnlineService.getShowed() != null)
        {
            userOnlineService.setShowed(1);
        }
        else
        {
            userOnlineService.setShowed(0);
        }
        userOnlineService.setUserId(brandId);
        userOnlineService.setUserType((short) UserAccountConst.DEALER);
        this.userOnlineServiceService.save(userOnlineService);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 更新头像
     * @param userPhoto 头像临时路径
     * @return JsonMessage
     * @throws BusinessException
     */
    @RequestMapping(value = "/updatePhoto")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage updatePhoto(String userPhoto) throws BusinessException
    {
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS);
        try
        {
            if (StringUtils.isNotBlank(userPhoto))
            {
                String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
                BrandContact brandContact = this.brandContactService.findByBrandId(brandId);// refrenceId == null 数据库不存在联系方式
                this.brandContactService.updatePhote(userPhoto, brandContact);
                jsonMessage.setObject(brandContact.getUserPhoto());
            }
        }
        catch (BusinessException e)
        {
            jsonMessage.setCode(e.getErrorCode().getCode());
            jsonMessage.setMessage(e.getErrorCode().getMessage());
        }
        return jsonMessage;
    }
    
    /**
     * 后台校验
     *
     * @param brandContact
     * @return
     */
    private List<String> verify(BrandContact brandContact, UserOnlineService userOnlineService)
    {
        List<String> list = Lists.newArrayList();
        if (StringUtils.isBlank(brandContact.getUserName()))
        {
            list.add("联系人名称不能为空");
        }
        if (!ValidateUtils.isIntRange(brandContact.getUserGender(), 0, 2, true))
        {
            list.add("性别不正确");
        }
        if (!ValidateUtils.isNull(brandContact.getUserMobile()))
        {
            if (!ValidateUtils.isMobileFormat(brandContact.getUserMobile(), true, 11))
            {
                list.add("手机的格式不正确");
            }
        }
        if (!ValidateUtils.isNull(brandContact.getUserTelphone()))
        {
            if (!ValidateUtils.isTelFormat(brandContact.getUserTelphone(), true, 15))
            {
                list.add("电话的格式不正确");
            }
        }
        if (ValidateUtils.isNull(brandContact.getUserTelphone()) && ValidateUtils.isNull(brandContact.getUserMobile()))
        {
            list.add("手机和电话请至少填写一个，电话的格式：区号-电话号码-分机");
        }
        return list;
    }
}
