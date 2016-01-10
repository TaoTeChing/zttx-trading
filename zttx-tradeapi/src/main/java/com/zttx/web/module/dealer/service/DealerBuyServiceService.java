/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerBuySerLog;
import com.zttx.web.module.dealer.entity.DealerBuyService;

/**
 * 经销商购买的服务 服务接口
 * <p>File：DealerBuyServiceService.java </p>
 * <p>Title: DealerBuyServiceService </p>
 * <p>Description:DealerBuyServiceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerBuyServiceService extends GenericServiceApi<DealerBuyService>
{
    /**
     * 列表（分页）
     * @param page
     * @param searchBean：过滤条件
     * 		buyTime			购买时间（搜索所有指定时间当天购买的数据）
     *		startSearchTime	开始搜索时间（搜索所有指定时间后购买的数据）
     *		endSearchTime	结束搜索时间（搜索所有指定时间前购买的数据）
     * 		refrenceId		资料编号
     * 		dealerName		经销商名称（模糊）
     *		serviceId		服务编号
     *		servicerCate	服务类别
     * @return
     * @author 周光暖
     */
    PaginateResult<DealerBuyService> searchByClient(Pagination page, DealerBuyService searchBean);
    
    /**
     * 新增/修改（第三方调用，会同时维护经销商购买的服务表）
     * @param dealerBuySerLog
     * 		refrenceId			经销商购买的服务表的主键（由第三方调用者提供）
     * 		dealerBuyServiceId	经销商购买的服务的主键（由第三方调用者提供，若记录不存在：新增、记录已存在：修改）
     * 		dealerId			经销商编号（必填）
     * 		dealerName			经销商名称
     *		serviceId			服务编号（必填）
     *		servicerCate		服务类别（读字典：字典编码 servicerCate）（必填）
     *		buyNum 				购买数量（必填）
     *		buyMoney			购买的金额（必填）
     *		chargType			购买类型（1：续期、2：增加数量）（必填）
     *		beginTime			服务开始时间
     *		endTime				服务结束时间
     * @author 周光暖
     */
    DealerBuyService saveByClient(DealerBuySerLog dealerBuySerLog) throws BusinessException;
    
    /**
     * 根据经销商编号和服务编号查询记录
     * @param dealerId
     * @param serviceId
     * @return
     */
    DealerBuyService findBy(String dealerId, String serviceId);
    
    /**
     *
     * @param dealerId
     * @throws BusinessException
     */
    void addTrialErpDealerBuyService(String dealerId) throws BusinessException;
}
