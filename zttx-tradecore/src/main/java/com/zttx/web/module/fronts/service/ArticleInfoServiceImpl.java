/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.PaginateUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.IndexConst;
import com.zttx.web.module.fronts.entity.ArticleCate;
import com.zttx.web.module.fronts.entity.ArticleInfo;
import com.zttx.web.module.fronts.mapper.ArticleInfoMapper;
import com.zttx.web.module.fronts.model.ArticleFilter;
import com.zttx.web.search.module.SolrResponse;

/**
 * 资讯文章信息 服务实现类
 * <p>File：ArticleInfo.java </p>
 * <p>Title: ArticleInfo </p>
 * <p>Description:ArticleInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ArticleInfoServiceImpl extends GenericServiceApiImpl<ArticleInfo> implements ArticleInfoService
{
    static final Logger        LOGGER     = Logger.getLogger(ArticleInfoServiceImpl.class);
    
    RestTemplate               restTemplate;
    
    NameValuePair              jsonFormat = new BasicNameValuePair("wt", "json");          // 默认返回json格式
    
    private ArticleInfoMapper  articleInfoMapper;
    
    @Autowired
    private ArticleCateService articleCateService;
    
    private String             articleSearchUrl;                                           // 资讯中心
    
    private String             serverUrl;                                                  // solr访问路径
    
    @Autowired(required = true)
    public ArticleInfoServiceImpl(ArticleInfoMapper articleInfoMapper)
    {
        super(articleInfoMapper);
        this.articleInfoMapper = articleInfoMapper;
    }
    
    public RestTemplate getRestTemplate()
    {
        return restTemplate;
    }
    
    public void setRestTemplate(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }
    
    @Override
    public Long findArticleToSolrCount(ArticleInfo articleInfo) throws BusinessException
    {
        return articleInfoMapper.findArticleToSolrCount(articleInfo);
    }
    
    @Override
    public List<ArticleInfo> findArticleToSolr(ArticleInfo articleInfo, Pagination pagination) throws BusinessException
    {
        List<ArticleInfo> articleInfos = articleInfoMapper.findArticleToSolr(articleInfo, pagination);
        for (ArticleInfo info : articleInfos)
        {
            if (StringUtils.isNotBlank(info.getCateId()))
            {
                info.setArticleCates(articleCateService.getParentArticleCates(info.getCateId()));
            }
        }
        return articleInfos;
    }
    
    /**
     * 分页查询出简单信息
     */
    @Override
    public PaginateResult<Map<String, Object>> findSimplyInfo(String cateId, Pagination pagination, Boolean hasImg)
    {
        List<ArticleInfo> list = articleInfoMapper.list(cateId, pagination, hasImg);
        return new PaginateResult(pagination, list);
    }
    
    @Override
    public PaginateResult<Map<String, Object>> searchArticleInfoList(ArticleFilter filter, Pagination pagination)
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        if (StringUtils.isNotBlank(filterKeyWord(filter.getQ())))
        {
            nameValuePairs.add(new BasicNameValuePair("df", "article_all"));
            nameValuePairs.add(new BasicNameValuePair("q", "articleTitle:*" + filterKeyWord(filter.getQ()) + "*^1 articleText:*" + filterKeyWord(filter.getQ()) + "*^0.5"));
        }
        if (StringUtils.isNotBlank(filter.getCateId())) nameValuePairs.add(new BasicNameValuePair("q", "articleCate:" + filterKeyWord(filter.getCateId())));
        if (StringUtils.isEmpty(filter.getCateId()) && StringUtils.isEmpty(filterKeyWord(filter.getQ())))
            nameValuePairs.add(new BasicNameValuePair("q", "articleTitle:*"));
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = serverUrl + articleSearchUrl + "?" + paramStr;
        try
        {
            return sendUrl(uriStr, pagination);
        }
        catch (BusinessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 过滤关键词
     * @param keyword
     * @return
     */
    String filterKeyWord(String keyword)
    {
        if (StringUtils.isNotBlank(keyword))
        {
            keyword = ClientUtils.escapeQueryChars(keyword);
            keyword = Jsoup.clean(keyword, new Whitelist());
            keyword = keyword.replaceAll("prompt", "").replaceAll("%22", "").replaceAll("\\*", "").replaceAll("\"", "").replaceAll("@", "").replaceAll("%", "")
                    .replaceAll("$", "").replaceAll("//", "").replaceAll("\\)", "").replaceAll("\\(", "");
            keyword = com.zttx.web.utils.StringUtils.xssEncode(keyword);
            keyword = ClientUtils.escapeQueryChars(keyword);
            keyword = StringEscapeUtils.unescapeHtml4(keyword);
            if (keyword.length() > 64) keyword = keyword.substring(0, 60);
            return keyword;
        }
        else return "";
    }
    
    /**
     * 发送请求
     *
     * @param url
     * @param pagination
     * @return
     * @throws BusinessException
     */
    PaginateResult<Map<String, Object>> sendUrl(String url, Pagination pagination) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result = new PaginateResult<Map<String, Object>>();
        URI uri = null;
        try
        {
            uri = new URI(url);
            ResponseEntity<SolrResponse> entity = restTemplate.getForEntity(uri, SolrResponse.class);
            if (!entity.getStatusCode().equals(HttpStatus.OK)) throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
            SolrResponse solrResponse = entity.getBody();
            result.setPage(getPagination(solrResponse, pagination));
            result.setList(solrResponse.getResponse().getDocs());
        }
        catch (URISyntaxException e)
        {
            LOGGER.error("Solr 查询 url 非法 " + url, e);
            throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
        }
        catch (ResourceAccessException e)
        {
            LOGGER.error("Solr 无法访问 " + uri, e);
        }
        return result;
    }
    
    Pagination getPagination(SolrResponse solrResponse, Pagination pagination)
    {
        return PaginateUtils.getPagination(pagination, solrResponse.getResponse().getNumFound());
    }
    
    @Override
    public PaginateResult<ArticleInfo> findBy(String cateId, Pagination pagination)
    {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setCateId(cateId);
        /**
         * 查询所有的信息分页显示
         */
        if (IndexConst.INFO_ALL.equals(cateId))
        {
            articleInfo.setPage(pagination);
            articleInfo.setCateId(cateId);
            /**
             * 返回分页对象
             */
            return new PaginateResult(articleInfo.getPage(), articleInfoMapper.findList(articleInfo));
        }
        else if (IndexConst.INFO_HOT.equals(cateId))
        {
            // /**
            // * 查询热门，有图片的前四条
            // */
            return new PaginateResult(pagination, articleInfoMapper.findBy(4, pagination));
        }
        /**
         *通过通过浏览的次数从高到低来排序
         * 通过有图的文章信息放在最前面
         */
        else if (IndexConst.ORDER.equals(cateId))
        {
            new PaginateResult(pagination, articleInfoMapper.findOrder(pagination));
        }
        ArticleCate articleCate = articleCateService.selectByPrimaryKey(cateId);
        if (articleCate == null) { return new PaginateResult<ArticleInfo>(new Pagination(), Collections.<ArticleInfo> emptyList()); }
        /**
         * 通过父类别来查询文章类别信息
         */
        ArticleCate parentArticleCate = articleCateService.selectByPrimaryKey(articleCate.getParentId());
        if (parentArticleCate == null) // 主级
        {
            return this.findAllBy(cateId, pagination);
        }
        else
        { // 子类
            return new PaginateResult<ArticleInfo>(new Pagination(), articleInfoMapper.findByCateId(cateId, pagination));
        }
    }
    
    @Override
    public PaginateResult<ArticleInfo> findAllBy(String cateId, Pagination pagination)
    {
        Long totalCount = articleInfoMapper.count(cateId);
        pagination = PaginateUtils.getPagination(pagination, totalCount);
        List<ArticleInfo> list = articleInfoMapper.list(cateId, pagination, Boolean.FALSE);
        PaginateResult<ArticleInfo> result = new PaginateResult<ArticleInfo>(pagination, list);
        return result;
    }
    
    @Override
    public PaginateResult<ArticleInfo> findPrvSix(Integer type, Pagination pagination)
    {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setPage(pagination);
        List<ArticleInfo> helpInfoList = selectAll();
        return new PaginateResult(articleInfo.getPage(), helpInfoList);
    }
    
    /**
     *  /**
     * 资讯文章信息 服务实现类
     * <p>File：ArticleInfoSerivce.java </p>
     * <p>Title: ArticleInfoSerivce </p>
     * <p>Description:ArticleInfo 查询最新的新闻信息</p>
     * <p>Copyright: Copyright (c) May 26, 2015</p>
     * <p>Company: 8637.com</p>
     * @author jimingqing
     * @version 1.0
     */
    @Override
    public PaginateResult<Map<String, Object>> loadLatestNews(Pagination pagination)
    {
        List<Map<String, Object>> lastNewList = articleInfoMapper.loadLatestNews(pagination);
        return new PaginateResult(pagination, lastNewList);
    }
    
    @Override
    public PaginateResult findSimpleByCate(String cateId, Pagination pagination)
    {
        return findSimplyInfo(cateId, pagination, Boolean.TRUE);
    }
    
    /*
     */
    @Override
    public PaginateResult findHotBy(Integer type, Pagination pagination)
    {
        List<Map<String, Object>> list = articleInfoMapper.findHotBy(type, pagination);
        return new PaginateResult(pagination, list);
    }
    
    @Override
    public PaginateResult<ArticleInfo> findBy(Integer type, Pagination pagination)
    {
        List<ArticleInfo> list = articleInfoMapper.findBy(type, pagination);
        return new PaginateResult(pagination, list);
    }
    
    /**
     * @param page              分页条件
     * @param articleInfoFilter 条件过滤对象
     * @param orderCriteria     分页条件
     * @return 分页数据
     * @author 章旭楠
     */
    @Override
    public PaginateResult<ArticleInfo> search(Pagination page, ArticleInfo articleInfoFilter, ArticleInfo.OrderCriteria orderCriteria)
    {
        articleInfoFilter.setPage(page);
        List<ArticleInfo> articleInfoList = articleInfoMapper.search(articleInfoFilter, page, orderCriteria);
        return new PaginateResult<>(articleInfoFilter.getPage(), articleInfoList);
    }
    
    /**
     * 保存(支撑接口调用)
     *
     * @param articleInfo 对象
     * @throws BusinessException
     */
    @Override
    public ArticleInfo saveByClient(ArticleInfo articleInfo) throws BusinessException
    {
        if (StringUtils.isNotBlank(articleInfo.getRefrenceId()))
        // to update
        {
            articleInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(articleInfo);
        }
        else
        // to create
        {
            articleInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            articleInfo.setCreateTime(CalendarUtils.getCurrentLong());
            articleInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            articleInfo.setViewNum(0);
            articleInfo.setShareNum(0);
            articleInfo.setCommentNum(0);
            articleInfo.setCollectNum(0);
            this.insertSelective(articleInfo);
        }
        // 更新文章数量
        articleCateService.updateArticleNum(articleInfo.getCateId(), 0);
        return articleInfo;
    }
}
