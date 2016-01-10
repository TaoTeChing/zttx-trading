/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.utils.IPUtil;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.RoleInfo;
import com.zttx.web.module.common.entity.RoleMenu;
import com.zttx.web.module.common.model.RoleMenuModel;
import com.zttx.web.module.common.service.RoleInfoService;
import com.zttx.web.module.common.service.RoleMenuService;

/**
 * 角色信息 控制器
 * <p>File：RoleInfoController.java </p>
 * <p>Title: RoleInfoController </p>
 * <p>Description:RoleInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/common/roleInfo")
public class RoleInfoController extends GenericController
{
    @Autowired(required = true)
    private RoleInfoService roleInfoService;
    @Autowired
    private RoleMenuService roleMenuService;

    
    @RequestMapping("/add")
    @ResponseBody
    public JsonMessage add(RoleInfo roleInfo,HttpServletRequest request){
        roleInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        roleInfo.setCreateTime(new Date().getTime());
        roleInfo.setUpdateTime(new Date().getTime());
        roleInfo.setCreateIp(IPUtil.getIpAddr(request));
        roleInfoService.insert(roleInfo);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequestMapping("/modify")
    @ResponseBody
    public JsonMessage modify(RoleInfo roleInfo){
        roleInfo.setUpdateTime(new Date().getTime());
        roleInfoService.updateByPrimaryKeySelective(roleInfo);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequestMapping("/modify/forward")
    @ResponseBody
    public RoleInfo modifyForward(RoleInfo roleInfo){
        roleInfo=roleInfoService.selectByPrimaryKey(roleInfo.getRefrenceId());
        return roleInfo;
    }
    
    @RequestMapping("/remove")
    @ResponseBody
    public JsonMessage remove(RoleInfo roleInfo){
        roleInfo=roleInfoService.selectByPrimaryKey(roleInfo.getRefrenceId());
        if(!roleInfo.getCanDel()){
            return this.getJsonMessage(CommonConst.FAIL.getCode(), "不可删除");
        }
        roleInfoService.delete(roleInfo.getRefrenceId());
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequestMapping("/enable")
    @ResponseBody
    public JsonMessage enable(RoleInfo roleInfo){
        roleInfoService.enable(roleInfo.getRefrenceId());
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    
    /**
     * 
     * @param
     * @param roleInfo
     * @return
     */
    @RequestMapping("/setMenu")
    @ResponseBody
    public JsonMessage setMenu(RoleMenuModel roleMenuModel,RoleInfo roleInfo){
        roleMenuService.deleteByRoleId(roleInfo.getRefrenceId());
        List<RoleMenu> roleMenuList=roleMenuModel.getRoleMenuList();
        if(roleMenuList!=null){
            for(int i=0;i<roleMenuList.size();i++){
                RoleMenu roleMenu=roleMenuList.get(i);
                roleMenu.setCreateTime(new Date().getTime());
                roleMenu.setUpdateTime(new Date().getTime());
                roleMenu.setDelFlag(false);
                roleMenu.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            }
        }
        roleMenuService.setMenu(roleMenuList,roleInfo);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequestMapping("/list")
    public String listMenuIfo(){
        return "common/list_role_info";
    }
    
    @RequestMapping("/data")
    @ResponseBody
    public JsonMessage data(RoleInfo roleInfo,Pagination pagination){
        pagination.setStartIndex((pagination.getCurrentPage()-1)*pagination.getPageSize());
        roleInfo.setPage(pagination);
        List<RoleInfo> roleInfoList=roleInfoService.findByRoleInfo(roleInfo);
        PaginateResult<RoleInfo> result = new PaginateResult<RoleInfo>(roleInfo.getPage(), roleInfoList);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
