/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerComplaint;

/**
 * 经销商投诉信息 服务接口
 * <p>File：DealerComplaintService.java </p>
 * <p>Title: DealerComplaintService </p>
 * <p>Description:DealerComplaintService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerComplaintService extends GenericServiceApi<DealerComplaint>
{
    /**
     * 根据订单id 查投诉
     * @param refrenceId
     * @return
     */
    DealerComplaint getDealerComplaint(String refrenceId);
    
    /**
     * 分页查询
     * @param dealerComplaint
     * @param page
     * @return
     */
    PaginateResult<DealerComplaint> searchByClient(DealerComplaint dealerComplaint, Pagination page);
    
    /**
     * 审核修改状态
     * @param dealerComplaint
     * @throws BusinessException
     */
    void updateComState(DealerComplaint dealerComplaint) throws BusinessException;
    
    /**
     * 提交投诉
     * @param dealerComplaint
     * @return
     */
    Short save(DealerComplaint dealerComplaint) throws BusinessException;
    
    /**
     * 通过记录ID和经销商ID查询对应记录
     * @param refrenceId
     * @param dealerId
     * @return
     */
    DealerComplaint loadDealerComplaint(String refrenceId, String dealerId);

    /**
     * 取消申请
     * @param dealerComplaint
     */
    void disApply(DealerComplaint dealerComplaint);
    
    /**
     * 搜索
     * @param dealerComplaint
     * @param page
     * @return
     */
    PaginateResult<DealerComplaint> getList(DealerComplaint dealerComplaint, Pagination page);
}
