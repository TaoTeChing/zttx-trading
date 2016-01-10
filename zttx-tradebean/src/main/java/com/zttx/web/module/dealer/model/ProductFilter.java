package com.zttx.web.module.dealer.model;

import com.zttx.sdk.core.GenericEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>File:ProductFilter</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c)2015/8/11 13:53</p>
 * <p>Company: 8637.com</p>
 *
 * @author 易永耀
 * @version 1.0
 */
public class ProductFilter extends GenericEntity
{
    // 品牌商ID
    private String       brandId;

    // 品牌ID
    private String       brandsId;

    // 分类
    private Integer      category;

    //分类父类
    private Integer      categoryBase=0;

    private String      productNo;

    // 排序
    private Integer      sort;

    // 关键字
    private String       keyWord;

    // 品牌自定义分类
    private String       cataId;

    // 经销商ID
    private String       dealerId;

    // 自定义分类列表
    private List<String> cateIdList;

    private BigDecimal   min;

    private BigDecimal   max;

    // 升序降序
    private Integer      order;

    // 区域
    private Integer      areaNo;

    /**
     * 经营品牌上级类目编号
     */
    private String       mainId;

    /**
     * 经营品牌上级类目名称
     */
    private String       mainName;
    
    /**
     * 展示方式
     */
    private Integer      displayMode;

    public Integer getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(Integer displayMode) {
		this.displayMode = displayMode;
	}

	public String getMainName()
    {
        return mainName;
    }

    public void setMainName(String mainName)
    {
        this.mainName = mainName;
    }

    public String getMainId()
    {
        return mainId;
    }

    public void setMainId(String mainId)
    {
        this.mainId = mainId;
    }

    public String getBrandId()
    {
        return brandId;
    }

    public void setBrandId(String brandId)
    {
        this.brandId = brandId;
    }

    public String getBrandsId()
    {
        return brandsId;
    }

    public void setBrandsId(String brandsId)
    {
        this.brandsId = brandsId;
    }

    public Integer getCategory()
    {
        return category;
    }

    public void setCategory(Integer category)
    {
        this.category = category;
    }

    public Integer getSort()
    {
        return sort;
    }

    public void setSort(Integer sort)
    {
        this.sort = sort;
    }

    public String getKeyWord()
    {
        return keyWord;
    }

    public void setKeyWord(String keyWord)
    {
        this.keyWord = keyWord;
    }

    public String getCataId()
    {
        return cataId;
    }

    public void setCataId(String cataId)
    {
        this.cataId = cataId;
    }

    public String getDealerId()
    {
        return dealerId;
    }

    public void setDealerId(String dealerId)
    {
        this.dealerId = dealerId;
    }

    public List<String> getCateIdList()
    {
        return cateIdList;
    }

    public void setCateIdList(List<String> cateIdList)
    {
        this.cateIdList = cateIdList;
    }

    public BigDecimal getMin()
    {
        return min;
    }

    public void setMin(BigDecimal min)
    {
        this.min = min;
    }

    public BigDecimal getMax()
    {
        return max;
    }

    public void setMax(BigDecimal max)
    {
        this.max = max;
    }

    public Integer getOrder()
    {
        return order;
    }

    public void setOrder(Integer order)
    {
        this.order = order;
    }

    public Integer getAreaNo()
    {
        return areaNo;
    }

    public void setAreaNo(Integer areaNo)
    {
        this.areaNo = areaNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getCategoryBase() {
        if(this.category!=null){
            // 产品类目
            int mod =this.category % 1000000;
            int level = (mod == 0 ? 1 : (mod % 1000 == 0) ? 2 : 3); // 分类级别
            switch (level)
            {
                case 1:
                    categoryBase = this.category + 1000000;
                    break;
                case 2:
                    categoryBase = this.category + 1000;
                    break;
                case 3:
                    categoryBase=0;
                    break;
                default:
                    break;
            }
        }
        return categoryBase;
    }

    public void setCategoryBase(Integer categoryBase) {
        this.categoryBase = categoryBase;
    }
}
