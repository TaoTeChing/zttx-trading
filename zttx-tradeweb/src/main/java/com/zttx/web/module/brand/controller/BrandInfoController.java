/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.LoggerUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.model.BrandCatelogModel;
import com.zttx.web.module.brand.model.BrandInfoModel;
import com.zttx.web.module.brand.service.BrandCatelogService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.TelCode;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.NetworkUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌商基本信息 控制器
 * <p>File：BrandInfoController.java </p>
 * <p>Title: BrandInfoController </p>
 * <p>Description:BrandInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/info")
public class BrandInfoController extends GenericController
{
    Logger                       logger = LoggerFactory.getLogger(BrandInfoController.class);
    
    @Autowired(required = true)
    private BrandInfoService     brandInfoService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    private DealDicService       dealDicService;
    
    @Autowired
    private UserInfoService      userInfoService;
    
    @Autowired
    private BrandCatelogService  brandCatelogService;
    
    @Autowired
    private RegionalService      regionalService;
    
    @Autowired
    private TelCodeService       telCodeService;
    
    @RequestMapping(value = "")
    public String defaultRedirect()
    {
        return "redirect:/brand/info/index";
    }
    
    /**
     * 跳转到经销商资料信息页
     *
     * @param model
     * @param request
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
        // 添加字典 鲍建明
        model.addAttribute("runType", dataDictValueService.findByDictCode(DataDictConstant.BRAND_RUN_TYPE));
        model.addAttribute("companyType", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_TYPE));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("brandTurnover", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        model.addAttribute("brandInfo", brandInfo);
        // 所有品类
        model.addAttribute("dealList", this.getDealDicJson());
        // 选中的品类
        List<BrandCatelogModel> brandCatelogs = brandCatelogService.getBrandCatelogByBrandId(userPrincipal.getRefrenceId());
        model.addAttribute("brandCatelogs", brandCatelogs);
        if (null != brandInfo && BrandConstant.BrandInfoConst.CHECK_STATE_PASS_AUDIT == brandInfo.getCheckState())
        {
            // 营业执照编号加*
            if (null != brandInfo)
            {
                model.addAttribute("comNum", com.zttx.web.utils.StringUtils.changeString(brandInfo.getComNum(), 7, "*"));
            }
            return "/brand/account_brandinfo_pass_modify";
        }
        else
        {
            if (null == brandInfo)
            {
                UserInfo userm = userInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
                if (null != userm && null == userm.getComName()) userm.setComName(userm.getUserName());
                model.addAttribute("userm", userm);
            }
            return "/brand/account_brandinfo_modify";
        }
    }
    
    /**
     * 获取JSON格式的类目数据
     *
     * @return
     * @author 张昌苗
     */
    public String getDealDicJson()
    {
        try
        {
            return this.dealDicService.getDealDicJson();
        }
        catch (BusinessException e)
        {
            LoggerUtils.logError(logger, e.getLocalizedMessage());
        }
        return "[]";
    }
    
    /**
     * 资料信息 保存
     *
     * @param model
     * @param request
     * @return
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public JsonMessage submit(BrandInfoModel brandInfo, int[] dealNos, HttpServletRequest request, Model model)
    {
        try
        {
            // 获取原数据
            UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
            String brandId = userPrincipal.getRefrenceId();
            BrandInfo oldBrandInfo = brandInfoService.selectByPrimaryKey(brandId);
            if (null != oldBrandInfo && BrandConstant.BrandInfoConst.CHECK_STATE_PASS_AUDIT == oldBrandInfo.getCheckState())
            {
                brandInfo.setRefrenceId(brandId);
                // 审核通过后的修改
                this.checkPassBrandInfo(brandInfo);
                this.brandInfoService.editPassBrandInfo(brandInfo, oldBrandInfo, dealNos);
            }
            else
            {
                this.checkAllBrandInfo(brandInfo, oldBrandInfo, model);
                this.setRegionalName(brandInfo);
                brandInfo.setRefrenceId(brandId);
                brandInfo.setDomainName(NetworkUtils.getDoMainName());
                // 企业类型目前不用，先设为0
                brandInfo.setComType((short) 0);
                this.brandInfoService.editBrandInfoImage(brandInfo, oldBrandInfo);
                // 保存
                this.brandInfoService.editBrandInfo(brandInfo, oldBrandInfo, dealNos);
            }
        }
        catch (Exception e)
        {
            return this.getJsonMessage(CommonConst.FAIL.code, e.getLocalizedMessage());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    private void setRegionalName(BrandInfo brandInfo) throws BusinessException
    {
        Integer code = brandInfo.getAreaNo();
        String fullName = this.regionalService.getFullNameByAreaNo(code, RegionalService.REGIONAL_SPLIT_CODE);
        if (StringUtils.isBlank(fullName)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        String[] nameAry = {"", "", ""};
        String[] tempNameAry = fullName.split(RegionalService.REGIONAL_SPLIT_CODE);
        for (int i = 0; i < tempNameAry.length; i++)
        {
            nameAry[i] = tempNameAry[i];
        }
        brandInfo.setProvinceName(nameAry[0]);
        brandInfo.setCityName(nameAry[1]);
        brandInfo.setAreaName(nameAry[2]);
    }
    
    private void checkAllBrandInfo(BrandInfoModel brandInfo, BrandInfo oldBrandInfo, Model model) throws BusinessException
    {
        String brandId = "";
        if (null != oldBrandInfo)
        {
            brandId = oldBrandInfo.getRefrenceId();
        }
        if (brandInfoService.isExits(brandInfo.getComName(), brandId)) { throw new BusinessException(BrandConst.BRAND_INFO_COMNAME_EXISTS); }
        // 验证
        if (!super.beanValidatorNoProperty(model, brandInfo)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        if (!ValidateUtils.isUrl(brandInfo.getComWeb(), false, 128)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 证书图片不能为空
        if ((StringUtils.isBlank(brandInfo.getBrandImage()) || StringUtils.isBlank(brandInfo.getBrandPhoto()))
                && (StringUtils.isBlank(brandInfo.getFile_cert_imageImage()) || StringUtils.isBlank(brandInfo.getFile_cert_imagePhoto()))) { throw new BusinessException(
                CommonConst.PARAMS_VALID_ERR); }
        // 身份证图片正面不能为空
        if (StringUtils.isBlank(brandInfo.getUserPhoto())
                && (StringUtils.isBlank(brandInfo.getFile_user_photoImage()) || StringUtils.isBlank(brandInfo.getFile_user_photoPhoto()))) { throw new BusinessException(
                CommonConst.PARAMS_VALID_ERR); }
        // 身份证图片反面不能为空
        if (StringUtils.isBlank(brandInfo.getUserImage())
                && (StringUtils.isBlank(brandInfo.getFile_user_imageImage()) || StringUtils.isBlank(brandInfo.getFile_user_imagePhoto()))) { throw new BusinessException(
                CommonConst.PARAMS_VALID_ERR); }
    }
    
    private void checkPassBrandInfo(BrandInfo brandInfo) throws BusinessException
    {
        if (!ValidateUtils.isUrl(brandInfo.getComWeb(), false, 128)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        if (StringUtils.isBlank(brandInfo.getComMark())) { throw new BusinessException(CommonConst.PARAM_NULL); }
        if (StringUtils.isBlank(brandInfo.getComAddress())) { throw new BusinessException(CommonConst.PARAM_NULL); }
    }
    
    /**
     * 安全验证页面
     *
     * @param request
     * @param model
     * @return
     * @throws BusinessException
     * @author 徐志勇
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/verifPage")
    public String verifPage(HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        // BrandUserm brandUserm = brandUsermService.findById(brandId);
        UserInfo brandUserm = userInfoService.selectByPrimaryKey(brandId);
        model.addAttribute("brandUserm", brandUserm);
        return "brand/securityVeri_brandInfo";
    }
    
    @RequestMapping(value = "/center/getCaptcha")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage getCaptcha(HttpServletRequest request)
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        // 生成验证码
        TelCode telCode = new TelCode();
        telCode.setCusType(ClientConst.CUS_TYPE_TRADE);
        telCode.setVerifyType(TelVerifyTypeConst.DEAL_PLATFORM);
        telCode.setUserMobile(userPrincipal.getUserMobile());
        telCode.setCreateIp(IPUtil.getIpAddr(request));
        Integer iRetCode = telCodeService.create(telCode);
        if (ExceptionConst.EXISTES.intValue() == iRetCode.intValue())
        {
            return super.getJsonMessage(CommonConst.VALID_EXIST_ERR);
        }
        else if (ExceptionConst.SUCCESS.intValue() != iRetCode.intValue()) { return super.getJsonMessage(CommonConst.VALID_CREATE_ERR); }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 手机认证
     *
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/mobile")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage verifyMobile(HttpServletRequest request, String oldVerifyCode, String newUserMobile, String newVerifyCode) throws BusinessException
    {
        UserPrincipal userm = OnLineUserUtils.getCurrentBrand();
        this.checkTelCode(userm.getUserMobile(), oldVerifyCode);
        this.checkTelCode(newUserMobile, newVerifyCode);
        this.userInfoService.updateVerifyMobile(userm.getRefrenceId(), newUserMobile, true);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    private void checkTelCode(String userMobile, String telCode) throws BusinessException
    {
        if (StringUtils.isBlank(telCode)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        if (!ValidateUtils.isMobileFormat(userMobile, true, 11)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 验证码校验
        Integer verifyResult = telCodeService.verifyAndCheck(userMobile, TelVerifyTypeConst.DEAL_PLATFORM, telCode);
        if (ExceptionConst.SUCCESS.intValue() != verifyResult.intValue()) { throw new BusinessException(BrandConst.VERIFY_FAIL); }
        telCodeService.modifyStateUsed(userMobile, TelVerifyTypeConst.DEAL_PLATFORM);
    }
    
    /**
     * 审核中页面
     *
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/unchecked")
    public String unchecked()
    {
        return "brand/unchecked_brandInfo";
    }
}
