/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.dealer.entity.DealerAudit;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.model.DealerShopEnvModel;

/**
 * 经销商基础信息 服务接口
 * <p>File：DealerInfoService.java </p>
 * <p>Title: DealerInfoService </p>
 * <p>Description:DealerInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerInfoService extends GenericServiceApi<DealerInfo>
{
    /**
     * 根据 主键 获取 实体
     * @param dealerId
     * @return
     * @author 易永耀
     */
    public DealerInfo getDealerInfo(String dealerId);
    
    /**
     * 根据终端商id获取终端商数据模型类
     *
     * @param dealerId
     * @return
     */
    public DealerInfoModel findById(String dealerId);
    
    /**
     * 分页查询 寻找更多经销商列表
     * @param page
     * @param info
     * @return
     */
    PaginateResult<Map<String, Object>> search(Pagination page, DealerInfoModel info);
    
    /**
     * 判断Dealer是否授权
     * @param dealerId
     * @return
     * @throws BusinessException
     */
    boolean isAuthorized(String dealerId) throws BusinessException;
    
    /**
     * 查询所有终端商基础信息
     * 包括ID,名称
     * @return
     */
    List<Map<String, Object>> findAllDealerBaseInfo();
    
    /**
     * 根据手机号码和店铺名称查询经销商信息(经销商明细表为主)
     * @param show 0表示全部显示，1：显示有图片的经销商(图片来源dealerImage)
     * @param userMobile 手机号
     * @param dealerName 店铺名称
     * @param pagination 分页
     * @return PaginateResult
     */
    PaginateResult<Map<String, Object>> getDealerInfosByClient(Boolean show, String userMobile, String dealerName, Pagination pagination);

    /**
     * 根据dealerId查询
     * @param dealerId
     * @return
     */
    public List<Map<String, Object>> findDealerClassById(String dealerId);
    
    /**
     * 更新dealerInfo
     * @param dealerInfo
     * @param request
     * @return
     * @throws BusinessException
     */
    DealerInfo modDealerInfoMore(DealerInfoModel dealerInfo, HttpServletRequest request) throws BusinessException;
    
    /**
     * 根据终端商编号获取
     * @author 陈建平
     * @param dealerIdList
     * @return
     */
    List<DealerInfo> getDealerInfos(List<String> dealerIdList);
    
    /**
     * 获取App需要同步的终端商数据
     * @author 陈建平
     * @param dealerId
     * @return
     */
    List<Map<String, Object>> listAppDealerInfo(String dealerId);
    
    /**
     * 支撑平台的 终端商账号添加
     * @author 陈建平
     * @param userInfo
     * @param dealerInfo
     * @param dealerShopEnvModel
     * @return
     * @throws BusinessException
     */
    String addDealerAccount(UserInfo userInfo, DealerInfoModel dealerInfo, DealerShopEnvModel dealerShopEnvModel) throws BusinessException;
    
    /**
     * 支撑平台的 终端商账号添加
     * @author 陈建平
     * @param dealerShopEnv
     * @return
     * @throws BusinessException
     */
    String addDealerShoperEnv(DealerShopEnvModel dealerShopEnv) throws BusinessException;
    
    /**
     * 修改审核状态
     * @param dealerAudit
     *		dealerId	经销商主帐号编号				（必填）
     *		checkState	审核状态（1：通过，2：不通过）	（必填）
     *		userId		审核人员编号			            （必填）
     *		checkMark	审核不通过说明			             （不通过时，必填）
     */
    void updateState(DealerAudit dealerAudit) throws BusinessException;
    
    /**
     * 修改当前经销商 是否开启短信收货验证
     * @param dealerId
     * @param bRcvSmsVerify
     * @return
     */
    boolean modifyDealerInfoRcvSmsVerify(String dealerId, boolean bRcvSmsVerify);
}
