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
import com.zttx.web.module.dealer.entity.DealerBuySerLog;

/**
 * 经销商购买的服务记录 服务接口
 * <p>File：DealerBuySerLogService.java </p>
 * <p>Title: DealerBuySerLogService </p>
 * <p>Description:DealerBuySerLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerBuySerLogService extends GenericServiceApi<DealerBuySerLog>
{
    /**
     * 分页查询
     * @param page 分页条件
     * @param searchBean：过滤条件
     * 		buyTime			购买时间（搜索所有指定时间当天购买的数据）
     *		startSearchTime	开始搜索时间（搜索所有指定时间后购买的数据）
     *		endSearchTime	结束搜索时间（搜索所有指定时间前购买的数据）
     * 		refrenceId		资料编号
     *		dealerId		经销商编号
     *		serviceId		服务编号
     *		servicerCate	服务类别
     *		buyState		支付状态（DealerConstant.DealerBuySerLog.BUY_STATE_CREATE）
     *		chargType		购买类型（1：续期、2：增加数量）
     * @return
     */
    PaginateResult<DealerBuySerLog> searchByClient(Pagination page, DealerBuySerLog searchBean);
    
    /**
     * 新增经销商购买的服务记录
     * @param dealerBuySerLog 服务记录
     * @throws BusinessException
     */
    void createDealerBuySerLog(DealerBuySerLog dealerBuySerLog) throws BusinessException;
    
    void addDealerBuySerLog(DealerBuySerLog dealerBuySerLog);
    
    /**
     * 查询
     * @param userId
     * @param orderIdArr
     * @return
     * @author 张昌苗
     */
    List<DealerBuySerLog> listWithException(String userId, String[] orderIdArr) throws BusinessException;
}
