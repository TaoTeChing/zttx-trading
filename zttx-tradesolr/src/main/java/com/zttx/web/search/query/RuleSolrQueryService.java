package com.zttx.web.search.query;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.model.RulesFilter;

/**
 * <p>File：RuleSolrQueryService.java </p>
 * <p>Title: 规章制度索引查询服务 </p>
 * <p>Description: RuleSolrQueryService </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class RuleSolrQueryService extends BaseSolrQueryService
{
    private String rulesSearchUrl = "rules/select";
    
    public void setRulesSearchUrl(String rulesSearchUrl)
    {
        this.rulesSearchUrl = rulesSearchUrl;
    }
    
    /**
     * 规则中心solr搜素接口
     *
     * @param filter
     * @param pagination
     * @throws com.zttx.sdk.exception.BusinessException
     * @return  {@link PaginateResult}
     */
    public PaginateResult<Map<String, Object>> searchRulesList(RulesFilter filter, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        if (StringUtils.isNotBlank(filterKeyWord(filter.getQ())) && StringUtils.isNotBlank(filter.getCateId()))
        {
            String keyword = filterKeyWord(filter.getQ());
            nameValuePairs.add(new BasicNameValuePair("q", "articleTitle:*" + keyword + "*^1 articleText:*" + keyword + "*^0.5"));
            nameValuePairs.add(new BasicNameValuePair("fq", "infoCateId:" + filter.getCateId() + " parentCateId:" + filter.getCateId()));
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
                nameValuePairs.add(new BasicNameValuePair("q", "infoCateId:" + filter.getCateId() + " parentCateId:" + filter.getCateId()));
            }
        }
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(rulesSearchUrl).append("?").append(paramStr).toString();
        return sendUrl(uriStr, pagination);
    }
}
