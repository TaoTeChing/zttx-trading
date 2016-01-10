/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.CommonConst;
import com.zttx.web.consts.ExceptionConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.brand.entity.BrandDoccate;
import com.zttx.web.module.brand.entity.BrandDocument;
import com.zttx.web.module.brand.entity.BrandLevel;
import com.zttx.web.module.brand.model.BrandDoccateModel;
import com.zttx.web.module.brand.model.BrandDocumentModel;
import com.zttx.web.module.brand.model.BrandJoinFilter;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.service.BrandDoccateService;
import com.zttx.web.module.brand.service.BrandDocumentService;
import com.zttx.web.module.brand.service.BrandLevelService;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.IPUtil;
import com.zttx.web.utils.ValidateUtils;

/**
 * 品牌商资料信息 控制器
 * <p>File：BrandDocumentController.java </p>
 * <p>Title: BrandDocumentController </p>
 * <p>Description:BrandDocumentController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/brandDocument")
public class BrandDocumentController extends GenericController
{
    private static final Logger  logger = Logger.getLogger(BrandDocumentController.class);
    
    @Autowired(required = true)
    private BrandDocumentService brandDocumentService;
    
    @Autowired
    private BrandesInfoService   brandesInfoService;
    
    @Autowired
    private BrandDoccateService  brandDoccateService;
    
    @Autowired
    private BrandLevelService    brandLevelService;
    
    /** 资料上传管理页面
     * 
     * @param request
     * @param model
     * @return
     * @throws BusinessException 
     */
    @RequestMapping(value = "")
    @RequiresPermissions("brand:center")
    public String document(Model model) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        List<BrandsInfoModel> infoList = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandesList", infoList);
        model.addAttribute("fileUrl", ZttxConst.FILEAPI_WEBURL);
        return "brand/down_manage";
    }
    
    /**
     * 资料列表页
     * @param page
     * @param request
     * @param model
     * @return
     * @author 施建波
     */
    @RequestMapping(value = "/list")
    @RequiresPermissions("brand:center")
    public String documentList()
    {
        return "brand/list_document";
    }
    
    /**
     * 分类管理
     * @return
     * @throws BusinessException 
     */
    @RequestMapping(value = "/sort")
    @RequiresPermissions("brand:center")
    public String sort(BrandDoccate brandDoccate, HttpServletRequest request, Model model) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        String brandsId = brandDoccate.getBrandsId();
        List<BrandsInfoModel> brandesInfoList = brandesInfoService.getCooperatedBrandes(brandId);
        model.addAttribute("brandesInfoList", brandesInfoList);
        if (null != brandesInfoList && !brandesInfoList.isEmpty())
        {
            if (StringUtils.isBlank(brandsId))
            {
                brandsId = brandesInfoList.get(0).getRefrenceId();
            }
            List<BrandDoccate> brandDoccates = brandDoccateService.getBrandDoccateList(brandId, brandsId);
            model.addAttribute("brandDoccates", brandDoccates);
        }
        model.addAttribute("brandsId", brandsId);
        return "brand/list_sort";
    }
    
    // 保存分类
    @RequestMapping(value = "/sort/save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public Object insertCatalog(BrandDoccateModel brandDoccate, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        brandDoccate.setBrandId(brandId);
        try
        {
            brandDoccateService.insertBrandDoccate(brandDoccate);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 资料信息删除
     * @param model
     * @param refrenceId
     * @param request
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonMessage delete(@RequestParam("uuid") String refrenceId, HttpServletRequest request) throws BusinessException
    {
        if (StringUtils.isBlank(refrenceId)) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        try
        {
            brandDocumentService.deleteDocument(brandId, refrenceId);
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 品牌资料列表数据
     * @param pagination
     * @param request
     * @return
     * @author 施建波
     * @throws BusinessException 
     */
    @RequestMapping(value = "/data")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public Object listJson(Pagination pagination) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        PaginateResult<BrandDocumentModel> page = brandDocumentService.getBrandDocumentList(pagination, brandId);
        return this.getJsonMessage(ExceptionConst.SUCCESS, page);
    }
    
    /**
     * 保存品牌商资料
     * 
     * @param request
     * @param brandDocument
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage execute(HttpServletRequest request, BrandDocumentModel brandDocument, Model model) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        brandDocument.setBrandId(brandId);
        List<Map<String, String>> validatorList = validator(brandDocument);
        if (null != validatorList && !validatorList.isEmpty()) { return this.getJsonMessage(CommonConst.FORM_PARAMS_VALID_ERR, validatorList); }
        brandDocument.setCreateIp(IPUtil.getIpAddr(request));
        Short[] brandStates = {BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED};
        // 判断当前状态是不是能进行操作
        brandesInfoService.validatorState(brandId, brandDocument.getBrandsId(), brandStates);
        try
        {
            if (StringUtils.isBlank(brandDocument.getRefrenceId()))
            {
                brandDocumentService.insertDocument(brandDocument);
            }
            else
            {
                brandDocumentService.updateDocument(brandDocument);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/execute/{refrenceId}", method = RequestMethod.GET)
    public String editDocument(@PathVariable String refrenceId, Model model) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        BrandDocument brandDocument = brandDocumentService.getBrandDocument(brandId, refrenceId);
        List<BrandDoccate> doccateList = brandDoccateService.getBrandDoccateList(brandDocument.getBrandId(), brandDocument.getBrandsId());
        model.addAttribute("oldBrandDocument", brandDocument);
        model.addAttribute("doccateList", doccateList);
        return document(model);
    }
    
    // 参数检验
    private List<Map<String, String>> validator(BrandDocument brandDocument)
    {
        List<Map<String, String>> validatorList = Lists.newArrayList();
        String name = "brandsId";
        if (StringUtils.isBlank(brandDocument.getBrandsId()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getSelMsgStr("品牌")));
        }
        name = "docName";
        String msgName = "资料名称";
        Integer words = 24;
        if (StringUtils.isBlank(brandDocument.getDocName()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr(msgName)));
        }
        else
        {
            if (!ValidateUtils.isRange(brandDocument.getDocName(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr(msgName, words)));
            }
        }
        name = "docnFile";
        if (StringUtils.isBlank(brandDocument.getDocnFile()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr("上传资料")));
        }
        if (StringUtils.isNotBlank(brandDocument.getWebAddress()))
        {
            name = "webAddressErr";
            msgName = "网盘地址";
            words = 128;
            if (!ValidateUtils.isRange(brandDocument.getWebAddress(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr(msgName, words)));
            }
            else
            {
                if (!ValidateUtils.isUrl(brandDocument.getWebAddress(), true, words))
                {
                    validatorList.add(this.getErrMsgMap(name, "不是有效的" + msgName));
                }
            }
        }
        name = "docMark";
        msgName = "内容描述";
        words = 4000;
        if (StringUtils.isBlank(brandDocument.getDocMark()))
        {
            validatorList.add(this.getErrMsgMap(name, this.getNuLLMsgStr(msgName)));
        }
        else
        {
            if (!ValidateUtils.isRange(brandDocument.getDocMark(), true, words))
            {
                validatorList.add(this.getErrMsgMap(name, this.getMaxMsgStr(msgName, words)));
            }
        }
        return validatorList;
    }
    
    /**
     * 获取资料分类
     * @param brandsId
     * @param request
     * @return
     * @author 施建波
     * @throws BusinessException 
     */
    @RequestMapping(value = "/doccateList")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public Object simpleList(String brandsId) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        List<BrandDoccate> doccateList = brandDoccateService.getBrandDoccateList(brandId, brandsId);
        return this.getJsonMessage(CommonConst.SUCCESS, doccateList);
    }
    
    /**
     * 下载文件
     * @author 陈建平
     * @param id
     * @param request
     * @param response
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/fileDown")
    public void trainDown(String id, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            if (StringUtils.isBlank(id)) { throw new BusinessException(CommonConst.PARAMS_VALID_ERR); }
            BrandDocument brandDocument = brandDocumentService.selectByPrimaryKey(id);
            if (null == brandDocument) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            String fileName = brandDocument.getDocoFile();
            if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0)
            {// 火狐浏览器
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
            else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
            {// IE浏览器
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            else if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0)
            {// CHROME浏览器
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
            else
            {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            response.setContentType("application/octet-stream;");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            URL url = new URL(ZttxConst.FILEAPI_WEBURL + brandDocument.getDocnFile());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
            os.flush();
            os.close();
            inputStream.close();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }
    
    /**
     * 获取可以查看资料的终端商
     * @author 陈建平
     * @param filter
     * @param request
     * @return
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/dealers")
    public JsonMessage queryJoinedDealers(@Valid BrandJoinFilter filter, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal user = OnLineUserUtils.getCurrentBrand();
        String brandId = user.getRefrenceId();
        if (!"1".equals(filter.getAddLevelType()) && !"2".equals(filter.getAddLevelType()) && !"3".equals(filter.getAddLevelType())) { return this
                .getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        if (!"3".equals(filter.getAddLevelType()))
        {
            if (StringUtils.isBlank(filter.getBid())) { return this.getJsonMessage(CommonConst.PARAMS_VALID_ERR); }
        }
        List<Map<String, Object>> queryResult = brandDoccateService.getDocJoinedDealers(filter, brandId);
        BrandLevel brandLevel = new BrandLevel();
        brandLevel.setBrandId(brandId);
        brandLevel.setBrandsId(filter.getBid());
        List<BrandLevel> levels = brandLevelService.findList(brandLevel);
        Map<String, Object> map = Maps.newHashMap();
        map.put("levels", levels);
        map.put("result", queryResult);
        return this.getJsonMessage(CommonConst.SUCCESS, map);
    }
}
