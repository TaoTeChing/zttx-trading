/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandsInfoModel;

/**
 * 品牌商品牌信息 持久层接口
 * <p>File：BrandesInfoDao.java </p>
 * <p>Title: BrandesInfoDao </p>
 * <p>Description:BrandesInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface BrandesInfoMapper extends GenericMapper<BrandesInfo>
{
    /**
     * 根据品牌商ID、品牌状态获取品牌
     *
     * @param brandId
     * @param brandState
     * @return
     */
    List<BrandsInfoModel> listWithFilter(@Param("brandId") String brandId, @Param("brandState") Short brandState);
    
    /**
     * @param brandId
     * @param brandsId
     * @return
     */
    BrandesInfo findBrandesInfo(@Param("brandId") String brandId, @Param("refrenceId") String brandsId);
    
    /**
     * 品牌索引查询服务
     * <p>
     * 查询对象为品牌实体对象，当前对象为空时返回所有品牌信息
     * </p>
     *
     * @param brandesInfo
     * @return {@link Long}
     */
    Long findBrandesInfoToSolrCount(@Param("brandesInfo") BrandesInfo brandesInfo);
    
    /**
     * 品牌索引查询服务
     * <p>
     * 查询对象为品牌实体对象，当前对象为空时返回所有品牌信息
     * </p>
     *
     * @param brandesInfo
     * @param pagination
     * @return {@link BrandesInfo}
     */
    List<BrandesInfo> findBrandesInfoToSolr(@Param("brandesInfo") BrandesInfo brandesInfo, @Param("page") Pagination pagination);
    
    /**
     * 根据品牌商和品牌商状态查询品牌
     *
     * @param brandId
     * @param brandState
     * @return
     */
    List<BrandesInfo> findBybrandIdAndState(@Param("brandId") String brandId, @Param("brandState") Short brandState);
    
    /**
     * 获取品牌商品牌 最大条形码助记码
     *
     * @param brandId 品牌商ID
     * @return
     * @author chenjp
     */
    String getMaxBrandesInfobarCodeNum(String brandId);
    
    /**
     * 根据ID取品牌和品牌商信息
     *
     * @param brandsId
     * @return
     */
    BrandesInfo findBrandAndBrandesInfo(String brandsId);
    
    /**
     * 根据一组品牌编码取品牌信息
     *
     * @param ids
     * @return
     */
    List<BrandesInfo> findBrandesInfoByIds(@Param("ids") List<String> ids,@Param("mainIds")List<String> mainIds);
    
    /**
     * 根据品牌编号和品牌商编号取品牌信息
     *
     * @param brandId
     * @param brandsId
     * @return
     */
    BrandesInfo findByBrandIdAndBrandsId(@Param("brandId") String brandId, @Param("brandsId") String brandsId);
    
    /**
     * 判断品牌名称是否存在
     *
     * @param filter 品牌名称
     * @return
     * @author chenjp
     */
    List<BrandesInfo> isExistBrandName(BrandesInfo filter);
    
    /**
     * 根据条件获取品牌数量
     *
     * @param info
     * @return
     */
    Long getBrandsInfoCount(BrandesInfo info);
    
    /**
     * 根据品牌商ID、品牌多个状态获取品牌
     *
     * @param brandId
     * @param brandStates "1,2"
     * @return
     * @author 陈建平
     */
    List<BrandesInfo> listBrandStates(@Param("brandId") String brandId, @Param("brandStates") String brandStates);
    
    /**
     * 根据品牌商ID、品牌多个状态获取品牌
     *
     * @param brandId
     * @param brandStates List<Short>
     * @return
     * @author 郭小亮
     */
    List<BrandesInfo> listByBrandStates(@Param("brandId") String brandId, @Param("brandStates") List<Short> brandStates);
    
    /**
     * 查询品牌商下品牌信息列表，用于DUBBO接口
     *
     * @param pagination
     * @param brandesInfo
     * @return
     * @throws BusinessException
     * @author 江枫林
     */
    List<BrandesInfo> queryBrandesInfosList(@Param("page") Pagination pagination, @Param("brandesInfo") BrandesInfo brandesInfo) throws BusinessException;
    
    /**
     * 查询所有品牌基础信息
     * 包括品牌ID和名称
     * @return
     */
    List<Map<String, Object>> findAllBrandsBaseInfo();
    
    /**
     * 根据品牌id获取品牌信息
     * @param brandsId
     * @return
     */
    Map<String, Object> getBrandsInfoByBrandsId(String brandsId);
    
    /**
     * 查询首页中品牌的数据
     * @param pagination
     * @param showType
     * @return
     */
    List<BrandsInfoModel> findIndexList(@Param("page") Pagination pagination, @Param("brandState") Short brandState, @Param("showType") Short showType);

    /**
     * 根据id集合查询品牌商信息
     * @param list
     * @return
     */
    List<BrandesInfo> findBrandesInfoToSolrByIds(List<String> list);
}
