/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import javax.servlet.http.HttpServletRequest;

import com.zttx.web.consts.ImageConst;
import com.zttx.web.consts.UploadAttCateConst;
import com.zttx.web.utils.FileClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandCard;
import com.zttx.web.module.brand.model.BrandCardModel;
import com.zttx.web.module.brand.service.BrandCardService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.IPUtil;

/**
 * 品牌商证书信息 控制器
 * <p>File：BrandCardController.java </p>
 * <p>Title: BrandCardController </p>
 * <p>Description:BrandCardController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/card")
public class BrandCardController extends GenericController
{
    @Autowired(required = true)
    private BrandCardService brandCardService;
    
    /**
     * 进入证书页面
     *
     * @return
     */
    @RequestMapping("/execute")
    @RequiresPermissions("brand:center")
    public String brandNewsList()
    {
        return "brand/list_brandCard";
    }
    
    /**
     * 获取品牌商证书列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("brand:center")
    public JsonMessage list(HttpServletRequest request, Pagination page) throws BusinessException
    {
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        PaginateResult<BrandCard> result = brandCardService.search(page, brandId);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 保存品牌商证书
     *
     * @param request
     * @param brandCard
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage save(HttpServletRequest request, BrandCardModel brandCard, Model model) throws BusinessException
    {
        // 验证
        if (!super.beanValidatorNoProperty(model, brandCard)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        if (StringUtils.isBlank(brandCard.getCertImage()) && StringUtils.isBlank(brandCard.getCertImagePath())) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
        UserPrincipal userPrincipal = OnLineUserUtils.getCurrentBrand();
        String brandId = userPrincipal.getRefrenceId();
        brandCard.setBrandId(brandId);
        brandCard.setCreateIp(IPUtil.getIpAddr(request));
        BrandCard oldCard = null;
        if (StringUtils.isNotBlank(brandCard.getRefrenceId()))
        {
            oldCard = this.brandCardService.getEntity(brandCard.getRefrenceId(), brandCard.getBrandId());
            if (null == oldCard) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        }
        // 迁移和压缩图片,返回图片路径
        if (StringUtils.isNotBlank(brandCard.getCertImagePath()))
        {
            // String path = MultipartUtils.moveAndresizeFile(request, MultipartUtils.BRAND_IMG_PATH, brandCard.getCertImagePath(), UploadAttCateConst.BRAND_CARD);
            /**需改成Http调文件服务器接口方式**/
            String path = FileClientUtil.moveImgFromTemp(ImageConst.BRAND_IMG_PATH, brandCard.getCertImagePath(), UploadAttCateConst.BRAND_CARD);
            brandCard.setCertImagePath(path);
        }
        this.brandCardService.saveImage(brandCard, oldCard);
        this.brandCardService.save(brandCard, oldCard);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 删除品牌商证书
     *
     * @param request
     * @param uuid
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(HttpServletRequest request, String uuid) throws BusinessException
    {
        if (StringUtils.isBlank(uuid)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        BrandCard brandCard = brandCardService.selectByPrimaryKey(uuid);
        if (null == brandCard) throw new BusinessException(CommonConst.DATA_NOT_EXISTS);
        this.brandCardService.delete(uuid);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
}
