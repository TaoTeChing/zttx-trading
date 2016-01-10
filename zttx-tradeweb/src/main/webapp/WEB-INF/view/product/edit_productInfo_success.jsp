<!doctype html>
<%@ page import="com.zttx.web.consts.ProductConsts" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<c:set var="beginType_first" value="<%=ProductConsts.BEGIN_TYPE_FIRST.getKey().toString() %>"></c:set>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理中心-产品管理-发布新产品-发布成功</title>
    <link rel="stylesheet" href="${res}/styles/brand/global.css" />
    <link rel="stylesheet" href="${res}/styles/brand/productmanag.css" />
</head>
<body>
<jsp:include page="/common/menuInfo/mainmenu"/> 
	<div class="main layout">
		 <jsp:include page="/common/menuInfo/sidemenu"/>

<div class="main_con">
    <!-- 面包导航，并不是每个页面都有 -->
    <div class="bread_nav">
        <div class="fl">
            <a class="link" href="/brand/center">管理中心</a>
            <span class="arrow">&gt;&gt;</span>
            <a class="link" href="${ctx}/brand/product/execute">产品管理</a>
            <span class="arrow">&gt;</span>
            <a class="link" href="${ctx}/brand/product/execute">发布新产品</a>
            <span class="arrow">&gt;</span>
            <span class="current">发布成功</span>
        </div>
        <div class="fr">
            <jsp:include page="/WEB-INF/view/brand/common_quick_bar.jsp">
                <jsp:param value="0" name="isShow"/>
            </jsp:include>
        </div>
    </div>
    <div class="inner">
        <!-- 内容都放这个地方  -->
        <div class="productadd">
            <%--第二版步骤条 第三步--%>
            <div class="v2-steps v2-3steps" style="width: 800px;margin-left: 94px;border-bottom: 2px solid #e0e0e0;">
                <span class="text1 current">选择类目</span>
                <span class="text2 current">添加资料</span>
                <span class="text3 current">发布成功</span>
                <%--<span class="text4">加入产品线</span>--%>
                <div class="steps v2-steps-3" style=" background-position-x: 88px;"></div>
            </div>
            <div class="productadd-success-T">
                <div class="title">
                    <i class="success-icon inline-block"></i>您的产品：<strong>${product.brandsName}-${product.productTitle}</strong>
                    <c:choose>
                        <c:when test="${product.stateSet == beginType_first}"> 已发布成功！</c:when>
                        <c:otherwise> 编辑成功！</c:otherwise>
                    </c:choose>
                </div>
                <div class="dobtn" style="margin: 10px 0 10px 42px;">
                    <%--<a href="${ctx}/market/product/${product.refrenceId}" class="ui_button ui_button_lblue" target="_blank">查看该产品</a>
                    --%><a href="${ctx}/brand/product/edit/${product.refrenceId}" class="simple_button">修改该产品</a>
                    <%--<a href="${ctx}/brand/product/linelist/${product.refrenceId}" class="simple_button">加入产品线</a>--%>
                    <a href="${ctx}/brand/product/list" class="simple_button">返回产品列表</a>
                    <a href="${ctx}/brand/product/addnew?dealNo=${product.dealNo}" class="simple_button">继续添加同类产品</a>
                </div>
            </div>
        </div>

    </div>
</div>
</div>
 <jsp:include page="/WEB-INF/view/brand/bottom.jsp" />
 <jsp:include page="/WEB-INF/view/brand/import_javascript.jsp" />


</body>
</html>