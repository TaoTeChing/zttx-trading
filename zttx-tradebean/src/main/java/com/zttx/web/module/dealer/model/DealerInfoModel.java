package com.zttx.web.module.dealer.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.dealer.entity.DealerClass;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.entity.DealerInfo;

/**
 * <p>File：DealerInfoModel.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-3-4 下午2:49:53</p>
 * <p>Company: 8637.com</p>
 *
 * @author 江枫林
 * @version 1.0
 */
public class DealerInfoModel extends DealerInfo {
    // 经销商的IP
    public Integer ip;

    // 品类编码
    public String[] dealNos;

    // 成立时间
    protected String foundTimeStr;

    // 经销商品类集合
    protected List<DealerClass> dealerClassess;


    protected String shopExperi;      // 实体店经验


    protected Integer shopExperiLeft;  // 实体店经验 区间条件1


    protected Integer shopExperiRight; // 实体店经验 区间条件2


    protected String shopNumByFind;   // 实体店数量


    protected Integer shopNumLeft;     // 实体店数量 区间条件1


    protected Integer shopNumRight;    // 实体店数量 区间条件2


    protected String saleTotal;       // 月销售额


    protected Integer saleTotalLeft;   // 月销售额 区间条件1


    protected Integer saleTotalRight;  // 月销售额 区间条件2


    protected String employeeNum;     // 员工数量

    protected Integer employeeNumLeft; // 员工数量区间条件1

    protected Integer employeeNumRight; // 员工数量区间条件2


    protected String province;        // 省


    protected String city;            // 市


    protected String county;          // 区


    protected String areaFullname;    // 区域全名


    protected Integer areaNo;


    protected Integer level;           // 区域等级


    private Boolean isViewAdd;       // 是否是新增查看联系记录


    private String hideMoble;       // 页面显示 隐藏手机号的一部分


    private String userMobile;      // 手机号


    private String userMail;        // 电子邮件


    private String brandId;         // 品牌商编号


    private String[] dealerImagePaths;


    protected List<DealerImage> dealerImages;


    // 经销商注册信息，第三方使用
    private UserInfo dealerUserm;


    private String dealerName;


    private Short attrType;        // 1.实体店数,2.员工数,3.成立时间


    private Short descType;        // 是否按降序排，1：是


    private Integer dictValue;       // 经营品类

    private List<Integer>       dealNo;
    
    public List<Integer> getDealNo()
    {
        return dealNo;
    }
    
    public void setDealNo(List<Integer> dealNo)
    {
        this.dealNo = dealNo;
    }

    public UserInfo getDealerUserm() {
        return dealerUserm;
    }

    public void setDealerUserm(UserInfo dealerUserm) {
        this.dealerUserm = dealerUserm;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getHideMoble() {
        return hideMoble;
    }

    public void setHideMoble(String hideMoble) {
        this.hideMoble = hideMoble;
    }

    public String[] getDealNos() {
        return dealNos;
    }

    public void setDealNos(String[] dealNos) {
        this.dealNos = dealNos;
    }

    public Integer getIp() {
        return ip;
    }

    public void setIp(Integer ip) {
        this.ip = ip;
    }


    public void setDealerClassess(List<DealerClass> dealerClassess) {
        this.dealerClassess = dealerClassess;
    }

    public String getShopExperi() {
        return shopExperi;
    }

    public void setShopExperi(String shopExperi) {
        this.shopExperi = shopExperi;
        this.shopExperiLeft = setLeft(shopExperi);
        this.shopExperiRight = setRight(shopExperi);
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
        this.employeeNumLeft = setLeft(employeeNum);
        this.employeeNumRight = setRight(employeeNum);
    }

    public String getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(String saleTotal) {
        this.saleTotal = saleTotal;
        this.saleTotalLeft = setLeft(saleTotal);
        this.saleTotalRight = setRight(saleTotal);
    }

    public String getShopNumByFind() {
        return shopNumByFind;
    }

    public void setShopNumByFind(String shopNumByFind) {
        this.shopNumByFind = shopNumByFind;
        this.shopNumLeft = setLeft(shopNumByFind);
        this.shopNumRight = setRight(shopNumByFind);
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(Integer areaNo) {
        this.areaNo = areaNo;
    }

    public Integer getShopNumLeft() {
        return shopNumLeft;
    }

    public Integer getShopNumRight() {
        return shopNumRight;
    }

    public Integer getSaleTotalLeft() {
        return saleTotalLeft;
    }

    public Integer getSaleTotalRight() {
        return saleTotalRight;
    }

    public Integer getEmployeeNumLeft() {
        return employeeNumLeft;
    }

    public Integer getEmployeeNumRight() {
        return employeeNumRight;
    }

    public Integer getShopExperiLeft() {
        return shopExperiLeft;
    }

    public Integer getShopExperiRight() {
        return shopExperiRight;
    }

    /**
     * 封装查询条件区间左侧条件
     *
     * @param str 预处理的字符串
     * @return Integer
     */
    public Integer setLeft(String str) {
        Integer left = null;
        if (!StringUtils.isBlank(str)) if (!str.equals("0")) {
            if (str.contains("-")) {
                String[] arr = str.split("-");
                left = Integer.parseInt(arr[0]);
            } else left = null;
        } else left = null;
        return left;
    }

    /**
     * 封装查询条件区间右侧条件
     *
     * @param str 预处理的字符串
     */
    public Integer setRight(String str) {
        Integer right = null;
        if (!StringUtils.isBlank(str)) if (!str.equals("0")) {
            if (str.contains("-")) {
                String[] arr = str.split("-");
                right = Integer.parseInt(arr[1]);
            } else right = Integer.parseInt(str);
        } else right = null;
        return right;
    }
    
    /**
     * 获取经销商品类
     *
     * @param dealerRefrenceId 经销商UUID
     * @return 经销商品类List
     */
    public List<DealerClass> getDealerClassess(String dealerRefrenceId)
    {
        if (null == dealNos || dealNos.length <= 0) return null;
        dealerClassess = Lists.newArrayList();
        for (String dealNo : dealNos)
        {
            DealerClass dc = new DealerClass(dealerRefrenceId, Integer.parseInt(dealNo.trim()), ip);
            dealerClassess.add(dc);
        }
        return dealerClassess;
    }

    public void setShopExperiLeft(Integer shopExperiLeft) {
        this.shopExperiLeft = shopExperiLeft;
    }

    public void setShopExperiRight(Integer shopExperiRight) {
        this.shopExperiRight = shopExperiRight;
    }

    public void setShopNumLeft(Integer shopNumLeft) {
        this.shopNumLeft = shopNumLeft;
    }

    public void setShopNumRight(Integer shopNumRight) {
        this.shopNumRight = shopNumRight;
    }

    public void setSaleTotalLeft(Integer saleTotalLeft) {
        this.saleTotalLeft = saleTotalLeft;
    }

    public void setSaleTotalRight(Integer saleTotalRight) {
        this.saleTotalRight = saleTotalRight;
    }

    public void setEmployeeNumLeft(Integer employeeNumLeft) {
        this.employeeNumLeft = employeeNumLeft;
    }

    public void setEmployeeNumRight(Integer employeeNumRight) {
        this.employeeNumRight = employeeNumRight;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAreaFullname() {
        return areaFullname;
    }

    public void setAreaFullname(String areaFullname) {
        this.areaFullname = areaFullname;
    }

    public String getFoundTimeStr() {
        return foundTimeStr;
    }

    public void setFoundTimeStr(String foundTimeStr) {
        this.foundTimeStr = foundTimeStr;
    }

    public Boolean getIsViewAdd() {
        return isViewAdd;
    }

    public void setIsViewAdd(Boolean isViewAdd) {
        this.isViewAdd = isViewAdd;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String[] getDealerImagePaths() {
        return dealerImagePaths;
    }

    public void setDealerImagePaths(String[] dealerImagePaths) {
        this.dealerImagePaths = dealerImagePaths;
    }

    public List<DealerImage> getDealerImages() {
        return dealerImages;
    }

    public void setDealerImages(List<DealerImage> dealerImages) {
        this.dealerImages = dealerImages;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public Short getAttrType() {
        return attrType;
    }

    public void setAttrType(Short attrType) {
        this.attrType = attrType;
    }

    public Short getDescType() {
        return descType;
    }

    public void setDescType(Short descType) {
        this.descType = descType;
    }

    public Integer getDictValue() {
        return dictValue;
    }

    public void setDictValue(Integer dictValue) {
        this.dictValue = dictValue;
    }
}
