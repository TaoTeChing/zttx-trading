package com.zttx.web.module.brand.controller;

import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.UserInfoConst;
import com.zttx.web.module.common.entity.Adjustments;
import com.zttx.web.module.common.entity.CreditToPoint;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.AdjustmentsService;
import com.zttx.web.module.common.service.CreditToPointService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerClearing;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.service.DealerClearingService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.CalendarUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * <p>File:BrandFinancialController</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/9/8 9:14</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandFinancial")
public class BrandFinancialController extends GenericController
{
    @Autowired
    private DealerOrderService    dealerOrderService;
    
    @Autowired
    private DealerClearingService dealerClearingService;
    
    @Autowired
    private UserInfoService       userInfoService;
    
    @Autowired
    private AdjustmentsService    adjustmentsService;
    
    @Autowired
    private CreditToPointService  creditToPointService;
    
    /**财务总账单*/
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/financial")
    public String financialIndex(Boolean isClient) throws BusinessException
    {
        if (null == isClient)
        {
            isClient = false;
        }
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        return "brand/dealer_financial";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/financial/data")
    public JsonMessage financialData(DealerOrder dealerOrder, Pagination pagination) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        dealerOrder.setBrandId(brandId);
        Map<String, Object> resultMap = dealerOrderService.getDealerOrderReportMap(pagination, dealerOrder);
        PaginateResult paginateResult = (PaginateResult) resultMap.get("recordPageResult");
        Map<String, Object> sumResult = (Map<String, Object>) resultMap.get("recordSum");
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
        jsonMessage.setObject(sumResult);
        return jsonMessage;
    }
    
    /**财务帐明细*/
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/financial/detail")
    public String financialDeatailIndex(String dealerId, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        UserInfo userInfo = userInfoService.selectUserInfoMore(dealerId, UserInfoConst.USER_TYPE_DEALER);
        Map<String, Object> tradeTypeList = dealerOrderService.selectTradeTypeList(dealerId, brandId); // 交易类型（采购、退款、调价、当期欠付）
        model.addAttribute("dealerInfo", userInfo);
        model.addAttribute("tradeType", tradeTypeList);
        return "brand/general_financial_detail";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/financial/detail/data")
    public JsonMessage financialDetailData(DealerOrder dealerOrder, Pagination pagination) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        dealerOrder.setBrandId(brandId);
        Map<String, Object> resultMap = dealerOrderService.getDealerOrderReportDetailMap(pagination, dealerOrder);
        Map<String, Object> sumResult = (Map<String, Object>) resultMap.get("sumResult");
        Map<String, Object> map = Maps.newHashMap();
        map.put("sumResult", sumResult);
        PaginateResult<Map<String, Object>> result = (PaginateResult<Map<String, Object>>) resultMap.get("result");
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, result);
        jsonMessage.setObject(map);
        return jsonMessage;
    }
    
    /* ========================================= 当期财务帐 [@author易永耀] begin================================================ */
    /**
     * 当期财务帐明细 index
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/currentFinancial/detail")
    public String currentFinancialDetailIndex(String dealerId, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        UserInfo userInfo = userInfoService.selectUserInfoMore(dealerId, UserInfoConst.USER_TYPE_DEALER);
        model.addAttribute("dealerInfo", userInfo);
        return "brand/factory_shop_current_financial";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/currentFinancial/detail/data", method = RequestMethod.POST)
    public JsonMessage currentFinancialDetailData(DealerClearing dealerClearing, Pagination pagination) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        dealerClearing.setBrandId(brandId);
        Long startTime = CalendarUtils.getLongFromTime(dealerClearing.getStartTime(), "yyyy-MM-dd");
        dealerClearing.setStartTime(startTime.toString());
        if (StringUtils.isNotBlank(dealerClearing.getEndTime()))
        {
            Long endTime = DateTimeFormat.forPattern("yyyy-MM-dd").parseDateTime(dealerClearing.getEndTime()).plusDays(1).getMillis();
            dealerClearing.setEndTime(endTime.toString());
        }
        Map<String, Object> result = dealerClearingService.selectDealerClearing(pagination, dealerClearing);
        PaginateResult<DealerClearing> paginateResult = (PaginateResult<DealerClearing>) result.get("recordResult");
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
        jsonMessage.setObject(result.get("recordSumResult"));
        return jsonMessage;
    }
    
    /* ========================================= 当期财务帐 end ================================================ */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/adjustPrice")
    public String getBrandAdjustment(String adjustId, String dealerId, Model model)
    {
        model.addAttribute("brandAdjustId", adjustId);
        model.addAttribute("dealerId", dealerId);
        return "dealer/financial_dealer_adjust_detail";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/adjustPrice/data")
    public JsonMessage getBrandAdjustmentsDetail(Pagination page, Adjustments adjustModel)
    {
        String brandId;
        PaginateResult<Map<String, Object>> result;
        Map<String, Object> sumMap = Maps.newHashMap(); // 统计条件查询所得结果，与分页无关
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            adjustModel.setBrandId(brandId);
            result = adjustmentsService.getBrandAdjustmentsList(sumMap, adjustModel, page);
            return this.getJsonMessage(CommonConst.SUCCESS, result, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/creditToPoint")
    public String getCreditToPoint(String dealerId, Model model)
    {
        model.addAttribute("dealerId", dealerId);
        return "brand/financial_credit_point_detail";
    }
    
    @RequestMapping(value = "/creditToPoint/data")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage getCreditToPointDetail(Pagination page, CreditToPoint creditToPoint)
    {
        String brandId;
        PaginateResult<Map<String, Object>> paginateResult;
        Map<String, Object> sumMap = Maps.newHashMap();
        try
        {
            brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            creditToPoint.setBrandId(brandId);
            paginateResult = creditToPointService.selectCreditToPointPage(page, creditToPoint, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
}
