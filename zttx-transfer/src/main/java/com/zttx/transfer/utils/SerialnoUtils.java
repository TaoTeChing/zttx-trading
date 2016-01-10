/*
 * @(#)SerialNumberUtils.java 2014-1-8 下午1:07:00
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.transfer.utils;

/**
 * <p>File：SerialNumberUtils.java</p>
 * <p>Title: 系统唯一编号生成工具类</p>
 * <p>Description:主要功能为主键、订单、支付、退款、发货、退货等编号的生成 </p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:07:00</p>
 * <p>Company: 8637.com</p>
 *
 * @author 刘志坚
 * @version 1.0
 */
public class SerialnoUtils {

    static IdWorker idworker = new IdWorker(1, 0);

    /**
     * 创建数据库主键
     *
     * @return String 数据库主键
     */
    public static String buildPrimaryKey() {
        return String.valueOf(idworker.nextId());
    }
}
