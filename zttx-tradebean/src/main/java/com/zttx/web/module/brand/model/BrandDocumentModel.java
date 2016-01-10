package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandDocument;

/**
 * <p>File：BrandDocumentModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-19 下午3:48:53</p>
 * <p>Company: 8637.com</p>
 * @version 1.0
 */
public class BrandDocumentModel extends BrandDocument
{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String[] dealerIds;
	
	private String   dealerId;
    
	private String   brandsName;
    
    public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	
    public String getBrandsName()
    {
        return brandsName;
    }
    
    public void setBrandsName(String brandsName)
    {
        this.brandsName = brandsName;
    }
    
    public String[] getDealerIds()
    {
        return dealerIds;
    }
    
    public void setDealerIds(String[] dealerIds)
    {
        this.dealerIds = dealerIds;
    }
}
