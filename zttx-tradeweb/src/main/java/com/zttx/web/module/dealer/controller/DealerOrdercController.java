/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DataDictConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.module.dealer.service.DealerOrdercService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商产品进货计数 控制器
 * <p>File：DealerOrdercController.java </p>
 * <p>Title: DealerOrdercController </p>
 * <p>Description:DealerOrdercController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer")
public class DealerOrdercController extends GenericController
{
    @Autowired
    private DealerOrdercService  dealerOrdercService;
    
    @Autowired
    private DealerJoinService    dealerJoinService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    /**
     * 授权产品库
     *
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/shopper/orderc", method = RequestMethod.GET)
    public String index(Model model) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        model.addAttribute("sortTypeList", dataDictValueService.findByDictCode(DataDictConstant.DEAER_SEARCH_SORT));
        model.addAttribute("joinedBrandList", dealerJoinService.findByDealerId(dealerId, DealerConstant.DealerJoin.COOPERATED));
        return "dealer/shopper_orderc";
    }
    
    /**
     * 分页时候以Ajax实行
     *
     * @param pagination
     * @param filter
     * @return
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/shopper/orderc/list", method = RequestMethod.GET)
    public JsonMessage listOrdercs(Pagination pagination, ProductFilter filter) throws BusinessException
    {
        pagination.setPageSize(ApplicationConst.DEFAULT_PAGE_SIZE);
        if (StringUtils.isBlank(filter.getBrandsId()))
        {
            filter.setBrandsId(null);
        }
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        PaginateResult<Map<String, Object>> pageResult = dealerOrdercService.findOrdercProductsByDealerId(dealerId, pagination, filter);
        return this.getJsonMessage(CommonConst.SUCCESS, pageResult);
    }
}
