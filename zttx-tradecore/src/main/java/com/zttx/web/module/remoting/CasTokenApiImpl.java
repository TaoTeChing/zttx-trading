package com.zttx.web.module.remoting;

import org.apache.commons.lang3.StringUtils;

import com.zttx.sdk.utils.JedisUtils;
import com.zttx.trade.remoting.api.CasTokenApi;
import com.zttx.trade.remoting.exception.CasException;
import com.zttx.trade.remoting.model.CasUser;
import com.zttx.web.consts.CommonConstant;
import com.zttx.web.consts.UserInfoConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;

/**
 * 结算平台登录令牌API
 * @author 李星
 * @version 1.0
 * @since 2015 2015/09/15 10:09
 */
public class CasTokenApiImpl implements CasTokenApi
{
    private UserInfoService userInfoService;
    
    public void setUserInfoService(UserInfoService userInfoService)
    {
        this.userInfoService = userInfoService;
    }
    
    @Override
    public CasUser validateCasToken(String token)
    {
        // 结算平台登录交易平台时在缓存中已经保存了登录用户ID,通过如下key获取
        String key = "cas_token_" + token;
        String tradeUserId = JedisUtils.get(key);
        if (tradeUserId == null) { throw new CasException("登录信息不正确或已过期."); }
        UserInfo userm = userInfoService.selectByPrimaryKey(tradeUserId);
        if (userm == null) { throw new CasException("用户信息不正确."); }
        CasUser casUser = new CasUser();
        casUser.setTradeUserId(userm.getRefrenceId());
        if (userm.getUserType() != null && userm.getUserType() == UserInfoConst.USER_TYPE_BRAND)
        {
            casUser.setUsername(StringUtils.trimToEmpty(userm.getComName()));
            casUser.setIsEnterprise(true);
        }
        else
        {
            casUser.setUsername(StringUtils.trimToEmpty(userm.getUserName()));
        }
        casUser.setMobile(userm.getUserMobile());
        casUser.setMerchantId(CommonConstant.OrderPay.PAY_MERCHANT_ID);
        // 删除token
        JedisUtils.del(key);
        return casUser;
    }
}
