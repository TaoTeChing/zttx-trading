package com.zttx.web.module.common.model;

import java.math.BigDecimal;

/**
 * 转账
 * <p>File：TransferModel.java</p>
 * <p>Title: TransferModel</p>
 * <p>Description:TransferModel</p>
 * <p>Copyright: Copyright (c) Jul 7, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class TransferModel
{
	/** 转账说明 */
	private String title;
	/** 转账金额 */
	private BigDecimal amount;
	/** 支付密码 */
	private String payPwd;
	/** 收款方用户ID */
	private String toUserId;
	/** 收款方名称 */
	private String toUserName;
	/** 收款方手机号码 */
	private String toUserMobile;
    /** 转账方用户 ID */
	private Long sourceUser;
	/** 收款方用户  ID */
	private Long targetUser;
	/** 转账方名称 */
	private String fromUserName;
	/** 加盟合作ID */
	private String dealerJoinId;
	/** 描述 */
	private String desc;
	/**
	 * 转账类型：1：转账  2：当期欠付款转账
	 */
	private Integer type = 1;
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getDesc()
    {
        return desc;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    public String getDealerJoinId()
    {
        return dealerJoinId;
    }
    public void setDealerJoinId(String dealerJoinId)
    {
        this.dealerJoinId = dealerJoinId;
    }
    public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getSourceUser() {
		return sourceUser;
	}
	public void setSourceUser(Long sourceUser) {
		this.sourceUser = sourceUser;
	}
	public Long getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(Long targetUser) {
		this.targetUser = targetUser;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getPayPwd() {
		return payPwd;
	}
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
    public String getToUserMobile()
    {
        return toUserMobile;
    }
    public void setToUserMobile(String toUserMobile)
    {
        this.toUserMobile = toUserMobile;
    }
}
