package com.zttx.web.module.brand.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * <p>File：BrandController.java </p>
 * <p>Title: BrandController </p>
 * <p>Description: BrandController </p>
 * <p>Copyright: Copyright (c) 2014 08/11/2015 11:14</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/brand")
public class BrandController extends GenericController
{
    @Autowired
    private BrandCountService brandCountService;
    
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    /**
     * 品牌管理中心首页
     * @return
     */
    @RequestMapping(value = "/center")
    @RequiresPermissions(value = "brand:center")
    public String brandCenter(HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal brandInfo = OnLineUserUtils.getPrincipal();
        model.addAttribute("brandCount", brandCountService.selectByPrimaryKey(brandInfo.getRefrenceId()));
        model.addAttribute("stopJoinCount", 0);
        
        List<BrandsInfoModel> brandsInfo = brandesInfoService.getCooperatedBrandes(brandInfo.getRefrenceId());
        model.addAttribute("brandsInfos", brandsInfo);
        return "brand/index";
    }
    
    @RequestMapping("/myAccount")
    @RequiresPermissions("brand:center")
    public String myAccount(HttpServletRequest request, Model model)
    {
        return "brand/info_brandContact";
    }
}
