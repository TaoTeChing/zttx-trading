/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandMessage;
import com.zttx.web.module.brand.mapper.BrandMessageMapper;
import com.zttx.web.module.brand.service.BrandInfoService;
import com.zttx.web.module.dealer.entity.DealerMessage;
import com.zttx.web.module.dealer.mapper.DealerMessageMapper;
import com.zttx.web.utils.CalendarUtils;

/**
 * 经销商消息管理 服务实现类
 * <p>File：DealerMessage.java </p>
 * <p>Title: DealerMessage </p>
 * <p>Description:DealerMessage </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerMessageServiceImpl extends GenericServiceApiImpl<DealerMessage> implements DealerMessageService
{
    private DealerMessageMapper dealerMessageMapper;
    
    @Autowired
    private BrandMessageMapper  brandMessageMapper;
    
    @Autowired
    private BrandInfoService    brandInfoService;
    
    @Autowired
    private DealerReadService   dealerReadService;
    
    @Autowired(required = true)
    public DealerMessageServiceImpl(DealerMessageMapper dealerMessageMapper)
    {
        super(dealerMessageMapper);
        this.dealerMessageMapper = dealerMessageMapper;
    }
    
    /*
     * (non-Javadoc)
     * @see com.zttx.web.module.dealer.service.DealerMessageService#getDealerMessageCount(java.lang.String)
     */
    @Override
    public Long getDealerMessageCount(String dealerId)
    {
        return dealerMessageMapper.countDealerUnReadMessages(dealerId);
    }
    
    @Override
    public DealerMessage getMsgWithBrand(String brandId, String msgId) throws BusinessException
    {
        DealerMessage dealerMessage = dealerMessageMapper.getMsgWithBrand(brandId, msgId);
        if (null == dealerMessage) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        // String senderName = this.getSenderName(dealerMessage.getMsgCate(), dealerMessage.getBrandId());
        dealerMessage.setBrandId(brandId);
        return dealerMessage;
    }
    
    /**
     * 根据经销商ID和消息ID获取消息对象
     *
     * @param dealerId 经销商ID
     * @param msgId    消息ID
     * @return DealerMessage 消息对象
     * @author 章旭楠
     */
    @Override
    public DealerMessage getMsgWithDealer(String dealerId, String msgId) throws BusinessException
    {
        DealerMessage dealerMessage = dealerMessageMapper.getMsgWithDealer(dealerId, msgId);
        if (null == dealerMessage) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        String senderName = this.getSenderName(dealerMessage.getMsgCate(), dealerMessage.getBrandId());
        dealerMessage.setBrandName(senderName);
        return dealerMessage;
    }
    
    @Override
    public void sendBrandMessage(String dealerId, String brandId, String title, String content) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId) || StringUtils.isBlank(brandId) || StringUtils.isBlank(title) || StringUtils.isBlank(content)) { throw new BusinessException(
                CommonConst.PARAM_NULL); }
        BrandMessage brandMessage = new BrandMessage();
        brandMessage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandMessage.setBrandId(brandId);
        brandMessage.setDealerId(dealerId);
        brandMessage.setMsgCate(CommonConstant.Message.MSG_CATE_NET);
        brandMessage.setMsgTitle(title);
        brandMessage.setMsgText(content);
        brandMessage.setCreateTime(CalendarUtils.getCurrentLong());
        brandMessage.setRefuseReply(CommonConstant.Message.MSG_REPLY_YES);
        brandMessageMapper.insert(brandMessage);
    }
    
    /**
     * 获取与经销商相关的消息
     *
     * @param page
     * @param dealerMessageModel
     * @return
     */
    @Override
    public PaginateResult<DealerMessage> listDealerMessage(Pagination page, DealerMessage dealerMessageModel) throws BusinessException
    {
        List<DealerMessage> dealerMessages = this.dealerMessageMapper.listDealerMessage(page, dealerMessageModel);
        for (DealerMessage dealerMessage : dealerMessages)
        {
            String senderName = this.getSenderName(dealerMessage.getMsgCate(), dealerMessage.getBrandId());
            dealerMessage.setBrandName(senderName);
            dealerMessage.setReaded(this.dealerReadService.isExistDealerRead(dealerMessage.getDealerId(), dealerMessage.getRefrenceId()));// 判断是否已读
        }
        return new PaginateResult<>(page, dealerMessages);
    }
    
    /**
     * 获取发送人名称
     * @author 章旭楠
     */
    private String getSenderName(Short msgCate, String brandId)
    {
        if (CommonConstant.Message.MSG_CATE_SYS.equals(msgCate))
        {
            return CommonConstant.Message.SYS_SEND_NAME;
        }
        else
        {
            BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandId);
            return brandInfo.getComName();
        }
    }
    
    /**
     * (假删)删除与经销商相关的多条消息
     *
     * @param dealerId  经销商ID
     * @param msgIdList 待删除的消息ID列表
     * @author 章旭楠
     */
    @Override
    public void deleteDealerMessage(String dealerId, List<String> msgIdList)
    {
        for (String msgId : msgIdList)
        {
            dealerMessageMapper.deleteDealerMessageByDealerId(dealerId, msgId);
        }
    }
}
