package com.zttx.web.module.fronts.controller;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CommonConst;
import com.zttx.web.module.brand.model.BrandesAuthUserModel;
import com.zttx.web.module.brand.service.BrandesAuthUserService;
import com.zttx.web.module.common.service.DealDicService;
import com.zttx.web.security.OnLineUserUtils;
import com.zttx.web.security.shiro.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.NavigationConst;
import com.zttx.web.consts.SearchConst;
import com.zttx.web.module.brand.service.BrandesInfoService;
import com.zttx.web.module.common.service.ProductInfoService;
import com.zttx.web.module.fronts.model.SolrFilter;

/**
 * <p>File：IndexController.java </p>
 * <p>Title: 首页控制器 </p>
 * <p>Description: IndexController </p>
 * <p>Copyright: Copyright (c) 2014 08/17/2015 15:47</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
public class IndexController extends GenericController
{
    @Autowired
    private ProductInfoService     productInfoService;
    
    @Autowired
    private BrandesInfoService     brandesInfoService;
    
    @Autowired
    private DealDicService         dealDicService;
    
    @Autowired
    private BrandesAuthUserService brandesAuthUserService;
    
    /**
     * 基础查询对象
     * @param filter
     * @param pagination
     * @return {@link ModelAndView}
     * @throws BusinessException
     */
    private ModelAndView search(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        ModelAndView mav;
        if (null != pagination) pagination.setPageSize(50);
        PaginateResult<Map<String, Object>> result;
        if (null != filter.getSt() && SearchConst.SEARCH_BRAND_TYPE.equals(filter.getSt()))
        {
            result = brandesInfoService.searchAllBrandes(filter, pagination);
            mav = new ModelAndView("fronts/search/brand_list");
        }
        else
        {
            mav = new ModelAndView("fronts/search/product_list");
            if (StringUtils.isNotBlank(filter.getBrandsId()))
            {
                List<String> ids = Lists.newArrayList();
                String[] brandsIds = filter.getBrandsId().split(",");
                if (brandsIds.length == 1)
                {
                    ids.add(brandsIds[0]);
                }
                else
                {
                    for (String id : brandsIds)
                        ids.add(id);
                }
                mav.addObject("brandesList", brandesInfoService.findBrandesInfoByIds(ids, filter.getMainId()));
            }
            if (StringUtils.isNotBlank(filter.getDealId()))
            {
                mav.addObject("dealDic", dealDicService.getDealDicByDealNo(Integer.valueOf(filter.getDealId())));
            }
            result = productInfoService.searchAllProduct(filter, pagination);
        }
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setSearchAll(Boolean.TRUE);
        }
        mav.addObject("filter", filter);
        mav.addObject("result", result);
        return mav;
    }
    
    /**
     *  女装导航
     * @param filter
     * @param  pagination
     * @return  {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping("/lady")
    public ModelAndView lady(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setMainId(SearchConst.SEARCH_LADY_CODE);
        }
        filter.setUrl("/lady");
        ModelAndView mav = this.search(filter, pagination);
        mav.addObject("showName", NavigationConst.NAVIGATION_LADY.getMessage());
        mav.addObject("nav", 1);
        return mav;
    }
    
    /**
     *  男装导航
     * @param filter
     * @param  pagination
     * @return  {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping("/man")
    public ModelAndView man(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setMainId(SearchConst.SEARCH_MAN_CODE);
        }
        filter.setUrl("/man");
        ModelAndView mav = this.search(filter, pagination);
        mav.addObject("showName", NavigationConst.NAVIGATION_MAN.getMessage());
        mav.addObject("nav", 2);
        return mav;
    }
    
    /**
    *  内衣导航
    * @param filter
    * @param  pagination
    * @return  {@link ModelAndView}
    * @throws BusinessException
    */
    @RequestMapping("/underwear")
    public ModelAndView underwear(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setMainId(SearchConst.SEARCH_UNDERWEAR_CODE);
        }
        filter.setUrl("/underwear");
        ModelAndView mav = this.search(filter, pagination);
        mav.addObject("showName", NavigationConst.NAVIGATION_UNDERWEAR.getMessage());
        mav.addObject("nav", 3);
        return mav;
    }
    
    /**
    *  家纺导航
    * @param filter
    * @param pagination
    * @return  {@link ModelAndView}
    * @throws BusinessException
    */
    @RequestMapping("/textiles")
    public ModelAndView textiles(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setMainId(SearchConst.SEARCH_TEXTILES_CODE);
        }
        filter.setUrl("/textiles");
        ModelAndView mav = this.search(filter, pagination);
        mav.addObject("showName", NavigationConst.NAVIGATION_TEXTILES.getMessage());
        mav.addObject("nav", 4);
        return mav;
    }
    
    /**
    *  童装导航
    * @param filter
    * @param  pagination
    * @return  {@link ModelAndView}
    * @throws BusinessException
    */
    @RequestMapping("/children")
    public ModelAndView children(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setMainId(SearchConst.SEARCH_CHILDREN_CODE);
        }
        filter.setUrl("/children");
        ModelAndView mav = this.search(filter, pagination);
        mav.addObject("showName", NavigationConst.NAVIGATION_CHILDREN.getMessage());
        mav.addObject("nav", 5);
        return mav;
    }
    
    /**
    *  鞋帽导航
    * @param filter
    * @param  pagination
    * @return  {@link ModelAndView}
    * @throws BusinessException
    */
    @RequestMapping("/shoes&hats")
    public ModelAndView shoesAndHats(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setMainId(SearchConst.SEARCH_SHOESANDHATS_CODE);
        }
        filter.setUrl("/shoes&hats");
        ModelAndView mav = this.search(filter, pagination);
        mav.addObject("showName", NavigationConst.NAVIGATION_SHOES_HATS.getMessage());
        mav.addObject("nav", 6);
        return mav;
    }
    
    /**
     * 通过产品编号时时取价
     * @param productIds 一组产品编号
     * @return JsonMessage
     * @throws BusinessException
     */
    @ResponseBody
    @RequestMapping("/getAuthPrice")
    public JsonMessage getAuthPrice(String productIds) throws BusinessException
    {
        if (StringUtils.isBlank(productIds)) return this.getJsonMessage(CommonConst.FAIL);
        String userId = null;
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null != principal) userId = principal.getRefrenceId();
        List<BrandesAuthUserModel> data = Lists.newArrayList();
        String[] ids = productIds.split(",");
        for (String id : ids)
        {
            data.add(brandesAuthUserService.getAuthPrice(userId, id, BrandesAuthUserModel.INCLUDE_SKU));
        }
        return this.getJsonMessage(CommonConst.SUCCESS, data);
    }
    
    /**
     * 箱包导航
     * @param filter
     * @param  pagination
     * @return  {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping("/bags")
    public ModelAndView bags(SolrFilter filter, Pagination pagination) throws BusinessException
    {
        if (StringUtils.isBlank(filter.getMainId()))
        {
            filter.setMainId(SearchConst.SEARCH_BAGS_CODE);
        }
        filter.setUrl("/bags");
        ModelAndView mav = this.search(filter, pagination);
        mav.addObject("showName", NavigationConst.NAVIGATION_BAGS.getMessage());
        // mav.addObject("nav", 7);
        return mav;
    }
}
