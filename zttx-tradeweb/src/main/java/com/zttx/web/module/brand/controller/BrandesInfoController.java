/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.brand.entity.BrandCert;
import com.zttx.web.module.brand.entity.BrandDeal;
import com.zttx.web.module.brand.entity.BrandLicening;
import com.zttx.web.module.brand.entity.BrandPhoto;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandesInfoModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandCertService;
import com.zttx.web.module.brand.service.BrandDealService;
import com.zttx.web.module.brand.service.BrandLiceningService;
import com.zttx.web.module.brand.service.BrandPhotoService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌商品牌信息 控制器
 * <p>File：BrandesInfoController.java </p>
 * <p>Title: BrandesInfoController </p>
 * <p>Description:BrandesInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandesInfo")
public class BrandesInfoController extends GenericController
{
    private final Logger         logger = Logger.getLogger(BrandesInfoController.class.getSimpleName());
    
    @Autowired(required = true)
    private BrandesInfoService   brandesInfoService;
    
    @Autowired
    private DealDicService       dealDicService;
    
    @Autowired
    private BrandDealService     brandDealService;
    
    @Autowired
    private BrandLiceningService brandLiceningService;
    
    @Autowired
    private BrandPhotoService    brandPhotoService;
    
    @Autowired
    private BrandCertService     brandCertService;
    
    /**
     * 获取JSON格式的类目数据
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
            logger.error(e);
        }
        return "[]";
    }
    
    /**
     * 新增品牌
     * @author 张昌苗
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brandes")
    public String brandes(Model model)
    {
        model.addAttribute("dealList", this.getDealDicJson());
        model.addAttribute("fileUrl", ZttxConst.FILEAPI_WEBURL);
        return "brand/add_brandesInfo";
    }
    
    /**
     * 提交新增的品牌
     * @author 张昌苗 @author 吴万杰 2014-4-30修改 增加修改功能
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brandes/submit", method = RequestMethod.POST)
    public String submitBrandes(BrandesInfoModel brandesInfoModel, HttpServletRequest request, Model model, String brandsId) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String userId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String gotoUrl = "redirect:/brand/brandesInfo/brands/list";
        model.addAttribute("brandsId", brandsId);
        BrandesInfo info = brandesInfoModel.parseBrandesInfo();
        if (StringUtils.isNotBlank(brandsId))
        {
            info = brandesInfoService.findByBrandIdAndBrandsId(brandId, brandsId);
            if (info == null)
            {
                addMessage(model, BrandConst.BRANDES_NOT_EXISTS.getCode(), BrandConst.BRANDES_NOT_EXISTS.getMessage());
                setInfoModel(brandesInfoModel, request, model);
                return brandes(model);
            }
            else if (BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED != info.getBrandState().shortValue()
                    && BrandConstant.BrandesInfoConst.BRAND_STATE_FAILURE != info.getBrandState().shortValue())
            {
                addMessage(model, BrandConst.BRANDES_JOIN_CANNOT_EDIT.getCode(), BrandConst.BRANDES_JOIN_CANNOT_EDIT.getMessage());
                setInfoModel(brandesInfoModel, request, model);
                return brandes(model);
            }
            if (BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED != info.getBrandState().shortValue())
            {
                brandesInfoModel.setBrandName(info.getBrandsName());
            }
            String t = request.getParameter("t");
            gotoUrl = (StringUtils.isNotBlank(t)) ? gotoUrl + "/" + t : gotoUrl;
        }
        List<Map<String, String>> validatorList = validator(brandesInfoModel, info, brandsId);
        if (null != validatorList && !validatorList.isEmpty())
        {
            this.addMessage(model, CommonConst.FORM_PARAMS_VALID_ERR.getCode(), JSON.toJSONString(validatorList));
            setInfoModel(brandesInfoModel, request, model);
            return this.brandes(model);
        }
        // 保存
        brandesInfoModel.setBrandId(brandId);
        brandesInfoModel.setCreateIp(IPUtil.getIpAddr(request));
        brandesInfoModel.setUserId(userId);
        try
        {
            info = brandesInfoService.save(brandesInfoModel, brandsId);
        }
        catch (BusinessException e)
        {
            this.addMessage(model, e.getErrorCode().getCode(), e.getErrorCode().getMessage());
            setInfoModel(brandesInfoModel, request, model);
            return this.brandes(model);
        }
        return gotoUrl;
    }
    
    private void setInfoModel(BrandesInfoModel infoModel, HttpServletRequest request, Model model)
    {
        BrandesInfo info = infoModel.parseBrandesInfo();
        model.addAttribute("info", info);
        model.addAttribute("t", request.getParameter("t"));
        model.addAttribute("logoImgName", infoModel.getLogoImgName());
        if (!ArrayUtils.isEmpty(infoModel.getCertImgPaths()))
        {
            List<BrandCert> certList = Lists.newArrayList();
            for (int i = 0; i < infoModel.getCertImgPaths().length; i++)
            {
                BrandCert brandCert = new BrandCert();
                brandCert.setFileName(infoModel.getCertImgNames()[i]);
                brandCert.setImageName(infoModel.getCertImgPaths()[i]);
                certList.add(brandCert);
            }
            model.addAttribute("certList", certList);
        }
        if (!ArrayUtils.isEmpty(infoModel.getPhotoImgPaths()))
        {
            List<BrandPhoto> photoList = Lists.newArrayList();
            for (int i = 0; i < infoModel.getPhotoImgPaths().length; i++)
            {
                BrandPhoto brandPhoto = new BrandPhoto();
                brandPhoto.setPhotoName(infoModel.getPhotoImgNames()[i]);
                brandPhoto.setImageName(infoModel.getPhotoImgPaths()[i]);
                photoList.add(brandPhoto);
            }
            model.addAttribute("photoList", photoList);
        }
        if (!ArrayUtils.isEmpty(infoModel.getDeals()))
        {
            List<BrandDeal> brandDicList = Lists.newArrayList();
            for (int i = 0; i < infoModel.getDeals().length; i++)
            {
                BrandDeal brandDeal = new BrandDeal();
                DealDic dealDic = new DealDic();
                brandDeal.setDealNo(Integer.parseInt(infoModel.getDeals()[i]));
                dealDic.setDealName(infoModel.getDealNames()[i]);
                brandDeal.setDealDic(dealDic);
                brandDicList.add(brandDeal);
            }
            model.addAttribute("brandDicList", brandDicList);
        }
        if (!ArrayUtils.isEmpty(infoModel.getLiceningImgPaths()))
        {
            List<BrandLicening> liceningList = Lists.newArrayList();
            for (int i = 0; i < infoModel.getLiceningImgPaths().length; i++)
            {
                BrandLicening brandLicening = new BrandLicening();
                brandLicening.setFileName(infoModel.getLiceningImgNames()[i]);
                brandLicening.setImageName(infoModel.getLiceningImgPaths()[i]);
                liceningList.add(brandLicening);
            }
            model.addAttribute("liceningList", liceningList);
        }
    }
    
    private List<Map<String, String>> validator(BrandesInfoModel brandesInfoModel, BrandesInfo info, String brandsId)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "brandName";
        String msgName = "品牌名称";
        Integer words = 32;
        if (StringUtils.isBlank(brandesInfoModel.getBrandName()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr(msgName)));
        }
        else
        {
            if (!ValidateUtils.isRange(brandesInfoModel.getBrandName(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr(msgName, words)));
            }
        }
        if (StringUtils.isBlank(brandsId) || BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED == info.getBrandState().shortValue())
        {
            if (brandesInfoService.isExistBrandName(brandesInfoModel.getBrandName(), brandsId))
            {
                validatorList.add(this.getErrMsgMap(name, BrandConst.BRANDES_NAME_EXISTS.getMessage()));
            }
        }
        name = "foreign_brand";
        if (!ValidateUtils.isInRange(brandesInfoModel.getBrandType(), 1, 2))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("品牌类型")));
        }
        name = "certError";
        if (ArrayUtils.isEmpty(brandesInfoModel.getCertImgPaths()))
        {
            validatorList.add(this.getErrMsgMap(name, "至少需要一张品牌证书"));
        }
        if (BrandConstant.BrandesInfoConst.BRAND_TYPE_FOREIGN == brandesInfoModel.getBrandType().shortValue())
        {
            name = "liceningError";
            if (ArrayUtils.isEmpty(brandesInfoModel.getLiceningImgPaths()))
            {
                validatorList.add(this.getErrMsgMap(name, "至少需要一张品牌授权书"));
            }
        }
        name = "logoError";
        if (StringUtils.isBlank(brandesInfoModel.getLogoImgPath()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("品牌LOGO")));
        }
        name = "photoError";
        if (ArrayUtils.isEmpty(brandesInfoModel.getPhotoImgPaths()))
        {
            validatorList.add(this.getErrMsgMap(name, "至少需要一张企业产品形象图"));
        }
        name = "holdError";
        if (!ValidateUtils.isInRange(brandesInfoModel.getBrandType(), 1, 2))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("品牌持有人")));
        }
        name = "dicError";
        if (ArrayUtils.isEmpty(brandesInfoModel.getDeals()))
        {
            validatorList.add(this.getErrMsgMap(name, "至少需要选择一条品牌主营类目"));
        }
        name = "subMarkError";
        words = 1024;
        if (StringUtils.isNotBlank(brandesInfoModel.getBrandSubMark()))
        {
            if (!ValidateUtils.isRange(brandesInfoModel.getBrandSubMark(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr("品牌文字介绍", words)));
            }
        }
        name = "myEditor1";
        if (StringUtils.isBlank(brandesInfoModel.getBrandMark()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("品牌简介")));
        }
        return validatorList;
    }
    
    /**
     * 品牌商拥有的某状态品牌
     * @param request
     * @param brandState
     * @return
     * @author 吴万杰
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brands/list/{brandState}")
    public ModelAndView listMyBrandes(HttpServletRequest request, @PathVariable(value = "brandState") Short brandState) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("brand/list_brandesInfo");
        mav.addObject("fileUrl", ZttxConst.FILEAPI_WEBURL);
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        BrandesInfo filter = new BrandesInfo();
        filter.setBrandId(brandId);
        filter.setBrandState(brandState);
        List<BrandsInfoModel> list = brandesInfoService.list(brandId, brandState);
        if (CollectionUtils.isNotEmpty(list))
        {
            StringBuilder sb = new StringBuilder();
            for (BrandsInfoModel info : list)
            {
                List<Map<String, Object>> dealList = brandDealService.getBrandDealNameList(info.getRefrenceId());
                if (CollectionUtils.isNotEmpty(dealList))
                {
                    sb.setLength(0);
                    for (Map<String, Object> item : dealList)
                    {
                        sb.append(item.get("dealName")).append(" ");
                    }
                    info.setBrandDealAllName(sb.toString());
                }
                info.setBrandDealList(dealList);
            }
        }
        mav.addObject("brandList", list);
        mav.addObject("brandState", brandState);
        return mav;
    }
    
    /**
     * 品牌商拥有的品牌
     * @param request
     * @return
     * @author 吴万杰
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brands/list")
    public ModelAndView listMyBrandes(HttpServletRequest request) throws BusinessException
    {
        return listMyBrandes(request, null);
    }
    
    /**
     * 修改品牌资料
     * @param brandsId
     * @param model
     * @return
     * @author 吴万杰
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/update/{brandsId}")
    public String editBrandes(HttpServletRequest request, @PathVariable String brandsId, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        BrandesInfo info = brandesInfoService.findByBrandIdAndBrandsId(brandId, brandsId);
        if (null != info
                && (BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED == info.getBrandState().shortValue() || BrandConstant.BrandesInfoConst.BRAND_STATE_FAILURE == info
                        .getBrandState().shortValue()))
        {
            model.addAttribute("info", info);
            List<BrandCert> certList = brandCertService.findByBrandsId(info.getBrandId(), info.getRefrenceId(), false);
            model.addAttribute("certList", certList);
            List<BrandDeal> brandDicList = brandDealService.findByBrandsId(info.getBrandId(), info.getRefrenceId(), false);
            model.addAttribute("brandDicList", brandDicList);
            List<BrandPhoto> photoList = brandPhotoService.findByBrandIdAndBrandsId(info.getBrandId(), info.getRefrenceId(), false);
            model.addAttribute("photoList", photoList);
            List<BrandLicening> liceningList = brandLiceningService.findByBrandsId(brandId, brandsId, false);
            model.addAttribute("liceningList", liceningList);
            model.addAttribute("t", request.getParameter("t"));
        }
        return brandes(model);
    }
    
    /**
     * 用户授权
     * @author 章旭楠
     * @param request
     * @param brandsId
     * @param userAuthCodes
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/userAuth")
    public JsonMessage userAuth(HttpServletRequest request, String brandsId, String[] userAuthCodes) throws BusinessException
    {
        try
        {
            brandesInfoService.saveUserAuth(brandsId, userAuthCodes);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return getJsonMessage(CommonConst.FAIL);
        }
        return getJsonMessage(CommonConst.SUCCESS);
    }
}
