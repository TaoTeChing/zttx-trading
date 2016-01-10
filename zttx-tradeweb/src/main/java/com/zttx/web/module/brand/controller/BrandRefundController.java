package com.zttx.web.module.brand.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.google.common.collect.Lists;
import com.zttx.pay.remoting.crm.PayAccountApi;
import com.zttx.pay.remoting.model.PayAccount;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.SmsConst;
import com.zttx.web.module.brand.entity.BrandAddress;
import com.zttx.web.module.brand.entity.BrandPayword;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandAddressModel;
import com.zttx.web.module.brand.service.BrandAddressService;
import com.zttx.web.module.brand.service.BrandPaywordService;
import com.zttx.web.module.brand.service.BrandStoreService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.model.DealerRefReplyModel;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.EncryptUtils;
import com.zttx.web.utils.EntityUtils;
import com.zttx.web.utils.IPUtil;

/**
 * 交易管理/退款功能
 * Created by 李星 on 2015/8/20.
 */
@Controller
@RequestMapping(value = ApplicationConst.BRAND)
public class BrandRefundController extends GenericController
{
    @Autowired
    private DealerOrderService      dealerOrderService;
    
    @Autowired
    private DealerRefundService     dealerRefundService;
    
    @Autowired
    private DealerRefReplyService   dealerRefReplyService;
    
    @Autowired
    private BrandAddressService     brandAddressService;
    
    @Autowired
    private DealerRefAttachtService dealerRefAttachtService;
    
    @Autowired
    private RegionalService         regionalService;
    
    @Autowired
    private OrderPayService         orderPayService;
    
    @Autowired
    private SmsTemplateService      smsTemplateService;
    
    @Autowired
    private SmsSendService          smsSendService;
    
    @Autowired
    private BrandPaywordService     brandPaywordService;
    
    @Autowired
    private UserInfoService         userInfoService;
    
    @Autowired
    private BrandesInfoService      brandesInfoService;
    
    @Autowired
    private BrandStoreService       brandStoreService;
    
    @Autowired
    private DealerInfoService       dealerInfoService;
    
    @Autowired
    private PayAccountApi           payAccountApi;
    
    /**
     * 退款详情页
     * @param orderNumber 订单号
     * @param map
     * @param request
     * @return
     * @throws BusinessException
     * @author 毛江乐
    */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/view/{orderNumber}")
    public String viewRefundInfo(@PathVariable Long orderNumber, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrderModel dealerOrder = dealerOrderService.getByOrderIdAndBrandId(orderNumber, brandId);
        if (null == dealerOrder) throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        DealerRefund dealerRefund = dealerRefundService.getEntityByOrderNumber(orderNumber);
        if (null == dealerRefund) throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        // 操作超时
        map.put("dealerOrder", dealerOrder);
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        if (dealerRefund.getCusJoin() != null
                && dealerRefund.getSerProStatus() != null
                && dealerRefund.getCusJoin() == true
                && dealerRefund.getSerProStatus() == 2
                && (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_HANDLE || dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_SHIP
                        || dealerRefund.getRefundState() == DealerConstant.DealerRefund.SHIPED || dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_REFUND || dealerRefund
                        .getRefundState() == DealerConstant.DealerRefund.NOT_RETURN))
        {// 2：纠纷处理中
            return "brand/refund_details_customerStauts3";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_HANDLE && dealerRefund.getNeedRefund() == false)// 1：申请退款等待处理 不需要退货
        {
            return "brand/refundDetails";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_HANDLE && dealerRefund.getNeedRefund() == true)// 1：申请退款等待处理 需要退货
        {
            // 默认品牌商收货地址
            BrandAddress defaultBrandAddress = brandAddressService.getDefaultBrandAddress(dealerOrder.getBrandsId());
            BrandAddressModel defaultBrandAddressModel = new BrandAddressModel();
            EntityUtils.copyProperties(defaultBrandAddressModel, defaultBrandAddress);
            if (defaultBrandAddress != null)
            {
                defaultBrandAddressModel.setFullAreaName(regionalService.getFullNameByAreaNo(defaultBrandAddress.getAreaNo(), RegionalService.REGIONAL_SPLIT_CODE));
            }
            map.put("defaultBrandAddress", defaultBrandAddressModel);
            return "brand/refund_details_address";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_SHIP)// 2：同意退货等待发货
        {
            return "brand/refund_details_agreeStauts";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.SHIPED)// 3：退货已发货
        {
            return "brand/refund_details_agreeStauts2";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_REFUND)// 4：拒绝退款
        {
            return "brand/refund_details_refusalStauts";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_RETURN)// 5：拒绝退货
        {
            return "brand/refund_details_refusalStauts";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.CLOSS_REFUND) // 6：退款关闭
        {
            return "brand/refund_details_closeStauts";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.CANCEL_REFUND) // 7: 取消退款
        {
            return "brand/refund_details_closeStauts";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.AGREE_REFUND)// 9：同意仅退款
        {
            return "brand/refund_details_successStauts";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND)// 10：同意退货退款
        { return "brand/refund_details_successStauts"; }
        return "redirect:";
    }
    
    /**
     * 进入同意退款页面
     *
     * @throws BusinessException
     * @author 毛江乐
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/view/agree/{orderNumber}", method = RequestMethod.POST)
    public String viewRefundInfoAgree(@PathVariable Long orderNumber, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndBrandId(orderNumber, brandId);
        DealerRefund dealerRefund = dealerRefundService.getEntityByOrderNumber(orderNumber);
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        map.put("hasBrandPayword", orderPayService.isPaymentPwdExists(brandId));
        map.put("dealerOrder", dealerOrder);
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        return "brand/refund_details_handleApply";
    }
    
    /**
     * 仅退款进入拒绝退款页面
     *
     * @throws BusinessException
     * @author 毛江乐
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/view/refuse/{orderNumber}", method = RequestMethod.POST)
    public String viewRefundInfoRefuse(@PathVariable Long orderNumber, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndBrandId(orderNumber, brandId);
        DealerRefund dealerRefund = dealerRefundService.getEntityByOrderNumber(orderNumber);
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        map.put("dealerOrder", dealerOrder);
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        return "brand/refund_details_refused";
    }
    
    /**
     * 退款-同意退款(退款成功)
     *
     * @author 鲍建明
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/agreeReturn", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage updateAgreeReturn(@RequestParam String refrenceId, @RequestParam String payWord, HttpServletRequest request)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            dealerRefundService.updateAgreeReturn(refrenceId, brandId, payWord);
            DealerRefund dealerRefund = dealerRefundService.selectByPrimaryKey(refrenceId);
            SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsConst.SMS_BRAND_REFUND_MONEY);
            if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
            {
                DealerOrder dealerOrder = dealerOrderService.selectByPrimaryKey(dealerRefund.getOrderId());
                String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()), dealerOrder.getOrderId(),
                        dealerRefund.getRefundAmount(), dealerRefund.getTotalAmount());
                smsSendService.sendSmsToUser(dealerRefund.getDealerId(), smsContent);
            }
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            e.printStackTrace();
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 拒绝退款
     *
     * @param refrenceId
     * @param request
     * @return
     * @throws Exception
     * @author 张孟如
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "refund/refuseReturn", method = RequestMethod.POST)
    public JsonMessage updateRefuseReturn(@RequestParam String refrenceId, @RequestParam String remark, HttpServletRequest request) throws Exception
    {
        try
        {
            if (StringUtils.isBlank(remark)) { return super.getJsonMessage(CommonConst.Message_IS_BLANK); }
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            dealerRefundService.refuseReturn(refrenceId, brandId, remark);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    // 新增支付密码
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/createPayword")
    public JsonMessage insertBrandPayword(HttpServletRequest request, BrandPayword newPwd) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        // BrandPayword主键与brandId一一对应
        BrandPayword brandPayword = brandPaywordService.selectByPrimaryKey(brandId);
        if (brandPayword != null) { return this.getJsonMessage(CommonConst.DATA_EXISTS); }
        newPwd.setRefrenceId(brandId);
        newPwd.setCreateIp(IPUtil.getIpAddr(request));
        UserInfo brandUserm = userInfoService.selectByPrimaryKey(brandId);
        try
        {
            isValiPass(brandUserm, newPwd.getPayWord());
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        try
        {
            newPwd.setDealerTel(brandUserm.getUserMobile());
            brandPaywordService.insertCreatePayPassword(newPwd);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    private void isValiPass(UserInfo userm, String newPassword) throws BusinessException
    {
        String z = "\\d*|[a-zA-Z]*";
        if (newPassword.matches(z))
        {
            // 支付密码不能全数字或全字母
            throw new BusinessException(CommonConst.BRAND_PAYWORD_NOT_NUMBER_OR_LETTER);
        }
        String salt = userm.getUserSalt();
        String pwdString = EncryptUtils.encrypt(newPassword + salt, ApplicationConst.ENCRYPT);
        if (pwdString.equals(userm.getUserPwd())) { throw new BusinessException(CommonConst.EQUALS_BRANDUSERM_PWD); }
    }
    
    /**
     * 拒绝退货退款页面
     *
     * @throws BusinessException
     * @author 毛江乐
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/view/refuseBoth/{orderNumber}")
    public String viewRefundInfoRefuseBoth(@PathVariable Long orderNumber, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndBrandId(orderNumber, brandId);
        DealerRefund dealerRefund = dealerRefundService.getEntityByOrderNumber(orderNumber);
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        map.put("dealerOrder", dealerOrder);
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        return "brand/refund_details_refusedBoth";
    }
    
    /**
     * 退款退货-同意退货(未实际退款) step 1
     *
     * @author 毛江乐
     */
    @RequestMapping(value = "/refund/agreeReturnBoth1", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage updateAgreeReturnBoth1(@RequestParam String refrenceId, @RequestParam String brandRecAddr, HttpServletRequest request)
    {
        try
        {
            if (brandRecAddr.trim() == null || brandRecAddr.trim() == "") { return super.getJsonMessage(CommonConst.ADDRESS_NOT_EXIST); }
            List<DealerRefundModel> dealerRefunderList = new ArrayList();
            DealerRefundModel dealerRefund = dealerRefundService.getEntityById(refrenceId);
            dealerRefund.setBrandRecAddr(brandRecAddr);
            dealerRefunderList.add(dealerRefund);
            dealerRefundService.updateAutoAgreeReturnBoth(dealerRefunderList, Boolean.FALSE);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 退款退货-同意退款(已收货) step 2
     *
     * @author 毛江乐
    */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/agreeReturnBoth2", method = RequestMethod.POST)
    public JsonMessage updateAgreeReturnBoth2(@RequestParam String refrenceId, @RequestParam String payWord, HttpServletRequest request)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            dealerRefundService.updateAgreeReturn(refrenceId, brandId, payWord);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 退货地址列表
     *
     * @param pagination
     * @param refrenceId
     * @author 毛江乐
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brandAddress/data")
    public Object listInviteJson(Pagination pagination, String refrenceId) throws BusinessException
    {
        DealerRefund dealerRefund = dealerRefundService.getEntityById(refrenceId);
        if (null == dealerRefund) { return this.getJsonMessage(CommonConst.BRAND_INFO_NULL); }
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndBrandId(dealerRefund.getOrderNumber(), brandId);
        List<BrandAddressModel> list = Lists.newArrayList();
        if (null == dealerOrder)
        {
            BrandAddress address = brandAddressService.getDefaultBrandAddressByBrandId(brandId);
            list.add(getBrandAddressModel(address));
        }
        else
        {
            PaginateResult<BrandAddress> result = brandAddressService.listAddress(pagination, dealerOrder.getBrandsId());
            for (BrandAddress address : result.getList())
            {
                list.add(getBrandAddressModel(address));
            }
        }
        PaginateResult<BrandAddressModel> resultTrue = new PaginateResult(pagination, list);
        resultTrue.setList(list);
        return this.getJsonMessage(CommonConst.SUCCESS, resultTrue);
    }
    
    private BrandAddressModel getBrandAddressModel(BrandAddress address)
    {
        BrandAddressModel model = new BrandAddressModel();
        EntityUtils.copyProperties(model, address);
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(address.getBrandsId());
        model.setBrandsName(brandesInfo.getBrandsName());
        model.setStoreName(brandStoreService.selectByPrimaryKey(address.getStoreId()).getStoreName());
        model.setFullAreaName(regionalService.getFullNameByAreaNo(address.getAreaNo(), RegionalService.REGIONAL_SPLIT_CODE));
        return model;
    }
    
    /**
     * 经销商已退货页,品牌商填写支付密码，确认退款页面
     *
     * @param orderNumber
     * @param map
     * @param request
     * @return
     * @throws BusinessException
     * @author 毛江乐
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/view/agreeStauts2_pay/{orderNumber}", method = RequestMethod.POST)
    public String viewAgreeStauts2_pay(@PathVariable Long orderNumber, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerOrder dealerOrder = dealerOrderService.getByOrderIdAndBrandId(orderNumber, brandId);
        DealerRefund dealerRefund = dealerRefundService.getEntityByOrderNumber(orderNumber);
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        map.put("hasBrandPayword", orderPayService.isPaymentPwdExists(brandId));
        map.put("dealerOrder", dealerOrder);
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        return "brand/refund_details_agreeStauts2_pay";
    }
    
    /**
     * 拒绝退货
     *
     * @param refrenceId
     * @param request
     * @return
     * @throws Exception
     * @author 毛江乐
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "refund/refuseRefund", method = RequestMethod.POST)
    public JsonMessage updateRefuseRefund(@RequestParam String refrenceId, @RequestParam String remark, HttpServletRequest request) throws Exception
    {
        try
        {
            if (StringUtils.isBlank(remark)) { return super.getJsonMessage(CommonConst.Message_IS_BLANK); }
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            dealerRefundService.refuseRefund(refrenceId, brandId, remark);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 退款关闭页
     *
     * @param orderNumber
     * @param map
     * @param request
     * @return
     * @throws BusinessException
     * @author 毛江乐
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/view/closeStauts/{orderNumber}")
    public String viewRefundcloseStauts(@PathVariable Long orderNumber, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        DealerRefund dealerRefund = dealerRefundService.getEntityByOrderNumber(orderNumber);
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        return "brand/refund_details_closeStauts";
    }
    
    /**
     * 工厂店退款处理
     *
     * @return {@link java.lang.String}
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/factory")
    public String factoryRefund() throws BusinessException
    {
        return "brand/refund_factory_list";
    }
    
    /**
     * 工厂店退款处理(JSON)
     *
     * @param pagination
     * @param dealerRefund
     * @return {@link java.lang.String}
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/factory/data")
    public Object factoryRefundData(HttpServletRequest request, Pagination pagination, DealerRefundModel dealerRefund) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        if (null != dealerRefund.getRefundType())
        {
            if (dealerRefund.getRefundType().equals(new Short("0")))
            {
                dealerRefund.setNeedRefund(false);
            }
            else if (dealerRefund.getRefundType().equals(new Short("1")))
            {
                dealerRefund.setNeedRefund(true);
            }
        }
        dealerRefund.setBrandId(brandId);
        dealerRefund.setFactoryStore(true);
        PaginateResult<DealerRefund> result = dealerRefundService.factoryStoreDealerRefund(dealerRefund, pagination);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 退款处详情(授信订单退款和总账退款公用)
     * @return {@link java.lang.String}
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/factory/view/{refundId}")
    public String factoryRefundInfo(@PathVariable Long refundId, ModelMap map) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerRefund dealerRefund = dealerRefundService.getByRefundIdAndBrandId(refundId, brandId);
        if (null == dealerRefund) throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        boolean isTotal = true; // 是否是总账退款详情,false则为授信订单退款详情
        if (StringUtils.isNotBlank(dealerRefund.getOrderId())) isTotal = false;
        DealerInfo dealerInfo = dealerInfoService.findById(dealerRefund.getDealerId());
        // 显示凭证记录详情
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        map.put("dealerInfo", dealerInfo);
        if (dealerRefund.getCusJoin() != null
                && dealerRefund.getSerProStatus() != null
                && dealerRefund.getCusJoin() == true
                && dealerRefund.getSerProStatus() == 2
                && (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_HANDLE || dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_SHIP
                        || dealerRefund.getRefundState() == DealerConstant.DealerRefund.SHIPED || dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_REFUND || dealerRefund
                        .getRefundState() == DealerConstant.DealerRefund.NOT_RETURN))
        { // 2：纠纷处理中
            if (isTotal) return "brand/refund_factory_disputes";
            else return "brand/refund_credit_disputes";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_HANDLE && dealerRefund.getNeedRefund() == false)
        {// 1：申请退款等待处理 不需要退货
            if (isTotal) return "brand/refund_factory_detail";
            else return "brand/refund_credit_detail";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_HANDLE && dealerRefund.getNeedRefund() == true)
        {// 1：申请退款等待处理 需要退货
         // 默认品牌商收货地址
            BrandAddress defaultBrandAddress = brandAddressService.getDefaultBrandAddressByBrandId(brandId);
            BrandAddressModel defaultBrandAddressModel = new BrandAddressModel();
            EntityUtils.copyProperties(defaultBrandAddressModel, defaultBrandAddress);
            if (defaultBrandAddress != null)
            {
                defaultBrandAddressModel.setFullAreaName(regionalService.getFullNameByAreaNo(defaultBrandAddress.getAreaNo(), RegionalService.REGIONAL_SPLIT_CODE));
            }
            map.put("defaultBrandAddress", defaultBrandAddressModel);
            if (isTotal) return "brand/refund_factory_address";
            else return "brand/refund_credit_address";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.WAIT_SHIP)
        {// 2：同意退货等待发货
            if (isTotal) return "brand/refund_factory_delivery";
            else return "brand/refund_credit_delivery";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.SHIPED)
        {// 3：退货已发货
            if (isTotal) return "brand/refund_factory_shipped";
            else return "brand/refund_credit_shipped";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.AGREE_REFUND
                || dealerRefund.getRefundState() == DealerConstant.DealerRefund.AGREE_RETURN_AND_REFUND)
        {// 处理完成
            if (isTotal) return "brand/refund_factory_success";
            else return "brand/refund_credit_success";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.CANCEL_REFUND || dealerRefund.getRefundState() == DealerConstant.DealerRefund.CLOSS_REFUND)
        { // 退款闭关或取消
            if (isTotal) return "brand/refund_factory_cancel";
            else return "brand/refund_credit_cancel";
        }
        else if (dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_REFUND || dealerRefund.getRefundState() == DealerConstant.DealerRefund.NOT_RETURN)
        { // 拒绝退款或退货
            if (isTotal) return "brand/refund_factory_refusal";
            else return "brand/refund_credit_refusal";
        }
        return "redirect:";
    }
    
    /**
     * 工厂店订单同意退款处理
     *
     * @param refundId
     * @param map
     * @param request
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/factory/agree/{refundId}", method = RequestMethod.POST)
    public String factoryRefundAgree(@PathVariable Long refundId, ModelMap map, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerRefund dealerRefund = dealerRefundService.getByRefundIdAndBrandId(refundId, brandId);
        DealerInfo dealerInfo = dealerInfoService.findById(dealerRefund.getDealerId());
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        BigDecimal debtMoney = dealerRefundService.getAllDebtMoney(dealerRefund.getBrandId(), dealerRefund.getDealerId());
        map.put("hasBrandPayword", orderPayService.isPaymentPwdExists(brandId));
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        map.put("debtMoney", debtMoney);
        map.put("accountBalance", getAcountBalance());
        map.put("dealerInfo", dealerInfo);
        if (StringUtils.isBlank(dealerRefund.getOrderId())) return "brand/refund_factory_agree";
        else return "brand/refund_credit_agree";
    }
    
    /**
    * 获取账号余额
    * @return
    * @author 李星
    */
    private BigDecimal getAcountBalance() throws BusinessException
    {
        Long payUserId = userInfoService.findPayUserId(OnLineUserUtils.getCurrentBrand().getRefrenceId());
        // 如果开通了结算平台
        if (null != payUserId)
        {
            PayAccount payAccount = payAccountApi.findByUserId(payUserId);
            return payAccount.getBalance();
        }
        return BigDecimal.ZERO;
    }
    
    /**
     * 拒绝退款处理页面
     * @param refundId
     * @param map
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/factory/refuse/{refundId}", method = RequestMethod.POST)
    public String factoryRefundRefuse(@PathVariable Long refundId, ModelMap map) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        DealerRefund dealerRefund = dealerRefundService.getByRefundIdAndBrandId(refundId, brandId);
        DealerInfo dealerInfo = dealerInfoService.findById(dealerRefund.getDealerId());
        List<DealerRefAttacht> dealerRefAttachts = dealerRefAttachtService.listByRefundId(dealerRefund.getRefrenceId());
        // 显示操作记录详情
        List<DealerRefReplyModel> drrList = dealerRefReplyService.listByRefundId(dealerRefund.getRefrenceId());
        Long nextActTime = dealerRefund.getNextActTime();
        map.put("dealerRefund", dealerRefund);
        map.put("dealerRefAttachts", dealerRefAttachts);
        map.put("endTime", nextActTime);
        map.put("drrList", drrList);
        map.put("dealerInfo", dealerInfo);
        if (StringUtils.isBlank(dealerRefund.getOrderId())) return "brand/refund_factory_refuse";
        else return "brand/refund_credit_refuse";
    }
    
    /**
     * 工厂店同意退款处理
     * @param refrenceId
     * @param payWord
     * @param request
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/factory/agreeReturn", method = RequestMethod.POST)
    public JsonMessage factoryAgreeReturn(@RequestParam String refrenceId, @RequestParam String payWord, HttpServletRequest request)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            dealerRefundService.updateFactoryAgreeReturn(refrenceId, brandId, payWord);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            e.printStackTrace();
            return super.getJsonMessage(e.getErrorCode());
        }
    }
    
    /**
     * 同意退款处理（总账）
     * @param refrenceId
     * @param payWord
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/refund/total/agreeReturn", method = RequestMethod.POST)
    public JsonMessage totalAgreeReturn(@RequestParam String refrenceId, @RequestParam String payWord)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            dealerRefundService.updateTotalAgreeReturn(refrenceId, brandId, payWord);
            return super.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (BusinessException e)
        {
            e.printStackTrace();
            return super.getJsonMessage(e.getErrorCode());
        }
    }
}
