<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp"%>
<link href="${res}/styles/fronts/market/brandviewbase_edit.css" rel="stylesheet" type="text/css" />
    <c:choose>
        <c:when test="${dGlobal.skinName!=null && dGlobal.skinName!='' && dGlobal.skinName!='default'}">
            <link href="${res}/styles/fronts/market/${dGlobal.skinName}/decoration_edit.css" rel="stylesheet" type="text/css" />
        </c:when>
        <c:otherwise>
            <link href="${res}/styles/fronts/market/decoration_edit.css" rel="stylesheet" type="text/css" />
        </c:otherwise>
    </c:choose>

<style>
        /*中间整体背景链接等*/
    .do-body{
    <c:if test="${not empty dGlobal.fontCcolor}"> color:${dGlobal.fontCcolor} ;</c:if>/*全局字体颜色*/
    <c:if test="${not empty dGlobal.backColor}">   <c:if test="${dGlobal.showBackColor}">background-color:${dGlobal.backColor};</c:if></c:if>
    <c:if test="${not empty dGlobal.backUrl}">    <c:if test="${dGlobal.showBackUrl}">background-image: url('${res}${dGlobal.backUrl}');</c:if></c:if>
    <c:if test="${not empty dGlobal.backRepeat}">  background-repeat: ${dGlobal.backRepeat} ;</c:if>/*全局背景颜色*/
    <c:if test="${not empty dGlobal.backPosition}">    background-position: ${dGlobal.backPosition};</c:if>
    }
    .do-body a{
        <c:if test="${not empty dGlobal.urlFontColor}">color:${dGlobal.urlFontColor};</c:if>
    }/*全局链接字体颜色*/
    .do-body a:hover{
        <c:if test="${not empty dGlobal.urlChangeColor}">color:${dGlobal.urlChangeColor};</c:if>
    }

        /*店名样式*/
    .logo{
    <c:if test="${dHeader.showLogo}">

        <c:if test="${not empty dHeader.logoName}"> background:url('${res}${dHeader.logoName}');</c:if>

    </c:if>

    <c:if test="${!dHeader.showLogo && !dHeader.showName}"> display: none; </c:if>

    }

    .logo h2{
    <c:if test="${not empty dHeader.nameColor}">  color:${dHeader.nameColor};</c:if>
    <c:if test="${not empty dHeader.nameSize}"> font-size:${dHeader.nameSize};</c:if>
    <c:if test="${!dHeader.showName}"> display: none;</c:if>
    }
        /*招牌顶部背景图*/
    .header-nav{
    <c:if test="${not empty dHeader.outBackUrl}"> background:#BA2926 url('${res}${dHeader.outBackUrl}')</c:if>
    }

        /* 通栏内背景图*/
    .header-navcen{
    <c:if test="${not empty dHeader.inBackUrl}"> background:url('${res}${dHeader.inBackUrl}')</c:if>
    }

        /*导航背景样式*/
    .nav-cen{
        background:<c:if test="${not empty dHeader.navDefaultColor}">${dHeader.navDefaultColor}</c:if> <c:if test="${not empty dHeader.navDefaultUrl}">url('${res}${dHeader.navDefaultUrl}')</c:if>;
    }

        /*导航文字颜色*/
    a.menu-link,.menu-link:hover {
        <c:if test="${not empty dHeader.navDefaultFont}">color:${dHeader.navDefaultFont}</c:if>
    }
    a.menu-link:hover{
        <c:if test="${not empty dHeader.navChangeFont}">color: ${dHeader.navChangeFont}</c:if>
    }


        /*选中的导航样式*/
    .menu-link.selected{
    <c:if test="${not empty dHeader.navSelectColor}"> background:${dHeader.navSelectColor}</c:if>
    }

        /*导航移动变化*/
    .menu:hover {
        background: <c:if test="${not empty dHeader.navChangeColor}"> ${dHeader.navChangeColor}</c:if> <c:if test="${not empty dHeader.navChangeUrl}">url('${res}${dHeader.navChangeUrl}')</c:if>
    }

</style>

