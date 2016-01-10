-- ----------------------------
-- 初始支付记录修改时间
-- ----------------------------
ALTER TABLE DealerOrderPay add COLUMN updateTime bigint(20) DEFAULT NULL COMMENT '修改时间';
update DealerOrderPay set updateTime = createTime;
-- ----------------------------
-- 品牌权重数据初始化
-- ----------------------------
CREATE TABLE `BrandesWeightInfo` (
  `refrenceId` char(32) NOT NULL COMMENT '品牌权重编号',
  `brandId` char(32) NOT NULL COMMENT '品牌商编号',
  `brandesId` char(32) NOT NULL COMMENT '品牌编号',
  `weight` int(4) DEFAULT '0' COMMENT '权重值',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌权重信息';

insert into BrandesWeightInfo(refrenceId,brandId,brandesId,weight,createTime,updateTime,delFlag)
select REPLACE(UUID(),'-',''),brandId,refrenceId,1,createTime,createTime,0 from BrandesInfo;

-- ----------------------------
-- 产品权重数据初始化
-- ----------------------------
CREATE TABLE `ProductWeightInfo` (
  `refrenceId` char(32) COLLATE utf8_bin NOT NULL COMMENT '产品id ',
  `brandId` char(32) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌商id ',
  `brandsId` char(32) COLLATE utf8_bin NOT NULL COMMENT '品牌id ',
  `weight` int(11) NOT NULL COMMENT '权重1-100 ',
  `season` smallint(4) NOT NULL COMMENT '季节，1，2，3，4 ',
  `createTime` bigint(20) NOT NULL COMMENT '创建时间 ',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '最后更新时间 ',
  `delFlag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标记，1删除，0未删除，默认0 ',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 初始化产品权重数据
INSERT INTO ProductWeightInfo (refrenceId, brandId, brandsId, weight, season, createTime, updateTime, delFlag) 
SELECT refrenceId, brandId, brandsId, 1, (select QUARTER(from_unixtime(createTime/1000,'%Y-%m-%d'))), createTime, createTime, 0
FROM ProductBaseInfo;

ALTER TABLE `ProductWeightInfo`
ADD INDEX `WeightInfo_brandId` (`brandId`) USING BTREE ,
ADD INDEX `WeightInfo_brandsId` (`brandsId`) USING BTREE ;

ALTER TABLE `ProductCount`
  ADD COLUMN `updateTime` bigint(20) COMMENT '修改时间' AFTER `createTime`;

