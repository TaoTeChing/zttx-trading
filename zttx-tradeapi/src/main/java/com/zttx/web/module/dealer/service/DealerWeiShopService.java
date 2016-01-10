package com.zttx.web.module.dealer.service;

import com.zttx.sdk.bean.JsonMessage;

/**
 * Created by 章旭楠 on 2015/8/31.
 */
public interface DealerWeiShopService
{
    /**
     * 交易平台开通微店，获得验证码的接口。
     * @param dealerId
     * @param mobile
     * @param validCode
     * @return
     */
    boolean openWeiShop(String dealerId, String mobile, String validCode);
    
    /**
     * 发送开通微店的验证码 codeType为空时 :codeType为004
     * @param userMobile
     * @param codeType
     * @return
     */
    JsonMessage sendWeiShopValidCode(String userMobile, String codeType);

    /**
     * 列出当前账号的所有微店
     * @param dealerId
     * @return
     */
    JsonMessage getDealerWeiShopsBy(String dealerId);

    /**
     * 获取微店访问Token
     * @param shopId
     * @return
     */
    JsonMessage getWeiShopToken(String shopId);

    /**
     * 获取微店访问地址
     * @return
     */
    String getWeiShopMgrAddr();
}
