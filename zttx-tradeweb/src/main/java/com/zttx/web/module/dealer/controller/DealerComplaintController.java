/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.entity.DataDictValue;
import com.zttx.web.module.common.entity.SmsTemplate;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerComplaint;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerOrder;
import com.zttx.web.module.dealer.service.DealerComplaintService;
import com.zttx.web.module.dealer.service.DealerInfoService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.security.OnLineUserUtils;

/**
 * 经销商投诉信息 控制器
 * <p>File：DealerComplaintController.java </p>
 * <p>Title: DealerComplaintController </p>
 * <p>Description:DealerComplaintController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/dealer/dealerComplaint")
public class DealerComplaintController extends GenericController
{
    @Autowired
    DealerOrderService             dealerOrderService;
    @Autowired
    DataDictValueService           dataDictValueService;
    @Autowired
    UserInfoService                userInfoService;
    @Autowired(required = true)
    private DealerComplaintService dealerComplaintService;
    @Autowired
    private SmsTemplateService     smsTemplateService;
    
    @Autowired
    private SmsSendService         smsSendService;
    
    @Autowired
    private DealerInfoService      dealerInfoService;
    
    @Autowired
    private BrandesInfoService     brandesInfoService;
    
    /**
     * 投诉管理查询
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/complaint")
    public String execute()
    {
        return "dealer/complaint_index";
    }
    
    /**
    * 投诉管理页列表数据
    */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/complaint.json")
    public JsonMessage execute(Pagination page, DealerComplaint dealerComplaint) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        PaginateResult<DealerComplaint> paginateResult = null;
        if (StringUtils.isNotBlank(dealerId))
        {
            dealerComplaint.setDealerId(dealerId);
            paginateResult = dealerComplaintService.getList(dealerComplaint, page);
        }
        return this.getJsonMessage(CommonConst.SUCCESS, paginateResult);
    }
    
    /**
     * 投诉详情记录
     */
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/process/{refrenceId}")
    public String complaintWait(Model model, @PathVariable("refrenceId") String refrenceId) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(refrenceId))
        {
            DealerComplaint dealerComplaint = dealerComplaintService.loadDealerComplaint(refrenceId, dealerId);
            if (null != dealerComplaint)
            {
                DealerOrder dealerOrder = dealerOrderService.getDealerOrder(dealerComplaint.getOrderId(), dealerId);
                model.addAttribute("dealerOrder", dealerOrder);
                model.addAttribute("dealerComplaint", dealerComplaint);
                if (dealerComplaint.getComState() == DealerConstant.DealerComplaint.COMSTATE_0) // 0：等待处理
                {
                    // 时间倒计时
                    DataDictValue ddv = dataDictValueService.findDictValue("complaintJoinDay", "complaintJoinDay");
                    if (ddv != null)
                    {
                        String date = CalendarUtils.addDay(new Date(dealerComplaint.getCreatetime()), Integer.valueOf(ddv.getDictValue()),
                                ApplicationConst.DATE_FORMAT_YMDHMS);
                        Long endTime = CalendarUtils.getLongFromTime(date, ApplicationConst.DATE_FORMAT_YMDHMS);
                        model.addAttribute("endTime", endTime);
                    }
                    return "dealer/complaint_wait";
                }
                else if (dealerComplaint.getComState() == DealerConstant.DealerComplaint.COMSTATE_1) // 1：客服介入
                {
                    return "dealer/complaint_work";
                }
                else if (dealerComplaint.getComState() == DealerConstant.DealerComplaint.COMSTATE_2) // 2：处理完成
                {
                    return "dealer/complaint_complete";
                }
                else if (dealerComplaint.getComState() == DealerConstant.DealerComplaint.COMSTATE_3) // 3：经销商撤消投诉
                { return "dealer/complaint_close"; }
            }
        }
        return "redirect:/complaint";
    }
    
    /**
     * 保存投诉记录
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(DealerComplaint dealerComplaint)
    {
        try
        {
            String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
            dealerComplaint.setDealerId(dealerId);
            Short state = dealerComplaintService.save(dealerComplaint);
            HashMap<String, Object> result = Maps.newHashMap();
            result.put("state", state);
            result.put("dealerComplaint", dealerComplaint);
            // 发送投诉短信提醒
            DealerComplaint complaint = dealerComplaintService.getDealerComplaint(dealerComplaint.getOrderId());
            // String smsTemplate = "%s %s终端商对%s品牌订单%s进行投诉，请及时关注处理。如需查看详情请登录【8637.com】";
            // String smsContent = String.format(smsTemplate, new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()),
            // complaint.getDealerName(), complaint.getBrandsName(), complaint.getOrderId());
            // brandUsermService.sendSmsToBrandUser(complaint.getBrandId(), smsContent);
            SmsTemplate smsTemplate = smsTemplateService.getBySmsKey(SmsTemplateServiceImpl.SMS_DEALER_COMPLAINT);
            if (smsTemplate != null && StringUtils.isNotEmpty(smsTemplate.getContent()))
            {
                DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(complaint.getDealerId());
                BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(complaint.getBrandsId());
                String smsContent = String.format(smsTemplate.getContent(), new SimpleDateFormat("MM月dd日 HH:mm").format(new Date()), dealerInfo.getDealerName(),
                        brandesInfo.getBrandsName(), complaint.getOrderId());
                smsSendService.sendSmsToUser(complaint.getBrandId(), smsContent);
            }
            return super.getJsonMessage(CommonConst.SUCCESS, result);
        }
        catch (BusinessException e)
        {
            return super.getJsonMessage(CommonConst.FAIL);
        }
    }
    
    /**
     * 撤销投诉
     */
    @ResponseBody
    @RequiresPermissions(value = "dealer:center")
    @RequestMapping(value = "/disApply", method = RequestMethod.POST)
    public JsonMessage disApply(String refrenceId) throws BusinessException
    {
        String dealerId = OnLineUserUtils.getCurrentDealer().getRefrenceId();
        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(refrenceId))
        {
            DealerComplaint dealerComplaint = dealerComplaintService.loadDealerComplaint(refrenceId, dealerId);
            if (null != dealerComplaint)
            {
                dealerComplaintService.disApply(dealerComplaint);
            }
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
}
