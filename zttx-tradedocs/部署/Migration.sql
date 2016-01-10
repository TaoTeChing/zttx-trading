-- 数据迁移脚本
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO `Zttx-Web-New`.DealerFeed ( refrenceId, dealerId, brandId, brandsId, feedText, createTime, updateTime, replyText, replyTime, replyId, delFlag )
SELECT refrenceId, dealerId, brandId, brandsId, feedText, createTime, createTime, replyText, replyTime, replyId, 0 FROM `ZttxWeb2014DEAL`.DealerFeed;

INSERT INTO `Zttx-Web-New`.DealerForget ( refrenceId, dealerId, oldPwd, newPwd, createTime, updateTime, createIp, delFlag )
SELECT refrenceId, dealerId, oldPwd, newPwd, createTime, createTime, createIp, 0 FROM `ZttxWeb2014DEAL`.DealerForget;

INSERT INTO `Zttx-Web-New`.DealerGroom ( refrenceId, brandId, brandsId, dealerId, userId, createTime, updateTime, delFlag )
SELECT refrenceId, brandId, brandsId, dealerId, userId, createTime, createTime, 0 FROM `ZttxWeb2014DEAL`.DealerGroom;

INSERT INTO `Zttx-Web-New`.DealerImage ( refrenceId, dealerId, photoName, imageName, createTime, updateTime, delFlag )
SELECT refrenceId, dealerId, photoName, imageName, createTime, createTime, delState FROM `ZttxWeb2014DEAL`.DealerImage;

INSERT INTO `Zttx-Web-New`.DealerInfo ( refrenceId, dealerName, dealerAddress, dealerShop, domainName, dealerLogo, headImage, shopNum, legalImgz, legalImgf, monNum, empNum, brandName, foundTime, busImg, otherImg, dealerMark, dealerUser, dealerGender, dealerTel, dealerFax, provinceName, cityName, areaName, areaNo, cardId, comNo, beginTime, endTime, gpsX, gpsY, dealerLevel, checkState, rcvSmsVerify, createTime, updateTime, delFlag )
SELECT refrenceId, dealerName, dealerAddress, dealerShop, domainName, dealerLogo, headImage, shopNum, legalImgz, legalImgf, monNum, empNum, brandName, foundTime, busImg, otherImg, dealerMark, dealerUser, dealerGender, dealerTel, dealerFax, provinceName, cityName, areaName, areaNo, cardId, comNo, beginTime, endTime, gpsX, gpsY, dealerLevel, checkState, rcvSmsVerify, createTime, createTime, 0 FROM `ZttxWeb2014DEAL`.DealerInfo;

INSERT INTO `Zttx-Web-New`.DealerJoin ( refrenceId, dealerId, brandId, brandsId, logoName, domainName, levelId, areaNo, joinSource, joinForm, joinState, creditCurrent, discount, creditAmount, applyTime, joinTime, updateTime, startTime, endTime, endMark, orderTime, orderNum, orderMoney, lastOrder, joinNet, isPaid, creditPaidPercent, paidAmount, paidTime, depositTotalAmount, depositClearingAmount, refundAmount, delFlag )
SELECT a.refrenceId, a.dealerId, a.brandId, a.brandsId, a.logoName, a.domainName, a.levelId, a.areaNo, a.joinSource, CASE b.isFactoryShop  WHEN 1 THEN 1 ELSE 0  END , a.joinState, 0, 1, 0, a.applyTime, a.joinTime, a.updateTime, a.startTime, a.endTime, a.endMark, a.orderTime, a.orderNum, a.orderMoney, a.lastOrder, a.joinNet, a.isPaid, 0, a.paidAmount, a.paidTime,a.depositTotalAmount, a.depositClearingAmount, 0, 0  FROM `ZttxWeb2014DEAL`.DealerJoin a,`ZttxWeb2014DEAL`.DealerInfo b where a.dealerId = b.refrenceId;

INSERT INTO `Zttx-Web-New`.DealerOrder ( refrenceId, orderId, dealerId, brandId, brandsId, productBulk, productWeight, productPrice, productCount, isAdvancePayment, orderMoney, shipCount, receiveCount, adjustPrice, freight, discountPrice, adjustFreight, frePayState, payment, payState, shipName, areaNo, dealerAddrProvince, dealerAddrCity, dealerAddrArea, dealerAddress, postCode, dealerMobile, dealerTel, remark, levelMark, brandRemark, createTime, updateTime, outActTime, orderStatus, refundStatus, serProStatus, complaintState, orderItem, sourceState, sourceId, activitiesNo, minPoint, dealerOrderType, isSampleOrder, pointBalanceAmount, frePayType, clearingStatus, clearingAmount, offSetAmount, noSendGoodsAmount, delFlag )
SELECT refrenceId, orderId, dealerId, brandId, brandsId, productBulk, productWeight, productPrice, productCount, isAdvancePayment, orderMoney, shipCount, receiveCount, adjustPrice, freight, 0, adjustFreight, frePayState, payment, payState, shipName, areaNo, dealerAddrProvince, dealerAddrCity, dealerAddrArea, dealerAddress, postCode, dealerMobile, dealerTel, remark, levelMark, brandRemark, createTime, updateTime, outActTime, orderStatus, refundStatus, serProStatus, complaintState, orderItem, sourceState, sourceId, activitiesNo, minPoint, dealerOrderType, 0, pointBalanceAmount, frePayType, clearingStatus, clearingAmount, offSetAmount, 0, 0 FROM `ZttxWeb2014DEAL`.DealerOrder;

INSERT INTO `Zttx-Web-New`.DealerOrderc ( refrenceId, dealerId, brandId, brandsId, productId, subTime, subNum, joinNum, orderNum, orderTime, delFlag )
SELECT refrenceId, dealerId, brandId, brandsId, productId, subTime, subNum, joinNum, orderNum, orderTime, 0 FROM `ZttxWeb2014DEAL`.DealerOrderc;

INSERT INTO `Zttx-Web-New`.DealerOrders ( refrenceId, orderId, dealerId, lineId, agio, brandId, brandsId, productId, productTitle, productNo, productImage, productSkuId, productSkuCode, productSkuName, createTime, updateTime, productAttrbute, price, adjustPrice, quantity, shipCount, discount, discountPrice, itemState, synchTime, totalAmount, point, pointAmount, delFlag )
SELECT refrenceId, orderId, dealerId, lineId, agio, brandId, brandsId, productId, productTitle, productNo, productImage, productSkuId, productSkuCode, productSkuName, updateTime, updateTime, productAttrbute, price, 0, quantity, shipCount, 0, 0, itemState, synchTime, totalAmount, point, pointAmount, 0 FROM `ZttxWeb2014DEAL`.DealerOrders;

INSERT INTO `Zttx-Web-New`.DealerRefAttacht ( refrenceId, refundId, userId, replyId, userName, domainName, attachtName, createTime, delFlag )
SELECT refrenceId, refundId, userId, replyId, userName, domainName, attachtName, createTime, delState FROM `ZttxWeb2014DEAL`.DealerRefAttacht;

INSERT INTO `Zttx-Web-New`.DealerRefReply ( refrenceId, refundId, userId, userName, replyContent, createTime, delFlag )
SELECT refrenceId, refundId, userId, userName, replyContent, createTime, 0 FROM `ZttxWeb2014DEAL`.DealerRefReply;

INSERT INTO `Zttx-Web-New`.DealerService ( refrenceId, dealerId, serviceName, userId, serviceImage, domainName, serviceTel, jobNum, serviceMobile, userGender, serviceCate, createTime, updateTime, delFlag )
SELECT refrenceId, dealerId, serviceName, userId, serviceImage, domainName, serviceTel, jobNum, serviceMobile, userGender, serviceCate, createTime, createTime, delState FROM `ZttxWeb2014DEAL`.DealerService;

INSERT INTO `Zttx-Web-New`.DealerShoper ( refrenceId, dealerId, brandId, brandsId, productId, productNum, productPrice, createTime, updateTime, sourceId, productType, discountPrice, delFlag )
SELECT refrenceId, dealerId, brandId, brandsId, productId, productNum, productPrice, createTime, createTime, sourceId, productType, 0, 0 FROM `ZttxWeb2014DEAL`.DealerShoper;

INSERT INTO `Zttx-Web-New`.DealerShopers ( refrenceId, shoperId, productId, productSkuId, purchaseNum, createTime, updateTime, productType, productSkuPrice, delFlag )
SELECT refrenceId, shoperId, productId, productSkuId, purchaseNum, createTime, createTime, productType, productSkuPrice, delState FROM `ZttxWeb2014DEAL`.DealerShopers;

INSERT INTO `Zttx-Web-New`.DealerShopInfo ( refrenceId, dealerId, userCode, shopId, createTime, updateTime, shopState, delFlag )
SELECT refrenceId, dealerId, userCode, shopId, createTime, createTime, shopState, 0 FROM `ZttxWeb2014DEAL`.DealerShopInfo;

INSERT INTO `Zttx-Web-New`.DealerTermina ( refrenceId, dealerId, brandId, brandsId, logoName, domainName, terminaTime, userId, delFlag )
SELECT refrenceId, dealerId, brandId, brandsId, logoName, domainName, terminaTime, userId, 0 FROM `ZttxWeb2014DEAL`.DealerTermina;

INSERT INTO `Zttx-Web-New`.DealerVisited ( refrenceId, dealerId, brandId, brandsId, domainName, logoName, areaNo, provinceName, cityName, areaName, viewNum, viewTime, delFlag )
SELECT refrenceId, dealerId, brandId, brandsId, domainName, logoName, areaNo, provinceName, cityName, areaName, viewNum, viewTime, 0 FROM `ZttxWeb2014DEAL`.DealerVisited;


-- 度量单位
INSERT INTO `Zttx-Web-New`.`WebUnit` ( `refrenceId`, `unitName`, `sortId`, `createTime`, `updateTime`, `delFlag` )
  SELECT unitNo, unIt, sortId, NOW(), NOW(), 0 from `ZttxWeb2014DEAL`.WebUnit;

-- 上传图片附件类型大小 实体对象
INSERT INTO `Zttx-Web-New`.`UploadAttSize` ( `refrenceId`, `attCateId`, `cateKey`, `height`, `width`, `createTime`, `updateTime`, `delFlag` )
  SELECT refrenceId,attCateId,cateKey,height,width,createTime,createTime,delState from `ZttxWeb2014DEAL`.UploadAttSize;

-- 上传图片附件类别
INSERT INTO `Zttx-Web-New`.`UploadAttCate` ( `refrenceId`, `cateKey`, `attName`, `createTime`, `updateTime`, `delFlag` )
  SELECT refrenceId,cateKey,attName,createTime,createTime,delState from `ZttxWeb2014DEAL`.UploadAttCate;

-- 用户信息
INSERT INTO `Zttx-Web-New`.`UserInfo` ( `refrenceId`, `userMobile`, `mobileVerify`, `userMail`, `mailVerify`, `userPwd`, `userSalt`, `roleId`, `userState`, `parentId`, `userName`, `areaNo`, `registerTime`, `registerIp`, `userType`, `loginIpStr`, `registIpStr`, `userSort`, `payUserId`, `updateTime`, `delFlag` )
  SELECT refrenceId, userMobile, mobileVerify, userMail, mailVerify, userPwd, userSalt, NULL, userState, parentId, userName, areaNo, registerTime, registerIp, isDealer, loginIp, registerIp, userSort, payUserId, registerTime, 0 FROM `ZttxWeb2014DEAL`.BrandUserm;

-- 品牌商信息
INSERT INTO `Zttx-Web-New`.`BrandInfo` ( `refrenceId`, `comName`, `comType`, `dealType`, `domainName`, `brandPhoto`, `brandImage`, `userPhoto`, `userImage`, `comNum`, `idNum`, `legalName`, `regMoney`, `areaNo`, `provinceName`, `cityName`, `areaName`, `comAddress`, `comWeb`, `comMark`, `createTime`, `updateTime`, `checkState`, `emploeeNum`, `moneyNum`, `barCodeNum`, `delFlag` )
  SELECT brandId, comName, comType, dealType, domainName, brandPhoto, brandImage, userPhoto, userImage, comNum, idNum, legalName, regMoney, areaNo, provinceName, cityName, areaName, comAddress, comWeb, comMark, createTime, createTime, checkState, emploeeNum, moneyNum, barCodeNum, false from `ZttxWeb2014DEAL`.BrandInfo;

-- 品牌信息
INSERT INTO `Zttx-Web-New`.`BrandesInfo` ( `refrenceId`,`brandId`, `brandsName`,`brandType`, `logoDomin`, `brandLogo`, `proLogo`, `brandHold`, `holdName`, `brandSubMark`, `brandMark`, `brandState`, `beginTime`, `endTime`, `ensureMoney`, `createTime`, `updateTime`, `createIp`, `showed`, `recommendImage`, `meetCount`, `factoryStore`, `deposit`, `barCodeNum`, `userAuth`, `delFlag` )
  SELECT refrenceId, brandId, brandName, brandType, logoDomin, brandLogo, proLogo, brandHold, holdName, brandSubMark, brandMark, brandState, beginTime, endTime, ensureMoney, createTime, createTime, createIp, showed, recommendImage, meetCount, factoryStore, deposit, barCodeNum,userAuth,delState FROM `ZttxWeb2014DEAL`.BrandesInfo;

-- 品牌商消息
INSERT INTO `Zttx-Web-New`.BrandMessage( refrenceId, brandId, dealerId, orderId, msgCate, msgTitle, msgText, areaNo, userName, userMobile, userContact, userGender, createTime, 							 updateTime, refuseReply, replyId, replyTime, replyText, userId, 		 delFlag )
  SELECT refrenceId, brandId, dealerId, orderId, msgCate, msgTitle, msgText, areaNo, userName, userMobile, userContact, userGender, createTime, createTime as updateTime, refuseReply, replyId, replyTime, replyText, userId,0 as delFlag FROM `ZttxWeb2014DEAL`.BrandMessage;

-- 经销商消息阅读
INSERT INTO `Zttx-Web-New`.DealerRead( refrenceId, dealerId, msgId, readTime, delFlag )
  SELECT refrenceId, dealerId, msgId, readTime, delState AS delFlag FROM `ZttxWeb2014DEAL`.DealerRead;

-- 经销商消息管理
INSERT INTO `Zttx-Web-New`.DealerMessage(refrenceId, dealerId, brandId, orderId, msgCate, msgTitle, msgText, createTime, 							 updateTime, refuseReply, replyId, replyTime, replyText, userId, 		  delFlag)
  SELECT refrenceId, dealerId, brandId, orderId, msgCate, msgTitle, msgText, createTime, createTime AS updateTime, refuseReply, replyId, replyTime, replyText, userId, 0 AS delFlag FROM `ZttxWeb2014DEAL`.DealerMessage;

-- 数据字典
INSERT INTO `Zttx-Web-New`.DataDict ( refrenceId, dictName, dictCode, createTime,updateTime, dictDesc,delFlag )
  SELECT refrenceId, dictName, dictCode, createTime, createTime AS updateTime, dictDesc ,0 AS delFlag FROM `ZttxWeb2014DEAL`.DataDict;

-- 数据字典值
INSERT INTO `Zttx-Web-New`.DataDictValue(refrenceId, dictid, dictCode, createTime, 							 updateTime, dictValueName, dictValue, dictOrder, remark, 						delFlag)
	SELECT refrenceId, dictid, dictCode, createTime, createTime AS updateTime, dictValueName, dictValue, dictOrder, remark, delState AS delFlag FROM `ZttxWeb2014DEAL`.DataDictValue;

-- 品牌经营品类信息
INSERT INTO `Zttx-Web-New`.DealDic(refrenceId, dealNo, dealName, dealIcon, parentId, dealLevel, dealOrder, productNum, 						 delFlag, productWeight, createTime, updateTime)
  SELECT refrenceId, dealNo, dealName, dealIcon, parentId, dealLevel, dealOrder, productNum, delState AS delFlag, productWeight, unix_timestamp(NOW()) AS createTime, unix_timestamp(NOW()) as updateTime FROM `ZttxWeb2014DEAL`.DealDic;

--  帮助类别信息
INSERT INTO `Zttx-Web-New`.`HelpCate` ( `refrenceId`, `cateName`, `parentId`, `cateNo`, `helpLevel`, `showType`, `showFlag`, `orderId`, `createTime`, `delFlag` )
	SELECT HelpCate.refrenceId, HelpCate.helpName, HelpCate.parentId, HelpCate.helpNo, HelpCate.helpLevel, HelpCate.showType,  HelpCate.showFlag, HelpCate.orderId, HelpCate.createTime, HelpCate.delState FROM `ZttxWeb2014DEAL`.HelpCate;

--   帮助信息脚本
INSERT INTO `Zttx-Web-New`.`HelpInfo` ( `refrenceId`, `helpNo`, `helpCateId`, `title`, `subMark`, `htmlText`, `isRecommand`, `isHot`, `isFaq`, `createIp`, `createTime`, `updateTime`, `delFlag` )
	SELECT HelpInfo.refrenceId, HelpInfo.helpNo, HelpInfo.helpCateId, HelpInfo.title, HelpInfo.subMark, HelpInfo.htmlText, HelpInfo.isRecommand, HelpInfo.isHot, HelpInfo.isFaq, HelpInfo.createIp, HelpInfo.createTime, HelpInfo.updateTime, HelpInfo.delState FROM `ZttxWeb2014DEAL`.HelpInfo;

--  规则类别信息
INSERT INTO `Zttx-Web-New`.`RulesCate` ( `refrenceId`, `cateName`, `cateMark`, `cateType`, `parentId`, `cateText`, `cateOrder`, `cateLevel`, `articleNum`, `createTime`, `delFlag` )
	SELECT `refrenceId`, `cateName`, `cateMark`, `cateType`, `parentId`, `cateText`, `cateOrder`, `cateLevel`, `articleNum`, `createTime`, delState FROM `ZttxWeb2014DEAL`.RulesCate;

--   规则类容信息
INSERT INTO `Zttx-Web-New`.`RulesInfo` ( `refrenceId`, `cateId`, `articleTitle`, `articleText`, `domainName`, `articleImage`, `createTime`, `updateTime`, `viewNum`, `delFlag` )
	SELECT `refrenceId`, `cateId`, `articleTitle`, `articleText`, `domainName`, `articleImage`, `createTime`, `updateTime`, `viewNum`, `delState` FROM `ZttxWeb2014DEAL`.RulesInfo;

--  规则日志信息
INSERT INTO `Zttx-Web-New`.`RulesInfoLog` ( `refrenceId`, `rulesId`, `articleTitle`, `articleText`, `domainName`, `articleImage`, `createTime`, `viewNum` )
	SELECT `refrenceId`, `rulesId`, `articleTitle`, `articleText`, `domainName`, `articleImage`, `createTime`, `viewNum` FROM `ZttxWeb2014DEAL`.RulesInfoLog;

--  文章类别
INSERT INTO `Zttx-Web-New`.`ArticleCate` ( `refrenceId`, `cateName`, `domainName`, `cateIcon`, `cateMark`, `parentId`, `cateOrder`, `cateLevel`, `articleNum`, `moduleFile`, `htmlFile`, `createTime`, `delFlag` )
	SELECT `refrenceId`, `cateName`, `domainName`, `cateIcon`, `cateMark`, `parentId`, `cateOrder`, `cateLevel`, `articleNum`, `moduleFile`, `htmlFile`, `createTime`, `delState` FROM `ZttxWeb2014DEAL`.ArticleCate;

--  文章信息
INSERT INTO `Zttx-Web-New`.`ArticleInfo` ( `refrenceId`, `cateId`, `articleTitle`, `articleText`, `domainName`, `articleImage`, `articleSource`, `articleAuthor`, `viewNum`, `shareNum`, `isTop`, `isHead`, `isComment`, `isHot`, `commentNum`, `collectNum`, `createTime`, `delFlag` )
	SELECT `refrenceId`, `cateId`, `articleTitle`, `articleText`, `domainName`, `articleImage`, `articleSource`, `articleAuthor`, `viewNum`, `shareNum`, `isTop`, `isHead`, `isComment`, `isHot`, `commentNum`, `collectNum`, `createTime`, `delState` FROM `ZttxWeb2014DEAL`.ArticleInfo;

-- 品牌商主营品类
INSERT INTO `Zttx-Web-New`.BrandCatelog ( refrenceId, brandId, dealNo, createTime, updateTime, delFlag )
	SELECT refrenceId, brandId, dealNo, createTime, createTime, delState AS delFlag FROM `ZttxWeb2014DEAL`.BrandCatelog;

-- 客服在线信息表
INSERT INTO `Zttx-Web-New`.UserOnlineService ( refrenceId, userType, onlineDateType, onlineBeginTime, onlineEndTime, createTime, userId, showed, delFlag )
	SELECT refrenceId, userType, onlineDateType, onlineBeginTime, onlineEndTime, createTime, userId, showed, 0 AS delFlag FROM `ZttxWeb2014DEAL`.UserOnlineService;

-- 客服在线信息详情表
INSERT INTO `Zttx-Web-New`.UserOnlineServiceDetail ( refrenceId, userOnlineId, qq, `name`, createTime, delFlag )
	SELECT refrenceId, userOnlineId, qq, `name`, createTime, 0 AS delFlag FROM `ZttxWeb2014DEAL`.UserOnlineServiceDetail;

-- 品牌商证书信息
INSERT INTO `Zttx-Web-New`.BrandCard ( refrenceId, brandId, certName, domainName, certPhoto, certImage, certMark, createTime, updateTime, createIp, delFlag )
	SELECT refrenceId, brandId, certName, domainName, certPhoto, certImage, certMark, createTime, createTime, createIp, 0 AS delFlag FROM `ZttxWeb2014DEAL`.BrandCard;

-- 品牌商支付密码
INSERT INTO `Zttx-Web-New`.BrandPayword ( refrenceId, dealerTel, payWord, paySalt, createTime, createIp, flag, delFlag )
  SELECT brandId as refrenceId , dealerTel, payWord, paySalt, createTime, createIp, flag, 0 as delFlag FROM `ZttxWeb2014DEAL`.BrandPayword;

-- 支付密码修改历史
INSERT INTO `Zttx-Web-New`.BrandPaylog ( refrenceId, brandId, oldWord, newWord, oldSalt, newSalt, createTime, createType, createIp, delFlag )
  SELECT refrenceId, brandId, oldWord, newWord, oldSalt, newSalt, createTime, createType, createIp, 0 as delFlag FROM `ZttxWeb2014DEAL`.BrandPaylog;

-- 全国区域信息
INSERT INTO `Zttx-Web-New`.Regional ( refrenceId, areaNo, areaName, areaParent, areaLevel, areaOrder, createTime, updateTime, delFlag )
	SELECT refrenceId, areaNo, areaName, areaParent, areaLevel, areaOrder, createTime, createTime, delState AS delFlag FROM `ZttxWeb2014DEAL`.Regional;

-- 品牌商品牌仓库信息
INSERT INTO `Zttx-Web-New`.BrandStore ( refrenceId, brandId, brandsId, storeName, createTime, updateTime, delFlag )
	SELECT refrenceId, brandId, brandsId, storeName, createTime, createTime, delState as delFlag FROM `ZttxWeb2014DEAL`.BrandStore;

-- 品牌商发货地址
INSERT INTO `Zttx-Web-New`.BrandAddress ( refrenceId, brandId, brandsId, storeId, userName, areaNo, street, userTel, userMobile, addressMark, sendDefault, returnDefault, zipCode, createTime, updateTime, delFlag )
	SELECT refrenceId, brandId, brandsId, storeId, userName, areaNo, street, userTel, userMobile, addressMark, sendDefault, returnDefault, zipCode, createTime, createTime, delState as delFlag FROM `ZttxWeb2014DEAL`.BrandAddress;

-- 经销商退款信息
INSERT INTO `Zttx-Web-New`.DealerRefund ( refrenceId, 	orderId, 	refundId, 	orderNumber, 	dealerId, 	brandId, 	received, 	needRefund, 	totalAmount, 	refundAmount, 	comPayment, 	refundReason, 	refundMark, 	brandRecAddr, 	returnTime, 	logisticsName, 	transNum, 	refundState, 	remark, 	brandMark, 	createTime, 	updateTime, 	nextActTime, 	updateNum, 	cusJoin, 	joinTime, 	applyTime, 	serProStatus, 	serProResult, 	factoryStore, 	reachAmount, 	reachStatus, 	delFlag)
  SELECT refrenceId, 	orderId, 	refundId, 	orderNumber, 	dealerId, 	brandId, 	received, 	needRefund, 	totalAmount, 	refundAmount, 	comPayment, 	refundReason, 	refundMark, 	brandRecAddr, 	returnTime, 	logisticsName, 	transNum, 	refundState, 	remark, 	brandMark, 	updateTime AS createTime, 	updateTime updateTime, 	nextActTime, 	updateNum, 	cusJoin, 	joinTime, 	applyTime, 	serProStatus, 	serProResult, 	factoryStore, 	reachAmount, 	reachStatus, 	0 AS delFlag FROM `ZttxWeb2014DEAL`.DealerRefund;

-- 品牌商物流公司
INSERT INTO `Zttx-Web-New`.BrandLogistics ( refrenceId, brandId, logisticName, logisticType, createTime, updateTime, delFlag )
  SELECT refrenceId, brandId, logisticName, logisticType, createTime, createTime as updateTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.BrandLogistics;

-- 订单操作历史记录
INSERT INTO `Zttx-Web-New`.DealerOrderAction ( refrenceId, orderId, userId, userName, actCode, actName, actContent, createTime, updateTime, delFlag )
  SELECT refrenceId, orderId, userId, userName, actCode, actName, actContent, createTime, createTime as updateTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.DealerOrderAction;

-- 订单支付记录表
Insert INTO `Zttx-Web-New`.DealerOrderPay ( refrenceId, orderId, orderNo, payId, payAmount, createTime, state, orderType, pointBalance, unpayAmount, payTime, delFlag )
  SELECT refrenceId, orderId, orderNo, payId, payAmount, createTime, state, orderType, pointBalance, unpayAmount, payTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.DealerOrderPay;

-- 终端商 收藏产品
INSERT INTO `Zttx-Web-New`.`DealerFavorite` (`refrenceId`,`dealerId`,`brandId`,`brandsId`,`productId`,`tagId`,`collectTime`,`delFlag`) 
  SELECT `refrenceId`,`dealerId`,`brandId`,`brandsId`,`productId`,`tagId`,`collectTime` ,FALSE FROM `ZttxWeb2014DEAL`.`DealerFavorite` ;


-- 短信模板
INSERT INTO `Zttx-Web-New`.SmsTemplate ( refrenceId, smsKey, templateName, content, place, remark, createTime, updateTime, delFlag )
  SELECT refrenceId, smsKey, templateName, content, place, remark, createTime, createTime as updateTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.SmsTemplate;

-- 品牌商图片分类信息
INSERT INTO `Zttx-Web-New`.BrandImgcate ( refrenceId, brandId, cateName, parentId, cateOrder, cateDefault, createTime, updateTime, delFlag )
  SELECT refrenceId, brandId, cateName, parentId, cateOrder, cateDefault, createTime, createTime AS updateTime , delState FROM `ZttxWeb2014DEAL`.BrandImgcate;

-- 品牌域名
INSERT INTO `Zttx-Web-New`.`BrandsDomain` (  `refrenceId`, `brandId`, `brandsId`,  `Domain`,  `createTime`,  `updateTime`,  `delFlag`) 
  SELECT   REPLACE(UUID(),'-',''), `brandId`, `brandsId`,  `Domain`,  UNIX_TIMESTAMP()*1000,  UNIX_TIMESTAMP()*1000,  FALSE FROM  `ZttxWeb2014DEAL`.`BrandsDomain` ;

-- 门店展示
INSERT INTO `Zttx-Web-New`.`BrandNetwork` (`refrenceId`,`brandId`,`brandsId`,`dealerId`,`dealerName`,`levelId`,`userName`,`telphone`,`mobile`,`provinceName`,`cityName`,`areaName`,`areaNo`,`address`,`showFlag`,`wholePercent`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`dealerId`,`dealerName`,`levelId`,`userName`,`telphone`,`mobile`,`provinceName`,`cityName`,`areaName`,`areaNo`,`address`,`showFlag`,`wholePercent`,`createTime`, `createTime`,`delState` FROM`ZttxWeb2014DEAL`.`BrandNetwork`;
 
INSERT INTO `Zttx-Web-New`.`BrandNetimg` (`refrenceId`,`networkId`,`brandId`,`brandsId`,`domainName`,`photoName`,`imageName`,`mainFlag`,`uploadTime`,`uploadIp`,`delFlag`)
  SELECT `refrenceId`,`networkId`,`brandId`,`brandsId`,`domainName`,`photoName`,`imageName`,`mainFlag`,`uploadTime`,`uploadIp`,FALSE FROM `ZttxWeb2014DEAL`.`BrandNetimg` ;

-- 品牌视频
INSERT INTO `Zttx-Web-New`.`BrandsVideo` (`refrenceId`,`brandId`,`brandsId`,`videoName`,`position`,`width`,`height`,`createTime`,`uploadTime`,`uploadIp`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`videoName`,`position`,`width`,`height`, UNIX_TIMESTAMP()*1000,`uploadTime`,`uploadIp`,`delState` FROM `ZttxWeb2014DEAL`.`BrandsVideo`;
  
-- 品牌资料
INSERT INTO `Zttx-Web-New`.`BrandDoccate` (`refrenceId`,`brandId`,`brandsId`,`cateName`,`createTime`,`orderId`,`delFlag`) 
  SELECT `refrenceId`,`brandId`,`brandsId`,`cateName`,`createTime`,`orderId`,`delState` FROM `ZttxWeb2014DEAL`.`BrandDoccate`;

INSERT INTO `Zttx-Web-New`.`BrandDocopen` (`refrenceId`,`brandId`,`brandsId`,`docId`,`dealerId`,`createTime`,`updateTime`,`delFlag`) 
  SELECT `refrenceId`,`brandId`,`brandsId`,`docId`,`dealerId`,`createTime`,`createTime`,FALSE FROM `ZttxWeb2014DEAL`.`BrandDocopen`;

INSERT INTO `Zttx-Web-New`.`BrandDocument` (`refrenceId`,`brandId`,`brandsId`,`cateId`,`docName`,`domainName`,`docoFile`,`docnFile`,`webAddress`,`docPass`,`docMark`,`docOpen`,`createTime`,`updateTime`,`createIp`,`downNum`,`delFlag`) 
  SELECT `refrenceId`,`brandId`,`brandsId`,`cateId`,`docName`,`domainName`,`docoFile`,`docnFile`,`webAddress`,`docPass`,`docMark`,`docOpen`,`createTime`,`createTime`,`createIp`,`downNum` ,FALSE FROM `ZttxWeb2014DEAL`.`BrandDocument`;

-- 品牌新闻
INSERT INTO `Zttx-Web-New`.`BrandNews` (`refrenceId`,`brandId`,`brandsId`,`newsTitle`,`cateId`,`imageDomin`,`imageUrl`,`newsSummary`,`newsContent`,`cronTime`,`hitNum`,`interestNum`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`newsTitle`,`cateId`,`imageDomin`,`imageUrl`,`newsSummary`,`newsContent`,`cronTime`,`hitNum`,`interestNum`,`createTime`,`createTime`,`delState` FROM `ZttxWeb2014DEAL`.`BrandNews`;

-- 品牌视觉
INSERT INTO `Zttx-Web-New`.`BrandVisual` (`refrenceId`,`brandId`,`brandsId`,`domainName`,`vedioDoc`,`vedioFile`,`threeDoc`,`threeFile`,`brandMark`,`createTime`,`updateTime`,`createIp`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`domainName`,`vedioDoc`,`vedioFile`,`threeDoc`,`threeFile`,`brandMark`,`createTime`, `createTime`,`createIp`,FALSE FROM `ZttxWeb2014DEAL`.`BrandVisual`;

-- 品牌招募书
INSERT INTO `Zttx-Web-New`.`BrandRecruit` (`refrenceId`,`brandId`,`recruitTitle`,`dealBrand`,`dealExper`,`dealShop`,`dealYear`,`recruitState`,`recruitText`,`createTime`,`updateTime`,`userId`,`delFlag`) 
  SELECT `brandsId`,`brandId`,`recruitTitle`,`dealBrand`,`dealExper`,`dealShop`,`dealYear`,`recruitState`,`recruitText`,`createTime`,`updateTime` ,`userId`,FALSE FROM `ZttxWeb2014DEAL`.`BrandRecruit`;

-- 品牌商展厅信息 
INSERT INTO `Zttx-Web-New`.`BrandRoom` (`refrenceId`,`brandId`,`roomName`,`domainName`,`logoPhoto`,`logoImage`,`brandMark`,`createTime`,`updateTime`,`userId`,`delFlag`) 
  SELECT `brandsId`,`brandId`,`roomName`,`domainName`,`logoPhoto`,`logoImage`,`brandMark`,`createTime`,`updateTime`,`userId`,FALSE FROM `ZttxWeb2014DEAL`.`BrandRoom`;
 
-- 品牌装修
INSERT INTO `Zttx-Web-New`.`DecorateConfig` (`refrenceId`,`brandId`,`brandsId`,`title`,`showTitle`,`configType`,`tagId`,`showFlag`,`showType`,`showText`,`showOrder`,`createTime`,`updateTime`,`delFlag`) 
  SELECT `refrenceId`,`brandId`,`brandsId`,`title`,`showTitle`,`configType`,`tagId`,`showFlag`,`showType`,`showText`,`showOrder`,`createTime`,`updateTime`,`delState` FROM `ZttxWeb2014DEAL`.`DecorateConfig` ;

INSERT INTO `Zttx-Web-New`.`DecorateConfigLog` (`refrenceId`,`brandId`,`brandsId`,`title`,`showTitle`,`tagId`,`configType`,`showFlag`,`showType`,`showText`,`showOrder`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`title`,`showTitle`,`tagId`,`configType`,`showFlag`,`showType`,`showText`,`showOrder`,`createTime`,`updateTime`,`delState` from `ZttxWeb2014DEAL`.`DecorateConfigLog`;

INSERT INTO `Zttx-Web-New`.`DecorateGlobal` (`refrenceId`,`brandId`,`brandsId`,`fontCcolor`,`urlFontColor`,`urlChangeColor`,`backColor`,`showBackColor`,`backUrl`,`showBackUrl`,`backRepeat`,`backPosition`,`skinName`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrencceId`,`brandId`,`brandsId`,`fontCcolor`,`urlFontColor`,`urlChangeColor`,`backColor`,`showBackColor`,`backUrl`,`showBackUrl`,`backRepeat`,`backPosition`,`skinName`,`createTime`,`updateTime`,false FROM `ZttxWeb2014DEAL`.`DecorateGlobal` ;

INSERT INTO `Zttx-Web-New`.`DecorateGlobalLog` (`refrenceId`,`brandId`,`brandsId`,`fontCcolor`,`urlFontColor`,`urlChangeColor`,`backColor`,`showBackColor`,`backUrl`,`showBackUrl`,`backRepeat`,`backPosition`,`skinName`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrencceId`,`brandId`,`brandsId`,`fontCcolor`,`urlFontColor`,`urlChangeColor`,`backColor`,`showBackColor`,`backUrl`,`showBackUrl`,`backRepeat`,`backPosition`,`skinName`,`createTime`,`updateTime`,delState FROM `ZttxWeb2014DEAL`.`DecorateGlobalLog` ;

INSERT INTO `Zttx-Web-New`.`DecorateHeader` (`refrenceId`,`brandId`,`brandsId`,`showCate`,`comName`,`showName`,`domainName`,`logoName`,`showLogo`,`mainDeal`,`nameFont`,`nameSize`,`nameColor`,`dealFont`,`dealSize`,`dealColor`,`outBackUrl`,`inBackUrl`,`navDefaultColor`,`navDefaultUrl`,`navChangeColor`,`navChangeUrl`,`navSelectColor`,`navDefaultFont`,`navChangeFont`,`headerText`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrencceId`,`brandId`,`brandsId`,`showCate`,`comName`,`showName`,`domainName`,`logoName`,`showLogo`,`mainDeal`,`nameFont`,`nameSize`,`nameColor`,`dealFont`,`dealSize`,`dealColor`,`outBackUrl`,`inBackUrl`,`navDefaultColor`,`navDefaultUrl`,`navChangeColor`,`navChangeUrl`,`navSelectColor`,`navDefaultFont`,`navChangeFont`,`headerText`,`createTime`,`updateTime`,false FROM `ZttxWeb2014DEAL`.`DecorateHeader`;

INSERT INTO `Zttx-Web-New`.`DecorateHeaderLog` (`refrenceId`,`brandId`,`brandsId`,`showCate`,`comName`,`showName`,`domainName`,`logoName`,`showLogo`,`mainDeal`,`nameFont`,`nameSize`,`nameColor`,`dealFont`,`dealSize`,`dealColor`,`outBackUrl`,`inbackUrl`,`navBackUrl`,`navDefaultColor`,`navDefaultUrl`,`navChangeColor`,`navChangeUrl`,`navSelectColor`,`navDefaultFont`,`navChangeFont`,`headerText`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrencceId`,`brandId`,`brandsId`,`showCate`,`comName`,`showName`,`domainName`,`logoName`,`showLogo`,`mainDeal`,`nameFont`,`nameSize`,`nameColor`,`dealFont`,`dealSize`,`dealColor`,`outBackUrl`,`inbackUrl`,`navBackUrl`,`navDefaultColor`,`navDefaultUrl`,`navChangeColor`,`navChangeUrl`,`navSelectColor`,`navDefaultFont`,`navChangeFont`,`headerText`,`createTime`,`updateTime`,delState FROM `ZttxWeb2014DEAL`.`DecorateHeaderLog`;

INSERT INTO `Zttx-Web-New`.`DecorateImage` (`refrenceId`,`rollId`,`brandId`,`altName`,`domainName`,`imageUrl`,`hrefText`,`showOrder`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`,`rollId`,`brandId`,`altName`,`domainName`,`imageUrl`,`hrefText`,`showOrder`,`createTime`,`updateTime` ,delState FROM `ZttxWeb2014DEAL`.`DecorateImage` ;

INSERT INTO `Zttx-Web-New`.`DecorateMenu` (`refrenceId`,`brandId`,`brandsId`,`menuValue`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`menuValue`,`createTime`,`updateTime`,`delState` FROM `ZttxWeb2014DEAL`.`DecorateMenu` ;

INSERT INTO `Zttx-Web-New`.`DecorateMenuLog` (`refrenceId`,`brandId`,`brandsId`,`menuValue`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`menuValue`,`createTime`,`updateTime`,false FROM `ZttxWeb2014DEAL`.`DecorateMenuLog` ;

INSERT INTO `Zttx-Web-New`.`DecorateRolling` (`refrenceId`,`configId`,`brandId`,`maxCount`,`width`,`height`,`rollMode`,`isAuto`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrencceId`,`configId`,`brandId`,`maxCount`,`width`,`height`,`rollMode`,`isAuto`,`createTime`,`updateTime` , `delState` FROM `ZttxWeb2014DEAL`.`DecorateRolling` ;

INSERT INTO `Zttx-Web-New`.`DecorateSysMenu` (`refrenceId`,`menuName`,`menuUrl`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`,`menuName`,`menuUrl`,`createTime`,`updateTime` , `delState` FROM `ZttxWeb2014DEAL`.`DecorateSysMenu` ;

INSERT INTO `Zttx-Web-New`.`BrandAlbum` (`refrenceId`,`brandId`,`brandsId`,`domainName`,`photoName`,`imageName`,`createTime`,`updateTime`,`createIp`,`delFlag`)
  SELECT `refrenceId`,`brandId`,`brandsId`,`domainName`,`photoName`,`imageName`,`createTime`, `createTime`,`createIp` , FALSE FROM `ZttxWeb2014DEAL`.`BrandAlbum` ;

-- 网站服务项目
INSERT INTO `Zttx-Web-New`.WebServiceItems ( refrenceId, serviceName, servicePhoto, commentNum, servicerCate, buyNum, viewNum, chargType, price, minBuyNum, servicePrice, comId, subMark, serviceMark, serviceType, createTime, updateTime, delFlag )
  SELECT refrenceId, serviceName, servicePhoto, commentNum, servicerCate, buyNum, viewNum, chargType, price, minBuyNum, servicePrice, comId, subMark, serviceMark, serviceType, createTime, createTime AS updateTime, delState AS delFlag FROM `ZttxWeb2014DEAL`.WebServiceItems;

-- 网站服务商
INSERT INTO `Zttx-Web-New`.WebServiceCom(refrenceId, comName, comPhoto, comCert, comTel, comEmail, comMobile, subMark, createTime, updateTime, delFlag)
  SELECT refrenceId, comName, comPhoto, comCert, comTel, comEmail, comMobile, subMark, createTime, createTime AS updateTime, delState AS delFlag  FROM `ZttxWeb2014DEAL`.WebServiceCom;

-- 首页感兴趣品牌展示
INSERT INTO `Zttx-Web-New`.WebBrandsShow ( refrenceId, brandsId, showType, orderId, image, createTime, updateTime, delFlag )
SELECT refrenceId, brandsId, showType, orderId, image, createTime, createTime AS updateTime, delState AS delFlag FROM `ZttxWeb2014DEAL`.WebBrandsShow;

-- 终端商店铺信息
INSERT INTO `Zttx-Web-New`.DealerShopEnv ( refrenceId, dealerId, shopName, environment, scale, ShopMeters, shape, trade, model, brand, salesVolume, GPSX, GPSY, brandSituation, intention, openTime, viewCount, areaNo, cityName, provinceName, areaName, createTime, updateTime, briefIntroduction, showed, delFlag )
  SELECT refrenceId, dealerId, shopName, environment, scale, ShopMeters, shape, trade, model, brand, salesVolume, GPSX, GPSY, brandSituation, intention, openTime, viewCount, areaNo, cityName, provinceName, areaName, createTime, createTime AS updateTime, briefIntroduction, showed, delState AS delFlag FROM `ZttxWeb2014DEAL`.DealerShopEnv;

-- 经销店铺 临时图片
INSERT INTO `Zttx-Web-New`.DealerShopEnvImgTemp ( refrenceId, shopEnvId, imagePath, createTime, updateTime, delFlag )
  SELECT refrenceId, shopEnvId, imagePath, NULL, NULL, 0 AS delFlag FROM `ZttxWeb2014DEAL`.DealerShopEnvImgTemp;

-- 经销店铺 临时信息
INSERT INTO `Zttx-Web-New`.DealerShopEnvTemp ( refrenceId, createTime, updateTime, status, detail, auditUser, auditTime, auditIp, delFlag )
  SELECT refrenceId, createTime, createTime AS updateTime, STATUS, detail, auditUser, auditTime, auditIp, 0 AS delFlag FROM `ZttxWeb2014DEAL`.DealerShopEnvTemp;

-- 经销商购买的服务
INSERT INTO `Zttx-Web-New`.DealerBuyService(refrenceId, dealerId, dealerName, serviceId, servicerCate, chargType, buyTime, beginTime, endTime, buyMoney, createTime, updateTime, delFlag)
  SELECT refrenceId, dealerId, dealerName, serviceId, servicerCate, chargType, buyTime, beginTime, endTime, buyMoney, buyTime as createTime,updateTime ,0 AS delFlag FROM `ZttxWeb2014DEAL`.DealerBuyService;

-- 经销商购买的服务记录
INSERT INTO `Zttx-Web-New`.DealerBuySerLog(refrenceId, dealerId, dealerName, serialNumber, serviceId, servicerCate, buyTime, buyNum, buyMoney, beginTime, endTime, buyState, chargType, addressId, createTime, updateTime, delFlag)
  SELECT refrenceId, dealerId, dealerName, serialNumber, serviceId, servicerCate, buyTime, buyNum, buyMoney, beginTime, endTime, buyState, chargType,  addressId ,buyTime AS createTime,updateTime,0 as delFlag FROM `ZttxWeb2014DEAL`.DealerBuySerLog;

-- 品牌商购买的服务记录
INSERT INTO `Zttx-Web-New`.BrandBuySerLog ( refrenceId, brandId, brandName, serialNumber, serviceId, servicerCate, buyTime, buyNum, buyMoney, beginTime, endTime, buyState, chargType, updateTime, delFlag )
SELECT refrenceId, brandId, brandName, serialNumber, serviceId, servicerCate, buyTime, buyNum, buyMoney, beginTime, endTime, buyState, chargType, updateTime, 0 AS delFlag FROM `ZttxWeb2014DEAL`.BrandBuySerLog;

-- 品牌商购买的服务
INSERT INTO `Zttx-Web-New`.BrandBuyService ( refrenceId, brandId, brandName, serviceId, servicerCate, chargType, buyTime, beginTime, endTime, buyMoney, updateTime, delFlag )
SELECT refrenceId, brandId, brandName, serviceId, servicerCate, chargType, buyTime, beginTime, endTime, buyMoney, updateTime, 0 AS delFlag FROM `ZttxWeb2014DEAL`.BrandBuyService;

-- 邮件验证
INSERT INTO `Zttx-Web-New`.EmailValidate ( refrenceId, userId, userCate, emailAddr, validTime, createIp, useTime, createTime, updateTime, delFlag )
SELECT refrenceId, userId, userCate, emailAddr, validTime, createIp, useTime, createTime, createTime AS updateTime, useState AS delFlag FROM `ZttxWeb2014DEAL`.EmailValidate;

-- 短信发送历史信息
INSERT INTO `Zttx-Web-New`.MessageHistory ( refrenceId, userMobile, message, createTime )
SELECT refrenceId, userMobile, message, createTime FROM `ZttxWeb2014DEAL`.MessageHistory;

-- 地区编码与常规描述的转换表
INSERT INTO `Zttx-Web-New`.RegionalCode ( refrenceId, areaNos, areaName, createTime, updateTime, delFlag )
SELECT refrenceId, areaNos, areaName, createTime, createTime AS updateTime, 0 AS delFlag FROM `ZttxWeb2014DEAL`.RegionalCode;

-- 经销商/品牌商申请更改手机认证
INSERT INTO `Zttx-Web-New`.SecurityCert ( refrenceId, userIdId, userName, userCate, oldMobile, userMobile, userPhoto, certPhoto, applyType, createTime, createIp, actState, userId, actionDate, actionIp )
SELECT refrenceId, userIdId, userName, userCate, oldMobile, userMobile, userPhoto, certPhoto, applyType, createTime, createIp, actState, userId, actionDate, actionIp FROM `ZttxWeb2014DEAL`.SecurityCert;

-- 意见和建议
INSERT INTO `Zttx-Web-New`.Suggestions ( refrenceId, email, message, createTime, updateTime, delFlag )
SELECT refrenceId, email, message, createTime, createTime AS updateTime, delState AS delFlag FROM `ZttxWeb2014DEAL`.Suggestions;

-- 用户操作日志
INSERT INTO `Zttx-Web-New`.UserOperationLog ( refrenceId, objectId, userId, userName, type, EVENT, eventTime, loginType, loginIP )
SELECT refrenceId, objectId, userId, userName, type, EVENT, eventTime, loginType, loginIP FROM `ZttxWeb2014DEAL`.UserOperationLog;

-- 页面模版定义日志
INSERT INTO `Zttx-Web-New`.WebDefTmpLog ( refrenceId, modelKey, htmlText, createTime, delFlag )
SELECT refrenceId, modelKey, htmlText, createTime, delState AS delFlag FROM `ZttxWeb2014DEAL`.WebDefTmpLog;

-- 商品属性表
INSERT INTO `Zttx-Web-New`.`ProductAttrValue` ( `refrenceId`, `productId`, `attributeId`, `attributeItemId`, `extAttributeValue`, `productSkuId`, `custAttribute`, `isSkuAttr`, `sortOrder` )
  SELECT `refrenceId`, `productId`, `attributeId`, `attributeItemId`, `extAttributeValue`, `productSkuId`, `custAttribute`, `isSkuAttr`, `sortOrder` FROM `ZttxWeb2014DEAL`.`ProductAttrValue`;

-- 商品基本属性表
INSERT INTO `Zttx-Web-New`.`ProductBaseInfo` ( `refrenceId`, `dealNo`, `brandId`, `brandsId`, `brandsName`, `dealerId`, `source`, `unitNo`, `unit`, `productCate`, `domainName`, `productImage`, `productKeyword`, `productNo`, `productTitle`, `productAlias`, `metaDesc`, `mobileMetaDesc`, `productPrice`, `directPrice`, `productCarry`, `downTime`, `stateSet`, `delFlag`, `isResultant`, `isWholesale`, `isMixWholesale`, `mixStartNum`, `mixStartPay`, `createTime`, `updateTime`, `custCateName`, `barCodeNum`, `skuBarCodeSeq` )
  SELECT `refrenceId`, `dealNo`, `brandId`, `brandsId`, `brandsName`, `dealerId`, `source`, `unitNo`, `unit`, `productCate`, `domainName`, `productImage`, `productKeyword`, `productNo`, `productTitle`, `productAlias`, `metaDesc`, `mobileMetaDesc`, `productPrice`, `directPrice`, `productCarry`, `downTime`, `stateSet`, `delFlag`, `isResultant`, `isWholesale`, `isMixWholesale`, `mixStartNum`, `mixStartPay`, `createTime`, `updateTime`, `custCateName`, `barCodeNum`, `skuBarCodeSeq` FROM `ZttxWeb2014DEAL`.`ProductBaseInfo`;

-- 产品扩展表
INSERT INTO `Zttx-Web-New`.`ProductExtInfo` ( `refrenceId`, `productBulk`, `productWeight`, `tempId`, `orderName`, `orderStart`, `orderEnd`, `startNum`, `orderNum`, `outStart`, `outEnd`, `orderSelect`, `orderMoney`, `productStore`, `minStore`, `productMark`, `patchMark`, `productBear`, `beginTime`, `productGroom`, `productCent`, `stopState`, `topTime`, `similarPrice`, `activitySort`, `isCredit`, `isSample`, `freTemplateId`, `barCode`, `isShow`, `companyCode`,isDiscount,recycle)
  SELECT `refrenceId`, `productBulk`, `productWeight`, `tempId`, `orderName`, `orderStart`, `orderEnd`, `startNum`, `orderNum`, `outStart`, `outEnd`, `orderSelect`, `orderMoney`, `productStore`, `minStore`, `productMark`, '', `productBear`, `beginTime`, `productGroom`, `productCent`, `stopState`, `topTime`, `similarPrice`, `activitySort`, '0', `isSample`, `freTemplateId`, `barCode`, `isShow`, `companyCode`,1,1 FROM `ZttxWeb2014DEAL`.`ProductExtInfo`;

-- 产品图片
INSERT INTO `Zttx-Web-New`.`ProductImage` ( `refrenceId`, `productId`, `domainName`, `photoName`, `imageName`, `imageMark`, `createTime`, `orderId`, `isMain`, `createIp`, `attributeItemId` )
  SELECT `refrenceId`, `productId`, `domainName`, `photoName`, `imageName`, `imageMark`, `createTime`, `orderId`, `isMain`, `createIp`, `attributeItemId` FROM `ZttxWeb2014DEAL`.`ProductImage`;

-- 产品sku
INSERT INTO `Zttx-Web-New`.`ProductSku` ( `refrenceId`, `productId`, `source`, `productSkuNo`, `extProductSkuNo`, `productSkuName`, `productSkuAlias`, `quantity`, `saleUnit`, `attrItemIds`, `brandsId`, `createTime`, `updateTime`, `delFlag`, `attributeValueId1`, `attributeValueId2` )
  SELECT `refrenceId`, `productId`, `source`, `productSkuNo`, `extProductSkuNo`, `productSkuName`, `productSkuAlias`, `quantity`, `saleUnit`, `attrItemIds`, `brandsId`, `createTime`, `updateTime`, `delFlag`, `attributeValueId1`, `attributeValueId2` FROM `ZttxWeb2014DEAL`.`ProductSku`;

-- sku条码
INSERT INTO `Zttx-Web-New`.`ProductSkuBarcode` ( `refrenceId`, `productSkuId`, `productId`, `barCodeType`, `barCodeValue`, `delFlag` )
  SELECT `refrenceId`, `productSkuId`, `productId`, `barCodeType`, `barCodeValue`, 0 FROM `ZttxWeb2014DEAL`.`ProductSkuBarcode`;

-- sku价格
INSERT INTO `Zttx-Web-New`.`ProductSkuPrice` ( `refrenceId`, `productSkuId`, `productId`, `agentPrice`, `samplePrice`, `directPrice`, `factoryStorePrice`, `enablePriceRange`, `creditPrice`, `skuPrice`, `createTime`, `costPrice`, `updateTime` )
  SELECT `refrenceId`, `productSkuId`, `productId`, `agentPrice`, `samplePrice`, `directPrice`, `factoryStorePrice`, `enablePriceRange`, NULL, `skuPrice`, `createTime`, `costPrice`, `createTime` FROM `ZttxWeb2014DEAL`.`ProductSkuPrice`;

-- skuRfid
INSERT INTO `Zttx-Web-New`.`ProductSkuRfid` ( `refrenceId`, `productSkuId`, `createTime`, `updateTime`, `state` )
  SELECT `refrenceId`, `productSkuId`, `createTime`, `updateTime`, `state` FROM `ZttxWeb2014DEAL`.`ProductSkuRfid`;

-- 属性分类
INSERT INTO `Zttx-Web-New`.`CateAttribute` ( `refrenceId`, `attributeName`, `attributeDesc`, `attributeNo`, `operateCate`, `sortOrder`, `state`, `delFlag`, `isImgAttr` )
  SELECT `refrenceId`, `attributeName`, `attributeDesc`, `attributeNo`, `operateCate`, `sortOrder`, `state`, `delFlag`, `isImgAttr` FROM `ZttxWeb2014DEAL`.`CateAttribute`;

-- 属性项
INSERT INTO `Zttx-Web-New`.`CateAttributeItem` ( `refrenceId`, `attributeId`, `attributeItem`, `attributeIcon`, `attributeOrder`, `state`, `delFlag` )
  SELECT `refrenceId`, `attributeId`, `attributeItem`, `attributeIcon`, `attributeOrder`, `state`, `delFlag` FROM `ZttxWeb2014DEAL`.`CateAttributeItem`;

-- 属性属性项关联表
INSERT INTO `Zttx-Web-New`.`CateAttributeItemRel` ( `refrenceId`, `attributeId`, `attributeItemId`, `cateNo`, `createTime` )
  SELECT `refrenceId`, `attributeId`, `attributeItemId`, `cateNo`, `createTime` FROM `ZttxWeb2014DEAL`.`CateAttributeItemRel`;

-- 属性类目关联表
INSERT INTO `Zttx-Web-New`.`CateAttributeRel` ( `refrenceId`, `attributeId`, `cateNo`, `createTime`, `isSkuAttr`, `enableSearch`, `enableAlias`, `isMultiSelect`, `valueType`, `sortOrder` )
  SELECT `refrenceId`, `attributeId`, `cateNo`, `createTime`, `isSkuAttr`, `enableSearch`, `enableAlias`, `isMultiSelect`, `valueType`, `sortOrder` FROM `ZttxWeb2014DEAL`.`CateAttributeRel`;

-- 自定义分类
INSERT INTO `Zttx-Web-New`.`ProductCatalog` ( `refrenceId`, `brandId`, `brandsId`, `cateName`, `cateOrder`, `parentId`, `cateLevel`, `domainName`, `cateIcon`, `productNum`, `createTime`, `updateTime`, `delFlag` )
  SELECT `refrenceId`, `brandId`, `brandsId`, `cateName`, `cateOrder`, `parentId`, `cateLevel`, `domainName`, `cateIcon`, `productNum`, `createTime`, `createTime`, `delState` FROM `ZttxWeb2014DEAL`.`ProductCatalog`;

-- 分类产品关联关系
INSERT INTO `Zttx-Web-New`.`ProductCate` ( `refrenceId`, `cateId`, `productId`, `createTime`, `updateTime`, `delFlag` )
  SELECT `refrenceId`, `cateId`, `productId`, `createTime`, `createTime`, FALSE FROM `ZttxWeb2014DEAL`.`ProductCate`;

-- 品牌商品牌地区列表
INSERT INTO `Zttx-Web-New`.`BrandesInfoRegional` (`refrenceId`, `brandId`, `brandesId`, `areaNo`,`createTime`,`updateTime`,`delFlag`)
  SELECT `refrenceId`, `brandId`, `brandesId`, `areaNo`,  `createTime`,`createTime`, 0 FROM `ZttxWeb2014DEAL`.`BrandesInfoRegional`;
  
-- 品牌授权证书
INSERT INTO `Zttx-Web-New`.`BrandLicening` (`refrenceId`,  `brandId`, `brandesId`, `domainName`, `fileName`, `imageName`, `createTime`,  `updateTime`, `createIp`, `delFlag`)
  SELECT `refrenceId`,  `brandId`, `brandesId`, `domainName`, `fileName`, `imageName`, `createTime`,  `createTime`, `createIp`, `delState` FROM `ZttxWeb2014DEAL`.`BrandLicening`;

-- 品牌形象照片
INSERT INTO `Zttx-Web-New`.`BrandPhoto` (`refrenceId`, `brandId`, `brandesId`, `domainName`, `photoName`, `imageName`, `createTime`, `updateTime`, `createIP`, `delFlag`)
  SELECT `refrenceId`, `brandId`, `brandesId`, `domainName`, `photoName`, `imageName`, `createTime`, `createTime`, `createIP`, `delState` FROM `ZttxWeb2014DEAL`.`BrandPhoto`;

-- 品牌形象照片
INSERT INTO `Zttx-Web-New`.`BrandImage` (`refrenceId`, `brandId`, `cateId`, `domainName`, `photoName`, `imageName`, `imageMark`, `imageSize`, `createTime`, `updateTime`, `createIp`, `delFlag`)
  SELECT `refrenceId`, `brandId`, `cateId`, `domainName`, `photoName`, `imageName`, `imageMark`, `imageSize`, `createTime`, `createTime`, `createIp`, `delState` FROM `ZttxWeb2014DEAL`.`BrandImage`;

-- 品牌商审核日志
INSERT INTO `Zttx-Web-New`.`BrandAudit` (`refrenceId`, `brandId`, `brandName`, `userId`, `checkTime`, `checkState`, `checkMark`, `delFlag`)
  SELECT `refrenceId`, `brandId`, `brandName`, `userId`, `checkTime`, `checkState`, `checkMark`, `delState` FROM `ZttxWeb2014DEAL`.`BrandAudit`;

-- 品牌证书
INSERT INTO `Zttx-Web-New`.`BrandCert` (`refrenceId`, `brandId`, `brandesId`, `domainName`, `fileName`, `imageName`, `createTime`, `updateTime`, `createIp`, `delFlag`)
  SELECT `refrenceId`, `brandId`, `brandesId`, `domainName`, `fileName`, `imageName`, `createTime`, `createTime`, `createIp`, `delState` FROM `ZttxWeb2014DEAL`.`BrandCert`;

-- 品牌商计数信息
INSERT INTO `Zttx-Web-New`.`BrandCount` (`refrenceId`, `cooperCount`, `applyCount`, `inviteCount`, `waitPayCount`, `preOrderCount`, `creditCount`, `waitSendCount`, `waitConfirmCount`, `refundCount`, `publishedCount`, `waitPublishCount`, `tightInventoryCount`, `shortageCount`, `prePublishedCount`, `viewDealerCount`, `brandsCount`, `viewDealerTotal`, `createTime`, `updateTime`, `delFlag`)
  SELECT `brandId`, `cooperCount`, `applyCount`, `inviteCount`, `waitPayCount`, `preOrderCount`, `creditCount`, `waitSendCount`, `waitConfirmCount`, `refundCount`, `publishedCount`, `waitPublishCount`, `tightInventoryCount`, `shortageCount`, `prePublishedCount`, `viewDealerCount`, `brandsCount`, `viewDealerTotal`, unix_timestamp()*1000, unix_timestamp()*1000, 0 FROM `ZttxWeb2014DEAL`.`BrandCount`;

-- 品牌商审核日志
INSERT INTO `Zttx-Web-New`.`BrandsAudit` (`refrenceId`, `brandsId`, `brandsNames`, `userId`, `checkTime`, `checkState`, `checkMark`, `delFlag`)
  SELECT `refrenceId`, `brandsId`, `brandsNames`, `userId`, `checkTime`, `checkState`, `checkMark`, `delState` FROM `ZttxWeb2014DEAL`.`BrandsAudit`;

-- 品牌商更新信息表CRM
INSERT INTO `Zttx-Web-New`.`BrandCrm` (`refrenceId`, `json`, `jsonType`, `createTime`, `brandState`)
  SELECT `refrenceId`, `json`, `jsonType`, `createTime`, `brandState` FROM `ZttxWeb2014DEAL`.`BrandCrm`;

-- 转账记录表
INSERT INTO `Zttx-Web-New`.`DepositBack` (`refrenceId`, `exId`, `billId`, `brandId`, `brandName`, `brandsId`, `brandsName`, `dealerId`, `dealerName`, `paidAmount`, `backAmount`, `depositBackTime`, `status`, `dealerjoinId`, `type`, `operateUserId`, `operateUserName`, `operateTime`)
  SELECT `refrenceId`, `exId`, `billId`, `brandId`, `brandName`, `brandsId`, `brandsName`, `dealerId`, `dealerName`, `paidAmount`, `backAmount`, `depositBackTime`, `status`, `dealerjoinId`, `type`, `operateUserId`, `operateUserName`, `operateTime` FROM `ZttxWeb2014DEAL`.`DepositBack`;

-- 扣点表 
INSERT INTO `Zttx-Web-New`.`BrandPointBalance` (`refrenceId`, `brandsId`, `point`, `createTime`, `updateTime`, `joinForm`, `delFlag`)
  SELECT REPLACE(UUID(),'-',''), a.refrenceId, 0.03, unix_timestamp()*1000, unix_timestamp()*1000, 1, 0 FROM `ZttxWeb2014DEAL`.`BrandesInfo` a, `ZttxWeb2014DEAL`.`BrandInfo` b WHERE a.delState = 0 and a.brandId = b.brandId and b.checkState = 1 and a.brandState > 0;

INSERT INTO `Zttx-Web-New`.`BrandPointBalance` (`refrenceId`, `brandsId`, `point`, `createTime`, `updateTime`, `joinForm`, `delFlag`)
  SELECT REPLACE(UUID(),'-',''), a.refrenceId, 0.03, unix_timestamp()*1000, unix_timestamp()*1000, 2, 0 FROM `ZttxWeb2014DEAL`.`BrandesInfo` a, `ZttxWeb2014DEAL`.`BrandInfo` b WHERE a.delState = 0 and a.brandId = b.brandId and b.checkState = 1 and a.brandState > 0;

-- 产品统计
INSERT INTO `Zttx-Web-New`.`ProductCount` ( `refrenceId`, `brandId`, `brandsId`, `viewNum`, `collectNum`, `saleNum`, `createTime` )
 SELECT `productId`, `brandId`, `brandsId`, `viewNum`, `collectNum`, `saleNum`, NOW() FROM `ZttxWeb2014DEAL`.`ProductCount`;

-- 产品修改
INSERT INTO `Zttx-Web-New`.`ProductEdit` ( `refrenceId`, `state`, `updateTime`, `createTime` )
 SELECT `productId`, `state`, `updateTime`, `createTime` FROM `ZttxWeb2014DEAL`.`ProductEdit`;

-- 产品修改审核日志
INSERT INTO `Zttx-Web-New`.`ProductEditAuditLog` ( `refrenceId`, `productId`, `operateId`, `content`, `createTime` )
 SELECT `refrenceId`, `productId`, `operateId`, `content`, `createTime` FROM `ZttxWeb2014DEAL`.`ProductEditAuditLog`;

-- 产品修改详情
INSERT INTO `Zttx-Web-New`.`ProductEditDetail` ( `refrenceId`, `productId`, `changeType`, `oldValue`, `newValue`, `state`, `checkResult`, `applyTime`, `vid`, `attributeIcon`, `createTime`, `updateTime` )
 SELECT `refrenceId`, `productId`, `changeType`, `oldValue`, `newValue`, `state`, `checkResult`, `applyTime`, `vid`, `attributeIcon`, `createTime`, `updateTime` FROM `ZttxWeb2014DEAL`.`ProductEditDetail`;

-- 产品比价表
INSERT INTO `Zttx-Web-New`.`ProductParity` ( `refrenceId`, `productId`, `parityId`, `price`, `url`, `createTime`, `updateTime`, `isShow`, `delFlag`, `keyWord` )
 SELECT `refrenceId`, `productId`, `parityId`, `price`, `url`, `createTime`, NOW(), `isShow`, `isDelete`, `keyWord` FROM `ZttxWeb2014DEAL`.`ProductParity`;

-- 终端商购买产品拿样日志
INSERT INTO `Zttx-Web-New`.`ProductSampleLog` ( `refrenceId`, `brandId`, `productId`, `dealerId`, `createTime`, `productCount`, `orderId` )
 SELECT `refrenceId`, `brandId`, `productId`, `dealerId`, `createTime`, `productCount`, `orderId` FROM `ZttxWeb2014DEAL`.`ProductSampleLog`;

-- sku价格范围
INSERT INTO `Zttx-Web-New`.`ProductSkuPriceRange` ( `refrenceId`, `productSkuId`, `rangeStart`, `rangeEnd`, `price`, `createTime`, `updateTime` )
 SELECT `refrenceId`, `productSkuId`, `rangeStart`, `rangeEnd`, `price`, `createTime`, NOW() FROM `ZttxWeb2014DEAL`.`ProductSkuPriceRange`;

-- 商品浏览历史记录
INSERT INTO `Zttx-Web-New`.`ProductViewLog` ( `refrenceId`, `productId`, `productTitle`, `userId`, `userName`, `productImage`, `productPrice`, `userCate`, `viewNum`, `createTime`, `delFlag` )
 SELECT `refrenceId`, `productId`, `productTitle`, `userId`, `userName`, `productImage`, `productPrice`, `userCate`, `viewNum`, `createTime`, FALSE FROM `ZttxWeb2014DEAL`.`ProductViewLog`;

-- 经销商地址信息
INSERT INTO `Zttx-Web-New`.DealerAddr ( refrenceId, dealerId, dealerName, dealerAddr, dealerAddress, provinceName, cityName, areaName, postCode, dealerMobile, dealerTel, dealerDefault, createTime, updateTime, delFlag )
SELECT refrenceId, dealerId, dealerName, dealerAddr, dealerAddress, provinceName, cityName, areaName, postCode, dealerMobile, dealerTel, dealerDefault, createTime, createTime as updateTime, delState as delFlag FROM `ZttxWeb2014DEAL`.DealerAddr;

-- 经销商优化建议
INSERT INTO `Zttx-Web-New`.DealerAdvice ( refrenceId, dealerId, adviceText, createTime, updateTime, delFlag )
SELECT refrenceId, dealerId, adviceText, createTime, createTime as updateTime,delState as delFlag FROM `ZttxWeb2014DEAL`.DealerAdvice;

-- 经销商加盟申请
INSERT INTO `Zttx-Web-New`.DealerApply ( refrenceId, dealerId, brandId, brandsId, areaNo, areaName, brandName, brandsName, applyUser, applyText, applyTime, auditState, undoTime, auditTime, auditMark, delFlag )
SELECT refrenceId, dealerId, brandId, brandsId, areaNo, areaName, brandName, brandsName, applyUser, applyText, applyTime, auditState, undoTime, auditTime, auditMark, 0 as delFlag FROM `ZttxWeb2014DEAL`.DealerApply;

-- 经销商审核日志
INSERT INTO `Zttx-Web-New`.DealerAudit ( refrenceId, userId, dealerId, dealerName, checkTime, checkState, checkMark, delFlag )
SELECT refrenceId, userId, dealerId, dealerName, checkTime, checkState, checkMark, delState as delFlag FROM `ZttxWeb2014DEAL`.DealerAudit;

-- 经销商经营品类
INSERT INTO `Zttx-Web-New`.DealerClass ( refrenceId, dealerId, dealNo, createTime, updateTime, createIp, delFlag )
SELECT refrenceId, dealerId, dealNo, createTime, createTime as updateTime, createIp, delState as delFlag FROM `ZttxWeb2014DEAL`.DealerClass;

-- 工厂店品牌信息结算表
INSERT INTO `Zttx-Web-New`.DealerClearing ( refrenceId, brandId, dealerId, brandsId, clearingStatus, clearingTime, salesAmount, clearingAmount, clearingVolume, createTime, updateTime, delFlag )
SELECT refrenceId, brandId, dealerId, "" as brandsId, clearingStatus, clearingTime, salesAmount, clearingAmount, clearingVolume, createTime, createTime as updateTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.DealerClearing;

-- 经销商品牌收藏夹
INSERT INTO `Zttx-Web-New`.DealerCollect ( refrenceId, dealerId, brandId, brandsId, areaNo, areaName, collectTime, delFlag )
SELECT refrenceId, dealerId, brandId, brandsId, areaNo, areaName, collectTime, delState as delFlag FROM `ZttxWeb2014DEAL`.DealerCollect;

-- 经销商投诉信息
INSERT INTO `Zttx-Web-New`.DealerComplaint ( refrenceId, orderId, complaintNumberId, orderNumber, dealerId, brandId, brandsId, complaintCause, complaintName, description, comState, interposeTime, brandDesc, complaintResult, createtime, updateTime, delFlag  )
SELECT refrenceId, orderId, complaintNumberId, orderNumber, dealerId, brandId, brandsId, complaintCause, complaintName, description, comState, interposeTime, brandDesc, complaintResult, createtime, createtime as updateTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.DealerComplaint;

-- 经销商计数信息
INSERT INTO `Zttx-Web-New`.DealerCount ( dealerId, joinCount, applyCount, inviteCount, balanceCount, waitConfirmCount, refundCount, sysMessageCount, warningCount, factoryJoinCount, factoryHasPaid, factoryNonPaid, createtime, updateTime, delFlag )
SELECT dealerId, joinCount, applyCount, inviteCount, balanceCount, waitConfirmCount, refundCount, sysMessageCount, warningCount, factoryJoinCount, factoryHasPaid, factoryNonPaid, UNIX_TIMESTAMP()*1000 as createtime, UNIX_TIMESTAMP()*1000 as updateTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.DealerCount;

-- 经销商自定义导航
INSERT INTO `Zttx-Web-New`.DealerDefMenu ( refrenceId, dealerId, menuCode, createTime, updateTime, delFlag )
SELECT refrenceId, dealerId, menuCode, createTime, createTime as updateTime, 0 as delFlag FROM `ZttxWeb2014DEAL`.DealerDefMenu;

-- 押金支付记录
INSERT INTO `Zttx-Web-New`.DealerDeposit ( refrenceId, dealerId, brandId, paidAmount, paidTime, delFlag )
SELECT refrenceId, dealerId, brandId, paidAmount, paidTime, 0 AS delFlag FROM `ZttxWeb2014DEAL`.DealerDeposit;

-- 忘记登录账户
INSERT INTO `Zttx-Web-New`.FindAccount ( refrenceId, uesrType, realName, certNo, certType, userMobile, certPhoto, userEmail, createTime, userId, checkTime, checkState, checkMark )
SELECT refrenceId, uesrType, realName, certNo, certType, userMobile, certPhoto, userEmail, createTime, userId, checkTime, checkState, checkMark FROM `ZttxWeb2014DEAL`.FindAccount;

-- 订单金额修改记录
INSERT INTO `Zttx-Web-New`.OrderChangeLog ( refrenceId, orderId, account, content, createIp, createTime )
SELECT refrenceId, orderId, account, content, createIp, createTime FROM `ZttxWeb2014DEAL`.OrderChangeLog;

-- 订单流水号
INSERT INTO `Zttx-Web-New`.OrderNumber( 	refrenceId,createTime,delFlag )
SELECT orderId AS refrenceId, 	createTime, 	0 AS delFlag FROM 	`ZttxWeb2014DEAL`.OrderNumber;

-- 经销商订单支付历史记录
INSERT INTO `Zttx-Web-New`.OrderPayRecord ( refrenceId, orderId, dealerId, dealerName, recharegeId, payAmount, createTime )
SELECT refrenceId, orderId, dealerId, dealerName, recharegeId, payAmount, createTime FROM `ZttxWeb2014DEAL`.OrderPayRecord;

-- 经销商订单发货记录
INSERT INTO `Zttx-Web-New`.OrderShipRecord ( refrenceId, orderId, brandId, brandName, shipCount, logisticName, shipNumber, createTime, sourceState, errNumber )
SELECT refrenceId, orderId, brandId, brandName, shipCount, logisticName, shipNumber, createTime, sourceState, errNumber FROM `ZttxWeb2014DEAL`.OrderShipRecord;

-- 退款流水号
INSERT INTO `Zttx-Web-New`.RefundNumber ( refundId, createTime ) SELECT refundId, createTime FROM `ZttxWeb2014DEAL`.RefundNumber;


-- 品牌商加盟关系
INSERT INTO `Zttx-Web-New`.BrandInvite ( refrenceId,opratorCata,brandId,dealerId,dealerName,brandName,brandsId,brandsName,domainName,brandsLogo,areaNo,provinceName,cityName,areaName,createTime,updateTime,inviteTime,inviteText,inviteState,applyState,undoTime,auditTime,auditMark,readState,sourceType,delFlag)
SELECT refrenceId,opratorCata,brandId,dealerId,dealerName,brandName,brandsId,brandsName,domainName,brandsLogo,areaNo,provinceName,cityName,areaName,updateTime,updateTime,inviteTime,inviteText,inviteState,applyState,undoTime,auditTime,auditMark,readState,sourceType,0 FROM `ZttxWeb2014DEAL`.BrandInvite;

-- 运费模板定义表
INSERT INTO `Zttx-Web-New`.BrandFreightTemplate ( refrenceId,brandId,name,areaNo,isDefault,isRecommend,createTime,updateTime,delFlag)
SELECT refrenceId,brandId,name,areaNo,isDefault,isRecommend,createTime,createTime,0 FROM `ZttxWeb2014DEAL`.BrandFreightTemplate;

-- 运费模板设置表
INSERT INTO `Zttx-Web-New`.BrandFreightSettings ( refrenceId,templateId,carryType,carryTypeName,createTime,updateTime,delFlag)
SELECT refrenceId,templateId,carryType,carryTypeName,createTime,createTime,0 FROM `ZttxWeb2014DEAL`.BrandFreightSettings;

-- 运费模板区域表
INSERT INTO `Zttx-Web-New`.BrandFreightRegion ( refrenceId,templateId,detailId,areaNo,createTime,areaName,updateTime,delFlag)
SELECT refrenceId,templateId,detailId,areaNo,createTime,areaName,createTime,0 FROM `ZttxWeb2014DEAL`.BrandFreightRegion;

-- 运费模板详情表
INSERT INTO `Zttx-Web-New`.BrandFreightDetail (refrenceId,templateId,settingsId,carryType,firstWeight,firstPrice,extendWeight,extendPrice,isDefault,createTime,updateTime,delFlag)
SELECT refrenceId,templateId,settingsId,carryType,firstWeight,firstPrice,extendWeight,extendPrice,isDefault,createTime,createTime,0 FROM `ZttxWeb2014DEAL`.BrandFreightDetail;

-- 品牌商收藏夹
INSERT INTO `Zttx-Web-New`.BrandFavorite (refrenceId,brandId,dealerId,collectTime,delFlag)
SELECT refrenceId,brandId,dealerId,collectTime,0 FROM `ZttxWeb2014DEAL`.BrandFavorite;

-- 品牌商自定义导航 
INSERT INTO `Zttx-Web-New`.`BrandDefMenu` ( `refrenceId`, `brandId`, `menuCode`, `createTime`, `updateTime`, `delFlag` )  
 SELECT  `refrenceId`, `brandId`, `menuCode`, `createTime`,  `createTime`,  FALSE FROM `ZttxWeb2014DEAL`.`BrandDefMenu` ; 

-- 品牌商资金冻结记录
INSERT INTO `Zttx-Web-New`.`BrandFrozen` ( `refrenceId`, `brandId`, `drawId`, `frozenType`, `amount`, `createTime`, `frozenState`, `remark`, `flag`, `delFlag` )
 SELECT  `refrenceId`, `brandId`, `drawId`, `frozenType`, `amount`, `createTime`, `frozenState`, `remark`, `flag` , FALSE FROM `ZttxWeb2014DEAL`.`BrandFrozen` ;  

-- 经销商等级
INSERT INTO `Zttx-Web-New`.`BrandLevel` ( `refrenceId`, `brandId`, `brandsId`, `levelName`, `levelMark`, `createTime`, `updateTime`, `delFlag` )
 SELECT  `refrenceId`, `brandId`, `brandsId`, `levelName`, `levelMark`, `createTime`,  `createTime`, `delState`  FROM `ZttxWeb2014DEAL`.`BrandLevel` ;

INSERT INTO `Zttx-Web-New`.BrandNewscate (refrenceId,cateName,cateMark,createTime,updateTime,delFlag)
SELECT refrenceId,cateName,cateMark,createTime,createTime,delState FROM `ZttxWeb2014DEAL`.BrandNewscate;

INSERT INTO `Zttx-Web-New`.BrandPointBalance (refrenceId,brandsId,point,createTime,updateTime,joinForm,delFlag)
SELECT refrenceId,brandsId,point,createTime,createTime,0,delState FROM `ZttxWeb2014DEAL`.BrandPointBalance;

INSERT INTO `Zttx-Web-New`.BrandPointBalanceLog (refrenceId,brandsId,content,createTime,updateTime,delFlag)
SELECT refrenceId,brandsId,content,createTime,createTime,0 FROM `ZttxWeb2014DEAL`.BrandPointBalanceLog;

INSERT INTO `Zttx-Web-New`.BrandRead (refrenceId,brandId,msgId,readTime,delFlag)
SELECT refrenceId,brandId,msgId,readTime,delState FROM `ZttxWeb2014DEAL`.BrandRead;

INSERT INTO `Zttx-Web-New`.DealerFavbrand (refrenceId,dealerId,brandId,collectTime,delFlag)
SELECT refrenceId,dealerId,brandId,collectTime,0 FROM `ZttxWeb2014DEAL`.DealerFavbrand;

INSERT INTO `Zttx-Web-New`.DealerFavbrands (refrenceId,dealerId,brandsId,collectTime,delFlag)
SELECT refrenceId,dealerId,brandsId,collectTime,0 FROM `ZttxWeb2014DEAL`.DealerFavbrands;

INSERT INTO `Zttx-Web-New`.DealerFavtag (refrenceId,dealerId,tagName,productNum,createTime,updateTime,delFlag)
SELECT refrenceId,dealerId,tagName,productNum,createTime,createTime,delState FROM `ZttxWeb2014DEAL`.DealerFavtag;

INSERT INTO `Zttx-Web-New`.BrandTemplate (refrenceId,brandId,brandsId,templateName,orderStart,orderEnd,startNum,orderNum,outStart,outEnd,orderSelect,orderMoney,createTime,updateTime,delFlag)
SELECT refrenceId,brandId,brandsId,templateName,orderStart,orderEnd,startNum,orderNum,outStart,outEnd,orderSelect,orderMoney,createTime,createTime,delState FROM `ZttxWeb2014DEAL`.BrandTemplate;

INSERT INTO `Zttx-Web-New`.BrandViewContact (refrenceId,brandId,dealerId,viewTime,viewType,delFlag)
SELECT refrenceId,brandId,dealerId,viewTime,viewType,0 FROM `ZttxWeb2014DEAL`.BrandViewContact;

INSERT INTO `Zttx-Web-New`.BrandVisited (refrenceId,brandId,dealerId,brandName,dealerName,areaNo,provinceName,cityName,areaName,viewNum,viewTime,delFlag)
SELECT refrenceId,brandId,dealerId,brandName,dealerName,areaNo,provinceName,cityName,areaName,viewNum,viewTime,0 FROM `ZttxWeb2014DEAL`.BrandVisited;

INSERT INTO `Zttx-Web-New`.FreightCompany (refrenceId,companyName,freightCode,createTime,updateTime,delFlag)
SELECT refrenceId,companyName,freightCode,createTime,createTime,0 FROM `ZttxWeb2014DEAL`.FreightCompany;

INSERT INTO `Zttx-Web-New`.LogisticsInfo (refrenceId,nu,com,status,state,data,createTime,updateTime,delFlag)
SELECT refrenceId,nu,com,status,state,data,createTime,createTime,delState FROM `ZttxWeb2014DEAL`.LogisticsInfo;

INSERT INTO `Zttx-Web-New`.ParityColumn (refrenceId,name,isRequired,sort,createTime,updateTime,delFlag,style,color)
SELECT refrenceId,name,isRequired,sort,createTime,createTime,delState,style,color FROM `ZttxWeb2014DEAL`.ParityColumn;

INSERT INTO `Zttx-Web-New`.BrandsCount  (`refrenceId`, `brandId`, `brandsId`, `joinCount`, `applyCount`, `inviteCount`, `orderNum`, `productCount`, `createTime`, `updateTime`, `ranking`, `favNum`, `viewNum`, `delFlag`)
SELECT   `brandsId` ,  `brandId`,  `brandsId`,`joinCount`, `applyCount`, `inviteCount`, `orderNum`, `productCount`,         0 ,         0,        `ranking`, `favNum`,  0 ,    0  FROM `ZttxWeb2014DEAL`.BrandsCount;

INSERT INTO `Zttx-Web-New`.BrandService  (`refrenceId`, `brandId`, `serviceName`, `userId`, `serviceImage`, `domainName`, `userGender`, `jobNum`, `serviceTel`, `serviceMobile`, `serviceCate`, `createTime`, `updateTime`, `delFlag`) 
SELECT    `refrenceId`, `brandId`, `serviceName`, `userId`, `serviceImage`, `domainName`, `userGender`, `jobNum`, `serviceTel`, `serviceMobile`, `serviceCate`, `createTime`,     0,        `delState`  FROM `ZttxWeb2014DEAL`.`BrandService`;

INSERT INTO `Zttx-Web-New`.Adjustment   (`refrenceId`, `brandId`, `dealerId`, `adjustAllPrice`, `createTime`, `updateTime`, `delFlag`)
SELECT  `refrenceId`, `brandId`, `dealerId`,   `adjustAllPrice`, `createTime`   , 0,         0           FROM `ZttxWeb2014DEAL`.`BrandAdjustment`;

INSERT INTO `Zttx-Web-New`.Adjustments   (`refrenceId`, `brandAdjustId`, `productId`, `productSkuId`, `oldFacSkuDirPrice`, `nowFacSkuDirPrice`, `quantity`, `totalAdjustPrice`, `createTime`, `updateTime`, `delFlag`)
SELECT  `refrenceId`, `brandAdjustId`, `productId`, `productSkuId`, `oldFacSkuDirPrice`, `nowFacSkuDirPrice`, `quantity`, `totalAdjustPrice`,   `createTime`   ,0          ,0        FROM `ZttxWeb2014DEAL`.`BrandAdjustments`;

INSERT INTO `Zttx-Web-New`.`BrandDeal` (`refrenceId`,  `brandId`, `brandesId`, `dealNo`, `createTime`, `updateTime`, `createIp`,  `delFlag`)
SELECT `refrenceId`,  `brandId`, `brandesId`, `dealNo`, `createTime`, `createTime`, `createIp`,  `delState` FROM `ZttxWeb2014DEAL`.`BrandDeal`;

-- 初始化产品权重数据
INSERT INTO `Zttx-Web-New`.`ProductWeightInfo` (
  `refrenceId`,
  `brandId`,
  `brandsId`,
  `weight`,
  `season`,
  `createTime`,
  `updateTime`,
  `delFlag`
) 
SELECT 
  `refrenceId`,
  `brandId`,
  `brandsId`,
	0,
    (select QUARTER(from_unixtime(createTime/1000,'%Y-%m-%d'))),
    0,
    0,
    0 
FROM
  `Zttx-Web-New`.`ProductBaseInfo`;
 

SET FOREIGN_KEY_CHECKS = 1;