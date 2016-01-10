/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.module.brand.entity.BrandDeal;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandDealMapper;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.search.solrj.BrandeSolrHandler;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌主营品类 服务实现类
 * <p>File：BrandDeal.java </p>
 * <p>Title: BrandDeal </p>
 * <p>Description:BrandDeal </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandDealServiceImpl extends GenericServiceApiImpl<BrandDeal> implements BrandDealService
{

	@Autowired
	private DealDicService  dealDicService;
	
	@Autowired
	private BrandesInfoService brandesInfoService;
	
	@Autowired
    private BrandeSolrHandler  brandeSolrHandler;
	
    private BrandDealMapper brandDealMapper;

    @Autowired(required = true)
    public BrandDealServiceImpl(BrandDealMapper brandDealMapper)
    {
        super(brandDealMapper);
        this.brandDealMapper = brandDealMapper;
    }
    
    /**
	 * 通过品牌商ID和品牌来ID来查询对应的所有品类
	 * @author 陈建平
	 * @param brandId
	 * @param brandsId
	 * @return
	 */
    @Override
    public java.util.List<Integer> findDealNoBy(String brandId, String brandsId)
    {
        return brandDealMapper.findDealNoBy(brandId, brandsId);
    }
    
    /**
     * 根据品牌商编号、品牌编号、删除标志
     * @author 陈建平
     * @param brandId
     * @param brandsId
     * @param delState
     * @return
     */
    @Override
    public List<BrandDeal> findByBrandsId(String brandId, String brandsId, Boolean delState){
    	List<BrandDeal> list = brandDealMapper.findByBrandsId(brandId, brandsId, delState);
    	if (list != null && !list.isEmpty())
        {
            for (BrandDeal deal : list)
            {
                DealDic dealDic = dealDicService.getDealDicByDealNo(deal.getDealNo());
                deal.setDealDic(dealDic);
            }
        }
    	return list;
    }
    
    /**
     * 获取品类名称列表
     * @param brandsId 品牌编号
     * @return
     * @author 施建波
     */
    @Override
    public List<Map<String, Object>> getBrandDealNameList(String brandsId){
    	return brandDealMapper.getBrandDealNameList(brandsId);
    }
    
    /**
     * 修改品牌经营类目
     * @author 陈建平
     * @param brandId
     * @param brandesId
     * @param dealNos
     * @param createIp
     */
    @Override
    public void updateBrandDeal(String brandId, String brandesId, String dealNos, Integer createIp)
    {
        if (StringUtils.isBlank(brandId) || StringUtils.isBlank(brandesId) || StringUtils.isBlank(dealNos) || null == createIp)
        {
            throw new IllegalArgumentException("请求参数为空");
        }
        String[] dealNoList = dealNos.split("\\|");
        
        List<BrandDeal> brandDealList = brandDealMapper.findByBrandsId(brandId, brandesId, BrandConstant.DEL_STATE_NONE_DELETED);
        List<BrandDeal> delDealList = Lists.newArrayList();
        List<BrandDeal> addDealList = Lists.newArrayList();
        if (null != brandDealList && !brandDealList.isEmpty()) {
            for (BrandDeal deal : brandDealList) {
                int size = com.zttx.web.utils.StringUtils.strArraySearch(dealNoList, String.valueOf(deal.getDealNo()));
                if (size >= 0) {
                	dealNoList[size] = "";
                } else {
                    deal.setDelFlag(BrandConstant.DEL_STATE_DELETED);
                    deal.setUpdateTime(CalendarUtils.getCurrentLong());
                    delDealList.add(deal);
                    this.updateByPrimaryKey(deal);
                }
            }
        }
        
        for (String dealNo : dealNoList)
        {
        	if(StringUtils.isNotBlank(dealNo)){
        		BrandDeal entity = new BrandDeal();
                try
                {
                    DealDic dealDic = dealDicService.selectByPrimaryKey(dealNo);
                    entity.setDealNo(dealDic.getDealNo());
                }
                catch (NullPointerException e)
                {
                    throw new IllegalArgumentException("品类不存在");
                }
                entity.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                entity.setBrandId(brandId);
                entity.setBrandesId(brandesId);
                entity.setCreateTime(CalendarUtils.getCurrentLong());
                entity.setUpdateTime(CalendarUtils.getCurrentLong());
                entity.setCreateIp(createIp);
                entity.setDelFlag(false);
                this.insert(entity);
                addDealList.add(entity);
        	}
        }
        BrandesInfo brandesInfo = new BrandesInfo();
        brandesInfo.setRefrenceId(brandesId);
        List<BrandesInfo> list = brandesInfoService.findBrandesInfoToSolr(brandesInfo, null);
        for(BrandesInfo temp : list){
        	temp.setDealList(addDealList);
        }
        brandeSolrHandler.addBrandsInfoList(list);
    }

    @Override
    public List<BrandDeal> selectBrandDealsByBrandesId(String brandesId){
        return brandDealMapper.selectBrandDealsByBrandesId(brandesId);
    }
}
