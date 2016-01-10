/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sun.net.util.IPAddressUtil;

import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.SendMobile;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.module.common.entity.TelCode;
import com.zttx.web.module.common.mapper.TelCodeMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.NetworkUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * 手机验证码 服务实现类
 * <p>File：TelCode.java </p>
 * <p>Title: TelCode </p>
 * <p>Description:TelCode </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class TelCodeServiceImpl extends GenericServiceApiImpl<TelCode> implements TelCodeService
{
    private static final Logger logger = LoggerFactory.getLogger(TelCodeServiceImpl.class);
    
    private TelCodeMapper       telCodeMapper;
    
    @Autowired(required = true)
    public TelCodeServiceImpl(TelCodeMapper telCodeMapper)
    {
        super(telCodeMapper);
        this.telCodeMapper = telCodeMapper;
    }
    
    @Override
    public Integer create(TelCode telCode)
    {
        if (StringUtils.isEmpty(telCode.getUserMobile()) || !ValidateUtils.isMobileFormat(telCode.getUserMobile(), true, ValidateUtils.MOBILE_LENGTH))
        {
            logger.debug("TelCode 's UserMobile is ERROR!");
            return ExceptionConst.FAILURE;
        }
        // 查找记录是否存在
        TelCode _telCode = telCodeMapper.search(telCode.getUserMobile(), telCode.getVerifyType(), false);
        // 记录存在时
        if (null != _telCode)
        {
            if ((CalendarUtils.getCurrentLong() - _telCode.getCreateTime()) < ApplicationConst.CODE_VALID_CREATE)
            {
                logger.debug("TelCode 's VerifyCode is EXIST!");
                return ExceptionConst.EXISTES;
            }
        }
        // TODO: 记录不存or超过60s时效,进行新增处理
        telCode.setRefrenceId(StringUtils.isEmpty(telCode.getRefrenceId()) ? SerialnoUtils.buildPrimaryKey() : telCode.getRefrenceId());
        // TODO: 记录不存or超过60s时效,进行新增业务处理
        telCode.setRefrenceId(StringUtils.isEmpty(telCode.getRefrenceId()) ? SerialnoUtils.buildPrimaryKey() : telCode.getRefrenceId());
        telCode.setCreateTime(CalendarUtils.getCurrentLong());
        telCode.setValidTime(ApplicationConst.CODE_VALID);
        // 将IP读取移动到,项目业务层
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != servletRequestAttributes && null != servletRequestAttributes.getRequest())
        {
            telCode.setCreateIp(getIpAddr(servletRequestAttributes.getRequest()));
        }
        else
        {
            telCode.setCreateIp(-1);
        }
        telCode.setVerifyCode(SerialnoUtils.buildRandomCode());
        telCode.setUseState(false);
        final TelCode _constTelCode = telCode;
        // TODO: 短信接口对接
        new Thread()
        {
            public void run()
            {
                logger.debug("手机验证码短信:Mobile【" + _constTelCode.getUserMobile() + "】 sendCode【" + _constTelCode.getVerifyCode() + "】");
                SendMobile.sendCode(_constTelCode.getUserMobile(), _constTelCode.getVerifyCode());
            }
        }.start();
        telCodeMapper.insert(telCode);
        _telCode = telCode;
        if (_telCode == null)
        {
            logger.debug("TelCode insert ERROR!");
            return ExceptionConst.FAILURE;
        }
        return ExceptionConst.SUCCESS;
    }
    
    private int getIpAddr(HttpServletRequest request)
    {
        int ipInt = 0;
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isNotBlank(ip))
        {
            if (IPAddressUtil.isIPv4LiteralAddress(ip))
            {
                ipInt = NetworkUtils.ipToInt(ip);
            }
            else
            {
                String[] string = ip.split(",");
                int iLen = string.length;
                if (iLen > 0) ip = StringUtils.trimToEmpty(string[iLen - 1]);
                if (IPAddressUtil.isIPv4LiteralAddress(ip))
                {
                    ipInt = NetworkUtils.ipToInt(ip);
                }
            }
        }
        return ipInt;
    }
    
    @Override
    public Integer verifyAndCheck(String userMobile, String verifyType, String verifyCode)
    {
        TelCode _telCode = telCodeMapper.search(userMobile, verifyType, null);
        if (null == _telCode)
        {
            logger.debug("TelCode is Not EXIST!");
            return ExceptionConst.NOEXITS;
        }
        if (true == _telCode.getUseState())
        {
            logger.debug("TelCode's verifyCode is USED !");
            return ExceptionConst.EXPIRES;
        }
        if ((CalendarUtils.getCurrentLong() - _telCode.getCreateTime()) >= ApplicationConst.CODE_VALID)
        {
            logger.debug("TelCode's verifyCode is EXPIRED !");
            return ExceptionConst.EXPIRES;
        }
        if (StringUtils.isNotEmpty(verifyCode) && StringUtils.equals(_telCode.getVerifyCode(), verifyCode)) { return ExceptionConst.SUCCESS; }
        return ExceptionConst.FAILURE;
    }
    
    @Override
    public Integer modifyStateUsed(String userMobile, String verifyType)
    {
        TelCode _telCode = telCodeMapper.search(userMobile, verifyType, null);
        if (null == _telCode)
        {
            logger.debug("TelCode is Not EXIST!");
            return ExceptionConst.NOEXITS;
        }
        telCodeMapper.updateUseState(_telCode.getRefrenceId(), true, CalendarUtils.getCurrentLong());
        return ExceptionConst.SUCCESS;
    }
    
    @Override
    public TelCode search(String userMobile, String verifyType, Boolean useState)
    {
        return telCodeMapper.search(userMobile, verifyType, useState);
    }
    
    @Override
    public Integer verifyAndUpdate(String userMobile, String verifyType, String verifyCode)
    {
        if (StringUtils.isBlank(userMobile) || StringUtils.isBlank(verifyType) || StringUtils.isBlank(verifyCode)) { return ExceptionConst.NULLPOT; }
        TelCode _telCode = telCodeMapper.search(userMobile, verifyType, null);
        if (null == _telCode)
        {
            logger.debug("TelCode is Not EXIST!");
            return ExceptionConst.NOEXITS;
        }
        if (true == _telCode.getUseState())
        {
            logger.debug("TelCode's verifyCode is USED !");
            return ExceptionConst.EXPIRES;
        }
        if ((CalendarUtils.getCurrentLong() - _telCode.getCreateTime()) >= ApplicationConst.CODE_VALID)
        {
            logger.debug("TelCode's verifyCode is EXPIRED !");
            return ExceptionConst.EXPIRES;
        }
        if (StringUtils.isNotEmpty(verifyCode) && StringUtils.equals(_telCode.getVerifyCode(), verifyCode))
        {
            telCodeMapper.updateUseState(_telCode.getRefrenceId(), true, CalendarUtils.getCurrentLong());
            return ExceptionConst.SUCCESS;
        }
        return ExceptionConst.FAILURE;
    }
}
