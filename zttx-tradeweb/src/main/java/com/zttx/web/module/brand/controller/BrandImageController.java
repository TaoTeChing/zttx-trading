/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.ApplicationConst;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.module.brand.entity.BrandImage;
import com.zttx.web.module.brand.service.BrandImageService;
import com.zttx.web.module.brand.service.BrandImgcateService;
import com.zttx.web.module.common.model.MenuTree;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.MultiPartUtils;

/**
 * 品牌商图片管理 控制器
 * <p>File：BrandImageController.java </p>
 * <p>Title: BrandImageController </p>
 * <p>Description:BrandImageController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandImage")
public class BrandImageController extends GenericController
{
    private static final Logger logger = LoggerFactory.getLogger(BrandImageController.class);
    
    @Autowired(required = true)
    private BrandImageService   brandImageService;
    
    @Autowired
    private BrandImgcateService brandImgcateService;
    
    /**
     * 图库信息页
     * @param request
     * @param model
     * @return
     * @author 施建波
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/gallery")
    public String gallery(Pagination page, HttpServletRequest request, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String parendId = request.getParameter("parendId");
        model.addAttribute("parendId", parendId);
        List<MenuTree> menuTree = brandImgcateService.getCateMenuTree(brandId);
        if (menuTree == null)
        {
            model.addAttribute("menuTree", "-1");
            model.addAttribute("page", "-1");
        }
        else
        {
            MenuTree allMenuTree = new MenuTree();
            allMenuTree.setName("全部");
            allMenuTree.setId("");
            menuTree.add(0, allMenuTree);
            PaginateResult<BrandImage> result = brandImageService.findByBrandId(page, brandId);
            Pagination pagination = result.getPage();
            JsonMessage jsonMessage = this.getJsonMessage(0);
            jsonMessage.setRows(result.getList());
            jsonMessage.setTotal(result.getPage().getTotalCount());
            jsonMessage.setHasNext(pagination.getHasNextPage());
            jsonMessage.setHasPrevious(pagination.getHasPreviousPage());
            jsonMessage.setCurrentPage(pagination.getCurrentPage());
            jsonMessage.setTotalPage(pagination.getTotalPage());
            model.addAttribute("page", JSON.toJSON(jsonMessage));
            model.addAttribute("menuTree", JSON.toJSON(menuTree));
        }
        return "/brand/gallery_image";
    }
    
    /**
     * 图库中心首页
     * @param page
     * @param image
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image")
    public String execute(Pagination page, BrandImage image, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        image.setBrandId(brandId);
        List<MenuTree> menuTree = this.brandImgcateService.getCateMenuTree(brandId);
        PaginateResult<BrandImage> paginateResult = this.brandImageService.search(page, image, false);
        model.addAttribute("listCate", menuTree);
        model.addAttribute("conditions", image);
        model.addAttribute("pageResult", paginateResult);
        return "/brand/list_picture";
    }
    
    /**
     * 图片上传页
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/upload_picture")
    public String uploadPage(Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        Long currentSize = this.brandImageService.getFileSum(brandId, null) * 1024;
        Long totalSize = ApplicationConst.BRAND_PIC_TOTAL_SIZE;
        model.addAttribute("currentSize", brandImageService.formatSize(currentSize, "MB"));// 当前图库大小
        model.addAttribute("totalSize", brandImageService.formatSize(totalSize, "MB"));// 当前图库大小
        model.addAttribute("percent", brandImageService.getPercent(currentSize, totalSize));
        List<MenuTree> menuTree = this.brandImgcateService.getCateMenuTree(brandId);
        model.addAttribute("listCate", menuTree);
        model.addAttribute("brandId", brandId);
        return "/brand/upload_picture";
    }
    
    /**
     * 回收站
     * @param page
     * @param image
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/recycle_picture")
    public String recyclePage(Pagination page, BrandImage image, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        image.setBrandId(brandId);
        PaginateResult<BrandImage> result = brandImageService.search(page, image, true);
        model.addAttribute("pageResult", result);
        return "/brand/recycle_picture";
    }
    
    /**
     * 删除图片放置回收站
     * @param ids
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/delete", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage deleteImages(@RequestBody String[] ids) throws BusinessException
    {
        if (null != ids && ids.length > 0)
        {
            for (int i = 0; i < ids.length; i++)
            {
                brandImageService.delete(ids[i]);
            }
        }
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 永久删除图片
     * @param ids
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/realdelete", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage realDeleteImages(@RequestBody String[] ids, HttpServletRequest request)
    {
        if (null != ids && ids.length > 0)
        {
            for (int i = 0; i < ids.length; i++)
            {
                brandImageService.deleteByPrimaryKey(ids[i]);
            }
        }
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 批量还原图片
     * @param ids
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/revert", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage revertImages(@RequestBody String[] ids, HttpServletRequest request) throws BusinessException
    {
        if (null != ids && ids.length > 0)
        {
            for (int i = 0; i < ids.length; i++)
            {
                brandImageService.revert(ids[i]);
            }
        }
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 清空回收站
     * @return JsonMessage
     * @author 章旭楠
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/clean")
    @ResponseBody
    public JsonMessage cleanRecycles() throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        brandImageService.deleteRecycles(brandId);
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 移动到其他类
     * @param ids
     * @param newCateId
     * @param request
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/image/move", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage moveImages(String[] ids, String newCateId, HttpServletRequest request) throws BusinessException
    {
        if (null == ids || ids.length == 0 || StringUtils.isBlank(newCateId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        this.brandImageService.updateImageCate(ids, newCateId, brandId);
        return super.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 显示图库
     * @param request
     * @param page
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/showImgLib")
    @ResponseBody
    public JsonMessage showImgLib(HttpServletRequest request, Pagination page) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String brandImgcateId = request.getParameter("brandImgcateId");
        String imageName = request.getParameter("imageName");
        BrandImage image = new BrandImage();
        image.setImageName(imageName);
        image.setBrandId(brandId);
        image.setCateId(brandImgcateId);
        image.setPage(page);
        PaginateResult<BrandImage> result = brandImageService.findByImage(image);
        return super.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    /**
     * 图片上传
     * @param request 请求
     * @param cateId 类型id
     * @param brandId 品牌商id
     * @param file 文件
     * @return
     * @throws Exception
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/image/batchupload", method = RequestMethod.POST)
    public JsonMessage uploadBatch(HttpServletRequest request, String cateId, String brandId, MultipartFile file) throws Exception
    {
        try
        {
            if (null == file) { throw new BusinessException(CommonConst.PARAM_NULL); }// 文件是否存在
            MultiPartUtils.checkFileType(file.getOriginalFilename(), StringUtils.join(ApplicationConst.IMAGE_SUFFIX, ","));// 检查文件类型 是否是图片
            // String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();由于通过flash上传时，有些浏览器会把sessionId屏蔽，导致获取不到登录用户
            long fileSize = file.getSize() / 1024 + 1;
            this.brandImageService.checkFileSize(brandId, null, fileSize); // 检查是否到达文件上传上限
            Map resultMap = (Map) brandImageService.uploadFile(file).getObject();
            resultMap.put("sizeKb", Integer.valueOf(fileSize + ""));
            Integer ipAddr = IPUtil.getIpAddr(request);
            BrandImage brandImage = new BrandImage();
            brandImageService.parseImage(brandImage, cateId, brandId, ipAddr, resultMap);
            this.brandImageService.insertSelective(brandImage);
            // this.brandImageService.savePicBatch(cateId, brandId, map, ipAddr);
            return super.getJsonMessage(CommonConst.SUCCESS, resultMap);
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            return super.getJsonMessage(e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        }
    }
    
    /**
     * 图库列表替换某个图片
     *
     */
    @RequiresPermissions("brand:center")
    @ResponseBody
    @RequestMapping(value = "/image/replace", method = RequestMethod.POST)
    public JsonMessage updateImg(String imgId, @RequestParam MultipartFile photo) throws Exception
    {
        JsonMessage jsonMessage = this.getJsonMessage(CommonConst.SUCCESS);
        try
        {
            if (null == imgId || null == photo) { throw new BusinessException(CommonConst.PARAM_NULL); }
            BrandImage image = brandImageService.selectByPrimaryKey(imgId);
            if (null == image) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            MultiPartUtils.checkFileType(photo.getOriginalFilename(), StringUtils.join(ApplicationConst.IMAGE_SUFFIX, ","));// 检查文件类型 是否是图片
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            long fileSize = photo.getSize() / 1024 + 1;
            this.brandImageService.checkFileSize(brandId, image.getRefrenceId(), fileSize); // 检查是否到达文件上传上限
            Map resultMap = (Map) brandImageService.uploadFile(photo).getObject();
            resultMap.put("sizeKb", Integer.valueOf(fileSize + ""));
            brandImageService.parseImage(image, resultMap);// 将文件服务器返回的结果 转给图片对象
            brandImageService.updateByPrimaryKeySelective(image);
            jsonMessage.setMessage(image.getImageName());
        }
        catch (BusinessException e)
        {
            logger.error(e.getMessage());
            jsonMessage.setCode(e.getErrorCode().getCode());
            jsonMessage.setMessage(e.getErrorCode().getMessage());
        }
        return jsonMessage;
    }
}
