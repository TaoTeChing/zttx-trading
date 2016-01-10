/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.*;
import com.zttx.web.module.brand.mapper.*;
import com.zttx.web.module.brand.model.BrandInviteModel;
import com.zttx.web.module.brand.model.BrandInviteView;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.mapper.DealerGroomMapper;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.model.DealerJoinModel;
import com.zttx.web.module.dealer.service.DealerCountService;
import com.zttx.web.module.dealer.service.DealerMessageService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.GlobalStaticTextUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 品牌商邀请经销商加盟 服务实现类
 * <p>File：BrandInvite.java </p>
 * <p>Title: BrandInvite </p>
 * <p>Description:BrandInvite </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandInviteServiceImpl extends GenericServiceApiImpl<BrandInvite> implements BrandInviteService {

    private BrandInviteMapper brandInviteMapper;
    @Autowired
    private BrandViewContactMapper brandViewContactMapper;
    @Autowired
    private DealerInfoMapper dealerInfoMapper;
    @Autowired
    private DealerMessageService dealerMessageService;
    @Autowired
    private DealerJoinMapper dealerJoinMapper;
    @Autowired
    private BrandesInfoMapper brandesInfoMapper;
    @Autowired
    private BrandRecruitMapper brandRecruitMapper;
    @Autowired
    private BrandInfoMapper brandInfoMapper;
    @Autowired
    private BrandCountMapper brandCountMapper;
    @Autowired
    private DealerGroomMapper dealerGroomMapper;
    @Autowired
    private BrandMessageMapper brandMessageMapper;
    @Autowired
    private BrandCheckMapper brandCheckMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Autowired
    private BrandCheckService brandCheckService;
    @Autowired
    private BrandViewContactService brandViewContactService;
    @Autowired
    private BrandCountService brandCountService;
    @Autowired
    private DealerCountService dealerCountService;
    @Autowired
    private BrandsCountService brandsCountService;
    @Autowired
    private BrandMessageService brandMessageService;

    @Autowired(required = true)
    public BrandInviteServiceImpl(BrandInviteMapper brandInviteMapper) {
        super(brandInviteMapper);
        this.brandInviteMapper = brandInviteMapper;
    }

    @Override
    public PaginateResult<Map<String, Object>> getInviteApplyStateList(Pagination pagination, BrandInviteModel brandInviteModel) {
        brandInviteModel.setPage(pagination);
        List<Map<String, Object>> mapList = brandInviteMapper.getInviteApplyStateList(brandInviteModel);
        if (null != mapList && mapList.size() > 0) {
            List<BrandInvite> inviteList = null;
            Map<String, Short> inviteMap = Maps.newHashMap();
            for (Map<String, Object> item : mapList) {
                item.put("emploeeNum", getCompanyRange(0, Integer.parseInt(item.get("emploeeNum").toString())));
                item.put("moneyNum", getCompanyRange(1, Integer.parseInt(item.get("moneyNum").toString())));
                String brandsId = item.get("brandsId").toString();
                String domain = GlobalStaticTextUtils.getBrandsIdSubDomain(brandsId);
                item.put("domain", domain);
            }
        }
        PaginateResult<Map<String, Object>> mapPaginateResult = new PaginateResult<>(pagination, mapList);
        return mapPaginateResult;
    }


    //类型 type： 0、公司规模　1、年营业额     number:对应的哪个范围
    public String getCompanyRange(Integer type, Integer number) {

        String[][] nameAry = {{"", "50人以下", "50-100人", "100-500人", "500-1000人", "1000人以上"}, {"", "10万以下", "10万-50万", "51万-100万", "101万-500万", "501万-1000万", "1000万以上"}};
        Integer len = nameAry[type].length;
        number = (number >= len) ? 0 : number;
        return nameAry[type][number];
    }

    @Override
    public BrandInvite getByDealerIdAndBrandsId(String dealerId, String brandesId) {
        return brandInviteMapper.getByDealerIdAndBrandsId(dealerId, brandesId);
    }

    /**
     * 查询品牌商加盟关系
     *
     * @param page
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    @Override
    public PaginateResult<Map<String, Object>> search(Pagination page, BrandInviteView brandInvite) throws BusinessException {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> list = brandInviteMapper.search(page, brandInvite);
        if (null != list && !list.isEmpty()) {
            for (Map<String, Object> item : list) {
                item.put("areaName", TagCommonConst.getFullArea(item.get("province"), item.get("city"), item.get("area"), "/"));
                int isExist = brandViewContactMapper.isExist(brandInvite.getBrandId(), item.get("id").toString(), null);
                if (isExist > 0) {
                    item.put("isExist", true);
                } else {
                    item.put("isExist", false);
                }
                String brandName = dealerInfoMapper.searchBrandsNameList((String) item.get("id"));
                item.put("brandName", brandName);
                paginateResult.setList(list);
            }
        }
        paginateResult.setPage(page);
        return paginateResult;
    }


    /**
     * 品牌商邀请终端商
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    @Override
    public BrandInviteModel addBrandInvite(BrandInviteModel brandInvite) throws BusinessException {
        Short[] auditStateAry = {BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK, BrandConstant.BrandInviteConst.APPLY_STATE_INVITE,
                BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS};
        this.auditStateValidator(brandInvite, auditStateAry);
        // crm免登陆品牌商后台操作-邀请加盟    //TODO 等王世贵有了解决方案在补充
//        String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
//        if (StringUtils.isNotBlank(crm_userId)) {
//            BrandCRMLog brandCRMLog = new BrandCRMLog();
//            brandCRMLog.setOperatorId(crm_userId);
//            brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.DEALER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
//            brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.INVITE); // 邀请加盟
//            DealerInfo dealerInfo = dealerInfoCache.getDealerInfoById(brandInvite.getDealerId());
//            BrandesInfo brandesInfo = brandesInfoCache.getBrandesInfoById(brandInvite.getBrandsId());
//            brandCRMLog.setBrandesId(brandInvite.getBrandsId());
//            brandCRMLog.setOperationDetails("邀请加盟:'终端商:" + dealerInfo.getDealerName() + "','加盟品牌:" + brandesInfo.getBrandName() + "';");
//            brandCRMLogService.addCRMLog(request, brandCRMLog);
//        }

        //核心邀请加盟逻辑，保存加盟信息，从新统计加盟数量
        return this.serviceDeal(brandInvite);
    }

    /**
     * 验证品牌商邀请规则
     *
     * @param brandInvite
     * @param auditStateAry
     * @return
     * @throws BusinessException
     */
    private BrandInvite auditStateValidator(BrandInvite brandInvite, Short[] auditStateAry) throws BusinessException {
        List<BrandInvite> inviteList = brandInviteMapper.getBrandInviteList(brandInvite.getDealerId(), brandInvite.getBrandsId(), brandInvite.getBrandId());
        if (null != inviteList && !inviteList.isEmpty()) {
            BrandInvite tempBrandInvite = inviteList.get(0);
            Short auditState = tempBrandInvite.getApplyState();
            for (Short item : auditStateAry) {
                if (item.shortValue() == auditState.shortValue()) {
                    if (auditState == 0) {
                        throw new BusinessException(DealerConst.USERM_ALREADY_JOINING);
                    } else if (auditState == 1) {
                        throw new BusinessException(DealerConst.USERM_ALREADY_JOIN);
                    } else {
                        throw new BusinessException(DealerConst.USERM_ALERDAY_INVITED);
                    }
                }
            }
            return tempBrandInvite;
        }

        return null;
    }

    /**
     * 验证品牌商邀请规则
     *
     * @param brandInvite
     * @param auditState
     * @return
     * @throws BusinessException
     */
    private BrandInvite auditStateValidator(BrandInvite brandInvite, Short auditState) throws BusinessException {
        List<BrandInvite> inviteList = brandInviteMapper.getBrandInviteList(brandInvite.getDealerId(), brandInvite.getBrandsId(), brandInvite.getBrandId());
        if (null != inviteList && !inviteList.isEmpty()) {
            BrandInvite tempBrandInvite = inviteList.get(0);
            Short tempAuditState = tempBrandInvite.getApplyState();
            if (tempAuditState.shortValue() != auditState.shortValue()) {
                throw new BusinessException(DealerConst.DEALER_AUDIT_STATE_ERROR);
            }
            return tempBrandInvite;
        }
        return null;
    }

    /**
     * 构造加盟关系数据
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    private BrandInviteModel serviceDeal(BrandInviteModel brandInvite) throws BusinessException {
        try {
            BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(brandInvite.getBrandsId());
            if (null == brandesInfo) {
                throw new BusinessException("不存在该品牌信息");
            }
            BrandInfo brandInfo = brandInfoMapper.selectByPrimaryKey(brandesInfo.getBrandId());
            if (null == brandInfo) {
                throw new BusinessException("不存在该品牌商信息");
            }
            DealerInfoModel dealerInfo = dealerInfoMapper.getDealerInfoModel(brandInvite.getDealerId());
            if (null == dealerInfo) {
                throw new BusinessException("不存在该经销商信息");
            }
            brandInvite.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
            brandInvite.setOpratorCata(BrandConstant.BrandInviteConst.OPRATOR_CATA_BRAND);
            brandInvite.setBrandId(brandInfo.getRefrenceId());
            brandInvite.setDealerName(dealerInfo.getDealerName());
            brandInvite.setBrandName(brandInfo.getComName());
            brandInvite.setBrandsName(brandesInfo.getBrandsName());
            brandInvite.setDomainName(brandesInfo.getLogoDomin());
            brandInvite.setBrandsLogo(brandesInfo.getBrandLogo());
            brandInvite.setAreaNo(brandInfo.getAreaNo());
            brandInvite.setProvinceName(brandInfo.getProvinceName());
            brandInvite.setCityName(brandInfo.getCityName());
            brandInvite.setAreaName(brandInfo.getAreaName());
            brandInvite.setInviteTime(CalendarUtils.getCurrentLong());
            brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_INVITE);
            brandInvite.setAuditMark("");
            brandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
            brandInvite.setCreateTime(CalendarUtils.getCurrentLong());
            brandInvite.setReadState(new Short("0"));

            brandInviteMapper.insert(brandInvite);

            //统计查看数量
            brandViewContactService.modifyViewContact(brandInvite.getBrandId(),brandInvite.getDealerId());

            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_INVITECOUNT);
            //统计邀请中的经销商
            brandCountService.modifyBrandCount(brandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));

            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_INVITECOUNT);
            dealerCountService.modifyDealerCount(brandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));

            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(dealerInfo.getRefrenceId());
            dealerInfo.setUserMobile(userInfo.getUserMobile());
            brandInvite.setDealerInfo(dealerInfo);
            return brandInvite;
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        return null;
    }


    /**
     * 保存接受邀请
     *
     * @param brandInvite
     * @throws BusinessException
     */
    @Override
    public void saveInviteJoin(BrandInvite brandInvite) throws BusinessException {

        // 检测是否已经存在加盟记录
        DealerJoin dealerJoin = dealerJoinMapper.findByDealerIdAndBrandsId(brandInvite.getDealerId(), brandInvite.getBrandsId());
        if (dealerJoin != null) {
            return;
        }
        BrandInvite oldBrandInvite = this.auditStateValidator(brandInvite, BrandConstant.BrandInviteConst.APPLY_STATE_INVITE);
        if (null == oldBrandInvite) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        BrandesInfo brandesInfo = brandesInfoMapper.findBrandesInfo(oldBrandInvite.getBrandId(), oldBrandInvite.getBrandsId());

        oldBrandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS);
        oldBrandInvite.setInviteState(BrandConstant.BrandInviteConst.INVITE_STATE_ACCEPT);
        oldBrandInvite.setAuditTime(CalendarUtils.getCurrentLong());
        oldBrandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
        brandInviteMapper.updateByPrimaryKey(oldBrandInvite);
        //保存合作信息
        this.insertDealerJoin(oldBrandInvite, DealerConstant.DealerJoin.JOIN_SOURCE_WEB_INVITE);

        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT);
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_INVITECOUNT);
        dealerCountService.modifyDealerCount(oldBrandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT);
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_INVITECOUNT);
        brandCountService.modifyBrandCount(oldBrandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));

        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT);
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_INVITECOUNT);
        brandsCountService.modifyBrandsCount(oldBrandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}),true);
        // 接收接受邀请消息
        BrandMessage brandMessage = new BrandMessage();
        brandMessage.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        brandMessage.setBrandId(oldBrandInvite.getBrandId());
        brandMessage.setDealerId(oldBrandInvite.getDealerId());
        brandMessage.setMsgCate(CommonConstant.Message.MSG_CATE_NET);
        String title = "【%s】接受了您的加盟申请";
        String content = "非常高兴的通知您,【%s】接受了您旗下【%s】品牌的加盟邀请,快把他加进产品线吧.";
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(oldBrandInvite.getDealerId());
        brandMessage.setMsgTitle(String.format(title, dealerInfo.getDealerName()));
        brandMessage.setMsgText(String.format(content, dealerInfo.getDealerName(), brandesInfo.getBrandsName()));
        brandMessage.setCreateTime(CalendarUtils.getCurrentLong());
        brandMessage.setRefuseReply(CommonConstant.Message.MSG_REPLY_YES);
        this.brandMessageMapper.insert(brandMessage);

        // crm免登陆经销商后台操作-接受邀请(同意加盟)         //TODO 等王世贵有了单点登录的解决方案 在补充
//        String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
//        if (StringUtils.isNotBlank(crm_userId))
//        {
//            DealerCrmLog dealerCrmLog = new DealerCrmLog();
//            dealerCrmLog.setOperatorId(crm_userId);
//            dealerCrmLog.setBrandesId(oldBrandInvite.getBrandsId());
//            dealerCrmLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.PRODUCTLINE); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
//            dealerCrmLog.setOperation(CrmConstant.CrmBrandOptionName.DELETE); // 接受邀请
//            dealerCrmLog.setOperationDetails("同意加盟:'品牌商:" + oldBrandInvite.getBrandName() + "','品牌:" + oldBrandInvite.getBrandsName() + "';");
//            dealerCrmLogService.addCRMLog(request, dealerCrmLog);
//        }

    }

    /**
     * 申请和加盟加盟,用于dubbo接口使用
     *
     * @param pagination
     * @param brandInvite
     * @return
     * @throws Exception
     */
    @Override
    public PaginateResult<BrandInvite> getBrandInvites(Pagination pagination, BrandInviteModel brandInvite) throws Exception {
        PaginateResult<BrandInvite> paginateResult = new PaginateResult<BrandInvite>();

        List<BrandInvite> inviteList = brandInviteMapper.applyOrInvite(pagination, brandInvite);
        paginateResult.setPage(pagination);
        paginateResult.setList(inviteList);
        return paginateResult;
    }


    /**
     * 保存合作信息
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    private DealerJoin insertDealerJoin(BrandInvite brandInvite, short joinSource) throws BusinessException {
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(brandInvite.getDealerId());

        DealerJoinModel join = new DealerJoinModel();
        join.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        join.setDealerId(brandInvite.getDealerId());
        join.setBrandId(brandInvite.getBrandId());
        join.setBrandsId(brandInvite.getBrandsId());
        join.setLogoName(brandInvite.getBrandsLogo());
        join.setDomainName(brandInvite.getDomainName());
        join.setApplyTime(brandInvite.getInviteTime());
        join.setJoinTime(brandInvite.getAuditTime());
        join.setStartTime(CalendarUtils.getCurrentLong());
        join.setJoinState(DealerConstant.DealerJoin.COOPERATION);
        join.setJoinSource(joinSource);
        join.setAreaNo(dealerInfo.getAreaNo());
        join.setUpdateTime(CalendarUtils.getCurrentLong());
        join.setIsPaid(false);
        join.setPaidAmount(null);
        join.setPaidTime(CalendarUtils.getCurrentLong());
        join.setDepositClearingAmount(BigDecimal.ZERO);
        join.setDepositTotalAmount(null);
        join.setJoinForm(DealerConstant.DealerJoin.JOINFORM_CASH);
        join.setCreditAmount(BigDecimal.ZERO);
        join.setCreditCurrent(BigDecimal.ZERO);
        join.setCreditPaidPercent(BigDecimal.ZERO);
        join.setDelFlag(false);
        join.setDiscount(BigDecimal.ONE);
        join.setClearingAmount(BigDecimal.ZERO);

        dealerJoinMapper.insert(join);

        join.setCheckState(BrandConstant.BrandCheckConst.AGREE_COOPERATION);
        brandCheckService.insertBrandCheck(join);

        return join;
    }


    /**
     * 品牌商保存同意加盟
     *
     * @param refrenceId
     * @throws BusinessException
     */
    @Override
    public void updateBrandInvite(String refrenceId) throws BusinessException {
        BrandInvite brandInvite = brandInviteMapper.selectByPrimaryKey(refrenceId);
        // 终端商对品牌的加盟申请
        if (BrandConstant.BrandInviteConst.OPRATOR_CATA_DEALER == brandInvite.getOpratorCata()) {
            BrandInvite oldBrandInvite = brandInvite;
            oldBrandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS);
            oldBrandInvite.setAuditTime(CalendarUtils.getCurrentLong());
            oldBrandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
            brandInviteMapper.updateByPrimaryKey(oldBrandInvite);

            this.insertDealerJoin(oldBrandInvite, DealerConstant.DealerJoin.JOIN_SOURCE_OFFLINE);

            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT);
            brandCountService.modifyBrandCount(brandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));
            // joinCount(已经加盟的品牌数)+1 applyCount(申请中的品牌数)-1

            countTypeList.clear();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT);
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT);
            dealerCountService.modifyDealerCount(brandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
            // joinCount(合作中的经销商)+1
            // applyCount(申请中的经销商)-1
            List<String> countTypeNameList = Lists.newArrayList();
            countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT);
            countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT);
            brandsCountService.modifyBrandsCount(brandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}));
        }
        // 品牌商对经销商的邀请
        if (BrandConstant.BrandInviteConst.OPRATOR_CATA_BRAND == brandInvite.getOpratorCata()) {
            DealerJoin dealerJoin = dealerJoinMapper.findByDealerIdAndBrandsId(brandInvite.getDealerId(), brandInvite.getBrandsId());
            if (dealerJoin != null) {
                throw new BusinessException("该品牌整个在跟该经销商合作中");
            }
            BrandInvite oldBrandInvite = brandInvite;
            oldBrandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS);
            oldBrandInvite.setInviteState(BrandConstant.BrandInviteConst.INVITE_STATE_ACCEPT);
            oldBrandInvite.setAuditTime(CalendarUtils.getCurrentLong());
            oldBrandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
            brandInviteMapper.updateByPrimaryKey(oldBrandInvite);
            insertDealerJoin(oldBrandInvite, DealerConstant.DealerJoin.JOIN_SOURCE_OFFLINE);

            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT);
            countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_INVITECOUNT);
            dealerCountService.modifyDealerCount(oldBrandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
            countTypeList.clear();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_INVITECOUNT);
            brandCountService.modifyBrandCount(oldBrandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));
            // joinCount(合作中的经销商)+1
            // applyCount(邀请中的经销商)-1
            List<String> countTypeNameList = Lists.newArrayList();
            countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT);
            countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_INVITECOUNT);
            brandsCountService.modifyBrandsCount(oldBrandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}));
            // 接收接受邀请消息
            BrandMessage brandMessage = new BrandMessage();
            brandMessage.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
            brandMessage.setBrandId(oldBrandInvite.getBrandId());
            brandMessage.setDealerId(oldBrandInvite.getDealerId());
            brandMessage.setMsgCate(CommonConstant.Message.MSG_CATE_NET);
            String title = "【%s】接受了您的加盟申请";
            String content = "非常高兴的通知您,【%s】接受了您旗下【%s】品牌的加盟邀请,快把他加进产品线吧.";
            DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(oldBrandInvite.getDealerId());
            BrandesInfo brandesInfo = brandesInfoMapper.findBrandesInfo(oldBrandInvite.getBrandId(), oldBrandInvite.getBrandsId());
            BrandInfo brandInfo = brandInfoMapper.selectByPrimaryKey(oldBrandInvite.getBrandId());
            brandMessage.setMsgTitle(String.format(title, dealerInfo.getDealerName()));
            brandMessage.setMsgText(String.format(content, dealerInfo.getDealerName(), brandInfo.getComName()));
            brandMessage.setCreateTime(CalendarUtils.getCurrentLong());
            brandMessage.setRefuseReply(CommonConstant.Message.MSG_REPLY_YES);
            brandMessageMapper.insert(brandMessage);
        }
    }


    /**
     * 品牌商点拒绝
     *
     * @param brandInvite
     * @throws BusinessException
     */
    @Override
    public void insertRejectApply(BrandInvite brandInvite) throws BusinessException {
        BrandInvite oldBrandInvite = auditStateValidator(brandInvite, BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (null == oldBrandInvite) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        oldBrandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_FAILURE);
        oldBrandInvite.setAuditTime(CalendarUtils.getCurrentLong());
        oldBrandInvite.setAuditMark(brandInvite.getAuditMark());
        oldBrandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
        brandInviteMapper.updateByPrimaryKey(oldBrandInvite);

        this.insertBrandCheck(oldBrandInvite);

        // crm免登陆品牌商后台操作-拒绝加盟          //TODO 等王世贵有了 单点登录解决方案 在补充
//        String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
//        if (StringUtils.isNotBlank(crm_userId))
//        {
//            BrandCRMLog brandCRMLog = new BrandCRMLog();
//            brandCRMLog.setOperatorId(crm_userId);
//            brandCRMLog.setBrandesId(brandInvite.getBrandsId());
//            brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.DEALER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
//            brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.REFUSE); // 拒绝加盟
//            DealerInfo dealerInfo = dealerInfoCache.getDealerInfoById(brandInvite.getDealerId());
//            BrandesInfo brandesInfo2 = brandesInfoCache.getBrandesInfoById(brandInvite.getBrandsId());
//            brandCRMLog.setOperationDetails("拒绝加盟:'终端商:" + dealerInfo.getDealerName() + "','加盟品牌:" + brandesInfo2.getBrandName() + "';");
//            brandCRMLogService.addCRMLog(request, brandCRMLog);
//        }
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT);
        dealerCountService.modifyDealerCount(brandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT);
        brandCountService.modifyBrandCount(brandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // applyCount(申请中的经销商)-1
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT);
        brandsCountService.modifyBrandsCount(brandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}));
    }


    /**
     * 插入验证信息
     *
     * @param brandInvite
     */
    private void insertBrandCheck(BrandInvite brandInvite) {
        BrandCheck brandCheck = new BrandCheck();
        brandCheck.setBrandId(brandInvite.getBrandId());
        brandCheck.setBrandsId(brandInvite.getBrandsId());
        brandCheck.setCheckState(BrandConstant.BrandCheckConst.REJECT_COOPERATION);
        brandCheck.setCheckTime(brandInvite.getAuditTime());
        brandCheck.setDealerId(brandInvite.getDealerId());
        brandCheck.setCreateTime(CalendarUtils.getCurrentLong());
        brandCheck.setUpdateTime(CalendarUtils.getCurrentLong());
        brandCheck.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        brandCheckMapper.insert(brandCheck);
    }

    /**
     * 指定品牌商邀请经销商加盟(用于品牌商erp)
     *
     * @param dealerId
     * @param brandsId
     * @return
     * @throws BusinessException
     */
    @Override
    public BrandInvite brand_invite_dealer(String dealerId, String brandsId, BrandInviteModel brandInvite) throws BusinessException {
        brandInvite.setBrandsId(brandsId);
        brandInvite.setDealerId(dealerId);
        if (StringUtils.isBlank(brandInvite.getInviteText())) {
            brandInvite.setInviteText("邀请加盟");
        }
        if (StringUtils.isBlank(brandInvite.getBrandsId())) {
            throw new BusinessException("品牌Id不能为空");
        }
        if (StringUtils.isBlank(brandInvite.getDealerId())) {
            throw new BusinessException("经销商Id不能为空");
        }
        BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(brandsId);
        if (brandesInfo == null) {
            throw new BusinessException("品牌商不存在");
        }
        brandInvite.setBrandId(brandesInfo.getBrandId());
//        UserInfo dealerUserm = userInfoMapper.selectByPrimaryKey(brandInvite.getDealerId());
        brandInvite = this.addBrandInvites(brandInvite);
//        if (null != dealerUserm) {
//            brandInvite.getDealerInfo().setUserMobile(dealerUserm.getUserMobile());
//        }
        // 向经绡商发送邀请加盟消息
        brandMessageService.sendDealerMessage(brandInvite.getBrandId(), brandInvite.getDealerId(), "邀请加盟", brandInvite.getInviteText());
        return brandInvite;
    }

    /**
     * 构造加盟关系数据
     *
     * @param brandInvite
     * @return
     * @throws BusinessException
     */
    @Override
    public BrandInviteModel addBrandInvites(BrandInviteModel brandInvite) throws BusinessException {
        Short[] auditStateAry = {BrandConstant.BrandInviteConst.APPLY_STATE_INVITE, BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS};
        auditStateValidator_specific(brandInvite, auditStateAry);
        return serviceDeal(brandInvite);
    }


    private BrandInvite auditStateValidator_specific(BrandInvite brandInvite, Short[] auditStateAry) throws BusinessException {
        List<BrandInvite> inviteList = brandInviteMapper.getBrandInviteList(brandInvite.getDealerId(), brandInvite.getBrandsId(), brandInvite.getBrandId());
        if (null != inviteList && !inviteList.isEmpty()) {
            BrandInvite tempBrandInvite = inviteList.get(0);
            Short auditState = tempBrandInvite.getApplyState();
            for (Short item : auditStateAry) {
                if (item.shortValue() == auditState.shortValue()) {
                    if (auditState == 1) {
                        throw new BusinessException(DealerConst.USERM_ALREADY_JOIN);
                    } else if (auditState == 3) {
                        throw new BusinessException(DealerConst.USERM_INVITE_JOINING);
                    } else {
                        throw new BusinessException(DealerConst.USERM_ALERDAY_INVITED);
                    }
                }
            }
            return tempBrandInvite;
        }
        return null;
    }

    /**
     * 撤销终端商对品牌的加盟申请
     *
     * @param brandInvite
     * @throws BusinessException
     */
    @Override
    public void updateInviteRemove(BrandInvite brandInvite) throws BusinessException {
        BrandInvite oldBrandInvite = auditStateValidator(brandInvite, BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (null == oldBrandInvite) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        oldBrandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_CANCEL);
        oldBrandInvite.setUndoTime(CalendarUtils.getCurrentLong());
        oldBrandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
        brandInviteMapper.updateByPrimaryKey(oldBrandInvite);
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT);
        dealerCountService.modifyDealerCount(oldBrandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT);
        brandCountService.modifyBrandCount(oldBrandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // applyCount(申请中的经销商)-1
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT);
        brandsCountService.modifyBrandsCount(oldBrandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}));
        // 合作的加
        brandInvite.setBrandId(oldBrandInvite.getBrandId());
    }

    /**
     * 撤销品牌商对终端商的邀请
     *
     * @param brandInvite
     * @throws BusinessException
     */
    @Override
    public void removeInvite(BrandInvite brandInvite) throws BusinessException {
        if (null == brandInvite) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_BRAND_INVITE);
        brandInvite.setUndoTime(CalendarUtils.getCurrentLong());
        brandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
        brandInviteMapper.updateByPrimaryKey(brandInvite);
        // 被邀请的经销商-1
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_INVITECOUNT);
        dealerCountService.modifyDealerCount(brandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
        // 品牌商邀请中的经销商-1
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_INVITECOUNT);
        brandCountService.modifyBrandCount(brandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // 品牌邀请中的经销商-1
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_INVITECOUNT);
        brandsCountService.modifyBrandsCount(brandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}));
        brandInvite.setBrandId(brandInvite.getBrandId());
    }

    /*
     * 终止合作
     */
    @Override
    public void discontinueDealer(DealerJoin dealerJoin, String endMark) throws BusinessException {
        if (null == dealerJoin) {
            throw new BusinessException("经销商未与该品牌商合作");
        }
        DealerJoinModel oldDealerJoin = this.joinStateValidator(dealerJoin, DealerConstant.DealerJoin.COOPERATION);
        Long upTime = CalendarUtils.getCurrentLong();
        if (null == oldDealerJoin) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        oldDealerJoin.setJoinState(DealerConstant.DealerJoin.STOP_COOPERATION);
        oldDealerJoin.setEndTime(CalendarUtils.getCurrentLong());
        oldDealerJoin.setEndMark(dealerJoin.getEndMark());
        oldDealerJoin.setUpdateTime(upTime);
        // 修改合作状态
        dealerJoinMapper.updateByPrimaryKey(oldDealerJoin);
        // 发送站内信息给终端商
        brandMessageService.sendDealerMessage(dealerJoin.getBrandId(), dealerJoin.getDealerId(), "终止合作", endMark);

        oldDealerJoin.setCheckState(BrandConstant.BrandCheckConst.STOP_COOPERATION);
        // 生成加盟申请日志
        brandCheckService.insertBrandCheck(oldDealerJoin);
        // 修改加盟申请状态
        List<BrandInvite> inviteList = brandInviteMapper.getBrandInviteList(oldDealerJoin.getDealerId(), oldDealerJoin.getBrandsId(), oldDealerJoin.getBrandId());
        if (null != inviteList && !inviteList.isEmpty()) {
            BrandInvite brandInvite = inviteList.get(0);
            brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_BRAND_STOP_COOPERATION);
            brandInvite.setUpdateTime(upTime);
            brandInviteMapper.updateByPrimaryKey(brandInvite);
        }
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT);
        dealerCountService.modifyDealerCount(dealerJoin.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT);
        brandCountService.modifyBrandCount(dealerJoin.getBrandId(), countTypeList.toArray(new Integer[]{}));
        // joinCount(合作中的经销商)-1
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT);
        brandsCountService.modifyBrandsCount(dealerJoin.getBrandsId(), countTypeNameList.toArray(new String[]{}));
    }

    /**
     * 获取当前经绡商最新加盟记录
     *
     * @param dealerJoin
     * @param joinState
     * @return
     * @throws BusinessException
     */
    private DealerJoinModel joinStateValidator(DealerJoin dealerJoin, Short joinState) throws BusinessException
    {
        List<DealerJoinModel> joinList = dealerJoinMapper.getDealerNewJoinList(dealerJoin);
        if (null != joinList && !joinList.isEmpty())
        {
            DealerJoinModel tempDealerJoin = joinList.get(0);
            Short tempJoinState = tempDealerJoin.getJoinState();
            if (tempJoinState.shortValue() != joinState.shortValue()) { throw new BusinessException(DealerConst.DEALER_JOIN_STATE_ERROR); }
            return tempDealerJoin;
        }
        return null;
    }

    /**
     * 查询品牌商加盟关系
     *
     * @param dealerId
     * @param brandsId
     * @param brandId
     * @return
     */
    @Override
    public BrandInvite getBrandInvite(String dealerId, String brandsId, String brandId) {
        List<BrandInvite> brandInviteList = brandInviteMapper.getBrandInviteList(dealerId, brandsId, brandId);
        if (brandInviteList != null && brandInviteList.size() > 0) {
            return brandInviteList.get(0);
        }
        return null;
    }


    /*
     * 同意申请
     */
    @Override
    public DealerJoin insertAgreeApply(BrandInvite brandInvite) throws BusinessException {
        BrandInvite oldBrandInvite = auditStateValidator(brandInvite, BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (null == oldBrandInvite) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        oldBrandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS);
        oldBrandInvite.setAuditTime(CalendarUtils.getCurrentLong());
        oldBrandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
        brandInviteMapper.updateByPrimaryKey(oldBrandInvite);
        DealerJoin dealerJoin = insertDealerJoin(oldBrandInvite, DealerConstant.DealerJoin.JOIN_SOURCE_WEB_APPLY);

        // crm免登陆品牌商后台操作-同意加盟
//        String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
//        if (StringUtils.isNotBlank(crm_userId))
//        {
//            BrandCRMLog brandCRMLog = new BrandCRMLog();
//            brandCRMLog.setOperatorId(crm_userId);
//            brandCRMLog.setBrandesId(brandInvite.getBrandsId());
//            brandCRMLog.setBeOperationName(CrmConstant.crmToBrandOptionModel.DEALER); // (1:产品,2:产品线,3:交易管理,4:终端商管理)
//            brandCRMLog.setOperation(CrmConstant.CrmBrandOptionName.AGREE); // 同意加盟
//            DealerInfo dealerInfo = dealerInfoCache.getDealerInfoById(brandInvite.getDealerId());
//            BrandesInfo brandesInfo2 = brandesInfoCache.getBrandesInfoById(brandInvite.getBrandsId());
//            brandCRMLog.setOperationDetails("同意加盟:'终端商:" + dealerInfo.getDealerName() + "','加盟品牌:" + brandesInfo2.getBrandName() + "';");
//            brandCRMLogService.addCRMLog(request, brandCRMLog);
//        }
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT);
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT);
        brandCountService.modifyBrandCount(brandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT);
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT);
        dealerCountService.modifyDealerCount(brandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT);
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT);
        brandsCountService.modifyBrandsCount(brandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}),true);
        return dealerJoin;
    }
    
    /**
     * 指定终端商加盟某品牌
     * @author 陈建平
     * @param brandInvite
     * @param map
     * @return
     * @throws BusinessException
     */
    @Override
    public BrandInvite updateJoinApply(BrandInvite brandInvite, Map<String, String> map) throws BusinessException{
    	if (StringUtils.isBlank(brandInvite.getBrandsId())) { throw new BusinessException("品牌Id不能为空"); }
        if (StringUtils.isBlank(brandInvite.getDealerId())) { throw new BusinessException("经销商Id不能为空"); }
        
        BrandInvite filter = new BrandInvite();
        filter.setDealerId(brandInvite.getDealerId());
        filter.setBrandId(brandInvite.getBrandsId());
        filter.setApplyState((short) 0);
        List<BrandInvite> list = findList(filter);
        BrandInvite brandInvite2 = null;
        if(null!=list && list.size()>0){
        	brandInvite2 = list.get(0);
        }
        if (null != brandInvite2) { throw new BusinessException("加盟申请正在处理中，请耐心等待"); }
        brandInvite.setInviteText(MapUtils.getString(map, "inviteText", "申请加盟"));
        BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(brandInvite.getBrandsId());
        if(null == brandesInfo){
        	throw new BusinessException("品牌不存在");
        }
        String _brandId = brandesInfo.getBrandId();
        BrandRecruit _brandRecruit = brandRecruitMapper.findByBrandIdAndBrandesid(_brandId, brandInvite.getBrandsId());
        if (_brandRecruit == null || _brandRecruit.getRecruitState() == null || _brandRecruit.getRecruitState().intValue() == 0) { 
        	throw new BusinessException("产品招募书已经关闭"); 
        }
        BrandesInfo _brandesInfo = brandesInfoMapper.selectByPrimaryKey(brandInvite.getBrandsId());
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(brandInvite.getDealerId());
        try
        {
            this.insertInvite(brandInvite);
            String context = "【%s】向您的品牌【%s】提交了加盟申请，并留言：%s。联系方式：%s";
            dealerMessageService.sendBrandMessage(brandInvite.getDealerId(), brandInvite.getBrandId(), "申请加盟",
                    String.format(context, dealerInfo.getDealerName(), _brandesInfo.getBrandsName(), brandInvite.getInviteText(), dealerInfo.getDealerTel()));
        }
        catch (BusinessException e)
        {
            throw new BusinessException(e.getErrorCode());
        }
        return brandInvite;
    }
    
    /**
     * 新增邀请加盟信息
     * @author 陈建平
     * @param brandInvite
     * @throws BusinessException
     */
    @Override
    public void insertInvite(BrandInvite brandInvite) throws BusinessException
    {
        Short[] auditStateAry = {BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK, BrandConstant.BrandInviteConst.APPLY_STATE_INVITE, BrandConstant.BrandInviteConst.APPLY_STATE_SUCCESS};
        auditStateValidator(brandInvite, auditStateAry);
        BrandesInfo brandesInfo = brandesInfoMapper.selectByPrimaryKey(brandInvite.getBrandsId());
        BrandInfo brandInfo = brandInfoMapper.selectByPrimaryKey(brandesInfo.getBrandId());
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(brandInvite.getDealerId());
        brandInvite.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandInvite.setOpratorCata(BrandConstant.BrandInviteConst.OPRATOR_CATA_DEALER);
        brandInvite.setBrandId(brandInfo.getRefrenceId());
        brandInvite.setDealerName(dealerInfo.getDealerName());
        brandInvite.setBrandName(brandInfo.getComName());
        brandInvite.setBrandsName(brandesInfo.getBrandsName());
        brandInvite.setDomainName(brandesInfo.getLogoDomin());
        brandInvite.setBrandsLogo(brandesInfo.getBrandLogo());
        brandInvite.setAreaNo(brandInfo.getAreaNo());
        brandInvite.setProvinceName(brandInfo.getProvinceName());
        brandInvite.setCityName(brandInfo.getCityName());
        brandInvite.setAreaName(brandInfo.getAreaName());
        brandInvite.setInviteTime(CalendarUtils.getCurrentLong());
        brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        brandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
        brandInvite.setCreateTime(CalendarUtils.getCurrentLong());
        if (null != brandInvite.getSourceType())
        {
            brandInvite.setSourceType(brandInvite.getSourceType());
        }
        brandInviteMapper.insert(brandInvite);
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT);
        dealerCountService.modifyDealerCount(brandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT);
        brandCountService.modifyBrandCount(brandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT);
        brandsCountService.modifyBrandsCount(brandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}));
    }
    
    /**
     * 获取指定品牌商所有加盟数据
     *
     * @param brandInvite
     * @return
     */
    @Override
    public List<BrandInvite> getBrandInvites(BrandInvite brandInvite){
    	return brandInviteMapper.getBrandInvites(brandInvite);
    }

    /**
     * 修改状态
     *
     * @param brandInvite
     * @throws BusinessException
     */
    @Override
    public void updateInvite(BrandInvite brandInvite) throws BusinessException
    {
        BrandInvite oldBrandInvite = auditStateValidator(brandInvite, BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (null == oldBrandInvite) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        oldBrandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_CANCEL);
        oldBrandInvite.setUndoTime(CalendarUtils.getCurrentLong());
        oldBrandInvite.setUpdateTime(CalendarUtils.getCurrentLong());
        brandInviteMapper.updateByPrimaryKey(oldBrandInvite);
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_APPLYCOUNT);
        dealerCountService.modifyDealerCount(oldBrandInvite.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_APPLYCOUNT);
        brandCountService.modifyBrandCount(oldBrandInvite.getBrandId(), countTypeList.toArray(new Integer[]{}));

        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT);
        brandsCountService.modifyBrandsCount(oldBrandInvite.getBrandsId(), countTypeNameList.toArray(new String[]{}));

        brandInvite.setBrandId(oldBrandInvite.getBrandId());
    }

}
