/*
 * @(#)OrderPayCallbackService.java 2014-11-28 下午1:32:17
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.math.BigDecimal;

import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：OrderPayCallbackService.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-28 下午1:32:17</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public interface OrderPayCallbackService
{
    /**
     * 处理支付平台回调
     * @param payId
     * @param state
     * @param amount
     * @throws BusinessException
     * @author 张昌苗
     */
    public void executeDealWith(Long payId, Integer state, BigDecimal amount) throws BusinessException;
}
