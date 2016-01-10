/*
 * @(#)DecorateHeaderEnumConst.java 14-4-15 上午10:48
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：DecorateHeaderEnumConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-4-15 上午10:48</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public enum DecorateHeaderEnumConst implements EnumDescribable
{
    // 状态码范围：116000到120000
    SHOW_CATE_CANNOT_NULL(116000, "请选择招牌显示类型"),
    COMPANY_NAME_NOT_EXIST(116001, "店铺名称不能为空"),
    BRANDSID_NOT_EXIST(116002, "品牌ID不能为空");

    private DecorateHeaderEnumConst(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public Integer code;
    
    public String  message;
    
    public Integer getCode()
    {
        return code;
    }
    
    public void setCode(Integer code)
    {
        this.code = code;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
}
