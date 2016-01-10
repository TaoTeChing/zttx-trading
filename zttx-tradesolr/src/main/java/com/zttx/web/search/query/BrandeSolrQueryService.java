package com.zttx.web.search.query;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.LoggerUtils;
import com.zttx.web.module.fronts.model.SolrFilter;
import com.zttx.web.search.module.FacetField;
import com.zttx.web.search.module.SolrModel;
import com.zttx.web.search.module.SolrResponse;
import com.zttx.web.search.utils.FacetUtils;

/**
 * <p>File：BrandeSolrQueryService.java </p>
 * <p>Title: 品牌索引查询服务 </p>
 * <p>Description: BrandeSolrQueryService </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:47</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class BrandeSolrQueryService extends BaseSolrQueryService
{
    Logger         logger          = LoggerFactory.getLogger(BrandeSolrQueryService.class);
    
    private String brandsSearchUrl = "brandes/select";
    
    private String brandsTermUrl   = "brandes/terms";
    
    public void setBrandsSearchUrl(String brandsSearchUrl)
    {
        this.brandsSearchUrl = brandsSearchUrl;
    }
    
    /**
     * 根据类目搜索品牌
     * @param filter 搜索对象
     * @param sorts  排序对象
     * @param pagination 分页对象
     * @return {@link PaginateResult}
     * @throws BusinessException
     */
    public PaginateResult<Map<String, Object>> searchBrandesByDealMain(SolrFilter filter, Map<String, String> sorts, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        Map<String, String> params = Maps.newHashMap();
        if (StringUtils.isNoneBlank(filter.getMainId()))
        {
            String[] mainIds = filter.getMainId().split(",");
            if (mainIds.length == 1)
            {
                params.put("mainId", mainIds[0]);
            }
            else
            {
                StringBuffer q = new StringBuffer("( ");
                for (String dealMain : mainIds)
                {
                    q.append(dealMain).append(" ");
                }
                q.append(")");
                params.put("mainId", q.toString());
            }
        }
        if (StringUtils.isNoneBlank(filter.getNotIn()))
        {
            String[] brandsIds = filter.getNotIn().split(",");
            StringBuffer q = new StringBuffer("(");
            int i = 0, length = brandsIds.length - 1;
            for (String dealMain : brandsIds)
            {
                q.append(dealMain);
                if (i < length)
                {
                    q.append(" OR ");
                }
                i++;
            }
            q.append(")");
            params.put("-refrenceId", q.toString());
        }
        // 添加排序字段
        if (null == sorts)
        {
            sorts = Maps.newHashMap();
            sorts.put("createTime", "desc");
        }
        nameValuePairs.add(genQueryParamsValue(params));
        nameValuePairs.add(genSortParamsValue(sorts));
        nameValuePairs.add(new BasicNameValuePair("defType", new String("edismax")));
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(brandsSearchUrl).append("?").append(paramStr).toString();
        return sendUrl(uriStr, pagination);
    }
    
    /**
     * 品牌信息solr搜素接口
     *
     * @param filter 搜索对象
     * @param facets 分组对象
     * @param sorts  排序对象
     * @param pagination 分页对象
     * @throws com.zttx.sdk.exception.BusinessException
     * @return  {@link PaginateResult}
     */
    public SolrModel searchAllBrandesList(SolrFilter filter, List<NameValuePair> facets, Map<String, String> sorts, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        Map<String, String> params = Maps.newHashMap();
        if (StringUtils.isNoneBlank(filter.getMainId()))
        {
            String[] mainIds = filter.getMainId().split(",");
            if (mainIds.length == 1)
            {
                params.put("dealId", mainIds[0]);
            }
            else
            {
                StringBuffer q = new StringBuffer("( ");
                for (String dealMain : mainIds)
                {
                    q.append(dealMain).append(" ");
                }
                q.append(")");
                params.put("dealId", q.toString());
            }
        }
        if (StringUtils.isNoneBlank(filter.getBrandsId()))
        {
            String[] brandsIds = filter.getBrandsId().split(",");
            if (brandsIds.length == 1)
            {
                params.put("refrenceId", brandsIds[0]);
            }
            else
            {
                StringBuffer q = new StringBuffer("( ");
                for (String dealMain : brandsIds)
                {
                    q.append(dealMain).append(" OR ");
                }
                q.append(")");
                params.put("refrenceId", q.toString());
            }
        }
        if (StringUtils.isNotBlank(filter.getQ()))
        {
            nameValuePairs.add(new BasicNameValuePair("q", "dealName:*"
                    + filter.getQ() + " brandName:*"
                    + filter.getQ() + " comName:*"
                    + filter.getQ() + " comAddress:*"
                    + filter.getQ()));
        }
        else
        {
            nameValuePairs.add(new BasicNameValuePair("q", "*:*"));
        }
        // 添加分组查询
        if (null != facets && facets.size() > 0)
        {
            for (NameValuePair facet : facets)
            {
                nameValuePairs.add(facet);
            }
        }
        // 添加排序字段
        /*
         * if (null == sorts)
         * {
         * sorts = Maps.newHashMap();
         * sorts.put("brandsWeight","desc");
         * sorts.put("saleNum","desc");
         * }
         */
        nameValuePairs.add(genFilterQueryParamsValue(params));
        nameValuePairs.add(buildBrandWeightSortParams(sorts));
        nameValuePairs.add(new BasicNameValuePair("defType", new String("edismax")));
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(brandsSearchUrl).append("?").append(paramStr).toString();
        SolrResponse response = sendUrl(uriStr);
        PaginateResult<Map<String, Object>> result = new PaginateResult();
        result.setPage(getPagination(response, pagination));
        result.setList(response.getResponse().getDocs());
        SolrModel model = new SolrModel();
        model.setResult(result);
        return model;
    }
    
    /**
     * 构造品牌搜索权重分值排序参数对象
     * 手动干预占70%,其它占30%;
     *
     * @return
     */
    private BasicNameValuePair buildBrandWeightSortParams(Map<String, String> sorts)
    {
        BasicNameValuePair basicNameValuePair = genSortParamsValue(sorts);
        StringBuffer weightFormulaSB = new StringBuffer("sum(product(sum(");
        // 加盟数权重
        weightFormulaSB.append("joinNum");
        // 浏览量权重
        weightFormulaSB.append(",min(div(viewNum,1000),5)");
        // 收藏数权重
        weightFormulaSB.append(",min(div(favNum,1000),5)");
        weightFormulaSB.append("),0.3)");
        // 品牌权重
        weightFormulaSB.append(",product(brandsWeight,0.7)");
        weightFormulaSB.append(") desc");
        if (null == basicNameValuePair)
        {
            basicNameValuePair = new BasicNameValuePair("sort", weightFormulaSB.toString());
        }
        else
        {
            String sort = weightFormulaSB.insert(0, ',').insert(0, basicNameValuePair.getValue()).toString();
            basicNameValuePair = new BasicNameValuePair("sort", sort);
        }
        LoggerUtils.logDebug(logger, basicNameValuePair.toString());
        return basicNameValuePair;
    }
    
    /**
     * 自动补全
     *
     * @param filter
     * @return {@link PaginateResult}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    public List<FacetField> autoCompletion(SolrFilter filter) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        if (StringUtils.isBlank(filter.getQ())) return null;
        if (StringUtils.isNotBlank(filter.getMainId()))
        {
            nameValuePairs.add(new BasicNameValuePair("q", "mainId:" + filter.getMainId()));
        }
        else
        {
            nameValuePairs.add(new BasicNameValuePair("q", "*:*"));
        }
        nameValuePairs.add(new BasicNameValuePair("terms.fl", "text"));
        nameValuePairs.add(new BasicNameValuePair("terms.prefix", filterKeyWord(filter.getQ())));
        nameValuePairs.add(new BasicNameValuePair("terms.regex", filterKeyWord(filter.getQ()) + "+. *"));
        nameValuePairs.add(new BasicNameValuePair("terms.regex.flag", "case_insensitive"));
        nameValuePairs.add(new BasicNameValuePair("terms.mincount", "1"));
        nameValuePairs.add(new BasicNameValuePair("terms.limit", "12"));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(brandsTermUrl).append("?").append(paramStr).toString();
        SolrResponse response = sendUrl(uriStr);
        return FacetUtils.getFacetFields(response.getTerms().get("text").toString());
    }
}
