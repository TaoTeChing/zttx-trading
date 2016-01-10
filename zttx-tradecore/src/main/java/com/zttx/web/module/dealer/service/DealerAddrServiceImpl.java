/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.module.dealer.entity.DealerAddr;
import com.zttx.web.module.dealer.mapper.DealerAddrMapper;
import com.zttx.web.utils.ListUtils;

/**
 * 经销商地址信息 服务实现类
 * <p>File：DealerAddr.java </p>
 * <p>Title: DealerAddr </p>
 * <p>Description:DealerAddr </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerAddrServiceImpl extends GenericServiceApiImpl<DealerAddr> implements DealerAddrService
{
    private DealerAddrMapper dealerAddrMapper;
    
    @Autowired
    private RegionalService  regionalService;
    
    @Autowired(required = true)
    public DealerAddrServiceImpl(DealerAddrMapper dealerAddrMapper)
    {
        super(dealerAddrMapper);
        this.dealerAddrMapper = dealerAddrMapper;
    }
    
    /**
     * 查询经销商下所有的收货地址
     * @param dealerId
     * @return
     *
     */
    @Override
    public List<DealerAddr> getAllDealerAddrsList(String dealerId)
    {
        if (null != dealerId)
        {
            DealerAddr filter = new DealerAddr();
            filter.setDealerId(dealerId);
            return dealerAddrMapper.findList(filter);
        }
        return null;
    }
    
    /**
     * 获取默认收货地址
     * @param dealerId
     * @return
     * @throws BusinessException
     */
    @Override
    public DealerAddr getDefaultDealerAddrBy(String dealerId) throws BusinessException
    {
        if (null != dealerId)
        {
            DealerAddr filter = new DealerAddr();
            filter.setDealerId(dealerId);
            filter.setDealerDefault(true);
            List<DealerAddr> defaultDealerAdds = dealerAddrMapper.findList(filter);
            if (ListUtils.isNotEmpty(defaultDealerAdds)) { return defaultDealerAdds.get(0); }
        }
        return null;
    }
    
    /**
     * 保存收货地址通用方法
     *
     * @param dealerAddr refrenceId null ：新增 地址总数不能超过20个
     *                   refrenceId not null ：修改
     * @throws BusinessException
     * @author 章旭楠
     */
    @Override
    public void save(DealerAddr dealerAddr) throws BusinessException
    {
        setArea(dealerAddr);// 设置区域
        // 默认地址更新
        if (dealerAddr.getDealerDefault() != null && dealerAddr.getDealerDefault())
        {
            this.updateDealerDefaultAddrs(dealerAddr.getDealerId(), null, false);
        }
        if (StringUtils.isNotBlank(dealerAddr.getRefrenceId()))// to update
        {
            dealerAddr.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(dealerAddr);
        }
        else
        {// to create
            if (this.getTotalDealerAddrCount(dealerAddr.getDealerId()) >= 20) { throw new BusinessException(CommonConst.FAIL.code, "地址数量不能大于 20 条！"); }
            dealerAddr.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerAddr.setCreateTime(CalendarUtils.getCurrentLong());
            this.insertSelective(dealerAddr);
        }
    }
    
    /**
     * 设置区域
     * @param dealerAddr
     */
    private void setArea(DealerAddr dealerAddr)
    {
        String regional = regionalService.getFullNameByAreaNo(dealerAddr.getDealerAddr(), RegionalService.REGIONAL_SPLIT_CODE);
        String[] regionalArr = regional.split(RegionalService.REGIONAL_SPLIT_CODE);
        dealerAddr.setAreaName("");
        if (regionalArr.length == 3)
        {
            dealerAddr.setAreaName(regionalArr[2]);
        }
        dealerAddr.setCityName(regionalArr[1]);
        dealerAddr.setProvinceName(regionalArr[0]);
    }
    
    /**
     * 获取该经销商收货地址总数
     *
     * @param dealerId
     * @return
     */
    @Override
    public int getTotalDealerAddrCount(String dealerId)
    {
        return dealerAddrMapper.getTotalDealerAddrCount(dealerId);
    }
    
    /**
     * 修改经销商默认地址状态
     *
     * @param dealerId   经销商id， 不为空
     * @param refrenceId 地址id ，允许为空
     * @param isDefault  是否默认
     */
    @Override
    public void updateDealerDefaultAddrs(String dealerId, String refrenceId, boolean isDefault)
    {
        this.dealerAddrMapper.updateDealerDefaultAddrs(dealerId, refrenceId, isDefault);
    }
    
    /**
     * 设置默认收货地址
     *
     * @param refrenceId 地址id
     * @param dealerId   经销商id
     * @throws BusinessException
     */
    @Override
    public void setDefaultAddress(String refrenceId, String dealerId) throws BusinessException
    {
        DealerAddr dealerAddr = this.selectByPrimaryKey(refrenceId);
        if (dealerAddr == null) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        this.updateDealerDefaultAddrs(dealerId, null, false);
        dealerAddr.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerAddr.setDealerDefault(true);
        this.updateByPrimaryKeySelective(dealerAddr);
    }
    
    /**
     * 获取区域代码
     *
     * @param province
     * @param city
     * @param county
     * @return
     */
    @Override
    public String getAreaNo(String province, String city, String county)
    {
        String areaNo = province;
        if (!StringUtils.isBlank(city)) areaNo = city;
        if (!StringUtils.isBlank(county)) areaNo = county;
        return areaNo;
    }
    
    @Override
    public PaginateResult<DealerAddr> list(Pagination page, String dealerId)
    {
        return new PaginateResult(page, dealerAddrMapper.list(page, dealerId));
    }
    
    @Override
    public Integer delete(String uuid, String dealerId)
    {
        int code = ExceptionConst.SUCCESS;
        List<DealerAddr> list = dealerAddrMapper.selectDealerAddrList(dealerId);
        int delR = dealerAddrMapper.delete(uuid); // 逻辑删除
        for (int i = 0; i < list.size(); i++)
        {
            DealerAddr dealerAddr = list.get(i);
            if (dealerAddr.getRefrenceId().equals(uuid) && (i + 1) < list.size() && dealerAddr.getDealerDefault())
            {
                DealerAddr newDealerAddr = list.get(i + 1);
                newDealerAddr.setDealerDefault(true);
                dealerAddrMapper.updateByPrimaryKey(newDealerAddr);
                break;
            }
        }
        if (delR == 0)
        {
            code = ExceptionConst.NOEXITS;
        }
        return code;
    }
}
