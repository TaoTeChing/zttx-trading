/*
 * @(#)CacheConst.java 2014-1-8 下午1:42:11 Copyright 2014 刘志坚, Inc. All rights
 * reserved. 8637.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * <p>
 * File：CacheConst.java
 * </p>
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014 2014-1-8 下午1:42:11
 * </p>
 * <p>
 * Company: 8637.com
 * </p>
 * 
 * @author 刘志坚
 * @version 1.0
 */
public class CacheConst
{
    // 项目名 与模块名之间分隔符
    private static final String SEPARATOR   = ".";
    
    // 项目里缓存 集合数据的KEY
    public static final String  CACHED_LIST = SEPARATOR + "CACHED_LIST";
    
    // 私有构造器，防止类的实例化
    private CacheConst()
    {
        super();
    }
    
    public static final String        AREA_CACHE                = ApplicationConst.PROJECT_NAME + SEPARATOR + "Area";
    
    // 排过序的所有区域信息 缓存
    public static final String        ALL_AREA_CACHE            = ApplicationConst.PROJECT_NAME + SEPARATOR + "ALL_Area";
    
    // 所有API接入码缓存
    public static final String        ALL_CLIENTKEY_CACHE       = ApplicationConst.PROJECT_NAME + SEPARATOR + "ALL_CLIENTKEY";
    
    // 商学院 - 热门分类
    public static final String        SCHOOL_CATE               = ApplicationConst.PROJECT_NAME + "SCHOOL_CATE";
    
    // 商学院 - 精选推荐
    public static final String        SCHOOL_SUGGEST            = ApplicationConst.PROJECT_NAME + "SCHOOL_SUGGEST";
    
    // 商学院 - TA们都在学习
    public static final String        SCHOOL_LEARN              = ApplicationConst.PROJECT_NAME + "SCHOOL_LEARN";
    
    // 商学院 - 大家都在关心的问题
    public static final String        SCHOOL_CARE               = ApplicationConst.PROJECT_NAME + "SCHOOL_CARE";
    
    // 商学院 - 正在讨论的问题
    public static final String        SCHOOL_DISCUSS            = ApplicationConst.PROJECT_NAME + "SCHOOL_DISCUSS";
    
    // 商学院 - 期待大家交流的问题
    public static final String        SCHOOL_COMMENT            = ApplicationConst.PROJECT_NAME + "SCHOOL_COMMENT";
    
    // 商学院 - 又有新资料了，还不来看一看？
    public static final String        SCHOOL_NEW                = ApplicationConst.PROJECT_NAME + "SCHOOL_NEW";
    
    // 商学院 - 最受大家欢迎的资料
    public static final String        SCHOOL_WELCOME            = ApplicationConst.PROJECT_NAME + "SCHOOL_WELCOME";
    
    // 商学院 - 也许你会爱上的课程，不妨看一看
    public static final String        SCHOOL_LOVE               = ApplicationConst.PROJECT_NAME + "SCHOOL_LOVE";
    
    // 商学院 - 本周热点课程
    public static final String        SCHOOL_HOT                = ApplicationConst.PROJECT_NAME + "SCHOOL_HOT";
    
    // 首页平台公告
    public static final String        INDEX_INFO                = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_INFO";
    
    // 首页推荐产品图
    public static final String        INDEX_PRODUCTINFO         = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_PRODUCTINFO";
    
    // 首页导航分类
    public static final String        INDEX_CATEGORY            = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_CATEGORY";
    
    // 首页导航分类下的品牌
    public static final String        INDEX_CATEGORY_BRANDSINFO = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_CATEGORY_BRANDSINFO";
    
    // 行业资讯导航
    public static final String        INFO_NAV                  = ApplicationConst.PROJECT_NAME + SEPARATOR + "INFO_NAV";
    
    // 行业资讯焦点图
    public static final String        INFO_FOCES                = ApplicationConst.PROJECT_NAME + SEPARATOR + "INFO_FOCES";
    
    // 帮助导航
    public static final String        HELP_NAV                  = ApplicationConst.PROJECT_NAME + SEPARATOR + "HELP_NAV";
    
    /** 首页正式使用的 key group **/
    // 网站 所有广告位
    public static final String        INDEX_ADVERTS_POSITION    = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_ADVERTS_POSITION";
    
    // 网站 所有广告
    public static final String        INDEX_ADVERTS             = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_ADVERTS";
    
    // 首页最新交易会
    public static final String        TRADE_MEETING             = ApplicationConst.PROJECT_NAME + SEPARATOR + "TRADE_MEETING_NEW";
    
    // 自定义Html模板
    public static final String        WEBDEF_TEMPLATE           = ApplicationConst.PROJECT_NAME + SEPARATOR + "WEBDEF_TEMPLATE";
    
    // 首页最新加盟品牌
    public static final String        INDEX_BRANDS              = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_BRANDS";
    
    // 首页发布会
    public static final String        INDEX_MEET                = ApplicationConst.PROJECT_NAME + SEPARATOR + "INDEX_MEET";
    
    // 首页搜索关键字
    public static final String        INDEX_SEARCH_HOTLIST      = ApplicationConst.PROJECT_NAME + SEPARATOR + "SEARCH_HOTLIST";
    
    // 加盟入驻相关页中的常见问题
    public static final String        APPLY_QUESTION            = "APPLY_QUESTION";
    
    // 规则最新信息
    public static final String        RULEINFO_NEW              = ApplicationConst.PROJECT_NAME + SEPARATOR + "RULEINFO_NEW";
    
    // 规则cate
    public static final String        RULEINFO_CATE             = ApplicationConst.PROJECT_NAME + SEPARATOR + "RULEINFO_CATE";
    
    // 帮助分类
    public static final String        HELP_CATE                 = ApplicationConst.PROJECT_NAME + SEPARATOR + "HELP_CATE";
    
    // 帮助
    public static final String        HELP_INFO                 = ApplicationConst.PROJECT_NAME + SEPARATOR + "HELP_INFO";
    
    // 意见和建议
    public static final String        SUGGESTIONS_INFO          = ApplicationConst.PROJECT_NAME + SEPARATOR + "SUGGESTIONS_INFO";
    
    // 行业资讯分类缓存
    public static final String        INFO_CATE                 = ApplicationConst.PROJECT_NAME + SEPARATOR + "INFO_CATE";
    
    // 行业资讯全部资讯
    public static final String        INFO_ALL                  = ApplicationConst.PROJECT_NAME + SEPARATOR + "INFO_ALL";
    
    // 品牌缓存
    public static final String        BRAND_INFO                = ApplicationConst.PROJECT_NAME + SEPARATOR + "BRAND_INFO";
    
    // 产品缓存
    public static final String        PRODUCT_INFO              = ApplicationConst.PROJECT_NAME + SEPARATOR + "PRODUCT_INFO";
    
    // 品牌二级域名
    public static final String        BRANDS_DOMAIN             = ApplicationConst.PROJECT_NAME + SEPARATOR + "BRANDS_DOMAIN";
    
    // 物流信息缓存
    public static final String        LOGISTICINFO_INFO         = ApplicationConst.PROJECT_NAME + SEPARATOR + "LOGISTICINFO_INFO";
    
    // 物流公司缓存
    public static final String        FREIGHTCOMPANY_INFO       = ApplicationConst.PROJECT_NAME + SEPARATOR + "FREIGHTCOMPANY_INFO";
    
    // 订单金额修改记录缓存
    public static final String        ORDER_CHANGE_LOGINFO      = ApplicationConst.PROJECT_NAME + SEPARATOR + "ORDER_CHANGE_LOGINFO";
    
    // KeyGroup的Map<String, String>集合
    public static Map<String, String> keyGroupMap;
    
    /**
     * 获取keyGroupMap：Map<String describe, String key>
     * 
     * @return
     * @author 周光暖
     */
    public static Map<String, String> getKeyGroupMap()
    {
        if (keyGroupMap == null)
        {
            keyGroupMap = Maps.newHashMap();
            keyGroupMap.put("网站所有广告位", INDEX_ADVERTS_POSITION);
            keyGroupMap.put("网站所有广告", INDEX_ADVERTS);
            keyGroupMap.put("首页最新交易会", TRADE_MEETING);
            keyGroupMap.put("自定义Html模板", WEBDEF_TEMPLATE);
            keyGroupMap.put("首页最新加盟品牌", INDEX_BRANDS);
            keyGroupMap.put("首页发布会", INDEX_MEET);
            keyGroupMap.put("首页搜索关键字", INDEX_SEARCH_HOTLIST);
            keyGroupMap.put("加盟入驻相关页中的常见问题", APPLY_QUESTION);
            keyGroupMap.put("规则分类", RULEINFO_CATE);
            keyGroupMap.put("规则最新信息", RULEINFO_NEW);
            keyGroupMap.put("帮助信息", HELP_INFO);
            keyGroupMap.put("帮助分类", HELP_CATE);
            keyGroupMap.put("行业资讯分类", INFO_CATE);
            keyGroupMap.put("行业资讯全部资讯", INFO_ALL);
            keyGroupMap.put("品牌缓存", BRAND_INFO);
            keyGroupMap.put("产品缓存", PRODUCT_INFO);
            keyGroupMap.put("品牌二级域名", BRANDS_DOMAIN);
        }
        return keyGroupMap;
    }
}
