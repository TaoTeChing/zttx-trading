/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.mapper.BrandInfoMapper;
import com.zttx.web.module.common.entity.SecurityCert;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.SecurityCertMapper;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.MobileMessageTemple;
import com.zttx.web.utils.UserCenterClient;

/**
 * 经销商/品牌商申请更改手机认证 服务实现类
 * <p>File：SecurityCert.java </p>
 * <p>Title: SecurityCert </p>
 * <p>Description:SecurityCert </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SecurityCertServiceImpl extends GenericServiceApiImpl<SecurityCert> implements SecurityCertService
{
    @Autowired
    private UserInfoMapper      userInfoMapper;
    
    @Autowired
    private BrandInfoMapper     brandInfoMapper;
    
    @Autowired
    private TextMessageSender   textMessageSender;
    
    @Autowired
    private MobileMessageTemple mobileMessageTemple;
    
    @Autowired
    private UserCenterClient    userCenterClient;
    
    private SecurityCertMapper  securityCertMapper;
    
    @Autowired(required = true)
    public SecurityCertServiceImpl(SecurityCertMapper securityCertMapper)
    {
        super(securityCertMapper);
        this.securityCertMapper = securityCertMapper;
    }
    
    @Override
    public SecurityCert findSecurityCert(String userId, Short actState)
    {
        return securityCertMapper.findByUserIdAndActState(userId, actState);
    }
    
    @Override
    public SecurityCert save(SecurityCert securityCert, UserPrincipal userPrincipal) throws BusinessException
    {
        // 查询原来的申请是否存在
        SecurityCert oldSecurityCert = this.findSecurityCert(userPrincipal.getRefrenceId(), CommonConstant.SecurityCert.NONE_AUDIT);
        if (null != oldSecurityCert) { throw new BusinessException(CommonConst.DATA_EXISTS); }
        // 查询手机是否存在
        UserInfo otherUserm = userInfoMapper.getByMobile(securityCert.getUserMobile());
        if (null != otherUserm) { throw new BusinessException(CommonConst.MOBILE_EXIST); }
        securityCert.setOldMobile(userPrincipal.getUserMobile());
        securityCert.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        securityCert.setUserIdId(userPrincipal.getRefrenceId());
        securityCert.setUserName(userPrincipal.getUserName());
        securityCert.setUserCate((short) UserAccountConst.DEALER);
        // 是品牌商
        if (userPrincipal.getUserType() == 0)
        {
            securityCert.setUserCate((short) UserAccountConst.BRAND);
            if (null != userPrincipal.getComName())
            {
                securityCert.setUserName(userPrincipal.getComName());
            }
            else
            {
                BrandInfo brandInfo = brandInfoMapper.selectByPrimaryKey(userPrincipal.getRefrenceId());
                if (null != brandInfo) securityCert.setUserName(brandInfo.getComName());
            }
        }
        securityCert.setApplyType(CommonConstant.SecurityCert.APPLY_UPDATE_MOBILE);
        securityCert.setCreateTime(CalendarUtils.getCurrentLong());
        securityCert.setActState(CommonConstant.SecurityCert.NONE_AUDIT);
        securityCert.setUserId(null);
        securityCert.setActionDate(null);
        securityCert.setActionIp(null);
        securityCertMapper.insert(securityCert);
        return securityCert;
    }
    
    @Override
    public PaginateResult<SecurityCert> searchByClient(SecurityCert searchBean, Pagination page)
    {
        searchBean.setPage(page);
        return new PaginateResult<>(searchBean.getPage(), this.securityCertMapper.findList(searchBean));
    }
    
    @Override
    public void updateActState(SecurityCert securityCert) throws BusinessException
    {
        SecurityCert oldSecurityCert = securityCertMapper.selectByPrimaryKey(securityCert.getRefrenceId());
        if (null == oldSecurityCert) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        // 处理申请
        if (CommonConstant.SecurityCert.APPLY_UPDATE_MOBILE == oldSecurityCert.getApplyType())// 更换手机号码
        {
            oldSecurityCert.setActState(securityCert.getActState());
            oldSecurityCert.setActionDate(CalendarUtils.getCurrentLong());
            oldSecurityCert.setUserId(securityCert.getUserId());
            oldSecurityCert.setActionIp(securityCert.getActionIp());
            securityCertMapper.updateByPrimaryKey(oldSecurityCert);
            if (CommonConstant.SecurityCert.PASS_AUDIT == securityCert.getActState())// 审核通过（更改手机号）
            {
                // 查询手机是否存在
                UserInfo otherUserm = userInfoMapper.getByMobile(oldSecurityCert.getUserMobile());
                if (null != otherUserm) { throw new BusinessException(CommonConst.MOBILE_EXIST); }
                userInfoMapper.updateVerifyMobile(oldSecurityCert.getUserIdId(), oldSecurityCert.getUserMobile(), true);
                textMessageSender.sendTextMessage(oldSecurityCert.getUserMobile(),
                        mobileMessageTemple.getMessageContent(MobileMessageTemple.SmsKey.SECURITYCERT_UPDATE_MOBILE, oldSecurityCert.getUserMobile()));
                // 修改用户中心的手机
                userCenterClient.changeMobile(oldSecurityCert.getUserIdId(), oldSecurityCert.getUserMobile());
            }
        }
    }
}
