package com.zttx.web.search.module;

import com.zttx.sdk.bean.PaginateResult;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>File：CustomModel.java </p>
 * <p>Title: CustomModel </p>
 * <p>Description: CustomModel </p>
 * <p>Copyright: Copyright (c) 2014 08/24/2015 17:59</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class SolrModel implements Serializable
{
    private static final long                   serialVersionUID = -7952095155395538124L;
    
    /**
     * 分页对象
     * <p>
     *     用于装查询结果集
     *     如Response对象中的docs
     * </p>
     */
    private PaginateResult<Map<String, Object>> result;
    
    /**
     * 统计对象
     * <p>
     *     用于封装统计结果集对象
     * </p>
     */
    private FacetCounts                         facet_counts;
    
    public PaginateResult<Map<String, Object>> getResult()
    {
        return result;
    }
    
    public void setResult(PaginateResult<Map<String, Object>> result)
    {
        this.result = result;
    }
    
    public FacetCounts getFacet_counts()
    {
        return facet_counts;
    }
    
    public void setFacet_counts(FacetCounts facet_counts)
    {
        this.facet_counts = facet_counts;
    }
}
