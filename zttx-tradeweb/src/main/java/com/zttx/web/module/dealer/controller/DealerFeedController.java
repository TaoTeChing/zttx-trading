/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.dealer.entity.DealerFeed;
import com.zttx.web.module.dealer.service.DealerFeedService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商反馈信息 控制器
 * <p>File：DealerFeedController.java </p>
 * <p>Title: DealerFeedController </p>
 * <p>Description:DealerFeedController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerFeed")
public class DealerFeedController extends GenericController
{
    @Autowired(required = true)
    private DealerFeedService  dealerFeedService;
    
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    /**
     * 进入产品管理中心的经销商反馈内容列表
     * @return
     * @author 周光暖
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/list")
    public String list(ModelMap map) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<BrandsInfoModel> brandesInfoList = brandesInfoService.getCooperatedBrandes(brandId);
        map.put("brandesInfoList", brandesInfoList);
        return "brand/list_dealerFeed";
    }
    
    /**
     * 进入产品管理中心的经销商反馈内容列表
     *
     * @param pagination
     * @return
     * @author 周光暖
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = ApplicationConst.BRAND + "/ajaxList")
    public JsonMessage ajaxList(Pagination pagination, String brandsId) throws BusinessException
    {
        pagination.setPageSize(5);
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        PaginateResult<DealerFeed> feeds = dealerFeedService.listByBrandIdAndBrandsId(pagination, brandId, brandsId);
        return super.getJsonMessage(BrandConst.SUCCESS, feeds);
    }
}
