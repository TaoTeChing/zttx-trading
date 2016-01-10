/*
 * @(#)BrandProductController.java 2015-8-14 上午9:54:51
 * Copyright 2015 黄小平, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.brand.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jsoup.helper.StringUtil;
import org.redisson.core.RLock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zttx.goods.consts.ProductConsts;
import com.zttx.goods.module.dto.Attribute;
import com.zttx.goods.module.dto.ProductBaseInfoModel;
import com.zttx.goods.module.dto.ProductFormBean;
import com.zttx.goods.module.dto.ProductSkuDto;
import com.zttx.goods.module.entity.*;
import com.zttx.goods.module.entity.DealDic;
import com.zttx.goods.module.entity.ProductAttrValue;
import com.zttx.goods.module.entity.ProductImage;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.consts.ZttxConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.*;
import com.zttx.web.consts.*;
import com.zttx.web.dubbo.service.*;
import com.zttx.web.module.brand.controller.view.ProductInfoExcelView;
import com.zttx.web.module.brand.entity.*;
import com.zttx.web.module.brand.entity.BrandCatelog;
import com.zttx.web.module.brand.model.BrandFreightParamModel;
import com.zttx.web.module.brand.model.BrandsInfoModel;
import com.zttx.web.module.brand.model.PriceModel;
import com.zttx.web.module.brand.service.*;
import com.zttx.web.module.common.controller.GenericBaseController;
import com.zttx.web.module.common.entity.*;
import com.zttx.web.module.common.model.MenuTree;
import com.zttx.web.module.common.model.ParityModel;
import com.zttx.web.module.common.service.*;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.service.DealerCollectService;
import com.zttx.web.module.dealer.service.DealerJoinService;
import com.zttx.web.module.dealer.service.DealerOrderService;
import com.zttx.web.search.solrj.BrandeSolrHandler;
import com.zttx.web.search.solrj.ProductSolrHandler;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import com.zttx.web.utils.IpUtilsHelper;
import com.zttx.web.utils.ProductInfoHelper;

/**
 * <p>File：BrandProductController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-14 上午9:54:51</p>
 * <p>Company: 8637.com</p>
 * @author 黄小平
 * @version 1.0
 */
@Controller
@RequestMapping("/brand/product")
public class BrandProductController extends GenericBaseController
{
    @Autowired
    private DealDicServiceDubboConsumer      dealDicServiceDubboConsumer;
    
    @Autowired
    private ProductInfoDubboConsumer         productInfoDubboConsumer;
    
    @Autowired
    private ProductSkuInfoDubboConsumer      productSkuInfoDubboConsumer;
    
    @Autowired
    private CateAttributeDubboConsumer       cateAttributeDubboConsumer;
    
    @Autowired
    private ProductImageDubboConsumer        productImageDubboConsumer;
    
    @Autowired
    private WebUnitServiceDubboConsumer      webUnitServiceDubboConsumer;
    
    @Autowired
    private ProductCateService               productCateService;
    
    @Autowired
    private BrandesInfoService               brandesInfoService;
    
    @Autowired
    private BrandInfoService                 brandInfoService;
    
    @Autowired
    private BrandFreightTemplateService      brandFreightTemplateService;
    
    @Autowired
    private ProductCatalogService            productCatalogService;
    
    @Autowired
    private DealerJoinService                dealerJoinService;
    
    @Autowired
    private BrandesInfoRegionalService       brandesInfoRegionalService;
    
    @Autowired
    private BrandTemplateService             brandTemplateService;
    
    @Autowired
    private BrandCountService                brandCountService;
    
    @Autowired
    private DealerOrderService               dealerOrderService;
    
    @Autowired
    private MenuInfoService                  menuInfoService;
    
    @Autowired
    private BrandsCountService               brandsCountService;
    
    @Autowired
    private DealerCollectService             dealerCollectService;
    
    @Autowired
    private BrandCatelogService              brandCatelogService;
    
    @Autowired
    private UserOnlineServiceService         userOnlineServiceService;
    
    @Autowired
    private UserOnlineServiceDetailService   userOnlineServiceDetailService;
    
    @Autowired
    private BrandContactService              brandContactService;
    
    @Autowired
    private BrandLevelService                brandLevelService;
    
    @Autowired
    private ProductCostPriceDubboConsumer    productCostPriceDubboConsumer;
    
    @Autowired
    private ProductEditDetailService         productEditDetailService;
    
    @Autowired
    private ProductParityService             productParityService;
    
    @Autowired
    private ProductSolrHandler               productSolrHandler;
    
    @Autowired
    private ProductInfoService               productInfoService;
    
    @Autowired
    private ProductCountService              productCountService;
    
    @Autowired
    private BrandeSolrHandler                brandeSolrHandler;
    
    @Autowired
    private DataDictValueService             dataDictValueService;
    
    @Autowired
    private ProductWeightInfoService         productWeightInfoService;
    
    @Autowired
    private ProductAdjustDetailService       productAdjustDetailService;
    
    @Autowired
    private DealerRefundDubboServiceConsumer dealerRefundDubboServiceConsumer;
    
    @RequiresPermissions("brand:center")
    @RequestMapping("/create/choose/dealdic")
    public String createProductChooseDealDic(Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<DealDic> dicList = (List<DealDic>) JedisUtils.getObject(RedisConst.DEALDIC_KEY + brandId);
        if (dicList == null)
        {
            JedisUtils.setObject(RedisConst.DEALDIC_KEY + brandId, new ArrayList<DealDic>(), RedisConst.DEALDIC_TIME_7_DAYS);
        }
        model.addAttribute("dicList", dicList);
        model.addAttribute("dealList", JSON.toJSON(dealDicServiceDubboConsumer.findLeversCascade()));
        return "product/edit_productInfo_selectcate";
    }
    
    /**
     * 验证产品货号是否重复
     * @param request
     * @param brandsId
     * @param productNo
     * @param productId
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/isSameProductNo")
    public JsonMessage isSameProductNo(HttpServletRequest request, String brandsId, String productNo, String productId, String copy) throws BusinessException
    {
        if (StringUtils.isBlank(brandsId) || StringUtils.isBlank(productNo)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        if (copy != null && copy.equals("copy"))
        {
            productId = null;
        }
        Boolean result = productInfoDubboConsumer.isSameProductNo(brandId, brandsId, productNo, productId);
        return this.getJsonMessage(CommonConst.SUCCESS, result);
    }
    
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/addnew")
    public String addProduct(HttpServletRequest request, Long dealNo, String id, Model model, String operType) throws BusinessException
    {
        DealDic dic = dealDicServiceDubboConsumer.findByDealNo(dealNo);
        Object obj = JedisUtils.getObject(RedisConst.DEALDIC_KEY + OnLineUserUtils.getCurrentBrand().getRefrenceId());
        if (obj != null)
        {
            List<DealDic> list = (List<DealDic>) obj;
            if (list.size() > RedisConst.DEALDIC_SIZE)
            {
                list = list.subList(list.size() - RedisConst.DEALDIC_SIZE, list.size());
            }
            CopyOnWriteArrayList<DealDic> cpList = new CopyOnWriteArrayList<>(list);
            for (int i = 0; i < cpList.size();)
            {
                if (cpList.get(i).getRefrenceId().equals(dic.getRefrenceId()))
                {
                    cpList.remove(i);
                }
                else
                {
                    i++;
                }
            }
            list = cpList;
            list.add(dic);
            JedisUtils.setObject(RedisConst.DEALDIC_KEY + OnLineUserUtils.getCurrentBrand().getRefrenceId(), list, RedisConst.DEALDIC_TIME_7_DAYS);
        }
        else
        {
            List<DealDic> list = new ArrayList<DealDic>();
            list.add(dic);
            JedisUtils.setObject(RedisConst.DEALDIC_KEY + OnLineUserUtils.getCurrentBrand().getRefrenceId(), list, RedisConst.DEALDIC_TIME_7_DAYS);
        }
        if (null == dic) { return "redirect:" + ApplicationConst.BRAND + "/product/create/choose/dealdic"; }
        ProductBaseInfoModel infoModel = new ProductBaseInfoModel();
        if (StringUtils.isNotBlank(id))
        {
            ProductBaseInfo info = new ProductBaseInfo();
            List<String> ids = new ArrayList<String>();
            ids.add(id);
            ProductBaseInfo product = new ProductBaseInfo();
            product.setBrandId(OnLineUserUtils.getCurrentBrand().getRefrenceId());
            if (productInfoDubboConsumer.findByProductIdsAndProduct(ids, product).getList().size() > 0)
            {
                info = productInfoDubboConsumer.findByProductIdsAndProduct(ids, product).getList().get(0);
            }
            BeanUtils.copyProperties(info, infoModel);
        }
        infoModel.setDealNo(dealNo.intValue());
        infoModel.setOperType(operType);
        return addOrEditProduct(request, infoModel, model, null);
    }
    
    String addOrEditProduct(HttpServletRequest request, ProductBaseInfoModel info, Model model, String copyId) throws BusinessException
    {
        if (info.getProductExtInfo() != null && info.getProductExtInfo().getPointEffTime() != null)
        {
            Date date = new Date(info.getProductExtInfo().getPointEffTime());
            info.getProductExtInfo().setPointEffTimeStr(CalendarUtils.dateFormat(date, "yyyy-MM-dd"));
        }
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        Long dealNo = new Long(info.getDealNo());
        String productId = info.getRefrenceId();
        if (StringUtils.isNotBlank(copyId))
        {
            productId = copyId;
        }
        String dealNames = dealDicServiceDubboConsumer.getFullDealDicNamesBy(dealNo); // 类目信息
        String cateIdsStr = productCateService.getCateIds(productId); // 类别信息
        Long groomUsed = productInfoDubboConsumer.countProductGroom(brandId);
        info.setTmpAttributes(assemblyAttrsJson(info));// 转换临时销售属性对象到JSON
        List<CateAttribute> temp0Attributes = cateAttributeDubboConsumer.findCateAttributesByDealNo(dealNo, true);// 销售属性信息
        List<CateAttribute> temp1Attributes = cateAttributeDubboConsumer.findCateAttributesByDealNo(dealNo, false); // 产品属性信息
        List<CateAttribute> saleAttributes1 = dealDicServiceDubboConsumer.findCasCadeByDealNo(dealNo);
        List<CateAttribute> saleAttributes = cateAttributeDubboConsumer.getSaleAttributes(saleAttributes1, productId);// 标记已选择过的属性
        List<CateAttribute> proAttributes = cateAttributeDubboConsumer.getProAttributes(temp1Attributes, productId); // 产品属性信息
        List<BrandsInfoModel> brandesInfoList = brandesInfoService.list(brandId, BrandConst.BRAND_STATE_JOIN.code.shortValue());
        // String[][] priceArray=null;
        Double samplePrice = null;
        if (info.getRefrenceId() != null)
        {
            List<ProductSkuPrice> priceList = productSkuInfoDubboConsumer.findSkuPriceByProductId(info.getRefrenceId());
            // priceArray=new String[priceList.size()][4];
            for (int i = 0; i < priceList.size(); i++)
            {
                // ProductSku sku=productSkuService.findProductSku(priceList.get(i).getProductSkuId());
                // ProductSkuBarcode barCode=productSkuService.findCompBarCodeBySkuId(sku.getRefrenceId());
                if (i == 0)
                {
                    samplePrice = priceList.get(i).getSamplePrice() == null ? 0 : priceList.get(i).getSamplePrice().doubleValue();
                    break;
                }
                /*
                 * priceArray[i]=new String[]{
                 * priceList.get(i).getSkuPrice()==null?"0":priceList.get(i).getSkuPrice().doubleValue()+"",
                 * sku.getQuantity()==null?"0":sku.getQuantity().intValue()+"",
                 * priceList.get(i).getDirectPrice()==null?"0":priceList.get(i).getDirectPrice()+"",
                 * barCode==null?"":barCode.getBarCodeValue(),
                 * priceList.get(i).getCreditPrice()==null?"0":priceList.get(i).getCreditPrice()+""
                 * };
                 */
            }
        }
        List<BrandFreightTemplate> templateList = brandFreightTemplateService.listTemplate(brandId); // 运费模版
        model.addAttribute("cateIds", cateIdsStr);
        model.addAttribute("dealNames", dealNames);
        model.addAttribute("productInfo", info);
        model.addAttribute("groomUsed", groomUsed);// 已使用的推荐窗口数量
        model.addAttribute("brandesInfoList", brandesInfoList);// 品牌选择
        model.addAttribute("saleAttributes", saleAttributes);// 如果颜色有图片，在下方[1]处设置
        model.addAttribute("proAttributes", proAttributes);
        // model.addAttribute("priceArray", priceArray);
        // request.setAttribute("priceArray", priceArray);
        model.addAttribute("samplePrice", samplePrice);
        model.addAttribute("templateList", templateList);
        model.addAttribute("groomTotal", ProductConsts.GTOOM_TOTAL);// 推荐窗口总数
        // 设置页面显示直供价必填控制
        model.addAttribute("directPriceRequired", true);
        if (temp0Attributes != null && temp0Attributes.size() > 0 && temp0Attributes.get(0) != null)
        {
            if (temp0Attributes.get(0).getItemList() != null && temp0Attributes.get(0).getItemList().size() > 0)
            {
                model.addAttribute("directPriceRequired", false);
            }
        }
        List<ProductImage> imageList = productImageDubboConsumer.findByProductId(productId);
        // 页面属于产品主图的图片
        List<ProductImage> imageList4Main = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(imageList))
        {
            for (ProductImage img : imageList)
            {
                // img.setChangeImagePath(MultipartUtils.getImageDomainPath(img.getImageName(), 160, 160));
                if (StringUtils.isBlank(img.getAttributeItemId()))
                {
                    imageList4Main.add(img);
                }
                else
                {
                    // [1]:产品规格属性中颜色属性有图片的将图片路径设置到saleAttributes.CateAttributeItem.setAttributeIcon属性中，方便页面显示该图片
                    for (CateAttribute attr : saleAttributes)
                    {
                        // 是颜色属性
                        if (attr.getIsImgAttr())
                        {
                            List<CateAttributeItem> items = attr.getItemList();
                            for (CateAttributeItem item : items)
                            {
                                if (img.getAttributeItemId().equals(item.getRefrenceId()))
                                {
                                    item.setAttributeIcon(img.getImageName());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        model.addAttribute("brandAlbums", imageList4Main);
        List<WebUnit> webUnitList = webUnitServiceDubboConsumer.findAll();
        model.addAttribute("webUnitList", webUnitList);
        List<Map<String, Object>> parityModel = productParityService.findParityModel(productId);
        model.addAttribute("parityModel", parityModel);
        return "product/edit_productInfo_addnew";
    }
    
    public String assemblyAttrsJson(ProductBaseInfo baseInfo) throws BusinessException
    {
        if (baseInfo == null) return null;
        List<PriceModel> modelList = Lists.newArrayList();
        if (baseInfo != null && baseInfo.getProductSkuList() != null)
        {
            for (ProductSku productSku : baseInfo.getProductSkuList())
            {
                PriceModel priceModel = new PriceModel();
                ProductSkuBarcode productSkuBarcode = null;
                if (productSku.getProductSkuBarcodeList() != null)
                {
                    for (int i = 0; i < productSku.getProductSkuBarcodeList().size(); i++)
                    {
                        if (productSku.getProductSkuBarcodeList().get(i).getBarCodeValue().equals("1"))
                        {
                            productSkuBarcode = productSku.getProductSkuBarcodeList().get(i);
                            break;
                        }
                    }
                }
                if (productSkuBarcode != null) priceModel.setBc(productSkuBarcode.getBarCodeValue());
                priceModel.setS(productSku.getQuantity());
                ProductSkuPrice productSkuPrice = productSku.getProductSkuPrice();
                if (productSkuPrice != null)
                {
                    priceModel.setP(productSku.getProductSkuPrice().getSkuPrice());
                    priceModel.setDp(productSku.getProductSkuPrice().getDirectPrice());
                    priceModel.setCp(productSku.getProductSkuPrice().getCreditPrice());
                    priceModel.setFd(productSku.getProductSkuPrice().getPointPrice());
                }
                else
                {
                    priceModel.setP(BigDecimal.ZERO);
                    priceModel.setDp(BigDecimal.ZERO);
                    priceModel.setCp(BigDecimal.ZERO);
                    priceModel.setFd(BigDecimal.ZERO);
                }
                if (productSku.getAttrValueList() != null)
                {
                    for (ProductAttrValue productAttrValue : productSku.getAttrValueList())
                    {
                        if (!productAttrValue.getAttributeId().equals("11111,22222"))
                        {
                            Attribute attribute = new Attribute();
                            attribute.setA(productAttrValue.getAttributeId());
                            attribute.setV(productAttrValue.getExtAttributeValue());
                            attribute.setVid(productAttrValue.getAttributeItemId());
                            priceModel.getZ().add(attribute);
                        }
                    }
                }
                modelList.add(priceModel);
            }
        }
        return JSON.toJSONString(modelList, SerializerFeature.DisableCircularReferenceDetect);
    }
    
    /**
     * 产品分类树
     * @param brandesId
     * @param request
     * @return
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping("/catalogTree")
    public Object catalogTree(String brandesId, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<MenuTree> menuTrees = productCatalogService.getCatalogTreeList(brandId, brandesId);
        return menuTrees;
    }
    
    private void setProductBaseInfoDetail(ProductBaseInfoModel productInfo) throws BusinessException
    {
        // 增加brandesState属性,目的用于产品ProductInfo页面状态显示,如果该品牌已经过期,那么该产品就应该显示为下架 yyy
        if (null != productInfo)
        {
            BrandesInfo brandes = brandesInfoService.selectByPrimaryKey(productInfo.getBrandsId());
            productInfo.setBrandesState(brandes.getBrandState().toString());
            productInfo.setProductMark(productInfo.getProductExtInfo().getProductMark());
            // 品类名称
            String[] dealNames = dealDicServiceDubboConsumer.getDealNamesWithParentByDealNo(productInfo.getDealNo() + "");
            productInfo.setDealNames(dealNames);
            // 图片路径
            List<String> imageUrls = Lists.newArrayList();
            String[] images = productInfo.getImages();
            for (int i = 0; i < images.length; i++)
            {
                if (i == 0)
                {
                    productInfo.setProductImage(images[0]);
                }
                imageUrls.add(images[i]);
            }
            productInfo.setImageUrls(imageUrls.toArray(new String[]{}));
        }
    }
    
    private Map<String, Object> getProductSalePrice(ProductBaseInfo productBaseInfo, Integer areaNo, String userId, String code)
    {
        Map<String, Object> productMap = Maps.newHashMap();
        if (null == productBaseInfo) return null;
        DealerJoin dealerJoin = dealerJoinService.findByDealerIdAndBrandsId(userId, productBaseInfo.getBrandsId());
        BrandesInfoRegional brandesInfoRegional = brandesInfoRegionalService.findByBrandsIdAndAreaNo(productBaseInfo.getBrandsId(), areaNo);
        if (null != dealerJoin || null != brandesInfoRegional || (productBaseInfo.getBrandId() == userId)) // 加盟关系 或 区域授权 或 产品品牌商登录
        {
            productMap.put("price", ProductInfoHelper.getInstance().getLowerSkuPrice(productBaseInfo));
            productMap.put("message", "直供价");
        }
        else
        { // 非加盟关系
            ProductExtInfo productExtInfo = productBaseInfo.getProductExtInfo();
            productMap.put("price", productBaseInfo.getProductPrice());
            productMap.put("startNum", productExtInfo.getStartNum());
            productMap.put("freightType", productBaseInfo.getProductCarry()); // 物流类型
            productMap.put("message", null); // 吊牌价,为null页面做判断
        }
        return productMap;
    }
    
    /**
     * 产品详细信息 预览
     *
     * @return
     * @throws BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/previewNew", method = RequestMethod.POST)
    public String preview(ProductFormBean formBean, Model model, HttpServletRequest request, String code) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        formBean.setBrandId(brandId);
        ProductBaseInfoModel productInfo = ProductInfoHelper.getInstance().getProductBaseInfo(formBean);
        productInfo.setBrandsName(brandesInfoService.selectByPrimaryKey(productInfo.getBrandsId()).getBrandsName());
        setProductBaseInfoDetail(productInfo);
        Set<String> colors = new HashSet<String>();
        if (formBean.getAttr_color_values() != null)
        {
            Map<String, List<ProductSku>> skuMap = new TreeMap<String, List<ProductSku>>();
            for (int i = 0; i < formBean.getAttr_color_values().length; i++)
            {
                colors.add(formBean.getAttr_color_values()[i]);
            }
            Iterator<String> it = colors.iterator();
            for (int i = 0; i < colors.size(); i++)
            {
                String color = it.next();
                List<ProductSku> skuList = productInfo.getProductSkuList();
                for (int j = 0; j < skuList.size(); j++)
                {
                    ProductSku sku = skuList.get(j);
                    List<ProductAttrValue> paList = sku.getAttrValueList();
                    boolean has = false;
                    for (int k = 0; k < paList.size(); k++)
                    {
                        if (color.equals(paList.get(k).getExtAttributeValue()))
                        {
                            has = true;
                            break;
                        }
                    }
                    if (has)
                    {
                        List<ProductSku> list = skuMap.get(color);
                        if (list == null)
                        {
                            list = new ArrayList<ProductSku>();
                            skuMap.put(color, list);
                        }
                        list.add(sku);
                    }
                }
            }
            model.addAttribute("skuMap", skuMap);
        }
        // boolean colorIds = null != formBean.getAttr_color_ids() && formBean.getAttr_color_ids().length > 0;
        boolean sizeIds = null != formBean.getAttr_size_ids() && formBean.getAttr_size_ids().length > 0;
        model.addAttribute("sizeIds", sizeIds);
        model.addAttribute("colors", colors);
        model.addAttribute("sample", productInfo.getProductExtInfo().getSample());
        model.addAttribute("samplePrice", formBean.getSamplePrice() == null ? "" : formBean.getSamplePrice());
        model.addAttribute("productInfo", productInfo);
        model.addAttribute("productExtInfo", productInfo.getProductExtInfo());
        String brandesId = productInfo.getBrandsId();
        model.addAttribute("brandesId", brandesId);
        model.addAttribute("brandId", productInfo.getBrandId());
        // 采购价
        model.addAttribute("purchasePrice", productInfo.getProductPrice());
        // 销售状态
        int saleState = ProductInfoHelper.getInstance().getSaleState(productInfo);
        model.addAttribute("saleState", saleState);
        Integer areaNo = IpUtilsHelper.getAreaNo(request, 1);
        String uesrId = productInfo.getBrandId(); // 品牌商登录
        Map<String, Object> productMap = getProductSalePrice(productInfo, areaNo, uesrId, code);
        model.addAttribute("productMap", productMap);
        // 是否登录
        BrandesInfo brandesInfo = brandesInfoService.selectByPrimaryKey(productInfo.getBrandsId());
        model.addAttribute("brandesInfo", brandesInfo);
        BrandInfo brandInfo = brandInfoService.selectByPrimaryKey(productInfo.getBrandId());
        model.addAttribute("brandInfo", brandInfo);
        BrandCatelog entity = new BrandCatelog();
        entity.setBrandId(brandId);
        List<BrandCatelog> brandCatelogList = brandCatelogService.findList(entity);
        List<String> business = new ArrayList<String>();
        for (int i = 0; i < brandCatelogList.size(); i++)
        {
            BrandCatelog catelog = brandCatelogList.get(i);
            String[] dealDicNameAry = dealDicServiceDubboConsumer.getDealNamesWithParentByDealNo(catelog.getDealNo().toString());
            String dealDicName = "";
            for (int j = 0; j < dealDicNameAry.length; j++)
            {
                dealDicName += dealDicNameAry[j] + ",";
            }
            if (dealDicName.length() > 0)
            {
                dealDicName = dealDicName.substring(0, dealDicName.length() - 1);
            }
            business.add(dealDicName);
        }
        model.addAttribute("businesses", business);
        UserOnlineService online = userOnlineServiceService.findUserOnlineService(brandId);
        if (online != null)
        {
            List<UserOnlineServiceDetail> detailList = userOnlineServiceDetailService.getByOnlineService(online.getRefrenceId());
            online.setDetailList(detailList);
        }
        model.addAttribute("online", online);
        BrandContact contact = brandContactService.findByBrandId(brandId);
        model.addAttribute("contact", contact);
        List<Short> brandStates = new ArrayList<Short>();
        brandStates.add(BrandConstant.BrandesInfoConst.BRAND_STATE_COOPERATED);
        List<BrandesInfo> braList = brandesInfoService.listByBrandStates(brandId, brandStates);
        model.addAttribute("braList", braList);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (principal != null && principal.getUserType() == DealerConstant.userType.DEALER_TYPE)
        {
            model.addAttribute("dealerLogon", true);
            DealerJoin join = dealerJoinService.findByDealerIdAndBrandsId(principal.getRefrenceId(), formBean.getBrandsId());
            if (join != null)
            {
                BrandLevel level = brandLevelService.selectByPrimaryKey(join.getLevelId());
                model.addAttribute("join", join);
                model.addAttribute("level", level);
            }
            Map<String, Object> countAndAmount = dealerOrderService.getCountAndAmountByDealerId(principal.getRefrenceId());
            model.addAttribute("countAndAmount", countAndAmount);
        }
        else
        {
            model.addAttribute("dealerLogon", false);
        }
        String attributeJson = assemblyAttrsJson(productInfo);
        if (StringUtils.isNotBlank(attributeJson))
        {
            Map<String, Object> colorMap = Maps.newHashMap();
            Map<String, Object> sizeMap = Maps.newHashMap();
            List<PriceModel> modelList = JSON.parseArray(attributeJson, PriceModel.class);
            for (PriceModel priceModel : modelList)
            {
                List<Attribute> attributes = priceModel.getZ();
                if (!CollectionUtils.isEmpty(attributes))
                {
                    for (Attribute attr : attributes)
                    {
                        String attrId = attr.getA();
                        CateAttribute cateAttr = cateAttributeDubboConsumer.selectByRefrenceId(attrId);
                        if (null != cateAttr)
                        {
                            if (!cateAttr.getIsImgAttr())
                            {
                                sizeMap.put(attr.getVid(), attr);
                            }
                            else
                            {
                                colorMap.put(attr.getVid(), attr);
                            }
                        }
                        else
                        {
                            // 颜色尺码为默认值
                            if (ZttxConst.CateAttributeItem.NONSKU_ATTRIBUTE_ID.equals(attrId))
                            {
                                if (ZttxConst.CateAttributeItem.COLOR_UNDIFINED_ID.equals(attr.getVid()))
                                {
                                    colorMap.put(attr.getVid(), attr);
                                }
                                else if (ZttxConst.CateAttributeItem.SIZE_UNDIFINED_ID.equals(attr.getVid()))
                                {
                                    sizeMap.put(attr.getVid(), attr);
                                }
                            }
                        }
                    }
                }
            }
            model.addAttribute("colorMap", colorMap);
            model.addAttribute("sizeMap", sizeMap);
        }
        model.addAttribute("isView", true);
        model.addAttribute("isAuth", true);
        model.addAttribute("minPrice", productInfo.getProductPrice());
        BrandsCount brandsCount = brandsCountService.findByBrandIdAndBrandsId(brandId, brandesId);
        if (brandsCount.getViewNum() == null)
        {
            brandsCount.setViewNum(0);
        }
        Boolean isCollected = this.isCollected(brandesId, request);
        model.addAttribute("isCollected", isCollected);
        model.addAttribute("brandsCount", brandsCount);
        String emploeeNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_COMPANY_SCOPE, brandInfo.getEmploeeNum().toString());
        String moneyNum = dataDictValueService.findDictValueName(DataDictConstant.BRAND_TURNOVER, brandInfo.getMoneyNum().toString());
        model.addAttribute("emploeeNum", emploeeNum);
        model.addAttribute("moneyNum", moneyNum);
        return "product/view_product";
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping("/isCredit")
    public ProductExtInfo isCredit(String productId) throws BusinessException
    {
        ProductBaseInfo info = productInfoDubboConsumer.findSimpleProductInfo(productId);
        BigDecimal samplePrice = productInfoDubboConsumer.getSamplePriceByProductId(productId);
        ProductExtInfo extInfo = info.getProductExtInfo();
        extInfo.setSamplePrice(samplePrice);
        if (extInfo.getPointEffTime() != null)
        {
            Date date = new Date(extInfo.getPointEffTime());
            extInfo.setPointEffTimeStr(CalendarUtils.dateFormat(date, "yyyy-MM-dd"));
        }
        return extInfo;
    }
    
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping("/set/sample")
    public JsonMessage setSample(BigDecimal[] samplePrice, boolean sample, String productIds) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        String[] productIdAry = productIds.split(",");
        productInfoDubboConsumer.updateProductSample(brandId, productIdAry, sample);
        for (int i = 0; i < productIdAry.length; i++)
        {
            addProductInfo(productIdAry[i]);
        }
        if (sample)
        {
            // productInfoDubboConsumer.updateProductSamplePrice(brandId, productIdAry, samplePrice);
            productInfoDubboConsumer.updateProductSamplePriceBatch(brandId, productIdAry, samplePrice);
        }
        return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
    }
    
    // 判断是否收藏
    private Boolean isCollected(String brandsId, HttpServletRequest request) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (principal == null || principal.getUserType() != 1) { return false; }
        String dealerId = principal.getRefrenceId();
        if (StringUtils.isBlank(dealerId)) { return false; }
        if (StringUtils.isBlank(brandsId)) { throw new BusinessException(CommonConst.PARAM_NULL); }
        Long dealerCollect = this.dealerCollectService.findDealerCollect(dealerId, brandsId, BrandConstant.DEL_STATE_NONE_DELETED);
        if (null == dealerCollect || dealerCollect <= 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    @RequestMapping(value = "/preview/validator")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage previewValidator(ProductBaseInfo info, ModelMap map, HttpServletRequest request)
    {
        return this.getJsonMessage(CommonConst.SUCCESS);
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
    
    /**
     * 保存产品信息
     * @param formBean
     * @param request
     * @param model
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMessage productSave(ProductFormBean formBean, ParityModel parityModel, HttpServletRequest request, Model model, Boolean costPush, String pointEffTimeStr,Boolean send)
            throws BusinessException
    {
        if(send==null){
            send=false;
        }
        ProductBaseInfoModel baseInfo;
        ProductBaseInfo productInfo = null;
        checkIsSameProductNo(request, formBean);
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        if (formBean != null && StringUtils.isNotBlank(brandId) && StringUtils.isNotBlank(formBean.getProductNo()))
        {
            StringBuffer lockKey = new StringBuffer(brandId).append(formBean.getProductNo());
            RLock lock = RedissonUtils.getInstance().getLock(String.valueOf(lockKey.hashCode()));
            try
            {
                lock.lock();
                List<CateAttribute> attributes = cateAttributeDubboConsumer.findCateAttributesByDealNo(new Long(formBean.getDealNo()), false);
                String[] ids = new String[attributes.size()];
                String[] itemIds = new String[attributes.size()];
                for (int i = 0; i < attributes.size(); i++)
                {
                    CateAttribute cateAttribute = attributes.get(i);
                    String itemId = request.getParameter(cateAttribute.getRefrenceId());
                    ids[i] = cateAttribute.getRefrenceId();
                    itemIds[i] = itemId;
                }
                formBean.setBrandId(OnLineUserUtils.getCurrentBrand().getRefrenceId());
                baseInfo = ProductInfoHelper.getInstance().getProductBaseInfo(formBean);
                baseInfo.setCateAttributeIds(ids);
                baseInfo.setCateAttributeItemIds(itemIds);
                baseInfo.setBrandsName(brandesInfoService.selectByPrimaryKey(baseInfo.getBrandsId()).getBrandsName());
                if (StringUtil.isBlank(baseInfo.getUnit()) && baseInfo.getUnitNo() != null)
                {
                    WebUnit wu = webUnitServiceDubboConsumer.getByRefrenceId(String.valueOf(baseInfo.getUnitNo()));
                    baseInfo.setUnit(wu.getUnitName());
                }
                // 返点
                ProductExtInfo extInfo = baseInfo.getProductExtInfo();
                // 如果是第一次设置返点
                if (extInfo.getPoint() == null)
                {
                    extInfo.setPoint(false);
                }
                // 如果不是设置第一次返点
                if (!StringUtils.isEmpty(pointEffTimeStr))
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try
                    {
                        date = sdf.parse(pointEffTimeStr);
                    }
                    catch (ParseException e)
                    {
                    }
                    Date now = CalendarUtils.parseStringToDate(CalendarUtils.getCurrentDate("yyyy-MM-dd"));
                    if (date == null)
                    {
                        date = now;
                        Long dateLong = CalendarUtils.addDay(date, 1);
                        extInfo.setPointEffTime(dateLong);
                    }
                    else
                    {
                        if (CalendarUtils.getDiffOfDay(now, date) >= 1)
                        {
                            Long dateLong = date.getTime();
                            extInfo.setPointEffTime(dateLong);
                        }
                    }
                    if (extInfo.getPointEffTime() == null)
                    {
                        date = now;
                        Long dateLong = CalendarUtils.addDay(date, 1);
                        extInfo.setPointEffTime(dateLong);
                    }
                }
                else
                {
                    Date now = CalendarUtils.parseStringToDate(CalendarUtils.getCurrentDate("yyyy-MM-dd"));
                    Long dateLong = CalendarUtils.addDay(now, 1);
                    extInfo.setPointEffTime(dateLong);
                }
                if (extInfo.getPointPercent().doubleValue() == 2)
                {
                    extInfo.setPointPercent(new BigDecimal(extInfo.getPointPercentOther().intValue() * 1.0 / 100));
                }
                productInfo = productInfoDubboConsumer.saveOrUpdateForTrade(baseInfo);
                extInfo.setRefrenceId(productInfo.getRefrenceId());
                productInfoDubboConsumer.updateProductExtInfoSimple(extInfo);
                if(extInfo.getPoint()){
                    for (ProductSku sku : productInfo.getProductSkuList())
                    {
                        ProductAdjustDetail productAdjustDetail = new ProductAdjustDetail();
                        productAdjustDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                        productAdjustDetail.setBrandId(brandId);
                        productAdjustDetail.setBrandsId(baseInfo.getBrandsId());
                        productAdjustDetail.setProductId(productInfo.getRefrenceId());
                        productAdjustDetail.setProSkuId(sku.getRefrenceId());
                        productAdjustDetail.setDelFlag(false);
                        productAdjustDetail.setCreateTime(CalendarUtils.getCurrentTime());
                        productAdjustDetail.setEffTime(extInfo.getPointEffTime());
                        ProductAdjustDetail last = productAdjustDetailService.findBySkuIdLastDetail(sku.getRefrenceId());
                        if (last == null)
                        {
                            productAdjustDetail.setBeforePercent(0.0);
                            productAdjustDetail.setBeforePrice(0l);
                        }
                        else
                        {
                            productAdjustDetail.setBeforePercent(last.getAfterPercent());
                            productAdjustDetail.setBeforePrice(last.getAfterPrice());
                        }
                        productAdjustDetail.setAfterPercent(extInfo.getPointPercent().doubleValue());
                        productAdjustDetail.setAfterPrice(sku.getProductSkuPrice().getPointPrice().longValue());
                        productAdjustDetailService.insert(productAdjustDetail);
                    }
                }
                
                // 推送到智慧门店
                if (extInfo.getPoint())
                {
                    DealerJoin filter = new DealerJoin();
                    filter.setBrandId(brandId);
                    filter.setBrandsId(productInfo.getBrandsId());
                    filter.setJoinState((short) 1);
                    filter.setPoint(true);
                    List<DealerJoin> dealeJoinrList = dealerJoinService.findDealerJoin(filter);
                    if (dealeJoinrList.size() > 0)
                    {
                        List<String> dealerIdList = new ArrayList<String>();
                        for (int i = 0; i < dealeJoinrList.size(); i++)
                        {
                            dealerIdList.add(dealeJoinrList.get(i).getDealerId());
                        }
                        Map<String, Double> skuPriceMap = new HashMap<String, Double>();
                        for (int i = 0; i < productInfo.getProductSkuList().size(); i++)
                        {
                            skuPriceMap.put(productInfo.getProductSkuList().get(i).getRefrenceId(), productInfo.getProductSkuList().get(i).getProductSkuPrice()
                                    .getPointPrice().doubleValue());
                        }
                        //清楚之前的返点
                        dealerRefundDubboServiceConsumer.removeRefundChange(productInfo.getRefrenceId());
                        //推送返点到智慧门店
                        dealerRefundDubboServiceConsumer.createRefundChange(productInfo.getRefrenceId(), dealerIdList, skuPriceMap,
                                extInfo.getPointPercent().doubleValue(), extInfo.getPointEffTime());
                    }
                }
                else
                {
                    if(!send){
                        dealerRefundDubboServiceConsumer.removeRefundChange(productInfo.getRefrenceId());
                    }
                    
                }
                ProductCount productCount = productCountService.selectByPrimaryKey(productInfo.getRefrenceId());
                if (productCount != null)
                {
                    productCount.setBrandsId(productInfo.getBrandsId());
                    productCountService.updateByPrimaryKeySelective(productCount);
                }
                else
                {
                    productCount = new ProductCount(productInfo.getRefrenceId(), productInfo.getBrandId(), productInfo.getBrandsId(), 0, 0, 0,
                            CalendarUtils.getCurrentTime(), 0);
                    productCountService.insert(productCount);
                }
                ProductWeightInfo weightInfo = productWeightInfoService.selectByPrimaryKey(baseInfo.getRefrenceId());
                // 新增产品权重数据
                if (weightInfo == null)
                {
                    weightInfo = new ProductWeightInfo();
                    weightInfo.setRefrenceId(productInfo.getRefrenceId());
                    weightInfo.setBrandId(productInfo.getBrandId());
                    weightInfo.setBrandsId(productInfo.getBrandsId());
                    weightInfo.setCreateTime(CalendarUtils.getCurrentTime());
                    weightInfo.setDelFlag(false);
                    weightInfo.setWeight(1);
                    weightInfo.setSeason(getSeason(weightInfo.getCreateTime()));
                    productWeightInfoService.insert(weightInfo);
                }
                List<Integer> countTypeList = Lists.newArrayList();
                // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT);
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
                countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
                brandCountService.modifyBrandCount(productInfo.getBrandId(), countTypeList.toArray(new Integer[]{}));
                String[] brandsCountType = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT};
                brandsCountService.modifyBrandsCount(productInfo.getBrandsId(), brandsCountType);
                productCountService.modifyProductCount(productInfo.getBrandId(), productInfo.getBrandsId(), productInfo.getRefrenceId());
                BrandesInfo brandesInfo = new BrandesInfo();
                brandesInfo.setRefrenceId(productInfo.getBrandsId());
                brandesInfo.setBrandId(productInfo.getBrandId());
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
                // solr
                if (Short.valueOf(productInfo.getStateSet()) == ProductConsts.BEGIN_TYPE_FIRST.getKey().shortValue())
                {
                    // 搜索产品到收索引擎
                    addProductInfo(productInfo.getRefrenceId());
                }
                if (!productInfo.getProductExtInfo().getShow())
                {
                    // 如果产品设置为不显示，则从搜索引擎中删除
                    productSolrHandler.removeProductInfo(productInfo.getRefrenceId());
                }
                productParityService.addOrUpdate(parityModel, productInfo.getRefrenceId());
                if (costPush != null && costPush)
                {
                    productCostPriceDubboConsumer.pushCostPriceChanges(productInfo.getProductSkuList());
                    // 调价后调整未付款,未发货的订单项数据
                    dealerOrderService.updateCostPriceByProductIdAndSkuList(productInfo.getProductSkuList());
                }
            }
            catch (BusinessException e)
            {
                return this.getJsonMessage(e.getErrorCode());
            }
            finally
            {
                lock.unlock();
            }
            return this.getJsonMessage(CommonConst.SUCCESS, productInfo.getRefrenceId());
        }
        else
        {
            return this.getJsonMessage(com.zttx.sdk.consts.CommonConst.FAIL);
        }
    }
    
    private void addProductInfo(String productId) throws BusinessException
    {
        if (StringUtils.isBlank(productId)) { throw new BusinessException("产品编号不可为空！"); }
        ProductInfo filter = new ProductInfo();
        filter.setRefrenceId(productId);
        List<ProductInfo> productInfos = productInfoService.findProductToSolr(filter, null);
        for (ProductInfo productInfo : productInfos)
        {
            productSolrHandler.addProducInfo(productInfo);
            break;
        }
    }
    
    /**
     * 根据品牌id获取品牌商的预定模版
     * @param request
     * @param brandsId
     * @return {@link java.util.List<com.zttx.web.module.brand.entity.BrandTemplate>}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping("/brandTemplate")
    public List<BrandTemplate> getBrandTemplates(HttpServletRequest request, String brandsId) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        return brandTemplateService.listBrandTemplate(brandId, brandsId);
    }
    
    /**
     * 取运费模版信息
     * @param request
     * @param templateId
     * @return {@link com.zttx.sdk.bean.JsonMessage}
     * @throws BusinessException
     */
    @ResponseBody
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/selTemplate", method = RequestMethod.POST)
    public JsonMessage selTemplate(HttpServletRequest request, String templateId) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        BrandFreightParamModel paramModel = brandFreightTemplateService.getTempalateAndDetailData(templateId, brandId);
        return this.getJsonMessage(CommonConst.SUCCESS, paramModel);
    }
    
    /**
     * 成功页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/success/{productId}")
    public String add_success(Model model, @PathVariable String productId, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<ProductBaseInfo> productList = productInfoDubboConsumer.findByBrandIdAndProductId(brandId, productId);
        ProductBaseInfo product = null;
        if (productList != null && productList.size() > 0)
        {
            product = productList.get(0);
        }
        model.addAttribute("product", product);
        return "product/edit_productInfo_success";
    }
    
    /**
     * 产品列表页
     * @return
     * @author 吴万杰
     * @throws BusinessException 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/list")
    public String productList(HttpServletRequest request, ProductBaseInfoModel infoModel, Pagination page, Model model, String menuId, String subMenuId)
            throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        if (null != infoModel && StringUtils.isBlank(infoModel.getTabSearch()))
        {
            infoModel.setTabSearch("0");
        }
        if (infoModel.getStoreWarnNum() == null)
        {
            infoModel.setStoreWarnNum(brandInfoService.getBrandMInStore(brandId));
        }
        List<BrandsInfoModel> brandes = brandesInfoService.getCooperatedBrandes(brandId);
        List<DealDic> dealDicList = dealDicServiceDubboConsumer.findFirstLever();
        Long count = productInfoDubboConsumer.countProductGroom(brandId);
        model.addAttribute("brandesList", brandes);// 品牌信息
        model.addAttribute("dealDicList", dealDicList);// 一级类目
        model.addAttribute("infoModel", infoModel);
        model.addAttribute("groomCount", count);
        model.addAttribute("productList_menuId", menuId);
        model.addAttribute("productList_subMenuId", subMenuId);
        return "product/list_productInfo";
    }
    
    /**
     * 品牌商后台产品列表
     * @author chenjp
     * @param request
     * @param infoModel
     * @param page
     * @return
     */
    @RequestMapping(value = "/ajax_list")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage productListJson(HttpServletRequest request, ProductBaseInfoModel infoModel, Pagination page) throws BusinessException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            if (infoModel.getSaleStartTimeStr() != null)
            {
                Date start = sdf.parse(infoModel.getSaleStartTimeStr());
                infoModel.setSaleStartTime(start.getTime());
            }
        }
        catch (ParseException e)
        {
        }
        try
        {
            if (infoModel.getSaleEndTimeStr() != null)
            {
                Date end = sdf.parse(infoModel.getSaleEndTimeStr());
                infoModel.setSaleEndTime(end.getTime());
            }
        }
        catch (ParseException e)
        {
        }
        page.setStartIndex((page.getCurrentPage() - 1) * page.getPageSize());
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        infoModel.setStoreWarnNum(brandInfoService.getBrandMInStore(brandId));
        infoModel.setPage(page);
        infoModel.setBrandId(brandId);
        PaginateResult<Map<String, Object>> pageResult = productInfoDubboConsumer.searchByBaseInfoModelForList(infoModel);
        return this.getJsonMessage(CommonConst.SUCCESS, pageResult, infoModel.getStoreWarnNum());
    }
    
    /**
     * 推荐产品
     *
     * @return
     */
    @RequestMapping(value = "/ajax_groom", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage groom(HttpServletRequest request, String[] productIds) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        try
        {
            productInfoDubboConsumer.updateProductGroom(brandId, productIds, true);
            for (int i = 0; i < productIds.length; i++)
            {
                addProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 取消推荐产品
     *
     * @return
     */
    @RequestMapping(value = "/ajax_ungroom", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage ungroom(HttpServletRequest request, String[] productIds) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        try
        {
            productInfoDubboConsumer.updateProductGroom(brandId, productIds, false);
            for (int i = 0; i < productIds.length; i++)
            {
                addProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 逻辑删除产品
     * @param productIds 产品id串
     * @author 吴万杰
     * @return
     */
    @RequestMapping(value = "/ajax_delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage deleteProduct(HttpServletRequest request, String[] productIds) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        try
        {
            List<ProductBaseInfo> productInfos = productInfoDubboConsumer.deleteProductInfos(brandId, productIds);
            List<Integer> countTypeList = Lists.newArrayList();
            // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
            brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
            String[] brandsCountType = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT};
            for (int i = 0; i < productInfos.size(); i++)
            {
                brandsCountService.modifyBrandsCount(productInfos.get(i).getBrandsId(), brandsCountType);
                BrandesInfo brandesInfo = new BrandesInfo();
                brandesInfo.setRefrenceId(productInfos.get(i).getBrandsId());
                brandesInfo.setBrandId(productInfos.get(i).getBrandId());
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
            for (int i = 0; i < productIds.length; i++)
            {
                productSolrHandler.removeProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 批量逻辑删除产品
     * @param productIds 产品id串
     * @author 吴万杰
     * @return
     */
    @RequestMapping(value = "/batch_delete", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage batchDeleteProduct(HttpServletRequest request, String[] productIds) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        try
        {
            List<ProductBaseInfo> productInfos = productInfoDubboConsumer.deleteProductInfosReal(brandId, productIds);
            List<Integer> countTypeList = Lists.newArrayList();
            // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
            brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
            String[] brandsCountType = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT};
            for (int i = 0; i < productInfos.size(); i++)
            {
                brandsCountService.modifyBrandsCount(productInfos.get(i).getBrandsId(), brandsCountType);
                BrandesInfo brandesInfo = new BrandesInfo();
                brandesInfo.setRefrenceId(productInfos.get(i).getBrandsId());
                brandesInfo.setBrandId(productInfos.get(i).getBrandId());
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
            for (int i = 0; i < productIds.length; i++)
            {
                productSolrHandler.removeProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 永久删除产品
     * @param productIds 产品id串
     * @author 
     * @return
     */
    @RequestMapping(value = "/ajax_delete_real", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage realDeleteProduct(HttpServletRequest request, String[] productIds) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        try
        {
            List<ProductBaseInfo> productInfos = productInfoDubboConsumer.deleteProductInfosReal(brandId, productIds);
            List<Integer> countTypeList = Lists.newArrayList();
            // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
            brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
            String[] brandsCountType = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT};
            for (int i = 0; i < productInfos.size(); i++)
            {
                brandsCountService.modifyBrandsCount(productInfos.get(i).getBrandsId(), brandsCountType);
                BrandesInfo brandesInfo = new BrandesInfo();
                brandesInfo.setRefrenceId(productInfos.get(i).getBrandsId());
                brandesInfo.setBrandId(productInfos.get(i).getBrandId());
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
            for (int i = 0; i < productIds.length; i++)
            {
                productSolrHandler.removeProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 还原删除状态
     *
     * @param request
     * @param productIds
     * @return
     */
    @RequestMapping(value = "/ajax_return", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage returnProduct(HttpServletRequest request, String[] productIds)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            List<ProductBaseInfo> productInfos = productInfoDubboConsumer.returnProductInfos(brandId, productIds);
            List<Integer> countTypeList = Lists.newArrayList();
            // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
            brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
            String[] brandsCountType = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT};
            for (int i = 0; i < productInfos.size(); i++)
            {
                brandsCountService.modifyBrandsCount(productInfos.get(i).getBrandsId(), brandsCountType);
                productCountService.modifyProductCount(brandId, productInfos.get(i).getBrandsId(), productInfos.get(i).getRefrenceId());
                BrandesInfo brandesInfo = new BrandesInfo();
                brandesInfo.setRefrenceId(productInfos.get(i).getBrandsId());
                brandesInfo.setBrandId(productInfos.get(i).getBrandId());
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
            for (int i = 0; i < productIds.length; i++)
            {
                addProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 产品下架
     *
     * @param request
     * @param productIds
     * @return
     */
    @RequestMapping(value = "/ajax_down", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage productDown(HttpServletRequest request, String[] productIds)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            List<ProductBaseInfo> productInfos = productInfoDubboConsumer.updateProductBeginType(brandId, productIds, ProductConsts.BEGIN_TYPE_STORE.getKey().shortValue());
            List<Integer> countTypeList = Lists.newArrayList();
            // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
            brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
            String[] brandsCountType = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT};
            for (int i = 0; i < productInfos.size(); i++)
            {
                brandsCountService.modifyBrandsCount(productInfos.get(i).getBrandsId(), brandsCountType);
                productCountService.modifyProductCount(brandId, productInfos.get(i).getBrandsId(), productInfos.get(i).getRefrenceId());
                BrandesInfo brandesInfo = new BrandesInfo();
                brandesInfo.setRefrenceId(productInfos.get(i).getBrandsId());
                brandesInfo.setBrandId(productInfos.get(i).getBrandId());
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
            for (int i = 0; i < productIds.length; i++)
            {
                productSolrHandler.removeProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 产品上架
     *
     * @param request
     * @param productIds
     * @return
     */
    @RequestMapping(value = "/ajax_up", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage productUP(HttpServletRequest request, String[] productIds)
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            List<ProductBaseInfo> productInfos = productInfoDubboConsumer.updateProductBeginType(brandId, productIds, ProductConsts.BEGIN_TYPE_FIRST.getKey().shortValue());
            List<Integer> countTypeList = Lists.newArrayList();
            // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_PREPUBLISHEDCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
            countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
            brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
            String[] brandsCountType = new String[]{BrandConstant.BrandsCountConst.BRANDSCOUNT_PRODUCTCOUNT};
            for (int i = 0; i < productInfos.size(); i++)
            {
                brandsCountService.modifyBrandsCount(productInfos.get(i).getBrandsId(), brandsCountType);
                productCountService.modifyProductCount(brandId, productInfos.get(i).getBrandsId(), productInfos.get(i).getRefrenceId());
                BrandesInfo brandesInfo = new BrandesInfo();
                brandesInfo.setRefrenceId(productInfos.get(i).getBrandsId());
                brandesInfo.setBrandId(productInfos.get(i).getBrandId());
                brandeSolrHandler.addBrandsInfoList(brandesInfoService.findBrandesInfoToSolr(brandesInfo, null));
            }
            for (int i = 0; i < productIds.length; i++)
            {
                addProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 保存库存预警值
     *
     * @param request
     * @param storeWarnNum
     * @return
     */
    @RequestMapping(value = "/ajax_saveWarnNum", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage saveStoreWarnNum(HttpServletRequest request, Integer storeWarnNum) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        if (null == storeWarnNum || storeWarnNum < 0) { return this.getJsonMessage(ProductErrorConst.STORE_WARN_NUM_ERROR); }
        productInfoDubboConsumer.saveStoreWarnNum(brandId, storeWarnNum);
        List<Integer> countTypeList = Lists.newArrayList();
        // 已铺货产品数量和未铺货产品数量只需放入一个类型就可以了
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
        brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
        return getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 设置拿样
     * @param request
     * @param productIds
     * @param isSample
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/ajax_sample", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage sample(HttpServletRequest request, String[] productIds, Boolean isSample) throws BusinessException
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            isSample = (null == isSample) ? false : isSample;
            productInfoDubboConsumer.updateProductSample(brandId, productIds, isSample);
            for (int i = 0; i < productIds.length; i++)
            {
                addProductInfo(productIds[i]);
            }
        }
        catch (BusinessException e)
        {
            return this.getJsonMessage(e.getErrorCode());
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 编辑产品
     * @param request
     * @param id
     * @param model
     * @return {@link java.lang.String}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/edit/{id}")
    public String editProduct(HttpServletRequest request, @PathVariable String id, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<ProductBaseInfo> infoList = productInfoDubboConsumer.findByBrandIdAndProductId(brandId, id);
        ProductBaseInfo info = null;
        if (infoList != null && infoList.size() == 1)
        {
            info = infoList.get(0);
        }
        else
        {
            throw new BusinessException(CommonConst.FAIL);
        }
        ProductBaseInfoModel infoModel = new ProductBaseInfoModel();
        BeanUtils.copyProperties(info, infoModel);
        if (info == null) { return "redirect:" + ApplicationConst.BRAND + "/product/execute"; }
        infoModel.setOperType("update");
        // 检查产品是否在有效订单中
        model.addAttribute("hasValidOrder", dealerOrderService.hasValidOrderByProId(info.getRefrenceId()));
        return addOrEditProduct(request, infoModel, model, null);
    }
    
    /**
     * 快捷调价功能
     * @author 章旭楠
     * @param request
     * @param productId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/quickEdit/{productId}")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage quickEdit(HttpServletRequest request, @PathVariable String productId, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        ProductBaseInfo info = productInfoDubboConsumer.findByBrandIdAndProductIdWithSkuAndSkuPriceAndBarCode(brandId, productId);
        if (info == null) { return this.getJsonMessage(CommonConst.DATA_NOT_EXISTS); }
        return this.getJsonMessage(CommonConst.SUCCESS, info);
    }
    
    /**
     * 快捷保存价格
     * @param request
     * @param productSkuForm
     * @param costPush
     * @param credit
     * @param sample
     * @param samplePrice
     * @return
     * @throws BusinessException
     */
    @RequestMapping(value = "/quickEdit/save")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage quickEditSave(HttpServletRequest request, ProductSkuDto productSkuForm, Boolean costPush, boolean credit, boolean sample, BigDecimal samplePrice,
            boolean discount, String pointEffTimeStr, ProductExtInfo extInfo,boolean send) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        for (ProductSku sku : productSkuForm.getProductSkuList())
        {
            sku.getProductSkuPrice().setSamplePrice(samplePrice);
        }
        productInfoDubboConsumer.saveProductSkuList(brandId, productSkuForm.getProductSkuList(), credit, sample, discount);
        if (costPush != null && costPush)
        {
            productCostPriceDubboConsumer.pushCostPriceChanges(productSkuForm.getProductSkuList());
            // 调价后调整未付款,未发货的订单项数据
            dealerOrderService.updateCostPriceByProductIdAndSkuList(productSkuForm.getProductSkuList());
        }
        List<Integer> countTypeList = Lists.newArrayList();
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_TIGHTINVENTORYCOUNT);
        countTypeList.add(BrandConstant.BrandCountConst.BRANDCOUNT_SHORTAGECOUNT);
        brandCountService.modifyBrandCount(brandId, countTypeList.toArray(new Integer[]{}));
        addProductInfo(productSkuForm.getProductSkuList().get(0).getProductId());
        // 返点
        String productId = productSkuForm.getProductSkuList().get(0).getProductId();
        extInfo.setRefrenceId(productId);
        if (extInfo.getPoint() == null)
        {
            extInfo.setPoint(false);
        }
        
        ProductBaseInfo baseInfo = productInfoDubboConsumer.findSimpleProductInfo(productId);
        if(extInfo.getPoint()){
         // 返点时间
            if (!StringUtils.isEmpty(pointEffTimeStr))
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try
                {
                    date = sdf.parse(pointEffTimeStr);
                }
                catch (ParseException e)
                {
                }
                Date now = CalendarUtils.parseStringToDate(CalendarUtils.getCurrentDate("yyyy-MM-dd"));
                if (date == null)
                {
                    date = now;
                    Long dateLong = CalendarUtils.addDay(date, 1);
                    extInfo.setPointEffTime(dateLong);
                }
                else
                {
                    if (CalendarUtils.getDiffOfDay(now,date) >= 1)
                    {
                        Long dateLong = date.getTime();
                        extInfo.setPointEffTime(dateLong);
                    }
                }
                if (extInfo.getPointEffTime() == null)
                {
                    date = now;
                    Long dateLong = CalendarUtils.addDay(date, 1);
                    extInfo.setPointEffTime(dateLong);
                }
            }
            else
            {
                Date now = CalendarUtils.parseStringToDate(CalendarUtils.getCurrentDate("yyyy-MM-dd"));
                Long dateLong = CalendarUtils.addDay(now, 1);
                extInfo.setPointEffTime(dateLong);
            }
            
            if (extInfo.getPointPercent().doubleValue() == 2)
            {
                extInfo.setPointPercent(new BigDecimal(extInfo.getPointPercentOther().intValue() * 1.0 / 100));
            }
            
         // 添加修改记录
            for (ProductSku sku : productSkuForm.getProductSkuList())
            {
                ProductAdjustDetail productAdjustDetail = new ProductAdjustDetail();
                productAdjustDetail.setRefrenceId(SerialnoUtils.buildPrimaryKey());
                productAdjustDetail.setBrandId(brandId);
                productAdjustDetail.setBrandsId(baseInfo.getBrandsId());
                productAdjustDetail.setProductId(productId);
                productAdjustDetail.setProSkuId(sku.getRefrenceId());
                productAdjustDetail.setDelFlag(false);
                productAdjustDetail.setCreateTime(CalendarUtils.getCurrentTime());
                productAdjustDetail.setEffTime(extInfo.getPointEffTime());
                ProductAdjustDetail last = productAdjustDetailService.findBySkuIdLastDetail(sku.getRefrenceId());
                if (last == null)
                {
                    productAdjustDetail.setBeforePercent(0.0);
                    productAdjustDetail.setBeforePrice(0l);
                }
                else
                {
                    productAdjustDetail.setBeforePercent(last.getAfterPercent());
                    productAdjustDetail.setBeforePrice(last.getAfterPrice());
                }
                productAdjustDetail.setAfterPercent(extInfo.getPointPercent().doubleValue());
                productAdjustDetail.setAfterPrice(sku.getProductSkuPrice().getPointPrice().longValue());
                productAdjustDetailService.insert(productAdjustDetail);
            }
            // 更新返点sku
            productSkuInfoDubboConsumer.saveProductSkuPointPriceList(brandId, productSkuForm.getProductSkuList());
        }
        
        productInfoDubboConsumer.updateProductExtInfoSimple(extInfo);
        
        // 推送到智慧门店
        if (extInfo.getPoint())
        {
            DealerJoin filter = new DealerJoin();
            filter.setBrandId(brandId);
            filter.setBrandsId(baseInfo.getBrandsId());
            filter.setJoinState((short) 1);
            filter.setPoint(true);
            List<DealerJoin> dealerJoinList = dealerJoinService.findDealerJoin(filter);
            if (dealerJoinList.size() > 0)
            {
                List<String> dealerIdList = new ArrayList<String>();
                for (int i = 0; i < dealerJoinList.size(); i++)
                {
                    dealerIdList.add(dealerJoinList.get(i).getDealerId());
                }
                Map<String, Double> skuPriceMap = new HashMap<String, Double>();
                for (int i = 0; i < productSkuForm.getProductSkuList().size(); i++)
                {
                    skuPriceMap.put(productSkuForm.getProductSkuList().get(i).getRefrenceId(), productSkuForm.getProductSkuList().get(i).getProductSkuPrice()
                            .getPointPrice().doubleValue());
                }
                //清楚之前返点
                dealerRefundDubboServiceConsumer.removeRefundChange(productId);
                //推送返点信息
                dealerRefundDubboServiceConsumer.createRefundChange(productId, dealerIdList, skuPriceMap, extInfo.getPointPercent().doubleValue(),
                        extInfo.getPointEffTime());
            }
        }
        else
        {
            if(!send){
                dealerRefundDubboServiceConsumer.removeRefundChange(productId);
            }
            
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 复制新增
     * @param request
     * @param id
     * @param model
     * @return {@link java.lang.String}
     * @throws com.zttx.sdk.exception.BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/copy/{id}")
    public String copyAddProduct(HttpServletRequest request, @PathVariable String id, Model model) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<ProductBaseInfo> infoList = productInfoDubboConsumer.findByBrandIdAndProductId(brandId, id);
        ProductBaseInfo info = null;
        if (infoList != null && infoList.size() == 1)
        {
            info = infoList.get(0);
        }
        else
        {
            throw new BusinessException(CommonConst.FAIL);
        }
        ProductBaseInfoModel infoModel = new ProductBaseInfoModel();
        BeanUtils.copyProperties(info, infoModel);
        if (info == null) { return "redirect:" + ApplicationConst.BRAND + "/product/execute"; }
        infoModel.setOperType("copy");
        return addOrEditProduct(request, infoModel, model, id);
    }
    
    /**
     * Excel 导出
     * @param request
     * @param infoModel
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public ModelAndView exportExcel(HttpServletRequest request, ProductBaseInfoModel infoModel) throws BusinessException
    {
        ModelAndView mav = new ModelAndView(new ProductInfoExcelView());
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<Map<String, Object>> list = productInfoDubboConsumer.findProductInfoListByBrandIdAndBaseModelWithPrice(brandId, infoModel);
        mav.addObject("list", list);
        return mav;
    }
    
    /**
     * 设置显示或不现实产品
     * @param request
     * @param productIds 产品id列表
     * @param upTab  上架或下架叶签
     * @param show   前台显示还是不显示
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/setShow")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage setShow(HttpServletRequest request, String[] productIds, boolean upTab, boolean show) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        productInfoDubboConsumer.executeSetShow(productIds, show, brandId);
        for (int i = 0; i < productIds.length; i++)
        {
            if (upTab && show)
            {
                addProductInfo(productIds[i]);
            }
            if (upTab && !show)
            {
                // 如果产品设置为不显示，则从搜索引擎中删除
                productSolrHandler.removeProductInfo(productIds[i]);
            }
        }
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 置顶
     *
     * @return
     * @throws BusinessException
     * @author 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/setTop")
    @ResponseBody
    public JsonMessage setTop(String productId, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        productInfoDubboConsumer.executeSetTop(productId, brandId);
        addProductInfo(productId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 取消置顶
     *
     * @return
     * @author 
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/cancelTop")
    @ResponseBody
    public JsonMessage cancelTop(String productId, HttpServletRequest request) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        productInfoDubboConsumer.executeCancelTop(productId, brandId);
        addProductInfo(productId);
        return this.getJsonMessage(CommonConst.SUCCESS);
    }
    
    /**
     * 回收站
     *
     * @param request
     * @param infoModel
     * @param page
     * @param model
     * @return
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/recycle")
    public String productRecycle(HttpServletRequest request, ProductBaseInfoModel infoModel, Pagination page, Model model) throws BusinessException
    {
        infoModel.setTabSearch(ProductConsts.TABSEARCH_RECYCLE.getKey().toString());
        String result = productList(request, infoModel, page, model, null, null);
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuName("产品列表");
        menuInfo.setMenuLevel((short) 4);
        List<MenuInfo> menuList = menuInfoService.findByMenuInfo(menuInfo);
        if (menuList != null && menuList.size() > 0)
        {
            MenuInfo menu = menuList.get(0);
            MenuInfo topmenu = menuInfoService.selectByPrimaryKey(menu.getUpMenuId());
            model.addAttribute("productList_menuId", topmenu.getUpMenuId());
            model.addAttribute("productList_subMenuId", menu.getRefrenceId());
        }
        return result;
    }
    
    /**
     * 添加或修改产品默认的类目选择页面
     * @param request
     * @param model
     * @return 
     * @throws com.zttx.sdk.exception.BusinessException
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/execute")
    public String productExecute(HttpServletRequest request, Model model) throws BusinessException
    {
        String menuId = "";
        String subMenuId = "";
        MenuInfo menuInfo = new MenuInfo();
        menuInfo.setMenuName("发布新产品");
        menuInfo.setMenuLevel((short) 4);
        List<MenuInfo> menuList = menuInfoService.findByMenuInfo(menuInfo);
        if (CollectionUtils.isNotEmpty(menuList))
        {
            MenuInfo menu = menuList.get(0);
            MenuInfo topmenu = menuInfoService.selectByPrimaryKey(menu.getParent().getUpMenuId());
            subMenuId = menu.getRefrenceId();
            menuId = topmenu.getRefrenceId();
        }
        return "forward:/brand/product/create/choose/dealdic?menuId=" + menuId + "&subMenuId=" + subMenuId;
    }
    
    /**
     * 查询支撑审核状态
     *
     * @throws BusinessException
     * @author 张昌苗
     */
    @RequiresPermissions("brand:center")
    @RequestMapping(value = "/selectSubmit", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage selectSubmit(HttpServletRequest request, ProductEditDetail param) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<ProductBaseInfo> productInfoList = productInfoDubboConsumer.findByBrandIdAndProductId(brandId, param.getProductId());
        ProductBaseInfo productInfo = null;
        if (productInfoList != null && productInfoList.size() > 0)
        {
            productInfo = productInfoList.get(0);
        }
        if (null == productInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        ProductEditDetail editDetail = productEditDetailService.executeSelect(param);
        return this.getJsonMessage(CommonConst.SUCCESS, editDetail);
    }
    
    /**
     * 修改产品货号，颜色，尺码，提交支撑审核
     *
     * @throws BusinessException
     * @author 张昌苗
     */
    @RequestMapping(value = "/changeSubmit", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage changeSubmit(HttpServletRequest request, ProductEditDetail param) throws BusinessException
    {
        String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
        List<ProductBaseInfo> productInfoList = productInfoDubboConsumer.findByBrandIdAndProductId(brandId, param.getProductId());
        ProductBaseInfo productInfo = null;
        if (productInfoList != null && productInfoList.size() > 0)
        {
            productInfo = productInfoList.get(0);
        }
        if (null == productInfo) { throw new BusinessException(CommonConst.DATA_NOT_EXISTS); }
        ProductEditDetail editDetail = productEditDetailService.executeSave(param);
        return this.getJsonMessage(CommonConst.SUCCESS, editDetail);
    }
    
    @RequestMapping("/editTitle")
    @ResponseBody
    @RequiresPermissions("brand:center")
    public JsonMessage editTitle(String title, String refrenceId) throws BusinessException
    {
        try
        {
            String brandId = OnLineUserUtils.getCurrentBrand().getRefrenceId();
            productInfoDubboConsumer.updateProductTitle(refrenceId, title, brandId);
            ProductBaseInfo productInfo = productInfoDubboConsumer.getProductById(refrenceId);
            if (Short.valueOf(productInfo.getStateSet()) == ProductConsts.BEGIN_TYPE_FIRST.getKey().shortValue())
            {
                // 搜索产品到收索引擎
                addProductInfo(productInfo.getRefrenceId());
            }
            return JsonMessageUtils.getJsonMessage(CommonConst.SUCCESS);
        }
        catch (Exception e)
        {
            return JsonMessageUtils.getJsonMessage(CommonConst.FAIL);
        }
    }
}
