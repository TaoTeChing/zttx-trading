/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.web.consts.ClientConstant;
import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.module.brand.entity.BrandContact;
import com.zttx.web.module.brand.mapper.BrandContactMapper;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.mapper.UserInfoMapper;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.FileClientUtil;

/**
 * 品牌商联系方式 服务实现类
 * <p>File：BrandContact.java </p>
 * <p>Title: BrandContact </p>
 * <p>Description:BrandContact </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BrandContactServiceImpl extends GenericServiceApiImpl<BrandContact> implements BrandContactService
{
    private BrandContactMapper brandContactMapper;
    
    @Autowired
    private UserInfoMapper     userInfoMapper;
    
    @Autowired
    private BrandCrmService    brandCrmService;
    
    @Autowired(required = true)
    public BrandContactServiceImpl(BrandContactMapper brandContactMapper)
    {
        super(brandContactMapper);
        this.brandContactMapper = brandContactMapper;
    }
    
    @Override
    public BrandContact findByBrandId(String brandId)
    {
        BrandContact brandContact = brandContactMapper.findByBrandId(brandId);
        brandContact = this.getBrandContact(brandContact, brandId);
        return brandContact;
    }
    
    @Override
    public BrandContact updateFile(BrandContact newContact, BrandContact oldContact) throws BusinessException
    {
        if (StringUtils.isNotBlank(newContact.getUserPhoto()))
        {
            // String path = MultipartUtils.moveAndresizeFile(request, MultipartUtils.BRAND_IMG_PATH, newContact.getUserPhoto(), UploadAttCateConst.USER_PHOTO);
            /**需改成Http调文件服务器接口方式**/
            String path = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, newContact.getUserPhoto(), UploadAttCateConst.USER_PHOTO);
            newContact.setUserPhoto(path);
        }
        BrandContact brandContact = this.save(newContact, oldContact);
        brandCrmService.save(JSON.toJSONString(brandContact), ClientConstant.BRAND_CONTACT);
        return brandContact;
    }
    
    @Override
    public BrandContact save(BrandContact newContact, BrandContact oldContact) throws BusinessException
    {
        if (StringUtils.isBlank(oldContact.getRefrenceId()))
        {
            newContact.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            newContact.setBrandId(oldContact.getBrandId());
            newContact.setBrandsId(null);
            if (oldContact.getMailVerify())
            {
                newContact.setUserMail(oldContact.getUserMail());
            }
            newContact.setCreateTime(CalendarUtils.getCurrentLong());
            brandContactMapper.insert(newContact);
        }
        else
        {
            newContact.setRefrenceId(oldContact.getRefrenceId());
            newContact.setBrandId(oldContact.getBrandId());
            newContact.setBrandsId(oldContact.getBrandsId());
            if (oldContact.getMailVerify())
            {
                newContact.setUserMail(oldContact.getUserMail());
            }
            newContact.setCreateTime(oldContact.getCreateTime());
            this.brandContactMapper.updateByPrimaryKey(newContact);
        }
        return newContact;
    }
    
    @Override
    public void updatePhote(String userPhoto, BrandContact oldContact) throws BusinessException
    {
        if (StringUtils.isBlank(oldContact.getRefrenceId()))
        {
            BrandContact newContact = new BrandContact();
            newContact.setUserName("");
            newContact.setUserPhoto(userPhoto);
            newContact.setUserGender((short) 0);
            newContact.setUserJob("");
            newContact.setUserIm("");
            newContact.setUserMobile(oldContact.getUserMobile());
            newContact.setUserTelphone("");
            newContact.setUserFax("");
            this.updateFile(newContact, oldContact);
            oldContact.setUserPhoto(newContact.getUserPhoto());
        }
        else
        {
            String headImage = FileClientUtil.moveAndDeleteFile(ImageConst.BRAND_IMG_PATH, userPhoto, UploadAttCateConst.USER_PHOTO);
            oldContact.setUserPhoto(headImage);
            oldContact.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateByPrimaryKeySelective(oldContact);
        }
    }
    
    /**
     * 获取BrandContact
     * @param brandContact
     * @param brandId
     * @return
     * @throws BusinessException
     */
    private BrandContact getBrandContact(BrandContact brandContact, String brandId)
    {
        UserInfo brandUserm = userInfoMapper.selectByPrimaryKey(brandId);
        if(brandUserm==null){
            return null;
        }
        if (null == brandContact)
        {
            brandContact = new BrandContact();
            brandContact.setBrandId(brandId);
        }
        
        brandContact.setAccountName(brandUserm.getUserMobile());
        if (brandUserm.getMailVerify())
        {
            brandContact.setUserMail(brandUserm.getUserMail());
        }
        else
        {
            brandContact.setUserMail(StringUtils.isNotBlank(brandContact.getUserMail()) ? brandContact.getUserMail() : brandUserm.getUserMail());
        }
        brandContact.setMailVerify(brandUserm.getMailVerify());
        if (StringUtils.isBlank(brandContact.getUserMobile()))
        {
            brandContact.setUserMobile(brandUserm.getUserMobile());
        }
        return brandContact;
    }
}
