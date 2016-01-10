package com.zttx.web.module.dealer.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.erp.module.statement.model.SalesStateMentModel;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.SellOrderServiceDubboConsumer;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandContactService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.Adjustments;
import com.zttx.web.module.common.entity.CreditToPoint;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.model.ErpSellDetailModel;
import com.zttx.web.module.common.model.TransferModel;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerClearing;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerRefReplyModel;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.NetworkUtils;

/**
 * <p>File:DealerFinancialController</p>
 * <p>Title: </p>
 * <p>Description: 经销商-品牌商财务帐</p>
 * <p>Copyright: Copyright (c)2015/9/8 9:12</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerFinancial")
public class DealerFinancialController extends GenericController
{
    @Autowired
    private SellOrderServiceDubboConsumer sellOrderServiceDubboConsumer;
    
    @Autowired
    private DealerOrderService            dealerOrderService;
    
    @Autowired
    private UserInfoService               userInfoService;
    
    @Autowired
    private DealerClearingService         dealerClearingService;
    
    @Autowired
    private PayApiService                 payApiService;
    
    @Autowired
    private DealerRefundService           dealerRefundService;
    
    @Autowired
    private BrandInfoService              brandInfoService;
    
    @Autowired
    private DealerRefAttachtService       dealerRefAttachtService;
    
    @Autowired
    private AdjustmentsService            adjustmentsService;
    
    @Autowired
    private BrandContactService           brandContactService;
    
    @Autowired
    private DealerRefReplyService         dealerRefReplyService;
    
    @Autowired
    private DataDictValueService          dataDictValueService;
    
    @Autowired
    private CreditToPointService          creditToPointService;
    
    /**财务总账单*/
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/financial")
    public String financialIndex() throws BusinessException
    {
        OnLineUserUtils.getCurrentDealer().getRefrenceId();
        return "dealer/financial_brand_normal";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/financial/data")
    public JsonMessage financialData(DealerOrder dealerOrder, Pagination pagination) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerOrder.setDealerId(dealerId);
        Map<String, Object> resultMap = dealerOrderService.getDealerOrderReportMap(pagination, dealerOrder);
        PaginateResult paginateResult = (PaginateResult) resultMap.get("recordPageResult");
        Map<String, Object> sumResult = (Map<String, Object>) resultMap.get("recordSum");
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
        jsonMessage.setObject(sumResult);
        return jsonMessage;
    }
    
    /**财务帐明细*/
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/financial/detail")
    public String financialDeatailIndex(String brandId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo userInfo = userInfoService.selectUserInfoMore(brandId, UserInfoConst.USER_TYPE_BRAND);
        Map<String, Object> tradeTypeList = dealerOrderService.selectTradeTypeList(dealerId, brandId); // 交易类型（采购、退款、调价、当期欠付）
        model.addAttribute("brandInfo", userInfo);
        model.addAttribute("tradeType", tradeTypeList);
        return "dealer/financial_brand_detail_factory";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/financial/detail/data")
    public JsonMessage financialDetailData(DealerOrder dealerOrder, Pagination pagination) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerOrder.setDealerId(dealerId);
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
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/currentFinancial/detail")
    public String currentFinancialDetailIndex(String brandId, Model model) throws BusinessException
    {
        UserInfo userInfo = userInfoService.selectUserInfoMore(brandId, UserInfoConst.USER_TYPE_BRAND);
        if (null == userInfo) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); // brandConcat
        }
        model.addAttribute("brandInfo", userInfo);
        return "dealer/financial_brand_current_payment";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/currentFinancial/detail/data", method = RequestMethod.POST)
    public JsonMessage currentFinancialDetailData(DealerClearing dealerClearing, Pagination pagination) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerClearing.setDealerId(dealerId);
        Map<String, Object> result = dealerClearingService.selectDealerClearing(pagination, dealerClearing);
        PaginateResult<DealerClearing> paginateResult = (PaginateResult<DealerClearing>) result.get("recordResult");
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
        jsonMessage.setObject(result.get("recordSumResult"));
        return jsonMessage;
    }
    
    /* ========================================= 当期财务帐 end ================================================ */
    /* ========================================= 查看销售明细 [@author易永耀] begin================================ */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/saleDetail")
    public String saleDetailIndex()
    {
        return "dealer/financial_brand_current_trade";
    }
    
    /**数据来源erp*/
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/saleDetail/data")
    @ResponseBody
    public JsonMessage saleDetailDataErp(HttpServletRequest request, ErpSellDetailModel erpSellDetailModel, Pagination pagination) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        erpSellDetailModel.setZttxDealerId(dealerId);
        erpSellDetailModel.setSearchTime(erpSellDetailModel.getSearchDate().getTime());
        if (null == erpSellDetailModel || StringUtils.isBlank(erpSellDetailModel.getZttxDealerId()) || StringUtils.isBlank(erpSellDetailModel.getSupplierId())
                || null == erpSellDetailModel.getSearchTime()) throw new BusinessException(CommonConst.PARAM_NULL);
        SalesStateMentModel searchModel = new SalesStateMentModel();
        searchModel.setZttxDealerId(erpSellDetailModel.getZttxDealerId());
        searchModel.setSupplierId(erpSellDetailModel.getSupplierId());
        searchModel.setSearchTime(erpSellDetailModel.getSearchTime());
        // PaginateResult<ErpSellDetail> result = erpClient.getSellDetail(erpSellDetailModel, pagination);
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS, sellOrderServiceDubboConsumer.getSellDetail(pagination, searchModel));
        return jsonMessage;
    }
    
    /* ========================================= 查看销售明细 end ================================================ */
    /* ========================================= 当期财务帐付款 [@author易永耀] begin================================================ */
    /**
     * 当期欠付款 付款后直接变动授信金额中已经使用的授信额度 creditCurrent  回调函数修改 creditCurrent 数值 和 dealerClearing 状态
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/payment")
    public JsonMessage pay(@RequestParam("brandIds") List<String> brandIds, @RequestParam("payWord") String payPassWord, Model model) throws BusinessException
    {
        if (!brandIds.isEmpty() && StringUtils.isNotBlank(payPassWord))
        {
            UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
            String dealerId = userPrincipal.getRefrenceId();
            String userName = userPrincipal.getUserName();
            List<TransferModel> transferModelList = Lists.newArrayList();
            for (String brandId : brandIds)
            {
                /*
                 * //查询品牌商与经销商的未结算当期款
                 * Map<String,Object> clearingMap = dealerClearingService.selectDealerClearing(dealerId,brandId);
                 */
                DealerClearing dealerClearing = new DealerClearing();
                dealerClearing.setDealerId(dealerId);
                dealerClearing.setBrandId(brandId);
                Map<String, Object> clearingMap = dealerClearingService.selectDealerClearing(null, dealerClearing);
                Map<String, Object> recordSumResult = MapUtils.getMap(clearingMap, "recordSumResult"); // allDebtPrice
                PaginateResult recordResult = (PaginateResult) clearingMap.get("recordResult");
                BigDecimal allDebtPrice = (BigDecimal) recordSumResult.get("allDebtPrice");
                List<DealerClearing> dealerClearingList = recordResult.getList();
                if (clearingMap != null && !clearingMap.isEmpty() && allDebtPrice.compareTo(BigDecimal.ZERO) == 1 && null != dealerClearingList
                        && !dealerClearingList.isEmpty())
                {
                    BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
                    Map<String, Object> mapTime = dealerClearingService.selectDealerClearingPayTime(dealerClearing);
                    String startClearingTime = CalendarUtils.getStringTime(MapUtils.getLong(mapTime, "startTime"), "yyyy-MM-dd");
                    String endClearingTime = CalendarUtils.getStringTime(MapUtils.getLong(mapTime, "endTime"), "yyyy-MM-dd");
                    TransferModel transferModel = new TransferModel();
                    transferModel.setToUserId(brandId);
                    transferModel.setTitle("支付(从" + startClearingTime + "到" + endClearingTime + " 共" + MapUtils.getInteger(mapTime, "count") + "笔)当期应付款");
                    transferModel.setAmount(allDebtPrice);
                    transferModel.setPayPwd(payPassWord);
                    transferModel.setFromUserName(userName);
                    transferModel.setToUserName(brandInfo.getComName());
                    transferModelList.add(transferModel);
                }
            }
            if (!transferModelList.isEmpty())
            {
                try
                {
                    payApiService.executeTransfer(dealerId, transferModelList); // 转账后，会修改当期应付款支付状态为已付，同时修改实时用掉的授信额度,生成支付记录
                }
                catch (Exception e)
                {
                    throw new BusinessException(CommonConst.FAIL.getCode(), e.getLocalizedMessage());
                }
            }
            else
            {
                return this.getJsonMessage(CommonConst.FAIL, "无需要支付的金额");
            }
            return this.getJsonMessage(CommonConst.SUCCESS);
        }
        return this.getJsonMessage(CommonConst.FAIL);
    }
    
    /* ========================================= 当期财务帐付款 end ================================================ */
    /* ========================================= 退货退款 [@author易永耀] begin================================================ */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund")
    public String refundIndex(String brandId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        Map<String, Object> refundTypeList = dealerRefundService.selectRefundTypeList(dealerId, brandId);
        model.addAttribute("brandId", brandId);
        model.addAttribute("refundTypeList", refundTypeList);
        return "dealer/financial_brand_factory_refund";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/data")
    public JsonMessage refundData(DealerRefund dealerRefund, Pagination page) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerRefund.setDealerId(dealerId);
        PaginateResult<DealerRefund> result = dealerRefundService.selectDealerRefund(dealerRefund, page);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/apply")
    public String applyFactoryRefund(String brandId, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        UserInfo userInfo = userInfoService.selectUserInfoMore(brandId, UserInfoConst.USER_TYPE_BRAND);
        model.addAttribute("userInfo", userInfo);
        // 可退金额 = 应付款-已完成退款金额(9.10)-正在处理中的退款总金额(1,2,3)-总当期欠付款；
        model.addAttribute("maxRefundAmount", getMaxRefundAmount(brandId, dealerId));
        return "dealer/financial_brand_factory_refund_apply";
    }
    
    /**
     * 可退金额获取
     *
     * @param brandId
     * @param dealerId
     * @return
     * @throws BusinessException
     */
    private double getMaxRefundAmount(String brandId, String dealerId) throws BusinessException
    {
        Map<String, Object> resultMap = dealerOrderService.getDealerReportInfo(brandId, dealerId);
        BigDecimal totalProductAmount = (BigDecimal) resultMap.get("totalProductAmount"); // 应付款，包含运费
        BigDecimal totalHasRefundAmount = (BigDecimal) resultMap.get("totalHasRefundAmount"); // 已完成的退款金额
        BigDecimal totalRefundIngAmount = (BigDecimal) resultMap.get("totalRefundIngAmount"); // 进行中的退款金额
        BigDecimal totalClearingAmount = (BigDecimal) resultMap.get("totalClearingAmount"); // 当期欠付款总款(未付)
        return Math.max(0, totalProductAmount.subtract(totalHasRefundAmount).subtract(totalRefundIngAmount).subtract(totalClearingAmount).doubleValue());
    }
    
    /**
     * 提交退款申请
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/factory/refund/submit")
    public JsonMessage apply(DealerRefund dealerRefund, String[] attachtNames, String brandId)
    {
        JsonMessage jsonMessage = super.getJsonMessage(CommonConst.SUCCESS);
        try
        {
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
            if (null == brandInfo) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); }
            if (beanValidator(jsonMessage, dealerRefund))
            {
                List<DealerRefAttacht> dealerRefAttachts = null;
                if (null != attachtNames && attachtNames.length > 0)
                {
                    String domainName = NetworkUtils.getDoMainName();
                    attachtNames = FileClientUtil.moveAndDeleteFile(ImageConst.DEALER_IMG_PATH, attachtNames, "");
                    dealerRefAttachts = dealerRefAttachtService.newListBydomainNames(attachtNames, domainName);
                }
                String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
                double maxRefundAmount = getMaxRefundAmount(brandId, dealerId);
                if (maxRefundAmount < dealerRefund.getRefundAmount().doubleValue()) { throw new BusinessException("退款金额不能大于可退金额"); }
                dealerRefundService.saveFactoryDealerRefund(dealerRefund, dealerRefAttachts, dealerId, brandId);
            }
        }
        catch (BusinessException e)
        {
            jsonMessage.setCode(e.getErrorCode().getCode());
            jsonMessage.setMessage(e.getErrorCode().getMessage());
        }
        return jsonMessage;
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/factory/refund/modify")
    public String modifyRefundApply(DealerRefund dealerRefund, ModelMap map) throws BusinessException
    {
        map.put("dealerRefund", dealerRefund);
        DealerRefund _dealerRefund = dealerRefundService.selectByPrimaryKey(dealerRefund.getRefrenceId());
        if (_dealerRefund == null) { throw new BusinessException(DealerConst.NOT_REFUND); }
        if (DealerConstant.DealerRefund.NOT_REFUND == _dealerRefund.getRefundState() || DealerConstant.DealerRefund.NOT_RETURN == _dealerRefund.getRefundState())
        {
            // 显示操作记录详情
            List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(_dealerRefund.getRefrenceId());
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            UserInfo userInfo = userInfoService.selectUserInfoMore(_dealerRefund.getBrandId(), UserInfoConst.USER_TYPE_BRAND);
            map.addAttribute("userInfo", userInfo);
            // 可退金额 = 应付款-已完成退款金额(9.10)-正在处理中的退款总金额(1,2,3)-总当期欠付款；
            map.addAttribute("maxRefundAmount", getMaxRefundAmount(_dealerRefund.getBrandId(), dealerId));
            /**重新上传凭证 因此不需要查*/
            // List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(_dealerRefund.getRefrenceId());
            // map.put("dealerRefAttachts",dealerRefAttachts);
            map.put("drrList", drrList);
            map.put("dealerRefund", _dealerRefund);
        }
        else
        {
            throw new BusinessException(DealerConst.DEALER_AUDIT_STATE_ERROR);
        }
        return "dealer/financial_brand_factory_refund_apply";
    }
    
    /**
     * 退款详情
     * @param refrenceId
     * @param map
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/factory/refund/details")
    public String viewRefundInfo(String refrenceId, ModelMap map) throws BusinessException
    {
        // 准备回显数据
        DealerRefund dealerRefund = dealerRefundService.selectByPrimaryKey(refrenceId);
        map.put("dealerRefund", dealerRefund);
        if (dealerRefund == null) { return "dealer/financial_brand_factory_refund_details"; }
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(dealerRefund.getBrandId());
        if (null == brandInfo) { throw new BusinessException(CommonConst.BRAND_INFO_NULL); }
        BrandContact brandContact = brandContactService.findByBrandId(dealerRefund.getBrandId());
        map.addAttribute("brandInfo", brandInfo);
        map.addAttribute("brandContact", brandContact);
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        map.put("drrList", drrList);
        map.put("endTime", dealerRefund.getNextActTime());
        // 先判断是否有客服介入
        if (dealerRefund.getCusJoin() != null && dealerRefund.getSerProStatus() != null && dealerRefund.getCusJoin() == true && dealerRefund.getSerProStatus() == 2) // 3：纠纷处理完毕
        { return "dealer/financial_brand_factory_refund_cusjoin"; }
        DataDictValue ddv = dataDictValueService.findDictValue("RefundDate", "serProDay");
        if (null != ddv)
        {
            map.put("dictValue", ddv.getDictValue());
        }
        return "dealer/financial_brand_factory_refund_details";
    }
    
    /**
     * 经销商退货发货
     *
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/factory/refund/deliver", method = RequestMethod.POST)
    public String returnDeliver(DealerRefundModel dealerRefund) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerRefundService.updateDeliverFactory(dealerRefund, dealerId);
        return "redirect:/dealer/dealerFinancial/factory/refund/details?refrenceId=" + dealerRefund.getRefrenceId();
    }
    
    /**
     * 总账退款-客服介入
     * @param refrenceId
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/factory/refund/appserjoin")
    public String appserJoin(@RequestParam("refrenceId") String refrenceId) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerRefundService.appserjoinFactory(refrenceId, dealerId);
        return "redirect:/dealer/dealerFinancial/factory/refund/details?refrenceId=" + refrenceId;
    }
    
    /* ========================================= 退货退款 end ================================================ */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/factory/adjustPrice")
    public String getBrandAdjustment(String adjustId, String brandId, Model model)
    {
        model.addAttribute("brandAdjustId", adjustId);
        model.addAttribute("brandId", brandId);
        return "dealer/financial_brand_adjust_detail";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/factory/adjustPrice/data")
    public JsonMessage getBrandAdjustmentsDetail(Pagination page, Adjustments adjustModel)
    {
        String dealerId;
        PaginateResult<Map<String, Object>> result;
        Map<String, Object> sumMap = Maps.newHashMap(); // 统计条件查询所得结果，与分页无关
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            adjustModel.setDealerId(dealerId);
            result = adjustmentsService.getBrandAdjustmentsList(sumMap, adjustModel, page);
            return this.getJsonMessage(CommonConst.SUCCESS, result, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/creditToPoint")
    public String getCreditToPoint(String brandId, Model model)
    {
        model.addAttribute("brandId", brandId);
        return "dealer/financial_credit_point_detail";
    }
    
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/creditToPoint/data")
    @ResponseBody
    public JsonMessage getCreditToPointDetail(Pagination page, CreditToPoint creditToPoint)
    {
        String dealerId;
        PaginateResult<Map<String, Object>> paginateResult;
        Map<String, Object> sumMap = Maps.newHashMap();
        try
        {
            dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            creditToPoint.setDealerId(dealerId);
            paginateResult = creditToPointService.selectCreditToPointPage(page, creditToPoint, sumMap);
            return this.getJsonMessage(CommonConst.SUCCESS, paginateResult, sumMap);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
    }
}
