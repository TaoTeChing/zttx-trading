/*
 * @(#)BrandProductBatchController.java 
 * Copyright 2015 张昌苗, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.redisson.core.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zttx.goods.module.dto.ProductBaseInfoModel;
import com.zttx.goods.module.dto.ProductFormBean;
import com.zttx.goods.module.entity.*;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.RedissonUtils;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.DealDicServiceDubboConsumer;
import com.zttx.web.dubbo.service.ProductInfoDubboConsumer;
import com.zttx.web.module.brand.entity.BrandFreightTemplate;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.model.ProductInfoUploadDataModel;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.brand.service.ProductInfoUploadService;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.entity.ProductCatalog;
import com.zttx.web.module.common.entity.ProductCount;
import com.zttx.web.module.common.entity.ProductWeightInfo;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.ProductCountService;
import com.zttx.web.module.common.service.ProductWeightInfoService;
import com.zttx.web.module.common.service.UserInfoService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.utils.ProductInfoHelper;
import com.zttx.web.utils.ReadExcelUtils;
import com.zttx.web.utils.entity.ExcelCellValue;

/**
 * <p>File：BrandProductBatchController.java</p>
 * <p>Title: </p>
 * <p>Description:相关文件：upload_productInfo.jsp</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: 8637.com</p>
 * @author 
 * @version 
 */
@Controller
@RequestMapping(ApplicationConst.BRAND + "/product/upload")
public class BrandProductBatchController extends GenericBaseController
{
    private final static Logger         logger = LoggerFactory.getLogger(BrandProductBatchController.class);
    
    @Autowired
    private ProductInfoDubboConsumer    productInfoDubboConsumer;
    
    @Autowired
    private BrandesInfoService          brandesInfoService;
    
    @Autowired
    private ProductInfoUploadService    productInfoUploadService;
    
    @Autowired
    private UserInfoService             userInfoService;
    
    @Autowired
    private ProductCountService         productCountService;
    
    @Autowired
    private ProductWeightInfoService    productWeightInfoService;
    
    @Autowired
    private DealDicServiceDubboConsumer dealDicServiceDubboConsumer;
    
    /**
     * 导入页面
     * @author 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "")
    public String view()
    {
        return "brand/upload_productInfo";
    }
    
    /**
     * 导入数据
     * @author 张昌苗
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/importData")
    @ResponseBody
    public JsonMessage importData(String filePath, HttpServletRequest request, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        UserInfo parent = userInfoService.getParentUserByChildId(brandId);
        String brandPid = parent != null ? parent.getRefrenceId() : null;
        ProductInfoUploadDataModel dataModel = productInfoUploadService.getProductInfoUploadDataModel(brandId, null, brandPid);
        Workbook workbook = ReadExcelUtils.getWorkbook(request, filePath);
        Integer type = productInfoUploadService.getType(workbook);
        if (BrandConstant.ProductInfoUpload.IMPORT_TYPE_ONE == type)
        {
            return importProduct(workbook, request, model, dataModel);
        }
        else if (BrandConstant.ProductInfoUpload.IMPORT_TYPE_TWO == type)
        {
            return importProductTwo(workbook, request, model, dataModel);
        }
        else if (BrandConstant.ProductInfoUpload.IMPORT_TYPE_THREE == type)
        {
            return importProductThree(workbook, request, model, dataModel);
        }
        else
        {
            throw new BusinessException(CommonConst.PARAMS_VALID_ERR);
        }
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping("/getError")
    public void getError(HttpServletRequest request, String errorUrl, HttpServletResponse response) throws BusinessException
    {
        try
        {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            String fileName = "错误文件.xls";
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
            URL url = new URL(ZttxConst.FILEAPI_WEBURL + errorUrl);
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
     * 获取模板
     * @author 张昌苗
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/getTemplate")
    public void getTemplate(HttpServletRequest request, Integer type, HttpServletResponse response) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        UserInfo parent = userInfoService.getParentUserByChildId(brandId);
        String brandPid = parent != null ? parent.getRefrenceId() : null;
        ProductInfoUploadDataModel dataModel = productInfoUploadService.getProductInfoUploadDataModel(brandId, null, brandPid);
        String templateFilePath;
        String fileName;
        if (null != type && BrandConstant.ProductInfoUpload.IMPORT_TYPE_TWO == type)
        {
            templateFilePath = productInfoUploadService.createExcelFileTwo(request, dataModel, null, false, "模板");
            fileName = "模板2.xls";
        }
        else if (null != type && BrandConstant.ProductInfoUpload.IMPORT_TYPE_THREE == type)
        {
            templateFilePath = productInfoUploadService.createExcelFileThree(request, dataModel, null, false, "模板");
            fileName = "模板3.xls";
        }
        else
        {
            templateFilePath = productInfoUploadService.createExcelFile(request, dataModel, null, null, false, "模板");
            fileName = "模板1.xls";
        }
        try
        {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
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
            URL url = new URL(ZttxConst.FILEAPI_WEBURL + templateFilePath);
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
    
    private ProductFormBean getProductParams(Map<String, ExcelCellValue> piData, ProductInfoUploadDataModel dataModel) throws BusinessException
    {
        ProductFormBean productInfo = new ProductFormBean();
        productInfo.setProductCate(ProductConsts.CATE_STOCK.getKey());
        productInfo.setStateSet(String.valueOf(ProductConsts.BEGIN_TYPE_STORE.getKey()));
        productInfo.setProductGroom(false);
        // 一层类目，二层类目
        String yclm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_YCLM, true);
        String eclm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_ECLM, true);
        DealDic dealDic = dataModel.getChildDealDic(yclm, eclm);
        productInfo.setDealNo(dealDic.getDealNo().intValue());
        // 品牌
        String ppxz = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_PPXZ, true);
        BrandesInfo brandesInfo = dataModel.getBrandesInfo(ppxz);
        productInfo.setBrandsId(brandesInfo.getRefrenceId());
        // 产品货号
        String cphh = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CPHH, true);
        productInfo.setProductNo(cphh);
        // 产品标题
        String cpbt = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CPBT, true);
        productInfo.setProductTitle(cpbt);
        // 单位
        String dw = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_DW, true);
        WebUnit webUnit = dataModel.getWebUnit(dw);
        productInfo.setUnitNo(webUnit.getRefrenceId());
        productInfo.setUnit(webUnit.getUnitName());
        /*
         * // 库存
         * Integer kc = ReadExcelUtils.getIntegerValue(piData, BrandConstant.ProductInfoUpload.HEAD_KC, true);
         * productInfo.setProductStore(kc);
         * // 企业编码
         * String qybh = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_QYBH, false);
         * productInfo.setCompanyCode(qybh);
         * // 条形码
         * String txm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_TXM, false);
         * productInfo.setBarCode(txm);
         */
        // 起批量
        Integer qpl = ReadExcelUtils.getIntegerValue(piData, BrandConstant.ProductInfoUpload.HEAD_QPL, true);
        productInfo.setStartNum(qpl);
        // 批量说明
        String plsm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_PLSM, false);
        productInfo.setPatchMark(plsm);
        // 主图
        String zt = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_ZT, true);
        String[] imageArr = zt.split(",");
        String[] photoNameArr = new String[imageArr.length];
        for (int i = 0; i < photoNameArr.length; i++)
        {
            if (-1 == imageArr[i].lastIndexOf("/")) { throw new BusinessException(CommonConst.FAIL.code, "主图路径不正确"); }
            photoNameArr[i] = imageArr[i].substring(imageArr[i].lastIndexOf("/"));
        }
        productInfo.setImages(imageArr);
        productInfo.setPhotoName(photoNameArr);
        // 描述
        String ms = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_MS, true);
        productInfo.setProductMark(ms);
        // 产品分类
        String cpfl = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CPFL, false);
        if (null != cpfl)
        {
            List<String> cateIdList = Lists.newArrayList();
            String[] cpflArr = cpfl.split(",");
            for (String s : cpflArr)
            {
                ProductCatalog productCatalog = dataModel.getProductCatalog(brandesInfo, s);
                cateIdList.add(productCatalog.getRefrenceId());
            }
            productInfo.setCateIds(StringUtils.join(cateIdList, ","));
        }
        // 重量
        Double zl = ReadExcelUtils.getDoubleValue(piData, BrandConstant.ProductInfoUpload.HEAD_ZL, true);
        productInfo.setProductWeight(zl);
        // 承担运费
        String cdyf = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CDYF, true);
        Short productCarry = dataModel.getProductCarry(cdyf);
        productInfo.setProductCarry((int) productCarry);
        if (1 == productCarry)
        {
            // 运费模版
            String yfmb = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_YFMB, true);
            BrandFreightTemplate brandFreightTemplate = dataModel.getBrandFreightTemplate(yfmb);
            productInfo.setFreTemplateId(brandFreightTemplate.getRefrenceId());
        }
        // 退换货承诺
        String thhcn = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_THHCN, false);
        productInfo.setProductBear(thhcn);
        /*
         * // 拿样
         * String ny = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_NY, true);
         * Boolean isSample = dataModel.getIsSample(ny);
         * productInfo.setIsSample(isSample);
         */
        // 前台可见
        String qtkj = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_QTKJ, true);
        Boolean isShow = dataModel.getIsShow(qtkj);
        productInfo.setIsShow(isShow);
        // 获取拿样价
        BigDecimal nyj = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_NYJ, false);
        productInfo.setSamplePrice(nyj);
        if (nyj != null && nyj.doubleValue() > 0)
        {
            productInfo.setIsSample(true);
        }
        return productInfo;
    }
    
    private ProductFormBean getProductParamsThree(Map<String, ExcelCellValue> piData, ProductInfoUploadDataModel dataModel) throws BusinessException
    {
        ProductFormBean productInfo = new ProductFormBean();
        productInfo.setProductCate(ProductConsts.CATE_STOCK.getKey());
        productInfo.setStateSet(String.valueOf(ProductConsts.BEGIN_TYPE_FIRST.getKey()));
        productInfo.setProductGroom(false);
        // 一层类目，二层类目
        String yclm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_YCLM, true);
        String eclm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_ECLM, true);
        DealDic dealDic = dataModel.getChildDealDic(yclm, eclm);
        productInfo.setDealNo(dealDic.getDealNo().intValue());
        // 品牌
        String ppxz = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_PPXZ, true);
        BrandesInfo brandesInfo = dataModel.getBrandesInfo(ppxz);
        productInfo.setBrandsId(brandesInfo.getRefrenceId());
        // 产品货号
        String cphh = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CPHH, true);
        productInfo.setProductNo(cphh);
        // 产品标题
        // String cpbt = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CPBT, true);
        String cpbt = ppxz + eclm + cphh;
        productInfo.setProductTitle(cpbt);
        // 单位
        // String dw = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_DW, true);
        String dw = "件";
        WebUnit webUnit = dataModel.getWebUnit(dw);
        productInfo.setUnitNo(webUnit.getRefrenceId());
        productInfo.setUnit(webUnit.getUnitName());
        /*
         * // 库存
         * Integer kc = ReadExcelUtils.getIntegerValue(piData, BrandConstant.ProductInfoUpload.HEAD_KC, true);
         * productInfo.setProductStore(kc);
         * // 企业编码
         * String qybh = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_QYBH, false);
         * productInfo.setCompanyCode(qybh);
         * // 条形码
         * String txm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_TXM, false);
         * productInfo.setBarCode(txm);
         */
        // 起批量
        // Integer qpl = ReadExcelUtils.getIntegerValue(piData, BrandConstant.ProductInfoUpload.HEAD_QPL, true);
        Integer qpl = 1;
        productInfo.setStartNum(qpl);
        // 批量说明
        // String plsm = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_PLSM, false);
        // productInfo.setPatchMark(plsm);
        // 主图
        // String zt = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_ZT, true);
        // String[] imageArr = zt.split(",");
        String[] imageArr = new String[]{brandesInfo.getBrandLogo()};
        String[] photoNameArr = new String[imageArr.length];
        for (int i = 0; i < photoNameArr.length; i++)
        {
            if (-1 == imageArr[i].lastIndexOf("/")) { throw new BusinessException(CommonConst.FAIL.code, "主图路径不正确"); }
            photoNameArr[i] = imageArr[i].substring(imageArr[i].lastIndexOf("/"));
        }
        productInfo.setImages(imageArr);
        productInfo.setPhotoName(photoNameArr);
        // 描述
        // String ms = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_MS, true);
        String ms = "1";
        productInfo.setProductMark(ms);
        // 产品分类
        /*
         * String cpfl = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CPFL, false);
         * if (null != cpfl)
         * {
         * List<String> cateIdList = Lists.newArrayList();
         * String[] cpflArr = cpfl.split(",");
         * for (String s : cpflArr)
         * {
         * ProductCatalog productCatalog = dataModel.getProductCatalog(brandesInfo, s);
         * cateIdList.add(productCatalog.getRefrenceId());
         * }
         * productInfo.setCateIds(StringUtils.join(cateIdList, ","));
         * }
         */
        // 重量
        // Double zl = ReadExcelUtils.getDoubleValue(piData, BrandConstant.ProductInfoUpload.HEAD_ZL, true);
        Double zl = dealDic.getProductWeight();
        productInfo.setProductWeight(zl);
        // 承担运费
        String cdyf = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CDYF, true);
        Short productCarry = dataModel.getProductCarry(cdyf);
        productInfo.setProductCarry((int) productCarry);
        if (1 == productCarry)
        {
            // 运费模版
            // String yfmb = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_YFMB, true);
            // BrandFreightTemplate brandFreightTemplate = dataModel.getBrandFreightTemplate(yfmb);
            if (dataModel.getBrandFreightTemplateList() != null && dataModel.getBrandFreightTemplateList().size() > 0)
            {
                productInfo.setFreTemplateId(dataModel.getBrandFreightTemplateList().get(0).getRefrenceId());
            }
            else
            {
                throw new BusinessException(CommonConst.FAIL.code, "运费模板未添加");
            }
        }
        // 退换货承诺
        // String thhcn = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_THHCN, false);
        // productInfo.setProductBear(thhcn);
        /*
         * // 拿样
         * String ny = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_NY, true);
         * Boolean isSample = dataModel.getIsSample(ny);
         * productInfo.setIsSample(isSample);
         */
        // 前台可见
        // String qtkj = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_QTKJ, true);
        // Boolean isShow = dataModel.getIsShow(qtkj);
        productInfo.setIsShow(false);
        // 获取拿样价
        BigDecimal nyj = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_NYJ, false);
        productInfo.setSamplePrice(nyj);
        if (nyj != null && nyj.doubleValue() > 0)
        {
            productInfo.setIsSample(true);
        }
        return productInfo;
    }
    
    private void fillProductSaleParams(ProductFormBean productInfo, List<Map<String, ExcelCellValue>> psaDataList) throws BusinessException
    {
        if (CollectionUtils.isEmpty(psaDataList)) { throw new BusinessException(CommonConst.FAIL.code, "找不到产品对应的颜色尺码信息"); }
        BigDecimal minPrice = null;
        BigDecimal minPrice4zgjg = null;
        Set<String> ysSet = Sets.newLinkedHashSet();
        Set<String> cmSet = Sets.newLinkedHashSet();
        for (Map<String, ExcelCellValue> psaData : psaDataList)
        {
            BigDecimal jg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_DPJ, true);
            BigDecimal zgjg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_ZGJ, true);
            if (null == minPrice || minPrice.compareTo(jg) > 0)
            {
                minPrice = jg;
            }
            if (null == minPrice4zgjg || minPrice4zgjg.compareTo(zgjg) > 0)
            {
                minPrice4zgjg = zgjg;
            }
            String ys = ReadExcelUtils.getStringValue(psaData, BrandConstant.ProductInfoUpload.HEAD_YS, false);
            if (StringUtils.isNotBlank(ys))
            {
                ysSet.add(ys);
            }
            String cm = ReadExcelUtils.getStringValue(psaData, BrandConstant.ProductInfoUpload.HEAD_CM, false);
            if (StringUtils.isNotBlank(cm))
            {
                cmSet.add(cm);
            }
        }
        // 颜色尺码
        List<Integer> slList = Lists.newArrayList();
        List<BigDecimal> jgList = Lists.newArrayList();
        List<BigDecimal> zgjgList = Lists.newArrayList();
        List<BigDecimal> sxjgList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(ysSet))
        {
            // 单尺码属性
            for (String cm : cmSet)
            {
                Map<String, ExcelCellValue> psaData = productInfoUploadService.getPsaDataByCm(psaDataList, cm);
                if (null == psaData)
                {
                    slList.add(0);
                    jgList.add(minPrice);
                    zgjgList.add(minPrice4zgjg);
                    sxjgList.add(new BigDecimal(0));
                }
                else
                {
                    Integer sl = ReadExcelUtils.getIntegerValue(psaData, BrandConstant.ProductInfoUpload.HEAD_SL, true);
                    BigDecimal jg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_DPJ, true);
                    BigDecimal zgjg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_ZGJ, true);
                    BigDecimal sxjg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_SXJ, false);
                    slList.add(sl);
                    jgList.add(jg);
                    zgjgList.add(zgjg);
                    sxjgList.add(sxjg);
                    if (sxjg != null && sxjg.doubleValue() > 0)
                    {
                        productInfo.setCredit(true);
                    }
                }
            }
        }
        else if (CollectionUtils.isEmpty(cmSet))
        {
            // 有颜色和尺码属性
            for (String ys : ysSet)
            {
                Map<String, ExcelCellValue> psaData = productInfoUploadService.getPsaDataByYs(psaDataList, ys);
                if (null == psaData)
                {
                    slList.add(0);
                    jgList.add(minPrice);
                    zgjgList.add(minPrice4zgjg);
                    sxjgList.add(new BigDecimal(0));
                }
                else
                {
                    Integer sl = ReadExcelUtils.getIntegerValue(psaData, BrandConstant.ProductInfoUpload.HEAD_SL, true);
                    BigDecimal jg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_DPJ, true);
                    BigDecimal zgjg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_ZGJ, true);
                    BigDecimal sxjg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_SXJ, false);
                    slList.add(sl);
                    jgList.add(jg);
                    zgjgList.add(zgjg);
                    sxjgList.add(sxjg);
                    if (sxjg != null && sxjg.doubleValue() > 0)
                    {
                        productInfo.setCredit(true);
                    }
                }
            }
        }
        else
        {
            // 单颜色属性
            for (String ys : ysSet)
            {
                for (String cm : cmSet)
                {
                    Map<String, ExcelCellValue> psaData = productInfoUploadService.getPsaData(psaDataList, ys, cm);
                    if (null == psaData)
                    {
                        slList.add(0);
                        jgList.add(minPrice);
                        zgjgList.add(minPrice4zgjg);
                    }
                    else
                    {
                        Integer sl = ReadExcelUtils.getIntegerValue(psaData, BrandConstant.ProductInfoUpload.HEAD_SL, true);
                        BigDecimal jg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_DPJ, true);
                        BigDecimal zgjg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_ZGJ, true);
                        BigDecimal sxjg = ReadExcelUtils.getBigDecimalValue(psaData, BrandConstant.ProductInfoUpload.HEAD_SXJ, false);
                        slList.add(sl);
                        jgList.add(jg);
                        zgjgList.add(zgjg);
                        sxjgList.add(sxjg);
                        if (sxjg != null && sxjg.doubleValue() > 0)
                        {
                            productInfo.setCredit(true);
                        }
                    }
                }
            }
        }
        List<String> ysList = Lists.newArrayList(ysSet);
        List<String> cmList = Lists.newArrayList(cmSet);
        fillProductSaleParams(productInfo, ysList, cmList, jgList, slList, zgjgList, sxjgList);
    }
    
    private void fillProductSaleParamsTwo(ProductFormBean productInfo, Map<String, ExcelCellValue> piData) throws BusinessException
    {
        String ysArrStr = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_YS, false);
        String cmArrStr = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CM, false);
        if (StringUtils.isBlank(ysArrStr) && StringUtils.isBlank(cmArrStr)) { throw new BusinessException(CommonConst.FAIL.code, "找不到产品对应的颜色尺码信息"); }
        Integer sl = ReadExcelUtils.getIntegerValue(piData, BrandConstant.ProductInfoUpload.HEAD_SL, true);
        BigDecimal jg = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_DPJ, true);
        BigDecimal zgjg = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_ZGJ, true);
        BigDecimal sxjg = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_SXJ, false);
        if (sxjg != null && sxjg.doubleValue() > 0)
        {
            productInfo.setCredit(true);
        }
        Set<String> ysSet = Sets.newLinkedHashSet();
        Set<String> cmSet = Sets.newLinkedHashSet();
        if (StringUtils.isNotBlank(ysArrStr))
        {
            String[] ysArr = ysArrStr.split(",");
            for (String ys : ysArr)
            {
                ysSet.add(ys);
            }
        }
        if (StringUtils.isNotBlank(cmArrStr))
        {
            String[] cmArr = cmArrStr.split(",");
            for (String cm : cmArr)
            {
                cmSet.add(cm);
            }
        }
        List<Integer> slList = Lists.newArrayList();
        List<BigDecimal> jgList = Lists.newArrayList();
        List<BigDecimal> zgjgList = Lists.newArrayList();
        List<BigDecimal> sxjgList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(ysSet))
        {
            // 单尺码属性
            for (int i = 0; i < cmSet.size(); i++)
            {
                slList.add(sl);
                jgList.add(jg);
                zgjgList.add(zgjg);
                sxjgList.add(sxjg);
            }
        }
        else if (CollectionUtils.isEmpty(cmSet))
        {
            // 有颜色和尺码属性
            for (int i = 0; i < ysSet.size(); i++)
            {
                slList.add(sl);
                jgList.add(jg);
                zgjgList.add(zgjg);
                sxjgList.add(sxjg);
            }
        }
        else
        {
            // 单颜色属性
            for (int i = 0; i < ysSet.size(); i++)
            {
                for (int j = 0; j < cmSet.size(); j++)
                {
                    slList.add(sl);
                    jgList.add(jg);
                    zgjgList.add(zgjg);
                    sxjgList.add(sxjg);
                }
            }
        }
        List<String> ysList = Lists.newArrayList(ysSet);
        List<String> cmList = Lists.newArrayList(cmSet);
        fillProductSaleParams(productInfo, ysList, cmList, jgList, slList, zgjgList, sxjgList);
    }
    
    /**
     * 组装SKU相关数据
     * @param productInfo
     * @param ysList 颜色
     * @param cmList 尺码
     * @param jgList 品牌价
     * @param slList 库存数量
     * @param zgjgList 直供价
     * @throws BusinessException
     * @author 李星
     */
    private void fillProductSaleParams(ProductFormBean productInfo, List<String> ysList, List<String> cmList, List<BigDecimal> jgList, List<Integer> slList,
            List<BigDecimal> zgjgList, List<BigDecimal> sxjgList) throws BusinessException
    {
        // 产品价格
        BigDecimal productPrice = null;
        for (BigDecimal jg : jgList)
        {
            if (null == productPrice || productPrice.compareTo(jg) > 0)
            {
                productPrice = jg;
            }
        }
        // 产品直供价
        BigDecimal directPrice = null;
        for (BigDecimal jg : zgjgList)
        {
            if (null == directPrice || directPrice.compareTo(jg) > 0)
            {
                directPrice = jg;
            }
        }
        productInfo.setProductPrice(productPrice);
        productInfo.setDirectPrice(directPrice);
        productInfo.setAttr_combo_count(slList.toArray(new Integer[]{}));
        productInfo.setAttr_combo_price(jgList.toArray(new BigDecimal[]{}));
        productInfo.setAttr_combo_direct_price(zgjgList.toArray(new BigDecimal[]{}));
        productInfo.setAttr_combo_credit_price(sxjgList.toArray(new BigDecimal[]{}));
        int ly = ysList.size();
        int lc = cmList.size();
        if (lc == 0)
        {
            lc = 1;
        }
        int len = ly * lc;
        // 颜色属性ID
        java.lang.String[] attr_color_ids = new String[len];
        // 颜色上级属性ID
        java.lang.String[] attr_color_super_ids = new String[len];
        // 颜色属性值
        java.lang.String[] attr_color_values = new String[len];
        // 尺码上级属性ID
        java.lang.String[] attr_size_super_ids = new String[len];
        // 尺码属性ID
        java.lang.String[] attr_size_ids = new String[len];
        // 尺码属性值
        java.lang.String[] attr_size_values = new String[len];
        // 颜色图片ID
        java.lang.String[] attr_colorImage_ids = new String[ly];
        // 颜色图片URL
        java.lang.String[] attr_colorImage_urls = new String[ly];
        // 颜色尺码对象的条码
        java.lang.String[] attr_combo_bar_code = new String[len];
        // List<CateAttribute> attrs = cateAttributeService.findCateAttributesByDealNo((long)productInfo.getDealNo(), true);
        List<CateAttribute> attrs = dealDicServiceDubboConsumer.findCasCadeByDealNo((long) productInfo.getDealNo());
        if (attrs != null && attrs.size() > 1)
        {
            CateAttribute attrYS = attrs.get(0);
            CateAttribute attrCM = attrs.get(1);
            for (CateAttribute attr : attrs)
            {
                if (attr.getIsImgAttr())
                {
                    attrYS = attr;
                }
                else if (BrandConstant.ProductInfoUpload.CATEATTR_SIZE_NAME.equals(attr.getAttributeName()))
                {
                    attrCM = attr;
                }
            }
            List<CateAttributeItem> ysItems = attrYS.getItemList();
            List<CateAttributeItem> cmItems = attrCM.getItemList();
            int size = 0;
            for (int i = 0; i < ysList.size(); i++)
            {
                for (int y = 0; y < lc; y++)
                {
                    attr_color_super_ids[size] = attrYS.getRefrenceId();
                    attr_size_super_ids[size] = attrCM.getRefrenceId();
                    attr_combo_bar_code[size] = "";
                    CateAttributeItem item_ys = ysItems.get(i);
                    attr_color_ids[size] = item_ys.getRefrenceId();
                    attr_color_values[size] = ysList.get(i);
                    if (attr_colorImage_ids[i] == null)
                    {
                        attr_colorImage_ids[i] = item_ys.getRefrenceId();
                        attr_colorImage_urls[i] = item_ys.getAttributeIcon();
                    }
                    CateAttributeItem item_cm = cmItems.get(y);
                    attr_size_ids[size] = item_cm.getRefrenceId();
                    attr_size_values[size] = cmList.get(y);
                    size++;
                }
            }
        }
        else if (attrs != null && attrs.size() == 1)
        {
            CateAttribute attrYS = attrs.get(0);
            for (CateAttribute attr : attrs)
            {
                if (attr.getIsImgAttr())
                {
                    attrYS = attr;
                }
            }
            List<CateAttributeItem> ysItems = attrYS.getItemList();
            int size = 0;
            for (int i = 0; i < ysList.size(); i++)
            {
                for (int y = 0; y < lc; y++)
                {
                    attr_color_super_ids[size] = attrYS.getRefrenceId();
                    attr_size_super_ids[size] = "11111,22222";
                    attr_combo_bar_code[size] = "";
                    CateAttributeItem item_ys = ysItems.get(i);
                    attr_color_ids[size] = item_ys.getRefrenceId();
                    attr_color_values[size] = ysList.get(i);
                    if (attr_colorImage_ids[i] == null)
                    {
                        attr_colorImage_ids[i] = item_ys.getRefrenceId();
                        attr_colorImage_urls[i] = item_ys.getAttributeIcon();
                    }
                    attr_size_ids[size] = "22222";
                    attr_size_values[size] = "默认";
                    size++;
                }
            }
        }
        productInfo.setAttr_color_ids(attr_color_ids);
        productInfo.setAttr_color_super_ids(attr_color_super_ids);
        productInfo.setAttr_color_values(attr_color_values);
        productInfo.setAttr_colorImage_ids(attr_colorImage_ids);
        productInfo.setAttr_colorImage_urls(attr_colorImage_urls);
        productInfo.setAttr_size_ids(attr_size_ids);
        productInfo.setAttr_size_super_ids(attr_size_super_ids);
        productInfo.setAttr_size_values(attr_size_values);
        productInfo.setAttr_combo_bar_code(attr_combo_bar_code);
    }
    
    private synchronized void importProduct(ProductFormBean productInfo, HttpServletRequest request, Model model) throws BusinessException
    {
        // 是否货号已经存在
        checkIsSameProductNo(request, productInfo);
        productInfo.setBrandId(OnLineUserUtils.getCurrentBrand().getRefrenceId());
        ProductBaseInfoModel baseInfo = ProductInfoHelper.getInstance().getProductBaseInfo(productInfo);
        baseInfo.setBrandsName(brandesInfoService.selectByPrimaryKey(baseInfo.getBrandsId()).getBrandsName());
        ProductBaseInfo productBaseInfo = productInfoDubboConsumer.saveOrUpdateForTrade(baseInfo);
        ProductCount productCount = productCountService.selectByPrimaryKey(productBaseInfo.getRefrenceId());
        if (productCount != null)
        {
            productCount.setBrandsId(productBaseInfo.getBrandsId());
            productCountService.updateByPrimaryKeySelective(productCount);
        }
        else
        {
            productCount = new ProductCount(productBaseInfo.getRefrenceId(), productBaseInfo.getBrandId(), productBaseInfo.getBrandsId(), 0, 0, 0,
                    CalendarUtils.getCurrentTime(), 0);
            productCountService.insert(productCount);
        }
        ProductWeightInfo weightInfo = new ProductWeightInfo();
        weightInfo.setRefrenceId(productBaseInfo.getRefrenceId());
        weightInfo.setBrandId(productBaseInfo.getBrandId());
        weightInfo.setBrandsId(productBaseInfo.getBrandsId());
        weightInfo.setCreateTime(CalendarUtils.getCurrentTime());
        weightInfo.setDelFlag(false);
        weightInfo.setWeight(1);
        weightInfo.setSeason(getSeason(weightInfo.getCreateTime()));
        productWeightInfoService.insert(weightInfo);
    }
    
    /**
     * 验证产品货号是否重复
     * @param request
     * @param productInfo
     * @throws com.zttx.sdk.exception.BusinessException
     */
    public void checkIsSameProductNo(HttpServletRequest request, ProductFormBean productInfo) throws BusinessException
    {
        boolean flag = productInfoDubboConsumer.isSameProductNo(OnLineUserUtils.getCurrentBrand().getRefrenceId(), productInfo.getBrandsId(), productInfo.getProductNo(),
                productInfo.getRefrenceId());
        if (flag) { throw new BusinessException("产品货号重复"); }
    }
    
    private JsonMessage importProduct(Workbook workbook, HttpServletRequest request, Model model, ProductInfoUploadDataModel dataModel) throws BusinessException
    {
        List<Map<String, ExcelCellValue>> piDataList = ReadExcelUtils.getDataList(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_INFO);
        List<Map<String, ExcelCellValue>> psaAllDataList = ReadExcelUtils.getDataList(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_SALE_ATTR);
        // 导入产品数据
        String errorFilePath = null;
        List<String> infoList = Lists.newArrayList();
        List<Map<String, ExcelCellValue>> errPiDataList = Lists.newArrayList();
        List<Map<String, ExcelCellValue>> errPsaAllDataList = Lists.newArrayList();
        Integer errIndex = 0;
        RLock lock = RedissonUtils.getInstance().getLock(String.valueOf(piDataList.hashCode()));
        try
        {
            lock.lock();
            for (int i = 0; i < piDataList.size(); i++)
            {
                Map<String, ExcelCellValue> piData = piDataList.get(i);
                Integer rowIndex = ReadExcelUtils.getIntegerValue(piData, "_ROW_INDEX", true);
                List<Map<String, ExcelCellValue>> psaDataList = productInfoUploadService.getPsaDataList(rowIndex, psaAllDataList);
                try
                {
                    ProductFormBean productInfo = getProductParams(piData, dataModel);
                    /*
                     * BigDecimal nyjg = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_NYJ, false);
                     * productInfo.setSamplePrice(nyjg);
                     * if(nyjg!=null&&nyjg.doubleValue()>0){
                     * productInfo.setIsSample(true);
                     * }
                     */
                    fillProductSaleParams(productInfo, psaDataList);
                    importProduct(productInfo, request, model);
                }
                catch (BusinessException e)
                {
                    String message = e.getMessage();
                    if (null != e.getObject())
                    {
                        message += ": " + e.getObject().toString();
                    }
                    productInfoUploadService.setErrorInfo(piData, psaDataList, ++errIndex, message);
                    errPiDataList.add(piData);
                    errPsaAllDataList.addAll(psaDataList);
                }
                catch (Exception e)
                {
                    String message = "数据格式有问题";
                    productInfoUploadService.setErrorInfo(piData, psaDataList, ++errIndex, message);
                    errPiDataList.add(piData);
                    errPsaAllDataList.addAll(psaDataList);
                    logger.error("导入的数据:{}, 出错:{}", piData, e);
                }
            }
            infoList.add("成功导入：" + (piDataList.size() - errPiDataList.size()) + "条");
            infoList.add("错误记录：" + errPiDataList.size() + "条");
            if (CollectionUtils.isNotEmpty(errPiDataList))
            {
                errorFilePath = productInfoUploadService.createExcelFile(request, dataModel, errPiDataList, errPsaAllDataList, true, "错误文件");
            }
        }
        catch (Exception e)
        {
            logger.error("批量上传产品失败", e.getLocalizedMessage());
        }
        finally
        {
            lock.unlock();
        }
        JsonMessage resultMessage = getJsonMessage(CommonConst.SUCCESS, errorFilePath);
        resultMessage.setRows(infoList);
        return resultMessage;
    }
    
    private JsonMessage importProductTwo(Workbook workbook, HttpServletRequest request, Model model, ProductInfoUploadDataModel dataModel) throws BusinessException
    {
        List<Map<String, ExcelCellValue>> piDataList = ReadExcelUtils.getDataList(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_INFO);
        // 导入产品数据
        Integer errIndex = 0;
        String errorFilePath = null;
        List<String> infoList = Lists.newArrayList();
        List<Map<String, ExcelCellValue>> errPiDataList = Lists.newArrayList();
        RLock lock = RedissonUtils.getInstance().getLock(String.valueOf(piDataList.hashCode()));
        try
        {
            lock.lock();
            for (int i = 0; i < piDataList.size(); i++)
            {
                Map<String, ExcelCellValue> piData = piDataList.get(i);
                try
                {
                    ProductFormBean productInfo = getProductParams(piData, dataModel);
                    fillProductSaleParamsTwo(productInfo, piData);
                    importProduct(productInfo, request, model);
                }
                catch (BusinessException e)
                {
                    String message = e.getMessage();
                    if (null != e.getObject())
                    {
                        message += ": " + e.getObject().toString();
                    }
                    productInfoUploadService.setErrorInfo(piData, null, ++errIndex, message);
                    errPiDataList.add(piData);
                }
                catch (Exception e)
                {
                    String message = "数据格式有问题";
                    productInfoUploadService.setErrorInfo(piData, null, ++errIndex, message);
                    errPiDataList.add(piData);
                    logger.error("导入的数据:{}, 出错:{}", piData, e);
                }
            }
            infoList.add("成功导入：" + (piDataList.size() - errPiDataList.size()) + "条");
            infoList.add("错误记录：" + errPiDataList.size() + "条");
            if (CollectionUtils.isNotEmpty(errPiDataList))
            {
                errorFilePath = productInfoUploadService.createExcelFileTwo(request, dataModel, errPiDataList, true, "错误文件");
            }
        }
        catch (Exception e)
        {
            logger.error("批量上传产品失败", e.getLocalizedMessage());
        }
        finally
        {
            lock.unlock();
        }
        JsonMessage resultMessage = getJsonMessage(CommonConst.SUCCESS, errorFilePath);
        resultMessage.setRows(infoList);
        return resultMessage;
    }
    
    private JsonMessage importProductThree(Workbook workbook, HttpServletRequest request, Model model, ProductInfoUploadDataModel dataModel) throws BusinessException
    {
        List<Map<String, ExcelCellValue>> piDataList = ReadExcelUtils.getDataList(workbook, BrandConstant.ProductInfoUpload.SHEET_NAME_PRODUCT_INFO_SIMPLE);
        // 导入产品数据
        Integer errIndex = 0;
        String errorFilePath = null;
        List<String> infoList = Lists.newArrayList();
        List<Map<String, ExcelCellValue>> errPiDataList = Lists.newArrayList();
        RLock lock = RedissonUtils.getInstance().getLock(String.valueOf(piDataList.hashCode()));
        try
        {
            lock.lock();
            for (int i = 0; i < piDataList.size(); i++)
            {
                Map<String, ExcelCellValue> piData = piDataList.get(i);
                try
                {
                    ProductFormBean productInfo = getProductParamsThree(piData, dataModel);
                    fillProductSaleParamsThree(productInfo, piData);
                    importProduct(productInfo, request, model);
                }
                catch (BusinessException e)
                {
                    String message = e.getMessage();
                    if (null != e.getObject())
                    {
                        message += ": " + e.getObject().toString();
                    }
                    productInfoUploadService.setErrorInfo(piData, null, ++errIndex, message);
                    errPiDataList.add(piData);
                }
                catch (Exception e)
                {
                    String message = "数据格式有问题";
                    productInfoUploadService.setErrorInfo(piData, null, ++errIndex, message);
                    errPiDataList.add(piData);
                    logger.error("导入的数据:{}, 出错:{}", piData, e);
                }
            }
            infoList.add("成功导入：" + (piDataList.size() - errPiDataList.size()) + "条");
            infoList.add("错误记录：" + errPiDataList.size() + "条");
            if (CollectionUtils.isNotEmpty(errPiDataList))
            {
                errorFilePath = productInfoUploadService.createExcelFileThree(request, dataModel, errPiDataList, true, "错误文件");
            }
        }
        catch (Exception e)
        {
            logger.error("批量上传产品失败", e.getLocalizedMessage());
        }
        finally
        {
            lock.unlock();
        }
        JsonMessage resultMessage = getJsonMessage(CommonConst.SUCCESS, errorFilePath);
        resultMessage.setRows(infoList);
        return resultMessage;
    }
    
    private void fillProductSaleParamsThree(ProductFormBean productInfo, Map<String, ExcelCellValue> piData) throws BusinessException
    {
        String ysArrStr = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_YS, true);
        String cmArrStr = ReadExcelUtils.getStringValue(piData, BrandConstant.ProductInfoUpload.HEAD_CM, true);
        if (StringUtils.isBlank(ysArrStr) && StringUtils.isBlank(cmArrStr)) { throw new BusinessException(CommonConst.FAIL.code, "找不到产品对应的颜色尺码信息"); }
        Integer sl = ReadExcelUtils.getIntegerValue(piData, BrandConstant.ProductInfoUpload.HEAD_SL, true);
        BigDecimal jg = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_DPJ, true);
        BigDecimal zgjg = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_ZGJ, true);
        BigDecimal sxjg = ReadExcelUtils.getBigDecimalValue(piData, BrandConstant.ProductInfoUpload.HEAD_SXJ, false);
        if (sxjg != null && sxjg.doubleValue() > 0)
        {
            productInfo.setCredit(true);
        }
        Set<String> ysSet = Sets.newLinkedHashSet();
        Set<String> cmSet = Sets.newLinkedHashSet();
        if (StringUtils.isNotBlank(ysArrStr))
        {
            String[] ysArr = ysArrStr.split(",");
            for (String ys : ysArr)
            {
                ysSet.add(ys);
            }
        }
        if (StringUtils.isNotBlank(cmArrStr))
        {
            String[] cmArr = cmArrStr.split(",");
            for (String cm : cmArr)
            {
                cmSet.add(cm);
            }
        }
        List<Integer> slList = Lists.newArrayList();
        List<BigDecimal> jgList = Lists.newArrayList();
        List<BigDecimal> zgjgList = Lists.newArrayList();
        List<BigDecimal> sxjgList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(ysSet))
        {
            // 单尺码属性
            for (int i = 0; i < cmSet.size(); i++)
            {
                slList.add(sl);
                jgList.add(jg);
                zgjgList.add(zgjg);
                sxjgList.add(sxjg);
            }
        }
        else if (CollectionUtils.isEmpty(cmSet))
        {
            // 有颜色和尺码属性
            for (int i = 0; i < ysSet.size(); i++)
            {
                slList.add(sl);
                jgList.add(jg);
                zgjgList.add(zgjg);
                sxjgList.add(sxjg);
            }
        }
        else
        {
            // 单颜色属性
            for (int i = 0; i < ysSet.size(); i++)
            {
                for (int j = 0; j < cmSet.size(); j++)
                {
                    slList.add(sl);
                    jgList.add(jg);
                    zgjgList.add(zgjg);
                    sxjgList.add(sxjg);
                }
            }
        }
        List<String> ysList = Lists.newArrayList(ysSet);
        List<String> cmList = Lists.newArrayList(cmSet);
        fillProductSaleParams(productInfo, ysList, cmList, jgList, slList, zgjgList, sxjgList);
    }
}
