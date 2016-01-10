package com.zttx.web.utils;

/**
 * <p>File:SerialnoUtils</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/9/2 10:43</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class SerialnoUtils
{
    // 订单37，充值32，退款39，提现62，投诉65，品牌临时域名 86
    protected static final int PREFIX_ORDER         = 37; // 订单
    
    protected static final int PREFIX_RECHARGE      = 32; // 充值
    
    protected static final int PREFIX_SERVICE       = 38; // 服务订单
    
    protected static final int PREFIX_REFUND        = 39; // 退款
    
    protected static final int PREFIX_DRAWING       = 62; // 提现
    
    protected static final int PREFIX_COMPLAINT     = 65; // 投诉
    
    protected static final int PREFIX_BRANDS_DOMAIN = 86; // 品牌临时域名
    
    // 私有构造器，防止类的实例化
    public SerialnoUtils()
    {
        super();
    }
    
    /**
     * 生成订单编号
     * @return long 订单编号
     */
    public static long buildOrderSN(long orderId)
    {
        return com.zttx.sdk.utils.SerialnoUtils.randomId(orderId, PREFIX_ORDER);
    }
    
    /**
     * 生成退货/退款单号
     * @return long 退货/退款编号
     */
    public synchronized static long buildRefundSN(long refundId)
    {
        return com.zttx.sdk.utils.SerialnoUtils.randomId(refundId, PREFIX_REFUND);
    }
    
    /**
     * 生成投诉编号
     * @return long 投诉编号
     */
    public synchronized static long buildComplainSN(long complainId)
    {
        return com.zttx.sdk.utils.SerialnoUtils.randomId(complainId, PREFIX_COMPLAINT);
    }
    
    /**
     * 生成服务订单编号
     * @param complainId 流水号
     * @return
     */
    public synchronized static long buildServiceSN(long complainId)
    {
        return com.zttx.sdk.utils.SerialnoUtils.randomId(complainId, PREFIX_SERVICE);
    }
}
