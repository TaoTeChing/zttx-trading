/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandMessage;

import java.util.List;
import java.util.Map;

/**
 * 品牌商消息管理 服务接口
 * <p>File：BrandMessageService.java </p>
 * <p>Title: BrandMessageService </p>
 * <p>Description:BrandMessageService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandMessageService extends GenericServiceApi<BrandMessage>{
    /**
     * （分页）根据查询条件BrandMessageModel，获取与品牌商相关的消息
     * @param page 分页参数
     * @param brandMessageModel 查询条件
     * @return PaginateResult<Map<String, Object>> 查询结果
     * @author 章旭楠
     */
    PaginateResult<Map<String, Object>> listBrandMessage(Pagination page, BrandMessage brandMessageModel);

    /**
     * （分页）根据查询条件BrandMessageModel，获取品牌商已发送的消息
     * @param page 分页参数
     * @param brandMessageModel 查询条件
     * @return PaginateResult<Map<String, Object>> 查询结果
     * @author 章旭楠
     */
    PaginateResult<Map<String, Object>> listBrandSendMessage(Pagination page, BrandMessage brandMessageModel);
    
    /**
     * 获取未读的消息条数
     * @param brandId 品牌商ID
     * @return Long 未读的消息条数
     * @author 章旭楠
     */
    Long getBrandMessageCount(String brandId);
    
    /**
     * 品牌商向一个经销商发送消息
     * @param brandId 品牌商ID（不能为空）
     * @param dealerId 经销商ID（不能为空）
     * @param title 消息标题（不能为空）
     * @param content 消息内容
     * @author 章旭楠
     */
    void sendDealerMessage(String brandId, String dealerId, String title, String content);
    
    /**
     * 品牌商向一个经销商发送消息(订单消息)
     * @param brandId
     * @param dealerId
     * @param title
     * @param content
     * @author 章旭楠
     */
    void sendDealerOrderMessage(String brandId, String dealerId, String title, String content);
    
    /**
     * 品牌商向多个经销商发送消息
     * @param brandId 品牌商ID（不能为空）
     * @param dealerIds 多个经销商ID（不能为空）
     * @param title 消息标题（不能为空）
     * @param content 消息内容
     * @author 章旭楠
     */
    void sendDealerMessage(String brandId, String[] dealerIds, String title, String content);
    
    /**
     * 品牌商发送一条短信
     * @param brandId
     * @param dealerId
     * @param content
     * @author 章旭楠
     */
    void executeSendMobMessage(String brandId, String dealerId, String content);
    
    /**
     * 品牌商发送一条短信
     * @param brandId
     * @param dealerIds
     * @param content
     * @author 章旭楠
     */
    void executeSendMobMessage(String brandId, String[] dealerIds, String content);
    
    /**
     * 删除与品牌商相关的所有消息
     * @param brandId 品牌商ID
     * @author 章旭楠
     */
    void deleteBrandMessage(String brandId);

    void deleteBrandMessage(String brandId, String msgId);
    
    /**
     * 删除与品牌商相关的多条消息
     * @param brandId 品牌商ID
     * @param msgIdList 待删除的消息ID列表
     * @author 章旭楠
     */
    void deleteBrandMessage(String brandId, List<String> msgIdList);
    
    /**
     * 删除品牌商发送的多条消息
     * @param brandId 品牌商ID
     * @param msgIdList 待删除的消息ID列表
     * @author 章旭楠
     */
    void deleteBrandSendMessage(String brandId, List<String> msgIdList);
    
    /**
     * 获取消息详细信息
     * @param brandId 品牌商ID
     * @param msgId 消息ID
     * @author 章旭楠
     */
    BrandMessage getBrandMessage(String brandId, String msgId) throws BusinessException;
    
    /**
     * 根据品牌商Id获取品牌商消息管理信息
     * @param brandMessage
     * @param pagination
     * @return
     * @throws BusinessException
     * @author 章旭楠
     */
    PaginateResult<BrandMessage> getBrandMessage(BrandMessage brandMessage, Pagination pagination) throws BusinessException;
    
    /**
     *获取品牌商消息管理信息
     * @param brandMessage
     * @return
     * @throws BusinessException
     * @author 章旭楠
     */
    List<BrandMessage> getBrandMessage(BrandMessage brandMessage) throws BusinessException;
    
    /**
     * (真删)删除与品牌商相关的多条消息包括已读消息
     * @param brandId 品牌商ID
     * @param msgIdList 待删除的消息ID列表
     * @author 章旭楠
     */
    void deleteBrandMessageAndBrandRead(String brandId, List<String> msgIdList);


}
