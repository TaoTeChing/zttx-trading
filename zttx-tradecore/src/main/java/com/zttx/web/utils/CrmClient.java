package com.zttx.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.google.common.collect.Maps;
import com.zttx.sdk.bean.JsonMessage;
import com.zttx.sdk.consts.CharsetConst;
import com.zttx.sdk.exception.BusinessException;
import com.zttx.web.bean.WeshopAPIConfig;
import com.zttx.web.consts.UserAccountConst;
import com.zttx.web.consts.ZttxConst;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.common.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>File:CrmClient</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/9/18 13:59</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */

@Component
public class CrmClient {

    private static final Logger logger = Logger.getLogger(CrmClient.class);

    public static final int     SEND_RTX_TYPE_LOGIN    = 1;                                // 登录

    public static final int     SEND_RTX_TYPE_ORDER    = 2;                                // 下单

    public static final int     SEND_RTX_TYPE_APPLY    = 3;                                // 申请加盟

    public static final int     SEND_RTX_TYPE_RECHARGE = 4;                                // 充值

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 发送RTX提醒
     * @param type RTX提醒类型（1：登录，2：下单，3：申请加盟，4，充值）
     * @param createTime 创建时间，时间戳（11位，精确到秒）
     * @param userId 用户主键ID
     * @author 张昌苗
     * @throws BusinessException
     */
    public void sendRTX(int type, int createTime, String userId) throws BusinessException
    {
        UserInfo userInfo = userInfoService.selectByPrimaryKey(userId);
        String userName = userInfo.getUserName();
        Integer ip = IPUtil.getIpAddr(request);
        Map<String, Object> params = Maps.newHashMap();
        params.put("type", type);
        params.put("createTime", createTime);
        params.put("clientId", userId);
        params.put("clientName", userName);
        params.put("clientType", UserAccountConst.DEALER == userInfo.getUserType().intValue() ? 2 : 1);
        params.put("ip", ip);
        final Map<String, Object> _params = params;
        new Thread()
        {
            @Override
            public void run()
            {
                getJsonMessage(_params, "/rpc/rtx/message");
            }
        }.start();
    }

    /**
     * 发送RTX提醒
     * @param type RTX提醒类型（1：登录，2：下单，3：申请加盟，4，充值）
     * @param userId 用户主键ID
     * @author 张昌苗
     */
    public void sendRTX(int type, String userId) throws BusinessException
    {
        sendRTX(type, (int) (CalendarUtils.getCurrentLong() / 1000), userId);
    }

    public JsonMessage getJsonMessage(Map<String, Object> params, String uri)
    {
        Map<String, String> _params = WeshopAPIConfig.transMapToEncryMap(params, ZttxConst.CRMAPI_USERKEY);
        String result = HttpUtils.postMultipart(ZttxConst.CRMAPI_WEBURL + uri, _params, CharsetConst.CHARSET_UT, null, null, null, null);
        JsonMessage resJson;
        try
        {
            resJson = JSON.parseObject(result, JsonMessage.class);
        }
        catch (JSONException e)
        {
            resJson = null;
        }
        if (resJson == null) logger.error("支撑系统接口调用失败");
        return resJson;
    }


}
