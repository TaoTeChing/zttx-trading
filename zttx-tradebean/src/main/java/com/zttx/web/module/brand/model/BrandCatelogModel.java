package com.zttx.web.module.brand.model;

import java.beans.Transient;

import com.zttx.web.module.brand.entity.BrandCatelog;
import com.zttx.web.module.common.entity.DealDic;

/**
 * Created by 李星 on 2015/8/11.
 */
public class BrandCatelogModel extends BrandCatelog
{
    private DealDic dealDic;
    
    @Transient
    public DealDic getDealDic()
    {
        return dealDic;
    }
    
    public void setDealDic(DealDic dealDic)
    {
        this.dealDic = dealDic;
    }
}
