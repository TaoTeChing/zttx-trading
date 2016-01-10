/*
 * @(#)DealerManageController.java 2014-3-24 下午1:36:53
 * Copyright 2014 罗盛平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandCount;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.model.BrandFavoriteView;
import com.zttx.web.module.brand.model.BrandInviteView;
import com.zttx.web.module.brand.model.BrandVisitedView;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.*;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerGroom;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.model.DealerGroomView;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.model.DealerVisitedView;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>File：DealerManageController.java</p>
 * <p>Title:经销商管理控制器 </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-24 下午1:36:53</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.BRAND + "/dealer")
public class DealerManageController extends GenericController
{
    @Autowired
    private DealerInfoService       dealerInfoService;
    
    @Autowired
    private DealerVisitedService    dealerVisitedService;
    
    @Autowired
    private DealerGroomService      dealerGroomService;
    
    @Autowired
    private BrandVisitedService     brandVisitedService;
    
    @Autowired
    private DealerApplyService      dealerApplyService;
    
    @Autowired
    private BrandesInfoService      brandesInfoService;
    
    @Autowired
    private BrandInfoService        brandInfoService;
    
    @Autowired
    private DealDicService          dealDicService;
    
    @Autowired
    private DataDictValueService    dataDictValueService;
    
    @Autowired(required = true)
    private BrandInviteService      brandInviteService;
    
    // @Autowired
    // private DataDictValueService dataDictValueService;
    @Autowired
    private UserInfoService         userInfoService;
    
    @Autowired
    private SmsTemplateService      smsTemplateService;
    
    @Autowired
    private BrandViewContactService brandViewContactService;
    
    @Autowired
    private BrandCountService       brandCountService;
    
    @Autowired
    private BrandFavoriteService    brandFavoriteService;
    
    @Autowired
    private BrandMessageService     brandMessageService;
    
    @Autowired
    private DealerImageService      dealerImageService;
    
    // @Autowired
    // private BrandCountCache brandCountCache;
    //
    // @Autowired
    // private DealDicService dealDicService;
    //
    @Autowired
    private DealerJoinService       dealerJoinService;
    
    /**
     * 品牌商 下 邀请中的经销商页面
     *
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/invite")
    public String inviteIndex(Model model)
    {
        this.addDataDict(model);
        return "brand/list_brandInvite";
    }
    
    /**
     * 品牌商 下 邀请或申请中的经销商列表
     *
     * @param pagination
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/invite/data")
    public Object listInviteJson(Pagination pagination, BrandInviteView brandInvite) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        Map<String, String> map = super.getAreaNoMap(brandInvite.getProvince(), brandInvite.getCity(), brandInvite.getCounty());
        if (!StringUtils.isBlank(map.get("areaNo")))
        {
            brandInvite.setAreaNo(Integer.valueOf(map.get("areaNo")));
            brandInvite.setLevel(Integer.valueOf(map.get("level")));
        }
        brandInvite.setBrandId(brandId);
        Short applyState = brandInvite.getApplyState();
        applyState = (BrandConstant.BrandInviteConst.APPLY_STATE_INVITE == applyState.shortValue() || BrandConstant.BrandInviteConst.APPLY_STATE_FAILURE == applyState
                .shortValue()) ? applyState : BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK;
        brandInvite.setApplyState(applyState);
        try
        {
            PaginateResult<Map<String, Object>> result = brandInviteService.search(pagination, brandInvite);
            return this.getJsonMessage(CommonConst.SUCCESS, result);
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            return this.getJsonMessage(CommonConst.FAIL, "异常");
        }
    }
    
    /**
     * 寻找更多经销商页面
     *
     * @param model
     * @return String
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/findDealer")
    public String listDealer(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandsInfos", brandsInfos);
        this.addDataDict(model);
        return "/brand/find_dealers";
    }
    
    /**
     * 寻找更多经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/findDealer/data")
    public Object listDealerJson(Pagination pagination, DealerInfoModel info) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        this.getArea(info);
        info.setBrandId(brandId);
        PaginateResult<Map<String, Object>> result = dealerInfoService.search(pagination, info);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 浏览过我的经销商列表
     *
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/dealerVisited")
    public String listDealerVisited(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandsInfos", brandsInfos);
        this.addDataDict(model);
        return "/brand/list_dealerVisited";
    }
    
    /**
     * 查询浏览过我的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/dealerVisited/data")
    public Object listDealerVisitedJson(Pagination pagination, DealerVisitedView info) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        Map<String, String> map = super.getAreaNoMap(info.getProvince(), info.getCity(), info.getCounty());
        if (!StringUtils.isBlank(map.get("areaNo")))
        {
            info.setAreaNo(Integer.valueOf(map.get("areaNo")));
            info.setLevel(Integer.valueOf(map.get("level")));
        }
        info.setBrandId(brandId);
        try
        {
            PaginateResult<Map<String, Object>> result = dealerVisitedService.search(pagination, info);
            return this.getJsonMessage(CommonConst.SUCCESS, result);
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            return this.getJsonMessage(CommonConst.FAIL, "异常");
        }
    }
    
    /**
     * 推荐的经销商页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/listDealerGroom")
    public String listDealerGroom(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandsInfos", brandsInfos);
        this.addDataDict(model);
        return "/brand/list_dealerGroom";
    }
    
    /**
     * 推荐的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/listDealerGroom/data")
    public Object listDealerGroomJson(Pagination pagination, DealerGroomView info) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        Map<String, String> map = super.getAreaNoMap(info.getProvince(), info.getCity(), info.getCounty());
        if (!StringUtils.isBlank(map.get("areaNo")))
        {
            info.setAreaNo(Integer.valueOf(map.get("areaNo")));
            info.setLevel(Integer.valueOf(map.get("level")));
        }
        info.setBrandId(brandId);
        try
        {
            PaginateResult<Map<String, Object>> result = dealerGroomService.search(pagination, info);
            return this.getJsonMessage(CommonConst.SUCCESS, result);
        }
        catch (Throwable ex)
        {
            ex.fillInStackTrace();
            return this.getJsonMessage(CommonConst.FAIL, "异常");
        }
    }
    
    /**
     * 根据品牌获取经绡商申请的最后一条记录
     *
     * @param info
     * @return
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/listDealerGroom/selBrands")
    public Object getApplyState(DealerGroom info) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        info.setBrandId(brandId);
        if (StringUtils.isBlank(info.getBrandsId()) || StringUtils.isBlank(info.getDealerId())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        BrandInvite brandInvite = brandInviteService.getBrandInvite(info.getDealerId(), info.getBrandsId(), brandId);
        return this.getJsonMessage(CommonConst.SUCCESS, brandInvite);
    }
    
    /**
     * 申请中的经销商
     *
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/listDealerApply")
    public String search(Model model)
    {
        this.addDataDict(model);
        return "/brand/list_dealerApply";
    }
    
    /**
     * 我拒绝的经销商列表
     *
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/listRefuse")
    public String listRefuse(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandsInfos", brandsInfos);
        this.addDataDict(model);
        return "/brand/list_brandRefuse";
    }
    
    // 添加字典
    private void addDataDict(Model model)
    {
        model.addAttribute("shopSuffer", dataDictValueService.findByDictCode(DataDictConstant.DEALER_SHOP_SUFFER));
        model.addAttribute("shopNum", dataDictValueService.findByDictCode(DataDictConstant.DEALER_SHOP_NUM));
        model.addAttribute("employeeNum", dataDictValueService.findByDictCode(DataDictConstant.DEALER_EMPLOYEE_NUM));
        model.addAttribute("monthMoney", dataDictValueService.findByDictCode(DataDictConstant.DEALER_MONTH_MONEY));
        try
        {
            model.addAttribute("dealList", dealDicService.getDealDicsBy(""));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 同意申请
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/agree", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage Agree(BrandInvite brandInvite) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        brandInvite.setBrandId(brandId);
        if (StringUtils.isBlank(brandInvite.getBrandsId()) || StringUtils.isBlank(brandInvite.getDealerId())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        DealerJoin filter = new DealerJoin();
        filter.setBrandsId(brandInvite.getBrandsId());
        filter.setDealerId(brandInvite.getDealerId());
        List<DealerJoin> tempList = dealerJoinService.findDealerJoin(filter);
        if (tempList != null && tempList.size() > 0)
        {
            DealerJoin temp = tempList.get(0);
            if (null != temp
                    && (temp.getJoinState() == DealerConstant.DealerJoin.TERMINATED || temp.getJoinState() == DealerConstant.DealerJoin.STOP_COOPERATION || temp
                            .getJoinState() == DealerConstant.DealerJoin.STOP_COOPERATION_DEALER)) { return this.getJsonMessage(CommonConst.FAIL_BRAND_INVITED); }
        }
        try
        {
            DealerJoin dealerJoin = brandInviteService.insertAgreeApply(brandInvite);
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
            SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_BRAND_AGREE_APPLY);
            if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
            {
                String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月DD日 HH:mm").format(new Date()), brandInfo.getComName());
                userInfoService.sendSmsToDealerUser(brandInvite.getDealerId(), smsContent);
            }
            return this.getJsonMessage(CommonConst.SUCCESS, dealerJoin.getRefrenceId());
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 拒绝申请
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage reject(BrandInvite brandInvite) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        brandInvite.setBrandId(brandId);
        if (StringUtils.isBlank(brandInvite.getBrandsId()) || StringUtils.isBlank(brandInvite.getDealerId())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        if (StringUtils.isBlank(brandInvite.getAuditMark())) { return this.getJsonMessage(DealerConst.APPLY_INFO_ERROR); }
        try
        {
            brandInviteService.insertRejectApply(brandInvite);
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
            SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_BRAND_REJECT_APPLY);
            if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
            {
                String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月DD日 HH:mm").format(new Date()), brandInfo.getComName());
                userInfoService.sendSmsToDealerUser(brandInvite.getDealerId(), smsContent);
            }
            // 发送站内信息给终端商
            brandMessageService.sendDealerMessage(brandInvite.getBrandId(), brandInvite.getDealerId(), "拒绝申请", brandInvite.getAuditMark());
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 终止合作
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage stop(@RequestParam String id)
    {
        try
        {
            UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
            String brandId = userPrincipal.getRefrenceId();
            dealerApplyService.insertStopApply(id, brandId);
            return super.getJsonMessage(DealerConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(DealerConst.FAILURE);
        }
    }
    
    /**
     * 我浏览过的经销商index
     *
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brandVisited")
    public String listBrandVisited(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandsInfos", brandsInfos);
        this.addDataDict(model);
        return "/brand/list_brandVisited";
    }
    
    /**
     * 我浏览过的经销商data
     *
     * @param pagination
     * @param info
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brandVisited/data")
    public Object listBrandVisitedJson(Pagination pagination, BrandVisitedView info) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        Map<String, String> map = super.getAreaNoMap(info.getProvince(), info.getCity(), info.getCounty());
        if (!StringUtils.isBlank(map.get("areaNo")))
        {
            info.setAreaNo(Integer.valueOf(map.get("areaNo")));
            info.setLevel(Integer.valueOf(map.get("level")));
        }
        info.setBrandId(brandId);
        try
        {
            PaginateResult<Map<String, Object>> result = brandVisitedService.search(pagination, info);
            return this.getJsonMessage(CommonConst.SUCCESS, result);
        }
        catch (Throwable ex)
        {
            ex.fillInStackTrace();
            return super.getJsonMessage(DealerConst.FAILURE);
        }
    }
    
    /**
     * 经销商信息详情
     *
     * @param dealerId    经销商ID
     * @param model
     * @return String
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "info/{dealerId}")
    public String viewDealer(@PathVariable String dealerId, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        if (StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        DealerInfo info = dealerInfoService.selectByPrimaryKey(dealerId);
        List<Map<String, Object>> listDealerClass = dealerInfoService.findDealerClassById(dealerId);
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
        model.addAttribute("info", info);
        model.addAttribute("categorys", listDealerClass);
        model.addAttribute("dealerUserm", dealerUserm);
        Integer isExitsInt = brandViewContactService.isExist(brandId, dealerId);
        Boolean isExits = false;
        if (isExitsInt >= 1)
        {
            isExits = true;
        }
        BrandCount brandCount = brandCountService.selectByPrimaryKey(brandId);
        Integer viewCount = brandCountService.getViewDealerTotal(brandId);
        Integer viewDealerCount = viewCount.intValue() - brandCount.getViewDealerCount().intValue();
        viewDealerCount = (viewDealerCount < 0) ? 0 : viewDealerCount;
        List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(brandId);
        Boolean isCollected = this.brandFavoriteService.isCollected(brandId, dealerId);
        brandVisitedService.insert(brandId, dealerId);
        List<DealerImage> dealerImageList = dealerImageService.selectDealerImageByDealerId(dealerId);
        model.addAttribute("brandsInfos", brandsInfos);
        model.addAttribute("isExits", isExits);
        model.addAttribute("isCollected", isCollected);
        model.addAttribute("viewDealerCount", viewDealerCount);
        model.addAttribute("dealerImageList", dealerImageList);
        return "/brand/view_dealerInfo";
    }
    
    /**
     * 收藏的经销商页面
     *
     * @param model
     * @return String
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/favorite")
    public String favoriteList(Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandsInfos", brandsInfos);
        this.addDataDict(model);
        return "/brand/list_brandFavorite";
    }
    
    /**
     * 收藏的经销商列表
     * @param pagination
     * @param favorite
     * @param request
     * @return
     * @author 施建波
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/favorite/data")
    public Object listInviteJson(Pagination pagination, BrandFavoriteView favorite, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        favorite.setBrandId(brandId);
        try
        {
            PaginateResult<Map<String, Object>> result = brandFavoriteService.search(pagination, favorite);
            return this.getJsonMessage(CommonConst.SUCCESS, result);
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            return this.getJsonMessage(CommonConst.FAIL, "异常");
        }
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
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/viewContact", method = RequestMethod.POST)
    public Object viewContact(String dealerId, Boolean isShow) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        if (StringUtils.isBlank(dealerId) || null == isShow) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        try
        {
            DealerInfo dealerInfo = brandViewContactService.modifyViewContact(brandId, dealerId);
            return this.getJsonMessage(CommonConst.SUCCESS, dealerInfo);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 收藏经销商
     *
     * @throws BusinessException
     */
    @RequestMapping(value = "/collectDealer")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage collectDealer(String dealerId) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        this.brandFavoriteService.executeCollect(brandId, dealerId);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 取消收藏经销商
     */
    @RequestMapping(value = "/unCollectDealer")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage unCollectDealer(String dealerId) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        this.brandFavoriteService.executeUnCollect(brandId, dealerId);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 下载经销商资料
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/downloadDealer")
    public ModelAndView exportExcel(String dealerId) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        ModelAndView mav = new ModelAndView("dealerExcel");
        DealerInfo info = dealerInfoService.selectByPrimaryKey(dealerId);
        List<Map<String, Object>> listDealerClass = dealerInfoService.findDealerClassById(dealerId);
        UserInfo dealerUserm = this.userInfoService.selectByPrimaryKey(dealerId);
        Integer isExitsInt = brandViewContactService.isExist(brandId, dealerId);
        Boolean isExits = false;
        if (isExitsInt > 0)
        {
            isExits = true;
        }
        mav.addObject("info", info);
        mav.addObject("categorys", listDealerClass);
        mav.addObject("dealerUserm", dealerUserm);
        mav.addObject("isExits", isExits);
        return mav;
    }
}
