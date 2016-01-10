/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
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
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.brand.entity.BrandRecruit;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandRecruitModel;
import com.zttx.web.module.brand.service.BrandRecruitService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌招募书 控制器
 * <p>File：BrandRecruitController.java </p>
 * <p>Title: BrandRecruitController </p>
 * <p>Description:BrandRecruitController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandRecruit")
public class BrandRecruitController extends GenericBaseController
{
    @Autowired(required = true)
    private BrandRecruitService  brandRecruitService;
    
    @Autowired
    private BrandesInfoService   brandesInfoService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String recruit()
    {
        return "redirect:recruit/execute";
    }
    
    /**
     * 显示品牌商
     * @param request
     * @param model
     * @return
     * @author 郭小亮
     */
    @RequiresPermissions("brand:center")
    @RequestMapping("/execute")
    public String execute(String id, HttpServletRequest request, Model model)
    {
        String brandParentId = this.getCurrentLoginBrandId();
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED);
        List<BrandesInfo> brandes = brandesInfoService.listByBrandStates(brandParentId, brandStates);
        if (StringUtils.isNotBlank(id) && !brandes.isEmpty())
        {
            BrandRecruit recruit = brandRecruitService.findByBrandIdAndBrandesid(brandParentId, id);
            if (null != recruit)
            {
                model.addAttribute("recruit", recruit);
            }
            model.addAttribute("id", id);
        }
        model.addAttribute("dealBrand", dataDictValueService.findByDictCode(DataDictConstant.DEAL_BRAND));
        model.addAttribute("dealExper", dataDictValueService.findByDictCode(DataDictConstant.DEAL_EXPER));
        model.addAttribute("dealShop", dataDictValueService.findByDictCode(DataDictConstant.DEAL_SHOP));
        model.addAttribute("dealYear", dataDictValueService.findByDictCode(DataDictConstant.DEAL_YEAR));
        model.addAttribute("brandes", brandes);
        return "brand/edit_brandRecruit";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(HttpServletRequest request, BrandRecruitModel recruit) throws BusinessException
    {
        String brandParentId = this.getCurrentLoginBrandId();
        String userId = this.getCurrentLoginUserId();
        List<Map<String, String>> validatorList = validator(recruit);
        if (null != validatorList && !validatorList.isEmpty()) { return this.getJsonMessage(CommonConst.FORM_PARAMS_VALID_ERR, validatorList); }
        BrandRecruit brandRecruit = new BrandRecruit();
        BeanUtils.copyProperties(recruit, brandRecruit);
        brandRecruit.setBrandId(brandParentId);
        brandRecruit.setUserId(userId);
        brandRecruit.setRefrenceId(recruit.getBrandsId());
        try
        {
            brandRecruitService.saveOrUpdate(brandRecruit);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    private List<Map<String, String>> validator(BrandRecruitModel recruit)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "brandsId";
        if (StringUtils.isBlank(recruit.getBrandsId()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("品牌")));
        }
        name = "recruitTitle";
        String msgName = "招募书标题";
        Integer words = 128;
        if (StringUtils.isBlank(recruit.getRecruitTitle()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr(msgName)));
        }
        else
        {
            if (!ValidateUtils.isRange(recruit.getRecruitTitle(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr(msgName, words)));
            }
        }
        name = "dealBrand";
        if (!ValidateUtils.isInRange(recruit.getDealBrand().intValue(), 0, 2))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("经营品牌")));
        }
        name = "dealExper";
        if (!ValidateUtils.isInRange(recruit.getDealExper(), 0, 1))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("开店经验")));
        }
        name = "dealShop";
        if (!ValidateUtils.isInRange(recruit.getDealShop(), 0, 1))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("有无实体店")));
        }
        name = "dealYear";
        if (!ValidateUtils.isInRange(recruit.getDealYear(), 0, 4))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("经营年限")));
        }
        name = "recruitState";
        if (!ValidateUtils.isInRange(recruit.getRecruitState(), 0, 1))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("招募书状态")));
        }
        name = "recruitText";
        if (StringUtils.isBlank(recruit.getRecruitText()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("招募内容")));
        }
        return validatorList;
    }
}
