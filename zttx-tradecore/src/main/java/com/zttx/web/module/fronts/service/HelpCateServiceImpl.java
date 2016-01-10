/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zttx.sdk.bean.PaginateResult;
import com.zttx.sdk.bean.Pagination;
import com.zttx.sdk.core.GenericServiceApiImpl;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.sdk.utils.CalendarUtils;
import com.zttx.sdk.utils.SerialnoUtils;
import com.zttx.sdk.utils.StringUtils;
import com.zttx.web.consts.ClientConst;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.module.fronts.entity.HelpCate;
import com.zttx.web.module.fronts.mapper.HelpCateMapper;

/**
 * 帮助分类 服务实现类
 * <p>File：HelpCate.java </p>
 * <p>Title: HelpCate </p>
 * <p>Description:HelpCate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class HelpCateServiceImpl extends GenericServiceApiImpl<HelpCate> implements HelpCateService
{
    private HelpCateMapper helpCateMapper;
    
    @Autowired(required = true)
    public HelpCateServiceImpl(HelpCateMapper helpCateMapper)
    {
        super(helpCateMapper);
        this.helpCateMapper = helpCateMapper;
    }
    
    /**
     * 根据分类ID取上级分类
     * <p>
     *     采用递归方式取上级分类
     * </p>
     * @param cateId
     * @return
     */
    @Override
    public List<HelpCate> getParentHelpCates(String cateId)
    {
        return getParentHelpCates(cateId, new ArrayList<HelpCate>());
    }
    
    /**
     * 递归查询
     * @param cateId
     * @param helpCates
     * @return  {@link List}
     */
    List<HelpCate> getParentHelpCates(String cateId, List<HelpCate> helpCates)
    {
        HelpCate helpCate = selectByPrimaryKey(cateId);
        if (null != helpCate)
        {
            helpCates.add(helpCate);
            if (StringUtils.isNotBlank(helpCate.getParentId()) && helpCate.getParentId() != helpCate.getRefrenceId()) { return getParentHelpCates(helpCate.getParentId(),
                    helpCates); }
        }
        return helpCates;
    }
    
    /**
     * <p>Copyright: Copyright (c) May 26, 2015 </p>
     * <p>Company: 8637.com</p>
     * <p>创建时间： 2015年8月30日</P>
     * <p>文件名称   ：  HelpCateService.java</P>
     * <p>包名称         ：  com.zttx.web.module.fronts.service</P>
     * <p>工程名称      ： zttx-tradeapi</P>
     * <p>返回类型      ： List<ArticleCate></P>
     *  </p>author  by : 季明清</p>
     * </p>description :该方法作用是 获得所有的帮助的信息以树的形式展现出来</p>
     */
    @Override
    public List<HelpCate> getAllHelpCates()
    {
        List<HelpCate> list = helpCateMapper.findByLevel(new Short("1"));
        for (HelpCate cate : list)
        {
            cate.setSubList(getSubList(cate.getRefrenceId(), new ArrayList<HelpCate>()));
        }
        return list;
    }
    
    /**
     * 递归查询,父亲类别下面的所有的子类别
     * @param parentId
     * @param cates
     * @return {@link List}
     */
    List<HelpCate> getSubList(String parentId, List<HelpCate> cates)
    {
        List<HelpCate> cateList = helpCateMapper.findByParentId(parentId);
        if (null != cateList)
        {
            cates.addAll(cateList);
            for (HelpCate cate : cateList)
            {
                return getSubList(cate.getRefrenceId(), cates);
            }
        }
        return cates;
    }
    
    /**
     * 获得最顶层的集合
     *
     * @return
     */
    @Override
    public TreeSet<HelpCate> listTop()
    {
        List<HelpCate> all = selectAll();
        Map<String, HelpCate> resource = Maps.newHashMap();
        for (HelpCate helpCate : all)
        {
            resource.put(helpCate.getRefrenceId(), helpCate);
        }
        for (int i = 3; i > 0; i--)
        {
            for (HelpCate helpCate : resource.values())
            {
                if (helpCate.getHelpLevel() != null && helpCate.getHelpLevel() == i)
                {
                    String parentId = helpCate.getParentId();
                    HelpCate parent = resource.get(parentId);
                    if (null == parent)
                    {
                        continue;
                    }
                    parent.getChildren().add(helpCate);
                }
            }
        }
        TreeSet<HelpCate> topList = Sets.newTreeSet(new HelpCate.HelpCateComparator());
        for (HelpCate helpCate : resource.values())
        {
            if (helpCate.getHelpLevel() != null && helpCate.getHelpLevel() == 1)
            {
                topList.add(resource.get(helpCate.getRefrenceId()));
            }
        }
        return topList;
    }
    
    /**
     * 分页查询
     *
     * @param page       分页对象
     * @param searchBean 过滤条件
     * @return PaginateResult
     */
    @Override
    public PaginateResult<HelpCate> searchByClient(Pagination page, HelpCate searchBean)
    {
        searchBean.setPage(page);
        return new PaginateResult<>(page, this.findList(searchBean));
    }
    
    /**
     * 根据id找上级节点
     *
     * @param refrenceId
     * @return
     */
    @Override
    public HelpCate getParentById(String refrenceId)
    {
        HelpCate helpCate = selectByPrimaryKey(refrenceId);
        if (null != helpCate) { return selectByPrimaryKey(helpCate.getParentId()); }
        return null;
    }
    
    /**
     * 根据主键获取分类名称
     *
     * @param refrenceId 主键id
     * @return 分类名称
     */
    @Override
    public String getHelpNameById(String refrenceId)
    {
        if (StringUtils.isBlank(refrenceId)) { return null; }
        HelpCate helpCate = this.selectByPrimaryKey(refrenceId);
        if (helpCate != null) { return helpCate.getCateName(); }
        return null;
    }
    
    /**
     * 新增/修改
     * refrenceId=null：新增
     * refrenceId!=null：修改
     *
     * @param helpCate refrenceId	资料编号(为空：新增，不为空：修改)
     *                 helpName	分类名称（必填）
     *                 orderId	排序字段（必填）
     *                 parentId	上级分类
     *                 showType	显示类型：（1：列表显示，2：文章显示'）
     *                 helpTitle	帮助标题
     *                 htmlText	内容
     *                 showFlag	是否显示：（1:显示2：不显示'）
     *                 author 周光暖
     */
    @Override
    public void saveByClient(HelpCate helpCate) throws BusinessException
    {
        Short showType = helpCate.getShowType();
        if (null == showType) { throw new BusinessException(ClientConst.NULERROR.getCode(), "显示类型参数为空"); }
        HelpCate parent = selectByPrimaryKey(helpCate.getParentId());
        if (null != parent)
        {
            if (parent.getShowType().equals(CommonConstant.HelpCate.SHOW_TYPE_ARTICLE)) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(),
                    "显示类型为文章的分类不能再创建子级！"); }
            Integer helpLevel = parent.getHelpLevel() != null ? parent.getHelpLevel() : 1;
            helpLevel += 1;
            if (helpLevel > 3) { throw new BusinessException(ClientConst.ERROR_HANDLE.getCode(), "不能再创建子级！"); }
            helpCate.setHelpLevel(helpLevel);
        }
        else
        {
            helpCate.setHelpLevel(1);
        }
        // 如果是文章
        if (showType.equals(CommonConstant.HelpCate.SHOW_TYPE_ARTICLE))
        {
            // if (StringUtils.isBlank(helpCate.getTitle())) { throw new BusinessException(ClientConst.NULERROR.getCode(), "标题不能为空"); }
            if (StringUtils.isBlank(helpCate.getHtmlText())) { throw new BusinessException(ClientConst.NULERROR.getCode(), "内容不能为空"); }
        }
        if (StringUtils.isBlank(helpCate.getRefrenceId()))
        {
            helpCate.setCateNo(this.getHelpNoByParent(parent));
            helpCate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
            helpCate.setCreateTime(CalendarUtils.getCurrentLong());
            helpCate.setUpdateTime(CalendarUtils.getCurrentLong());
            this.insertHelpCate(helpCate);
        }
        else
        {
            helpCate.setCateNo(this.getHelpNoByParent(parent));
            helpCate.setUpdateTime(CalendarUtils.getCurrentLong());
            this.updateHelpCate(helpCate);
        }
    }
    
    private void updateHelpCate(HelpCate helpCate)
    {
        HelpCate old = this.selectByPrimaryKey(helpCate.getRefrenceId());
        if (!old.getParentId().equals(helpCate.getParentId()))// 父级变化
        {
            HelpCate parent = this.selectByPrimaryKey(helpCate.getParentId());
            helpCate.setCateNo(this.getHelpNoByParent(parent));
        }
        else
        {
            helpCate.setCateNo(old.getCateNo());
        }
        helpCate.setCreateTime(old.getCreateTime());
        helpCate.setDelFlag(old.getDelFlag());
        this.updateByPrimaryKeySelective(helpCate);
    }
    
    private void insertHelpCate(HelpCate helpCate)
    {
        HelpCate parent = this.selectByPrimaryKey(helpCate.getParentId());
        helpCate.setCateNo(this.getHelpNoByParent(parent));
        helpCate.setRefrenceId(SerialnoUtils.buildPrimaryKey());
        helpCate.setCreateTime(CalendarUtils.getCurrentLong());
        helpCate.setDelFlag(false);
        this.insertSelective(helpCate);
    }
    
    /**
     * 根据parent生成helpNo
     *
     * @param parent
     * @return
     * @author 周光暖
     */
    @Override
    public Integer getHelpNoByParent(HelpCate parent)
    {
        Integer helpNo;
        if (null == parent)// 表示第一级
        {
            Integer maxHelpNo = helpCateMapper.getHelpNoByParent(null);
            if (null != maxHelpNo)
            {
                helpNo = this.getHelpNo(maxHelpNo, 1);
            }
            else
            {
                helpNo = 100000;
            }
        }
        else
        // 二级 或 三级
        {
            Integer maxHelpNo = helpCateMapper.getHelpNoByParent(parent.getRefrenceId());
            if (null != maxHelpNo)
            {
                helpNo = this.getHelpNo(maxHelpNo, parent.getHelpLevel() + 1);
            }
            else
            {
                helpNo = this.getHelpNo(parent.getCateNo(), parent.getHelpLevel() + 1);
            }
        }
        return helpNo;
    }
    
    private Integer getHelpNo(Integer maxHelpNo, Integer helpLevel)
    {
        Integer level1 = 10000;
        Integer level2 = 100;
        Integer level3 = 1;
        switch (helpLevel)
        {
            case 1:
                maxHelpNo += level1;
                break;
            case 2:
                maxHelpNo += level2;
                break;
            case 3:
                maxHelpNo += level3;
                break;
        }
        return maxHelpNo;
    }
}
