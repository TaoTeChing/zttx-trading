<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/view/include/taglib.jsp"  %>
<html>
<head>
<meta charset="utf-8">
<title>8637品牌超级代理-店铺装修-选择颜色</title>
<meta name="keywords" content="8637品牌超级代理-店铺装修">
<meta name="description" content="8637品牌超级代理-店铺装修">
<link href="${res}/styles/market/brandmarket/brand_edit/kuang.css" rel="stylesheet" type="text/css" />
<link href="${res}/styles/market/brandmarket/brand_edit/btnico.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="/WEB-INF/view/market/brandmarket/brand_edit/brand_edit_k_top.jsp" />
<div class="k-body">
    <div class="k-left f-l">
    		<h1 class="k-title">样式管理</h1>
            <h2 class="k-lefth2"><i class="k-hico"></i>基础页</h2>
            <ul class="k-leftul">
                <li><a href="背景设置(下级页面).html" title="" target="">背景设置</a></li>
                <li class="selected"><a href="选择配套颜色.html" title="" target="">选择配套颜色</a></li>
            </ul>
    </div>
    <div class="k-right f-l">
    	<div class="shousuo"></div>
    	<div class="k-method"><span class="k-edit selected">页面编辑</span> <span class="k-cantrl">布局管理</span> <span class="k-line">页面属性</span> <span class="k-delete">删除页面</span></div>
        <div class="k-editall">
        	<div class="k-titcolor">
            	<span class="btn btn-primary" id="primary">天蓝色</span> <span class="btn btn-success">草绿色</span> <span class="btn btn-inverse">黑白色</span> <span class="btn btn-danger">深红色</span> <span class="btn btn-info">浅蓝色</span> <span class="btn btn-warning">浅黄色</span>
            </div>
            <div class="k-sub"><div class="btn btn-small btn-inverse k-cath f-l">保存</div><div class="btn btn-small btn-inverse f-l">取消</div></div>
        </div>
         <div class="k-sliupdown"><div class="k-sliup">收起</div><div class="k-slidown">展开</div></div>
    	<iframe id="testIframe" name="mainframe" marginwidth="0" marginheight="0" src="" frameborder="0" width="100%" height="100%" scrolling="auto"></iframe>
    </div>
    <div class="clear"></div>
</div>
<script src="${res}/scripts/jquery-1.11.0.min.js"></script>
<script src="${res}/scripts/common/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="${res}/scripts/market/brandmarket/jquery.elastislide.js" type="text/javascript"></script>
<script>
$(function(){
	$('#testIframe').ready(function(){
		var hhhh = $($(this)[0].contentDocument).find("#headerhover1").height();
	});
	//设置颜色
    $('.k-titcolor .btn-primary').click(function(){
		$("#testIframe").contents().find("#headerhover1").css({
			backgroundColor:'#0071CC',
		});
		$("#testIframe").contents().find("#headerhover1 a.selected").css({
			backgroundColor:'#0045CC',
		});
		$("#testIframe").contents().find("h3.title").css({
			backgroundColor:'#0071CC',
			color:'#fff'
		});
    });
	$('.k-titcolor .btn-success').click(function(){
		$("#testIframe").contents().find("#headerhover1").css({
			backgroundColor:'#5BB75B',
		});
		$("#testIframe").contents().find("#headerhover1 a.selected").css({
			backgroundColor:'#51A451',
		});
		$("#testIframe").contents().find("h3.title").css({
			backgroundColor:'#5BB75B',
			color:'#fff'
		});
	});
	$('.k-titcolor .btn-inverse').click(function(){
		$("#testIframe").contents().find("#headerhover1").css({
			backgroundColor:'#363636',
		});
		$("#testIframe").contents().find("#headerhover1 a.selected").css({
			backgroundColor:'#000',
		});
		$("#testIframe").contents().find("h3.title").css({
			backgroundColor:'#363636',
			color:'#fff'
		});
	});
	$('.k-titcolor .btn-danger').click(function(){
		$("#testIframe").contents().find("#headerhover1").css({
			backgroundColor:'#BD362F',
		});
		$("#testIframe").contents().find("#headerhover1 a.selected").css({
			backgroundColor:'#ab261f',
		});
		$("#testIframe").contents().find("h3.title").css({
			backgroundColor:'#BD362F',
			color:'#fff'
		});
	});
	$('.k-titcolor .btn-info').click(function(){
		$("#testIframe").contents().find("#headerhover1").css({
			backgroundColor:'#49AFCD',
		});
		$("#testIframe").contents().find("#headerhover1 a.selected").css({
			backgroundColor:'#3198B6',
		});
		$("#testIframe").contents().find("h3.title").css({
			backgroundColor:'#49AFCD',
			color:'#fff'
		});
	});
	$('.k-titcolor .btn-warning').click(function(){
		$("#testIframe").contents().find("#headerhover1").css({
			backgroundColor:'#F89406',
		});
		$("#testIframe").contents().find("#headerhover1 a.selected").css({
			backgroundColor:'#e18707',
		});
		$("#testIframe").contents().find("h3.title").css({
			backgroundColor:'#F89406',
			color:'#fff'
		});
	});
	/*下拉*/
	$('.k-sliup').click(function(){
		$('.k-editall').slideUp();
		$(this).hide();
		$('.k-slidown').show();
	});
	$('.k-slidown').click(function(){
		$('.k-editall').slideDown();
		$(this).hide();
		$('.k-sliup').show();
	});
});
</script>
<script src="${res}/scripts/market/brandmarket/kuang.js" type="text/javascript"></script>
</body>
</html>