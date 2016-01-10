package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandAddress;

/**
 * Created by 李星 on 2015/8/15.
 */
public class BrandAddressModel extends BrandAddress
{
    private Boolean newStore;
    
    private String  storeValue; // ?

    private String  fullAreaName;
    
    private String  brandsName;
    
    private String  storeName;
    
    public Boolean getNewStore()
    {
        return newStore;
    }
    
    public void setNewStore(Boolean newStore)
    {
        this.newStore = newStore;
    }
    
    public String getStoreValue()
    {
        return storeValue;
    }
    
    public void setStoreValue(String storeValue)
    {
        this.storeValue = storeValue;
    }
    
    public String getFullAreaName()
    {
        return fullAreaName;
    }
    
    public void setFullAreaName(String fullAreaName)
    {
        this.fullAreaName = fullAreaName;
    }
    
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }
    
    public String getStoreName()
    {
        return storeName;
    }
    
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }
}
