/*
 * @(#)OrderPayModel.java 2014-11-29 上午10:45:41
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import java.math.BigDecimal;

/**
 * <p>File：OrderPayModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-29 上午10:45:41</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class OrderPayModel
{
    private BigDecimal curPayAmount;        // 本次支付的金额
    
    private BigDecimal curFreightPaidAmount; // 本次支付的运费金额
    
    private BigDecimal curGoodsPaidAmount;  // 本次支付的货款金额
    
    private Boolean    isFreightPaid;       // 支付后运费是否付清
    
    private Boolean    isGoodsPaid;         // 支付后货款是否付清
    
    private Boolean    isFirstPay;          // 是否是第一次付款
    
    public Boolean getIsFreightPaid()
    {
        return isFreightPaid;
    }
    
    public void setIsFreightPaid(Boolean isFreightPaid)
    {
        this.isFreightPaid = isFreightPaid;
    }
    
    public Boolean getIsGoodsPaid()
    {
        return isGoodsPaid;
    }
    
    public void setIsGoodsPaid(Boolean isGoodsPaid)
    {
        this.isGoodsPaid = isGoodsPaid;
    }
    
    public Boolean getIsFirstPay()
    {
        return isFirstPay;
    }
    
    public void setIsFirstPay(Boolean isFirstPay)
    {
        this.isFirstPay = isFirstPay;
    }
    
    public BigDecimal getCurPayAmount()
    {
        return curPayAmount;
    }
    
    public void setCurPayAmount(BigDecimal curPayAmount)
    {
        this.curPayAmount = curPayAmount;
    }
    
    public BigDecimal getCurFreightPaidAmount()
    {
        return curFreightPaidAmount;
    }
    
    public void setCurFreightPaidAmount(BigDecimal curFreightPaidAmount)
    {
        this.curFreightPaidAmount = curFreightPaidAmount;
    }
    
    public BigDecimal getCurGoodsPaidAmount()
    {
        return curGoodsPaidAmount;
    }
    
    public void setCurGoodsPaidAmount(BigDecimal curGoodsPaidAmount)
    {
        this.curGoodsPaidAmount = curGoodsPaidAmount;
    }
}
