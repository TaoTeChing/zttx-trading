/*
 * @(#)SchoolConst.java 2014-4-9 下午1:38:44
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：SchoolConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-9 下午1:38:44</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public abstract class SchoolConst
{
    // 0：未审核
    public final static Short   PASSING         = 0;
    
    // 1：审核通过
    public final static Short   PASS            = 1;
    
    // 2：审核不通过
    public final static Short   NO_PASS         = 2;
    
    // 1：经销商发布，2：品牌商发布，3：平台发布
    public final static Short   ART_DEALER      = 1;
    
    public final static Short   ART_BRAND       = 2;
    
    public final static Short   ART_SYS         = 3;
    
    // 用户的最新记录储存条数
    public final static Integer JSON_COUNT      = 2;
    
    // 文章-免费
    public final static Integer ART_COST_FREE   = 0;
    
    // 文章-收费
    public final static Integer ART_COST_PAY    = 1;
    
    // 缓存条数-热门分类
    public final static Integer ART_NUM_CATE    = 3;
    
    // 缓存条数-精选推荐
    public final static Integer ART_NUM_SUGGEST = 3;
    
    // 缓存条数-TA们都在学习
    public final static Integer ART_NUM_LEARN   = 6;
    
    // 缓存条数-大家都在关心的问题
    public final static Integer ART_NUM_CARE    = 8;
    
    // 缓存条数-正在讨论的问题
    public final static Integer ART_NUM_DISCUSS = 6;
    
    // 缓存条数-期待大家交流的问题
    public final static Integer ART_NUM_COMMENT = 8;
    
    // 缓存条数-又有新资料了，还不来看一看？
    public final static Integer ART_NUM_NEW     = 6;
    
    // 缓存条数-最受大家欢迎的资料
    public final static Integer ART_NUM_WELCOME = 8;
    
    // 缓存条数-也许你会爱上的课程，不妨看一看
    public final static Integer ART_NUM_LOVE    = 6;
    
    // 缓存条数-本周热点课程
    public final static Integer ART_NUM_HOT     = 6;
}
