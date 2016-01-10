package com.zttx.web.module.brand.controller;

import com.google.common.collect.Maps;
import com.zttx.goods.module.service.ProductSkuService;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.UserInfoConst;
import com.zttx.web.module.common.entity.PointSaleDetail;
import com.zttx.web.module.common.entity.PointSaleSum;
import com.zttx.web.module.common.entity.StockAdjustDetail;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.PointSaleDetailService;
import com.zttx.web.module.common.service.PointSaleSumService;
import com.zttx.web.module.common.service.StockAdjustDetailService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>File:BrandFinancialPointController</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/11/23 9:48</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/brand/brandFinancialPoint")
public class BrandFinancialPointController extends GenericController
{
    @Autowired
    private PointSaleDetailService   pointSaleDetailService;
    
    @Autowired
    private UserInfoService          userInfoService;
    
    @Autowired
    private PointSaleSumService      pointSaleSumService;
    
    @Autowired
    private StockAdjustDetailService stockAdjustDetailService;
    
    @Autowired(required = false)
    private ProductSkuService        productSkuService;
    
    /* ========================================= 返点财务帐 销售详情 [@author易永耀] begin================================================ */
    /**
     * 返点财务帐 erp门店销售明细
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/saleDetail")
    public String saleDetailIndex()
    {
        return "brand/rebate_financial_dayDetail";
    }
    
    @RequestMapping(value = "/saleDetail/data", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage saleDetailData(Pagination page, PointSaleDetail pointSaleDetail)
    {
        String brandId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            pointSaleDetail.setBrandId(brandId);
            paginateResult = pointSaleDetailService.selectPointSaleDetailPage(page, pointSaleDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 返点财务帐erp销售明细和
     * @param dealerId
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/saleSum")
    public String saleSumIndex(String dealerId, Model model) throws BusinessException
    {
        UserInfo userInfo = userInfoService.selectUserInfoMore(dealerId, UserInfoConst.USER_TYPE_DEALER);
        model.addAttribute("dealerInfo", userInfo);
        return "brand/rebate_financial_daySum";
    }
    
    @RequestMapping(value = "/saleSum/data", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage saleSumData(Pagination page, PointSaleSum pointSaleSum)
    {
        String brandId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            pointSaleSum.setBrandId(brandId);
            paginateResult = pointSaleSumService.selectPointSaleSumPage(page, pointSaleSum, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /* ========================================= 返点财务帐 销售详情 end ================================================ */
    /* ========================================= 返点财务帐 库存详情 [@author易永耀] begin================================================ */
    @RequestMapping(value = "/stockCountBySkuDetail")
    @RequiresPermissions("brand:center")
    public String stockCountBySkuDetailIndex(String dealerId, String productSkuId, Model model) throws BusinessException
    {
        StockAdjustDetail stockAdjustDetail = new StockAdjustDetail();
        stockAdjustDetail.setBrandId(OnLineUserUtils.getCurrentBrand().getRefrenceId());
        stockAdjustDetail.setDealerId(dealerId);
        stockAdjustDetail.setProductSkuId(productSkuId);
        Map<String, Object> headMap = stockAdjustDetailService.selectHeadData(stockAdjustDetail);
        model.addAttribute("headMap", headMap);
        return "brand/rebate_stock_count_bySku_detail";
    }
    
    @RequestMapping(value = "/stockCountBySkuDetail/data")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage stockCountBySkuDetailData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String brandId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            stockAdjustDetail.setBrandId(brandId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailBySku(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequestMapping(value = "/stockCountByDayDetail")
    @RequiresPermissions("brand:center")
    public String stockCountByDayDetailIndex()
    {
        return "brand/rebate_stock_count_byDate_detail";
    }
    
    @RequestMapping(value = "/stockCountByDayDetail/data")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage stockCountByDayDetailData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String brandId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            stockAdjustDetail.setBrandId(brandId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailByTimeAndSource(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/stockCountByDate")
    public String stockCountByDateIndex(String dealerId, Model model)
    {
        UserInfo userInfo = userInfoService.selectUserInfoMore(dealerId, UserInfoConst.USER_TYPE_DEALER);
        model.addAttribute("dealerInfo", userInfo);
        return "brand/rebate_stock_count_byDate";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/stockCountByDate/data", method = RequestMethod.POST)
    public JsonMessage stockCountByDateData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String brandId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            stockAdjustDetail.setBrandId(brandId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailGroupByDatePage(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/stockCountByProductSku")
    public String stockCountByProductSkuIndex(String dealerId, Model model)
    {
        UserInfo userInfo = userInfoService.selectUserInfoMore(dealerId, UserInfoConst.USER_TYPE_DEALER);
        model.addAttribute("dealerInfo", userInfo);
        return "brand/rebate_stock_count_byProduct";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/stockCountByProductSku/data", method = RequestMethod.POST)
    public JsonMessage stockCountByProductSkuData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String brandId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            stockAdjustDetail.setBrandId(brandId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailGroupByProductPage(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /* ========================================= 返点财务帐 库存详情 end ================================================ */
    /**
     * 返点财务帐 交易平台统计财务总账
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/priceSum")
    public String priceSumIndex()
    {
        return "brand/rebate_financial_sum";
    }
    
    @RequestMapping(value = "/priceSum/data", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage priceSumData(Pagination page, PointSaleSum pointSaleSum) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        PaginateResult paginateResult;
        Map<String, Object> sumMap = Maps.newHashMap();
        try
        {
            pointSaleSum.setBrandId(brandId);
            paginateResult = pointSaleSumService.countDealersPointFinancial(page, pointSaleSum, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
}
