package com.zttx.web.module.dealer.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.bean.PropertiesLoader;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.utils.AppParameterUtils;

/**
 * Created by 章旭楠 on 2015/8/31.
 */
@Service
public class DealerWeiShopServiceImpl implements DealerWeiShopService
{
    private PropertiesLoader  propertiesLoader = new PropertiesLoader("zttx.properties");
    
    @Autowired
    private DealerInfoService dealerInfoService;
    
    /**
     * 交易平台开通微店，获得验证码的接口。
     *
     * @param dealerId
     * @param mobile
     * @param validCode
     * @return
     */
    @Override
    public boolean openWeiShop(String dealerId, String mobile, String validCode)
    {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("zttxDealerId", dealerId);
        params.put("userMobile", mobile);
        params.put("validCode", validCode);
        DealerInfo dealerInfo = dealerInfoService.selectByPrimaryKey(dealerId);
        params.put("dealerName", dealerInfo == null ? null : dealerInfo.getDealerName());
        String requestUrl = propertiesLoader.getProperty("zttx.weishop.openshop");
        JsonMessage jsonMsg = AppParameterUtils.getWeiShopJsonMessage(params, requestUrl);
        return jsonMsg.getCode() != null && jsonMsg.getCode() == 121000;
    }
    
    /**
     * 发送开通微店的验证码 codeType为空时 :codeType为004
     *
     * @param userMobile
     * @param codeType
     * @return
     */
    @Override
    public JsonMessage sendWeiShopValidCode(String userMobile, String codeType)
    {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("userMobile", userMobile);
        params.put("codeType", codeType == null ? "004" : codeType);
        String requestUrl = propertiesLoader.getProperty("zttx.weishop.sndvcode");
        JsonMessage jsonMsg = AppParameterUtils.getWeiShopJsonMessage(params, requestUrl);
        return jsonMsg;
    }
    
    /**
     * 列出当前账号的所有微店
     *
     * @param dealerId
     * @return
     */
    @Override
    public JsonMessage getDealerWeiShopsBy(String dealerId)
    {
        HashMap<String, Object> params = Maps.newHashMap();
        params.put("zttxDealerId", dealerId);
        String requestUrl = propertiesLoader.getProperty("zttx.weishop.showlist");
        JsonMessage jsonMsg = AppParameterUtils.getWeiShopJsonMessage(params, requestUrl);
        return jsonMsg;
    }

    /**
     * 获取微店访问Token
     *
     * @param shopId
     * @return
     */
    @Override
    public JsonMessage getWeiShopToken(String shopId) {
        return null;
    }

    /**
     * 获取微店访问地址
     *
     * @return
     */
    @Override
    public String getWeiShopMgrAddr() {
        return null;
    }
}
