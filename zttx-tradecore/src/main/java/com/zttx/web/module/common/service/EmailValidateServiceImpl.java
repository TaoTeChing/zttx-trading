/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.entity.EmailValidate;
import com.zttx.web.module.common.mapper.EmailValidateMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.SendMail;
import com.zttx.web.utils.ValidateUtils;

/**
 * 邮件验证 服务实现类
 * <p>File：EmailValidate.java </p>
 * <p>Title: EmailValidate </p>
 * <p>Description:EmailValidate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class EmailValidateServiceImpl extends GenericServiceApiImpl<EmailValidate> implements EmailValidateService
{

    private static final Logger  logger = Logger.getLogger(EmailValidateServiceImpl.class);
    
    @Autowired
    private DataDictValueService dataDictValueService;
    
    @Autowired
    SendMail                     sendMail;
    
    private EmailValidateMapper emailValidateMapper;

    @Autowired(required = true)
    public EmailValidateServiceImpl(EmailValidateMapper emailValidateMapper)
    {
        super(emailValidateMapper);
        this.emailValidateMapper = emailValidateMapper;
    }
    
    @Override
    public void create(EmailValidate emailValidate) throws BusinessException
    {
        if (StringUtils.isEmpty(emailValidate.getEmailAddr()) || !ValidateUtils.isMailFormat(emailValidate.getEmailAddr(), true, 64))
        {
            logger.debug("EmailValidate 's emailAddr is ERROR!");
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        // 查找记录是否存在
        EmailValidate _emailValidate = emailValidateMapper.search(emailValidate.getEmailAddr(), false);
        // 记录存在时
        if (null != _emailValidate)
        {
            if ((CalendarUtils.getCurrentLong() - _emailValidate.getCreateTime()) < _emailValidate.getValidTime())
            {
                logger.debug("EmailValidate 's emailAddr is EXIST!");
                throw new BusinessException(CommonConst.DATA_EXISTS);
            }
        }
        // 记录不存在时
        emailValidate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        emailValidate.setCreateTime(CalendarUtils.getCurrentLong());
        emailValidate.setUpdateTime(emailValidate.getCreateTime());
        emailValidate.setValidTime(ApplicationConst.EMAIL_VALID_TIME.longValue());
        /* emailValidate.setUseState(false); */
        emailValidate.setDelFlag(false);
        DataDictValue ddv = dataDictValueService.findDictValue("emailUrl", "emailUrl_prefix");
        StringBuffer url = new StringBuffer(ddv.getDictValue());
        url.append("/common/emailValidate/verify?refrenceId=");
        url.append(emailValidate.getRefrenceId());
        url.append("&emailAddr=");
        url.append(emailValidate.getEmailAddr());
        logger.debug(url);
        // 邮箱接口对接 发送邮箱
        try
        {
            StringBuffer text = new StringBuffer();
            text.append("邮箱认证<br/>");
            text.append("亲爱的【" + emailValidate.getEmailAddr() + "】!<br/>");
            text.append("您使用了8637品牌超级代理邮箱认证，请点击以下链接完成邮箱验证：<br/>");
            text.append("<a href='" + url + "' target='_blank' style='color:#ed145b;' >" + url + "</a></br>");
            text.append("为了保障您的账号安全，请在24小时内点击该链接，您也可以将连接复制到浏览器地址栏访问。</br>");
            text.append("如果验证链接已经失效，请重发验证邮件！</br>");
            text.append("此为系统邮件，请勿回复！</br>");
            text.append("如有任何疑问，请联系我们：service@8637.com 谢谢！");
            sendMail.initialize();
            sendMail.setTo(emailValidate.getEmailAddr().toString());
            sendMail.setSubject("8637品牌超级代理邮箱认证");
            sendMail.setText("text/html", text.toString());
            Boolean sendState = sendMail.send();
            if (sendState)
            {
                logger.debug("邮件发送成功++++++++++++++++++++++++++");
            }
            else
            {
                logger.debug("邮件发送失败--------------------------");
            }
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
            logger.debug(e);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.debug(e);
        }
        this.emailValidateMapper.insert(emailValidate);
    }
    
    @Override
    public EmailValidate verifyAndUpdate(String refrenceId, String emailAddr) throws BusinessException
    {
        if (StringUtils.isBlank(emailAddr) || StringUtils.isBlank(refrenceId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        EmailValidate _emailValidate = emailValidateMapper.selectByPrimaryKey(refrenceId);
        if (null == _emailValidate || !StringUtils.equals(_emailValidate.getEmailAddr(), emailAddr))
        {
            logger.debug("EmailValidate's verify is not EXIST !");
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        if (true == _emailValidate.getDelFlag())
        {
            logger.debug("EmailValidate's verify is USED !");
            throw new BusinessException(CommonConst.VALID_MAIL_USED);
        }
        if ((CalendarUtils.getCurrentLong() - _emailValidate.getCreateTime()) >= _emailValidate.getValidTime())
        {
            logger.debug("EmailValidate's verify is EXPIRED !");
            throw new BusinessException(CommonConst.VALID_MAIL_EXPIRES);
        }
        this.emailValidateMapper.update(_emailValidate.getRefrenceId(), true, CalendarUtils.getCurrentLong());
        return _emailValidate;
    }
}
