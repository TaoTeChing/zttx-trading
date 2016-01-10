/*
 * @(#)BrandesInfoConst 2014/4/30 9:28
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：BrandesInfoConst</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/30 9:28</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public abstract class BrandesInfoConst
{
    // 品牌授权类型
    public abstract static class UserAuth
    {
        public static final String TOURIST          = "01"; // 游客
        
        public static final String MEMBER_REGISTER  = "02"; // 注册用户
        
        public static final String MEMBER_CERTIFIED = "03"; // 认证用户
        
        public static final String MEMBER_JOIN      = "04"; // 加盟用户
    }
}
