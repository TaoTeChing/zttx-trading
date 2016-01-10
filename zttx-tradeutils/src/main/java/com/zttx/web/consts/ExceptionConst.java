/*
 * @(#)ExceptionConst.java 2014-1-8 下午1:02:27
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：ExceptionConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:02:27</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class ExceptionConst
{
    // 私有构造器，防止类的实例化
    private ExceptionConst()
    {
        super();
    }
    
    // 交易成功
    public static final Integer SUCCESS = 0X000;
    
    // NullPointerException
    public static final Integer NULLPOT = 0X001;
    
    // NumberFormatException
    public static final Integer NUMBERS = 0x002;
    
    // 记录已经存在
    public static final Integer EXISTES = 0X003;
    
    // IndexOutOfBoundsException
    public static final Integer OUTBOUD = 0x004;
    
    // FileNotFoundException
    public static final Integer FILEEXT = 0X005;
    
    // ArrayIndexOutOfBoundsException
    public static final Integer ARRAYIN = 0X006;
    
    // ClassCastException
    public static final Integer CASTEXP = 0X007;
    
    // 记录不存在(如帐号不存在)
    public static final Integer NOEXITS = 0X008;
    
    // 信息已过期(如帐号已过期)
    public static final Integer EXPIRES = 0X009;
    
    // 数据格式不正确
    public static final Integer FORMATS = 0X010;
    
    // userDes校验失败
    public static final Integer DESEXCP = 0X011;
    
    // dataLen参数错误
    public static final Integer DATALEN = 0X012;
    
    // 业务失败
    public static final Integer FAILURE = 0x00A;
}
