package com.zttx.web.module.fronts.model;

import java.io.Serializable;

/**
 * <p>File：HelpFilter.java </p>
 * <p>Title: HelpFilter </p>
 * <p>Description: HelpFilter </p>
 * <p>Copyright: Copyright (c) 2014 08/18/2015 14:08</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class HelpFilter implements Serializable
{
    private static final long serialVersionUID = -1039051682704924722L;
    
    /**
     * 键入的内容
     */
    private String            q;
    
    /**
     * 分类ID
     */
    private String            cateId;
    
    public String getQ()
    {
        return q;
    }
    
    public void setQ(String q)
    {
        this.q = q;
    }
    
    public String getCateId()
    {
        return cateId;
    }
    
    public void setCateId(String cateId)
    {
        this.cateId = cateId;
    }
}
