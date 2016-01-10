package com.zttx.web.module.client.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.ClientParameter;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.TelVerifyTypeConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.module.brand.entity.BrandAudit;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.service.BrandCatelogService;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.TelCodeService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.utils.MobileMessageTemple;
import com.zttx.web.utils.ParameterUtils;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌商基础信息
 * <p>File：BrandInfoClientController.java</p>
 * <p>Title: BrandInfoClientController</p>
 * <p>Description:BrandInfoClientController</p>
 * <p>Copyright: Copyright (c) Sep 8, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 陈建平
 * @version 1.0
 */
@Controller
@RequestMapping(value = ApplicationConst.CLIENT + "/brandInfo")
public class BrandInfoClientController extends GenericController
{
    @Autowired
    private BrandInfoService    brandInfoService;
    
    @Autowired
    private UserInfoService   	userInfoService;
    
    @Autowired
    private TelCodeService      telCodeService;
    
    @Autowired
    private BrandCatelogService brandCatelogService;
    
    @Autowired
    private TextMessageSender   textMessageSender;
    
    @Autowired
    private MobileMessageTemple mobileMessageTemple;
    
    /**
     * 处理申请(审核通过/审核不通过)
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/handApply")
    public JsonMessage handApply(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        BrandAudit brandAudit = new BrandAudit();
        BeanUtils.populate(brandAudit, map);
        String refrenceId = MapUtils.getString(map, "refrenceId");
        Short action = MapUtils.getShort(map, "action");
        String dealNos = MapUtils.getString(map, "dealNos");
        brandInfoService.updateState(refrenceId, action, brandAudit, dealNos);
        if (action == BrandConstant.BrandInfoConst.CHECK_STATE_PASS_AUDIT) // 审核通过时发送短信提示用户
        {
            UserInfo userInfo = userInfoService.selectByPrimaryKey(refrenceId);
            if (userInfo != null && StringUtils.isNotBlank(userInfo.getUserMobile()))
            {
            	userInfoService.logoutByUserMobile(request, userInfo.getUserMobile());
                final String mobile = userInfo.getUserMobile();
                final String mobileMessage = mobileMessageTemple.getMessageContent(MobileMessageTemple.SmsKey.BRAND_USERM_OPEN_ACCOUNT, mobile);
                textMessageSender.asyncSendTextMessage(mobile, mobileMessage);
            }
        }
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 更改品牌商经营类目
     * @param request
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateDealNos")
    public JsonMessage updateDealNos(HttpServletRequest request, ClientParameter param) throws Exception
    {
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        String brandId = MapUtils.getString(map, "refrenceId");
        String dealNos = MapUtils.getString(map, "dealNos");
        brandCatelogService.updateBrandCatelogByClient(brandId, dealNos);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 注册品牌商账户
     * @param request
     * @param param
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @throws BusinessException 
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonMessage register(HttpServletRequest request, ClientParameter param) throws Exception
    {
        JsonMessage json = super.getJsonMessage(ClientConst.FAILURE);
        Map<String, String> map = ParameterUtils.getMapFromParameter(param);
        Boolean hasExist = false;
        UserInfo userInfo = new UserInfo();
        BeanUtils.populate(userInfo, map);
        userInfo.setRegisterIp(MapUtils.getInteger(map, "registerIp"));
        List<String> validatorList = brandValidator(userInfo, null, hasExist);
        if (null != validatorList && !validatorList.isEmpty())
        {
            json.setMessage(validatorList.toString());
            return json;
        }
        String dealNoString = MapUtils.getString(map, "dealNoString");
        // String dealNos = MapUtils.getString(map, key)
        String[] dealNoList = null;
        if (dealNoString != null && StringUtils.isNotBlank(dealNoString))
        {
            dealNoList = dealNoString.split("\\|");
        }
        BrandInfo brandInfo = new BrandInfo();
        BigDecimalConverter bd = new BigDecimalConverter(BigDecimal.ZERO);    
        ConvertUtils.register(bd, java.math.BigDecimal.class);    
        BeanUtils.populate(brandInfo, map);
        // 非必填项，BeanUtils.populate(brandInfo, map)方法默认会将数字类型赋初始值0，可能影响业务需求，所以手动设置
        //brandInfo.setRegMoney(MapUtils.getDouble(map, "regMoney"));
        brandInfo.setEmploeeNum(MapUtils.getShort(map, "emploeeNum"));
        brandInfo.setMoneyNum(MapUtils.getShort(map, "moneyNum"));
        brandInfo.setComMark(request.getParameter(ClientConst.HTML));
        Integer areaNo = MapUtils.getInteger(map, "areaNo");
        if (null == areaNo)
        {
            areaNo = MapUtils.getInteger(map, "cityCode");
        }
        brandInfo.setAreaNo(areaNo);
        List<String> validatorList1 = brandValidator(brandInfo, null);
        if (null != validatorList1 && !validatorList1.isEmpty())
        {
            json.setMessage(validatorList1.toString());
            return json;
        }
        BrandContact brandContact = new BrandContact();
        BeanUtils.populate(brandContact, map);
        brandContact.setUserPhoto(MapUtils.getString(map, "contactPhoto"));
        List<String> validatorList2 = brandValidator(brandContact, null);
        if (null != validatorList2 && !validatorList2.isEmpty())
        {
            json.setMessage(validatorList2.toString());
            return json;
        }
        //userInfo.setAccountType(UserAccountConst.ACCOUNT_TYPE_MASTER);
        userInfo.setUserState(UserAccountConst.USER_STAT_OPEN);
        userInfo.setMobileVerify(true);
        userInfo.setMailVerify(false);
        if (!hasExist)
        {
            String id = userInfoService.addBrandAccount(userInfo, brandInfo, brandContact, dealNoList);
            if (StringUtils.isNotBlank(id)) // 注册成功时，发送短信
            {
            	userInfo = userInfoService.selectByPrimaryKey(id);
                if (userInfo != null && StringUtils.isNotBlank(userInfo.getUserMobile()))
                {
                    final String mobile = userInfo.getUserMobile();
                    final String mobileMessage = mobileMessageTemple.getMessageContent(MobileMessageTemple.SmsKey.BRAND_USERM_REGISTER_ACCOUNT, mobile);
                    textMessageSender.asyncSendTextMessage(mobile, mobileMessage);
                }
            }
        }
        if (null != userInfo)
        {
            telCodeService.modifyStateUsed(userInfo.getUserMobile(), TelVerifyTypeConst.DEAL_PLATFORM);
            //memcachedSessionManager.put(request, MemcachedSessionManager.SessionKey.BRAND_ID, userInfo.getRefrenceId());
        }
        return super.getJsonMessage(CommonConst.SUCCESS, userInfo);
    }
    
    // 品牌商用户注册参数检验 ========================================================================================
    private List<String> brandValidator(UserInfo userInfo, String areaNo, Boolean hasExist)
    {
        List<String> validatorList = Lists.newArrayList();
        if (StringUtils.isBlank(userInfo.getRegisterIp().toString()))
        {
            validatorList.add("注册IP为空");
        }
        if (StringUtils.isBlank(userInfo.getUserMobile()))
        {
            validatorList.add("手机号码为空");
        }
        if (!StringUtils.isBlank(userInfo.getUserMail()))
        {
            if (!ValidateUtils.isMailFormat(userInfo.getUserMail(), true, ValidateUtils.MAX_EMAIL_LENGTH))
            {
                validatorList.add("邮件地址格式错误");
            }
        }
        if (StringUtils.isNotBlank(userInfo.getUserPwd()) && !ValidateUtils.isRange(userInfo.getUserPwd(), 6, 16, true))
        {
            validatorList.add("密码格式错误");
        }
        if (StringUtils.isBlank(userInfo.getUserName()))
        {
            validatorList.add("姓名为空");
        }
        return validatorList;
    }
    
    private List<String> brandValidator(BrandInfo brandInfo, String areaNo)
    {
        List<String> validatorList = Lists.newArrayList();
        if (StringUtils.isBlank(brandInfo.getComName()))
        {
            validatorList.add("公司名称为空");
        }
        if (StringUtils.isBlank(brandInfo.getComType().toString()))
        {
            validatorList.add("企业类型为空");
        }
        if (StringUtils.isBlank(brandInfo.getDealType().toString()))
        {
            validatorList.add("经营类型为空");
        }
        if (StringUtils.isBlank(brandInfo.getBrandImage()))
        {
            validatorList.add("执照图片新名(正)为空");
        }
        if (StringUtils.isBlank(brandInfo.getComNum()))
        {
            validatorList.add("营业执照编号为空");
        }
        if (StringUtils.isBlank(brandInfo.getLegalName()))
        {
            validatorList.add("法人代表为空");
        }
        if (StringUtils.isBlank(brandInfo.getComMark()))
        {
            validatorList.add("企业介绍为空");
        }
        return validatorList;
    }
    
    private List<String> brandValidator(BrandContact brandContact, String areaNo)
    {
        List<String> validatorList = Lists.newArrayList();
        if (StringUtils.isBlank(brandContact.getUserName()))
        {
            validatorList.add("联系人员为空");
        }
        if (StringUtils.isBlank(brandContact.getUserGender().toString()))
        {
            validatorList.add("性别为空");
        }
        return validatorList;
    }
}
