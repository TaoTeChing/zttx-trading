<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<style>

</style>
<div class="header-round">
    <div class="header clearfix" style="z-index: 216;">
        <div class="logo">
            <a href="/">
                <h1>8637品牌超级代理</h1>
            </a>
        </div>
        <div class="fr clearfix" style="margin-top: 28px;">
            <div class="search fl clearfix">
                <form action="${ctx}/search" method="get" id="headerSearch">
                    <div class="form fl">
                        <input type="text" name="q" id="searchTxt" class="searchTxt" placeholder="搜索您需要的商品或品牌" value="${param.q == null ? "" : param.q}" autocomplete="off" />
                        <span id="hostList"></span>
                        <ul class="search-list" id="searchList"></ul>
                    </div>
                    <button type="submit" id="searchBtn" class="fl">
                        搜索
                    </button>
                </form>
            </div>
            <c:set value="${fns:getUserPrincipal()}" var="userPrincipal"/>
            <c:if test="${null != userPrincipal && userPrincipal.userType eq 1}">
            <div class="cart fl" id="cart">
                <div class="cart-hd">
                    <i class="icon-cart"></i>
                    <%--<i class="more1">&gt;</i>--%>
                    <%--<i class="cartCount" id="cartCount">0</i>--%>
                    <a href="javascript:;">我的进货单</a>
                </div>
                <div class="cart-drop" id="cartDrop">
                    <div class="d1">商品列表</div>
                    <div class="d2">
                        <ul id="cartList"></ul>
                    </div>
                    <div class="d3 clearfix">
                        <span class="fl ta-r" style="word-break: break-all;">还剩 <strong class="cartSurplus">0</strong> 件商品 <%--共计 <strong>￥110.00</strong> 元--%></span>
                        <a href="${ctx}/dealer/dealerShoper/cart" class="fr" target="_blank">去进货单</a>
                    </div>
                </div>
            </div>
            </c:if>
        </div>
    </div>
</div>
<%-- header-round end --%>
