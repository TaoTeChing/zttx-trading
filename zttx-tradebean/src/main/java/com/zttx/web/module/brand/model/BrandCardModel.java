package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandCard;

/**
 * Created by 李星 on 2015/8/13.
 */
public class BrandCardModel extends BrandCard
{
    protected String certImagePath; // 临时路径
    
    public String getCertImagePath()
    {
        return certImagePath;
    }
    
    public void setCertImagePath(String certImagePath)
    {
        this.certImagePath = certImagePath;
    }
}
