/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.dealer.entity.DealerGroom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 推荐给品牌商的经销商 持久层接口
 * <p>File：DealerGroomDao.java </p>
 * <p>Title: DealerGroomDao </p>
 * <p>Description:DealerGroomDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerGroomMapper extends GenericMapper<DealerGroom>
{

    /**
     * 推荐的经销商列表
     *
     * @param pagination
     * @param info
     * @return
     */
    public List<Map<String, Object>> search(Pagination pagination, DealerGroom info);


    /**
     * 校验是否存在
     * @param brandId
     * @param dealerId
     * @return
     */
    Integer isExist(@Param("brandId")String brandId,@Param("dealerId")String dealerId);


    /**
     * 查询指定品牌商所有推荐的终端商
     *
     * @param dealerGroom
     * @return
     */
    List<DealerGroom> listDealerGrooms(@Param("dealerGroom")DealerGroom dealerGroom);

    /**
     * 查询推荐的品牌商列表
     *
     * @param dealerGroom
     * @param pagination
     * @return
     * @throws BusinessException
     */
    public List<DealerGroom> queryDealerGrooms(@Param("dealerGroom")DealerGroom dealerGroom, @Param("pagination")Pagination pagination) throws BusinessException;
}
