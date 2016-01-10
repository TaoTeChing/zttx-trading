/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.module.brand.service.BrandAuditService;

/**
 * 品牌商审核日志 控制器
 * <p>File：BrandAuditController.java </p>
 * <p>Title: BrandAuditController </p>
 * <p>Description:BrandAuditController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/brandAudit")
public class BrandAuditController extends GenericController
{
    @Autowired(required = true)
    private BrandAuditService brandAuditService;
}
