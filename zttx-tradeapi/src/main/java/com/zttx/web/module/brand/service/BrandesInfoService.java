/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.BrandesInfoModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.fronts.model.SolrFilter;

/**
 * 品牌商品牌信息 服务接口
 * <p>File：BrandesInfoService.java </p>
 * <p>Title: BrandesInfoService </p>
 * <p>Description:BrandesInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandesInfoService extends GenericServiceApi<BrandesInfo>
{
    /**
     * 取最近一个月销量最好的品牌信息
     * @return
     * @throws BusinessException
     */
    Map<String, List<BrandesInfo>> searchTopSaleBrandes() throws BusinessException;
    
    /**
     * 根据品牌类别取数据
     * <p>
     *     用于首页品牌分类查询使用;
     *      如：
     *          女装品牌馆、男装品牌馆。
     * </p>
     * @param mainId
     * @param notIn
     * @return
     * @throws BusinessException
     */
    Map<String, List<BrandesInfo>> searchBrandesResult(String mainId,String notIn) throws BusinessException;
    
    /**
     * 根据品牌类别取数据
     * <p>
     *     用于首页其他类目推荐品牌;
     * </p>
     * @param mainIds
     * @return
     * @throws BusinessException
     */
    Map<String, List<BrandesInfo>> searchOtherBrandesResult(String[] mainIds) throws BusinessException;
    
    /**
     * solr品牌搜索接口
     * @param filter
     * @param pagination
     * @return {@link PaginateResult<Map<String, Object>>}
     * @throws BusinessException
     */
    PaginateResult<Map<String, Object>> searchAllBrandes(SolrFilter filter, Pagination pagination) throws BusinessException;
    
    /**
     * 品牌索引查询服务
     * <p>
     *     查询对象为品牌实体对象，当前对象为空时返回所有品牌信息
     * </p>
     * @param brandesInfo
     * @return  {@link Long}
     */
    Long findBrandesInfoToSolrCount(BrandesInfo brandesInfo);
    
    /**
     * 品牌索引查询服务
     * <p>
     *     查询对象为品牌实体对象，当前对象为空时返回所有品牌信息
     * </p>
     * @param brandesInfo
     * @param pagination
     * @return  {@link BrandesInfo}
     */
    List<BrandesInfo> findBrandesInfoToSolr(BrandesInfo brandesInfo, Pagination pagination);

    /**
     *
     * @param brandesIdList
     * @return
     */
    List<BrandesInfo> findBrandesInfoToSolr(List<String> brandesIdList);
    
    /**
     * 获取已经在合作状态的品牌列表
     * @param brandId
     * @return
     */
    List<BrandsInfoModel> getCooperatedBrandes(String brandId) throws BusinessException;
    
    /**
     * 获取品牌列表
     * @param brandId
     * @param brandState
     * @return
     */
    List<BrandsInfoModel> list(String brandId, Short brandState) throws BusinessException;
    
    /**
     * 保存新增的品牌
     * @author 陈建平
     * @param brandesInfoModel
     * @param brandsId
     * @return
     * @throws BusinessException
     */
    BrandesInfo save(BrandesInfoModel brandesInfoModel, String brandsId) throws BusinessException;
    
    /**
     * 获取品牌商品牌 最大条形码助记码
     * @author chenjp
     * @param brandId 品牌商ID
     * @return
     */
    String getMaxBrandesInfobarCodeNum(String brandId);
    
    /**
     * 根据品牌编号和品牌商编号取品牌信息
     * @param brandId
     * @param brandsId
     * @return
     */
    BrandesInfo findByBrandIdAndBrandsId(String brandId, String brandsId);
    
    /**
     * 根据ID取品牌和品牌商信息
     * @param brandsId
     * @return
     */
    BrandesInfo findBrandAndBrandesInfo(String brandsId);
    
    /**
     * 根据一组品牌编码取品牌信息
     * @param ids
     * @param mainId
     * @return
     */
    List<BrandesInfo> findBrandesInfoByIds(List<String> ids,String mainId);
    
    /**
     * 判断品牌名称是否存在
     * @param brandName     品牌名称
     * @param brandsId      品牌编号
     * @return
     * @author 施建波
     */
    Boolean isExistBrandName(String brandName, String brandsId);
    
    /**
     * 判断品牌名称是否存在
     * @param brandName     品牌名称
     * @return
     * @author 施建波
     */
    Boolean isExistBrandName(String brandName);
    
    /**
     * 根据品牌商ID、品牌多个状态获取品牌
     * @author 陈建平
     * @param brandId
     * @param brandStates
     * @return
     */
    List<BrandesInfo> listBrandStates(String brandId, String brandStates);
    
    /**
     * 根据品牌商ID、品牌多个状态获取品牌
     * @author 郭小亮
     * @param brandId
     * @param brandStates List<Short>
     * @return
     */
    List<BrandesInfo> listByBrandStates(String brandId, List<Short> brandStates);
    
    /**
     * 根据品牌商编号、品牌编号和状态集合来检测是否适合当前操作
     * @param brandId           品牌商编号
     * @param brandsId          品牌编号
     * @param brandStates       状态集合
     */
    BrandesInfo validatorState(String brandId, String brandsId, Short[] brandStates) throws BusinessException;
    
    /**
     * 设置用户授权
     * @author 章旭楠
     * @param brandsId
     * @param userAuthCodes
     */
    void saveUserAuth(String brandsId, String[] userAuthCodes) throws BusinessException;
    
    /**
     * 查询所有品牌基础信息
     * 包括品牌ID和名称
     * @return
     */
    List<Map<String, Object>> findAllBrandsBaseInfo();
    
    /**
     * 控制品牌首页是否显示
     * @author 陈建平
     * @param refrenceId
     * @param showed
     * @throws BusinessException
     */
    void updateShowed(String refrenceId,short showed) throws BusinessException;
    
    /**
     * 更新品牌的"工厂店品牌"
     * @author 陈建平
     * @param brandsId
     * @param isFactoryStore
     * @param deposit
     * @throws BusinessException
     */
    void updateFactoryStore(String brandsId, boolean isFactoryStore, BigDecimal deposit) throws BusinessException;
    
    /**
     * 修改品牌审核状态
     * @author 陈建平
     * @param refrenceId
     * @param state
     * @param beginTime
     * @param endTime
     * @param dealNos
     * @param createIp
     * @param showed
     * @param checkMark 审核不通过原因
     * @param userId 审核人员
     * @throws BusinessException
     */
    void updateState(String refrenceId, Short state, Long beginTime, Long endTime, String dealNos, Integer createIp, Short showed, String checkMark, String userId) throws BusinessException;
    
    /**
     * 根据主键，更改指定字段的值
     * @author 陈建平
     * @param refrenceId
     * @param fieldName
     * @param fieldValue
     * @return
     */
    BrandesInfo updateField(String refrenceId, String fieldName, Object fieldValue);


}
