package com.zttx.web.search.module;

import java.io.Serializable;

/**
 * <p>File：FacetField.java </p>
 * <p>Title: FacetField </p>
 * <p>Description: FacetField </p>
 * <p>Copyright: Copyright (c) 2014 08/25/2015 09:26</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class FacetField implements Serializable
{
    private static final long serialVersionUID = -1208303922499870891L;
    
    private String            name             = null;
    
    private String            count            = "0";
    
    public FacetField(String name, String count)
    {
        this.name = name;
        this.count = count;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getCount()
    {
        return count;
    }
    
    public void setCount(String count)
    {
        this.count = count;
    }
}
