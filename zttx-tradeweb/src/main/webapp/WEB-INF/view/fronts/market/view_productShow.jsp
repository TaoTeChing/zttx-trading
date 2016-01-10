<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<h3 class="title clear">产品展示</h3>

<div class="productshow">
    <div id="carousel" class="es-carousel-wrapper">
        <div class="es-carousel">
            <ul class="productshowul mainlist piclist">
                <c:forEach var="item" items="${proList}">
                    <li>
                        <c:set value="${item.productImage}" var="url"></c:set>
                        <a href="${ctx}/market/product/${item.refrenceId}" target="_blank" class="js-img-center">
                            <img src="${res}${fns:getImageDomainPath(url, 220, 220)}" alt="${item.productTitle}" style="/*width:220px;height:220px;*/"/>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="es-nav">
            <span class="es-nav-prev">Previous</span>
            <span class="es-nav-next">Next</span>
        </div>
    </div>
</div>