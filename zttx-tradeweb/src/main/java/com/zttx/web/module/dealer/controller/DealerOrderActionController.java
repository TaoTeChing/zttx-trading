/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zttx.web.module.dealer.service.DealerOrderActionService;
import com.zttx.sdk.core.GenericController;

/**
 * 订单操作历史记录 控制器
 * <p>File：DealerOrderActionController.java </p>
 * <p>Title: DealerOrderActionController </p>
 * <p>Description:DealerOrderActionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerOrderAction")
public class DealerOrderActionController extends GenericController
{
    @Autowired(required = true)
    private DealerOrderActionService dealerOrderActionService;

}
