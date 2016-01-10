/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerApply;
import org.apache.ibatis.annotations.Param;

/**
 * 经销商加盟申请 持久层接口
 * <p>File：DealerApplyDao.java </p>
 * <p>Title: DealerApplyDao </p>
 * <p>Description:DealerApplyDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerApplyMapper extends GenericMapper<DealerApply> {

    /**
     * 查询加盟申请信息
     *
     * @param refrenceId
     * @param brandId
     * @return
     */
    DealerApply findByBrandIdAndId(@Param("refrenceId")String refrenceId,@Param("brandId") String brandId);

    /**
     * 查询申请加盟是否存在
     * @param dealerId
     * @param brandsId
     * @return
     * @author 易永耀
     */
    DealerApply selectDealerApplyBy(@Param("dealerId") String dealerId, @Param("brandsId") String brandsId);
}
