<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>8637品牌超级代理-店铺装修-商品分类管理</title>
<meta name="keywords" content="8637品牌超级代理-店铺装修">
<meta name="description" content="8637品牌超级代理-店铺装修">
<link href="${res}/styles/fronts/market/kuang.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="k-top">
	<div class="k-logo"><img src="${res}/images/fronts/market/k-logo.gif" height="56"/></div>
	<ul class="k-top-cen">
    	<li>
        	<a href="#" title="" target="" class="k-bet">装修<i class="k-down"></i></a>
            <dl class="k-dropmenu">
                <dd><a href="页面管理.html" title="" target="">页面管理</a></dd>
                <dd><a href="样式管理.html" title="" target="">样式管理</a></dd>
                <dd><a href="#" title="" target="">模板管理</a></dd>
                <dd><a href="#" title="" target="">模块管理</a></dd>
                <dd><a href="#" title="" target="">装修分析</a></dd>
            </dl>
        </li>
        <li>
        	<a href="#" title="" target="" class="k-bet">产品<i class="k-down"></i></a>
        	<dl class="k-dropmenu">
                	<dd><a href="商品分类管理.html" title="" target="">分类管理</a></dd>
                    <dd><a href="#" title="" target="">宝贝管理</a></dd>
                </dl>
        </li>
        <li><a href="#" title="" target="" class="k-bet">营销</a></li>
    </ul>
    <div class="k-shopername">
    	<p><a href="#"><span class="k-shoperwelcome">欢迎您：上海朵彩棉服饰有限公司</span></a><a href="javascript:;"><span class="k-shoperexit">退出登录</span></a></p>
        <p><span class="k-shoperonhelp f-r"><i class="k-shoperonh"></i>在线帮助</span><span class="k-shopermessage f-r"><i class="k-shoperm"></i>消息0条</span></p>
    </div>
</div>
<div class="k-body">
    <div class="k-left f-l">
    		<h1 class="k-title">分类管理</h1>
            <h2 class="k-lefth2"><i class="k-hico"></i>基础页</h2>
            <ul class="k-leftul">
                <li class="selected"><a href="manage_sort.html" title="" target="">分类管理</a></li>
                <li><a href="#" title="" target="">宝贝管理</a></li>
            </ul>
    </div>
    <div class="k-right f-l">
    	<div class="shousuo"></div>
    	<div class="k-method"><span class="k-addmaintree">添加分类</span></div>
    	<iframe id="testIframe" name="mainframe" marginwidth="0" marginheight="0" src="manageSort" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
    <div class="clear"></div>
</div>
<script src="${res}/scripts/jquery.min.js"></script>
<script src="${res}/scripts/plugin/jqueryui/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="${res}/scripts/market/jquery.elastislide.js" type="text/javascript"></script>
<script src="${res}/scripts/market/kuang.js" type="text/javascript"></script>
</body>
</html>