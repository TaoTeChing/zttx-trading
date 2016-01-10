/*
 * @(#)RPCUtils.java 2014-11-10 下午3:28:59
 * Copyright 2014 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zttx.web.bean.WeshopAPIConfig;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.sdk.exception.BusinessException;

/**
 * <p>File：RPCUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-11-10 下午3:28:59</p>
 * <p>Company: 8637.com</p>
 * @author 张昌苗
 * @version 1.0
 */
public class RPCUtils
{
    /**
     * 获取UUID
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static String getUUID() throws BusinessException
    {
        Map<String, Object> _params = Maps.newHashMap();
        _params.put("platformCode", ZttxConst.IDAPI_PLATFORMCODE);
        Map<String, String> tempParams = WeshopAPIConfig.transMapToEncryMap(_params, ZttxConst.IDAPI_USERKEY);
        String result = HttpUtils.postMultipart(ZttxConst.IDAPI_UUID_WEBURL, tempParams, CharsetConst.CHARSET_UT, null, null, null, null);
        JSONObject resJson = JSONObject.parseObject(result);
        if (resJson == null) { throw new BusinessException("UUID获取失败"); }
        if (!CommonConst.SUCCESS.code.equals(resJson.getInteger("code"))) { throw new BusinessException("UUID获取失败:" + resJson.getInteger("message")); }
        return resJson.getString("object");
    }
    
    /**
     * 获取IdWorker
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static Long getIdWorker() throws BusinessException
    {
        Map<String, Object> _params = Maps.newHashMap();
        _params.put("platformCode", ZttxConst.IDAPI_PLATFORMCODE);
        Map<String, String> tempParams = WeshopAPIConfig.transMapToEncryMap(_params, ZttxConst.IDAPI_USERKEY);
        String result = HttpUtils.postMultipart(ZttxConst.IDAPI_IDWORKER_WEBURL, tempParams, CharsetConst.CHARSET_UT, null, null, null, null);
        JSONObject resJson = JSONObject.parseObject(result);
        if (resJson == null) { throw new BusinessException("IdWorker获取失败"); }
        if (!CommonConst.SUCCESS.code.equals(resJson.getInteger("code"))) { throw new BusinessException("IdWorker获取失败:" + resJson.getInteger("message")); }
        return Long.parseLong(resJson.getString("object"));
    }
    
    /**
     * 获取SequenceId
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static String getSequenceId() throws BusinessException
    {
        return getSequenceId(null, null);
    }
    
    /**
     * 获取SequenceId
     * @param businessName 序列名称
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static String getSequenceId(String businessName) throws BusinessException
    {
        return getSequenceId(businessName, null);
    }
    
    /**
     * 获取SequenceId
     * @param businessName 序列名称
     * @param size  流水号长度
     * @return
     * @throws BusinessException
     * @author 张昌苗
     */
    public static String getSequenceId(String businessName, String size) throws BusinessException
    {
        Map<String, Object> _params = Maps.newHashMap();
        _params.put("businessName", businessName);
        _params.put("size", size);
        _params.put("platformCode", ZttxConst.IDAPI_PLATFORMCODE);
        Map<String, String> tempParams = WeshopAPIConfig.transMapToEncryMap(_params, ZttxConst.IDAPI_USERKEY);
        String result = HttpUtils.postMultipart(ZttxConst.IDAPI_SEQUENCEID_WEBURL, tempParams, CharsetConst.CHARSET_UT, null, null, null, null);
        JSONObject resJson = JSONObject.parseObject(result);
        if (resJson == null) { throw new BusinessException("SequenceId获取失败"); }
        if (!CommonConst.SUCCESS.code.equals(resJson.getInteger("code"))) { throw new BusinessException("SequenceId获取失败:" + resJson.getInteger("message")); }
        return resJson.getString("object");
    }
}
