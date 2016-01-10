package com.zttx.web.module.dealer.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.erp.module.refund.model.RefundChangeModel;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductExtInfo;
import com.zttx.goods.module.entity.ProductSkuPrice;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.dubbo.service.DealerRefundDubboServiceConsumer;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandJoinFilter;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.model.DealerJoinTeminateModel;
import com.zttx.web.module.brand.service.BrandLevelService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.service.OrderPayService;
import com.zttx.web.module.dealer.controller.servlet.view.document.DealerJoinExcelView;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.model.DealerDepositView;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.model.DealerJoinModel;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * <p>File：DealerJoinController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-3 下午4:32:06</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
@Controller
public class DealerJoinController extends GenericController
{
    @Autowired
    private BrandesInfoService               brandesInfoService;
    
    @Autowired
    private BrandLevelService                brandLevelService;
    
    @Autowired
    private DealerJoinService                dealerJoinService;
    
    @Autowired
    private OrderPayService                  orderPayService;
    
    @Autowired
    private DealerInfoService                dealerInfoService;
    
    @Autowired
    private DealerRefundDubboServiceConsumer dealerRefundDubboServiceConsumer;
    
    @Autowired
    private ProductInfoDubboConsumer         productInfoDubboConsumer;
    
    @RequiresPermissions("dealer:center")
    @RequestMapping(ApplicationConst.DEALER + "/copartner/brandes")
    public String list(Pagination page, Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerInfoModel dealerInfo = dealerInfoService.findById(dealerId);
        model.addAttribute("dealerInfo", dealerInfo);
        PaginateResult<Map<String, Object>> findBrands = dealerJoinService.findJoinByDealerId(dealerId, null, null, page);
        model.addAttribute("result", findBrands);
        return "dealer/copartner_brandes";
    }
    
    /**
     * 加盟品牌停止合同
     *
     * @return
     */
    @ResponseBody
    @RequiresPermissions("dealer:center")
    @RequestMapping(value = ApplicationConst.DEALER + "/joined/stop", method = RequestMethod.POST)
    public JsonMessage stop(DealerJoinModel dealerJoin) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentDealer();
        String dealerId = userPrincipal.getRefrenceId();
        dealerJoin.setDealerId(dealerId);
        dealerJoin.setStopUserId(dealerId);
        dealerJoinService.updateStopDealerJoinState(dealerJoin);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 品牌商管理已合作的经销商
     *
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/join")
    public String redirect()
    {
        return "redirect:/brand/join/list";
    }
    
    /**
     * 品牌商管理已合作的经销商
     *
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/join/list")
    public String list(Pagination pagination, BrandJoinFilter filter, Model model) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String branderId = userPrincipal.getRefrenceId();
        // 过滤信息
        model.addAttribute("filter", filter);
        if (filter.getDealerLevel() != null && filter.getBid() != null)
        {
            model.addAttribute("dealerLevels", brandLevelService.getBrandLevelsBy(branderId, filter.getBid()));
        }
        PaginateResult<Map<String, Object>> pageResult = dealerJoinService.findByBrandId(branderId, pagination, filter);
        model.addAttribute("pageResult", pageResult);
        List<BrandsInfoModel> brandsList = brandesInfoService.getCooperatedBrandes(branderId);
        // 品牌列表
        model.addAttribute("brandsInfos", brandsList);
        List<Map<String, Object>> brandLevels = Lists.newArrayList();
        for (BrandesInfo brandesInfo : brandsList)
        {
            List<BrandLevel> levels = brandLevelService.getBrandLevelsBy(branderId, brandesInfo.getRefrenceId());
            Map<String, Object> map = Maps.newHashMap();
            map.put("refrenceId", brandesInfo.getRefrenceId());
            map.put("levels", levels);
            brandLevels.add(map);
        }
        // 各个品牌的经销商等级
        model.addAttribute("brandLevels", brandLevels);
        if (filter.getState().equals(DealerConstant.DealerJoin.TERMINATED)) { return "brand/list_dealerJoinedTeminated"; }
        model.addAttribute("filter", filter);
        return "brand/list_dealerJoined";
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/join/list.xls", method = RequestMethod.GET)
    public ModelAndView exportExcel(BrandJoinFilter filter) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String branderId = userPrincipal.getRefrenceId();
        Pagination pagination = new Pagination(ApplicationConst.DEFAULT_CURRENT_PAGE, ApplicationConst.MAX_PAGE_SIZE);
        List<Map<String, Object>> list = Lists.newArrayList();
        do
        {
            PaginateResult<Map<String, Object>> result = dealerJoinService.findByBrandId(branderId, pagination, filter);
            list.addAll(result.getList());
            pagination = result.getPage();
            pagination.setCurrentPage(pagination.getCurrentPage() + 1);
        }
        while (pagination.getHasNextPage());
        ModelAndView mav = new ModelAndView(new DealerJoinExcelView());
        mav.addObject("filter", filter);
        mav.addObject("list", list);
        return mav;
    }
    
    /**
     * 品牌商管理已合作的经销商
     *
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/join/list.json")
    public JsonMessage listJson(Pagination pagination, BrandJoinFilter filter) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String branderId = userPrincipal.getRefrenceId();
        PaginateResult<Map<String, Object>> pageResult = dealerJoinService.findByBrandId(branderId, pagination, filter);
        return this.getJsonMessage(CommonConst.SUCCESS, pageResult);
    }
    
    /**
     * 终止合作（已合作列表内）
     *
     * @param teminateModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/join/terminate", method = RequestMethod.POST)
    public JsonMessage terminate(DealerJoinTeminateModel teminateModel) throws BusinessException
    {
        dealerJoinService.brandTerminal(teminateModel.getId(), teminateModel.getEndMark(), teminateModel.getBrandsId());
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 获取加盟合作
     *
     * @param dealerJoinId
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/join/getDealerJoin", method = RequestMethod.POST)
    public JsonMessage getDealerJoin(String dealerJoinId) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = dealerJoinService.findByRefrenceIdAndBrandId(dealerJoinId, brandId);
        return this.getJsonMessage(CommonConst.SUCCESS, dealerJoin);
    }
    
    @RequiresPermissions("dealer:center")
    @RequestMapping(value = ApplicationConst.DEALER + "/join/redirect")
    public String redirectPay(RedirectAttributes redirectAttributes, String orderIdArr, String orderType) throws BusinessException
    {
        orderPayService.executeClosePay(orderIdArr);
        redirectAttributes.addAttribute("orderIdArr", orderIdArr);
        redirectAttributes.addAttribute("orderType", orderType);
        return "redirect:/dealer/orderPay/pay";
    }
    
    /**
     * 设置押金金额
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/join/setDeposit")
    public JsonMessage setDeposit(HttpServletRequest request, String joinId, DealerDepositView depositView) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        DealerJoin dealerJoin = dealerJoinService.findByRefrenceIdAndBrandId(joinId, brandId);
        dealerJoinService.saveSetDepositAmount(request, dealerJoin, depositView);
        // 推送数据到智慧门店
        List<ProductBaseInfo> productList = productInfoDubboConsumer.findBybrandsIdAndPointed(dealerJoin.getBrandsId());
        if (depositView.getPoint())
        {
            List<RefundChangeModel> changeModelList = new ArrayList<RefundChangeModel>();
            for (int i = 0; i < productList.size(); i++)
            {
                ProductBaseInfo product = productList.get(i);
                RefundChangeModel refund = new RefundChangeModel();
                refund.setProductId(product.getRefrenceId());
                refund.setRefundPercent(product.getProductExtInfo().getPointPercent().doubleValue());
                Long effTime = product.getProductExtInfo().getPointEffTime();
                Date now = CalendarUtils.parseStringToDate(CalendarUtils.getCurrentDate("yyyy-MM-dd"));
                Long nowLong = CalendarUtils.addDay(now, 1);
                if (effTime - nowLong < 0)
                {
                    refund.setStartDate(nowLong);
                    ProductExtInfo extInfo = new ProductExtInfo();
                    extInfo.setRefrenceId(product.getRefrenceId());
                    extInfo.setPointEffTime(nowLong);
                    productInfoDubboConsumer.updateProductExtInfoSimple(extInfo);
                }
                else
                {
                    refund.setStartDate(effTime);
                }
                List<String> zttxDealerIds = new ArrayList<String>();
                zttxDealerIds.add(dealerJoin.getDealerId());
                refund.setZttxDealerIds(zttxDealerIds);
                Map<String, Double> skuPriceMap = new HashMap<String, Double>();
                for (int j = 0; j < product.getProductSkuList().size(); j++)
                {
                    ProductSkuPrice price = product.getProductSkuList().get(j).getProductSkuPrice();
                    skuPriceMap.put(price.getProductSkuId(), price.getPointPrice().doubleValue());
                }
                refund.setSkuPriceMap(skuPriceMap);
                changeModelList.add(refund);
            }
            if (changeModelList.size() != 0)
            {
                dealerRefundDubboServiceConsumer.createRefundChange(changeModelList);
            }
        }
        else
        {
            Set<String> productIds = new HashSet<String>();
            for (int i = 0; i < productList.size(); i++)
            {
                ProductBaseInfo product = productList.get(i);
                productIds.add(product.getRefrenceId());
            }
            if (productIds.size() != 0)
            {
                dealerRefundDubboServiceConsumer.removeRefundChange(dealerJoin.getDealerId(), productIds);
            }
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
