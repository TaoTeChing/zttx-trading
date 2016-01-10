package com.zttx.web.module.dealer.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.model.BrandVisitedModel;
import com.zttx.web.module.brand.service.BrandInviteService;
import com.zttx.web.module.brand.service.BrandVisitedService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.common.service.ProductViewLogService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.controller.servlet.view.document.DealerJoinBrandExcelView;
import com.zttx.web.module.dealer.model.*;
import com.zttx.web.module.dealer.service.DealerCollectService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.module.dealer.service.DealerVisitedService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * <p>File:DealerJoinManageController</p>
 * <p>Title:</p>
 * <p>Description: 经销商加盟管理控制层：  已加盟的品牌库
 *                                       申请加盟管理（浏览过我的品牌商，我浏览过的品牌商，申请中，邀请中，撤销的申请，未通过审核的）
 *                                       品牌收藏
 * </p>
 * <p>Copyright: Copyright (c)2015/8/12 11:01</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerJoinManage")
public class DealerJoinManageController extends GenericController
{
    @Autowired
    private UserInfoService       userInfoService;
    
    @Autowired
    private DealerInfoService     dealerInfoService;
    
    @Autowired
    private DataDictValueService  dataDictValueService;
    
    @Autowired
    private BrandVisitedService   brandVisitedService;
    
    @Autowired
    private DealerVisitedService  dealerVisitedService;
    
    @Autowired
    private BrandInviteService    brandInviteService;
    
    @Autowired
    private DealerCollectService  dealerCollectService;
    
    @Autowired
    private DealerJoinService     dealerJoinService;
    
    @Autowired
    private ProductViewLogService productViewLogService;
    
    /* =========================================经销商 已加盟的品牌库 begin================================================ */
    /**
     * 经销商已经加盟的品牌库  test 二期重新改版
     * @return
     * @author 易永耀
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/joinBrandes")
    public String BrandesIndex()
    {
        return "dealer/copartner_brandes";
    }
    
    /* =========================================经销商 已加盟的品牌库 end================================================ */
    /* =========================================经销商申请加盟管理 begin================================================ */
    /**
     * 经销商申请加盟管理（浏览过我的品牌商）index
     * @author 吕海斌
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/beBrandVisited")
    public String visitedIndex(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("dealerUserm", userInfoService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.getDealerInfo(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_bevisited";
    }
    
    /**
     * 经销商申请加盟管理（浏览过我的品牌商）data
     * @author 吕海斌
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/beBrandVisited/data")
    public Object visitedData(Pagination pagination, BrandVisitedModel brandVisitedModel) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandVisitedModel.setDealerId(dealerId);
        PaginateResult<Map<String, Object>> paginateResult = brandVisitedService.getBrandVisitedPage(pagination, brandVisitedModel);
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     *  经销商申请加盟管理（我浏览过的品牌）index
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/visitedBrands")
    public String brandsIndex(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("dealerUserm", userInfoService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.getDealerInfo(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_tovisite";
    }
    
    /**
     * 经销商申请加盟管理（经销商我浏览过的品牌）data
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/visitedBrands/data")
    public Object brandsData(Pagination pagination, DealerVisitedModel dealerVisitedModel) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        pagination.setPageSize(10);
        dealerVisitedModel.setDealerId(dealerId);
        PaginateResult<Map<String, Object>> paginateResult = dealerVisitedService.getDealerVisitedsBy(dealerVisitedModel, pagination);
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 浏览过的产品data
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/browseHistroy/data")
    public Object browseHistroyData(Pagination pagination, ProductFilter filter) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        filter.setDealerId(dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS, productViewLogService.selectViewLogProductPage(pagination, filter));
    }
    
    /**
    * 经销商申请加盟管理（申请中）index
    * @param model
    * @return
    */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/applying")
    public String apllyingIndex(Model model)
    {
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_apply";
    }
    
    /**
     * 经销商申请加盟管理（申请中）data
     * @param pagination
     * @param brandInviteModel
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/applying/data")
    public Object apllyingData(Pagination pagination, BrandInviteModel brandInviteModel) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandInviteModel.setDealerId(dealerId);
        pagination.setPageSize(10);
        brandInviteModel.setApplyState(brandInviteModel.getApplyState() == null ? 0 : brandInviteModel.getApplyState());
        PaginateResult<Map<String, Object>> page = brandInviteService.getInviteApplyStateList(pagination, brandInviteModel);
        return this.getJsonMessage(CommonConst.SUCCESS, page);
    }
    
    /**
     * 经销商申请加盟管理（邀请中的）index
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/inventing")
    public String inventingIndex(Model model)
    {
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "/dealer/copartner_invited";
    }
    
    /**
     * 经销商申请加盟管理（邀请中的）data
     * @param pagination
     * @param brandInviteModel
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/inventing/data")
    public JsonMessage inventingData(Pagination pagination, BrandInviteModel brandInviteModel) throws BusinessException
    {
        pagination.setPageSize(10);
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandInviteModel.setDealerId(dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS, brandInviteService.getInviteApplyStateList(pagination, brandInviteModel));
    }
    
    /**
     * 经销商申请加盟管理（我撤销的） index
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/disApply")
    public String disApplyIndex(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("dealerUserm", userInfoService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.getDealerInfo(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_disapply";
    }
    
    /**
     * 经销商申请加盟管理（我撤销的） data
     * @param pagination
     * @param brandInviteModel
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/disApply/data")
    public Object disApplyData(Pagination pagination, BrandInviteModel brandInviteModel) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandInviteModel.setDealerId(dealerId);
        pagination.setPageSize(10);
        brandInviteModel.setApplyState(brandInviteModel.getApplyState() == null ? 0 : brandInviteModel.getApplyState());
        PaginateResult<Map<String, Object>> page = brandInviteService.getInviteApplyStateList(pagination, brandInviteModel);
        return this.getJsonMessage(CommonConst.SUCCESS, page);
    }
    
    /**
     * 经销商申请加盟管理（未审核通过的） index data
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/noPass")
    public String noPassIndex(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("dealerUserm", userInfoService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.getDealerInfo(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "dealer/copartner_nopass";
    }
    
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/noPass/data")
    public Object noPassData(Pagination pagination, BrandInviteModel brandInviteModel) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        brandInviteModel.setDealerId(dealerId);
        pagination.setPageSize(10);
        brandInviteModel.setApplyState(brandInviteModel.getApplyState() == null ? 0 : brandInviteModel.getApplyState());
        PaginateResult<Map<String, Object>> page = brandInviteService.getInviteApplyStateList(pagination, brandInviteModel);
        return this.getJsonMessage(CommonConst.SUCCESS, page);
    }
    
    /* =========================================经销商 申请加盟管理 end================================================ */
    /* =========================================经销商 品牌收藏夹 begin================================================ */
    /**
     *  经销商 品牌收藏夹 index
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/brandsCollect")
    public String collectIndex(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("dealerUserm", userInfoService.selectByPrimaryKey(dealerId));
        model.addAttribute("dealerInfo", dealerInfoService.getDealerInfo(dealerId));
        model.addAttribute("companyScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_COMPANY_SCOPE));
        model.addAttribute("turnoverScope", dataDictValueService.findByDictCode(DataDictConstant.BRAND_TURNOVER));
        return "/dealer/copartner_favorite";
    }
    
    /**
     * 收藏的品牌列表数据
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/brandsCollect/data")
    public Object collectData(Pagination pagination, DealerCollectModel DealerCollectModel) throws BusinessException
    {
        pagination.setPageSize(10);
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerCollectModel.setDealerId(dealerId);
        PaginateResult<Map<String, Object>> page = dealerCollectService.getDealerCollectsBy(pagination, DealerCollectModel);
        return this.getJsonMessage(CommonConst.SUCCESS, page);
    }
    
    /* =========================================经销商 品牌收藏夹 end================================================ */
    /**
     * 显示已加盟品牌库
     *
     * @param page
     * @param model
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/copartner/brandes")
    public String copartnerBrandes(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerInfoModel dealerInfo = dealerInfoService.findById(dealerId);
        model.addAttribute("dealerInfo", dealerInfo);
        return "dealer/copartner_brandes";
    }
    
    /**
     * 查询已加盟品牌库
     *
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/copartner/brandes/data")
    public Object copartnerBrandesData(Pagination page, DealerJoinView dealerJoinView) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        PaginateResult<Map<String, Object>> findBrands = dealerJoinService
                .findJoinByDealerId(dealerId, dealerJoinView.getBrandName(), dealerJoinView.getBrandsName(), page);
        return this.getJsonMessage(CommonConst.SUCCESS, findBrands);
    }
    
    /**
     * 已加盟品牌库导出excel
     *
     * @param dealerJoinView
     * @param model
     * @return
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/copartner/brandes/list.xls", method = RequestMethod.GET)
    public ModelAndView exportExcel(Model model, DealerJoinView dealerJoinView) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        DealerInfoModel dealerInfo = dealerInfoService.findById(dealerId);
        model.addAttribute("dealerInfo", dealerInfo);
        List<Map<String, Object>> findBrands = dealerJoinService.findJoinByDealerId(dealerId, dealerJoinView.getBrandName(), dealerJoinView.getBrandsName());
        ModelAndView mav = new ModelAndView(new DealerJoinBrandExcelView());
        mav.addObject("list", findBrands);
        return mav;
    }
}
