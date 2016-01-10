/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandMessage;

/**
 * 品牌商消息管理 持久层接口
 * <p>File：BrandMessageDao.java </p>
 * <p>Title: BrandMessageDao </p>
 * <p>Description:BrandMessageDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandMessageMapper extends GenericMapper<BrandMessage> {
    /**
     * 获取与品牌商相关的消息
     *
     * @param brandMessageModel
     * @return
     */
    List<Map<String, Object>> listBrandMessage(BrandMessage brandMessageModel);

    /**
     * 获取品牌商已发送的消息
     *
     * @param brandMessageModel
     * @return
     */
    List<Map<String, Object>> listBrandSendMessage(BrandMessage brandMessageModel);

    /**
     * 获取未读的消息条数
     *
     * @param brandId
     * @return
     */
    Long countBrandMessage(String brandId);

    /**
     * 获取消息详细信息
     *
     * @param brandId
     * @param msgId
     * @return
     */
    List<BrandMessage> getBrandMessage(@Param("brandId")String brandId, @Param("msgId")String msgId);

    void deleteRealSelective(BrandMessage brandMessage);
}
