/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zttx.web.module.common.service.FinancialPayService;
import com.zttx.sdk.core.GenericController;

/**
 * 财务账支付记录表 控制器
 * <p>File：FinancialPayController.java </p>
 * <p>Title: FinancialPayController </p>
 * <p>Description:FinancialPayController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/financialPay")
public class FinancialPayController extends GenericController
{
    @Autowired(required = true)
    private FinancialPayService financialPayService;

}
