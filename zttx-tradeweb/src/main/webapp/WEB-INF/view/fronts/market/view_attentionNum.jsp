<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%--<!-- 浏览量  关注  -->--%>
<div class="attention">
    <p class="font fontcolor3">
        <span class="guanzhudu"></span>收藏：<em class="gz-num">${empty brandsCount ? 0 : brandsCount.favNum}</em>
    </p>
    <c:if test="${showFavBtn}">
        <p class="font fontcolor3">
            <c:if test="${isCollected}"><span class="yiguanzhu"></span>已收藏</c:if>
            <c:if test="${not isCollected}"><span class="weiguanzhu"></span><a href="javascript:void(0);" class="js-collected-brands" data-brandsid="${brandsId}">点击收藏</a></c:if>
        </p>
    </c:if>
</div>