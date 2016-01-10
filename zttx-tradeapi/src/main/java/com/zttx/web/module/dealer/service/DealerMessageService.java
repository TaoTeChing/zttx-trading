/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerMessage;

/**
 * 经销商消息管理 服务接口
 * <p>File：DealerMessageService.java </p>
 * <p>Title: DealerMessageService </p>
 * <p>Description:DealerMessageService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerMessageService extends GenericServiceApi<DealerMessage>
{
    /**
     * 获取未读的消息条数
     * @author 章旭楠
     * @param dealerId
     * @return 未读的消息条数
     */
    Long getDealerMessageCount(String dealerId);
    
    /**
     * 根据品牌商ID和消息ID获取消息对象
     * @param brandId
     * @param msgId
     * @return
     * @throws BusinessException
     */
    DealerMessage getMsgWithBrand(String brandId, String msgId) throws BusinessException;
    
    /**
     * 根据经销商ID和消息ID获取消息对象
     * @param dealerId 经销商ID
     * @param msgId 消息ID
     * @return DealerMessage 消息对象
     * @author 章旭楠
     */
    DealerMessage getMsgWithDealer(String dealerId, String msgId) throws BusinessException;
    
    /**
     * 经销商向品牌商发送站内消息
     * @param dealerId
     * @param brandId
     * @param title
     * @param content
     * @throws BusinessException
     */
    void sendBrandMessage(String dealerId, String brandId, String title, String content) throws BusinessException;
    
    /**
     * 获取与经销商相关的消息
     * @param page
     * @param dealerMessageModel
     * @return
     */
    PaginateResult<DealerMessage> listDealerMessage(Pagination page, DealerMessage dealerMessageModel) throws BusinessException;
    
    /**
     * (假删)删除与经销商相关的多条消息
     * @param dealerId 经销商ID
     * @param msgIdList 待删除的消息ID列表 (可以放null)
     * @author 章旭楠
     */
    void deleteDealerMessage(String dealerId, List<String> msgIdList);
}
