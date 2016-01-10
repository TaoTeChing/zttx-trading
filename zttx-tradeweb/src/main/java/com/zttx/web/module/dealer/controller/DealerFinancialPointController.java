package com.zttx.web.module.dealer.controller;

import com.google.common.collect.Maps;
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
import com.zttx.web.module.common.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>File:DealerFinancialPointController</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/11/23 10:01</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/dealer/dealerFinancialPoint")
public class DealerFinancialPointController extends GenericController
{
    @Autowired
    private PointSaleSumService      pointSaleSumService;
    
    @Autowired
    private UserInfoService          userInfoService;
    
    @Autowired
    private PointSaleDetailService   pointSaleDetailService;
    
    @Autowired
    private StockAdjustDetailService stockAdjustDetailService;
    
    @Autowired
    private PayApiService            payApiService;
    
    /* ========================================= 返点财务帐付款 [@author易永耀] begin================================================ */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/payment")
    public JsonMessage pay(@RequestParam("brandIds") List<String> brandIds, @RequestParam("payWord") String payPassWord) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        String dealerId = userPrincipal.getRefrenceId();
        String dealerName = userPrincipal.getUserName();
        pointSaleSumService.executePayPoint(brandIds, dealerId, payPassWord, dealerName);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /* ========================================= 返点财务帐付款 end ================================================ */
    /* ========================================= 返点财务帐 销售详情 begin ================================================ */
    /**
     * 返点财务帐 erp门店销售明细
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/saleDetail")
    public String saleDetailIndex()
    {
        return "dealer/rebate_financial_dayDetail";
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/saleDetail/data", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage saleDetailData(Pagination page, PointSaleDetail pointSaleDetail)
    {
        String dealerId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            pointSaleDetail.setDealerId(dealerId);
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
     * @param brandId
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/saleSum")
    public String saleSumIndex(String brandId, Model model)
    {
        UserInfo userInfo = userInfoService.selectUserInfoMore(brandId, UserInfoConst.USER_TYPE_BRAND);
        model.addAttribute("brandInfo", userInfo);
        return "dealer/rebate_financial_daySum";
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/saleSum/data", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage saleSumData(Pagination page, PointSaleSum pointSaleSum)
    {
        String dealerId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            pointSaleSum.setDealerId(dealerId);
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
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountBySkuDetail")
    public String stockCountBySkuDetailIndex(String brandId, String productSkuId, Model model) throws BusinessException
    {
        StockAdjustDetail stockAdjustDetail = new StockAdjustDetail();
        stockAdjustDetail.setDealerId(OnLineUserUtils.getCurrentDealer().getRefrenceId());
        stockAdjustDetail.setBrandId(brandId);
        stockAdjustDetail.setProductSkuId(productSkuId);
        Map<String, Object> headMap = stockAdjustDetailService.selectHeadData(stockAdjustDetail);
        model.addAttribute("headMap", headMap);
        return "dealer/rebate_stock_count_bySku_detail";
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountBySkuDetail/data")
    @ResponseBody
    public JsonMessage stockCountBySkuDetailData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String dealerId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            stockAdjustDetail.setDealerId(dealerId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailBySku(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountByDayDetail")
    public String stockCountByDayDetailIndex()
    {
        return "dealer/rebate_stock_count_byDate_detail";
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountByDayDetail/data")
    @ResponseBody
    public JsonMessage stockCountByDayDetailData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String dealerId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            stockAdjustDetail.setDealerId(dealerId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailByTimeAndSource(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountByDate")
    public String stockCountByDateIndex(String brandId, Model model)
    {
        UserInfo userInfo = userInfoService.selectUserInfoMore(brandId, UserInfoConst.USER_TYPE_BRAND);
        model.addAttribute("brandInfo", userInfo);
        return "dealer/rebate_stock_count_byDate";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountByDate/data", method = RequestMethod.POST)
    public JsonMessage stockCountByDateData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String dealerId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            stockAdjustDetail.setDealerId(dealerId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailGroupByDatePage(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountByProductSku")
    public String stockCountByProductSkuIndex(String brandId, Model model)
    {
        UserInfo userInfo = userInfoService.selectUserInfoMore(brandId, UserInfoConst.USER_TYPE_BRAND);
        model.addAttribute("brandInfo", userInfo);
        return "dealer/rebate_stock_count_byProduct";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/stockCountByProductSku/data", method = RequestMethod.POST)
    public JsonMessage stockCountByProductSkuData(Pagination page, StockAdjustDetail stockAdjustDetail)
    {
        String dealerId;
        Map<String, Object> sumMap = Maps.newHashMap();
        PaginateResult paginateResult;
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            stockAdjustDetail.setDealerId(dealerId);
            paginateResult = stockAdjustDetailService.selectStockAdjustDetailGroupByProductPage(page, stockAdjustDetail, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    /* ========================================= 返点财务帐 库存详情 end ================================================ */
   @RequestMapping("/payIng")
   @ResponseBody
   @RequiresPermissions(value = "dealer:center")
   public JsonMessage payIngpriceSumData(String[] brandIds) throws BusinessException {
       String dealerId  = OnLineUserUtils.getCurrentDealer().getRefrenceId();
       Map<String,Object> map = null;
       map = pointSaleSumService.selectPayIng(dealerId,brandIds);
       return this.getJsonMessage(CommonConst.SUCCESS,map);
   }




    /**
     * 返点财务帐 交易平台统计财务总账
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/priceSum")
    public String priceSumIndex()
    {
        return "dealer/rebate_financial_sum";
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/priceSum/data", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage priceSumData(Pagination page, PointSaleSum pointSaleSum) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        PaginateResult paginateResult;
        Map<String, Object> sumMap = Maps.newHashMap();
        try
        {
            pointSaleSum.setDealerId(dealerId);
            paginateResult = pointSaleSumService.countDealersPointFinancial(page, pointSaleSum, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
}
