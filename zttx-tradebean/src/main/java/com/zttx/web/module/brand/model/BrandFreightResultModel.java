/*
 * @(#)BrandFreightResultModel.java 2014-12-23 下午2:11:22
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import com.zttx.web.consts.BrandConstant;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>File：BrandFreightResultModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-23 下午2:11:22</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class BrandFreightResultModel
{
    private Integer    carryType;
    
    private String     carryName;
    
    private BigDecimal freightAmount;
    
    public BrandFreightResultModel()
    {
    }
    
    public BrandFreightResultModel(Integer carryType, String carryName, BigDecimal freightAmount)
    {
        this.carryType = carryType;
        this.carryName = carryName;
        this.freightAmount = freightAmount;
    }
    
    public static BrandFreightResultModel getFreeFreight()
    {
        BrandFreightResultModel resultModel = new BrandFreightResultModel();
        resultModel.setCarryType(BrandConstant.BrandFreight.CARRY_TYPE_FREE_FREIGHT);
        resultModel.setCarryName(BrandConstant.BrandFreight.CARRY_NAME_FREE_FREIGHT);
        resultModel.setFreightAmount(BigDecimal.ZERO);
        return resultModel;
    }
    
    public static BrandFreightResultModel getCleanFreight()
    {
        BrandFreightResultModel resultModel = new BrandFreightResultModel();
        resultModel.setCarryType(BrandConstant.BrandFreight.CARRY_TYPE_CLEAN_FREIGHT);
        resultModel.setCarryName(BrandConstant.BrandFreight.CARRY_NAME_CLEAN_FREIGHT);
        resultModel.setFreightAmount(BigDecimal.ZERO);
        return resultModel;
    }
    
    public static BrandFreightResultModel getCleanCollect()
    {
        BrandFreightResultModel resultModel = new BrandFreightResultModel();
        resultModel.setCarryType(BrandConstant.BrandFreight.CARRY_TYPE_CLEAN_COLLECT);
        resultModel.setCarryName(BrandConstant.BrandFreight.CARRY_NAME_CLEAN_COLLECT);
        resultModel.setFreightAmount(BigDecimal.ZERO);
        return resultModel;
    }
    
    public static BrandFreightResultModel getResultModel(List<BrandFreightResultModel> resultModelList, Integer carryType)
    {
        for (BrandFreightResultModel resultModel : resultModelList)
            if (carryType.equals(resultModel.getCarryType())) return resultModel;
        return null;
    }
    
    public void add(BrandFreightResultModel resultModel)
    {
        this.freightAmount = null == this.freightAmount ? BigDecimal.ZERO : this.freightAmount;
        BigDecimal freightAmount = null == resultModel.getFreightAmount() ? BigDecimal.ZERO : resultModel.getFreightAmount();
        this.freightAmount = this.freightAmount.add(freightAmount);
    }
    
    public String getCarryName()
    {
        return carryName;
    }
    
    public void setCarryName(String carryName)
    {
        this.carryName = carryName;
    }
    
    public BigDecimal getFreightAmount()
    {
        return freightAmount;
    }
    
    public void setFreightAmount(BigDecimal freightAmount)
    {
        this.freightAmount = freightAmount;
    }
    
    public Integer getCarryType()
    {
        return carryType;
    }
    
    public void setCarryType(Integer carryType)
    {
        this.carryType = carryType;
    }
}
