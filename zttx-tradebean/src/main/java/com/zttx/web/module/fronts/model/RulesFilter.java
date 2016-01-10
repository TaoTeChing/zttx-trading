package com.zttx.web.module.fronts.model;

import java.io.Serializable;

/**
 * <p>File：RulesFilter.java </p>
 * <p>Title: RulesFilter </p>
 * <p>Description: RulesFilter </p>
 * <p>Copyright: Copyright (c) 2014 08/18/2015 14:20</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class RulesFilter implements Serializable
{
    private static final long serialVersionUID = -8010782980172306490L;
    
    /**
     * 键入的内容
     */
    private String            q;
    
    /**
     * 规则类型
     */
    private Integer           rulesType;
    
    /**
     * 分类ID
     */
    private String            cateId;
    
    /**
     * 分类类型
     */
    private String            cateType;
    
    /**
     * 分类级别
     */
    private Short             cateLevel;
    
    public String getQ()
    {
        return q;
    }
    
    public void setQ(String q)
    {
        this.q = q;
    }
    
    public Integer getRulesType()
    {
        return rulesType;
    }
    
    public void setRulesType(Integer rulesType)
    {
        this.rulesType = rulesType;
    }
    
    public String getCateId()
    {
        return cateId;
    }
    
    public void setCateId(String cateId)
    {
        this.cateId = cateId;
    }
    
    public String getCateType()
    {
        return cateType;
    }
    
    public void setCateType(String cateType)
    {
        this.cateType = cateType;
    }
    
    public Short getCateLevel()
    {
        return cateLevel;
    }
    
    public void setCateLevel(Short cateLevel)
    {
        this.cateLevel = cateLevel;
    }
}
