package com.zttx.web.module.common.model;

import java.sql.Date;

/**
 * <p>File:ErpSellDetailModel</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/10/13 13:51</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class ErpSellDetailModel {

    private String zttxDealerId; // 经销商编号

    private String supplierId;  // 供货商编号

    private Long   searchTime;  // 时间(指定日期凌晨时间精确到毫秒，Long类型)

    private String productNo;   // 产品货号

    private Date searchDate;  // 时间(指定日期凌晨时间精确到毫秒，Long类型)

    private String productName; // 产品名称

    public String getZttxDealerId()
    {
        return zttxDealerId;
    }

    public void setZttxDealerId(String zttxDealerId)
    {
        this.zttxDealerId = zttxDealerId;
    }

    public String getSupplierId()
    {
        return supplierId;
    }

    public void setSupplierId(String supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSearchTime()
    {
        return searchTime;
    }

    public void setSearchTime(Long searchTime)
    {
        this.searchTime = searchTime;
    }

    public String getProductNo()
    {
        return productNo;
    }

    public void setProductNo(String productNo)
    {
        this.productNo = productNo;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public Date getSearchDate()
    {
        return searchDate;
    }

    public void setSearchDate(Date searchDate)
    {
        this.searchDate = searchDate;
    }
}
