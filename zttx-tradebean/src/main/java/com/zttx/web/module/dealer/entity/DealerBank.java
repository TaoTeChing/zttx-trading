/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.entity;

import com.zttx.sdk.core.GenericEntity;

/**
 * 经销商银行卡信息 实体对象
 * <p>File：DealerBank.java</p>
 * <p>Title: DealerBank</p>
 * <p>Description:DealerBank</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class DealerBank extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**经销商编号*/
    private String            dealerId;
    
    /**帐户类型（１：个人帐户，２：公司帐户）*/
    private Short             bankCate;
    
    /**银行卡编号*/
    private String            bankId;
    
    /**姓名/公司名*/
    private String            userName;
    
    /**身份证号/营业执照号*/
    private String            userId;
    
    /**银行卡号*/
    private String            bankNo;
    
    /**开户银行*/
    private String            openBank;
    
    /**开户分行*/
    private String            subBank;
    
    /**区域编号*/
    private String            areaNo;
    
    /**是否默认银行卡*/
    private Boolean           cardDefault;
    
    /**建档时间*/
    private Long              createTime;
    
    /**修改时间*/
    private Long              updateTime;
    
    /**审核状态（0：未审核，1：审核通过，2：审核不通过）*/
    private Short             checkState;
    
    /**迁移标记*/
    private Integer           flag;
    
    public String getDealerId()
    {
        return this.dealerId;
    }
    
    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }
    
    public Short getBankCate()
    {
        return this.bankCate;
    }
    
    public void setBankCate(Short bankCate)
    {
        this.bankCate = bankCate;
    }
    
    public String getBankId()
    {
        return this.bankId;
    }
    
    public void setBankId(String bankId)
    {
        this.bankId = bankId;
    }
    
    public String getUserName()
    {
        return this.userName;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    public String getUserId()
    {
        return this.userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getBankNo()
    {
        return this.bankNo;
    }
    
    public void setBankNo(String bankNo)
    {
        this.bankNo = bankNo;
    }
    
    public String getOpenBank()
    {
        return this.openBank;
    }
    
    public void setOpenBank(String openBank)
    {
        this.openBank = openBank;
    }
    
    public String getSubBank()
    {
        return this.subBank;
    }
    
    public void setSubBank(String subBank)
    {
        this.subBank = subBank;
    }
    
    public String getAreaNo()
    {
        return this.areaNo;
    }
    
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public Boolean getCardDefault()
    {
        return this.cardDefault;
    }
    
    public void setCardDefault(Boolean cardDefault)
    {
        this.cardDefault = cardDefault;
    }
    
    public Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Long createTime)
    {
        this.createTime = createTime;
    }
    
    public Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public Short getCheckState()
    {
        return this.checkState;
    }
    
    public void setCheckState(Short checkState)
    {
        this.checkState = checkState;
    }
    
    public Integer getFlag()
    {
        return this.flag;
    }
    
    public void setFlag(Integer flag)
    {
        this.flag = flag;
    }
}
