package com.zttx.web.search.query;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.model.HelpFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * <p>File：HelpSolrQueryService.java </p>
 * <p>Title: 帮助索引查询服务 </p>
 * <p>Description: HelpSolrQueryService </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:48</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class HelpSolrQueryService extends BaseSolrQueryService
{
    private String helpSearchUrl = "help/select";
    
    public void setHelpSearchUrl(String helpSearchUrl)
    {
        this.helpSearchUrl = helpSearchUrl;
    }
    
    /**
     * 帮助中心solr搜索接口
     *
     * @param filter
     * @param pagination
     * @throws com.zttx.sdk.exception.BusinessException
     */
    public PaginateResult<Map<String, Object>> searchHelpInfoList(HelpFilter filter, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        nameValuePairs.add(new BasicNameValuePair("df", "text"));
        if (StringUtils.isNotBlank(filterKeyWord(filter.getQ())) && StringUtils.isNotBlank(filter.getCateId()))
        {
            String keyword = filterKeyWord(filter.getQ());
            nameValuePairs.add(new BasicNameValuePair("q", "helpTitle:*" + keyword + "*^1 helpText:*" + keyword + "*^0.5"));
            nameValuePairs.add(new BasicNameValuePair("fq", "helpCateId:" + filter.getCateId() + " parentCateId:" + filter.getCateId()));
        }
        else if (StringUtils.isEmpty(filter.getCateId()) && StringUtils.isEmpty(filterKeyWord(filter.getQ())))
        {
            nameValuePairs.add(new BasicNameValuePair("q", "helpTitle:*"));
        }
        else
        {
            if (StringUtils.isNotBlank(filterKeyWord(filter.getQ())))
            {
                String keyword = filterKeyWord(filter.getQ());
                nameValuePairs.add(new BasicNameValuePair("q", "helpTitle:*" + keyword + "*^1 helpText:*" + keyword + "*^0.5"));
            }
            if (StringUtils.isNotBlank(filter.getCateId()))
            {
                nameValuePairs.add(new BasicNameValuePair("q", "helpCateId:" + filter.getCateId() + " parentCateId:" + filter.getCateId()));
            }
        }
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(helpSearchUrl).append("?").append(paramStr).toString();
        return sendUrl(uriStr, pagination);
    }
}
