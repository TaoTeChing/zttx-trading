/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;

/**
 * 退款附件 持久层接口
 * <p>File：DealerRefAttachtDao.java </p>
 * <p>Title: DealerRefAttachtDao </p>
 * <p>Description:DealerRefAttachtDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerRefAttachtMapper extends GenericMapper<DealerRefAttacht>
{
    /**
     * 根据退款编号查找
     * @param refundId
     * @return
     */
    List<DealerRefAttacht> listByRefundId(@Param("refundId") String refundId);
    
    /**
     * 根据退款编号和留言编号查找
     * @param refundId
     * @param replyId
     * @return
     */
    List<DealerRefAttacht> listByRefundIdAndReplyId(@Param("refundId") String refundId, @Param("replyId") String replyId);

}
