package com.zttx.web.module.serach.controller;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.SearchConst;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.module.fronts.entity.ArticleInfo;
import com.zttx.web.module.fronts.entity.HelpInfo;
import com.zttx.web.module.fronts.entity.RulesInfo;
import com.zttx.web.module.fronts.service.ArticleInfoService;
import com.zttx.web.module.fronts.service.HelpInfoService;
import com.zttx.web.module.fronts.service.RulesInfoService;
import com.zttx.web.search.solrj.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>File：SolrClientController.java </p>
 * <p>Title: 搜索引擎接口控制器 </p>
 * <p>Description: SolrClientController </p>
 * <p>Copyright: Copyright (c) 15/9/1</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/solr")
public class ManagerSolrController extends GenericController
{
    @Autowired
    private HelpInfoService    helpInfoService;
    
    @Autowired
    private ArticleInfoService articleInfoService;
    
    @Autowired
    private RulesInfoService   rulesInfoService;
    
    @Autowired
    private ProductInfoService productInfoService;
    
    @Autowired
    private BrandesInfoService brandesInfoService;
    
    @Autowired
    private ProductSolrHandler productSolrHandler;
    
    @Autowired
    private BrandeSolrHandler  brandeSolrHandler;
    
    @Autowired
    private HelpSolrHandler    helpSolrHandler;
    
    @Autowired
    private RuleSolrHandler    ruleSolrHandler;
    
    @Autowired
    private ArticleSolrHandler articleSolrHandler;
    
    /**
     * 建立Solr索引
     *
     * @param request
     * @param core    实例名称，如：product,help,rule
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/buildSolr")
    public JsonMessage buildSolr(HttpServletRequest request, String core) throws BusinessException
    {
        JsonMessage json = new JsonMessage(CommonConst.FAIL);
        StringBuffer sb = new StringBuffer("");
        if (StringUtils.isNotBlank(core))
        {
            if (SearchConst.CORE0.equals(core))
            {
                sb.append("solr服务器地址:" + SolrjHandler.getSolrServerInvokeUrl()).append("<p><p>");
                Long rowNums = brandesInfoService.findBrandesInfoToSolrCount(null);
                Integer pageSize = 100;
                Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
                Pagination page = new Pagination(1, pageSize);
                for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
                {
                    List<BrandesInfo> brandesInfos = brandesInfoService.findBrandesInfoToSolr(null, page);
                    brandeSolrHandler.addBrandsInfoList(brandesInfos);
                    page = new Pagination(currentPage.intValue() + 1, pageSize);
                }
                sb.append("符合记录的品牌有:" + rowNums).append("<p>");
                sb.append("结束<p>");
                json.setCode(CommonConst.SUCCESS.code);
                json.setMessage(sb.toString());
            }
            else if (SearchConst.CORE1.equals(core))
            {
                sb.append("solr服务器地址:" + SolrjHandler.getSolrServerInvokeUrl()).append("<p><p>");
                Long rowNums = productInfoService.findProductToSolrCount(null);
                Integer pageSize = 100;
                Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
                Pagination page = new Pagination(1, pageSize);
                for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
                {
                    List<ProductInfo> productInfos = productInfoService.findProductToSolr(null, page);
                    productSolrHandler.addProductInfoList(productInfos);
                    page = new Pagination(currentPage.intValue() + 1, pageSize);
                }
                sb.append("符合记录的产品有:" + rowNums).append("<p>");
                sb.append("结束<p>");
                json.setCode(CommonConst.SUCCESS.code);
                json.setMessage(sb.toString());
            }
            else if (SearchConst.CORE2.equals(core))
            {
                sb.append("solr服务器地址:" + SolrjHandler.getSolrServerInvokeUrl()).append("<p><p>");
                Long rowNums = articleInfoService.findArticleToSolrCount(null);
                Integer pageSize = 100;
                Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
                Pagination page = new Pagination(1, pageSize);
                for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
                {
                    List<ArticleInfo> articles = articleInfoService.findArticleToSolr(null, page);
                    articleSolrHandler.addArticleInfoList(articles);
                    page = new Pagination(currentPage.intValue() + 1, pageSize);
                }
                sb.append("符合记录的资讯有:" + rowNums).append("<p>");
                sb.append("结束<p>");
                json.setCode(CommonConst.SUCCESS.code);
                json.setMessage(sb.toString());
            }
            else if (SearchConst.CORE3.equals(core))
            {
                sb.append("solr服务器地址:" + SolrjHandler.getSolrServerInvokeUrl()).append("<p><p>");
                Long rowNums = rulesInfoService.findRulesToSolrCount(null);
                Integer pageSize = 100;
                Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
                Pagination page = new Pagination(1, pageSize);
                for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
                {
                    List<RulesInfo> articles = rulesInfoService.findRulesToSolr(null, page);
                    ruleSolrHandler.addRulesInfoList(articles);
                    page = new Pagination(currentPage.intValue() + 1, pageSize);
                }
                sb.append("符合记录的规则信息有:" + rowNums).append("<p>");
                sb.append("结束<p>");
                json.setCode(CommonConst.SUCCESS.code);
                json.setMessage(sb.toString());
            }
            else if (SearchConst.CORE4.equals(core))
            {
                sb.append("solr服务器地址:" + SolrjHandler.getSolrServerInvokeUrl()).append("<p><p>");
                Long rowNums = helpInfoService.findHelpToSolrCount(null);
                Integer pageSize = 100;
                Long pageCounts = (rowNums % pageSize) > 0 ? (rowNums / pageSize) + 1 : rowNums / pageSize;
                Pagination page = new Pagination(1, pageSize);
                for (Long currentPage = 1l; currentPage <= pageCounts; currentPage++)
                {
                    List<HelpInfo> helpinfos = helpInfoService.findHelpToSolr(null, page);
                    helpSolrHandler.addHelpInfoList(helpinfos);
                    page = new Pagination(currentPage.intValue() + 1, pageSize);
                }
                sb.append("符合记录的帮助信息有:" + rowNums).append("<p>");
                sb.append("结束<p>");
                json.setCode(CommonConst.SUCCESS.code);
                json.setMessage(sb.toString());
            }
        }
        return json;
    }
    
    /**
     * 根据产品编号重新发布产品索引
     * @param ids
     * @return {@link JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/rebuildProduct")
    public JsonMessage publishProduct(String ids) throws BusinessException
    {
        if (null != ids && StringUtils.isNotBlank(ids))
        {
            String[] refrenceIds = ids.split(",");
            for (String refrenceId : refrenceIds)
            {
                ProductInfo filter = new ProductInfo();
                filter.setRefrenceId(refrenceId);
                List<ProductInfo> productInfos = productInfoService.findProductToSolr(filter, null);
                productSolrHandler.addProductInfoList(productInfos);
            }
            return getJsonMessage(CommonConst.SUCCESS);
        }
        return getJsonMessage(CommonConst.FAIL);
    }
    
    /**
     * 重建Solr索引
     * <p>
     * 新清空后再全新构建
     * </p>
     *
     * @param request
     * @param core    实例名称，如：product,help,rule
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/rebuildSolr")
    public JsonMessage rebuildSolr(HttpServletRequest request, String core) throws BusinessException
    {
        cleanSolr(request, core);
        return buildSolr(request, core);
    }
    
    /**
     * 清空Solr 索引
     *
     * @param request
     * @param core
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/cleanSolr")
    public JsonMessage cleanSolr(HttpServletRequest request, String core) throws BusinessException
    {
        JsonMessage json = new JsonMessage(CommonConst.FAIL);
        if (SolrjHandler.delDocByQuery("*:*", core))
        {
            json = new JsonMessage(CommonConst.SUCCESS);
        }
        return json;
    }
}
