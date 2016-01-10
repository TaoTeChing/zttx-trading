/*
 * @(#)BrandJoinInfoController.java 2014-4-2 下午2:16:23
 * Copyright 2014 江枫林, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.brand.entity.BrandNetwork;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandLevelService;
import com.zttx.web.module.brand.service.BrandNetworkService;
import com.zttx.web.module.brand.service.BrandVisitedService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.module.dealer.service.DealerOrdercService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * <p>File：BrandJoinInfoController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-2 下午2:16:23</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.BRAND)
public class BrandJoinInfoController extends GenericController
{
    @Autowired
    private DealerJoinService        dealerJoinService;
    
    @Autowired
    private UserInfoService          dealerUsermService;
    
    @Autowired
    private DealerOrdercService      dealerOrdercService;
    
    @Autowired
    private BrandLevelService        brandLevelService;
    
    @Autowired
    private DealerInfoService        dealerInfoService;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired
    private DealerOrderService       dealerOrderService;
    
    @Autowired
    private BrandNetworkService      brandNetworkService;
    
    @Autowired
    private BrandVisitedService      brandVisitedService;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    /**
     * 基本资料
     * @param joinId 加盟信息ID
     * @author 江枫林
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/join/dealerInfo/{joinId}")
    public String joinDealerInfo(@PathVariable("joinId") String joinId, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = this.getDealerJoin(joinId, brandId, false);
        UserInfo dealerUserm = this.dealerUsermService.selectByPrimaryKey(dealerJoin.getDealerId());
        DealerInfo dealerInfo = this.dealerInfoService.selectByPrimaryKey(dealerJoin.getDealerId());
        BrandesInfo brandesInfo = this.brandesInfoService.selectByPrimaryKey(dealerJoin.getBrandsId());
        List<Map<String, Object>> dealerClassList = this.dealerInfoService.findDealerClassById(dealerJoin.getDealerId());
        BrandNetwork brandNetwork = this.getBrandNetwork(dealerJoin, true);
        brandVisitedService.insert(dealerJoin.getBrandId(), dealerJoin.getDealerId());
        model.addAttribute("dealerInfo", dealerInfo);
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("dealerClassList", dealerClassList);
        model.addAttribute("dealerJoin", dealerJoin);
        model.addAttribute("dealerUserm", dealerUserm);
        model.addAttribute("showFlag", brandNetwork != null);
        return "brand/show_joinedDealerInfo";
    }
    
    /**
     * 等级授权
     * @param joinId 加盟信息ID
     * @author 江枫林
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/join/dealerLevel/{joinId}")
    public String joinDealerLevel(@PathVariable("joinId") String joinId, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = this.getDealerJoin(joinId, brandId, false);
        UserInfo dealerUserm = this.dealerUsermService.selectByPrimaryKey(dealerJoin.getDealerId());
        BrandesInfo brandesInfo = this.brandesInfoService.selectByPrimaryKey(dealerJoin.getBrandsId());
        // 查询等级
        BrandLevel brandLevel = null;
        if (!StringUtils.isBlank(dealerJoin.getLevelId()))
        {
            brandLevel = this.brandLevelService.selectByPrimaryKey(dealerJoin.getLevelId());
        }
        // 获取产品统计数
        this.hasCount(dealerJoin, model);
        model.addAttribute("dealerJoin", dealerJoin);
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("dealerUserm", dealerUserm);
        model.addAttribute("brandLevel", brandLevel);
        return "brand/show_joinedDealerLevel";
    }
    
    /**
     * 进货明细
     * @param joinId 加盟信息ID
     * @author 江枫林
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/join/dealerDeal/{joinId}")
    public String joinDealerDeal(@PathVariable("joinId") String joinId, HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = this.getDealerJoin(joinId, brandId, false);
        UserInfo dealerUserm = this.dealerUsermService.selectByPrimaryKey(dealerJoin.getDealerId());
        BrandesInfo brandesInfo = this.brandesInfoService.selectByPrimaryKey(dealerJoin.getBrandsId());
        model.addAttribute("dealerJoin", dealerJoin);
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("dealerUserm", dealerUserm);
        return "brand/show_joinedDealerDeal";
    }
    
    /**
     * 获取已进款、常进款和未进款的产品信息
     * @param joinId 加盟信息ID
     * @param type 0：已进款，1：常进款， 2：未进款
     * @author 江枫林
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/join/proCateList/{joinId}")
    @ResponseBody
    public Object proCateList(@PathVariable("joinId") String joinId, Integer type, Pagination page) throws BusinessException
    {
        type = (null == type ? 0 : type);
        if (type < 0 || type > 2) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR, "type超出范围"); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = this.getDealerJoin(joinId, brandId, false);
        List<String> proIdList = this.getProIdList(dealerJoin, type);
        PaginateResult<ProductBaseInfo> paginateResult = new PaginateResult<ProductBaseInfo>(page, null);
        if (null != proIdList && !proIdList.isEmpty())
        {
            ProductBaseInfo baseInfo = new ProductBaseInfo();
            baseInfo.setPage(page);
            paginateResult = productInfoDubboConsumer.findByProductIdsAndProduct(proIdList, baseInfo);
        }
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 获取进货明细数据
     * @param joinId 加盟信息ID
     * @author 江枫林
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/join/proDetList/{joinId}")
    @ResponseBody
    public Object proDetList(@PathVariable("joinId") String joinId, Pagination page) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = this.getDealerJoin(joinId, brandId, false);
        PaginateResult<DealerOrderModel> paginateResult = this.dealerOrderService.getOrdList(dealerJoin.getDealerId(), dealerJoin.getBrandId(), dealerJoin.getBrandsId(),
                page);
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 添加经销网络
     * @param joinId 加盟信息ID
     * @author 江枫林
     * @throws FileNotFoundException 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/join/addNetwork/{joinId}")
    @ResponseBody
    public JsonMessage addNetwork(@PathVariable("joinId") String joinId, HttpServletRequest request) throws FileNotFoundException, BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = this.getDealerJoin(joinId, brandId, true);
        if (DealerConstant.DealerJoin.COOPERATION != dealerJoin.getJoinState()) { throw new BusinessException(CommonConst.STATE_NOT_COOPERATION); }
        BrandNetwork brandNetwork = this.getBrandNetwork(dealerJoin, null);
        if (null == brandNetwork)
        {
            try
            {
                brandNetwork = new BrandNetwork();
                brandNetwork.setBrandId(dealerJoin.getBrandId());
                brandNetwork.setBrandsId(dealerJoin.getBrandsId());
                brandNetwork.setIdAry(new String[]{dealerJoin.getRefrenceId()});
                brandNetworkService.insertBrandNetwork(request, brandNetwork);
            }
            catch (BusinessException e)
            {
                return this.getJsonMessage(e.getErrorCode());
            }
        }
        else
        {
            if (!brandNetwork.getShowFlag())
            {
                this.brandNetworkService.updateShowFlag(brandNetwork.getRefrenceId(), true);
            }
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 删除经销网络
     * @param joinId 加盟信息ID
     * @author 江枫林
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/join/delNetwork/{joinId}")
    @ResponseBody
    public Object delNetwork(@PathVariable("joinId") String joinId, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = this.getDealerJoin(joinId, brandId, true);
        BrandNetwork brandNetwork = this.getBrandNetwork(dealerJoin, true);
        if (null != brandNetwork)
        {
            this.brandNetworkService.updateShowFlag(brandNetwork.getRefrenceId(), false);
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 统计进货款式信息
     * @author 江枫林
     */
    private void hasCount(DealerJoin dealerJoin, Model model)
    {
        Long proCate = this.dealerOrdercService.countProCate(dealerJoin.getDealerId(), dealerJoin.getBrandId(), dealerJoin.getBrandsId());
        Long freProCate = this.dealerOrdercService.countFreProCate(dealerJoin.getDealerId(), dealerJoin.getBrandId(), dealerJoin.getBrandsId());
        // Long unProCate = this.dealerOrdercService.countUnProCate(dealerJoin.getDealerId(), dealerJoin.getBrandId(), dealerJoin.getBrandsId(), list);
        model.addAttribute("proCate", proCate);
        model.addAttribute("freProCate", freProCate);
        // model.addAttribute("unProCate", unProCate);
    }
    
    /**
     * 获取已进款、常进款和未进款的产品ID
     * @author 江枫林
     */
    private List<String> getProIdList(DealerJoin dealerJoin, Integer type)
    {
        if (null == dealerJoin) { return null; }
        List<String> proIdList = null;
        if (0 == type)
        {
            proIdList = this.dealerOrdercService.getProCateList(dealerJoin.getDealerId(), dealerJoin.getBrandId(), dealerJoin.getBrandsId());
        }
        else if (1 == type)
        {
            proIdList = this.dealerOrdercService.getFreProCateList(dealerJoin.getDealerId(), dealerJoin.getBrandId(), dealerJoin.getBrandsId());
        }
        else if (2 == type)
        {
            // List<String> allProIdList = this.productLineService.findAuthorizedProductsId(dealerJoin.getDealerId(), dealerJoin.getBrandsId(), dealerJoin.getBrandId());
            // proIdList = this.dealerOrdercService.getUnProCateList(dealerJoin.getDealerId(), dealerJoin.getBrandId(), dealerJoin.getBrandsId(), allProIdList);
        }
        return proIdList;
    }
    
    /**
     * 获取加盟信息
     * @param joinId 加盟ID
     * @author 江枫林
     * @throws BusinessException 
     */
    private DealerJoin getDealerJoin(String joinId, String brandId, boolean checkCooperation) throws BusinessException
    {
        if (StringUtils.isEmpty(joinId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        DealerJoin dealerJoin = this.dealerJoinService.findByRefrenceIdAndBrandId(joinId, brandId);
        if (null == dealerJoin) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        if (checkCooperation && DealerConstant.DealerJoin.COOPERATION != dealerJoin.getJoinState()) { throw new BusinessException(CommonConst.STATE_NOT_COOPERATION); }
        return dealerJoin;
    }
    
    /**
     * 获取经销网络信息
     * @param dealerJoin 加盟信息
     * @param showFlag 是否在展厅中展示
     * @author 江枫林
     */
    private BrandNetwork getBrandNetwork(DealerJoin dealerJoin, Boolean showFlag)
    {
        return this.brandNetworkService.getBrandNetwork(dealerJoin.getBrandId(), dealerJoin.getBrandsId(), dealerJoin.getDealerId(), showFlag);
    }
}
