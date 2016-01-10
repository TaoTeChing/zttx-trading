/*
 Navicat Premium Data Transfer

 Source Server         : 121.40.58.50(写库)
 Source Server Type    : MySQL
 Source Server Version : 50535
 Source Host           : localhost
 Source Database       : Zttx-Web-New

 Target Server Type    : MySQL
 Target Server Version : 50535
 File Encoding         : utf-8

 Date: 10/21/2015 20:44:45 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `RoleMenu`
-- ----------------------------
DROP TABLE IF EXISTS `RoleMenu`;
CREATE TABLE `RoleMenu` (
  `refrenceId` char(32) NOT NULL,
  `roleId` char(32) NOT NULL COMMENT '角色编码',
  `menuId` char(32) NOT NULL COMMENT '菜单编码',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单关联表';

-- ----------------------------
--  Table structure for `MenuInfo`
-- ----------------------------
DROP TABLE IF EXISTS `MenuInfo`;
CREATE TABLE `MenuInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `upMenuId` char(32) NOT NULL COMMENT '上层编号',
  `menuName` varchar(32) NOT NULL COMMENT '菜单名称',
  `menuAddr` varchar(128) NOT NULL COMMENT '菜单地址',
  `menuType` bit(1) NOT NULL COMMENT '菜单类型：\n0 指菜单\n1 指权限',
  `authority` varchar(32) DEFAULT NULL COMMENT '权限编码',
  `menuLevel` tinyint(4) DEFAULT NULL COMMENT '菜单级别',
  `menuOrder` int(11) DEFAULT NULL COMMENT '排序编号',
  `menuStyle` varchar(32) DEFAULT NULL COMMENT '菜单样式',
  `canTop` bit(1) DEFAULT NULL COMMENT '是否可在顶部显示（0：表是否，1：表是是）',
  `isSelect` bit(1) DEFAULT NULL COMMENT '是否可以设置1：可以， 0不可以',
  `shopMenu` bit(1) DEFAULT NULL COMMENT '是否是微店菜单0：否，1是',
  `menuOpen` bit(1) NOT NULL COMMENT '菜单是否展开',
  `showflag` bit(1) NOT NULL COMMENT '是否显示1：显示0：不显示',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间\n',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商、品牌商菜单信息';

-- ----------------------------
--  Table structure for `Adverts`
-- ----------------------------
DROP TABLE IF EXISTS `Adverts`;
CREATE TABLE `Adverts` (
  `refrenceId` char(32) NOT NULL DEFAULT '' COMMENT '资料编号',
  `advertId` varchar(32) NOT NULL COMMENT '广告位编号',
  `imgWeight` int(11) NOT NULL COMMENT '广告权重',
  `adTitle` varchar(64) NOT NULL COMMENT '广告标题',
  `urlAddress` varchar(128) NOT NULL COMMENT '链接地址',
  `domainName` varchar(128) DEFAULT NULL COMMENT '广告图片域名',
  `backgroundColor` varchar(64) DEFAULT NULL COMMENT '背景颜色： 一般是#000000 或 #990',
  `adLogo` varchar(128) DEFAULT NULL COMMENT '广告图片',
  `altMark` varchar(128) DEFAULT NULL COMMENT '图片/附档的alt描述',
  `beginTime` bigint(20) NOT NULL COMMENT '广告开始时间',
  `endTime` bigint(20) NOT NULL COMMENT '广告结束时间',
  `orderId` int(11) NOT NULL DEFAULT '1' COMMENT '排序字段',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `target` varchar(32) DEFAULT NULL COMMENT '打开方式',
  `delFlag` tinyint(4) NOT NULL COMMENT '删除状态0：正常1：删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告';

-- ----------------------------
--  Table structure for `AdvertPosit`
-- ----------------------------
DROP TABLE IF EXISTS `AdvertPosit`;
CREATE TABLE `AdvertPosit` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `advertName` varchar(32) NOT NULL COMMENT '广告位名称',
  `advertDesc` varchar(256) NOT NULL COMMENT '广告描述',
  `advertKey` varchar(32) NOT NULL COMMENT '广告位key',
  `viewNum` int(11) DEFAULT '1' COMMENT '显示数量',
  `advertCate` tinyint(4) NOT NULL COMMENT '广告类别（1：图片广告，2：标题广告）',
  `imgWidth` int(11) DEFAULT NULL COMMENT '图片宽',
  `imgHeight` int(11) DEFAULT NULL COMMENT '图片高',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告位置管理';

-- ----------------------------
--  Table structure for `RoleInfo`
-- ----------------------------
DROP TABLE IF EXISTS `RoleInfo`;
CREATE TABLE `RoleInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `roleCode` char(20) NOT NULL COMMENT '角色编码',
  `roleName` varchar(32) NOT NULL COMMENT '角色名称',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL,
  `canDel` bit(1) DEFAULT b'1' COMMENT '是否允许删除\r\n0：不允许\r\n1：允许',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息';

-- ----------------------------
--  Table structure for `ClientKey`
-- ----------------------------
DROP TABLE IF EXISTS `ClientKey`;
CREATE TABLE `ClientKey` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `accessType` char(3) DEFAULT NULL COMMENT '接入类型\n001 交易平台\n002 文件服务器',
  `userKey` char(32) DEFAULT NULL COMMENT '认证钥匙',
  `userLimit` bit(1) DEFAULT NULL COMMENT '白名单限制',
  `createTime` bigint(20) NOT NULL COMMENT '申请时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '申请者IP',
  `checkState` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态（0：未审核，1：审核通过，2：审核不通过)',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='API接入码';

-- ----------------------------
--  Table structure for `WebDefTemplate`
-- ----------------------------
DROP TABLE IF EXISTS `WebDefTemplate`;
CREATE TABLE `WebDefTemplate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `modelName` varchar(32) NOT NULL COMMENT '模块名字',
  `modelKey` varchar(32) NOT NULL COMMENT '模块Key',
  `subDesc` varchar(256) NOT NULL COMMENT '简介',
  `htmlText` text COMMENT '模块内容',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面模版定义';

-- ----------------------------
--  Table structure for `Adjustment`
-- ----------------------------
DROP TABLE IF EXISTS `Adjustment`;
CREATE TABLE `Adjustment` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商调价主键',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商Id',
  `dealerId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '经销商Id',
  `adjustAllPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '批量调价价格总计',
  `createTime` bigint(20) NOT NULL COMMENT '调价创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='调价信息主表';

-- ----------------------------
--  Table structure for `Adjustments`
-- ----------------------------
DROP TABLE IF EXISTS `Adjustments`;
CREATE TABLE `Adjustments` (
  `refrenceId` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '调价表主键',
  `brandAdjustId` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '品牌商调价Id',
  `productId` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '产品Id',
  `productSkuId` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '调价产品的skuId',
  `oldFacSkuDirPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '原工厂店sku直供价',
  `nowFacSkuDirPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '现工厂店直sku供价',
  `quantity` int(11) NOT NULL DEFAULT '0' COMMENT 'sku现有库存量',
  `totalAdjustPrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'sku总 优惠/加价金额(+为加价,-为优惠: (现价-原价)*库存量)',
  `createTime` bigint(20) NOT NULL COMMENT '智慧门店调价时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调价详细信息表';

-- ----------------------------
--  Table structure for `ArticleCate`
-- ----------------------------
DROP TABLE IF EXISTS `ArticleCate`;
CREATE TABLE `ArticleCate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateName` varchar(16) NOT NULL COMMENT '类别名称',
  `domainName` varchar(32) NOT NULL COMMENT '图标域名',
  `cateIcon` varchar(128) DEFAULT NULL COMMENT '类别图标',
  `cateMark` varchar(128) DEFAULT NULL COMMENT '类别描述',
  `parentId` varchar(32) DEFAULT NULL COMMENT '上层编号',
  `cateOrder` int(11) NOT NULL COMMENT '排序编号',
  `cateLevel` tinyint(4) NOT NULL COMMENT '类别层级',
  `articleNum` int(11) NOT NULL DEFAULT '0' COMMENT '文章数量',
  `moduleFile` varchar(32) DEFAULT NULL COMMENT '模板文件',
  `htmlFile` varchar(64) DEFAULT NULL COMMENT '静态文件',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站资讯类别';

-- ----------------------------
--  Table structure for `ArticleInfo`
-- ----------------------------
DROP TABLE IF EXISTS `ArticleInfo`;
CREATE TABLE `ArticleInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateId` char(32) NOT NULL COMMENT '类别编号',
  `articleTitle` varchar(63) NOT NULL COMMENT '文章主题',
  `articleText` text NOT NULL COMMENT '文章内容',
  `domainName` varchar(127) DEFAULT NULL COMMENT '图片域名',
  `articleImage` varchar(127) DEFAULT NULL COMMENT '图片地址',
  `articleSource` varchar(32) DEFAULT NULL COMMENT '文章来源',
  `articleAuthor` varchar(32) DEFAULT NULL COMMENT '文章作者',
  `viewNum` int(11) DEFAULT '0' COMMENT '查看次数',
  `shareNum` int(11) DEFAULT '0' COMMENT '分享次数',
  `isTop` bit(1) NOT NULL COMMENT '是否置顶',
  `isHead` bit(1) NOT NULL COMMENT '是否头条',
  `isComment` bit(1) NOT NULL COMMENT '是否推荐',
  `isHot` bit(1) NOT NULL COMMENT '是否热门',
  `commentNum` int(11) DEFAULT '0' COMMENT '评论次数',
  `collectNum` int(11) DEFAULT '0' COMMENT '收藏次数',
  `createTime` bigint(20) NOT NULL COMMENT '发布时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态（0：未删除，1：已删除）',
  PRIMARY KEY (`refrenceId`),
  KEY `articleInfo_cateId` (`cateId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资讯文章信息';

-- ----------------------------
--  Table structure for `BrandAddress`
-- ----------------------------
DROP TABLE IF EXISTS `BrandAddress`;
CREATE TABLE `BrandAddress` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `storeId` char(32) NOT NULL COMMENT '仓库名称',
  `userName` varchar(32) NOT NULL COMMENT '发货人员',
  `areaNo` int(11) NOT NULL COMMENT '所在地区',
  `street` varchar(128) NOT NULL COMMENT '街道地址',
  `userTel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `userMobile` char(11) DEFAULT NULL COMMENT '手机号码',
  `addressMark` varchar(256) DEFAULT NULL COMMENT '备注说明',
  `sendDefault` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认',
  `returnDefault` bit(1) NOT NULL DEFAULT b'0' COMMENT '默认退货地址',
  `zipCode` varchar(10) DEFAULT NULL,
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商发货地址信息';

-- ----------------------------
--  Table structure for `BrandAdvice`
-- ----------------------------
DROP TABLE IF EXISTS `BrandAdvice`;
CREATE TABLE `BrandAdvice` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `adviceText` varchar(5120) NOT NULL COMMENT '建议内容',
  `createTime` bigint(20) NOT NULL COMMENT '建议时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商优化建议';

-- ----------------------------
--  Table structure for `BrandAlbum`
-- ----------------------------
DROP TABLE IF EXISTS `BrandAlbum`;
CREATE TABLE `BrandAlbum` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `domainName` varchar(32) NOT NULL COMMENT '服务器域名',
  `photoName` varchar(64) NOT NULL COMMENT '图片原名称',
  `imageName` varchar(128) NOT NULL COMMENT '图片新名称',
  `createTime` bigint(20) NOT NULL COMMENT '上传时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '上传者IP',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌相册信息';

-- ----------------------------
--  Table structure for `BrandAudit`
-- ----------------------------
DROP TABLE IF EXISTS `BrandAudit`;
CREATE TABLE `BrandAudit` (
  `refrenceId` char(32) NOT NULL,
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandName` varchar(100) NOT NULL COMMENT '品牌商名称',
  `userId` char(32) NOT NULL COMMENT '审核人员编号',
  `checkTime` bigint(20) NOT NULL COMMENT '审核时间',
  `checkState` tinyint(4) NOT NULL COMMENT '审核状态',
  `checkMark` varchar(64) NOT NULL COMMENT '不通过原因',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商审核日志';

-- ----------------------------
--  Table structure for `BrandBuySerLog`
-- ----------------------------
DROP TABLE IF EXISTS `BrandBuySerLog`;
CREATE TABLE `BrandBuySerLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandName` varchar(120) DEFAULT NULL COMMENT '品牌商名称',
  `serialNumber` bigint(20) NOT NULL COMMENT '支付流水号',
  `serviceId` char(32) NOT NULL COMMENT '服务编号',
  `servicerCate` tinyint(4) NOT NULL COMMENT '服务类别\r\n            ',
  `buyTime` bigint(20) NOT NULL COMMENT '购买时间',
  `buyNum` int(11) NOT NULL COMMENT '购买数量',
  `buyMoney` decimal(11,2) NOT NULL COMMENT '购买的金额',
  `beginTime` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `endTime` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `buyState` tinyint(4) NOT NULL COMMENT '支付状态\r\n            0：生成\r\n            1：提交支付\r\n            2：支付成功\r\n            3：支付失败',
  `chargType` tinyint(4) NOT NULL COMMENT '1：续期\r\n            2：增加数量',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商购买的服务记录';

-- ----------------------------
--  Table structure for `BrandBuyService`
-- ----------------------------
DROP TABLE IF EXISTS `BrandBuyService`;
CREATE TABLE `BrandBuyService` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandName` varchar(128) DEFAULT NULL COMMENT '品牌商名称',
  `serviceId` char(32) DEFAULT NULL COMMENT '服务编号',
  `servicerCate` tinyint(4) NOT NULL COMMENT '服务类别',
  `chargType` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1：续期\r\n            2：增加数量',
  `buyTime` bigint(20) DEFAULT NULL COMMENT '购买时间',
  `beginTime` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `endTime` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `buyMoney` decimal(11,2) DEFAULT NULL COMMENT '购买金额',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商购买的服务';

-- ----------------------------
--  Table structure for `BrandCard`
-- ----------------------------
DROP TABLE IF EXISTS `BrandCard`;
CREATE TABLE `BrandCard` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `certName` varchar(64) NOT NULL COMMENT '证书名称',
  `domainName` varchar(32) NOT NULL COMMENT '证书所在域名',
  `certPhoto` varchar(64) NOT NULL COMMENT '证书图片(旧）',
  `certImage` varchar(128) NOT NULL COMMENT '证书图片(新)',
  `certMark` varchar(2048) NOT NULL COMMENT '证书描述',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '上传者IP',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商证书信息';

-- ----------------------------
--  Table structure for `BrandCatelog`
-- ----------------------------
DROP TABLE IF EXISTS `BrandCatelog`;
CREATE TABLE `BrandCatelog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `dealNo` int(11) NOT NULL COMMENT '品类编码',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商主营品类';

-- ----------------------------
--  Table structure for `BrandCert`
-- ----------------------------
DROP TABLE IF EXISTS `BrandCert`;
CREATE TABLE `BrandCert` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandesId` char(32) NOT NULL COMMENT '品牌编号',
  `domainName` varchar(64) NOT NULL COMMENT '证书域名',
  `fileName` varchar(64) NOT NULL COMMENT '证书原名称',
  `imageName` varchar(128) NOT NULL COMMENT '证书新名称',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `delFlag` bit(1) NOT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌证书';

-- ----------------------------
--  Table structure for `BrandCheck`
-- ----------------------------
DROP TABLE IF EXISTS `BrandCheck`;
CREATE TABLE `BrandCheck` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `checkTime` bigint(20) NOT NULL COMMENT '审核时间',
  `checkState` tinyint(4) NOT NULL COMMENT '审核状态（1：同意合作，2：拒绝合作，3：中止合作）',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商审核经销商加盟申请日志';

-- ----------------------------
--  Table structure for `BrandContact`
-- ----------------------------
DROP TABLE IF EXISTS `BrandContact`;
CREATE TABLE `BrandContact` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` varchar(32) DEFAULT NULL COMMENT '品牌编号',
  `userName` varchar(32) NOT NULL COMMENT '联系人员',
  `userPhoto` varchar(128) DEFAULT NULL COMMENT '头像',
  `userMail` varchar(64) DEFAULT NULL COMMENT '邮件地址',
  `userGender` tinyint(4) NOT NULL COMMENT '性别',
  `userJob` varchar(32) DEFAULT NULL COMMENT '职务',
  `userIm` varchar(16) DEFAULT NULL COMMENT '业务qq',
  `userMobile` char(11) DEFAULT NULL COMMENT '手机号码',
  `userTelphone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `userFax` varchar(20) DEFAULT NULL COMMENT '传真号码',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商联系方式';

-- ----------------------------
--  Table structure for `BrandCount`
-- ----------------------------
DROP TABLE IF EXISTS `BrandCount`;
CREATE TABLE `BrandCount` (
  `refrenceId` char(32) NOT NULL COMMENT '品牌商编号',
  `cooperCount` int(11) DEFAULT '0' COMMENT '合作中的经销商',
  `applyCount` int(11) DEFAULT '0' COMMENT '申请中的经销商',
  `inviteCount` int(11) DEFAULT '0' COMMENT '邀请中的经销商',
  `waitPayCount` int(11) DEFAULT '0' COMMENT '等待付款订单数量',
  `preOrderCount` int(11) DEFAULT '0' COMMENT '预订产品订单数量',
  `creditCount` int(11) DEFAULT '0' COMMENT '授信订单数量',
  `waitSendCount` int(11) DEFAULT '0' COMMENT '待发货订单数量',
  `waitConfirmCount` int(11) DEFAULT '0' COMMENT '已发货订单数量',
  `refundCount` int(11) DEFAULT '0' COMMENT '退款订单数量',
  `publishedCount` int(11) DEFAULT '0' COMMENT '已铺货产品数量',
  `waitPublishCount` int(11) DEFAULT '0' COMMENT '未铺货产品数量',
  `tightInventoryCount` int(11) DEFAULT '0' COMMENT '紧张库存产品数量',
  `shortageCount` int(11) DEFAULT '0' COMMENT '库存缺货产品数量',
  `prePublishedCount` int(11) DEFAULT '0' COMMENT '预订铺货产品数量',
  `viewDealerCount` int(11) DEFAULT '0' COMMENT '查看经销商联系信息数量',
  `brandsCount` int(11) DEFAULT '0' COMMENT '品牌数量',
  `viewDealerTotal` int(11) DEFAULT NULL COMMENT '查看经销商联系信息总数量',
  `createTime` bigint(20) DEFAULT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商计数信息';

-- ----------------------------
--  Table structure for `BrandCrm`
-- ----------------------------
DROP TABLE IF EXISTS `BrandCrm`;
CREATE TABLE `BrandCrm` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `json` text NOT NULL COMMENT 'json组合',
  `jsonType` tinyint(4) NOT NULL COMMENT '组合类型\r\n            1:branduserm\r\n            2:brandInfo\r\n            3:brandcontact\r\n            4:dealeruserm\r\n            5:dealerinfo\r\n            6:brandesInfo',
  `createTime` bigint(20) NOT NULL COMMENT '生成时间',
  `brandState` tinyint(4) NOT NULL COMMENT '1：未获取\r\n            2：已获取',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商更新信息表CRM';

-- ----------------------------
--  Table structure for `BrandCRMLog`
-- ----------------------------
DROP TABLE IF EXISTS `BrandCRMLog`;
CREATE TABLE `BrandCRMLog` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '日志记录Id',
  `operator` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT 'crm操作人',
  `operatorId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'crm操作人Id',
  `brandName` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'crm免登陆品牌商用户名',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'crm免登陆品牌商用户Id',
  `brandesName` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌的名称',
  `brandesId` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌的Id',
  `beOperationName` tinyint(4) NOT NULL COMMENT '操作分类(1:产品,2:产品线,3:交易管理,4:终端商管理)',
  `operation` tinyint(4) NOT NULL COMMENT '对操作分类的详细操作',
  `operationDetails` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '操作详情',
  `createTime` bigint(20) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='支撑(crm免登陆到品牌商后台)对品牌操作日志';

-- ----------------------------
--  Table structure for `BrandDeal`
-- ----------------------------
DROP TABLE IF EXISTS `BrandDeal`;
CREATE TABLE `BrandDeal` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandesId` char(32) NOT NULL COMMENT '品牌编号',
  `dealNo` int(11) NOT NULL COMMENT '品类编码',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`),
  KEY `brandDeal_brandesId` (`brandesId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌主营品类';

-- ----------------------------
--  Table structure for `BrandDefMenu`
-- ----------------------------
DROP TABLE IF EXISTS `BrandDefMenu`;
CREATE TABLE `BrandDefMenu` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `menuCode` int(11) NOT NULL COMMENT '菜单编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商自定义导航';

-- ----------------------------
--  Table structure for `BrandDoccate`
-- ----------------------------
DROP TABLE IF EXISTS `BrandDoccate`;
CREATE TABLE `BrandDoccate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `cateName` varchar(32) NOT NULL COMMENT '类别名称',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `orderId` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商文档类别信息';

-- ----------------------------
--  Table structure for `BrandDocopen`
-- ----------------------------
DROP TABLE IF EXISTS `BrandDocopen`;
CREATE TABLE `BrandDocopen` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `docId` char(32) NOT NULL COMMENT '文档编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商资料公开';

-- ----------------------------
--  Table structure for `BrandDocument`
-- ----------------------------
DROP TABLE IF EXISTS `BrandDocument`;
CREATE TABLE `BrandDocument` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `cateId` char(32) NOT NULL COMMENT '资料类别(对应BrandDoccate的主键编号）',
  `docName` varchar(64) NOT NULL COMMENT '资料名称',
  `domainName` varchar(32) NOT NULL COMMENT '文档域名',
  `docoFile` varchar(64) DEFAULT NULL COMMENT '资料文档(旧名)',
  `docnFile` varchar(128) DEFAULT NULL COMMENT '资料文档(新名)',
  `webAddress` varchar(128) DEFAULT NULL COMMENT '网盘地址',
  `docPass` varchar(32) DEFAULT NULL COMMENT '资料密码',
  `docMark` varchar(4096) NOT NULL COMMENT '内容描述',
  `docOpen` bit(1) NOT NULL COMMENT '完全公开',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL DEFAULT '0' COMMENT '上传者IP地址',
  `downNum` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商资料信息';

-- ----------------------------
--  Table structure for `BrandesInfo`
-- ----------------------------
DROP TABLE IF EXISTS `BrandesInfo`;
CREATE TABLE `BrandesInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '品牌编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsName` varchar(32) NOT NULL COMMENT '品牌名称',
  `brandType` tinyint(4) DEFAULT '1' COMMENT '品牌类型1：自有品牌2：授权品牌',
  `logoDomin` varchar(32) NOT NULL COMMENT 'logo域名',
  `brandLogo` varchar(128) NOT NULL COMMENT '品牌logo',
  `proLogo` varchar(128) DEFAULT NULL COMMENT '品牌产品形象图',
  `brandHold` tinyint(4) NOT NULL COMMENT '品牌持有人',
  `holdName` varchar(64) NOT NULL COMMENT '持有人名称',
  `brandSubMark` varchar(1024) DEFAULT NULL COMMENT '品牌文字介绍',
  `brandMark` text NOT NULL COMMENT '品牌介绍',
  `brandState` tinyint(4) NOT NULL DEFAULT '0' COMMENT '品牌状态 0：未审核 1：已审核通过 2：已经合作 4：过期 5:已失败（审核不通过）',
  `beginTime` bigint(20) DEFAULT NULL COMMENT '服务开始时间',
  `endTime` bigint(20) DEFAULT NULL COMMENT '服务结束时间',
  `ensureMoney` decimal(10,2) DEFAULT NULL COMMENT '保证金金额',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除状态',
  `showed` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示 0:不显示,1显示',
  `recommendImage` varchar(128) DEFAULT NULL COMMENT '推荐图片',
  `meetCount` int(11) DEFAULT '0' COMMENT '满足条件数量',
  `factoryStore` bit(1) NOT NULL COMMENT '是否为工厂店品牌',
  `deposit` decimal(10,2) DEFAULT '0.00' COMMENT '活动押金',
  `barCodeNum` varchar(6) DEFAULT NULL COMMENT '条形码助记码',
  `userAuth` varchar(200) DEFAULT NULL COMMENT '用户认证',
  PRIMARY KEY (`refrenceId`),
  KEY `BrandesInfo_brandId` (`brandId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商品牌信息';

-- ----------------------------
--  Table structure for `BrandesInfoRegional`
-- ----------------------------
DROP TABLE IF EXISTS `BrandesInfoRegional`;
CREATE TABLE `BrandesInfoRegional` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandesId` char(32) NOT NULL COMMENT '品牌编号',
  `areaNo` int(11) NOT NULL DEFAULT '0' COMMENT '地区编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `brandesInfoRegional_brandId` (`brandId`) USING BTREE,
  KEY `brandesInfoRegional_brandesId` (`brandesId`) USING BTREE,
  KEY `brandesInfoRegional_areaNo` (`areaNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商品牌地区列表';

-- ----------------------------
--  Table structure for `BrandFavorite`
-- ----------------------------
DROP TABLE IF EXISTS `BrandFavorite`;
CREATE TABLE `BrandFavorite` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `collectTime` bigint(20) NOT NULL COMMENT '收藏时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商收藏的经销商';

-- ----------------------------
--  Table structure for `BrandFreightDetail`
-- ----------------------------
DROP TABLE IF EXISTS `BrandFreightDetail`;
CREATE TABLE `BrandFreightDetail` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `templateId` char(32) NOT NULL COMMENT '运费模板ID',
  `settingsId` char(32) NOT NULL COMMENT '运费设置ID',
  `carryType` int(11) NOT NULL COMMENT '运送方式',
  `firstWeight` decimal(11,3) NOT NULL COMMENT '首重',
  `firstPrice` decimal(11,2) NOT NULL COMMENT '首重价格',
  `extendWeight` decimal(11,3) NOT NULL COMMENT '续重',
  `extendPrice` decimal(11,2) NOT NULL COMMENT '续重价格',
  `isDefault` tinyint(4) NOT NULL COMMENT '是否默认（0：否，1：是）',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运费明细表';

-- ----------------------------
--  Table structure for `BrandFreightRegion`
-- ----------------------------
DROP TABLE IF EXISTS `BrandFreightRegion`;
CREATE TABLE `BrandFreightRegion` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `templateId` char(32) NOT NULL COMMENT '运费模板ID',
  `detailId` char(32) NOT NULL COMMENT '明细ID',
  `areaNo` int(11) DEFAULT NULL COMMENT '收货地区编号',
  `createTime` bigint(11) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `areaName` varchar(100) DEFAULT NULL,
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运费区域表';

-- ----------------------------
--  Table structure for `BrandFreightSettings`
-- ----------------------------
DROP TABLE IF EXISTS `BrandFreightSettings`;
CREATE TABLE `BrandFreightSettings` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `templateId` char(32) NOT NULL COMMENT '运费模板ID',
  `carryType` int(11) NOT NULL COMMENT '运送方式（1：快递，2：物流，3：快递到付，4：物流到付）',
  `carryTypeName` varchar(32) NOT NULL COMMENT '运送方式名称',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运费设置表';

-- ----------------------------
--  Table structure for `BrandFreightTemplate`
-- ----------------------------
DROP TABLE IF EXISTS `BrandFreightTemplate`;
CREATE TABLE `BrandFreightTemplate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) DEFAULT NULL COMMENT '品牌商ID（推荐模板值为null）',
  `name` varchar(100) NOT NULL COMMENT '模板名称',
  `areaNo` int(11) NOT NULL COMMENT '发货地区编号',
  `isDefault` tinyint(4) NOT NULL COMMENT '是否默认（0：否，1：是）',
  `isRecommend` tinyint(4) NOT NULL COMMENT '是否为推荐模板（0：否，1：是）',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运费模板表';

-- ----------------------------
--  Table structure for `BrandFrozen`
-- ----------------------------
DROP TABLE IF EXISTS `BrandFrozen`;
CREATE TABLE `BrandFrozen` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `drawId` char(32) DEFAULT NULL COMMENT '冻结编号',
  `frozenType` tinyint(4) NOT NULL COMMENT '冻结类型（1：提现冻结，2...）',
  `amount` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '冻结金额',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `frozenState` tinyint(4) NOT NULL COMMENT '冻结状态（0：处理中，1：提现成功，2：提现失败）',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  `flag` int(11) NOT NULL DEFAULT '0' COMMENT '迁移标记',
  `delFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商资金冻结记录';

-- ----------------------------
--  Table structure for `BrandImage`
-- ----------------------------
DROP TABLE IF EXISTS `BrandImage`;
CREATE TABLE `BrandImage` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `cateId` char(32) NOT NULL COMMENT '类别编号',
  `domainName` varchar(32) NOT NULL COMMENT '图片域名',
  `photoName` varchar(64) NOT NULL COMMENT '图片原名称',
  `imageName` varchar(128) NOT NULL COMMENT '图片新名称',
  `imageMark` varchar(64) DEFAULT NULL COMMENT '图片说明',
  `imageSize` int(11) DEFAULT '0' COMMENT '图片大小 单位KB',
  `createTime` bigint(20) NOT NULL COMMENT '上传时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '上传者IP',
  `delFlag` bit(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商图片管理';

-- ----------------------------
--  Table structure for `BrandImgcate`
-- ----------------------------
DROP TABLE IF EXISTS `BrandImgcate`;
CREATE TABLE `BrandImgcate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `cateName` varchar(32) NOT NULL COMMENT '类别名称',
  `parentId` varchar(32) DEFAULT NULL COMMENT '上层编号',
  `cateOrder` int(11) DEFAULT NULL COMMENT '排序编号',
  `cateDefault` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认分类（0：否，1：是）',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商图片分类信息';

-- ----------------------------
--  Table structure for `BrandInfo`
-- ----------------------------
DROP TABLE IF EXISTS `BrandInfo`;
CREATE TABLE `BrandInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '品牌商编号',
  `comName` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `comType` tinyint(4) DEFAULT NULL COMMENT '企业类型',
  `dealType` tinyint(4) DEFAULT NULL COMMENT '经营类型',
  `domainName` varchar(32) DEFAULT NULL COMMENT '图片域名',
  `brandPhoto` varchar(64) DEFAULT NULL COMMENT '执照图片原名(正)',
  `brandImage` varchar(128) DEFAULT NULL COMMENT '执照图片新名(正)',
  `userPhoto` varchar(128) DEFAULT NULL COMMENT '身份证正',
  `userImage` varchar(128) DEFAULT NULL COMMENT '身份证反',
  `comNum` varchar(20) DEFAULT NULL COMMENT '营业执照编号',
  `idNum` varchar(18) DEFAULT NULL COMMENT '身份证编号',
  `legalName` varchar(32) DEFAULT NULL COMMENT '法人代表',
  `regMoney` decimal(10,2) DEFAULT NULL COMMENT '注册资金',
  `areaNo` int(11) DEFAULT NULL COMMENT '企业地址',
  `provinceName` varchar(32) DEFAULT NULL COMMENT '省份名称',
  `cityName` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `areaName` varchar(32) DEFAULT NULL COMMENT '区域名称',
  `comAddress` varchar(128) DEFAULT NULL COMMENT '详细地址',
  `comWeb` varchar(128) DEFAULT NULL COMMENT '企业网址',
  `comMark` text COMMENT '企业介绍',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `checkState` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态（0：未审核，1：审核通过，2：审核不通过）',
  `emploeeNum` tinyint(16) DEFAULT NULL COMMENT '公司规模',
  `moneyNum` tinyint(4) DEFAULT NULL COMMENT '年营业额',
  `barCodeNum` varchar(6) DEFAULT NULL COMMENT '条形码助记码',
  `delFlag` bit(1) NOT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商基本信息';

-- ----------------------------
--  Table structure for `BrandInvite`
-- ----------------------------
DROP TABLE IF EXISTS `BrandInvite`;
CREATE TABLE `BrandInvite` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `opratorCata` tinyint(4) NOT NULL COMMENT '操作类型（1：经销商申请，2：品牌商邀请）',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `dealerName` varchar(100) NOT NULL COMMENT '经销商名称',
  `brandName` varchar(100) NOT NULL COMMENT '品牌商名称',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `brandsName` varchar(32) NOT NULL COMMENT '品牌名称',
  `domainName` varchar(32) NOT NULL COMMENT '品牌logo域名',
  `brandsLogo` varchar(128) NOT NULL COMMENT '品牌logo',
  `areaNo` int(11) NOT NULL COMMENT '品牌商区域编码',
  `provinceName` varchar(32) NOT NULL COMMENT '品牌商所在省份',
  `cityName` varchar(32) NOT NULL COMMENT '品牌商所在城市',
  `areaName` varchar(32) DEFAULT NULL COMMENT '品牌商所在县区',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `inviteTime` bigint(20) NOT NULL COMMENT '邀请时间',
  `inviteText` varchar(512) DEFAULT NULL COMMENT '邀请内容',
  `inviteState` tinyint(1) DEFAULT NULL COMMENT 'inviteState（邀请状态）0：已邀请未回复，1：已邀请已同意，2：已邀请已拒绝，3：已删除',
  `applyState` tinyint(4) DEFAULT NULL COMMENT '0：未审核，1：审核通过，2：审核不通过，3：邀请加盟，4：经销商中止合作，5:撤消申请，6：品牌商中止合作）',
  `undoTime` bigint(20) DEFAULT NULL COMMENT '经销商撤消申请时间',
  `auditTime` bigint(20) DEFAULT NULL COMMENT '品牌商审核时间',
  `auditMark` varchar(512) DEFAULT NULL COMMENT '审核不通过原因',
  `readState` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支撑系统是否已读：0、未读　1、已读',
  `sourceType` tinyint(4) DEFAULT NULL COMMENT '来源类型： 0、交易平台 1、支撑系统',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商邀请经销商加盟';

-- ----------------------------
--  Table structure for `BrandLevel`
-- ----------------------------
DROP TABLE IF EXISTS `BrandLevel`;
CREATE TABLE `BrandLevel` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `levelName` varchar(32) NOT NULL COMMENT '等级名称',
  `levelMark` varchar(1024) NOT NULL COMMENT '等级描述',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商等级';

-- ----------------------------
--  Table structure for `BrandLicening`
-- ----------------------------
DROP TABLE IF EXISTS `BrandLicening`;
CREATE TABLE `BrandLicening` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandesId` char(32) NOT NULL COMMENT '品牌编号',
  `domainName` varchar(64) NOT NULL COMMENT '证书域名',
  `fileName` varchar(64) NOT NULL COMMENT '证书原名称',
  `imageName` varchar(128) NOT NULL COMMENT '证书新名称',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌授权证书';

-- ----------------------------
--  Table structure for `BrandLogistics`
-- ----------------------------
DROP TABLE IF EXISTS `BrandLogistics`;
CREATE TABLE `BrandLogistics` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主账户编号',
  `logisticName` varchar(32) NOT NULL COMMENT '物流公司名称',
  `logisticType` int(11) NOT NULL DEFAULT '1',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商物流公司';

-- ----------------------------
--  Table structure for `BrandMessage`
-- ----------------------------
DROP TABLE IF EXISTS `BrandMessage`;
CREATE TABLE `BrandMessage` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) DEFAULT NULL COMMENT '品牌商编号',
  `dealerId` char(32) DEFAULT NULL COMMENT '经销商编号',
  `orderId` varchar(20) DEFAULT NULL COMMENT '订单编号',
  `msgCate` tinyint(4) NOT NULL COMMENT '消息类型',
  `msgTitle` varchar(128) NOT NULL COMMENT '消息主题',
  `msgText` varchar(5120) NOT NULL COMMENT '消息内容',
  `areaNo` int(6) DEFAULT NULL COMMENT '留言-用户性别',
  `userName` varchar(32) DEFAULT NULL COMMENT '留言-用户姓名',
  `userMobile` varchar(12) DEFAULT NULL COMMENT '留言-手机号',
  `userContact` varchar(128) DEFAULT NULL COMMENT '留言-联系方式 QQ/Email',
  `userGender` int(2) DEFAULT NULL COMMENT '留言-区域',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `refuseReply` bit(1) NOT NULL COMMENT '是否拒绝回复',
  `replyId` char(32) DEFAULT NULL COMMENT '回复的消息编号',
  `replyTime` bigint(20) DEFAULT NULL COMMENT '回复的时间',
  `replyText` varchar(5120) DEFAULT NULL COMMENT '回复的内容',
  `userId` varchar(32) DEFAULT NULL COMMENT '回复人编号',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `BrandMessage_brandId` (`brandId`) USING BTREE,
  KEY `BrandMessage_dealerId` (`dealerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商消息管理';

-- ----------------------------
--  Table structure for `BrandNetimg`
-- ----------------------------
DROP TABLE IF EXISTS `BrandNetimg`;
CREATE TABLE `BrandNetimg` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `networkId` char(32) NOT NULL COMMENT '经销商网络编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `domainName` varchar(32) NOT NULL COMMENT '所在域名',
  `photoName` varchar(64) NOT NULL COMMENT '图片原名称',
  `imageName` varchar(128) NOT NULL COMMENT '图片新名称',
  `mainFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否主图（0：非主图，1：主图）',
  `uploadTime` bigint(20) NOT NULL COMMENT '上传时间',
  `uploadIp` int(11) NOT NULL COMMENT '上传者IP',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商经销网络图片信息';

-- ----------------------------
--  Table structure for `BrandNetwork`
-- ----------------------------
DROP TABLE IF EXISTS `BrandNetwork`;
CREATE TABLE `BrandNetwork` (
  `refrenceId` char(32) NOT NULL DEFAULT '' COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `dealerId` char(32) DEFAULT NULL COMMENT '经销商主帐号编号',
  `dealerName` varchar(100) NOT NULL COMMENT '经销商名称',
  `levelId` char(32) DEFAULT NULL COMMENT '经销商等级编号',
  `userName` varchar(32) NOT NULL COMMENT '联系人',
  `telphone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `mobile` char(11) DEFAULT NULL COMMENT '手机号码',
  `provinceName` varchar(32) DEFAULT NULL COMMENT '省名称',
  `cityName` varchar(32) DEFAULT NULL COMMENT '城市名称',
  `areaName` varchar(32) DEFAULT NULL COMMENT '区名称',
  `areaNo` int(11) NOT NULL COMMENT '所在区域',
  `address` varchar(128) NOT NULL COMMENT '所在地址',
  `showFlag` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否在展厅显示（0：不显示，1：显示）',
  `wholePercent` int(11) DEFAULT NULL COMMENT '完整度',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌经销网络';

-- ----------------------------
--  Table structure for `BrandNews`
-- ----------------------------
DROP TABLE IF EXISTS `BrandNews`;
CREATE TABLE `BrandNews` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `newsTitle` varchar(128) NOT NULL COMMENT '新闻标题',
  `cateId` char(32) NOT NULL COMMENT '资讯类别编号',
  `imageDomin` varchar(32) DEFAULT NULL COMMENT '图片域名',
  `imageUrl` varchar(128) NOT NULL COMMENT '图片地址',
  `newsSummary` varchar(256) NOT NULL COMMENT '新闻摘要',
  `newsContent` text NOT NULL COMMENT '新闻内容',
  `cronTime` bigint(20) NOT NULL COMMENT '定时发布',
  `hitNum` int(11) NOT NULL DEFAULT '0' COMMENT '点击次数',
  `interestNum` int(11) NOT NULL DEFAULT '0' COMMENT '关注次数',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商新闻资讯';

-- ----------------------------
--  Table structure for `BrandNewscate`
-- ----------------------------
DROP TABLE IF EXISTS `BrandNewscate`;
CREATE TABLE `BrandNewscate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateName` varchar(32) NOT NULL COMMENT '类别名称',
  `cateMark` varchar(64) DEFAULT NULL COMMENT '类别说明',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌资讯类别';

-- ----------------------------
--  Table structure for `BrandPaylog`
-- ----------------------------
DROP TABLE IF EXISTS `BrandPaylog`;
CREATE TABLE `BrandPaylog` (
  `refrenceId` char(32) CHARACTER SET utf8 NOT NULL COMMENT '资料编号',
  `brandId` char(32) CHARACTER SET utf8 NOT NULL COMMENT '品牌商编号',
  `oldWord` char(64) CHARACTER SET utf8 NOT NULL COMMENT '原密码',
  `newWord` char(64) CHARACTER SET utf8 NOT NULL COMMENT '新密码',
  `oldSalt` char(11) CHARACTER SET utf8 NOT NULL COMMENT '旧盐值',
  `newSalt` char(11) CHARACTER SET utf8 NOT NULL COMMENT '新盐值',
  `createTime` bigint(20) NOT NULL COMMENT '修改时间',
  `createType` tinyint(4) NOT NULL COMMENT '修改类型',
  `createIp` int(11) NOT NULL COMMENT '修改者IP',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='支付密码修改历史';

-- ----------------------------
--  Table structure for `BrandPayword`
-- ----------------------------
DROP TABLE IF EXISTS `BrandPayword`;
CREATE TABLE `BrandPayword` (
  `refrenceId` char(32) NOT NULL COMMENT '同品牌商编号对应',
  `dealerTel` char(11) NOT NULL COMMENT '手机号码',
  `payWord` char(64) NOT NULL COMMENT '支付密码',
  `paySalt` char(11) NOT NULL COMMENT '密码盐值',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `flag` int(11) NOT NULL DEFAULT '0' COMMENT '迁移标记',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商支付密码';

-- ----------------------------
--  Table structure for `BrandPhoto`
-- ----------------------------
DROP TABLE IF EXISTS `BrandPhoto`;
CREATE TABLE `BrandPhoto` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandesId` char(32) NOT NULL COMMENT '品牌编号',
  `domainName` varchar(32) NOT NULL,
  `photoName` varchar(64) NOT NULL COMMENT '照片原名称',
  `imageName` varchar(128) NOT NULL COMMENT '照片新名称',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `createIP` int(11) NOT NULL COMMENT '建档IP',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌形象照片';

-- ----------------------------
--  Table structure for `BrandPointBalance`
-- ----------------------------
DROP TABLE IF EXISTS `BrandPointBalance`;
CREATE TABLE `BrandPointBalance` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `point` decimal(11,4) NOT NULL COMMENT '扣点',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `joinForm` tinyint(4) DEFAULT '1' COMMENT '加盟方式：1 现款，2受信',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态(0、正常　1、删除)',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='扣点表';

-- ----------------------------
--  Table structure for `BrandPointBalanceLog`
-- ----------------------------
DROP TABLE IF EXISTS `BrandPointBalanceLog`;
CREATE TABLE `BrandPointBalanceLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌ID',
  `content` varchar(512) NOT NULL COMMENT '内容',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='扣点修改日志';

-- ----------------------------
--  Table structure for `BrandRead`
-- ----------------------------
DROP TABLE IF EXISTS `BrandRead`;
CREATE TABLE `BrandRead` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `msgId` char(32) NOT NULL COMMENT '消息编号',
  `readTime` bigint(20) NOT NULL COMMENT '阅读时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态(0：未删除，１：已删除）',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商消息阅读';

-- ----------------------------
--  Table structure for `BrandRecruit`
-- ----------------------------
DROP TABLE IF EXISTS `BrandRecruit`;
CREATE TABLE `BrandRecruit` (
  `refrenceId` char(32) NOT NULL COMMENT '品牌编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `recruitTitle` varchar(128) NOT NULL COMMENT '招募书标题',
  `dealBrand` tinyint(4) NOT NULL COMMENT '经营品牌',
  `dealExper` tinyint(4) NOT NULL COMMENT '开店经验',
  `dealShop` tinyint(4) NOT NULL COMMENT '实体店',
  `dealYear` tinyint(4) NOT NULL COMMENT '经营年限',
  `recruitState` tinyint(4) NOT NULL COMMENT '招募书状态',
  `recruitText` text NOT NULL COMMENT '招募内容',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `userId` char(32) NOT NULL COMMENT '编辑人员',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌招募书';

-- ----------------------------
--  Table structure for `BrandRoom`
-- ----------------------------
DROP TABLE IF EXISTS `BrandRoom`;
CREATE TABLE `BrandRoom` (
  `refrenceId` char(32) NOT NULL COMMENT '品牌编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `roomName` varchar(64) NOT NULL COMMENT '展厅名称',
  `domainName` varchar(32) NOT NULL COMMENT 'logo域名',
  `logoPhoto` varchar(64) NOT NULL COMMENT 'logo原名称',
  `logoImage` varchar(128) NOT NULL COMMENT 'logo新名称',
  `brandMark` text NOT NULL COMMENT '品牌介绍',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `userId` char(32) NOT NULL COMMENT '编辑人员（当前登录的品牌商人员编号）',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商展厅信息';

-- ----------------------------
--  Table structure for `BrandsAudit`
-- ----------------------------
DROP TABLE IF EXISTS `BrandsAudit`;
CREATE TABLE `BrandsAudit` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userId` char(32) DEFAULT NULL COMMENT '审核人员编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `brandsNames` varchar(32) NOT NULL COMMENT '品牌名称',
  `logoName` varchar(128) DEFAULT NULL COMMENT '品牌logo',
  `domainName` varchar(32) DEFAULT NULL COMMENT '品牌logo域名',
  `checkTime` bigint(20) NOT NULL COMMENT '审核时间',
  `checkState` tinyint(4) NOT NULL COMMENT '审核状态(1：审核通过，2：审核不通过)',
  `checkMark` varchar(64) DEFAULT NULL COMMENT '审核不通过原因',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志(0：未删除，1：已删除)',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌审核日志';

-- ----------------------------
--  Table structure for `BrandsCount`
-- ----------------------------
DROP TABLE IF EXISTS `BrandsCount`;
CREATE TABLE `BrandsCount` (
  `refrenceId` char(32) NOT NULL,
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `joinCount` int(11) DEFAULT '0' COMMENT '合作中的经销商',
  `applyCount` int(11) DEFAULT '0' COMMENT '申请中的经销商',
  `inviteCount` int(11) DEFAULT '0' COMMENT '邀请中的经销商',
  `orderNum` int(11) DEFAULT '0' COMMENT '发货订单数量',
  `productCount` int(11) DEFAULT '0' COMMENT '产品数量',
  `createTime` bigint(20) DEFAULT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `ranking` int(11) DEFAULT '1' COMMENT '当前排名',
  `favNum` int(11) DEFAULT '0' COMMENT '收藏数量',
  `viewNum` int(11) DEFAULT NULL COMMENT '点击数量',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌计数信息';

-- ----------------------------
--  Table structure for `BrandsDomain`
-- ----------------------------
DROP TABLE IF EXISTS `BrandsDomain`;
CREATE TABLE `BrandsDomain` (
  `refrenceId` char(32) NOT NULL COMMENT '品牌编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌id',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `Domain` varchar(20) NOT NULL COMMENT '品牌域名',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌域名';

-- ----------------------------
--  Table structure for `BrandService`
-- ----------------------------
DROP TABLE IF EXISTS `BrandService`;
CREATE TABLE `BrandService` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `serviceName` varchar(100) NOT NULL COMMENT '客服名字',
  `userId` char(32) DEFAULT NULL COMMENT '客服编号(对应CRM客服编号）',
  `serviceImage` varchar(128) DEFAULT NULL COMMENT '客服照片',
  `domainName` varchar(32) DEFAULT NULL COMMENT '客服域名',
  `userGender` tinyint(4) NOT NULL COMMENT '客服性别',
  `jobNum` char(4) DEFAULT NULL COMMENT '工号',
  `serviceTel` varchar(16) DEFAULT NULL COMMENT '客服电话',
  `serviceMobile` varchar(11) DEFAULT NULL COMMENT '客服手机',
  `serviceCate` tinyint(4) NOT NULL COMMENT '以后扩展用，默认为1',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态\r\n            0：未删除\r\n            1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商客服信息';

-- ----------------------------
--  Table structure for `BrandStore`
-- ----------------------------
DROP TABLE IF EXISTS `BrandStore`;
CREATE TABLE `BrandStore` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `storeName` char(64) NOT NULL COMMENT '仓库名称',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商品牌仓库信息';

-- ----------------------------
--  Table structure for `BrandsVideo`
-- ----------------------------
DROP TABLE IF EXISTS `BrandsVideo`;
CREATE TABLE `BrandsVideo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `videoName` varchar(128) NOT NULL COMMENT '视频地址',
  `position` varchar(10) DEFAULT '' COMMENT '视频位置\r\n',
  `width` int(11) NOT NULL COMMENT '视频宽',
  `height` int(11) NOT NULL COMMENT '视频高',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `uploadTime` bigint(20) NOT NULL COMMENT '上传时间',
  `uploadIp` int(11) NOT NULL COMMENT '上传者IP',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态0：未删除，1：删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌模特视频';

-- ----------------------------
--  Table structure for `BrandTemplate`
-- ----------------------------
DROP TABLE IF EXISTS `BrandTemplate`;
CREATE TABLE `BrandTemplate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `templateName` varchar(64) NOT NULL COMMENT '模板名称',
  `orderStart` bigint(20) NOT NULL COMMENT '订货时间',
  `orderEnd` bigint(20) NOT NULL COMMENT '订货结束时间',
  `startNum` int(11) NOT NULL COMMENT '起批量',
  `orderNum` int(11) NOT NULL COMMENT '订货量',
  `outStart` bigint(20) NOT NULL COMMENT '出货开始时间',
  `outEnd` bigint(20) NOT NULL COMMENT '出货结束时间',
  `orderSelect` tinyint(4) DEFAULT NULL COMMENT '订单选择',
  `orderMoney` tinyint(4) DEFAULT NULL COMMENT '订单金额',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌预订模板';

-- ----------------------------
--  Table structure for `BrandViewContact`
-- ----------------------------
DROP TABLE IF EXISTS `BrandViewContact`;
CREATE TABLE `BrandViewContact` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `viewTime` bigint(20) NOT NULL COMMENT '查看时间',
  `viewType` tinyint(4) NOT NULL COMMENT '查看类型（1：主动浏览，2：经销商申请，3：天下商邦人员撮合）',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商查看经销商联系信息';

-- ----------------------------
--  Table structure for `BrandVisited`
-- ----------------------------
DROP TABLE IF EXISTS `BrandVisited`;
CREATE TABLE `BrandVisited` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandName` varchar(100) NOT NULL COMMENT '品牌商名称',
  `dealerName` varchar(100) NOT NULL COMMENT '经销商名称',
  `areaNo` int(11) NOT NULL COMMENT '地区编码',
  `provinceName` varchar(32) NOT NULL COMMENT '经销商所在省份名称',
  `cityName` varchar(32) NOT NULL COMMENT '经销商所在城市名称',
  `areaName` varchar(32) NOT NULL COMMENT '经销商所在区域名称',
  `viewNum` int(11) NOT NULL COMMENT '查看次数',
  `viewTime` bigint(20) NOT NULL COMMENT '最后查看时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商浏览记录';

-- ----------------------------
--  Table structure for `BrandVisual`
-- ----------------------------
DROP TABLE IF EXISTS `BrandVisual`;
CREATE TABLE `BrandVisual` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `domainName` varchar(32) NOT NULL COMMENT '服务器域名',
  `vedioDoc` varchar(64) DEFAULT NULL COMMENT '视频原文件',
  `vedioFile` varchar(128) DEFAULT NULL COMMENT '视频文件',
  `threeDoc` varchar(64) DEFAULT NULL COMMENT '3D原文件',
  `threeFile` varchar(128) DEFAULT NULL COMMENT '3D展厅',
  `brandMark` text NOT NULL COMMENT '描述内容',
  `createTime` bigint(20) NOT NULL COMMENT '上传时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '上传者IP',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌视觉信息';

-- ----------------------------
--  Table structure for `CateAttribute`
-- ----------------------------
DROP TABLE IF EXISTS `CateAttribute`;
CREATE TABLE `CateAttribute` (
  `refrenceId` varchar(18) NOT NULL,
  `attributeName` varchar(32) NOT NULL,
  `attributeDesc` varchar(256) DEFAULT NULL,
  `attributeNo` varchar(18) DEFAULT NULL,
  `operateCate` tinyint(4) DEFAULT '1' COMMENT '操作选项（1：单选，2：多选，3：可输入，只针对产品属性）',
  `sortOrder` int(11) NOT NULL,
  `state` tinyint(2) NOT NULL COMMENT '启用1;停用0',
  `delFlag` bit(1) NOT NULL COMMENT '0:未删除,1:已删除',
  `isImgAttr` bit(1) DEFAULT NULL COMMENT '是否是颜色属性0：否1：是',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目属性表';

-- ----------------------------
--  Table structure for `CateAttributeItem`
-- ----------------------------
DROP TABLE IF EXISTS `CateAttributeItem`;
CREATE TABLE `CateAttributeItem` (
  `refrenceId` varchar(18) NOT NULL,
  `attributeId` varchar(18) NOT NULL,
  `attributeItem` varchar(32) NOT NULL,
  `attributeIcon` varchar(128) DEFAULT NULL COMMENT '属性值图标',
  `attributeOrder` int(11) NOT NULL,
  `state` tinyint(2) NOT NULL COMMENT '启用1停用0',
  `delFlag` bit(1) NOT NULL COMMENT '0:未删除,1:已删除',
  PRIMARY KEY (`refrenceId`),
  KEY `CateAttributeItem_attributeId` (`attributeId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性项表';

-- ----------------------------
--  Table structure for `CateAttributeItemRel`
-- ----------------------------
DROP TABLE IF EXISTS `CateAttributeItemRel`;
CREATE TABLE `CateAttributeItemRel` (
  `refrenceId` varchar(18) NOT NULL,
  `attributeId` varchar(18) NOT NULL COMMENT '属性ID',
  `attributeItemId` varchar(18) NOT NULL COMMENT '属性项ID',
  `cateNo` varchar(18) NOT NULL COMMENT '类目No',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`refrenceId`),
  KEY `CateAttributeItemRel_attributeItemId` (`attributeItemId`) USING BTREE,
  KEY `CateAttributeItemRel_cateNo` (`cateNo`) USING BTREE,
  KEY `CateAttributeItemRel_attributeId` (`attributeId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目商品属性项关系表';

-- ----------------------------
--  Table structure for `CateAttributeRel`
-- ----------------------------
DROP TABLE IF EXISTS `CateAttributeRel`;
CREATE TABLE `CateAttributeRel` (
  `refrenceId` varchar(18) NOT NULL,
  `attributeId` varchar(18) DEFAULT NULL,
  `cateNo` varchar(18) DEFAULT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `isSkuAttr` bit(1) NOT NULL COMMENT '0 否;1 是',
  `enableSearch` bit(1) NOT NULL COMMENT '0 否;1 是',
  `enableAlias` bit(1) NOT NULL COMMENT '0 否;1 是',
  `isMultiSelect` bit(1) NOT NULL COMMENT '0 否;1 是',
  `valueType` varchar(18) NOT NULL COMMENT '1 字符；2日期；3 布尔',
  `sortOrder` int(11) NOT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `CateAttributeRel_attributeId` (`attributeId`) USING BTREE,
  KEY `CateAttributeRel_cateNo` (`cateNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类目商品属性关系表';

-- ----------------------------
--  Table structure for `ComplainNumber`
-- ----------------------------
DROP TABLE IF EXISTS `ComplainNumber`;
CREATE TABLE `ComplainNumber` (
  `complainId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '投诉流水号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  PRIMARY KEY (`complainId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='投诉流水号';

-- ----------------------------
--  Table structure for `DataDict`
-- ----------------------------
DROP TABLE IF EXISTS `DataDict`;
CREATE TABLE `DataDict` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dictName` varchar(32) NOT NULL COMMENT '字典名称',
  `dictCode` varchar(32) NOT NULL COMMENT '字典编码',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `dictDesc` varchar(512) DEFAULT NULL,
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标识0：未删除 1：删除 ',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
--  Table structure for `DataDictValue`
-- ----------------------------
DROP TABLE IF EXISTS `DataDictValue`;
CREATE TABLE `DataDictValue` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dictid` char(32) NOT NULL COMMENT '字典编号',
  `dictCode` varchar(32) DEFAULT NULL COMMENT '字典编码',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `dictValueName` varchar(32) NOT NULL COMMENT '字典值名称',
  `dictValue` varchar(32) NOT NULL COMMENT '字典值',
  `dictOrder` int(11) NOT NULL COMMENT '排序字段',
  `remark` varchar(64) DEFAULT NULL COMMENT '描述',
  `delFlag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典值';

-- ----------------------------
--  Table structure for `DealDic`
-- ----------------------------
DROP TABLE IF EXISTS `DealDic`;
CREATE TABLE `DealDic` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealNo` int(11) NOT NULL COMMENT '品类编号',
  `dealName` varchar(32) NOT NULL COMMENT '品类名称',
  `dealIcon` varchar(128) DEFAULT NULL COMMENT '品类图标',
  `parentId` varchar(32) DEFAULT NULL COMMENT '上层编号',
  `dealLevel` tinyint(4) NOT NULL COMMENT '品类级别',
  `dealOrder` int(11) NOT NULL COMMENT '排序编号',
  `productNum` int(11) DEFAULT NULL COMMENT '该品类下的产品数量',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  `productWeight` decimal(11,2) DEFAULT NULL COMMENT '默认产品重量',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`refrenceId`),
  KEY `DealDic_dealNo` (`dealNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌经营品类信息';

-- ----------------------------
--  Table structure for `DealerAddr`
-- ----------------------------
DROP TABLE IF EXISTS `DealerAddr`;
CREATE TABLE `DealerAddr` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `dealerName` varchar(32) NOT NULL COMMENT '收货人姓名',
  `dealerAddr` int(6) NOT NULL COMMENT '收货人所在地区',
  `dealerAddress` varchar(128) NOT NULL COMMENT '收货人街道地址',
  `provinceName` varchar(32) NOT NULL COMMENT '省份名称',
  `cityName` varchar(32) NOT NULL COMMENT '城市名称',
  `areaName` varchar(32) NOT NULL COMMENT '区域名称',
  `postCode` char(6) DEFAULT NULL COMMENT '邮政编码',
  `dealerMobile` char(11) DEFAULT NULL COMMENT '手机号码',
  `dealerTel` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `dealerDefault` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否默认地址',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商地址信息';

-- ----------------------------
--  Table structure for `DealerAdvice`
-- ----------------------------
DROP TABLE IF EXISTS `DealerAdvice`;
CREATE TABLE `DealerAdvice` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `adviceText` varchar(5120) NOT NULL COMMENT '建议内容',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商优化建议';

-- ----------------------------
--  Table structure for `DealerApply`
-- ----------------------------
DROP TABLE IF EXISTS `DealerApply`;
CREATE TABLE `DealerApply` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `areaNo` int(11) NOT NULL,
  `areaName` varchar(32) NOT NULL,
  `brandName` varchar(100) NOT NULL,
  `brandsName` varchar(32) NOT NULL,
  `applyUser` varchar(32) NOT NULL COMMENT '申请人',
  `applyText` varchar(5120) NOT NULL COMMENT '留言内容',
  `applyTime` bigint(20) NOT NULL COMMENT '申请时间',
  `auditState` tinyint(4) DEFAULT NULL COMMENT 'auditState（品牌商审核状态）0：未审核，1：审核通过，2：审核不通过，3：邀请加盟，4：经销商中止合作,5:撤消申请',
  `undoTime` bigint(20) DEFAULT NULL COMMENT '撤销时间',
  `auditTime` bigint(20) DEFAULT NULL COMMENT '品牌商审核时间',
  `auditMark` varchar(512) DEFAULT NULL,
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商加盟申请';

-- ----------------------------
--  Table structure for `DealerAudit`
-- ----------------------------
DROP TABLE IF EXISTS `DealerAudit`;
CREATE TABLE `DealerAudit` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userId` char(32) NOT NULL COMMENT '审核人员编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `dealerName` varchar(100) NOT NULL COMMENT '经销商名称',
  `checkTime` bigint(20) NOT NULL COMMENT '审核时间',
  `checkState` tinyint(4) NOT NULL COMMENT '审核状态（1：通过，2：不通过）',
  `checkMark` varchar(64) NOT NULL COMMENT '审核不通过说明',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商审核日志';

-- ----------------------------
--  Table structure for `DealerBuySerLog`
-- ----------------------------
DROP TABLE IF EXISTS `DealerBuySerLog`;
CREATE TABLE `DealerBuySerLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `dealerName` varchar(128) DEFAULT NULL COMMENT '经销商名称',
  `serialNumber` bigint(20) NOT NULL COMMENT '支付流水号',
  `serviceId` char(32) NOT NULL COMMENT '服务编号',
  `servicerCate` tinyint(4) NOT NULL COMMENT '服务类别\r\n            ',
  `buyTime` bigint(20) NOT NULL COMMENT '购买时间',
  `buyNum` int(11) NOT NULL COMMENT '购买数量',
  `buyMoney` decimal(11,2) NOT NULL COMMENT '购买的金额',
  `beginTime` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `endTime` bigint(20) DEFAULT NULL,
  `buyState` tinyint(4) NOT NULL COMMENT '支付状态\r\n            0：生成\r\n            1：提交支付\r\n            2：支付成功\r\n            3：支付失败',
  `chargType` tinyint(4) NOT NULL COMMENT '1：续期\r\n            2：增加数量',
  `addressId` varchar(32) DEFAULT NULL,
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商购买的服务记录';

-- ----------------------------
--  Table structure for `DealerBuyService`
-- ----------------------------
DROP TABLE IF EXISTS `DealerBuyService`;
CREATE TABLE `DealerBuyService` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '服务商名称',
  `dealerName` varchar(128) DEFAULT NULL,
  `serviceId` char(32) DEFAULT NULL COMMENT '服务编号',
  `servicerCate` tinyint(4) NOT NULL COMMENT '服务类别',
  `chargType` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1：续期\r\n            2：增加数量',
  `buyTime` bigint(20) DEFAULT NULL COMMENT '购买时间',
  `beginTime` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `endTime` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `buyMoney` decimal(11,2) DEFAULT NULL COMMENT '购买金额',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商购买的服务';

-- ----------------------------
--  Table structure for `DealerClass`
-- ----------------------------
DROP TABLE IF EXISTS `DealerClass`;
CREATE TABLE `DealerClass` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `dealNo` int(11) NOT NULL COMMENT '品类编码',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商经营品类l';

-- ----------------------------
--  Table structure for `DealerClearing`
-- ----------------------------
DROP TABLE IF EXISTS `DealerClearing`;
CREATE TABLE `DealerClearing` (
  `refrenceId` varchar(32) NOT NULL COMMENT '主键',
  `brandId` varchar(32) DEFAULT NULL COMMENT '品牌商编号',
  `dealerId` varchar(32) DEFAULT NULL COMMENT '经销商编号',
  `brandsId` varchar(32) DEFAULT NULL COMMENT '品牌编号',
  `clearingStatus` bit(1) DEFAULT b'0' COMMENT '结算状态(0:未结算,1:已结算)',
  `clearingTime` bigint(20) DEFAULT NULL COMMENT '结算时间',
  `salesAmount` decimal(11,2) DEFAULT '0.00' COMMENT '销售金额	',
  `clearingAmount` decimal(11,2) DEFAULT NULL COMMENT '结算金额',
  `clearingVolume` bigint(11) DEFAULT NULL COMMENT '结算-销量',
  `isClearingAmount` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已经摊到授信中（0否，1是）',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工厂店品牌信息结算表';

-- ----------------------------
--  Table structure for `DealerClearingPay`
-- ----------------------------
DROP TABLE IF EXISTS `DealerClearingPay`;
CREATE TABLE `DealerClearingPay` (
  `refrenceId` varchar(32) NOT NULL COMMENT '主键',
  `dealerId` varchar(32) NOT NULL COMMENT '经销商Id',
  `brandId` varchar(32) NOT NULL COMMENT '品牌商Id',
  `payClearingAmount` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '当期支付金额',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志（0：未 删除1：已删除）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `DealerCollect`
-- ----------------------------
DROP TABLE IF EXISTS `DealerCollect`;
CREATE TABLE `DealerCollect` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `areaNo` int(11) NOT NULL COMMENT '品牌商地区编码',
  `areaName` varchar(32) NOT NULL COMMENT '品牌商所在地区名称',
  `collectTime` bigint(20) NOT NULL COMMENT '收藏时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商品牌收藏夹';

-- ----------------------------
--  Table structure for `DealerComplaint`
-- ----------------------------
DROP TABLE IF EXISTS `DealerComplaint`;
CREATE TABLE `DealerComplaint` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` char(32) NOT NULL COMMENT '订单编号',
  `complaintNumberId` bigint(20) NOT NULL COMMENT '投诉单号',
  `orderNumber` bigint(20) DEFAULT NULL COMMENT '友好订单编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `complaintCause` smallint(6) DEFAULT NULL COMMENT '投诉原因',
  `complaintName` varchar(128) DEFAULT NULL COMMENT '投诉原因名',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `comState` tinyint(4) DEFAULT NULL COMMENT '投诉状态（0：等待处理，1：客服介入，2：处理完成，3：经销商撤消投诉）',
  `interposeTime` bigint(20) DEFAULT NULL COMMENT '客服介入的时间',
  `brandDesc` varchar(512) DEFAULT NULL COMMENT '品牌商陈述',
  `complaintResult` varchar(512) DEFAULT NULL COMMENT '客服处理结果',
  `createtime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商投诉信息';

-- ----------------------------
--  Table structure for `DealerCount`
-- ----------------------------
DROP TABLE IF EXISTS `DealerCount`;
CREATE TABLE `DealerCount` (
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `joinCount` int(11) DEFAULT '0' COMMENT '已经加盟的品牌数',
  `applyCount` int(11) DEFAULT '0' COMMENT '申请中的品牌数',
  `inviteCount` int(11) DEFAULT '0' COMMENT '邀请中的品牌数',
  `balanceCount` int(11) DEFAULT '0' COMMENT '待结算订单数量',
  `waitConfirmCount` int(11) DEFAULT '0' COMMENT '待收货订单数量',
  `refundCount` int(11) DEFAULT '0' COMMENT '退款中的订单数',
  `sysMessageCount` int(11) DEFAULT '0' COMMENT '未读系统消息',
  `warningCount` int(11) DEFAULT '0' COMMENT '未读预警消息',
  `factoryJoinCount` int(11) DEFAULT '0' COMMENT '工厂店已加盟数量',
  `factoryHasPaid` int(11) DEFAULT '0' COMMENT '工厂店已缴押金数量',
  `factoryNonPaid` int(11) DEFAULT '0' COMMENT '工厂店未缴押金数量',
  `createtime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`dealerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商计数信息';

-- ----------------------------
--  Table structure for `DealerCRMLog`
-- ----------------------------
DROP TABLE IF EXISTS `DealerCRMLog`;
CREATE TABLE `DealerCRMLog` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `operator` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'crm操作人',
  `operatorId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'crm操作人Id',
  `dealerName` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '经销商名',
  `dealerId` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '经销商Id',
  `brandesName` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌名',
  `brandesId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌Id',
  `beOperationName` tinyint(4) NOT NULL COMMENT '被操作分类(1:普通订单,2:询价订单,3:工厂店订单,4:加盟管理;)',
  `operation` tinyint(4) NOT NULL COMMENT '操作分类详细操作(1:增加,2:关闭,3:申请退款,4:确认收货,5:付款,6:申请加盟,7:同意加盟.8:终止合作;)',
  `operationDetails` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '操作详情',
  `createTime` bigint(20) NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='crm免登陆经销商操作日志';

-- ----------------------------
--  Table structure for `DealerDefMenu`
-- ----------------------------
DROP TABLE IF EXISTS `DealerDefMenu`;
CREATE TABLE `DealerDefMenu` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `menuCode` int(11) NOT NULL COMMENT '菜单编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商自定义导航';

-- ----------------------------
--  Table structure for `DealerDeposit`
-- ----------------------------
DROP TABLE IF EXISTS `DealerDeposit`;
CREATE TABLE `DealerDeposit` (
  `refrenceId` varchar(32) NOT NULL COMMENT '业务无关 唯一主键',
  `dealerId` varchar(32) NOT NULL COMMENT '经销商ID',
  `brandId` varchar(32) NOT NULL COMMENT '品牌商ID',
  `paidAmount` decimal(11,2) NOT NULL COMMENT '当笔押金支付金额',
  `paidTime` bigint(11) NOT NULL COMMENT '支付时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='押金支付记录';

-- ----------------------------
--  Table structure for `DealerFavbrand`
-- ----------------------------
DROP TABLE IF EXISTS `DealerFavbrand`;
CREATE TABLE `DealerFavbrand` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `collectTime` bigint(20) NOT NULL COMMENT '收藏时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商收藏的品牌商';

-- ----------------------------
--  Table structure for `DealerFavbrands`
-- ----------------------------
DROP TABLE IF EXISTS `DealerFavbrands`;
CREATE TABLE `DealerFavbrands` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `collectTime` bigint(20) NOT NULL COMMENT '收藏时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商收藏的品牌';

-- ----------------------------
--  Table structure for `DealerFavorite`
-- ----------------------------
DROP TABLE IF EXISTS `DealerFavorite`;
CREATE TABLE `DealerFavorite` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `productId` char(32) NOT NULL COMMENT '产品编号',
  `tagId` char(32) DEFAULT NULL COMMENT '收藏标签编号',
  `collectTime` bigint(20) NOT NULL COMMENT '收藏时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商产品收藏';

-- ----------------------------
--  Table structure for `DealerFavtag`
-- ----------------------------
DROP TABLE IF EXISTS `DealerFavtag`;
CREATE TABLE `DealerFavtag` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `tagName` varchar(32) NOT NULL COMMENT '标签名称',
  `productNum` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商收藏标签';

-- ----------------------------
--  Table structure for `DealerFeed`
-- ----------------------------
DROP TABLE IF EXISTS `DealerFeed`;
CREATE TABLE `DealerFeed` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `feedText` varchar(5120) NOT NULL COMMENT '反馈内容',
  `createTime` bigint(20) NOT NULL COMMENT '反馈时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `replyText` varchar(5120) DEFAULT NULL COMMENT '品牌商回复内容',
  `replyTime` bigint(20) DEFAULT NULL COMMENT '品牌商回复时间',
  `replyId` char(32) DEFAULT NULL COMMENT '回复人员编号（品牌商编号）',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商反馈信息';

-- ----------------------------
--  Table structure for `DealerForget`
-- ----------------------------
DROP TABLE IF EXISTS `DealerForget`;
CREATE TABLE `DealerForget` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `oldPwd` char(32) NOT NULL COMMENT '原密码',
  `newPwd` char(32) NOT NULL COMMENT '新密码',
  `createTime` bigint(20) NOT NULL COMMENT '修改时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '修改者IP',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='取回密码日志';

-- ----------------------------
--  Table structure for `DealerGroom`
-- ----------------------------
DROP TABLE IF EXISTS `DealerGroom`;
CREATE TABLE `DealerGroom` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `userId` char(32) NOT NULL COMMENT '推荐人员',
  `createTime` bigint(20) NOT NULL COMMENT '推荐时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='推荐给品牌商的经销商';

-- ----------------------------
--  Table structure for `DealerImage`
-- ----------------------------
DROP TABLE IF EXISTS `DealerImage`;
CREATE TABLE `DealerImage` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `photoName` varchar(64) DEFAULT NULL COMMENT '图片原名称',
  `imageName` varchar(128) NOT NULL COMMENT '图片新名称',
  `createTime` bigint(20) NOT NULL COMMENT '上传时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`refrenceId`),
  KEY `dealerId` (`dealerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商店铺招牌';

-- ----------------------------
--  Table structure for `DealerInfo`
-- ----------------------------
DROP TABLE IF EXISTS `DealerInfo`;
CREATE TABLE `DealerInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '经销商编号',
  `dealerName` varchar(100) NOT NULL COMMENT '公司/店铺名称',
  `dealerAddress` varchar(128) NOT NULL COMMENT '经销商地址',
  `dealerWeb` varchar(128) DEFAULT NULL COMMENT '经销商网址',
  `dealerShop` varchar(128) DEFAULT NULL COMMENT '网上商铺',
  `domainName` varchar(32) NOT NULL DEFAULT '域名地址',
  `dealerLogo` varchar(128) DEFAULT NULL COMMENT '经销商图标',
  `headImage` varchar(128) DEFAULT NULL,
  `shopNum` int(11) NOT NULL COMMENT '分店数量',
  `legalImgz` varchar(128) DEFAULT NULL COMMENT '法人代表(正)',
  `legalImgf` varchar(128) DEFAULT NULL COMMENT '法人代表(反)',
  `monNum` decimal(6,2) DEFAULT NULL COMMENT '月销售额',
  `empNum` int(11) NOT NULL COMMENT '员工数量',
  `brandName` varchar(128) DEFAULT NULL COMMENT '经营品牌',
  `foundTime` bigint(6) DEFAULT NULL COMMENT '成立时间',
  `busImg` varchar(128) DEFAULT NULL COMMENT '营业执照',
  `otherImg` varchar(128) DEFAULT NULL COMMENT '其他证书',
  `dealerMark` text COMMENT '经销商介绍',
  `dealerUser` varchar(32) NOT NULL COMMENT '联系人员',
  `dealerGender` tinyint(4) DEFAULT NULL COMMENT '联系人性别',
  `dealerTel` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `dealerFax` varchar(20) DEFAULT NULL COMMENT '传真号码',
  `provinceName` varchar(32) NOT NULL COMMENT '省份名称',
  `cityName` varchar(32) NOT NULL COMMENT '市名称',
  `areaName` varchar(32) NOT NULL COMMENT '县区名称',
  `areaNo` int(11) NOT NULL,
  `cardId` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `comNo` varchar(32) DEFAULT NULL COMMENT '营业执照编号',
  `beginTime` bigint(20) DEFAULT NULL COMMENT '服务开始时间',
  `endTime` bigint(20) DEFAULT NULL COMMENT '服务结束时间',
  `gpsX` varchar(32) DEFAULT NULL COMMENT '百度地图经度',
  `gpsY` varchar(32) DEFAULT NULL COMMENT '百度地图纬度',
  `dealerLevel` tinyint(4) DEFAULT NULL COMMENT '店铺等级\r\n(CRM客服给店铺评定的等级)1,2,3,4,5',
  `checkState` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态（0：未审核，1：审核通过，2：审核不通过）',
  `rcvSmsVerify` bit(1) DEFAULT b'1' COMMENT '收货是否需要短信验证',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商基础信息';

-- ----------------------------
--  Table structure for `DealerJoin`
-- ----------------------------
DROP TABLE IF EXISTS `DealerJoin`;
CREATE TABLE `DealerJoin` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `logoName` varchar(128) NOT NULL COMMENT '品牌logo',
  `domainName` varchar(32) NOT NULL COMMENT 'logo域名',
  `levelId` varchar(32) DEFAULT NULL,
  `areaNo` int(11) DEFAULT NULL COMMENT '经销商区域编码',
  `joinSource` tinyint(4) NOT NULL COMMENT '加盟来源（1：平台线上申请加盟，2：平台线上邀请加盟，3：平台线下加盟，4：关系户加盟...）',
  `joinForm` tinyint(4) NOT NULL DEFAULT '0' COMMENT '加盟方式：0 现款，1受信',
  `joinState` tinyint(4) NOT NULL COMMENT '合作状态（1：正在合作，2：品牌商中止合作，3：经销商中止合作）',
  `creditCurrent` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '实时受信金额',
  `clearingAmount` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '当期应付款摊到授信额度中的金额',
  `discount` decimal(4,3) NOT NULL DEFAULT '1.000' COMMENT '(加盟折扣数： 默认为1,小数为打折)',
  `creditAmount` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '受信金额',
  `applyTime` bigint(20) NOT NULL COMMENT '申请时间',
  `joinTime` bigint(20) NOT NULL COMMENT '加入时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `startTime` bigint(20) NOT NULL COMMENT '开始日期',
  `endTime` bigint(20) DEFAULT NULL COMMENT '结束日期',
  `endMark` varchar(512) DEFAULT NULL COMMENT '中止合作原因',
  `orderTime` int(11) DEFAULT NULL COMMENT '累记进货次数',
  `orderNum` int(11) DEFAULT NULL COMMENT '累记进货数量',
  `orderMoney` decimal(10,2) DEFAULT NULL COMMENT '累记进货金额',
  `lastOrder` bigint(20) DEFAULT NULL COMMENT '最后进货时间',
  `joinNet` bit(1) DEFAULT NULL COMMENT '标志该经销商是否已加入品牌商的经销网络',
  `isPaid` bit(1) DEFAULT b'0' COMMENT '是否已支付押金',
  `creditPaidPercent` decimal(6,3) NOT NULL DEFAULT '0.000' COMMENT '授信押金百分比（押金 = 授信金额*授信押金百分比）',
  `paidAmount` decimal(11,2) DEFAULT NULL COMMENT '已付押金金额',
  `paidTime` bigint(11) DEFAULT NULL COMMENT '押金支付时间',
  `depositTotalAmount` decimal(11,2) DEFAULT '0.00' COMMENT '品牌商给终端商设定的 押金额度',
  `depositClearingAmount` decimal(11,2) DEFAULT '0.00' COMMENT '平台给品牌已支付的押金',
  `refundAmount` decimal(11,2) DEFAULT NULL COMMENT '已退押金金额',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商加盟信息';

-- ----------------------------
--  Table structure for `DealerMessage`
-- ----------------------------
DROP TABLE IF EXISTS `DealerMessage`;
CREATE TABLE `DealerMessage` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) DEFAULT NULL COMMENT '经销商编号',
  `brandId` char(32) DEFAULT NULL COMMENT '发送人编号(站内消息)',
  `orderId` char(32) DEFAULT NULL COMMENT '订单编号',
  `msgCate` tinyint(4) NOT NULL COMMENT '消息类型',
  `msgTitle` varchar(128) NOT NULL COMMENT '消息标题',
  `msgText` varchar(5120) DEFAULT NULL COMMENT '消息内容',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `refuseReply` bit(1) DEFAULT b'0' COMMENT '拒绝回复（表示该消息是否不能回复）0：可以回复，1：不能回复',
  `replyId` char(32) DEFAULT NULL COMMENT '回复的消息编号',
  `replyTime` bigint(20) DEFAULT NULL COMMENT '回复时间',
  `replyText` varchar(5120) DEFAULT NULL COMMENT '回复内容',
  `userId` char(32) DEFAULT NULL COMMENT '回复人编号',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `DealerMessage_brandId` (`brandId`) USING BTREE,
  KEY `DealerMessage_dealerId` (`dealerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商消息管理';

-- ----------------------------
--  Table structure for `DealerOrder`
-- ----------------------------
DROP TABLE IF EXISTS `DealerOrder`;
CREATE TABLE `DealerOrder` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` bigint(20) NOT NULL COMMENT '订单编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL DEFAULT '0' COMMENT '品牌编号',
  `productBulk` decimal(5,2) DEFAULT NULL COMMENT '产品体积',
  `productWeight` decimal(11,3) NOT NULL COMMENT '产品重量',
  `productPrice` decimal(11,2) NOT NULL COMMENT '产品价格',
  `productPriceWhenPay` decimal(11,2) DEFAULT NULL COMMENT '付款时订单即时总货款,调价时不改变',
  `productCount` int(11) NOT NULL COMMENT '总购买数量',
  `isAdvancePayment` bit(1) NOT NULL COMMENT '是否是预付订单',
  `orderMoney` int(11) DEFAULT NULL COMMENT '订单支付百分比',
  `shipCount` int(11) NOT NULL COMMENT '已经发货数量',
  `receiveCount` int(11) DEFAULT NULL,
  `adjustPrice` decimal(11,2) DEFAULT NULL COMMENT '优惠价格',
  `freight` decimal(11,2) NOT NULL COMMENT '运费',
  `discountPrice` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '授信订单折扣价',
  `adjustFreight` decimal(11,2) DEFAULT NULL COMMENT '调整后的运费',
  `frePayState` tinyint(4) DEFAULT '0' COMMENT '运费支付状态0：未支付1：已支付',
  `payment` decimal(11,2) DEFAULT NULL COMMENT '支付金额',
  `payState` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付状态0：未支付1：部分支付2：全部支付',
  `shipName` varchar(32) NOT NULL COMMENT '收货人姓名',
  `areaNo` int(11) NOT NULL COMMENT '收货人所在地区',
  `dealerAddrProvince` varchar(32) NOT NULL COMMENT '收货人所在地区',
  `dealerAddrCity` varchar(32) NOT NULL COMMENT '收货人所在地区',
  `dealerAddrArea` varchar(32) NOT NULL COMMENT '收货人所在地区',
  `dealerAddress` varchar(128) NOT NULL COMMENT '收货人街道地址',
  `postCode` char(6) DEFAULT NULL COMMENT '邮政编码',
  `dealerMobile` char(11) DEFAULT NULL COMMENT '手机号码',
  `dealerTel` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `remark` varchar(2000) DEFAULT NULL COMMENT '订单留言',
  `levelMark` tinyint(4) DEFAULT NULL COMMENT '星级,1-5',
  `brandRemark` varchar(2000) DEFAULT NULL COMMENT '品牌商备注',
  `createTime` bigint(20) NOT NULL COMMENT '操作时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `outActTime` bigint(20) DEFAULT NULL COMMENT '下一个操作时间',
  `orderStatus` tinyint(4) NOT NULL COMMENT '1等待付款\r\n2 等待发货\r\n3:部分发货\r\n4 等待确认收货\r\n9 交易成功\r\n10 交易关闭',
  `refundStatus` tinyint(4) DEFAULT NULL COMMENT '退款状态  1：申请退款等待处理2：同意退货等待发货 3：退货已发货  4：拒绝退款5：拒绝退货   6：退款关闭 7: 取消退款9：同意退款 10：同意退货退款\r\n      ',
  `serProStatus` tinyint(4) DEFAULT NULL COMMENT '客服介入处理状态1：客服介入处理中，2：纠纷处理中，3：处理完毕,4:纠纷关闭',
  `complaintState` tinyint(4) DEFAULT NULL COMMENT '投诉状态（0：等待处理，1：客服介入，2：处理完成，3：经销商撤消投诉）',
  `orderItem` text COMMENT '冗余的订单明细信息，使用json保存最新十笔订单明细',
  `sourceState` tinyint(4) DEFAULT '0' COMMENT '订单来源（0：交易平台，1：支撑系统）',
  `sourceId` varchar(4000) DEFAULT NULL COMMENT '来源ID',
  `activitiesNo` varchar(32) DEFAULT NULL COMMENT '订单活动',
  `minPoint` decimal(11,2) DEFAULT NULL COMMENT '最低扣点比例',
  `dealerOrderType` tinyint(4) NOT NULL DEFAULT '1' COMMENT '订单类型：1、现款订单  2、授信订单',
  `isSampleOrder` bit(1) DEFAULT b'0' COMMENT '现款订单：拿样订单（0否，1是）',
  `pointBalanceAmount` decimal(11,2) DEFAULT NULL COMMENT '已扣的佣金',
  `frePayType` tinyint(4) NOT NULL DEFAULT '1' COMMENT '运费支付方式：1、快递 2、物流 3、快递到付 4、物流到付 60、包邮',
  `clearingStatus` bit(1) DEFAULT b'0' COMMENT '结算状态',
  `clearingAmount` decimal(11,2) DEFAULT '0.00' COMMENT '结算金额',
  `offSetAmount` decimal(11,2) DEFAULT '0.00' COMMENT '抵扣金额',
  `noSendGoodsAmount` decimal(11,2) DEFAULT NULL COMMENT '未发货产品货款',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `DealerOrder_brandId` (`brandId`) USING BTREE,
  KEY `DealerOrder_brandsId` (`brandsId`) USING BTREE,
  KEY `DealerOrder_dealerId` (`dealerId`) USING BTREE,
  KEY `DealerOrder_orderId` (`orderId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商订单信息';

-- ----------------------------
--  Table structure for `DealerOrderAction`
-- ----------------------------
DROP TABLE IF EXISTS `DealerOrderAction`;
CREATE TABLE `DealerOrderAction` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` char(32) NOT NULL COMMENT '订单编号',
  `userId` char(32) NOT NULL COMMENT '经销商/品牌商编号',
  `userName` varchar(100) NOT NULL COMMENT '经销商/店铺名称',
  `actCode` varchar(32) NOT NULL COMMENT '操作编码\r\n            createorder:创建订单，\r\n            orderpay:付款，\r\n            shipgoods：发货，\r\n            applyrefund： 申请退款\r\n            confrimgoods：确认收货\r\n            agreerefund: 同意退款\r\n            ',
  `actName` varchar(32) NOT NULL COMMENT '操作名',
  `actContent` varchar(1024) DEFAULT NULL COMMENT '操作内容',
  `createTime` bigint(20) NOT NULL COMMENT '操作时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单操作历史记录';

-- ----------------------------
--  Table structure for `DealerOrderc`
-- ----------------------------
DROP TABLE IF EXISTS `DealerOrderc`;
CREATE TABLE `DealerOrderc` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `productId` char(32) NOT NULL COMMENT '商品编号',
  `subTime` int(11) unsigned zerofill DEFAULT NULL COMMENT '下单次数',
  `subNum` int(11) unsigned zerofill DEFAULT NULL COMMENT '下单数量',
  `joinNum` int(11) unsigned zerofill DEFAULT NULL COMMENT '进货次数',
  `orderNum` int(11) unsigned zerofill DEFAULT NULL COMMENT '进货数量',
  `orderTime` bigint(20) NOT NULL COMMENT '最后进货时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标识：0未删除，1删除',
  PRIMARY KEY (`refrenceId`),
  KEY `DealerOrderc_brandId` (`brandId`) USING BTREE,
  KEY `DealerOrderc_brandsId` (`brandsId`) USING BTREE,
  KEY `DealerOrderc_dealerId` (`dealerId`) USING BTREE,
  KEY `DealerOrderc_productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商产品进货计数';

-- ----------------------------
--  Table structure for `DealerOrderPay`
-- ----------------------------
DROP TABLE IF EXISTS `DealerOrderPay`;
CREATE TABLE `DealerOrderPay` (
  `refrenceId` varchar(32) NOT NULL COMMENT '支付记录编号',
  `orderId` varchar(32) NOT NULL COMMENT '订单ID',
  `orderNo` bigint(20) DEFAULT NULL COMMENT '订单号',
  `payId` bigint(20) NOT NULL COMMENT '支付ID',
  `payAmount` decimal(11,2) NOT NULL COMMENT '付款金额',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `state` tinyint(4) NOT NULL COMMENT '支付状态(0:未完成,1:已完成,2:已关闭,3:已退款)',
  `orderType` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单类型（0：订单，1：终端商购买服务，2：品牌商购买服务）',
  `pointBalance` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '佣金',
  `unpayAmount` decimal(11,2) DEFAULT NULL COMMENT '未支付金额',
  `payTime` bigint(20) DEFAULT NULL COMMENT '支付时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态(0:未删除，1:已删除)',
  PRIMARY KEY (`refrenceId`),
  UNIQUE KEY `IDX_PAYID` (`payId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付记录表';

-- ----------------------------
--  Table structure for `DealerOrders`
-- ----------------------------
DROP TABLE IF EXISTS `DealerOrders`;
CREATE TABLE `DealerOrders` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` char(32) NOT NULL COMMENT '订单编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `lineId` char(32) DEFAULT NULL COMMENT '产品线编号',
  `agio` decimal(4,3) DEFAULT NULL COMMENT '折扣值',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `productId` char(32) NOT NULL COMMENT '商品编号',
  `productTitle` varchar(128) NOT NULL COMMENT '产品标题',
  `productNo` varchar(64) DEFAULT NULL COMMENT '产品货号',
  `productImage` varchar(128) NOT NULL COMMENT '产品主图',
  `productSkuId` char(32) DEFAULT NULL COMMENT 'SKU主键',
  `productSkuCode` varchar(100) DEFAULT NULL COMMENT 'SKU 编码',
  `productSkuName` varchar(100) DEFAULT NULL COMMENT 'SKU 名称',
  `createTime` bigint(20) NOT NULL COMMENT '操作时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `productAttrbute` text COMMENT '属性值',
  `price` decimal(10,2) NOT NULL COMMENT '商品单价',
  `oldPrice` decimal(10,2) DEFAULT NULL COMMENT '授信订单调价后保存的调价前的原始单价(未折扣,并且第一次调价时设置后不会更改)',
  `adjustPrice` decimal(11,2) DEFAULT NULL COMMENT '品牌商修改金额后的价格',
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `shipCount` int(11) DEFAULT NULL COMMENT '已发货数量',
  `discount` decimal(4,3) NOT NULL DEFAULT '1.000' COMMENT '授信订单折扣',
  `discountPrice` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '授信订单项总优惠价',
  `itemState` tinyint(4) NOT NULL COMMENT 'itemState（订单项状态）\r\n            0：订单项已关闭\r\n            1：正常状态\r\n            2：退款中',
  `synchTime` bigint(20) DEFAULT NULL COMMENT '经销商同步时间',
  `totalAmount` decimal(11,2) DEFAULT NULL COMMENT '总金额',
  `point` decimal(11,2) DEFAULT NULL COMMENT '扣点比例',
  `pointAmount` decimal(11,2) DEFAULT NULL COMMENT '扣点金额',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `DealerOrders_orderId` (`orderId`) USING BTREE,
  KEY `DealerOrders_dealerId` (`dealerId`) USING BTREE,
  KEY `DealerOrders_brandId` (`brandId`) USING BTREE,
  KEY `DealerOrders_brandsId` (`brandsId`) USING BTREE,
  KEY `DealerOrders_productId` (`productId`) USING BTREE,
  KEY `DealerOrders_productSkuId` (`productSkuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商订单项信息';

-- ----------------------------
--  Table structure for `DealerPayword`
-- ----------------------------
DROP TABLE IF EXISTS `DealerPayword`;
CREATE TABLE `DealerPayword` (
  `refrenceId` char(32) NOT NULL COMMENT '经销商编号一一对应',
  `dealerTel` char(11) NOT NULL COMMENT '手机号码',
  `payWord` char(64) NOT NULL,
  `paySalt` char(11) NOT NULL COMMENT '密码盐值',
  `createTime` bigint(20) NOT NULL,
  `createIp` int(11) NOT NULL,
  `flag` int(11) NOT NULL DEFAULT '0' COMMENT '迁移标记',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商支付密码';

-- ----------------------------
--  Table structure for `DealerRead`
-- ----------------------------
DROP TABLE IF EXISTS `DealerRead`;
CREATE TABLE `DealerRead` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `msgId` char(32) NOT NULL COMMENT '消息编号',
  `readTime` bigint(20) NOT NULL COMMENT '阅读时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态(0：未删除，１：已删除）',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商消息阅读';

-- ----------------------------
--  Table structure for `DealerRefAttacht`
-- ----------------------------
DROP TABLE IF EXISTS `DealerRefAttacht`;
CREATE TABLE `DealerRefAttacht` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `refundId` char(32) NOT NULL,
  `userId` char(32) NOT NULL COMMENT '用户编号',
  `replyId` char(32) DEFAULT NULL COMMENT '留言回复编号',
  `userName` varchar(128) DEFAULT NULL COMMENT '用户/经销商/品牌商名称',
  `domainName` varchar(32) DEFAULT NULL COMMENT '附件域名',
  `attachtName` varchar(128) NOT NULL COMMENT '附件地址',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款附件';

-- ----------------------------
--  Table structure for `DealerRefReply`
-- ----------------------------
DROP TABLE IF EXISTS `DealerRefReply`;
CREATE TABLE `DealerRefReply` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `refundId` char(32) NOT NULL COMMENT '退款编号',
  `userId` char(32) NOT NULL COMMENT '用户编号',
  `userName` varchar(128) DEFAULT NULL COMMENT '用户/经销商/品牌商名称',
  `replyContent` varchar(512) DEFAULT NULL COMMENT '附件域名',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款留言';

-- ----------------------------
--  Table structure for `DealerRefund`
-- ----------------------------
DROP TABLE IF EXISTS `DealerRefund`;
CREATE TABLE `DealerRefund` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` char(32) NOT NULL COMMENT '订单编号=>订单表中的refrenceId',
  `refundId` bigint(20) NOT NULL COMMENT '退款友好编号',
  `orderNumber` bigint(20) NOT NULL COMMENT '好友订单编号=订单表中的orderId',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `received` bit(1) NOT NULL COMMENT '是否收到货',
  `needRefund` bit(1) NOT NULL COMMENT '是否需要退货',
  `totalAmount` decimal(10,2) NOT NULL COMMENT '总金额',
  `refundAmount` decimal(10,2) NOT NULL COMMENT '退款金额',
  `comPayment` decimal(10,2) NOT NULL COMMENT '支付给品牌商的金额',
  `refundReason` varchar(32) DEFAULT NULL COMMENT '退款的原因',
  `refundMark` varchar(1024) DEFAULT NULL COMMENT '退款描述',
  `brandRecAddr` varchar(255) DEFAULT NULL COMMENT '品牌商收货地址',
  `returnTime` bigint(20) DEFAULT NULL COMMENT '商品退款时间',
  `logisticsName` varchar(32) DEFAULT NULL COMMENT '物流公司名称',
  `transNum` varchar(32) DEFAULT NULL COMMENT '运送单号',
  `refundState` tinyint(4) NOT NULL COMMENT '退款状态  1：申请退款等待处理2：同意退货等待发货 3：退货已发货  4：拒绝退款5：拒绝退货   6：退款关闭 7: 取消退款9：同意退款 10：同意退货退款\r\n      ',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  `brandMark` varchar(128) DEFAULT NULL COMMENT '品牌商处理结果',
  `createTime` bigint(20) NOT NULL COMMENT '操作时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `nextActTime` bigint(20) DEFAULT NULL,
  `updateNum` int(11) NOT NULL COMMENT '协议修改次数',
  `cusJoin` bit(1) DEFAULT NULL COMMENT '是否申请客服介入',
  `joinTime` bigint(20) DEFAULT NULL COMMENT '申请客服介入的时间',
  `applyTime` bigint(20) DEFAULT NULL COMMENT '协议提出时间',
  `serProStatus` tinyint(4) DEFAULT NULL COMMENT '客服介入处理状态1：客服介入处理中，2：纠纷处理中，3：处理完毕,4:纠纷关闭5:等待客服介入中',
  `serProResult` varchar(512) DEFAULT NULL COMMENT '客服处理结果',
  `factoryStore` bit(1) DEFAULT b'0' COMMENT '是否为工厂店品牌',
  `reachAmount` decimal(11,2) DEFAULT '0.00' COMMENT '抵用金额(欠收抵应付金额)',
  `reachStatus` tinyint(4) DEFAULT '0' COMMENT '退款支付方式(0:直接支付 1:欠收抵应付 2:0和1同步存在)',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商退款信息';

-- ----------------------------
--  Table structure for `DealerService`
-- ----------------------------
DROP TABLE IF EXISTS `DealerService`;
CREATE TABLE `DealerService` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `serviceName` varchar(100) NOT NULL COMMENT '客服名字',
  `userId` char(32) DEFAULT NULL COMMENT '客服编号(对应CRM客服编号）',
  `serviceImage` varchar(128) DEFAULT NULL COMMENT '客服照片',
  `domainName` varchar(32) DEFAULT NULL COMMENT '客服域名',
  `serviceTel` varchar(16) DEFAULT NULL COMMENT '客服电话',
  `jobNum` char(4) DEFAULT NULL COMMENT '工号',
  `serviceMobile` varchar(11) DEFAULT NULL COMMENT '客服手机',
  `userGender` tinyint(4) NOT NULL COMMENT '客服性别',
  `serviceCate` tinyint(4) NOT NULL COMMENT '以后扩展用，默认为1',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态\r\n            0：未删除\r\n            1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商商客服信息';

-- ----------------------------
--  Table structure for `DealerShopEnv`
-- ----------------------------
DROP TABLE IF EXISTS `DealerShopEnv`;
CREATE TABLE `DealerShopEnv` (
  `refrenceId` char(32) NOT NULL,
  `dealerId` char(32) NOT NULL,
  `shopName` varchar(100) DEFAULT NULL COMMENT '铺店名称',
  `environment` varchar(30) DEFAULT NULL COMMENT '周边环境',
  `scale` int(11) DEFAULT NULL COMMENT '店面数',
  `ShopMeters` double DEFAULT NULL COMMENT '店铺平米数',
  `shape` varchar(30) DEFAULT NULL COMMENT '店铺形式',
  `trade` varchar(30) DEFAULT NULL COMMENT '经营行业',
  `model` varchar(30) DEFAULT NULL COMMENT '销售模式',
  `brand` varchar(30) DEFAULT NULL COMMENT '经营品牌',
  `salesVolume` varchar(30) DEFAULT NULL COMMENT '年销售额',
  `GPSX` double DEFAULT NULL COMMENT 'GPS X坐标',
  `GPSY` double DEFAULT NULL COMMENT 'GPS Y坐标',
  `brandSituation` varchar(1000) DEFAULT NULL COMMENT '现有品牌销售情况',
  `intention` varchar(1000) DEFAULT NULL COMMENT '合作意向',
  `openTime` bigint(20) DEFAULT NULL COMMENT '开店时间',
  `viewCount` int(11) DEFAULT '0' COMMENT '浏览次数',
  `areaNo` int(11) DEFAULT NULL COMMENT '区域代码',
  `cityName` varchar(50) DEFAULT NULL COMMENT '城市名称',
  `provinceName` varchar(11) DEFAULT NULL COMMENT '省份名称',
  `areaName` varchar(11) DEFAULT NULL COMMENT '区域名称',
  `createTime` bigint(20) DEFAULT NULL COMMENT '记录产生时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `briefIntroduction` text COMMENT '备注',
  `showed` tinyint(1) DEFAULT '1' COMMENT '是否显示店铺',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `DealerShopEnv_dealerId` (`dealerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='终端商店铺信息';

-- ----------------------------
--  Table structure for `DealerShopEnvImgTemp`
-- ----------------------------
DROP TABLE IF EXISTS `DealerShopEnvImgTemp`;
CREATE TABLE `DealerShopEnvImgTemp` (
  `refrenceId` varchar(32) NOT NULL,
  `shopEnvId` varchar(32) DEFAULT NULL,
  `imagePath` varchar(512) DEFAULT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `updateTime` bigint(20) DEFAULT NULL,
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销店铺 临时图片';

-- ----------------------------
--  Table structure for `DealerShopEnvTemp`
-- ----------------------------
DROP TABLE IF EXISTS `DealerShopEnvTemp`;
CREATE TABLE `DealerShopEnvTemp` (
  `refrenceId` varchar(32) NOT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `updateTime` bigint(20) DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '0:未处理 1:已处理',
  `detail` text,
  `auditUser` varchar(255) DEFAULT NULL,
  `auditTime` bigint(20) DEFAULT NULL,
  `auditIp` varchar(20) DEFAULT NULL,
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销店铺 临时信息';

-- ----------------------------
--  Table structure for `DealerShoper`
-- ----------------------------
DROP TABLE IF EXISTS `DealerShoper`;
CREATE TABLE `DealerShoper` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `productId` char(32) NOT NULL COMMENT '商品编号',
  `productNum` int(11) DEFAULT NULL COMMENT '总数量',
  `productPrice` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `sourceId` varchar(32) DEFAULT NULL COMMENT '来源ID',
  `productType` tinyint(4) DEFAULT NULL COMMENT '产品类型 0普通 1授信 2 拿样',
  `discountPrice` decimal(11,2) DEFAULT '0.00' COMMENT '折扣价(授信车单)',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商购物车';

-- ----------------------------
--  Table structure for `DealerShopers`
-- ----------------------------
DROP TABLE IF EXISTS `DealerShopers`;
CREATE TABLE `DealerShopers` (
  `refrenceId` char(32) NOT NULL,
  `shoperId` char(32) NOT NULL COMMENT '购物车编号',
  `productId` char(32) NOT NULL COMMENT '产品编号',
  `productSkuId` char(32) NOT NULL COMMENT '产品SKU编号',
  `purchaseNum` int(10) DEFAULT NULL COMMENT '购买数量',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `productType` tinyint(4) DEFAULT '0' COMMENT '产品类型:0普通 1授信 2拿样',
  `productSkuPrice` decimal(10,2) DEFAULT '0.00' COMMENT '产品sku的价格',
  `delFlag` bit(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物详细信息表';

-- ----------------------------
--  Table structure for `DealerShopInfo`
-- ----------------------------
DROP TABLE IF EXISTS `DealerShopInfo`;
CREATE TABLE `DealerShopInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `userCode` char(8) DEFAULT NULL COMMENT '微逛号',
  `shopId` char(32) DEFAULT NULL COMMENT '微店编号',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(32) DEFAULT NULL COMMENT '修改时间',
  `shopState` tinyint(4) NOT NULL COMMENT '状态',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='终端商微店信息';

-- ----------------------------
--  Table structure for `DealerTermina`
-- ----------------------------
DROP TABLE IF EXISTS `DealerTermina`;
CREATE TABLE `DealerTermina` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) DEFAULT NULL COMMENT '品牌编号',
  `logoName` varchar(128) NOT NULL COMMENT 'logo文件',
  `domainName` varchar(32) NOT NULL COMMENT 'logo域名',
  `terminaTime` bigint(20) NOT NULL COMMENT '终止时间',
  `userId` char(32) NOT NULL COMMENT '终止人员',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商中止合作日志';

-- ----------------------------
--  Table structure for `DealerVisited`
-- ----------------------------
DROP TABLE IF EXISTS `DealerVisited`;
CREATE TABLE `DealerVisited` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商主帐号编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `domainName` varchar(32) NOT NULL COMMENT 'logo所在的服务器域名',
  `logoName` varchar(128) NOT NULL COMMENT 'logo文件名称',
  `areaNo` int(11) NOT NULL COMMENT '地区编码',
  `provinceName` varchar(32) NOT NULL COMMENT '品牌商省份名称',
  `cityName` varchar(32) NOT NULL COMMENT '品牌商城市名称',
  `areaName` varchar(32) NOT NULL COMMENT '品牌商区域名称',
  `viewNum` int(11) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `viewTime` bigint(20) NOT NULL COMMENT '最后浏览时间',
  `delFlag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `DealerVisited_brandId` (`brandId`) USING BTREE,
  KEY `DealerVisited_brandsId` (`brandsId`) USING BTREE,
  KEY `DealerVisited_dealerId` (`dealerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商浏览记录';

-- ----------------------------
--  Table structure for `DecorateConfig`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateConfig`;
CREATE TABLE `DecorateConfig` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `showTitle` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示标题',
  `configType` tinyint(4) DEFAULT NULL COMMENT '模块类型\r\n            0：自定义非固定模块\r\n            1：品牌介绍固定模块',
  `tagId` smallint(6) DEFAULT NULL COMMENT '模块编号',
  `showFlag` bit(1) DEFAULT NULL COMMENT '是否显示',
  `showType` int(11) DEFAULT NULL COMMENT '显示类型\r\n            1:自定义html内容\r\n            2:图片滚动模块',
  `showText` text COMMENT '显示内容',
  `showOrder` smallint(6) DEFAULT NULL COMMENT '排序编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态\r\n            0：未删除\r\n            1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅自定义模块配置';

-- ----------------------------
--  Table structure for `DecorateConfigLog`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateConfigLog`;
CREATE TABLE `DecorateConfigLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `showTitle` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示标题',
  `tagId` smallint(6) DEFAULT NULL COMMENT '模块编号',
  `configType` tinyint(4) DEFAULT NULL COMMENT '模块类型\r\n            0：自定义非固定模块\r\n            1：品牌介绍固定模块',
  `showFlag` bit(1) DEFAULT NULL COMMENT '是否显示',
  `showType` int(11) DEFAULT NULL COMMENT '显示类型\r\n            1:自定义html内容\r\n            2:图片滚动模块',
  `showText` text COMMENT '显示内容',
  `showOrder` smallint(6) DEFAULT NULL COMMENT '排序编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态\r\n            0：未删除\r\n            1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅自定义模块配置';

-- ----------------------------
--  Table structure for `DecorateGlobal`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateGlobal`;
CREATE TABLE `DecorateGlobal` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL DEFAULT '' COMMENT '品牌编号',
  `fontCcolor` varchar(32) DEFAULT NULL COMMENT '字体颜色',
  `urlFontColor` varchar(32) DEFAULT NULL COMMENT '全局链接字体颜色',
  `urlChangeColor` varchar(32) DEFAULT NULL COMMENT '链接变化颜色',
  `backColor` varchar(7) DEFAULT '0' COMMENT '背景色',
  `showBackColor` bit(1) DEFAULT NULL COMMENT '是否显示背景色',
  `backUrl` varchar(128) DEFAULT NULL COMMENT '全局背景图片地址',
  `showBackUrl` bit(1) DEFAULT b'0' COMMENT '是否显示背景图',
  `backRepeat` varchar(32) DEFAULT NULL COMMENT '背景平铺方式',
  `backPosition` varchar(32) DEFAULT NULL COMMENT '背景所在位置',
  `skinName` varchar(20) DEFAULT NULL COMMENT '样式名称',
  `createTime` bigint(20) NOT NULL COMMENT '建档日期',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改日期',
  `delFlag` bit(1) NOT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅装修全局配置';

-- ----------------------------
--  Table structure for `DecorateGlobalLog`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateGlobalLog`;
CREATE TABLE `DecorateGlobalLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL DEFAULT '' COMMENT '品牌编号',
  `fontCcolor` varchar(7) DEFAULT NULL COMMENT '字体颜色',
  `urlFontColor` varchar(7) DEFAULT NULL COMMENT '全局链接字体颜色',
  `urlChangeColor` varchar(7) DEFAULT NULL COMMENT '链接变化颜色',
  `backColor` varchar(7) DEFAULT '0' COMMENT '背景色',
  `showBackColor` bit(1) DEFAULT NULL COMMENT '是否显示背景色',
  `backUrl` varchar(128) DEFAULT NULL COMMENT '全局背景图片地址',
  `showBackUrl` bit(1) DEFAULT b'0' COMMENT '是否显示背景图',
  `backRepeat` varchar(32) DEFAULT NULL COMMENT '背景平铺方式',
  `backPosition` varchar(32) DEFAULT NULL COMMENT '背景所在位置',
  `skinName` varchar(20) DEFAULT NULL COMMENT '样式名称',
  `createTime` bigint(20) NOT NULL COMMENT '建档日期',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改日期',
  `delFlag` bit(1) NOT NULL COMMENT '0未删除 1已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅装修全局配置历史记录';

-- ----------------------------
--  Table structure for `DecorateHeader`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateHeader`;
CREATE TABLE `DecorateHeader` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `showCate` tinyint(4) NOT NULL COMMENT '显示类型',
  `comName` varchar(100) NOT NULL COMMENT '企业名称',
  `showName` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示企业名',
  `domainName` varchar(32) DEFAULT NULL COMMENT '图片所在服务器的域名',
  `logoName` varchar(128) DEFAULT NULL COMMENT 'logo图片地址，名称',
  `showLogo` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示Logo',
  `mainDeal` varchar(128) DEFAULT NULL COMMENT '主营业务',
  `nameFont` varchar(16) DEFAULT NULL COMMENT '公司名字体',
  `nameSize` varchar(4) DEFAULT NULL COMMENT '公司名字体大小',
  `nameColor` varchar(7) DEFAULT NULL COMMENT '公司名字体颜色',
  `dealFont` varchar(16) DEFAULT NULL COMMENT '主营业务字体',
  `dealSize` varchar(4) DEFAULT NULL COMMENT '主营业务字体大小',
  `dealColor` varchar(7) DEFAULT NULL COMMENT '主营业务字体颜色',
  `outBackUrl` varchar(128) DEFAULT NULL COMMENT '通栏外背景地址',
  `inBackUrl` varchar(128) DEFAULT NULL COMMENT '通栏内背景地址',
  `navDefaultColor` varchar(7) DEFAULT NULL COMMENT '导航背景颜色',
  `navDefaultUrl` varchar(128) DEFAULT NULL COMMENT '导航默认背景图',
  `navChangeColor` varchar(7) DEFAULT NULL COMMENT '导航变化背景色',
  `navChangeUrl` varchar(128) DEFAULT NULL COMMENT '导航变化背景图',
  `navSelectColor` varchar(7) DEFAULT NULL COMMENT '导航选中背景色',
  `navDefaultFont` varchar(7) DEFAULT NULL COMMENT '导航文字默认色',
  `navChangeFont` varchar(7) DEFAULT NULL COMMENT '导航文字变化色',
  `headerText` varchar(4000) DEFAULT NULL COMMENT '自定义头部说明',
  `createTime` bigint(20) NOT NULL COMMENT '建档日期',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改日期',
  `delFlag` bit(1) NOT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅头部装修';

-- ----------------------------
--  Table structure for `DecorateHeaderLog`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateHeaderLog`;
CREATE TABLE `DecorateHeaderLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `showCate` tinyint(4) NOT NULL COMMENT '显示类型\r\n            1：配置显示\r\n            2：自定义内容',
  `comName` varchar(100) NOT NULL COMMENT '企业名称',
  `showName` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示企业名',
  `domainName` varchar(32) DEFAULT NULL COMMENT '域名',
  `logoName` varchar(128) DEFAULT NULL COMMENT '店铺logo',
  `showLogo` bit(1) DEFAULT NULL,
  `mainDeal` varchar(128) DEFAULT NULL COMMENT '主营业务',
  `nameFont` varchar(16) DEFAULT NULL COMMENT '公司名字体',
  `nameSize` varchar(4) DEFAULT NULL COMMENT '公司名字体大小',
  `nameColor` varchar(7) DEFAULT NULL COMMENT '公司名字体颜色',
  `dealFont` varchar(16) DEFAULT NULL COMMENT '主营业务字体',
  `dealSize` varchar(4) DEFAULT NULL COMMENT '主营业务字体大小',
  `dealColor` varchar(7) DEFAULT NULL COMMENT '主营业务字体颜色',
  `outBackUrl` varchar(128) DEFAULT NULL COMMENT '头部背景地址',
  `inbackUrl` varchar(128) DEFAULT NULL COMMENT '导航条背景地址',
  `navBackUrl` char(10) DEFAULT NULL,
  `navDefaultColor` varchar(7) DEFAULT NULL COMMENT '导航背景颜色',
  `navDefaultUrl` varchar(128) DEFAULT NULL COMMENT '导航默认背景图',
  `navChangeColor` varchar(7) DEFAULT NULL COMMENT '导航变化背景色',
  `navChangeUrl` varchar(128) DEFAULT NULL COMMENT '导航变化背景图',
  `navSelectColor` varchar(7) DEFAULT NULL,
  `navDefaultFont` varchar(7) DEFAULT NULL COMMENT '导航文字默认色',
  `navChangeFont` varchar(7) DEFAULT NULL COMMENT '导航文字变化色',
  `headerText` varchar(4000) DEFAULT NULL COMMENT '自定义头部说明',
  `createTime` bigint(20) NOT NULL COMMENT '建档日期',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改日期',
  `delFlag` bit(1) NOT NULL COMMENT '0未删 1删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅头部装修';

-- ----------------------------
--  Table structure for `DecorateImage`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateImage`;
CREATE TABLE `DecorateImage` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `rollId` char(32) NOT NULL COMMENT '滚动配置编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `altName` varchar(64) DEFAULT NULL COMMENT 'alt描述',
  `domainName` varchar(32) DEFAULT NULL COMMENT '域名',
  `imageUrl` varchar(128) DEFAULT NULL COMMENT '图片地址',
  `hrefText` varchar(128) DEFAULT NULL COMMENT '链接地址',
  `showOrder` int(11) DEFAULT NULL COMMENT '排序编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅自定义滚动图片内容';

-- ----------------------------
--  Table structure for `DecorateMenu`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateMenu`;
CREATE TABLE `DecorateMenu` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL DEFAULT '' COMMENT '品牌编号',
  `menuValue` varchar(4000) DEFAULT NULL COMMENT '菜单json组合',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅装修菜单';

-- ----------------------------
--  Table structure for `DecorateMenuLog`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateMenuLog`;
CREATE TABLE `DecorateMenuLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `brandsId` char(32) NOT NULL DEFAULT '' COMMENT '品牌编号',
  `menuValue` varchar(4000) DEFAULT NULL COMMENT '菜单json组合',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '0未删 1 已删',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅装修菜单历史记录';

-- ----------------------------
--  Table structure for `DecorateRolling`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateRolling`;
CREATE TABLE `DecorateRolling` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `configId` char(32) NOT NULL COMMENT '配置编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商主帐号编号',
  `maxCount` smallint(6) DEFAULT NULL COMMENT '最多显示数',
  `width` int(11) DEFAULT '1' COMMENT '图片宽度',
  `height` int(11) DEFAULT NULL COMMENT '图片高度',
  `rollMode` varchar(32) DEFAULT NULL COMMENT '滚动方式',
  `isAuto` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否自动滚动',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '排序删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅图片自定义滚动表';

-- ----------------------------
--  Table structure for `DecorateSysMenu`
-- ----------------------------
DROP TABLE IF EXISTS `DecorateSysMenu`;
CREATE TABLE `DecorateSysMenu` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `menuName` varchar(32) NOT NULL COMMENT '菜单名称',
  `menuUrl` varchar(64) DEFAULT NULL COMMENT '菜单链接',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='展厅装修系统菜单';

-- ----------------------------
--  Table structure for `DepositBack`
-- ----------------------------
DROP TABLE IF EXISTS `DepositBack`;
CREATE TABLE `DepositBack` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `exId` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '流水号',
  `billId` bigint(20) DEFAULT NULL COMMENT '支付平台转账记录ID',
  `brandId` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌商id',
  `brandName` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌商名称',
  `brandsId` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌id',
  `brandsName` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌名称',
  `dealerId` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺id',
  `dealerName` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺名称',
  `paidAmount` decimal(11,2) DEFAULT '0.00' COMMENT '终端商已缴纳押金',
  `backAmount` decimal(11,2) DEFAULT '0.00' COMMENT '品牌商退回押金',
  `depositBackTime` bigint(20) DEFAULT NULL COMMENT '退回时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态 0冻结 1已解冻',
  `dealerjoinId` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '加盟合作id',
  `type` tinyint(4) DEFAULT '0' COMMENT '转账类型（0：终止合作退押金 1：退押金 ）',
  `operateUserId` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人ID',
  `operateUserName` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '操作人',
  `operateTime` bigint(20) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='转账记录表';

-- ----------------------------
--  Table structure for `EmailValidate`
-- ----------------------------
DROP TABLE IF EXISTS `EmailValidate`;
CREATE TABLE `EmailValidate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userId` char(32) NOT NULL COMMENT '经销商/品牌商主账户编号',
  `userCate` tinyint(4) NOT NULL COMMENT '用户类型\r\n            1：经销商\r\n            2：品牌商',
  `emailAddr` varchar(64) NOT NULL,
  `validTime` bigint(20) NOT NULL COMMENT '有效时间',
  `createIp` int(11) NOT NULL COMMENT '建档IP',
  `useTime` bigint(20) DEFAULT NULL COMMENT '使用时间',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '使用状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件验证';

-- ----------------------------
--  Table structure for `FindAccount`
-- ----------------------------
DROP TABLE IF EXISTS `FindAccount`;
CREATE TABLE `FindAccount` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `uesrType` tinyint(4) NOT NULL COMMENT '账户类型\r\n            0：品牌商\r\n            1：终端商',
  `realName` varchar(32) NOT NULL COMMENT '真实姓名/法人代表',
  `certNo` varchar(32) NOT NULL COMMENT '证件号码',
  `certType` tinyint(4) NOT NULL COMMENT '证件类型\r\n            1：身份证\r\n            2：营业执照\r\n            3：其他',
  `userMobile` char(11) NOT NULL COMMENT '新手机号码',
  `certPhoto` varchar(128) NOT NULL,
  `userEmail` varchar(32) DEFAULT NULL COMMENT '新邮件地址',
  `createTime` bigint(20) NOT NULL COMMENT '提交时间',
  `userId` char(32) DEFAULT NULL COMMENT '审核人员编号',
  `checkTime` bigint(20) DEFAULT NULL COMMENT '审核时间',
  `checkState` tinyint(4) DEFAULT NULL COMMENT '审核状态\r\n            0：等待处理\r\n            1：处理完成\r\n            2：不处理/处理失败',
  `checkMark` varchar(64) DEFAULT NULL COMMENT '不通过原因',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='忘记登录账户';

-- ----------------------------
--  Table structure for `FreightCompany`
-- ----------------------------
DROP TABLE IF EXISTS `FreightCompany`;
CREATE TABLE `FreightCompany` (
  `refrenceId` varchar(32) NOT NULL COMMENT '主键',
  `companyName` varchar(100) NOT NULL COMMENT '物流公司名称',
  `freightCode` varchar(50) NOT NULL COMMENT '物流公司编码',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`),
  KEY `INDEX_FREIGHTCOMPANY` (`companyName`,`freightCode`) USING BTREE COMMENT '物流公司名称及编码索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货运（物流）公司信息';

-- ----------------------------
--  Table structure for `HelpCate`
-- ----------------------------
DROP TABLE IF EXISTS `HelpCate`;
CREATE TABLE `HelpCate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateName` varchar(32) NOT NULL COMMENT '分类名称',
  `parentId` char(32) NOT NULL COMMENT '上级分类',
  `cateNo` int(11) NOT NULL COMMENT '帮助分类',
  `helpLevel` int(11) DEFAULT NULL COMMENT '层级',
  `description` text COMMENT '帮助分类描述',
  `showType` tinyint(4) DEFAULT '1' COMMENT '显示类型2：文章显示1：列表显示',
  `showFlag` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否显示\r\n1:显示2：不显示',
  `orderId` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` tinyint(4) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮助分类';

-- ----------------------------
--  Table structure for `HelpInfo`
-- ----------------------------
DROP TABLE IF EXISTS `HelpInfo`;
CREATE TABLE `HelpInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `helpNo` int(11) NOT NULL COMMENT '帮助分类',
  `helpCateId` char(32) NOT NULL COMMENT '分类编号',
  `title` varchar(64) NOT NULL COMMENT '标题',
  `subMark` varchar(512) DEFAULT NULL COMMENT '简单描述',
  `htmlText` text COMMENT '内容',
  `isRecommand` bit(1) NOT NULL COMMENT '是否推荐',
  `isHot` bit(1) NOT NULL COMMENT '是否热门',
  `isFaq` bit(1) NOT NULL COMMENT '是否常见问题',
  `createIp` int(11) DEFAULT NULL COMMENT '创建IP',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` tinyint(4) NOT NULL COMMENT '除删状态：0正常，1：删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帮助内容';

-- ----------------------------
--  Table structure for `JoinInfo`
-- ----------------------------
DROP TABLE IF EXISTS `JoinInfo`;
CREATE TABLE `JoinInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userName` varchar(32) NOT NULL COMMENT '联系人员',
  `userGender` char(4) NOT NULL COMMENT '联系人性别',
  `userMail` varchar(64) DEFAULT NULL COMMENT '邮箱地址',
  `userMobile` char(11) DEFAULT NULL COMMENT '手机号码',
  `userTelphone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `userFax` varchar(20) DEFAULT NULL COMMENT '传真号码',
  `areaNo` int(11) NOT NULL COMMENT '所在区域',
  `joinMark` varchar(5120) DEFAULT NULL COMMENT '留言内容',
  `joinType` tinyint(4) NOT NULL COMMENT '加盟类型\r\n            0：服务商\r\n            1：工厂店',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `createIp` int(11) NOT NULL COMMENT '建档者IP',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加盟入驻信息';

-- ----------------------------
--  Table structure for `LogisticsInfo`
-- ----------------------------
DROP TABLE IF EXISTS `LogisticsInfo`;
CREATE TABLE `LogisticsInfo` (
  `refrenceId` varchar(32) NOT NULL COMMENT '主键',
  `nu` varchar(50) NOT NULL COMMENT '物流编号',
  `com` varchar(50) NOT NULL COMMENT '物流公司',
  `status` int(1) NOT NULL COMMENT '查询结果：0：物流单暂无结果，1：查询成功，2：接口出现异常\n',
  `state` int(1) NOT NULL COMMENT '快递单当前的状态 ：0：在途，即货物处于运输过程中；1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息；2：疑难，货物寄送过程出了问题；3：签收，收件人已签收；4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收；5：派件，即快递正在进行同城派件；6：退回，货物正处于退回发件人的途中；',
  `data` text NOT NULL COMMENT '物流信息 以JSON格式存储\n',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间\n',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态\n',
  PRIMARY KEY (`refrenceId`),
  KEY `INDEX_LOGISTICSINFO` (`nu`,`status`) USING BTREE COMMENT '物流单号和状态索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流详细信息';

-- ----------------------------
--  Table structure for `MessageHistory`
-- ----------------------------
DROP TABLE IF EXISTS `MessageHistory`;
CREATE TABLE `MessageHistory` (
  `refrenceId` varchar(32) NOT NULL COMMENT '消息主键',
  `userMobile` varchar(11) NOT NULL COMMENT '手机号码',
  `message` varchar(5000) NOT NULL COMMENT '短信内容',
  `createTime` bigint(20) NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送历史信息';

-- ----------------------------
--  Table structure for `OrderChangeLog`
-- ----------------------------
DROP TABLE IF EXISTS `OrderChangeLog`;
CREATE TABLE `OrderChangeLog` (
  `refrenceId` varchar(32) NOT NULL COMMENT '主键ID',
  `orderId` bigint(32) NOT NULL COMMENT '订单编号',
  `account` varchar(32) NOT NULL COMMENT '帐户',
  `content` text COMMENT '操作内容',
  `createIp` int(20) DEFAULT NULL COMMENT '创建IP',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`refrenceId`),
  KEY `INDEX_ORDERCHANGELOG` (`orderId`,`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单金额修改记录';

-- ----------------------------
--  Table structure for `OrderNumber`
-- ----------------------------
DROP TABLE IF EXISTS `OrderNumber`;
CREATE TABLE `OrderNumber` (
  `refrenceId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单流水号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB AUTO_INCREMENT=4726 DEFAULT CHARSET=utf8 COMMENT='订单流水号';

-- ----------------------------
--  Table structure for `OrderPayRecord`
-- ----------------------------
DROP TABLE IF EXISTS `OrderPayRecord`;
CREATE TABLE `OrderPayRecord` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` char(32) NOT NULL COMMENT '订单编号',
  `dealerId` char(32) NOT NULL COMMENT '经销商编号（支付人编号）',
  `dealerName` varchar(100) NOT NULL COMMENT '经销商名称',
  `recharegeId` varchar(32) NOT NULL COMMENT '支付编号（充值编号）',
  `payAmount` decimal(11,2) DEFAULT NULL COMMENT '支付金额',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间\n',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商订单支付历史记录';

-- ----------------------------
--  Table structure for `OrderShipRecord`
-- ----------------------------
DROP TABLE IF EXISTS `OrderShipRecord`;
CREATE TABLE `OrderShipRecord` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` char(32) NOT NULL COMMENT '订单编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandName` varchar(100) NOT NULL COMMENT '品牌商名称',
  `shipCount` int(32) NOT NULL COMMENT '发货数量',
  `logisticName` varchar(32) DEFAULT NULL COMMENT '物流公司名称',
  `shipNumber` varchar(32) DEFAULT NULL COMMENT '单号',
  `createTime` bigint(20) NOT NULL COMMENT '发货时间',
  `sourceState` tinyint(4) NOT NULL DEFAULT '1' COMMENT '发货来源（0：交易平台，1：品牌ERP）',
  `errNumber` int(2) DEFAULT NULL COMMENT '物流查询错误次数',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商订单发货记录';

-- ----------------------------
--  Table structure for `ParityColumn`
-- ----------------------------
DROP TABLE IF EXISTS `ParityColumn`;
CREATE TABLE `ParityColumn` (
  `refrenceId` char(32) NOT NULL COMMENT '比价栏目编号',
  `name` varchar(100) NOT NULL COMMENT '比价栏目名称',
  `isRequired` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否必填：　0、否　1、是',
  `sort` int(11) DEFAULT '0' COMMENT '排序号，数值越大越排在前面',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '最后修改时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态  0:正常1：删除',
  `style` varchar(100) DEFAULT NULL COMMENT '样式',
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='比价栏目表';

-- ----------------------------
--  Table structure for `ProductAttrValue`
-- ----------------------------
DROP TABLE IF EXISTS `ProductAttrValue`;
CREATE TABLE `ProductAttrValue` (
  `refrenceId` varchar(18) NOT NULL,
  `productId` varchar(32) NOT NULL COMMENT '商品ID',
  `attributeId` varchar(18) NOT NULL COMMENT '属性ID',
  `attributeItemId` varchar(18) NOT NULL COMMENT '属性项ID',
  `extAttributeValue` varchar(32) NOT NULL,
  `productSkuId` varchar(32) DEFAULT '0',
  `custAttribute` mediumtext COMMENT '自定义商品属性 json串',
  `isSkuAttr` bit(1) NOT NULL,
  `sortOrder` int(11) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `ProductAttrValue_productId` (`productId`) USING BTREE,
  KEY `ProductAttrValue_productSkuId` (`productSkuId`) USING BTREE,
  KEY `ProductAttrValue_attributeId` (`attributeId`) USING BTREE,
  KEY `ProductAttrValue_attributeItemId` (`attributeItemId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品属性值：商品属性和销售属性均存储在此';

-- ----------------------------
--  Table structure for `ProductBaseInfo`
-- ----------------------------
DROP TABLE IF EXISTS `ProductBaseInfo`;
CREATE TABLE `ProductBaseInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `dealNo` int(11) NOT NULL COMMENT '产品类目',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `brandsName` varchar(32) DEFAULT NULL COMMENT '品牌名称',
  `dealerId` varchar(32) DEFAULT NULL COMMENT '当source是 终端商的时候，该字段必填',
  `source` int(2) NOT NULL DEFAULT '1' COMMENT '0交易平台;1门店erp;3品牌erp;4约逛微店',
  `unitNo` varchar(32) DEFAULT NULL COMMENT '单位编码',
  `unit` varchar(4) DEFAULT NULL,
  `productCate` tinyint(4) NOT NULL COMMENT '产品类型,1现货产品，2预订产品',
  `domainName` varchar(32) DEFAULT NULL COMMENT '图片域名',
  `productImage` varchar(128) NOT NULL COMMENT '产品主图',
  `productKeyword` varchar(256) DEFAULT NULL COMMENT '产品关键字',
  `productNo` varchar(64) NOT NULL COMMENT '产品货号',
  `productTitle` varchar(128) NOT NULL COMMENT '产品标题',
  `productAlias` varchar(128) DEFAULT NULL,
  `metaDesc` varchar(512) DEFAULT NULL COMMENT 'meta描述',
  `mobileMetaDesc` varchar(512) DEFAULT NULL COMMENT '手机用描述内容',
  `productPrice` decimal(10,2) NOT NULL COMMENT '对于品牌商系统，该字段为吊牌价格',
  `directPrice` decimal(10,2) DEFAULT NULL COMMENT '产品默认直供价',
  `productCarry` tinyint(4) NOT NULL COMMENT '运费物流，1经销商承担，2品牌商承担',
  `downTime` bigint(20) DEFAULT NULL COMMENT '下架时间',
  `stateSet` varchar(16) DEFAULT NULL COMMENT '交易平台上下架，1 上架 2延期上架 3下架',
  `delFlag` bit(1) NOT NULL COMMENT '0未删 1已删',
  `isResultant` bit(1) DEFAULT NULL COMMENT '是否组合商品',
  `isWholesale` bit(1) DEFAULT NULL COMMENT '是否支持批发：0 不支持，1 支持',
  `isMixWholesale` bit(1) DEFAULT NULL COMMENT '是否支持混批:0 不支持，1 按数量；2 按金额',
  `mixStartNum` bigint(20) DEFAULT '0' COMMENT '混批起批数量',
  `mixStartPay` decimal(10,2) DEFAULT '0.00' COMMENT '混批起批金额',
  `createTime` bigint(20) NOT NULL COMMENT '发布时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `custCateName` varchar(64) DEFAULT NULL COMMENT '品牌店内分类的名称合集,多个名称 / 分隔',
  `barCodeNum` varchar(6) NOT NULL COMMENT '条形码助记码',
  `skuBarCodeSeq` int(11) NOT NULL COMMENT '商品sku 条码助记码自增序列',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductBaseInfo_brandId` (`brandId`) USING BTREE,
  KEY `ProductBaseInfo_brandsId` (`brandsId`) USING BTREE,
  KEY `ProductBaseInfo_dealNo` (`dealNo`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品基础信息表';

-- ----------------------------
--  Table structure for `ProductCatalog`
-- ----------------------------
DROP TABLE IF EXISTS `ProductCatalog`;
CREATE TABLE `ProductCatalog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `cateName` varchar(32) NOT NULL COMMENT '分类名称',
  `cateOrder` int(11) NOT NULL COMMENT '排序编号',
  `parentId` varchar(32) DEFAULT NULL COMMENT '上层分类',
  `cateLevel` tinyint(4) DEFAULT NULL COMMENT '分类级别',
  `domainName` varchar(32) DEFAULT NULL COMMENT '图标域名',
  `cateIcon` varchar(128) DEFAULT NULL COMMENT '分类图标',
  `productNum` int(11) NOT NULL DEFAULT '0' COMMENT '产品数量',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品分类信息';

-- ----------------------------
--  Table structure for `ProductCate`
-- ----------------------------
DROP TABLE IF EXISTS `ProductCate`;
CREATE TABLE `ProductCate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateId` char(32) NOT NULL COMMENT '分类编号',
  `productId` char(32) NOT NULL COMMENT '产品编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductCate_productId` (`productId`) USING BTREE,
  KEY `ProductCate_cateId` (`cateId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品所属分类';

-- ----------------------------
--  Table structure for `ProductCount`
-- ----------------------------
DROP TABLE IF EXISTS `ProductCount`;
CREATE TABLE `ProductCount` (
  `refrenceId` char(32) NOT NULL COMMENT '产品编号',
  `brandId` char(32) NOT NULL,
  `brandsId` char(32) NOT NULL,
  `viewNum` int(11) DEFAULT NULL COMMENT '产品被查看次数',
  `collectNum` int(11) DEFAULT NULL COMMENT '产品被收藏次数',
  `monthSaleNum` int(11) DEFAULT NULL COMMENT '当前月销售量',
  `saleNum` int(11) DEFAULT NULL COMMENT '产品销售数量',
  `createTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `ProductCount_brandId` (`brandId`) USING BTREE,
  KEY `ProductCount_brandsId` (`brandsId`) USING BTREE,
  KEY `ProductCount_productId` (`refrenceId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品计数信息';

-- ----------------------------
--  Table structure for `ProductEdit`
-- ----------------------------
DROP TABLE IF EXISTS `ProductEdit`;
CREATE TABLE `ProductEdit` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号,产品id',
  `state` tinyint(4) NOT NULL COMMENT '审核状态（0：待审，1：已审）',
  `updateTime` bigint(20) NOT NULL COMMENT '修改时间',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品修改';

-- ----------------------------
--  Table structure for `ProductEditAuditLog`
-- ----------------------------
DROP TABLE IF EXISTS `ProductEditAuditLog`;
CREATE TABLE `ProductEditAuditLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `productId` char(32) NOT NULL COMMENT '产品ID',
  `operateId` char(32) NOT NULL COMMENT '操作人ID',
  `content` varchar(256) NOT NULL COMMENT '日志内容',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品修改审核日志';

-- ----------------------------
--  Table structure for `ProductEditDetail`
-- ----------------------------
DROP TABLE IF EXISTS `ProductEditDetail`;
CREATE TABLE `ProductEditDetail` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `productId` char(32) NOT NULL COMMENT '产品ID',
  `changeType` tinyint(4) NOT NULL COMMENT '变更类型（0：产品货号，1：颜色，2：尺码）',
  `oldValue` varchar(64) NOT NULL COMMENT '原始值',
  `newValue` varchar(64) DEFAULT NULL COMMENT '变更值',
  `state` tinyint(4) NOT NULL COMMENT '审核状态（0：未审，1：通过，2：拒绝）',
  `checkResult` varchar(256) DEFAULT NULL COMMENT '审核结果',
  `applyTime` bigint(20) DEFAULT NULL COMMENT '生效时间',
  `vid` char(32) DEFAULT NULL COMMENT '属性ID（变更颜色，尺码时用到）',
  `attributeIcon` varchar(128) DEFAULT NULL COMMENT '图标（变更颜色时用到）',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品修改详情';

-- ----------------------------
--  Table structure for `ProductExtInfo`
-- ----------------------------
DROP TABLE IF EXISTS `ProductExtInfo`;
CREATE TABLE `ProductExtInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `productBulk` decimal(5,2) DEFAULT NULL COMMENT '产品体积',
  `productWeight` decimal(11,3) DEFAULT NULL COMMENT '产品重量',
  `tempId` char(32) DEFAULT NULL COMMENT '预订模板编号',
  `orderName` varchar(64) DEFAULT NULL COMMENT '预订名称',
  `orderStart` bigint(20) DEFAULT NULL COMMENT '订货时间',
  `orderEnd` bigint(20) DEFAULT NULL COMMENT '订货结束时间',
  `startNum` int(11) DEFAULT NULL COMMENT '起批量',
  `orderNum` int(11) DEFAULT NULL COMMENT '订货量',
  `outStart` bigint(20) DEFAULT NULL COMMENT '出货开始时间',
  `outEnd` bigint(20) DEFAULT NULL COMMENT '出货结束时间',
  `orderSelect` tinyint(4) DEFAULT NULL COMMENT '订金选择',
  `orderMoney` int(11) DEFAULT NULL COMMENT '订单金额百分比',
  `productStore` int(11) NOT NULL COMMENT '产品库存',
  `minStore` int(11) DEFAULT NULL COMMENT '最少库存值',
  `productMark` text COMMENT '产品说明',
  `patchMark` varchar(1024) DEFAULT NULL COMMENT '批量说明',
  `productBear` varchar(1024) DEFAULT NULL COMMENT '退换货承诺',
  `beginTime` bigint(20) DEFAULT NULL COMMENT '设定的时间',
  `productGroom` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否推荐，1推荐，0不推荐',
  `productCent` int(11) DEFAULT NULL COMMENT '产品积分',
  `stopState` tinyint(4) NOT NULL DEFAULT '0' COMMENT '终止状态：0：正常1：终止',
  `topTime` bigint(20) DEFAULT NULL COMMENT '置顶时间',
  `similarPrice` decimal(11,2) DEFAULT NULL COMMENT '同类产品价格',
  `activitySort` int(11) DEFAULT '0' COMMENT '活动排序号',
  `isDiscount` bit(1) DEFAULT NULL,
  `isCredit` bit(1) DEFAULT b'0' COMMENT '是否支持授信',
  `isSample` bit(1) DEFAULT b'0' COMMENT '是否支持拿样： 0、否 1、是',
  `freTemplateId` char(32) DEFAULT NULL COMMENT '运费模版编号',
  `barCode` varchar(32) DEFAULT NULL COMMENT '条形码',
  `recycle` bit(1) DEFAULT b'1' COMMENT '是否在回收站显示1：显示,0不显示',
  `isShow` bit(1) NOT NULL DEFAULT b'1',
  `companyCode` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品品牌扩展信息表';

-- ----------------------------
--  Table structure for `ProductImage`
-- ----------------------------
DROP TABLE IF EXISTS `ProductImage`;
CREATE TABLE `ProductImage` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `productId` char(32) NOT NULL COMMENT '产品编号',
  `domainName` varchar(32) NOT NULL,
  `photoName` varchar(64) NOT NULL COMMENT '图片原名称',
  `imageName` varchar(128) NOT NULL COMMENT '图片新名称',
  `imageMark` varchar(256) DEFAULT NULL,
  `createTime` bigint(20) NOT NULL COMMENT '上传时间',
  `orderId` int(11) DEFAULT NULL COMMENT '排序编号',
  `isMain` bit(1) DEFAULT NULL COMMENT '是否是主图',
  `createIp` int(11) NOT NULL COMMENT '上传者IP',
  `attributeItemId` varchar(32) DEFAULT NULL COMMENT '商品​颜色属性项ID',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductImage_productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌商产品图片信息';

-- ----------------------------
--  Table structure for `ProductParity`
-- ----------------------------
DROP TABLE IF EXISTS `ProductParity`;
CREATE TABLE `ProductParity` (
  `refrenceId` char(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '编号',
  `productId` char(32) NOT NULL COMMENT '产品编号',
  `parityId` char(32) NOT NULL COMMENT '比价栏目编号',
  `price` decimal(11,2) DEFAULT NULL COMMENT '比价价格',
  `url` varchar(512) DEFAULT NULL COMMENT '链接地址',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '修改时间',
  `isShow` bit(1) NOT NULL COMMENT '是否显示\\n0：显示\\n1：不显示',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除:0是未删除,1是删除',
  `keyWord` varchar(30) DEFAULT NULL COMMENT '关键字(不超过15个汉字)',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductParity_productId` (`productId`) USING BTREE,
  KEY `ProductParity_parityId` (`parityId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品比价表';

-- ----------------------------
--  Table structure for `ProductSampleLog`
-- ----------------------------
DROP TABLE IF EXISTS `ProductSampleLog`;
CREATE TABLE `ProductSampleLog` (
  `refrenceId` char(32) NOT NULL,
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `productId` char(32) NOT NULL COMMENT '产品编号',
  `dealerId` char(32) NOT NULL COMMENT '经端商编号',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `productCount` int(11) NOT NULL DEFAULT '0' COMMENT '拿样产品购买数量',
  `orderId` varchar(32) NOT NULL COMMENT '订单编号',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='终端商购买产品拿样日志';

-- ----------------------------
--  Table structure for `ProductSku`
-- ----------------------------
DROP TABLE IF EXISTS `ProductSku`;
CREATE TABLE `ProductSku` (
  `refrenceId` varchar(18) NOT NULL,
  `productId` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `source` int(2) DEFAULT NULL COMMENT '来源',
  `productSkuNo` varchar(96) DEFAULT NULL COMMENT '商品SKU编码',
  `extProductSkuNo` varchar(96) DEFAULT NULL COMMENT '商品SKU外部编码',
  `productSkuName` varchar(128) NOT NULL COMMENT 'SKU商品名称',
  `productSkuAlias` varchar(128) DEFAULT NULL COMMENT 'SKU商品别名',
  `quantity` int(11) DEFAULT NULL COMMENT '库存',
  `saleUnit` varchar(18) DEFAULT NULL COMMENT '销售单位',
  `attrItemIds` varchar(128) DEFAULT NULL COMMENT '属性项ID集合',
  `brandsId` varchar(32) DEFAULT NULL COMMENT '品牌ID',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  `delFlag` bit(1) DEFAULT b'0' COMMENT '0:未删除,1:已删除',
  `attributeValueId1` varchar(32) DEFAULT NULL,
  `attributeValueId2` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`refrenceId`),
  KEY `ProductSku_brandsId` (`brandsId`) USING BTREE,
  KEY `ProductSku_productId` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品sku表';

-- ----------------------------
--  Table structure for `ProductSkuBarcode`
-- ----------------------------
DROP TABLE IF EXISTS `ProductSkuBarcode`;
CREATE TABLE `ProductSkuBarcode` (
  `refrenceId` varchar(32) NOT NULL,
  `productSkuId` varchar(32) DEFAULT NULL,
  `productId` varchar(32) NOT NULL,
  `barCodeType` tinyint(4) NOT NULL COMMENT '1 企业编码;2 8637通用码',
  `barCodeValue` varchar(100) DEFAULT NULL COMMENT 'SKU条码',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除:0是未删除,1是删除',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductSkuBarcode_productId` (`productId`) USING BTREE,
  KEY `ProductSkuBarcode_productSkuId` (`productSkuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品条码';

-- ----------------------------
--  Table structure for `ProductSkuPrice`
-- ----------------------------
DROP TABLE IF EXISTS `ProductSkuPrice`;
CREATE TABLE `ProductSkuPrice` (
  `refrenceId` varchar(18) NOT NULL,
  `productSkuId` varchar(18) NOT NULL COMMENT '商品SKUID',
  `productId` varchar(32) NOT NULL COMMENT '商品ID',
  `agentPrice` decimal(11,2) DEFAULT NULL COMMENT '省代价格',
  `samplePrice` decimal(11,2) DEFAULT NULL COMMENT '拿样价格',
  `directPrice` decimal(11,2) DEFAULT NULL COMMENT 'sku直供价',
  `factoryStorePrice` decimal(11,2) DEFAULT NULL COMMENT '工厂店零售价',
  `enablePriceRange` bit(1) NOT NULL DEFAULT b'0' COMMENT '启用区间价格',
  `creditPrice` decimal(11,2) DEFAULT NULL COMMENT '授信加',
  `skuPrice` decimal(11,2) DEFAULT NULL COMMENT 'sku吊牌价格',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `costPrice` decimal(11,2) DEFAULT NULL COMMENT '成本价',
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductSkuPrice_productId` (`productId`) USING BTREE,
  KEY `ProductSkuPrice_productSkuId` (`productSkuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品sku价格表';

-- ----------------------------
--  Table structure for `ProductSkuPriceRange`
-- ----------------------------
DROP TABLE IF EXISTS `ProductSkuPriceRange`;
CREATE TABLE `ProductSkuPriceRange` (
  `refrenceId` varchar(18) NOT NULL,
  `productSkuId` varchar(18) DEFAULT NULL,
  `rangeStart` int(11) DEFAULT NULL,
  `rangeEnd` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品sku价格区间';

-- ----------------------------
--  Table structure for `ProductSkuRfid`
-- ----------------------------
DROP TABLE IF EXISTS `ProductSkuRfid`;
CREATE TABLE `ProductSkuRfid` (
  `refrenceId` varchar(18) NOT NULL,
  `productSkuId` varchar(18) DEFAULT NULL COMMENT '商品SKUID',
  `createTime` bigint(20) DEFAULT NULL,
  `updateTime` bigint(20) DEFAULT NULL,
  `state` tinyint(2) DEFAULT NULL COMMENT '1 正常 2 作废 3 已售',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品sku和RFID关系表';

-- ----------------------------
--  Table structure for `ProductViewLog`
-- ----------------------------
DROP TABLE IF EXISTS `ProductViewLog`;
CREATE TABLE `ProductViewLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `productId` char(32) NOT NULL COMMENT '公告标题',
  `productTitle` varchar(128) NOT NULL COMMENT '商品标题',
  `userId` char(32) NOT NULL COMMENT '用户编号',
  `userName` varchar(100) NOT NULL COMMENT '用户名称/经销商，品牌商名称',
  `productImage` varchar(128) NOT NULL COMMENT '商品图片',
  `productPrice` decimal(11,2) NOT NULL COMMENT '商品价格',
  `userCate` tinyint(4) NOT NULL COMMENT '1:品牌商\r\n            2：经销商',
  `viewNum` int(11) NOT NULL DEFAULT '1' COMMENT '浏览次数',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标识：0未删除，1删除',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductViewLog_userId` (`userId`) USING BTREE,
  KEY `ProductViewLog_productId` (`productId`) USING BTREE,
  KEY `ProductViewLog_createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品浏览历史记录';

-- ----------------------------
--  Table structure for `RefundNumber`
-- ----------------------------
DROP TABLE IF EXISTS `RefundNumber`;
CREATE TABLE `RefundNumber` (
  `refundId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '退款编号',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  PRIMARY KEY (`refundId`)
) ENGINE=InnoDB AUTO_INCREMENT=441 DEFAULT CHARSET=utf8 COMMENT='退款流水号';

-- ----------------------------
--  Table structure for `Regional`
-- ----------------------------
DROP TABLE IF EXISTS `Regional`;
CREATE TABLE `Regional` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `areaNo` int(11) NOT NULL COMMENT '区域编码',
  `areaName` varchar(32) NOT NULL COMMENT '区域名称',
  `areaParent` varchar(32) NOT NULL COMMENT '上层编码',
  `areaLevel` tinyint(4) NOT NULL COMMENT '所在层级',
  `areaOrder` int(11) NOT NULL COMMENT '区域排序',
  `createTime` bigint(20) NOT NULL,
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`),
  KEY `Regional_areaNo` (`areaNo`) USING BTREE,
  KEY `Regional_areaName` (`areaName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全国区域信息';

-- ----------------------------
--  Table structure for `RegionalCode`
-- ----------------------------
DROP TABLE IF EXISTS `RegionalCode`;
CREATE TABLE `RegionalCode` (
  `refrenceId` varchar(32) NOT NULL,
  `areaNos` varchar(2048) DEFAULT NULL COMMENT '划分的所属区域包含的 逗号分隔 eg. 110000,122200',
  `areaName` varchar(64) DEFAULT NULL COMMENT '区域描述',
  `createTime` bigint(20) DEFAULT NULL,
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区编码与常规描述的转换表';

-- ----------------------------
--  Table structure for `RulesCate`
-- ----------------------------
DROP TABLE IF EXISTS `RulesCate`;
CREATE TABLE `RulesCate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateName` varchar(16) NOT NULL COMMENT '类别名称',
  `cateMark` varchar(128) DEFAULT NULL COMMENT '类别描述',
  `cateType` tinyint(4) DEFAULT '1' COMMENT '规则分类类型1：文章，2：列表',
  `parentId` varchar(32) DEFAULT NULL COMMENT '上层编号',
  `cateText` text COMMENT '规则内容',
  `cateOrder` int(11) NOT NULL COMMENT '排序编号',
  `cateLevel` tinyint(4) NOT NULL COMMENT '类别层级',
  `articleNum` int(11) NOT NULL DEFAULT '0' COMMENT '文章数量',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站规则类别';

-- ----------------------------
--  Table structure for `RulesInfo`
-- ----------------------------
DROP TABLE IF EXISTS `RulesInfo`;
CREATE TABLE `RulesInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateId` char(32) NOT NULL COMMENT '类别编号',
  `articleTitle` varchar(128) NOT NULL COMMENT '规则主题',
  `articleText` text NOT NULL COMMENT '规则内容',
  `domainName` varchar(128) DEFAULT NULL COMMENT '规则图片域名',
  `articleImage` varchar(128) DEFAULT NULL COMMENT '规则图片',
  `createTime` bigint(20) NOT NULL COMMENT '发布时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `viewNum` int(11) DEFAULT '0' COMMENT '查看次数',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则内容信息';

-- ----------------------------
--  Table structure for `RulesInfoLog`
-- ----------------------------
DROP TABLE IF EXISTS `RulesInfoLog`;
CREATE TABLE `RulesInfoLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `rulesId` char(32) DEFAULT NULL COMMENT '规则编号',
  `articleTitle` varchar(128) NOT NULL COMMENT '文章主题',
  `articleText` text NOT NULL COMMENT '文章内容',
  `domainName` varchar(128) DEFAULT NULL COMMENT '文章图片域名',
  `articleImage` varchar(128) DEFAULT NULL COMMENT '文章图片',
  `createTime` bigint(20) NOT NULL COMMENT '发布时间',
  `viewNum` int(11) DEFAULT '0' COMMENT '查看次数',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则内容历史记录';

-- ----------------------------
--  Table structure for `SecurityCert`
-- ----------------------------
DROP TABLE IF EXISTS `SecurityCert`;
CREATE TABLE `SecurityCert` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userIdId` char(32) NOT NULL COMMENT '经销商/品牌商主帐号编号',
  `userName` varchar(100) NOT NULL COMMENT '经销商/品牌商名称',
  `userCate` tinyint(32) NOT NULL COMMENT '账户类型\r\n            0：品牌商，1：经销商',
  `oldMobile` varchar(11) DEFAULT NULL COMMENT '原手机号码',
  `userMobile` varchar(11) DEFAULT NULL COMMENT '修改的手机号码',
  `userPhoto` varchar(128) DEFAULT NULL COMMENT '身份证证件正面照片',
  `certPhoto` varchar(128) DEFAULT NULL COMMENT '营业执照图片',
  `applyType` tinyint(4) NOT NULL DEFAULT '1' COMMENT '申请类型：1:更换手机号码',
  `createTime` bigint(20) NOT NULL COMMENT '申请时间',
  `createIp` int(11) NOT NULL COMMENT '创建IP',
  `actState` tinyint(4) DEFAULT NULL COMMENT '操作结果',
  `userId` char(32) DEFAULT NULL COMMENT '操作人员(CRM系统员工编号）',
  `actionDate` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `actionIp` int(11) DEFAULT NULL COMMENT '操作人IP',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商/品牌商申请更改手机认证';

-- ----------------------------
--  Table structure for `SmsTemplate`
-- ----------------------------
DROP TABLE IF EXISTS `SmsTemplate`;
CREATE TABLE `SmsTemplate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `smsKey` char(32) NOT NULL COMMENT '短信模块Key',
  `templateName` varchar(32) NOT NULL COMMENT '短信名称',
  `content` varchar(256) NOT NULL COMMENT '短信内容',
  `place` varchar(64) DEFAULT NULL COMMENT '占位符',
  `remark` varchar(256) DEFAULT NULL COMMENT '描述',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信模板';

-- ----------------------------
--  Table structure for `Suggestions`
-- ----------------------------
DROP TABLE IF EXISTS `Suggestions`;
CREATE TABLE `Suggestions` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `email` char(32) NOT NULL COMMENT '电子邮件',
  `message` varchar(6000) DEFAULT NULL COMMENT '留言信息',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `delFlag` bit(1) DEFAULT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='意见和建议';

-- ----------------------------
--  Table structure for `TelCode`
-- ----------------------------
DROP TABLE IF EXISTS `TelCode`;
CREATE TABLE `TelCode` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userMobile` char(11) NOT NULL COMMENT '手机号码',
  `verifyCode` char(6) NOT NULL COMMENT '验证码',
  `cusType` tinyint(4) DEFAULT NULL COMMENT '客户类别',
  `verifyType` char(3) NOT NULL COMMENT '验证码类型',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `validTime` bigint(20) DEFAULT NULL COMMENT '有效时间',
  `createIp` int(11) NOT NULL COMMENT '建档IP',
  `useState` bit(1) NOT NULL COMMENT '使用状态',
  `useTime` bigint(20) DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`refrenceId`),
  KEY `TelCode_userMobile` (`userMobile`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手机验证码';

-- ----------------------------
--  Table structure for `TradeDetails`
-- ----------------------------
DROP TABLE IF EXISTS `TradeDetails`;
CREATE TABLE `TradeDetails` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `orderId` char(32) NOT NULL COMMENT '订单编号',
  `serialNumber` varchar(32) NOT NULL COMMENT '交易流水号',
  `sendUserId` char(32) NOT NULL COMMENT '出款方编号',
  `sendName` varchar(100) NOT NULL COMMENT '出款方名称',
  `recUserId` char(32) DEFAULT NULL COMMENT '收款方编号',
  `recName` varchar(100) DEFAULT NULL COMMENT '收款方名称',
  `balance` decimal(10,2) NOT NULL COMMENT '出款金额',
  `title` varchar(128) DEFAULT NULL COMMENT '交易内容(如：订单商品标题）',
  `tradeType` tinyint(4) NOT NULL COMMENT '交易类型\r\n            1：订单支付\r\n            2：短信支付',
  `payState` tinyint(4) DEFAULT NULL COMMENT '支付状态\r\n0：未支付\r\n1：部分支付\r\n2：全部支付',
  `tradeState` tinyint(4) NOT NULL COMMENT '交易状态\r\n            1等待付款\r\n            2 等待发货\r\n            3:部分发货\r\n            4 等待确认收货\r\n            9 交易成功\r\n            10 交易关闭',
  `createTime` bigint(20) NOT NULL COMMENT '交易时间',
  `updatetime` bigint(20) NOT NULL COMMENT '修改时间',
  `pointBalance` decimal(10,2) DEFAULT NULL,
  `flag` int(11) NOT NULL DEFAULT '0' COMMENT '迁移标记',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易明细表';

-- ----------------------------
--  Table structure for `UploadAttCate`
-- ----------------------------
DROP TABLE IF EXISTS `UploadAttCate`;
CREATE TABLE `UploadAttCate` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `cateKey` varchar(32) DEFAULT NULL COMMENT '类型',
  `attName` varchar(32) NOT NULL COMMENT '模块Key',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传图片附件类型';

-- ----------------------------
--  Table structure for `UploadAttSize`
-- ----------------------------
DROP TABLE IF EXISTS `UploadAttSize`;
CREATE TABLE `UploadAttSize` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `attCateId` varchar(32) NOT NULL COMMENT '附件类型',
  `cateKey` varchar(32) DEFAULT NULL COMMENT '类型Key',
  `height` int(11) DEFAULT NULL COMMENT '高度',
  `width` int(11) NOT NULL COMMENT '宽度',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) NOT NULL COMMENT '更新时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传图片附件类型大小';

-- ----------------------------
--  Table structure for `UserInfo`
-- ----------------------------
DROP TABLE IF EXISTS `UserInfo`;
CREATE TABLE `UserInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userMobile` varchar(16) NOT NULL COMMENT '手机号码',
  `mobileVerify` bit(1) NOT NULL DEFAULT b'0' COMMENT '手机认证状态',
  `userMail` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `mailVerify` bit(1) DEFAULT b'0' COMMENT '邮箱认证状态',
  `userPwd` char(32) NOT NULL COMMENT '密码',
  `userSalt` char(6) NOT NULL COMMENT '密码盐值',
  `roleId` char(32) DEFAULT NULL COMMENT '角色',
  `userState` tinyint(4) NOT NULL COMMENT '帐号状态\r\n0:等待审核\r\n1:审核通过\r\n2：冻结\r\n3:审核不通过\r\n',
  `parentId` varchar(32) DEFAULT NULL COMMENT '主帐号编号',
  `userName` varchar(32) NOT NULL COMMENT '名称',
  `areaNo` int(6) DEFAULT NULL COMMENT '所在地区',
  `registerTime` bigint(20) NOT NULL COMMENT '注册时间',
  `registerIp` int(11) DEFAULT NULL COMMENT '注册IP',
  `userType` tinyint(4) DEFAULT '0' COMMENT '帐户类型：0 品牌商，1终端商',
  `loginIpStr` varchar(32) DEFAULT '' COMMENT '上一次登录IP',
  `registIpStr` varchar(32) DEFAULT '' COMMENT '注册IP',
  `userSort` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型：0、普通用户　1、代理商用户　2、内部用户',
  `payUserId` bigint(20) DEFAULT NULL COMMENT '支付用户ID',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`),
  KEY `BrandUserm_userMobile` (`userMobile`) USING BTREE,
  KEY `BrandUserm_userPwd` (`userPwd`) USING BTREE,
  KEY `BrandUserm_userSalt` (`userSalt`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
--  Table structure for `UserOnlineService`
-- ----------------------------
DROP TABLE IF EXISTS `UserOnlineService`;
CREATE TABLE `UserOnlineService` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userType` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  `onlineDateType` tinyint(4) DEFAULT NULL COMMENT '在线时间段（0.周一到周五，1.周一到周六，2.周一到周日）',
  `onlineBeginTime` bigint(20) DEFAULT NULL COMMENT '开始时间',
  `onlineEndTime` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `userId` char(32) DEFAULT NULL COMMENT '用户ID',
  `showed` int(1) DEFAULT NULL COMMENT '是否显示 0、不显示　1、显示',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服在线信息表';

-- ----------------------------
--  Table structure for `UserOnlineServiceDetail`
-- ----------------------------
DROP TABLE IF EXISTS `UserOnlineServiceDetail`;
CREATE TABLE `UserOnlineServiceDetail` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `userOnlineId` char(32) DEFAULT NULL COMMENT '在线服务编号',
  `qq` char(20) DEFAULT NULL COMMENT 'QQ号',
  `name` char(100) DEFAULT NULL COMMENT '客服名称',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服在线信息详情表';

-- ----------------------------
--  Table structure for `UserOperationLog`
-- ----------------------------
DROP TABLE IF EXISTS `UserOperationLog`;
CREATE TABLE `UserOperationLog` (
  `refrenceId` varchar(32) NOT NULL COMMENT '用户操作日志ID',
  `objectId` varchar(32) NOT NULL COMMENT '操作对象ID',
  `userId` varchar(32) DEFAULT NULL COMMENT '操作人id',
  `userName` varchar(100) DEFAULT NULL COMMENT '操作人',
  `type` int(11) NOT NULL COMMENT '操作类型   0：操作品牌商押金   1：操作订单金额或者运费   2：操作退款  3：操作财务帐  4：操作产品  5：操作活动产品',
  `event` varchar(500) NOT NULL COMMENT '操作事件',
  `eventTime` bigint(20) NOT NULL COMMENT '事件操作时间',
  `loginType` tinyint(4) DEFAULT NULL COMMENT '登录IP',
  `loginIP` int(11) DEFAULT NULL COMMENT '登录IP',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户操作日志';

-- ----------------------------
--  Table structure for `WebBrandsShow`
-- ----------------------------
DROP TABLE IF EXISTS `WebBrandsShow`;
CREATE TABLE `WebBrandsShow` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `brandsId` char(32) NOT NULL COMMENT '品牌编号',
  `showType` tinyint(4) DEFAULT '1' COMMENT '显示类型：1:感兴趣的品牌\r\n            ',
  `orderId` int(11) DEFAULT NULL COMMENT '排序编号',
  `image` varchar(128) DEFAULT NULL,
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态\r\n            0:正常1：删除',
  PRIMARY KEY (`refrenceId`),
  KEY `WebBrandsShow_brandsId` (`brandsId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页感兴趣品牌展示';

-- ----------------------------
--  Table structure for `WebDefTmpLog`
-- ----------------------------
DROP TABLE IF EXISTS `WebDefTmpLog`;
CREATE TABLE `WebDefTmpLog` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `modelKey` varchar(32) NOT NULL COMMENT '模块Key',
  `htmlText` text COMMENT '模块内容',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面模版定义日志';

-- ----------------------------
--  Table structure for `WebServiceCom`
-- ----------------------------
DROP TABLE IF EXISTS `WebServiceCom`;
CREATE TABLE `WebServiceCom` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `comName` varchar(64) NOT NULL COMMENT '服务商名称',
  `comPhoto` varchar(128) DEFAULT NULL COMMENT '服务商图片',
  `comCert` varchar(64) DEFAULT NULL COMMENT '资质认证',
  `comTel` varchar(128) DEFAULT NULL COMMENT '联系电话',
  `comEmail` varchar(64) DEFAULT NULL COMMENT '联系邮箱',
  `comMobile` char(11) DEFAULT NULL COMMENT '联系手机',
  `subMark` varchar(512) DEFAULT NULL COMMENT '服务商简介',
  `createTime` bigint(20) NOT NULL,
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '0：正常\r\n            1：删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站服务商';

-- ----------------------------
--  Table structure for `WebServiceItems`
-- ----------------------------
DROP TABLE IF EXISTS `WebServiceItems`;
CREATE TABLE `WebServiceItems` (
  `refrenceId` char(32) NOT NULL COMMENT '资料编号',
  `serviceName` varchar(64) NOT NULL COMMENT '服务名称',
  `servicePhoto` varchar(128) NOT NULL COMMENT '服务图片',
  `commentNum` int(11) NOT NULL COMMENT '推荐指数',
  `servicerCate` tinyint(4) NOT NULL COMMENT '服务类别',
  `buyNum` int(11) NOT NULL COMMENT '购买人数',
  `viewNum` int(11) NOT NULL COMMENT '浏览次数',
  `chargType` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1：续期\r\n            2：增加数量',
  `price` decimal(11,2) NOT NULL COMMENT '服务单价',
  `minBuyNum` int(11) NOT NULL COMMENT '服务起买数',
  `servicePrice` decimal(11,2) NOT NULL COMMENT '服务金额',
  `comId` char(32) NOT NULL COMMENT '服务方编号',
  `subMark` varchar(512) DEFAULT NULL COMMENT '服务简介',
  `serviceMark` text COMMENT '服务详细',
  `serviceType` tinyint(4) NOT NULL COMMENT '1：品牌商\r\n            2：:终端商\r\n            ',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站服务项目';

-- ----------------------------
--  Table structure for `WebUnit`
-- ----------------------------
DROP TABLE IF EXISTS `WebUnit`;
CREATE TABLE `WebUnit` (
  `refrenceId` char(32) NOT NULL COMMENT '编号',
  `unitName` varchar(10) DEFAULT NULL COMMENT '单位名称',
  `sortId` int(11) DEFAULT NULL COMMENT '排序字段',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态0：未删除1：已删除',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='度量单位\n';


-- ----------------------------
--  Table structure for `ProductWeightInfo`
-- ----------------------------
DROP TABLE IF EXISTS `ProductWeightInfo`;
CREATE TABLE `ProductWeightInfo` (
`refrenceId`  char(32) NOT NULL ,
`brandId`  char(32) NULL ,
`brandsId`  char(32) NOT NULL ,
`weight`  int(11) NOT NULL ,
`season`  smallint(4) NOT NULL ,
`createTime`  bigint(20) NOT NULL ,
`updateTime`  bigint(20) NULL ,
`delFlag`  bit(1) NOT NULL 
)
;

ALTER TABLE `ProductWeightInfo`
MODIFY COLUMN `refrenceId`  char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '产品id ' FIRST ,
MODIFY COLUMN `brandId`  char(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '品牌商id ' AFTER `refrenceId`,
MODIFY COLUMN `brandsId`  char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '品牌id ' AFTER `brandId`,
MODIFY COLUMN `weight`  int(11) NOT NULL COMMENT '权重1-100 ' AFTER `brandsId`,
MODIFY COLUMN `season`  smallint(4) NOT NULL COMMENT '季节，1，2，3，4 ' AFTER `weight`,
MODIFY COLUMN `createTime`  bigint(20) NOT NULL COMMENT '创建时间 ' AFTER `season`,
MODIFY COLUMN `updateTime`  bigint(20) NULL DEFAULT NULL COMMENT '最后更新时间 ' AFTER `createTime`,
MODIFY COLUMN `delFlag`  bit(1) NOT NULL DEFAULT 0 COMMENT '删除标记，1删除，0未删除，默认0 ' AFTER `updateTime`;

SET FOREIGN_KEY_CHECKS = 1;
