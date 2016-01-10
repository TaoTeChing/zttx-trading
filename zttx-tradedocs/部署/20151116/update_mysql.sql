/*
 Navicat Premium Data Transfer

 Source Server         : 172.16.16.200
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : 172.16.16.200
 Source Database       : Zttx-Web-New

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : utf-8

 Date: 11/18/2015 08:59:04 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `FinancialPay`;
CREATE TABLE `FinancialPay` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `payExtId` varchar(18) COLLATE utf8_bin NOT NULL COMMENT '支付流水号',
  `dealerId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '经销商id',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商id',
  `payAmount` decimal(11,2) NOT NULL COMMENT '支付金额',
  `payState` tinyint(4) NOT NULL COMMENT '支付状态（0未支付，1支付成功，2支付失败）',
  `beginTime` bigint(20) NOT NULL COMMENT '支付的期款开始时间',
  `endTime` bigint(20) NOT NULL COMMENT '支付的期款结束时间',
  `amountType` tinyint(4) NOT NULL COMMENT '支付的款项类型（0返点财务帐，1终端商财务帐）',
  `delFlag` bit(1) NOT NULL COMMENT '删除标志（0未删除，1删除）',
  PRIMARY KEY (`refrenceId`),
  KEY `INDEX_PAYEXTID` (`payExtId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='财务账支付记录表';

-- ----------------------------
--  Table structure for `PointSaleSum`
-- ----------------------------
DROP TABLE IF EXISTS `PointSaleSum`;
CREATE TABLE `PointSaleSum` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `dealerId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '经销商id',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商id',
  `saleNumSum` int(11) NOT NULL COMMENT '销量和',
  `salePriceSum` decimal(10,0) NOT NULL COMMENT '销售额和',
  `pointPriceSum` decimal(10,0) NOT NULL COMMENT '返点金额和（非返点价和）',
  `costPriceSum` decimal(10,0) NOT NULL COMMENT '成本价和',
  `paidPriceSum` decimal(10,0) NOT NULL COMMENT '已付成本价和',
  `debtPriceSum` decimal(10,0) NOT NULL COMMENT '未付成本价和',
  `isPaid` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否支付：0为支付 1支付',
  `erpTime` bigint(20) NOT NULL COMMENT 'erp生成时间',
  `createTime` bigint(20) NOT NULL COMMENT 'zttx调度插入时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标识：0未删除 1删除',
  PRIMARY KEY (`refrenceId`),
  KEY `PointSaleSum_dealerId` (`dealerId`),
  KEY `PointSaleSum_brandId` (`brandId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='返点类型产品门店销售统计和表';

-- ----------------------------
--  Table structure for `CreditToPoint`
-- ----------------------------
DROP TABLE IF EXISTS `CreditToPoint`;
CREATE TABLE `CreditToPoint` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商id',
  `dealerId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '经销商id',
  `brandsId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌id',
  `productId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品id',
  `productSkuId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品skuid',
  `costPirce` decimal(11,2) NOT NULL COMMENT 'sku成本价',
  `baseStock` int(11) NOT NULL COMMENT 'erp基础库存量',
  `sumCost` decimal(11,2) NOT NULL COMMENT '成本合计',
  `pointPrice` decimal(11,2) NOT NULL COMMENT '返点价',
  `pointPercent` decimal(5,2) NOT NULL COMMENT '返点比例',
  `erpTime` bigint(32) NOT NULL COMMENT 'erp交易类型变更时间',
  `createTime` bigint(32) NOT NULL COMMENT '交易平台记录创建时间',
  PRIMARY KEY (`refrenceId`),
  KEY `CreditToPoint_BrandId` (`brandId`),
  KEY `CreditToPoint_BrandsId` (`brandsId`),
  KEY `CreditToPoint_DealerId` (`dealerId`),
  KEY `CreditToPoint_ProductId` (`productId`),
  KEY `CreditToPoint_ProductSkuId` (`productSkuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='erp铺货变返点sku详细信息表';

-- ----------------------------
--  Table structure for `PointRefund`
-- ----------------------------
DROP TABLE IF EXISTS `PointRefund`;
CREATE TABLE `PointRefund` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `dealerId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '经销商id',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商id',
  `brandsId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌id',
  `productId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品id',
  `productSkuId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品skuId',
  `refundNum` int(11) NOT NULL COMMENT '退货数量',
  `pointPrice` decimal(11,2) NOT NULL COMMENT '返点价',
  `pointPercent` decimal(5,2) NOT NULL COMMENT '返点比例',
  `erpTime` bigint(20) NOT NULL COMMENT 'erp退货记录生成时间',
  `createTime` bigint(20) NOT NULL COMMENT '交易平台记录创建时间',
  PRIMARY KEY (`refrenceId`),
  KEY `PointRefund_BrandId` (`brandId`),
  KEY `PointRefund_BrandsId` (`brandsId`),
  KEY `PointRefund_DealerId` (`dealerId`),
  KEY `PointRefund_ProductId` (`productId`),
  KEY `PointRefund_SkuId` (`productSkuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='产品返点退货记录表';

-- ----------------------------
-- Table structure for PointSaleDetail
-- ----------------------------
DROP TABLE IF EXISTS `PointSaleDetail`;
CREATE TABLE `PointSaleDetail` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `dealerId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '经销商Id',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商Id',
  `brandsId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌Id',
  `productId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品Id',
  `productSkuId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品skuId',
  `saleNum` int(11) NOT NULL COMMENT '销售数量',
  `refundNum` int(11) NOT NULL COMMENT '退货数量',
  `salePrice` decimal(11,2) NOT NULL COMMENT '销售金额',
  `refundPrice` decimal(11,2) NOT NULL COMMENT '退货金额',
  `lossNum` int(11) NOT NULL COMMENT '盘亏数量',
  `lossCost` decimal(11,2) NOT NULL COMMENT '盘亏成本',
  `saleCost` decimal(11,2) NOT NULL COMMENT '销售成本',
  `refundCost` decimal(11,2) NOT NULL COMMENT '退款成本',
  `countCost` decimal(11,2) NOT NULL COMMENT '合计成本：（盘亏+销售-退货）成本',
  `erpTime` bigint(20) NOT NULL COMMENT 'erp记录生成时间',
  `createTime` bigint(20) NOT NULL COMMENT '交易平台创建时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`refrenceId`),
  KEY `PointSaleDetail_BrandId` (`brandId`),
  KEY `PointSaleDetail_BrandsId` (`brandsId`),
  KEY `PointSaleDetail_DealerId` (`dealerId`),
  KEY `PointSaleDetail_ProductId` (`productId`),
  KEY `PointSaleDetail_SkuId` (`productSkuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='返点财务帐销售明细表';


-- ----------------------------
-- Table structure for StockAdjustDetail
-- ----------------------------
DROP TABLE IF EXISTS `StockAdjustDetail`;
CREATE TABLE `StockAdjustDetail` (
  `refrenceId` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '主键',
  `dealerId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '经销商id',
  `brandId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌商id',
  `brandsId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '品牌id',
  `productId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品id',
  `productSkuId` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '产品skuId',
  `orderNo` bigint(20) DEFAULT NULL COMMENT '采购单号（source=0）',
  `baseStock` int(11) NOT NULL DEFAULT '0' COMMENT 'erp铺货变返点基础库存',
  `sendNum` int(11) NOT NULL DEFAULT '0' COMMENT '发货数量',
  `saleNum` int(11) NOT NULL DEFAULT '0' COMMENT '销售数量',
  `refundNum` int(11) NOT NULL DEFAULT '0' COMMENT '退货数量',
  `source` tinyint(4) NOT NULL COMMENT '库存变更类型(数据来源)：0采购，1销售，2退货，3铺货变返点',
  `createTime` bigint(20) NOT NULL COMMENT '记录创建时间（来源于其他数据）',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标识：0未删除1删除',
  PRIMARY KEY (`refrenceId`),
  KEY `StockAdjust_BrandId` (`brandId`) USING BTREE,
  KEY `StockAdjust_BrandsId` (`brandsId`) USING BTREE,
  KEY `StockAdjust_ProductId` (`productId`) USING BTREE,
  KEY `StockAdjust_DealerId` (`dealerId`) USING BTREE,
  KEY `StockAdjust_SkuId` (`productSkuId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='产品库存调整记录表（该表由调度执行生成）';


-- ----------------------------
--  Table structure for `ProductAdjustDetail`
-- ----------------------------
DROP TABLE IF EXISTS `ProductAdjustDetail`;
CREATE TABLE `ProductAdjustDetail` (
  `refrenceId` varchar(32) NOT NULL,
  `brandId` varchar(32) NOT NULL COMMENT '品牌商编号',
  `brandsId` varchar(32) NOT NULL COMMENT '品牌编号',
  `productId` varchar(32) NOT NULL COMMENT '产品编号',
  `proSkuId` varchar(32) NOT NULL COMMENT 'SKU编号',
  `beforePrice` decimal(10,0) DEFAULT NULL COMMENT '调整前价格',
  `beforePercent` double DEFAULT NULL COMMENT '调整前比例',
  `afterPrice` decimal(10,0) DEFAULT NULL COMMENT '调整后价格',
  `afterPercent` double DEFAULT NULL COMMENT '调整后比例',
  `createTime` bigint(20) DEFAULT NULL COMMENT '调整时间',
  `delFlag` bit(1) DEFAULT NULL COMMENT '数据状态',
  PRIMARY KEY (`refrenceId`),
  KEY `ProductAdjustDetail_BrandId` (`brandId`),
  KEY `ProductAdjustDetail_BrandsId` (`brandsId`),
  KEY `ProductAdjustDetail_ProductId` (`productId`),
  KEY `ProductAdjustDetail_SkuId` (`proSkuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品调价明细表';


-- 20151116
ALTER TABLE `ProductExtInfo`
ADD COLUMN `isPoint`  bit(1) NULL DEFAULT b'0' COMMENT '是否返点' AFTER `isCredit`;

ALTER TABLE `ProductSkuPrice`
ADD COLUMN `pointPrice`  decimal(20,2) NULL COMMENT '返点价格' AFTER `createTime`;

ALTER TABLE `DealerJoin`
ADD COLUMN `isPoint`  bit(1) NULL DEFAULT 0 COMMENT '是否返点' AFTER `joinSource`;

ALTER TABLE `ProductExtInfo`
ADD COLUMN `pointPercent`  decimal(11,2) NULL COMMENT '返点比例' AFTER `isCredit`;

ALTER TABLE `ProductExtInfo`
ADD COLUMN `pointEffTime`  bigint(20) NULL COMMENT '返点生效时间' AFTER `isPoint`;

-- 新增返点调价明细菜单
INSERT INTO `Zttx-Web-New`.`MenuInfo` (`refrenceId`, `upMenuId`, `menuName`, `menuAddr`, `menuType`, `authority`, `menuLevel`, `menuOrder`, `menuStyle`, `canTop`, `isSelect`, `shopMenu`, `menuOpen`, `showflag`, `createTime`, `updateTime`, `delFlag`) 
VALUES ('3E85025D2D154FB99F6B4CC7AEEBA5B9', 'F513FA2D9DE44C50823B556542BF469D', '返点调价明细', '/common/productAdjustDetail?menuId=3E85025D2D154FB99F6B4CC7AEEBA5B9', '\0', '', '4', '10', '', '\0', NULL, NULL, 1, 1, '1448264251009', '1448264268979', '\0');


alter table DealerOrder modify dealerOrderType tinyint(4) NOT NULL DEFAULT '1' COMMENT '订单类型：1、现款订单  2、授信订单 3、返点订单';

ALTER TABLE DealerOrders
ADD COLUMN pointPercent decimal(11,2) NULL COMMENT '返点比例';

ALTER TABLE `ProductAdjustDetail`
ADD COLUMN `effTime`  bigint(20) NULL AFTER `afterPercent`;

alter table BrandPointBalance modify joinForm tinyint(4) DEFAULT '1' COMMENT '加盟方式：1 现款，2受信，3返点';


ALTER TABLE `PointSaleSum`
ADD COLUMN `brandsId`  varchar(32) NOT NULL COMMENT '品牌id' AFTER `brandId`;

ALTER TABLE `PointSaleSum`
ADD COLUMN `payExtId`  varchar(18) NULL DEFAULT NULL COMMENT '支付流水号' AFTER `isPaid`;

SET FOREIGN_KEY_CHECKS = 1;
