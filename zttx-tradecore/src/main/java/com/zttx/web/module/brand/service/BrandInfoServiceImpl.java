/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.*;
import com.zttx.web.module.brand.entity.BrandAudit;
import com.zttx.web.module.brand.entity.BrandCatelog;
import com.zttx.web.module.brand.entity.BrandCount;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.mapper.BrandCatelogMapper;
import com.zttx.web.module.brand.mapper.BrandInfoMapper;
import com.zttx.web.module.brand.model.BrandInfoModel;
import com.zttx.web.module.common.entity.CenterUser;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.RoleInfoMapper;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.EntityUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.UserCenterClient;

/**
 * 品牌商基本信息 服务实现类
 * <p>File：BrandInfo.java </p>
 * <p>Title: BrandInfo </p>
 * <p>Description:BrandInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandInfoServiceImpl extends GenericServiceApiImpl<BrandInfo> implements BrandInfoService
{
	@Autowired
    private BrandCatelogMapper brandCatelogMapper;
    
    @Autowired
    private UserInfoMapper     userInfoMapper;
    
    @Autowired
    private BrandCrmService    brandCrmService;
    
    @Autowired
    private BrandAuditService  brandAuditService;
    
    @Autowired
    private BrandCountService  brandCountService;
    
    @Autowired
    private UserInfoService    userInfoService;
    
    @Autowired
    private BrandCatelogService brandCatelogService;
    
    @Autowired
    private UserCenterClient   userCenterClient;
    
    @Autowired
    private RoleInfoMapper     roleInfoMapper;

    private BrandInfoMapper    brandInfoMapper;
    
    @Autowired(required = true)
    public BrandInfoServiceImpl(BrandInfoMapper brandInfoMapper)
    {
        super(brandInfoMapper);
        this.brandInfoMapper = brandInfoMapper;
    }
    
    @Override
    public BrandInfoModel editPassBrandInfo(BrandInfoModel brandInfo, BrandInfo oldBrandInfo, int[] dealNos)
    {
        // 更新操作
        oldBrandInfo.setComAddress(brandInfo.getComAddress());
        oldBrandInfo.setComWeb(brandInfo.getComWeb());
        oldBrandInfo.setComMark(brandInfo.getComMark());
        oldBrandInfo.setEmploeeNum(brandInfo.getEmploeeNum());
        oldBrandInfo.setMoneyNum(brandInfo.getMoneyNum());
        brandInfoMapper.updateByPrimaryKey(oldBrandInfo);
        // 更新品类
        this.updateCatelog(oldBrandInfo, dealNos);
        List<Integer> noList = brandCatelogMapper.fingBrandCateLogNo(oldBrandInfo.getRefrenceId());
        brandInfo.setDealNo(noList);
        EntityUtils.copyProperties(brandInfo, oldBrandInfo);
        brandCrmService.save(JSON.toJSONString(brandInfo), ClientConstant.BRAND_INFO);
        return brandInfo;
    }
    
    private void updateCatelog(BrandInfo brandInfo, int[] dealNos)
    {
        brandCatelogMapper.deleteBrandCatelogsByBrandId(brandInfo.getRefrenceId());
        for (int dealNo : dealNos)
        {
            BrandCatelog brandCatelog = new BrandCatelog();
            brandCatelog.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            brandCatelog.setBrandId(brandInfo.getRefrenceId());
            brandCatelog.setDealNo(dealNo);
            brandCatelog.setCreateTime(CalendarUtils.getCurrentLong());
            brandCatelog.setDelFlag(BrandConstant.DEL_STATE_NONE_DELETED);
            brandCatelogMapper.insert(brandCatelog);
        }
    }
    
    @Override
    public void editBrandInfoImage(BrandInfoModel brandInfo, BrandInfo oldBrandInfo) throws BusinessException
    {
        if (StringUtils.isNotBlank(brandInfo.getFile_cert_imageImage()))
        {
            // String path = MultipartUtils.moveAndresizeFile(request, MultipartUtils.BRAND_IMG_PATH, brandInfo.getFile_cert_imageImage(),
            // UploadAttCateConst.BRAND_COM_IMG);
            /**需改成Http调文件服务器接口方式**/
            String path = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandInfo.getFile_cert_imageImage(), UploadAttCateConst.BRAND_COM_IMG);
            brandInfo.setBrandImage(path);
            brandInfo.setBrandPhoto(brandInfo.getFile_cert_imagePhoto());
        }
        else
        {
            brandInfo.setBrandPhoto(oldBrandInfo == null ? null : oldBrandInfo.getBrandPhoto());
            brandInfo.setBrandImage(oldBrandInfo == null ? null : oldBrandInfo.getBrandImage());
        }
        if (StringUtils.isNotBlank(brandInfo.getFile_user_photoImage()))
        {
            // String path = MultipartUtils.moveAndresizeFile(request, MultipartUtils.BRAND_IMG_PATH, brandInfo.getFile_user_photoImage(), UploadAttCateConst.BRAND_ID_IMG);
            /**需改成Http调文件服务器接口方式**/
            String path = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandInfo.getFile_user_photoImage(), UploadAttCateConst.BRAND_ID_IMG);
            brandInfo.setUserImage(path);
        }
        else if (StringUtils.isNotBlank(brandInfo.getUserImage()))
        {
            brandInfo.setUserImage(oldBrandInfo == null ? "" : oldBrandInfo.getUserImage());
        }
        else
        {
            brandInfo.setUserImage("");
        }
        if (StringUtils.isNotBlank(brandInfo.getFile_user_imageImage()))
        {
            // String path = MultipartUtils.moveAndresizeFile(request, MultipartUtils.BRAND_IMG_PATH, brandInfo.getFile_user_imageImage(), UploadAttCateConst.BRAND_ID_IMG);
            /**需改成Http调文件服务器接口方式**/
            String path = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandInfo.getFile_user_imageImage(), UploadAttCateConst.BRAND_ID_IMG);
            brandInfo.setUserPhoto(path);
        }
        else if (StringUtils.isNotBlank(brandInfo.getUserImage()))
        {
            brandInfo.setUserPhoto(oldBrandInfo == null ? "" : oldBrandInfo.getUserPhoto());
        }
        else
        {
            brandInfo.setUserPhoto("");
        }
    }
    
    @Override
    public BrandInfoModel editBrandInfo(BrandInfoModel brandInfo, BrandInfo oldBrandInfo, int[] dealNos) throws BusinessException
    {
        if (oldBrandInfo == null)
        {
            // 作新增操作
            brandInfo.setCheckState(BrandConstant.BrandInfoConst.CHECK_STATE_NONE_AUDIT);
            brandInfo.setCreateTime(CalendarUtils.getCurrentLong());
            // 添加条形码助记码
            String nextBarCodeNum = "";
            String currentBarCodeNum = brandInfoMapper.getMaxBrandInfobarCodeNum();
            if (StringUtils.isBlank(currentBarCodeNum))
            {
                nextBarCodeNum = com.zttx.sdk.utils.SerialnoUtils.buildNextBarCodeNum(BrandConstant.BRANDINFO_BARCODENUM_DEAFAULT,
                        BrandConstant.BRANDINFO_BARCODENUM_LENGTH);
            }
            else
            {
                nextBarCodeNum = com.zttx.sdk.utils.SerialnoUtils.buildNextBarCodeNum(currentBarCodeNum, BrandConstant.BRANDINFO_BARCODENUM_LENGTH);
            }
            brandInfo.setBarCodeNum(nextBarCodeNum);
            brandInfoMapper.insert(brandInfo);
        }
        else
        {
            // 更新操作
            oldBrandInfo.setComName(brandInfo.getComName());
            oldBrandInfo.setComType(brandInfo.getComType());
            oldBrandInfo.setDealType(brandInfo.getDealType());
            oldBrandInfo.setDomainName(brandInfo.getDomainName());
            oldBrandInfo.setBrandPhoto(brandInfo.getBrandPhoto());
            oldBrandInfo.setBrandImage(brandInfo.getBrandImage());
            oldBrandInfo.setUserPhoto(brandInfo.getUserPhoto());
            oldBrandInfo.setUserImage(brandInfo.getUserImage());
            oldBrandInfo.setComNum(brandInfo.getComNum());
            oldBrandInfo.setIdNum(brandInfo.getIdNum());
            oldBrandInfo.setLegalName(brandInfo.getLegalName());
            oldBrandInfo.setRegMoney(brandInfo.getRegMoney());
            oldBrandInfo.setAreaNo(brandInfo.getAreaNo());
            oldBrandInfo.setProvinceName(brandInfo.getProvinceName());
            oldBrandInfo.setCityName(brandInfo.getCityName());
            oldBrandInfo.setAreaName(brandInfo.getAreaName());
            oldBrandInfo.setComAddress(brandInfo.getComAddress());
            oldBrandInfo.setComWeb(brandInfo.getComWeb());
            oldBrandInfo.setComMark(brandInfo.getComMark());
            oldBrandInfo.setEmploeeNum(brandInfo.getEmploeeNum());
            oldBrandInfo.setMoneyNum(brandInfo.getMoneyNum());
            if (BrandConstant.BrandInfoConst.CHECK_STATE_PASS_AUDIT != oldBrandInfo.getCheckState())
            {
                oldBrandInfo.setCheckState(BrandConstant.BrandInfoConst.CHECK_STATE_NONE_AUDIT);
            }
            brandInfoMapper.updateByPrimaryKey(oldBrandInfo);
        }
        // 更新品类
        if (null == oldBrandInfo) oldBrandInfo = EntityUtils.buildModelByEntity(BrandInfo.class, brandInfo);
        this.updateCatelog(oldBrandInfo, dealNos);
        List<Integer> noList = brandCatelogMapper.fingBrandCateLogNo(oldBrandInfo.getRefrenceId());
        brandInfo.setDealNo(noList);
        brandCrmService.save(JSON.toJSONString(oldBrandInfo), ClientConstant.BRAND_INFO);
        // 用户中心
        try
        {
            CenterUser centerUser = userCenterClient.getUser(oldBrandInfo.getRefrenceId());
            centerUser.setTrueName(oldBrandInfo.getComName());
            userCenterClient.update(centerUser);
        }
        catch (BusinessException e)
        {
            throw new BusinessException("用户中心访问异常:" + e.getLocalizedMessage());
        }
        return brandInfo;
    }
    
    @Override
    public Boolean isExits(String comName, String oldBrandId)
    {
        return brandInfoMapper.isExits(comName, oldBrandId);
    }
    
    /**
     * 获取品牌商 最大条形码助记码
     * @author chenjp
     * @return
     */
    @Override
    public String getMaxBrandInfobarCodeNum()
    {
        return brandInfoMapper.getMaxBrandInfobarCodeNum();
    }
    
    @Override
    public Integer getBrandMInStore(String brandId)
    {
        return brandInfoMapper.getBrandMInStore(brandId);
    }
    
    @Override
    public List<Map<String, Object>> findAllBrandBaseInfo()
    {
        return brandInfoMapper.findAllBrandBaseInfo();
    }
    
    /**
     * 修改审核状态
     * @param refrenceId 品牌商编号
     * @param state （1：审核通过，2：审核不通过，其它会抛业务异常，提示非法操作）
     * @param brandAudit 
     * 		  checkMark（如果审核不通过时，需要填写原因） 
     * @param dealNos 品类编码(即品牌商经营类目）（格式：101000000|103000000|250000000）（审核通过时，必填）
     */
    @Override
    public void updateState(String refrenceId, Short state, BrandAudit brandAudit, String dealNos) throws BusinessException
    {
        if (StringUtils.isBlank(refrenceId) || null == state) { 
        	throw new BusinessException(ClientConst.NULERROR); 
        }
        if (BrandConst.PASS_VERIFY.getCode().shortValue() != state && BrandConst.HAS_NOT_VERIFY.getCode().shortValue() != state) {
        	throw new BusinessException(ClientConst.ERROR_HANDLE); 
        }
        BrandInfo brandInfo = selectByPrimaryKey(refrenceId);
        if(null == brandInfo){
        	throw new BusinessException(ClientConst.FAILURE.getCode(),"品牌商信息不存在");
        }
        UserInfo userInfo = userInfoService.selectByPrimaryKey(refrenceId);
        if(null == userInfo){
        	throw new BusinessException(ClientConst.FAILURE.getCode(),"品牌商账号不存在");
        }
        brandInfo.setCheckState(state);
        brandInfoMapper.updateByPrimaryKey(brandInfo);
        if (state == BrandConst.PASS_VERIFY.getCode().shortValue())// 审核通过
        {
            if (StringUtils.isBlank(dealNos)) {
            	throw new BusinessException(ClientConst.NULERROR.getCode(), "品牌商经营类目不能为空"); 
            }
            try
            {
                // 更改BrandCatelog（品牌商经营类目）
                brandCatelogService.updateBrandCatelogByClient(refrenceId, dealNos);
            }
            catch (IllegalArgumentException e)
            {
                throw new BusinessException(ClientConst.PARAMERROR.getCode(), e.getMessage());
            }
            // 新增 BrandBalance（品牌商资金表）
            //brandBalanceService.addNewBrandBalance(refrenceId);
            // 新增BrandCount（品牌商计数信息）
            if (null == brandCountService.selectByPrimaryKey(refrenceId))
            {
                BrandCount brandCount = new BrandCount();
                brandCount.setRefrenceId(refrenceId);
                brandCount.setCreateTime(CalendarUtils.getCurrentLong());
                brandCount.setUpdateTime(CalendarUtils.getCurrentLong());
                brandCountService.insert(brandCount);
            }
            userInfo.setRoleId(roleInfoMapper.findRefrenceIdByCode(CommonConstant.RoleType.ROLE_BRAND_USER_CODE));
            userInfoService.updateByPrimaryKey(userInfo);
        }
        else if (state == BrandConst.HAS_NOT_VERIFY.getCode().shortValue())// 审核不通过
        {
            String checkMark = brandAudit.getCheckMark();
            if (StringUtils.isBlank(checkMark)) {
            	throw new BusinessException(ClientConst.NOT_PASS_REASON); 
            }
            // 新增品牌商审核日志
            brandAudit.setBrandId(refrenceId);
            brandAudit.setBrandName(brandInfo.getComName());
            brandAudit.setCheckTime(CalendarUtils.getCurrentLong());
            brandAudit.setCheckState(state);
            brandAuditService.insert(brandAudit);
            
            userInfo.setRoleId(roleInfoMapper.findRefrenceIdByCode(CommonConstant.RoleType.ROLE_BRAND_UNOPEN_USER_CODE));
            userInfoService.updateByPrimaryKey(userInfo);
        }
    }

    /**
     * 根据编码获取公司规模或营业额中文描述
     * @param type
     * @param number
     * @return
     */
    @Override
    public String getParamAryName(Integer type, Integer number)
    {
        // 0、公司规模　1、年营业额
        String[][] nameAry = {{"", "50人以下", "50-100人", "100-500人", "500-1000人", "1000人以上"}, {"", "10万以下", "10万-50万", "51万-100万", "101万-500万", "501万-1000万", "1000万以上"}};
        Integer len = nameAry[type].length;
        number = (number >= len) ? 0 : number;
        return nameAry[type][number];
    }

}