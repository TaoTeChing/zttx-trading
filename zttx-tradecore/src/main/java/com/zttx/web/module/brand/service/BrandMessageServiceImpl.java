/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.bean.TextMessageSender;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.brand.entity.BrandMessage;
import com.zttx.web.module.brand.entity.BrandRead;
import com.zttx.web.module.brand.mapper.BrandMessageMapper;
import com.zttx.web.module.brand.mapper.BrandReadMapper;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerMessage;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.mapper.DealerMessageMapper;
import com.zttx.web.module.dealer.mapper.DealerReadMapper;
import com.zttx.web.module.dealer.service.DealerReadService;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商消息管理 服务实现类
 * <p>File：BrandMessage.java </p>
 * <p>Title: BrandMessage </p>
 * <p>Description:BrandMessage </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandMessageServiceImpl extends GenericServiceApiImpl<BrandMessage> implements BrandMessageService
{
    @Autowired
    private BrandMessageMapper  brandMessageMapper;
    
    @Autowired
    private DealerMessageMapper dealerMessageMapper;
    
    @Autowired
    private UserInfoMapper      userInfoMapper;
    
    @Autowired
    private DealerInfoMapper    dealerInfoMapper;
    
    @Autowired
    private BrandReadService    brandReadService;
    
    @Autowired
    private DealerReadService   dealerReadService;
    
    @Autowired
    private BrandReadMapper     brandReadMapper;
    
    @Autowired
    private DealerReadMapper    dealerReadMapper;
    
    @Autowired(required = true)
    public BrandMessageServiceImpl(BrandMessageMapper brandMessageMapper)
    {
        super(brandMessageMapper);
        this.brandMessageMapper = brandMessageMapper;
    }
    
    /**
     * （分页）根据查询条件BrandMessageModel，获取与品牌商相关的消息
     *
     * @param page              分页参数
     * @param brandMessageModel 查询条件
     * @return PaginateResult<Map<String, Object>> 查询结果
     * @author 章旭楠
     */
    @Override
    public PaginateResult<Map<String, Object>> listBrandMessage(Pagination page, BrandMessage brandMessageModel)
    {
        brandMessageModel.setPage(page);
        setBrandMessageModelSearchTime(brandMessageModel);
        List<Map<String, Object>> brandMessages = brandMessageMapper.listBrandMessage(brandMessageModel);
        for (Map brandMessageMap : brandMessages)
        {
            String senderName = this.getSenderName((Integer) brandMessageMap.get("msgCate"), (String) brandMessageMap.get("dealerId"));
            brandMessageMap.put("userName", senderName);
            brandMessageMap.put("isReaded", this.brandReadService.isExistBrandRead(brandMessageModel.getBrandId(), (String) brandMessageMap.get("refrenceId")));
        }
        return new PaginateResult<>(page, brandMessages);
    }
    
    /**
     * （分页）根据查询条件BrandMessageModel，获取品牌商已发送的消息
     *
     * @param page              分页参数
     * @param brandMessageModel 查询条件
     * @return PaginateResult<Map<String, Object>> 查询结果
     * @author 章旭楠
     */
    @Override
    public PaginateResult<Map<String, Object>> listBrandSendMessage(Pagination page, BrandMessage brandMessageModel)
    {
        brandMessageModel.setPage(page);
        setBrandMessageModelSearchTime(brandMessageModel);
        List<Map<String, Object>> brandSendMessages = brandMessageMapper.listBrandSendMessage(brandMessageModel);
        for (Map brandMessageMap : brandSendMessages)
        {
            String senderName = this.getSenderName((Integer) brandMessageMap.get("msgCate"), (String) brandMessageMap.get("dealerId"));
            brandMessageMap.put("userName", senderName);
            brandMessageMap.put("isReaded", this.dealerReadService.isExistDealerRead((String) brandMessageMap.get("dealerId"), (String) brandMessageMap.get("refrenceId")));
        }
        return new PaginateResult<>(page, brandSendMessages);
    }
    
    /**
     * 获取发送人名称
     *
     * @author 张昌苗
     */
    private String getSenderName(Integer msgCate, String dealerId)
    {
        if (CommonConstant.Message.MSG_CATE_SYS.equals(msgCate))
        {
            return CommonConstant.Message.SYS_SEND_NAME;
        }
        else
        {
            return getUserName(dealerId);
        }
    }
    
    private String getUserName(String dealerId)
    {
        String userName = "";
        if (StringUtils.isNoneBlank(dealerId))
        {
            DealerInfo user = dealerInfoMapper.selectByPrimaryKey(dealerId);
            userName = user == null ? "" : user.getDealerName();
        }
        return userName;
    }
    
    /**
     * 查询时间设置
     *
     * @param brandMessageModel
     */
    private void setBrandMessageModelSearchTime(BrandMessage brandMessageModel)
    {
        // 开始发送时间
        if (null != brandMessageModel.getSendBeginTime())
        {
            brandMessageModel.setSendBeginLongTime(CalendarUtils.getLongFromTime(brandMessageModel.getSendBeginTime()));
        }
        // 结束发送时间
        if (null != brandMessageModel.getSendEndTime())
        {
            brandMessageModel.setSendEndLongTime(CalendarUtils.addDay(brandMessageModel.getSendEndTime(), 1));
        }
    }
    
    /**
     * 获取未读的消息条数
     *
     * @param brandId 品牌商ID
     * @return Long 未读的消息条数
     * @author 章旭楠
     */
    @Override
    public Long getBrandMessageCount(String brandId)
    {
        return this.brandMessageMapper.countBrandMessage(brandId);
    }
    
    /**
     * 品牌商向一个经销商发送消息
     *
     * @param brandId  品牌商ID（不能为空）
     * @param dealerId 经销商ID（不能为空）
     * @param title    消息标题（不能为空）
     * @param content  消息内容
     * @author 章旭楠
     */
    @Override
    public void sendDealerMessage(String brandId, String dealerId, String title, String content)
    {
        DealerMessage dealerMessage = this.parseDealerMessage(brandId, dealerId, title, content);
        // List<Integer> countTypeList = Lists.newArrayList();todo dealerCount 统计提取
        // countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_SYSMESSAGECOUNT);todo dealerCount 统计提取
        // dealerCountMapper.modifyDealerCountCache(dealerId, countTypeList.toArray(new Integer[]{})); todo dealerCount 统计提取
        // dealerCountCache.modifyDealerCountCache(dealerId, countTypeList.toArray(new Integer[]{})); todo dealerCount 统计提取
        dealerMessageMapper.insertSelective(dealerMessage);
    }
    
    /**
     * 创建DealerMessage对象
     */
    private DealerMessage parseDealerMessage(String brandId, String dealerId, String title, String content)
    {
        DealerMessage dealerMessage = new DealerMessage();
        dealerMessage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerMessage.setDealerId(dealerId);
        dealerMessage.setBrandId(brandId);
        dealerMessage.setMsgCate(CommonConstant.Message.MSG_CATE_NET);
        dealerMessage.setMsgTitle(title);
        dealerMessage.setMsgText(content);
        dealerMessage.setCreateTime(CalendarUtils.getCurrentLong());
        dealerMessage.setRefuseReply(CommonConstant.Message.MSG_REPLY_YES);
        return dealerMessage;
    }
    
    /**
     * 品牌商向一个经销商发送消息(订单消息)
     *
     * @param brandId
     * @param dealerId
     * @param title
     * @param content
     * @author 章旭楠
     */
    @Override
    public void sendDealerOrderMessage(String brandId, String dealerId, String title, String content)
    {
        DealerMessage dealerMessage = this.parseDealerOrderMessage(brandId, dealerId, title, content);
        // List<Integer> countTypeList = Lists.newArrayList();
        // countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_SYSMESSAGECOUNT);
        // dealerCountCache.modifyDealerCountCache(dealerId, countTypeList.toArray(new Integer[]{}));//todo dealerCount 统计提取
        dealerMessageMapper.insertSelective(dealerMessage);
    }
    
    /**
     * 创建DealerMessage对象(订单信息)
     *
     * @param brandId
     * @param dealerId
     * @param title
     * @param content
     * @return
     * @author 施建波
     */
    private DealerMessage parseDealerOrderMessage(String brandId, String dealerId, String title, String content)
    {
        DealerMessage dealerMessage = new DealerMessage();
        dealerMessage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        dealerMessage.setDealerId(dealerId);
        dealerMessage.setBrandId(brandId);
        dealerMessage.setMsgCate(CommonConstant.Message.MSG_CATE_BIL);
        dealerMessage.setMsgTitle(title);
        dealerMessage.setMsgText(content);
        dealerMessage.setCreateTime(CalendarUtils.getCurrentLong());
        dealerMessage.setRefuseReply(CommonConstant.Message.MSG_REPLY_NO);
        return dealerMessage;
    }
    
    /**
     * 品牌商向多个经销商发送消息
     *
     * @param brandId   品牌商ID（不能为空）
     * @param dealerIds 多个经销商ID（不能为空）
     * @param title     消息标题（不能为空）
     * @param content   消息内容
     * @author 章旭楠
     */
    @Override
    public void sendDealerMessage(String brandId, String[] dealerIds, String title, String content)
    {
        List<DealerMessage> list = Lists.newArrayList();
        for (int i = 0; i < dealerIds.length; i++)
        {
            DealerMessage dealerMessage = this.parseDealerMessage(brandId, dealerIds[i], title, content);
            // List<Integer> countTypeList = Lists.newArrayList();todo dealerCount 统计提取
            // countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_SYSMESSAGECOUNT);todo dealerCount 统计提取
            // dealerCountCache.modifyDealerCountCache(dealerIds[i], countTypeList.toArray(new Integer[]{}));todo dealerCount 统计提取
            list.add(dealerMessage);
        }
        dealerMessageMapper.insertBatch(list);
    }
    
    /**
     * 品牌商发送一条短信
     *
     * @param brandId
     * @param dealerId
     * @param content
     * @author 章旭楠
     */
    @Override
    public void executeSendMobMessage(String brandId, String dealerId, String content)
    {
        TextMessageSender msgSender = new TextMessageSender();
        msgSender.sendTextMessage(this.getMobile(dealerId), content);
    }
    
    /**
     * 品牌商发送一条短信
     *
     * @param brandId
     * @param dealerIds
     * @param content
     * @author 章旭楠
     */
    @Override
    public void executeSendMobMessage(String brandId, String[] dealerIds, String content)
    {
        TextMessageSender msgSender = new TextMessageSender();
        msgSender.sendTextMessage(this.getMobile(dealerIds), content);
    }
    
    private String getMobile(String dealerId)
    {
        UserInfo userm = this.userInfoMapper.selectByPrimaryKey(dealerId);
        // DealerUserm userm = this.dealerUsermCache.getCacheDealerUserm(dealerId);
        return userm.getUserMobile();
    }
    
    private List<String> getMobile(String[] dealerIds)
    {
        List<String> dealerIdList = Lists.newArrayList();
        for (String dealerId : dealerIds)
        {
            dealerIdList.add(this.getMobile(dealerId));
        }
        return dealerIdList;
    }
    
    /**
     * 删除与品牌商相关的所有消息
     *
     * @param brandId 品牌商ID
     * @author 章旭楠
     */
    @Override
    public void deleteBrandMessage(String brandId)
    {
        BrandRead brandRead = new BrandRead();
        brandRead.setBrandId(brandId);
        brandReadMapper.deleteSelective(brandRead);
    }
    
    /**
     * 删除与品牌商相关的多条消息
     *
     * @param brandId   品牌商ID
     * @param msgIdList 待删除的消息ID列表
     * @author 章旭楠
     */
    @Override
    public void deleteBrandMessage(String brandId, List<String> msgIdList)
    {
        for (String msgId : msgIdList)
        {
            this.deleteBrandMessage(brandId, msgId);
        }
    }
    
    /**
     * (物理删)删除与品牌商相关的一条已读记录
     *
     * @param brandId
     * @param msgIdList
     */
    public void deleteRealBrandRead(String brandId, List<String> msgIdList)
    {
        BrandRead brandRead = new BrandRead();
        brandRead.setBrandId(brandId);
        for (String msgId : msgIdList)
        {
            brandRead.setMsgId(msgId);
            brandReadMapper.deleteRealSelective(brandRead);
        }
    }
    
    /**
     * 逻辑删除
     * @param brandId
     * @param msgId
     */
    @Override
    public void deleteBrandMessage(String brandId, String msgId)
    {
        BrandRead brandRead = new BrandRead();
        brandRead.setBrandId(brandId);
        brandRead.setMsgId(msgId);
        brandReadMapper.deleteSelective(brandRead);
    }
    
    /**
     * 删除品牌商发送的多条消息
     *
     * @param brandId   品牌商ID
     * @param msgIdList 待删除的消息ID列表
     * @author 章旭楠
     */
    @Override
    public void deleteBrandSendMessage(String brandId, List<String> msgIdList)
    {
        for (String msgId : msgIdList)
        {
            dealerReadMapper.deleteRealDealerReadByBrandId(brandId, msgId);
            dealerMessageMapper.deleteDealerMessageByBrandId(brandId, msgId);
        }
    }
    
    /**
     * 获取消息详细信息
     *
     * @param brandId 品牌商ID
     * @param msgId   消息ID
     * @author 章旭楠
     */
    @Override
    public BrandMessage getBrandMessage(String brandId, String msgId) throws BusinessException
    {
        List<BrandMessage> brandMessages = brandMessageMapper.getBrandMessage(brandId, msgId);
        if (null == brandMessages || brandMessages.isEmpty()) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        BrandMessage brandMessage = brandMessages.get(0);
        if (null == brandMessage) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        // 添加发送人名称
        String senderName = this.getSenderName(new Integer(brandMessage.getMsgCate()), brandMessage.getDealerId());
        brandMessage.setSenderName(senderName);
        return brandMessage;
    }
    
    /**
     * 根据品牌商Id获取品牌商消息管理信息
     *
     * @param brandMessage
     * @param pagination
     * @return
     * @throws BusinessException
     * @author 章旭楠
     */
    @Override
    public PaginateResult<BrandMessage> getBrandMessage(BrandMessage brandMessage, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(brandMessage.getBrandId())) { throw new BusinessException("品牌商Id不能为空"); }
        brandMessage.setPage(pagination);
        return new PaginateResult(pagination, brandMessageMapper.findList(brandMessage));
    }
    
    /**
     * 获取品牌商消息管理信息
     *
     * @param brandMessage
     * @return
     * @throws BusinessException
     * @author 章旭楠
     */
    @Override
    public List<BrandMessage> getBrandMessage(BrandMessage brandMessage) throws BusinessException
    {
        if (StringUtils.isBlank(brandMessage.getBrandId())) { throw new BusinessException("品牌商Id不能为空"); }
        return brandMessageMapper.findList(brandMessage);
    }
    
    /**
     * (真删)删除与品牌商相关的多条消息包括已读消息
     *
     * @param brandId   品牌商ID
     * @param msgIdList 待删除的消息ID列表
     * @author 章旭楠
     */
    @Override
    public void deleteBrandMessageAndBrandRead(String brandId, List<String> msgIdList)
    {
        this.deleteRealBrandMessage(brandId, msgIdList);
        this.deleteRealBrandRead(brandId, msgIdList);
    }
    
    /**
     * (真删)品牌商相关的多条消息
     * @param brandId
     * @param msgIdList
     */
    public void deleteRealBrandMessage(String brandId, List<String> msgIdList)
    {
        BrandMessage brandMessage = new BrandMessage();
        brandMessage.setBrandId(brandId);
        for (String msgId : msgIdList)
        {
            brandMessage.setRefrenceId(msgId);
            brandMessageMapper.deleteRealSelective(brandMessage);
        }
    }
}
