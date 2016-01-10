<%@ page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%--<!-- 品牌商品牌信息控件 -->--%>
<div class="hidelogobox">
    <div class="imgbox">
    <c:set value="${ fns:getBrandsIdSubDomain(branesInfo.refrenceId)}" var="domain"></c:set>
        <c:set var="url" value="http://${domain}${zttx}"/>
        <a href="${url}" title="${branesInfo.brandsName}">
        	<c:set value="${fns:getImageDomainPath(brandRoom.logoImage, 240, 120) }" var="img"></c:set>
            <img src="${res}${img}" style="width:240px; height:120px;"/>
        </a>
    </div>
    <p class="title"><a href="${url}">${branesInfo.brandsName}</a></p>
    <c:if test="${branesInfo.factoryStore==true}">
    <%--需求 #3818 工厂店图标--%><p class="mt5"><img src="${res}/images/common/factory-brand.jpg" alt=""/></p>
    </c:if>
</div>