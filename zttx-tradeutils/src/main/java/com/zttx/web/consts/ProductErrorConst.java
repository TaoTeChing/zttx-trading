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
public enum ProductErrorConst implements EnumDescribable
{
    // 状态码范围：116000到120000
    SUCCESS(116000, "成功"),
    FAILURE(116001, "失败"),
    PRODUCT_lINE_NOT_SELECTED(116002, "请选择产品线"),
    PRODUCT_lINE_NOT_EXISTS(116003, "该产品线不存在"),
    BRANDSID_CANNOT_NULL(116004, "品牌不能为空"),
    PRODUCT_NO_CANNOT_NULL(116005, "产品货号不能为空"),
    PRODUCT_TITLE_CANNOT_NULL(116006, "产品标题不能为空"),
    PRODUCT_PRICE_CANNOT_NULL(116007, "产品价格不能为空"),
    PRODUCT_STORE_CANNOT_NULL(116008, "产品库存不能为空"),
    PRODUCT_IMAGE_NUM_ERROR(116009, "产品图片不能超过5张，且不能为空"),
    PRODUCT_CATE_CANNOT_NULL(116010, "产品类型不能为空"),
    PRODUCT_CARRY_CANNOT_NULL(116011, "运费物流不能为空"),
    PRODUCT_MARK_CANNOT_NULL(116012, "产品描述不能为空"),
    PRODUCT_BEGINTYPE_CANNOT_NULL(116013, "发布时间不能为空"),
    PRODUCT_LINE_NOT_EXISTS(116014, "要加入的产品线不存在或已被删除"),
    PRODUCT_NOT_EXISTS(116015, "该产品不存在"),
    GTOOM_IS_FULL(116016, "推荐窗口已满"),
    STORE_WARN_NUM_ERROR(116017, "库存预警值不能为负数"),
    PRODUCT_SKU_NOT_NULL(116018,"产品sku属性不能为空");

    private ProductErrorConst(Integer code, String message)
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
        for (ProductErrorConst c : ProductErrorConst.values())
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
}
