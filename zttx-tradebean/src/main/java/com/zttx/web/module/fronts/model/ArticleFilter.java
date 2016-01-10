package com.zttx.web.module.fronts.model;

import java.io.Serializable;

/**
 * <p>File：ArticleFilter.java </p>
 * <p>Title: ArticleFilter </p>
 * <p>Description: ArticleFilter </p>
 * <p>Copyright: Copyright (c) 2014 08/18/2015 14:26</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class ArticleFilter implements Serializable
{
    private static final long serialVersionUID = -3170573629936469737L;
    
    /**
     * 键入的内容
     */
    private String            q;
    
    /**
     * 分类ID
     */
    private String            cateId;
    
    // 是否是搜索 默认不是搜索
    private Short             isSearch         = 0;
    
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

    public Short getIsSearch()
    {
        return isSearch;
    }

    public void setIsSearch(Short isSearch)
    {
        this.isSearch = isSearch;
    }
}
