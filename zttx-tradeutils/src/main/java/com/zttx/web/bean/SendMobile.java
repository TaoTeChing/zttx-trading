package com.zttx.web.bean;

import java.text.MessageFormat;
import java.util.Map;

import com.zttx.sdk.bean.PropertiesLoader;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.HttpUtils;
import com.zttx.web.utils.ParameterUtils;
import com.zttx.web.utils.ValidateUtils;

public class SendMobile
{
    private static final Logger           log              = Logger.getLogger(SendMobile.class);
    
    private static final PropertiesLoader propertiesLoader = new PropertiesLoader("classpath:sms.properties");
    
    public static JsonMessage sendCode(String userMobile, String code)
    {
        String text = MessageFormat.format(propertiesLoader.getProperty("code_text"), code);
        Map<String, String> map = Maps.newHashMap();
        long currentTime = CalendarUtils.getCurrentLong();
        map.put("userId", propertiesLoader.getProperty("user.id"));
        map.put("dealerId", "");
        map.put("brandId", "");
        map.put("msgReciver", userMobile);
        map.put("userName", propertiesLoader.getProperty("user.name"));
        map.put("msgText", text);
        map.put("msgCate", "1");
        map.put("sendTime", Long.toString(currentTime));
        String data = ParameterUtils.getDataFromMap(map);
        int dataLen = ValidateUtils.length(data);
        String userDes = ParameterUtils.getUserDes(propertiesLoader.getProperty("user.key"), dataLen);
        Map<String, String> httpMap = Maps.newHashMap();
        httpMap.put("userDes", userDes);
        httpMap.put("userKey", propertiesLoader.getProperty("user.key"));
        httpMap.put("dataLen", Integer.toString(dataLen));
        httpMap.put("data", data);
        JsonMessage jsonMessage = null;
        // 加个是否开启短信网关
        if (!Boolean.parseBoolean(propertiesLoader.getProperty("isSend"))) return new JsonMessage(CommonConst.NOSEND);
        String result = HttpUtils.post(propertiesLoader.getProperty("sms.url"), httpMap, "utf-8");
        try
        {
            JSONObject jsonObject = JSON.parseObject(result);
            Integer stateCode = jsonObject.getInteger("code");
            String message = jsonObject.getString("message");
            jsonMessage = new JsonMessage();
            jsonMessage.setCode(stateCode);
            jsonMessage.setMessage(message);
        }
        catch (RuntimeException e)
        {
            log.error(e);
            jsonMessage = new JsonMessage(CommonConst.FAIL);
        }
        return jsonMessage;
    }
    
    public static JsonMessage sendPswd(String userMobile, String pswd)
    {
        String text = MessageFormat.format(propertiesLoader.getProperty("pswd_text"), pswd);
        Map<String, String> map = Maps.newHashMap();
        long currentTime = CalendarUtils.getCurrentLong();
        map.put("userId", propertiesLoader.getProperty("user.id"));
        map.put("dealerId", "");
        map.put("brandId", "");
        map.put("msgReciver", userMobile);
        map.put("userName", propertiesLoader.getProperty("user.name"));
        map.put("msgText", text);
        map.put("msgCate", "1");
        map.put("sendTime", Long.toString(currentTime));
        String data = ParameterUtils.getDataFromMap(map);
        int dataLen = ValidateUtils.length(data);
        String userDes = ParameterUtils.getUserDes(propertiesLoader.getProperty("user.key"), dataLen);
        Map<String, String> httpMap = Maps.newHashMap();
        httpMap.put("userDes", userDes);
        httpMap.put("userKey", propertiesLoader.getProperty("user.key"));
        httpMap.put("dataLen", Integer.toString(dataLen));
        httpMap.put("data", data);
        JsonMessage jsonMessage = null;
        // 加个是否开启短信网关
        if (!Boolean.parseBoolean(propertiesLoader.getProperty("isSend"))) return new JsonMessage(CommonConst.NOSEND);
        String result = HttpUtils.post(propertiesLoader.getProperty("sms.url"), httpMap, "utf-8");
        try
        {
            JSONObject jsonObject = JSON.parseObject(result);
            Integer stateCode = jsonObject.getInteger("code");
            String message = jsonObject.getString("message");
            jsonMessage = new JsonMessage();
            jsonMessage.setCode(stateCode);
            jsonMessage.setMessage(message);
        }
        catch (RuntimeException e)
        {
            log.error(e);
            jsonMessage = new JsonMessage(CommonConst.FAIL);
        }
        return jsonMessage;
    }
}
