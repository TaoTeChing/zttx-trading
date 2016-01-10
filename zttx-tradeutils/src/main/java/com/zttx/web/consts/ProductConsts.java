/*
 * @(#)ProductConsts.java 14-3-25 上午10:23
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：ProductConsts.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-3-25 上午10:23</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public enum ProductConsts
{
    PRODUCT_ATTRIBUTE_CATE_PRO(1), // 产品属性
    PRODUCT_ATTRIBUTE_CATE_SALE(2), // 销售属性
    // 产品类型
    CATE_STOCK(1), // 现货产品
    CATE_ORDER(2), // 预订产品
    // 开始方式
    BEGIN_TYPE_FIRST(1), // 立刻
    BEGIN_TYPE_SET(2), // 设定时间
    BEGIN_TYPE_STORE(3), // 放入仓库
    // 设为推荐
    GROOM_FALSE(0), // 不设为推荐
    GROOM_TRUE(1), // 设为推荐
    // 订金选择
    ORDER_SELECT_NEED(1), // 需要订金
    ORDER_SELECT_NOT_NEED(0), // 无订金
    // 运费物流
    CARRY_DEALER(1), // 经销商承担
    CARRY_BRAND(2), // 品牌商承担
    // 操作选项（1：单选，2：多选，3：可输入，只针对产品属性）
    OPERATE_CATE_INPUT(3), OPERATE_CATE_SIN(1), OPERATE_CATE_MUL(2),
    // tab查询条件
    TABSEARCH_UP(0), // 上架的产品
    TABSEARCH_DOWN(1), // 下架的产品
    TABSEARCH_GROOM(2), // 推荐的产品
    TABSEARCH_ACTIVITY(3), // 活动中的
    TABSEARCH_RECYCLE(4), // 回收站
    TABSEARCH_LITTLE(5), // 库存紧张
    TABSEARCH_NULL(6), // 库存缺货
    // 销售状态
    CAN_ORDER(0), // ：可以订货 --> 加入购物车、去结算
    SOLD_OUT(1), // ：不能订货 --> 下架
    NO_ORDERSTART(2), // ：不能订货 --> 预定未开始
    ALREADY_ORDEREND(3), // ：不能订货 --> 预定已结束
    CAN_NOT_ALL(4)// ：没有任何操作
    ;
    public static int GTOOM_TOTAL    = 50; // 推荐窗口总数 todo 后期可能要做修改，不同用户不同总数
    
    public static int STORE_WARN_NUM = 10; // 库存预警值 todo 后期可能要做修改
    
    private Integer   key;
    
    public Integer getKey()
    {
        return key;
    }
    
    private ProductConsts(Integer key)
    {
        this.key = key;
    }
}
