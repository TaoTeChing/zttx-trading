/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.brand.entity.BrandNetwork;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandLevelService;
import com.zttx.web.module.brand.service.BrandNetimgService;
import com.zttx.web.module.brand.service.BrandNetworkService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.entity.Regional;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌经销网络 控制器
 * <p>File：BrandNetworkController.java </p>
 * <p>Title: BrandNetworkController </p>
 * <p>Description:BrandNetworkController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandNetwork")
public class BrandNetworkController extends GenericBaseController
{
    @Autowired
    private BrandNetworkService brandNetworkService;
    
    @Autowired
    private BrandesInfoService  brandesInfoService;
    
    @Autowired
    private BrandLevelService   brandLevelService;
    
    @Autowired
    private RegionalService     regionalService;
    
    @Autowired
    private BrandNetimgService  brandNetimgService;
    
    /**
     * 进入新添加经销网络
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/execute")
    public String execute(String id, HttpServletRequest request, Model model)
    {
        String brandId = this.getCurrentLoginBrandId();
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED);
        List<BrandesInfo> brandes = brandesInfoService.listByBrandStates(brandId, brandStates);
        model.addAttribute("brandes", brandes);
        model.addAttribute("id", id);
        return "brand/add_brandnetwork";
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/execute/{refrenceId}")
    public String update_execute(@PathVariable String refrenceId, HttpServletRequest request, Model model)
    {
        String id = "";
        if (StringUtils.isNotBlank(refrenceId))
        {
            BrandNetwork brandNetwork = brandNetworkService.selectByPrimaryKey(refrenceId);
            if (null != brandNetwork)
            {
                model.addAttribute("brandNetwork", brandNetwork);
                List<Map<String, Object>> netimgList = brandNetimgService.getBrandNetimgList(refrenceId);
                if (CollectionUtils.isNotEmpty(netimgList))
                {
                    for (Map<String, Object> item : netimgList)
                    {
                        item.put("changeImagePath", item.get("imageName"));
                    }
                }
                id = brandNetwork.getBrandsId();
                model.addAttribute("brandAlbums", netimgList);
            }
        }
        return execute(id, request, model);
    }
    
    /**
     * 保存新添加经销网络
     * @param request
     * @param network
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(BrandNetwork network, HttpServletRequest request)
    {
        String brandId = this.getCurrentLoginBrandId();
        network.setBrandId(brandId);
        network.setWholePercent(80);
        List<Map<String, String>> validatorList = validator(network);
        if (null != validatorList && !validatorList.isEmpty()) { return this.getJsonMessage(CommonConst.FORM_PARAMS_VALID_ERR, validatorList); }
        try
        {
            brandNetworkService.save(network, request);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    // 参数检验
    private List<Map<String, String>> validator(BrandNetwork network)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "brandsId";
        if (StringUtils.isBlank(network.getBrandsId()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("品牌")));
        }
        name = "dealerName";
        Integer min = 2;
        Integer max = 100;
        if (StringUtils.isBlank(network.getDealerName()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("经销商名")));
        }
        else
        {
            if (!ValidateUtils.isRange(network.getDealerName(), min, max, true))
            {
                validatorList.add(this.getErrMsgMap(name, "请输入2-100个字符的经销商名称"));
            }
        }
        name = "userName";
        max = 32;
        if (StringUtils.isBlank(network.getUserName()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("联系人")));
        }
        else
        {
            if (!ValidateUtils.isRange(network.getDealerName(), min, max, true))
            {
                validatorList.add(this.getErrMsgMap(name, "请输入2-32个字符的联系人"));
            }
        }
        if (StringUtils.isBlank(network.getTelphone()))
        {
            if (StringUtils.isBlank(network.getMobile()))
            {
                validatorList.add(this.getErrMsgMap("mobile", "手机或电话至少填写一个"));
            }
        }
        else
        {
            network.setWholePercent(100);
            if (!ValidateUtils.isTelFormat(network.getTelphone(), true, 20))
            {
                validatorList.add(this.getErrMsgMap("phone", "请输入正确的电话号码"));
            }
        }
        if (StringUtils.isNotBlank(network.getMobile()))
        {
            if (!ValidateUtils.isMobileFormat(network.getMobile(), true, 11))
            {
                validatorList.add(this.getErrMsgMap("mobile", "请输入正确的手机号"));
            }
        }
        name = "address";
        max = 128;
        if (StringUtils.isBlank(network.getProvince()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("省份")));
        }
        else
        {
            String areaNo = getAreaNo(network.getProvince(), network.getCity(), network.getCounty());
            setRegionalName(network);
            network.setAreaNo(Integer.parseInt(areaNo));
            if (StringUtils.isBlank(network.getAddress()))
            {
                validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("公司地址")));
            }
            else
            {
                if (!ValidateUtils.isRange(network.getAddress(), true, max))
                {
                    validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr("公司地址", max)));
                }
            }
        }
        name = "upError";
        if (ArrayUtils.isEmpty(network.getImages()))
        {
            validatorList.add(this.getErrMsgMap(name, "至少需要一张图片上传"));
        }
        else
        {
            if (!ValidateUtils.isInRange(network.getImages().length, 1, 6))
            {
                validatorList.add(this.getErrMsgMap(name, "超出上传图片的张数"));
            }
        }
        return validatorList;
    }
    
    @Override
    public String getCurrentLoginDealerId()
    {
        return super.getCurrentLoginDealerId();
    }
    
    private void setRegionalName(BrandNetwork network)
    {
        String code = network.getProvince();
        if (StringUtils.isNotBlank(code))
        {
            Regional regional = regionalService.searchByCode(Integer.parseInt(code));
            network.setProvinceName(regional.getAreaName());
        }
        code = network.getCity();
        if (StringUtils.isNotBlank(code))
        {
            Regional regional = regionalService.searchByCode(Integer.parseInt(code));
            network.setCityName(regional.getAreaName());
        }
        code = network.getCounty();
        if (StringUtils.isNotBlank(code))
        {
            Regional regional = regionalService.searchByCode(Integer.parseInt(code));
            network.setAreaName(regional.getAreaName());
        }
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/list/data")
    @ResponseBody
    public JsonMessage networkData(Pagination page, BrandNetwork brandNetwork, HttpServletRequest request)
    {
        String brandId = this.getCurrentLoginBrandId();
        page.setPageSize(8);
        brandNetwork.setBrandId(brandId);
        String areaNo = this.getAreaNo(brandNetwork.getProvince(), brandNetwork.getCity(), brandNetwork.getCounty());
        if (StringUtils.isNotBlank(areaNo))
        {
            brandNetwork.setAreaNo(Integer.parseInt(areaNo));
        }
        brandNetwork.setPage(page);
        List<BrandNetwork> resultList = brandNetworkService.findList(brandNetwork);
        PaginateResult<BrandNetwork> result = new PaginateResult<BrandNetwork>(page, resultList);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 修改是否在展厅显示
     * @param brandNetwork
     * @param request
     * @return
     * @author 施建波
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/list/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage updateShowFlag(BrandNetwork brandNetwork, HttpServletRequest request)
    {
        String brandId = this.getCurrentLoginBrandId();
        brandNetwork.setBrandId(brandId);
        if (StringUtils.isBlank(brandNetwork.getRefrenceId()) && brandNetwork.getShowFlag() != null) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        brandNetworkService.updateShowFlag(brandNetwork.getRefrenceId(), brandNetwork.getShowFlag());
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 修改网络经绡商删除标记
     * @param brandNetwork
     * @param request
     * @return
     * @author 施建波
     */
    @RequestMapping(value = "/list/delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage updateDelState(BrandNetwork brandNetwork, HttpServletRequest request)
    {
        String brandId = this.getCurrentLoginBrandId();
        brandNetwork.setBrandId(brandId);
        if (StringUtils.isBlank(brandNetwork.getRefrenceId())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        brandNetworkService.delete(brandNetwork.getRefrenceId());
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 在展厅中显示
     * @param request
     * @param showFlag
     * @param refrenceId
     * @return
     */
    @RequestMapping(value = "/showFlag")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage showFlag(HttpServletRequest request, Boolean showFlag, String refrenceId)
    {
        String brandId = this.getCurrentLoginBrandId();
        if (showFlag != null && StringUtils.isNotBlank(refrenceId) && StringUtils.isNotBlank(brandId))
        {
            brandNetworkService.updateShowFlag(refrenceId, showFlag);
        }
        return null;
    }
    
    /**
     * 进入从现有经销商选取页面
     * @param request
     * @param page
     * @param dealerJoin
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/select")
    public String select(String id, HttpServletRequest request, Model model)
    {
        String brandId = this.getCurrentLoginBrandId();
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED);
        List<BrandesInfo> brandes = brandesInfoService.listByBrandStates(brandId, brandStates);
        model.addAttribute("brandes", brandes);
        model.addAttribute("id", id);
        if (StringUtils.isNotBlank(id))
        {
            List<BrandLevel> brandLevels = brandLevelService.getBrandLevelsBy(brandId, id);
            model.addAttribute("brandLevels", brandLevels);
        }
        return "/brand/select_brandnetwork";
    }
    
    @RequestMapping(value = "/select/data")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage networkDealerData(Pagination page, BrandNetwork brandNetwork, HttpServletRequest request)
    {
        String brandId = this.getCurrentLoginBrandId();
        page.setPageSize(8);
        brandNetwork.setBrandId(brandId);
        String areaNo = this.getAreaNo(brandNetwork.getProvince(), brandNetwork.getCity(), brandNetwork.getCounty());
        if (StringUtils.isNotBlank(areaNo))
        {
            brandNetwork.setAreaNo(Integer.parseInt(areaNo));
        }
        PaginateResult<Map<String, Object>> result = brandNetworkService.getNetwortNotDealerList(page, brandNetwork);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 添加到经销网络
     * @param request
     * @param refId
     * @return
     */
    @RequestMapping(value = "/select/insert", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage insert(BrandNetwork brandNetwork, HttpServletRequest request)
    {
        if (ArrayUtils.isEmpty(brandNetwork.getIdAry())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR.getCode(), "请选择一个经绡商"); }
        String brandId = this.getCurrentLoginBrandId();
        brandNetwork.setBrandId(brandId);
        brandNetworkService.insert(brandNetwork);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/list")
    public String list(String id, HttpServletRequest request, Model model)
    {
        String brandId = this.getCurrentLoginBrandId();
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED);
        List<BrandesInfo> brandes = brandesInfoService.listByBrandStates(brandId, brandStates);
        model.addAttribute("brandes", brandes);
        model.addAttribute("id", id);
        if (StringUtils.isNotBlank(id))
        {
            List<BrandLevel> brandLevels = brandLevelService.getBrandLevelsBy(brandId, id);
            model.addAttribute("brandLevels", brandLevels);
        }
        return "brand/list_brandnetwork";
    }
}
