<!DOCTYPE html><%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<html>
<head>
<meta charset="utf-8">
<title>8637品牌超级代理-店铺装修-页面管理</title>
<meta name="keywords" content="8637品牌超级代理-店铺装修">
<meta name="description" content="8637品牌超级代理-店铺装修">
<link href="${res}/styles/market/brandmarket/brand_edit/kuang.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/WEB-INF/view/market/brandmarket/brand_edit/brand_edit_k_top.jsp" />
<div class="k-body">
    <div class="k-left f-l">
    		<h1 class="k-title">页面管理</h1>
            <h2 class="k-lefth2"><i class="k-hico"></i>基础页</h2>
            <ul class="k-leftul">
                <li><a href="" title="" target="mainframe">首页</a></li>
                <li class="selected"><a href="brandrecruitingbook" title="" target="mainframe">品牌招募书</a></li>
                <li><a href="" title="" target="mainframe">企业展示</a></li>
                <li><a href="" title="" target="mainframe">品牌新闻</a></li>
                <li><a href="" title="" target="mainframe">陈列视觉</a></li>
                <li><a href="" title="" target="mainframe">门店展示</a></li>
            </ul>
            <h2 class="k-lefth2"><i class="k-hico"></i>列表页</h2>
            <ul class="k-leftul">
                <li><a href="" title="" target="mainframe">默认列表页</a></li>
                <li><a href="" title="" target="mainframe">产品展示列表页</a></li>
            </ul>
            <h2 class="k-lefth2"><i class="k-hico"></i>详情页</h2>
            <ul class="k-leftul">
            	<li><a href="" title="" target="mainframe">默认详情页面</a></li>
                <li><a href="" title="" target="mainframe">产品展示详情页</a></li>
            </ul>
            <h2 class="k-lefth2"><i class="k-hico"></i>自定义页面</h2>
            <ul class="k-leftul">
            	<li><a href="" title="" target="mainframe">默认自定义页面</a></li>
            </ul>
    </div>
    <div class="k-right f-l">
    	<div class="shousuo"></div>
    	<div class="k-method"><a href="#" title="" target="mainframe"><span class="k-edit selected">页面编辑</span></a><a href="#" title="" target="mainframe"><span class="k-cantrl">布局管理</span></a><a href="#" title="" target="mainframe"><span class="k-line">页面属性</span></a><a href="#" title="" target="mainframe"><span class="k-delete">删除页面</span></a></div>
    	<iframe id="testIframe" name="mainframe" marginwidth="0" marginheight="0" src="brandrecruitingbook" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
    <div class="clear"></div>
</div>
    <jsp:include page="/WEB-INF/view/market/brandmarket/brand_edit/brand_edit_foot.jsp" />
</body>
</html>