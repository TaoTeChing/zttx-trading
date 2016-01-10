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
import com.zttx.web.module.dealer.entity.DealerComplaint;

/**
 * 经销商投诉信息 持久层接口
 * <p>File：DealerComplaintDao.java </p>
 * <p>Title: DealerComplaintDao </p>
 * <p>Description:DealerComplaintDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerComplaintMapper extends GenericMapper<DealerComplaint>
{
    /**
     * 根据订单id 查投诉
     * @param orderId
     * @return
     */
    List<DealerComplaint> getDealerComplaint(@Param("orderId") String orderId);
    
    /**
     * 根据订单号来查询对应的投诉是否存在
     * @param orderId
     * @return
     */
    boolean isExiect(@Param("orderId") String orderId);
    
    /**
     * 通过记录ID和经销商ID查询对应记录
     * @param refrenceId
     * @param dealerId
     * @return
     */
    DealerComplaint loadDealerComplaint(@Param("refrenceId") String refrenceId, @Param("dealerId") String dealerId);
    
    /**
     * 分页条件查询
     * @param dealerComplaint
     * @param page
     * @return
     */
    List<DealerComplaint> getList(@Param("dealerComplaint") DealerComplaint dealerComplaint, @Param("page") Pagination page);

    List<DealerComplaint> searchByClient(@Param("dealerComplaint")DealerComplaint dealerComplaint,@Param("page") Pagination page);
}
