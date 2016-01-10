package com.zttx.web.module.common.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 商品属性值
 * <p>File：ProductAttrValue.java </p>
 * <p>Title: ProductAttrValue </p>
 * <p>Description:ProductAttrValue </p>
 * <p>Copyright: Copyright (c) 八月, 2015</p>
 * <p>Company: 8637.com</p>
 * <p>CreateDate:15/8/27</>
 *
 * @author Playguy
 * @version 1.0
 */
public class ProductAttrValue extends GenericEntity
{
    private static final long serialVersionUID = -5545647212414772167L;
    
    /** 属性ID **/
    private String            attributeId;
    
    /** 属性项ID **/
    private String            attributeItemId;
    
    /** 属性名称 **/
    private String            extAttributeValue;
    
    /** 是否为SKU属性 **/
    private Boolean           isSkuAttr;
    
    public String getAttributeId()
    {
        return attributeId;
    }
    
    public void setAttributeId(String attributeId)
    {
        this.attributeId = attributeId;
    }
    
    public String getAttributeItemId()
    {
        return attributeItemId;
    }
    
    public void setAttributeItemId(String attributeItemId)
    {
        this.attributeItemId = attributeItemId;
    }
    
    public String getExtAttributeValue()
    {
        return extAttributeValue;
    }
    
    public void setExtAttributeValue(String extAttributeValue)
    {
        this.extAttributeValue = extAttributeValue;
    }
    
    public Boolean getIsSkuAttr()
    {
        return isSkuAttr;
    }
    
    public void setIsSkuAttr(Boolean isSkuAttr)
    {
        this.isSkuAttr = isSkuAttr;
    }
}
