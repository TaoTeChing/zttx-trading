-- 数据初始化
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 初始化用户角色
-- ----------------------------
BEGIN;
  UPDATE UserInfo SET roleId = 'D771752C9CEA4B41B65FBB086CA6FBAB' WHERE userType = 0;
  UPDATE UserInfo SET roleId = 'D771752C9CEA4B41B65FBB086CA6FAAC' WHERE userType = 1;
COMMIT;

update ProductExtInfo set recycle=true;

update ProductExtInfo set isCredit=false;
-- ----------------------------
-- 扣点：扣点统一默认设置为0.03
-- ----------------------------
update BrandPointBalance set point = 0.03;

update BrandPointBalance set delFlag = 1 where joinForm = 0;

-- ----------------------------
-- 初始化区域授权产品价格
-- ----------------------------
update BrandesInfo a set a.userAuth="01,02,03,04" where a.userAuth IS NULL OR a.userAuth="";

UPDATE ProductSkuPrice a, ( SELECT max(b.directPrice) directPrice, b.productId FROM ProductSkuPrice b GROUP BY b.productId ) b SET a.samplePrice = b.directPrice WHERE a.productId = b.productId;

UPDATE ProductBaseInfo a, ( SELECT min(directPrice) minDirectPrice, productId FROM ProductSkuPrice GROUP BY productId ) b SET a.directPrice = b.minDirectPrice WHERE a.refrenceId = b.productId;

UPDATE DealerClearing a, ( SELECT b.brandsId, c.refrenceId FROM DealerJoin b, DealerClearing c WHERE ( c.brandsId IS NULL OR c.brandsId = "" ) AND b.dealerId = c.dealerId AND b.brandId = c.brandId GROUP BY c.refrenceId ) t SET a.brandsId = t.brandsId WHERE a.refrenceId = t.refrenceId;

-- ----------------------------
-- 初始支付记录修改时间
-- ----------------------------
update DealerOrderPay set updateTime = createTime;

-- ----------------------------
-- 品牌权重数据初始化
-- ----------------------------
insert into BrandesWeightInfo(refrenceId,brandId,brandesId,weight,createTime,updateTime,delFlag)
select REPLACE(UUID(),'-',''),brandId,refrenceId,0,createTime,createTime,0 from BrandesInfo
