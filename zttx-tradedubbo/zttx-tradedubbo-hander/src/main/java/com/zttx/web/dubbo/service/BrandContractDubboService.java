/*
 * @(#)BrandContractDubboService.java 2015-9-2 上午10:32:20
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import java.util.Map;

/**
 * <p>File：BrandContractDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-2 上午10:32:20</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface BrandContractDubboService
{
    /**
     * 根据品牌商id获取联系信息
     * @param brandId
     * @return
     */
    Map<String,Object> getBrandContract(String brandId);
}
