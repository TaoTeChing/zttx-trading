/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.zttx.sdk.core.GenericEntity;

/**
 * 角色信息 实体对象
 * <p>File：RoleInfo.java</p>
 * <p>Title: RoleInfo</p>
 * <p>Description:RoleInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public class RoleInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**角色编码*/
    @NotBlank(message = "角色编码不能为空")
    private java.lang.String  roleCode;
    
    /**角色名称*/
    @NotBlank(message = "角色名称不能为空")
    private java.lang.String  roleName;
    
    /**建档者IP*/
    private java.lang.Integer createIp;
    
    /**建档时间*/
    private java.lang.Long    createTime;
    
    /**updateTime*/
    private java.lang.Long    updateTime;
    
    /**
     * 是否允许删除
     * 0：不允许
     * 1：允许
     */
    private java.lang.Boolean canDel;
    
    public java.lang.String getRoleCode()
    {
        return this.roleCode;
    }
    
    public void setRoleCode(java.lang.String roleCode)
    {
        this.roleCode = roleCode;
    }
    
    public java.lang.String getRoleName()
    {
        return this.roleName;
    }
    
    public void setRoleName(java.lang.String roleName)
    {
        this.roleName = roleName;
    }
    
    public java.lang.Integer getCreateIp()
    {
        return this.createIp;
    }
    
    public void setCreateIp(java.lang.Integer createIp)
    {
        this.createIp = createIp;
    }
    
    public java.lang.Long getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(java.lang.Long createTime)
    {
        this.createTime = createTime;
    }
    
    public java.lang.Long getUpdateTime()
    {
        return this.updateTime;
    }
    
    public void setUpdateTime(java.lang.Long updateTime)
    {
        this.updateTime = updateTime;
    }
    
    public java.lang.Boolean getCanDel()
    {
        return this.canDel;
    }
    
    public void setCanDel(java.lang.Boolean canDel)
    {
        this.canDel = canDel;
    }
}
