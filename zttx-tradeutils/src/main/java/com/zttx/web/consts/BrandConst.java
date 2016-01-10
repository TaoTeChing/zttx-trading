/*
 * @(#)BrandConst.java 2014-2-28 上午10:18:56
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：BrandConst.java</p>
 * <p>Title: 品牌商管理中心状态码定义</p>
 * <p>Description:状态码范围：116000到120000</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-28 上午10:18:56</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public enum BrandConst implements EnumDescribable
{
    // 状态码范围：116000到120000
    /**
     * 请使用 {@link com.zttx.web.consts.CommonConst#SUCCESS} 代替
     */
    SUCCESS(116000, "成功"),
    FAILURE(116001, "失败"),
    BRANDIMGTYPEEXIST(116002, "品牌图片类型已存在"),
    PICNOTEXIST(116003, "源文件不存在"),
    NULLERROR(116004, "参数为空"),
    USERM_NOT_EXIST(116005, "用户不存在"),
    CAPTCHA_SEND_SUCCESS(116006, "验证码发送成功"),
    CAPTCHA_EXIST(116007, "验证码已发送"),
    CAPTCHA_SEND_FAIL(116008, "验证码发送失败"),
    VERIFY_SUCCESS(116009, "验证通过"),
    VERIFY_FAIL(116010, "验证失败"),
    NOTDELPARENT(116011, "该分类下还存在子分类，不可删除"),
    BRANDES_INFO_NOT_EXISTS(116012, "品牌不存在"),
    PWD_NOT_MATCH(116013, "两次密码不一致"),
    BRANDES_NOT_APPROVE(116014, "品牌未通过审核"),
    IMG_CATE_NAME_REPEAT(116015, "分类名称已存在"),
    LEVEL_NAME_REPEAT(116016, "等级名称已存在"),
    // checkState（品牌商审核状态）
    NOT_PASS_VERIFY(0, "未审核"),
    PASS_VERIFY(1, "审核通过"),
    HAS_NOT_VERIFY(2, "审核不通过"),
    // brandState（品牌状态）
    BRAND_STATE_NEW(0, "新增"),
    BRAND_STATE_VERIFY(1, "已审核"),
    BRAND_STATE_JOIN(2, "合作中"),
    BRAND_STATE_CANCEL(3, "已取消"),
    BRAND_STATE_PAST(4, "已过期"),
    BRAND_STATE_FAIL(5, "已失败"),
    // 品牌商品牌信息-时间搜索条件的比较方式
    SEARCH_WAY_BEFORE_BEGIN(1, "大于开始时间"),
    SEARCH_WAY_LATER_BEGIN(2, "小于开始时间"),
    SEARCH_WAY_BEFORE_END(3, "大于结束时间"),
    SEARCH_WAY_LATER_END(4, "小于结束时间"),
    // 品牌资料-企业规模
    BRAND_EMPLOEE_NUM_1(1, "5人以下"),
    BRAND_EMPLOEE_NUM_2(2, "5人-50人"),
    BRAND_EMPLOEE_NUM_3(3, "51-100人"),
    BRAND_EMPLOEE_NUM_4(4, "101-500人"),
    BRAND_EMPLOEE_NUM_5(5, "501-1000人"),
    BRAND_EMPLOEE_NUM_6(6, "1000人以上"),
    BRAND_MONEY_NUM_1(1, "5人以下"),
    BRAND_MONEY_NUM_2(2, "5人-50人"),
    BRAND_MONEY_NUM_3(3, "51-100人"),
    BRAND_MONEY_NUM_4(4, "101-500人"),
    BRAND_MONEY_NUM_5(5, "501-1000人"),
    BRAND_MONEY_NUM_6(6, "1000人以上"),
    // 品牌商审核日志
    BRAND_REFRENCEID(116300, "请传入品牌商审核日志编号"),
    BRAND_BRANDID(116301, "请传入品牌商主帐号编号"),
    BRAND_NOTEXIST(116302, "请求的资料不存在"),
    BRAND_DEPT_EXISTS(116303, "相同部门已存在"),
    BRAND_ROLE_EXISTS(116304, "相同权限已存在"),
    PRODUCT_NOT_EXISTS(116305, "产品不存在"),
    BRANDES_NOT_EXISTS(116306, "品牌不存在"),
    BRANDES_JOIN_CANNOT_EDIT(116307, "品牌当前状态不能修改"),
    BRANDES_NAME_EXISTS(116308, "已经有相同名称的品牌存在"),
    BRANDES_STATE_ERROR(116309, "不能对当前品牌的状态进行操作"),
    // 产品线错误信息
    PRODUCT_LINE_DEL_ERROR(116017, "请先删除产品线下的所有终端商和产品"),
    // VIEW_CONTACT_LACK_POINTS(116018,"非常抱歉，本年度开放给您的"+BrandConstant.BrandCountConst.BRAND_VIEW_COUNT+"次查看机会已经用完，如需查看更多，请向客服进行咨询"),
    VIEW_CONTACT_LACK_POINTS(116018, "非常抱歉，本年度开放给您的查看机会已经用完，如需查看更多，请向客服进行咨询"),
    DEALER_ORDER_SHIP_NOT_GREATE_BUY(116019, "订单发货总数不能大于购买总数"),
    DEALER_ORDER_NOT_FIND_PRODUCT(116020, "订单中不存在您要发货的产品"),
    DEALER_ORDER_PROPERTY_ERROR(116021, "订单中不存在您要发货的产品"),
    VIEW_CONTACT_LACK_POINTS_NOT_JOIN(116022, "非常抱歉，本年度开放给您的邀请加盟机会已经用完，如需了解更多，请向客服进行咨询"),
    BRAND_INFO_COMNAME_EXISTS(116023, "已有相同公司名称的品牌商存在"),
    BRAND_BALANCE_EXISTS(116024, "品牌商金额记录已存在"),
    PRODUCT_NOT_JOIN_LINE(116025, "该产品还未加入产品线"),
    PRODUCT_LINE_NOT_EXISTS(116026, "该产品线不存在"),
    DEALER_JOIN_NET_BRANDS(116027, "该终端商还未加盟品牌"),
    ACTIVITY_PRICE_ERROR(117001, "活动价格格式错误"),
    ACTIVITY_IMAGE_NOT_EXISTS(117002, "活动图片不能为空"),
    ACTIVITY_PARAMS_VALID_ERR(117003, "活动参数验证错误"),
    PARITY_PRICE_NOT_EXISTS(117004, "平台价格至少填写一项"),
    PARITY_PRICE_NOT_FLOOR_ACTIVITY_PRICE(117005, "平台价格不能低于活动价格"),
    ACTIVITY_REG_NOT_START(117006, "活动报名还未开始"),
    ACTIVITY_REG_HAS_ENDED(117007, "活动报名已结束"),
    PROVINCE_PRICE_ERROR(117008, "活动价格格式错误"),
    PROVINCE_PRICE_NOT_FLOOR_ACTIVITY_PRICE(117005, "省代价格不能低于活动价格");
    /*
     * PRODUCT_ATTRIBUTE_CATE_PRO(1,"产品属性"),
     * PRODUCT_ATTRIBUTE_CATE_SALE(2,"销售属性")
     */
    private BrandConst(Integer code, String message)
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
        for (BrandConst c : BrandConst.values())
        {
            if (c.code.equals(code))
            {
                result = c.message;
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
    // 未审核
    /*
     * public static final Short BRAND_CHECK_STATE_NOCHECK = 0;
     * // 审核 通过
     * public static final Short BRAND_CHECK_STATE_SUCCESS = 1;
     * // 审核 失败
     * public static final Short BRAND_CHECK_STATE_FAILURE = 2;
     */
    // 1：同意合作
    /*
     * public static final Short AGREE_COOPERATION = 1;
     * // 拒绝合作
     * public static final Short REJECT_COOPERATION = 2;
     * // 中止合作
     * public static final Short STOP_COOPERATION = 3;
     */
    // 针对: BrandInvite: INVITE_STATE
    // 0：已邀请未回复
    /*
     * public static final Integer INVITE_STATE_UNDEAL = 0;
     * // 1：已邀请已同意
     * public static final Integer INVITE_STATE_ACCEPT = 1;
     * // 2：已邀请已拒绝，
     * public static final Integer INVITE_STATE_REJECT = 2;
     * // 3：已删除
     * public static final Integer INVITE_STATE_DELETE = 3;
     * // inviteState（邀请状态）0：已邀请未回复，1：已邀请已同意，2：已邀请已拒绝，3：已删除
     */
    // 经绡商申请
    /*
     * public static final Short OPRATOR_CATA_DEALER = 1;
     * //品牌商申请
     * public static final Short OPRATOR_CATA_BRAND = 2;
     */
    // applyState(申请状态)0：未审核，1：审核通过，2：审核不通过，3：邀请加盟，4：经销商中止合作,5:撤消申请，6:品牌商中止合作
    /*
     * public static final Short APPLY_STATE_NOCHECK = 0;
     * public static final Short APPLY_STATE_SUCCESS = 1;
     * public static final Short APPLY_STATE_FAILURE = 2;
     * public static final Short APPLY_STATE_INVITE = 3;
     * public static final Short APPLY_STATE_DEALER_STOP_COOPERATION = 4;
     * public static final Short APPLY_STATE_CANCEL = 5;
     * public static final Short APPLY_STATE_BRAND_STOP_COOPERATION = 6;
     */
    // joinSource（加盟来源）1：平台线上申请加盟，2：平台线上邀请加盟，3：平台线下加盟，4：关系户加盟
    /*
     * public static final Short JOIN_SOURCE_WEB_APPLY = 1;
     * public static final Short JOIN_SOURCE_WEB_INVITE = 2;
     * public static final Short JOIN_SOURCE_OFFLINE = 3;
     * public static final Short JOIN_SOURCE_RELATIONSHIP = 4;
     */
    // 订货会 申请加入 审核值
    /*
     * public static final Short JOIN_STATE_UNCHECK = 0;
     * public static final Short JOIN_STATE_ACCEPT = 1;
     * public static final Short JOIN_STATE_REJECT = 2;
     */
    // 品牌审核状态　0、未审核　1、已审核通过　2、已合作　3、
    /*
     * public static final Short UNAPPROVED = 0;
     * public static final Short APPROVED = 1;
     * public static final Short COOPERATED = 2;
     */
    /**
     * 已过期
     */
    // public static final short Expired = 3;
}
