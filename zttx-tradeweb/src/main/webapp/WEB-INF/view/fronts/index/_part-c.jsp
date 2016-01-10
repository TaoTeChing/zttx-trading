<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="result" value="${fns:searchBrandesResult('101000000',notIn)}"/>
<div class="hall clearfix">
    <div class="fl hall-m">
        <div class="tit clearfix">
            <h4 class="fl"><a href="${ctx}/lady?st=b">女装品牌馆</a></h4>
            <a href="${ctx}/lady?st=b" class="more_1 fr"></a>
        </div>
        <div class="hall-focus">
            <a href="javascript:;" class="prev">&lt;</a>
            <a href="javascript:;" class="next">&gt;</a>

            <div class="hall-focus-content">
                <ul>
                    <c:set value="${fns:getAdvertPosit(advert_brand_lady ) }" var="posit"/>
                    <c:if test="${posit != null && posit.viewNum > 0 }">
                        <%--<!--广告-->--%>
                        <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum-1}">
                            <li class="hall-focus-list">
                                <a href="${list.urlAddress}" target="_blank" title="${list.adTitle}">
                                    <img src="${res}${list.adLogo}" alt="${list.adTitle}" border="0"
                                         width="${posit.imgWidth}" height="${posit.imgHeight }"/>
                                </a>
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
            <div class="hall-focus-bg">
                <c:set value="${fns:getAdvertPosit(advert_brand_lady_bottom ) }" var="posit"/>
                <c:if test="${posit != null && posit.viewNum > 0 }">
                    <c:forEach var="item" items="${posit.subList}" begin="0" end="${posit.viewNum-1}">
                        <a href="${item.urlAddress}" class="js-img-center" title="${item.adTitle}" target="_blank">
                            <img src="${res}${item.adLogo}" alt="${item.adTitle}">
                        </a>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
    <div class="hall-list fl">
        <ul>
            <c:set var="newests" value="${result.newests}"/>
            <c:forEach var="item" items="${newests}">
                <c:set var="notIn" scope="session" value="${notIn.concat(',').concat(item.refrenceId)}"/>
                <li>
                    <div class="hall-list-hd" title="${item.brandsName}">
                        <c:set value="${fns:getBrandsIdSubDomain(item.refrenceId)}" var="domain"/>
                        <a href="http://${domain}${zttx}" target="_blank">${item.brandsName}</a>
                    </div>
                    <c:set var="proTopSales" value="${item.productList}"/>
                    <div class="hall-list-bd clearfix">
                        <c:forEach var="pro" items="${proTopSales}" varStatus="status">
                            <c:choose>
                                <c:when test="${status.index == 0}">
                                    <a href="${ctx}/market/product/${pro.refrenceId}?code=default"
                                       title="${pro.productTitle}" class="js-img-center pic_a_${status.index}" target="_blank">
                                        <img class="pic_${status.index}" alt="${pro.productTitle}"
                                             src="${res}${fns:getImageDomainPath(pro.productImage,200 ,200)}"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctx}/market/product/${pro.refrenceId}?code=default"
                                       title="${pro.productTitle}" class="js-img-center pic_a_${status.index}" target="_blank">
                                        <img class="pic_${status.index}" alt="${pro.productTitle}"
                                             src="${res}${fns:getImageDomainPath(pro.productImage,90 ,90)}"/>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <div class="hall-list-fd clearfix">
                        <span class="fd_1">${item.brandsCount.productCount}<br>产品数</span>
                        <span class="fd_2"><em>50%</em><br>品牌特惠</span>
                        <a href="javascript:applyJoin('${pro.refrenceId}', '');" class="fd_3">申请<br>加盟</a>
                        <%--没有 品牌特惠 的显示方式
                        <span class="fd_4">产品数&nbsp;&nbsp;${item.brandsCount.productCount}</span>
                        <a href="javascript:;" class="fd_5">申请加盟</a>--%>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>