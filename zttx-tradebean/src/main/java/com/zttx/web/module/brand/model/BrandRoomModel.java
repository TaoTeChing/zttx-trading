package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandRoom;

/**
 * 
 * <p>File：BrandRoomModel.java</p>
 * <p>Title: BrandRoomModel</p>
 * <p>Description:BrandRoomModel</p>
 * <p>Copyright: Copyright (c) Aug 28, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class BrandRoomModel extends BrandRoom{

    private static final long serialVersionUID = -8807930191875151781L;
    
    // 品牌文字介绍
    private String            brandSubMark;
    
    // 企业产品形象图
    private String[]          photoImgPaths;
    
    // 企业产品形象图的原文件名，与企业产品形象图是一一对应的关系
    private String[]          photoImgNames;
    
    private String            mainProLogo;
    
    private Integer           ip;
    
    // 推荐图片
    private String            recommendImagePath;
    
    // 推荐图片原文件名
    private String            recommendImageName;
    
    private String            brandsId;
    
    public String getBrandsId() {
		return brandsId;
	}

	public void setBrandsId(String brandsId) {
		this.brandsId = brandsId;
	}

	public String getBrandSubMark()
    {
        return brandSubMark;
    }
    
    public void setBrandSubMark(String brandSubMark)
    {
        this.brandSubMark = brandSubMark;
    }
    
    public String[] getPhotoImgPaths()
    {
        return photoImgPaths;
    }
    
    public void setPhotoImgPaths(String[] photoImgPaths)
    {
        this.photoImgPaths = photoImgPaths;
    }
    
    public String[] getPhotoImgNames()
    {
        return photoImgNames;
    }
    
    public void setPhotoImgNames(String[] photoImgNames)
    {
        this.photoImgNames = photoImgNames;
    }
    
    public String getMainProLogo()
    {
        return mainProLogo;
    }
    
    public void setMainProLogo(String mainProLogo)
    {
        this.mainProLogo = mainProLogo;
    }
    
    public Integer getIp()
    {
        return ip;
    }
    
    public void setIp(Integer ip)
    {
        this.ip = ip;
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
