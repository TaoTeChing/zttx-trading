/*
 * @(#)PayErrorConvertor.java 2014-12-6 下午2:53:22
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import com.zttx.pay.remoting.exception.RemotingException;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：PayErrorConvertor.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-12-6 下午2:53:22</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class PayErrorConvertor
{
    public final static Integer CATE_USER_ERROR      = 201000;
    
    public final static Integer CATE_PAY_ORDER_ERROR = 202000;
    
    public final static BusinessException parse(int cate, RemotingException e)
    {
        return new BusinessException(cate + e.getCode(), e.getMessage());
    }
}
