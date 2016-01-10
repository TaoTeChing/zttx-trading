package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：DealerDrawingConst.java</p>
 * <p>Title: 品牌商品提现申请记录</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-19 10:31:22</p>
 * <p>Company: 8637.com</p>
 * @author 徐志勇
 * @version 1.0
 */
public enum DealerDrawingConst implements EnumDescribable
{
    // 状态码范围：116000到120000
    SUCCESS(116000, "成功"),
    FAILURE(116001, "失败"),
    NOTPAYPASS(116002, "用户未设置支付密码"),
    PAYPASSERROR(116003, "支付密码输入错误"),
    USERBLAND(116004, "用户未登录"),
    NOBANK(116005, "请选择需要提现的银行卡"),
    NOBANKINFO(116006, "提现银行卡信息不存在"),
    NOPASSANDAMOUT(116007, "请输入正确的提款金额或支付密码"),
    BANANCE_NEGATIVE(116008, "提现余额不足"),
    NULERROR(116009, "请求参数格式错误"),
    NOEXISTRESULT(116010, "请求参数不存在"),
    NOPASSBANKINFO(116011, "银行卡信息未审核");

    private DealerDrawingConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public Integer code;
    
    public String  message;
    
    @Override
    public Integer getCode()
    {
        return this.code;
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
    
    /**
     * 根据状态码获取状态码描述
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code)
    {
        String result = null;
        for (DealerDrawingConst c : DealerDrawingConst.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
            }
        }
        return result;
    }
}
