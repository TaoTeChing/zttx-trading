<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<layout:override name="content">
    <ul>
        <c:forEach items="${brandsPage.list}" var="brands">
            <c:set var="mark" value="${fns:cleanHtmlElems(brands.brandesMark)}"/>
            <c:set value="${ fns:getBrandsIdSubDomain(brands.id)}" var="domain"></c:set>
            <li class="brand-item">
                <div class="imgbox">
                    <c:set value="${res}${brands.brandesPhotpImg}" var="brandesPhotpImgUrl"/>
                    <a class="js-img-center" href="http://${domain}${zttx}" target="_blank" title="${brands.brandesName}"><img src="${fns:getImageDomainPath(brandesPhotpImgUrl, 300, 300) }" alt="${brands.brandesName}"/></a>
                </div>
                <div class="brand-content-name">
                    <c:set value="${res}${brands.brandesLogo}" var="brandesLogoUrl"/>
                    <a class="js-img-center" href="http://${domain}${zttx}" target="_blank"><img src="${fns:getImageDomainPath(brandesLogoUrl, 100, 50) }" alt="${fns:trimLongString(brands.brandesName, 10)}"/></a>
                </div>
                <div class="brand-content-des">
                    <span<%-- title="${mark}"--%>>${fns:trimLongString(mark, 60)}</span>
                </div>
                <div class="brand-content-bottom">
                        <%--<a class="online js-online" href="#"><i class="brandicons icons-brand-onlink"></i>在线留言</a>--%>
                    <c:set var="bName" value="${brands.brandesName}"/>
                    <a class="${fns:cleanHtmlElems(bName)}" href="http://${domain}${zttx}" target="_blank" title="${fns:trimLongString(bName, 10)}">${fns:trimLongString(bName, 10)}</a>
                    <a class="company" href="http://${domain}${zttx}/company" target="_blank">${brands.brandesCompanyName}</a>
                </div>
                <a class="attent" href="javascript:;"><i class="brandicons icons-brand-attent"></i></a>
                <div class="brand-content-check">
                    <a class="brand-item-btn check-term" href="http://${domain}${zttx}/recruit" target="_blank" style="margin-right: 10px;">查看招募条件</a><%--链接到当前品牌前台“品牌招募书页面”--%>
                    <a class="brand-item-btn leave-message" href="http://${domain}${zttx}" target="_blank">我要咨询留言</a><%--链接到当前品牌前台“留言”--%>
                </div>
                <%--
                	<div>
                    <strong class="brand-item-number">意向加盟人数：11人</strong>
                    <strong class="brand-item-number">关注数：19988人</strong>
                	</div>
                 --%>
            </li>
        </c:forEach>
    </ul>
</layout:override>
<%--父模板--%>
<%@ include file="search_brands_layout.jsp" %>