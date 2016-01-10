<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>开发日志</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  	<pre style="font-size: 12px;">
  	 	[2014-11-13]
  	 		1.[MOD]ALTER TABLE `DealerBuySerLog` ADD COLUMN `addressId`  varchar(32) NULL COMMENT '设备收货地址' AFTER `updateTime`;
  	  		2.
  		[2014-11-12]
  			1.[NEW]询价列表 增加已移除状态的显示 
  			 
  		[2014-11-11]
  			1.[FIX]修正询价订单列表中 "加入购物车" 改成 "加入进货单"
  			2.[FIX][<a href="http://172.16.1.6:8080/index.php?m=bug&f=view&t=html&id=5153" target="_blank">#5153</a>]修正询价订单列表查询条件时间临界范围问题
  			
  		[2014-11-10]
			1.[FIX][<a href="http://172.16.1.6:8080/index.php?m=bug&f=view&t=html&id=5150" target="_blank">#5150</a>]修正询价列表页面 某些订单不能加入购物车的BUG
  	    	
  		[2014-10-21]
  			1.[NEW]启用Spring @Async 简化需异步处理的代码 
  				1.Spring.xml 配置有调整
  			2.[FIX]重构接口方法中需要调用发送短信的接口代码
  			
  		[2014-10-17]
  			1.[FIX]增加邮编为必填项的验证
  			
  		[2014-10-14]
  			1.[NEW]增加终端商收货地址,下单时收货地址 增加邮编的功能
  			2.[FIX]修正终端商目录页面,页头点击失效的BUG
  		
  		[2014-10-13]
  			1.[FIX]修正清仓业页面 被修改导致业务处理出错的BUG(不能正常添加入进货单)
  			
  		[2014-10-09]
  			1.[NEW]增加ProductCount monthSaleNum增加 调整相关业务
  			
  		[2014-10-08]
  			1.[FIX]产品属性依据isImgAttr判断是颜色还是发尺寸(不严谨)  效果见单属性的订单详情页
  			
  		[2014-09-29]
  			1.[TST]本地模拟测试域名Cookies 后台操作问题[正常]
  			2.[TST]200机器模拟测试域名Cookies 后台操作问题[正常]
  			
  		[2014-09-28]
  			1.[NEW]测试cookies,在www.8637.com后台无法移除的问题 
  				1)cookies set参数 写为 {path:"/",domain:document.domain}
  			2.修改终端商名录 文字描述 {免费用户--&gt;实体认证}
  			
  		[2014-09-26]
  			1.[MOD]修改店铺目录下的部分文字描述
  			2.[FIX]BUG排查修复
  			
  		[2014-09-25]
  			1.[FIX]清空本地购物车,cookies没有被清除的BUG
  			2.[MOD]修改终端商目录推荐策略 当前选定范围的兄弟城市/县区(排除当前选择)
  			3.[FIX]修改处理后台无法清空cookies的问题(针对images.8637.com)
  			4.[FIX]修改终端后台购物 快速修改数量 可能出现数量 修改无效的BUG
  			5.[NEW]增加接口添加图片时 实行缩小图片的处理
  	
  		[2014-09-24]
  			1.[FIX]产品详细面增加对内部账号发布的产品 只限清仓活动页面[活动ID取值错误的问题]	
  			2.[FIX]清仓活动页面加入购物车字符描述错误
  			3.[NEW]增加经销商店铺同步接口syncShoperEnv
  			4.[NEW]清仓购物车增加单个删除与清空功能(包括处理本地购物车与后台购物车)
  			5.[FIX]清仓物品本地购物车图片取值错误
  			
  		[2014-09-23]
  			1.[NEW]增加未登录用户的针对活动产品的本地购物车(基于Cookies) [限授权的游客用户]
  				1) 活动产品添加到本地购物车便于维护
  				2) 登录后限时添加到购物车(限终端用户)
  			2.[NEW]产品详细面增加对内部账号发布的产品 只限清仓活动页面	
  			3.[FIX]品牌产品活动修改产品需要强制上传图的BUG
  			4.[OTH]品牌商后台，活动产品线页面加一列，未通过产品数  具体问单志峰(告知施)
  				
  		[2014-09-22]
  			1.[NEW]增加店铺信息在前台可控显示的需求
  				1)DealerShopEnv 增加 showed 字段 1: 显示 0: 不显示  
  					alter table DealerShopEnv add showed int(1) default 1 comment '是否显示店铺';
  				2)增加支撑平台控制显示与否的接口
  				3)调整原来register接口使其支持showed字段的扩展
  			2.[NEW]增加店铺信息采集手机号码不能重复的需求
  			3.[FIX]修正店铺从支持平台导过来的数据错误的BUG
  				1) update DealerShopEnv set trade=1 where trade="";
  				8) update DealerShopEnv set trade=8 where trade="";
  			4.[FIX]店铺列表展示统一修改为按创建时间 逆序排列
  				
  		---------------------------------------------------------------------------------------------------
  		
  		<font color="red">[2014-09-20] *** 生成环境 发布一版本 ***</font>
  		
  		[2014-09-19]
  			1./market/clearance 清仓活动页面 只显示购物车里清仓产品
  			2.店铺城市选择页面,根据ip选择当前城市及周边城市(同级别城市)
  			
  		[2014-09-18]
  			1.清理trade,shoper两个模块的空包结构
  			2.修改品牌商的注册接口时的 生成密码 策略 从随机 6位字母数字变成 6位数字
  			3.修正购物车产品价格小数为保留两位小数
  			
  		[2014-09-17]
  			1.重写购物车显示页相关方法[优化]
  			2.账号审核接口发送短信内空修改[经销姓名-&gt;经销账号(手机)]
  	</pre>
  </body>
</html>
