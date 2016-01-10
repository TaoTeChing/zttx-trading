/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.controller;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.module.fronts.entity.ArticleCate;
import com.zttx.web.module.fronts.entity.ArticleInfo;
import com.zttx.web.module.fronts.service.ArticleCateService;
import com.zttx.web.module.fronts.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zttx.sdk.core.GenericController;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.model.RulesFilter;

import java.util.Collections;

/**
 * 资讯文章信息 控制器
 * <p>File：ArticleInfoController.java </p>
 * <p>Title: ArticleInfoController </p>
 * <p>Description:ArticleInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Controller
public class ArticleInfoController extends GenericController
{
    @Autowired(required = true)
    private ArticleCateService articleCateService;
    
    @Autowired(required = true)
    private ArticleInfoService articleInfoService;
    
    /**
     * 资讯导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/info")
    public String info() throws BusinessException
    {
        return "fronts/info/index";
    }
    
    /**
     * 资讯分类搜索导航
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/info/list")
    public ModelAndView infoList(RulesFilter filter) throws BusinessException
    {
        ModelAndView mav = new ModelAndView("fronts/info/list");
        mav.addObject("filter", filter);
        if (StringUtils.isNotBlank(filter.getCateId()))
        {
            mav.addObject("articleCate", articleCateService.selectByPrimaryKey(filter.getCateId()));
        }
        return mav;
    }
    
    /**
     *  根据类目导航
     * @param articleInfoId
     * @param model
     * @return
     * @throws BusinessException
     */
    @RequestMapping("/info/detail/{articleInfoId}")
    public String detail(@PathVariable("articleInfoId") String articleInfoId, Model model) throws BusinessException
    {
        ArticleInfo articleInfo = articleInfoService.selectByPrimaryKey(articleInfoId);
        if (articleInfo != null)
        {
            ArticleCate articleCate = articleCateService.selectByPrimaryKey(articleInfo.getCateId());
            PaginateResult<ArticleInfo> articleInfos = articleInfoService.findBy(articleInfo.getCateId(), new Pagination(15));
            Collections.shuffle(articleInfos.getList());
            model.addAttribute("articleInfo", articleInfo);
            model.addAttribute("articleCate", articleCate);
            model.addAttribute("articleInfos", articleInfos);
        }
        return "fronts/info/info";
    }
}
