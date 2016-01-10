/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.ClientConstant;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.DealerConst;
import com.zttx.web.consts.DealerConstant;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.TagCommonConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.brand.mapper.BrandViewContactMapper;
import com.zttx.web.module.brand.service.BrandCrmService;
import com.zttx.web.module.common.entity.CenterUser;
import com.zttx.web.module.common.entity.DealDic;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.RoleInfoMapper;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.module.common.service.RegionalService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.module.dealer.entity.DealerAudit;
import com.zttx.web.module.dealer.entity.DealerClass;
import com.zttx.web.module.dealer.entity.DealerCount;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.module.dealer.entity.DealerShopEnvImgTemp;
import com.zttx.web.module.dealer.mapper.DealerClassMapper;
import com.zttx.web.module.dealer.mapper.DealerImageMapper;
import com.zttx.web.module.dealer.mapper.DealerInfoMapper;
import com.zttx.web.module.dealer.model.DealerInfoModel;
import com.zttx.web.module.dealer.model.DealerShopEnvModel;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.ClientValidator;
import com.zttx.web.utils.EncryptUtils;
import com.zttx.web.utils.FileClientUtil;
import com.zttx.web.utils.NetworkUtils;
import com.zttx.web.utils.UserCenterClient;

/**
 * 经销商基础信息 服务实现类
 * <p>File：DealerInfo.java </p>
 * <p>Title: DealerInfo </p>
 * <p>Description:DealerInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class DealerInfoServiceImpl extends GenericServiceApiImpl<DealerInfo> implements DealerInfoService
{
    private DealerInfoMapper       dealerInfoMapper;
    
    @Autowired
    private DealerImageMapper      dealerImageMapper;
    
    @Autowired
    private RegionalService        regionalService;
    
    @Autowired
    private BrandViewContactMapper brandViewContactMapper;

    @Autowired
    private DealDicService              dealDicService;
    
    @Autowired
    private UserCenterClient            userCenterClient;
    
    @Autowired
    private DealerClassMapper           dealerClassMapper;

    @Autowired
    private DealerClassService     dealerClassService;
    
    @Autowired
    private DealerCountService     dealerCountService;
    
    @Autowired
    private BrandCrmService        brandCrmService;
    
    @Autowired
    private UserInfoService        userInfoService;
    
    @Autowired
    private DealerShopEnvService   dealerShopEnvService;
    
    @Autowired
    private DealerShopEnvImgTempService dealerShopEnvImgTempService;
    
    @Autowired
    private DealerAuditService     dealerAuditService;
    
    @Autowired
    private RoleInfoMapper         roleInfoMapper;
    
    private static int             PASSWORD_LENGTH = 6;
    
    @Autowired(required = true)
    public DealerInfoServiceImpl(DealerInfoMapper dealerInfoMapper)
    {
        super(dealerInfoMapper);
        this.dealerInfoMapper = dealerInfoMapper;
    }
    
    public DealerInfo getDealerInfo(String DealerId)
    {
        if (null != DealerId) { return dealerInfoMapper.getDealerInfo(DealerId); }
        return null;
    }
    
    @Override
    public DealerInfoModel findById(String dealerId)
    {
        if (StringUtils.isNotBlank(dealerId))
        {
            DealerInfoModel dealerInfo = dealerInfoMapper.getDealerInfoModel(dealerId);
            Integer city = null;
            Integer province = null;
            if (null != dealerInfo)
            {
                if (null == dealerInfo.getDealerTel())
                {
                    UserInfo userInfo = userInfoService.selectByPrimaryKey(dealerId);
                    if (null != userInfo) dealerInfo.setUserMobile(userInfo.getUserMobile());
                }
                dealerInfo.setDealerImages(dealerImageMapper.selectDealerImagesByDealerId(dealerId));
                city = regionalService.getFatherCode(dealerInfo.getAreaNo(), 3);
                province = regionalService.getFatherCode(city, 2);
                dealerInfo.setProvince(province == null ? "" : province.toString());
                dealerInfo.setCity(city == null ? "" : city.toString());
                dealerInfo.setCounty(String.valueOf(dealerInfo.getAreaNo()));
                return dealerInfo;
            }
        }
        return null;
    }
    
    /**
     * 寻找终端商列表
     *
     * @param page
     * @param info
     * @return
     */
    @Override
    public PaginateResult<Map<String, Object>> search(Pagination page, DealerInfoModel info)
    {
        try
        {
            PaginateResult<Map<String, Object>> paginateResult = new PaginateResult<Map<String, Object>>();
            List<Map<String, Object>> result = dealerInfoMapper.search(page, info);
            if (null != result && !result.isEmpty())
            {
                for (Map<String, Object> item : result)
                {
                    item.put("areaName", TagCommonConst.getFullArea(item.get("province"), item.get("city"), item.get("area"), "/"));
                    int isExist = brandViewContactMapper.isExist(info.getBrandId(), item.get("id").toString(), null);
                    if (isExist > 0)
                    {
                        item.put("isExist", true);
                    }
                    else
                    {
                        item.put("isExist", false);
                    }
                    String brandName = dealerInfoMapper.searchBrandsNameList((String) item.get("id"));
                    item.put("brandName", brandName);
                    List<DealDic> dealerClassess = Lists.newArrayList();
                    dealerClassess = dealerClassService.findbyId((String) item.get("id"));
                    if(null!=dealerClassess && dealerClassess.size()>0){
                    	String dealDicNames = "";
                    	for(DealDic dealDic:dealerClassess){
                    		dealDicNames+=dealDic.getDealName()+"，";
                    	}
                    	dealDicNames = dealDicNames.substring(0, dealDicNames.length()-1);
                    	item.put("dealerClassess", dealDicNames);
                    }
                }
                paginateResult.setList(result);
            }
            paginateResult.setPage(page);
            return paginateResult;
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public boolean isAuthorized(String dealerId) throws BusinessException
    {
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerId);
        if (dealerInfo == null) { throw new BusinessException(DealerConst.USERM_NOT_EXIST); }
        if (dealerInfo.getEndTime() == null || dealerInfo.getEndTime() < CalendarUtils.getCurrentLong())
        {
            return true;
        }
        return false;
    }
    
    @Override
    public List<Map<String, Object>> findAllDealerBaseInfo()
    {
        return dealerInfoMapper.findAllDealerBaseInfo();
    }
    
    @Override
    public PaginateResult<Map<String, Object>> getDealerInfosByClient(Boolean show, String userMobile, String dealerName, Pagination pagination)
    {
        return new PaginateResult<>(pagination, dealerInfoMapper.getDealerInfosByClient(show, userMobile, dealerName, pagination));
    }
    
    /*

     */
    @Override
    public List<Map<String, Object>> findDealerClassById(String dealerId)
    {
        List<Map<String, Object>> list = dealerInfoMapper.findDealerClassById(dealerId);
        if (null != list && !list.isEmpty())
        {
            Integer dealNo = null;
            for (Map<String, Object> map : list)
            {
                dealNo = Integer.valueOf(map.get("dealNo").toString());
                DealDic dealDic = dealDicService.getDealDicByDealNo(dealNo);
                if (null != dealDic)
                {
                    map.put("dealName", dealDicService.getDealDicByDealNo(dealNo).getDealName());
                }
            }
        }
        return list;
    }
    
    @Override
    public DealerInfo modDealerInfoMore(DealerInfoModel dealerInfo, HttpServletRequest request) throws BusinessException
    {
        DealerInfo _dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerInfo.getRefrenceId());
        String[] dealerImagePaths = dealerInfo.getDealerImagePaths();
        if (dealerImagePaths != null)
        {
            if (dealerImagePaths.length != 0)
            {
                dealerImageMapper.deleteDealerImagesByDealerId(dealerInfo.getRefrenceId());
            }
            for (int i = 0; i < dealerImagePaths.length; i++)
            {
                // 切换成文件服务器接口
                // String dealerLogoPath = MultiPartUtils.moveAndresizeFile(request, MultipartUtils.DEALER_IMG_PATH, dealerImagePaths[i],
                // UploadAttCateConst.DEALER_LOGO,null);
                String dealerLogoPath = FileClientUtil.moveImgFromTemp(ImageConst.DEALER_IMG_PATH, dealerImagePaths[i], UploadAttCateConst.DEALER_LOGO);
                if (i == 0)
                {
                    _dealerInfo.setDealerLogo(dealerLogoPath);
                }
                DealerImage dealerImage = new DealerImage();
                dealerImage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerImage.setDealerId(dealerInfo.getRefrenceId());
                dealerImage.setDelFlag(false);
                dealerImage.setCreateTime(CalendarUtils.getCurrentLong());
                dealerImage.setImageName(dealerLogoPath);
                dealerImageMapper.insert(dealerImage);
            }
        }
        // String dealerLogoOldURL = _dealerInfo.getDealerLogo();
        // String dealerLogoNewURL = dealerInfo.getDealerLogo();
        String legalImgZNewURL = dealerInfo.getLegalImgz();
        // 切换成文件服务器接口
        // String legalImgZPath = MultipartUtils.moveAndresizeFile(request, MultipartUtils.DEALER_IMG_PATH, legalImgZNewURL, UploadAttCateConst.DEALER_LOGO, null);
        String legalImgZPath = FileClientUtil.moveImgFromTemp(ImageConst.DEALER_IMG_PATH, legalImgZNewURL, UploadAttCateConst.DEALER_LOGO);
        _dealerInfo.setLegalImgz(legalImgZPath);
        _dealerInfo.setCardId(dealerInfo.getCardId());
        _dealerInfo.setFoundTime(CalendarUtils.getLongFromTime(dealerInfo.getFoundTimeStr()));
        // 地区代码 第三级允许为空
        dealerInfo.setCounty(getAreaNo(dealerInfo.getProvince(), dealerInfo.getCity(), dealerInfo.getCounty()));
        if (org.apache.commons.lang3.StringUtils.isEmpty(dealerInfo.getCounty())) { throw new BusinessException(10000, "请选择区"); }
        // 终端 修改地址时 需同步 相应记录
        if (_dealerInfo.getAreaNo() != Integer.parseInt(dealerInfo.getCounty()))
        {
        }
        _dealerInfo.setAreaNo(Integer.parseInt(dealerInfo.getCounty()));
        _dealerInfo.setDealerName(dealerInfo.getDealerName());
        _dealerInfo.setDealerUser(dealerInfo.getDealerUser());
        _dealerInfo.setDealerGender(dealerInfo.getDealerGender());
        _dealerInfo.setDealerAddress(dealerInfo.getDealerAddress());
        _dealerInfo.setEmpNum(dealerInfo.getEmpNum());
        // 如果 审核不通过 允许修改资料 // 待处理
        if (_dealerInfo.getCheckState() != null && _dealerInfo.getCheckState() == DealerConstant.DealerInfo.CHECK_STATE_FAILURE)
        {
            _dealerInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_PROCESS);
        }
        String regionname = regionalService.getFullNameByAreaNoAndLevel(_dealerInfo.getAreaNo(), 3, ",");
        String[] regionnames = regionname.split(",");
        if (regionnames.length == 3)
        {
            _dealerInfo.setProvinceName(regionnames[0]);
            _dealerInfo.setCityName(regionnames[1]);
            _dealerInfo.setAreaName(regionnames[2]);
        }
        if (regionnames.length == 2)
        {
            _dealerInfo.setProvinceName(regionnames[0]);
            _dealerInfo.setCityName(regionnames[1]);
            _dealerInfo.setAreaName("");
        }
        dealerInfoMapper.updateByPrimaryKey(_dealerInfo);
        // 更新经营类别
        dealerClassMapper.deleteByDealerInfoId(dealerInfo.getRefrenceId());
        String[] dealNos = dealerInfo.getDealNos();
        if (dealNos != null && dealNos.length > 0)
        {
            List<DealerClass> dealerClassess = dealerInfo.getDealerClassess(dealerInfo.getRefrenceId());
            for (DealerClass dealerClass : dealerClassess)
            {
                dealerClass.setCreateIp(NetworkUtils.getRemortIpToInt(request));
            }
            if (null != dealerClassess)
            {
                saveBatch(dealerClassess);
            }
        }
        dealerInfo.setDealNo(dealerClassMapper.findByDealerId(dealerInfo.getRefrenceId()));
        brandCrmService.save(JSON.toJSONString(_dealerInfo), ClientConstant.DEALER_INFO);
        // 修改用户中心的名称
        CenterUser centerUser = userCenterClient.getUser(_dealerInfo.getRefrenceId());
        centerUser.setTrueName(_dealerInfo.getDealerName());
        userCenterClient.update(centerUser);
        return _dealerInfo;
    }
    
    private void saveBatch(List<DealerClass> dealerClassList)
    {
        for (DealerClass dealerClass : dealerClassList)
        {
            dealerClass.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerClass.setCreateTime(CalendarUtils.getCurrentLong());
            dealerClass.setDelFlag(false);
            dealerClassMapper.insert(dealerClass);
        }
    }
    
    public String getAreaNo(String province, String city, String county)
    {
        String areaNo = province;
        if (!org.apache.commons.lang3.StringUtils.isBlank(city)) areaNo = city;
        if (!org.apache.commons.lang3.StringUtils.isBlank(county)) areaNo = county;
        return areaNo;
    }
    
    /**
     * 根据终端商编号获取
     * @author 陈建平
     * @param dealerIdList
     * @return
     */
    @Override
    public List<DealerInfo> getDealerInfos(List<String> dealerIdList){
    	if(null != dealerIdList && dealerIdList.size()>0){
    		return dealerInfoMapper.getDealerInfos(dealerIdList);
    	}
    	return null;
    }
    
    /**
     * 获取App需要同步的终端商数据
     * @author 陈建平
     * @param dealerId
     * @return
     */
    @Override
    public List<Map<String, Object>> listAppDealerInfo(String dealerId)
    {
        List<Map<String, Object>> dataList = dealerInfoMapper.listAppDealerInfo(dealerId);
        for (Map<String, Object> map : dataList)
        {
            map.put("shopBackImg", dealerImageMapper.selectDealerImagesByDealerId((String) map.get("zttxDealerId")));
            map.put("shopDealNo", dealerClassService.findDealNoBy((String) map.get("zttxDealerId")));
            map.put("isEntity", null != map.get("scale") && (Integer) map.get("scale") > 0);
            map.put("shopLogoDomain", ZttxConst.IMAGE_DOMAIN);
            map.put("shopBackImgDomain", ZttxConst.IMAGE_DOMAIN);
        }
        return dataList;
    }
    
    /**
     * 支撑平台的 终端商账号添加
     * @author 陈建平
     * @param userInfo
     * @param dealerInfo
     * @param dealerShopEnvModel
     * @return
     * @throws BusinessException
     */
    @Override
    public String addDealerAccount(UserInfo userInfo, DealerInfoModel dealerInfo, DealerShopEnvModel dealerShopEnvModel) throws BusinessException
    {
        addDealerAccount(userInfo, dealerInfo);
        return addDealerShoperEnv(dealerShopEnvModel);
    }
    
    private String addDealerAccount(UserInfo dealerUserm, DealerInfoModel dealerInfo) throws BusinessException
    {
        // 判断主键是否存在
        UserInfo _dealerUserm = userInfoService.getByMobile(dealerUserm.getUserMobile());
        if (_dealerUserm != null)
        {
            if (_dealerUserm.getUserType() == 0)
            {
                throw new BusinessException(111501, "此用户是品牌商用户，不能注册为终端商:" + _dealerUserm.getRefrenceId());
            }
            else
            {
                throw new BusinessException(111500, "用户已存在:" + _dealerUserm.getRefrenceId());
            }
        }
        // 判断主键是否存在
        _dealerUserm = userInfoService.selectByPrimaryKey(dealerUserm.getRefrenceId());
        if (_dealerUserm != null) { throw new BusinessException(DealerConst.USERM_KEY_EXIST); }
        dealerUserm.setRegisterTime(CalendarUtils.getCurrentLong());
        dealerUserm.setUserType(UserAccountConst.ACCOUNT_TYPE_MASTER);
        dealerUserm.setUserState(UserAccountConst.USER_STAT_OPEN);
        dealerUserm.setMobileVerify(true);
        dealerUserm.setUserSort((short) 0);
        dealerUserm.setRoleId(roleInfoMapper.findRefrenceIdByCode(CommonConstant.RoleType.ROLE_DEALER_USER_CODE));
        dealerUserm.setUserSalt(RandomStringUtils.randomAlphanumeric(6));
        String _userPwd = dealerUserm.getUserPwd();
        if (StringUtils.isEmpty(_userPwd))
        {
            _userPwd = RandomStringUtils.randomNumeric(PASSWORD_LENGTH);
            dealerUserm.setUserPwd(_userPwd);
        }
        dealerUserm.setUserPwd(EncryptUtils.encrypt(dealerUserm.getUserPwd() + dealerUserm.getUserSalt(), ApplicationConst.ENCRYPT));
        userInfoService.insert(dealerUserm);
        dealerInfo.setCreateTime(CalendarUtils.getCurrentLong());
        dealerInfo.setFoundTime(CalendarUtils.getLongFromTime(dealerInfo.getFoundTimeStr()));
        dealerInfo.setDomainName(NetworkUtils.getDoMainName());
        dealerInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_SUCCESS);
        String regionname = regionalService.getFullNameByAreaNoAndLevel(dealerInfo.getAreaNo(), 3, ",");
        String[] regionnames = regionname.split(",");
        if (regionnames.length == 3)
        {
            dealerInfo.setProvinceName(regionnames[0]);
            dealerInfo.setCityName(regionnames[1]);
            dealerInfo.setAreaName(regionnames[2]);
        }
        if (regionnames.length == 2)
        {
            dealerInfo.setProvinceName(regionnames[0]);
            dealerInfo.setCityName(regionnames[1]);
            dealerInfo.setAreaName(regionnames[1]);
        }
        if (regionnames.length == 1)
        {
            dealerInfo.setProvinceName(regionnames[0]);
            dealerInfo.setCityName(regionnames[0]);
            dealerInfo.setAreaName(regionnames[0]);
        }
        insert(dealerInfo);
        // 修改经营品类
        dealerClassService.deleteByDealerInfoId(dealerInfo.getRefrenceId());
        String[] dealNos = dealerInfo.getDealNos();
        List<Integer> noList = Lists.newArrayList();
        if (dealNos != null && dealNos.length > 0)
        {
            List<DealerClass> dealerClassess = dealerInfo.getDealerClassess(dealerInfo.getRefrenceId());
            for (DealerClass dealerClass : dealerClassess)
            {
                noList.add(dealerClass.getDealNo());
                dealerClass.setCreateIp(dealerUserm.getRegisterIp());
                dealerClassService.insert(dealerClass);
            }
        }
        dealerInfo.setDealNo(noList);
        brandCrmService.save(JSON.toJSONString(dealerUserm), ClientConstant.DEALER_INFO_REG);
        brandCrmService.save(JSON.toJSONString(dealerInfo), ClientConstant.DEALER_INFO_BASE);
        DealerCount dealerCount = dealerCountService.selectByDealerId(dealerUserm.getRefrenceId());
        if (dealerCount == null)
        {
            dealerCount = new DealerCount();
            dealerCount.setDealerId(dealerUserm.getRefrenceId());
            dealerCount.setCreatetime(CalendarUtils.getCurrentLong());
            dealerCount.setUpdateTime(CalendarUtils.getCurrentLong());
            dealerCountService.insert(dealerCount);
        }
        return dealerUserm.getRefrenceId();
    }
    
    /**
     * 支撑平台的 终端商账号添加
     * @author 陈建平
     * @param dealerShopEnv
     * @return
     * @throws BusinessException
     */
    @Override
    public String addDealerShoperEnv(DealerShopEnvModel dealerShopEnv) throws BusinessException
    {
        // 判断店铺账号
        if (StringUtils.isEmpty(dealerShopEnv.getDealerId())) { throw new BusinessException("经销商ID 不能为空!"); }
        DealerInfo _dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerShopEnv.getDealerId());
        if (_dealerInfo == null) { 
        	throw new BusinessException("经销商用户 不存在!"); 
        }
        // 设置主键
        dealerShopEnv.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        // 设置区域
        if (dealerShopEnv.getAreaNo() != null)
        {
            String regions = regionalService.getFullNameByAreaNoAndLevel(dealerShopEnv.getAreaNo(), 3, ",");
            String[] arrs = regions.split(",");
            if (arrs != null)
            {
                if (arrs.length == 1)
                {
                    dealerShopEnv.setProvinceName(arrs[0]);
                    dealerShopEnv.setCityName(arrs[0]);
                    dealerShopEnv.setAreaName(arrs[0]);
                }
                if (arrs.length == 2)
                {
                    dealerShopEnv.setProvinceName(arrs[0]);
                    dealerShopEnv.setCityName(arrs[1]);
                    dealerShopEnv.setAreaName(arrs[1]);
                }
                if (arrs.length == 3)
                {
                    dealerShopEnv.setProvinceName(arrs[0]);
                    dealerShopEnv.setCityName(arrs[1]);
                    dealerShopEnv.setAreaName(arrs[2]);
                }
            }
        }
        // 设置时间
        dealerShopEnv.setCreateTime(CalendarUtils.getCurrentLong());
        dealerShopEnv.setUpdateTime(CalendarUtils.getCurrentLong());
        dealerShopEnvService.insert(dealerShopEnv);
        
        // 移槙数据
        if (dealerShopEnv.getEnvTmpImgIds() != null)
        {
            for (String envTmpImgId : dealerShopEnv.getEnvTmpImgIds())
            {
                DealerShopEnvImgTemp _dealerShopEnvImgTemp = dealerShopEnvImgTempService.selectByPrimaryKey(envTmpImgId);
                DealerImage dealerImage = new DealerImage();
                dealerImage.setDealerId(dealerShopEnv.getDealerId());
                dealerImage.setDelFlag(false);
                dealerImage.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                dealerImage.setImageName(_dealerShopEnvImgTemp.getImagePath());
                dealerImage.setCreateTime(CalendarUtils.getCurrentLong());
                dealerImage.setUpdateTime(CalendarUtils.getCurrentLong());
                dealerImageMapper.insert(dealerImage);
            }
        }
        // 保存图片
        if (dealerShopEnv.getDealerImages() != null)
        {
            for (DealerImage dealerImage : dealerShopEnv.getDealerImages())
            {
            	dealerImageMapper.insert(dealerImage);
            }
        }
        return dealerShopEnv.getDealerId();
    }
    
    /**
     * 修改审核状态
     * @param dealerAudit
     *		dealerId	经销商主帐号编号				（必填）
     *		checkState	审核状态（1：通过，2：不通过）	（必填）
     *		userId		审核人员编号			            （必填）
     *		checkMark	审核不通过说明			             （不通过时，必填）
     */
    @Override
    public void updateState(DealerAudit dealerAudit) throws BusinessException
    {
        ClientValidator.validateByClient(dealerAudit);
        DealerInfo dealerInfo = dealerInfoMapper.selectByPrimaryKey(dealerAudit.getDealerId());
        if (null == dealerInfo) { throw new BusinessException(ClientConst.PARAMERROR.code, "经销商账号不存在"); }
        dealerAudit.setDealerName(dealerInfo.getDealerName());
        String dealerId = dealerAudit.getDealerId();
        Short checkState = dealerAudit.getCheckState();
        // 1.操作 审核不通过
        if (checkState == DealerConstant.DealerInfo.CHECK_STATE_FAILURE)
        {
            String checkMark = dealerAudit.getCheckMark();
            if (StringUtils.isBlank(checkMark)) { 
            	throw new BusinessException(ClientConst.PARAMERROR.code, "审核不通过原因不能为空");
            }
            dealerInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_FAILURE);
            dealerInfoMapper.updateByPrimaryKey(dealerInfo);
            dealerAudit.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            dealerAudit.setDelFlag(false);
            dealerAuditService.insert(dealerAudit);
        }
        // 2.操作审核通过
        else if (checkState == DealerConstant.DealerInfo.CHECK_STATE_SUCCESS)
        {
            if (dealerInfo.getCheckState() != null && dealerInfo.getCheckState().intValue() == DealerConstant.DealerInfo.CHECK_STATE_SUCCESS) { throw new BusinessException(
                    ClientConst.CLIENT_AREADY_SUCCESS); }
            dealerInfo.setCheckState(DealerConstant.DealerInfo.CHECK_STATE_SUCCESS);
            dealerInfoMapper.updateByPrimaryKey(dealerInfo);
            DealerCount dealerCount = dealerCountService.selectByDealerId(dealerId);
            if (dealerCount == null)
            {
                dealerCount = new DealerCount();
                dealerCount.setDealerId(dealerId);
                dealerCount.setCreatetime(CalendarUtils.getCurrentLong());
                dealerCount.setUpdateTime(CalendarUtils.getCurrentLong());
                dealerCount.setDelFlag(false);
                dealerCountService.insert(dealerCount);
            }
        }
    }
    
    @Override
    public boolean modifyDealerInfoRcvSmsVerify(String dealerId, boolean bRcvSmsVerify)
    {
        return dealerInfoMapper.updateDealuerInfoRcvSmsVerify(dealerId, bRcvSmsVerify) == 1;
    }
}
