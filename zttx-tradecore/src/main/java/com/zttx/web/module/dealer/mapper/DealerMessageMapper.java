/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerMessage;

/**
 * 经销商消息管理 持久层接口
 * <p>File：DealerMessageDao.java </p>
 * <p>Title: DealerMessageDao </p>
 * <p>Description:DealerMessageDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerMessageMapper extends GenericMapper<DealerMessage>
{
    int insertBatch(@Param("entityList") List<DealerMessage> dealerMessageList);
    
    /**
     * 统计经销商未读消息
     * @author 章旭楠
     * @param dealerId
     * @return 未读消息数目
     */
    Long countDealerUnReadMessages(String dealerId);
    
    /**
     * （物理删）删除品牌商发送的消息
     * @param brandId
     * @param msgId
     */
    void deleteDealerMessageByBrandId(@Param("brandId") String brandId, @Param("msgId") String msgId);
    
    /**
     * 逻辑删除经销商消息
     * @param dealerId
     * @param msgId
     */
    void deleteDealerMessageByDealerId(@Param("dealerId") String dealerId, @Param("msgId") String msgId);
    
    /**
     * 获取消息
     * @param brandId
     * @param msgId
     * @return
     */
    DealerMessage getMsgWithBrand(@Param("brandId") String brandId, @Param("msgId") String msgId);
    
    /**
     * 获取消息
     * @param dealerId
     * @param msgId
     * @return
     */
    DealerMessage getMsgWithDealer(@Param("dealerId") String dealerId, @Param("msgId") String msgId);
    
    /**
     *
     * @param dealerId
     * @return
     */
    List<String> getUnReadMsgIdList(String dealerId);
    
    /**
     * @param page
     * @param dealerMessageModel 注意dealerMessageModel.dealerId 不为空
     * @return
     */
    List<DealerMessage> listDealerMessage(@Param("page") Pagination page, @Param("dealerMessageModel") DealerMessage dealerMessageModel);
}
