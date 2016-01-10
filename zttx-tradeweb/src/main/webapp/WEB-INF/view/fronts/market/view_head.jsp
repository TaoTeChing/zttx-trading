<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header-nav" style="height: 150px;">
    <div class="header-navcen">
        <div class="header-cen" style="height: 120px" id="headerhover">
            <style>
                .header-cen { background: url(${ctx}/images/market/temp/banar.jpg) no-repeat; }
            </style>
            <div class="logo" style="height: 55px">logo</div>
            <img src="${ctx}/images/market/temp/banar.jpg" width="1200" height="120" />
        </div>
        <div class="nav-cen" style="height: 30px" id="headerhover1">
            <ul class="menu-list">
                <li class="menu"><a class="menu-link<c:if test="${param.m=='1'}"> selected</c:if>" href="/index/${brandesId}"><span class="title">品牌首页</span></a></li>
                <li class="menu"><a class="menu-link<c:if test="${param.m=='2'}"> selected</c:if>" href="/viewBrandRecruit/${brandesId}/${brandId}"><span class="title">品牌招募书</span></a></li>
                <li class="menu"><a class="menu-link<c:if test="${param.m=='3'}"> selected</c:if>" href="/viewBrandVisual/${brandesId}/${brandId}"><span class="title">陈列视觉</span></a></li>
                <li class="menu"><a class="menu-link<c:if test="${param.m=='4'}"> selected</c:if>" href="/viewProductInfo/${brandesId}/${brandId}"><span class="title">产品展示</span></a></li>
                <li class="menu"><a class="menu-link<c:if test="${param.m=='5'}"> selected</c:if>" href="/viewCompany/${brandesId}/${brandId}"><span class="title">企业展示</span></a></li>
                <li class="menu"><a class="menu-link<c:if test="${param.m=='6'}"> selected</c:if>" href="/viewBrandNetwork/${brandesId}/${brandId}"><span class="title">门店展示</span></a></li>
                <li class="menu"><a class="menu-link<c:if test="${param.m=='7'}"> selected</c:if>" href="/viewBrandNews/${brandesId}/${brandId}"><span class="title">品牌新闻</span></a></li>
                <li class="menu"><a class="menu-link<c:if test="${param.m=='8'}"> selected</c:if>" href="/viewBrandDocument/${brandesId}/${brandId}"><span class="title">资料下载</span></a></li>
                <%--<li class="menu"><a class="menu-link<c:if test="${param.m=='9'}"> selected</c:if>" href="/viewLeaveMessage/${brandesId}/${brandId}"><span class="title">留言互动</span></a></li>--%>
                <li><span class="address font f-r">店铺地址：http://duocare.8637.com 复制</span></li>
        	</ul>
        </div>
    </div>
</div>
<!--// header end-->