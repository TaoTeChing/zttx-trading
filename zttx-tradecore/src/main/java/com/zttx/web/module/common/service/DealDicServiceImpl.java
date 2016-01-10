/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zttx.sdk.annotation.DataSource;
import com.zttx.sdk.db.DataSourceEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.mapper.DealDicMapper;
import com.zttx.sdk.utils.StringUtils;

/**
 * 品牌经营品类信息 服务实现类
 * <p>File：DealDic.java </p>
 * <p>Title: DealDic </p>
 * <p>Description:DealDic </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealDicServiceImpl extends GenericServiceApiImpl<DealDic> implements DealDicService
{
    private DealDicMapper dealDicMapper;
    
    @Autowired(required = true)
    public DealDicServiceImpl(DealDicMapper dealDicMapper)
    {
        super(dealDicMapper);
        this.dealDicMapper = dealDicMapper;
    }
    
    /**
     * 取产品顶级类别
     *
     * @return {@link List}
     * @throws BusinessException
     */
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<DealDic> getTopProductDealDics() throws BusinessException
    {
        /*
         * List<DealDic> dealDics = (List<DealDic>) genericMemcache.get(TOP_PRODUCT_CATE_CACHE);
         * if (dealDics == null)
         * {
         * dealDics = dealDicMapper.getTopProductDealDics();
         * genericMemcache.put(TOP_PRODUCT_CATE_CACHE, dealDics, CACHE_TIMEOUT);
         * }
         */
        // todo 缓存
        return dealDicMapper.getTopProductDealDics();
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<DealDic> getParentDealDics(Integer dealNo)
    {
        DealDic dealDic = dealDicMapper.getDealDicByDealNo(dealNo);
        if (null != dealDic && StringUtils.isNoneBlank(dealDic.getParentId()))
        {// 排出当前节点，只加入级接口
            return getParentDealDics(dealDic.getParentId(), new ArrayList<DealDic>());
        }
        return null;
    }
    
    /**
     * 递归查询
     * @param refrenceId
     * @param dealDics
     * @return {@link List}
     */
    @DataSource(DataSourceEnum.SLAVE)
    List<DealDic> getParentDealDics(String refrenceId, List<DealDic> dealDics)
    {
        DealDic dealDic = selectByPrimaryKey(refrenceId);
        if (null != dealDic)
        {
            dealDics.add(dealDic);
            if (StringUtils.isNotBlank(dealDic.getParentId()) && !"-1".equals(dealDic.getParentId()))
            { // 当前上级不为空和不等于『-1』时说明还有上级
                return getParentDealDics(dealDic.getParentId(), dealDics);
            }
        }
        return dealDics;
    }
    
    /**
     * 根据字典编码取下级与产品有关的字典
     *
     * @param dealNo
     * @return {@link List}
     * @throws BusinessException
     */
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<DealDic> getProductDealDics(Integer dealNo) throws BusinessException
    {
        /*
         * if (dealNo == null) return null;
         * String dealNos = String.valueOf(dealNo);
         * List<DealDic> dealDics = (List<DealDic>) genericMemcache.get(CHILD_PRODUCT_CATE_CACHE + dealNos);
         * if (dealDics == null)
         * {
         * dealDics = dealDicCache.getProductDealDics(dealNo);
         * genericMemcache.put(CHILD_PRODUCT_CATE_CACHE, dealDics + dealNos, CACHE_TIMEOUT);
         * }
         */
        if (dealNo == null) { throw new BusinessException(CommonConst.PARAM_NULL); }
        // todo 缓存
        return dealDicMapper.getProductDealDics(dealNo);
    }
    
    /**
     * 查询指定 parentId下的 经营品牌
     *
     * @param parentId
     * @return {@link List}
     * @throws BusinessException
     */
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<DealDic> getDealDicsBy(String parentId) throws BusinessException
    {
        parentId = StringUtils.isEmpty(parentId) ? "-1" : parentId;
        return dealDicMapper.getDealDicsBy(parentId);
    }
    
    /**
     * 优先从缓存中查询指定 parentId下的 经营品牌
     *
     * @param parentId
     * @return
     * @author 吴万杰
     */
    @Override
    public List<DealDic> listFormCache(String parentId) throws BusinessException
    {
        return getDealDicsBy(parentId);
    }
    
    /**
     * 根据dealNo获取类目
     *
     * @param dealNo
     * @return 查不到就返回null
     */
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public DealDic getDealDicByDealNo(Integer dealNo)
    {
        List<DealDic> dealDics = dealDicMapper.selectDealDicByDealNo(dealNo);
        if (dealDics != null && dealDics.size() == 1) { return dealDics.get(0); }
        return null;
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<DealDic> getDealDicByDealNos(List<Integer> dealNos)
    {
        return dealDicMapper.getDealDicByDealNos(dealNos);
    }
    
    /**
     * 根据目录品类级别查询记录
     *
     * @param level (必填)
     * @return
     * @author 吴万杰
     */
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<DealDic> getDealDicByLevel(Short level) throws BusinessException
    {
        if (level == null) { throw new BusinessException(CommonConst.PARAM_NULL); }
        return dealDicMapper.getDealDicByLevel(level);
    }
    
    /**
     * 首页导航分类 只取大类
     *
     * @param page    大分类取的分页
     * @param subpage 小分类取的分页
     * @return
     * @author 鲍建明
     */
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<DealDic> getDealDicOrderByField(Pagination page, Pagination subpage)
    {
        List<DealDic> list = dealDicMapper.getDealDicOrderByField(page);
        for (DealDic dealDic : list)
        {
            List<Map<String, Object>> subList = dealDicMapper.findIndexNav(dealDic.getDealNo(), subpage);
            dealDic.setSubList(new PaginateResult<>(subpage, subList));
        }
        return list;
    }
    
    /**
     * 把所有类目转化为JSON
     *
     * @author 张昌苗
     */
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public String getDealDicJson() throws BusinessException
    {
        List<Object> parentJsonList = Lists.newArrayList();
        List<DealDic> parentDealList = this.getDealDicsBy("");
        for (DealDic parentDeal : parentDealList)
        {
            Map<String, Object> parentMap = Maps.newHashMap();
            parentMap.put("id", parentDeal.getDealNo().toString());
            parentMap.put("item", parentDeal.getDealName());
            List<Object> childJsonList = Lists.newArrayList();
            List<DealDic> childDealList = this.getDealDicsBy(parentDeal.getRefrenceId());
            for (DealDic childDeal : childDealList)
            {
                Map<String, String> childMap = Maps.newHashMap();
                childMap.put("id", childDeal.getDealNo().toString());
                childMap.put("item", childDeal.getDealName());
                childJsonList.add(childMap);
            }
            parentMap.put("childs", childJsonList);
            parentJsonList.add(parentMap);
        }
        return JSON.toJSONString(parentJsonList);
    }
    
    /**
     * 设置用户选择类目的历史记录缓存集合
     * todo 暂无缓存集合
     * @param brandId 品牌商编号
     * @param dealNo  栏目编号
     * @author 施建波
     */
    @Override
    public void setBrandDicList(String brandId, Integer dealNo)
    {
    }
    
    /**
     * 获取用户选择类目的历史记录缓存集合
     * todo 暂无缓存集合
     * @param brandId 品牌商编号
     * @return
     * @author 施建波
     */
    @Override
    public List<DealDic> getBrandDicList(String brandId)
    {
        return Lists.newArrayList();
    }
    
    /**
     * 第三方保存：refrenceId（null：新增，非null：修改）
     *
     * @param dealDic refrenceId（String）主键
     *                dealName（String）品类名称		（必填）
     *                dealIcon（String）品类图标		（必填）
     *                dealOrder（Integer）排序编号	（必填）
     *                parentId（String）父级类目主键（null：一级类目，非null：指定类目的子类目）
     * ClientValidator.validateByClient(dealDic); dealDic 需要 验证
     * @return DealDic
     * @author 章旭楠
     */
    @Override
    public void saveByClient(DealDic dealDic) throws BusinessException
    {
        if (StringUtils.isBlank(dealDic.getParentId()))
        {
            dealDic.setParentId("-1");
        }
        if (StringUtils.isBlank(dealDic.getRefrenceId()))
        {
            if (this.isExistDealName(dealDic.getDealLevel(), dealDic.getDealName())) { throw new BusinessException(ClientConst.PARAMERROR.code, "品类名称以存在！"); }
            DealDic parent = super.selectByPrimaryKey(dealDic.getParentId());
            if (null != parent)
            {
                short dealLevel = parent.getDealLevel() != null ? parent.getDealLevel() : 1;
                dealLevel += 1;
                if (dealLevel > 3) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "不能再创建子级！"); }
                dealDic.setDealLevel(dealLevel);
                dealDic.setDealNo(this.getDealNoByParent(parent));
            }
            else
            {
                dealDic.setDealLevel((short) 1);
                dealDic.setDealNo(this.getDealNoByParent(parent));
            }
            dealDicMapper.insertSelective(dealDic);
        }
        else
        {
            this.updateDealDic(dealDic);
        }
    }
    
    private void updateDealDic(DealDic dealDic) throws BusinessException
    {
        DealDic old = dealDicMapper.selectByPrimaryKey(dealDic.getRefrenceId());
        if (null == old) { throw new BusinessException(ClientConst.PARAMERROR.getCode(), "修改数据不存在"); }
        if (!old.getDealName().equals(dealDic.getDealName()))// 如果修改品类名称
        {
            if (this.isExistDealName(dealDic.getDealLevel(), dealDic.getDealName())) { throw new BusinessException(ClientConst.PARAMERROR.code, "品类名称已存在！"); }
            old.setDealName(dealDic.getDealName());
        }
        /*
         * DealDic parent = dealDicMapper.selectByPrimaryKey(dealDic.getParentId());
         * String oldParentId = old.getParentId();
         * if(null == parent || parent.getRefrenceId().equals(oldParentId)){
         * // 顶级类目，或者更改所属类目时，修改 DealNo
         * old.setDealNo(dealDic.getDealNo());
         * }
         */
        old.setDealIcon(dealDic.getDealIcon());
        old.setDealNo(dealDic.getDealNo());
        old.setUpdateTime(CalendarUtils.getCurrentLong());
        dealDicMapper.updateByPrimaryKeySelective(old);
    }
    
    private Integer getDealNoByParent(DealDic parent)
    {
        Integer dealNo;
        Integer maxdealNo;
        if (null == parent)// 表示第一级
        {
            maxdealNo = this.dealDicMapper.getMaxDealNo("", (short) 1);
            dealNo = null != maxdealNo ? this.getDealNo(maxdealNo, (short) 1) : 100000000;
        }
        else
        {// 二级 或 三级
            maxdealNo = this.dealDicMapper.getMaxDealNo(parent.getRefrenceId(), null);
            short dealLevel = (short) (parent.getDealLevel() + 1);
            dealNo = null != maxdealNo ? this.getDealNo(maxdealNo, dealLevel) : this.getDealNo(parent.getDealNo(), dealLevel);
        }
        return dealNo;
    }
    
    private Integer getDealNo(Integer maxdealNo, short dealLevel)
    {
        Integer level1 = 1000000;
        Integer level2 = 1000;
        Integer level3 = 1;
        switch (dealLevel)
        {
            case 1:
                maxdealNo += level1;
                break;
            case 2:
                maxdealNo += level2;
                break;
            case 3:
                maxdealNo += level3;
                break;
        }
        return maxdealNo;
    }
    
    private boolean isExistDealName(Short dealLevel, String dealName)
    {
        DealDic searchBean = new DealDic();
        searchBean.setDealLevel(dealLevel);
        searchBean.setDealName(dealName);
        return dealDicMapper.findList(searchBean).size() > 0;
    }
    
    /**
     * 第三方删除
     *
     * @param refrenceId
     * @author 周光暖
     */
    @Override
    public void deleteByClient(String refrenceId) throws BusinessException
    {
        DealDic dealDic = dealDicMapper.selectByPrimaryKey(refrenceId);
        if (dealDic == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        List<DealDic> children = dealDicMapper.getDealDicsBy(dealDic.getRefrenceId());
        if (!CollectionUtils.isEmpty(children)) { throw new BusinessException(ClientConst.PARAMERROR.getCode(), "存在子类目，不能删除"); }
        dealDicMapper.delete(refrenceId);
    }
    
    /**
     * 列表
     *
     * @param page
     * @param searchBean 过滤条件（dealName 模糊查询、 dealLevel）
     * @return
     * @author 周光暖
     */
    @Override
    public PaginateResult<DealDic> searchByClient(Pagination page, DealDic searchBean)
    {
        searchBean.setPage(page);
        searchBean.setParentId(StringUtils.isBlank(searchBean.getRefrenceId()) ? "-1" : searchBean.getRefrenceId());
        return new PaginateResult<>(page, dealDicMapper.findList(searchBean));
    }
    
    /**
     * 通过ID来查询parentId
     *
     * @param refrenceId
     * @return DealDic(String parentId, String dealName)
     * @author 周光暖
     */
    @Override
    public DealDic findParentIdByRefrenceId(String refrenceId)
    {
        return this.findByRefrenceId(refrenceId);
    }
    
    /**
     * 通过ID来查询
     *
     * @param refrenceId
     * @return 全的DealDic
     * @author 周光暖
     */
    @Override
    public DealDic findByRefrenceId(String refrenceId)
    {
        return dealDicMapper.selectByPrimaryKey(refrenceId);
    }
    
    /**
     * 获取类目分页结果集
     *
     * @param dealDic
     * @param page
     * @return
     * @throws BusinessException
     * @author 李飞欧
     */
    @Override
    public PaginateResult<DealDic> listDealDics(DealDic dealDic, Pagination page) throws BusinessException
    {
        dealDic.setPage(page);
        return new PaginateResult(page, dealDicMapper.findList(dealDic));
    }
}
