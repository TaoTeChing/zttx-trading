package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandDoccate;

/**
 * <p>File：BrandDoccateModel.java</p>
 * <p>Title: BrandDoccateModel</p>
 * <p>Description:BrandDoccateModel</p>
 * <p>Copyright: Copyright (c) Aug 24, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class BrandDoccateModel extends BrandDoccate
{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// 产品分类编号集合
    private String[] refrenceIdAry;
    
    // 产品分类名称集合
    private String[] cateNameAry;
    
    // 多选分类编号集合
    private String[] chkRefrenceId;
    
    public String[] getRefrenceIdAry()
    {
        return refrenceIdAry;
    }
    
    public void setRefrenceIdAry(String[] refrenceIdAry)
    {
        this.refrenceIdAry = refrenceIdAry;
    }
    
    public String[] getCateNameAry()
    {
        return cateNameAry;
    }
    
    public void setCateNameAry(String[] cateNameAry)
    {
        this.cateNameAry = cateNameAry;
    }
    
    public String[] getChkRefrenceId()
    {
        return chkRefrenceId;
    }
    
    public void setChkRefrenceId(String[] chkRefrenceId)
    {
        this.chkRefrenceId = chkRefrenceId;
    }
}
