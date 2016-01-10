/*
 * @(#)ZtreeTreeNode.java 2015-8-12 下午7:27:19
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.model;

/**
 * <p>File：ZtreeTreeNode.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-12 下午7:27:19</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
public class ZtreeTreeNode
{
    private String          id;
    
    private String          pId;
    
    private String          name;
    
    private Boolean         checked;
    
    private Boolean         open;
    
    private Integer         orderNo;  // 排序号
    
    /**菜单级别*/
    private java.lang.Short menuLevel;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getpId()
    {
        return pId;
    }
    
    public void setpId(String pId)
    {
        this.pId = pId;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Boolean getChecked()
    {
        return checked;
    }
    
    public void setChecked(Boolean checked)
    {
        this.checked = checked;
    }
    
    public Boolean getOpen()
    {
        return open;
    }
    
    public void setOpen(Boolean open)
    {
        this.open = open;
    }
    
    public Integer getOrderNo()
    {
        return orderNo;
    }
    
    public ZtreeTreeNode setOrderNo(Integer orderNo)
    {
        this.orderNo = orderNo;
        return this;
    }
    
    public Short getMenuLevel()
    {
        return menuLevel;
    }
    
    public ZtreeTreeNode setMenuLevel(Short menuLevel)
    {
        this.menuLevel = menuLevel;
        return this;
    }
}
