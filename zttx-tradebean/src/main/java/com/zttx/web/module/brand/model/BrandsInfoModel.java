package com.zttx.web.module.brand.model;

import java.util.List;
import java.util.Map;
import com.zttx.web.module.brand.entity.BrandRecruit;
import com.zttx.web.module.brand.entity.BrandesInfo;
import com.zttx.web.module.brand.entity.BrandsCount;

/**
 * Created by 李星 on 2015/8/14.
 */
public class BrandsInfoModel extends BrandesInfo
{
    // 审核不通过原因
    private String      checkMark;
    
	// 企业品牌形象图
    private String      imageName;
    
    // 企业品牌形象图域名
    private String      domainName;


    
    private List<Map<String, Object>> brandDealList;
    
    private String                    brandDealAllName;
    
    // 招募书实体
    private BrandRecruit              brandRecruit;
    
    protected String                  image;
    
    protected String                  comName;
    
    protected String                  legalName;
    
    protected String                  provinceName;
    
    protected Short                   dealType;
    
    protected String                  cityName;
    
    protected String                  areaName;
    
    protected String                  comAddress;
    
    protected Double                  regMoney;
    
    protected Object[]                dealName;
    
    protected Long                    networkNum;
    
    protected Long                    askNum;
    
    protected Long                    applyNum;
    
    protected Short                   dealBrand;
    
    protected Object[]                dealMain;                        // 产品类目
    
    protected Object[]                dealNum;
    
    private Long                      searchTime;                      // 时间搜索条件
    
    private Integer                   searchWay;                       // 时间搜索条件的比较方式（1："大于开始时间"，2："小于开始时间"，3："大于结束时间"，4："小于结束时间"）
    
    private List<Integer>             dealNo;
    
    // 品牌商名称
    private String                    brandCompanyName;
    
    public String getCheckMark() {
        return checkMark;
    }

    public void setCheckMark(String checkMark) {
        this.checkMark = checkMark;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public List<Map<String, Object>> getBrandDealList() {
		return brandDealList;
	}

	public void setBrandDealList(List<Map<String, Object>> brandDealList) {
		this.brandDealList = brandDealList;
	}

	public String getBrandDealAllName() {
		return brandDealAllName;
	}

	public void setBrandDealAllName(String brandDealAllName) {
		this.brandDealAllName = brandDealAllName;
	}

	public BrandRecruit getBrandRecruit() {
		return brandRecruit;
	}

	public void setBrandRecruit(BrandRecruit brandRecruit) {
		this.brandRecruit = brandRecruit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Short getDealType() {
		return dealType;
	}

	public void setDealType(Short dealType) {
		this.dealType = dealType;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getComAddress() {
		return comAddress;
	}

	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}

	public Double getRegMoney() {
		return regMoney;
	}

	public void setRegMoney(Double regMoney) {
		this.regMoney = regMoney;
	}

	public Object[] getDealName() {
		return dealName;
	}

	public void setDealName(Object[] dealName) {
		this.dealName = dealName;
	}

	public Long getNetworkNum() {
		return networkNum;
	}

	public void setNetworkNum(Long networkNum) {
		this.networkNum = networkNum;
	}

	public Long getAskNum() {
		return askNum;
	}

	public void setAskNum(Long askNum) {
		this.askNum = askNum;
	}

	public Long getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(Long applyNum) {
		this.applyNum = applyNum;
	}

	public Short getDealBrand() {
		return dealBrand;
	}

	public void setDealBrand(Short dealBrand) {
		this.dealBrand = dealBrand;
	}

	public Object[] getDealMain() {
		return dealMain;
	}

	public void setDealMain(Object[] dealMain) {
		this.dealMain = dealMain;
	}

	public Object[] getDealNum() {
		return dealNum;
	}

	public void setDealNum(Object[] dealNum) {
		this.dealNum = dealNum;
	}

	public Long getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Long searchTime) {
		this.searchTime = searchTime;
	}

	public Integer getSearchWay() {
		return searchWay;
	}

	public void setSearchWay(Integer searchWay) {
		this.searchWay = searchWay;
	}

	public List<Integer> getDealNo() {
		return dealNo;
	}

	public void setDealNo(List<Integer> dealNo) {
		this.dealNo = dealNo;
	}

	public String getBrandCompanyName() {
		return brandCompanyName;
	}

	public void setBrandCompanyName(String brandCompanyName) {
		this.brandCompanyName = brandCompanyName;
	}
}
