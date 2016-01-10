/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.fronts.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.sdk.core.GenericMapper;
import com.zttx.web.module.fronts.entity.RulesCate;

/**
 * 网站规则类别 持久层接口
 * <p>File：RulesCateDao.java </p>
 * <p>Title: RulesCateDao </p>
 * <p>Description:RulesCateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface RulesCateMapper extends GenericMapper<RulesCate>
{
    /**
     * 
     * <p>Copyright: Copyright (c) May 26, 2015 </p>
     * <p>Company: 8637.com</p>
     * <p>创建时间： 2015年8月30日</P>
     * <p>文件名称   ：  RulesCateMapper.java</P>
     * <p>包名称         ：  com.zttx.web.module.fronts.mapper</P>
     * <p>工程名称      ： zttx-tradecore</P>
     * <p>返回类型      ： List<RulesCate></P>
     *  </p>author  by : 季明清</p>
     * </p>description :该方法作用是 通过级别来查询所有的规则信息 </p>
     */
    List<RulesCate> findByLevel(Short level);
    
    /**
     * 
     * <p>Copyright: Copyright (c) May 26, 2015 </p>
     * <p>Company: 8637.com</p>
     * <p>创建时间： 2015年8月30日</P>
     * <p>文件名称   ：  RulesCateMapper.java</P>
     * <p>包名称         ：  com.zttx.web.module.fronts.mapper</P>
     * <p>工程名称      ： zttx-tradecore</P>
     * <p>返回类型      ： List<RulesCate></P>
     *  </p>author  by : 季明清</p>
     * </p>description :该方法作用是通过父类别的id来查询父类别的信息 </p>
     */
    List<RulesCate> findByParentId(String parentId);
    
    /**
     * 统计该父类下面包含多少文章数量
     * @param parentId 父 id
     * @param cateTypeArticle 类型
     * @return 数量
     * @author 章旭楠
     */
    int countArticleNum(@Param("parentId") String parentId, @Param("cateType") short cateTypeArticle);
}
