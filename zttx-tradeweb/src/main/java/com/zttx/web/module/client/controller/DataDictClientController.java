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
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.DataDict;
import com.zttx.web.module.common.service.DataDictService;
import com.zttx.web.module.common.service.DataDictValueService;
import com.zttx.web.utils.ParameterUtils;

/**
 * <p>File：DataDictClientController.java </p>
 * <p>Title: DataDictClientController </p>
 * <p>Description:数据字典对外接口控制器 </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping(ApplicationConst.CLIENT + "/dataDict")
public class DataDictClientController extends GenericController
{
    private final static Logger  logger = LoggerFactory.getLogger(DataDictClientController.class);
    
    @Autowired(required = true)
    private DataDictService      dataDictService;
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    /**
     * 列表
     * @param param
     * @return
     * @throws BusinessException
     * @author 章旭楠
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public JsonMessage search(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Pagination page = new Pagination(MapUtils.getIntValue(map, "currentPage"), MapUtils.getIntValue(map, "pageSize"));
        DataDict searchBean = new DataDict();
        BeanUtils.populate(searchBean, map);
        PaginateResult<DataDict> result = dataDictService.searchByClient(page, searchBean);
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
        DataDict dataDict = dataDictService.selectByPrimaryKey(MapUtils.getString(map, "refrenceId"));
        if (null == dataDict) { return super.getJsonMessage(ClientConst.DBERROR); }
        return super.getJsonMessage(CommonConst.SUCCESS, dataDict);
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
        DataDict dataDict = new DataDict();
        BeanUtils.populate(dataDict, map);
        JsonMessage jsonMessage = super.getJsonMessage(ClientConst.SUCCESS);
        if (beanValidator(jsonMessage, dataDict))
        {
            dataDictService.save(dataDict);
        }
        return jsonMessage;
    }
    
    /**
     * 是否存在字典值
     * @param param refrenceId
     * @return JsonMessage
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/hasDictValue", method = RequestMethod.POST)
    public JsonMessage hasDictValue(ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Boolean hasDictValue = dataDictValueService.hasDictValue(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS, hasDictValue);
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
        dataDictService.delete(MapUtils.getString(map, "refrenceId"));
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
