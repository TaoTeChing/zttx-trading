/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.BrandConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandDoccate;
import com.zttx.web.module.brand.mapper.BrandDoccateMapper;
import com.zttx.web.module.brand.model.BrandDoccateModel;
import com.zttx.web.module.brand.model.BrandJoinFilter;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.utils.CalendarUtils;

/**
 * 品牌商文档类别信息 服务实现类
 * <p>File：BrandDoccate.java </p>
 * <p>Title: BrandDoccate </p>
 * <p>Description:BrandDoccate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandDoccateServiceImpl extends GenericServiceApiImpl<BrandDoccate> implements BrandDoccateService
{
	@Autowired
	private BrandesInfoService brandesInfoService;
	
	@Autowired
	private DealerJoinService  dealerJoinService;
	
	@Autowired
	private BrandDocopenService brandDocopenService;
	
    private BrandDoccateMapper brandDoccateMapper;

    @Autowired(required = true)
    public BrandDoccateServiceImpl(BrandDoccateMapper brandDoccateMapper)
    {
        super(brandDoccateMapper);
        this.brandDoccateMapper = brandDoccateMapper;
    }
    
    /**
     * 根据品牌编号获取所有资料分类
     * @param brandId       品牌商编号
     * @param brandsId      品牌编号
     * @return
     */
    @Override
    public List<BrandDoccate> getBrandDoccateList(String brandId, String brandsId){
    	BrandDoccate filter = new BrandDoccate();
    	filter.setBrandId(brandId);
    	filter.setBrandsId(brandsId);
    	return brandDoccateMapper.findList(filter);
    }
    
    /**
     * 保存分类
     * @author 陈建平
     * @param brandDoccate
     * @throws BusinessException
     */
    @Override
    public void insertBrandDoccate(BrandDoccateModel brandDoccate) throws BusinessException{
    	Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED};
        // 判断当前状态是不是能进行操作
        brandesInfoService.validatorState(brandDoccate.getBrandId(), brandDoccate.getBrandsId(), brandStates);
        List<BrandDoccate> brandDoccates = getBrandDoccateList(brandDoccate.getBrandId(), brandDoccate.getBrandsId());
        Map<String, BrandDoccate> brandDoccateMap = Maps.newHashMap();
        Map<String, String> brandDoccateNameMap = Maps.newHashMap();
        if (null != brandDoccates && !brandDoccates.isEmpty())
        {
            for (BrandDoccate item : brandDoccates)
            {
                brandDoccateMap.put(item.getRefrenceId(), item);
                brandDoccateNameMap.put(item.getCateName(), "");
            }
        }
        List<BrandDoccate> addList = Lists.newArrayList();
        List<BrandDoccate> updateList = Lists.newArrayList();
        String cateName = "";
        String refrenceId = "";
        if (ArrayUtils.isNotEmpty(brandDoccate.getCateNameAry()))
        {
            for (int i = 0; i < brandDoccate.getCateNameAry().length; i++)
            {
                cateName = brandDoccate.getCateNameAry()[i];
                if (StringUtils.isNotBlank(cateName))
                {
                    refrenceId = getAryStr(brandDoccate.getRefrenceIdAry(), i);
                    if (StringUtils.isBlank(refrenceId))
                    {
                        if (null != brandDoccateNameMap.get(cateName)) { throw new BusinessException(BrandConst.IMG_CATE_NAME_REPEAT); }
                        BrandDoccate newBrandDoccate = new BrandDoccate();
                        newBrandDoccate.setBrandId(brandDoccate.getBrandId());
                        newBrandDoccate.setBrandsId(brandDoccate.getBrandsId());
                        newBrandDoccate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                        newBrandDoccate.setCateName(cateName);
                        newBrandDoccate.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                        newBrandDoccate.setCreateTime(CalendarUtils.getCurrentLong());
                        newBrandDoccate.setOrderId(i);
                        addList.add(newBrandDoccate);
                        brandDoccateNameMap.put(cateName, "");
                    }
                    else
                    {
                        if (null == brandDoccateMap.get(refrenceId)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
                        BrandDoccate newBrandDoccate = brandDoccateMap.get(refrenceId);
                        Boolean isUpdate = false;
                        if (null != newBrandDoccate)
                        {
                            if (!newBrandDoccate.getCateName().equals(cateName))
                            {
                                if (null != brandDoccateNameMap.get(cateName)) { throw new BusinessException(CommonConst.DATA_EXISTS); }
                                brandDoccateNameMap.remove(newBrandDoccate.getCateName());
                                newBrandDoccate.setCateName(cateName);
                                brandDoccateNameMap.put(cateName, "");
                                isUpdate = true;
                            }
                            if (i != newBrandDoccate.getOrderId())
                            {
                                newBrandDoccate.setOrderId(i);
                                isUpdate = true;
                            }
                            if (isUpdate)
                            {
                                updateList.add(newBrandDoccate);
                            }
                            brandDoccateMap.remove(newBrandDoccate.getRefrenceId());
                        }
                    }
                }
            }
            if(null != addList && addList.size()>0){
            	for(BrandDoccate temp:addList){
            		insert(temp);
            	}
            }
            if(null != updateList && updateList.size()>0){
            	for(BrandDoccate temp:updateList){
            		updateByPrimaryKey(temp);
            	}
            }
        }
        this.updateBrandDoccateDelState(brandDoccateMap);
    }
    
    private void updateBrandDoccateDelState(Map<String, BrandDoccate> brandDoccateMap)
    {
        if (null != brandDoccateMap && !brandDoccateMap.isEmpty())
        {
            Iterator<String> iterator = brandDoccateMap.keySet().iterator();
            List<String> listIds = Lists.newArrayList();
            while (iterator.hasNext())
            {
                listIds.add(iterator.next().toString());
            }
            if(!listIds.isEmpty()){
            	brandDoccateMapper.updateBrandDoccateDelFlag(listIds);
            }
        }
    }
    
    private String getAryStr(String[] strAry, Integer size)
    {
        if (null != strAry)
        {
            Integer len = strAry.length;
            if (size.intValue() <= len.intValue() - 1) { return strAry[size].trim(); }
        }
        return null;
    }
    
    /**
     * 获取
     * @author 陈建平
     * @param filter
     * @param brandId
     * @return
     */
    @Override
    public List<Map<String, Object>> getDocJoinedDealers(BrandJoinFilter filter, String brandId)
    {
    	DealerJoin dealerJoin = new DealerJoin();
        dealerJoin.setBrandId(brandId);
        dealerJoin.setBrandsId(filter.getBid());
        if(null!=filter.getProvince()){
        	dealerJoin.setAreaNo(Integer.parseInt(filter.getProvince()));
        }
        dealerJoin.setLevelId(filter.getDealerLevel());
        List<Map<String, Object>> list = dealerJoinService.getDealerJoinList(dealerJoin);
        if (StringUtils.isNotBlank(filter.getDocId()))
        {
            List<String> dealers = brandDocopenService.getDocDealerIdList(brandId, filter.getBid(), filter.getDocId());
            Set<String> dealerSet = Sets.newHashSet(dealers);
            for (Map<String, Object> map : list)
            {
                if (dealerSet.contains((String) map.get("dealerId")))
                {
                    map.put("joined", true);
                }
                else
                {
                    map.put("joined", false);
                }
            }
        }
        return list;
    }
}
