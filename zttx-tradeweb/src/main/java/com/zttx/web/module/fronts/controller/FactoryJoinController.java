package com.zttx.web.module.fronts.controller;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.model.SolrFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>File：FactoryJoinController.java </p>
 * <p>Title: 工厂店加盟介绍 </p>
 * <p>Description: FactoryJoinController </p>
 * <p>Copyright: Copyright (c) 2014 08/22/2015 13:13</p>
 * <p>Company: 8637.com</p>
 *
 * @author playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/factory")
public class FactoryJoinController extends GenericController
{
    /**
     *  工厂店加盟介绍
     * @param filter
     * @return  {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping("/introduce")
    public ModelAndView introduce(SolrFilter filter) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/factory/factory_intro");
        mav.addObject("filter", filter);
        mav.addObject("nav", 9);
        return mav;
    }
    
    /**
     *  工厂店加盟
     * @param filter
     * @return  {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping("/join")
    public ModelAndView join(SolrFilter filter) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/factory/factory_join");
        mav.addObject("filter", filter);
        mav.addObject("nav", 9);
        return mav;
    }
    
    /**
     *  工厂店供货介绍
     * @param filter
     * @return  {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping("/supply")
    public ModelAndView supply(SolrFilter filter) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/factory/factory_supply");
        mav.addObject("filter", filter);
        mav.addObject("nav", 9);
        return mav;
    }
}
