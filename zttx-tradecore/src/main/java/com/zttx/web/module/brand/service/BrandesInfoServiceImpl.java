/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.math.BigDecimal;
import java.util.*;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.sdk.annotation.DataSource;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.db.DataSourceEnum;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.LoggerUtils;
import com.zttx.sdk.utils.ReflectionUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.*;
import com.zttx.web.module.brand.mapper.*;
import com.zttx.web.module.brand.model.BrandesInfoModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.entity.ProductInfo;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.module.dealer.entity.DealerCollect;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.mapper.DealerCollectMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.module.dealer.mapper.DealerOrderMapper;
import com.zttx.web.module.dealer.model.DealerOrderModel;
import com.zttx.web.module.dealer.service.DealerCountService;
import com.zttx.web.module.fronts.model.SolrFilter;
import com.zttx.web.search.module.SolrModel;
import com.zttx.web.search.query.BrandeSolrQueryService;
import com.zttx.web.search.query.ProductSolrQueryService;
import com.zttx.web.search.solrj.BrandeSolrHandler;
import com.zttx.web.search.solrj.ProductSolrHandler;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.ListUtils;

/**
 * 品牌商品牌信息 服务实现类
 * <p>File：BrandesInfo.java </p>
 * <p>Title: BrandesInfo </p>
 * <p>Description:BrandesInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandesInfoServiceImpl extends GenericServiceApiImpl<BrandesInfo> implements BrandesInfoService
{
    private Logger                   logger                 = LoggerFactory.getLogger(BrandesInfoServiceImpl.class);
    
    /**
     * 默认用户授权
     */
    private static final String      USERAUTH_DEFAULT_VALUE = BrandesInfoConst.UserAuth.TOURIST + "," + BrandesInfoConst.UserAuth.MEMBER_REGISTER + ","
                                                                    + BrandesInfoConst.UserAuth.MEMBER_CERTIFIED + "," + BrandesInfoConst.UserAuth.MEMBER_JOIN;
    
    @Autowired
    private BrandRoomService         brandRoomService;
    
    @Autowired
    private BrandCertService         brandCertService;
    
    @Autowired
    private BrandLiceningService     brandLiceningService;
    
    @Autowired
    private BrandPhotoService        brandPhotoService;
    
    @Autowired
    private BrandDealService         brandDealService;
    
    @Autowired
    private BrandCrmService          brandCrmService;
    
    @Autowired
    private BrandsAuditMapper        brandsAuditMapper;
    
    @Autowired
    private BrandsCountMapper        brandsCountMapper;
    
    @Autowired
    private BrandPhotoMapper         brandPhotoMapper;
    
    @Autowired
    private DealerJoinMapper         dealerJoinMapper;
    
    @Autowired
    private BrandInviteMapper        brandInviteMapper;
    
    @Autowired
    private DealerOrderMapper        dealerOrderMapper;
    
    @Autowired
    private DealerCollectMapper      dealerCollectMapper;
    
    @Autowired
    private ProductInfoDubboConsumer productInfoDubboConsumer;
    
    @Autowired
    private BrandeSolrQueryService   brandeSolrQueryService;
    
    @Autowired
    private ProductSolrQueryService  productSolrQueryService;
    
    @Autowired
    private DealerCountService       dealerCountService;
    
    @Autowired
    private DealDicService           dealDicService;
    
    @Autowired
    private BrandCatelogMapper       brandCatelogMapper;
    
    @Autowired
    private BrandsDomainService      brandsDomainService;
    
    @Autowired
    private BrandInfoService         brandInfoService;
    
    @Autowired
    private BrandCountService        brandCountService;
    
    @Autowired
    private BrandeSolrHandler        brandeSolrHandler;
    
    @Autowired
    private ProductSolrHandler       productSolrHandler;
    
    @Autowired
    private BrandsAuditService       brandsAuditService;
    
    @Autowired
    private BrandesWeightInfoMapper  brandesWeightInfoMapper;
    
    @Autowired
    private ProductInfoService       productInfoService;
    
    private BrandesInfoMapper        brandesInfoMapper;
    
    @Autowired(required = true)
    public BrandesInfoServiceImpl(BrandesInfoMapper brandesInfoMapper)
    {
        super(brandesInfoMapper);
        this.brandesInfoMapper = brandesInfoMapper;
    }
    
    @Override
    public Map<String, List<BrandesInfo>> searchTopSaleBrandes() throws BusinessException
    {
        Map<String, List<BrandesInfo>> maps = Maps.newHashMap();
        PaginateResult<Map<String, Object>> result;
        List<BrandesInfo> brandesInfoList;
        SolrFilter filter = new SolrFilter();
        Map<String, String> sorts = Maps.newHashMap();
        sorts.put("saleNum", "desc");
        result = brandeSolrQueryService.searchBrandesByDealMain(filter, sorts, new Pagination(10));
        if (null != result)
        {
            brandesInfoList = Lists.newArrayList();
            for (Map<String, Object> map : result.getList())
            {
                brandesInfoList.add(convertMap(map));
            }
            maps.put("topSales", brandesInfoList);
        }
        return maps;
    }
    
    @Override
    public Map<String, List<BrandesInfo>> searchBrandesResult(String mainId, String notIn) throws BusinessException
    {
        Map<String, List<BrandesInfo>> maps = Maps.newHashMap();
        PaginateResult<Map<String, Object>> result;
        List<BrandesInfo> brandesInfoList;
        // 取当前类目下最新合作的4个品牌
        SolrFilter filter = new SolrFilter(mainId);
        if (notIn != null && notIn.length() > 0)
        {
            notIn = notIn.substring(notIn.indexOf(",") + 1, notIn.length());
            filter.setNotIn(notIn);
        }
        Map<String, String> sorts = Maps.newHashMap();
        sorts.put("updateTime", "desc");
        result = brandeSolrQueryService.searchBrandesByDealMain(filter, sorts, new Pagination(10));
        if (null != result)
        {
            brandesInfoList = Lists.newArrayList();
            filter = new SolrFilter();
            Pagination pagination = new Pagination(3);
            sorts = Maps.newHashMap();
            sorts.put("topTime", "desc");
            for (Map<String, Object> map : result.getList())
            {
                BrandesInfo brandes = convertMap(map);
                filter.setBrandsId(brandes.getRefrenceId());
                filter.setMainId(mainId);
                List<Map<String, Object>> products = productSolrQueryService.searchProduct(filter, sorts, pagination);
                if (null != products && products.size() > 0)
                {
                    brandes.setProductList(convertMap(products));
                    brandesInfoList.add(brandes);
                }
                if (brandesInfoList.size() > 3) break;
            }
            maps.put("newests", brandesInfoList);
        }
        return maps;
    }
    
    @Override
    public Map<String, List<BrandesInfo>> searchOtherBrandesResult(String[] mainIds) throws BusinessException
    {
        Map<String, List<BrandesInfo>> maps = Maps.newHashMap();
        PaginateResult<Map<String, Object>> result;
        List<BrandesInfo> brandesInfoList = Lists.newArrayList();
        Map<String, String> sorts;
        // 取当前类目下最新合作的4个品牌
        for (String mainId : mainIds)
        {
            sorts = Maps.newHashMap();
            sorts.put("updateTime", "desc");
            result = brandeSolrQueryService.searchBrandesByDealMain(new SolrFilter(mainId), sorts, new Pagination(5));
            if (null != result)
            {
                SolrFilter filter = new SolrFilter();
                DealDic dealDic = dealDicService.getDealDicByDealNo(Integer.valueOf(mainId));
                sorts = Maps.newHashMap();
                sorts.put("topTime", "desc");
                for (Map<String, Object> map : result.getList())
                {
                    BrandesInfo brandes = convertMap(map);
                    brandes.setMainId(dealDic.getDealNo());
                    brandes.setMainName(dealDic.getDealName());
                    filter.setBrandsId(brandes.getRefrenceId());
                    filter.setMainId(mainId);
                    List<Map<String, Object>> products = productSolrQueryService.searchProduct(filter, sorts, new Pagination(3));
                    if (null != products && products.size() > 0)
                    {
                        brandes.setProductList(convertMap(products));
                        brandesInfoList.add(brandes);
                        break;
                    }
                }
            }
        }
        maps.put("others", brandesInfoList);
        return maps;
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public PaginateResult<Map<String, Object>> searchAllBrandes(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        PaginateResult<Map<String, Object>> result = new PaginateResult<>();
        SolrModel data = brandeSolrQueryService.searchAllBrandesList(filter, null, null, pagination);
        if (null != data)
        {
            result = data.getResult();
            pagination = new Pagination(2);
            Map<String, String> sorts = Maps.newHashMap();
            sorts.put("productGroom", "desc");
            sorts.put("updateTime", "desc");
            for (Map<String, Object> map : result.getList())
            {
                String brandsId = MapUtils.getString(map, "refrenceId", null);
                filter = new SolrFilter();
                filter.setBrandsId(brandsId);
                List<Map<String, Object>> productList = productSolrQueryService.searchProduct(filter, sorts, pagination);
                map.put("productList", productList);
                // 设置是否已收藏
                map.put("isCollected", isCollected(brandsId));
            }
        }
        return result;
    }
    
    /**
     * 判断是否已经收藏
     *
     * @param brandsId
     * @return
     * @throws BusinessException
     * @author 陈建平
     */
    private Boolean isCollected(String brandsId) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (com.zttx.sdk.utils.StringUtils.isBlank(brandsId) || principal == null || principal.getUserType() != 1
                || com.zttx.sdk.utils.StringUtils.isBlank(principal.getRefrenceId())) return false;
        DealerCollect dealerCollect = dealerCollectMapper.findDealerCollect(principal.getRefrenceId(), brandsId, BrandConstant.DEL_STATE_NONE_DELETED);
        if (null == dealerCollect) return false;
        else return true;
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public Long findBrandesInfoToSolrCount(BrandesInfo brandesInfo)
    {
        return brandesInfoMapper.findBrandesInfoToSolrCount(brandesInfo);
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<BrandesInfo> findBrandesInfoToSolr(BrandesInfo brandesInfo, Pagination pagination)
    {
        List<BrandesInfo> result = brandesInfoMapper.findBrandesInfoToSolr(brandesInfo, pagination);
        setMoreInfo(result);
        return result;
    }
    
    private void setMoreInfo(List<BrandesInfo> brandesInfos)
    {
        for (BrandesInfo info : brandesInfos)
        {
            List<BrandCatelog> brandCatelogs = brandCatelogMapper.selectBrandCatelogsByBrandId(info.getBrandId());
            info.setCatelogList(brandCatelogs);
            List<BrandDeal> brandDealList = brandDealService.selectBrandDealsByBrandesId(info.getRefrenceId());
            info.setDealList(brandDealList);
        }
    }
    
    @Override
    public List<BrandesInfo> findBrandesInfoToSolr(List<String> brandesIdList)
    {
        if (ListUtils.isNotEmpty(brandesIdList))
        {
            List<BrandesInfo> brandesInfos = brandesInfoMapper.findBrandesInfoToSolrByIds(brandesIdList);
            setMoreInfo(brandesInfos);
            return brandesInfos;
        }
        return null;
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<BrandsInfoModel> getCooperatedBrandes(String brandId) throws BusinessException
    {
        return list(brandId, BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<BrandsInfoModel> list(String brandId, Short brandState) throws BusinessException
    {
        List<BrandsInfoModel> list = brandesInfoMapper.listWithFilter(brandId, brandState);
        if (null != list && !list.isEmpty())
        {
            for (BrandsInfoModel item : list)
            {
                BrandPhoto photo = brandPhotoMapper.findBrandPhoto(brandId, item.getRefrenceId());
                if (null != photo)
                {
                    item.setImageName(photo.getImageName());
                    item.setDomainName(photo.getDomainName());
                }
                if (BrandConstant.BrandesInfoConst.BRAND_STATE_FAILURE == item.getBrandState().shortValue())
                {
                    List<Map<String, Object>> auditList = brandsAuditMapper.getBrandsAuditMarkList(item.getRefrenceId(),
                            BrandConstant.BrandsAuditConst.CHECK_STATE_NO_AUDIT);
                    if (null != auditList && !auditList.isEmpty())
                    {
                        Object obj = auditList.get(0).get("checkMark");
                        if (null != obj && StringUtils.isNotBlank(obj.toString()))
                        {
                            item.setCheckMark(obj.toString());
                        }
                    }
                }
                BrandsCount brandsCount = modifyBrandsCount(item.getRefrenceId(), null);
                item.setBrandsCount(brandsCount);
            }
        }
        return list;
    }
    
    /**
     * 重新设置统计数据
     *
     * @param brandsId
     * @param countTypeName
     * @return
     */
    private BrandsCount modifyBrandsCount(String brandsId, String[] countTypeName) throws BusinessException
    {
        if (StringUtils.isBlank(brandsId)) { return null; }
        BrandesInfo brandesInfo = selectByPrimaryKey(brandsId);
        if (null == brandesInfo) { return null; }
        // BrandsCount brandsCount = (BrandsCount) super.getUserCache(brandesInfo.getBrandId(), BrandsCount.class, brandesInfo.getRefrenceId());
        BrandsCount brandsCount = brandsCountMapper.findByBrandIdAndBrandesId(brandesInfo.getBrandId(), brandesInfo.getRefrenceId());
        Boolean isExist = true;
        if (null == brandsCount)
        {
            isExist = false;
            brandsCount = new BrandsCount();
            brandsCount.setBrandsId(brandesInfo.getRefrenceId());
            brandsCount.setBrandId(brandesInfo.getBrandId());
            brandsCount.setCreateTime(new Date().getTime());
            brandsCount.setUpdateTime(new Date().getTime());
            brandsCount.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        }
        DealerJoin filter = new DealerJoin();
        filter.setBrandId(brandesInfo.getBrandId());
        filter.setBrandsId(brandesInfo.getRefrenceId());
        Long count = 0L;
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT))
        {
            // 合作中的经销商
            count = dealerJoinMapper.getDealerJoinCount(filter);
            brandsCount.setJoinCount(count.intValue());
        }
        BrandInvite brandInvite = new BrandInvite();
        brandInvite.setBrandId(brandesInfo.getBrandId());
        brandInvite.setBrandsId(brandesInfo.getRefrenceId());
        brandInvite.setApplyState((short) BrandConstant.BrandInviteConst.APPLY_STATE_NOCHECK);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_APPLYCOUNT))
        {
            // 申请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandsCount.setApplyCount(count.intValue());
        }
        brandInvite.setApplyState((short) BrandConstant.BrandInviteConst.APPLY_STATE_INVITE);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_INVITECOUNT))
        {
            // 邀请中的经销商
            count = brandInviteMapper.getBrandInviteCount(brandInvite);
            brandsCount.setInviteCount(count.intValue());
        }
        DealerOrderModel dealerOrder = new DealerOrderModel();
        dealerOrder.setBrandId(brandesInfo.getBrandId());
        dealerOrder.setBrandsId(brandesInfo.getRefrenceId());
        dealerOrder.setOrderCountType(DealerConstant.DealerOrder.ORDER_STATUS_TO_RECEIVE);
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_ORDERCOUNT))
        {
            // 发货订单数量
            count = dealerOrderMapper.getDealerOrderCount(dealerOrder);
            brandsCount.setOrderNum(count.intValue());
        }
        ProductBaseInfo productInfo = new ProductBaseInfo();
        productInfo.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName)
                || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT))
        {
            // 产品数量
            try
            {
                count = productInfoDubboConsumer.getProductBaseInfoCount(productInfo);
                brandsCount.setProductCount(count.intValue());
            }
            catch (Exception e)
            {
                LoggerUtils.logError(logger, e.getLocalizedMessage());
            }
        }
        DealerCollect dealerCollect = new DealerCollect();
        dealerCollect.setBrandsId(brandesInfo.getRefrenceId());
        if (ArrayUtils.isEmpty(countTypeName) || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_FAVNUM))
        {
            // 收藏数量
            count = dealerCollectMapper.getDealerCollectCount(dealerCollect);
            brandsCount.setFavNum(count.intValue());
        }
        if (isExist)
        {
            brandsCount.setUpdateTime(CalendarUtils.getCurrentTime());
            brandsCountMapper.updateByPrimaryKey(brandsCount);
            if (ArrayUtils.isEmpty(countTypeName)
                    || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT))
            {
                brandeSolrHandler.addBrandsInfoList(brandesInfoMapper.findBrandesInfoToSolr(brandesInfo, null));
            }
            return brandsCount;
        }
        else
        {
            brandsCountMapper.insert(brandsCount);
            if (ArrayUtils.isEmpty(countTypeName)
                    || -1 != com.zttx.web.utils.StringUtils.strArraySearch(countTypeName, BrandConstant.BrandsCountConst.BRANDSCOUNT_JOINCOUNT))
            {
                brandeSolrHandler.addBrandsInfoList(brandesInfoMapper.findBrandesInfoToSolr(brandesInfo, null));
            }
            return brandsCount;
        }
    }
    
    /**
     * 保存新增的品牌
     *
     * @param brandesInfoModel
     * @param brandsId
     * @return
     * @throws BusinessException
     * @author 陈建平
     */
    @Override
    public BrandesInfo save(BrandesInfoModel brandesInfoModel, String brandsId) throws BusinessException
    {
        BrandesInfo brandesInfo = null;
        if (StringUtils.isNotBlank(brandsId))
        {
            // 把图片从临时文件夹移动到正式文件夹
            moveImgFromTemp(brandesInfoModel);
            brandesInfo = updateBrands(brandesInfoModel, brandsId);
            if (StringUtils.isNotBlank(brandesInfoModel.getMainProLogo()))
            {
                if (!brandesInfoModel.getMainProLogo().equals(brandesInfo.getProLogo()))
                {
                    brandesInfo.setProLogo(brandesInfoModel.getMainProLogo());
                }
            }
            else
            {
                brandesInfo.setProLogo(brandesInfoModel.getPhotoImgPaths()[brandesInfoModel.getPhotoImgPaths().length - 1]);
            }
            brandesInfo.setBrandLogo(brandesInfoModel.getLogoImgPath());
            brandesInfo.setRecommendImage(brandesInfoModel.getRecommendImagePath());
            // 条形码助记码
            if (StringUtils.isBlank(brandesInfo.getBarCodeNum()))
            {
                String nextBarCodeNum = "";
                String currentBarCodeNum = brandesInfoMapper.getMaxBrandesInfobarCodeNum(brandesInfo.getBrandId());
                if (StringUtils.isBlank(currentBarCodeNum))
                {
                    nextBarCodeNum = com.zttx.sdk.utils.SerialnoUtils.buildNextBarCodeNum(BrandConstant.BRANDESINFO_BARCODENUM_DEAFAULT,
                            BrandConstant.BRANDESINFO_BARCODENUM_LENGTH);
                }
                else
                {
                    nextBarCodeNum = com.zttx.sdk.utils.SerialnoUtils.buildNextBarCodeNum(currentBarCodeNum, BrandConstant.BRANDESINFO_BARCODENUM_LENGTH);
                }
                brandesInfo.setBarCodeNum(nextBarCodeNum);
            }
            brandesInfoMapper.updateByPrimaryKey(brandesInfo);
        }
        else
        {
            if (isExistBrandName(brandesInfoModel.getBrandName())) { throw new BusinessException(BrandConst.BRANDES_NAME_EXISTS); }
            // 把图片从临时文件夹移动到正式文件夹
            moveImgFromTemp(brandesInfoModel);
            // 写入品牌商品牌信息
            brandesInfo = brandesInfoModel.parseBrandesInfo();
            brandesInfo.setProLogo(brandesInfoModel.getPhotoImgPaths()[brandesInfoModel.getPhotoImgPaths().length - 1]);
            // 添加条形码助记码
            String nextBarCodeNum = "";
            String currentBarCodeNum = brandesInfoMapper.getMaxBrandesInfobarCodeNum(brandesInfo.getBrandId());
            if (StringUtils.isBlank(currentBarCodeNum))
            {
                nextBarCodeNum = com.zttx.sdk.utils.SerialnoUtils.buildNextBarCodeNum(BrandConstant.BRANDESINFO_BARCODENUM_DEAFAULT,
                        BrandConstant.BRANDESINFO_BARCODENUM_LENGTH);
            }
            else
            {
                nextBarCodeNum = com.zttx.sdk.utils.SerialnoUtils.buildNextBarCodeNum(currentBarCodeNum, BrandConstant.BRANDESINFO_BARCODENUM_LENGTH);
            }
            brandesInfo.setBarCodeNum(nextBarCodeNum);
            /* 需求 #5430 新增用户授权功能 设置默认授权 修改人 ：章旭楠 2015-07-28 */
            brandesInfo.setUserAuth(USERAUTH_DEFAULT_VALUE);
            brandesInfo.setShowed(false);
            brandesInfo.setFactoryStore(false);
            brandesInfoMapper.insert(brandesInfo);
            BrandesWeightInfo brandesWeightInfo = new BrandesWeightInfo();
            brandesWeightInfo.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandesWeightInfo.setBrandesId(brandesInfo.getRefrenceId());
            brandesWeightInfo.setBrandId(brandesInfo.getBrandId());
            brandesWeightInfo.setWeight(1);
            brandesWeightInfo.setDelFlag(false);
            brandesWeightInfo.setCreateTime(CalendarUtils.getCurrentLong());
            brandesWeightInfo.setUpdateTime(CalendarUtils.getCurrentLong());
            brandesWeightInfoMapper.insert(brandesWeightInfo);
            // 添加一条展厅信息
            Long time = CalendarUtils.getCurrentLong();
            BrandRoom brandRoom = new BrandRoom();
            brandRoom.setRefrenceId(brandesInfo.getRefrenceId());
            brandRoom.setBrandId(brandesInfo.getBrandId());
            brandRoom.setRoomName(brandesInfo.getBrandsName());
            brandRoom.setDomainName(brandesInfo.getLogoDomin());
            brandRoom.setLogoPhoto(brandesInfoModel.getLogoImgName());
            // String roomLogoPath = FileClientUtil.copyFile(request, brandesInfo.getBrandLogo(),UploadAttCateConst.BRANDS_LOGO);
            brandRoom.setLogoImage(brandesInfo.getBrandLogo());
            brandRoom.setBrandMark(brandesInfo.getBrandMark());
            brandRoom.setCreateTime(time);
            brandRoom.setUserId(brandesInfoModel.getUserId());
            brandRoom.setUpdateTime(time);
            brandRoomService.insert(brandRoom);
        }
        // 写入品牌证书
        List<BrandCert> brandCertList = brandesInfoModel.parseBrandCertList(brandesInfo);
        if (null != brandCertList && brandCertList.size() > 0)
        {
            brandCertService.insertBatch(brandCertList);
        }
        if (brandesInfo.getBrandType().shortValue() == BrandConstant.BrandesInfoConst.BRAND_TYPE_FOREIGN)
        {
            List<BrandLicening> brandLiceningList = brandesInfoModel.parseBrandLiceningList(brandesInfo);
            brandLiceningService.insertBatch(brandLiceningList);
        }
        // 写入品牌形象照片
        List<BrandPhoto> brandPhotoList = brandesInfoModel.parseBrandPhotoList(brandesInfo);
        for (BrandPhoto brandPhoto : brandPhotoList)
        {
            brandPhotoService.insert(brandPhoto);
        }
        // brandPhotoService.insertBatch(brandPhotoList);
        // 写入品牌主营品类
        List<BrandDeal> brandDealList = brandesInfoModel.parseBrandDealList(brandesInfo);
        if (null != brandDealList && brandDealList.size() > 0)
        {
            brandDealService.insertBatch(brandDealList);
        }
        // 修改搜索引擎
        // updateSolrData(brandesInfo, null, true);
        brandeSolrHandler.addBrandsInfoList(findBrandesInfoToSolr(brandesInfo, null));
        List<Integer> noList = brandDealService.findDealNoBy(brandesInfo.getBrandId(), brandesInfo.getRefrenceId());
        BrandsInfoModel brandsInfoModel = JSONObject.parseObject(JSON.toJSONString(brandesInfo), BrandsInfoModel.class);
        brandsInfoModel.setDealNo(noList);
        brandCrmService.save(JSON.toJSONString(brandsInfoModel), ClientConstant.BRANDES_INFO);
        return brandesInfo;
    }
    
    /**
     * 判断品牌名称是否存在
     *
     * @param brandName 品牌名称
     * @param brandsId  品牌编号
     * @return
     * @author chenjp
     */
    @Override
    public Boolean isExistBrandName(String brandName, String brandsId)
    {
        BrandesInfo filter = new BrandesInfo();
        filter.setBrandsName(brandName);
        filter.setRefrenceId(brandsId);
        List<BrandesInfo> list = brandesInfoMapper.isExistBrandName(filter);
        if (list == null || list.size() == 0) { return false; }
        return true;
    }
    
    /**
     * 判断品牌名称是否存在
     *
     * @param brandName 品牌名称
     * @return
     * @author chenjp
     */
    @Override
    public Boolean isExistBrandName(String brandName)
    {
        if (StringUtils.isBlank(brandName)) { return true; }
        BrandesInfo filter = new BrandesInfo();
        filter.setBrandsName(brandName);
        List<BrandesInfo> list = brandesInfoMapper.isExistBrandName(filter);
        if (list == null || list.size() == 0) { return false; }
        return true;
    }
    
    public void moveImgFromTemp(BrandesInfoModel brandesInfoModel) throws BusinessException
    {
        // 该方法只是实现本地文件移到的功能
        String resultPath = brandesInfoModel.getLogoImgPath();
        // 移动图片（Logo）
        if (StringUtils.isNotBlank(resultPath))
        {
            resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandesInfoModel.getLogoImgPath(), UploadAttCateConst.BRANDS_LOGO);
            brandesInfoModel.setLogoImgPath(resultPath);
        }
        // 移动图片（品牌证书）
        for (int i = 0; i < brandesInfoModel.getCertImgPaths().length; i++)
        {
            String imgPath = brandesInfoModel.getCertImgPaths()[i];
            if (StringUtils.isNotBlank(imgPath))
            {
                resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, imgPath, UploadAttCateConst.BRANDS_CERT);
                brandesInfoModel.getCertImgPaths()[i] = resultPath;
            }
        }
        if (BrandConstant.BrandesInfoConst.BRAND_TYPE_FOREIGN == brandesInfoModel.getBrandType().shortValue())
        {
            for (int i = 0; i < brandesInfoModel.getLiceningImgPaths().length; i++)
            {
                String imgPath = brandesInfoModel.getLiceningImgPaths()[i];
                if (StringUtils.isNotBlank(imgPath))
                {
                    resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, imgPath, UploadAttCateConst.BRAND_LICENING);
                    brandesInfoModel.getLiceningImgPaths()[i] = resultPath;
                }
            }
        }
        // 移动图片（品牌形象照片）
        for (int i = 0; i < brandesInfoModel.getPhotoImgPaths().length; i++)
        {
            String imgPath = brandesInfoModel.getPhotoImgPaths()[i];
            if (StringUtils.isNotBlank(imgPath))
            {
                resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, imgPath, UploadAttCateConst.PRODUCT_GRAPH);
                brandesInfoModel.getPhotoImgPaths()[i] = resultPath;
            }
        }
        // 移动推荐图片
        resultPath = brandesInfoModel.getRecommendImagePath();
        if (StringUtils.isNotBlank(resultPath))
        {
            resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandesInfoModel.getRecommendImagePath(), UploadAttCateConst.BRANDS_LOGO);
            brandesInfoModel.setRecommendImagePath(resultPath);
        }
    }
    
    private BrandesInfo updateBrands(BrandesInfoModel info, String brandsId) throws BusinessException
    {
        String brandId = info.getBrandId();
        BrandesInfo dbInfo = findByBrandIdAndBrandsId(brandId, brandsId);
        dbInfo.setBrandsName(info.getBrandName());
        dbInfo.setBrandHold(info.getBrandHold());
        dbInfo.setHoldName(info.getHoldName());
        dbInfo.setBrandMark(info.getBrandMark());
        dbInfo.setBrandSubMark(info.getBrandSubMark());
        if (BrandConst.BRAND_STATE_FAIL.getCode().shortValue() == dbInfo.getBrandState().shortValue())
        {
            // 如果未通过审核则重置为新增状态
            dbInfo.setBrandState(BrandConst.BRAND_STATE_NEW.getCode().shortValue());
        }
        // 重置为新增状态
        dbInfo.setCreateIp(info.getCreateIp());
        dbInfo.setBrandType(info.getBrandType());
        updateCert(info, brandId, brandsId);
        updateLicening(info, brandId, brandsId);
        updatePhoto(info, brandId, brandsId);
        updateDeal(info, brandId, brandsId);
        return dbInfo;
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public BrandesInfo findByBrandIdAndBrandsId(String brandId, String brandsId)
    {
        return brandesInfoMapper.findByBrandIdAndBrandsId(brandId, brandsId);
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public BrandesInfo findBrandAndBrandesInfo(String brandsId)
    {
        return brandesInfoMapper.findBrandAndBrandesInfo(brandsId);
    }
    
    @Override
    @DataSource(DataSourceEnum.SLAVE)
    public List<BrandesInfo> findBrandesInfoByIds(List<String> ids, String mainId)
    {
        List<String> mainList = null;
        if (StringUtils.isNotBlank(mainId))
        {
            mainList = Lists.newArrayList();
            String[] mainIds = mainId.split(",");
            for (String id : mainIds)
            {
                mainList.add(id);
            }
        }
        return brandesInfoMapper.findBrandesInfoByIds(ids, mainList);
    }
    
    /**
     * 修改品牌证书
     *
     * @param info     传入的品牌信息
     * @param brandId  品牌商编号
     * @param brandsId 品牌编号
     * @author chenjp
     */
    @DataSource(DataSourceEnum.MASTER)
    private void updateCert(BrandesInfoModel info, String brandId, String brandsId)
    {
        List<BrandCert> brandCertList = brandCertService.findByBrandsId(brandId, brandsId, BrandConstant.DEL_STATE_NONE_DELETED);
        List<BrandCert> delBrandCertList = Lists.newArrayList();
        if (null != brandCertList && !brandCertList.isEmpty())
        {
            for (BrandCert cert : brandCertList)
            {
                int size = com.zttx.web.utils.StringUtils.strArraySearch(info.getCertImgPaths(), cert.getImageName());
                if (size >= 0)
                {
                    info.getCertImgPaths()[size] = "";
                }
                else
                {
                    cert.setDelFlag(BrandConstant.DEL_STATE_DELETED);
                    delBrandCertList.add(cert);
                }
            }
            if (null != delBrandCertList && delBrandCertList.size() > 0)
            {
                brandCertService.updateBatch(delBrandCertList);
            }
        }
    }
    
    /**
     * 修改品牌授权证书
     *
     * @param info     传入的品牌信息
     * @param brandId  品牌商编号
     * @param brandsId 品牌编号
     * @author 陈建平
     */
    private void updateLicening(BrandesInfoModel info, String brandId, String brandsId)
    {
        if (info.getBrandType().shortValue() == BrandConstant.BrandesInfoConst.BRAND_TYPE_DOMESTIC)
        {
            brandLiceningService.updateDelState(brandId, brandsId);
        }
        else
        {
            List<BrandLicening> liceningList = brandLiceningService.findByBrandsId(brandId, brandsId, BrandConstant.DEL_STATE_NONE_DELETED);
            List<BrandLicening> delLiceningList = Lists.newArrayList();
            if (null != liceningList && !liceningList.isEmpty())
            {
                for (BrandLicening licening : liceningList)
                {
                    int size = com.zttx.web.utils.StringUtils.strArraySearch(info.getLiceningImgPaths(), licening.getImageName());
                    if (size >= 0)
                    {
                        info.getLiceningImgPaths()[size] = "";
                    }
                    else
                    {
                        licening.setDelFlag(BrandConstant.DEL_STATE_DELETED);
                        delLiceningList.add(licening);
                    }
                }
                if (null != delLiceningList && delLiceningList.size() > 0)
                {
                    brandLiceningService.updateBatch(delLiceningList);
                }
            }
        }
    }
    
    /**
     * 修改品牌形象照片
     *
     * @param info     传入的品牌信息
     * @param brandId  品牌商编号
     * @param brandsId 品牌编号
     * @author chenjp
     */
    private void updatePhoto(BrandesInfoModel info, String brandId, String brandsId)
    {
        List<BrandPhoto> brandPhotoList = brandPhotoService.findByBrandIdAndBrandsId(brandId, brandsId, BrandConstant.DEL_STATE_NONE_DELETED);
        List<BrandPhoto> delPhotoList = Lists.newArrayList();
        if (null != brandPhotoList && !brandPhotoList.isEmpty())
        {
            for (BrandPhoto photo : brandPhotoList)
            {
                int size = com.zttx.web.utils.StringUtils.strArraySearch(info.getPhotoImgPaths(), photo.getImageName());
                if (size >= 0)
                {
                    info.getPhotoImgPaths()[size] = "";
                    // 获取数据库最先上传的图片
                    info.setMainProLogo(photo.getImageName());
                }
                else
                {
                    photo.setDelFlag(BrandConstant.DEL_STATE_DELETED);
                    delPhotoList.add(photo);
                }
            }
            if (null != delPhotoList && delPhotoList.size() > 0)
            {
                brandPhotoService.updateBatch(delPhotoList);
            }
        }
    }
    
    /**
     * 修改品牌主营品类
     *
     * @param info     传入的品牌信息
     * @param brandId  品牌商编号
     * @param brandsId 品牌编号
     * @author chenjp
     */
    private void updateDeal(BrandesInfoModel info, String brandId, String brandsId)
    {
        List<BrandDeal> brandDealList = brandDealService.findByBrandsId(brandId, brandsId, BrandConstant.DEL_STATE_NONE_DELETED);
        List<BrandDeal> delDealList = Lists.newArrayList();
        if (null != brandDealList && !brandDealList.isEmpty())
        {
            for (BrandDeal deal : brandDealList)
            {
                int size = com.zttx.web.utils.StringUtils.strArraySearch(info.getDeals(), String.valueOf(deal.getDealNo()));
                if (size >= 0)
                {
                    info.getDeals()[size] = "";
                }
                else
                {
                    deal.setDelFlag(BrandConstant.DEL_STATE_DELETED);
                    delDealList.add(deal);
                }
            }
            if (null != delDealList && delDealList.size() > 0)
            {
                brandDealService.updateBatch(delDealList);
            }
        }
    }
    
    /**
     * 获取品牌商品牌 最大条形码助记码
     *
     * @param brandId 品牌商ID
     * @return
     * @author chenjp
     */
    @Override
    public String getMaxBrandesInfobarCodeNum(String brandId)
    {
        return brandesInfoMapper.getMaxBrandesInfobarCodeNum(brandId);
    }
    
    /**
     * 根据品牌商ID、品牌多个状态获取品牌
     *
     * @param brandId
     * @param brandStates
     * @return
     * @author 陈建平
     */
    @Override
    public List<BrandesInfo> listBrandStates(String brandId, String brandStates)
    {
        return brandesInfoMapper.listBrandStates(brandId, brandStates);
    }
    
    /**
     * 根据品牌商编号、品牌编号和状态集合来检测是否适合当前操作
     *
     * @param brandId     品牌商编号
     * @param brandsId    品牌编号
     * @param brandStates 状态集合
     */
    @Override
    public BrandesInfo validatorState(String brandId, String brandsId, Short[] brandStates) throws BusinessException
    {
        BrandesInfo brandesInfo = brandesInfoMapper.findBrandesInfo(brandId, brandsId);
        if (null == brandesInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        Arrays.sort(brandStates);
        int size = Arrays.binarySearch(brandStates, brandesInfo.getBrandState().shortValue());
        if (size < 0) { throw new BusinessException(BrandConst.BRANDES_STATE_ERROR); }
        return brandesInfo;
    }
    
    /**
     * 设置用户授权
     *
     * @param brandsId
     * @param userAuthCodes
     * @author 章旭楠
     */
    @Override
    public void saveUserAuth(String brandsId, String[] userAuthCodes) throws BusinessException
    {
        if (null == brandsId) { throw new BusinessException(CommonConst.PARAM_NULL); }
        BrandesInfo info = selectByPrimaryKey(brandsId);
        if (null == info) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        String userAuthCodesString = userAuthCodes == null ? "" : ListUtils.join2String(Arrays.asList(userAuthCodes));
        info.setUserAuth(userAuthCodesString);
        brandesInfoMapper.updateByPrimaryKey(info);
    }
    
    @Override
    public List<Map<String, Object>> findAllBrandsBaseInfo()
    {
        return brandesInfoMapper.findAllBrandsBaseInfo();
    }
    
    /**
     * 转换Map到BrandesInfo对象
     *
     * @param map
     * @return BrandesInfo
     */
    BrandesInfo convertMap(Map<String, Object> map)
    {
        BrandesInfo brandesInfo = new BrandesInfo();
        brandesInfo.setRefrenceId(MapUtils.getString(map, "refrenceId", null));
        brandesInfo.setBrandId(MapUtils.getString(map, "brandId", null));
        BrandInfo brandInfo = new BrandInfo();
        brandInfo.setRefrenceId(MapUtils.getString(map, "brandId", null));
        brandInfo.setComName(MapUtils.getString(map, "comName", null));
        brandInfo.setDealType(MapUtils.getShort(map, "dealType", null));
        brandInfo.setLegalName(MapUtils.getString(map, "legalName", null));
        brandInfo.setProvinceName(MapUtils.getString(map, "provinceName", null));
        brandInfo.setCityName(MapUtils.getString(map, "cityName", null));
        brandInfo.setAreaName(MapUtils.getString(map, "areaName", null));
        brandInfo.setComAddress(MapUtils.getString(map, "comAddress", null));
        brandInfo.setRegMoney(new BigDecimal(MapUtils.getDouble(map, "regMoney", null)));
        brandesInfo.setBrandInfo(brandInfo);
        BrandsCount brandsCount = new BrandsCount();
        brandsCount.setApplyCount(MapUtils.getInteger(map, "applyNum", null));
        brandsCount.setFavNum(MapUtils.getInteger(map, "favNum", null));
        brandsCount.setProductCount(MapUtils.getInteger(map, "productNum", null));
        brandsCount.setViewNum(MapUtils.getInteger(map, "viewNum", null));
        brandsCount.setInviteCount(MapUtils.getInteger(map, "inviteNum", null));
        brandesInfo.setBrandsCount(brandsCount);
        ;
        brandesInfo.setBrandsName(MapUtils.getString(map, "brandName", null));
        brandesInfo.setBrandType(MapUtils.getShort(map, "brandType", null));
        brandesInfo.setBrandLogo(MapUtils.getString(map, "brandLogo", null));
        brandesInfo.setProLogo(MapUtils.getString(map, "proLogo", null));
        brandesInfo.setHoldName(MapUtils.getString(map, "holdName", null));
        brandesInfo.setBrandMark(MapUtils.getString(map, "brandMark", null));
        brandesInfo.setRecommendImage(MapUtils.getString(map, "recommendImage", null));
        brandesInfo.setCreateTime(CalendarUtils.getLongFromTime(MapUtils.getObject(map, "createTime", null)));
        brandesInfo.setUpdateTime(CalendarUtils.getLongFromTime(MapUtils.getObject(map, "updateTime", null)));
        return brandesInfo;
    }
    
    /**
     * 转换Map到List对象
     *
     * @param maps
     * @return BrandesInfo
     */
    List<ProductInfo> convertMap(List<Map<String, Object>> maps)
    {
        List<ProductInfo> productInfos = Lists.newArrayList();
        for (Map<String, Object> map : maps)
        {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setRefrenceId(MapUtils.getString(map, "refrenceId", null));
            productInfo.setProductNo(MapUtils.getString(map, "productNo", null));
            productInfo.setProductTitle(MapUtils.getString(map, "productTitle", null));
            productInfo.setProductImage(MapUtils.getString(map, "productImage", null));
            productInfo.setProductPrice(new BigDecimal(MapUtils.getDouble(map, "productPrice", null)));
            productInfo.setCreateTime(CalendarUtils.getLongFromTime(MapUtils.getObject(map, "createTime", null)));
            productInfo.setUpdateTime(CalendarUtils.getLongFromTime(MapUtils.getObject(map, "updateTime", null)));
            productInfos.add(productInfo);
        }
        return productInfos;
    }
    
    /**
     * 根据品牌商ID、品牌多个状态获取品牌
     *
     * @param brandId
     * @param brandStates
     * @return
     * @author 陈建平
     */
    @Override
    public List<BrandesInfo> listByBrandStates(String brandId, List<Short> brandStates)
    {
        return brandesInfoMapper.listByBrandStates(brandId, brandStates);
    }
    
    /**
     * 控制品牌首页是否显示
     * @author 陈建平
     * @param refrenceId
     * @param showed
     * @throws BusinessException
     */
    @Override
    public void updateShowed(String refrenceId, short showed) throws BusinessException
    {
        if (StringUtils.isBlank(refrenceId)) { throw new BusinessException("品牌编号不能为空"); }
        BrandesInfo brandesInfo = selectByPrimaryKey(refrenceId);
        if (null == brandesInfo) { throw new BusinessException("该品牌商信息不存在或已删除"); }
        boolean flag = showed > 0 ? true : false;
        brandesInfo.setShowed(flag);
        brandesInfoMapper.updateByPrimaryKey(brandesInfo);
        if (flag)
        {
            brandeSolrHandler.addBrandsInfoList(findBrandesInfoToSolr(brandesInfo, null));
        }
        else
        {
            brandeSolrHandler.removeBrandesInfo(brandesInfo);
        }
    }
    
    /**
     * 更新品牌的"工厂店品牌"
     * @author 陈建平
     * @param brandsId
     * @param isFactoryStore
     * @param deposit
     * @throws BusinessException
     */
    @Override
    public void updateFactoryStore(String brandsId, boolean isFactoryStore, BigDecimal deposit) throws BusinessException
    {
        if (StringUtils.isBlank(brandsId)) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        BrandesInfo brandesInfo = selectByPrimaryKey(brandsId);
        brandesInfo.setFactoryStore(isFactoryStore);
        brandesInfo.setDeposit(deposit);
        updateByPrimaryKey(brandesInfo);
        if (isFactoryStore && deposit.compareTo(BigDecimal.ZERO) == 0) // 是工厂店,并且押金为0;
        {
            DealerJoin filter = new DealerJoin();
            filter.setBrandsId(brandsId);
            filter.setIsPaid(false);
            filter.setJoinState(DealerConstant.DealerJoin.COOPERATION);
            List<DealerJoin> dealerJoinList = dealerJoinMapper.findList(filter); // 得到所有与brandsId 状态是"合作,未支付"的 信息
            int success = dealerJoinMapper.updateDealerJoinIsPaid(true, BigDecimal.ZERO, CalendarUtils.getCurrentLong(), brandsId); // 状态是"合作,未支付"才会修改为"已支付押金"
            if (success > 0 && null != dealerJoinList)
            {
                for (DealerJoin dealerJoin : dealerJoinList)
                {
                    String dealerId = dealerJoin.getDealerId();
                    dealerCountService.updateCount(dealerId, CalendarUtils.getCurrentLong());
                }
            }
        }
    }
    
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
    @Override
    public void updateState(String refrenceId, Short state, Long beginTime, Long endTime, String dealNos, Integer createIp, Short showed, String checkMark, String userId)
            throws BusinessException
    {
        // 验证请求是否合法
        if (StringUtils.isBlank(refrenceId) || null == state) { throw new BusinessException(ClientConst.NULERROR); }
        BrandesInfo brandesInfo = selectByPrimaryKey(refrenceId);
        if (null == brandesInfo) { throw new BusinessException(ClientConst.OBJECTEXIST); }
        short oldState = brandesInfo.getBrandState();
        BrandsDomain brandsDomain = brandsDomainService.selectByPrimaryKey(brandesInfo.getRefrenceId());
        if (null == brandsDomain)
        {
            BrandsDomain _brandsDomain = new BrandsDomain();
            _brandsDomain.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            _brandsDomain.setBrandsId(brandesInfo.getRefrenceId());
            _brandsDomain.setBrandId(brandesInfo.getBrandId());
            _brandsDomain.setCreateTime(CalendarUtils.getCurrentLong());
            _brandsDomain.setUpdateTime(CalendarUtils.getCurrentLong());
            _brandsDomain.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
            String domain = "";
            while (true)
            {
                for (int i = 0; i < 6; i++)
                {
                    domain = domain + new Random().nextInt(10);
                }
                if (!brandsDomainService.isExistDomains(refrenceId, domain))
                {
                    break;
                }
                domain = "";
            }
            _brandsDomain.setDomain(domain);
            brandsDomainService.insert(_brandsDomain);
        }
        // 操作
        if (BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED == state)// 已审核通过
        {
            if (BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED != oldState) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "用户未提交申请，不能审核"); }
            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_BRANDSCOUNT);
            if (null == brandCountService.modifyBrandCount(brandesInfo.getBrandId(), countTypeList.toArray(new Integer[]{}))) { throw new BusinessException(
                    ClientConst.FAILURE.getCode(), "修改品牌商的统计数量失败"); }
            brandesInfo = updateField(refrenceId, "brandState", state);
            if (StringUtils.isBlank(dealNos)) { throw new BusinessException(ClientConst.NULERROR.getCode(), "品牌经营类目不能为空"); }
            if (null == createIp) { throw new BusinessException(ClientConst.NULERROR.getCode(), "创建者IP不能为空"); }
            try
            {
                brandDealService.updateBrandDeal(brandesInfo.getBrandId(), refrenceId, dealNos, createIp);
            }
            catch (IllegalArgumentException e)
            {
                throw new BusinessException(ClientConst.PARAMERROR.getCode(), e.getMessage());
            }
            BrandsCount brandsCount = brandsCountMapper.findByBrandIdAndBrandesId(brandesInfo.getBrandId(), brandesInfo.getRefrenceId());
            if (null == brandsCount)
            {
                // 新增 品牌计数信息
                brandsCount = new BrandsCount();
                brandsCount.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandsCount.setBrandsId(brandesInfo.getRefrenceId());
                brandsCount.setBrandId(brandesInfo.getBrandId());
                brandsCount.setCreateTime(CalendarUtils.getCurrentLong());
                brandsCount.setUpdateTime(CalendarUtils.getCurrentLong());
                brandsCount.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                brandsCount.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                brandsCountMapper.insert(brandsCount);
            }
            // 修改搜索引擎数据
            // BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandesInfo.getBrandId());
            // brandesInfoCache.updateSolrData(brandesInfo, brandInfo, showed > 0 ? true : false);
            // brandeSolrHandler.addBrandsInfoList(findBrandesInfoToSolr(brandesInfo, null));
            //
            // ProductInfo productInfo = new ProductInfo();
            // productInfo.setBrandsId(brandesInfo.getRefrenceId());
            // List<ProductInfo> list = productInfoService.findProductToSolr(productInfo, null);
            // productSolrHandler.addProductInfoList(list);
            // //合作的时候，开启旗下产品正常运行
            // productInfoDubboConsumer.updateStopStateByBrandsId(brandesInfo.getRefrenceId(), BrandConstant.ProductInfoConst.STATE_NORMAL);
            return;
        }
        else if (BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED == state)// 已合作
        {
            // 前台展示
            brandesInfo.setShowed(showed > 0 ? true : false);
            brandesInfoMapper.updateByPrimaryKey(brandesInfo);
            if (BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED == oldState)
            {
                List<Integer> countTypeList = Lists.newArrayList();
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_BRANDSCOUNT);
                if (null == brandCountService.modifyBrandCount(brandesInfo.getBrandId(), countTypeList.toArray(new Integer[]{}))) { throw new BusinessException(
                        ClientConst.FAILURE.getCode(), "修改品牌商的统计数量失败"); }
                brandesInfo = updateField(refrenceId, "brandState", state);
                if (StringUtils.isBlank(dealNos)) { throw new BusinessException(ClientConst.NULERROR.getCode(), "品牌经营类目不能为空"); }
                if (null == createIp) { throw new BusinessException(ClientConst.NULERROR.getCode(), "创建者IP不能为空"); }
                try
                {
                    brandDealService.updateBrandDeal(brandesInfo.getBrandId(), refrenceId, dealNos, createIp);
                }
                catch (IllegalArgumentException e)
                {
                    throw new BusinessException(ClientConst.PARAMERROR.getCode(), e.getMessage());
                }
                BrandsCount brandsCount = brandsCountMapper.findByBrandIdAndBrandesId(brandesInfo.getBrandId(), brandesInfo.getRefrenceId());
                if (null == brandsCount)
                {
                    // 新增 品牌计数信息
                    brandsCount = new BrandsCount();
                    brandsCount.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandsCount.setBrandsId(brandesInfo.getRefrenceId());
                    brandsCount.setBrandId(brandesInfo.getBrandId());
                    brandsCount.setCreateTime(CalendarUtils.getCurrentLong());
                    brandsCount.setUpdateTime(CalendarUtils.getCurrentLong());
                    brandsCount.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                    brandsCount.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                    brandsCountMapper.insert(brandsCount);
                }
                // 修改搜索引擎数据
                // BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(brandesInfo.getBrandId());
                // brandesInfoCache.updateSolrData(brandesInfo, brandInfo, showed > 0 ? true : false);
                // brandeSolrHandler.addBrandsInfoList(findBrandesInfoToSolr(brandesInfo, null));
                //
                // ProductInfo productInfo = new ProductInfo();
                // productInfo.setBrandsId(brandesInfo.getRefrenceId());
                // List<ProductInfo> list = productInfoService.findProductToSolr(productInfo, null);
                // productSolrHandler.addProductInfoList(list);
                // // 合作的时候，开启旗下产品正常运行
                // productInfoDubboConsumer.updateStopStateByBrandsId(brandesInfo.getRefrenceId(), BrandConstant.ProductInfoConst.STATE_NORMAL);
            }
            else if (BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED == oldState)
            {
                // 已审核通过
            }
            else if (BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED == oldState)
            {
                // 已合作
            }
            else if (BrandConstant.BrandesInfoConst.BRAND_STATE_EXPIRED == oldState)
            {
                // 已过期
                // 重新合作的时候，开启旗下产品正常运行
                // productInfoDubboConsumer.updateStopStateByBrandsId(brandesInfo.getRefrenceId(), BrandConstant.ProductInfoConst.STATE_NORMAL);
            }
            else
            {
                throw new BusinessException(ClientConst.ERROR_HANDLE);
            }
            if (null == beginTime || null == endTime) { throw new BusinessException(ClientConst.NULERROR.getCode(), "服务时间不能为空"); }
            updateField(refrenceId, "beginTime", beginTime);
            updateField(refrenceId, "endTime", endTime);
        }
        else if (BrandConstant.BrandesInfoConst.BRAND_STATE_EXPIRED == state)// 已过期
        {
            if (BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED != oldState) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "用户未合作，不能终止合作"); }
            // 更新统计信息
            List<Integer> countTypeList = Lists.newArrayList();
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
            brandCountService.modifyBrandCount(brandesInfo.getBrandId(), countTypeList.toArray(new Integer[]{}));
            // 停止该品牌下的所有产品
            /*
             * productInfoDubboConsumer.updateStopStateByBrandsId(brandesInfo.getRefrenceId(), BrandConstant.ProductInfoConst.STATE_STOPED);
             * try
             * {
             * brandeSolrHandler.removeBrandesInfo(brandesInfo);
             * ProductInfo productInfo = new ProductInfo();
             * productInfo.setBrandsId(brandesInfo.getRefrenceId());
             * List<ProductInfo> list = productInfoService.findProductToSolr(productInfo, null);
             * for(ProductInfo ProductInfo : list){
             * productSolrHandler.removeProductInfo(ProductInfo.getRefrenceId());
             * }
             * }
             * catch (Exception e)
             * {
             * logger.error("删除品牌索引失败, ID: " + refrenceId, e);
             * }
             */
        }
        else if (BrandConstant.BrandesInfoConst.BRAND_STATE_FAILURE == state)// 已失败
        {
            if (BrandConstant.BrandesInfoConst.BRAND_STATE_UNAPPROVED != oldState) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "用户未提交申请，不能审核"); }
            BrandsAudit brandsAudit = new BrandsAudit();
            brandsAudit.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandsAudit.setCheckMark(checkMark);
            brandsAudit.setCheckState(BrandConstant.BrandsAuditConst.CHECK_STATE_NO_AUDIT);
            brandsAudit.setCheckTime(CalendarUtils.getCurrentLong());
            brandsAudit.setBrandsId(brandesInfo.getRefrenceId());
            brandsAudit.setBrandsNames(brandesInfo.getBrandsName());
            brandsAudit.setLogoName(brandesInfo.getBrandLogo());
            brandsAudit.setDomainName(brandesInfo.getLogoDomin());
            brandsAudit.setDelFlag(false);
            brandsAudit.setUserId(userId);
            brandsAuditService.insert(brandsAudit);
        }
        else
        {
            throw new BusinessException(ClientConst.ERROR_HANDLE);
        }
        brandesInfo = updateField(refrenceId, "brandState", state);
    }
    
    /**
     * 根据主键，更改指定字段的值
     * @author 陈建平
     * @param refrenceId
     * @param fieldName
     * @param fieldValue
     * @return
     */
    @Override
    public BrandesInfo updateField(String refrenceId, String fieldName, Object fieldValue)
    {
        BrandesInfo brandesInfo = selectByPrimaryKey(refrenceId);
        if (null != brandesInfo)
        {
            ReflectionUtils.invokeSetter(brandesInfo, fieldName, fieldValue);
            brandesInfoMapper.updateByPrimaryKey(brandesInfo);
        }
        return brandesInfo;
    }
}
