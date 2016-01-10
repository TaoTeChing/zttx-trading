/*
 * @(#)DemoController.java 2015-8-10 上午9:16:00
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.utils.JsonMessageUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.Demo;
import com.zttx.web.module.common.service.DemoService;

/**
 * <p>File：DemoController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-10 上午9:16:00</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@RequestMapping("/common/demo")
@Controller
public class DemoController
{
    @Autowired
    private DemoService demoService;
    /**
     * 权限命名规则
     * 模块名(父模块:子模块):操作方法
     * 
     * 
     * @param demo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions("common:demo:add")
    public JsonMessage add(Demo demo){
        demoService.insert(demo);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequestMapping("/modify")
    @ResponseBody
    @RequiresPermissions("common:demo:modify")
    public JsonMessage modify(Demo demo){
        demoService.updateByPrimaryKeySelective(demo);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 
     * @param refrenceId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @RequiresPermissions("common:demo:delete")
    public JsonMessage delete(String refrenceId){
        //这个是逻辑山出
        demoService.delete(refrenceId);
        /*//设个是物理删除
        demoService.deleteByPrimaryKey(refrenceid)*/
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @ResponseBody
    @RequestMapping("/find")
    @RequiresPermissions("common:demo:find")
    public PaginateResult<Demo> find(Demo demo){
        /**
         * demo.setPage(page)来设置分页
         * 或者传入一个分页对象
         * 
         * 如果page对象为空则查询所有
         */
        
        List<Demo> demoList=demoService.findList(demo);
        
        PaginateResult<Demo> result=new PaginateResult<Demo>(demo.getPage(), demoList);
        return result;
    }
    
}
