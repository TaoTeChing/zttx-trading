/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerRefAttacht;

/**
 * 退款附件 服务接口
 * <p>File：DealerRefAttachtService.java </p>
 * <p>Title: DealerRefAttachtService </p>
 * <p>Description:DealerRefAttachtService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerRefAttachtService extends GenericServiceApi<DealerRefAttacht>
{
    /**
     * 根据退款编号返回最新退款附件
     * @param refundId
     * @return
     */
    List<DealerRefAttacht> listByRefundId(String refundId);
    
    /**
     * 创建DealerRefAttacht实例的集合(属性设置不完整)
     * @param attachtNames 附件名称(凭证图片url地址)的数组
     * @param domainName 附件域名(凭证图片域名)
     * @return
     */
    List<DealerRefAttacht> newListBydomainNames(String[] attachtNames, String domainName);

    /**
     * 批量新增(指定refundId)
     * @param dealerRefAttachts
     * @param refundId 经销商退款信息表主键
     * @param userId 用户编号
     * @param userName 用户名称
     * @param replyId 回复编号
     */
    void insertList(List<DealerRefAttacht> dealerRefAttachts, String refundId, String userId, String userName, String replyId);
}
