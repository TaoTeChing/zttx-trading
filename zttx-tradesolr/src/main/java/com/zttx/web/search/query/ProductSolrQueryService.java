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
import com.zttx.web.module.dealer.model.ProductFilter;
import com.zttx.web.module.fronts.model.SolrFilter;
import com.zttx.web.search.module.FacetField;
import com.zttx.web.search.module.SolrModel;
import com.zttx.web.search.module.SolrResponse;
import com.zttx.web.search.solrj.SolrjHandler;
import com.zttx.web.search.utils.FacetUtils;

/**
 * <p>File：ProductSolrQueryService.java </p>
 * <p>Title: 产品的索引查询服务 </p>
 * <p>Description: ProductSolrQueryService </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:46</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class ProductSolrQueryService extends BaseSolrQueryService
{
    Logger                          logger           = LoggerFactory.getLogger(ProductSolrQueryService.class);
    
    private String                  productSearchUrl = "product/select";
    
    private String                  productTermsUrl  = "product/terms";
    
    private static final Pagination pagin            = new Pagination(0, 15);
    
    public void setProductSearchUrl(String productSearchUrl)
    {
        this.productSearchUrl = productSearchUrl;
    }
    
    /**
     * 根据品牌ID搜索产品
     * @param filter
     * @param sorts
     * @param pagination
     * @return
     * @throws BusinessException
     */
    public List<Map<String, Object>> searchProduct(SolrFilter filter, Map<String, String> sorts, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        if (null == pagination) pagination = pagin;
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
        if (StringUtils.isNoneBlank(filter.getBrandsId()))
        {
            String[] brandsIds = filter.getBrandsId().split(",");
            if (brandsIds.length == 1)
            {
                params.put("brandsId", brandsIds[0]);
            }
            else
            {
                StringBuffer q = new StringBuffer("( ");
                for (String dealMain : brandsIds)
                {
                    q.append(dealMain).append(" OR ");
                }
                q.append(")");
                params.put("brandsId", q.toString());
            }
        }
        // 添加排序字段
        if (null == sorts)
        {
            sorts = Maps.newHashMap();
            sorts.put("topTime", "desc");
            sorts.put("productGroom", "desc");
            sorts.put("monthSaleNum", "desc");
        }
        nameValuePairs.add(genQueryParamsValue(params));
        nameValuePairs.add(genSortParamsValue(sorts));
        nameValuePairs.add(new BasicNameValuePair("defType", new String("edismax")));
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(productSearchUrl).append("?").append(paramStr).toString();
        return getLists(uriStr);
    }
    
    /**
     * 产品信息solr搜素接口
     *
     * @param filter
     * @param sorts
     * @param facets
     * @param pagination
     * @return {@link PaginateResult}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    public SolrModel searchAllProductList(SolrFilter filter, Map<String, String> sorts, List<NameValuePair> facets, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        if (null == pagination) pagination = pagin;
        Map<String, String> fqs = Maps.newHashMap();
        if (StringUtils.isNoneBlank(filter.getMainId()))
        {
            String[] mainIds = filter.getMainId().split(",");
            if (mainIds.length == 1)
            {
                fqs.put("mainId", mainIds[0]);
            }
            else
            {
                StringBuffer q = new StringBuffer("( ");
                for (String dealMain : mainIds)
                {
                    q.append(dealMain).append(" ");
                }
                q.append(")");
                fqs.put("mainId", q.toString());
            }
        }
        if (StringUtils.isNoneBlank(filter.getBrandsId()))
        {
            String[] brandsIds = filter.getBrandsId().split(",");
            if (brandsIds.length == 1)
            {
                fqs.put("brandsId", brandsIds[0]);
            }
            else
            {
                StringBuffer q = new StringBuffer("( ");
                for (String dealMain : brandsIds)
                {
                    q.append(dealMain).append(" ");
                }
                q.append(")");
                fqs.put("brandsId", q.toString());
            }
        }
        if (StringUtils.isNoneBlank(filter.getDealId()))
        {
            fqs.put("dealId", String.valueOf(filter.getDealId()));
        }
        if (null != filter.getRecom() && filter.getRecom())
        {
            fqs.put("productGroom", String.valueOf(filter.getRecom()));
        }
        if (null != filter.getCredit() && filter.getCredit())
        {
            fqs.put("isCredit", String.valueOf(filter.getCredit()));
        }
        if(null!=filter.getPoint()&&filter.getPoint()){
            fqs.put("isPoint", String.valueOf(filter.getPoint()));
        }
        if (null != filter.getSamples() && filter.getSamples())
        {
            fqs.put("isSample", String.valueOf(filter.getSamples()));
        }
        if (StringUtils.isNotBlank(filter.getQ()))
        {
            nameValuePairs.add(new BasicNameValuePair("q", "mainName:*"
                    + filter.getQ() + " dealName:*"
                    + filter.getQ() + " productTitle:*"
                    + filter.getQ() + " productTitleCopy:*"
                    + filter.getQ() + " productTitlePinyin:*"
                    + filter.getQ() + " brandsName:*"
                    + filter.getQ() + " productNo:*"
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

        nameValuePairs.add(genFilterQueryParamsValue(fqs));
        nameValuePairs.add(buildProductWeightSortParams(sorts));
        nameValuePairs.add(new BasicNameValuePair("defType", new String("edismax")));
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        nameValuePairs.add(new BasicNameValuePair("hl", "true"));
        nameValuePairs.add(new BasicNameValuePair("hl.fl", "mainName,dealName,brandsName,productTitle,productNo"));
        nameValuePairs.add(new BasicNameValuePair("hl.simple.pre", "<font color='red'>"));
        nameValuePairs.add(new BasicNameValuePair("hl.simple.post", "</font>"));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(productSearchUrl).append("?").append(paramStr).toString();
        SolrResponse response = sendUrl(uriStr);
        highlightingResponse(response);
        PaginateResult<Map<String, Object>> result = new PaginateResult();
        result.setPage(getPagination(response, pagination));
        result.setList(response.getResponse().getDocs());
        SolrModel model = new SolrModel();
        model.setResult(result);
        model.setFacet_counts(response.getFacet_counts());
        return model;
    }
    
    /**
     * 构造产品搜索权重分值排序参数对象
     * 手动干预占70%,其它占30%;
     * 分值取值规则如下：
     产品主值=销量/1000+收藏量/100+推荐+点击量/1000+新品因子；
     销量：产品在各有效订单内的销售数量/1000，最高为3；
     收藏量：产品的收藏数量/100，最高为1；
     点击量：当前产品点击量/10000，最高为2；
     推荐：产品为推荐产品，则+4；
     新品因子取值，以发布时间为准
     * @return
     */
    private BasicNameValuePair buildProductWeightSortParams(Map<String, String> sorts)
    {
        BasicNameValuePair basicNameValuePair = genSortParamsValue(sorts);
        StringBuffer weightFormulaSB = new StringBuffer("sum(product(sum(");
        // 销量权重
        weightFormulaSB.append("min(div(if(monthSaleNum,monthSaleNum,0),1000),3)");
        // 收藏量权重
        weightFormulaSB.append(",min(div(collectNum,100),1)");
        // 点击率权重
        weightFormulaSB.append(",min(div(viewNum,10000),2)");
        // 推荐权重
        weightFormulaSB.append(",if(productGroom,4,0)");
        weightFormulaSB.append("),0.3)");
        // 品牌权重
        weightFormulaSB.append(",sum(product(sum(brandsWeight");
        // 产品权重
        weightFormulaSB.append(",productWeight),0.7))");
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
     * 热门搜索
     *
     * @param filter
     * @return {@link List}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    public List<FacetField> autoCompletion(SolrFilter filter) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        if (StringUtils.isBlank(filter.getQ())) return null;
        if (StringUtils.isNotBlank(filter.getMainId()) && StringUtils.isNotBlank(filter.getDealId()))
        {
            nameValuePairs.add(new BasicNameValuePair("q", "mainId:" + filter.getMainId() + " AND dealId:" + filter.getDealId()));
        }
        else if (StringUtils.isNotBlank(filter.getMainId()) || StringUtils.isNotBlank(filter.getDealId()))
        {
            if (StringUtils.isNotBlank(filter.getMainId()))
            {
                nameValuePairs.add(new BasicNameValuePair("q", "mainId:" + filter.getMainId()));
            }
            if (StringUtils.isNotBlank(filter.getDealId()))
            {
                nameValuePairs.add(new BasicNameValuePair("q", "dealId:" + filter.getDealId()));
            }
        }
        else
        {
            nameValuePairs.add(new BasicNameValuePair("q", "*:*"));
        }
        nameValuePairs.add(new BasicNameValuePair("terms.fl", "text"));
        nameValuePairs.add(new BasicNameValuePair("terms.prefix", filterKeyWord(filter.getQ())));
        nameValuePairs.add(new BasicNameValuePair("terms.limit", "10"));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(productTermsUrl).append("?").append(paramStr).toString();
        SolrResponse response = sendUrl(uriStr);
        return FacetUtils.getFacetFields(response.getTerms().get("text").toString());
    }
    
    /**
     * Solr Facet查询实现自动补全
     * @param filter
     * @return
     * @throws BusinessException
     * @author 李星
     */
    public List<FacetField> autoCompletionByFacet(SolrFilter filter) throws BusinessException
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
        nameValuePairs.add(new BasicNameValuePair("facet", "true"));
        nameValuePairs.add(new BasicNameValuePair("facet.field", "text"));
        nameValuePairs.add(new BasicNameValuePair("facet.prefix", filterKeyWord(filter.getQ())));
        nameValuePairs.add(new BasicNameValuePair("facet.limit", "10"));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(productSearchUrl).append("?").append(paramStr).toString();
        SolrResponse response = sendUrl(uriStr);
        return FacetUtils.getFacetFields(response.getFacet_counts().getFacet_fields().get("text").toString());
    }
    
    /**
     * 品牌 产品展示  根据店内分类，价格区间商品及货号关键字搜索商品
     * @param filter
     * @param pagination
     * @return
     * @throws BusinessException
     */
    public SolrModel searchBrandProductList(ProductFilter filter, Pagination pagination) throws BusinessException
    {
        List<NameValuePair> nameValuePairs = Lists.newArrayList(jsonFormat);
        Map<String, String> params = Maps.newHashMap();
        Map<String, String> sorts = Maps.newHashMap();
        // 过滤条件
        if (StringUtils.isNotBlank(filter.getBrandId())) params.put("brandId", filter.getBrandId());
        if (StringUtils.isNotBlank(filter.getBrandsId())) params.put("brandsId", filter.getBrandsId());
        if (StringUtils.isNoneBlank(filter.getCataId())) params.put("categoryId", String.valueOf(filter.getCataId()));
        if (StringUtils.isNotBlank(filter.getKeyWord()))
        {
            params.put("text", filterKeyWord(filter.getKeyWord()));
            nameValuePairs.add(new BasicNameValuePair("mm", SolrjHandler.SOLR_QUERY_MM));
        }
        if (null != filter.getMin() && null != filter.getMax()) params.put("productPrice", "[" + filter.getMin() + " TO " + filter.getMax() + "]");
        else
        {
            if (null != filter.getMin()) params.put("productPrice", "[" + filter.getMin() + " TO *]");
            if (null != filter.getMax()) params.put("productPrice", "[* TO " + filter.getMax() + "]");
        }
        // 排序处理
        if (null != filter.getSort() && null != filter.getOrder())
        {
            String sort = "desc";
            if (2 == filter.getOrder()) sort = "asc";
            if (2 == filter.getSort()) sorts.put("updateTime", sort);
            else if (3 == filter.getSort()) sorts.put("productPrice", sort);
            else sorts.put("saleNum", "desc");
        }
        /*else
        {// 默认排序
            sorts.put("topTime", "desc");
            sorts.put("productGroom", "desc");
            sorts.put("monthSaleNum", "desc");
        }*/
        nameValuePairs.add(genQueryParamsValue(params));
        nameValuePairs.add(buildBrandProductWeightSortParams(sorts));
        nameValuePairs.add(new BasicNameValuePair("defType", new String("edismax")));
        nameValuePairs.add(new BasicNameValuePair("start", String.valueOf(pagination.getPageSize() * (pagination.getCurrentPage() - 1))));
        nameValuePairs.add(new BasicNameValuePair("rows", String.valueOf(pagination.getPageSize())));
        String paramStr = URLEncodedUtils.format(nameValuePairs, Charset.forName(CharsetConst.CHARSET_UT));
        String uriStr = new StringBuffer(serverUrl).append(productSearchUrl).append("?").append(paramStr).toString();
        SolrResponse response = sendUrl(uriStr);
        PaginateResult<Map<String, Object>> result = new PaginateResult();
        result.setPage(getPagination(response, pagination));
        result.setList(response.getResponse().getDocs());
        SolrModel model = new SolrModel();
        model.setResult(result);
        model.setFacet_counts(response.getFacet_counts());
        return model;
    }


    /**
     * 构造品牌店内产品搜索权重分值排序参数对象
     * 手动干预占70%,其它占30%;
     * 分值取值规则如下：
     * 产品主值=销量/1000+推荐+置顶；
     * 销量：产品在各有效订单内的销售数量/1000，最高为3；
     * 推荐：产品为推荐产品，则+4；
     * 置顶 +4
     *
     * @return
     */
    private BasicNameValuePair buildBrandProductWeightSortParams(Map<String, String> sorts) {
        BasicNameValuePair basicNameValuePair = genSortParamsValue(sorts);
        StringBuffer weightFormulaSB = new StringBuffer("sum(product(sum(");
        // 销量权重
        weightFormulaSB.append("min(div(if(monthSaleNum,monthSaleNum,0),1000),3)");
        // 推荐权重
        weightFormulaSB.append(",if(productGroom,4,0)");
        // 置顶权重
        weightFormulaSB.append(",if(not(ms(topTime,1970-01-01T00:00:00Z)),0,4)");
        weightFormulaSB.append("),0.3)");
        // 产品权重
        weightFormulaSB.append(",product(productWeight,0.7)");
        weightFormulaSB.append(") desc");
        if (null == basicNameValuePair) {
            basicNameValuePair = new BasicNameValuePair("sort", weightFormulaSB.toString());
        } else {
            String sort = weightFormulaSB.insert(0, ',').insert(0, basicNameValuePair.getValue()).toString();
            basicNameValuePair = new BasicNameValuePair("sort", sort);
        }
        LoggerUtils.logDebug(logger, basicNameValuePair.toString());
        return basicNameValuePair;
    }
}
