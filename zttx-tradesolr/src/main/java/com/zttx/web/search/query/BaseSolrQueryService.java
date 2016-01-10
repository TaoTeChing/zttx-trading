package com.zttx.web.search.query;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.PaginateUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.search.module.SolrResponse;

/**
 * <p>File：BaseSolrQueryService.java </p>
 * <p>Title: 基础的SOLR查询服务 </p>
 * <p>Description: BaseSolrQueryService </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:36</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class BaseSolrQueryService implements Serializable
{
    private static final long      serialVersionUID = 144735043154306211L;
    
    // 默认返回json格式
    protected static NameValuePair jsonFormat       = new BasicNameValuePair("wt", "json");
    
    public static RestTemplate     restTemplate;
    
    public static String           serverUrl;
    
    /**
     * 过滤数据
     * @param keyword
     * @return
     */
    protected String filterKeyWord(String keyword)
    {
        if (com.zttx.sdk.utils.StringUtils.isNotBlank(keyword))
        {
            keyword = ClientUtils.escapeQueryChars(keyword);
            keyword = Jsoup.clean(keyword, new Whitelist());
            keyword = keyword.replaceAll("prompt", "").replaceAll("%22", "").replaceAll("\\*", "");
            keyword = keyword.replaceAll("\"", "").replaceAll("@", "").replaceAll("%", "").replaceAll("$", "");
            keyword = keyword.replaceAll("//", "").replaceAll("\\)", "").replaceAll("\\(", "");
            keyword = com.zttx.sdk.utils.StringUtils.xssEncode(keyword);
            keyword = ClientUtils.escapeQueryChars(keyword);
            keyword = StringEscapeUtils.unescapeHtml4(keyword);
            if (keyword.length() > 64) keyword = keyword.substring(0, 60);
            return keyword;
        }
        else return "";
    }
    
    /**
     * 生成基础的查询参数对象
     *
     * @param params 参数值
     * @return {@link org.apache.http.message.BasicNameValuePair}
     */
    BasicNameValuePair genQueryParamsValue(Map<String, String> params)
    {
        BasicNameValuePair basicNameValuePair = null;
        StringBuilder stringBuilder = new StringBuilder("");
        if (params != null && params.size() > 0)
        {
            int index = 0;
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                if (index != 0) stringBuilder.append(" AND " + entry.getKey() + ":" + entry.getValue());
                else stringBuilder.append(entry.getKey() + ":" + entry.getValue());
                index++;
            }
        }
        else stringBuilder.append("*:*");
        basicNameValuePair = new BasicNameValuePair("q", stringBuilder.toString());
        return basicNameValuePair;
    }
    
    /**
     * 生成基础的过滤参数对象
     * @param params 参数值
     * @return {@link org.apache.http.message.BasicNameValuePair}
     */
    BasicNameValuePair genFilterQueryParamsValue(Map<String, String> params)
    {
        BasicNameValuePair basicNameValuePair = null;
        StringBuilder stringBuilder = new StringBuilder("");
        if (params != null && params.size() > 0)
        {
            int index = 0;
            for (Map.Entry<String, String> entry : params.entrySet())
            {
                if (index != 0) stringBuilder.append(" AND " + entry.getKey() + ":" + entry.getValue());
                else stringBuilder.append(entry.getKey() + ":" + entry.getValue());
                index++;
            }
        }
        else stringBuilder.append("*:*");
        basicNameValuePair = new BasicNameValuePair("fq", stringBuilder.toString());
        return basicNameValuePair;
    }
    
    /**
     * 生成基础的排序参数对象
     *
     * @param sorts 排序对象值
     * @return {@link org.apache.http.message.BasicNameValuePair}
     */
    BasicNameValuePair genSortParamsValue(Map<String, String> sorts)
    {
        BasicNameValuePair basicNameValuePair = null;
        StringBuilder stringBuilder = new StringBuilder("");
        if (sorts != null && sorts.size() > 0)
        {
            int index = 0;
            for (Map.Entry<String, String> entry : sorts.entrySet())
            {
                if (index != 0) stringBuilder.append("," + entry.getKey() + " " + entry.getValue());
                else stringBuilder.append(entry.getKey() + " " + entry.getValue());
                index++;
            }
            basicNameValuePair = new BasicNameValuePair("sort", stringBuilder.toString());
        }
        return basicNameValuePair;
    }
    
    /**
     * 处理高亮
     *
     * @param response
     */
    void highlightingResponse(SolrResponse response)
    {
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        if (null != highlighting && highlighting.size() > 0)
        {
            List<Map<String, Object>> docs = response.getResponse().getDocs();
            for (int i = 0; i < docs.size(); i++)
            {
                Map<String, Object> doc = docs.get(i);
                String id = doc.get("refrenceId").toString();
                Map<String, List<String>> highlight = highlighting.get(id);
                if (null != highlight)
                {
                    for (String field : highlight.keySet())
                    {
                        Object hl = doc.get(field);
                        if (null != hl)
                        {
                            hl = highlight.get(field);
                            if (null != hl) doc.put(field, hl.toString().replace("[", "").replace("]", ""));
                        }
                    }
                }
            }
        }
        response.setHighlighting(null);
    }
    
    /**
     * 发送请求
     * @param url
     * @return
     * @throws BusinessException
     */
    protected SolrResponse sendUrl(String url) throws BusinessException
    {
        SolrResponse solrResponse = null;
        try
        {
            ResponseEntity<SolrResponse> entity = restTemplate.getForEntity(new URI(url), SolrResponse.class);
            if (!entity.getStatusCode().equals(HttpStatus.OK)) throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
            solrResponse = entity.getBody();
        }
        catch (URISyntaxException e)
        {
            throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
        }
        catch (ResourceAccessException e)
        {
            throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
        }
        return solrResponse;
    }
    
    /**
     * 取List类型数据
     * @param url
     * @return
     * @throws BusinessException
     */
    protected List<Map<String, Object>> getLists(String url) throws BusinessException
    {
        List<Map<String, Object>> result;
        try
        {
            ResponseEntity<SolrResponse> entity = restTemplate.getForEntity(new URI(url), SolrResponse.class);
            if (!entity.getStatusCode().equals(HttpStatus.OK)) throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
            SolrResponse solrResponse = entity.getBody();
            result = solrResponse.getResponse().getDocs();
        }
        catch (URISyntaxException e)
        {
            throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
        }
        catch (ResourceAccessException e)
        {
            throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
        }
        return result;
    }
    
    /**
     * 发送请求
     *
     * @param url
     * @param pagination
     * @return   {@link PaginateResult}
     * @throws BusinessException
     */
    protected PaginateResult<Map<String, Object>> sendUrl(String url, Pagination pagination) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result = new PaginateResult();
        try
        {
            ResponseEntity<SolrResponse> entity = restTemplate.getForEntity(new URI(url), SolrResponse.class);
            if (!entity.getStatusCode().equals(HttpStatus.OK)) throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
            SolrResponse solrResponse = entity.getBody();
            result.setPage(getPagination(solrResponse, pagination));
            result.setList(solrResponse.getResponse().getDocs());
        }
        catch (URISyntaxException e)
        {
            throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
        }
        catch (ResourceAccessException e)
        {
            throw new BusinessException(CommonConst.SEARCH_ENGINE_ERROR);
        }
        return result;
    }
    
    /**
     * 获取分页对象
     * @param solrResponse
     * @param pagination
     * @return {@link Pagination}
     */
    Pagination getPagination(SolrResponse solrResponse, Pagination pagination)
    {
        return PaginateUtils.getPagination(pagination, solrResponse.getResponse().getNumFound());
    }
    
    public void setRestTemplate(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }
    
    public void setServerUrl(String serverUrl)
    {
        this.serverUrl = serverUrl;
    }
}
