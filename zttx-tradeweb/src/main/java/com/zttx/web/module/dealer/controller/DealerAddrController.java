/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.module.dealer.entity.DealerAddr;
import com.zttx.web.module.dealer.service.DealerAddrService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商地址信息 控制器
 * <p>File：DealerAddrController.java </p>
 * <p>Title: DealerAddrController </p>
 * <p>Description:DealerAddrController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerAddr")
public class DealerAddrController extends GenericController
{
    @Autowired(required = true)
    private DealerAddrService dealerAddrService;
    
    /**
     * 地址管理首页
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/account/address")
    public String addressIndex()
    {
        return "dealer/account_address";
    }
    
    /**
     * 地址管理列表数据
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/address/list")
    public JsonMessage list(HttpServletRequest request, Pagination pagination) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        if (StringUtils.isBlank(dealerId)) { return this.getJsonMessage(ExceptionConst.EXPIRES); }
        PaginateResult<DealerAddr> result = dealerAddrService.list(pagination, dealerId);
        return this.getJsonMessage(ExceptionConst.SUCCESS, result);
    }
    
    /**
     * 获取经销商收获地址 data
     * @return
     * @author 易永耀
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public JsonMessage dealerAddrData() throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        List<DealerAddr> dealerAddrList = dealerAddrService.getAllDealerAddrsList(dealerId);
        return this.getJsonMessage(CommonConst.SUCCESS, dealerAddrList);
    }
    
    /**
     * 保存收货地址
     * @param dealerAddr
     * @return {@link JsonMessage}
     * @throws BusinessException
     * @author 章旭楠
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/save")
    public JsonMessage saveDealerAddr(DealerAddr dealerAddr)
    {
        String areaNo = dealerAddrService.getAreaNo(dealerAddr.getProvince(), dealerAddr.getCity(), dealerAddr.getCounty());
        if (areaNo != null && StringUtils.isNotBlank(areaNo) && !areaNo.equals("0"))
        {
            dealerAddr.setDealerAddr(Integer.parseInt(areaNo));
        }
        JsonMessage result = new JsonMessage(CommonConst.SUCCESS);
        if (beanValidator(result, dealerAddr))
        {
            try
            {
                dealerAddr.setDealerId(OnLineUserUtils.getCurrentDealer().getRefrenceId());
                dealerAddrService.save(dealerAddr);
            }
            catch (BusinessException e)
            {
                result.setCode(e.getErrorCode().getCode());
                result.setMessage(e.getErrorCode().getMessage());
            }
        }
        return result;
    }
    
    /**
     * 设置默认收货地址
     * @param addrId 地址id
     * @return {@link JsonMessage} json代码
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping("/setDef")
    public JsonMessage setDefAddr(String addrId)
    {
        JsonMessage result = new JsonMessage();
        Integer code = CommonConst.FAIL.code;
        try
        {
            dealerAddrService.setDefaultAddress(addrId, OnLineUserUtils.getCurrentDealer().getRefrenceId());
            code = CommonConst.SUCCESS.code;
        }
        catch (BusinessException e)
        {
            result.setMessage(e.getMessage());
        }
        result.setCode(code);
        return result;
    }
    
    /**
     * 加载经销商地址
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/address/load", method = RequestMethod.POST)
    public JsonMessage load(@RequestParam("uuid") String refrenceId)
    {
        DealerAddr result = dealerAddrService.selectByPrimaryKey(refrenceId);
        if (result == null) { return this.getJsonMessage(ExceptionConst.NOEXITS); }
        return this.getJsonMessage(ExceptionConst.SUCCESS, "加载成功！", result);
    }
    
    /**
     * 删除经销商地址
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/address/delete", method = RequestMethod.POST)
    public JsonMessage delete(@RequestParam("uuid") String refrenceId) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        if (StringUtils.isBlank(refrenceId)) { return this.getJsonMessage(ExceptionConst.FAILURE); }
        int code = dealerAddrService.delete(refrenceId, dealerId);
        return this.getJsonMessage(code);
    }
}
