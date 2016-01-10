/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CommonConst;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.NetworkUtils;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerRefReplyModel;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.module.dealer.service.DealerRefReplyService;
import com.zttx.web.module.dealer.service.DealerRefundService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.StringUtils;

/**
 * 经销商退款信息 控制器
 * <p>File：DealerRefundController.java </p>
 * <p>Title: DealerRefundController </p>
 * <p>Description:DealerRefundController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer")
public class DealerRefundController extends GenericController
{
    private static final Logger   logger = Logger.getLogger(DealerRefundController.class);
    
    @Autowired(required = true)
    private DealerRefundService   dealerRefundService;
    
    @Autowired
    private DealerOrderService    dealerOrderService;
    
    @Autowired
    private DealerRefReplyService dealerRefReplyService;
    
    @Autowired
    private UserInfoService       userInfoService;
    
    @Autowired
    private DataDictValueService  dataDictValueService;
    
    @Autowired
    private SmsTemplateService    smsTemplateService;
    
    @Autowired
    private SmsSendService        smsSendService;
    
    @Autowired
    private OrderPayService       orderPayService;
    
    /**
     * 退款/退货申请 选择页面
     *
     * @return
     * @author 周光暖
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/apply")
    public String refundApply(Long orderNumber, ModelMap map) throws BusinessException
    {
        // 验证当前用户，指定订单号的订单是否已经申请过退款
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        if (dealerOrderService.isRefundStatus(orderNumber, dealerId))
        {
            logger.error("订单不能重复申请退款！");
            throw new BusinessException(DealerConst.ALREADY_APPLY_REFUND);
        }
        map.put("orderNumber", orderNumber);
        DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndDealerId(orderNumber, dealerId);
        map.put("dealerOrder", dealerOrder);
        return "dealer/refund_apply";
    }
    
    /**
     * 退款/退货申请 详情页面
     *
     * @return
     * @author 周光暖
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/choice")
    public String viewRefundApply(DealerRefund dealerRefund, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        map.put("dealerRefund", dealerRefund);
        DealerOrder dealerOrder = getDealerOrder(dealerRefund.getOrderNumber(), map, request);
        Short refundStatus = dealerOrder.getRefundStatus();
        if (null == refundStatus)
        {
            // 显示 申请退款 表单
        }
        else if (DealerConstant.DealerRefund.NOT_REFUND == refundStatus || DealerConstant.DealerRefund.NOT_RETURN == refundStatus)
        {
            // 修改退款协议, 准备回显数据
            DealerRefund df = dealerRefundService.getEntityByOrderNumber(dealerRefund.getOrderNumber());
            // 显示操作记录详情
            List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(df.getRefrenceId());
            map.put("drrList", drrList);
            map.put("dealerRefund", df);
        }
        else
        {
            logger.error("订单不能重复申请退款！");
            throw new BusinessException(DealerConst.ALREADY_APPLY_REFUND);
        }
        // 退款退货
        if (null != dealerRefund.getNeedRefund() && true == dealerRefund.getNeedRefund())
        {
            return "dealer/refund_choice_both";
        }
        else
        {
            return "dealer/refund_choice_money";
        }
    }
    
    /**
     * 准备订单信息数据
     *
     * @param orderNumber
     * @param map
     * @param request
     * @throws BusinessException
     * @author 周光暖
     */
    private DealerOrder getDealerOrder(Long orderNumber, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndDealerId(orderNumber, dealerId);
            map.put("dealerOrder", dealerOrder);
            return dealerOrder;
        }
        catch (Exception e)
        {
            throw new BusinessException(DealerConst.FAILURE);
        }
    }
    
    /**
     * 提交 退款/退货申请
     *
     * @return
     * @author 周光暖
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/apply/submit")
    public JsonMessage apply(DealerRefundModel dealerRefund, BigDecimal adjustFreight, String[] attachtNames, HttpServletRequest request)
    {
        JsonMessage json = this.getJsonMessage(DealerConst.FAILURE);
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerId);
            DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndDealerId(dealerRefund.getOrderNumber(), dealerId);
            Short refundStatus = dealerOrder.getRefundStatus();
            // 订单退款状态:不未null(已经申请过退款)，且不是 拒绝退款 和 拒绝退货
            if (null != refundStatus && DealerConstant.DealerRefund.NOT_REFUND != refundStatus && DealerConstant.DealerRefund.NOT_RETURN != refundStatus) { throw new BusinessException(
                    DealerConst.ALREADY_APPLY_REFUND); }
            /**
             * 验证退款金额不超过订单总金额，即可申请退款；
             * 计算公式：订单总金额=订单货款+运费-优惠金额-关闭发货金额
             * 关闭发货金额在执行关闭发货时已经在订单货款中减掉了。
             * 运费在下面调用的dealerRefundService.validateRefundAmount方法中加上了
             * 由此，这里只处理优惠金额。优惠金额如果是优惠就是负数，是加价就是正数.
             */
            BigDecimal totalAmount = dealerOrder.getProductPrice();
            if (null != dealerOrder.getAdjustPrice())
            {
                totalAmount.add(dealerOrder.getAdjustPrice());
            }
            // 验证申请退款金额是否有效
            if (!dealerRefundService.validateRefundAmount(dealerRefund.getRefundAmount(), totalAmount,
                    (dealerOrder.getFrePayState().intValue() == 1) ? dealerOrder.getAdjustFreight() : new BigDecimal(0))) { throw new BusinessException(
                    DealerConst.REFUND_AMOUNT_APPLY_ERROR); }
            String domainName = NetworkUtils.getDoMainName();
            List<DealerRefAttacht> dealerRefAttachts = null;
            if (null != attachtNames && attachtNames.length > 0)
            {
                // attachtNames = MultipartUtils.moveAndDeleteFile(request, MultipartUtils.DEALER_IMG_PATH, attachtNames, null);
                for (int i = 0; i < attachtNames.length; i++)
                {
                    attachtNames[i] = FileClientUtil.moveImgFromTemp(ImageConst.DEALER_IMG_PATH, attachtNames[i], UploadAttCateConst.BRAND_COM_IMG);
                }
                dealerRefAttachts = newListBydomainNames(attachtNames, domainName);
            }
            // 订单退款状态:拒绝退款 或 拒绝退货
            if (null == refundStatus)
            {
                // 申请退款
                dealerRefundService.addApply(dealerRefund, dealerRefAttachts, dealerUserm);
                // String smsTemplate = "%s %s终端商对订单%s申请退款，退款金额%s元，请及时处理，如需查看详情请登录【8637.com】";
                // String smsContent = String.format(smsTemplate, new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()),
                // dealerOrder.getDealerName(), dealerOrder.getOrderId(), dealerRefund.getRefundAmount());
                // brandUsermService.sendSmsToBrandUser(dealerOrder.getBrandId(), smsContent);
                SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_DEALER_REFUND_MONEY);
                if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
                {
                    String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()), dealerOrder.getDealerName(),
                            dealerOrder.getOrderId(), dealerRefund.getRefundAmount());
                    smsSendService.sendSmsToUser(dealerOrder.getBrandId(), smsContent);
                }
            }
            else if (DealerConstant.DealerRefund.NOT_REFUND == refundStatus || DealerConstant.DealerRefund.NOT_RETURN == refundStatus)
            {
                // 当品牌商拒绝时， 再次修改退款协议
                dealerRefundService.updateApply(dealerRefund, dealerRefAttachts, dealerUserm);
            }
            // 订单退款状态:null(未申请过退款)
            json = this.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return json;
    }
    
    // 创建DealerRefAttacht实例的集合(属性设置不完整)
    private List<DealerRefAttacht> newListBydomainNames(String[] attachtNames, String domainName)
    {
        List<DealerRefAttacht> dealerRefAttachts = Lists.newArrayList();
        if (null != attachtNames && attachtNames.length > 0)
        {
            for (String attachtName : attachtNames)
            {
                DealerRefAttacht dealerRefAttacht = new DealerRefAttacht();
                dealerRefAttacht.setAttachtName(attachtName);
                dealerRefAttacht.setDomainName(domainName);
                dealerRefAttachts.add(dealerRefAttacht);
            }
        }
        return dealerRefAttachts;
    }
    
    /**
     * 退款详情页
     * @throws BusinessException
     * @author 周光暖
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/details")
    public String viewRefundInfo(Long orderNumber, String refrenceId, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        // 准备回显数据
        DealerOrder dealerOrder = this.getDealerOrder(orderNumber, map, request);
        DealerRefund dealerRefund = null;
        if (org.apache.commons.lang3.StringUtils.isNotBlank(refrenceId))
        {
            dealerRefund = dealerRefundService.selectByPrimaryKey(refrenceId);
        }
        else
        {
            dealerRefund = dealerRefundService.getEntityByOrderNumber(orderNumber);
        }
        map.put("dealerRefund", dealerRefund);
        if (dealerRefund == null) { return "dealer/refund_details"; }
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        map.put("drrList", drrList);
        map.put("endTime", dealerRefund.getNextActTime());
        // 先判断是否有客服介入
        if (dealerRefund.getCusJoin() != null && dealerRefund.getSerProStatus() != null && dealerRefund.getCusJoin() == true && dealerRefund.getSerProStatus() == 2) // 3：纠纷处理完毕
        { return "dealer/refund_cusJoin"; }
        DataDictValue ddv = dataDictValueService.findDictValue("RefundDate", "serProDay");
        if (null != ddv)
        {
            map.put("dictValue", ddv.getDictValue());
        }
        //
        if (null != dealerOrder && dealerOrder.getDealerOrderType().shortValue() == DealerConstant.DealerOrder.ORDER_TYPE_CREDIT)
        {
            map.put("isCredit", true);
            BigDecimal creditAmountUsed = orderPayService.countCretditAmountUsed(dealerOrder);
            BigDecimal cashNeedRefund = dealerRefund.getRefundAmount().subtract(creditAmountUsed); // 需退的现金
            if (cashNeedRefund.compareTo(BigDecimal.ZERO) >= 0) map.put("cashNeedRefund", cashNeedRefund.doubleValue());
        }
        return "dealer/refund_details";
    }
    
    /**
     * 取消退款
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "refund/cancel", method = RequestMethod.POST)
    public String cancel(DealerRefund dealerRefund) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerRefundService.updateRefundProcess(dealerRefund.getRefrenceId(), dealerId);
        if (dealerRefund.getOrderNumber() == 0) { return "redirect:/dealer/dealerFinancial/factory/refund/details?refrenceId=" + dealerRefund.getRefrenceId(); }
        return "redirect:details?orderNumber=" + dealerRefund.getOrderNumber();
    }
    
    /**
     * 经销商退货发货
     *
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/returnDeliver", method = RequestMethod.POST)
    public String returnDeliver(DealerRefundModel dealerRefund, HttpServletRequest request) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerRefundService.updateDeliver(dealerRefund, dealerId);
        return "redirect:details?orderNumber=" + dealerRefund.getOrderNumber();
    }
    
    /**
     * 纠纷处理-申请客服介入
     *
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/refund/appserjoin")
    public String appserJoin(@RequestParam String orderNumber, @RequestParam("refrenceId") String refrenceId, HttpServletRequest request, Model model)
            throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        dealerRefundService.appserjoin(refrenceId, dealerId);
        return "redirect:details?orderNumber=" + orderNumber;
    }
}
