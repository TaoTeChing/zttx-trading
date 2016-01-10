/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerRefReply;
import com.zttx.web.module.dealer.model.DealerRefReplyModel;

import java.util.List;

/**
 * 退款留言 服务接口
 * <p>File：DealerRefReplyService.java </p>
 * <p>Title: DealerRefReplyService </p>
 * <p>Description:DealerRefReplyService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerRefReplyService extends GenericServiceApi<DealerRefReply>{

    /**
     * 根据退款编号查找
     * @param refundId
     * @return
     */
    List<DealerRefReplyModel> listByRefundId(String refundId);
}
