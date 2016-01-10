package com.zttx.web.module.common.service;

/**
 * 发送用户短信服务
 * Created by 李星 on 2015/8/21.
 */
public interface SmsSendService
{
    /**
     * 发送手机短信给用户
     * @param uuid
     * @param content
     */
    void sendSmsToUser(String uuid, String content);
}
