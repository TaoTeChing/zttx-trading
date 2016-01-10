package com.zttx.web.module.dealer.model;

import com.zttx.web.module.dealer.entity.DealerJoin;

/**
 * <p>File：DealerJoinModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-1 下午5:35:58</p>
 * <p>Company: 8637.com</p>
 * @author 施建波
 * @version 1.0
 */
public class DealerJoinModel extends DealerJoin
{
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// 经绡商编号集合
    private String[] idAry;
    
    // 省
    private String   province;
    
    // 市
    private String   city;
    
    // 区
    private String   county;
    
    // 加盟申请日志状态
    private Short    checkState;
    
    // 合作中止人员
    private String   stopUserId;
    
    // 经绡商所对应的品牌编号
    private String  joinBrandsId;
    
    // 区域等级
    private Integer level;
    
    private String  brandName;

    private String  brandsName;

    private String  dealerName;


    public String getStopUserId()
    {
        return stopUserId;
    }
    
    public void setStopUserId(String stopUserId)
    {
        this.stopUserId = stopUserId;
    }
    
    public Short getCheckState()
    {
        return checkState;
    }
    
    public void setCheckState(Short checkState)
    {
        this.checkState = checkState;
    }
    
    public String getJoinBrandsId()
    {
        return joinBrandsId;
    }
    
    public void setJoinBrandsId(String joinBrandsId)
    {
        this.joinBrandsId = joinBrandsId;
    }
    
    public String getProvince()
    {
        return province;
    }
    
    public void setProvince(String province)
    {
        this.province = province;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }
    
    public String getCounty()
    {
        return county;
    }
    
    public void setCounty(String county)
    {
        this.county = county;
    }
    
    public String[] getIdAry()
    {
        return idAry;
    }
    
    public void setIdAry(String[] idAry)
    {
        this.idAry = idAry;
    }
    
    public Integer getLevel()
    {
        return level;
    }
    
    public void setLevel(Integer level)
    {
        this.level = level;
    }
}
