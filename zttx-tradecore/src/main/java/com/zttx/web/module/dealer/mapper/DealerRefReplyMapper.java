/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import com.zttx.web.module.dealer.model.DealerRefReplyModel;
import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerRefReply;

/**
 * 退款留言 持久层接口
 * <p>File：DealerRefReplyDao.java </p>
 * <p>Title: DealerRefReplyDao </p>
 * <p>Description:DealerRefReplyDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerRefReplyMapper extends GenericMapper<DealerRefReply>
{
    /**
     * 根据退款编号查找
     * @param refundId
     * @return
     */
    List<DealerRefReplyModel> listByRefundId(@Param("refundId") String refundId);
}
