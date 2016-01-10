/*
 * @(#)BrandConstant.java 2014-5-2 上午9:52:25
 * Copyright 2014 施建波, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zttx.web.consts;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * <p>File：BrandConstant.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-5-2 上午9:52:25</p>
 * <p>Company: 8637.com</p>
 * @author 施建波
 * @version 1.0
 */
public abstract class BrandConstant
{
    // 品牌商 条形码助记码 开始数
    public static final String  BRANDINFO_BARCODENUM_DEAFAULT   = "10000";
    
    // 品牌商 条形码助记码 位数
    public static final int     BRANDINFO_BARCODENUM_LENGTH     = 5;
    
    // 品牌商品牌 条形码助记码 开始数
    public static final String  BRANDESINFO_BARCODENUM_DEAFAULT = "00";
    
    // 品牌商品牌 条形码助记码 位数
    public static final int     BRANDESINFO_BARCODENUM_LENGTH   = 2;
    
    // 产品 条形码助记码 开始数
    public static final String  PRODUCT_BARCODENUM_DEAFAULT     = "000";
    
    /**
     * 产品条形码助记码 位数
     */
    public static final int     PRODUCT_BARCODENUM_LENGTH       = 5;
    
    /**
     * 商品SKU条码类型常量：1=企业编码
     */
    public static final Integer SKU_BARCODE_TYPE_COMP           = 1;
    
    /**
     * 商品SKU条码类型常量:2=8637内部通用码
     */
    public static final Integer SKU_BARCODE_TYPE_8637           = 2;
    
    // delState(删除标志)
    public static final boolean DEL_STATE_DELETED               = true;   // 已经删除
    
    public static final boolean DEL_STATE_NONE_DELETED          = false;  // 未删除
    
    public abstract static class userType
    {
        public static final short BRAND_TYPE = 0; // 品牌商用户类型 0
    }
    
    public abstract static class BrandInviteConst
    {
        // applyState(申请状态)
        public static final short APPLY_STATE_NOCHECK                 = 0; // 未审核
        
        public static final short APPLY_STATE_SUCCESS                 = 1; // 审核通过
        
        public static final short APPLY_STATE_FAILURE                 = 2; // 审核不通过
        
        public static final short APPLY_STATE_INVITE                  = 3; // 邀请加盟
        
        public static final short APPLY_STATE_DEALER_STOP_COOPERATION = 4; // 经销商中止合作
        
        public static final short APPLY_STATE_CANCEL                  = 5; // 撤消申请
        
        public static final short APPLY_STATE_BRAND_STOP_COOPERATION  = 6; // 品牌商中止合作
        
        public static final short APPLY_STATE_BRAND_INVITE            = 7; // 撤消邀请
        
        // inviteState(邀请状态)
        public static final int   INVITE_STATE_UNDEAL                 = 0; // 已邀请未回复
        
        public static final int   INVITE_STATE_ACCEPT                 = 1; // 已邀请已同意
        
        public static final int   INVITE_STATE_REJECT                 = 2; // 已邀请已拒绝
        
        public static final int   INVITE_STATE_DELETE                 = 3; // 已删除
        
        // opratorCata(申请类型)
        public static final short OPRATOR_CATA_DEALER                 = 1; // 经绡商申请
        
        public static final short OPRATOR_CATA_BRAND                  = 2; // 品牌商邀请
    }
    
    public abstract static class BrandesInfoConst
    {
        // brandState(品牌状态)
        public static final short BRAND_STATE_UNAPPROVED = 0; // 未审核
        
        public static final short BRAND_STATE_APPROVED   = 1; // 已审核通过
        
        public static final short BRAND_STATE_COOPERATED = 2; // 已合作
        
        public static final short BRAND_STATE_CANCEL     = 3; // 已取消 （没有该状态）
        
        public static final short BRAND_STATE_EXPIRED    = 4; // 已过期
        
        public static final short BRAND_STATE_FAILURE    = 5; // 已失败
        
        // brandType(品牌类型)
        public static final short BRAND_TYPE_DOMESTIC    = 1; // 国内品牌
        
        public static final short BRAND_TYPE_FOREIGN     = 2; // 国外品牌
    }
    
    public abstract static class BrandMeetingConst
    {
        // auditState(审核状态)
        public static final short NONE_AUDIT    = 0; // 未审核
        
        public static final short PASS_AUDIT    = 1; // 审核通过
        
        public static final short NO_PASS_AUDIT = 2; // 审核不通过
        
        public static final short STOP_AUDIT    = 3; // 终止
        
        public static final short EXPIRED_AUDIT = 10; // 过期
    }
    
    public abstract static class BrandMeetingJoinConst
    {
        // joinState(审核状态)
        public static final short JOIN_STATE_UNCHECK = 0; // 未审核
        
        public static final short JOIN_STATE_ACCEPT  = 1; // 接受
        
        public static final short JOIN_STATE_REJECT  = 2; // 拒绝
    }
    
    public abstract static class BrandCheckConst
    {
        // checkState(审核状态)
        public static final short AGREE_COOPERATION  = 1; // 同意合作
        
        public static final short REJECT_COOPERATION = 2; // 拒绝合作
        
        public static final short STOP_COOPERATION   = 3; // 中止合作
    }
    
    public abstract static class BrandImgcate
    {
        // cateDefault 是否是默认分类
        public static final boolean CATE_NONE_DEFAULT = false; // 否
        
        public static final boolean CATE_DEFAULT      = true; // 是
    }
    
    public abstract static class BrandBank
    {
        public static final Short NONE_AUDIT    = 0; // 未审核
        
        public static final Short PASS_AUDIT    = 1; // 审核通过
        
        public static final Short NO_PASS_AUDIT = 2; // 审核不通过
    }
    
    public abstract static class DealDic
    {
        // 产品类目分类级别
        public static final short LEVEL_ONE   = 1; // 1级
        
        public static final short LEVEL_TWO   = 2; // 2级
        
        public static final short LEVEL_THREE = 3; // 3级
    }
    
    public abstract static class BrandNetworkConst
    {
        // showFlag(是否展厅中显示)
        public static final boolean FLAG_NOT_SHOW = false; // 否
        
        public static final boolean FLAG_SHOW     = true; // 是
    }
    
    public abstract static class BrandNetimgConst
    {
        // mainFlag(是否是主图)
        public static final boolean NETIMG_NOT_MAIN_FLAG = false; // 否
        
        public static final boolean NETIMG_MAIN_FLAG     = true; // 是
    }
    
    public abstract static class BrandInfoConst
    {
        // checkState(审核状态)
        public static final short CHECK_STATE_NONE_AUDIT = 0; // 未审核
        
        public static final short CHECK_STATE_PASS_AUDIT = 1; // 审核通过
        
        public static final short CHECK_STATE_NO_AUDIT   = 2; // 审核不通过
    }
    
    public abstract static class BrandsAuditConst
    {
        // checkState(审核状态)
        public static final short CHECK_STATE_PASS_AUDIT = 1; // 审核通过
        
        public static final short CHECK_STATE_NO_AUDIT   = 2; // 审核不通过
    }

    
    public abstract static class ProductInfoConst
    {
        public static final int ONSALE       = 1; // 上架产品
        
        public static final int PRE_ORDER    = 2; // 预定产品
        
        public static final int SOLE_OUT     = 3; // 下架产品
        
        public static final int STATE_STOPED = 1; // 终止合作
        
        public static final int STATE_NORMAL = 0; // 正常状态
    }
    
    public abstract static class BrandCountConst
    {
        public static final String[] BRANDCOUNT_COLUMN_NAME_ARY     = {"cooperCount", "applyCount", "inviteCount", "waitPayCount", "preOrderCount", "creditCount",
                                                                            "waitSendCount", "waitConfirmCount", "refundCount", "publishedCount", "waitPublishCount",
                                                                            "tightInventoryCount", "shortageCount", "prePublishedCount", "productCountline", "brandsCount",
                                                                            "viewDealerCount"};
        
        // 品牌商统计类型
        public static final int      BRANDCOUNT_COOPERCOUNT         = 0;                       // 合作中的经销商
        
        public static final int      BRANDCOUNT_APPLYCOUNT          = 1;                       // 申请中的经销商
        
        public static final int      BRANDCOUNT_INVITECOUNT         = 2;                       // 邀请中的经销商
        
        public static final int      BRANDCOUNT_WAITPAYCOUNT        = 3;                       // 等待付款订单数量
        
        public static final int      BRANDCOUNT_PREORDERCOUNT       = 4;                       // 预订产品订单数量
        
        public static final int      BRANDCOUNT_CREDITCOUNT         = 5;                       // 授信订单数量
        
        public static final int      BRANDCOUNT_WAITSENDCOUNT       = 6;                       // 待发货订单数量
        
        public static final int      BRANDCOUNT_WAITCONFIRECOUNT    = 7;                       // 已发货订单数量
        
        public static final int      BRANDCOUNT_REFUNDCOUNT         = 8;                       // 退款订单数量
        
        public static final int      BRANDCOUNT_PUBLISHEDCOUNT      = 9;                       // 已铺货产品数量
        
        public static final int      BRANDCOUNT_WAITPUBLISHCOUNT    = 10;                      // 未铺货产品数量
        
        public static final int      BRANDCOUNT_TIGHTINVENTORYCOUNT = 11;                      // 紧张库存产品数量
        
        public static final int      BRANDCOUNT_SHORTAGECOUNT       = 12;                      // 库存缺货产品数量
        
        public static final int      BRANDCOUNT_PREPUBLISHEDCOUNT   = 13;                      // 预订铺货产品数量
        
        public static final int      BRANDCOUNT_BRANDSCOUNT         = 14;                      // 品牌数量
        
        public static final int      BRANDCOUNT_VIEWDEALERCOUNT     = 15;                      // 查看经销商联系信息数量
        
        public static final int      BRAND_VIEW_COUNT               = 2000;                    // 品牌商加盟后能查看的经绡商总数
    }
    
    public abstract static class BrandProAttrValueConst
    {
        // operateCate(操作选项)
        public static final short OPERATE_CATE_SELECT   = 1; // 单选
        
        public static final short OPERATE_CATE_CHECKBOX = 2; // 多选
        
        public static final short OPERATE_CATE_TEXT     = 3; // 输入
    }
    
    public abstract static class ProductCatalogConst
    {
        public static final short LEVEL_ONE = 1; // 一级
        
        public static final short LEVEL_TWO = 2; // 二级
    }
    
    public abstract static class BrandViewContact
    {
        // viewType(查看类型)
        public static final short VIEW_TYPE_INITIATIVE  = 1; // 主动浏览
        
        public static final short VIEW_TYPE_APPLICATION = 2; // 经销商申请
        
        public static final short VIEW_TYPE__RECOMMEND  = 3; // 天下商邦人员撮合
    }
    
    public abstract static class BrandsCountConst
    {
        public static final String BRANDSCOUNT_JOINCOUNT        = "joinCount";       // 合作中的经销商
        
        public static final String BRANDSCOUNT_APPLYCOUNT       = "applyCount";      // 申请中的经销商
        
        public static final String BRANDSCOUNT_INVITECOUNT      = "inviteCount";     // 邀请中的经销商
        
        public static final String BRANDSCOUNT_ORDERCOUNT       = "orderNum";        // 发货订单数量
        
        public static final String BRANDSCOUNT_PRODUCTCOUNT     = "productCount";    // 产品数量
        
        public static final String BRANDSCOUNT_RANKING          = "ranking";         // 当前排名
        
        public static final String BRANDSCOUNT_FAVNUM           = "favNum";          // 收藏数量
    }
    
    public abstract static class BrandDocument
    {
        public static final String BRANDDOCUMENT_DOWNNUM = "downNum"; // 下载次数
    }
    
    public static class BrandDrawing
    {
        public static final short DRAWSTATE_0 = 0; // 申请等待处理
        
        public static final short DRAWSTATE_1 = 1; // 提现成功
        
        public static final short DRAWSTATE_3 = 3; // 财务处理中
        
        public static final short DRAWSTATE_5 = 5; // 已提交到银行
        
        public static final short DRAWSTATE_7 = 7; // 提现失败（失败原因备注说明）
        
        public static final short DRAWSTATE_9 = 9; // 取消提现
    }
    
    public static class BrandFrozen
    {
        public static final short FROZENSTATE_0 = 0; // 0：处理中
        
        public static final short FROZENSTATE_1 = 1; // 1：提现成功
        
        public static final short FROZENSTATE_2 = 2; // 2：提现失败
        
        public static final short FROZENSTATE_3 = 3; // 3：取消提现
    }
    
    // 品牌交易会报名
    public abstract static class TradeMeetJoin
    {
        /**
         * appointment 常量
         */
        public static final short SIGN_UP     = 0; // 报名
        
        public static final short APPOINTMENT = 1; // 预约
        
        /**
         * 用户类型：userType 常量
         */
        public static final short USER_TYPE_0 = 0; // 实体门店
        
        public static final short USER_TYPE_1 = 1; // 个人
        
        public static final short USER_TYPE_2 = 2; // 网店
        
        public static final short USER_TYPE_3 = 3; // 品牌厂商
    }
    
    public abstract static class BrandCount
    {
        public static final int VIEW_TOTAL_COUNT = 2000; // 下载次数
    }
    
    public abstract static class WebBrandsShow
    {
        public static final short SHOW_TYPE_ONE   = 1; // 感兴趣的品牌
        
        public static final short SHOW_TYPE_TWO   = 2; // 加盟入驻品牌推荐
        
        public static final short SHOW_TYPE_THREE = 3; // 新闻资讯知名品牌
        
        public static final short SHOW_TYPE_FOUR  = 4; // 推荐产品下方的品牌
        
        public static final short SHOW_TYPE_FIVE  = 5; // 推荐品牌
    }
    
    public abstract static class BrandUserm
    {
        // userSort 注册用户类型
        public static final short   USER_SORT_ORDINARY = 0; // 普通用户
        
        public static final short   USER_SORT_AGENT    = 1; // 代理商用户
        
        public static final short   USER_SORT_INTERNAL = 2; // 内部用户
        
        public static final Integer IS_BRAND           = 1; // 是品牌商
        
        public static final Integer NOT_BRAND          = 0; // 是品牌商
        
        public static final Integer IS_DEALER          = 1; // 是终端商
        
        public static final Integer NOT_DEALER         = 0; // 不是终端商
    }
    
    public abstract static class productPriceType
    {
        public static final Integer PRODUCT_DIR = 0; // 直供价
        
        public static final Integer PRODUCT_CRE = 1; // 授信价
        
        public static final Integer PRODUCT_SAM = 2; // 拿样价
    }
    
    public abstract static class BrandActivityList
    {
        // activityType 活动类型
        public static final short  ACTIVITY_TYPE_EXTERNAL         = 0;        // 内部活动
        
        public static final short  ACTIVITY_TYPE_INTERNAL         = 1;        // 外部活动
        
        // activityStateValue 活动状态
        public static final short  ACTIVITY_STATE_VALUE_UNRUN     = 0;        // 活动未开始
        
        public static final short  ACTIVITY_STATE_VALUE_RUN       = 1;        // 活动中
        
        public static final short  ACTIVITY_STATE_VALUE_END       = 2;        // 活动已结束
        
        // code 活动编码
        public static final String PRODUCT_CODE_PT                = "default"; // 普通现款现货
        
        public static final String ACTIVITY_CODE_BK               = "A00001"; // 爆款
        
        public static final String ACTIVITY_CODE_QC               = "A00002"; // 清仓
        
        public static final String ACTIVITY_CODE_GCD              = "A00003"; // 工厂店
        
        public static final Short  ACTIVITY_CODE_GCD_TYPE_GENERAL = 0;        // 工厂店活动'现款现货'产品
        
        public static final Short  ACTIVITY_CODE_GCD_TYPE_FACTORY = 1;        // 工厂店活动'铺货'产品
    }
    
/*    public abstract static class BrandActivityLine
    {
        // state 活动产品线审核状态
        public static final short ACTIVITY_LINE_STATE_NOCHECK = 0; // 未审核
        
        public static final short ACTIVITY_LINE_STATE_SUCCESS = 1; // 审核通过
        
        public static final short ACTIVITY_LINE_STATE_FAILURE = 2; // 审核不通过
    }*/
    
    public abstract static class WebProductShow
    {
        // showType 显示类型
        public static final String[] SHOW_TYPES_NAME   = {"首页推荐爆款类型"};
        
        public static final Short[]  SHOW_TYPES_INDEX  = {0};
        
        public static final Short    SHOW_TYPE_INDEXBK = 0;
    }
    
    public abstract static class WebInfoCount
    {
        // code 编码
        public static final String CODE_CBRZPP       = "CBRZPP";      // 筹备入驻品牌
        
        public static final String CODE_TJCPS        = "TJCPS";       // 提交产品数
        
        public static final String CODE_DCJMGX       = "DCJMGX";      // 达成加盟关系
        
        public static final String CODE_JSHK         = "JSHK";        // 节省货款
        
        public static final String CODE_LJJHJE       = "LJJHJE";      // 累计进货金额
        
        public static final String CODE_RZZDS        = "RZZDS";       // 入驻终端商
        
        public static final String CODE_TJJMSQ       = "TJJMSQ";      // 提交加盟申请
        
        public static final String CODE_FGQGSQ       = "FGQGSQ";      // 覆盖全国商圈
        
        public static final String CODE_BRANDS_COUNT = "BRANDS_COUNT"; // 品牌统计数
    }
    
    public abstract static class OrderShipRecord
    {
        // 发货来源
        public static final Short SOURCESTATE_ZTTX     = 0; // 交易平台
        
        public static final Short SOURCESTATE_BRANDERP = 1; // 品牌商erp
    }
    
    public abstract static class BrandPointBalance
    {
        public static final String DEFAULT_NAME = "普通商品";
        
        public static final Short  JOINFORM_XK = 1; //现款
        
        public static final Short  JOINFORM_SX = 2; //授信
        
        public static final Short  JOINFORM_FD = 3; //返点
    }
    
    public abstract static class BrandFreight
    {
        public static final Integer  CARRY_TYPE_EXPRESS           = 1;                                  // 快递
        
        public static final Integer  CARRY_TYPE_LOGISTICS         = 2;                                  // 物流
        
        public static final Integer  CARRY_TYPE_EXPRESS_COLLECT   = 3;                                  // 快递到付
        
        public static final Integer  CARRY_TYPE_LOGISTICS_COLLECT = 4;                                  // 物流到付
        
        public static final String   CARRY_NAME_EXPRESS           = "快递";
        
        public static final String   CARRY_NAME_LOGISTICS         = "物流";
        
        public static final String   CARRY_NAME_EXPRESS_COLLECT   = "快递到付";
        
        public static final String   CARRY_NAME_LOGISTICS_COLLECT = "物流到付";
        
        public static final Integer  CARRY_TYPE_FREE_FREIGHT      = 60;                                 // 包邮
        
        public static final Integer  CARRY_TYPE_CLEAN_FREIGHT     = 61;                                 // 运费
        
        public static final Integer  CARRY_TYPE_CLEAN_COLLECT     = 62;                                 // 到付
        
        public static final String   CARRY_NAME_FREE_FREIGHT      = "包邮";
        
        public static final String   CARRY_NAME_CLEAN_FREIGHT     = "运费";
        
        public static final String   CARRY_NAME_CLEAN_COLLECT     = "到付";
        
        public static final String[] CARRY_TYPE_NAME              = new String[]{null, CARRY_NAME_EXPRESS, CARRY_NAME_LOGISTICS, CARRY_NAME_EXPRESS_COLLECT,
                                                                          CARRY_NAME_LOGISTICS_COLLECT};
    }
    
    public abstract static class BrandFreightTemplate
    {
        public static final Short IS_DEFAULT_NO    = 0; // 不是默认
        
        public static final Short IS_DEFAULT_YES   = 1; // 是默认
        
        public static final Short IS_RECOMMEND_NO  = 0; // 不是推荐
        
        public static final Short IS_RECOMMEND_YES = 1; // 是推荐
    }
    
    public abstract static class BrandFreightDetail
    {
        public static final Short IS_DEFAULT_NO  = 0; // 不是默认
        
        public static final Short IS_DEFAULT_YES = 1; // 是默认
    }
    
    public abstract static class ProductInfoUpload
    {
        // excel name
        public final static String       EXCEL_NAME_TOP_DEAL_DIC              = "TOP_DEAL_DIC";
        
        public final static String       EXCEL_NAME_CHILD_DEAL_DIC            = "CHILD_DEAL_DIC_";
        
        public final static String       EXCEL_NAME_BRANDES_INFO              = "BRANDES_INFO";
        
        public final static String       EXCEL_NAME_PRODUCT_CATALOG           = "PRODUCT_CATALOG_";
        
        public final static String       EXCEL_NAME_PRODUCT_LINE              = "PRODUCT_LINE_";
        
        public final static String       EXCEL_NAME_BRAND_FREIGHT_TEMPLATE    = "BRAND_FREIGHT_TEMPLATE";
        
        public final static String       EXCEL_NAME_WEB_UNIT                  = "WEB_UNIT";
        
        public final static String       EXCEL_NAME_CPLX                      = "CPLX";
        
        public final static String       EXCEL_NAME_YFWL                      = "YFWL";
        
        public final static String       EXCEL_NAME_NY                        = "NY";
        
        public final static String       EXCEL_NAME_KSFS                      = "KSFS";
        
        public final static String       EXCEL_NAME_QTKJ                      = "QTKJ";
        
        public final static String       EXCEL_NAME_TJSZ                      = "TJSZ";
        
        // 表头名称 - 产品信息
        public final static String       HEAD_ERROR                           = "错误信息";
        
        public final static String       HEAD_YCLM                            = "一层类目";
        
        public final static String       HEAD_ECLM                            = "二层类目";
        
        public final static String       HEAD_PPXZ                            = "品牌选择";
        
        public final static String       HEAD_CPHH                            = "产品货号";
        
        public final static String       HEAD_CPBT                            = "产品标题";
        
        public final static String       HEAD_DW                              = "单位";
        
        public final static String       HEAD_KC                              = "库存";
        
        public final static String       HEAD_QYBH                            = "企业编码";
        
        public final static String       HEAD_TXM                             = "条形码";
        
        public final static String       HEAD_QPL                             = "起批量";
        
        public final static String       HEAD_PLSM                            = "批量说明";
        
        public final static String       HEAD_ZT                              = "主图（图片链接地址）";
        
        public final static String       HEAD_MS                              = "描述";
        
        public final static String       HEAD_CPFL                            = "产品分类";
        
        // public final static String HEAD_CPX = "产品线";
        public final static String       HEAD_ZL                              = "重量（kg）";
        
        public final static String       HEAD_CDYF                            = "承担运费";
        
        public final static String       HEAD_YFMB                            = "运费模版";
        
        public final static String       HEAD_THHCN                           = "退换货承诺";
        
        public final static String       HEAD_NY                              = "拿样";
        
        public final static String       HEAD_QTKJ                            = "前台可见";
        
        // 表头名称 - 销售属性
        public final static String       HEAD_CPXH                            = "产品序号";
        
        public final static String       HEAD_YS                              = "颜色";
        
        public final static String       HEAD_CM                              = "尺码";
        
        public final static String       HEAD_SL                              = "数量";
        
        public final static String       HEAD_DPJ                             = "吊牌价";
        
        public final static String       HEAD_ZGJ                             = "现款价";
        
        public final static String       HEAD_NYJ                              ="拿样价";
        
        public final static String       HEAD_SXJ                              ="授信价";
        // 表头名称列表 - 产品信息
        public final static List<String> LIST_PRODUCT_INFO_HEAD               = Lists.newArrayList(HEAD_YCLM, HEAD_ECLM, HEAD_PPXZ, HEAD_CPHH, HEAD_CPBT, HEAD_DW, 
                                                                                      HEAD_QPL, HEAD_PLSM, HEAD_ZT, HEAD_MS, HEAD_CPFL, HEAD_ZL,
                                                                                      HEAD_CDYF, HEAD_YFMB, HEAD_THHCN,  HEAD_QTKJ,HEAD_NYJ);
        
        // 表头名称列表 - 产品信息（必填）
        public final static List<String> LIST_PRODUCT_INFO_REQUIRED_HEAD      = Lists.newArrayList(HEAD_YCLM, HEAD_ECLM, HEAD_PPXZ, HEAD_CPHH, HEAD_CPBT, HEAD_DW,
                                                                                      HEAD_QPL, HEAD_ZT, HEAD_MS, HEAD_ZL, HEAD_CDYF, HEAD_YFMB, HEAD_QTKJ);
        
        // 表头名称列表 - 销售属性
        public final static List<String> LIST_PRODUCT_SALE_ATTR_HEAD          = Lists.newArrayList(HEAD_CPXH, HEAD_YS, HEAD_CM, HEAD_SL, HEAD_DPJ, HEAD_ZGJ,HEAD_SXJ);
        
        // 表头名称列表 - 销售属性（必填）
        public final static List<String> LIST_PRODUCT_SALE_ATTR_REQUIRED_HEAD = Lists.newArrayList(HEAD_CPXH, HEAD_YS, HEAD_CM, HEAD_SL, HEAD_DPJ, HEAD_ZGJ);
        
        // 表头名称列表1 - 产品信息
        public final static List<String> LIST_PRODUCT_INFO_HEAD1              = Lists.newArrayList(HEAD_YCLM, HEAD_ECLM, HEAD_PPXZ, HEAD_CPHH, HEAD_CPBT, HEAD_DW, 
                                                                                      HEAD_QPL, HEAD_PLSM, HEAD_ZT, HEAD_MS, HEAD_CPFL, HEAD_ZL,
                                                                                      HEAD_CDYF, HEAD_YFMB, HEAD_THHCN,  HEAD_QTKJ, HEAD_YS, HEAD_CM, HEAD_SL,
                                                                                      HEAD_DPJ, HEAD_ZGJ,HEAD_SXJ,HEAD_NYJ);
        
        // 表头名称列表1 - 产品信息（必填）
        public final static List<String> LIST_PRODUCT_INFO_REQUIRED_HEAD1     = Lists.newArrayList(HEAD_YCLM, HEAD_ECLM, HEAD_PPXZ, HEAD_CPHH, HEAD_CPBT, HEAD_DW,
                                                                                      HEAD_QPL, HEAD_ZT, HEAD_MS, HEAD_ZL, HEAD_CDYF, HEAD_YFMB,  HEAD_QTKJ,
                                                                                      HEAD_YS, HEAD_CM, HEAD_SL, HEAD_DPJ, HEAD_ZGJ);
                
        // 模板3表头名称列表
        public final static List<String> LIST_PRODUCT_INFO_HEAD_THREE          = Lists.newArrayList(HEAD_YCLM, HEAD_ECLM, HEAD_PPXZ, HEAD_CPHH, HEAD_CDYF, HEAD_YS, HEAD_CM,
                HEAD_SL, HEAD_DPJ, HEAD_ZGJ, HEAD_SXJ, HEAD_NYJ);
                
        // 模板3表头名称列表必填项
        public final static List<String> LIST_PRODUCT_INFO_REQUIRED_HEAD_THREE = Lists.newArrayList(HEAD_YCLM, HEAD_ECLM, HEAD_PPXZ, HEAD_CPHH, HEAD_CDYF, HEAD_YS, HEAD_CM,
                HEAD_SL, HEAD_DPJ, HEAD_ZGJ);
                
        // 需设置为文本的列表头名称列表
        public final static List<String> LIST_PRODUCT_INFO_HEAD_TEXT          = Lists.newArrayList(HEAD_CPHH,HEAD_CM, HEAD_SL, HEAD_DPJ, HEAD_ZGJ);
        
        // 表名称
        public final static String       SHEET_NAME_PRODUCT_INFO              = "产品信息";
        
        public final static String       SHEET_NAME_PRODUCT_INFO_SIMPLE       = "产品信息-简化";
        
        public final static String       SHEET_NAME_PRODUCT_SALE_ATTR         = "产品颜色尺码信息";
        
        public final static String       SHEET_NAME_DATA                      = "数据";
        
        // 导入文件的模板类型
        public final static Integer      IMPORT_TYPE_ONE                      = 0;                                                                                   // 产品信息和颜色尺码信息分开导入
        
        public final static Integer      IMPORT_TYPE_TWO                       = 1;
        
        public final static Integer      IMPORT_TYPE_THREE                     = 2;                                                                                         // 产品信息和颜色尺码信息1个Sheet导入
        
        // sku属性尺码的中文名称
        public final static String       CATEATTR_SIZE_NAME                   = "尺码";
    }
    
    public abstract static class BrandActivityProduct
    {
        public static final Short  FACTORY_STATE_UNAUDIT      = 0;                         // 未审
        
        public static final Short  FACTORY_STATE_PASSAUDIT1   = 1;                         // 一审通过
        
        public static final Short  FACTORY_STATE_PASSAUDIT2   = 2;                         // 二审通过
        
        public static final Short  FACTORY_STATE_UNPASSAUDIT1 = 3;                         // 一审拒绝
        
        public static final Short  FACTORY_STATE_UNPASSAUDIT2 = 4;                         // 二审拒绝
        
        public static final Short  FACTORY_STATE_REMOVE       = 5;                         // 移除
        
        /**
         * 活动产品参加状态
         */
        public static final Short  STATE_UNAUDIT              = 0;                         // 参加状态 未审
        
        public static final Short  STATE_PASSAUDIT            = 1;                         // 参加状态 已审
        
        public static final Short  STATE_UNPASSAUDIT          = 2;                         // 参加状态 审核失败
        
        /**
         * 调价生效方式
         */
        public static final Short  PRICE_EFFECT_TYPE_REALTIME = 0;                         // 实时库存生效
        
        /**
         * 价格设置
         */
        public static final Short  PRICE_SET_TYPE_UNIFIED     = 1;                         // 统一设置
        
        public static final Short  PRICE_SET_TYPE_ALONE       = 0;                         // 单独设置
        
        public static final String BOSS_ADJUST_PRICE_URL      = "/rpc/zttxAlterPrice/save"; // 支撑调价推送地址
    }
    
    public abstract static class ProductEdit
    {
        // 审核状态
        public static final Short STATE_UNAUDIT   = 0; // 待审
        
        public static final Short STATE_PASSAUDIT = 1; // 已审
    }
    
    public abstract static class ProductEditDetail
    {
        // 变更类型
        public static final Short    CHANGE_TYPE_PRODUCT_NO    = 0;                             // 产品货号
        
        public static final Short    CHANGE_TYPE_PRODUCT_COLOR = 1;                             // 颜色
        
        public static final Short    CHANGE_TYPE_PRODUCT_SIZE  = 2;                             // 尺码
        
        // 变更类型名称
        public static final String[] CHANGE_TYPE_NAMES         = new String[]{"货号", "颜色", "尺码"};
        
        // 审核状态
        public static final Short    STATE_UNAUDIT             = 0;                             // 未审
        
        public static final Short    STATE_PASSAUDIT           = 1;                             // 通过
        
        public static final Short    STATE_UNPASSAUDIT         = 2;                             // 拒绝
    }
    
    public class BrandInfo
    {
        public static final short CHECK_STATE_UNVERFIY = 0; // 0：未审核
        
        public static final short CHECK_STATE_SUCCESS  = 1; // 1：审核通过
        
        public static final short CHECK_STATE_FAILURE  = 2; // 2：审核不通过
    }
}
