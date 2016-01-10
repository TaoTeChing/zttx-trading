package com.zttx.web.module.brand.model;

import java.beans.Transient;
import java.util.List;

import com.zttx.web.module.brand.entity.BrandInfo;

/**
 * Created by 李星 on 2015/8/12.
 */
public class BrandInfoModel extends BrandInfo
{
    // 品牌商所有的品类编号
    private List<Integer> dealNo;

    // 执照文件名
    String file_cert_imagePhoto;

    // 执照文件路径
    String file_cert_imageImage;

    String file_user_photoImage;

    String file_user_photoPhoto;

    String file_user_imageImage;

    String file_user_imagePhoto;

    @Transient
    public List<Integer> getDealNo()
    {
        return dealNo;
    }
    
    public void setDealNo(List<Integer> dealNo)
    {
        this.dealNo = dealNo;
    }

    @Transient
    public String getFile_cert_imagePhoto() {
        return file_cert_imagePhoto;
    }

    public void setFile_cert_imagePhoto(String file_cert_imagePhoto) {
        this.file_cert_imagePhoto = file_cert_imagePhoto;
    }

    @Transient
    public String getFile_cert_imageImage() {
        return file_cert_imageImage;
    }

    public void setFile_cert_imageImage(String file_cert_imageImage) {
        this.file_cert_imageImage = file_cert_imageImage;
    }

    @Transient
    public String getFile_user_photoImage() {
        return file_user_photoImage;
    }

    public void setFile_user_photoImage(String file_user_photoImage) {
        this.file_user_photoImage = file_user_photoImage;
    }

    @Transient
    public String getFile_user_photoPhoto() {
        return file_user_photoPhoto;
    }

    public void setFile_user_photoPhoto(String file_user_photoPhoto) {
        this.file_user_photoPhoto = file_user_photoPhoto;
    }

    @Transient
    public String getFile_user_imageImage() {
        return file_user_imageImage;
    }

    public void setFile_user_imageImage(String file_user_imageImage) {
        this.file_user_imageImage = file_user_imageImage;
    }

    @Transient
    public String getFile_user_imagePhoto() {
        return file_user_imagePhoto;
    }

    public void setFile_user_imagePhoto(String file_user_imagePhoto) {
        this.file_user_imagePhoto = file_user_imagePhoto;
    }
}
