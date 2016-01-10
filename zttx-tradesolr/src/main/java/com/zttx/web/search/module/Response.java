/*
 * @(#)Response 2014/5/7 13:01
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.search.module;

import java.util.List;
import java.util.Map;

/**
 * <p>File：Response.java </p>
 * <p>Title: Solr响应对象封装 </p>
 * <p>Description: Response </p>
 * <p>Copyright: Copyright (c) 2014 08/12/2015 16:49</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class Response
{
    private Integer                   numFound;
    
    private Long                      start;
    
    private List<Map<String, Object>> docs;
    
    public Integer getNumFound()
    {
        return numFound;
    }
    
    public void setNumFound(Integer numFound)
    {
        this.numFound = numFound;
    }
    
    public Long getStart()
    {
        return start;
    }
    
    public void setStart(Long start)
    {
        this.start = start;
    }
    
    public List<Map<String, Object>> getDocs()
    {
        return docs;
    }
    
    public void setDocs(List<Map<String, Object>> docs)
    {
        this.docs = docs;
    }
}
