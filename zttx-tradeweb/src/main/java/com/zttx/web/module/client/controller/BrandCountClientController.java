/*
 * @(#)BrandCountClientController.java 2014 10 23 10:21:31
 * Copyright 2014 李飞欧, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandCount;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：BrandCountClientController.java</p>
 * <p>Title: 品牌商记数信息</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014 10 23 10:21:31</p>
 * <p>Company: 8637.com</p>
 * @author 李飞欧
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/brandCount")
public class BrandCountClientController extends GenericController
{
    @Autowired
    private BrandCountService brandCountService;
    
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public JsonMessage brandCount(HttpServletRequest request, ClientParameter parameter) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(parameter);
        String brandId = MapUtils.getString(map, "brandId");
        String count = MapUtils.getString(map, "viewDealerTotal");
        // 品牌商id 不允许为空
        if (StringUtils.isEmpty(brandId)) { throw new BusinessException("品牌商ID不能为空!"); }
        // 找不到品牌商
        BrandCount brandCount = brandCountService.selectByPrimaryKey(brandId);
        if (brandCount == null) { throw new BusinessException("该品牌商ID不存在!"); }
        int total = brandCount.getViewDealerTotal() == null ? 0 : brandCount.getViewDealerTotal();
        total = total == 0 ? BrandConstant.BrandCount.VIEW_TOTAL_COUNT : total;
        int used = brandCount.getViewDealerCount() == null ? 0 : brandCount.getViewDealerCount();
        if (StringUtils.isEmpty(count))
        { // ||StringUtils.equals(count, "0")
            Map<String, Object> retMap = Maps.newHashMap();
            retMap.put("total", total);
            retMap.put("brandId", brandId);
            retMap.put("rest", total - used);
            return this.getJsonMessage(CommonConst.SUCCESS, retMap);
        }
        // 数据为空 就返回该品牌商的
        if (!StringUtils.isNumeric(count) || Integer.parseInt(count) < 0) { throw new BusinessException("Count 格式不合法 or 值小于等于0"); }
        brandCount.setViewDealerTotal(Integer.parseInt(count));
        total = Integer.parseInt(count);
        brandCountService.updateByPrimaryKey(brandCount);
        Map<String, Object> retMap = Maps.newHashMap();
        retMap.put("total", total);
        retMap.put("brandId", brandId);
        retMap.put("rest", total - used);
        return this.getJsonMessage(CommonConst.SUCCESS, retMap);
    }
}
