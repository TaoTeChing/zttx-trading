/*
 * @(#)AdvertsConst.java 2014-5-6 上午11:12:18
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：AdvertsConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-6 上午11:12:18</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
public class AdvertsConst
{
    // 广告使用状态
    public static final Short  USING         = 1;       // 开始时间<当前时间&&结束时间>当前时间(使用中)
    
    public static final Short  NOT_YET_START = 2;       // 开始时间>当前时间(还未开始)
    
    public static final Short  ALREADY_END   = 3;       // 结束时间<当前时间(已经结束)
    
    // 广告类型
    public static final Short  IMAGE_ADVERT  = 1;       // 1：图片广告
    
    public static final Short  TITLE_ADVERT  = 2;       // 2：标题广告
    
    // target 打开方式
    public static final String BLANK         = "_blank";
    
    public static final String SELF          = "_self";
}
