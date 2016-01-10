/*
 * @(#)PriceModel.java 14-3-25 下午2:50
 * Copyright 2014 吴万杰, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.zttx.goods.module.dto.Attribute;

/**
 * <p>File：PriceModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 14-3-25 下午2:50</p>
 * <p>Company: 8637.com</p>
 *
 * @author 吴万杰
 * @version 1.0
 */
public class PriceModel implements Serializable
{
    private static final long serialVersionUID = 5038824183410337037L;
    
    protected List<Attribute> z                = Lists.newArrayList(); // 属性组合
    
    protected BigDecimal      p;                                      // /**价格*/
    
    protected Integer         s;                                      // 库存
    
    protected String          bc;                                     // 条形码
    
    protected BigDecimal      dp;                                     // 直供价格
    
    protected BigDecimal      cp;                                     //授信价
    
    private BigDecimal        fd;                                    //返点价
    
    
    
    public BigDecimal getFd()
    {
        return fd;
    }

    public void setFd(BigDecimal fd)
    {
        this.fd = fd;
    }

    public BigDecimal getCp()
    {
        return cp;
    }

    public void setCp(BigDecimal cp)
    {
        this.cp = cp;
    }

    public Integer getS()
    {
        return s;
    }
    
    public BigDecimal getP()
    {
        return p;
    }
    
    public void setP(BigDecimal p)
    {
        this.p = p;
    }
    
    public void setS(Integer s)
    {
        this.s = s;
    }
    
    public String[] findArrayZ()
    {
        List<String> vids = Lists.newArrayList();
        for (Attribute attr : z)
            vids.add(attr.getVid());
        return vids.toArray(new String[0]);
    }
    
    public String getBc()
    {
        return bc;
    }
    
    public void setBc(String bc)
    {
        this.bc = bc;
    }

    public BigDecimal getDp()
    {
        return dp;
    }

    public void setDp(BigDecimal dp)
    {
        this.dp = dp;
    }

    public List<Attribute> getZ()
    {
        return z;
    }

    public void setZ(List<Attribute> z)
    {
        this.z = z;
    }
    
    
}
