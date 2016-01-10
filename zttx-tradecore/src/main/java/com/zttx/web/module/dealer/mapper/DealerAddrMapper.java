/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerAddr;

/**
 * 经销商地址信息 持久层接口
 * <p>File：DealerAddrDao.java </p>
 * <p>Title: DealerAddrDao </p>
 * <p>Description:DealerAddrDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerAddrMapper extends GenericMapper<DealerAddr>
{
    /**
     * 查找经销商 收获地址
     * @param dealerId 经销商id
     * @return
     * @author 易永耀
     */
    List<DealerAddr> selectDealerAddrList(String dealerId);
    
    /**
     * 查经销商 默认的收获地址
     * @param dealerId 经销商id
     * @return
     * @author 易永耀
     */
    DealerAddr getDefaultAddr(String dealerId);
    
    /**
     * 修改经销商默认地址状态
     *
     * @param dealerId   经销商id， 不为空
     * @param refrenceId 地址id ，允许为空
     * @param isDefault  是否默认
     */
    void updateDealerDefaultAddrs(@Param("dealerId") String dealerId, @Param("refrenceId") String refrenceId, @Param("isDefault") boolean isDefault);
    
    /**
     * 获取该经销商收货地址总数
     * @param dealerId 经销商id
     * @return 有效收货地址总数
     */
    int getTotalDealerAddrCount(String dealerId);
    
    /**
     * 分页获取经销商地址
     * @param page
     * @param dealerId
     * @return
     */
    List<DealerAddr> list(@Param("page")Pagination page, @Param("dealerId")String dealerId);
}
