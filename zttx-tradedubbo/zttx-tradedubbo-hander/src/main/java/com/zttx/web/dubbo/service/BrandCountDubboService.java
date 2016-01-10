/*
 * @(#)BrandCountDubboService.java 2015-8-24 上午10:32:16
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：BrandCountDubboService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-24 上午10:32:16</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public interface BrandCountDubboService
{
    void modifyBrandCountCache(String brandId, Integer[] types)throws BusinessException;
}
