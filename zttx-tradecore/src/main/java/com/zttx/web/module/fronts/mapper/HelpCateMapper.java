/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.fronts.entity.HelpCate;

/**
 * 帮助分类 持久层接口
 * <p>File：HelpCateDao.java </p>
 * <p>Title: HelpCateDao </p>
 * <p>Description:HelpCateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface HelpCateMapper extends GenericMapper<HelpCate>
{
    /**
     * <p>Copyright: Copyright (c) May 26, 2015 </p>
     * <p>Company: 8637.com</p>
     * <p>创建时间： 2015年8月30日</P>
     * <p>文件名称   ：  HelpCateMapper.java</P>
     * <p>包名称         ：  com.zttx.web.module.fronts.mapper</P>
     * <p>工程名称      ： zttx-tradecore</P>
     * <p>返回类型      ： List<ArticleCate></P>
     *  </p>author  by : 季明清</p>
     * </p>description :该方法作用是  通过级别来查询出所有的类别信息</p>
     */
    List<HelpCate> findByLevel(Short level);
    
    /**
     * 
     * <p>Copyright: Copyright (c) May 26, 2015 </p>
     * <p>Company: 8637.com</p>
     * <p>创建时间： 2015年8月30日</P>
     * <p>文件名称   ：  HelpCateMapper.java</P>
     * <p>包名称         ：  com.zttx.web.module.fronts.mapper</P>
     * <p>工程名称      ： zttx-tradecore</P>
     * <p>返回类型      ： List<HelpCate></P>
     *  </p>author  by : 季明清</p>
     * </p>description :该方法作用是通过复类别id来查询所有的子类别信息   </p>
     */
    List<HelpCate> findByParentId(String parentId);
    
    /**
     * 取最大编号
     * @param parentId
     * @return
     */
    Integer getHelpNoByParent(@Param(value = "parentId") String parentId);
}
