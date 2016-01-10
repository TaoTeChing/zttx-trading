/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 品牌经销网络 实体对象
 * <p>File：BrandNetwork.java</p>
 * <p>Title: BrandNetwork</p>
 * <p>Description:BrandNetwork</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class BrandNetwork extends GenericEntity
{
    private static final long serialVersionUID = 1L;

    /**品牌商编号*/
    private java.lang.String  brandId;

    /**品牌编号*/
    private java.lang.String  brandsId;

    /**经销商主帐号编号*/
    private java.lang.String  dealerId;

    /**经销商名称*/
    private java.lang.String  dealerName;

    /**经销商等级编号*/
    private java.lang.String  levelId;

    /**联系人*/
    private java.lang.String  userName;

    /**联系电话*/
    private java.lang.String  telphone;

    /**手机号码*/
    private java.lang.String  mobile;

    /**省名称*/
    private java.lang.String  provinceName;

    /**城市名称*/
    private java.lang.String  cityName;

    /**区名称*/
    private java.lang.String  areaName;

    /**所在区域*/
    private java.lang.Integer areaNo;

    /**所在地址*/
    private java.lang.String  address;

    /**是否在展厅显示（0：不显示，1：显示）*/
    private java.lang.Boolean showFlag;

    /**完整度*/
    private java.lang.Integer wholePercent;

    /**建档时间*/
    private java.lang.Long    createTime;

    /**修改时间*/
    private java.lang.Long    updateTime;

    private String            path;

    private String            domain;

    
    /**
     * 其他属性
     */
    
    // 图片路径
    private String[]          images;

    public String[] getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String[] photoName) {
        this.photoName = photoName;
    }

    private String[]          photoName;

    /**城市代码*/
    private java.lang.String  city;

    /**省代码*/
    private java.lang.String  province;

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    /**县区**/
    private String county;

    
  /*  // 门店形象图片地址
    protected List<String>    imgspath;
    
    // 上传图片原名称
    protected List<String>    oldimgNames;
    
    // 域名
    protected String          domainName;
    
    // 图片新名称
    protected String          imageName;
    
    // 区域等级
    protected Integer         level;
    

    
    // 图片名称
    private String[]          photoName;
    
    // 上传者IP
    private Integer           uploadIp;
    
    */
    
 // 经绡商编号集合
    private String[]          idAry;
    
    
    public java.lang.String getCity()
    {
        return city;
    }

    public void setCity(java.lang.String city)
    {
        this.city = city;
    }

    public java.lang.String getProvince()
    {
        return province;
    }

    public void setProvince(java.lang.String province)
    {
        this.province = province;
    }

    public String[] getIdAry()
    {
        return idAry;
    }

    public void setIdAry(String[] idAry)
    {
        this.idAry = idAry;
    }

    public String getPath()
    {
        return path;
    }

    public String[] getImages()
    {
        return images;
    }

    public void setImages(String[] images)
    {
        this.images = images;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public java.lang.String getBrandId()
    {
        return this.brandId;
    }

    public void setBrandId(java.lang.String brandId)
    {
        this.brandId = brandId;
    }

    public java.lang.String getBrandsId()
    {
        return this.brandsId;
    }

    public void setBrandsId(java.lang.String brandsId)
    {
        this.brandsId = brandsId;
    }

    public java.lang.String getDealerId()
    {
        return this.dealerId;
    }

    public void setDealerId(java.lang.String dealerId)
    {
        this.dealerId = dealerId;
    }

    public java.lang.String getDealerName()
    {
        return this.dealerName;
    }

    public void setDealerName(java.lang.String dealerName)
    {
        this.dealerName = dealerName;
    }

    public java.lang.String getLevelId()
    {
        return this.levelId;
    }

    public void setLevelId(java.lang.String levelId)
    {
        this.levelId = levelId;
    }

    public java.lang.String getUserName()
    {
        return this.userName;
    }

    public void setUserName(java.lang.String userName)
    {
        this.userName = userName;
    }

    public java.lang.String getTelphone()
    {
        return this.telphone;
    }

    public void setTelphone(java.lang.String telphone)
    {
        this.telphone = telphone;
    }

    public java.lang.String getMobile()
    {
        return this.mobile;
    }

    public void setMobile(java.lang.String mobile)
    {
        this.mobile = mobile;
    }

    public java.lang.String getProvinceName()
    {
        return this.provinceName;
    }

    public void setProvinceName(java.lang.String provinceName)
    {
        this.provinceName = provinceName;
    }

    public java.lang.String getCityName()
    {
        return this.cityName;
    }

    public void setCityName(java.lang.String cityName)
    {
        this.cityName = cityName;
    }

    public java.lang.String getAreaName()
    {
        return this.areaName;
    }

    public void setAreaName(java.lang.String areaName)
    {
        this.areaName = areaName;
    }

    public java.lang.Integer getAreaNo()
    {
        return this.areaNo;
    }

    public void setAreaNo(java.lang.Integer areaNo)
    {
        this.areaNo = areaNo;
    }

    public java.lang.String getAddress()
    {
        return this.address;
    }

    public void setAddress(java.lang.String address)
    {
        this.address = address;
    }

    public java.lang.Boolean getShowFlag()
    {
        return this.showFlag;
    }

    public void setShowFlag(java.lang.Boolean showFlag)
    {
        this.showFlag = showFlag;
    }

    public java.lang.Integer getWholePercent()
    {
        return this.wholePercent;
    }

    public void setWholePercent(java.lang.Integer wholePercent)
    {
        this.wholePercent = wholePercent;
    }

    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }

    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }


}
