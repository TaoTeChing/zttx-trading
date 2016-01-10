/*
 * Copyright 2015 Playguy, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.dubbo.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.model.BrandInviteModel;


/**
 * 
 * <p>File：BrandInviteDubboService.java</p>
 * <p>Title: BrandInviteDubboService</p>
 * <p>Description:BrandInviteDubboService</p>
 * <p>Copyright: Copyright (c) 2015年9月6日</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
public interface BrandInviteDubboService {

    /**
     * 获取加盟关系列表
     * @param pagination
     * @param brandInvite
     * @return
     * @throws Exception
     */
    public PaginateResult<BrandInvite> applyOrInvite(Pagination pagination,BrandInviteModel brandInvite) throws Exception;
    
    /**
     * 同意加盟
     * @param refrenceId
     * @return
     * @throws Exception
     */
    public void agreeApply(String refrenceId) throws BusinessException;
    /**
     * 拒绝加盟
     * @param auditMark
     * @return
     * @throws Exception
     */
    public void refuseDealer(String refrenceId, String auditMark) throws BusinessException ;

    /**
     * 指定品牌商邀请经销商加盟(用于品牌商erp)
     * @param dealerId
     * @param brandsId
     * @return
     * @throws BusinessException
     */
    public BrandInvite inviteJoinBrandErp(String dealerId, String brandsId,String inviteText) throws BusinessException;

    /**
     * 移除加盟
     * @param refrenceId
     * @return
     * @throws Exception
     */
    public  void  removeApply( String  refrenceId) throws BusinessException;
    /**
     * 终止合作
     * @param refrenceId
     * @return
     * @throws Exception
     */
    public  void  discontinueDealer( String  refrenceId) throws BusinessException;
    
}
