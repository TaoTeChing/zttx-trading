package com.zttx.web.module.brand.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandLogistics;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.model.SendGoodsModel;
import com.zttx.web.module.brand.service.BrandLogisticsService;
import com.zttx.web.module.brand.service.BrandMessageService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.model.DealerOrderActionModel;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.model.OrderModel;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌商订单控制器
 * Created by 李星 on 2015/8/13.
 */
@Controller
@RequestMapping(value = ApplicationConst.BRAND + "/order")
public class BrandOrderController extends GenericController
{
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired
    private DataDictValueService     dataDictValueService;
    
    @Autowired
    private DealerOrderService       dealerOrderService;
    
    @Autowired
    private DealerOrdersService      dealerOrdersService;
    
    @Autowired
    private DealerOrderActionService dealerOrderActionService;
    
    @Autowired
    private OrderPayRecordService    orderPayRecordService;
    
    @Autowired
    private DealerOrderPayService    dealerOrderPayService;
    
    @Autowired
    private BrandLogisticsService    brandLogisticsService;
    
    @Autowired
    private SmsTemplateService       smsTemplateService;
    
    @Autowired
    private SmsSendService           smsSendService;
    
    @Autowired
    private OrderPayService          orderPayService;
    
    @Autowired
    private OrderChangeLogService    orderChangeLogService;
    
    @Autowired
    private BrandMessageService      brandMessageService;
    
    @Autowired
    private RegionalService          regionalService;
    
    @Autowired
    private DealerJoinService        dealerJoinService;
    
    /**
     * 品牌商订单列表页
     * @param request
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/purorder")
    public String purorder(HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        List<BrandsInfoModel> infoList = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("infoList", infoList);
        model.addAttribute("orderStatus", dataDictValueService.findByDictCode(DataDictConstant.BRAND_ORDER_STATUS));
        return "brand/list_brand_order";
    }
    
    /**
     * 品牌商订单列表Ajax
     *
     * @param pagination
     * @param dealerOrder
     * @param request
     * @return
     * @author 施建波
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/purorder/data")
    public Object listInviteJson(Pagination pagination, DealerOrderModel dealerOrder, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        dealerOrder.setBrandId(brandId);
        PaginateResult<Map<String, Object>> result = dealerOrderService.getDealerOrderList(pagination, dealerOrder);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 品牌商订单管理页
     *
     * @param orderType
     * @param model
     * @return
     * @author 施建波
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/purorder/{orderType}")
    public String purorderType(@PathVariable("orderType") String orderType, HttpServletRequest request, Model model) throws BusinessException
    {
        model.addAttribute("orderType", orderType);
        return purorder(request, model);
    }
    
    /**
     * 订单详情
     *
     * @param request
     * @param refrenceId
     * @param model
     * @return {@link java.lang.String}
     * @author 李飞欧
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/view/{refrenceId}")
    public String view(HttpServletRequest request, @PathVariable String refrenceId, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrderModel dealerOrder = dealerOrderService.findEntityBy(refrenceId, brandId);
        List<DealerOrders> dealerOrders = Lists.newArrayList();
        List<DealerOrderAction> dealerOrderAction = Lists.newArrayList();
        if (null != dealerOrder.getRefrenceId() && null != dealerOrder.getBrandId() && null != dealerOrder.getOrderId())
        {
            dealerOrders = dealerOrdersService.findByOrderIdAndBrandId(dealerOrder.getRefrenceId(), dealerOrder.getBrandId());
            dealerOrderAction = dealerOrderActionService.findByOrderId(dealerOrder.getRefrenceId());
        }
        List<OrderModel> orderModels = dealerOrdersService.convertDealerOrdersToOrderModel(dealerOrders);
        // 订单付款记录
        List<OrderPayRecord> listPay = orderPayRecordService.listPayRecordBy(dealerOrder.getRefrenceId(), dealerOrder.getDealerId());
        if (null != listPay && !listPay.isEmpty())
        {
            model.addAttribute("listPay", listPay);
        }
        List<DealerOrderAction> shipRecordList = dealerOrderActionService.findByOrderId(dealerOrder.getRefrenceId());
        List<DealerOrderPay> orderPayList = Lists.newArrayList();
        if (null != dealerOrder.getDealerOrderType() && DealerConstant.DealerOrder.ORDER_TYPE_CREDIT == dealerOrder.getDealerOrderType().intValue())
        {
            orderPayList = dealerOrderPayService.findByOrderId(refrenceId);
        }
        Boolean isFactoryShop = false;
        if (StringUtils.isNotBlank(dealerOrder.getActivitiesNo()) && StringUtils.equals(BrandConstant.BrandActivityList.ACTIVITY_CODE_GCD, dealerOrder.getActivitiesNo()))
        {
            isFactoryShop = true;
        }
        model.addAttribute("dealerOrders", orderModels);
        model.addAttribute("dealer", dealerOrder);
        model.addAttribute("dealerOrderAction", dealerOrderAction);
        model.addAttribute("shipRecordList", shipRecordList);
        model.addAttribute("orderPayList", orderPayList);
        model.addAttribute("isFactoryShop", isFactoryShop);
        return "brand/orderDetails";
    }
    
    /**
     * 确定发货订单
     *
     * @param orderId 订单号
     * @param request HttpServletRequest
     * @return String
     * @author 罗盛平
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/sendGoods/{orderId}")
    public String sendGoods(@PathVariable Long orderId, HttpServletRequest request, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<DealerOrders> dealerOrders = null;
        if (null == orderId) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        DealerOrderModel order = dealerOrderService.getByOrderIdAndBrandId(orderId, brandId);
        if (null == order) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        if (null != order.getRefrenceId() && null != order.getBrandId() && null != order.getOrderId())
            dealerOrders = dealerOrdersService.findByOrderIdAndBrandId(order.getRefrenceId(), order.getBrandId());
        List<OrderModel> orderModels = dealerOrdersService.convertDealerOrdersToOrderModel(dealerOrders);
        // 订单付款记录
        List<OrderPayRecord> listPay = orderPayRecordService.listPayRecordBy(order.getDealerId(), order.getRefrenceId());
        List<BrandLogistics> listBrandLogistics = brandLogisticsService.listByBrandId(brandId);
        List<DealerOrderAction> shipRecordList = dealerOrderActionService.findByOrderId(order.getRefrenceId());
        model.addAttribute("shipRecordList", shipRecordList);
        model.addAttribute("orderModels", orderModels);
        model.addAttribute("order", order);
        model.addAttribute("listPay", listPay);
        model.addAttribute("brandLogisticsList", listBrandLogistics);
        return "/brand/sendGoods";
    }
    
    /**
     * 发货
     *
     * @param sendModel SendGoodsModel
     * @param request   HttpServletRequest
     * @param model     Model
     * @return String
     * @author 罗盛平
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/confirmSend", method = RequestMethod.POST)
    public String confirmSend(@ModelAttribute SendGoodsModel sendModel, HttpServletRequest request, Model model, Boolean sendAll) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        sendModel.setBrandId(brandId);
        if (null != sendModel)
        {
            long orderId = sendModel.getOrderId();
            DealerOrder order = dealerOrderService.getByOrderIdAndBrandId(orderId, brandId);
            if (order == null)
            {// 当前订单 没权限操作
                model.addAttribute("status", DealerConst.POWER_ERROR.message);
                return "/brand/sendGoods";
            }
            if (order.getOrderStatus() != DealerConstant.DealerOrder.ORDER_STATUS_TO_DELIVER
                    && order.getOrderStatus() != DealerConstant.DealerOrder.ORDER_STATUS_PART_DELIVERED) { throw new BusinessException("订单不是处于待发货状态"); }
            try
            {
                dealerOrderService.sendGoods(sendModel, sendAll);
                // 发送发货短信提醒
                String sendText = "部分发货";
                if (sendAll) sendText = "全部发货";
                SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsConst.SMS_BRAND_SEND_GOODS);
                if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
                {
                    String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()), sendModel.getBrandName(),
                            sendModel.getOrderId(), sendText, sendModel.getLogisticName(), sendModel.getShipNumber());
                    smsSendService.sendSmsToUser(order.getDealerId(), smsContent);
                }
                if (StringUtils.isNotBlank(sendModel.getCustomLogisName()) && !brandLogisticsService.isExits(brandId, sendModel.getCustomLogisName()))
                {
                    BrandLogistics brandLogistics = new BrandLogistics();
                    brandLogistics.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandLogistics.setCreateTime(CalendarUtils.getCurrentLong());
                    brandLogistics.setLogisticType(1);
                    brandLogistics.setBrandId(brandId);
                    brandLogistics.setLogisticName(sendModel.getCustomLogisName());
                    brandLogisticsService.insert(brandLogistics);
                }
            }
            catch (Exception e)
            {
                model.addAttribute("status", e.getMessage());
                return "/brand/sendGoods";
            }
        }
        return "redirect:/brand/order/purorder";
    }
    
    /**
     * 关闭发货
     * 1、订单关闭发货后，那么需将未发货产品的货款减掉；
     * 2、未发货产品货款统计：
     * （1）无折扣：未发货产品货款=单价*（SKU产品数量-已发货数量）；
     * （2）有折扣：未发货产品货款=折扣单价*（SKU产品数量-已发货产品数量）；
     * （3）有优惠：未发货产品货款=优惠后金额*未发货数量/SKU产品数量，保留至小数点后两位；
     * 3、关闭发货的订单，仍需终端确认收货；
     * @param orderId
     * @param request
     * @param model
     * @return
     * @author 李星
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/closeSendGoods", method = RequestMethod.POST)
    public JsonMessage closeSendGoods(Long orderId, String reason, HttpServletRequest request, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrderModel dealerOrder = dealerOrderService.getByOrderIdAndBrandId(orderId, brandId);
        if (dealerOrder == null)
        {
            return super.getJsonMessage(CommonConst.FAIL, "当前订单没有权限操作");
        }
        else if (dealerOrder.getOrderStatus() != DealerConstant.DealerOrder.ORDER_STATUS_PART_DELIVERED) { return super.getJsonMessage(CommonConst.FAIL, "订单不是处于可关闭状态"); }
        
        if(null!=dealerOrder.getDealerOrderType()&&dealerOrder.getDealerOrderType()==DealerConstant.DealerOrder.ORDER_TYPE_POINT){
        	List<DealerOrders> dealerOrdersList = dealerOrdersService.findByOrderIdAndBrandId(dealerOrder.getRefrenceId(), brandId);
            BigDecimal noSendGoodsMoney = new BigDecimal(0); // 未发货产品货款
            for (DealerOrders dos : dealerOrdersList)
            {
                BigDecimal num4Send = new BigDecimal(dos.getQuantity() - dos.getShipCount()); // 未发货数量
                BigDecimal noSendGoodsPrice = new BigDecimal(0);
                noSendGoodsPrice = dos.getPrice().multiply(new BigDecimal(1).subtract(dos.getPointPercent())).multiply(num4Send);
                noSendGoodsMoney = noSendGoodsMoney.add(noSendGoodsPrice);
            }
            dealerOrder.setNoSendGoodsAmount(noSendGoodsMoney);
            dealerOrder.setProductPrice(dealerOrder.getProductPrice().subtract(noSendGoodsMoney));
        	dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_COMPLETED);
        }else{
        	List<DealerOrders> dealerOrdersList = dealerOrdersService.findByOrderIdAndBrandId(dealerOrder.getRefrenceId(), brandId);
            BigDecimal noSendGoodsMoney = new BigDecimal(0); // 未发货产品货款
            for (DealerOrders dos : dealerOrdersList)
            {
                BigDecimal totalNum = new BigDecimal(dos.getQuantity());
                BigDecimal num4Send = new BigDecimal(dos.getQuantity() - dos.getShipCount()); // 未发货数量
                // 有优惠
                if (null != dealerOrder.getAdjustPrice() && dealerOrder.getAdjustPrice().compareTo(new BigDecimal(0)) != 0)
                {
                    if (null != dos.getAdjustPrice() && dos.getAdjustPrice().compareTo(new BigDecimal(0)) != 0)
                    {
                        BigDecimal noSendMoney = dos.getAdjustPrice().multiply(num4Send.divide(totalNum, 2)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        noSendGoodsMoney = noSendGoodsMoney.add(noSendMoney);
                    }
                }
                else
                {
                    if (null != dos.getOldPrice()) // 授信订单有调价
                    {
                        BigDecimal noSendMoney = dos.getPrice().multiply(num4Send);
                        noSendGoodsMoney = noSendGoodsMoney.add(noSendMoney);
                    }
                    else
                    {
                        // 有折扣
                        BigDecimal discountRatio = dos.getDiscount();
                        if (null != discountRatio && discountRatio.compareTo(new BigDecimal(0)) != 0)
                        {
                            noSendGoodsMoney = noSendGoodsMoney.add(dos.getPrice().multiply(discountRatio).multiply(num4Send));
                        }
                        else
                        {
                            noSendGoodsMoney = noSendGoodsMoney.add(dos.getPrice().multiply(num4Send));
                        }
                    }
                }
            }
            dealerOrder.setNoSendGoodsAmount(noSendGoodsMoney);
            dealerOrder.setProductPrice(dealerOrder.getProductPrice().subtract(noSendGoodsMoney));
        	dealerOrder.setOrderStatus(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
        }
        dealerOrder.setUpdateTime(CalendarUtils.getCurrentLong());
        // 设置下一个操作时间
        dealerOrderService.setOutActTime(dealerOrder, null, null);
        dealerOrderService.updateByPrimaryKey(dealerOrder);
        buildDealerOrderAction(dealerOrder, reason);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 创建订单操作历史记录
     * @param order
     */
    private void buildDealerOrderAction(DealerOrderModel order, String reason)
    {
        // 新增订单操作记录
        DealerOrderAction dealerOrderAction = new DealerOrderAction();
        dealerOrderAction.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerOrderAction.setActCode(DealerConstant.DealerOrderAction.CLOSESENDGOODS);
        dealerOrderAction.setActName(DealerConstant.DealerOrderAction.CLOSESENDGOODS_NAME);
        String content = reason;
        dealerOrderAction.setActContent(content);
        dealerOrderAction.setCreateTime(CalendarUtils.getCurrentLong());
        dealerOrderAction.setOrderId(order.getRefrenceId());
        dealerOrderAction.setUserId(order.getBrandId());
        dealerOrderAction.setUserName(order.getBrandName());
        dealerOrderActionService.insert(dealerOrderAction);
    }
    
    /**
     * 关闭交易
     *
     * @param request
     * @param dealerOrderAction
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @RequestMapping(value = "/closeOrder")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage closeOrder(HttpServletRequest request, DealerOrderActionModel dealerOrderAction)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            dealerOrderAction.setUserId(brandId);
            dealerOrderActionService.saveCloseOrder(dealerOrderAction, "brand");
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 修改价格
     *
     * @param dealerOrder
     * @param request
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @RequestMapping(value = "/modPrice")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage modPrice(DealerOrderModel dealerOrder, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        dealerOrder.setBrandId(brandId);
        try
        {
            // 修改价格后将关闭支付平台的支付订单
            orderPayService.executeClosePay(dealerOrder.getRefrenceId());
            DealerOrderModel orderInfo = dealerOrderService.updatePrice(dealerOrder);
            Map<String, String> paramMap = Maps.newHashMap();
            paramMap.put("orderId", orderInfo.getOrderId().toString());
            String[] msg = MessageConst.UPDATE_PRICE.execute(paramMap);
            brandMessageService.sendDealerOrderMessage(orderInfo.getBrandId(), orderInfo.getDealerId(), msg[0], msg[1]);
            // 加入修改金额日志信息
            OrderChangeLog orderChangeLog = new OrderChangeLog();
            orderChangeLog.setCreateTime(new Date().getTime());
            orderChangeLog.setCreateIp(IPUtil.getIpAddr(request));
            orderChangeLog.setOrderId(orderInfo.getOrderId());
            orderChangeLog.setAccount(brandId);
            orderChangeLog.setContent("修改订单金额：原订单金额" + orderInfo.getOldBalance() + "元，修改后的订单金额" + orderInfo.getNewBalance() + "元。");
            orderChangeLogService.insert(orderChangeLog);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 修改运费
     *
     * @param request
     * @param adjustFreight
     * @param refrenceId
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @RequestMapping(value = "/modFare")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage modFare(HttpServletRequest request, @RequestParam BigDecimal adjustFreight, @RequestParam String refrenceId, String brandsId)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            // 修改价格后将关闭支付平台的支付订单
            orderPayService.executeClosePay(refrenceId);
            DealerOrderModel dealerOrder = dealerOrderService.updateFare(adjustFreight, brandId, refrenceId, brandsId);
            if (dealerOrder.getAdjustFreight().compareTo(new BigDecimal("0")) >= 0)
            {
                Map<String, String> paramMap = Maps.newHashMap();
                paramMap.put("orderId", refrenceId);
                brandMessageService.sendDealerOrderMessage(brandId, dealerOrder.getDealerId(), "修改运费", "运费价格已修改为" + dealerOrder.getAdjustFreight() + "元");
                // 加入修改运费日志信息
                OrderChangeLog orderChangeLog = new OrderChangeLog();
                orderChangeLog.setCreateTime(new Date().getTime());
                orderChangeLog.setCreateIp(IPUtil.getIpAddr(request));
                orderChangeLog.setOrderId(dealerOrder.getOrderId());
                orderChangeLog.setAccount(brandId);
                orderChangeLog.setContent("修改订单运费：原快递费" + dealerOrder.getTmpAdjust() + "元，修改后的快递费" + dealerOrder.getAdjustFreight() + "元。");
                orderChangeLogService.insert(orderChangeLog);
            }
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 备注
     *
     * @param request
     * @param orderIds
     * @param brandRemark
     * @param levelMark
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @RequestMapping(value = "/remark", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage remark(HttpServletRequest request, @RequestParam(value = "orderIds[]") String[] orderIds, String brandRemark, Short levelMark)
            throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        if (ArrayUtils.isEmpty(orderIds) || StringUtils.isBlank(brandRemark) || null == levelMark) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        if (!ValidateUtils.isInRange(levelMark.intValue(), 1, 5)) { return this.getJsonMessage(DealerConst.LEVEL_MARK_ERROR); }
        if (!ValidateUtils.isRange(brandRemark, true, 2000)) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR.getCode(), "备注不能超过2000个字"); }
        try
        {
            dealerOrderService.updateBrandRemark(orderIds, brandId, brandRemark, levelMark);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 延长收货期限
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/outActTime")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage outActTime(HttpServletRequest request, @RequestParam String refrenceId, @RequestParam Long actTime)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            if (StringUtils.isBlank(refrenceId) || null == actTime) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
            actTime = Math.abs(actTime);
            actTime = actTime > 15 ? 15 : actTime;
            dealerOrderService.updateOutActTime(actTime, brandId, refrenceId);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 修改订单地址
     *
     * @param dealerOrder
     * @param request
     * @return
     * @author 李飞欧
     */
    @RequestMapping("/modOrderAddr")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage modOrderAddr(DealerOrder dealerOrder, Integer addressprovince, Integer addresscity, Integer addresscounty, HttpServletRequest request)
    {
        try
        {
            if (addresscounty != null)
            {
                String address = regionalService.getFullNameByAreaNoAndLevel(addresscounty, 3, RegionalService.REGIONAL_SPLIT_CODE);
                String[] addr = address.split(RegionalService.REGIONAL_SPLIT_CODE);
                dealerOrder.setDealerAddrProvince(addr[0]);
                dealerOrder.setDealerAddrCity(addr[1]);
                dealerOrder.setDealerAddrArea(addr[2]);
                dealerOrder.setAreaNo(addresscounty);
            }
            if (addresscity != null && addresscounty == null)
            {
                String address = regionalService.getFullNameByAreaNoAndLevel(addresscity, 2, RegionalService.REGIONAL_SPLIT_CODE);
                String[] addr = address.split(RegionalService.REGIONAL_SPLIT_CODE);
                dealerOrder.setDealerAddrProvince(addr[0]);
                dealerOrder.setDealerAddrCity(addr[1]);
                dealerOrder.setDealerAddrArea("");
                dealerOrder.setAreaNo(addresscity);
            }
            if (addresscity == null && addresscounty == null)
            {
                String address = regionalService.getFullNameByAreaNoAndLevel(addressprovince, 1, RegionalService.REGIONAL_SPLIT_CODE);
                dealerOrder.setDealerAddrProvince(address);
                dealerOrder.setDealerAddrCity("");
                dealerOrder.setDealerAddrArea("");
                dealerOrder.setAreaNo(addressprovince);
            }
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            long orderId = dealerOrder.getOrderId();
            dealerOrderService.updateAddr(dealerOrder, brandId, orderId);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 批量免邮
     *
     * @param request
     * @param orderIds
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     */
    @RequestMapping(value = "/avoidMail", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage avoidMail(HttpServletRequest request, @RequestParam(value = "orderIds[]") String[] orderIds)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            // 修改价格后将关闭支付平台的支付订单
            orderPayService.executeClosePay(orderIds);
            dealerOrderService.updateAvoidMail(orderIds, brandId);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
}
