/*
 * @(#)MarketController.java 2015-8-18 下午6:10:06
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.zttx.goods.module.dto.ProductBaseInfoModel;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductImage;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.ZttxConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.PaginateUtils;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.DealDicServiceDubboConsumer;
import com.zttx.web.dubbo.service.ProductImageDubboConsumer;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.dubbo.service.ProductSkuInfoDubboConsumer;
import com.zttx.web.module.brand.entity.*;
import com.zttx.web.module.brand.service.*;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.entity.*;
import com.zttx.web.module.common.model.UserOnlineServiceModel;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerFavorite;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.module.exhibition.entity.DecorateGlobal;
import com.zttx.web.module.exhibition.entity.DecorateHeader;
import com.zttx.web.module.exhibition.entity.DecorateMenu;
import com.zttx.web.module.exhibition.model.MenuJsonModel;
import com.zttx.web.module.exhibition.service.DecorateGlobalService;
import com.zttx.web.module.exhibition.service.DecorateHeaderService;
import com.zttx.web.module.exhibition.service.DecorateMenuService;
import com.zttx.web.module.exhibition.service.DecorateService;
import com.zttx.web.search.module.SolrModel;
import com.zttx.web.search.query.ProductSolrQueryService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.DecorateMenuHelper;
import com.zttx.web.utils.IPUtil;

/**
 * <p>File：MarketController.java</p>
 * <p>Title: 品牌首页 、企业展示，产品展示、门店展厅 、品牌新闻、资料下载</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-18 下午6:10:06</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平,郭小亮
 * @version 1.0
 */
@Controller
@RequestMapping("/market")
public class MarketController extends GenericBaseController
{
    private static Logger                  logger = LoggerFactory.getLogger(MarketController.class);
    
    @Autowired
    private BrandesInfoService             brandesInfoService;
    
    @Autowired
    private DecorateHeaderService          headerService;
    
    @Autowired
    private DecorateGlobalService          globalService;
    
    @Autowired
    private DecorateMenuService            decorateMenuService;
    
    @Autowired
    private DecorateService                decorateService;
    
    @Autowired
    private BrandNewsService               brandNewsService;
    
    @Autowired
    private BrandsCountService             brandsCountService;
    
    @Autowired
    private DealerCollectService           dealerCollectService;
    
    @Autowired
    private BrandRoomService               brandRoomService;
    
    @Autowired
    private BrandInfoService               brandInfoService;
    
    @Autowired
    private BrandRecruitService            brandRecruitService;
    
    @Autowired
    private DealerJoinService              dealerJoinService;
    
    @Autowired
    private DataDictValueService           dataDictValueService;
    
    @Autowired
    private BrandContactService            brandContactService;
    
    @Autowired
    private BrandInviteService             brandInviteService;
    
    @Autowired
    private UserOnlineServiceService       userOnlineServiceService;
    
    @Autowired
    private BrandAlbumService              brandAlbumService;
    
    @Autowired
    private BrandNetworkService            brandNetworkService;
    
    @Autowired
    private BrandDoccateService            brandDoccateService;
    
    @Autowired
    private BrandsVideoService             brandsVideoService;
    
    @Autowired
    private ProductCatalogService          productCatalogService;
    
    @Autowired
    private ProductSolrQueryService        productSolrQueryService;
    
    @Autowired
    private RegionalService                regionalService;
    
    @Autowired
    private DealerInfoService              dealerInfoService;
    
    @Autowired
    private DealerFavoriteService          dealerFavoriteService;
    
    @Autowired
    private BrandCatelogService            brandCatelogService;
    
    @Autowired
    private UserOnlineServiceDetailService userOnlineServiceDetailService;
    
    @Autowired
    private BrandLevelService              brandLevelService;
    
    @Autowired
    private DealerOrderService             dealerOrderService;
    
    @Autowired
    private BrandDocumentService           brandDocumentService;
    
    @Autowired
    private ProductViewLogService          productViewLogService;
    
    @Autowired
    private ProductImageDubboConsumer      productImageDubboConsumer;
    
    @Autowired
    private ProductInfoDubboConsumer       productInfoDubboConsumer;
    
    @Autowired
    private DealDicServiceDubboConsumer    dealDicServiceDubboConsumer;
    
    @Autowired
    private ProductSkuInfoDubboConsumer    productSkuInfoDubboConsumer;
    
    /**
     * MARKET公用头部 css
     *
     * @param brandesId
     * @param model
     * @return
     */
    @RequestMapping(value = "/header_css")
    public String commonHeaderCss(String brandesId, Model model)
    {
        String brandId = brandesInfoService.selectByPrimaryKey(brandesId).getBrandId();
        DecorateHeader header = headerService.findByBrandIdAndBrandsId(brandId, brandesId);
        model.addAttribute("dHeader", header);
        DecorateGlobal dGlobal = globalService.findByBrandIdAndBrandsId(brandId, brandesId);
        model.addAttribute("dGlobal", dGlobal);// 全局属性
        return "fronts/market/common/view_header_css";
    }
    
    private String getHeaderHtml(String brandId, String brandsId)
    {
        DecorateHeader header = headerService.findByBrandIdAndBrandsId(brandId, brandsId);
        DecorateMenu menu = decorateMenuService.findByBrandIdAndBrandsId(brandId, brandsId);
        // meger 系统默认导航菜单
        List<MenuJsonModel> allMenus = DecorateMenuHelper.mergeSysMenu(menu, brandId, brandsId);
        if (null == menu)
        {
            menu = new DecorateMenu(brandId, brandsId, false, null);
        }
        menu.setMenus(allMenus);
        return DecorateMenuHelper.getHeaderHtml(header, menu, brandsId);
    }
    
    /**
     * MARKET公用头部
     *
     * @param brandesId
     * @param model
     * @return
     */
    @RequestMapping(value = "/header")
    public String commonHeader(String brandesId, String url, Model model)
    {
        String brandId = brandesInfoService.selectByPrimaryKey(brandesId).getBrandId();
        String headerHtml = getHeaderHtml(brandId, brandesId);
        Document doc = Jsoup.parse(headerHtml);
        Elements eles = doc.getElementById("menu-list").getElementsByTag("a");
        for (Element ele : eles)
        {
            String href = ele.attr("href");
            if (StringUtils.isNotBlank(href) && StringUtils.isNotBlank(url) && href.indexOf(url) != -1)
            {
                ele.addClass("selected");
                break;
            }
        }
        headerHtml = doc.getElementsByTag("body").html();
        model.addAttribute("headerHtml", headerHtml);
        return "fronts/market/common/view_common_header";
    }
    
    /**
     * 收藏信息展示
     *
     * @throws BusinessException
     * @author 周光暖
     */
    @RequestMapping(value = "/brand/attentionNum")
    public String attentionNum(String brandesId, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        String brandId = brandesInfo.getBrandId();
        BrandsCount brandsCount = brandsCountService.findByBrandIdAndBrandsId(brandId, brandesId);
        Boolean isCollected = this.isCollected(brandesId);
        // 品牌商登录不显示收藏按钮
        boolean showFavBtn = !isBrandLogin();
        // map.put("showFavBtn", showFavBtn);
        // map.put("isCollected", isCollected);
        map.put("brandsCount", brandsCount);
        return "fronts/market/view_attentionNum";
    }
    
    // 判断当前终端商是否已经收藏了该品牌
    private Boolean isCollected(String brandsId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (principal == null || principal.getUserType() != 1) { return false; }
        String dealerId = principal.getRefrenceId();
        if (StringUtils.isBlank(dealerId)) { return false; }
        if (StringUtils.isBlank(brandsId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        Long dealerCollect = this.dealerCollectService.findDealerCollect(dealerId, brandsId, BrandConstant.DEL_STATE_NONE_DELETED);
        return !(null == dealerCollect || dealerCollect.intValue() <= 0);
    }
    
    /**
     * 品牌信息展示
     *
     * @return
     * @author 周光暖
     */
    @RequestMapping(value = "/brand/brandesInfo")
    public String brandesInfo(String brandesId, ModelMap map)
    {
        BrandesInfo branesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        BrandRoom brandRoom = brandRoomService.findByBrandId(branesInfo.getBrandId());
        map.put("branesInfo", branesInfo);
        map.put("brandRoom", brandRoom);
        return "fronts/market/view_brandesInfo";
    }
    
    /**
     * 品牌商基本信息展示
     *
     * @return
     * @author 
     */
    @RequestMapping(value = "/brand/brandInfo")
    public String brandInfo(String brandId, String brandesId, ModelMap map, Model model, HttpServletRequest request)
    {
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        BrandRecruit brandRecruit = brandRecruitService.findByBrandIdAndBrandesid(brandId, brandesId);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        boolean hasJoined = false;
        String dealerId = null;
        String branderId = null;
        if (principal == null)
        {
            hasJoined = false;
        }
        else
        {
            if (principal.getUserType() == 1)
            {
                dealerId = principal.getRefrenceId();
                hasJoined = dealerJoinService.findByDealerIdAndBrandsId(principal.getRefrenceId(), brandesId) != null;
            }
            else
            {
                branderId = principal.getRefrenceId();
            }
        }
        String emploeeNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_COMPANY_SCOPE, brandInfo.getEmploeeNum().toString());
        String moneyNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_TURNOVER, brandInfo.getMoneyNum().toString());
        int btnState = 1; // 1 显示按钮 2表示已合作 0 不显示
        if (StringUtils.isNotBlank(dealerId))
        {
            model.addAttribute("dealerId", dealerId);
            DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerId, brandesId);
            if (dealerJoin != null)
            {
                map.put("dealerJoin", dealerJoin);
                BrandContact brandContact = brandContactService.findByBrandId(brandId);
                map.put("brandContact", brandContact);
            }
            else
            {
                UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
                if (dealerUserm != null)
                {
                    map.addAttribute("dealerUserm", dealerUserm);
                }
            }
            BrandInvite brandInvite = brandInviteService.getByDealerIdAndBrandsId(dealerId, brandesId);
            if (brandInvite == null)
            {
                btnState = 1;
            }
            else if (brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_FAILURE)
                    || brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_DEALER_STOP_COOPERATION)
                    || brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_CANCEL)
                    || brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_BRAND_STOP_COOPERATION))
            {
                btnState = 1;
            }
            else if (brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS))
            {
                btnState = 2;
            }
        }
        if (StringUtils.isNotBlank(branderId))
        {
            btnState = 0;
        }
        UserOnlineService userOnlineService = userOnlineServiceService.findUserOnlineService(brandId);
        UserOnlineServiceModel onlineModel = new UserOnlineServiceModel();
        if (userOnlineService != null)
        {
            BeanUtils.copyProperties(userOnlineService, onlineModel);
        }
        userOnlineServiceService.fillUserOnlineServiceDetail(onlineModel);
        map.put("userOnlineService", onlineModel);
        map.put("btnState", btnState);
        map.put("emploeeNum", emploeeNum);
        map.put("moneyNum", moneyNum);
        map.put("brandInfo", brandInfo);
        map.put("hasJoined", hasJoined);
        map.put("brandesId", brandesId);
        map.put("brandRecruit", brandRecruit);
        return "fronts/market/view_brandInfo";
    }
    
    /**
     * 品牌商旗下所有品牌信息展示
     *
     * @return braList[(id:品牌ID, name:品牌标题, imgUrl:品牌图片路径)]
     * @author 
     */
    @RequestMapping(value = "/brand/brandesList")
    public String viewBrandesShow(String brandId, Model model)
    {
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        List<BrandesInfo> braList = brandesInfoService.listByBrandStates(brandId, brandStates);
        model.addAttribute("braList", braList);
        return "fronts/market/view_brandesShow";
    }
    
    /**
     * 品牌首页 
     * @param brandesId 品牌id
     * @param fav boolean 是否同时收藏店铺
     * @param model  
     * @param request
     * @param response
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws BusinessException
     */
    @RequestMapping(value = "/brand/" + BrandsDomainConst.INDEX + "/{brandesId}")
    public String index(@PathVariable String brandesId, Boolean fav, Model model, HttpServletRequest request, HttpServletResponse response)
            throws InvocationTargetException, IllegalAccessException, BusinessException
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        if (brandesInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandesInfo.getBrandId());
        if (brandInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("brandId", brandInfo.getRefrenceId());
        String configsHTml = decorateService.getConfigsHtml(brandInfo.getRefrenceId(), brandesId, DecorateConfigConst.TAGID_LEFT);
        model.addAttribute("configsHTml", configsHTml);
        // 添加品牌访问计数
        executeDealerViewBrands(brandInfo.getRefrenceId(), brandesId);
        // 添加品牌收藏
        if (BooleanUtils.isTrue(fav))
        {
            executeDealerCollectBrands(brandInfo.getRefrenceId(), brandesId);
        }
        return "fronts/market/view_brandesIndex";
    }
    
    /**
     * 品牌招募书
     * @param brandesId
     * @param brandId
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/brand/" + BrandsDomainConst.RECRUIT + "/{brandesId}/{brandId}")
    public String viewBrandRecruit(@PathVariable String brandesId, @PathVariable String brandId, Model model, HttpServletRequest request, HttpServletResponse response)
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        if (brandesInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        BrandRecruit brandRecruit = brandRecruitService.findByBrandIdAndBrandesid(brandId, brandesId);
        if (brandRecruit != null)
        {
            if (brandRecruit.getRecruitState() != null && brandRecruit.getRecruitState().equals((short) 0))
            {
                brandRecruit = null;
            }
        }
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandesInfo.getBrandId());
        if (brandInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        model.addAttribute("brandRecruit", brandRecruit);
        model.addAttribute("brandMark", brandesInfo.getBrandMark());
        model.addAttribute("brandesInfo", brandesInfo);
        executeDealerViewBrands(brandInfo.getRefrenceId(), brandesId);
        return "fronts/market/view_brandRecruit";
    }
    
    /**
     * 品牌视觉
     * @param brandesId
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/brand/" + BrandsDomainConst.VISUAL + "/{brandesId}/{brandId}")
    public String viewBrandVisual(@PathVariable String brandesId, @PathVariable String brandId, Model model, HttpServletRequest request)
    {
        BrandAlbum params = new BrandAlbum();
        params.setBrandId(brandId);
        params.setBrandsId(brandesId);
        List<BrandAlbum> imageList = brandAlbumService.findList(params);
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("imageList", imageList);
        executeDealerViewBrands(brandInfo.getRefrenceId(), brandesId);
        return "fronts/market/view_brandVisual";
    }
    
    /**
     * 品牌 产品展示
     * @param brandsId
     * @param brandId
     * @param productFilter
     * @param pagination
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/brand/" + BrandsDomainConst.PRODUCT + "/{brandsId}/{brandId}")
    public String viewProductInfo(@PathVariable String brandsId, @PathVariable String brandId, ProductFilter productFilter, Pagination pagination, Model model,
            HttpServletRequest request, HttpServletResponse response)
    {
        PaginateResult<Map<String, Object>> pageResult = null;
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandsId);
        if (brandesInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        executeDealerViewBrands(brandesInfo.getBrandId(), brandsId);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        model.addAttribute("brandInfo", brandInfo);
        // 改默认排序为按上架时间来排序
//        if (productFilter.getSort() == null)
//        {
//            productFilter.setSort(2);
//        }
        if(productFilter.getOrder() == null){
        	productFilter.setOrder(1);//1：降序 2：升序
        }
        if(productFilter.getDisplayMode() == null){
        	productFilter.setDisplayMode(1);
        }
        boolean isLogin = isAlreadyLogin();
        model.addAttribute("isLogin", isLogin);
        if (isBrandLogin())
        {
            model.addAttribute("isBrand", true);
        }else{
        	model.addAttribute("isBrand", false);
        }
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("brandesId", brandsId);
        model.addAttribute("brandId", brandId);
        processFilter(brandsId, brandId, productFilter);
        model.addAttribute("filter", productFilter);
        // 一级分类
        List<ProductCatalog> primaryList = Lists.newArrayList(productCatalogService.getCatalogList(brandId, brandsId, BrandConstant.ProductCatalogConst.LEVEL_ONE));
        model.addAttribute("primaryList", primaryList);
        if (StringUtils.isNotBlank(productFilter.getCataId()))
        {
            ProductCatalog productCatalog = productCatalogService.selectByPrimaryKey(productFilter.getCataId());
            if (productCatalog != null)
            {
                List<ProductCatalog> secondList;
                if (productCatalog.getCateLevel().equals(BrandConstant.ProductCatalogConst.LEVEL_ONE))
                {
                    // 查找指定一级分类下的二级分类
                    secondList = productCatalogService.findSubCates(productFilter.getCataId());
                    model.addAttribute("primaryCate", productCatalog.getRefrenceId());
                }
                else
                {
                    // 查找相同二级分类的二级分类
                    model.addAttribute("secondCate", productCatalog.getRefrenceId());
                    secondList = productCatalogService.findSubCates(productCatalog.getParentId());
                }
                model.addAttribute("secondList", secondList);
            }
        }
        try
        {
            pagination.setPageSize(Integer.valueOf(15));
            SolrModel sm = productSolrQueryService.searchBrandProductList(productFilter, pagination);
            pageResult = sm.getResult();
            this.filterProductPrice(pageResult.getList(), request, productFilter);
            // 如果当前是终端商登录，则自动关注所有商品
            String dealerId = getCurrentLoginDealerId();
            if (StringUtils.isNotBlank(dealerId))
            {
                for (Map<String, Object> map : pageResult.getList())
                {
                    DealerFavorite dealerFavorite = new DealerFavorite();
                    String productId = (String) map.get("refrenceId");
                    dealerFavorite.setProductId(productId);
                    dealerFavorite.setDealerId(dealerId);
                    Boolean isExists = dealerFavoriteService.isProductExists(dealerFavorite);
                    map.put("tag", isExists);
                }
            }
        }
        catch (BusinessException e)
        {
            e.printStackTrace();
        }
        model.addAttribute("pageResult", pageResult);
        return "fronts/market/view_products";
    }
    
    private void processFilter(String brandesId, String brandId, ProductFilter filter)
    {
        filter.setBrandId(brandId);
        filter.setBrandsId(brandesId);
        /*
         * if (filter.getSort() == null)
         * {
         * filter.setSort(1);
         * }
         * if (filter.getOrder() == null)
         * {
         * filter.setOrder(1);
         * }
         */
        BigDecimal min = filter.getMin();
        BigDecimal max = filter.getMax();
        if (min != null && max != null && max.compareTo(min) < 0)
        {
            BigDecimal tmp = filter.getMax();
            filter.setMax(filter.getMin());
            filter.setMin(tmp);
        }
    }
    
    private void filterProductPrice(List<Map<String, Object>> productList, HttpServletRequest request, ProductFilter filter) throws BusinessException
    {
        String code = BrandConstant.BrandActivityList.ACTIVITY_CODE_BK;
        String dealerId = getCurrentLoginDealerId();
        DealerInfo dealerInfo = null;
        if (dealerId != null)
        {
            dealerInfo = dealerInfoService.getDealerInfo(dealerId);
        }
        String ip = IPUtil.getOriginalIpAddr(request);
        try
        {
            String city = new IPUtil().search4city(ip);
            Integer areaNo = null;
            if (dealerInfo != null)
            {
                areaNo = dealerInfo.getAreaNo();
            }
            else
            {
                Regional regional = regionalService.getRegionalByName(city);
                if (regional != null)
                {
                    areaNo = regional.getAreaNo();
                }
            }
            for (Map<String, Object> productMap : productList)
            {
                // TODO yyy
                // UserGrantModel userGrantModel = brandesUserAuthService
                // .getAuthProductPrice(request, dealerId,
                // (String) productMap.get("refrenceId"), code);
                // if (null != userGrantModel)
                // {
                // productMap.put("userGrantModel", userGrantModel);
                // }
            }
        }
        catch (Exception e)
        {
            logger.error("根据ip获取城市异常:", e);
        }
    }
    
    /**
     * 品牌 企业展示
     * @param brandesId
     * @param model
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/brand/" + BrandsDomainConst.COMPANY + "/{brandesId}/{brandId}")
    public String viewCompany(@PathVariable("brandesId") String brandesId, @PathVariable String brandId, Model model, HttpServletResponse response,
            HttpServletRequest request)
    {
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        if (brandInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("brandInfo", brandInfo);
        executeDealerViewBrands(brandInfo.getRefrenceId(), brandesId);
        return "fronts/market/view_company";
    }
    
    /**
     * 品牌新闻
     * @param brandesId
     * @param model
     * @param page
     * @param newsType
     * @param request
     * @return
     */
    @RequestMapping("/brand/" + BrandsDomainConst.NEWS + "/{brandesId}/{brandId}")
    public String viewBrandNews(@PathVariable("brandesId") String brandesId, @PathVariable String brandId, Model model,
            @RequestParam(required = false, defaultValue = "1") int page, Integer newsType, HttpServletRequest request, HttpServletResponse response)
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        if (brandesInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandesInfo.getBrandId());
        if (brandInfo == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        Pagination pagination = new Pagination(page, 10);
        BrandNews params = new BrandNews();
        params.setPage(pagination);
        params.setBrandsId(brandesId);
        if (newsType != null)
        {
            params.setCateId(String.valueOf(newsType));
        }
        List<Map<String, Object>> pageList = brandNewsService.listBrandNews(params);
        PaginateResult<Map<String, Object>> pageResult = new PaginateResult<Map<String, Object>>(pagination, pageList);
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("brandesId", brandesId);
        model.addAttribute("brandId", brandId);
        model.addAttribute("newsType", newsType);
        executeDealerViewBrands(brandInfo.getRefrenceId(), brandesId);
        return "fronts/market/view_brandNews";
    }
    
    /**
     * 品牌新闻-查看
     * @return
     * @author 张昌苗
     */
    @RequestMapping("/brand/viewBrandNewsInfo/{brandesId}/{brandId}/{newsId}")
    public String viewBrandNewsInfo(@PathVariable("brandesId") String brandesId, @PathVariable("brandId") String brandId, @PathVariable("newsId") String newsId, Model map,
            HttpServletResponse response)
    {
        BrandNews news = brandNewsService.selectByPrimaryKey(newsId);
        if (news == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return "";
        }
        brandNewsService.addHitNum(newsId);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        map.addAttribute("brandInfo", brandInfo);
        map.addAttribute("brandesInfo", brandesInfo);
        map.addAttribute("news", news);
        map.addAttribute("brandesId", brandesId);
        map.addAttribute("brandId", brandId);
        return "fronts/market/view_brandNewsInfo";
    }
    
    /**
     * 经销网络
     * @return
     * @author 郭小亮
     */
    @RequestMapping("/brand/" + BrandsDomainConst.NETWORK + "/{brandesId}/{brandId}")
    public String viewBrandNetwork(@PathVariable("brandesId") String brandesId, @PathVariable("brandId") String brandId, Model model,
            @RequestParam(required = false, defaultValue = "1") int page, HttpServletRequest request)
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandesInfo.getBrandId());
        model.addAttribute("brandesInfo", brandInfo);
        model.addAttribute("brandesInfo", brandesInfo);
        List<BrandNetwork> networkList = brandNetworkService.selectNetworkAndImgByBrandesId(brandesId);
        if (CollectionUtils.isNotEmpty(networkList))
        {
            Pagination pagination = PaginateUtils.getPagination(ZttxConst.DEFAULT_PAGE_SIZE, page, networkList.size());
            PaginateResult<BrandNetwork> pageResult = new PaginateResult<BrandNetwork>(pagination, networkList);
            model.addAttribute("pageResult", pageResult);
        }
        executeDealerViewBrands(brandesInfo.getBrandId(), brandesId);
        return "fronts/market/view_brandNetwork";
    }
    
    /**
     * 资料下载
     * @param brandesId
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/brand/" + BrandsDomainConst.DOCUMENT + "/{brandesId}/{brandId}")
    public String viewBrandDocument(@PathVariable("brandesId") String brandesId, @PathVariable String brandId, ModelMap map, HttpServletRequest request)
    {
        boolean isLogin = isAlreadyLogin();
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        map.addAttribute("brandesInfo", brandesInfo);
        map.put("isLogin", isLogin);
        executeDealerViewBrands(brandesInfo.getBrandId(), brandesId);
        return "fronts/market/view_brandDocument";
    }
    
    /**
     * 资料下载-内容
     */
    @RequestMapping(value = "/brand/brandDocument")
    public String brandDocument(String brandId, String brandesId, ModelMap map)
    {
        BrandDoccate params = new BrandDoccate();
        params.setBrandId(brandId);
        params.setBrandsId(brandesId);
        List<BrandDoccate> doccateList = brandDoccateService.findList(params);
        map.addAttribute("doccateList", doccateList);
        map.addAttribute("brandId", brandId);
        map.addAttribute("brandesId", brandesId);
        return "fronts/market/list_brandDocument";
    }
    
    /**
     * 资料下载数据
     *
     * @author 张昌苗
     */
    @RequestMapping(value = "/brand/listDocument")
    @ResponseBody
    public Object listDocument(BrandDocument brandDocument, Pagination page, HttpServletRequest request)
    {
        String dealerId = this.getCurrentLoginDealerId();
        PaginateResult<BrandDocument> paginateResult;
        if (StringUtils.isNotBlank(dealerId))
        {
            paginateResult = brandDocumentService.getBrandDocumentListByDealerId(page, dealerId, brandDocument);
        }
        else
        {
            String branderId = this.getCurrentLoginBrandId();
            if (StringUtils.isNotBlank(branderId) && branderId.equals(brandDocument.getBrandId()))
            {
                // 品牌商可以查看自己的所有下载链接
                paginateResult = brandDocumentService.getBrandDocumentListByDealerId(page, null, brandDocument);
            }
            else
            {
                paginateResult = brandDocumentService.getBrandDocumentListByDealerId(page, branderId, brandDocument);
            }
        }
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 视频主持人
     * @param brandsId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/brand/rightFlash")
    public String rightFlash(String brandsId, Model model) throws BusinessException
    {
        if (StringUtils.isBlank(brandsId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        BrandsVideo params = new BrandsVideo();
        params.setBrandsId(brandsId);
        List<BrandsVideo> videoList = brandsVideoService.findList(params);
        if (CollectionUtils.isNotEmpty(videoList))
        {
            model.addAttribute("brandsVideo", videoList.get(0));
        }
        return "fronts/market/view_rightFlash";
    }
    
    /**
     * 产品展示 栏目
     * @param brandesId
     * @param model
     * @return List<ProductInfo>
     */
    @RequestMapping(value = "/brand/listProduct")
    public String listProduct(String brandesId, Model model)
    {
        List<ProductBaseInfo> proList = productInfoDubboConsumer.getShowProductByBrandsIdAndSateSet(brandesId, ProductConsts.BEGIN_TYPE_FIRST.getKey().toString());
        model.addAttribute("proList", proList);
        return "/fronts/market/view_productShow";
    }
    
    /**
     * 产品列表数据
     * @param brandesId 品牌ID
     * @return JsonMessage
     */
    @RequestMapping(value = "/brand/listProductInfo")
    @ResponseBody
    public Object listProductInfo(String brandesId, Pagination page)
    {
        // ##获取发布的产品
        PaginateResult<ProductBaseInfo> proList = null;
        return getJsonMessage(CommonConst.SUCCESS.getCode(), proList);
    }
    
    /**
     * 产品详细信息
     * @param productId 产品id
     * @param model 数据
     * @return {@link String} jsp
     * @throws BusinessException
     * @author 章旭楠
     */
    @RequestMapping(value = "/product/{productId}")
    public String product(@PathVariable String productId, Model model) throws BusinessException
    {
        ProductBaseInfo productInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
        if (productInfo == null) { throw new BusinessException(CommonConst.PARAM_NULL, "产品id不存在"); }
        List<ProductImage> imageList = productImageDubboConsumer.findByProductId(productId);
        ProductBaseInfoModel productInfoModel = new ProductBaseInfoModel();
        productInfoModel.setProductImageList(imageList);
        productInfoModel.setRefrenceId(productInfo.getRefrenceId());
        productInfoModel.setBrandsId(productInfo.getBrandsId());
        productInfoModel.setDealNo(productInfo.getDealNo());
        productInfoModel.setProductNo(productInfo.getProductNo());
        productInfoModel.setProductPrice(productInfo.getProductPrice());
        productInfoModel.setProductCarry(productInfo.getProductCarry());
        productInfoModel.setProductExtInfo(productInfo.getProductExtInfo());
        productInfoModel.setProductTitle(productInfo.getProductTitle());
        productInfoModel.setProductImage(productInfo.getProductImage());
        productInfoModel.setStateSet(productInfo.getStateSet());
        setProductBaseInfoDetail(productInfoModel);
        Set<String> colors = new HashSet<String>();
        Double samplePrice = null;
        boolean sizeIds = false;
        List<ProductSku> productSkuList = productSkuInfoDubboConsumer.findByProductId(productId, false);
        productInfo.setProductSkuList(productSkuList);
        if (productInfo.getProductSkuList().size() > 0)
        {
            Map<String, List<ProductSku>> skuMap = new TreeMap<String, List<ProductSku>>();
            List<ProductSku> slist = productInfo.getProductSkuList();
            for (int i = 0; i < slist.size(); i++)
            {
                ProductSku sku = slist.get(i);
                List<ProductAttrValue> paList = sku.getAttrValueList();
                for (int j = 0; j < paList.size(); j++)
                {
                    ProductAttrValue pa = paList.get(j);
                    if (pa.getAttributeId().equals("3372"))
                    {
                        colors.add(pa.getExtAttributeValue());
                    }
                }
            }
            boolean has = false;
            Iterator<String> it = colors.iterator();
            for (int i = 0; i < colors.size(); i++)
            {
                String color = it.next();
                List<ProductSku> skuList = productInfo.getProductSkuList();
                for (int j = 0; j < skuList.size(); j++)
                {
                    ProductSku sku = skuList.get(j);
                    samplePrice = sku.getProductSkuPrice().getSamplePrice() == null ? 0.00 : sku.getProductSkuPrice().getSamplePrice().doubleValue();
                    List<ProductAttrValue> paList = sku.getAttrValueList();
                    for (int k = 0; k < paList.size(); k++)
                    {
                        if (!"22222".equals(paList.get(k).getAttributeItemId()) && !paList.get(k).getAttributeId().equals("3372"))
                        {
                            has = true;
                            break;
                        }
                    }
                    if (has)
                    {
                        break;
                    }
                }
                if (has)
                {
                    break;
                }
            }
            if (has)
            {
                sizeIds = true;
            }
            // model.addAttribute("skuMap", skuMap);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        /*
         * String userId=null;
         * if(principal!=null){
         * userId=principal.getRefrenceId();
         * }
         */
        // BrandesAuthUserModel priceModel=brandesAuthUserService.getAuthPrice(userId, productId, BrandesAuthUserModel.INCLUDE_SKU);
        model.addAttribute("sample", productInfo.getProductExtInfo().getSample());
        model.addAttribute("sizeIds", sizeIds);
        model.addAttribute("samplePrice", samplePrice);
        // model.addAttribute("colors", colors);
        model.addAttribute("productInfo", productInfoModel);
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(productInfo.getBrandsId());
        model.addAttribute("brandesInfo", brandesInfo);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(productInfo.getBrandId());
        model.addAttribute("brandInfo", brandInfo);
        BrandCatelog entity = new BrandCatelog();
        entity.setBrandId(productInfo.getBrandId());
        List<BrandCatelog> brandCatelogList = brandCatelogService.findList(entity);
        List<String> business = new ArrayList<String>();
        for (int i = 0; i < brandCatelogList.size(); i++)
        {
            BrandCatelog catelog = brandCatelogList.get(i);
            String[] dealDicNameAry = dealDicServiceDubboConsumer.getDealNamesWithParentByDealNo(catelog.getDealNo().toString());
            String dealDicName = "";
            for (int j = 0; j < dealDicNameAry.length; j++)
            {
                dealDicName += dealDicNameAry[j] + ",";
            }
            if (dealDicName.length() > 0)
            {
                dealDicName = dealDicName.substring(0, dealDicName.length() - 1);
            }
            business.add(dealDicName);
        }
        model.addAttribute("businesses", business);
        UserOnlineService online = userOnlineServiceService.findUserOnlineService(productInfo.getBrandId());
        if (online != null)
        {
            List<UserOnlineServiceDetail> detailList = userOnlineServiceDetailService.getByOnlineService(online.getRefrenceId());
            online.setDetailList(detailList);
        }
        model.addAttribute("online", online);
        BrandContact contact = brandContactService.findByBrandId(productInfo.getBrandId());
        model.addAttribute("brandContact", contact);
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        List<BrandesInfo> braList = brandesInfoService.listByBrandStates(productInfo.getBrandId(), brandStates);
        model.addAttribute("braList", braList);
        if (principal != null && principal.getUserType() == DealerConstant.userType.DEALER_TYPE)
        {
            model.addAttribute("dealerLogon", true);
            DealerJoin join = dealerJoinService.findByDealerIdAndBrandsId(principal.getRefrenceId(), productInfo.getBrandsId());
            if (join != null)
            {
                BrandLevel level = brandLevelService.selectByPrimaryKey(join.getLevelId());
                model.addAttribute("dealerJoin", join);
                model.addAttribute("level", level);
            }
            else
            {
                UserInfo dealerUserm = userInfoService.selectByPrimaryKey(principal.getRefrenceId());
                if (dealerUserm != null)
                {
                    model.addAttribute("dealerUserm", dealerUserm);
                }
            }
            Map<String, Object> countAndAmount = dealerOrderService.getCountAndAmountByDealerId(principal.getRefrenceId());
            model.addAttribute("countAndAmount", countAndAmount);
            DealerFavorite dealerFavorite = new DealerFavorite();
            dealerFavorite.setProductId(productId);
            dealerFavorite.setDealerId(principal.getRefrenceId());
            Boolean isExists = dealerFavoriteService.isProductExists(dealerFavorite);
            model.addAttribute("isFaved", isExists);
        }
        else
        {
            model.addAttribute("dealerLogon", false);
        }
        BrandsCount brandsCount = brandsCountService.findByBrandIdAndBrandsId(productInfo.getBrandId(), productInfo.getBrandsId());
        if (brandsCount == null)
        {
            brandsCount = new BrandsCount();
        }
        if (brandsCount.getViewNum() == null)
        {
            brandsCount.setViewNum(0);
        }
        // 品牌收藏相关
        Boolean isCollected = this.isCollected(productInfo.getBrandsId());
        model.addAttribute("isCollected", isCollected);
        boolean showFavBtn = !isBrandLogin(); // 品牌商登录不显示收藏按钮
        model.addAttribute("showFavBtn", showFavBtn);
        model.addAttribute("brandsCount", brandsCount);
        model.addAttribute("isLogin", null != OnLineUserUtils.getPrincipal());
        if (OnLineUserUtils.getPrincipal() != null)
        {
            ProductViewLog productViewLog = new ProductViewLog();
            productViewLog.setProductId(productInfo.getRefrenceId());
            productViewLog.setProductTitle(productInfo.getProductTitle());
            productViewLog.setProductPrice(productInfo.getProductPrice()); // 价格无用
            productViewLog.setUserCate(principal.getUserType());
            productViewLog.setProductImage(productInfo.getProductImage());
            productViewLog.setUserId(principal.getRefrenceId());
            productViewLog.setUserName(principal.getUserName());
            productViewLogService.saveProductViewLog(productViewLog);
        }
        /**{ 2015/11/6 经销商浏览产品记录*/
        if(principal != null && principal.getUserType() == DealerConstant.userType.DEALER_TYPE){
            ProductFilter filter = new ProductFilter();
            filter.setDealerId(principal.getRefrenceId());
            Pagination pagination = new Pagination(5);
            model.addAttribute("browseHistroy", productViewLogService.selectViewLogProductPage(pagination, filter).getList());
        }
        /**}*/
        // 销售状态
        // int saleState = productInfoDubboConsumer.(productInfo);
        // model.addAttribute("saleState", saleState);
        String emploeeNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_COMPANY_SCOPE, brandInfo.getEmploeeNum().toString());
        String moneyNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_TURNOVER, brandInfo.getMoneyNum().toString());
        model.addAttribute("emploeeNum", emploeeNum);
        model.addAttribute("moneyNum", moneyNum);
        return "/fronts/market/view_product";
    }
    
    private void setProductBaseInfoDetail(ProductBaseInfoModel productInfo) throws BusinessException
    {
        // 增加brandesState属性,目的用于产品ProductInfo页面状态显示,如果该品牌已经过期,那么该产品就应该显示为下架 yyy
        if (null != productInfo)
        {
            BrandesInfo brandes = brandesInfoService.selectByPrimaryKey(productInfo.getBrandsId());
            productInfo.setBrandesState(brandes.getBrandState().toString());
            productInfo.setProductMark(productInfo.getProductExtInfo().getProductMark());
            // 品类名称
            String[] dealNames = dealDicServiceDubboConsumer.getDealNamesWithParentByDealNo(productInfo.getDealNo() + "");
            productInfo.setDealNames(dealNames);
            // 图片路径
            List<String> imageUrls = Lists.newArrayList();
            List<ProductImage> images = productInfo.getProductImageList();
            for (int i = 0; i < images.size(); i++)
            {
                if(StringUtils.isEmpty(images.get(i).getAttributeItemId())){
                    if (i == 0)
                    {
                        productInfo.setProductImage(images.get(i).getImageName());
                    }
                    imageUrls.add(images.get(i).getImageName());
                }
            }
            productInfo.setImageUrls(imageUrls.toArray(new String[]{}));
        }
    }

    /**
     * 用户操作记录
     *
     * @param request
     * @param id
     * @param code
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/user_rtk", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage addRtxRecord(HttpServletRequest request, String id, int code, String productId) throws BusinessException
    {
        return this.getJsonMessage(CommonConst.SUCCESS);
    }

    /**
     * 预览页面
     *
     * @param brandesId
     * @param model
     * @return
     * @author 吴万杰
     */
    @RequestMapping(value = "/brand/preview/{brandesId}")
    public String preview(@PathVariable String brandesId, Model model) throws InvocationTargetException, IllegalAccessException
    {
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        String brandId = brandesInfo.getBrandId();
        model.addAttribute("brandesInfo", brandesInfo);
        model.addAttribute("brandId", brandId);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        model.addAttribute("brandInfo", brandInfo);
        Short tagId = DecorateConfigConst.TAGID_LEFT;
        String configsHTml = decorateService.getConfigsHtmlView(brandId, brandesId, tagId);
        model.addAttribute("configsHTml", configsHTml);
        model.addAttribute("preview", "_preview");
        return "fronts/market/view_brandesIndex";
    }

    /**
     * 添加收藏
     * @author 张昌苗
     */
    @RequestMapping(value = "/brand/attentionNum/add")
    @ResponseBody
    public JsonMessage attentionNumAdd(String brandesId, HttpServletRequest request) throws BusinessException
    {
        String dealerId = this.getCurrentLoginDealerId();
        if (StringUtils.isBlank(dealerId)) { throw new BusinessException(CommonConst.USER_NOT_LOGIN); }
        if (StringUtils.isBlank(brandesId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        this.dealerCollectService.saveCollect(dealerId, brandesId);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }

    /**
     * 品牌 前台页面右侧工具栏
     * @author 郭小亮
     */
    @RequestMapping(value = "/brand/rightSidebar")
    public String rightSidebar(String brandesId, String brandId, HttpServletRequest request, Model model) throws BusinessException
    {
        // 品牌基本信息
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandesId);
        if (StringUtils.isBlank(brandId)) {
            brandId = brandesInfo.getBrandId();
        }

        BrandRoom brandRoom = brandRoomService.findByBrandId(brandId);
        model.addAttribute("branesInfo", brandesInfo);
        model.addAttribute("brandRoom", brandRoom);

        // 品牌商基本信息
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        BrandRecruit brandRecruit = brandRecruitService.findByBrandIdAndBrandesid(brandId, brandesId);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        boolean hasJoined = false;
        String dealerId = null;
        String branderId = null;
        if (principal == null)
        {
            hasJoined = false;
        }
        else
        {
            if (principal.getUserType() == 1)
            {
                dealerId = principal.getRefrenceId();
                hasJoined = dealerJoinService.findByDealerIdAndBrandsId(principal.getRefrenceId(), brandesId) != null;
            }
            else
            {
                branderId = principal.getRefrenceId();
            }
        }
        String emploeeNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_COMPANY_SCOPE, brandInfo.getEmploeeNum().toString());
        String moneyNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_TURNOVER, brandInfo.getMoneyNum().toString());
        int btnState = 1; // 1 显示按钮 2表示已合作 0 不显示
        if (StringUtils.isNotBlank(dealerId))
        {
            model.addAttribute("dealerId", dealerId);
            DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(dealerId, brandesId);
            if (dealerJoin != null)
            {
                model.addAttribute("dealerJoin", dealerJoin);
                BrandLevel level = brandLevelService.selectByPrimaryKey(dealerJoin.getLevelId());
                model.addAttribute("level", level);
            }
            else
            {
                UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
                if (dealerUserm != null)
                {
                    model.addAttribute("dealerUserm", dealerUserm);
                }
            }
            BrandInvite brandInvite = brandInviteService.getByDealerIdAndBrandsId(dealerId, brandesId);
            if (brandInvite == null)
            {
                btnState = 1;
            }
            else if (brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_FAILURE)
                    || brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_DEALER_STOP_COOPERATION)
                    || brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_CANCEL)
                    || brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_BRAND_STOP_COOPERATION))
            {
                btnState = 1;
            }
            else if (brandInvite.getApplyState().equals(BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS))
            {
                btnState = 2;
            }
        }
        if (StringUtils.isNotBlank(branderId))
        {
            btnState = 0;
        }
        // 品牌商行业
        BrandCatelog entity = new BrandCatelog();
        entity.setBrandId(brandId);
        List<BrandCatelog> brandCatelogList = brandCatelogService.findList(entity);
        List<String> business = new ArrayList<String>();
        for (int i = 0; i < brandCatelogList.size(); i++)
        {
            BrandCatelog catelog = brandCatelogList.get(i);
            String[] dealDicNameAry = dealDicServiceDubboConsumer.getDealNamesWithParentByDealNo(catelog.getDealNo().toString());
            String dealDicName = "";
            for (int j = 0; j < dealDicNameAry.length; j++)
            {
                dealDicName += dealDicNameAry[j] + ",";
            }
            if (dealDicName.length() > 0)
            {
                dealDicName = dealDicName.substring(0, dealDicName.length() - 1);
            }
            business.add(dealDicName);
        }
        model.addAttribute("businesses", business);
        BrandContact brandContact = brandContactService.findByBrandId(brandId);
        model.addAttribute("brandContact", brandContact);
        UserOnlineService userOnlineService = userOnlineServiceService.findUserOnlineService(brandId);
        UserOnlineServiceModel onlineModel = new UserOnlineServiceModel();
        if (userOnlineService != null)
        {
            BeanUtils.copyProperties(userOnlineService, onlineModel);
        }
        userOnlineServiceService.fillUserOnlineServiceDetail(onlineModel);
        model.addAttribute("online", onlineModel);
        model.addAttribute("btnState", btnState);
        model.addAttribute("emploeeNum", emploeeNum);
        model.addAttribute("moneyNum", moneyNum);
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("hasJoined", hasJoined);
        model.addAttribute("brandesId", brandesId);
        model.addAttribute("brandRecruit", brandRecruit);

        // 添加品牌访问计数
        //executeDealerViewBrands(brandId, brandesId);

        // 品牌商下的其他品牌
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        List<BrandesInfo> braList = brandesInfoService.listByBrandStates(brandId, brandStates);
        model.addAttribute("braList", braList);

        // 品牌收藏相关
        Boolean isCollected = this.isCollected(brandesId);
        model.addAttribute("isCollected", isCollected);
        model.addAttribute("isLogin", null != OnLineUserUtils.getPrincipal());
        /**{ 2015/11/6 经销商浏览产品记录*/
        if(principal != null && principal.getUserType() == DealerConstant.userType.DEALER_TYPE){
            ProductFilter filter = new ProductFilter();
            filter.setDealerId(principal.getRefrenceId());
            Pagination pagination = new Pagination(5);
            model.addAttribute("browseHistroy", productViewLogService.selectViewLogProductPage(pagination, filter).getList());
        }
        /**}*/
        // 浏览量 关注度
        BrandsCount brandsCount = brandsCountService.findByBrandIdAndBrandsId(brandId, brandesId);
        if (brandsCount == null)
        {
            brandsCount = new BrandsCount();
            brandsCount.setFavNum(0);
            brandsCount.setViewNum(0);
        }
        model.addAttribute("brandsCount", brandsCount);
        boolean showFavBtn = !isBrandLogin(); // 品牌商登录不显示收藏按钮
        model.addAttribute("showFavBtn", showFavBtn);
        return "fronts/market/right_sidebar_new";
    }
}
