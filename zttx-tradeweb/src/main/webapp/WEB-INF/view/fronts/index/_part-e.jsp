<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<c:set var="mainIds" value="200000000,150000000,151000000,254000000,250000000"/>
<c:set var="result" value="${fns:searchOtherBrandesResult(mainIds)}"/>
<div class="hall hall-more clearfix">
    <div class="hall-list fl">
        <ul class="clearfix">
            <c:forEach var="item" items="${result.others}">
                <li>
                    <div class="hall-bat">
                        <div class="tit clearfix">
                            <h4 class="fl"><i class="icon-children"></i><a href="${ctx}/search?mainId=${item.mainId}">${item.mainName}</a></h4>
                            <a href="${ctx}/search?mainId=${item.mainId}" class="more_1 fr"></a>
                        </div>
                        <div class="hall-tag">
                            <%--<a href="">夏季新品</a><a href="">商场同款</a><a href="">男装</a><a href="">女装</a>--%>
                        </div>
                    </div>
                    <div class="hall-list-hd" title="${item.brandsName}">
                        <c:set value="${fns:getBrandsIdSubDomain(item.refrenceId)}" var="domain" />
                        <a href="http://${domain}${zttx}" target="_blank">${item.brandsName}</a>
                    </div>
                    <c:set var="proTopSales" value="${item.productList}"/>
                    <div class="hall-list-bd clearfix">
                        <c:forEach var="pro" items="${proTopSales}" varStatus="status">
                            <c:choose>
                                <c:when test="${status.index == 0}">
                                    <a href="${ctx}/market/product/${pro.refrenceId}?code=default" class="js-img-center pic_a_${status.index}" title="${pro.productTitle}" target="_blank">
                                        <img class="pic_${status.index}" alt="${pro.productTitle}"
                                             src="${res}${fns:getImageDomainPath(pro.productImage,200 ,200)}"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${ctx}/market/product/${pro.refrenceId}?code=default" class="js-img-center pic_a_${status.index}" title="${pro.productTitle}" target="_blank">
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
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>