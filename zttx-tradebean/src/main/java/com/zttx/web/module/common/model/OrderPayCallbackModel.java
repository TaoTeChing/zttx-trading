/*
 * @(#)OrderPayCallbackModel.java 2014-11-28 下午12:00:53
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import java.math.BigDecimal;

/**
 * <p>File：OrderPayCallbackModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-28 下午12:00:53</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class OrderPayCallbackModel
{
    private Long         id;     // 支付ID
    
    private String       sign;   // 签名
    
    private BigDecimal   amount; // 金额
    
    private Integer      state;  // 支付状态
    
    private Long[]       ids;
    
    private BigDecimal[] amounts;
    
    private Integer[]    states;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public String getSign()
    {
        return sign;
    }
    
    public void setSign(String sign)
    {
        this.sign = sign;
    }
    
    public BigDecimal getAmount()
    {
        return amount;
    }
    
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
    
    public Integer getState()
    {
        return state;
    }
    
    public void setState(Integer state)
    {
        this.state = state;
    }
    
    public Long[] getIds()
    {
        return ids;
    }
    
    public void setIds(Long[] ids)
    {
        this.ids = ids;
    }
    
    public BigDecimal[] getAmounts()
    {
        return amounts;
    }
    
    public void setAmounts(BigDecimal[] amounts)
    {
        this.amounts = amounts;
    }
    
    public Integer[] getStates()
    {
        return states;
    }
    
    public void setStates(Integer[] states)
    {
        this.states = states;
    }
}
