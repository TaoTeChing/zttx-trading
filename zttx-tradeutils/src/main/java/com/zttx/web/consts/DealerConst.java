/*
 * @(#)DealerConst.java 2014-2-27 下午6:23:05
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：DealerConst.java</p>
 * <p>Title: 经销商管理中心状态码定义</p>
 * <p>Description:状态码范围：111000到115000</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-27 下午6:23:05</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public enum DealerConst implements EnumDescribable
{
    // 状态码范围：111000到115000
    /**
     * 请使用 {@link com.zttx.web.consts.CommonConst#SUCCESS} 代替
     */
    SUCCESS(126000, "成功"),
    FAILURE(111001, "失败"),
    USERM_NOT_EXIST(111002, "用户不存在"),
    CAPTCHA_SEND_SUCCESS(111003, "验证码发送成功"),
    CAPTCHA_EXIST(111004, "验证码已发送"),
    CAPTCHA_SEND_FAIL(111005, "验证码发送失败"),
    VERIFY_SUCCESS(111006, "验证通过"),
    VERIFY_FAIL(111007, "验证失败"),
    PWD_NOT_MATCH(111008, "两次密码不一致"),
    USERM_NOLOGIN(111009, "用户未登陆"),
    USERM_ALREADY_JOIN(111010, "已加盟该品牌"),
    NONE_PASS_VERIGY(111011, "未通过经销商信息认证"),
    APPLY_INFO_ERROR(111012, "申请加盟信息不能为空"),
    DEALER_REFRENCEID(111013, "请传入经销商审核日志编号"),
    DEALER_DEALERID(111014, "请传入经销商主帐号编号"),
    DEALER_NOTEXIST(111015, "请求的资料不存在"),
    DEALER_NOPARAM(111016, "请求参数为空"),
    DEALER_AUDIT_STATE_ERROR(111017, "审核申请状态有误"),
    POWER_ERROR(111018, "权限不足"),
    REFUND_AMOUNT_APPLY_ERROR(111019, "申请退款金额录入错误"),
    COMPLAINT_ORDER_ERROR(111020, "提交投诉的订单不存在"),
    NOT_COMPLAINT(111021, "该订单不能发起投诉"),
    ALREADY_APPLY_REFUND(111022, "该订单已经申请过退款"),
    NOT_REFUND(111023, "该退款申请不存在"),
    CANNOT_SCOPE(111024, "该申请不在取消的范围内"),
    DEALER_JOIN_STATE_ERROR(111025, "加盟合作状态有误"),
    PRODUCT_FAVORITE_EXISTS(111026, "此产品已被收藏"),
    PRODUCT_FAVORITE_ERROR(111026, "收藏产品失败"),
    INVENTORY_NOT_ENOUGH(111027, "库存不足"),
    ORDER_NOT_EXIST(111027, "该订单不存在"),
    NOT_MODIFY_PRICE(111028, "该订单不能修改金额"),
    PRICE_ERROR(111029, "金额不能为负数"),
    ORDER_STATE_ERROR(111030, "该订单状态错误"),
    ORDER_STATE_EXIST(111031, "该订单已经是该状态了"),
    ORDER_DATA_ERROR(111032, "该订单数据错误"),
    LEVEL_MARK_ERROR(111033, "级别错误"),
    ORDER_BEYOND_DATE(111034, "该订单已经超出申请投诉的时间"),
    PRIVILEGE_SELECT_ERROR(111035, "请选择加价或者是优惠"),
    DISCOUNT_ERROR(111036, "折扣不正确，请重新选择！"),
    ORDER_COUNT_ERROR(111037, "订单计数错误！"),
    ORDER_CANNOT_EDIT(111038, "订单不可修改"),
    NOT_USERPASSWORD_PAYWORD(111039, "登录密码和支付密码相同"),
    ALREADY_INSHOPPER(111040, "该产品已加入"),
    OVERTOP_MONEY(111041, "超出输入的金额范围"),
    OVERTOP_BUDGET(111042, "超出预算的范围"),
    ORDER_PRIVILEGE_NOT(111043, "订单优惠或加价类型不明确"),
    PRODUCT_HAS_DELETE(111044, "该产品已删除"),
    PAYWORD_ERROR(111045, "支付密码不正确"),
    USERM_HAS_EXIST(111046, "用户已存在"),
    USERM_KEY_EXIST(111047, "主键已存在"),
    NOT_PAY_FOR_SERVICE(111048, "尚未购买服务"),
    DEALER_BALANCE_EXIST(111049, "经销商金额记录已存在"),
    BRANDS_FAVORITE_ERROR(111050, "收藏品牌失败"),
    APPLY_TELPHONE_ERROR(111051, "联系电话为空 或 联系电话格式不对 "),
    PRODUCT_HAS_ILLEGAL(111052, "该产品已失效 "),
    VERIFY_CODE_FAIL(111053, "验证码有误"),
    USERM_ALREADY_JOINING(111054, "加盟申请已存在,正在审核进行中"),
    USERM_ALERDAY_INVITED(111055, "品牌商已邀请,请勿再提交"),
    USERM_INVITE_JOINING(111056, "邀请加盟已存在,正在审核进行中"),
    PRODUCTSKU_NOT_EXIST(111057, "该产品SKU不存在"),
    USER_NOT_FACTORY(111058,"非工厂店经销商"),
    ORDER_STARTNUM_ERROR(111059,"订单未达到起批量"),
    NOT_JOIN_STATE(111060,"品牌合作失效，请重新加盟");



    private DealerConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    /**
     * 根据状态码获取状态码描述
     * @param code 状态码
     * @return String 状态码描述
     */
    public static String getMessage(Integer code)
    {
        String result = null;
        for (DealerConst c : DealerConst.values())
        {
            if (c.code == code)
            {
                result = c.message;
                break;
            }
            if (code != null && c.code != null && c.code.intValue() == code.intValue())
            {
                result = c.message;
                break;
            }
        }
        return result;
    }
    
    public Integer code;
    
    public String  message;
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.bean.EnumDescribable#getCode()
     */
    @Override
    public Integer getCode()
    {
        return this.code;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.bean.EnumDescribable#getMessage()
     */
    @Override
    public String getMessage()
    {
        return this.message;
    }
}
