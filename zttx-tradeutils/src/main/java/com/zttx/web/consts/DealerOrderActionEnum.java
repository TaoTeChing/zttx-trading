/*
 * @(#)DealerOrderActionEnum.java 2014-4-17 下午1:27:53
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：DealerOrderActionEnum.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-17 下午1:27:53</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public enum DealerOrderActionEnum
{
    NO_BUY("我不想买了"),
    AGAIN_BUY("信息填写错误"),
    STOCKOUT("品牌商缺货"),
    OTHER("其他原因"),
    NO_LINK("长时间联系不到终端商"),
    DEALER_NO_BUY("终端商不想买了"),
    NOT_GOODS("没有货了，交易无法完成"),
    PAY_OUTTIME_CLOSE_ORDER("终端商支付超时，订单自动关闭");

    private DealerOrderActionEnum(String message)
    {
        this.message = message;
    }
    
    private String message;
    
    public String getMessage()
    {
        return this.message;
    }
}
