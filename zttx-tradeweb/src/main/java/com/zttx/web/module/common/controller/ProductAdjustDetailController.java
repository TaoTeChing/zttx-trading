/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.ProductAdjustDetail;
import com.zttx.web.module.common.service.ProductAdjustDetailService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 产品调价明细表 控制器
 * <p>File：ProductAdjustDetailController.java </p>
 * <p>Title: ProductAdjustDetailController </p>
 * <p>Description:ProductAdjustDetailController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/productAdjustDetail")
public class ProductAdjustDetailController extends GenericController
{
    @Autowired(required = true)
    private ProductAdjustDetailService productAdjustDetailService;
    @Autowired
    private BrandesInfoService brandesInfoService;

    @RequestMapping("")
    public String forward(Model model)throws BusinessException{
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<BrandsInfoModel> brandes = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandesList", brandes);// 品牌信息
        return "product/rebate_financial_detail";
    }
    
    @ResponseBody
    @RequestMapping("/ajax_list")
    public JsonMessage ajaxList(ProductAdjustDetail detail,Pagination page)throws BusinessException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            if (detail.getCreateTimeStart() != null)
            {
                Date start = sdf.parse(detail.getCreateTimeStart());
                detail.setCreateTimeStartLong(start.getTime());
            }
        }
        catch (ParseException e)
        {
        }
        try{
            if(detail.getCreateTimeEnd()!=null){
                Date end=sdf.parse(detail.getCreateTimeEnd());
                detail.setCreateTimeEndLong(end.getTime());
            }
        }catch(ParseException e){}
        
        try{
            if(detail.getEffectiveTimeStart()!=null){
                Date start=sdf.parse(detail.getEffectiveTimeStart());
                detail.setEffectiveTimeStartLong(start.getTime());
            }
        }catch(ParseException e){}
        try{
            if(detail.getEffectiveTimeEnd()!=null){
                Date end=sdf.parse(detail.getEffectiveTimeEnd());
                detail.setEffectiveTimeEndLong(end.getTime());
            }
        }catch(ParseException e){}
        detail.setPage(page);
        PaginateResult<Map<String, Object>> pageResult = productAdjustDetailService.search(detail);
        return this.getJsonMessage(CommonConst.SUCCESS, pageResult);
    }
}
