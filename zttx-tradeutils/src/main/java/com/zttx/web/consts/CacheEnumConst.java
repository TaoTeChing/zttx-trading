package com.zttx.web.consts;

public enum CacheEnumConst
{
    SESSION_CACHE("session_cache"), // 会话缓存
    REGIONAL_CACHE("regional_cache"), // 全国区域信息
    PRODUCTUNIT_CACHE("productUnit_cache"), // 产品单位信息
    DEALDIC_CACHE("dealDic_cache"), // 经营品类
    CODEDIC_CACHE("codeDic_cache"), // 验证码类型
    WEBMODULE_CACHE("webModule_cache"), // 网站模块信息
    WEBTEMPLATE_CACHE("webTemplate_cache"), // 网站模板信息缓存
    NETADDRESS_CACHE("netAddress_cache"), // API接入地址白名单缓存
    BANKCARD_CACHE("bankCard_cache"), // 银行卡字典信息
    BrandProAttribute_CACHE("brandProAttribute_cache"), // 产品属性信息
    BrandProAttrValue_CACHE("BrandProAttrValue_chache"), // 产品属性值信息
    BRANDESINFO_CACHE("BRANDS_"), // 品牌信息
    WEBADDRES_CACHE("webAddres_cache"), // 网站地址信息
    WEBDOMAIN_CACHE("webDomain_cache"), // 网站域名缓存信息管理
    BRANDINVITE_CACHE("branInvite_cache"), // 品牌商邀请经销商加盟
    ARTICLECATE_CACHE("articleCate_cache"), // 网站资讯类别
    ARTICLEINFO_CACHE("articleInfo_cache"), // 资讯文章信息
    BRANDNEWS_CACHE("brandNews_cache"), // 品牌商新闻资讯
    WEBDEFTMPLOG_CACHE("webDefTmpLog_cache"), // 自定义Html模板历史记录
    VISITOR_CACHE("visitor_cache"), // 游客缓存
    CAS_TOKEN("cas_cache") // 登录令牌缓存
    ;
    public String key;
    
    public String getKey()
    {
        return key;
    }
    
    // 私有构造器，防止类的实例化
    private CacheEnumConst(String key)
    {
        this.key = key;
    }
}
