/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandPointBalanceLog;

/**
 * 扣点修改日志 服务接口
 * <p>File：BrandPointBalanceLogService.java </p>
 * <p>Title: BrandPointBalanceLogService </p>
 * <p>Description:BrandPointBalanceLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandPointBalanceLogService extends GenericServiceApi<BrandPointBalanceLog>{

	/**
     * 记录新增内容
     * @author 陈建平
     * @param brandsId
     * @param operName
     * @param joinForm
     * @param point
     * @throws BusinessException
     */
    void executeAddLog(String brandsId, String operName, short joinForm, BigDecimal point) throws BusinessException;
    
    /**
     * 记录修改内容
     * @author 陈建平
     * @param brandsId
     * @param operName
     * @param joinForm
     * @param oldPoint
     * @param newPoint
     * @throws BusinessException
     */
    void executeUpdLog(String brandsId, String operName, short joinForm, BigDecimal oldPoint, BigDecimal newPoint) throws BusinessException;
    
    /**
     * 记录删除内容
     * @author 陈建平
     * @param brandsId
     * @param operName
     * @param joinForm
     * @throws BusinessException
     */
    void executeDelLog(String brandsId, String operName, short joinForm) throws BusinessException;
}
