<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="mt20">
    <c:set value="${fns:getAdvertPosit(advert_index_banner) }" var="posit"/>
    <c:if test="${posit != null && posit.viewNum > 0 }">
        <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum -1}">
            <a href="${list.urlAddress}" target="_blank" title="${list.adTitle}">
                <img src="${res}${list.adLogo}" alt="${list.adTitle}" border="0"
                     width="${posit.imgWidth}" height="${posit.imgHeight }"/>
            </a>
        </c:forEach>
    </c:if>
    <%--<a href="" target="_blank"<img src="${res}/images/fronts/index/temp/ad.jpg" alt="" width="1190" height="90"/></a>--%>
</div>