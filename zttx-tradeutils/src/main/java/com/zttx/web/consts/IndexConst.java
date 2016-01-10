/*
 * @(#)IndexConst.java 2014-5-7 下午4:11:17
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

/**
 * <p>File：IndexConst.java</p>
 * <p>Title: 前台页面中使用到的常量</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-7 下午4:11:17</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public class IndexConst
{
    // 入驻相关页中的常见问题
    public static final String APPLY_QUESTION            = "APPLY_QUESTION";
    
    // 排行榜
    public static final String INFO_ORDERS               = "INFO_ORDERS";
    
    // 全部资讯
    public static final String INFO_ALL                  = "all";
    
    // 推荐资讯
    public static final String INFO_HOT                  = "hot";
    
    public static final String ORDER                     = "order";
    
    // 文章类型
    public static final Short  ARTICLE                   = CommonConstant.HelpCate.SHOW_TYPE_ARTICLE;
    
    // 列表类型
    public static final Short  LIST                      = CommonConstant.HelpCate.SHOW_TYPE_LIST;
    
    // 级别为大类的
    public static final Short  CATELEVEL                 = 1;
    
    // 搜索框类型 品牌
    public static final Short  BRANDS_SEARCH             = 0;
    
    // 搜索框类型 资讯
    public static final Short  ARTICLE_SEARCH            = 1;
    
    // 全部资讯
    public static final Short  ARTICLE_ALL               = 0;
    
    // 推荐阅读
    public static final Short  ARTICLE_RECOMMEND         = 1;
    
    // 总排行榜
    public static final Short  ARTICLE_ORDER             = 2;
    
    /**
     * 首页感兴趣品牌展示（WebBrandsShow）
     */
    // 显示类型
    public static final Short  WEBBRANDSSHOW_SHOWTYPE_O1 = 1;                                         // 感兴趣的品牌
}
