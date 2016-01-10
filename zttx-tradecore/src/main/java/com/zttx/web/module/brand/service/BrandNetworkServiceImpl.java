/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.IPUtil;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.brand.entity.BrandNetimg;
import com.zttx.web.module.brand.entity.BrandNetwork;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.mapper.BrandLevelMapper;
import com.zttx.web.module.brand.mapper.BrandNetimgMapper;
import com.zttx.web.module.brand.mapper.BrandNetworkMapper;
import com.zttx.web.module.brand.mapper.BrandesInfoMapper;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.mapper.DealerImageMapper;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.mapper.DealerJoinMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.NetworkUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 品牌经销网络 服务实现类
 * <p>File：BrandNetwork.java </p>
 * <p>Title: BrandNetwork </p>
 * <p>Description:BrandNetwork </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandNetworkServiceImpl extends GenericServiceApiImpl<BrandNetwork> implements BrandNetworkService {
    @Autowired
    private BrandesInfoMapper brandesInfoMapper;

    @Autowired
    private BrandNetimgMapper brandNetimgMapper;

    @Autowired
    private DealerImageMapper dealerImageMapper;

    @Autowired
    private DealerJoinMapper dealerJoinMapper;

    @Autowired
    private DealerInfoMapper dealerInfoMapper;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BrandLevelMapper brandLevelMapper;

    private BrandNetworkMapper brandNetworkMapper;

    @Autowired(required = true)
    public BrandNetworkServiceImpl(BrandNetworkMapper brandNetworkMapper) {
        super(brandNetworkMapper);
        this.brandNetworkMapper = brandNetworkMapper;
    }

    @Override
    public List<BrandNetwork> selectNetworkAndImgByBrandesId(String brandesId) {
        return brandNetworkMapper.selectNetworkAndImgByBrandesId(brandesId);
    }

    @Override
    public void save(BrandNetwork brandNetwork, HttpServletRequest request) throws BusinessException {
        Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED, BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED};
        // 判断当前状态是不是通进行操作
        validatorState(brandNetwork.getBrandId(), brandNetwork.getBrandsId(), brandStates);
        if (StringUtils.isNotBlank(brandNetwork.getRefrenceId())) {
            BrandNetwork oldBrandNetwork = brandNetworkMapper.selectByPrimaryKey(brandNetwork.getRefrenceId());
            oldBrandNetwork.setBrandsId(brandNetwork.getBrandsId());
            oldBrandNetwork.setDealerName(brandNetwork.getDealerName());
            oldBrandNetwork.setUserName(brandNetwork.getUserName());
            oldBrandNetwork.setTelphone(brandNetwork.getTelphone());
            oldBrandNetwork.setMobile(brandNetwork.getMobile());
            oldBrandNetwork.setProvinceName(brandNetwork.getProvinceName());
            oldBrandNetwork.setCityName(brandNetwork.getCityName());
            oldBrandNetwork.setAreaName(brandNetwork.getAreaName());
            oldBrandNetwork.setAreaNo(brandNetwork.getAreaNo());
            oldBrandNetwork.setAddress(brandNetwork.getAddress());
            brandNetworkMapper.updateByPrimaryKeySelective(oldBrandNetwork);
        } else {
            brandNetwork.setRefrenceId(SerialnoUtils.buildPrimaryKey());// 主键
            brandNetwork.setCreateTime(CalendarUtils.getCurrentLong());
            brandNetwork.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
            brandNetwork.setShowFlag(BrandConstant.BrandNetworkConst.FLAG_SHOW);
            brandNetworkMapper.insert(brandNetwork);
        }
        updateNetimg(brandNetwork, request);
    }


    private void updateNetimg(BrandNetwork brandNetwork, HttpServletRequest request) throws BusinessException {
        List<BrandNetimg> netimgList = brandNetimgMapper.findBrandNetimgList(brandNetwork.getRefrenceId());
        List<String> delBrandNetimgList = Lists.newArrayList();
        if (null != netimgList && !netimgList.isEmpty()) {
            for (BrandNetimg netimg : netimgList) {
                int size = com.zttx.web.utils.StringUtils.strArraySearch(brandNetwork.getImages(), netimg.getImageName());
                if (size >= 0) {
                    brandNetwork.getImages()[size] = "";
                } else {
                    brandNetimgMapper.deleteByPrimaryKey(netimg.getRefrenceId());
                    //TODO 删除图片文件
                }
            }
        }

        if (ArrayUtils.isNotEmpty(brandNetwork.getImages())) {
            moveImgFromTemp(request, brandNetwork);
            List<BrandNetimg> brandNetimgList = parseBrandLiceningList(brandNetwork, request);
            for (BrandNetimg brandNetimg : brandNetimgList) {
                brandNetimgMapper.insert(brandNetimg);
            }
        }
    }

    public void moveImgFromTemp(HttpServletRequest request, BrandNetwork brandNetwork) throws BusinessException {
        // 移动图片
        for (int i = 0; i < brandNetwork.getImages().length; i++) {
            String imgPath = brandNetwork.getImages()[i];
            String resultPath = "";
            if (StringUtils.isNotBlank(imgPath)) {
                resultPath = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, imgPath, UploadAttCateConst.BRAND_NETWORK);
                brandNetwork.getImages()[i] = resultPath;
            }
        }
    }

    public List<BrandNetimg> parseBrandLiceningList(BrandNetwork brandNetwork, HttpServletRequest request) {
        List<BrandNetimg> list = new ArrayList<BrandNetimg>();
        for (int i = 0; i < brandNetwork.getImages().length; i++) {
            String imageName = brandNetwork.getImages()[i];
            if (StringUtils.isNotBlank(imageName)) {
                String photoName = brandNetwork.getPhotoName()[i];
                BrandNetimg brandNetimg = new BrandNetimg();
                brandNetimg.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                brandNetimg.setNetworkId(brandNetwork.getRefrenceId());
                brandNetimg.setBrandId(brandNetwork.getBrandId());
                brandNetimg.setBrandsId(brandNetwork.getBrandsId());
                brandNetimg.setDomainName(NetworkUtils.getDoMainName());
                brandNetimg.setPhotoName(photoName);
                brandNetimg.setImageName(imageName);
                if (i == 0) {
                    brandNetimg.setMainFlag(BrandConstant.BrandNetimgConst.NETIMG_MAIN_FLAG);
                } else {
                    brandNetimg.setMainFlag(BrandConstant.BrandNetimgConst.NETIMG_NOT_MAIN_FLAG);
                }
                // 简单处理 错开时间
                brandNetimg.setUploadTime(CalendarUtils.getCurrentLong() + i);
                brandNetimg.setUploadIp(IPUtil.getIpAddr(request));
                list.add(brandNetimg);
            }
        }
        return list;
    }

    private BrandesInfo validatorState(String brandId, String brandsId, Short[] brandStates) throws BusinessException {
        BrandesInfo brandesInfo = brandesInfoMapper.findBrandAndBrandesInfo(brandsId);
        return this.validatorState(brandesInfo, brandStates);
    }

    public BrandesInfo validatorState(BrandesInfo brandesInfo, Short[] brandStates) throws BusinessException {
        if (null == brandesInfo) {
            throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        }
        Arrays.sort(brandStates);
        int size = Arrays.binarySearch(brandStates, brandesInfo.getBrandState().shortValue());
        if (size < 0) {
            throw new BusinessException(BrandConst.BRANDES_STATE_ERROR);
        }
        return brandesInfo;
    }

    @Override
    public PaginateResult<Map<String, Object>> getNetwortNotDealerList(
            Pagination page, BrandNetwork brandNetwork) {
        PaginateResult<Map<String, Object>> result = dealerJoinMapper.getNetwortNotDealerList(page, brandNetwork);
        List<Map<String, Object>> list = result.getList();
        if (!CollectionUtils.isEmpty(list))
        {
            String dealerId = "";

            DealerInfo dealerInfo = null;
            Object levelObj = null;
            BrandLevel brandLevel = null;
            for (Map<String, Object> item : list)
            {
                dealerId = item.get("dealerId").toString();
                dealerInfo = dealerInfoMapper.getDealerInfo(dealerId);
                if (null != dealerInfo)
                {
                    item.put("dealerName", dealerInfo.getDealerName());
                    item.put("shopNum", dealerInfo.getShopNum());
                    item.put("monNum", dealerInfo.getMonNum());
                    item.put("provinceName", dealerInfo.getProvinceName());
                    item.put("cityName", dealerInfo.getCityName());
                    item.put("areaName", dealerInfo.getAreaName());
                    levelObj = item.get("levelId");
                    if (null != levelObj && StringUtils.isNotBlank(levelObj.toString()))
                    {
                        brandLevel = brandLevelMapper.selectByPrimaryKey(levelObj.toString());
                        if (null != brandLevel)
                        {
                            item.put("levelName", brandLevel.getLevelName());
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void updateShowFlag(String refrenceId, Boolean showFlag) {
        BrandNetwork brandNetwork = new BrandNetwork();
        brandNetwork.setShowFlag(showFlag);
        brandNetwork.setRefrenceId(refrenceId);
        brandNetworkMapper.updateShowFlag(brandNetwork);
    }
    
    
    @Override
    public void updateNetworkLevel(String brandId, String brandsId, String dealerId, String levelId)
    {
        brandNetworkMapper.updateNetworkLevel(brandId,brandsId,dealerId,levelId);
    }

    /**
     * 查询销售网信息
     *
     * @param brandId
     * @param brandsId
     * @param dealerId
     * @param showFlag
     * @return
     */
    @Override
    public BrandNetwork getBrandNetwork(String brandId, String brandsId, String dealerId, Boolean showFlag) {
        return brandNetworkMapper.getBrandNetwork(brandId, brandsId, dealerId, showFlag);
    }


    /**
     * 保存经销网络
     *
     * @param request
     * @param brandNetwork
     * @throws BusinessException
     */
    @Override
    public void insertBrandNetwork(HttpServletRequest request, BrandNetwork brandNetwork) throws BusinessException {
        Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED, BrandConstant.BrandesInfoConst.BRAND_STATE_APPROVED};
        // 判断当前状态是不是通进行操作
        this.validatorState(brandNetwork.getBrandId(), brandNetwork.getBrandsId(), brandStates);
        Map<String, Object> map = Maps.newHashMap();
        Object obj = null;
        Integer size = 20;
        for (String id : brandNetwork.getIdAry()) {
            map = dealerJoinMapper.findByRefrenceIdAndBrandIdMap(id, brandNetwork.getBrandId());
            String dealerId = map.get("dealerId").toString();
            DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerId);
            brandNetwork.setDealerId(dealerId);
            if (!this.isExistNetworkDealer(brandNetwork)) {
                if (brandNetwork.getBrandsId().equals(map.get("brandsId").toString())
                        && Short.parseShort(map.get("joinState").toString()) == DealerConstant.DealerJoin.COOPERATION) {

                    BrandNetwork network = new BrandNetwork();
                    network.setWholePercent(size);
                    network.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                    network.setBrandId(brandNetwork.getBrandId());
                    network.setBrandsId(brandNetwork.getBrandsId());
                    network.setDealerId(dealerId);
                    network.setDealerName(dealerInfo.getDealerName());
                    obj = map.get("levelId");
                    if (null != obj && StringUtils.isNotBlank(obj.toString())) {
                        network.setLevelId(obj.toString());
                    }
                    if (null != dealerInfo && StringUtils.isNotBlank(dealerInfo.getDealerUser())) {
                        network.setUserName(dealerInfo.getDealerUser());
                        network.setWholePercent(network.getWholePercent() + size);
                    }
                    obj = map.get("telphone");
                    if (null != obj && StringUtils.isNotBlank(obj.toString())) {
                        network.setTelphone(obj.toString());
                        network.setWholePercent(network.getWholePercent() + size);
                    }
                    UserInfo dealerUserm = userInfoService.selectByPrimaryKey(network.getDealerId());
                    network.setMobile(dealerUserm.getUserMobile());
                    obj = map.get("provinceName");
                    if (null != obj && StringUtils.isNotBlank(obj.toString())) {
                        network.setProvinceName(obj.toString());
                        network.setWholePercent(network.getWholePercent() + size);
                    }
                    network.setCityName(getObjStr(map.get("cityName")));
                    network.setAreaName(getObjStr(map.get("areaName")));
                    obj = map.get("areaNo");
                    if (null != obj && StringUtils.isNotBlank(obj.toString())) {
                        network.setAreaNo(Integer.parseInt(obj.toString()));
                    }
                    network.setAddress(getObjStr(map.get("address")));
                    obj = map.get("dealerLogo");
                    Boolean isDealerLogo = (null != obj && StringUtils.isNotBlank(obj.toString()));
                    if (isDealerLogo) {
                        network.setShowFlag(BrandConstant.BrandNetworkConst.FLAG_SHOW);
                    } else {
                        network.setShowFlag(BrandConstant.BrandNetworkConst.FLAG_NOT_SHOW);
                    }
                    network.setCreateTime(CalendarUtils.getCurrentLong());
                    network.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
                    brandNetworkMapper.insert(network);
                    if (isDealerLogo) {
                        network.setWholePercent(network.getWholePercent() + size);
                        List<DealerImage> dealerImageList = dealerImageMapper.selectDealerImagesByDealerId(dealerId);
                        if (!org.springframework.util.CollectionUtils.isEmpty(dealerImageList)) {
                            List<BrandNetimg> netimgList = Lists.newArrayList();
                            for (DealerImage dealerImage : dealerImageList) {
                                BrandNetimg img = new BrandNetimg();
                                img.setRefrenceId(com.zttx.sdk.utils.SerialnoUtils.buildPrimaryKey());
                                img.setNetworkId(network.getRefrenceId());
                                img.setBrandId(brandNetwork.getBrandId());
                                img.setBrandsId(brandNetwork.getBrandsId());
                                img.setDomainName("");
                                img.setImageName(dealerImage.getImageName());
                                String photoName = dealerImage.getPhotoName();
                                photoName = (StringUtils.isBlank(photoName) ? "" : photoName);
                                img.setPhotoName(photoName);
                                if (dealerImage.getImageName().equals(obj.toString())) {
                                    img.setMainFlag(BrandConstant.BrandNetimgConst.NETIMG_MAIN_FLAG);
                                } else {
                                    img.setMainFlag(BrandConstant.BrandNetimgConst.NETIMG_NOT_MAIN_FLAG);
                                }
                                img.setUploadTime(CalendarUtils.getCurrentLong());
                                img.setUploadIp(com.zttx.web.utils.IPUtil.getIpAddr(request));
                                netimgList.add(img);
                            }
                            brandNetimgMapper.insertBatch(netimgList);
                        }
                    }
                }
            }
        }
    }

    /**
     * 校验经销网络是否存在
     *
     * @param brandNetwork
     * @return
     */
    private Boolean isExistNetworkDealer(BrandNetwork brandNetwork){

        Integer result = brandNetworkMapper.isExistNetworkDealer(brandNetwork);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    private String getObjStr(Object obj) {
        if (null != obj && StringUtils.isNotBlank(obj.toString())) {
            return obj.toString();
        }
        return "";
    }
}
