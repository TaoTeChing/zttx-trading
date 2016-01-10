/*
 * @(#)SolrResponse 2014/5/7 12:59
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.search.module;

import java.util.List;
import java.util.Map;

/**
 * <p>File：SolrResponse.java </p>
 * <p>Title: Solr响应信息对象封装 </p>
 * <p>Description: SolrResponse </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class SolrResponse
{
    private Error               error;
    
    private Map<String, Object> terms;
    
    private Response            response;
    
    private FacetCounts         facet_counts;
    
    private ResponseHeader      responseHeader;
    
    // Highlight Info
    private Map<String, Map<String, List<String>>> highlighting;
    
    public Map<String, Map<String, List<String>>> getHighlighting()
    {
        return highlighting;
    }
    
    public void setHighlighting(Map<String, Map<String, List<String>>> highlighting)
    {
        this.highlighting = highlighting;
    }
    
    public Error getError()
    {
        return error;
    }
    
    public void setError(Error error)
    {
        this.error = error;
    }
    
    public Response getResponse()
    {
        return response;
    }
    
    public void setResponse(Response response)
    {
        this.response = response;
    }
    
    public FacetCounts getFacet_counts()
    {
        return facet_counts;
    }
    
    public void setFacet_counts(FacetCounts facet_counts)
    {
        this.facet_counts = facet_counts;
    }
    
    public Map<String, Object> getTerms()
    {
        return terms;
    }
    
    public void setTerms(Map<String, Object> terms)
    {
        this.terms = terms;
    }
    
    public ResponseHeader getResponseHeader()
    {
        return responseHeader;
    }
    
    public void setResponseHeader(ResponseHeader responseHeader)
    {
        this.responseHeader = responseHeader;
    }
}
