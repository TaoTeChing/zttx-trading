/*
 * @(#)DealerJoinTeminateModel 2014/4/4 8:57
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import java.math.BigDecimal;

/**
 * <p>File：DealerJoinTeminateModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/4 8:57</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class DealerJoinTeminateModel
{
    private String id;     // ID
    private String brandsId;     // ID

    //退押金金额
    private BigDecimal refundAmount;
    
    private String endMark; // 终止原因
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getEndMark()
    {
        return endMark;
    }
    
    public void setEndMark(String endMark)
    {
        this.endMark = endMark;
    }
    
    public BigDecimal getRefundAmount()
    {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount)
    {
        this.refundAmount = refundAmount;
    }

    public String getBrandsId() {
        return brandsId;
    }

    public void setBrandsId(String brandsId) {
        this.brandsId = brandsId;
    }
}
