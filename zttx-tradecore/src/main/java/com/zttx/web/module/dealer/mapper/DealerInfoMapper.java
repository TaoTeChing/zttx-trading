/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 经销商基础信息 持久层接口
 * <p>File：DealerInfoDao.java </p>
 * <p>Title: DealerInfoDao </p>
 * <p>Description:DealerInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author 江枫林
 * @version 1.0
 */
@MyBatisDao
public interface DealerInfoMapper extends GenericMapper<DealerInfo>
{
    /**
     * 根据dealerId（主键） 获取dealerInfo 信息
     * @param refrenceId
     * @return
     * @author 易永耀
     */
    DealerInfo getDealerInfo(String refrenceId);
    
    List<DealerInfo> findList(Pagination entity);
    
    /**
     * 根据dealerId（主键） 获取dealerInfo 信息，返回扩展属性类
     *
     * @param refrenceId
     * @return
     * @author 江枫林
     */
    DealerInfoModel getDealerInfoModel(String refrenceId);
    
    /**
     *  查询终端商列表，用于品牌商加盟列表
     *
     * @param pagination
     * @param info
     * @return
     */
    List<Map<String, Object>> search(@Param("page") Pagination pagination, @Param("info") DealerInfo info);
    
    /**
     * 根据dealerId获取终端商下所有品类名称
     *
     * @param dealerId
     * @return
     * @author 江枫林
     */
    String searchBrandsNameList(@Param("dealerId") String dealerId);
    
    /**
     * 根据批量id查询终端商列表
     *
     * @param dealerIdList
     * @return
     * @author 江枫林
     */
    List<DealerInfo> getDealerInfoList(@Param("dealerIdList") Map<String, List<String>> dealerIdList, @Param("page") Pagination page);
    
    /**
     * 根据批量id以外的终端商列表
     *
     * @param dealerIdList
     * @return
     * @author 江枫林
     */
    List<DealerInfo> getNotDealerInfoList(@Param("dealerIdList") Map<String, List<String>> dealerIdList, @Param("page") Pagination page);
    
    /**
     * 查找基础终端商信息
     * @return
     */
    List<Map<String, Object>> findAllDealerBaseInfo();
    
    /**
     * 更新服务开始时间和结束时间
     * @param refrenceId
     * @param beginTime
     * @param endTime
     * @return
     */
    Integer updateBeginTimeAndEndTime(@Param("refrenceId") String refrenceId, @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);
    
    /**
     * 根据手机号码和店铺名称查询经销商信息
     * @param show
     * @param userMobile
     * @param dealerName
     * @param pagination
     * @return
     */
    List<Map<String, Object>> getDealerInfosByClient(@Param("isShow") Boolean show, @Param("userMobile") String userMobile, @Param("dealerName") String dealerName,
            @Param("page") Pagination pagination);


    /**
     *
     * @param dealerId
     * @return
     */
    List<Map<String, Object>> findDealerClassById(@Param("dealerId")String dealerId);

    /**
     * 根据终端商编号获取
     * @author 陈建平
     * @param dealerIdList
     * @return
     */
    List<DealerInfo> getDealerInfos(@Param("dealerIdList")List<String> dealerIdList);
    
    /**
     * 获取App需要同步的终端商数据
     * @author 陈建平
     * @param dealerId
     * @return
     */
    List<Map<String, Object>> listAppDealerInfo(@Param("dealerId")String dealerId);
    
    /**
     * 修改经销商是否需要 短信验证收货
     * @param dealerId
     * @param bRcvSmsVerify
     * @return
     * @ahthor 李星
     */
    Integer updateDealuerInfoRcvSmsVerify(@Param("dealerId") String dealerId, @Param("bRcvSmsVerify") boolean bRcvSmsVerify);
}
