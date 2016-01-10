/*
 * @(#)GenericBaseCache.java 2014-3-27 下午2:37:33
 * Copyright 2014 吕海斌, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.core;

/**
 * 
 * <p>File：GenericBaseCache.java</p>
 * <p>Title: 缓存层 (Dao + Cache) 业务逻辑不应出现该层 存在问题:1 业务层 需要多个数据层协同完成 此时发生回滚 会导致数据库数据未更新,但缓存已被更新(即产生了脏数据) 2.业务层使用缓存(作用减弱)</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-27 下午2:37:33</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * 	
 * @version 1.0
 */
public interface BaseCache
{
    /**
     * 根据key来清除
     * @param key
     * @return
     */
    public boolean clear(String key);
}
