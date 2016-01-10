/*
 * @(#)DealerComplaintClientController.java 2014-6-7 上午9:20:08
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.dealer.entity.DealerComplaint;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerComplaintService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerComplaintClientController.java </p>
 * <p>Title: DealerComplaintClientController </p>
 * <p>Description: 经销商投诉信息管理接口 </p>
 * <p>Copyright: Copyright (c) 15/9/12</p>
 * <p>Company: 8637.com</p>
 *
 * @author 章旭楠
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerComplaint")
public class DealerComplaintClientController extends GenericController
{
    private final static Logger    logger = LoggerFactory.getLogger(DealerComplaintClientController.class);
    
    @Autowired
    private DealerComplaintService dealerComplaintService;
    
    @Autowired
    private BrandesInfoService     brandesInfoService;
    
    @Autowired
    private BrandInfoService       brandInfoService;
    
    @Autowired
    private DealerInfoService      dealerInfoService;
    
    /**
     * 列表（分页）
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealerComplaint dealerComplaint = new DealerComplaint();
        dealerComplaint.setDealerName(MapUtils.getString(map, "dealerName"));
        dealerComplaint.setBrandsName(MapUtils.getString(map, "brandsName"));
        dealerComplaint.setComState(MapUtils.getShort(map, "comState"));
        dealerComplaint.setComplaintCause(MapUtils.getShort(map, "complaintCause"));
        dealerComplaint.setOrderNumber(MapUtils.getLong(map, "orderNumber"));
        PaginateResult<DealerComplaint> result = dealerComplaintService.searchByClient(dealerComplaint, page);
        return super.getJsonMessage(ClientConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view")
    public JsonMessage view(ClientParameter param)
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerComplaint dealerComplaint = dealerComplaintService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(dealerComplaint.getBrandsId());
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerComplaint.getDealerId());
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(dealerComplaint.getBrandId());
        dealerComplaint.setBrandsName(null == brandesInfo ? "" : brandesInfo.getBrandsName());
        dealerComplaint.setDealerName(null == dealerInfo ? "" : dealerInfo.getDealerName());
        dealerComplaint.setBrandName(null == brandInfo ? "" : brandInfo.getComName());
        if (null == dealerComplaint) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, dealerComplaint);
    }
    
    /**
     * 处理申请
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/handApply")
    public JsonMessage handApply(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerComplaint dealerComplaint = new DealerComplaint();
        BeanUtils.populate(dealerComplaint, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, dealerComplaint))
        {
            dealerComplaintService.updateComState(dealerComplaint);
        }
        return jsonMessage;
    }
}
