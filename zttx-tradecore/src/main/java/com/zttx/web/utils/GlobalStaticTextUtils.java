/*
 * @(#)GlobalStaticTextUtils.java 2014-5-9 下午5:41:14
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SpringUtils;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandsDomain;
import com.zttx.web.module.brand.model.BrandesAuthUserModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandesAuthUserService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.brand.service.BrandsDomainService;
import com.zttx.web.module.common.entity.WebDefTemplate;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.module.common.service.WebDefTemplateService;
import com.zttx.web.module.dealer.service.DealerShoperService;
import com.zttx.web.module.fronts.entity.*;
import com.zttx.web.module.fronts.service.*;
import com.zttx.web.search.module.FacetField;
import com.zttx.web.search.utils.FacetUtils;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;

/**
 * <p>File：GlobalStaticTextUtils.java</p>
 * <p>Title: 全局辅助类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-9 下午5:41:14</p>
 * <p>Company: 8637.com</p>
 *
 * @author 鲍建明
 * @version 1.0
 */
public class GlobalStaticTextUtils
{
    // 首页的静态模块
    static final WebDefTemplateService  webDefTemplateService  = SpringUtils.getBean(WebDefTemplateService.class);
    
    // 资讯类别
    static final ArticleCateService     articleCateService     = SpringUtils.getBean(ArticleCateService.class);
    
    // 规则分类
    static final RulesCateService       rulesCateService       = SpringUtils.getBean(RulesCateService.class);
    
    // 帮助分类
    static final HelpCateService        helpCateService        = SpringUtils.getBean(HelpCateService.class);
    
    // 规则信息
    static final RulesInfoService       rulesInfoService       = SpringUtils.getBean(RulesInfoService.class);
    
    // 资讯信息
    static final ArticleInfoService     articleInfoService     = SpringUtils.getBean(ArticleInfoService.class);
    
    // 入驻页中的常见问题
    static final HelpInfoService        helpInfoService        = SpringUtils.getBean(HelpInfoService.class);
    
    // 广告位管理
    static final AdvertPositService     advertPositService     = SpringUtils.getBean(AdvertPositService.class);
    
    // 品牌服务
    static final BrandesInfoService     brandesInfoService     = SpringUtils.getBean(BrandesInfoService.class);
    
    static final BrandesAuthUserService brandesAuthUserService = SpringUtils.getBean(BrandesAuthUserService.class);
    
    // 感兴趣的品牌
    static final WebBrandsShowService   webBrandsShowService   = SpringUtils.getBean(WebBrandsShowService.class);
    
    static final BrandsDomainService    brandsDomainService    = SpringUtils.getBean(BrandsDomainService.class);
    
    static final DealerShoperService    dealerShoperService    = SpringUtils.getBean(DealerShoperService.class);
    
    private static final Logger         logger                 = LoggerFactory.getLogger(GlobalStaticTextUtils.class);
    
    private static RegionalService      regionalService        = SpringUtils.getBean(RegionalService.class);
    
    public static int countShoperNum() throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        return dealerShoperService.getShoperCountByUserId(dealerId).intValue();
    }
    
    public static List<Map<String, Object>> getAreaObjects(int areaNo)
    {
        return regionalService.getRegionalArea(areaNo);
    }
    
    /**
     * 根据产品id 获取该产品赋予当前用户的产品价格
     *
     * @param productId
     * @param State
     * @return
     */
    public static BrandesAuthUserModel getAuthPrice(String productId, String State)
    {
        String userId = null;
        if (OnLineUserUtils.getPrincipal() != null)
        {
            userId = OnLineUserUtils.getPrincipal().getRefrenceId();
        }
        try
        {
            return brandesAuthUserService.getAuthPrice(userId, productId, State);
        }
        catch (BusinessException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }
    
    /**
     * 根据产品id 获取该产品赋予当前用户的产品价格
     *
     * @param product
     * @param State
     * @return
     */
    public static BrandesAuthUserModel getProductAuthPrice(Map<String, Object> product, String State)
    {
        UserPrincipal principal =  OnLineUserUtils.getPrincipal();

        try
        {
            return brandesAuthUserService.getAuthPrice(principal , product, State);
        }
        catch (BusinessException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }
    
    /**
     * 获取静态HTML内容
     *
     * @param key
     * @return {@link WebDefTemplate}
     */
    public static String getDocument(String key)
    {
        String htmlText = null;
        try
        {
            WebDefTemplate template = webDefTemplateService.findByKey(key);
            if (null != template)
            {
                htmlText = template.getHtmlText();
            }
        }
        catch (BusinessException e)
        {
            logger.error("getDocument error:", e.getLocalizedMessage());
        }
        return htmlText;
    }
    
    /**
     * 取用户信息
     * @return
     */
    public static UserPrincipal getUserPrincipal()
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
        return userPrincipal;
    }
    
    /**
     * 根据品牌类别取数据
     * <p>
     * 用于首页品牌分类查询使用;
     * 如：
     * 女装品牌馆、男装品牌馆。
     * </p>
     *
     * @param mainId
     * @return
     * @throws BusinessException
     */
    public static Map<String, List<BrandesInfo>> searchBrandesResult(String mainId, String notIn) throws BusinessException
    {
        return brandesInfoService.searchBrandesResult(mainId, notIn);
    }
    
    /**
     * 取最近一个月销量最好的品牌信息
     *
     * @return
     * @throws BusinessException
     */
    public static Map<String, List<BrandesInfo>> searchTopSaleBrandes() throws BusinessException
    {
        return brandesInfoService.searchTopSaleBrandes();
    }
    
    /**
     * 根据品牌类别取数据
     * <p>
     * 用于首页其他类目推荐品牌;
     * </p>
     *
     * @param mainId
     * @return
     * @throws BusinessException
     */
    public static Map<String, List<BrandesInfo>> searchOtherBrandesResult(String mainId) throws BusinessException
    {
        return brandesInfoService.searchOtherBrandesResult(mainId.split(","));
    }
    
    /**
     * 根据统计结果返回自定义对象
     *
     * @param result solr facet_counts结果
     *               <p>
     *               如：["丝中娇",99,"华诚",69,"杭州女装919",66,"紫茉莉",61]"。
     *               </p>
     * @return {@link List<FacetField>}
     */
    public static List<FacetField> getFaectFields(String result)
    {
        return FacetUtils.getFacetFields(result);
    }
    
    /**
     * 获取请求图片大小的文件服务器URL
     *
     * @param url
     * @param width
     * @param height
     * @return {@link String}
     */
    public static String getImageDomainPath(String url, Integer width, Integer height)
    {
        StringBuilder s = new StringBuilder(StringUtils.trim(url));
        if (StringUtils.isNotBlank(url))
        {
            s.append("_").append(width).append("x").append(height).append(".").append(FilenameUtils.getExtension(url)).toString();
        }
        return s.toString();
    }
    
    /**
     * 根据品牌编号取品牌域名
     *
     * @param brandesId
     * @return {@link String}
     */
    public static String getBrandsIdSubDomain(String brandesId)
    {
        BrandsDomain domain = brandsDomainService.selectByBrandesId(brandesId);
        if (null == domain) { return "www"; }
        return domain.getDomain();
    }
    
    /**
     * 获取行业资讯导航的所有分类
     *
     * @return {@link List<ArticleCate>}
     */
    public static List<ArticleCate> getAllCate()
    {
        return articleCateService.getAllCates();
    }
    
    /**
     * 取所有资讯的导航菜单
     *
     * @param infoId 当前选中的资讯菜单ID
     * @return
     */
    public static String getInfoNavHtml(String infoId)
    {
        StringBuffer buffer = new StringBuffer();
        if (com.zttx.sdk.utils.StringUtils.isBlank(infoId))
        {
            buffer.append("<li class='item hover'><a href='/info'>首页</a></li>");
        }
        else
        {
            buffer.append("<li class='item'><a href='/info'>首页</a></li>");
        }
        List<ArticleCate> cateList = articleCateService.getAllCates();
        if (null != cateList && cateList.size() > 0)
        {
            for (ArticleCate cate : cateList)
            {
                if (cate.getRefrenceId().equals(infoId))
                {
                    buffer.append("<li class='item hover'>");
                }
                else
                {
                    buffer.append("<li class='item'>");
                }
                buffer.append("<a href='/info/list?cateId=" + cate.getRefrenceId() + "'>" + cate.getCateName() + "</a>");
                if (null != cate.getSubList() && cate.getSubList().size() > 0)
                {
                    buffer.append("<ul class='drop_menu'>");
                    genSubInfoNavHtml(buffer, cate.getSubList());
                    buffer.append("</ul>");
                }
                buffer.append("</li>");
            }
        }
        return buffer.toString();
    }
    
    /**
     * 生成子级资讯菜单
     *
     * @param buffer
     * @param cateList
     */
    static void genSubInfoNavHtml(StringBuffer buffer, List<ArticleCate> cateList)
    {
        for (ArticleCate cate : cateList)
        {
            if (cate.getSubList() != null && cate.getSubList().size() > 0)
            {
                buffer.append("<li class='has'>");
                for (ArticleCate subCate : cate.getSubList())
                {
                    buffer.append("<a href='/info/list?cateId=" + subCate.getRefrenceId() + "'>" + subCate.getCateName() + "</a>");
                    if (subCate.getSubList() != null && subCate.getSubList().size() > 0)
                    {
                        buffer.append("<ul class='menu'>");
                        genSubInfoNavHtml(buffer, subCate.getSubList());
                        buffer.append("</ul>");
                    }
                }
                buffer.append("</li>");
            }
            else
            {
                buffer.append("<li>");
                buffer.append("<a href='/info/list?cateId=" + cate.getRefrenceId() + "'>" + cate.getCateName() + "</a>");
                buffer.append("</li>");
            }
        }
    }
    
    /**
     * 根据帮助分类编号取帮助信息
     *
     * @param cateId
     * @return
     */
    public static List<HelpInfo> getInfosByHelpCateId(String cateId, Integer pageSize)
    {
        Pagination pagination = new Pagination(pageSize);
        return helpInfoService.getInfosByHelpCateId(cateId, pagination);
    }
    
    /**
     * 根据帮助分类编号取帮助信息
     *
     * @param cateId
     * @return
     */
    public static List<RulesInfo> getRulesInfoByCateKey(String cateId, Integer pageSize)
    {
        Pagination pagination = new Pagination(pageSize);
        return rulesInfoService.getRulesInfoByCateKey(cateId, pagination);
    }
    
    /**
     * 取最新的规则信息
     *
     * @param pageSize
     * @return
     */
    public static List<RulesInfo> getNewRulesInfo(Integer pageSize)
    {
        Pagination pagination = new Pagination(pageSize);
        return rulesInfoService.getNewRulesInfo(pagination);
    }
    
    /**
     * 获取所有帮助分类导航
     *
     * @return
     */
    public static String getHelpCateAll(String selectId)
    {
        StringBuffer buffer = new StringBuffer();
        List<HelpCate> helpCateList = helpCateService.getAllHelpCates();
        List<HelpCate> parentHelpCates = helpCateService.getParentHelpCates(selectId);
        if (helpCateList != null && helpCateList.size() > 0)
        {
            for (HelpCate helpCate : helpCateList)
            {
                buffer.append("<li data-id='' " + (isSelectHelpCate(parentHelpCates, helpCate.getRefrenceId()) ? "class='active'" : "") + ">");
                buffer.append("<h3>");
                buffer.append("<a title='" + helpCate.getCateName() + "'");
                buffer.append("href='/help/list?cateId=" + helpCate.getRefrenceId() + "'>");
                buffer.append("<span>" + helpCate.getCateName() + "</span></a>");
                buffer.append("</h3>");
                if (helpCate.getSubList() != null && helpCate.getSubList().size() > 0)
                {
                    printHelpInfo(parentHelpCates, helpCate.getSubList(), buffer);
                }
                buffer.append(" </li>");
            }
        }
        return buffer.toString();
    }
    
    private static boolean isSelectHelpCate(List<HelpCate> helpCates, String cateId)
    {
        if (ListUtils.isNotEmpty(helpCates) && StringUtils.isNotBlank(cateId))
        {
            for (HelpCate helpCate : helpCates)
            {
                if (cateId.equals(helpCate.getRefrenceId())) { return true; }
            }
        }
        return false;
    }
    
    /**
     * 根据类目列表生成导航菜单
     *
     * @param parentHelpCates
     * @param list
     * @param buffer
     */
    static void printHelpInfo(List<HelpCate> parentHelpCates, List<HelpCate> list, StringBuffer buffer)
    {
        for (HelpCate helpCate : list)
        {
            buffer.append("<ul style='display: none;'>");
            buffer.append("<li data-id='' " + (isSelectHelpCate(parentHelpCates, helpCate.getRefrenceId()) ? "class='active'" : "") + ">");
            buffer.append("<a href='/help/list?cateId=" + helpCate.getRefrenceId() + "'>");
            buffer.append(helpCate.getCateName() + "</a>");
            if (helpCate.getSubList() != null && helpCate.getSubList().size() > 0)
            {
                printHelpInfo(parentHelpCates, helpCate.getSubList(), buffer);
            }
            buffer.append("</li>");
            buffer.append("</ul>");
        }
    }
    
    /**
     * 获取所有规则的导航菜单
     *
     * @return
     */
    public static String getRulesCateAll(String selectId)
    {
        StringBuffer buffer = new StringBuffer();
        List<RulesCate> rulesCateList = rulesCateService.getAllRuleCates();
        List<RulesCate> parentRulesCates = rulesCateService.getParentRulesCates(selectId);
        if (rulesCateList != null && rulesCateList.size() > 0)
        {
            for (RulesCate rules : rulesCateList)
            {
                buffer.append(" <li data-id=' '" + (isSelectRulesCate(parentRulesCates, rules.getRefrenceId()) ? "class='active'" : "") + ">");
                buffer.append(" <h3>");
                buffer.append(" <a title='" + rules.getCateName() + "'");
                buffer.append(" href='/rules/list?cateId=" + rules.getRefrenceId() + "'><span>");
                buffer.append(rules.getCateName() + "</span></a>");
                buffer.append(" </h3>");
                if (rules.getSubList() != null && rules.getSubList().size() > 0)
                {
                    printRulesInfo(parentRulesCates, rules.getSubList(), buffer);
                }
                buffer.append(" </li>");
            }
        }
        return buffer.toString();
    }
    
    private static boolean isSelectRulesCate(List<RulesCate> rulesCates, String cateId)
    {
        if (ListUtils.isNotEmpty(rulesCates) && StringUtils.isNotBlank(cateId))
        {
            for (RulesCate rulesCate : rulesCates)
            {
                if (cateId.equals(rulesCate.getRefrenceId())) { return true; }
            }
        }
        return false;
    }
    
    /**
     * 根据规则分类生成导航菜单
     *
     * @param list
     * @param buffer
     */
    static void printRulesInfo(List<RulesCate> parentRulesCates, List<RulesCate> list, StringBuffer buffer)
    {
        for (RulesCate rulesCate : list)
        {
            buffer.append("<ul style='display: none;'>");
            buffer.append("<li data-id='' " + (isSelectRulesCate(parentRulesCates, rulesCate.getRefrenceId()) ? "class='active'" : "") + ">");
            buffer.append("<a href='/rules/list?cateId=" + rulesCate.getRefrenceId() + "'>");
            buffer.append(rulesCate.getCateName() + "</a>");
            if (rulesCate.getSubList() != null && rulesCate.getSubList().size() > 0)
            {
                printRulesInfo(parentRulesCates, rulesCate.getSubList(), buffer);
            }
            buffer.append("</li>");
            buffer.append("</ul>");
        }
    }
    
    /**
     * 获取规则分类组
     *
     * @param cateLevel 类别的级别
     * @param viewNum   需要显示的条数
     * @return {@link List}
     */
    public static List<RulesCate> getRulesCates(Short cateLevel, Integer viewNum)
    {
        RulesCate rulesCate = new RulesCate();
        rulesCate.setCateLevel(cateLevel);
        rulesCate.setPage(new Pagination(1, viewNum));
        return rulesCateService.findList(rulesCate);
    }
    
    /**
     * 获取指定级别的帮助分类
     *
     * @param cateLevel 级别
     * @param viewNum   需要显示的条数
     * @return
     */
    public static PaginateResult<HelpCate> getHelpCateByLevel(Integer cateLevel, Integer viewNum)
    {
        HelpCate helpCate = new HelpCate();
        helpCate.setHelpLevel(cateLevel);
        helpCate.setPage(new Pagination(1, viewNum));
        PaginateResult<HelpCate> result = null;
        List<HelpCate> helpCates = helpCateService.findList(helpCate);
        if (null != helpCates && helpCates.size() > 0)
        {
            result = new PaginateResult(helpCate.getPage(), helpCates);
        }
        return result;
    }
    
    /**
     * 获取广告位
     *
     * @param key 广告位KEY
     * @return
     */
    public static AdvertPosit getAdvertPosit(String key)
    {
        return advertPositService.findAdvertPostByKey(key);
    }
    
    /**
     * 获取分类
     *
     * @param refrenceId 分类ID
     * @return
     */
    public static ArticleCate getArticleCate(String refrenceId)
    {
        return articleCateService.selectByPrimaryKey(refrenceId);
    }
    
    /**
     * 获取指定分类的分页信息
     * 可以没有图片
     * 第二期再改
     *
     * @param cateId  分类的ID
     * @param viewNum 需要显示的条数
     * @return
     */
    public static PaginateResult<Map<String, Object>> findSimpleByInfo(String cateId, Integer viewNum)
    {
        return articleInfoService.findSimplyInfo(cateId, new Pagination(viewNum), Boolean.FALSE);
    }
    
    /**
     * 获取资讯头条。以有图者优先
     *
     * @param viewNum 需要显示的条数
     * @return
     */
    public static PaginateResult<ArticleInfo> findArticleHead(Integer viewNum)
    {
        Pagination pagination = new Pagination(viewNum);
        return articleInfoService.findBy(2, pagination);
    }
    
    /**
     * 获取最新资讯
     *
     * @param viewNum 需要显示的条数
     * @return
     */
    public static PaginateResult<Map<String, Object>> loadLatestNews(Integer viewNum)
    {
        Pagination pagination = new Pagination(viewNum);
        return articleInfoService.loadLatestNews(pagination);
    }
    
    /**
     * 获取指定分类的分页信息
     * 只找有图的
     *
     * @param cateId  分类的ID
     * @param viewNum 需要显示的条数
     * @return
     */
    public static PaginateResult<Map<String, Object>> findSimpleByCate(String cateId, Integer viewNum)
    {
        Pagination pagination = new Pagination(viewNum);
        return articleInfoService.findSimpleByCate(cateId, pagination);
    }
    
    /**
     * 查询最热门的资讯
     *
     * @param index   0：总排行榜 1：昨天排行榜 2：今天排行榜 3：一周排行榜
     * @param viewNum 需要显示的条数
     * @return
     */
    public static PaginateResult<Map<String, Object>> findHotBy(Integer index, Integer viewNum)
    {
        return articleInfoService.findHotBy(index, new Pagination(viewNum));
    }
    
    /**
     * 品牌推荐，查询所有
     *
     * @param viewNum  需要显示的条数
     * @param showType 1:首页感兴趣的品牌  2:入驻加盟中品牌推荐 3：新闻资讯中知名品牌
     * @return
     */
    public static List<BrandsInfoModel> getBrandesInfo(Integer viewNum, Short showType)
    {
        return webBrandsShowService.indexList(new Pagination(1, viewNum), showType);
    }
    
    /**
     * 获取资讯类别中 的子类别
     *
     * @param parendId 父类ID
     * @return
     */
    public static List<ArticleCate> getArticleCates(String parendId)
    {
        return articleCateService.findBy(parendId);
    }
}
