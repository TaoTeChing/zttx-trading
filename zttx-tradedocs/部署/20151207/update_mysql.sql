DROP TABLE IF EXISTS `ProductCrmFavorite`;
CREATE TABLE `ProductCrmFavorite` (
  `refrenceId` char(32) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `userId` char(32) COLLATE utf8_bin NOT NULL COMMENT '支撑用户ID',
  `productId` char(32) COLLATE utf8_bin NOT NULL COMMENT '产品ID',
  `createTime` bigint(20) NOT NULL COMMENT '建档时间',
  `updateTime` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `delFlag` bit(1) NOT NULL COMMENT '删除状态',
  PRIMARY KEY (`refrenceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='支撑产品收藏夹';

ALTER TABLE `ProductBaseInfo`
ADD COLUMN `optimization` bit(1) DEFAULT b'0' COMMENT '是否待优化(0：否 1：待优化)';

ALTER TABLE `ProductBaseInfo`
ADD COLUMN `optimizeType` varchar(10) DEFAULT NULL COMMENT '待优化的类型（1：标题，2：产品类目）多个,号分隔';

ALTER TABLE `BrandCount`
ADD COLUMN `optimizeCount` int(11) DEFAULT '0' COMMENT '待优化产品数量';
