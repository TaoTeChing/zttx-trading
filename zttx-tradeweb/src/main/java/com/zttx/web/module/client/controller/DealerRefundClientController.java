/*
 * @(#)DealerRefundClientController.java 2015 1 19 13:06:40
 * Copyright 2015 李飞欧, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.entity.DealerRefund;
import com.zttx.web.module.dealer.model.DealerRefundModel;
import com.zttx.web.module.dealer.service.DealerRefundService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DealerRefundClientController.java</p>
 * <p>Title: 退款</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015 1 19 13:06:40</p>
 * <p>Company: 8637.com</p>
 * @author 李星
 * @version 2.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerRefund")
public class DealerRefundClientController extends GenericController
{
    @Autowired
    private DealerRefundService dealerRefundService;
    
    /**
     * 同步退款信息
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/dealerRefunds_crm", method = RequestMethod.POST)
    public JsonMessage getDealerRefunds(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination pagination = new Pagination();
        DealerRefundModel dealerRefund = new DealerRefundModel();
        BeanUtils.populate(pagination, map);
        BeanUtils.populate(dealerRefund, map);
        if (null == dealerRefund.getBeginTime()) return super.getJsonMessage(CommonConst.FAIL, "开始时间不能为空");
        PaginateResult<DealerRefund> result = dealerRefundService.getDealerRefundList4Boss(dealerRefund, pagination);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
