/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.brand.entity.BrandNetwork;
import com.zttx.web.module.brand.model.BrandJoinFilter;
import com.zttx.web.module.common.model.TransferSearchModel;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.model.DealerJoinModel;
import com.zttx.web.module.dealer.model.ProductFilter;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 经销商加盟信息 持久层接口
 * <p>File：DealerJoinDao.java </p>
 * <p>Title: DealerJoinDao </p>
 * <p>Description:DealerJoinDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface DealerJoinMapper extends GenericMapper<DealerJoin>
{
    /**
     * 统计合作的数量
     * @param filter
     * @return
     */
    Long getDealerJoinCount(DealerJoin filter);


    /**
     * 根据dealerId获取与该经销商加盟的 map
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<Map<String, Object>> findByDealerId(String dealerId,String joinState);



    /**
     * 根据dealerId获取与该经销商加盟的 map
     * @param dealerId
     * @return
     * @author 江枫林
     */
    List<Map<String, Object>> findJoinByDealerId(@Param("dealerId") String dealerId, @Param("page") Pagination pagination);

    /**
     * 根据加盟终端商id和品牌id 查询有合作的加盟关系
     *
     * @param dealerId
     * @param brandsId
     * @return
     */
    DealerJoin findByDealerIdAndBrandsId(@Param("dealerId") String dealerId, @Param("brandsId") String brandsId);

    /**
     * 根据加盟主键和品牌商id 查询有合作的加盟关系
     *
     * @param refrenceId
     * @param brandId
     * @return
     */
    public DealerJoin findByRefrenceIdAndBrandId(@Param("refrenceId")String refrenceId,@Param("brandId") String brandId);

    /**
     * 根据加盟主键和品牌商id 查询有合作的加盟关系
     *
     * @param refrenceId
     * @param brandId
     * @return
     */
    public Map<String,Object> findByRefrenceIdAndBrandIdMap(@Param("refrenceId")String refrenceId,@Param("brandId") String brandId);

    /**
     * 根据 productFilter 虚拟参数获取经销商加盟产品信息
     * @param filter
     * @return
     * @author 易永耀
     */
    List<Map<String, Object>> selectGrantProductList(ProductFilter filter);
    
    /**
     * 经销商授权产品库  品牌类目查询
     * @param dealerId
     * @return
     * @author 易永耀
     */
    List<Map<String, Object>> selectGrantCataList(String dealerId);
    
    /**
     * 经销商 我的授权产品库  我的浏览记录  校验产品的有效性，用于页面产品状态判断
     * @param dealerId
     * @param productId
     * @return
     * @author 易永耀
     */
    Map<String, Object> getValidMap(String dealerId, String brandsId, String productId);
    
    /**
     * 根据过滤条件获取经销商加盟信息
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String, Object>> getDealerJoinList(DealerJoin filter);
    
    /**
     * 获取所有合作中的经销商，用于发送消息
     * @param brandId
     * @return
     */
    List<String> getDealerIdsWithJoin(String brandId);

    /**
     * 查询终端商加盟关系
     *
     * @param filter
     * @return
     */
    List<DealerJoin> findDealerJoin(@Param("filter")DealerJoin filter);

    /**
     * 查询品牌商已合作列表
     *
     * @param branderId
     * @param pagination
     * @param filter
     * @author 江枫林
     * @return
     */
    List<Map<String, Object>> findByBrandId(@Param("branderId")String branderId, @Param("page")Pagination pagination, @Param("filter")BrandJoinFilter filter);
    
    /**
     * 搜索加盟brandId所有的经销商
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String, Object>> findCooperatedDealer(TransferSearchModel filter);
    
    /**
     * 搜索和dealerId合作的所有的品牌商
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String, Object>> findCooperatedBrand(TransferSearchModel filter);
    
    /**
     * 修改合作品牌为brandsId下的押金信息
     * @author 陈建平
     * @param isPaid
     * @param paidAmount
     * @param paidTime
     * @param brandsId
     * @return
     */
    int updateDealerJoinIsPaid(@Param("isPaid")Boolean isPaid,@Param("paidAmount")BigDecimal paidAmount,@Param("paidTime")Long paidTime, @Param("brandsId")String brandsId);
    
    /**
     * 根据经销商编号DealerId，品牌编号brandsId查询
     * @param dealerId
     * @param brandsId
     * @return
     * @author 李星
     */
    DealerJoin selectDealerJoinByDealerIdAndBrandsId(@Param("dealerId") String dealerId, @Param("brandsId") String brandsId);


    /**
     * 获取当前经绡商最新加盟记录
     *
     * @author 江枫林
     * @param dealerJoin
     * @return
     */
    public List<DealerJoinModel> getDealerNewJoinList(@Param("dealerJoin")DealerJoin dealerJoin);
    
    /**
     * 根据经销商id查找合作的品牌
     * @param dealerId
     * @return
     */
    List<Map<String,Object>> findCopBrandsByDealerId(String dealerId);

    /**
     * 获取未结算的押金记录
     * @author 陈建平
     * @param filter
     * @return
     */
    List<Map<String, Object>> selectUnClearingDepositList(DealerJoin filter);


    /**
     * 查询加盟关系
     *
     * @param dealerId
     * @param brandId
     * @param brandsId
     * @return
     */
    DealerJoin findByDealerIdBrandIdBrandsId(String dealerId, String brandId,String brandsId);


    /**
     * 查询已加盟的品牌库
     *
     * @param pagination
     * @param dealerId
     * @return
     */
    List<Map<String,Object>> findByDealerIdByBrands(@Param("page") Pagination pagination,@Param("dealerId") String  dealerId,@Param("brandName")String brandName,@Param("brandsName")String brandsName);

    /**
     * 查询已加盟的品牌库,无分页
     *
     * @param brandName
     * @param dealerId
     * @return
     */
    List<Map<String,Object>> findByDealerIdByBrands(@Param("dealerId") String  dealerId,@Param("brandName")String brandName,@Param("brandsName")String brandsName);


    /**
     * 通过品牌商id和品牌id来分页查询
     * @param page
     * @param dealerJoin
     * @return
     */
    List<Map<String,Object>> findDealerJoinPage(@Param("page")Pagination page,@Param("dealerJoin")DealerJoin dealerJoin);


    /**
     * 更新dealerjoinLevel
     * @param dealerJoin
     * @param idAry
     */
    void updateDealerLevel(@Param("dealerJoin")DealerJoin dealerJoin,@Param("idAry") String[] idAry);
    
    /**
     * 获取授信关系（终端商ID，品牌商ID）
     * @return
     * @author 张昌苗
     */
    List<Map<String,Object>> listCreditJoin();


    PaginateResult<Map<String,Object>> getNetwortNotDealerList(Pagination page, BrandNetwork brandNetwork);
    
    /**
     * 搜索和dealerId合作的所有的品牌名称
     * @author 陈建平
     * @param dealerId
     * @param brandId
     * @return
     */
    List<String> findCooperatedBrandsName(@Param("dealerId") String  dealerId,@Param("brandId")String brandId);

    /**
     * 根据终端商id和加盟id，查询加盟关系
     *
     * @param dealerId
     * @param joinId
     * @return
     */
    DealerJoin findrWithException(@Param("dealerId")String dealerId,@Param("joinId")String joinId);
    /**
     * 财务帐查经销商与品牌之间的授信关系，不受加盟状态限制
     * @author 易永耀
     * @param dealerId
     * @param brandsId
     * @return
     */
    DealerJoin getByDealerIdAndBrandsId(@Param("dealerId") String dealerId, @Param("brandsId") String brandsId);
    /**
     * 根据 dealerId brandId获取两之间的合作品牌
     * @author 易永耀
     * @param dealerId
     * @param brandId
     * @return
     *  map{brandesName:品牌名称}
     */
    List<Map<String,Object>> findByDealerIdAndBrandId(@Param("dealerId") String dealerId, @Param("brandId") String brandId);
}
