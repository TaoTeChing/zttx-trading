/*
 * @(#)TextMessage 2014/4/30 15:08
 * Copyright 2014 夏铭, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.bean;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.HttpUtils;
import com.zttx.web.utils.ParameterUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * <p>File：TextMessageSender</p>
 * <p>Title: </p>
 * <p>Description: 短信发送</p>
 * <p>Copyright: Copyright (c) 2014 2014/4/30 15:08</p>
 * <p>Company: 8637.com</p>
 *
 * @author 夏铭
 * @version 1.0
 */
public class TextMessageSender
{
    private static final Logger log = Logger.getLogger(TextMessageSender.class);
    
    private String              smsUrl;
    
    private String              userKey;
    
    private String              userId;
    
    private String              userName;
    
    public void setSmsUrl(String smsUrl)
    {
        this.smsUrl = smsUrl;
    }
    
    public void setUserKey(String userKey)
    {
        this.userKey = userKey;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    /**
     * 发送短信， 调用之前请自行验证手机号码
     * @param mobile 要发送的短信号码
     * @param textMessage 要发送的短信内容
     * @return
     */
    public JsonMessage sendTextMessage(String mobile, String textMessage)
    {
        // System.out.println(text);
        Map<String, String> map = Maps.newHashMap();
        long currentTime = CalendarUtils.getCurrentLong();
        map.put("userId", userId);
        map.put("dealerId", "");
        map.put("brandId", "");
        map.put("msgReciver", mobile);
        map.put("userName", userName);
        map.put("msgText", textMessage);
        map.put("msgCate", "1");
        map.put("sendTime", Long.toString(currentTime));
        String data = ParameterUtils.getDataFromMap(map);
        int dataLen = ValidateUtils.length(data);
        String userDes = ParameterUtils.getUserDes(userKey, dataLen);
        Map<String, String> httpMap = Maps.newHashMap();
        httpMap.put("userDes", userDes);
        httpMap.put("userKey", userKey);
        httpMap.put("dataLen", String.valueOf(dataLen));
        httpMap.put("data", data);
        String result = HttpUtils.post(smsUrl, httpMap, "utf-8");
        // System.out.println(result);
        JsonMessage jsonMessage = null;
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
    
    /**
     * 发送短信， 调用之前请自行验证手机号码
     * @param mobiles 要发送的短信号码列表
     * @param message 要发送的短信内容
     * @return
     */
    public JsonMessage sendTextMessage(List<String> mobiles, String message)
    {
        StringBuilder mobileBuilder = new StringBuilder();
        for (String mobile : mobiles)
            mobileBuilder.append(mobile).append(",");
        // 删除最后一个 "," 号
        if (mobileBuilder.length() > 1) mobileBuilder.deleteCharAt(mobileBuilder.length() - 1);
        return sendTextMessage(mobileBuilder.toString(), message);
    }
    
    /**
     * 异步执行  sendTextMessage(String mobile, String textMessage)
     * @param mobile
     * @param textMessage
     * @return
     */
    @Async
    public JsonMessage asyncSendTextMessage(String mobile, String textMessage)
    {
        log.info("开始发送短信！ in TextMessageSender");
        JsonMessage jsonMessage = this.sendTextMessage(mobile, textMessage);
        log.info("短信发送完毕！in TextMessageSender");
        return jsonMessage;
    }
}
