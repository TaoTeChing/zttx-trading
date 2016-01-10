/*
 * @(#)JsonMessageUtil 2014/5/7 15:15
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.util.List;

import com.zttx.sdk.bean.EnumDescribable;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;

/**
 * <p>File：JsonMessageUtil</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014/5/7 15:15</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public abstract class JsonMessageUtil
{
    /**
     * 分页查询记录集：API接口返回分页及记录集的JSON处理
     * @param code 异常码
     * @param message 异常码描述
     * @param result 结果集及Pagination对象
     * @return JsonMessage JsonMessage
     */
    public static <T extends Object> JsonMessage getJsonMessage(Integer code, String message, PaginateResult<T> result)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(code);
        jsonMessage.setMessage(message);
        jsonMessage.setRows(result.getList());
        jsonMessage.setTotal(result.getPage().getTotalCount());
        return jsonMessage;
    }
    
    /**
     * 分页查询记录集：API接口返回分页及记录集的JSON处理，不返回状态码描述
     * @param code 异常码
     * @param result 结果集及Pagination对象
     * @return JsonMessage JsonMessage
     */
    public static <T extends Object> JsonMessage getJsonMessage(Integer code, PaginateResult<T> result)
    {
        Pagination pagination = result.getPage();
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(code);
        jsonMessage.setRows(result.getList());
        jsonMessage.setTotal(result.getPage().getTotalCount());
        jsonMessage.setHasNext(pagination.getHasNextPage());
        jsonMessage.setHasPrevious(pagination.getHasPreviousPage());
        jsonMessage.setCurrentPage(pagination.getCurrentPage());
        jsonMessage.setTotalPage(pagination.getTotalPage());
        return jsonMessage;
    }
    
    /**
     * 分页查询记录集：API接口返回分页及记录集的JSON处理，不返回状态码描述
     * @param errorCode 异常码
     * @param result 结果集及Pagination对象
     * @return JsonMessage JsonMessage
     */
    public static <T extends Object> JsonMessage getJsonMessage(EnumDescribable errorCode, PaginateResult<T> result)
    {
        Pagination pagination = result.getPage();
        JsonMessage jsonMessage = getJsonMessage(errorCode);
        jsonMessage.setRows(result.getList());
        jsonMessage.setTotal(result.getPage().getTotalCount());
        jsonMessage.setHasNext(pagination.getHasNextPage());
        jsonMessage.setHasPrevious(pagination.getHasPreviousPage());
        jsonMessage.setCurrentPage(pagination.getCurrentPage());
        jsonMessage.setTotalPage(pagination.getTotalPage());
        return jsonMessage;
    }
    
    /**
     * 该方法不再推荐使用，请使用{@link com.zttx.web.core.GenericController#getJsonMessage(EnumDescribable)} getJsonMessage(EnumDescribable describable) 方法代替
     * 接口处理结果反馈：API接口返回数据或交易处理结果的JSON处理
     * @param code 异常码
     * @param message 异常描述
     * @return JsonMessage JsonMessage
     */
    @Deprecated
    public static JsonMessage getJsonMessage(Integer code, String message)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(code);
        jsonMessage.setMessage(message);
        return jsonMessage;
    }
    
    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     * @param describable 异常代码描述
     * @return JsonMessage JsonMessage
     * @author 夏铭
     */
    public static JsonMessage getJsonMessage(EnumDescribable describable)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        return jsonMessage;
    }
    
    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     * @param describable 异常代码描述
     * @param object 单结果返回对象
     * @return
     * @author 夏铭
     */
    public static JsonMessage getJsonMessage(EnumDescribable describable, Object object)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        jsonMessage.setObject(object);
        return jsonMessage;
    }
    
    public static JsonMessage getJsonMessage(EnumDescribable describable, List<?> list)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        jsonMessage.setRows(list);
        return jsonMessage;
    }
    
    /**
     * 单对象持有反馈：API接口返回处理结果及对象数据的JSON处理
     * @param code 异常码
     * @param message 异常集描述
     * @param object Objecct
     * @return JsonMessage JsonMessage
     */
    public static JsonMessage getJsonMessage(Integer code, String message, Object object)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(code);
        jsonMessage.setMessage(message);
        jsonMessage.setObject(object);
        return jsonMessage;
    }
    
    /**
     * 仅返回处理状态码（主要用于平台内部使用）
     * @param code 状态码
     * @return JsonMessage JsonMessage
     */
    public static JsonMessage getJsonMessage(Integer code)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(code);
        return jsonMessage;
    }
    
    /**
     * 单对象持有反馈，不返回状态码描述
     * @param code 状态码
     * @param object 持有的对象
     * @return JsonMessage JsonMessage
     */
    public static JsonMessage getJsonMessage(Integer code, Object object)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(code);
        jsonMessage.setObject(object);
        return jsonMessage;
    }
}
