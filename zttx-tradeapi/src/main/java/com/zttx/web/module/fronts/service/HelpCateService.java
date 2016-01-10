/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.List;
import java.util.TreeSet;

import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.module.fronts.entity.HelpCate;

/**
 * 帮助分类 服务接口
 * <p>File：HelpCateService.java </p>
 * <p>Title: HelpCateService </p>
 * <p>Description:HelpCateService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface HelpCateService extends GenericServiceApi<HelpCate>
{
    /**
     * 根据分类ID取上级分类
     * <p>
     *     采用递归方式取上级分类
     * </p>
     * @param cateId
     * @return
     */
    List<HelpCate> getParentHelpCates(String cateId);
    
    /**
     * 该方法作用是 获得所有的帮助的信息以树的形式展现出来
     * @return
     */
    List<HelpCate> getAllHelpCates();
    
    /**
     * 获得最顶层的集合
     * @return
     */
    TreeSet<HelpCate> listTop();
    
    /**
     * 分页查询
     * @param page 分页对象
     * @param searchBean 过滤条件
     * @return PaginateResult
     */
    PaginateResult<HelpCate> searchByClient(Pagination page, HelpCate searchBean);
    
    /**
     * 根据id找上级节点
     * @param refrenceId
     * @return
     */
    HelpCate getParentById(String refrenceId);
    
    /**
     * 根据主键获取分类名称
     * @param refrenceId 主键id
     * @return 分类名称
     */
    String getHelpNameById(String refrenceId);
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     * @param helpCate
     * 		refrenceId	资料编号(为空：新增，不为空：修改)
     *      helpName	分类名称（必填）
     *      orderId	排序字段（必填）
     *      parentId	上级分类
     *      showType	显示类型：（1：列表显示，2：文章显示'）
     *      helpTitle	帮助标题
     *      htmlText	内容
     *      showFlag	是否显示：（1:显示2：不显示'）
     *      author 周光暖
     */
    void saveByClient(HelpCate helpCate) throws BusinessException;
    
    /**
     * 根据parent生成helpNo
     * @param parent
     * @return
     * @author 周光暖
     */
    Integer getHelpNoByParent(HelpCate parent);
}
