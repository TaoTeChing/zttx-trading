/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.utils.JedisUtils;
import com.zttx.web.security.shiro.session.RedisSessionDAO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zttx.pay.remoting.api.PayUserApi;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.SendMobile;
import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.ClientConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.brand.entity.BrandCatelog;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandCount;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.mapper.BrandCatelogMapper;
import com.zttx.web.module.brand.model.BrandInfoModel;
import com.zttx.web.module.brand.service.BrandContactService;
import com.zttx.web.module.brand.service.BrandCountService;
import com.zttx.web.module.brand.service.BrandCrmService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.CenterUser;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.MessageHistoryMapper;
import com.zttx.web.module.common.mapper.RoleInfoMapper;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EncryptUtils;
import com.zttx.web.utils.NetworkUtils;
import com.zttx.web.utils.SendSmsClient;
import com.zttx.web.utils.UserCenterClient;
import com.zttx.web.utils.ValidateUtils;
import redis.clients.jedis.ShardedJedis;

/**
 * 用户信息 服务实现类
 * <p>File：UserInfo.java </p>
 * <p>Title: UserInfo </p>
 * <p>Description:UserInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserInfoServiceImpl extends GenericServiceApiImpl<UserInfo> implements UserInfoService
{
    private static final Logger  logger          = Logger.getLogger(UserInfoServiceImpl.class);
    
    private static int           PASSWORD_LENGTH = 6;
    
    @Autowired
    private BrandCatelogMapper   brandCatelogMapper;
    
    @Autowired
    private BrandCrmService      brandCrmService;
    
    @Autowired
    private DealerInfoService    dealerInfoService;
    
    @Autowired
    private BrandCountService    brandCountService;
    
    @Autowired
    private BrandContactService  brandContactService;
    
    @Autowired
    private BrandInfoService     brandInfoService;
    
    @Autowired
    private PayUserApi           payUserApi;
    
    @Autowired
    private UserCenterClient     userCenterClient;
    
    @Autowired
    private RegionalService      regionalService;
    
    @Autowired
    private MessageHistoryMapper messageHistoryMapper;
    
    @Autowired
    private TextMessageSender    textMessageSender;
    
    @Autowired
    private RoleInfoMapper       roleInfoMapper;
    
    // @Autowired
    // private UserCenterClient userCenterClient;
    private UserInfoMapper       userInfoMapper;
    
    @Autowired(required = true)
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper)
    {
        super(userInfoMapper);
        this.userInfoMapper = userInfoMapper;
    }
    
    @Override
    public UserInfo loginVerifi(String userMobile)
    {
        return userInfoMapper.loginVerifi(userMobile);
    }
    
    @Override
    public UserInfo getByMobile(String mobile)
    {
        return userInfoMapper.getByMobile(mobile);
    }
    
    @Override
    public void updateVerifyMobile(String uuid, String userMobile, Boolean state) throws BusinessException
    {
        if (StringUtils.isBlank(uuid) || state == null) { throw new BusinessException(CommonConst.PARAM_NULL); }
        if (userInfoMapper.updateVerifyMobile(uuid, userMobile, state) == 0) { throw new BusinessException(CommonConst.FAIL); }
        // 修改用户中心的手机
        try
        {
            userCenterClient.changeMobile(uuid, userMobile);
        }
        catch (BusinessException e)
        {
            throw new BusinessException("用户中心访问异常:" + e.getLocalizedMessage());
        }
    }
    
    @Override
    public void updateVerifyMail(String uuid, String userMail, Boolean state) throws BusinessException
    {
        if (StringUtils.isBlank(uuid) || state == null) { throw new BusinessException(CommonConst.PARAM_NULL); }
        if (userInfoMapper.updateVerifyMail(uuid, userMail, state) == 0) { throw new BusinessException(CommonConst.FAIL); }
        try
        {
            // 修改用户中心的邮箱
            CenterUser centerUser = userCenterClient.getUser(uuid);
            centerUser.setEmail(state ? userMail : null);
            userCenterClient.update(centerUser);
        }
        catch (Exception e)
        {
            throw new BusinessException("用户中心访问异常:" + e.getLocalizedMessage());
        }
    }
    
    @Override
    public Long findPayUserId(String userId)
    {
        UserInfo userinfo = selectByPrimaryKey(userId);
        return null == userinfo ? null : userinfo.getPayUserId();
    }
    
    @Override
    public Long executeFindPayUserId(String userId) throws BusinessException
    {
        Long payUserId = findPayUserId(userId);
        if (null != payUserId) { return payUserId; }
        try
        {
            payUserId = payUserApi.loadPayUserIdByCenterUser(userId);
        }
        catch (Exception e)
        {
            logger.error("调用支付系统接口失败：", e);
            throw new BusinessException(CommonConst.ORDER_PAY_INTERFACE_FAIL);
        }
        if (payUserId != null)
        {
            UserInfo user = selectByPrimaryKey(userId);
            user.setPayUserId(payUserId);
            userInfoMapper.updateByPrimaryKey(user);
            return payUserId;
        }
        throw new BusinessException("查找结算平台用户失败");
    }
    
    @Override
    public UserInfo getParentUserByChildId(String childId)
    {
        return userInfoMapper.getParentUserByChildId(childId);
    }
    
    @Override
    public UserInfo selectUserInfoMore(String refrenceId, short userType)
    {
        if (null != refrenceId) { return userInfoMapper.selectUserInfoMore(refrenceId, userType); }
        return null;
    }
    
    /**
     * 注册品牌商账户
     *
     * @param userInfo
     * @param brandInfo
     * @param brandContact
     * @param dealNoList
     * @return
     * @throws BusinessException
     * @author 陈建平
     */
    @Override
    public String addBrandAccount(UserInfo userInfo, BrandInfo brandInfo, BrandContact brandContact, String[] dealNoList) throws BusinessException
    {
        UserInfo _brandBrandUserm = userInfoMapper.getByMobile(userInfo.getUserMobile());
        // 修改
        if (_brandBrandUserm != null) { return updateBrandAccount(userInfo, brandInfo, brandContact, dealNoList); }
        // 新增
        String _userPwd = userInfo.getUserPwd();
        if (StringUtils.isEmpty(_userPwd))
        {
            _userPwd = RandomStringUtils.randomNumeric(PASSWORD_LENGTH);
            userInfo.setUserPwd(_userPwd);
        }
        userInfo.setRefrenceId(userCenterClient.registerOrUpdate(userInfo));
        userInfo.setUserSalt(RandomStringUtils.randomAlphanumeric(6));
        userInfo.setUserPwd(EncryptUtils.encrypt(userInfo.getUserPwd() + userInfo.getUserSalt(), ApplicationConst.ENCRYPT));
        Long registerTime = CalendarUtils.getCurrentLong();
        userInfo.setRegisterTime(registerTime);
        userInfo.setUserSort((short) UserAccountConst.BRAND);
        userInfo.setUserType(BrandConstant.userType.BRAND_TYPE);
        userInfo.setRoleId(roleInfoMapper.findRefrenceIdByCode(CommonConstant.RoleType.ROLE_BRAND_USER_CODE));
        insert(userInfo);
        brandCrmService.save(JSON.toJSONString(userInfo), ClientConstant.CLIENT_INFO);
        brandInfo.setRefrenceId(userInfo.getRefrenceId());
        brandInfo.setCreateTime(CalendarUtils.getCurrentLong());
        brandInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_SUCCESS);
        String regionname = regionalService.getFullNameByAreaNoAndLevel(brandInfo.getAreaNo(), 3, ",");
        String[] regionnames = regionname.split(",");
        brandInfo.setProvinceName(regionnames[0]);
        brandInfo.setCityName(regionnames[1]);
        if (regionnames.length > 2)
        {
            brandInfo.setAreaName(regionnames[2]);
        }
        else
        {
            brandInfo.setAreaName("");
        }
        brandInfo.setBrandPhoto(brandInfo.getBrandPhoto());
        brandInfo.setBrandImage(brandInfo.getBrandImage());
        brandInfo.setDomainName(NetworkUtils.getDoMainName());
        // brandInfo.setRegMoney(null == brandInfo.getRegMoney() ? 0 : brandInfo.getRegMoney());
        brandInfoService.insert(brandInfo);
        List<Integer> dealNoList1 = Lists.newArrayList();
        if (dealNoList != null)
        {
            for (String dealNo : dealNoList)
            {
                if (StringUtils.isNotBlank(dealNo))
                {
                    // 品类
                    dealNoList1.add(Integer.parseInt(dealNo));
                    BrandCatelog brandCatelog = new BrandCatelog();
                    brandCatelog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandCatelog.setDealNo(Integer.parseInt(dealNo));
                    brandCatelog.setBrandId(userInfo.getRefrenceId());
                    brandCatelog.setCreateTime(registerTime);
                    brandCatelog.setDelFlag(false);
                    brandCatelogMapper.insert(brandCatelog);
                }
            }
        }
        BrandInfoModel brandInfoModel = new BrandInfoModel();
        try
        {
            BeanUtils.copyProperties(brandInfoModel, brandInfo);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        brandInfoModel.setDealNo(dealNoList1);
        brandCrmService.save(JSON.toJSONString(brandInfoModel), ClientConstant.CLIENT_INFO_2);
        // 新增联系人信息
        brandContact.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandContact.setBrandId(userInfo.getRefrenceId());
        brandContact.setCreateTime(registerTime);
        brandContactService.insert(brandContact);
        if (null == brandCountService.selectByPrimaryKey(userInfo.getRefrenceId()))
        {
            BrandCount brandCount = new BrandCount();
            brandCount.setRefrenceId(userInfo.getRefrenceId());
            brandCountService.insert(brandCount);
        }
        // 发送提醒短信
        final String _userMobile = userInfo.getUserMobile();
        final String _userPswd = _userPwd;
        new Thread()
        {
            public void run()
            {
                SendMobile.sendPswd(_userMobile, _userPswd);
            }
        }.start();
        return userInfo.getRefrenceId();
    }
    
    /**
     * @param userInfo
     * @param brandInfo
     * @param brandContact
     * @param dealNoList
     * @return
     * @author 李飞欧
     */
    private String updateBrandAccount(UserInfo userInfo, BrandInfo brandInfo, BrandContact brandContact, String[] dealNoList)
    {
        // 修改用户注册信息
        UserInfo _brandBrandUserm = userInfoMapper.getByMobile(userInfo.getUserMobile());
        userInfo.setRefrenceId(_brandBrandUserm.getRefrenceId());
        _brandBrandUserm.setUserName(StringUtils.isBlank(userInfo.getUserName()) ? _brandBrandUserm.getUserName() : userInfo.getUserName());
        _brandBrandUserm.setUserMail(StringUtils.isBlank(userInfo.getUserMail()) ? _brandBrandUserm.getUserMail() : userInfo.getUserMail());
        // UserInfo brandUsermnew =
        userInfoMapper.updateByPrimaryKey(_brandBrandUserm);
        Long registerTime = CalendarUtils.getCurrentLong();
        // 做记录
        brandCrmService.save(JSON.toJSONString(_brandBrandUserm), ClientConstant.CLIENT_INFO);
        // 修改品牌商信息
        BrandInfo branInfonew = new BrandInfo();
        BrandInfo info = brandInfoService.selectByPrimaryKey(userInfo.getRefrenceId());
        // brandInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_SUCCESS);
        String regionname = regionalService.getFullNameByAreaNoAndLevel(brandInfo.getAreaNo(), 3, ",");
        String[] regionnames = regionname.split(",");
        if (null == info)
        {
            brandInfo.setRefrenceId(userInfo.getRefrenceId());
            brandInfo.setCreateTime(CalendarUtils.getCurrentLong());
            brandInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_SUCCESS);
            brandInfo.setProvinceName(regionnames[0]);
            brandInfo.setCityName(regionnames[1]);
            brandInfo.setAreaName(regionnames[2]);
            brandInfo.setBrandPhoto(brandInfo.getBrandPhoto());
            brandInfo.setBrandImage(brandInfo.getBrandImage());
            brandInfo.setDomainName(NetworkUtils.getDoMainName());
            // brandInfo.setRegMoney(null == brandInfo.getRegMoney() ? 0 : brandInfo.getRegMoney());
            // branInfonew =
            brandInfoService.insert(brandInfo);
        }
        else
        {
            info.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_SUCCESS);
            info.setProvinceName(StringUtils.isBlank(regionnames[0]) ? info.getProvinceName() : regionnames[0]);
            info.setCityName(StringUtils.isBlank(regionnames[1]) ? info.getCityName() : regionnames[1]);
            info.setAreaName(StringUtils.isBlank(regionnames[2]) ? info.getAreaName() : regionnames[2]);
            info.setComAddress(brandInfo.getComAddress());
            info.setBrandPhoto(StringUtils.isBlank(brandInfo.getBrandPhoto()) ? info.getBrandPhoto() : brandInfo.getBrandPhoto());
            info.setComName(StringUtils.isBlank(brandInfo.getComName()) ? info.getComName() : brandInfo.getComName());
            info.setComType(null == brandInfo.getComType() ? info.getComType() : brandInfo.getComType());
            info.setDealType(null == brandInfo.getDealType() ? info.getDealType() : brandInfo.getDealType());
            info.setBrandImage(StringUtils.isBlank(brandInfo.getBrandImage()) ? info.getBrandPhoto() : brandInfo.getBrandImage());
            info.setComNum(StringUtils.isBlank(brandInfo.getComNum()) ? info.getComNum() : brandInfo.getComNum());
            info.setLegalName(StringUtils.isBlank(brandInfo.getLegalName()) ? info.getLegalName() : brandInfo.getLegalName());
            info.setAreaNo(null == brandInfo.getAreaNo() ? info.getAreaNo() : brandInfo.getAreaNo());
            info.setComMark(StringUtils.isBlank(brandInfo.getComMark()) ? info.getComMark() : brandInfo.getComMark());
            info.setUserPhoto(StringUtils.isBlank(brandInfo.getUserPhoto()) ? info.getUserPhoto() : brandInfo.getUserPhoto());
            info.setUserImage(StringUtils.isBlank(brandInfo.getUserImage()) ? info.getUserImage() : brandInfo.getUserImage());
            info.setRegMoney(null == brandInfo.getRegMoney() ? info.getRegMoney() : brandInfo.getRegMoney());
            info.setComWeb(StringUtils.isBlank(brandInfo.getComWeb()) ? info.getComWeb() : brandInfo.getComWeb());
            info.setEmploeeNum(null == brandInfo.getEmploeeNum() ? info.getEmploeeNum() : brandInfo.getEmploeeNum());
            info.setMoneyNum(null == brandInfo.getMoneyNum() ? info.getMoneyNum() : brandInfo.getMoneyNum());
            // branInfonew =
            brandInfoService.updateByPrimaryKey(info);
            // genericMemcache.remove(CacheConst.BRAND_INFO + info.getBrandId());
        }
        // 更新品类
        brandCatelogMapper.deleteBrandCatelogsByBrandId(userInfo.getRefrenceId());
        List<Integer> dealNoList1 = Lists.newArrayList();
        if (dealNoList != null)
        {
            for (String dealNo : dealNoList)
            {
                dealNoList1.add(Integer.parseInt(dealNo));
                BrandCatelog brandCatelog = new BrandCatelog();
                brandCatelog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandCatelog.setDealNo(Integer.parseInt(dealNo));
                brandCatelog.setBrandId(userInfo.getRefrenceId());
                brandCatelog.setCreateTime(registerTime);
                brandCatelog.setDelFlag(false);
                brandCatelogMapper.insert(brandCatelog);
            }
        }
        BrandInfoModel brandInfoModel = new BrandInfoModel();
        try
        {
            BeanUtils.copyProperties(brandInfoModel, branInfonew);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        brandInfoModel.setDealNo(dealNoList1);
        brandCrmService.save(JSON.toJSONString(brandInfoModel), ClientConstant.CLIENT_INFO_2);
        if (null == brandCountService.selectByPrimaryKey(userInfo.getRefrenceId()))
        {
            BrandCount brandCount = new BrandCount();
            brandCount.setRefrenceId(userInfo.getRefrenceId());
            brandCount.setCreateTime(CalendarUtils.getCurrentLong());
            brandCount.setUpdateTime(CalendarUtils.getCurrentLong());
            brandCountService.insert(brandCount);
        }
        return userInfo.getRefrenceId();
    }
    
    /**
     * 过去注册用户信息
     *
     * @param filter
     * @return
     * @author 陈建平
     */
    @Override
    public List<UserInfo> findUserInfo(UserInfo filter)
    {
        return userInfoMapper.findUserInfo(filter);
    }
    
    @Override
    public void updateLocalInfo(CenterUser centerUser, UserInfo localUserInfo) throws BusinessException
    {
        if (StringUtils.isBlank(centerUser.getUserName())) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        if (!ValidateUtils.isMobileFormat(centerUser.getUserName(), true, ValidateUtils.MOBILE_LENGTH)) { throw new BusinessException(CommonConst.MOBILE_FORMAT_ERR); }
        localUserInfo.setUserMobile(centerUser.getUserName());
        localUserInfo.setUserMail(centerUser.getEmail());
        if (StringUtils.isNotBlank(centerUser.getTrueName()))
        {
            localUserInfo.setUserName(centerUser.getTrueName());
        }
        localUserInfo.setUpdateTime(centerUser.getLastUpdateTime());
        this.userInfoMapper.updateByPrimaryKeySelective(localUserInfo);
        // 修改详细信息
        if (1 == localUserInfo.getUserType())
        { // 0 品牌商，1终端商
            DealerInfo dealerInfo = dealerInfoService.getDealerInfo(localUserInfo.getRefrenceId());
            if (null != dealerInfo)
            {
                if (StringUtils.isNotBlank(centerUser.getTrueName()))
                {
                    dealerInfo.setDealerName(centerUser.getTrueName());
                }
                dealerInfo.setUpdateTime(centerUser.getLastUpdateTime());
                dealerInfoService.updateByPrimaryKeySelective(dealerInfo);
            }
        }
    }
    
    /**
     * 发送短信通知
     *
     * @param uuid
     * @param content
     */
    @Override
    public void sendSmsToDealerUser(String uuid, String content)
    {
        UserInfo dealerUserm = userInfoMapper.selectByPrimaryKey(uuid);
        if (dealerUserm == null || dealerUserm.getUserMobile() == null) return;
        SendSmsClient sendSmsClient = new SendSmsClient(messageHistoryMapper, textMessageSender, dealerUserm.getUserMobile(), content);
        new Thread(sendSmsClient).start();
        // textMessageSender.asyncSendTextMessage(dealerUserm.getUserMobile(), content);
    }
    
    /**
     * 修改帐户注册信息
     * @author 陈建平
     * @param userInfo
     * @return
     * @throws BusinessException
     */
    @Override
    public void updateUserInfo(UserInfo userInfo) throws BusinessException
    {
        if (userInfo.getUserPwd().length() < CommonConst.USER_PASSWORD_LEN)
        {
            userCenterClient.setpass(null, userInfo.getRefrenceId(), userInfo.getUserMobile(), userInfo.getUserPwd(), true);
            String salt = RandomStringUtils.randomAlphanumeric(6);
            userInfo.setUserSalt(salt);
            String userPwd = EncryptUtils.encrypt(userInfo.getUserPwd() + salt, ApplicationConst.ENCRYPT);
            userInfo.setUserPwd(userPwd);
        }
        userInfoMapper.updateByPrimaryKey(userInfo);
    }
    
    @Override
    public boolean isUserpasswordMatch(String dealerId, String pwd)
    {
        UserInfo userPassword = userInfoMapper.selectByPrimaryKey(dealerId);
        return EncryptUtils.encrypt(pwd + userPassword.getUserSalt(), ApplicationConst.ENCRYPT).equals(userPassword.getUserPwd());
    }
    
    @Override
    public void logoutByUserMobile(HttpServletRequest request, String userMobile)
    {
        if (null == userMobile) return;
        UserInfo userInfo = userInfoMapper.getByMobile(userMobile);
        if (null != userInfo)
        {
            ShardedJedis jedis = null;
            try
            {
                jedis = JedisUtils.getResource();
                Map<String, String> map = jedis.hgetAll(RedisSessionDAO.SESSION_GROUPS);
                for (Map.Entry<String, String> e : map.entrySet())
                {
                    if (com.zttx.sdk.utils.StringUtils.isNotBlank(e.getKey()) && com.zttx.sdk.utils.StringUtils.isNotBlank(e.getValue()))
                    {
                        String[] ss = com.zttx.sdk.utils.StringUtils.split(e.getValue(), "|");
                        if (ss != null && ss.length == 3)
                        {
                            if (ss[0].equals(userInfo.getRefrenceId()))
                            {
                                jedis.hdel(RedisSessionDAO.SESSION_GROUPS, e.getKey());
                                if (jedis.exists(e.getKey())) jedis.del(e.getKey());
                                break;
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                logger.error("logoutByUserMobile error ", e);
            }
            finally
            {
                JedisUtils.returnResource(jedis);
            }
        }
    }
}
