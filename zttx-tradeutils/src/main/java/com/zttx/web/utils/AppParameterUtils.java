/*
 * @(#)AppParameterUtils.java 2014-7-21 下午2:11:00
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PropertiesLoader;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.AppException;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.bean.WeshopAPIConfig;

/**
 * <p>File：AppParameterUtils.java</p>
 * <p>Title: 与APP对接工具类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-7-21 下午2:11:00</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public class AppParameterUtils
{
    private static final Logger     logger           = Logger.getLogger(AppParameterUtils.class);
    
    private static PropertiesLoader propertiesLoader = new PropertiesLoader("weshop.properties");
    
    private static String           WEB_URL;
    
    private static String           ERP_URL;
    static
    {
        WEB_URL = propertiesLoader.getProperty("weshop.weburl");
        ERP_URL = propertiesLoader.getProperty("erpapi.weburl");
    }
    
    /**
    * 提供APP和交易平台对接参数的转换
    * @param params
    * @param url
    * @return
    */
    public static JsonMessage getAppJsonMessage(Map<String, Object> params, String uri)
    {
        Map<String, String> _params = (Map<String, String>) WeshopAPIConfig.transMapToEncryMap(params, WeshopAPIConfig.USER_KEY);
        String result = HttpUtils.postMultipart(WEB_URL + uri, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JsonMessage resJson = JSONObject.parseObject(result, JsonMessage.class);
        if (resJson == null) { throw new AppException("第三方接口对接失败"); }
        return resJson;
    }
    
    /**
     * 提供APP和交易平台对接参数的转换
     * @param params
     * @param url
     * @return
     */
    public static JSONObject getAppJSONObject(Map<String, Object> params, String uri)
    {
        Map<String, String> _params = (Map<String, String>) WeshopAPIConfig.transMapToEncryMap(params, WeshopAPIConfig.USER_KEY);
        String result = HttpUtils.postMultipart(WEB_URL + uri, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JSONObject resJson = JSONObject.parseObject(result);
        if (resJson == null) { throw new AppException("第三方接口对接失败"); }
        return JSONObject.parseObject(result);
    }
    
    /**
     * 提供APP和交易平台对接文件的上传
     * @param params
     * @param url
     * @return
     */
    public static JsonMessage uploadImages(String field, byte[] file, String filename, String uri, String contentType) throws BusinessException
    {
        Map<String, String> _params = (Map<String, String>) WeshopAPIConfig.transMapToEncryMap(new HashMap<String, Object>(), WeshopAPIConfig.USER_KEY);
        String result = HttpUtils.postMultipart(WEB_URL + uri, _params, CharsetConst.CHARSET_UT, field, file, filename, contentType);
        JsonMessage resJson = JSONObject.parseObject(result, JsonMessage.class);
        if (resJson == null) { throw new AppException("Mobile接口对接失败"); }
        return resJson;
    }
    
    /**
     * 从ERP平台对接参数的转换
     * @param params
     * @param url
     * @return
     */
    public static JSONObject getErpTrailJSONObject(Map<String, Object> params) throws BusinessException
    {
        Map<String, String> _params = (Map<String, String>) WeshopAPIConfig.transMapToEncryMap(params, WeshopAPIConfig.ERP_KEY);
        String result = HttpUtils.postMultipart(ERP_URL, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JSONObject resJson = JSONObject.parseObject(result);
        if (resJson == null) { throw new BusinessException("ERP接口对接失败"); }
        return JSONObject.parseObject(result);
    }
    
    /**
     * 从ERP平台对接参数的转换
     * @param params
     * @param url
     * @return
     */
    public static JSONObject getErpJSONObject(Map<String, Object> params, String uri)
    {
        Map<String, String> _params = (Map<String, String>) WeshopAPIConfig.transMapToEncryMap(params, WeshopAPIConfig.ERP_KEY);
        String result = HttpUtils.postMultipart(uri, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JSONObject resJson = JSONObject.parseObject(result);
        if (resJson == null) { throw new AppException("ERP接口对接失败:未正确配置ERP服务的接口地址"); }
        return JSONObject.parseObject(result);
    }
    
    /**
     * 提供WeiShop和交易平台对接参数的转换
     * @param params
     * @param url
     * @return
     */
    public static JsonMessage getWeiShopJsonMessage(Map<String, Object> params, String uri)
    {
        Map<String, String> _params = (Map<String, String>) WeshopAPIConfig.transMapToEncryMap(params, WeshopAPIConfig.USER_KEY);
        String result = HttpUtils.postMultipart(uri, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JsonMessage resJson = null;
        try
        {
            resJson = JSONObject.parseObject(result, JsonMessage.class);
        }
        catch (Exception e)
        {
            logger.error(e);
            e.printStackTrace();
        }
        if (resJson == null)
        {
            // throw new AppException("第三方接口对接失败");
            throw new AppException("操作失败");
        }
        return resJson;
    }
    
    /**
     * 提供WeiShop和交易平台对接参数的转换
     * @param params
     * @param url
     * @return
     */
    public static JSONObject getWeiShopJSONObject(Map<String, Object> params, String uri)
    {
        Map<String, String> _params = (Map<String, String>) WeshopAPIConfig.transMapToEncryMap(params, WeshopAPIConfig.USER_KEY);
        String result = HttpUtils.postMultipart(uri, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JSONObject resJson = JSONObject.parseObject(result);
        if (resJson == null) { throw new AppException("第三方接口对接失败"); }
        return JSONObject.parseObject(result);
    }
}
