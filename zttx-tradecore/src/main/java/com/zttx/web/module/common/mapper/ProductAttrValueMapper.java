package com.zttx.web.module.common.mapper;

import com.zttx.sdk.annotation.MyBatisDao;
import com.zttx.web.module.common.entity.ProductAttrValue;

import java.util.List;

/**
 * <p>File：ProductAttrValueMapper.java </p>
 * <p>Title: ProductAttrValueMapper </p>
 * <p>Description:ProductAttrValueMapper </p>
 * <p>Copyright: Copyright (c) 八月, 2015</p>
 * <p>Company: 8637.com</p>
 * <p>CreateDate:15/8/27</>
 *
 * @author Playguy
 * @version 1.0
 */
@MyBatisDao
public interface ProductAttrValueMapper
{
    /**
     * 根据产品编号取属性
     * <p>
     *     建立产品索引专用
     * </p>
     * @param productId
     * @return {@link List}
     */
    List<ProductAttrValue> getAttrValues(String productId);
    
}
