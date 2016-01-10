/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DataDictValueClientController.java </p>
 * <p>Title: DataDictValueClientController </p>
 * <p>Description:数据字典值对外接口控制器</p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/dataDictValue")
public class DataDictValueClientController extends GenericController
{
    private final static Logger  logger = LoggerFactory.getLogger(DataDictValueClientController.class);
    
    @Autowired(required = true)
    private DataDictValueService dataDictValueService;
    
    /**
     * 分页查询列表
     * @param param DataDictValue page
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DataDictValue searchBean = new DataDictValue();
        BeanUtils.populate(searchBean, map);
        PaginateResult<DataDictValue> result = dataDictValueService.searchByClient(page, searchBean);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 查看
     * @param param refrenceId
     * @return JsonMessage
     * @throws Exception
     */
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage view(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DataDictValue dataDictValue = dataDictValueService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == dataDictValue) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, dataDictValue);
    }
    
    /**
     * 新增/修改
     * @param param
     *  refrenceId=null：新增
     *  refrenceId!=null：修改
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DataDictValue dataDictValue = new DataDictValue();
        BeanUtils.populate(dataDictValue, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, dataDictValue))
        {
            dataDictValueService.save(dataDictValue);
        }
        return jsonMessage;
    }
    
    /**
     * 删除
     * @param param refrenceId
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        dataDictValueService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
