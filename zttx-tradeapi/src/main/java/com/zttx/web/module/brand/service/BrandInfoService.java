/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.brand.entity.BrandAudit;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.model.BrandInfoModel;

/**
 * 品牌商基本信息 服务接口
 * <p>File：BrandInfoService.java </p>
 * <p>Title: BrandInfoService </p>
 * <p>Description:BrandInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface BrandInfoService extends GenericServiceApi<BrandInfo>
{
    /**
     * 编辑审核通过的品牌商基本资料
     * @param brandInfo
     * @param oldBrandInfo
     * @param dealNos
     * @return
     */
    BrandInfoModel editPassBrandInfo(BrandInfoModel brandInfo, BrandInfo oldBrandInfo, int[] dealNos);
    
    /**
     * 处理品牌商基本资料中的图片
     * @param brandInfo
     * @param oldBrandInfo
     * @throws BusinessException
     */
    void editBrandInfoImage(BrandInfoModel brandInfo, BrandInfo oldBrandInfo) throws BusinessException;
    
    /**
     * 编辑 品牌商基本资料
     * @param brandInfo
     * @param oldBrandInfo
     * @param dealNos
     * @return
     * @throws BusinessException
     */
    BrandInfoModel editBrandInfo(BrandInfoModel brandInfo, BrandInfo oldBrandInfo, int[] dealNos) throws BusinessException;
    
    /**
     * 检测是否存在相同名称的品牌商
     *
     * @param comName
     * @param oldBrandId
     * @return
     */
    Boolean isExits(String comName, String oldBrandId);
    
    /**
     * 获取品牌商 最大条形码助记码
     * @author chenjp
     * @return
     */
    String getMaxBrandInfobarCodeNum();
    
    /**
     * 根据brandId获取最小库存
     * @param brandId
     * @return
     */
    Integer getBrandMInStore(String brandId);
    
    /**
     * 查询所有品牌商的基础信息
     * 包括ID和名称
     * @return
     */
    List<Map<String, Object>> findAllBrandBaseInfo();
    
    /**
     * 修改审核状态
     * @param refrenceId 品牌商编号
     * @param state （1：审核通过，2：审核不通过，其它会抛业务异常，提示非法操作）
     * @param brandAudit 
     * 		  checkMark（如果审核不通过时，需要填写原因） 
     * @param dealNos 品类编码(即品牌商经营类目）（格式：101000000|103000000|250000000）（审核通过时，必填）
     */
    void updateState(String refrenceId, Short action, BrandAudit brandAudit, String dealNos) throws BusinessException;

    /**
     * 根据编码获取公司规模或营业额中文描述
     * @param type
     * @param number
     * @return
     */
    public String getParamAryName(Integer type, Integer number);
}
