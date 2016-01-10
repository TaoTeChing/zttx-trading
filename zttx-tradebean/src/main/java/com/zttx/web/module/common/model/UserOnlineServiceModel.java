package com.zttx.web.module.common.model;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.zttx.web.module.common.entity.UserOnlineService;
import com.zttx.web.module.common.entity.UserOnlineServiceDetail;

/**
 * Created by 李星 on 2015/8/11.
 */
public class UserOnlineServiceModel extends UserOnlineService
{

    private List<UserOnlineServiceDetail> onlineDetailList;
    
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date                          tmpBeginTime;
    
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date                          tmpEndTime;
    
    private String[]                      qqs;
    
    private String[]                      names;
    
    @Transient
    public List<UserOnlineServiceDetail> getOnlineDetailList()
    {
        return onlineDetailList;
    }
    
    public void setOnlineDetailList(List<UserOnlineServiceDetail> onlineDetailList)
    {
        this.onlineDetailList = onlineDetailList;
    }
    
    @Transient
    public Date getTmpBeginTime()
    {
        return tmpBeginTime;
    }
    
    public void setTmpBeginTime(Date tmpBeginTime)
    {
        this.tmpBeginTime = tmpBeginTime;
    }
    
    @Transient
    public Date getTmpEndTime()
    {
        return tmpEndTime;
    }
    
    public void setTmpEndTime(Date tmpEndTime)
    {
        this.tmpEndTime = tmpEndTime;
    }
    
    @Transient
    public String[] getQqs()
    {
        return qqs;
    }
    
    public void setQqs(String[] qqs)
    {
        this.qqs = qqs;
    }
    
    @Transient
    public String[] getNames()
    {
        return names;
    }
    
    public void setNames(String[] names)
    {
        this.names = names;
    }
}
