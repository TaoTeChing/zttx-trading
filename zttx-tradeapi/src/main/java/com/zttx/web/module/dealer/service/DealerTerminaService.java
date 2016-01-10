/*
 * Copyright 2015 Zttx, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.module.dealer.service;

import com.zttx.sdk.core.GenericServiceApi;
import com.zttx.web.module.dealer.entity.DealerJoin;
import com.zttx.web.module.dealer.entity.DealerTermina;
/**
 * 经销商中止合作日志 服务接口
 * <p>File：DealerTerminaService.java </p>
 * <p>Title: DealerTerminaService </p>
 * <p>Description:DealerTerminaService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * @author Playguy
 * @version 1.0
 */
public interface DealerTerminaService extends GenericServiceApi<DealerTermina>{

    /**
     * 保存终端商终止合作日志
     * @param dealerJoin
     * @param stopUserId
     * @return
     */
    public DealerTermina insertDealerTermina(DealerJoin dealerJoin,String stopUserId);
}
