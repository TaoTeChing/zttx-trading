/*
 * @(#)DealerInfoClientController.java 2014-5-7 下午6:23:26
 * Copyright 2014 周光暖, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.brand.service.BrandInviteService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerAudit;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.model.DealerShopEnvModel;
import com.zttx.web.module.dealer.service.DealerGroomService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.AppParameterUtils;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.MobileMessageTemple;
import com.zttx.web.utils.ParameterUtils;
import com.zttx.web.utils.UserCenterClient;
import com.zttx.web.utils.ValidateUtils;

/**
 * 经销商基础信息
 * <p>File：DealerInfoClientController.java</p>
 * <p>Title: DealerInfoClientController</p>
 * <p>Description:DealerInfoClientController</p>
 * <p>Copyright: Copyright (c) Sep 17, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/dealerInfo")
public class DealerInfoClientController extends GenericController
{
    private static final Logger logger          = Logger.getLogger(DealerInfoClientController.class);
    
    @Autowired
    private DealerInfoService   dealerInfoService;
    
    @Resource
    @Autowired
    private UserInfoService     userInfoService;
    
    @Autowired
    private TextMessageSender   textMessageSender;
    
    @Autowired
    private MobileMessageTemple mobileMessageTemple;
    
    @Autowired
    private BrandInviteService  brandInviteService;
    
    @Autowired
    private DealerJoinService   dealerJoinService;
    
    @Autowired
    private DealerGroomService  dealerGroomService;
    
    @Autowired
    private UserCenterClient    userCenterClient;
    
    private static int          PASSWORD_LENGTH = 6;
    
    /**
     * 已修正 处理申请(审核通过/审核不通过)
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/handApply", method = RequestMethod.POST)
    public JsonMessage handApply(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerAudit dealerAudit = new DealerAudit();
        BeanUtils.populate(dealerAudit, map);
        dealerAudit.setCheckState(MapUtils.getShort(map, "checkState"));
        dealerInfoService.updateState(dealerAudit);
        if (DealerConstant.DealerInfo.CHECK_STATE_SUCCESS == dealerAudit.getCheckState())// 审核通过时发送短信
        {
            // 状态必须是审核通过才需要发送短信，重置密码，
            UserInfo dealerUserm = userInfoService.selectByPrimaryKey(dealerAudit.getDealerId());
            if (dealerUserm != null && StringUtils.isNotBlank(dealerUserm.getUserMobile()))
            {
                final String mobile = dealerUserm.getUserMobile();
                // 重置密码
                String _userPwd = RandomStringUtils.randomNumeric(PASSWORD_LENGTH);
                dealerUserm.setUserPwd(_userPwd);
                userInfoService.updateByPrimaryKey(dealerUserm);
                final String mobileMessage = mobileMessageTemple.getMessageContent(MobileMessageTemple.SmsKey.DEALER_USERM_OPEN_ACCOUNT, dealerUserm.getUserMobile(),
                        _userPwd);
                textMessageSender.asyncSendTextMessage(mobile, mobileMessage);
            }
            else
            {
                return super.getJsonMessage(ClientConst.FAILURE.code, "短信发送失败：用户不存在或手机号码为空！");
            }
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @ResponseBody
    @RequestMapping(value = "/syncShoperEnv", method = RequestMethod.POST)
    public JsonMessage syncShoperEnv(HttpServletRequest request, ClientParameter param, @RequestParam(value = "images", required = false) MultipartFile[] images)
            throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        DealerShopEnvModel dealerShopEnv = new DealerShopEnvModel();
        BeanUtils.populate(dealerShopEnv, map);
        dealerShopEnv.setGPSX(MapUtils.getDouble(map, "gpsx"));
        dealerShopEnv.setGPSY(MapUtils.getDouble(map, "gpsy"));
        dealerShopEnv.setShopName(MapUtils.getString(map, "dealerName"));
        // 直接保存图片
        ArrayList<DealerImage> dealerImages = new ArrayList<DealerImage>();
        if (images != null)
        {
            for (MultipartFile file : images)
            {
            	Map<String, Object> params = Maps.newHashMap();
            	String imgPath = "";
            	try
                {
            		JsonMessage json = FileClientUtil.getJsonMessage(params, "/client/upload", file.getBytes(), "file", file.getOriginalFilename(), file.getContentType());
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(json.getObject()));
                    String filePath = jsonObject.get("urlPath").toString();
                    imgPath = FileClientUtil.moveImgFromTemp(ImageConst.DEALER_IMG_PATH, filePath, UploadAttCateConst.DEALER_LOGO);
                }
                catch (BusinessException e)
                {
                    return this.getJsonMessage(e.getErrorCode(),e.getMessage());
                }
                catch (IOException e)
                {
                 	return super.getJsonMessage(CommonConst.IMG_SAVE_FAULT);
                }
                DealerImage dealerImage = new DealerImage();
                dealerImage.setCreateTime(CalendarUtils.getCurrentLong());
                dealerImage.setUpdateTime(CalendarUtils.getCurrentLong());
                dealerImage.setDealerId(dealerShopEnv.getDealerId());
                dealerImage.setImageName(imgPath);
                dealerImage.setDelFlag(false);
                dealerImage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerImages.add(dealerImage);
            }
        }
        dealerShopEnv.setDealerImages(dealerImages);
        dealerShopEnv.setDelFlag(false);
        dealerInfoService.addDealerShoperEnv(dealerShopEnv);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 冻结账户
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 周光暖
     */
    @ResponseBody
    @RequestMapping(value = "/frozenAccount", method = RequestMethod.POST)
    public JsonMessage frozenAccount(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String action = MapUtils.getString(map, "action");
        String refrenceId = MapUtils.getString(map, "refrenceId");
        Boolean isResetPwd = MapUtils.getBoolean(map, "isResetPwd");
        UserInfo dealerUserm = null;
        if (StringUtils.isBlank(refrenceId))
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
        else
        {
            dealerUserm = userInfoService.selectByPrimaryKey(refrenceId);
            if (null == dealerUserm) { throw new BusinessException(CommonConst.DEALER_INFO_NULL); }
        }
        if ("frozen".equals(action))// 冻结
        {
        	dealerUserm.setUserState(DealerConstant.DealerUserm.USERSTATE_2);
        	userInfoService.updateByPrimaryKey(dealerUserm);
        }
        else if ("open".equals(action))// 开放
        {
            if (dealerUserm != null && StringUtils.isNotBlank(dealerUserm.getUserMobile()))
            {
                dealerUserm.setUserState(DealerConstant.DealerUserm.USERSTATE_1);
                String _userPwd = RandomStringUtils.randomNumeric(PASSWORD_LENGTH);
                final String mobile = dealerUserm.getUserMobile();
                if (isResetPwd)
                {
                    dealerUserm.setUserPwd(_userPwd);
                }
                userInfoService.updateUserInfo(dealerUserm);
                String mobileMessage = mobileMessageTemple.getMessageContent(MobileMessageTemple.SmsKey.DEALER_USERM_OPEN_ACCOUNT, dealerUserm.getUserMobile(),
                        isResetPwd ? _userPwd : "原来使用的密码");
                textMessageSender.asyncSendTextMessage(mobile, mobileMessage);
            }
            else
            {
                return super.getJsonMessage(ClientConst.FAILURE.code, "短信发送失败：用户不存在或手机号码为空！");
            }
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 注册经销商账户
     * 
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonMessage register(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        UserInfo dealerUserm = new UserInfo();
        DealerInfoModel dealerInfo = new DealerInfoModel();
        DealerShopEnvModel dealerShopEnv = new DealerShopEnvModel();
        BeanUtils.populate(dealerUserm, map);
        BeanUtils.populate(dealerInfo, map);
        BeanUtils.populate(dealerShopEnv, map);
        dealerUserm.setRegisterIp(IPUtil.formatStrIpToInt(MapUtils.getString(map, "registerIp")));
        dealerInfo.setDealerMark(request.getParameter(ClientConst.HTML));
        String envTmpImgIds = MapUtils.getString(map, "envTmpImgIds");
        if (StringUtils.isNotEmpty(envTmpImgIds))
        {
            String[] arrays = envTmpImgIds.split(",");
            dealerShopEnv.setEnvTmpImgIds(Lists.newArrayList(arrays));
        }
        // 参数校验
        List<String> errorList1 = this.verifyDealerInfo(dealerInfo);
        List<String> errorList2 = this.verifyDealerUserm(dealerUserm);
        if (!errorList1.isEmpty() || !errorList2.isEmpty())
        {
            JsonMessage json = super.getJsonMessage(ClientConst.PARAMERROR);
            StringBuilder errorStr = new StringBuilder(errorList1.toString());
            errorStr.append(errorList2.toString());
            json.setMessage(errorStr.toString());
            logger.error(errorStr.toString());
            return json;
        }
        if(StringUtils.isNotBlank(dealerInfo.getDealerLogo())){
        	String dealerLogoPath = FileClientUtil.moveImgFromTemp(ImageConst.DEALER_IMG_PATH, dealerInfo.getDealerLogo(), UploadAttCateConst.DEALER_LOGO);
            dealerInfo.setDealerLogo(dealerLogoPath);
        }
        dealerUserm.setRefrenceId(userCenterClient.registerOrUpdate(dealerUserm));
        dealerInfo.setRefrenceId(dealerUserm.getRefrenceId());
        dealerShopEnv.setDealerId(dealerUserm.getRefrenceId());
        String id = dealerInfoService.addDealerAccount(dealerUserm, dealerInfo, dealerShopEnv);
        // APP信息同步
        syncApp(dealerInfo.getRefrenceId());
        return super.getJsonMessage(126000, "注册成功:" + id, "");
    }
    
    @ResponseBody
    @RequestMapping(value = "/register3", method = RequestMethod.POST)
    public JsonMessage registerWithImage(HttpServletRequest request, ClientParameter param) throws Exception
    {
        return registerWithImage(request, param, null);
    }
    
    /**
     * 注册经销商账户
     * 
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping(value = "/register2", method = RequestMethod.POST)
    public JsonMessage registerWithImage(HttpServletRequest request, ClientParameter param, @RequestParam(value = "images", required = false) MultipartFile[] images)
            throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        UserInfo dealerUserm = new UserInfo();
        DealerInfoModel dealerInfo = new DealerInfoModel();
        DealerShopEnvModel dealerShopEnv = new DealerShopEnvModel();
        BeanUtils.populate(dealerUserm, map);
        BeanUtils.populate(dealerInfo, map);
        BeanUtils.populate(dealerShopEnv, map);
        dealerUserm.setRegisterIp(IPUtil.formatStrIpToInt(MapUtils.getString(map, "registerIp")));
        dealerInfo.setDealerMark(request.getParameter(ClientConst.HTML));
        // shopname 用值于dealerName
        dealerShopEnv.setShopName(MapUtils.getString(map, "dealerName"));
        // 移动图片
        String envTmpImgIds = MapUtils.getString(map, "envTmpImgIds");
        if (StringUtils.isNotEmpty(envTmpImgIds))
        {
            String[] arrays = envTmpImgIds.split(",");
            dealerShopEnv.setEnvTmpImgIds(Lists.newArrayList(arrays));
        }
        // 参数校验
        List<String> errorList1 = this.verifyDealerInfo(dealerInfo);
        List<String> errorList2 = this.verifyDealerUserm(dealerUserm);
        if (!errorList1.isEmpty() || !errorList2.isEmpty())
        {
            JsonMessage json = super.getJsonMessage(ClientConst.PARAMERROR);
            StringBuilder errorStr = new StringBuilder(errorList1.toString());
            errorStr.append(errorList2.toString());
            json.setMessage(errorStr.toString());
            logger.error(errorStr.toString());
            return json;
        }
        if(StringUtils.isNotBlank(dealerInfo.getDealerLogo())){
        	String dealerLogoPath = FileClientUtil.moveImgFromTemp(ImageConst.DEALER_IMG_PATH, dealerInfo.getDealerLogo(), UploadAttCateConst.DEALER_LOGO);
            dealerInfo.setDealerLogo(dealerLogoPath);
        }
        String _userPwd = dealerUserm.getUserPwd();
        if (StringUtils.isEmpty(_userPwd))
        {
            _userPwd = RandomStringUtils.randomNumeric(PASSWORD_LENGTH);
            dealerUserm.setUserPwd(_userPwd);
        }
        dealerUserm.setRefrenceId(userCenterClient.registerOrUpdate(dealerUserm));
        dealerInfo.setRefrenceId(dealerUserm.getRefrenceId());
        dealerShopEnv.setDealerId(dealerUserm.getRefrenceId());
        // 直接保存图片
        ArrayList<DealerImage> dealerImages = new ArrayList<DealerImage>();
        if (images != null)
        {
            for (MultipartFile file : images)
            {
            	Map<String, Object> params = Maps.newHashMap();
            	String imgPath = "";
            	try
                {
            		JsonMessage json = FileClientUtil.getJsonMessage(params, "/client/upload", file.getBytes(), "file", file.getOriginalFilename(), file.getContentType());
                    JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(json.getObject()));
                    String filePath = jsonObject.get("urlPath").toString();
                    imgPath = FileClientUtil.moveImgFromTemp(ImageConst.DEALER_IMG_PATH, filePath, UploadAttCateConst.DEALER_LOGO);
                }
                catch (BusinessException e)
                {
                    return this.getJsonMessage(e.getErrorCode(),e.getMessage());
                }
                catch (IOException e)
                {
                 	return super.getJsonMessage(CommonConst.IMG_SAVE_FAULT);
                }
                DealerImage dealerImage = new DealerImage();
                dealerImage.setCreateTime(CalendarUtils.getCurrentLong());
                dealerImage.setUpdateTime(CalendarUtils.getCurrentLong());
                dealerImage.setDealerId(dealerUserm.getRefrenceId());
                dealerImage.setImageName(imgPath);
                dealerImage.setDelFlag(false);
                dealerImage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerImages.add(dealerImage);
            }
        }
        dealerShopEnv.setDealerImages(dealerImages);
        dealerShopEnv.setDelFlag(false);
        String id = dealerInfoService.addDealerAccount(dealerUserm, dealerInfo, dealerShopEnv);
        // APP信息同步
        syncApp(dealerInfo.getRefrenceId());
        return super.getJsonMessage(126000, "注册成功:" + id, "");
    }
    
    // ===============================================================================
    private List<String> verifyDealerInfo(DealerInfo dealerInfo)
    {
        List<String> errorList = Lists.newArrayList();
        if (ValidateUtils.isNull(dealerInfo.getDealerName()))
        {
            errorList.add("公司/店铺名称不能为空");
        }
        if (ValidateUtils.isNull(dealerInfo.getDealerUser()))
        {
            errorList.add("联系人员不能为空");
        }
        if (null == dealerInfo.getFoundTime())
        {
            errorList.add("成立时间不能为空");
        }
        if (ValidateUtils.isNull(dealerInfo.getDealerMark()))
        {
            errorList.add("经销商介绍不能为空");
        }
        if (null == dealerInfo.getShopNum())
        {
            errorList.add("分店数量不能为空");
        }
        if (null == dealerInfo.getEmpNum())
        {
            errorList.add("月销售额不能为空");
        }
        if (ValidateUtils.isNull(dealerInfo.getDealerAddress()))
        {
            errorList.add("经销商地址不能为空");
        }
        return errorList;
    }
    
    private List<String> verifyDealerUserm(UserInfo dealerUserm)
    {
        List<String> errorList = Lists.newArrayList();
        if (null == dealerUserm.getRegisterIp() || dealerUserm.getRegisterIp() == 0)
        {
            errorList.add("注册ip不能为空");
        }
        if (ValidateUtils.isNull(dealerUserm.getRefrenceId()))
        {
            errorList.add("经销商编号不能为空");
        }
        if (ValidateUtils.isNull(dealerUserm.getUserName()))
        {
            errorList.add("名称不能为空");
        }
        if (ValidateUtils.isNull(dealerUserm.getUserMobile()))
        {
            errorList.add("手机号码不能为空");
        }
        if (!ValidateUtils.isMobileFormat(dealerUserm.getUserMobile(), true, 11))
        {
            errorList.add("手机格式不正确[" + dealerUserm.getUserMobile() + "]");
        }
        return errorList;
    }
    
    private void syncApp(String dealerId)
    {
        // 推送给APP
        try
        {
        	if(StringUtils.isNotBlank(dealerId)){
        		List<Map<String, Object>> dataList = dealerInfoService.listAppDealerInfo(dealerId);
                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(dataList))
                {
                    Map<String, Object> params = Maps.newHashMap();
                    params.put("result", JSON.toJSONString(dataList));
                    JsonMessage jsonMessage = AppParameterUtils.getAppJsonMessage(params, "/trade/shop/syncZttxShop");
                    if (121000 != jsonMessage.getCode().intValue())
                    {
                        logger.error(jsonMessage.getMessage());
                    }
                }
        	}
        }
        catch (Exception e)
        {
            logger.error(e);
        }
    }
    
    /**
     * 根据多个经销商编号获取经销商信息(品牌商erp)
     * @param request
     * @param param
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @ResponseBody
    @RequestMapping(value = "/manyDealerinfos", method = RequestMethod.POST)
    public JsonMessage manyDealerinfos(HttpServletRequest request, ClientParameter param) throws BusinessException
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String dealerIds = MapUtils.getString(map, "dealerId");
        List<DealerInfo> result = null;
        if (StringUtils.isBlank(dealerIds))
        {
            return null;
        }
        else
        {
            if (dealerIds.contains("~"))
            {
                String[] dealerId_temp = dealerIds.split("~");
                List<String> list = Arrays.asList(dealerId_temp);
                result = dealerInfoService.getDealerInfos(list);
            }
            result = dealerInfoService.getDealerInfos(Arrays.asList(dealerIds));
        }
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
}
