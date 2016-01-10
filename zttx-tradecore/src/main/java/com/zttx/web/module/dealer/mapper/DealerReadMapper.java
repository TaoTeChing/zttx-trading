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
import com.zttx.web.module.dealer.entity.DealerRead;

/**
 * 经销商消息阅读 持久层接口
 * <p>File：DealerReadDao.java </p>
 * <p>Title: DealerReadDao </p>
 * <p>Description:DealerReadDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerReadMapper extends GenericMapper<DealerRead>
{
    /**
     * 根据经销商ID，获取未读消息ID列表
     * @param dealerId 经销商ID
     * @return 消息ID列表
     * @author 章旭楠
     */
    List<String> getUnReadMsgIdList(String dealerId);
    
    /**
     * 获取与经销商相关的多条已读记录列表
     *
     * @param dealerId  经销商ID （可以为null）
     * @param msgIdList 消息ID列表
     * @return 与消息ID列表相关的已读记录
     */
    List<DealerRead> getDealerReadList(@Param("dealerId") String dealerId, @Param("msgIdList") List<String> msgIdList);
    
    /**
     * 获取品牌商发送的消息状态
     *
     * @param brandId
     * @param msgIdList
     * @return
     */
    List<DealerRead> getDealerReadListByBrandId(String brandId, List<String> msgIdList);
    
    /**
     * 判断与经销商相关的已读记录是否已存在
     *
     * @param dealerId 经销商ID
     * @param msgId    消息ID
     * @return
     * @author 章旭楠
     */
    List<Integer> isExistDealerRead(@Param("dealerId") String dealerId, @Param("msgId") String msgId);
    
    /**
     * (假删)删除与经销商相关的一条已读记录
     *
     * @param dealerId 经销商ID （if null 即根据 msgId删除）
     * @param msgId    待删除的消息ID（if null 即为删除与经销商相关的所有已读记录）
     * @author 章旭楠
     */
    void deleteDealerRead(@Param("dealerId") String dealerId, @Param("msgId") String msgId);
    
    /**
     * (真删)删除与经销商相关的一条已读记录
     *
     * @param dealerId 经销商ID
     * @param msgId    待删除的消息ID（如果为空 即删除与经销商相关的所有已读记录）
     */
    void deleteRealDealerRead(@Param("dealerId") String dealerId, @Param("msgId") String msgId);
    
    /**
     * (真删)删除与品牌商相关的一条已读记录
     *
     * @param brandId 品牌商ID
     * @param msgId   待删除的消息ID
     * @author 章旭楠
     */
    void deleteRealDealerReadByBrandId(@Param("brandId") String brandId, @Param("msgId") String msgId);
    
    /**
     * 根据经销商Id获取经销商消息阅读信息
     * @param dealerId
     * @param pagination
     * @return
     */
    List<DealerRead> getDealerReads(String dealerId, Pagination pagination);
}
