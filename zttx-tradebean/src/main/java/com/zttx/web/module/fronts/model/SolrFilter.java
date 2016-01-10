package com.zttx.web.module.fronts.model;

import java.io.Serializable;

/**
 * <p>File：SolrFilter.java </p>
 * <p>Title: SolrFilter </p>
 * <p>Description: SolrFilter </p>
 * <p>Copyright: Copyright (c) 2014 08/18/2015 14:36</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
public class SolrFilter implements Serializable
{
    private static final long serialVersionUID = 3688269905741064369L;
    
    /**
     * 键入的内容
     */
    private String            q;
    
    /**
     * 收索类型（产品、品牌）
     */
    private String            st;
    
    /**
    * 经营品牌上级类目编号
    */
    private String            mainId;
    
    /**
     * 经营品牌类目编号
     */
    private String            dealId;
    
    /**
     * 经营品牌上级类目名称
     */
    private String            mainName;
    
    /**
     * SKU颜色
     */
    private String            skuColor;
    
    /**
     * SKU尺码
     */
    private String            skuSize;
    
    /**
     * 其它属性
     */
    private String            otherAttr;
    
    /**
     * 经营品牌类目名称
     */
    private String            dealName;
    
    /**
     * 排序字段
     */
    private String            sorts;
    
    /**
     *  排序
     */
    private String            orderBy;
    
    /**
     * 品牌ID
     */
    private String            brandsId;
    
    /**
     * 待排除的
     */
    private String            notIn;
    
    /**
     * 是否推荐
     */
    private Boolean           recom;
    
    /**
     * 是否支持拿样
     */
    private Boolean           samples;
    
    /**
     * 是否有授权
     */
    private Boolean           authorize;
    
    /**
     * 是否有授信
     */
    private Boolean           credit;
    /**
     * 是否返点
     */
    private Boolean           point;
    
    /**
     * 请求地址
     */
    private String            url;
    
    /**
     * 是否全部搜索 
     */
    private Boolean           searchAll        = Boolean.FALSE;
    
    public SolrFilter()
    {
    }
    
    public SolrFilter(String mainId)
    {
        this.mainId = mainId;
    }
    
    public SolrFilter(String mainId, String dealId)
    {
        this.mainId = mainId;
        this.dealId = dealId;
    }
    
    
    public Boolean getPoint()
    {
        return point;
    }

    public void setPoint(Boolean point)
    {
        this.point = point;
    }

    public String getQ()
    {
        return q;
    }
    
    public void setQ(String q)
    {
        this.q = q;
    }
    
    public String getSt()
    {
        return st;
    }
    
    public void setSt(String st)
    {
        this.st = st;
    }
    
    public String getMainId()
    {
        return mainId;
    }
    
    public void setMainId(String mainId)
    {
        this.mainId = mainId;
    }
    
    public String getDealId()
    {
        return dealId;
    }
    
    public void setDealId(String dealId)
    {
        this.dealId = dealId;
    }
    
    public String getMainName()
    {
        return mainName;
    }
    
    public void setMainName(String mainName)
    {
        this.mainName = mainName;
    }
    
    public String getSkuColor()
    {
        return skuColor;
    }
    
    public void setSkuColor(String skuColor)
    {
        this.skuColor = skuColor;
    }
    
    public String getSkuSize()
    {
        return skuSize;
    }
    
    public void setSkuSize(String skuSize)
    {
        this.skuSize = skuSize;
    }
    
    public String getDealName()
    {
        return dealName;
    }
    
    public void setDealName(String dealName)
    {
        this.dealName = dealName;
    }
    
    public String getSorts()
    {
        return sorts;
    }
    
    public void setSorts(String sorts)
    {
        this.sorts = sorts;
    }
    
    public String getOrderBy()
    {
        return orderBy;
    }
    
    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }
    
    public String getBrandsId()
    {
        return brandsId;
    }
    
    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }
    
    public Boolean getRecom()
    {
        return recom;
    }
    
    public void setRecom(Boolean recom)
    {
        this.recom = recom;
    }
    
    public Boolean getSamples()
    {
        return samples;
    }
    
    public void setSamples(Boolean samples)
    {
        this.samples = samples;
    }
    
    public Boolean getAuthorize()
    {
        return authorize;
    }
    
    public void setAuthorize(Boolean authorize)
    {
        this.authorize = authorize;
    }
    
    public Boolean getCredit()
    {
        return credit;
    }
    
    public void setCredit(Boolean credit)
    {
        this.credit = credit;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public Boolean getSearchAll()
    {
        return searchAll;
    }
    
    public void setSearchAll(Boolean searchAll)
    {
        this.searchAll = searchAll;
    }
    
    public String getOtherAttr()
    {
        return otherAttr;
    }
    
    public void setOtherAttr(String otherAttr)
    {
        this.otherAttr = otherAttr;
    }
    
    public String getNotIn()
    {
        return notIn;
    }
    
    public void setNotIn(String notIn)
    {
        this.notIn = notIn;
    }
}
