/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.common.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.common.entity.TelCode;
/**
 * 手机验证码 服务接口
 * <p>File：TelCodeService.java </p>
 * <p>Title: TelCodeService </p>
 * <p>Description:TelCodeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface TelCodeService extends GenericServiceApi<TelCode>{

    /**
     * 生成手机验证码,带检测 如果存在有效则直接返回已存的TelCode实体,
     *
     * @param telCode 手机验证码实体
     * @return 处理结果码 SUCCESS:0X00 NULLPOT:0X01 DATA_EXISTS:0x03
     */
    public Integer create(TelCode telCode);

    /**
     * 进行手机验证验证处理 ,只作检测验证码正确与否,不修改记录useState字段
     * @param userMobile
     * @param verifyType
     * @param verifyCode
     * @return
     */
    Integer verifyAndCheck(String userMobile, String verifyType, String verifyCode);

    /**
     * 修改手机验证记录为使用状态
     *
     * @param userMobile 手机号
     * @param verifyType 验证类别
     * @return 返回处理结果 成功 失败
     */
    Integer modifyStateUsed(String userMobile, String verifyType);
    
    /**
     * 根据手机号,验证类型,使用状态查找TelCode
     *
     * @param userMobile
     * @param verifyType
     * @param useState
     * @return
     */
    TelCode search(String userMobile, String verifyType, Boolean useState);
    
    /**
     * 进行手机验证验证处理 验证通过时,同时会更新数据记录的useState字段为TRUE
     *
     * @param userMobile 手机号
     * @param verifyType 验证类别
     * @param verifyCode 验证码
     * @return 返回处理结果 成功 失效 错误
     */
    Integer verifyAndUpdate(String userMobile, String verifyType, String verifyCode);
}
