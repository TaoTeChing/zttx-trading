package com.zttx.web.module.brand.model;

/**
 * 品牌商品牌授权区域查询条件
 * <p>File：BrandesInfoRegionalModel.java</p>
 * <p>Title: BrandesInfoRegionalModel</p>
 * <p>Description:BrandesInfoRegionalModel</p>
 * <p>Copyright: Copyright (c) Jun 11, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class BrandesInfoRegionalModel
{
    /** 品牌商编号 */
    private String   brandId;
    
    /** 品牌编号 */
    private String   brandesId;
    
    /** 地区编号 */
    private Integer  areaNo;
    
    /**地区编码列表 */
    public Integer[] areaAry;
    
    public Integer[] getAreaAry()
    {
        return areaAry;
    }

    public void setAreaAry(Integer[] areaAry)
    {
        this.areaAry = areaAry;
    }

    public String getBrandesId()
    {
        return brandesId;
    }
    
    public void setBrandesId(String brandesId)
    {
        this.brandesId = brandesId;
    }
    
    public String getBrandId()
    {
        return brandId;
    }
    
    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }
    
    public Integer getAreaNo()
    {
        return areaNo;
    }
    
    public void setAreaNo(Integer areaNo)
    {
        this.areaNo = areaNo;
    }
}
