/*
 * @(#)DataDictConst.java 14-4-17 下午2:56
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import com.zttx.sdk.bean.EnumDescribable;

/**
 * <p>File：DataDictConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-4-17 下午2:56</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public enum DataDictConst implements EnumDescribable
{
    // 状态码范围：111000到115000
    DICT_CODE_NOT_EXIST(111000, "dictCode不能为空"),
    DICT_NAME_NOT_EXIST(111001, "dictName不能为空"),
    DICT_ID_NOT_EXIST(111002, "dictid不能为空"),
    ID_NOT_EXIST(111003, "主键不能为空"),
    DICT_VALUE_NOT_EXIST(111004, "dictValue不能为空"),
    DICT_VALUE_NAME_NOT_EXIST(111005, "dictValueName不能为空");

    // delState(删除标志)
    public static final short DELETED      = 1; // 已经删除
    
    public static final short NONE_DELETED = 0; // 未删除
    
    private DataDictConst(Integer code, String message)
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
