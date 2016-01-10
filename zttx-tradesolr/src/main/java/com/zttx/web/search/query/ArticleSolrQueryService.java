package com.zttx.web.search.query;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.fronts.model.ArticleFilter;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * <p>File：ArticleSolrQueryService.java </p>
 * <p>Title: 资讯索引查询服务 </p>
 * <p>Description: ArticleSolrQueryService </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class ArticleSolrQueryService extends BaseSolrQueryService
{
    private String articleSearchUrl = "article/select";
    
    public void setArticleSearchUrl(String articleSearchUrl)
    {
        this.articleSearchUrl = articleSearchUrl;
    }
    
    /**
     * 资讯中心solr搜索接口
     *
     * @param filter
     * @param pagination
     * @throws com.zttx.sdk.exception.BusinessException
     */
    public PaginateResult<Map<String, Object>> searchArticleInfoList(ArticleFilter filter, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        nameValuePairs.add(new BasicNameValuePair("df", "text"));
        if (StringUtils.isNotBlank(filterKeyWord(filter.getQ())) && StringUtils.isNotBlank(filter.getCateId()))
        {
            String keyword = filterKeyWord(filter.getQ());
            nameValuePairs.add(new BasicNameValuePair("q", "articleTitle:*" + keyword + "*^1 articleText:*" + keyword + "*^0.5"));
            nameValuePairs.add(new BasicNameValuePair("fq", "cateId:" + filter.getCateId() + " parentCateId:" + filter.getCateId()));
        }
        else if (StringUtils.isEmpty(filter.getCateId()) && StringUtils.isEmpty(filterKeyWord(filter.getQ())))
        {
            nameValuePairs.add(new BasicNameValuePair("q", "articleTitle:*"));
        }
        else
        {
            if (StringUtils.isNotBlank(filterKeyWord(filter.getQ())))
            {
                String keyword = filterKeyWord(filter.getQ());
                nameValuePairs.add(new BasicNameValuePair("q", "articleTitle:*" + keyword + "*^1 articleText:*" + keyword + "*^0.5"));
            }
            if (StringUtils.isNotBlank(filter.getCateId()))
            {
                nameValuePairs.add(new BasicNameValuePair("q", "cateId:" + filter.getCateId() + " parentCateId:" + filter.getCateId()));
            }
        }
        Map<String, String> sorts = Maps.newHashMap();
        sorts.put("createTime","desc");
        nameValuePairs.add(genSortParamsValue(sorts));
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(articleSearchUrl).append("?").append(paramStr).toString();
        return sendUrl(uriStr, pagination);
    }
}
