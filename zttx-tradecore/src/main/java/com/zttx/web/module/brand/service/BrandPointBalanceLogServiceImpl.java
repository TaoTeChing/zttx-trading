/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.NumericUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.module.brand.entity.BrandPointBalanceLog;
import com.zttx.web.module.brand.mapper.BrandPointBalanceLogMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 扣点修改日志 服务实现类
 * <p>File：BrandPointBalanceLog.java </p>
 * <p>Title: BrandPointBalanceLog </p>
 * <p>Description:BrandPointBalanceLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandPointBalanceLogServiceImpl extends GenericServiceApiImpl<BrandPointBalanceLog> implements BrandPointBalanceLogService
{

    private BrandPointBalanceLogMapper brandPointBalanceLogMapper;

    @Autowired(required = true)
    public BrandPointBalanceLogServiceImpl(BrandPointBalanceLogMapper brandPointBalanceLogMapper)
    {
        super(brandPointBalanceLogMapper);
        this.brandPointBalanceLogMapper = brandPointBalanceLogMapper;
    }
    
    /**
     * 记录新增内容
     * @author 陈建平
     * @param brandsId
     * @param operName
     * @param joinForm
     * @param point
     * @throws BusinessException
     */
    @Override
    public void executeAddLog(String brandsId, String operName, short joinForm, BigDecimal point) throws BusinessException
    {
    	String pointName = "";
    	if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_XK){
    		pointName = "现款";
    	}else if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_SX){
    		pointName = "授信";
    	}else if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_FD){
    		pointName = "返点";
    	}
        StringBuffer logBuffer = new StringBuffer(operName);
        logBuffer.append("在").append(CalendarUtils.getCurrentDate("yyyy年MM月dd日")).append("，设置了").append(pointName).append("的佣金")
                .append(NumericUtils.formatDown(point, 2));
        this.insert(brandsId, logBuffer.toString());
    }
    
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
    @Override
    public void executeUpdLog(String brandsId, String operName, short joinForm, BigDecimal oldPoint, BigDecimal newPoint) throws BusinessException
    {
    	String pointName = "";
    	if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_XK){
    		pointName = "现款";
    	}else if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_SX){
    		pointName = "授信";
    	}else if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_FD){
    		pointName = "返点";
    	}
        StringBuffer logBuffer = new StringBuffer(operName);
        logBuffer.append("在").append(CalendarUtils.getCurrentDate("yyyy年MM月dd日")).append("将").append(pointName).append("的佣金从")
                .append(NumericUtils.formatDown(oldPoint, 2)).append("修改成").append(NumericUtils.formatDown(newPoint, 2));
        this.insert(brandsId, logBuffer.toString());
    }
    
    /**
     * 记录删除内容
     * @author 陈建平
     * @param brandsId
     * @param operName
     * @param joinForm
     * @throws BusinessException
     */
    @Override
    public void executeDelLog(String brandsId, String operName, short joinForm) throws BusinessException
    {
    	String pointName = "";
    	if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_XK){
    		pointName = "现款";
    	}else if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_SX){
    		pointName = "授信";
    	}else if(joinForm == BrandConstant.BrandPointBalance.JOINFORM_FD){
    		pointName = "返点";
    	}
        StringBuffer logBuffer = new StringBuffer(operName);
        logBuffer.append("在").append(CalendarUtils.getCurrentDate("yyyy年MM月dd日")).append("，删除了").append(pointName).append("的佣金");
        this.insert(brandsId, logBuffer.toString());
    }
    
    /**
     * 保存日志
     * @author 陈建平
     * @param brandsId
     * @param context
     */
    private void insert(String brandsId, String context)
    {
        BrandPointBalanceLog obj = new BrandPointBalanceLog();
        obj.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        obj.setBrandsId(brandsId);
        obj.setContent(context);
        obj.setCreateTime(CalendarUtils.getCurrentLong());
        obj.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
        brandPointBalanceLogMapper.insert(obj);
    }
}
