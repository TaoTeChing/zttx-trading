/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandInvite;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.model.BrandInviteView;
import com.zttx.web.module.dealer.entity.DealerJoin;

import java.util.List;
import java.util.Map;

/**
 * 品牌商邀请经销商加盟 服务接口
 * <p>File：BrandInviteService.java </p>
 * <p>Title: BrandInviteService </p>
 * <p>Description:BrandInviteService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandInviteService extends GenericServiceApi<BrandInvite>{
    /**
     * 分页查询
     * @param pagination
     * @param brandInviteModel
     * @return
     */
    PaginateResult<Map<String,Object>> getInviteApplyStateList(Pagination pagination, BrandInviteModel brandInviteModel);
    /**
     * 根据经销商id和品牌id获取邀请
     * @param dealerId
     * @param brandesId
     * @return
     */
    BrandInvite getByDealerIdAndBrandsId(String dealerId,String brandesId);

    /**
     * 查询品牌商加盟关系
     *
     * @param page
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    public PaginateResult<Map<String, Object>> search(Pagination page, BrandInviteView brandInvite) throws BusinessException;

    /**
     * 品牌商邀请终端商
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    public BrandInviteModel addBrandInvite(BrandInviteModel brandInvite) throws BusinessException;

    /**
     * 保存接受邀请
     *
     * @param brandInvite
     * @throws BusinessException
     */
    public void saveInviteJoin(BrandInvite brandInvite) throws BusinessException;

    /**
     * 查询加盟关系列表
     *
     * @param pagination
     * @param brandInvite
     * @return
     * @throws Exception
     */
    public PaginateResult<BrandInvite> getBrandInvites(Pagination pagination,BrandInviteModel brandInvite) throws Exception;

    /**
     * 品牌商保存同意加盟
     *
     * @param refrenceId
     * @throws BusinessException
     */
    public void updateBrandInvite(String refrenceId) throws BusinessException;

    /**
     * 品牌商点拒绝
     *
     * @param brandInvite
     * @throws BusinessException
     */
    public void insertRejectApply(BrandInvite brandInvite) throws BusinessException;

    /**
     * 指定品牌商邀请经销商加盟(用于品牌商erp)
     *
     * @param dealerId
     * @param brandsId
     * @return
     * @throws BusinessException
     */
    public BrandInvite brand_invite_dealer(String dealerId, String brandsId, BrandInviteModel brandInvite) throws BusinessException;

    /**
     * 添加加盟关系
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    public BrandInviteModel addBrandInvites(BrandInviteModel brandInvite) throws BusinessException;

    /**
     * 撤销申请
     * @param brandInvite
     * @throws BusinessException
     */
    public void updateInviteRemove(BrandInvite brandInvite) throws BusinessException;

    /**
     * 撤销邀请
     *
     * @param brandInvite
     * @throws BusinessException
     */
    public void removeInvite(BrandInvite brandInvite) throws BusinessException;

    /**
     * 终止合作
     * @param dealerJoin
     * @param endMark
     * @throws BusinessException
     */
    public void discontinueDealer(DealerJoin dealerJoin, String endMark) throws BusinessException;
    
    /**
     * 指定终端商加盟某品牌
     * @author 陈建平
     * @param brandInvite
     * @param map
     * @return
     * @throws BusinessException
     */
    BrandInvite updateJoinApply(BrandInvite brandInvite, Map<String, String> map) throws BusinessException;
    
    /**
     * 新增邀请加盟信息
     * @author 陈建平
     * @param brandInvite
     * @throws BusinessException
     */
    void insertInvite(BrandInvite brandInvite) throws BusinessException;

    /**
     * 查询品牌商加盟关系
     *
     * @param dealerId
     * @param brandsId
     * @param brandId
     * @return
     */
    public BrandInvite getBrandInvite(String dealerId, String brandsId, String brandId);

    /*
     * 同意申请
     */
    public DealerJoin insertAgreeApply(BrandInvite brandInvite) throws BusinessException;
    
    /**
     * 获取指定品牌商所有加盟数据
     *
     * @param brandInvite
     * @return
     */
    List<BrandInvite> getBrandInvites(BrandInvite brandInvite);

    /**
     * 修改状态
     *
     * @param brandInvite
     * @throws BusinessException
     */
    public void updateInvite(BrandInvite brandInvite) throws BusinessException;

}
