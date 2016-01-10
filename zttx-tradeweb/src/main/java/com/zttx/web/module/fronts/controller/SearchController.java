package com.zttx.web.module.fronts.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.SearchConst;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.module.dealer.mapper.DealerCollectMapper;
import com.zttx.web.module.fronts.model.ArticleFilter;
import com.zttx.web.module.fronts.model.HelpFilter;
import com.zttx.web.module.fronts.model.RulesFilter;
import com.zttx.web.module.fronts.model.SolrFilter;
import com.zttx.web.search.module.FacetField;
import com.zttx.web.search.query.*;

/**
 * <p>File：SearchController.java </p>
 * <p>Title: 搜索控制器 </p>
 * <p>Description: SearchController </p>
 * <p>Copyright: Copyright (c) 2014 08/18/2015 17:42</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/search")
public class SearchController extends GenericController
{
    public static final Logger      logger = LoggerFactory.getLogger(SearchController.class);
    
    @Autowired
    private ProductSolrQueryService productSolrQueryService;
    
    @Autowired
    private BrandeSolrQueryService  brandeSolrQueryService;
    
    @Autowired
    private ArticleSolrQueryService articleSolrQueryService;
    
    @Autowired
    private HelpSolrQueryService    helpSolrQueryService;
    
    @Autowired
    private RuleSolrQueryService    ruleSolrQueryService;
    
    @Autowired
    private ProductInfoService      productInfoService;
    
    @Autowired
    private BrandesInfoService      brandesInfoService;
    
    @Autowired
    private DealDicService          dealDicService;
    
    @Autowired
    private DealerCollectMapper     dealerCollectMapper;
    
    /**
     * 综合搜索，主要针对产品和品牌进行搜索
     * @param filter
     * @param pagination
     * @return {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping(value = "")
    public ModelAndView execute(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/search/product_list");

        PaginateResult<Map<String, Object>> result;
        try
        {
            if (SearchConst.SEARCH_BRAND_TYPE.equals(filter.getSt()))
            {
                mav.setViewName("fronts/search/brand_list");
                if (StringUtils.isBlank(filter.getMainId()) && StringUtils.isBlank(filter.getMainName()))
                {
                    filter.setSearchAll(Boolean.TRUE);
                }
                if(null != pagination) pagination.setPageSize(30);
                result = brandesInfoService.searchAllBrandes(filter, pagination);
            }
            else
            {
                if (StringUtils.isBlank(filter.getMainId()))
                {
                    filter.setSearchAll(Boolean.TRUE);
                }
                if (StringUtils.isNotBlank(filter.getBrandsId()))
                {
                    List<String> ids = Lists.newArrayList();
                    String[] brandsIds = filter.getBrandsId().split(",");
                    if (brandsIds.length == 1)
                    {
                        ids.add(brandsIds[0]);
                    }
                    else
                    {
                        for (String id : brandsIds)
                            ids.add(id);
                    }
                    mav.addObject("brandesList", brandesInfoService.findBrandesInfoByIds(ids,filter.getMainId()));
                }
                if (StringUtils.isNotBlank(filter.getDealId()))
                {
                    mav.addObject("dealDic", dealDicService.getDealDicByDealNo(Integer.valueOf(filter.getDealId())));
                }
                if(null != pagination) pagination.setPageSize(50);
                result = productInfoService.searchAllProduct(filter, pagination);
            }
            filter.setUrl("/search");
            mav.addObject("result", result);
            mav.addObject("filter", filter);
        }
        catch (BusinessException e)
        {
            logger.error("search error:", e.getLocalizedMessage());
        }
        return mav;
    }
    
    /**
     * 热门搜索
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/loadHotList")
    public JsonMessage loadHostList() throws BusinessException
    {
        JsonMessage json = this.getJsonMessage(CommonConst.SUCCESS);
        return json;
    }
    
    /**
     * 自动补全查询
     * @param filter
     * @return {@link JsonMessage}
     */
    @ResponseBody
    @RequestMapping("/autoCompletion")
    public JsonMessage autoCompletion(SolrFilter filter)
    {
        JsonMessage json = new JsonMessage(CommonConst.FAIL);
        List<FacetField> result;
        try
        {
            if (SearchConst.SEARCH_BRAND_TYPE.equals(filter.getSt()))
            { // 品牌查询
                result = brandeSolrQueryService.autoCompletion(filter);
                json = this.getJsonMessage(CommonConst.SUCCESS, result);
            }
            else
            { // 产品查询
                result = productSolrQueryService.autoCompletionByFacet(filter);
                json = this.getJsonMessage(CommonConst.SUCCESS, result);
            }
        }
        catch (BusinessException e)
        {
            logger.error("autoCompletion error:", e.getLocalizedMessage());
        }
        return json;
    }
    
    /**
     * 帮助搜索
     * @param filter
     * @param pagination
     * @return {@link ModelAndView}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/help")
    public JsonMessage searchHelp(HelpFilter filter, Pagination pagination) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result = null;
        try
        {
            result = helpSolrQueryService.searchHelpInfoList(filter, pagination);
        }
        catch (BusinessException e)
        {
            logger.error("searchHelp error:", e.getLocalizedMessage());
        }
        return getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 规则搜索
     * @param filter
     * @param pagination
     * @return {@link ModelAndView}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/rules")
    public JsonMessage searchRules(RulesFilter filter, Pagination pagination) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result = null;
        try
        {
            result = ruleSolrQueryService.searchRulesList(filter, pagination);
        }
        catch (BusinessException e)
        {
            logger.error("searchRules error:", e.getLocalizedMessage());
        }
        return getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 资讯搜索
     * @param filter
     * @param pagination
     * @return {@link ModelAndView}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/info")
    public JsonMessage searchInfo(ArticleFilter filter, Pagination pagination) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result = null;
        try
        {
            result = articleSolrQueryService.searchArticleInfoList(filter, pagination);
        }
        catch (BusinessException e)
        {
            logger.error("searchInfo error:", e.getLocalizedMessage());
        }
        return getJsonMessage(CommonConst.SUCCESS, result);
    }
}
