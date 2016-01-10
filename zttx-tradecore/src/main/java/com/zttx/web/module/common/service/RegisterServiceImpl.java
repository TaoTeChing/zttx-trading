/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandCrmService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.TelCode;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.RoleInfoMapper;
import com.zttx.web.module.dealer.entity.DealerCount;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerCountService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.security.UsernamePasswordToken;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EncryptUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * Created by txsb on 2015/8/15.
 */
@Service
public class RegisterServiceImpl implements RegisterService
{
    @Autowired
    private UserInfoService    userInfoService;
    
    @Autowired
    private TelCodeService     telCodeService;
    
    @Autowired
    private DealerInfoService  dealerInfoService;
    
    @Autowired
    private DealerCountService dealerCountService;
    
    @Autowired
    private BrandInfoService   brandInfoService;
    
    @Autowired
    private RegionalService    regionalService;
    
    @Autowired
    private BrandCrmService    brandCrmService;
    
    @Autowired
    private RoleInfoMapper     roleInfoMapper;
    
    /**
     * 获取区域可能用节点
     *
     * @param province 省
     * @param city     市
     * @param county   区
     * @return
     * @author 章旭楠
     */
    @Override
    public String getAreaNo(String province, String city, String county)
    {
        String areaNo = province;
        if (!StringUtils.isBlank(city)) areaNo = city;
        if (!StringUtils.isBlank(county)) areaNo = county;
        return areaNo;
    }
    
    /**
     * 品牌商用户 经销商用户 通用属性赋值
     *
     * @param userm
     * @param ipAddr
     * @param ipAddInt
     * @param areaNo
     * @return
     */
    @Override
    public UserInfo buildUserInfo(UserInfo userm, String ipAddr, int ipAddInt, String areaNo)
    {
        userm.setUserSort((short) UserAccountConst.SYSTEM);
        String salt = RandomStringUtils.randomAlphanumeric(6);
        userm.setUserSalt(salt);
        userm.setUserPwd(EncryptUtils.encrypt(userm.getUserPwd() + salt, ApplicationConst.ENCRYPT));
        userm.setRegisterTime(CalendarUtils.getCurrentLong());
        userm.setUserState(UserAccountConst.USER_STAT_OPEN);
        userm.setMobileVerify(true);
        userm.setRegistIpStr(ipAddr);
        userm.setRegisterIp(ipAddInt);
        userm.setAreaNo(StringUtils.isNoneBlank(areaNo) ? Integer.valueOf(areaNo) : null);
        return userm;
    }
    
    /**
     * 保存注册用户
     *
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) throws BusinessException
    {
        userInfo.setRoleId(isDealerUser(userInfo) ? roleInfoMapper.findRefrenceIdByCode(CommonConstant.RoleType.ROLE_DEALER_USER_CODE) : roleInfoMapper
                .findRefrenceIdByCode(CommonConstant.RoleType.ROLE_BRAND_UNOPEN_USER_CODE));
        if (isDealerUser(userInfo))
        {
            // 经销商基本信息save
            createDealerInfo(userInfo);
            createDealerCountInfo(userInfo.getRefrenceId());
            brandCrmService.save(JSON.toJSONString(userInfo), ClientConstant.DEALER_USERM);
        }
        else
        {
            // 品牌商基本信息save
            // createBrandInfo(userInfo);
            brandCrmService.save(JSON.toJSONString(userInfo), ClientConstant.BRAND_USERM);
        }
        userInfoService.insertSelective(userInfo);
    }
    
    private void createDealerCountInfo(String dealerId)
    {
        DealerCount dealerCount = new DealerCount();
        dealerCount.setDealerId(dealerId);
        dealerCount.setCreatetime(CalendarUtils.getCurrentLong());
        dealerCount.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerCount.setDelFlag(false);
        dealerCountService.insert(dealerCount);
    }
    
    private void createBrandInfo(UserInfo userInfo)
    {
        BrandInfo brandInfo = new BrandInfo();
        brandInfo.setRefrenceId(userInfo.getRefrenceId());
        brandInfo.setComName(userInfo.getComName());
        brandInfo.setComType((short) 0);
        brandInfo.setCheckState(BrandConstant.BrandInfo.CHECK_STATE_UNVERFIY);
        brandInfo.setCreateTime(userInfo.getRegisterTime());
        brandInfoService.insertSelective(brandInfo);
    }
    
    private void createDealerInfo(UserInfo userInfo)
    {
        DealerInfo dealerInfo = new DealerInfo();
        dealerInfo.setRefrenceId(userInfo.getRefrenceId());
        dealerInfo.setAreaNo(userInfo.getAreaNo());
        dealerInfo.setProvinceName(userInfo.getProvince());
        dealerInfo.setCityName(userInfo.getCity());
        dealerInfo.setDealerName(userInfo.getUserName());
        dealerInfo.setDealerAddress("");
        dealerInfo.setDomainName("");
        dealerInfo.setShopNum(0);
        dealerInfo.setEmpNum(0);
        dealerInfo.setDealerUser(userInfo.getUserName());
        dealerInfo.setRcvSmsVerify(true);
        String allCityName = regionalService.getFullNameByAreaNo(userInfo.getAreaNo(), ",");
        String[] allCityNameAry = {"", "", ""};
        if (StringUtils.isNotBlank(allCityName))
        {
            String[] strAry = allCityName.split(",");
            for (int i = 0; i < strAry.length; i++)
            {
                allCityNameAry[i] = strAry[i];
            }
        }
        dealerInfo.setProvinceName(allCityNameAry[0]);
        dealerInfo.setCityName(allCityNameAry[1]);
        dealerInfo.setAreaName(allCityNameAry[2]);
        dealerInfo.setAreaNo(userInfo.getAreaNo());
        dealerInfo.setCreateTime(userInfo.getRegisterTime());
        dealerInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_SUCCESS);
        dealerInfoService.insertSelective(dealerInfo);
    }
    
    /**
     * 验证手机号
     *
     * @param user
     * @throws BusinessException
     */
    @Override
    public void phoneVerify(UserInfo user) throws BusinessException
    {
        String userMobile = user.getUserMobile();
        if (!ValidateUtils.isMobileFormat(userMobile, true, ValidateUtils.MOBILE_LENGTH)) { throw new BusinessException(CommonConst.MOBILE_FORMAT_ERR); }
        // 判断该手机是否已经注册
        if (isRegisted(userMobile)) { throw new BusinessException(CommonConst.MOBILE_EXIST_TOLOGIN); }
    }
    
    @Override
    public void modifyStateUsed(String userMobile, String dealPlatform)
    {
        telCodeService.modifyStateUsed(userMobile, dealPlatform);
    }
    
    @Override
    public Integer verifyAndCheck(String userMobile, String dealPlatform, String verifyCode)
    {
        return telCodeService.verifyAndCheck(userMobile, dealPlatform, verifyCode);
    }
    
    @Override
    public void createTelCode(String userMobile, int ipAddr) throws BusinessException
    {
        // 生成验证码
        TelCode telCode = new TelCode();
        telCode.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        telCode.setCusType(ClientConst.CUS_TYPE_TRADE);
        telCode.setVerifyType(TelVerifyTypeConst.DEAL_PLATFORM);
        telCode.setUserMobile(userMobile);
        telCode.setCreateIp(ipAddr);
        telCode.setCreateTime(CalendarUtils.getCurrentLong());
        telCode.setValidTime(ApplicationConst.CODE_VALID);
        telCode.setVerifyCode(SerialnoUtils.buildRandomCode());
        telCode.setUseState(false);
        Integer result = telCodeService.create(telCode);
        if (result != ExceptionConst.SUCCESS) { throw new BusinessException(CommonConst.VALID_CREATE_ERR); }
    }
    
    @Override
    public void tryLogin(UserInfo userInfo)
    {
        AuthenticationToken token = new UsernamePasswordToken(userInfo.getUserMobile(), userInfo.getUserPwd().toCharArray(), true, "", "");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
    }
    
    @Override
    public boolean isRegisted(String userMobile)
    {
        UserInfo user = userInfoService.getByMobile(userMobile);
        return null != user;
    }
    
    private boolean isDealerUser(UserInfo userInfo)
    {
        return UserAccountConst.USERINFO_TYPE_DEALER == userInfo.getUserType() ? true : false;
    }
}
