package com.zttx.web.utils;

import java.util.Date;

import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.module.common.entity.MessageHistory;
import com.zttx.web.module.common.mapper.MessageHistoryMapper;

/**
 * 异步发送手机短信
 * Created by Stone on 14/12/20.
 */
public class SendSmsClient implements Runnable
{
    /**
     * 短信发送历史
     */
    private MessageHistoryMapper messageHistoryMapper;
    
    /**
     * 消息发送服务
     */
    private TextMessageSender    textMessageSender;
    
    /**
     * 手机号码
     */
    private String               moblePhone;
    
    /**
     * 短信内容
     */
    private String               content;
    
    public SendSmsClient(MessageHistoryMapper messageHistoryService, TextMessageSender textMessageSender, String moblePhone, String content)
    {
        this.messageHistoryMapper = messageHistoryService;
        this.textMessageSender = textMessageSender;
        this.moblePhone = moblePhone;
        this.content = content;
    }
    
    @Override
    public void run()
    {
        // 发送手机短信
        textMessageSender.sendTextMessage(moblePhone, content);
        // 保存发送历史
        MessageHistory messageHistory = new MessageHistory();
        messageHistory.setCreateTime(new Date().getTime());
        messageHistory.setMessage(content);
        messageHistory.setUserMobile(moblePhone);
        messageHistory.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        messageHistoryMapper.insert(messageHistory);
    }
}
