package com.zttx.web.module.dealer.model;

import java.util.List;

import com.zttx.web.module.dealer.entity.DealerRefAttacht;
import com.zttx.web.module.dealer.entity.DealerRefReply;

/**
 * Created by 李星 on 2015/8/20.
 */
public class DealerRefReplyModel extends DealerRefReply
{
    // 退款附件
    private List<DealerRefAttacht> draList;
    
    public List<DealerRefAttacht> getDraList()
    {
        return draList;
    }
    
    public void setDraList(List<DealerRefAttacht> draList)
    {
        this.draList = draList;
    }
}
