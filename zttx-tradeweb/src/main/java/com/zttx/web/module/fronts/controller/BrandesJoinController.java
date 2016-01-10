package com.zttx.web.module.fronts.controller;

import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.model.SolrFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>File：BrandesJoinController.java </p>
 * <p>Title: 品牌加盟导航 </p>
 * <p>Description: BrandesJoinController </p>
 * <p>Copyright: Copyright (c) 15/9/2</p>
 * <p>Company: 8637.com</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@RequestMapping("/brandJoin")
public class BrandesJoinController
{
    /**
     *  终端商入驻
     * @param filter
     * @return  {@link ModelAndView}
     * @throws BusinessException
     */
    @RequestMapping("")
    public ModelAndView dealerJoin(SolrFilter filter) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/brand/apply_dealer");
        mav.addObject("filter", filter);
        mav.addObject("nav", 7);
        return mav;
    }
}
