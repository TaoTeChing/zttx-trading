package com.zttx.web.search.module;

import java.util.Map;

/**
 * <p>File：FacetResponse.java </p>
 * <p>Title: FacetResponse </p>
 * <p>Description: FacetResponse </p>
 * <p>Copyright: Copyright (c) 2014 08/24/2015 11:18</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class FacetCounts
{
    private Map<String, Object> facet_queries;
    
    private Map<String, Object> facet_fields;
    
    private Map<String, Object> facet_dates;
    
    private Map<String, Object> facet_ranges;
    
    private Map<String, Object> facet_intervals;
    
    private Map<String, Object> facet_heatmaps;
    
    public Map<String, Object> getFacet_queries()
    {
        return facet_queries;
    }
    
    public void setFacet_queries(Map<String, Object> facet_queries)
    {
        this.facet_queries = facet_queries;
    }
    
    public Map<String, Object> getFacet_fields()
    {
        return facet_fields;
    }
    
    public void setFacet_fields(Map<String, Object> facet_fields)
    {
        this.facet_fields = facet_fields;
    }
    
    public Map<String, Object> getFacet_dates()
    {
        return facet_dates;
    }
    
    public void setFacet_dates(Map<String, Object> facet_dates)
    {
        this.facet_dates = facet_dates;
    }
    
    public Map<String, Object> getFacet_ranges()
    {
        return facet_ranges;
    }
    
    public void setFacet_ranges(Map<String, Object> facet_ranges)
    {
        this.facet_ranges = facet_ranges;
    }
    
    public Map<String, Object> getFacet_intervals()
    {
        return facet_intervals;
    }
    
    public void setFacet_intervals(Map<String, Object> facet_intervals)
    {
        this.facet_intervals = facet_intervals;
    }
    
    public Map<String, Object> getFacet_heatmaps()
    {
        return facet_heatmaps;
    }
    
    public void setFacet_heatmaps(Map<String, Object> facet_heatmaps)
    {
        this.facet_heatmaps = facet_heatmaps;
    }
}
