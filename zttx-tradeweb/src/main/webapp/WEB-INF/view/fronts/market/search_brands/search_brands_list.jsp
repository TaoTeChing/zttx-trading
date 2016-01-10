<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<layout:override name="content">
    <c:forEach items="${brandsPage.list}" var="brands">
        <c:set var="bName" value="${fns:cleanHtmlElems(brands.brandesName)}"/>
        <c:set var="mark" value="${fns:cleanHtmlElems(brands.brandesMark)}"/>
        <c:set value="${ fns:getBrandsIdSubDomain(brands.id)}" var="domain"></c:set>
        <div class="brand-ana-item">
            <div class="brand-recom">
                <div class="imgbox">
                    <a class="js-img-center" href="http://${domain}${zttx}" target="_blank" title="${bName}">
                        <img src="${res}${fns:getImageDomainPath(brands.brandesLogo, 100, 50)}"/>
                    </a>
                </div>
                <a href="http://${domain}${zttx}" target="_blank" title="${bName}">
                    <h3 class="tit">${bName}</h3>
                </a>

                <p class="des">
                    <em class="fontcolor3"> 品牌介绍：</em> <a href title="${fns:trimLongString(mark, 60)}" >${fns:trimLongString(mark, 60)}</a>
                </p>
            </div>
            <div class="brand-trand">
                <p class="newstate-p">品牌产品</p>

                <div class="newstate-show">
                    <a href="javascript:;" class="pagestep-btn prev">&lt;</a>
                    <a href="javascript:;" class="pagestep-btn next">&gt;</a>

                    <div class="newstate-scroller">
                        <ul class="newstate-showul">
                            <c:forEach items="${brands.prodList}" var="prod">
                                <li class="newstate-showli">
                                    <a href="${ctx}/market/product/${prod.refrenceId}" title="${prod.productTitle}" target="_blank">
                                        <img src="${res}${fns:getImageDomainPath(prod.productImage, 220, 220)}" alt="${prod.productTitle}" width="126" height="126"/>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</layout:override>
<%-- 父模板 --%>
<%@ include file="search_brands_layout.jsp" %>