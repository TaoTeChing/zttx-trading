package com.zttx.web.module.pay.controller;

import java.math.BigDecimal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.pay.remoting.api.PayUserApi;
import com.zttx.pay.remoting.model.PayAccount;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.model.TransferModel;
import com.zttx.web.module.common.model.TransferSearchModel;
import com.zttx.web.module.common.service.PayApiService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;

/**
 * 支付控制器
 * <p>File：PayApiController.java</p>
 * <p>Title: PayApiController</p>
 * <p>Description:PayApiController</p>
 * <p>Copyright: Copyright (c) Jul 7, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
public class PayApiController extends GenericBaseController
{
    @Autowired
    private PayApiService     payApiService;
    
    @Autowired
    private PayUserApi        payUserApi;
    
    @Autowired
    private DealerJoinService dealerJoinService;
    
    @Autowired
    private BrandInfoService  brandInfoService;
    
    @Autowired
    private UserInfoService   userInfoService;
    
    @Autowired
    private DealerInfoService dealerInfoService;
    
    /**
     * 经销商转账
     * @author 陈建平
     * @return
     */
    @RequiresPermissions("dealer:center")
    @RequestMapping(value = "/dealer/payApi/dealerTransfer")
    public String dealerTransfer(Model model) throws BusinessException
    {
        String dealerId = this.getCurrentLoginDealerId();
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
        BigDecimal balance = new BigDecimal(0);
        try
        {
            Long userId = userInfoService.findPayUserId(dealerId);
            PayAccount payAccount = payUserApi.loadPayAccountByUserId(userId);
            balance = payAccount.getBalance();
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(), "未开通支付平台");
        }
        model.addAttribute("dealerInfo", dealerInfo);
        model.addAttribute("balance", balance);
        return "/dealer/transfer_accounts";
    }
    
    /**
     * 品牌商转账
     * @author 陈建平
     * @return
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brand/payApi/brandTransfer")
    public String brandTransfer(Model model) throws BusinessException
    {
        String brandId = this.getCurrentLoginBrandId();
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
        BigDecimal balance = new BigDecimal(0);
        try
        {
            Long userId = userInfoService.executeFindPayUserId(brandId);
            PayAccount payAccount = payUserApi.loadPayAccountByUserId(userId);
            balance = payAccount.getBalance();
        }
        catch (Exception e)
        {
            throw new BusinessException(CommonConst.FAIL.getCode(), "未开通支付平台");
        }
        model.addAttribute("brandInfo", brandInfo);
        model.addAttribute("balance", balance);
        return "/brand/transfer_accounts";
    }
    
    /**
     * 转账
     * @author 陈建平
     * @param request
     * @param transferModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brand/payApi/transfer", method = RequestMethod.POST)
    public JsonMessage brandTransfer(TransferModel transferModel) throws BusinessException
    {
        String brandId = this.getCurrentLoginBrandId();
        payApiService.executeTransfer(brandId, "", transferModel);
        return this.getJsonMessage(CommonConst.SUCCESS.getCode());
    }
    
    /**
     * 转账
     * @author 陈建平
     * @param request
     * @param transferModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("dealer:center")
    @RequestMapping(value = "/dealer/payApi/transfer", method = RequestMethod.POST)
    public JsonMessage dealerTransfer(TransferModel transferModel) throws BusinessException
    {
        String dealerId = this.getCurrentLoginDealerId();
        payApiService.executeTransfer("", dealerId, transferModel);
        return this.getJsonMessage(CommonConst.SUCCESS.getCode());
    }
    
    /**
     * 分页获取经销商加盟
     * @author 陈建平
     * @param page
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/payApi/listDealerJoin")
    @RequiresPermissions(value = {"brand:center", "brand:center"}, logical = Logical.OR)
    public Object listDealerJoin(Pagination page, TransferSearchModel searchBean)
    {
        String brandId = this.getCurrentLoginBrandId();
        String dealerId = this.getCurrentLoginDealerId();
        searchBean.setBrandId(brandId);
        searchBean.setDealerId(dealerId);
        searchBean.setPage(page);
        if (StringUtils.isBlank(brandId) && StringUtils.isBlank(dealerId)) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "请先登录"); }
        PaginateResult<Map<String, Object>> paginateResult = null;
        if (StringUtils.isNotBlank(brandId))
        {
            paginateResult = dealerJoinService.findCooperatedDealer(searchBean);
        }
        else
        {
            paginateResult = dealerJoinService.findCooperatedBrand(searchBean);
        }
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 退押金并终止合作
     * @author 陈建平
     * @param request
     * @param transferModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brand/payApi/depositTransfer", method = RequestMethod.POST)
    public JsonMessage brandDepositTransfer(TransferModel transferModel) throws BusinessException
    {
        String brandId = this.getCurrentLoginBrandId();
        if (StringUtils.isBlank(brandId)) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "请先登录"); }
        transferModel.setTitle("退押金");
        payApiService.executeBrandDepositTransfer(brandId, transferModel, true);
        return this.getJsonMessage(CommonConst.SUCCESS.getCode());
    }
    
    /**
     * 退押金但不终止合作
     * @author 陈建平
     * @param request
     * @param transferModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brand/payApi/depositTransferNotStop", method = RequestMethod.POST)
    public JsonMessage depositTransferNotStop(TransferModel transferModel) throws BusinessException
    {
        String brandId = this.getCurrentLoginBrandId();
        if (StringUtils.isBlank(brandId)) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "请先登录"); }
        transferModel.setTitle("退押金");
        payApiService.executeBrandDepositTransfer(brandId, transferModel, false);
        return this.getJsonMessage(CommonConst.SUCCESS.getCode());
    }
    
    /**
     * 缴押金
     * @author 陈建平
     * @param request
     * @param transferModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("dealer:center")
    @RequestMapping(value = "/dealer/payApi/depositTransfer", method = RequestMethod.POST)
    public JsonMessage dealerDepositTransfer(TransferModel transferModel) throws BusinessException
    {
        String dealerId = this.getCurrentLoginDealerId();
        if (StringUtils.isBlank(dealerId)) { return this.getJsonMessage(CommonConst.FAIL.getCode(), "请先登录"); }
        transferModel.setTitle("缴押金");
        // payApiService.executeDealerDepositTransfer(request,dealerId,transferModel);
        return this.getJsonMessage(CommonConst.SUCCESS.getCode());
    }
}
