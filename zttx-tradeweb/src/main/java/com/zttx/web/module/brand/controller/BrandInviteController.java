/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.consts.UserInfoConst;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.service.BrandInviteService;
import com.zttx.web.module.brand.service.BrandMessageService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 品牌商邀请经销商加盟 控制器
 * <p>File：BrandInviteController.java </p>
 * <p>Title: BrandInviteController </p>
 * <p>Description:BrandInviteController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandInvite")
public class BrandInviteController extends GenericController
{
    @Autowired
    UserInfoService     userInfoService;
    
    @Autowired
    BrandInviteService  brandInviteService;
    
    @Autowired
    BrandMessageService brandMessageService;
    
    @Override
    protected <T> JsonMessage getJsonMessage(Integer code, String message, PaginateResult<T> result)
    {
        return super.getJsonMessage(code, message, result);
    }
    
    @Override
    public JsonMessage getJsonMessage(Integer code, String message)
    {
        return super.getJsonMessage(code, message);
    }
    
    /**
     * 获取区域条件
     *
     * @param info DealerInfoModel 模型
     */
    public void getArea(DealerInfoModel info)
    {
        Map<String, String> map = super.getAreaNoMap(info.getProvince(), info.getCity(), info.getCounty());
        if (!StringUtils.isBlank(map.get("areaNo")))
        {
            info.setAreaNo(Integer.valueOf(map.get("areaNo")));
            info.setLevel(Integer.valueOf(map.get("level")));
        }
    }
    
    /**
     * 品牌商 邀请 经销商加盟
     *
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "invite/join")
    @ResponseBody
    public JsonMessage inviteJoin(BrandInviteModel brandInvite) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        brandInvite.setBrandId(brandId);
        if (org.apache.commons.lang3.StringUtils.isBlank(brandInvite.getBrandsId()) || org.apache.commons.lang3.StringUtils.isBlank(brandInvite.getDealerId())) { return this
                .getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        if (org.apache.commons.lang3.StringUtils.isBlank(brandInvite.getInviteText())) { return this.getJsonMessage(DealerConst.APPLY_INFO_ERROR); }
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(brandInvite.getDealerId());
        if (dealerUserm == null || (dealerUserm.getUserType() != null && !dealerUserm.getUserType().equals(UserInfoConst.USER_TYPE_DEALER))) { return this.getJsonMessage(
                CommonConst.FAIL, "终端商不存在"); }
        brandInvite = brandInviteService.addBrandInvite(brandInvite);
        // brandInvite.getDealerInfo().setUserMobile(dealerUserm.getUserMobile());
        // 向经绡商发送邀请加盟消息
        brandMessageService.sendDealerMessage(brandInvite.getBrandId(), brandInvite.getDealerId(), "邀请加盟", brandInvite.getInviteText());
        return this.getJsonMessage(CommonConst.SUCCESS, brandInvite.getDealerInfo());
    }
}
