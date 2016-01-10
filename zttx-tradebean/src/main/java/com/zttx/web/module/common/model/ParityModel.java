/*
 * @(#)ParityModel.java 2015-9-21 上午11:06:24
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

import java.util.List;

import com.zttx.web.module.common.entity.ProductParity;

/**
 * <p>File：ParityModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-9-21 上午11:06:24</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public class ParityModel
{
    public static final String REF="门店参考价";
    public static final String TM="天猫旗舰店";
    public static final String VPH="唯品会";
    public static final String ALBB="阿里巴巴";
    public static final String QT="其他";
    
    private String refName;  // 引用名称
    
    private Double refPrice; // 引用价格
    
    private String tmName;   // 天猫名称
    
    private Double tmPrice;  // 天猫价格
    
    private String tmLink;   // 天猫连接
    
    private String vphName;  // 唯品会名称
    
    private Double vphPrice;  // 唯品会价格
    
    private String vphLink;   // 唯品会连接
    
    private String albbName;  // 阿里巴巴名称
    
    private Double albbPrice; // 阿里巴巴价格
    
    private String albbLink;  // 阿里巴巴链接
    
    private String qtName;    // 其他名称
    
    private Double qtPrice;   // 其它价格
    
    private String qtLink;    // 其他连接
    
    private int modify;//是否修改
    
    private List<ProductParity> parity;
    
    
    
    public List<ProductParity> getParity()
    {
        return parity;
    }
    public void setParity(List<ProductParity> parity)
    {
        this.parity = parity;
    }
    public int getModify()
    {
        return modify;
    }
    public void setModify(int modify)
    {
        this.modify = modify;
    }
    public Object[][] buildList(){
        Object[][] result=new Object[5][3];
        result[0][0]=refName;
        result[0][1]=refPrice;
        result[0][2]=null;
        result[1][0]=tmName;
        result[1][1]=tmPrice;
        result[1][2]=tmLink;
        result[2][0]=vphName;
        result[2][1]=vphPrice;
        result[2][2]=vphLink;
        result[3][0]=albbName;
        result[3][1]=albbPrice;
        result[3][2]=albbLink;
        result[4][0]=qtName;
        result[4][1]=qtPrice;
        result[4][2]=qtLink;
        return result;
        
    }
    public String getTmName()
    {
        return tmName;
    }
    
    public void setTmName(String tmName)
    {
        this.tmName = tmName;
    }
    
    public Double getTmPrice()
    {
        return tmPrice;
    }
    
    public void setTmPrice(Double tmPrice)
    {
        this.tmPrice = tmPrice;
    }
    
    public String getTmLink()
    {
        return tmLink;
    }
    
    public void setTmLink(String tmLink)
    {
        this.tmLink = tmLink;
    }
    
    public String getVphName()
    {
        return vphName;
    }
    
    public void setVphName(String vphName)
    {
        this.vphName = vphName;
    }
    
    public Double getVphPrice()
    {
        return vphPrice;
    }
    
    public void setVphPrice(Double vphPrice)
    {
        this.vphPrice = vphPrice;
    }
    
    
    public String getVphLink()
    {
        return vphLink;
    }
    public void setVphLink(String vphLink)
    {
        this.vphLink = vphLink;
    }
    public String getAlbbName()
    {
        return albbName;
    }
    
    public void setAlbbName(String albbName)
    {
        this.albbName = albbName;
    }
    
    public Double getAlbbPrice()
    {
        return albbPrice;
    }
    
    public void setAlbbPrice(Double albbPrice)
    {
        this.albbPrice = albbPrice;
    }
    
    public String getAlbbLink()
    {
        return albbLink;
    }
    
    public void setAlbbLink(String albbLink)
    {
        this.albbLink = albbLink;
    }
    
    public String getQtName()
    {
        return qtName;
    }
    
    public void setQtName(String qtName)
    {
        this.qtName = qtName;
    }
    
    public Double getQtPrice()
    {
        return qtPrice;
    }
    
    public void setQtPrice(Double qtPrice)
    {
        this.qtPrice = qtPrice;
    }
   
    
    public String getQtLink()
    {
        return qtLink;
    }
    public void setQtLink(String qtLink)
    {
        this.qtLink = qtLink;
    }
    public String getRefName()
    {
        return refName;
    }
    
    public void setRefName(String refName)
    {
        this.refName = refName;
    }
    
    public Double getRefPrice()
    {
        return refPrice;
    }
    
    public void setRefPrice(Double refPrice)
    {
        this.refPrice = refPrice;
    }
}
