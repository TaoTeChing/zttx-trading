/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.*;
import com.zttx.web.module.brand.mapper.BrandInviteMapper;
import com.zttx.web.module.brand.mapper.BrandLevelMapper;
import com.zttx.web.module.brand.model.BrandJoinFilter;
import com.zttx.web.module.brand.service.*;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.entity.UserOperationLog;
import com.zttx.web.module.common.model.TransferSearchModel;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.common.service.UserOperationLogService;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.module.dealer.model.DealerDepositView;
import com.zttx.web.module.dealer.model.DealerJoinModel;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EncodeUtils;
import com.zttx.web.utils.GlobalStaticTextUtils;
import com.zttx.web.utils.ListUtils;

/**
 * 经销商加盟信息 服务实现类
 * <p>File：DealerJoin.java </p>
 * <p>Title: DealerJoin </p>
 * <p>Description:DealerJoin </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerJoinServiceImpl extends GenericServiceApiImpl<DealerJoin> implements DealerJoinService
{
    @Autowired
    private DealerInfoService        dealerInfoService;
    
    @Autowired
    private BrandMessageService      brandMessageService;
    
    @Autowired
    private BrandLevelMapper         brandLevelMapper;
    
    @Autowired
    private BrandCheckService        brandCheckService;
    
    @Autowired
    private BrandInviteService       brandInviteService;
    
    @Autowired
    private BrandInviteMapper        brandInviteMapper;
    
    @Autowired
    private BrandesInfoService       brandesInfoService;
    
    @Autowired
    private UserOperationLogService  userOperationLogService;
    
    @Autowired
    private DealerCountService       dealerCountService;
    
    @Autowired
    private BrandCountService        brandCountService;
    
    @Autowired
    private BrandContactService      brandContactService;
    
    @Autowired
    private DealDicService           dealDicService;
    
    @Autowired
    private UserInfoService          userInfoService;
    
    @Autowired
    private DealerTerminaService     dealerTerminaService;
    
    @Autowired
    private BrandsCountService       brandsCountService;
    
    @Autowired
    private BrandDealService         brandDealService;
    
    private DealerJoinMapper         dealerJoinMapper;
    
    @Autowired
    private DealerOrderMapper        dealerOrderMapper;
    
    @Autowired
    private DealerClassService       dealerClassService;
    
    @Autowired
    private BrandNetworkService      brandNetworkService;
    
    @Autowired
    private RegionalService          regionalService;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired(required = true)
    public DealerJoinServiceImpl(DealerJoinMapper dealerJoinMapper)
    {
        super(dealerJoinMapper);
        this.dealerJoinMapper = dealerJoinMapper;
    }
    
    /**
     * 根据终端商id和加盟状态 查询加盟信息
     * @param dealerId
     * @param joinState
     * @return
     */
    @Override
    public List<Map<String, Object>> findByDealerId(String dealerId, Short joinState)
    {
        if (null != dealerId) { return dealerJoinMapper.findByDealerId(dealerId, joinState.toString()); }
        return null;
    }
    
    @Override
    public PaginateResult<Map<String, Object>> findByDealerId(Pagination page, String dealerId)
    {
        PaginateResult<Map<String, Object>> result = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> list = dealerJoinMapper.findJoinByDealerId(dealerId, page);
        Map<String, Object> item;
        if (list != null && list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                item = list.get(i);
                item.put("brandDeal", getBrandesDeal((String) item.get("brandsId")));
                int proCount = 0;
                // 经销商是否支付押金
                DealerJoin join = dealerJoinMapper.findByDealerIdAndBrandsId(dealerId, (String) item.get("brandsId"));
                if (join.getDepositTotalAmount() == null)
                {
                    item.put("depositTotalAmount", null);
                    item.put("paidAmount", null);
                    item.put("needToPay", null);
                }
                else
                {
                    item.put("depositTotalAmount", join.getDepositTotalAmount());
                    item.put("paidAmount", join.getPaidAmount());
                    item.put("needToPay", join.getDepositTotalAmount().subtract(join.getPaidAmount() == null ? BigDecimal.ZERO : join.getPaidAmount()));
                }
                item.put("proCount", proCount);
                item.put("brandMark", EncodeUtils.unscapeHtml((String) item.get("brandMark")));
                BrandContact contact = brandContactService.findByBrandId((String) item.get("brandId"));
                UserInfo brandUserm = userInfoService.selectByPrimaryKey((String) item.get("brandId"));
                if (contact != null)
                {
                    item.put("userTelphone", contact.getUserTelphone());
                    item.put("userMobile", contact.getUserMobile());
                }
                else
                {
                    if (brandUserm != null)
                    {
                        item.put("userMobile", brandUserm.getUserMobile());
                    }
                }
            }
        }
        result.setList(list);
        result.setPage(page);
        return result;
    }
    
    /*
     * 终端商终止合作
     */
    @Override
    public void updateStopDealerJoinState(DealerJoinModel dealerJoin) throws BusinessException
    {
        DealerJoin oldDealerJoin = joinStateValidator(dealerJoin, DealerConstant.DealerJoin.COOPERATION);
        Long upTime = CalendarUtils.getCurrentLong();
        if (null == oldDealerJoin) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        oldDealerJoin.setJoinState(DealerConstant.DealerJoin.STOP_COOPERATION_DEALER);
        oldDealerJoin.setEndTime(CalendarUtils.getCurrentLong());
        oldDealerJoin.setEndMark(dealerJoin.getEndMark());
        oldDealerJoin.setUpdateTime(upTime);
        // 修改合作状态
        dealerJoinMapper.updateByPrimaryKey(oldDealerJoin);
        dealerTerminaService.insertDealerTermina(oldDealerJoin, dealerJoin.getStopUserId());
        // 修改加盟申请状态
        List<BrandInvite> inviteList = brandInviteMapper.getBrandInviteList(oldDealerJoin.getDealerId(), oldDealerJoin.getBrandsId(), oldDealerJoin.getBrandId());
        if (null != inviteList && !inviteList.isEmpty())
        {
            BrandInvite brandInvite = inviteList.get(0);
            brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_DEALER_STOP_COOPERATION);
            brandInvite.setUpdateTime(upTime);
            brandInviteService.updateByPrimaryKey(brandInvite);
        }
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT);
        dealerCountService.modifyDealerCount(oldDealerJoin.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT);
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_VIEWDEALERCOUNT);
        brandCountService.modifyBrandCount(oldDealerJoin.getBrandId(), countTypeList.toArray(new Integer[]{}));
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT);
        brandsCountService.modifyBrandsCount(oldDealerJoin.getBrandsId(), countTypeNameList.toArray(new String[]{}));
        // 发送站内信
        BrandMessage brandMessage = new BrandMessage();
        brandMessage.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
        brandMessage.setBrandId(oldDealerJoin.getBrandId());
        brandMessage.setDealerId(oldDealerJoin.getDealerId());
        brandMessage.setMsgCate(CommonConstant.Message.MSG_CATE_NET);
        brandMessage.setMsgTitle("合作终止");
        String content = "【%s】终止了与您的品牌【%s】之间的合作关系";
        DealerInfo dealerInfo = dealerInfoService.getDealerInfo(oldDealerJoin.getDealerId());
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(oldDealerJoin.getBrandsId());
        brandMessage.setMsgText(String.format(content, dealerInfo.getDealerName(), brandesInfo.getBrandsName()));
        brandMessage.setCreateTime(CalendarUtils.getCurrentLong());
        brandMessage.setRefuseReply(CommonConstant.Message.MSG_REPLY_YES);
        // crm免登陆经销商后台操作-终止合作
        // String crm_userId = memcachedSessionManager.getString(request, MemcachedSessionManager.SessionKey.CRM_USERID);
        // if (StringUtils.isNotBlank(crm_userId)) {
        // DealerCrmLog dealerCrmLog = new DealerCrmLog();
        // dealerCrmLog.setOperatorId(crm_userId);
        // dealerCrmLog.setBrandesId(oldDealerJoin.getBrandsId());
        // dealerCrmLog.setBeOperationName(CrmConstant.crmToDealerOptionModel.BRAND);
        // dealerCrmLog.setOperation(CrmConstant.CrmDealerOptionName.STOP); // 终止合作
        // dealerCrmLog.setOperationDetails("终止合作:'品牌:" + oldDealerJoin.getBrandsName() + "';");
        // dealerCrmLogService.addCRMLog(request, dealerCrmLog);
        // }
        // brandCountCache.updateBrandCount(BrandConstant.BrandCountConst.BRANDCOUNT_VIEWDEALERCOUNT, oldDealerJoin.getBrandId(), 1);
        brandMessageService.insert(brandMessage);
    }
    
    /**
     * 获取品牌主营
     *
     * @param brandsId
     */
    private String getBrandesDeal(String brandsId)
    {
        List<BrandDeal> list = brandDealService.selectBrandDealsByBrandesId(brandsId);
        List<String> result = Lists.newArrayList();
        for (BrandDeal brandDeal : list)
        {
            DealDic dealDic = dealDicService.getDealDicByDealNo(brandDeal.getDealNo());
            if (dealDic == null)
            {
                continue;
            }
            result.add(dealDic.getDealName());
        }
        return StringUtils.join(result.toArray(), "/");
    }
    
    /**
     * 终端商已加盟的品牌列表
     *
     * @param dealerId
     * @param pagination
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> findJoinByDealerId(String dealerId, String brandName, String brandsName, Pagination pagination)
    {
        PaginateResult<Map<String, Object>> result = new PaginateResult<Map<String, Object>>();
        List<Map<String, Object>> dealerList = dealerJoinMapper.findByDealerIdByBrands(pagination, dealerId, brandName, brandsName);
        Map<String, Object> item;
        if (dealerList != null && dealerList.size() > 0)
        {
            for (int i = 0; i < dealerList.size(); i++)
            {
                item = dealerList.get(i);
                Integer joinForm = (Integer) item.get("joinForm");
                if (joinForm != null)
                {
                    if (joinForm.equals(Integer.valueOf(DealerConstant.DealerJoin.JOINFORM_CASH)))
                    {
                        item.put("joinFormCn", "现款");
                    }
                    else if (joinForm.equals(Integer.valueOf(DealerConstant.DealerJoin.JOINFORM_CREDIT)))
                    {
                        item.put("joinFormCn", "授信");
                    }
                    else
                    {
                        item.put("joinFormCn", "其他");
                    }
                }
                if(item.get("point")!=null&&(boolean)item.get("point")){
                    item.put("joinFormCn", item.get("joinFormCn")+"/返点");
                }
                Long startTime = (Long) item.get("startTime");
                if (startTime != null)
                {
                    item.put("startTimeStr", CalendarUtils.getStringTime(startTime, "yyyy-MM-dd"));
                }
                if (item.get("paidAmount") == null)
                {
                    item.put("paidAmount", BigDecimal.ZERO);
                }
                Object depositTotalAmount = item.get("depositTotalAmount");
                if (depositTotalAmount == null)
                {
                    item.put("depositTotalAmount", BigDecimal.ZERO);
                    if (item.get("paidAmount") == null)
                    {
                        item.put("wantMoney", BigDecimal.ZERO);
                    }
                    else
                    {
                        item.put("wantMoney", BigDecimal.ZERO.subtract((BigDecimal) item.get("paidAmount")));
                    }
                }
                else
                {
                    if (item.get("paidAmount") == null)
                    {
                        item.put("wantMoney", depositTotalAmount);
                    }
                    else
                    {
                        item.put("wantMoney", ((BigDecimal) depositTotalAmount).subtract((BigDecimal) item.get("paidAmount")));
                    }
                }
                // 计算剩余信誉
                Object creditAmount = item.get("creditAmount");
                if (creditAmount == null)
                {
                    item.put("creditAmount", BigDecimal.ZERO);
                    if (item.get("creditCurrent") == null)
                    {
                        item.put("wantCurrent", BigDecimal.ZERO);
                    }
                    else
                    {
                        item.put("wantCurrent", BigDecimal.ZERO.subtract((BigDecimal) item.get("creditCurrent")));
                    }
                }
                else
                {
                    if (item.get("creditCurrent") == null)
                    {
                        item.put("wantCurrent", creditAmount);
                    }
                    else
                    {
                        item.put("wantCurrent", ((BigDecimal) creditAmount).subtract((BigDecimal) item.get("creditCurrent")));
                    }
                }
                DealerOrderModel orderModel = new DealerOrderModel();
                orderModel.setDealerId(dealerId);
                orderModel.setBrandsId(String.valueOf(item.get("brandsId")));
                orderModel.setOrderStatusStr("3");
                // 统计进货订单数
                Long orderTime = dealerOrderMapper.getDealerOrderCount(orderModel);
                item.put("orderTime", orderTime);
                String brandsId = String.valueOf(item.get("brandsId"));
                String domain = GlobalStaticTextUtils.getBrandsIdSubDomain(brandsId);
                item.put("domain", domain);
            }
            result.setList(dealerList);
        }
        result.setPage(pagination);
        return result;
    }
    
    /**
     * 终端商已加盟的品牌列表
     *
     * @param dealerId
     * @param brandName
     * @return
     */
    @Override
    public List<Map<String, Object>> findJoinByDealerId(String dealerId, String brandName, String brandsName)
    {
        List<Map<String, Object>> dealerList = dealerJoinMapper.findByDealerIdByBrands(dealerId, brandName, brandsName);
        Map<String, Object> item;
        if (dealerList != null && dealerList.size() > 0)
        {
            for (int i = 0; i < dealerList.size(); i++)
            {
                item = dealerList.get(i);
                Integer joinForm = (Integer) item.get("joinForm");
                if (joinForm != null)
                {
                    if (joinForm.equals(Integer.valueOf(DealerConstant.DealerJoin.JOINFORM_CASH)))
                    {
                        item.put("joinFormCn", "现款");
                    }
                    else if (joinForm.equals(Integer.valueOf(DealerConstant.DealerJoin.JOINFORM_CREDIT)))
                    {
                        item.put("joinFormCn", "授信");
                    }
                    else
                    {
                        item.put("joinFormCn", "其他");
                    }
                }
                Long startTime = (Long) item.get("startTime");
                if (startTime != null)
                {
                    item.put("startTimeStr", CalendarUtils.getStringTime(startTime, "yyyy-MM-dd"));
                }
            }
        }
        return dealerList;
    }
    
    @Override
    public DealerJoin findByDealerIdAndBrandsId(String dealerId, String brandsId)
    {
        if (null != dealerId && null != brandsId) { return dealerJoinMapper.findByDealerIdAndBrandsId(dealerId, brandsId); }
        return null;
    }
    
    /**
     * 根据过滤条件获取经销商加盟信息
     *
     * @param filter
     * @return
     * @author 陈建平
     */
    @Override
    public List<Map<String, Object>> getDealerJoinList(DealerJoin filter)
    {
        return dealerJoinMapper.getDealerJoinList(filter);
    }
    
    /**
     * 获取所有合作中的经销商，用于发送消息
     *
     * @param brandId
     * @return
     */
    @Override
    public String[] getDealerIdsWithJoin(String brandId)
    {
        return dealerJoinMapper.getDealerIdsWithJoin(brandId).toArray(new String[]{});
    }
    
    /**
     * 查询加盟关系
     *
     * @param filter
     * @return
     */
    @Override
    public List<DealerJoin> findDealerJoin(DealerJoin filter)
    {
        return dealerJoinMapper.findDealerJoin(filter);
    }
    
    /**
     * 查新品牌商已加盟数据
     *
     * @param branderId
     * @param pagination
     * @param filter
     * @return
     * @throws BusinessException
     */
    @Override
    public PaginateResult<Map<String, Object>> findByBrandId(String branderId, Pagination pagination, BrandJoinFilter filter) throws BusinessException
    {
        try
        {
            PaginateResult<Map<String, Object>> result = new PaginateResult<Map<String, Object>>();
            List<Map<String, Object>> list = dealerJoinMapper.findByBrandId(branderId, pagination, filter);
            for (Map<String, Object> map : list)
            {
                this.formatData(map);
            }
            result.setList(list);
            result.setPage(pagination);
            return result;
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * 列表数据格式化
     *
     * @param map
     */
    private void formatData(Map<String, Object> map)
    {
        Object levelObj = map.get("levelId");
        String levelName = "";
        if (levelObj != null && StringUtils.isNotBlank((String) levelObj))
        {
            levelName = brandLevelMapper.selectByPrimaryKey((String) levelObj).getLevelName();
        }
        map.put("levelName", levelName);
        // 翻译终端商名称
        String dealerId = (String) map.get("dealerId");
        if (StringUtils.isNotBlank(dealerId))
        {
            DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
            if (dealerInfo != null)
            {
                map.put("dealerName", dealerInfo.getDealerName());
            }
        }
        // 翻译品牌名称
        String brandsId = (String) map.get("brandsId");
        if (StringUtils.isNotBlank(brandsId))
        {
            BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(brandsId);
            if (brandesInfo != null)
            {
                map.put("brandsName", brandesInfo.getBrandsName());
            }
        }
        DealerOrderModel orderModel = new DealerOrderModel();
        orderModel.setDealerId(dealerId);
        orderModel.setBrandsId(brandsId);
        orderModel.setOrderStatusStr("3");
        // 统计进货订单数
        Long orderMoney = dealerOrderMapper.getSumOrderMoney(orderModel);
        map.put("orderMoney", orderMoney);
        // 翻译 合作方式
        Integer joinForm = (Integer) map.get("joinForm");
        if (joinForm != null)
        {
            if (joinForm.equals(Integer.valueOf(DealerConstant.DealerJoin.JOINFORM_CASH)))
            {
                map.put("joinFormName", "现款");
                if(map.get("point")!=null&&(Boolean)map.get("point")){
                    map.put("joinFormName", map.get("joinFormName")+"<br/>支持返点");
                }
            }
            else if (joinForm.equals(Integer.valueOf(DealerConstant.DealerJoin.JOINFORM_CREDIT)))
            {
                map.put("joinFormName", "授信");
                if(map.get("point")!=null&&(Boolean)map.get("point")){
                    map.put("joinFormName", map.get("joinFormName")+"<br/>支持返点");
                }
            }
        }
        BigDecimal creditAmount = (BigDecimal) map.get("creditAmount");
        BigDecimal creditCurrent = (BigDecimal) map.get("creditCurrent");
        // 剩余可用授信 = 授信额度-实时受信金额
        if (creditAmount != null)
        {
            if (creditCurrent != null)
            {
                map.put("availableCurrent", creditAmount.subtract(creditCurrent));
            }
            else
            {
                map.put("availableCurrent", creditAmount);
            }
        }
        else
        {
            if (creditAmount != null)
            {
                map.put("availableCurrent", BigDecimal.ZERO.subtract(creditAmount));
            }
            else
            {
                map.put("availableCurrent", 0);
            }
        }
        // 待缴或待退押金 = 押金金额-已缴押金
        BigDecimal creditPaid = (BigDecimal) map.get("depositTotalAmount"); // 押金金额
        if (creditPaid != null)
        {
            BigDecimal paidAmount = (BigDecimal) map.get("paidAmount");// 已缴押金
            if (paidAmount != null)
            {
                BigDecimal wantPaid = creditPaid.subtract(paidAmount);
                map.put("wantPaid", wantPaid);
                this.wantButton(map, wantPaid);
            }
            else
            {
                map.put("wantPaid", creditPaid);
                this.wantButton(map, creditPaid);
            }
        }
        else
        {
            BigDecimal paidAmount = (BigDecimal) map.get("paidAmount");// 已缴押金
            if (paidAmount != null)
            {
                BigDecimal wantPaid = BigDecimal.ZERO.subtract(paidAmount);
                map.put("wantPaid", wantPaid);
                this.wantButton(map, wantPaid);
            }
            else
            {
                map.put("wantPaid", BigDecimal.ZERO);
            }
        }
    }
    
    /**
     * 已缴金额-押金金额 如果小于0，说明是待退金额，需要显示退款按钮
     * @param map
     * @param wantPaid
     */
    private void wantButton(Map<String, Object> map, BigDecimal wantPaid)
    {
        if (wantPaid != null && wantPaid.compareTo(BigDecimal.ZERO) < 0)
        {
            // 已缴金额-押金金额 如果小于0，说明是待退金额，需要显示退款按钮
            map.put("wantButton", "true");
        }
    }
    
    /**
     * 搜索加盟brandId所有的经销商
     *
     * @param searchBean
     * @return
     * @author 陈建平
     */
    @Override
    public PaginateResult<Map<String, Object>> findCooperatedDealer(TransferSearchModel searchBean)
    {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<>(searchBean.getPage(), dealerJoinMapper.findCooperatedDealer(searchBean));
        return paginateResult;
    }
    
    /**
     * 搜索和dealerId合作的所有的品牌商
     *
     * @param searchBean
     * @return
     * @author 陈建平
     */
    @Override
    public PaginateResult<Map<String, Object>> findCooperatedBrand(TransferSearchModel searchBean)
    {
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<>(searchBean.getPage(), dealerJoinMapper.findCooperatedBrand(searchBean));
        List<Map<String, Object>> list = paginateResult.getList();
        for (Map<String, Object> map : list)
        {
            map.put("brandsName", ListUtils.join2String(dealerJoinMapper.findCooperatedBrandsName(searchBean.getDealerId(), map.get("brandId").toString())));
        }
        return paginateResult;
    }
    
    /**
     * 品牌商终止加盟合作
     *
     * @param dealerJoin
     * @param endMark
     * @throws BusinessException
     * @author 陈建平
     */
    @Override
    public void updateStopBrandJoinState(DealerJoin dealerJoin, String endMark) throws BusinessException
    {
        if (null == dealerJoin) { throw new BusinessException("经销商未与该品牌商合作"); }
        DealerJoin oldDealerJoin = joinStateValidator(dealerJoin, DealerConstant.DealerJoin.COOPERATION);
        Long upTime = CalendarUtils.getCurrentLong();
        if (null == oldDealerJoin) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        oldDealerJoin.setJoinState(DealerConstant.DealerJoin.STOP_COOPERATION);
        oldDealerJoin.setEndTime(CalendarUtils.getCurrentLong());
        oldDealerJoin.setEndMark(dealerJoin.getEndMark());
        oldDealerJoin.setUpdateTime(upTime);
        // 修改合作状态
        updateByPrimaryKey(oldDealerJoin);
        // 发送站内信息给终端商
        brandMessageService.sendDealerMessage(dealerJoin.getBrandId(), dealerJoin.getDealerId(), "终止合作", endMark);
        // 生成加盟申请日志
        DealerJoinModel dealerJoinModel = new DealerJoinModel();
        BeanUtils.copyProperties(oldDealerJoin, dealerJoinModel);
        dealerJoinModel.setCheckState(BrandConstant.BrandCheckConst.STOP_COOPERATION);
        brandCheckService.insertBrandCheck(dealerJoinModel);
        // 修改加盟申请状态
        BrandInvite brandInvite = brandInviteService.getByDealerIdAndBrandsId(oldDealerJoin.getDealerId(), oldDealerJoin.getBrandsId());
        if (null != brandInvite)
        {
            brandInvite.setApplyState(BrandConstant.BrandInviteConst.APPLY_STATE_BRAND_STOP_COOPERATION);
            brandInvite.setUpdateTime(upTime);
            brandInviteService.updateByPrimaryKey(brandInvite);
        }
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(DealerConstant.DealerCount.DEALERCOUNT_COLUMN_JOINCOUNT);
        dealerCountService.modifyDealerCount(dealerJoin.getDealerId(), countTypeList.toArray(new Integer[]{}));
        countTypeList.clear();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_COOPERCOUNT);
        brandCountService.modifyBrandCount(dealerJoin.getBrandId(), countTypeList.toArray(new Integer[]{}));
        List<String> countTypeNameList = Lists.newArrayList();
        countTypeNameList.add(BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT);
        brandsCountService.modifyBrandsCount(dealerJoin.getBrandsId(), countTypeNameList.toArray(new String[]{}));
        // 发送站内信
        BrandMessage brandMessage = new BrandMessage();
        brandMessage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        brandMessage.setBrandId(oldDealerJoin.getBrandId());
        brandMessage.setDealerId(oldDealerJoin.getDealerId());
        brandMessage.setMsgCate(CommonConstant.Message.MSG_CATE_NET);
        brandMessage.setMsgTitle("合作终止");
        String content = "【%s】终止了与您的品牌【%s】之间的合作关系";
        DealerInfo dealerInfo = dealerInfoService.getDealerInfo(oldDealerJoin.getDealerId());
        BrandesInfo brandesInfo = brandesInfoService.findBrandAndBrandesInfo(oldDealerJoin.getBrandsId());
        brandMessage.setMsgText(String.format(content, dealerInfo.getDealerName(), brandesInfo.getBrandsName()));
        brandMessage.setCreateTime(CalendarUtils.getCurrentLong());
        brandMessage.setRefuseReply(CommonConstant.Message.MSG_REPLY_YES);
        brandMessageService.insert(brandMessage);
    }
    
    /**
     * 验证加盟状态
     *
     * @param dealerJoin
     * @param joinState
     * @return
     * @throws BusinessException
     * @author 陈建平
     */
    private DealerJoin joinStateValidator(DealerJoin dealerJoin, Short joinState) throws BusinessException
    {
        List<DealerJoin> joinList = dealerJoinMapper.findList(dealerJoin);
        if (null != joinList && !joinList.isEmpty())
        {
            DealerJoin tempDealerJoin = joinList.get(0);
            Short tempJoinState = tempDealerJoin.getJoinState();
            if (tempJoinState.shortValue() != joinState.shortValue()) { throw new BusinessException(DealerConst.DEALER_JOIN_STATE_ERROR); }
            return tempDealerJoin;
        }
        return null;
    }
    
    @Override
    public DealerJoin selectDealerJoinByDealerIdAndBrandsId(String dealerId, String brandsId)
    {
        return dealerJoinMapper.selectDealerJoinByDealerIdAndBrandsId(dealerId, brandsId);
    }
    
    @Override
    public void savePaidDepositAmount(DealerJoin dealerJoin, BigDecimal paidAmount) throws BusinessException
    {
        if (null == dealerJoin || null == paidAmount) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 获取原已付押金
        BigDecimal oldPaidAmount = dealerJoin.getPaidAmount();
        if (null == oldPaidAmount)
        {
            oldPaidAmount = BigDecimal.ZERO;
        }
        // 计算新已付押金
        BigDecimal newPaidAmount = oldPaidAmount.add(paidAmount);
        dealerJoin.setPaidAmount(newPaidAmount);
        // 获取总押金
        BigDecimal totalAmount = dealerJoin.getDepositTotalAmount();
        if (null == totalAmount)
        {
            totalAmount = BigDecimal.ZERO;
        }
        // 押金付足时，修改押金付款状态
        if (totalAmount.compareTo(newPaidAmount) < 1)
        {
            dealerJoin.setIsPaid(true);
        }
        else
        {
            dealerJoin.setIsPaid(false);
        }
        dealerJoin.setPaidTime(CalendarUtils.getCurrentLong());
        dealerJoinMapper.updateByPrimaryKey(dealerJoin);
    }
    
    /**
     * 获取未结算的押金记录
     *
     * @param page
     * @param filter
     * @return
     * @author 陈建平
     */
    @Override
    public PaginateResult<Map<String, Object>> selectUnClearingDepositList(Pagination page, DealerJoin filter)
    {
        filter.setPage(page);
        PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>(page, dealerJoinMapper.selectUnClearingDepositList(filter));
        return paginateResult;
    }
    
    /**
     * 更新押金结算金额
     *
     * @param refrenceIdArr
     * @param payAmountArr
     * @throws BusinessException
     * @author 陈建平
     */
    @Override
    public void updateDepositClearingAmount(String[] refrenceIdArr, BigDecimal[] payAmountArr) throws BusinessException
    {
        if (refrenceIdArr.length != payAmountArr.length) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        for (int i = 0; i < refrenceIdArr.length; i++)
        {
            if (null == payAmountArr[i] || payAmountArr[i].compareTo(BigDecimal.ZERO) < 1) { throw new BusinessException(CommonConst.DEALERJOIN_DEPOSIT_CLEARING_ZERO); }
            if (StringUtils.isBlank(refrenceIdArr[i])) { throw new BusinessException(CommonConst.PARAM_NULL); }
            DealerJoin dealerJoin = dealerJoinMapper.selectByPrimaryKey(refrenceIdArr[i]);
            if (null == dealerJoin) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            BigDecimal depositClearingAmount = dealerJoin.getDepositClearingAmount();
            if (null == depositClearingAmount)
            {
                depositClearingAmount = BigDecimal.ZERO;
            }
            depositClearingAmount = depositClearingAmount.add(payAmountArr[i]);
            dealerJoin.setDepositClearingAmount(depositClearingAmount);
            dealerJoinMapper.updateByPrimaryKey(dealerJoin);
        }
    }
    
    @Override
    public BigDecimal getUnpayDepositAmount(DealerJoin dealerJoin) throws BusinessException
    {
        if (null == dealerJoin) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        // 检查押金是否已设置
        BigDecimal totalAmount = dealerJoin.getDepositTotalAmount();
        if (null == totalAmount) { throw new BusinessException(CommonConst.DEALERJOIN_DEPOSIT_NULL); }
        // 获取已付押金
        BigDecimal paidAmount = dealerJoin.getPaidAmount();
        if (null == paidAmount)
        {
            paidAmount = BigDecimal.ZERO;
        }
        // 检查总押金不能小于已付押金
        if (totalAmount.compareTo(paidAmount) < 0) { throw new BusinessException(CommonConst.DEALERJOIN_DEPOSIT_LESS); }
        // 计算未付押金（总押金 - 已付押金）
        BigDecimal unpayAmount = totalAmount.subtract(paidAmount);
        return unpayAmount;
    }
    
    @Override
    public List<Map<String, Object>> listCreditJoin()
    {
        return dealerJoinMapper.listCreditJoin();
    }
    
    /*
     * 终止合作
     */
    @Override
    public void brandTerminal(String dealerId, String endMark, String brandsId) throws BusinessException
    {
        DealerJoin join = dealerJoinMapper.findByDealerIdAndBrandsId(dealerId, brandsId);
        if (join == null) { throw new BusinessException(CommonConst.SUCCESS); }
        this.updateStopBrandJoinState(join, endMark);
    }
    
    /**
     * 根据终端商id和品牌商id查询加盟关系
     *
     * @param dealerId
     * @param brandId
     * @return
     */
    @Override
    public DealerJoin findByIdAndBrandId(String dealerId, String brandId)
    {
        return dealerJoinMapper.findByDealerIdAndBrandsId(dealerId, brandId);
    }
    
    /**
     * 根据加盟主键和品牌商id 查询有合作的加盟关系
     *
     * @param refrenceId
     * @param brandId
     * @return
     */
    @Override
    public DealerJoin findByRefrenceIdAndBrandId(String refrenceId, String brandId)
    {
        return dealerJoinMapper.findByRefrenceIdAndBrandId(refrenceId, brandId);
    }
    
    /**
     * 设置押金
     *
     * @param dealerJoin
     * @param request
     * @throws BusinessException
     */
    @Override
    public void saveSetDepositAmount(HttpServletRequest request, DealerJoin dealerJoin, DealerDepositView depositView) throws BusinessException
    {
        if (null == dealerJoin) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        // 设置的押金必须大于等于0
        if (null == depositView.getDepositTotalAmount() || depositView.getDepositTotalAmount().compareTo(BigDecimal.ZERO) < 0) { throw new BusinessException(
                CommonConst.DEALERJOIN_DEPOSIT_ZERO); }
        if (null == dealerJoin.getDepositTotalAmount() || dealerJoin.getDepositTotalAmount().compareTo(depositView.getDepositTotalAmount()) != 0)
        {
            UserOperationLog userOperationLog = new UserOperationLog();
            userOperationLog.setObjectId(dealerJoin.getRefrenceId());
            userOperationLog.setType(UserOperationLogConst.TYPE_DEALERJOIN_DEPOSITTOTALAMOUNT);
            userOperationLog.setEvent("【修改押金：修改前：" + (dealerJoin.getDepositTotalAmount() == null ? "" : dealerJoin.getDepositTotalAmount()) + " 修改后："
                    + new java.text.DecimalFormat("0.00").format(depositView.getDepositTotalAmount()) + "】");
            userOperationLogService.addUserOperationLog(request, userOperationLog);
        }
        dealerJoin.setJoinForm(depositView.getJoinForm().shortValue()); // 交易模式
        dealerJoin.setPoint(depositView.getPoint());
        // 如果是现款，清空授信内容
        if (DealerConstant.DealerJoin.JOINFORM_CASH == depositView.getJoinForm().shortValue())
        {
            dealerJoin.setDepositTotalAmount(BigDecimal.ZERO); // 押金额度
            dealerJoin.setCreditPaidPercent(BigDecimal.ZERO); // 押金比例
            dealerJoin.setCreditAmount(BigDecimal.ZERO);// 授信额度
        }
        else
        {
            dealerJoin.setDepositTotalAmount(depositView.getDepositTotalAmount()); // 押金额度
            dealerJoin.setCreditPaidPercent(depositView.getCreditPaidPercent()); // 押金比例
            dealerJoin.setCreditAmount(depositView.getCreditAmount());// 授信额度
        }
        // 数据保护
        if (dealerJoin.getDepositTotalAmount() == null)
        {
            dealerJoin.setDepositTotalAmount(BigDecimal.ZERO);
        }
        // 数据保护
        if (dealerJoin.getPaidAmount() == null)
        {
            dealerJoin.setPaidAmount(BigDecimal.ZERO);
        }
        // 如果押金额度-已付押金 小于或等于0，说明押金缴全了
        if (dealerJoin.getDepositTotalAmount().subtract(dealerJoin.getPaidAmount()).compareTo(BigDecimal.ZERO) <= 0)
        {
            dealerJoin.setIsPaid(true);
        }
        else
        {
            dealerJoin.setIsPaid(false);
        }
        dealerJoin.setDiscount(depositView.getDiscount().divide(BigDecimal.TEN));// 折扣 折扣除以10，保存到库中
        dealerJoinMapper.updateByPrimaryKey(dealerJoin);
    }
    
    @Override
    public PaginateResult<Map<String, Object>> queryDealerJoinPage(Pagination pagination, DealerJoin dealerJoin)
    {
        List<Map<String, Object>> list = dealerJoinMapper.findDealerJoinPage(pagination, dealerJoin);
        if (null != list && !list.isEmpty())
        {
            for (Map<String, Object> item : list)
            {
                Object areaNoObj = item.get("areaNo");
                if (null != areaNoObj && !"".equals(areaNoObj.toString()))
                {
                    item.put("areaNoName", regionalService.getFullNameByAreaNoAndLevel(Integer.parseInt(areaNoObj.toString()), 2, "<br/>"));
                }
                String dealerId = item.get("dealerId").toString();
                item.put("dealerDicList", dealerClassService.findbyId(dealerId));
                Long startTime = Long.parseLong(item.get("startTime").toString());
                item.put("startTimeStr", CalendarUtils.getDateLengthStr(startTime));
                DealerInfo dealerinfo = dealerInfoService.selectByPrimaryKey(dealerId);
                item.put("dealerName", dealerinfo.getDealerName());
            }
        }
        return new PaginateResult<>(pagination, list);
    }
    
    @Override
    public void updateDealerLevel(DealerJoin dealerJoin, String[] idAry)
    {
        for (String dealerId : idAry)
        {
            this.brandNetworkService.updateNetworkLevel(dealerJoin.getBrandId(), dealerJoin.getBrandsId(), dealerId, dealerJoin.getLevelId());
        }
        dealerJoinMapper.updateDealerLevel(dealerJoin, idAry);
    }
    
    /**
     * 根据终端商id和主键id批量获取加盟关系
     *
     * @param dealerId
     * @param joinIdArr
     * @return
     * @throws BusinessException
     */
    @Override
    public List<DealerJoin> listWithException(String dealerId, String[] joinIdArr) throws BusinessException
    {
        List<DealerJoin> list = Lists.newArrayList();
        for (String joinId : joinIdArr)
        {
            DealerJoin obj = findWithException(dealerId, joinId);
            list.add(obj);
        }
        return list;
    }
    
    /**
     * 根据终端商id和主键id获取加盟关系
     *
     * @param dealerId
     * @param joinId
     * @return
     * @throws BusinessException
     */
    @Override
    public DealerJoin findWithException(String dealerId, String joinId) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId) || StringUtils.isBlank(joinId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        DealerJoin obj = dealerJoinMapper.findrWithException(dealerId, joinId);
        if (null == obj) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        return obj;
    }
    
    @Override
    public DealerJoin getByDealerIdAndBrandsId(String dealerId, String brandsId)
    {
        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(brandsId)) { return dealerJoinMapper.getByDealerIdAndBrandsId(dealerId, brandsId); }
        return null;
    }
    
    @Override
    public List<Map<String, Object>> findByDealerIdAndBrandId(String dealerId, String brandId) throws BusinessException
    {
        if (StringUtils.isBlank(dealerId) || StringUtils.isBlank(brandId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        return dealerJoinMapper.findByDealerIdAndBrandId(dealerId, brandId);
    }
    
    @Override
    public Boolean isSupportPoint(String dealerId, String productId) throws BusinessException
    {
        if (StringUtils.isNotBlank(dealerId) && StringUtils.isNotBlank(productId))
        {
            ProductBaseInfo productBaseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
            DealerJoin dealerJoin = null;
            if (null != productBaseInfo)
            {
                dealerJoin = this.findByDealerIdAndBrandsId(dealerId, productBaseInfo.getBrandsId());
            }
            return this.isSupportPoint(dealerJoin, productBaseInfo);
        }
        return Boolean.FALSE;
    }
    
    @Override
    public Boolean isSupportPoint(DealerJoin dealerJoin, ProductBaseInfo productBaseInfo) throws BusinessException
    {
        if (null != dealerJoin && null != dealerJoin.getPoint() && null != productBaseInfo && null != productBaseInfo.getProductExtInfo()
                && null != productBaseInfo.getProductExtInfo().getPoint()) { return dealerJoin.getPoint() && productBaseInfo.getProductExtInfo().getPoint(); }
        return Boolean.FALSE;
    }
}
