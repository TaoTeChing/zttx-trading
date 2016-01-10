package com.zttx.web.module.fronts.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandCount;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.module.brand.service.BrandFavoriteService;
import com.zttx.web.module.brand.service.BrandViewContactService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.entity.Regional;
import com.zttx.web.module.common.entity.RegionalCode;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.RegionalCodeService;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.module.dealer.entity.*;
import com.zttx.web.module.dealer.service.*;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.CookieUtils;
import com.zttx.web.utils.FileClientUtil;

/**
 * <p>File：TerminalController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-8-21 下午4:32:40</p>
 * <p>Company: 8637.com</p>
 * @author 李星
 * @version 2.0
 */
@Controller
@RequestMapping(value = ApplicationConst.SHOP)
public class TerminalController extends GenericBaseController
{
    @Autowired
    private RegionalService             regionalService;
    
    @Autowired
    private DealerInfoService           dealerInfoService;
    
    @Autowired
    private DealerShopEnvService        dealerShopEnvService;
    
    @Autowired
    private RegionalCodeService         regionalCodeService;
    
    @Autowired
    private DealerImageService          dealerImageService;
    
    @Autowired
    private DealerShopEnvTempService    dealerShopEnvTempService;
    
    @Autowired
    private DealerShopEnvImgTempService dealerShopEnvImgTempService;
    
    @Autowired
    private DealerJoinService           dealerJoinService;
    
    @Autowired
    private BrandCountService           brandCountService;
    
    @Autowired
    private BrandFavoriteService        brandFavoriteService;
    
    @Autowired
    private BrandViewContactService     brandViewContactService;
    
    @Autowired
    private BrandesInfoService          brandesInfoService;
    
    @RequestMapping(value = "")
    public String inPage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        return "redirect:/shop/default";
    }
    
    /**默认*/
    @RequestMapping(value = "default")
    public String terminalDefault(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 1 本地浏览记录
        String city = CookieUtils.get(request, "8637_city");
        // 2 IP显示的地区
        if (StringUtils.isBlank(city))
        {
            Regional regional = regionalService.getRegionalByRequest(request);
            if (null != regional)
            {
                RegionalCode regionalCode = regionalCodeService.getRegionalCode(null, regional.getAreaNo().toString(), null);
                city = regionalCode.getAreaName();
            }
        }
        // 3 默认区域
        if (StringUtils.isBlank(city))
        {
            city = "010";
        }
        return "redirect:" + city;
    }
    
    /** 终端商店铺显示列表  */
    @RequestMapping(value = "{location}")
    public String terminalIndex(@PathVariable String location, DealerShopEnv dealerShopEnv, Pagination pagination, Model model, HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
        // 地区要转换
        RegionalCode regionalCode = regionalCodeService.getRegionalCode(null, null, location);
        if (regionalCode == null) { throw new BusinessException("访问的地址URL不存在"); }
        int regionCode = Integer.parseInt(regionalCode.getAreaNos());
        boolean bTemp = false;
        if (dealerShopEnv.getAreaNo() == null || dealerShopEnv.getAreaNo() == 0)
        {
            bTemp = true;
            dealerShopEnv.setAreaNo(regionCode);
        }
        List<Regional> regionals = regionalService.getSubRegionalByCode(regionCode);
        model.addAttribute("regionals", regionals);
        PaginateResult<DealerShopEnv> majorResults = dealerShopEnvService.getDealerShopEnvsBy(dealerShopEnv, pagination);
        List<DealerShopEnv> dealerShopEnvs = dealerShopEnvService.getTopNewestDealerShopEnvs(10);
        model.addAttribute("dealerShopEnvs", dealerShopEnvs);
        model.addAttribute("results1", majorResults);
        if (majorResults.getPage().getTotalCount() < 20)
        {
            PaginateResult<DealerShopEnv> minorResults = dealerShopEnvService.getExcludeDealerShopEnvsBy(dealerShopEnv.getAreaNo(), new Pagination(1, 4));
            model.addAttribute("results2", minorResults);
        }
        model.addAttribute("dealerShopEnv", dealerShopEnv);
        model.addAttribute("cityName", regionalService.getNameByAreaNo(dealerShopEnv.getAreaNo() / 100 * 100));
        dealerShopEnv.setAreaNo(bTemp ? 0 : dealerShopEnv.getAreaNo());
        CookieUtils.put(request, response, "8637_city", location);
        UserPrincipal user = OnLineUserUtils.getPrincipal();
        if (null == user) model.addAttribute("hasLogon", false);
        else
        {
            model.addAttribute("hasLogon", true);
            List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(user.getRefrenceId());
            model.addAttribute("brandsInfos", brandsInfos);

            Boolean isExits = brandViewContactService.insertWithCheck(user.getRefrenceId(), dealerShopEnv.getDealerId(), false);
            model.addAttribute("isExits", isExits);
        }
        return "terminal/index";
    }
    
    /** 终端商店铺详细页面 */
    @Deprecated
    @RequestMapping(value = "details_/{id}")
    public String terminalDetails(@PathVariable String id, Model model) throws Exception
    {
        dealerShopEnvService.modDealerShopEnvViewCount(id, 1);
        DealerShopEnv dealerShopEnv = dealerShopEnvService.selectByPrimaryKey(id);
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerShopEnv.getDealerId());
        List<DealerShopEnv> dealerShopEnvs = dealerShopEnvService.getTopNewestDealerShopEnvs(6);
        List<DealerImage> dealerImages = dealerImageService.selectDealerImageByDealerId(dealerShopEnv.getDealerId());
        model.addAttribute("dealerShopEnvs", dealerShopEnvs);
        model.addAttribute("dealerShopEnv", dealerShopEnv);
        model.addAttribute("dealerInfo", dealerInfo);
        model.addAttribute("dealerImages", dealerImages);
        return "terminal/details";
    }
    
    /** 终端商店铺详细页面 */
    @RequestMapping(value = "details/{id}")
    public String terminalDetailsExt(@PathVariable String id, Model model, HttpServletRequest request) throws Exception
    {
        dealerShopEnvService.modDealerShopEnvViewCount(id, 1);
        DealerShopEnv dealerShopEnv = dealerShopEnvService.selectByPrimaryKey(id);
        if (null == dealerShopEnv) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerShopEnv.getDealerId());
        List<DealerShopEnv> dealerShopEnvs = dealerShopEnvService.getTopNewestDealerShopEnvs(6);
        List<DealerImage> dealerImages = dealerImageService.selectDealerImageByDealerId(dealerShopEnv.getDealerId());
        model.addAttribute("dealerShopEnvs", dealerShopEnvs);
        model.addAttribute("dealerShopEnv", dealerShopEnv);
        model.addAttribute("dealerInfo", dealerInfo);
        model.addAttribute("dealerImages", dealerImages);
        List<Map<String, Object>> joinedBrandes = dealerJoinService.findByDealerId(dealerShopEnv.getDealerId(), DealerConstant.DealerJoin.COOPERATED);
        model.addAttribute("dealerJoinBrandesNum", joinedBrandes == null ? 0 : joinedBrandes.size());
        boolean isAuth = false;
        String userId = this.getCurrentLoginUserId();
        if (StringUtils.isNotBlank(userId))
        {
            UserInfo b = userInfoService.selectByPrimaryKey(userId);
            if (null != b)
            {
                isAuth = true;
                if (b.getUserType().shortValue() == BrandConstant.userType.BRAND_TYPE)
                {
                    List<BrandsInfoModel> brandsInfos = brandesInfoService.getCooperatedBrandes(userId);
                    Boolean isExits = brandViewContactService.insertWithCheck(userId, dealerShopEnv.getDealerId(), false);
                    Boolean isCollected = brandFavoriteService.isCollected(userId, dealerShopEnv.getDealerId());
                    model.addAttribute("brandsInfos", brandsInfos);
                    model.addAttribute("isCollected", isCollected);
                    model.addAttribute("isBrand", true);
                    model.addAttribute("isExits", isExits);
                    if (isExits)
                    {
                        setUserMobile(model, dealerShopEnv);
                    }
                }
                else
                {
                    if (userId.equals(dealerShopEnv.getDealerId()))
                    {
                        model.addAttribute("isDealer", true);
                        setUserMobile(model, dealerShopEnv);
                    }
                }
            }
        }
        model.addAttribute("isAuth", isAuth);
        return "terminal/details_v2";
    }
    
    private void setUserMobile(Model model, DealerShopEnv dealerShopEnv)
    {
        UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerShopEnv.getDealerId());
        String userMobile = dealerUserm.getUserMobile();
        model.addAttribute("userMobile", userMobile);
    }
    
    /** 终端商店铺详细页面 */
    @ResponseBody
    @RequestMapping(value = "list/data")
    public JsonMessage shopList(DealerShopEnv dealerShopEnv, Pagination pagination) throws Exception
    {
        pagination.setPageSize(8);
        PaginateResult<DealerShopEnv> majorResults = dealerShopEnvService.getDealerShopEnvsBy(dealerShopEnv, pagination);
        // 强制取前8条(首页)
        majorResults.getPage().setTotalPage(1);
        return this.getJsonMessage(CommonConst.SUCCESS, majorResults);
    }
    
    /** 终端商店铺登记页面 */
    @RequestMapping(value = "collect")
    public Object collect() throws Exception
    {
        return "terminal/collect";
    }
    
    /** 终端商店铺登记页面 */
    @RequestMapping(value = "city")
    public Object city(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
    {
        // 1 默认区域
        String city = "0574";
        // 2 IP显示的地区
        RegionalCode regionalCode = regionalCodeService.getRegionalCode(null, null, city);
        Regional regional = regionalService.getRegionalByRequest(request);
        if (null != regional)
        {
            regionalCode = regionalCodeService.getRegionalCode(null, regional.getAreaNo().toString(), null);
            if (regionalCode != null)
            {
                city = regionalCode.getAreaName();
            }
        }
        if (regionalCode != null)
        {
            model.addAttribute("recity", Integer.valueOf(regionalCode.getAreaNos()) / 10000 * 10000);
            model.addAttribute("currcity", city);
        }
        return "terminal/city";
    }
    
    /**
     * 根据省份获取城市
     * @param areaNo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "citysInProvince")
    public List<Map<String, Object>> cityByProvince(Integer areaNo) throws Exception
    {
        return regionalService.getRegionalArea(areaNo);
    }

    /**
     * 根据城市名,电话区号获取城市信息
     * @param cityInfo
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "goToCity")
    public Map<String, Object> goToCity(String cityInfo) throws Exception {
        return regionalService.getRegionalAreaByInfo(cityInfo);
    }
    
    /** 终端商店铺信息保存 */
    @ResponseBody
    @RequestMapping(value = "collect/save")
    public JsonMessage save(DealerShopEnv dealerShopEnv, HttpServletRequest request) throws Exception
    {
        dealerShopEnv.getMobile();
        if (StringUtils.isBlank(dealerShopEnv.getMobile())) { throw new BusinessException("手机号不允许为空!"); }
        UserInfo dealerUserm = userInfoService.getByMobile(dealerShopEnv.getMobile());
        if (dealerUserm != null) { throw new BusinessException("该手机号已经被登记!"); }
        DealerShopEnvTemp dealerShopEnvTemp = new DealerShopEnvTemp();
        dealerShopEnvTemp.setCreateTime(CalendarUtils.getCurrentLong());
        dealerShopEnvTemp.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        dealerShopEnvTemp.setStatus(0);
        if (dealerShopEnv.getAreaNo() == null) throw new BusinessException("未选择地区");
        if (StringUtils.isNotEmpty(dealerShopEnv.getOpenTimeStr()))
        {
            dealerShopEnv.setOpenTime(CalendarUtils.getLongFromTime(dealerShopEnv.getOpenTimeStr(), "yyyy-MM-dd"));
        }
        String fullAreaName = regionalService.getFullNameByAreaNo(dealerShopEnv.getAreaNo(), regionalService.REGIONAL_SPLIT_CODE);
        if (StringUtils.isNotEmpty(fullAreaName))
        {
            dealerShopEnv.setAreaNo(dealerShopEnv.getAreaNo());
            dealerShopEnv.setCityNo(dealerShopEnv.getAreaNo() / 100 * 100);
            dealerShopEnv.setProvinceNo(dealerShopEnv.getAreaNo() / 10000 * 10000);
            String[] arrNames = fullAreaName.split("/");
            if (arrNames.length == 1)
            {
                dealerShopEnv.setProvinceName(arrNames[0]);
                dealerShopEnv.setCityName(arrNames[0]);
                dealerShopEnv.setAreaName(arrNames[0]);
            }
            if (arrNames.length == 2)
            {
                dealerShopEnv.setProvinceName(arrNames[0]);
                dealerShopEnv.setCityName(arrNames[1]);
                dealerShopEnv.setAreaName(arrNames[1]);
            }
            if (arrNames.length == 3)
            {
                dealerShopEnv.setProvinceName(arrNames[0]);
                dealerShopEnv.setCityName(arrNames[1]);
                dealerShopEnv.setAreaName(arrNames[2]);
            }
        }
        dealerShopEnvTemp.setDetail(JSONObject.toJSONString(dealerShopEnv));
        dealerShopEnvTempService.insert(dealerShopEnvTemp);
        if (dealerShopEnv.getPics() != null)
        {
            for (String picUrl : dealerShopEnv.getPics())
            {
                String dealerLogoPath = FileClientUtil.moveAndDeleteFile(ImageConst.DEALER_IMG_PATH, picUrl, UploadAttCateConst.DEALER_LOGO);
                DealerShopEnvImgTemp dealerShopEnvImgTemp = new DealerShopEnvImgTemp();
                dealerShopEnvImgTemp.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                dealerShopEnvImgTemp.setShopEnvId(dealerShopEnvTemp.getRefrenceId());
                dealerShopEnvImgTemp.setImagePath(dealerLogoPath);
                dealerShopEnvImgTempService.insert(dealerShopEnvImgTemp);
            }
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**查询品牌商当前可查看次数*/
    @ResponseBody
    @RequestMapping(value = "viewCount")
    public JsonMessage findViewCount() throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        BrandCount brandCount = brandCountService.selectByPrimaryKey(brandId);
        Integer viewCount = brandCountService.getViewDealerTotal(brandId);
        Integer viewDealerCount = viewCount.intValue() - brandCount.getViewDealerCount().intValue();
        return this.getJsonMessage(CommonConst.SUCCESS, viewDealerCount);
    }
}
