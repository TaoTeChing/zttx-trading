/*
 * @(#)DealerShopEnvModel.java 2014-8-25 下午1:27:37
 * Copyright 2014 吕海斌, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.model;

import java.util.List;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.entity.DealerShopEnv;

/**
 * <p>File：DealerShopEnvModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-8-25 下午1:27:37</p>
 * <p>Company: 8637.com</p>
 * @author 吕海斌
 * @version 1.0
 */
public class DealerShopEnvModel extends DealerShopEnv
{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private boolean           onlyShowPic;
    
    private boolean           payedUser;
    
    private String            mobile;
    
    private String            username;
    
    private List<String>      envTmpImgIds;
    
    private Integer           cityNo;
    
    private Integer           provinceNo;
    
    private String            areaName;
    
    private String            cityName;
    
    private String            provinceName;
    
    private String            openTimeStr;
    
    private List<String>      pics;
    
    private String            address;
    
    private List<DealerImage> dealerImages;
    
    public boolean isOnlyShowPic()
    {
        return onlyShowPic;
    }
    
    public void setOnlyShowPic(boolean onlyShowPic)
    {
        this.onlyShowPic = onlyShowPic;
    }
    
    public boolean isPayedUser()
    {
        return payedUser;
    }
    
    public void setPayedUser(boolean payedUser)
    {
        this.payedUser = payedUser;
    }
    
    public List<DealerImage> getDealerImages()
    {
        return dealerImages;
    }
    
    public void setDealerImages(List<DealerImage> dealerImages)
    {
        this.dealerImages = dealerImages;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public Integer getCityNo()
    {
        return cityNo;
    }
    
    public void setCityNo(Integer cityNo)
    {
        this.cityNo = cityNo;
    }
    
    public Integer getProvinceNo()
    {
        return provinceNo;
    }
    
    public void setProvinceNo(Integer provinceNo)
    {
        this.provinceNo = provinceNo;
    }
    
    public String getAreaName()
    {
        return areaName;
    }
    
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    public String getProvinceName()
    {
        return provinceName;
    }
    
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }
    
    public String getOpenTimeStr()
    {
        return openTimeStr;
    }
    
    public void setOpenTimeStr(String openTimeStr)
    {
        this.openTimeStr = openTimeStr;
    }
    
    public List<String> getPics()
    {
        return pics;
    }
    
    public void setPics(List<String> pics)
    {
        this.pics = pics;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public List<String> getEnvTmpImgIds()
    {
        return envTmpImgIds;
    }
    
    public void setEnvTmpImgIds(List<String> envTmpImgIds)
    {
        this.envTmpImgIds = envTmpImgIds;
    }
}
