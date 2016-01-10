/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.model.BrandFreightQueryModel;
import com.zttx.web.module.brand.model.BrandesAuthUserModel;
import com.zttx.web.module.brand.service.BrandFreightTemplateService;
import com.zttx.web.module.brand.service.BrandesAuthUserService;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.common.service.ProductViewLogService;
import com.zttx.web.module.dealer.entity.DealerShoper;
import com.zttx.web.module.dealer.entity.DealerShopers;
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.module.dealer.model.ShoperModel;
import com.zttx.web.module.dealer.service.DealerGrantService;
import com.zttx.web.module.dealer.service.DealerOrdercService;
import com.zttx.web.module.dealer.service.DealerShoperService;
import com.zttx.web.module.dealer.service.DealerShopersService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.ListUtils;

/**
 * 经销商购物车 控制器
 * <p>File：DealerShoperController.java </p>
 * <p>Title: DealerShoperController </p>
 * <p>Description:DealerShoperController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerShoper")
public class DealerShoperController extends GenericController
{
    private final static Logger         logger = LoggerFactory.getLogger(DealerShoperController.class);
    
    @Autowired(required = true)
    private DealerShoperService         dealerShoperService;
    
    @Autowired
    private DataDictValueService        dataDictValueService;
    
    @Autowired
    private DealerOrdercService         dealerOrdercService;
    
    @Autowired
    private ProductViewLogService       productViewLogService;
    
    @Autowired
    private DealerGrantService          dealerGrantService;
    
    @Autowired
    private DealerShopersService        dealerShopersService;
    
    @Autowired
    private BrandFreightTemplateService brandFreightTemplateService;
    
    @Autowired
    private BrandesAuthUserService      brandesAuthUserService;
    
    /* ========================================= 改版加车 [@author易永耀] begin================================================ */
    /**
     * 加车选择sku，支持扣点
     * @param productId 产品id
     * @return sku列表
     * @throws BusinessException
     * @update 2015/11/16 by Zhangxn
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/listSkuIds")
    public JsonMessage getProductskuIds(String productId) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        BrandesAuthUserModel brandesAuthUserModel;
        try
        {
            brandesAuthUserModel = brandesAuthUserService.getAuthPrice(dealerId, productId, BrandesAuthUserModel.INCLUDE_SKU);
        }
        catch (BusinessException e)
        {
            logger.error(e.getLocalizedMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS, brandesAuthUserModel);
    }
    
    /**
     * 加车
     * @param productId
     * @param productType
     * @param skuIds
     * @param purchaseNum
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/addCart")
    public JsonMessage addCartAction(String productId, Short productType, @RequestParam(value = "skuIds") List<String> skuIds,
            @RequestParam(value = "purchaseNum") List<Integer> purchaseNum) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        try
        {
            dealerShoperService.saveDealerShoper(productId, productType, skuIds, purchaseNum, dealerId);
        }
        catch (BusinessException e)
        {
            logger.error(e.getLocalizedMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 显示更多
     * @param productId 产品id
     * @param productType 购物车类型
     * @param shoperId 购物车id
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/showMore")
    public JsonMessage showMore(String productId, Short productType, String shoperId)
    {
        List<DealerShopers> dealerShopersList;
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            // 虚拟的dealerShopers,所有信息都是来自商品中心
            dealerShopersList = dealerShoperService.getMoreDealerShopers(productId, productType, dealerId, shoperId);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS, dealerShopersList);
    }
    
    /* ========================================= 改版加车 end ================================================ */
    /* ========================================= 加车动作 [@author易永耀] begin================================================ */
    /**
     * 经销商 加车前 产品权限检测  (如平台费用)
     *
     * @return
     * @throws BusinessException
     * @author 易永耀
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/checkForCart", method = RequestMethod.POST)
    public JsonMessage authCheck() throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        // TODO: 判断付费用户 这部分功能 暂时取消(曾) isAuthorized() 为强制true
        return this.getJsonMessage(CommonConst.SUCCESS, true);
    }
    
    /**
     * 经销商加车
     *
     * @param productsId   产品id集合
     * @param productsType 产品类型：0普通加车（直供价） 1 授信加车（授信价） 2拿样加车（拿样价）
     * @return
     * @throws BusinessException
     * @author 易永耀  -废除  只有快速下单还在用
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/addCart2", method = RequestMethod.POST)
    public String addCartAction(@RequestParam(value = "productsId") List<String> productsId, @RequestParam(value = "productsType") List<Integer> productsType)
            throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerShoperService.batchSaveShopers(productsId, productsType, dealerId);
        return "redirect:/dealer/dealerShoper/cart";
    }
    
    /**
     * 产品直接加入购物车
     * @param productId 产品id
     * @param productType 类型 授信
     * @param skuIds sku信息
     * @param amounts 数量
     * @return
     * @throws BusinessException
     * @author 章旭楠    -废除
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/productAddCar", method = RequestMethod.POST)
    public JsonMessage productAddCar(String productId, Short productType, @RequestParam(value = "skuIds") List<String> skuIds,
            @RequestParam(value = "amounts") List<Integer> amounts) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerShoperService.saveDealerShoper(productId, productType, skuIds, amounts, dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 产品详情页 直接结算
     * @param model
     * @param productId
     * @param productType
     * @param skuIds
     * @param amounts
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/productToBalance", method = RequestMethod.POST)
    public String productToBalance(Model model, String productId, Short productType, @RequestParam(value = "skuIds") List<String> skuIds,
            @RequestParam(value = "purchaseNum") List<Integer> amounts)
    {
        List<ShoperModel> shopersModelList = Lists.newArrayList();
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            DealerShoper dealerShoper = dealerShoperService.productToBalance(productId, productType, skuIds, amounts, dealerId);
            Map<String, ShoperModel> shoperModelMap = transDealerShopperToShoperModelList(Lists.newArrayList(dealerShoper));
            for (ShoperModel shoperModel : shoperModelMap.values())
            {
                shopersModelList.add(shoperModel);
            }
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            addMessage(model, e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        }
        model.addAttribute("shopersModelList", shopersModelList);
        model.addAttribute("_noShoper", Boolean.TRUE);// 由于产品直接结算 没有经过购物车 做个标记
        model.addAttribute("_productId", productId);
        return "dealer/shopper_balance_v2";
    }
    
    /**
     * 经销商 删车 单一/批量/清空
     *
     * @param dealerShopersId
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/removeCart", method = RequestMethod.POST)
    public JsonMessage removeCartAction(@RequestParam(value = "dealerShopersId") List<String> dealerShopersId) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerShoperService.batchRemoveShopers(dealerShopersId, dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /* ========================================= 加车动作 end ================================================ */
    /* ========================================= 购物车index [@author易永耀] begin================================================ */
    /**
     * 经销商购物车 （进货单）index data
     *
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/cart")
    public String cartIndex(Model model)
    {
        String dealerId;
        List<DealerShoper> dealerShoperList;
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            // 标识非首页的购物车，取所有的购物车记录，而非前3条
            dealerShoperList = dealerShoperService.selectDealerShoperBy(dealerId, Boolean.FALSE);
            if (!ListUtils.isNotEmpty(dealerShoperList)) { return "dealer/shopper_index_v2"; }
            // sku属性装入实体
            for (DealerShoper dealerShoper : dealerShoperList)
            {
                List<DealerShopers> dealerShopersList = dealerShopersService.selectDealerShopersBy(dealerShoper.getRefrenceId(), dealerShoper.getProductType());
                dealerShoper.setDealerShopersList(dealerShopersList);
            }
            // 封装数据
            Map<String, ShoperModel> shoperModelMap = transDealerShopperToShoperModelList(dealerShoperList);
            List<ShoperModel> shoperModelList = Lists.newArrayList();
            for (ShoperModel shoperModel : shoperModelMap.values())
            {
                shoperModelList.add(shoperModel);
            }
            model.addAttribute("shopersModelList", shoperModelList);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            addMessage(model, e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        }
        return "dealer/shopper_index_v2";
    }
    
    // 数据封装模型
    public Map<String, ShoperModel> transDealerShopperToShoperModelList(List<DealerShoper> dealerShoperList)
    {
        Map<String, ShoperModel> shoperModelMap = Maps.newHashMap();
        for (DealerShoper item : dealerShoperList)
        {
            if (item == null)
            {
                continue;
            }
            String brandesId = item.getBrandsId();
            if (shoperModelMap.get(brandesId) == null)
            {
                ShoperModel shoperModel = new ShoperModel();
                shoperModel.setBrandId(item.getBrandId());
                shoperModel.setBrandName(item.getBrandName());
                shoperModel.setBrandesId(brandesId);
                shoperModel.setBrandesName(item.getBrandsName());
                shoperModel.getDealerShoperList().add(item);
                shoperModelMap.put(brandesId, shoperModel);
            }
            else
            {
                ShoperModel shoperModel = shoperModelMap.get(brandesId);
                shoperModel.getDealerShoperList().add(item);
            }
            if (item.getProductNum() != null && item.getProductNum() > 0)
            {
                ShoperModel shoperModel = shoperModelMap.get(brandesId);
                shoperModel.setPurchasePriceAllSum(shoperModel.getPurchasePriceAllSum().add(item.getProductPrice()).subtract(item.getDiscountPrice()));
                shoperModel.setPurchaseNumAllSum(shoperModel.getPurchaseNumAllSum() + item.getProductNum());
            }
        }
        return shoperModelMap;
    }
    
    /* ========================================= 购物车显示 end ================================================ */
    /* ========================================= 购物车结算前 结算数据实时同步 [@author易永耀] begin================================================ */
    /**
     * 结算数据实时同步
     * @param jsonList 需要同步的数据
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/synShopers")
    public JsonMessage synShopersForClear(@RequestBody List<JSONObject> jsonList)
    {
        Map<String, Map<String, Object>> dealerShoperMap;
        try
        {
            UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
            if (null == userPrincipal || DealerConstant.userType.DEALER_TYPE != userPrincipal.getUserType()) { throw new BusinessException(CommonConst.USER_NOT_LOGIN); }
            dealerShoperMap = dealerShoperService.synShopers(jsonList, userPrincipal.getRefrenceId());
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS, dealerShoperMap);
    }
    
    /* ========================================= 购物车结算前 结算数据实时同步 end ================================================ */
    /* ========================================= 购物车结算 [@author易永耀] begin================================================ */
    /**
     * 新版 购物车列表页 结算页面
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    public String shopperBalance(String[] shopperIds, Model model)
    {
        List<ShoperModel> shopersModelList = Lists.newArrayList();
        try
        {
            if (shopperIds == null || shopperIds.length == 0) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            List<DealerShoper> dealerShoperList = dealerShoperService.getPurchaseDealerShoperList(dealerId, shopperIds);
            if (!ListUtils.isNotEmpty(dealerShoperList)) { throw new BusinessException(CommonConst.FAIL.code, "获取不到进货单数据"); }
            for (DealerShoper dealerShoper : dealerShoperList)
            {
                Boolean isPoint = dealerShoper.getIsPoint();
                List<DealerShopers> dealerShopersList = dealerShopersService.selectDealerShopersBy(dealerShoper.getRefrenceId(), dealerShoper.getProductType());
                /* this.countDealerOrdersToDealerOrder(dealerShoper, dealerShopersList); */
                // 由于购物车中dealerShoper.productPrice不是实时计算的，导致产品没重新加车时，产品的价格变动（拿样，现款，授信），sku实时同步了，但总价格没有实时同步，这里需要重新计算下
                BigDecimal productPrice = BigDecimal.ZERO;
                for (DealerShopers dealerShopers : dealerShopersList)
                {
                    if (isPoint)
                    {
                        productPrice = productPrice.add(dealerShopers.getProductSkuPrice().multiply(BigDecimal.ONE.subtract(dealerShoper.getPointPercent()))
                                .multiply(new BigDecimal(dealerShopers.getPurchaseNum())));// 返点成本价 = (1-返点比例)*返点价
                    }
                    else
                    {
                        productPrice = productPrice.add(dealerShopers.getProductSkuPrice().multiply(new BigDecimal(dealerShopers.getPurchaseNum())));
                    }
                }
                dealerShoper.setProductPrice(productPrice);
                dealerShoper.setDiscountPrice(dealerShoper.getProductPrice().multiply(BigDecimal.ONE.subtract(dealerShoper.getDiscount())));
                dealerShoper.setDealerShopersList(dealerShopersList);
            }
            // 封装数据
            Map<String, ShoperModel> shoperModelMap = transDealerShopperToShoperModelList(dealerShoperList);
            for (ShoperModel shoperModel : shoperModelMap.values())
            {
                shopersModelList.add(shoperModel);
            }
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            addMessage(model, e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        }
        model.addAttribute("shopersModelList", shopersModelList);
        model.addAttribute("_noShoper", Boolean.FALSE);// 经过购物车 做个标记
        return "dealer/shopper_balance_v2";
    }
    
    /* ========================================= 购物车结算 end ================================================ */
    /* =========================================结算页面运费模版 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/calcfreight")
    public JsonMessage calcFreight(int areaNo, String brandesId, String[] productIds, int[] productcount)
    {
        BrandFreightQueryModel brandFreightQueryModel = new BrandFreightQueryModel();
        brandFreightQueryModel.setAreaNo(areaNo);
        for (int i = 0; i < productIds.length; i++)
        {
            brandFreightQueryModel.addProductParam(productIds[i], productcount[i]);
        }
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, brandesId);
        brandFreightQueryModel.setIsClean(StringUtils.isBlank(brandesId));
        try
        {
            if (StringUtils.isBlank(brandesId)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
            jsonMessage.setRows(brandFreightTemplateService.getFreightAmount(brandFreightQueryModel));
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode(), brandesId);
        }
        return jsonMessage;
    }
    
    /* ========================================= 结算页面运费模版 end ================================================ */
    /* ========================================= 经销商授权产品库 [@author易永耀] begin================================================ */
    /**
     * 经销商授权产品库
     * @param brandsId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/grant", method = RequestMethod.GET)
    public String grantIndex(String brandsId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<Map<String, Object>> mapList = dealerGrantService.selectGrantCata(dealerId); // 品牌类目 分类
        List<DataDictValue> dataDictValueList = dataDictValueService.findByDictCode(DataDictConstant.DEAER_SEARCH_SORT); // 数据字典 经销商搜索排序
        model.addAttribute("brandsCataList", mapList);
        model.addAttribute("sortTypeList", dataDictValueList);
        model.addAttribute("brandsId", brandsId);
        return "dealer/shopper_grant";
    }
    
    /**
     * 经销商授权产品库 data
     * @param pagination
     * @param filter
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/grant/data", method = RequestMethod.GET)
    public JsonMessage grantData(Pagination pagination, ProductFilter filter)
    {
        try
        {
            pagination.setPageSize(20);
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            filter.setDealerId(dealerId);
            PaginateResult<Map<String, Object>> pageResult = dealerGrantService.selectGrantProductPage(pagination, filter);
            return this.getJsonMessage(CommonConst.SUCCESS, pageResult);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /* ========================================= 经销商授权产品库 end ================================================ */
    /* ========================================= 经销商常进款式 [@author易永耀] begin================================================ */
    /**
     * 经销商常进款式
     * @param brandsId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/orderc", method = RequestMethod.GET)
    public String ordercIndex(String brandsId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<Map<String, Object>> mapList = dealerOrdercService.selectOrdercCata(dealerId); // 品牌类目 分类
        List<DataDictValue> dataDictValueList = dataDictValueService.findByDictCode(DataDictConstant.DEAER_SEARCH_SORT); // 数据字典 经销商搜索排序
        model.addAttribute("brandsCataList", mapList);
        model.addAttribute("sortTypeList", dataDictValueList);
        model.addAttribute("brandsId", brandsId);
        return "dealer/shopper_orderc";
    }
    
    /**
     * 经销商常进款式数据 data
     * @param pagination
     * @param filter
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/orderc/data", method = RequestMethod.GET)
    public JsonMessage ordercData(Pagination pagination, ProductFilter filter)
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            filter.setDealerId(dealerId);
            PaginateResult<Map<String, Object>> pageResult = dealerOrdercService.selectOrdercProductPage(pagination, filter);
            return this.getJsonMessage(CommonConst.SUCCESS, pageResult);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 经销商 常进款式 delete
     *
     * @param dealerOrdercsId
     * @return
     * @throws BusinessException
     * @author 易永耀
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/removeOrderc", method = RequestMethod.POST)
    public JsonMessage removeOrdercAction(@RequestParam(value = "dealerOrdercsId") List<String> dealerOrdercsId) throws BusinessException
    {
        if (!ListUtils.isNotEmpty(dealerOrdercsId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        try
        {
            dealerOrdercService.batchRemoveOrdercProduct(dealerOrdercsId);
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(), e.getLocalizedMessage());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /* ========================================= 经销商常进款式 end ================================================ */
    /* ========================================= 经销商浏览记录 [@author易永耀] begin================================================ */
    /**
     * 经销商浏览记录
     * @param brandsId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/browseHistroy", method = RequestMethod.GET)
    public String histroyIndex(String brandsId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<Map<String, Object>> mapList = productViewLogService.selectViewLogCata(dealerId); // 品牌类目 分类
        List<DataDictValue> dataDictValueList = dataDictValueService.findByDictCode(DataDictConstant.DEAER_SEARCH_SORT); // 数据字典 经销商搜索排序
        model.addAttribute("brandsCataList", mapList);
        model.addAttribute("sortTypeList", dataDictValueList);
        model.addAttribute("brandsId", brandsId);
        return "dealer/shopper_productviewlog";
    }
    
    /**
     * 经销商浏览记录  data
     * @param pagination
     * @param filter
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/browseHistroy/data", method = RequestMethod.GET)
    public JsonMessage historyData(Pagination pagination, ProductFilter filter)
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            filter.setDealerId(dealerId);
            PaginateResult<Map<String, Object>> pageResult = productViewLogService.selectViewLogProductPage(pagination, filter);
            return this.getJsonMessage(CommonConst.SUCCESS, pageResult);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 经销商 浏览记录 delete
     *
     * @param productViewLogsId
     * @return
     * @author 易永耀
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/removeHistory", method = RequestMethod.POST)
    public JsonMessage removeHistoryAction(@RequestParam(value = "productViewLogsId") List<String> productViewLogsId)
    {
        try
        {
            if (!ListUtils.isNotEmpty(productViewLogsId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
            productViewLogService.batchRemoveHistoryProduct(productViewLogsId);
            return this.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /* ========================================= 经销商浏览记录 end ================================================ */
    /* ========================================= 快速下单 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/quickCart", method = RequestMethod.POST)
    public JsonMessage quickCart(ProductFilter productFilter) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        productFilter.setDealerId(dealerId);
        Pagination pagination = new Pagination();
        pagination.setPageSize(10);
        List<Map<String, Object>> mapList = dealerGrantService.selectGrantProductPage(pagination, productFilter).getList();
        return this.getJsonMessage(CommonConst.SUCCESS, mapList);
    }
    
    /* ========================================= 快速下单 end ================================================ */
    /* ========================================= 购物车交易模式更新 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/updateTradeModel", method = RequestMethod.POST)
    public JsonMessage updateProductTradeModel(String shoperId, Integer productType) // tradeModel 0:现款 1：授信 2：拿样
    {
        DealerShoper dealerShoper;
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            dealerShoper = dealerShoperService.updateProductTradeModel(dealerId, shoperId, productType);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS, dealerShoper);
    }
    
    /* ========================================= 购物车交易模式更新 end ================================================ */
    /* ========================================= 购物车进度条 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/getProcess", method = RequestMethod.POST)
    public String getProcess()
    {
        return "";
    }
    /* ========================================= 购物车进度条 end ================================================ */
}
