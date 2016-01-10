package com.zttx.web.module.brand.model;

import com.zttx.web.module.brand.entity.BrandDeal;
import com.zttx.web.module.brand.entity.BrandFavorite;
import com.zttx.web.module.brand.entity.BrandInfo;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.common.entity.UserInfo;
import com.zttx.web.module.dealer.entity.DealerClass;
import com.zttx.web.module.dealer.entity.DealerImage;
import com.zttx.web.module.dealer.entity.DealerInfo;
import com.zttx.web.utils.CalendarUtils;
import com.zttx.web.utils.ValidateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * BrandFavoriteView
 *
 * @author 江枫林
 * @date 2015/9/4
 */
public class BrandFavoriteView extends BrandFavorite {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3357225368499766164L;


    private BrandInfo brandInfo;


    private BrandesInfo brandesInfo;


    private DealerInfo dealerInfo;


    private String brandDealStr;


    private List<BrandDeal> brandDeals;

    // 开始时间

    private String startTimeStr;

    // 结束时间

    private String endTimeStr;

    // 企业规模

    private String emploeeNum;

    // 年营业额

    private String moneyNum;

    // 经销商的IP

    public Integer ip;

    // 品类编码

    public String[] dealNos;

    // 成立时间

    protected String foundtime;

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

    public String getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(String moneyNum) {
        this.moneyNum = (ValidateUtils.isInt(moneyNum)) ? moneyNum : null;
    }

    public String getEmploeeNum() {
        return emploeeNum;
    }

    public void setEmploeeNum(String emploeeNum) {
        this.emploeeNum = (ValidateUtils.isInt(emploeeNum)) ? emploeeNum : null;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = String.valueOf(CalendarUtils.getLongFromTime(startTimeStr));
        this.startTimeStr = ("0".equals(this.startTimeStr)) ? null : this.startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = String.valueOf(CalendarUtils.getLongFromTime(endTimeStr));
        this.endTimeStr = ("0".equals(this.endTimeStr)) ? null : this.endTimeStr;
    }

    public BrandInfo getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(BrandInfo brandInfo) {
        this.brandInfo = brandInfo;
    }

    public DealerInfo getDealerInfo() {
        return dealerInfo;
    }

    public void setDealerInfo(DealerInfo dealerInfo) {
        this.dealerInfo = dealerInfo;
    }

    public BrandesInfo getBrandesInfo() {
        return brandesInfo;
    }

    public void setBrandesInfo(BrandesInfo brandesInfo) {
        this.brandesInfo = brandesInfo;
    }

    public List<BrandDeal> getBrandDeals() {
        return brandDeals;
    }

    public void setBrandDeals(List<BrandDeal> brandDeals) {
        this.brandDeals = brandDeals;
    }

    public String getBrandDealStr() {
        return brandDealStr;
    }

    public void setBrandDealStr(String brandDealStr) {
        this.brandDealStr = brandDealStr;
    }


    public UserInfo getDealerUserm()
    {
        return dealerUserm;
    }

    public void setDealerUserm(UserInfo dealerUserm)
    {
        this.dealerUserm = dealerUserm;
    }

    public String getUserMail()
    {
        return userMail;
    }

    public void setUserMail(String userMail)
    {
        this.userMail = userMail;
    }

    public String getHideMoble()
    {
        return hideMoble;
    }

    public void setHideMoble(String hideMoble)
    {
        this.hideMoble = hideMoble;
    }

    public String[] getDealNos()
    {
        return dealNos;
    }

    public void setDealNos(String[] dealNos)
    {
        this.dealNos = dealNos;
    }

    public Integer getIp()
    {
        return ip;
    }

    public void setIp(Integer ip)
    {
        this.ip = ip;
    }

    /**
     * 获取经销商品类
     *
     * @param dealerRefrenceId
     *            经销商UUID
     * @return 经销商品类List
     */
    public List<DealerClass> getDealerClassess(String dealerRefrenceId)
    {
//        if (null == dealNos || dealNos.length <= 0) return null;
//        dealerClassess = Lists.newArrayList();
//        for (String dealNo : dealNos)
//        {
//            DealerClass dc = new DealerClass(dealerRefrenceId, Integer.parseInt(dealNo.trim()), ip);
//            dealerClassess.add(dc);
//        }
        return dealerClassess;
    }

    public List<DealerClass> getDealerClassess()
    {
        return dealerClassess;
    }

    public void setDealerClassess(List<DealerClass> dealerClassess)
    {
        this.dealerClassess = dealerClassess;
    }

    public String getShopExperi()
    {
        return shopExperi;
    }

    public void setShopExperi(String shopExperi)
    {
        this.shopExperi = shopExperi;
        this.shopExperiLeft = setLeft(shopExperi);
        this.shopExperiRight = setRight(shopExperi);
    }

    public String getEmployeeNum()
    {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum)
    {
        this.employeeNum = employeeNum;
        this.employeeNumLeft = setLeft(employeeNum);
        this.employeeNumRight = setRight(employeeNum);
    }

    public String getSaleTotal()
    {
        return saleTotal;
    }

    public void setSaleTotal(String saleTotal)
    {
        this.saleTotal = saleTotal;
        this.saleTotalLeft = setLeft(saleTotal);
        this.saleTotalRight = setRight(saleTotal);
    }

    public String getShopNumByFind()
    {
        return shopNumByFind;
    }

    public void setShopNumByFind(String shopNumByFind)
    {
        this.shopNumByFind = shopNumByFind;
        this.shopNumLeft = setLeft(shopNumByFind);
        this.shopNumRight = setRight(shopNumByFind);
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince(String province)
    {
        this.province = province;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public Integer getAreaNo()
    {
        return areaNo;
    }

    public void setAreaNo(Integer areaNo)
    {
        this.areaNo = areaNo;
    }

    public Integer getShopNumLeft()
    {
        return shopNumLeft;
    }

    public Integer getShopNumRight()
    {
        return shopNumRight;
    }

    public Integer getSaleTotalLeft()
    {
        return saleTotalLeft;
    }

    public Integer getSaleTotalRight()
    {
        return saleTotalRight;
    }

    public Integer getEmployeeNumLeft()
    {
        return employeeNumLeft;
    }

    public Integer getEmployeeNumRight()
    {
        return employeeNumRight;
    }

    public Integer getShopExperiLeft()
    {
        return shopExperiLeft;
    }

    public Integer getShopExperiRight()
    {
        return shopExperiRight;
    }

    /**
     * 封装查询条件区间左侧条件
     * @param str 预处理的字符串
     * @return  Integer
     * @author 罗盛平
     */
    public Integer setLeft(String str)
    {
        Integer left = null;
        if (!StringUtils.isBlank(str)) if (!str.equals("0"))
        {
            if (str.contains("-"))
            {
                String[] arr = str.split("-");
                left = Integer.parseInt(arr[0]);
            }
            else left = null;
        }
        else left = null;
        return left;
    }

    /**
     * 封装查询条件区间右侧条件
     * @param str 预处理的字符串
     * @author 罗盛平
     */
    public Integer setRight(String str)
    {
        Integer right = null;
        if (!StringUtils.isBlank(str)) if (!str.equals("0"))
        {
            if (str.contains("-"))
            {
                String[] arr = str.split("-");
                right = Integer.parseInt(arr[1]);
            }
            else right = Integer.parseInt(str);
        }
        else right = null;
        return right;
    }

    public void setShopExperiLeft(Integer shopExperiLeft)
    {
        this.shopExperiLeft = shopExperiLeft;
    }

    public void setShopExperiRight(Integer shopExperiRight)
    {
        this.shopExperiRight = shopExperiRight;
    }

    public void setShopNumLeft(Integer shopNumLeft)
    {
        this.shopNumLeft = shopNumLeft;
    }

    public void setShopNumRight(Integer shopNumRight)
    {
        this.shopNumRight = shopNumRight;
    }

    public void setSaleTotalLeft(Integer saleTotalLeft)
    {
        this.saleTotalLeft = saleTotalLeft;
    }

    public void setSaleTotalRight(Integer saleTotalRight)
    {
        this.saleTotalRight = saleTotalRight;
    }

    public void setEmployeeNumLeft(Integer employeeNumLeft)
    {
        this.employeeNumLeft = employeeNumLeft;
    }

    public void setEmployeeNumRight(Integer employeeNumRight)
    {
        this.employeeNumRight = employeeNumRight;
    }

    public String getCounty()
    {
        return county;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public String getAreaFullname()
    {
        return areaFullname;
    }

    public void setAreaFullname(String areaFullname)
    {
        this.areaFullname = areaFullname;
    }

    public String getFoundtime()
    {
        return foundtime;
    }

    public void setFoundtime(String foundtime)
    {
        this.foundtime = foundtime;
    }

    public Boolean getIsViewAdd()
    {
        return isViewAdd;
    }

    public void setIsViewAdd(Boolean isViewAdd)
    {
        this.isViewAdd = isViewAdd;
    }

    public String getUserMobile()
    {
        return userMobile;
    }

    public void setUserMobile(String userMobile)
    {
        this.userMobile = userMobile;
    }

    public String getBrandId()
    {
        return brandId;
    }

    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }

    public String[] getDealerImagePaths()
    {
        return dealerImagePaths;
    }

    public void setDealerImagePaths(String[] dealerImagePaths)
    {
        this.dealerImagePaths = dealerImagePaths;
    }

    public List<DealerImage> getDealerImages()
    {
        return dealerImages;
    }

    public void setDealerImages(List<DealerImage> dealerImages)
    {
        this.dealerImages = dealerImages;
    }

    public String getDealerName()
    {
        return dealerName;
    }

    public void setDealerName(String dealerName)
    {
        this.dealerName = dealerName;
    }

    public Short getAttrType()
    {
        return attrType;
    }

    public void setAttrType(Short attrType)
    {
        this.attrType = attrType;
    }

    public Short getDescType()
    {
        return descType;
    }

    public void setDescType(Short descType)
    {
        this.descType = descType;
    }

    public Integer getDictValue()
    {
        return dictValue;
    }

    public void setDictValue(Integer dictValue)
    {
        this.dictValue = dictValue;
    }
}
