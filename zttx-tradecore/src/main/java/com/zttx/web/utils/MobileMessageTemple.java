package com.zttx.web.utils;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Maps;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.service.SmsTemplateService;

/**
 * <p>File：MobileMessageTemple.java</p>
 * <p>Title: 短信模板</p>
 * <p>Description: 管理短信的模板，提供了根据smsKey（短信模块Key）获取模板内容的服务</p>
 * <pre>
 *    使用例子：
 *    
 * (1) String mobileMessage = mobileMessageTemple.getMessageContent(MobileMessageTemple.SmsKey.DEALER_USERM_OPENACCOUNT);
 *    
 * (2) String mobileMessage = mobileMessageTemple.getMessageContent(
 *    			MobileMessageTemple.SmsKey.DEALER_USERM_OPEN_ACCOUNT, mobile, password);
 *    
 *    注意：
 *   		如果短信模板存在占位符，调用方法时没有提供对应的替换内容，会抛以下异常
 *    		{"code":126119,"message":"短信格式错误：必须替换{$mobile$,$password$}占位符"}
 * </pre>
 * <p>Copyright: Copyright (c) 2014 2014-7-22 下午2:04:12</p>
 * <p>Company: 8637.com</p>
 * @author 周光暖
 * @version 1.0
 */
@SuppressWarnings("all")
@Component
public class MobileMessageTemple
{
    // 短信模块Key
    public static enum SmsKey
    {
        DEALER_USERM_OPEN_ACCOUNT("dealerUserm-openAccount"), // 开通经销商账户
        BRAND_USERM_OPEN_ACCOUNT("brandUserm-openAccount"), // 开通品牌商账户
        BRAND_USERM_REGISTER_ACCOUNT("brandUserm-registerAccount"), // 注册品牌商账户
        SECURITYCERT_UPDATE_MOBILE("securityCert-updateMobile") // 更改账户手机号码成功
        ;
        private String smsKey;
        
        private SmsKey(String smsKey)
        {
            this.smsKey = smsKey;
        }
        
        String getKey()
        {
            return this.smsKey;
        }
    }
    
    // 分隔符（短信内容占位符）
    private static final String      SEPARATOR = ",";
    
    @Autowired
    private SmsTemplateService       smsTemplateService;
    
    private Map<String, SmsTemplate> temples;
    
    /**
     * 获得短信内容
     * @param smsKey			短信模块Key
     * @param rePlacesContent	占位符
     * @return
     * @throws BusinessException
     * 		{"code":126000,"message":"短信格式错误：必须替换{$mobile$,$password$}占位符"}
     * @author 周光暖
     */
    public String getMessageContent(SmsKey smsKey, String ... rePlacesContent) throws BusinessException
    {
        SmsTemplate smsTemplate = this.getSmsTemplate(smsKey);
        if (null == smsTemplate) throw new BusinessException(CommonConst.MOBILE_MESSAGE_TEMPLATE_NULL);
        String messageTemple = smsTemplate.getContent();
        String[] places = getPlaces(smsTemplate);
        if (ArrayUtils.isNotEmpty(places))// 需要替换占位符
        {
            if (!canReplacedPlaces(places, rePlacesContent))
            {
                String errorMessage = CommonConst.MOBILE_MESSAGE_RTF_ERROR.getMessage() + "：必须替换" + ArrayUtils.toString(places) + "占位符";
                throw new BusinessException(CommonConst.SUCCESS.getCode(), errorMessage);
            }
            messageTemple = StringUtils.replaceEachRepeatedly(messageTemple, places, rePlacesContent);
        }
        return messageTemple;
    }
    
    private SmsTemplate getSmsTemplate(SmsKey smsKey)
    {
        if (CollectionUtils.isEmpty(temples))
        {
            temples = Maps.newHashMap();
            List<SmsTemplate> smsTemplateList = smsTemplateService.selectAll();
            for (SmsTemplate smsTemplate : smsTemplateList)
                temples.put(smsTemplate.getSmsKey(), smsTemplate);
        }
        return temples.get(smsKey.getKey());
    }
    
    private String[] getPlaces(SmsTemplate smsTemplate)
    {
        String placeStrs = smsTemplate.getPlace();
        if (StringUtils.isNotBlank(placeStrs)) return placeStrs.split(SEPARATOR);
        return null;
    }
    
    private boolean canReplacedPlaces(String[] places, String[] rePlacesContent)
    {
        if (ArrayUtils.isEmpty(places) || ArrayUtils.isEmpty(rePlacesContent)) return false;
        return places.length == rePlacesContent.length;
    }
}
