/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.BrandsDomainConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandsDomain;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.brand.service.BrandsDomainService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * 品牌域名 控制器
 * <p>File：BrandsDomainController.java </p>
 * <p>Title: BrandsDomainController </p>
 * <p>Description:BrandsDomainController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
public class BrandsDomainController extends GenericController
{
    @Autowired(required = true)
    private BrandsDomainService brandsDomainService;
    
    @Autowired(required = true)
    private BrandesInfoService  brandesInfoService;
    
    /**
     * 域名设置页面
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/brand/brandsDomain/domain")
    public String index(Model model)
    {
        UserPrincipal user = OnLineUserUtils.getPrincipal();
        String _brandId = user.getRefrenceId();
        List<Map<String, Object>> brandsDomains = brandsDomainService.getBrandsDomainsByBrandId(_brandId);
        model.addAttribute("brandsDomains", brandsDomains);
        return "brand/domainName_set";
    }
    
    /**
     * 修改域名
     * @param brandsDomain
     * @return
     */
    @RequestMapping(value = "/brand/brandsDomain/updateDomain")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage updateDomain(BrandsDomain brandsDomain)
    {
        if (StringUtils.isBlank(brandsDomain.getRefrenceId()) || StringUtils.isBlank(brandsDomain.getDomain())) { return super.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        UserPrincipal user = OnLineUserUtils.getPrincipal();
        String _brandId = user.getRefrenceId();
        brandsDomain.setBrandId(_brandId);
        if (brandsDomainService.updateBrandsDomainValue(brandsDomain)) { return super.getJsonMessage(CommonConst.SUCCESS); }
        return super.getJsonMessage(CommonConst.FAIL);
    }
    
    /**
     * 搜索域名是否存在
     * @param domain
     * @return
     */
    @RequestMapping(value = "/brand/brandsDomain/searchDomain/{domain}")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage searchDomain(@PathVariable String domain)
    {
        if (!brandsDomainService.isExistDomains("", domain)) { return super.getJsonMessage(CommonConst.SUCCESS); }
        return super.getJsonMessage(CommonConst.FAIL);
    }
    
    /**
     * nginx 二级域名友好转向入口
     * @param domain
     * @param location
     * @param newid
     * @param request
     * @param response
     * @param model
     * @return 
     */
    @RequestMapping(value = "/market/subdomain")
    public Object forward(String domain, String location, String newid, HttpServletRequest request, HttpServletResponse response, Model model)
    {
        BrandsDomain brandsDomain = brandsDomainService.findByDomain(domain); // 根据二级域名获取品牌ID
        // 未找到品牌
        if (brandsDomain == null)
        {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ModelAndView();
        }
        String brandsId = brandsDomain.getBrandsId();
        String brandId = brandsDomain.getBrandId();
        StringBuilder forward = new StringBuilder("forward:/market/brand");
        if (StringUtils.isBlank(location) || ZttxConst.INDEX.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.INDEX).append("/").append(brandsId);
        }
        else if (ZttxConst.RECRUIT.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.RECRUIT).append("/").append(brandsId).append("/").append(brandId);
        }
        else if (ZttxConst.VISUAL.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.VISUAL).append("/").append(brandsId).append("/").append(brandId);
        }
        else if (ZttxConst.PRODUCT.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.PRODUCT).append("/").append(brandsId).append("/").append(brandId);
        }
        else if (ZttxConst.COMPANY.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.COMPANY).append("/").append(brandsId).append("/").append(brandId);
        }
        else if (ZttxConst.NETWORK.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.NETWORK).append("/").append(brandsId).append("/").append(brandId);
        }
        else if (ZttxConst.NEWS.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.NEWS).append("/").append(brandsId).append("/").append(brandId);
        }
        else if (ZttxConst.DOCUMENT.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.DOCUMENT).append("/").append(brandsId).append("/").append(brandId);
        }
        /*
         * 2.0 版本砍掉
         * else if (ZttxConst.DEAL.equalsIgnoreCase(location))
         * {
         * forward.append("/").append(DomainFilter.DEAL).append("/").append(brandsId);
         * }
         * else if (ZttxConst.PRODUCT_PHOTOS.equalsIgnoreCase(location))
         * {
         * forward.append("/").append(DomainFilter.PRODUCT_PHOTOS).append("/").append(brandsId).append("/").append(brandsId);
         * }
         */
        else if (ZttxConst.NEWSINFO.equalsIgnoreCase(location))
        {
            forward.append("/").append(BrandsDomainConst.NEWSINFO).append("/").append(brandsId).append("/").append(brandId).append("/").append(newid);
        }
        else
        {
            forward.setLength(0);
            forward.append("forward:" + location);
        }
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString))
        {
            String[] strAry = queryString.split("&");
            Integer len = strAry.length;
            if (len > 2)
            {
                for (int i = 2; i < len; i++)
                {
                    String[] paramStrAry = strAry[i].split("=");
                    String paramValue = "";
                    if (paramStrAry.length > 1)
                    {
                        paramValue = paramStrAry[1];
                    }
                    model.addAttribute(paramStrAry[0], paramValue);
                }
            }
            /*
             * if(StringUtils.isNotBlank(sb)) { queryString = sb.toString().substring(1); forward.append("?").append(queryString); }
             */
        }
        return forward.toString();
    }
}
