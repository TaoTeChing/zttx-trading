/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.mapper.SmsTemplateMapper;

/**
 * 短信模板 服务实现类
 * <p>File：SmsTemplate.java </p>
 * <p>Title: SmsTemplate </p>
 * <p>Description:SmsTemplate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SmsTemplateServiceImpl extends GenericServiceApiImpl<SmsTemplate> implements SmsTemplateService
{
	// 品牌商发货短信提醒
    public static final String SMS_BRAND_SEND_GOODS       = "brand_send_goods";
    
    // 退款成功后短信提醒
    public static final String SMS_BRAND_REFUND_MONEY     = "brand_refund_money";
    
    // 品牌商同意加盟短信提醒
    public static final String SMS_BRAND_AGREE_APPLY      = "brand_agree_apply";
    
    // 品牌商拒绝加盟短信提醒
    public static final String SMS_BRAND_REJECT_APPLY     = "brand_reject_apply";
    
    // 通知品牌商发货短信提醒
    public static final String SMS_BRAND_NOTICE_SENDGOODS = "brand_notice_sendgoods";
    
    // 经销商付款成功短信提醒
    public static final String SMS_DEALER_PLAYD_ORDER     = "dealer_playd_order";
    
    // 终端商加盟短信提醒
    public static final String SMS_DEALER_APPLY_INVITE    = "dealer_apply_invite";
    
    // 终端商投诉短信提醒
    public static final String SMS_DEALER_COMPLAINT       = "dealer_complaint_info";
    
    // 确认收货短信提醒品牌商
    public static final String SMS_DEALER_CONFIRM_RECEIVE = "dealer_confirm_receive";
    
    // 终端商申请退款提醒
    public static final String SMS_DEALER_REFUND_MONEY    = "dealer_refund_money";
    
    //转账短信提醒
    public static final String SMS_TRANSFER_MONEY         = "sms_transfer_money";
    
    //退押金短信提醒
    public static final String SMS_REFUND_TRANSFER_MONEY  = "sms_refund_transfer_money";

    private SmsTemplateMapper smsTemplateMapper;

    @Autowired(required = true)
    public SmsTemplateServiceImpl(SmsTemplateMapper smsTemplateMapper)
    {
        super(smsTemplateMapper);
        this.smsTemplateMapper = smsTemplateMapper;
    }
    
    @Override
    public SmsTemplate getBySmsKey(String smsKey)
    {
        return smsTemplateMapper.getBySmsKey(smsKey);
    }
    
    @Override
    public PaginateResult<SmsTemplate> pageSearch(SmsTemplate template, Pagination pagination)
    {
        template.setPage(pagination);
        List<SmsTemplate> list = smsTemplateMapper.pageSearch(template);
        return new PaginateResult(pagination, list);
    }
    
    @Override
    public SmsTemplate saveOrUpdate(SmsTemplate template) throws BusinessException
    {
        if (StringUtils.isNotBlank(template.getRefrenceId()))
        {
            SmsTemplate templateDB = smsTemplateMapper.selectByPrimaryKey(template.getRefrenceId());
            if (null == templateDB) doSave(template);
            else
            {
                if (!templateDB.getSmsKey().equals(template.getSmsKey()) && checkSmsKeyExist(template.getSmsKey()))
                    throw new BusinessException(CommonConst.SAVE_SMS_TEMPLATE_ERROR);
                processTemplate(template);
                smsTemplateMapper.updateByPrimaryKey(template);
            }
        }
        else doSave(template);
        return template;
    }
    
    private void doSave(SmsTemplate template) throws BusinessException
    {
        if (checkSmsKeyExist(template.getSmsKey())) throw new BusinessException(CommonConst.SAVE_SMS_TEMPLATE_ERROR);
        template.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        processTemplate(template);
        smsTemplateMapper.insert(template);
    }
    
    private void processTemplate(SmsTemplate template)
    {
        if (null == template.getCreateTime()) template.setCreateTime(CalendarUtils.getCurrentTime());
        if (null == template.getDelFlag()) template.setDelFlag(Boolean.FALSE);
        template.setUpdateTime(CalendarUtils.getCurrentTime());
    }
    
    // 检验短信模板KEY是否已存在
    private boolean checkSmsKeyExist(String smsKey)
    {
        SmsTemplate templateExist = smsTemplateMapper.getBySmsKey(smsKey);
        if (null == templateExist) return false;
        return true;
    }
}
