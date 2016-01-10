/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.entity.BrandRecruit;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandInviteService;
import com.zttx.web.module.brand.service.BrandRecruitService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.common.service.SmsTemplateService;
import com.zttx.web.module.common.service.SmsTemplateServiceImpl;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerApplyService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerMessageService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CrmClient;
import com.zttx.web.utils.ValidateUtils;

/**
 * 经销商加盟申请 控制器
 * <p>File：DealerApplyController.java </p>
 * <p>Title: DealerApplyController </p>
 * <p>Description:DealerApplyController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
public class DealerApplyController extends GenericController
{
    @Autowired(required = true)
    private DealerApplyService       dealerApplyService;
    
    @Autowired
    private DealerInfoService        dealerInfoService;
    
    @Autowired
    private BrandInfoService         brandInfoService;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired
    private DealerMessageService     dealerMessageService;
    
    @Autowired
    private UserInfoService          userInfoService;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired
    private DataDictValueService     dataDictValueService;
    
    @Autowired
    private BrandInviteService       brandInviteService;
    
    @Autowired
    private CrmClient                crmClient;
    
    @Autowired
    private SmsTemplateService       smsTemplateService;
    
    @Autowired
    private BrandRecruitService      brandRecruitService;
    
    @Autowired
    private UserInfoService          brandUsermService;
    
    /**
     * 经销商管理后台，加盟管理，申请中的
     * @param model
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/dealer/copartner/apply")
    public String joinManageAplly(Model model)
    {
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_apply";
    }
    
    /**
     * 经销商管理后台，加盟管理，我撤销的
     *
     * @param model
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/dealer/copartner/disapply")
    public String disApply(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        String dealerId = userPrincipal.getRefrenceId();
        model.addAttribute("dealerUserm", userInfoService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.findById(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_disapply";
    }
    
    /**
     * 经销商管理后台，加盟管理，未通过审核的
     *
     * @param model
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/dealer/copartner/nopass")
    public String noPass(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        String dealerId = userPrincipal.getRefrenceId();
        model.addAttribute("dealerUserm", userInfoService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.findById(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_nopass";
    }
    
    /**
     * 撤消申请
     *
     * @param refrenceId
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/dealer/joinManage/apply/del")
    public JsonMessage joinManageDel(String refrenceId) throws BusinessException
    {
        if (StringUtils.isNotBlank(refrenceId))
        {
            dealerApplyService.delApply(refrenceId);
            return this.getJsonMessage(DealerConst.SUCCESS);
        }
        return this.getJsonMessage(DealerConst.USERM_NOLOGIN);
    }
    
    /**
     * 申请加盟
     *
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/dealer/joinManage/apply/join", method = RequestMethod.POST)
    public JsonMessage joinApply(BrandInviteModel brandInvite) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        String dealerId = userPrincipal.getRefrenceId();
        if (null == brandInvite || StringUtils.isBlank(brandInvite.getBrandsId()))
        {
            if (StringUtils.isBlank(brandInvite.getProductId())) { return this.getJsonMessage(DealerConst.DEALER_NOPARAM); }
            ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(brandInvite.getProductId());
            brandInvite.setBrandId(productBaseInfo.getBrandId());
            brandInvite.setBrandsId(productBaseInfo.getBrandsId());
        }
        if (StringUtils.isBlank(brandInvite.getTelphone()) || brandInvite.getTelphone().length() != 11)
        {
            brandInvite.setTelphone(userInfoService.selectByPrimaryKey(dealerId).getUserMobile());
        }
        return this._joinApply(brandInvite, dealerId);
    }
    
    /**
     * 修改申请状态
     *
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/dealer/joinManage/apply/update")
    public JsonMessage updateApply(BrandInvite brandInvite) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        String dealerId = userPrincipal.getRefrenceId();
        brandInvite.setDealerId(dealerId);
        if (StringUtils.isBlank(brandInvite.getBrandsId())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        try
        {
            brandInviteService.updateInvite(brandInvite);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS, brandInvite.getBrandId());
    }
    
    /**
     * 根据不同状态获取申请记录
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/dealer/copartner/apply/list")
    public Object listJson(Pagination pagination, BrandInviteModel brandInvite) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        String dealerId = userPrincipal.getRefrenceId();
        brandInvite.setDealerId(dealerId);
        pagination.setPageSize(10);
        brandInvite.setApplyState(brandInvite.getApplyState() == null ? 0 : brandInvite.getApplyState());
        PaginateResult<Map<String, Object>> page = brandInviteService.getInviteApplyStateList(pagination, brandInvite);
        return this.getJsonMessage(CommonConst.SUCCESS, page);
    }
    
    public JsonMessage _joinApply(BrandInviteModel brandInvite, String dealerId)
    {
        if (StringUtils.isBlank(brandInvite.getBrandsId())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        if (!ValidateUtils.isMobileFormat(brandInvite.getTelphone(), true, 11)) { return this.getJsonMessage(DealerConst.APPLY_TELPHONE_ERROR); }
        if (StringUtils.isBlank(brandInvite.getInviteText())) { return this.getJsonMessage(DealerConst.APPLY_INFO_ERROR); }
        brandInvite.setDealerId(dealerId);
        brandInvite.setReadState(new Short("0"));
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandInvite.getBrandsId());
        String _brandId = brandesInfo.getBrandId();
        BrandRecruit _brandRecruit = brandRecruitService.findByBrandIdAndBrandesid(_brandId, brandInvite.getBrandsId());
        if (_brandRecruit == null || _brandRecruit.getRecruitState() == null || _brandRecruit.getRecruitState().intValue() == 0) { return this
                .getJsonMessage(CommonConst.BRAND_RECRUIT_CLOSE); }
        BrandesInfo _brandesInfo = brandesInfoService.selectByPrimaryKey(brandInvite.getBrandsId());
        DealerInfo dealerInfo = dealerInfoService.findById(dealerId);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(_brandesInfo.getBrandId());
        try
        {
            brandInviteService.insertInvite(brandInvite);
            String context = "【%s】向您的品牌【%s】提交了加盟申请，并留言：%s。联系方式：%s";
            dealerMessageService.sendBrandMessage(brandInvite.getDealerId(), brandInvite.getBrandId(), "申请加盟",
                    String.format(context, dealerInfo.getDealerName(), brandInfo.getComName(), brandInvite.getInviteText(), brandInvite.getTelphone()));
            crmClient.sendRTX(CrmClient.SEND_RTX_TYPE_APPLY, dealerId);
            SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_DEALER_APPLY_INVITE);
            if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
            {
                String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月DD日 HH:mm").format(new Date()), dealerInfo.getDealerName(),
                        brandInfo.getComName());
                brandUsermService.sendSmsToDealerUser(_brandRecruit.getBrandId(), smsContent);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
