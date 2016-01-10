/*
 * @(#)DepositClientController.java 2015-4-21 上午11:05:29
 * Copyright 2015 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.math.BigDecimal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.NumericUtils;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DepositClientController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-21 上午11:05:29</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/deposit")
public class DepositClientController extends GenericController
{
    @Autowired
    private DealerJoinService dealerJoinService;
    
    /**
     * 工厂店押金列表
     * @author 张昌苗
     */
    @RequestMapping("/depositList")
    @ResponseBody
    public JsonMessage depositList(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DealerJoin filter = new DealerJoin();
        PaginateResult<Map<String, Object>> paginateResult = this.dealerJoinService.selectUnClearingDepositList(page, filter);
        return super.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 多个品牌商批量打款
     * @author 张昌苗
     */
    @RequestMapping("/depositManyPay")
    @ResponseBody
    public JsonMessage depositManyPay(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String refrenceIdStr = MapUtils.getString(map, "refrenceId");
        String payAmountStr = MapUtils.getString(map, "payAmount");
        if (StringUtils.isBlank(refrenceIdStr) || StringUtils.isBlank(payAmountStr)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        String[] refrenceIdArr = refrenceIdStr.split(ClientConstant.ARR_SPLIT);
        BigDecimal[] payAmountArr = NumericUtils.parseBigDecimalArr(payAmountStr.split(ClientConstant.ARR_SPLIT));
        this.dealerJoinService.updateDepositClearingAmount(refrenceIdArr, payAmountArr);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
