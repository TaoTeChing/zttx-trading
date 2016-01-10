package com.zttx.web.module.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.MessageHistoryMapper;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.utils.SendSmsClient;

/**
 * 发送用户短信服务
 * Created by 李星 on 2015/8/21.
 */
@Service
public class SmsSendServiceImpl implements SmsSendService
{
    @Autowired
    private UserInfoMapper       userInfoMapper;
    
    @Autowired
    private TextMessageSender    textMessageSender;
    
    @Autowired
    private MessageHistoryMapper messageHistoryMapper;
    
    @Override
    public void sendSmsToUser(String uuid, String content)
    {
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(uuid);
        if (userInfo == null || userInfo.getUserMobile() == null) return;
        SendSmsClient sendSmsClient = new SendSmsClient(messageHistoryMapper, textMessageSender, userInfo.getUserMobile(), content);
        new Thread(sendSmsClient).start();
    }
}
