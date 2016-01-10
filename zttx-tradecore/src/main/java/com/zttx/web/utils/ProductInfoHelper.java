package com.zttx.web.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.zttx.goods.module.dto.ProductBaseInfoModel;
import com.zttx.goods.module.dto.ProductFormBean;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductBaseInfo;
import com.zttx.goods.module.entity.ProductExtInfo;
import com.zttx.goods.module.entity.ProductSku;
import com.zttx.goods.module.entity.ProductSkuBarcode;
import com.zttx.goods.module.entity.ProductSkuPrice;
import com.zttx.sdk.consts.ZttxConst;
import com.zttx.web.consts.BrandConstant;
import com.zttx.web.consts.ProductConsts;

/**
 * <p>File：产品信息对象转换器.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 15-5-28</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class ProductInfoHelper
{
    private volatile static ProductInfoHelper instance;
    
    /**
     * 单例模式
     *
     * @return
     */
    public static ProductInfoHelper getInstance()
    {
        if (instance == null)
        {
            synchronized (ProductInfoHelper.class)
            {
                if (instance == null)
                {
                    instance = new ProductInfoHelper();
                }
            }
        }
        return instance;
    }
    
    /**
     * 添加SKU相关信息
     *
     * @param baseInfo
     * @param formBean
     */
    void addSkuInfo(ProductBaseInfo baseInfo, ProductFormBean formBean)
    {
        boolean colorIds = null != formBean.getAttr_color_ids() && formBean.getAttr_color_ids().length > 0;
        boolean sizeIds = null != formBean.getAttr_size_ids() && formBean.getAttr_size_ids().length > 0;
        List<ProductSku> productSkus = Lists.newArrayList();
        if (colorIds && sizeIds)
        {// 表示有SKU相关信息,包含颜色和尺码
            Integer quantity = 0;
            for (int i = 0; i < formBean.getAttr_color_ids().length; i++)
            {
                String colorId = formBean.getAttr_color_ids()[i];
                String colorValue = formBean.getAttr_color_values()[i];
                String sizeId = formBean.getAttr_size_ids()[i];
                String sizeValue = formBean.getAttr_size_values()[i];
                BigDecimal skuPrice = formBean.getAttr_combo_price()[i];
                BigDecimal skuDirectPrice = formBean.getAttr_combo_direct_price()[i];
                quantity += formBean.getAttr_combo_count()[i];
                BigDecimal creditPrice=null;
                if(formBean.getAttr_combo_credit_price()!=null&&formBean.getAttr_combo_credit_price().length>0){
                    creditPrice=formBean.getAttr_combo_credit_price()[i];
                }
                BigDecimal pointPrice=null;
                if(formBean.getAttr_combo_rebate_price()!=null&&formBean.getAttr_combo_rebate_price().length>0){
                    pointPrice=formBean.getAttr_combo_rebate_price()[i];
                }
                // 建立SKU对象
                ProductSku productSku = new ProductSku();
                productSku.setProductId(baseInfo.getRefrenceId());
                productSku.setQuantity(formBean.getAttr_combo_count()[i]);
                productSku.setAttrItemIds(colorId + ZttxConst.SEPARATOR + sizeId);
                productSku.setProductSkuName(colorValue + ZttxConst.SEPARATOR + sizeValue);
                productSku.setSaleUnit(baseInfo.getUnit());
                productSku.setBrandsId(baseInfo.getBrandsId());
                productSku.setCreateTime(baseInfo.getCreateTime());
                productSku.setUpdateTime(baseInfo.getUpdateTime());
                productSku.setDelFlag(Boolean.FALSE);
                // 加入价格信息
                ProductSkuPrice productSkuPrice = new ProductSkuPrice();
                productSkuPrice.setProductId(baseInfo.getRefrenceId());
                productSkuPrice.setProductSkuId(productSku.getRefrenceId());
                productSkuPrice.setSkuPrice(skuPrice);
                productSkuPrice.setDirectPrice(skuDirectPrice);
                productSkuPrice.setCreditPrice(creditPrice);
                productSkuPrice.setPointPrice(pointPrice);
                productSkuPrice.setCreateTime(baseInfo.getCreateTime());
                productSkuPrice.setSamplePrice(formBean.getSamplePrice());
                productSku.setProductSkuPrice(productSkuPrice);
                // 加入SKU属性信息
                List<ProductAttrValue> attrValues = productSku.getAttrValueList();
                if(attrValues==null){
                    attrValues=Lists.newArrayList();
                }
                // 加入颜色属性
                ProductAttrValue attrValue = new ProductAttrValue();
                attrValue.setAttributeItemId(colorId);
                attrValue.setProductId(baseInfo.getRefrenceId());
                attrValue.setExtAttributeValue(colorValue);
                attrValue.setAttributeId(formBean.getAttr_color_super_ids()[i]);
                attrValue.setSkuAttr(Boolean.TRUE);
                attrValue.setSortOrder(01);
                attrValues.add(attrValue);
                // 加入尺码属性
                attrValue = new ProductAttrValue();
                attrValue.setAttributeItemId(sizeId);
                attrValue.setProductId(baseInfo.getRefrenceId());
                attrValue.setAttributeId(formBean.getAttr_size_super_ids()[i]);
                attrValue.setExtAttributeValue(sizeValue);
                attrValue.setSkuAttr(Boolean.TRUE);
                attrValue.setSortOrder(02);
                attrValues.add(attrValue);
                productSku.setAttrValueList(attrValues);
                // 加入sku企业条码属性
                if (formBean.getAttr_combo_bar_code() != null && formBean.getAttr_combo_bar_code().length >= (i + 1))
                {
                    ProductSkuBarcode skuBarCode = new ProductSkuBarcode();
                    skuBarCode.setBarCodeValue(formBean.getAttr_combo_bar_code()[i]);
                    skuBarCode.setBarCodeType(BrandConstant.SKU_BARCODE_TYPE_COMP);// 1:企业自己添加的条形码
                    List<ProductSkuBarcode> productSkuBarcodeList=new ArrayList<ProductSkuBarcode>();
                    productSkuBarcodeList.add(skuBarCode);
                    productSku.setProductSkuBarcodeList(productSkuBarcodeList);
                }
                productSkus.add(productSku);
            }
            baseInfo.getProductExtInfo().setProductStore(quantity);
            baseInfo.setProductSkuList(productSkus);
        }
        else if (colorIds)
        {//只有颜色
            Integer quantity = 0;
            for (int i = 0; i < formBean.getAttr_color_ids().length; i++)
            {
                String colorId = formBean.getAttr_color_ids()[i];
                String colorValue = formBean.getAttr_color_values()[i];
                BigDecimal skuPrice = formBean.getAttr_combo_price()[i];
                BigDecimal skuDirectPrice = formBean.getAttr_combo_direct_price()[i];
                quantity += formBean.getAttr_combo_count()[i];
                BigDecimal creditPrice=null;
                if(formBean.getAttr_combo_credit_price()!=null&&formBean.getAttr_combo_credit_price().length>0){
                    creditPrice=formBean.getAttr_combo_credit_price()[i];
                }
                BigDecimal pointPrice=null;
                if(formBean.getAttr_combo_rebate_price()!=null&&formBean.getAttr_combo_rebate_price().length>0){
                    pointPrice=formBean.getAttr_combo_rebate_price()[i];
                }
                // 建立SKU对象
                ProductSku productSku = new ProductSku();
                productSku.setProductId(baseInfo.getRefrenceId());
                productSku.setQuantity(formBean.getAttr_combo_count()[i]);
                productSku.setAttrItemIds(colorId);
                productSku.setProductSkuName(colorValue);
                productSku.setSaleUnit(baseInfo.getUnit());
                productSku.setBrandsId(baseInfo.getBrandsId());
                productSku.setCreateTime(baseInfo.getCreateTime());
                productSku.setUpdateTime(baseInfo.getUpdateTime());
                productSku.setDelFlag(Boolean.FALSE);
                // 加入价格信息
                ProductSkuPrice productSkuPrice = new ProductSkuPrice();
                productSkuPrice.setProductId(baseInfo.getRefrenceId());
                productSkuPrice.setProductSkuId(productSku.getRefrenceId());
                productSkuPrice.setSkuPrice(skuPrice);
                productSkuPrice.setCreditPrice(creditPrice);
                productSkuPrice.setPointPrice(pointPrice);
                productSkuPrice.setDirectPrice(skuDirectPrice);
                productSkuPrice.setCreateTime(baseInfo.getCreateTime());
                productSkuPrice.setSamplePrice(formBean.getSamplePrice());
                productSku.setProductSkuPrice(productSkuPrice);
                // 加入SKU属性信息
                List<ProductAttrValue> attrValues = productSku.getAttrValueList();
                if(attrValues==null){
                    attrValues=Lists.newArrayList();
                    productSku.setAttrValueList(attrValues);
                }
                // 加入颜色属性
                ProductAttrValue attrValue = new ProductAttrValue();
                attrValue.setAttributeItemId(colorId);
                attrValue.setProductId(baseInfo.getRefrenceId());
                attrValue.setExtAttributeValue(colorValue);
                attrValue.setAttributeId(formBean.getAttr_color_super_ids()[i]);
                attrValue.setSkuAttr(Boolean.TRUE);
                attrValue.setSortOrder(01);
                attrValues.add(attrValue);
                // 加入sku企业条码属性
                if (formBean.getAttr_combo_bar_code() != null && formBean.getAttr_combo_bar_code().length >= (i + 1))
                {
                    ProductSkuBarcode skuBarCode = new ProductSkuBarcode();
                    skuBarCode.setBarCodeValue(formBean.getAttr_combo_bar_code()[i]);
                    skuBarCode.setBarCodeType(BrandConstant.SKU_BARCODE_TYPE_COMP);// 1:企业自己添加的条形码
                    List<ProductSkuBarcode> productSkuBarcodeList=new ArrayList<ProductSkuBarcode>();
                    productSkuBarcodeList.add(skuBarCode);
                    productSku.setProductSkuBarcodeList(productSkuBarcodeList);
                }
                productSkus.add(productSku);
            }
            baseInfo.getProductExtInfo().setProductStore(quantity);
            baseInfo.setProductSkuList(productSkus);
        }
        else if (sizeIds)
        {//只有尺码
            Integer quantity = 0;
            for (int i = 0; i < formBean.getAttr_color_ids().length; i++)
            {
                String sizeId = formBean.getAttr_size_ids()[i];
                String sizeValue = formBean.getAttr_size_values()[i];
                BigDecimal skuPrice = formBean.getAttr_combo_price()[i];
                BigDecimal skuDirectPrice = formBean.getAttr_combo_direct_price()[i];
                quantity += formBean.getAttr_combo_count()[i];
                BigDecimal creditPrice=null;
                if(formBean.getAttr_combo_credit_price()!=null&&formBean.getAttr_combo_credit_price().length>0){
                    creditPrice=formBean.getAttr_combo_credit_price()[i];
                }
                BigDecimal pointPrice=null;
                if(formBean.getAttr_combo_rebate_price()!=null&&formBean.getAttr_combo_rebate_price().length>0){
                    pointPrice=formBean.getAttr_combo_rebate_price()[i];
                }
                // 建立SKU对象
                ProductSku productSku = new ProductSku();
                productSku.setProductId(baseInfo.getRefrenceId());
                productSku.setQuantity(formBean.getAttr_combo_count()[i]);
                productSku.setAttrItemIds(sizeId);
                productSku.setProductSkuName(sizeValue);
                productSku.setSaleUnit(baseInfo.getUnit());
                productSku.setBrandsId(baseInfo.getBrandsId());
                productSku.setCreateTime(baseInfo.getCreateTime());
                productSku.setUpdateTime(baseInfo.getUpdateTime());
                productSku.setDelFlag(Boolean.FALSE);
                // 加入价格信息
                ProductSkuPrice productSkuPrice = new ProductSkuPrice();
                productSkuPrice.setProductId(baseInfo.getRefrenceId());
                productSkuPrice.setProductSkuId(productSku.getRefrenceId());
                productSkuPrice.setSkuPrice(skuPrice);
                productSkuPrice.setCreditPrice(creditPrice);
                productSkuPrice.setPointPrice(pointPrice);
                productSkuPrice.setDirectPrice(skuDirectPrice);
                productSkuPrice.setCreateTime(baseInfo.getCreateTime());
                productSkuPrice.setSamplePrice(formBean.getSamplePrice());
                productSku.setProductSkuPrice(productSkuPrice);
                // 加入SKU属性信息
                List<ProductAttrValue> attrValues = productSku.getAttrValueList();
                if(attrValues==null){
                    attrValues=Lists.newArrayList();
                    productSku.setAttrValueList(attrValues);
                }
                // 加入尺码属性
                ProductAttrValue attrValue = new ProductAttrValue();
                attrValue.setAttributeItemId(sizeId);
                attrValue.setProductId(baseInfo.getRefrenceId());
                attrValue.setAttributeId(formBean.getAttr_size_super_ids()[i]);
                attrValue.setExtAttributeValue(sizeValue);
                attrValue.setSkuAttr(Boolean.TRUE);
                attrValue.setSortOrder(02);
                attrValues.add(attrValue);
                productSku.setAttrValueList(attrValues);
                // 加入sku企业条码属性
                if (formBean.getAttr_combo_bar_code() != null && formBean.getAttr_combo_bar_code().length >= (i + 1))
                {
                    ProductSkuBarcode skuBarCode = new ProductSkuBarcode();
                    skuBarCode.setBarCodeValue(formBean.getAttr_combo_bar_code()[i]);
                    skuBarCode.setBarCodeType(BrandConstant.SKU_BARCODE_TYPE_COMP);// 1:企业自己添加的条形码
                    List<ProductSkuBarcode> productSkuBarcodeList=new ArrayList<ProductSkuBarcode>();
                    productSkuBarcodeList.add(skuBarCode);
                    productSku.setProductSkuBarcodeList(productSkuBarcodeList);
                }
                productSkus.add(productSku);
            }
            baseInfo.getProductExtInfo().setProductStore(quantity);
            baseInfo.setProductSkuList(productSkus);
        }
        else
        {// 表示没有SKU信息，没有的情况下取颜色和尺码的默认值
         // 建立SKU对象
            ProductSku productSku = new ProductSku();
            productSku.setProductId(baseInfo.getRefrenceId());
            productSku.setQuantity(baseInfo.getProductExtInfo().getProductStore());
            productSku.setAttrItemIds(ZttxConst.CateAttributeItem.COLOR_UNDIFINED_ID + ZttxConst.SEPARATOR + ZttxConst.CateAttributeItem.SIZE_UNDIFINED_ID);
            productSku.setProductSkuName(ZttxConst.CateAttributeItem.COLOR_UNDIFINED_VALUE + ZttxConst.SEPARATOR + ZttxConst.CateAttributeItem.SIZE_UNDIFINED_VALUE);
            productSku.setSaleUnit(baseInfo.getUnit());
            productSku.setBrandsId(baseInfo.getBrandsId());
            productSku.setCreateTime(baseInfo.getCreateTime());
            productSku.setUpdateTime(baseInfo.getUpdateTime());
            productSku.setDelFlag(Boolean.FALSE);
            // 加入价格信息
            ProductSkuPrice productSkuPrice = new ProductSkuPrice();
            productSkuPrice.setProductId(baseInfo.getRefrenceId());
            productSkuPrice.setProductSkuId(productSku.getRefrenceId());
            productSkuPrice.setSkuPrice(baseInfo.getProductPrice());
            productSkuPrice.setDirectPrice(baseInfo.getDirectPrice());// 直供价
            productSkuPrice.setCreateTime(baseInfo.getCreateTime());
            productSkuPrice.setSamplePrice(formBean.getSamplePrice());
            productSku.setProductSkuPrice(productSkuPrice);
            // 加入SKU属性信息
            List<ProductAttrValue> attrValues = productSku.getAttrValueList();
            if(attrValues==null){
                attrValues=new ArrayList<ProductAttrValue>();
                productSku.setAttrValueList(attrValues);
            }
            // 加入颜色属性
            ProductAttrValue attrValue = new ProductAttrValue();
            attrValue.setAttributeItemId(ZttxConst.CateAttributeItem.COLOR_UNDIFINED_ID);
            attrValue.setProductId(baseInfo.getRefrenceId());
            attrValue.setExtAttributeValue(ZttxConst.CateAttributeItem.COLOR_UNDIFINED_VALUE);
            attrValue.setAttributeId(ZttxConst.CateAttributeItem.NONSKU_ATTRIBUTE_ID);
            attrValue.setSkuAttr(Boolean.TRUE);
            attrValue.setSortOrder(01);
            attrValues.add(attrValue);
            // 加入尺码属性
            attrValue = new ProductAttrValue();
            attrValue.setAttributeItemId(ZttxConst.CateAttributeItem.SIZE_UNDIFINED_ID);
            attrValue.setProductId(baseInfo.getRefrenceId());
            attrValue.setExtAttributeValue(ZttxConst.CateAttributeItem.SIZE_UNDIFINED_VALUE);
            attrValue.setSkuAttr(Boolean.TRUE);
            attrValue.setAttributeId(ZttxConst.CateAttributeItem.NONSKU_ATTRIBUTE_ID);
            attrValue.setSortOrder(02);
            attrValues.add(attrValue);
            productSku.setAttrValueList(attrValues);
            productSkus.add(productSku);
            baseInfo.setProductSkuList(productSkus);
        }
    }
    
    /**
     * 将ProductFormBean转换成ProductBaseInfo对象
     *
     * @param formBean
     * @return
     */
    public ProductBaseInfoModel getProductBaseInfo(ProductFormBean formBean)
    {
        if (formBean == null) return null;
        ProductBaseInfoModel baseInfo = new ProductBaseInfoModel();
        /** 产品基础信息 **/
        baseInfo.setDealNo(formBean.getDealNo());
        baseInfo.setBrandId(formBean.getBrandId());
        baseInfo.setBrandsId(formBean.getBrandsId());
        baseInfo.setBrandsName(formBean.getBrandsName());
        baseInfo.setSource(formBean.getSource());
        baseInfo.setUnitNo(formBean.getUnitNo());
        baseInfo.setUnit(formBean.getUnit());
        baseInfo.setDomainName(formBean.getDomainName());
        baseInfo.setRefrenceId(formBean.getRefrenceId());
        baseInfo.setProductCate(formBean.getProductCate());
        baseInfo.setProductTitle(formBean.getProductTitle());
        baseInfo.setProductNo(formBean.getProductNo());
        baseInfo.setProductKeyword(formBean.getProductKeyword());
        baseInfo.setProductImage(formBean.getProductImage());
        baseInfo.setProductAlias(formBean.getProductAlias());
        baseInfo.setProductPrice(formBean.getProductPrice());
        baseInfo.setDirectPrice(formBean.getDirectPrice());// 直供价
        baseInfo.setProductCarry(formBean.getProductCarry());
        baseInfo.setDownTime(formBean.getDownTime());
        baseInfo.setStateSet(formBean.getStateSet());
        baseInfo.setCreateTime(formBean.getCreateTime());
        baseInfo.setUpdateTime(formBean.getUpdateTime());
        baseInfo.setImages(formBean.getImages());
        baseInfo.setCateIds(formBean.getCateIds());
        baseInfo.setPhotoName(formBean.getPhotoName());
        baseInfo.setLineIdAry(formBean.getLineIdAry());
        if (formBean.getDelFlag() != null)
        {
            baseInfo.setDelFlag(formBean.getDelFlag());
        }
        // 颜色属性和上传的图片信息
        baseInfo.setAttr_colorImage_ids(formBean.getAttr_colorImage_ids());
        baseInfo.setAttr_colorImage_urls(formBean.getAttr_colorImage_urls());
        /** 产品扩展信息 **/
        ProductExtInfo extInfo = baseInfo.getProductExtInfo();
        if(extInfo==null){
            extInfo=new ProductExtInfo();
            baseInfo.setProductExtInfo(extInfo);
        }
        extInfo.setProductBulk(formBean.getProductBulk());
        extInfo.setProductWeight(formBean.getProductWeight());
        extInfo.setTempId(formBean.getTempId());
        extInfo.setOrderName(formBean.getOrderName());
        extInfo.setOrderNum(formBean.getOrderNum());
        extInfo.setDiscount(formBean.isDiscount());
        extInfo.setPointPercent(formBean.getPointPercent());
        extInfo.setPoint(formBean.getPoint());
        extInfo.setPointPercentOther(formBean.getPointPercentOther());
        if (formBean.getOrderStart() != null)
        {
            extInfo.setOrderStart(formBean.getOrderStart());
        }
        else if (org.apache.commons.lang3.StringUtils.isNotBlank(formBean.getOrderStartStr()))
        {
            extInfo.setOrderStart(CalendarUtils.getLongFromTime(formBean.getOrderStartStr(), "yyyy-MM-dd"));
        }
        if (formBean.getOrderEnd() != null)
        {
            extInfo.setOrderEnd(formBean.getOrderEnd());
        }
        else if (org.apache.commons.lang3.StringUtils.isNotBlank(formBean.getOrderEndStr()))
        {
            extInfo.setOrderEnd(CalendarUtils.getLongFromTime(formBean.getOrderEndStr(), "yyyy-MM-dd"));
        }
        extInfo.setStartNum(formBean.getStartNum());
        if (formBean.getOutStart() != null)
        {
            extInfo.setOutStart(formBean.getOutStart());
        }
        else if (org.apache.commons.lang3.StringUtils.isNotBlank(formBean.getOutStartStr()))
        {
            extInfo.setOutStart(CalendarUtils.getLongFromTime(formBean.getOutStartStr(), "yyyy-MM-dd"));
        }
        if (formBean.getOutEnd() != null)
        {
            extInfo.setOutEnd(formBean.getOutEnd());
        }
        else if (org.apache.commons.lang3.StringUtils.isNotBlank(formBean.getOutEndStr()))
        {
            extInfo.setOutEnd(CalendarUtils.getLongFromTime(formBean.getOutEndStr(), "yyyy-MM-dd"));
        }
        extInfo.setOrderSelect(formBean.getOrderSelect());
        extInfo.setOrderMoney(formBean.getOrderMoney());
        extInfo.setProductStore(formBean.getProductStore());
        extInfo.setMinStore(formBean.getMinStore());
        extInfo.setPatchMark(formBean.getPatchMark());
        extInfo.setProductBear(formBean.getProductBear());
        if (formBean.getBeginTime() != null)
        {
            extInfo.setBeginTime(formBean.getBeginTime());
        }
        else if (org.apache.commons.lang3.StringUtils.isNotBlank(formBean.getBeginTimeStr()))
        {
            formBean.setBeginTimeStr(formBean.getBeginTimeStr().replace("+", " "));// 预览时传到后台的时间字符串有+号,类似"yyyy-MM-dd+HH:mm:ss"
            extInfo.setBeginTime(CalendarUtils.getLongFromTime(formBean.getBeginTimeStr(), "yyyy-MM-dd HH:mm:ss"));
        }
        extInfo.setProductGroom(formBean.getProductGroom());
        extInfo.setProductCent(formBean.getProductCent());
        extInfo.setStopState(formBean.getStopState());
        extInfo.setTopTime(formBean.getTopTime());
        extInfo.setSimilarPrice(formBean.getSimilarPrice()!=null?formBean.getSimilarPrice().doubleValue():null);
        extInfo.setActivitySort(formBean.getActivitySort());
        extInfo.setSample(formBean.getIsSample());
        extInfo.setCredit(formBean.getCredit());
        extInfo.setFreTemplateId(formBean.getFreTemplateId());
        extInfo.setBarCode(formBean.getBarCode());
        extInfo.setCompanyCode(formBean.getCompanyCode());
        extInfo.setProductMark(formBean.getProductMark());
        extInfo.setShow(formBean.getIsShow());
        baseInfo.setProductExtInfo(extInfo);
        // 加入SKU信息
        addSkuInfo(baseInfo, formBean);
        
        baseInfo.setProductPrice(calculateMinSkuPrice(baseInfo.getProductSkuList()));
        
        return baseInfo;
    }
    
    public int getSaleState(ProductBaseInfo productInfo)
    {
        /*
         * begintype:3(放入仓库),不能订货 --> 下架 begintype:1(立即发布),如果是现货产品则“可以订货”，如果是预定产品，则根据预定开始时间、结束时间判断 begintype:2(设置时间发布),如果该时间段是发布状态，则同begintype一样的处理方式吴万杰 修改 2014-5-15 13:22
         */
        Long orderStart = productInfo.getProductExtInfo().getOrderStart();
        Long orderEnd = productInfo.getProductExtInfo().getOrderEnd();
        Long beginTime = productInfo.getProductExtInfo().getBeginTime();
        Long now = CalendarUtils.getCurrentLong();
        String beginType = productInfo.getStateSet();
        if (Integer.parseInt(beginType) == ProductConsts.BEGIN_TYPE_STORE.getKey())// 已经下架
        { return ProductConsts.SOLD_OUT.getKey(); }
        boolean isUp = false;// 是否上架状态
        if (Integer.parseInt(beginType) == ProductConsts.BEGIN_TYPE_FIRST.getKey())// 开始时间：立即
        {
            isUp = true;
        }
        else
        {
            // 开始时间：设置时间
            if (null == beginTime || beginTime > now)// 预定未开始
            {
                return ProductConsts.NO_ORDERSTART.getKey();
            }
            else
            {
                isUp = true;
            }
        }
        if (!isUp)
        {
            return ProductConsts.SOLD_OUT.getKey();
        }
        else
        {
            int cate = (int) productInfo.getProductCate();
            // 现货、预定
            if (cate == ProductConsts.CATE_ORDER.getKey())
            {
                // 预订产品
                if (null != orderStart && orderStart > now)// 预定未开始
                {
                    return ProductConsts.NO_ORDERSTART.getKey();
                }
                else if (null != orderEnd && orderEnd < now)// 预定已结束
                {
                    return ProductConsts.ALREADY_ORDEREND.getKey();
                }
                else if (null != orderStart && null != orderEnd && orderStart < now && orderEnd > now)// 可以订货
                {
                    return ProductConsts.CAN_ORDER.getKey();
                }
                else
                {
                    return ProductConsts.SOLD_OUT.getKey();
                }
            }
            return ProductConsts.CAN_ORDER.getKey();
        }
    }
    
    /**
     * 获取最低直供价,没有sku则取ProductBaseInfo的默认直供价directPrice
     * @param productBaseInfo
     * @return
     * @author 李星
     */
    public BigDecimal getLowerSkuPrice(ProductBaseInfo productBaseInfo)
    {
        List<ProductSku> skus = productBaseInfo.getProductSkuList();
        BigDecimal low = null;
        // 默认sku的通用attrItemIds值
        String default_attrItemIds = ZttxConst.CateAttributeItem.COLOR_UNDIFINED_ID + ZttxConst.SEPARATOR + ZttxConst.CateAttributeItem.SIZE_UNDIFINED_ID;
        for (ProductSku sku : skus)
        {
            // 不为空且不是默认sku
            if (sku != null && !default_attrItemIds.equals(sku.getAttrItemIds()))
            {
                if (sku.getProductSkuPrice() != null && sku.getProductSkuPrice().getDirectPrice() != null)
                {
                    if (low == null || sku.getProductSkuPrice().getDirectPrice().compareTo(low) <= 0)
                    {
                        low = sku.getProductSkuPrice().getDirectPrice();
                    }
                }
            }
        }
        if (low == null)
        {
            low = productBaseInfo.getDirectPrice();
        }
        return low;
    }
    
    /**
     * 取sku最小吊牌价
     * @author 章旭楠
     * @param skus
     * @return
     */
    private BigDecimal calculateMinSkuPrice(List<ProductSku> skus)
    {
        BigDecimal minSkuPrice = BigDecimal.ZERO;// 产品吊牌价
        if (skus.size() > 0)
        {
            minSkuPrice = null2Default(((null != skus.get(0).getProductSkuPrice() ? skus.get(0).getProductSkuPrice().getSkuPrice() : BigDecimal.ZERO)), BigDecimal.ZERO);
            for (int i = 1; i < skus.size(); i++)
            {
                ProductSku sku = skus.get(i);
                BigDecimal skuPrice = (null != sku.getProductSkuPrice() ? sku.getProductSkuPrice().getSkuPrice() : BigDecimal.ZERO);
                skuPrice = null2Default(skuPrice, BigDecimal.ZERO);
                if (null != minSkuPrice && skuPrice.compareTo(minSkuPrice) < 0)
                {
                    minSkuPrice = skuPrice;
                }
            }
        }
        return minSkuPrice;
    }
    
    private BigDecimal null2Default(BigDecimal obj, BigDecimal defaultValue)
    {
        return null == obj ? defaultValue : obj;
    }
    
}
