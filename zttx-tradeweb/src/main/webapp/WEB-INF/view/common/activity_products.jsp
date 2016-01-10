<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>


<c:forEach items="${activityProductResult.list}" var="actPro">
 <c:set value="${fns:getImageDomainPath(actPro.image,440,440)}" var="actProImage"></c:set>
    <li>
        <div class="pic">
            <a href="${ctx}/market/product/${actPro.productId}?code=A00001" target="_blank" title="${actPro.productTitle }" alt="${actPro.productTitle }"><img src="${res}${actProImage}" width="250" height="250"></a>
        </div>
        <div class="detail">
            <h4 class="title"><a href="${ctx}/market/product/${actPro.productId}?code=A00001" target="_blank" title="${actPro.productTitle}">${actPro.productTitle}</a></h4>
            <%-- 未开放 --%>
            <c:if test="${actPro.isActivity != 1}">
                <p class="unempower"></p>
            </c:if>
            <%-- 已开放 --%>
            <c:if test="${actPro.isActivity == 1}">
                <p class="price"><fmt:formatNumber value="${actPro.price}" type="currency" pattern="0.00" /></p>
            </c:if>
            <p class="time">促销时间：${fns:getTimeFromLong(actPro.activityStartTime,"yy.MM.dd")}-${fns:getTimeFromLong(actPro.activityEndTime,"yy.MM.dd")}</p>
            <c:forEach items="${actPro.productList}" var="obj" begin="0" end="1">
                <p><i class="icon icon-shop ${obj.style}"></i>${obj.columnName}：<fmt:formatNumber value="${obj.price}" type="currency" pattern="0.00" />元</p>
            </c:forEach>
            <p><i class="icon icon-shengdai"></i>省代价格：<fmt:formatNumber value="${actPro.provincePrice}" type="currency" pattern="0.00" />元</p>
            <p class="mt10">
                <c:set value="${fns:getBrandsIdSubDomain(actPro.brandsId)}" var="domain"></c:set>
                <!--   <a class="ts-button ts-button-white" href="javascript:;"><i class="icon icon-message-s"></i>dianji </a>-->
                <c:if test="${actPro.isActivity != 1}">
                    <a class="ts-button ts-button-disabled" href="javascript:;"><i class="icon icon-file"></i>立即购买</a>
                </c:if>
                <c:if test="${actPro.isActivity == 1}">
                    <a class="ts-button ts-button-orange" href="${ctx}/market/product/${actPro.productId}?code=A00001" target="_blank"><i class="icon icon-file"></i>立即购买</a>
                </c:if>
            </p>
        </div>
    </li>
</c:forEach>