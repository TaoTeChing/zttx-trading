/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.consts.UserOperationLogConst;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.UserOperationLog;
import com.zttx.web.module.common.mapper.UserOperationLogMapper;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.IPUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户操作日志 服务实现类
 * <p>File：UserOperationLog.java </p>
 * <p>Title: UserOperationLog </p>
 * <p>Description:UserOperationLog </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserOperationLogServiceImpl extends GenericServiceApiImpl<UserOperationLog> implements UserOperationLogService
{
    @Autowired
    DealerInfoService dealerInfoService;
    @Autowired
    BrandInfoService brandInfoService;

    private UserOperationLogMapper userOperationLogMapper;

    @Autowired(required = true)
    public UserOperationLogServiceImpl(UserOperationLogMapper userOperationLogMapper)
    {
        super(userOperationLogMapper);
        this.userOperationLogMapper = userOperationLogMapper;
    }

    /**
     * 添加用户操作日志
     * @author 陈建平
     * @param request
     * @param userOperationLog
     * @throws BusinessException
     */
    @Override
    public void addUserOperationLog(HttpServletRequest request, UserOperationLog userOperationLog)
    {
        if(null != request){
            UserPrincipal userPrincipal = OnLineUserUtils.getPrincipal();
            if(null != userPrincipal){
            	String crm_userId = userPrincipal.getCrm_id();
                String crmUserName = userPrincipal.getCrm_name();
                if (StringUtils.isNotBlank(crm_userId)){
                    userOperationLog.setUserId(crm_userId);
                    userOperationLog.setUserName(crmUserName);
                    userOperationLog.setLoginType((short)1);
                }else{
                    userOperationLog.setLoginType((short) 0);

                    if(userPrincipal.getUserType().equals(UserAccountConst.USERINFO_TYPE_DEALER)){
                        DealerInfo dealerInfo = dealerInfoService.findById(userPrincipal.getRefrenceId());
                        if(null != dealerInfo){
                            userOperationLog.setUserName(dealerInfo.getDealerName());
                        }
                        userOperationLog.setUserId(userPrincipal.getRefrenceId());
                    }else if(userPrincipal.getUserType().equals(UserAccountConst.USERINFO_TYPE_BRAND)){
                        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(userPrincipal.getRefrenceId());
                        if(null != brandInfo){
                            userOperationLog.setUserName(brandInfo.getComName());
                        }
                        userOperationLog.setUserId(userPrincipal.getRefrenceId());
                    }
                }
                userOperationLog.setLoginIP(IPUtil.getIpAddr(request));
            }
        }

        if(StringUtils.isBlank(userOperationLog.getUserId())){
            if(StringUtils.isBlank(userOperationLog.getUserName())){
                userOperationLog.setUserName(UserOperationLogConst.TYPE_USERNAME_TASK);
            }
        }

        userOperationLog.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        userOperationLog.setEventTime(CalendarUtils.getCurrentLong());
        userOperationLogMapper.insert(userOperationLog);
    }
}
