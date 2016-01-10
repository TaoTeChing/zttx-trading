/*
 * @(#)BrandesInfoModel.java 2014-3-6 下午12:10:47
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.module.brand.entity.BrandCert;
import com.zttx.web.module.brand.entity.BrandDeal;
import com.zttx.web.module.brand.entity.BrandLicening;
import com.zttx.web.module.brand.entity.BrandPhoto;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.NetworkUtils;

/**
 * <p>File：BrandesInfoModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-6 下午12:10:47</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class BrandesInfoModel extends BrandesInfo
{
    // 品牌名称
    private String   brandName;
    
    // 品牌LOGO
    private String   logoImgPath;
    
    // 品牌ＬＯＧＯ原文件名
    private String   logoImgName;
    
    // 品牌证书
    private String[] certImgPaths;
    
    // 品牌证书的原文件名，与品牌证书是一一对应的关系
    private String[] certImgNames;
    
    // 品牌授权书图片路径
    private String[] liceningImgPaths;
    
    // 品牌授权书图片名称
    private String[] liceningImgNames;
    
    // 企业产品形象图
    private String[] photoImgPaths;
    
    // 企业产品形象图的原文件名，与企业产品形象图是一一对应的关系
    private String[] photoImgNames;
    
    // 品牌持有人
    private Short    brandHold;
    
    // 品牌类型
    private Short    brandType;
    
    // 持有人名称
    private String   holdName;
    
    // 品牌主营类目
    private String[] deals;
    
    // 品牌主营类目名称
    private String[] dealNames;
    
    // 品牌介绍
    private String   brandMark;
    
    // 品牌商编号
    private String   brandId;
    
    // 建档者IP
    private Integer  createIp;
    
    // 当前操作用户编号
    private String   userId;
    
    // 品牌形象企业主图
    private String   mainProLogo;
    
    // 品牌文字介绍
    private String   brandSubMark;
    
    // 推荐图片
    private String   recommendImagePath;
    
    // 推荐图片原文件名
    private String   recommendImageName;
    
    // 转化成BrandesInfo对象
    public BrandesInfo parseBrandesInfo()
    {
        BrandesInfo brandesInfo = new BrandesInfo();
        brandesInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandesInfo.setBrandId(this.brandId);
        brandesInfo.setBrandsName(this.brandName);
        brandesInfo.setLogoDomin(NetworkUtils.getDoMainName());
        brandesInfo.setBrandLogo(this.logoImgPath);
        brandesInfo.setBrandType(this.brandType);
        brandesInfo.setBrandHold(this.brandHold);
        brandesInfo.setHoldName(this.holdName);
        brandesInfo.setBrandMark(this.brandMark);
        brandesInfo.setBrandState(BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED);
        brandesInfo.setCreateTime(CalendarUtils.getCurrentLong());
        brandesInfo.setCreateIp(this.createIp);
        brandesInfo.setDelFlag(false);
        brandesInfo.setBrandSubMark(this.brandSubMark);
        return brandesInfo;
    }
    
    // 转化成BrandCert对象
    public List<BrandCert> parseBrandCertList(BrandesInfo brandesInfo)
    {
        List<BrandCert> list = new ArrayList<BrandCert>();
        for (int i = 0; i < this.certImgPaths.length; i++)
        {
            String certImgPath = this.certImgPaths[i];
            if (StringUtils.isNotBlank(certImgPath))
            {
                String certImgName = this.certImgNames[i];
                BrandCert brandCert = new BrandCert();
                brandCert.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandCert.setBrandId(this.brandId);
                brandCert.setBrandesId(brandesInfo.getRefrenceId());
                brandCert.setDomainName(NetworkUtils.getDoMainName());
                brandCert.setFileName(certImgName);
                brandCert.setImageName(certImgPath);
                brandCert.setCreateTime(CalendarUtils.getCurrentLong());
                brandCert.setCreateIp(this.createIp);
                brandCert.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                list.add(brandCert);
            }
        }
        return list;
    }
    
    // 转化成BrandLicening对象
    public List<BrandLicening> parseBrandLiceningList(BrandesInfo brandesInfo)
    {
        List<BrandLicening> list = new ArrayList<BrandLicening>();
        for (int i = 0; i < this.liceningImgPaths.length; i++)
        {
            String liceningImgPath = this.liceningImgPaths[i];
            if (StringUtils.isNotBlank(liceningImgPath))
            {
                String liceningImgName = this.liceningImgNames[i];
                BrandLicening brandLicening = new BrandLicening();
                brandLicening.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandLicening.setBrandId(this.brandId);
                brandLicening.setBrandesId(brandesInfo.getRefrenceId());
                brandLicening.setDomainName(NetworkUtils.getDoMainName());
                brandLicening.setFileName(liceningImgName);
                brandLicening.setImageName(liceningImgPath);
                brandLicening.setCreateTime(CalendarUtils.getCurrentLong());
                brandLicening.setCreateIp(this.createIp);
                brandLicening.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                list.add(brandLicening);
            }
        }
        return list;
    }
    
    // 转化成BrandPhoto对象
    public List<BrandPhoto> parseBrandPhotoList(BrandesInfo brandesInfo)
    {
        List<BrandPhoto> list = new ArrayList<BrandPhoto>();
        for (int i = 0; i < this.photoImgPaths.length; i++)
        {
            String photoImgPath = this.photoImgPaths[i];
            if (StringUtils.isNotBlank(photoImgPath))
            {
                String photoImgName = this.photoImgNames[i];
                BrandPhoto brandPhoto = new BrandPhoto();
                brandPhoto.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandPhoto.setBrandId(this.brandId);
                brandPhoto.setBrandesId(brandesInfo.getRefrenceId());
                brandPhoto.setDomainName(NetworkUtils.getDoMainName());
                brandPhoto.setPhotoName(photoImgName);
                brandPhoto.setImageName(photoImgPath);
                brandPhoto.setCreateTime(CalendarUtils.getCurrentLong());
                brandPhoto.setCreateIP(this.createIp);
                brandPhoto.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                list.add(brandPhoto);
            }
        }
        return list;
    }
    
    // 转化成BrandDeal对象
    public List<BrandDeal> parseBrandDealList(BrandesInfo brandesInfo)
    {
        List<BrandDeal> list = new ArrayList<BrandDeal>();
        for (String deal : this.deals)
            if (StringUtils.isNotBlank(deal))
            {
                Integer dealNo = Integer.parseInt(deal);
                BrandDeal brandDeal = new BrandDeal();
                brandDeal.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandDeal.setBrandId(this.brandId);
                brandDeal.setBrandesId(brandesInfo.getRefrenceId());
                brandDeal.setDealNo(dealNo);
                brandDeal.setCreateTime(CalendarUtils.getCurrentLong());
                brandDeal.setCreateIp(this.createIp);
                brandDeal.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                list.add(brandDeal);
            }
        return list;
    }
    
    @NotBlank(message = "品牌Logo路径不能为空")
    public String getLogoImgPath()
    {
        return logoImgPath;
    }
    @NotBlank(message = "品牌名称不能为空")
    @Length(max = 32, message = "品牌名称的长度必须小于{max}个字")
    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public void setLogoImgPath(String logoImgPath)
    {
        this.logoImgPath = logoImgPath;
    }
    
    public String getLogoImgName()
    {
        return logoImgName;
    }
    
    public void setLogoImgName(String logoImgName)
    {
        this.logoImgName = logoImgName;
    }
    
    @NotEmpty(message = "品牌证书不能为空")
    public String[] getCertImgPaths()
    {
        return certImgPaths;
    }
    
    public void setCertImgPaths(String[] certImgPaths)
    {
        this.certImgPaths = certImgPaths;
    }
    
    @NotEmpty(message = "品牌形象不能为空")
    public String[] getPhotoImgPaths()
    {
        return photoImgPaths;
    }
    
    public void setPhotoImgPaths(String[] photoImgPaths)
    {
        this.photoImgPaths = photoImgPaths;
    }
    
    @NotNull(message = "品牌持有人类型不能为空")
    public Short getBrandHold()
    {
        return brandHold;
    }
    
    public void setBrandHold(Short brandHold)
    {
        this.brandHold = brandHold;
    }
    
    @NotNull(message = "品牌类型不能为空")
    public Short getBrandType()
    {
        return brandType;
    }
    
    public void setBrandType(Short brandType)
    {
        this.brandType = brandType;
    }
    
    @NotBlank(message = "品牌持有人名称不能为空")
    @Length(max = 64, message = "品牌持有人名称的长度必须小于{max}个字")
    public String getHoldName()
    {
        return holdName;
    }
    
    public void setHoldName(String holdName)
    {
        this.holdName = holdName;
    }
    
    @NotEmpty(message = "品牌主营类目不能为空")
    public String[] getDeals()
    {
        return deals;
    }
    
    public void setDeals(String[] deals)
    {
        this.deals = deals;
    }
    
    @NotBlank(message = "品牌简介不能为空")
    @Length(max = 65535, message = "品牌简介的长度必须小于{max}个字")
    public String getBrandMark()
    {
        return brandMark;
    }
    
    public void setBrandMark(String brandMark)
    {
        this.brandMark = brandMark;
    }
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public Integer getCreateIp()
    {
        return createIp;
    }
    
    public void setCreateIp(Integer createIp)
    {
        this.createIp = createIp;
    }
    
    @NotEmpty(message = "品牌证书原文件名不能为空")
    public String[] getCertImgNames()
    {
        return certImgNames;
    }
    
    public void setCertImgNames(String[] certImgNames)
    {
        this.certImgNames = certImgNames;
    }
    
    @NotEmpty(message = "品牌形象原文件名不能为空")
    public String[] getPhotoImgNames()
    {
        return photoImgNames;
    }
    
    public void setPhotoImgNames(String[] photoImgNames)
    {
        this.photoImgNames = photoImgNames;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String[] getDealNames()
    {
        return dealNames;
    }
    
    public void setDealNames(String[] dealNames)
    {
        this.dealNames = dealNames;
    }
    
    public String[] getLiceningImgPaths()
    {
        return liceningImgPaths;
    }
    
    public void setLiceningImgPaths(String[] liceningImgPaths)
    {
        this.liceningImgPaths = liceningImgPaths;
    }
    
    public String[] getLiceningImgNames()
    {
        return liceningImgNames;
    }
    
    public void setLiceningImgNames(String[] liceningImgNames)
    {
        this.liceningImgNames = liceningImgNames;
    }
    
    public String getMainProLogo()
    {
        return mainProLogo;
    }
    
    public void setMainProLogo(String mainProLogo)
    {
        this.mainProLogo = mainProLogo;
    }
    
    public String getBrandSubMark()
    {
        return brandSubMark;
    }
    
    public void setBrandSubMark(String brandSubMark)
    {
        this.brandSubMark = brandSubMark;
    }
    
    public String getRecommendImagePath()
    {
        return recommendImagePath;
    }
    
    public void setRecommendImagePath(String recommendImagePath)
    {
        this.recommendImagePath = recommendImagePath;
    }
    
    public String getRecommendImageName()
    {
        return recommendImageName;
    }
    
    public void setRecommendImageName(String recommendImageName)
    {
        this.recommendImageName = recommendImageName;
    }
}
