<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<div class="grid bgc-w bd p9 main_three mb10">
    <div class="grid">
        <ul class="main-three-items">
            <c:set value="${fns:getAdvertPosit(recommend_big) }" var="up_posit"></c:set>
            <c:if test="${ up_posit != null }">
                <c:forEach items="${up_posit.subList }" var="adverts" varStatus="status">
                    <li class="${status.last ? 'last' : '' }">
                        <dl>
                            <dt>${adverts.adTitle }</dt>
                            <dd class="first">
                                <a href="${ctx }${adverts.urlAddress}" title="${adverts.adTitle }" target="_blank">
                                    <p>
                                        <c:set value="${res }${adverts.adLogo}" var="url" />
                                        <img alt="${adverts.altMark }" class="js-loadafter" src="${res }${adverts.adLogo}"
                                             data-original="${res }${adverts.adLogo}" width="${up_posit.imgWidth }"
                                             height="${up_posit.imgHeight }"/>
                                    </p>
                                </a>
                            </dd>
                        </dl>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
        <div class="line">
            <b></b>
        </div>
    </div>
</div>