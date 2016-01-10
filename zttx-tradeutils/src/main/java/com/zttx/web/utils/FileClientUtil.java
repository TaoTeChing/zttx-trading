package com.zttx.web.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.JsonMessageUtils;
import com.zttx.web.bean.WeshopAPIConfig;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.ZttxConst;

/**
 * 文件服务器接口
 * <p>File：FileClient.java</p>
 * <p>Title: FileClient</p>
 * <p>Description:FileClient</p>
 * <p>Copyright: Copyright (c) Aug 13, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
public class FileClientUtil
{
    /**
     * Logger for this class
     */
    private static final Logger logger           = Logger.getLogger(FileClientUtil.class);
    
    public static final String  DEFAULT_CHARSET  = "UTF-8";
    
    public static final String  DEFAULT_MIMETYPE = "text/plain";
    
    private static final String MESSAGE_FLAG     = "0XFF";
    
    private static final String MESSAGE_ELAG     = "0X00";
    
    private static final String MESSAGE_SPEC     = ",";
    
    private static final String MESSAGE_LINK     = ":";
    
    private static final String JS_FORMDATA_SPEC = "&";
    
    private static final String JS_FORMDATA_LINK = "=";
    
    /**
     * 调用文件服务器接口
     * @author 陈建平
     * @param params
     * @param uri
     * @param file
     * @param filename
     * @return  {@link JsonMessage}
     * @throws BusinessException
     */
    public static JsonMessage getJsonMessage(Map<String, Object> params, String uri, byte[] file, String field, String filename, String contentType)
            throws BusinessException
    {
        Map<String, String> _params = WeshopAPIConfig.transMapToEncryMap(params, ZttxConst.FILEAPI_USERKEY);
        String result = HttpUtils.postMultipart(ZttxConst.FILEAPI_WEBURL + uri, _params, CharsetConst.CHARSET_UT, field, file, filename, contentType);
        JsonMessage resJson;
        try
        {
            resJson = JSON.parseObject(result, JsonMessage.class);
        }
        catch (JSONException e)
        {
            resJson = null;
        }
        if (null == resJson) throw new BusinessException(CommonConst.FILE_INTERFACE_FAIL);
        if (resJson.getCode().intValue() != CommonConstant.FileClient.SUCCESS) throw new BusinessException(resJson.getCode(), resJson.getMessage(), resJson.getObject());
        return resJson;
    }
    
    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static JsonMessage deleteFile(String filePath)
    {
        HttpUtils.post(ZttxConst.FILEAPI_WEBURL + filePath);
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     *   移动文件，并返回移动后的路径
     * @param type  图片类型
     * @param newURL  完整的url路径
     * @param cateKey   文件类别
     * @return  {@link String}
     * @throws BusinessException
     */
    public static String moveImgFromTemp(String type, String newURL, String cateKey) throws BusinessException
    {
        String url = "/client/moveImg";
        String jsonData = "url=" + url + "&cateKey=" + cateKey + "&type=" + type + "&newURL=" + newURL;
        JsonMessage jsonMessage = getJsonMessage(url, jsonData);
        String newPath = jsonMessage.getObject().toString();
        return FileUtils.formatPath(newPath);
    }
    
    /**
     * 移动文件
     * @author 陈建平
     * @param newDir
     * @param newURL
     * @param cateKey
     * @return
     * @throws BusinessException
     */
    public static String moveAndDeleteFile(String newDir, String newURL, String cateKey) throws BusinessException{
    	String url = "/client/moveFile";
        String jsonData = "url=" + url + "&cateKey=" + cateKey + "&type=" + newDir + "&newURL=" + newURL;
        JsonMessage jsonMessage = getJsonMessage(url, jsonData);
        String newPath = jsonMessage.getObject().toString();
        return FileUtils.formatPath(newPath);
    }

    /**
     * 批量移动文件
     * @param newDir
     * @param newURL
     * @param cateKey
     * @return
     * @throws BusinessException
     */
    public static String[] moveAndDeleteFile(String newDir, String[] newURL, String cateKey) throws BusinessException
    {
        if (ArrayUtils.isEmpty(newURL)) { return null; }
        String[] result = new String[newURL.length];
        for (int i = 0; i < newURL.length; i++)
        {
            result[i] = moveAndDeleteFile(newDir, newURL[i], cateKey);
        }
        return result;
    }
    
    /**
     * 文件拷贝
     * @param request
     * @param path
     * @param cateKey
     * @return   {@link String}
     * @throws BusinessException
     */
    public static String copyFile(HttpServletRequest request, String path, String cateKey) throws BusinessException
    {
        String url = "/client/copyFile";
        String jsonData = "url=" + url + "&cateKey=" + cateKey + "&path=" + path;
        JsonMessage jsonMessage = getJsonMessage(url, jsonData);
        String newPath = jsonMessage.getObject().toString();
        return FileUtils.formatPath(newPath);
    }
    
    /**
     * 调用接口，返回JSONObject（会验证返回结果的状态，为失败时，会抛异常）
     * @param url
     * @param jsFormData
     * @return  {@link JSONObject}
     * @throws BusinessException
     */
    public static JSONObject getVerifiedJsonResult(String url, String jsFormData) throws BusinessException
    {
        String jsonResult = getJsonResult(url, jsFormData);
        JSONObject result = JSON.parseObject(jsonResult);
        verifyResultCode(result);
        return result;
    }
    
    /**
     * 调用接口，返回JSONObject
     * @param url
     * @param jsFormData
     * @return  {@link JSONObject}
     */
    public static JSONObject getJSONObject(String url, String jsFormData)
    {
        String jsonResult = getJsonResult(url, jsFormData);
        JSONObject result = JSON.parseObject(jsonResult);
        return result;
    }
    
    /**
     * 调用接口，返回JSONObject
     * @param url
     * @param jsFormData
     * @return  {@link JsonMessage}
     * @throws BusinessException
     */
    public static JsonMessage getJsonMessage(String url, String jsFormData) throws BusinessException
    {
        String jsonResult = getJsonResult(url, jsFormData);
        JsonMessage resJson;
        try
        {
            resJson = JSON.parseObject(jsonResult, JsonMessage.class);
        }
        catch (JSONException e)
        {
            resJson = null;
        }
        if (null == resJson) throw new BusinessException(CommonConst.FILE_INTERFACE_FAIL);
        if (resJson.getCode().intValue() != CommonConstant.FileClient.SUCCESS) throw new BusinessException(resJson.getCode(), resJson.getMessage(), resJson.getObject());
        return resJson;
    }
    
    /**
     * 调用接口，返回JSONObject
     * @param url
     * @param file
     * @param fileFieldName
     * @param jsFormData
     * @return {@link JSONObject}
     */
    public static JSONObject getJSONObject(String url, File file, String fileFieldName, String jsFormData)
    {
        String jsonResult = getJsonResult(url, file, fileFieldName, jsFormData);
        JSONObject result = JSON.parseObject(jsonResult);
        return result;
    }
    
    /**
     * 调用接口，返回JSONObject
     * @param url
     * @param file
     * @param fileFieldName
     * @param jsFormData
     * @return   {@link JsonMessage}
     * @throws BusinessException
     */
    public static JsonMessage getJSONObject(String url, byte[] file, String fileFieldName, String jsFormData) throws BusinessException
    {
        String jsonResult = getJsonResult(url, file, fileFieldName, jsFormData);
        JsonMessage resJson;
        try
        {
            resJson = JSON.parseObject(jsonResult, JsonMessage.class);
        }
        catch (JSONException e)
        {
            resJson = null;
        }
        if (null == resJson) throw new BusinessException(CommonConst.FILE_INTERFACE_FAIL);
        if (resJson.getCode().intValue() != CommonConstant.FileClient.SUCCESS) throw new BusinessException(resJson.getCode(), resJson.getMessage(), resJson.getObject());
        return resJson;
    }
    
    /**
     * 调用接口，返回JSONObject
     * @param url
     * @param files
     * @param fileFieldName
     * @param jsFormData
     * @param html
     * @return   {@link JSONObject}
     */
    public static JSONObject getJSONObject(String url, File[] files, String fileFieldName, String jsFormData, String html)
    {
        JSONObject result = null;
        try
        {
            String jsonResult = getJsonResult(url, files, fileFieldName, jsFormData, html);
            result = JSON.parseObject(jsonResult);
        }
        catch (Exception e)
        {
            logger.error("接口错误：" + e.getMessage(), e);
        }
        return result;
    }
    
    /**
     * 调用接口，返回JSONObject(接收富文本编辑器代码)
     * @param url
     * @param jsFormData
     * @param html
     * @return  {@link JSONObject}
     */
    public static JSONObject getJSONObjectHtml(String url, String jsFormData, String html)
    {
        String jsonResult = getJsonResultHtml(url, jsFormData, html);
        JSONObject result = JSON.parseObject(jsonResult);
        return result;
    }
    
    /**
     * 调用接口，返回JSONObject(接收富文本编辑器代码)
     * @param url
     * @param jsFormData
     * @param html
     * @return  {@link JSONObject}
     */
    public static JSONObject getJSONObjectHtmlForJson(String url, String jsFormData, String html)
    {
        String jsonResult = getJsonResultByJsonDataHtml(url, jsFormData, html);
        JSONObject result = JSON.parseObject(jsonResult);
        return result;
    }
    
    /**
     * 调用接口，返回 jsonStr
     * @param url
     * @param jsFormData
     * @return {@link String}
     */
    public static String getJsonResult(String url, String jsFormData)
    {
        String jsonData = getJsonData(jsFormData);
        return getJsonResultByJsonData(url, jsonData);
    }
    
    /**
     * 调用接口，返回 jsonStr
     * @param url
     * @param files
     * @param fileFieldName
     * @param jsFormData
     * @param html
     * @return  {@link String}
     */
    public static String getJsonResult(String url, File[] files, String fileFieldName, String jsFormData, String html)
    {
        String jsonData = getJsonData(jsFormData);
        return getJsonResultByJsonData(url, files, fileFieldName, jsonData, html);
    }
    
    /**
     * 调用接口，返回 jsonStr
     * @param url
     * @param file
     * @param fileFieldName
     * @param jsFormData
     * @return  {@link String}
     */
    public static String getJsonResult(String url, File file, String fileFieldName, String jsFormData)
    {
        String jsonData = getJsonData(jsFormData);
        return getJsonResultByJsonData(url, file, fileFieldName, jsonData);
    }
    
    /**
     * 调用接口，返回 jsonStr
     * @param url
     * @param file
     * @param fileFieldName
     * @param jsFormData
     * @return  {@link String}
     * @throws BusinessException
     */
    public static String getJsonResult(String url, byte[] file, String fileFieldName, String jsFormData) throws BusinessException
    {
        String jsonData = getJsonData(jsFormData);
        return getJsonResultByJsonData(url, file, fileFieldName, jsonData);
    }
    
    /**
     * 调用接口，返回 jsonStr
     * @param url
     * @param jsFormData
     * @param html
     * @return  {@link String}
     */
    public static String getJsonResultHtml(String url, String jsFormData, String html)
    {
        String jsonData = getJsonData(jsFormData);
        return getJsonResultByJsonDataHtml(url, jsonData, html);
    }
    
    // =======================================================================================================================
    /**
     * 调用接口（post方式）
     * @param url 接口路径（如："client/advertPosit/search"）
     * @param file
     * @param fileFieldName
     * @param jsonData 请求参数
     * @return String json字符串
     * @author chenjp
     */
    private static String getJsonResultByJsonData(String url, File file, String fileFieldName, String jsonData)
    {
        String jsonResult = null;
        url = ZttxConst.FILEAPI_WEBURL + url;
        int dataLen = StringUtils.length(jsonData);
        String userKey = ZttxConst.FILEAPI_USERKEY;
        String userDes = ParameterUtils.getUserDes(userKey, dataLen);
        Map<String, String> map = Maps.newHashMap();
        map.put(ZttxConst.FILE_PARAM_USER_DES, userDes);
        map.put(ZttxConst.FILE_PARAM_DATA_LEN, Integer.toString(dataLen));
        map.put(ZttxConst.FILE_PARAM_USR_KEY, userKey);
        map.put(ZttxConst.FILE_PARAM_DATA, jsonData);
        HttpClientTool tool = new HttpClientTool();
        jsonResult = tool.doPostStr(url, map, file, fileFieldName);
        logger.info("=========================================================================================================>");
        logger.info(jsonResult);
        logger.info("=========================================================================================================>");
        return jsonResult;
    }
    
    // =======================================================================================================================
    /**
     * 调用接口（post方式）
     * @param url 接口路径（如："client/advertPosit/search"）
     * @param file
     * @param fileFieldName
     * @param jsonData 请求参数
     * @return String json字符串
     * @author chenjp
     * @throws BusinessException 
     */
    private static String getJsonResultByJsonData(String url, byte[] file, String fileFieldName, String jsonData) throws BusinessException
    {
        String jsonResult = null;
        url = ZttxConst.FILEAPI_WEBURL + url;
        int dataLen = StringUtils.length(jsonData);
        String userKey = ZttxConst.FILEAPI_USERKEY;
        String userDes = ParameterUtils.getUserDes(userKey, dataLen);
        Map<String, String> map = Maps.newHashMap();
        map.put(ZttxConst.FILE_PARAM_USER_DES, userDes);
        map.put(ZttxConst.FILE_PARAM_DATA_LEN, Integer.toString(dataLen));
        map.put(ZttxConst.FILE_PARAM_USR_KEY, userKey);
        map.put(ZttxConst.FILE_PARAM_DATA, jsonData);
        HttpClientTool tool = new HttpClientTool();
        jsonResult = tool.doPostStr(url, map, file, fileFieldName);
        logger.info("=========================================================================================================>");
        logger.info(jsonResult);
        logger.info("=========================================================================================================>");
        return jsonResult;
    }
    
    /**
     * 调用接口（post方式）
     * @param url 接口路径（如："client/advertPosit/search"）
     * @param files
     * @param fileFieldName
     * @param jsonData 请求参数
     * @return String json字符串
     * @author chenjp
     */
    private static String getJsonResultByJsonData(String url, File[] files, String fileFieldName, String jsonData, String html)
    {
        String jsonResult = null;
        url = ZttxConst.FILEAPI_WEBURL + url;
        int dataLen = StringUtils.length(jsonData);
        String userKey = ZttxConst.FILEAPI_USERKEY;
        String userDes = ParameterUtils.getUserDes(userKey, dataLen);
        Map<String, String> map = Maps.newHashMap();
        map.put(ZttxConst.FILE_PARAM_USER_DES, userDes);
        map.put(ZttxConst.FILE_PARAM_DATA_LEN, Integer.toString(dataLen));
        map.put(ZttxConst.FILE_PARAM_USR_KEY, userKey);
        map.put(ZttxConst.FILE_PARAM_DATA, jsonData);
        map.put(ZttxConst.FILE_PARAM_HTML, html);
        HttpClientTool tool = new HttpClientTool();
        jsonResult = tool.doPostStr(url, map, files, fileFieldName);
        logger.info("=========================================================================================================>");
        logger.info(jsonResult);
        logger.info("=========================================================================================================>");
        return jsonResult;
    }
    
    /**
     * 调用接口（post方式）
     * @param url 接口路径（如："client/advertPosit/search"）
     * @param jsonData 请求参数
     * @return String json字符串
     */
    private static String getJsonResultByJsonData(String url, String jsonData)
    {
        String jsonResult = null;
        try
        {
            url = ZttxConst.FILEAPI_WEBURL + url;
            int dataLen = StringUtils.length(jsonData);
            String userKey = ZttxConst.FILEAPI_USERKEY;
            String userDes = ParameterUtils.getUserDes(userKey, dataLen);
            Map<String, String> map = Maps.newHashMap();
            map.put(ZttxConst.FILE_PARAM_USER_DES, userDes);
            map.put(ZttxConst.FILE_PARAM_DATA_LEN, Integer.toString(dataLen));
            map.put(ZttxConst.FILE_PARAM_USR_KEY, userKey);
            map.put(ZttxConst.FILE_PARAM_DATA, jsonData);
            HttpClientTool tool = new HttpClientTool();
            jsonResult = tool.doPostStr(url, map);
            logger.info("=========================================================================================================>");
            logger.info(jsonResult);
            logger.info("=========================================================================================================>");
        }
        catch (ParseException e)
        {
            logger.error(e);
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        return jsonResult;
    }
    
    /**
     * 调用接口（post方式）
     * @param url 接口路径
     * @param jsonData 请求参数
     * @return String json字符串
     */
    private static String getJsonResultByJsonDataHtml(String url, String jsonData, String html)
    {
        String jsonResult = null;
        try
        {
            url = ZttxConst.FILEAPI_WEBURL + url;
            int dataLen = StringUtils.length(jsonData);
            String userKey = ZttxConst.FILEAPI_USERKEY;
            String userDes = ParameterUtils.getUserDes(userKey, dataLen);
            Map<String, String> map = Maps.newHashMap();
            map.put(ZttxConst.FILE_PARAM_USER_DES, userDes);
            map.put(ZttxConst.FILE_PARAM_DATA_LEN, Integer.toString(dataLen));
            map.put(ZttxConst.FILE_PARAM_USR_KEY, userKey);
            map.put(ZttxConst.FILE_PARAM_DATA, jsonData);
            map.put(ZttxConst.FILE_PARAM_HTML, html);
            HttpClientTool tool = new HttpClientTool();
            jsonResult = tool.doPostStr(url, map);
            logger.info("=========================================================================================================>");
            logger.info(jsonResult);
            logger.info("=========================================================================================================>");
        }
        catch (ParseException e)
        {
            logger.error(e);
        }
        catch (IOException e)
        {
            logger.error(e);
        }
        return jsonResult;
    }
    
    /**
     * 将对象转换成json字符串（数据会进行消息体转码处理）
     * @param  jsFormData（序列化表单值，所创建的URL编码文本字符串）
     * @return String jsonData
     */
    public static String getJsonData(String jsFormData)
    {
        return getJsonDataFromJsFormData(jsFormData);
    }
    
    /**
     * 接收并解析jsFormData
     * @param jsFormData 序列化表单值，所创建的URL编码文本字符串
     * 		（name=zhougguangn周光暖&age=18&height=18&sex=男）
     * 		name=zhougguangn%E5%91%A8%E5%85%89%E6%9A%96&age=18&height=18&sex=%E7%94%B7
     * @return String jsonData
     * 		name:zhougguangn周光暖,age=18:height=18,sex=男
     */
    private static String getJsonDataFromJsFormData(String jsFormData)
    {
        Map map = getMapFromJsFormData(jsFormData);
        String jsonData = ParameterUtils.getData(map);
        return jsonData;
    }
    
    /**
     * 接收并解析jsFormData
     * @param jsFormData 序列化表单值，所创建的URL编码文本字符串
     * 		（name=zhougguangn周光暖&age=18&height=18&sex=男）
     * 		name=zhougguangn%E5%91%A8%E5%85%89%E6%9A%96&age=18&height=18&sex=%E7%94%B7
     * @return Map<String, String> 
     */
    @SuppressWarnings("deprecation")
    private static Map<String, String> getMapFromJsFormData(String jsFormData)
    {
        jsFormData = replacerDecode(jsFormData);
        Map<String, String> map = Maps.newHashMap();
        if (StringUtils.isNotBlank(jsFormData))
        {
            String[] strings = StringUtils.split(jsFormData, JS_FORMDATA_SPEC);
            if (null != strings && strings.length > 0)
            {
                for (int i = 0; i < strings.length; i++)
                {
                    String[] keyValue = StringUtils.split(strings[i], JS_FORMDATA_LINK);
                    int len = keyValue.length;
                    if (null != keyValue && len > 0)
                    {
                        if (len == 2)
                        {
                            String valueString = StringUtils.trimToEmpty(keyValue[1]);
                            valueString = StringUtils.replace(valueString, MESSAGE_FLAG, MESSAGE_SPEC);
                            valueString = StringUtils.replace(valueString, MESSAGE_ELAG, MESSAGE_LINK);
                            map.put(StringUtils.trimToEmpty(keyValue[0]), valueString);
                        }
                        else if (len == 1)
                        {
                            map.put(StringUtils.trimToEmpty(keyValue[0]), "");
                        }
                    }
                }
            }
        }
        return map;
    }
    
    /**
     * 验证接口调用的状态（失败时抛异常）
     * @param result 接口调用返回的结果
     * @throws BusinessException
     */
    private static void verifyResultCode(JSONObject result) throws BusinessException
    {
        if (!result.get(ZttxConst.CODE).equals(ZttxConst.SUCCESS)) { throw new BusinessException(Integer.parseInt(result.get(ZttxConst.CODE).toString()), result.get(
                ZttxConst.MESSAGE).toString()); }
    }
    
    /**
     * 特殊字符处理
     * @param jsondata
     * @return
     */
    private static String replacerDecode(String jsondata)
    {
        if (StringUtils.isNotBlank(jsondata))
        {
            try
            {
                jsondata = jsondata.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                jsondata = jsondata.replaceAll("\\+", "%2B");
                jsondata = URLDecoder.decode(jsondata, "utf-8");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return jsondata;
    }


    // =====================================================================================================
}
