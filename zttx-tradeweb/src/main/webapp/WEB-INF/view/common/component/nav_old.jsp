<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<%--网页导航--%>
<div class="nav">
    <div class="em100 clearfix">
        <div class="nav-brand">
            <h2>热门加盟品牌推荐</h2>
             <c:choose>
                 <c:when test="${param.m == '1' }">
                     <div class="nav-brand-recoment clearfix" style="width: 1200px;display: block;border-bottom: 1px solid #ddd; position: absolute; background: #fff;top: 36px;">
                 </c:when>
                 <c:otherwise>
                     <div class="nav-brand-recoment clearfix js-recoment" style="display: none;border: 1px solid #ddd; position: absolute; background: #fff;">
                 </c:otherwise>
             </c:choose>
                    <dl class="recoment-dl clearfix" style="">
                        <c:set var="dealDic1" value="${fns:getAdvertPosit('index_DealDic_1')}"/>
                        <c:set var="dealDic1s" value="${fns:getRandomAdverts(dealDic1.refrenceId, dealDic1.advertKey, 6)}"/>
                        <dt class="recoment-dt"><i class="recoment-icons recoment-dt-dress"></i>${dealDic1.advertName}</dt>
                        <c:forEach items="${dealDic1s}" var="item">
                            <dd><a href="${fns:getAdUrl(item.urlAddress)}" target="_blank" title="${adverts.adTitle }"><img  src="${res}${item.adLogo}" alt="${item.altMark}" width="80" height="40"></a></dd>
                        </c:forEach>
                    </dl>
                    <dl class="recoment-dl clearfix" style="">
                        <c:set var="dealDic2" value="${fns:getAdvertPosit('index_DealDic_2')}"/>
                        <c:set var="dealDic2s" value="${fns:getRandomAdverts(dealDic2.refrenceId, dealDic2.advertKey, 6)}"/>
                        <dt class="recoment-dt"><i class="recoment-icons recoment-dt-dress"></i>${dealDic2.advertName}</dt>
                        <c:forEach items="${dealDic2s}" var="item">
                            <dd><a href="${fns:getAdUrl(item.urlAddress)}" target="_blank" title="${adverts.adTitle }"><img  src="${res}${item.adLogo}" alt="${item.altMark}" width="80" height="40"></a></dd>
                        </c:forEach>
                    </dl>
                    <dl class="recoment-dl clearfix" style="">
                        <c:set var="dealDic3" value="${fns:getAdvertPosit('index_DealDic_3')}"/>
                        <c:set var="dealDic3s" value="${fns:getRandomAdverts(dealDic3.refrenceId, dealDic3.advertKey, 6)}"/>
                        <dt class="recoment-dt"><i class="recoment-icons recoment-dt-dress"></i>${dealDic3.advertName}</dt>
                        <c:forEach items="${dealDic3s}" var="item">
                            <dd><a href="${fns:getAdUrl(item.urlAddress)}" target="_blank" title="${adverts.adTitle }"><img src="${res}${item.adLogo}" alt="${item.altMark}" width="80" height="40"></a></dd>
                        </c:forEach>
                    </dl>
                    <dl class="recoment-dl clearfix" style="border-right:0;">
                        <c:set var="dealDic4" value="${fns:getAdvertPosit('index_DealDic_4')}"/>
                        <c:set var="dealDic4s" value="${fns:getRandomAdverts(dealDic4.refrenceId, dealDic4.advertKey, 6)}"/>
                        <dt class="recoment-dt"><i class="recoment-icons recoment-dt-dress"></i>${dealDic4.advertName}</dt>
                        <c:forEach items="${dealDic4s}" var="item">
                            <dd><a href="${fns:getAdUrl(item.urlAddress)}" target="_blank" title="${adverts.adTitle }"><img src="${res}${item.adLogo}" alt="${item.altMark}" width="80" height="40"></a></dd>
                        </c:forEach>
                    </dl>
                </div>
            <c:if test="${param.m == '1' }">
            <div class="arrow">
                <em></em>
                <span></span>
            </div>
            </c:if>
        </div>
        <ul class="nav-items js-nav-items clearfix">
            <li class="home<c:if test="${empty param.m || param.m=='1'}"> on</c:if>"><a href="${ctx}/">首页</a></li>
            <li<c:if test="${param.m=='2'}"> class="on"</c:if>><a href="${ctx}/market/index">品牌市场</a></li>
            <li<c:if test="${param.m=='6'}"> class="on"</c:if>><a href="${ctx}/meet/deal" style="color: #E70012; font-weight: 600;">在线交易会</a></li>
            <li<c:if test="${param.m=='4'}"> class="on"</c:if>><a href="${ctx}/meet">线下发布会</a></li>
            <li<c:if test="${param.m=='5'}"> class="on"</c:if>><a href="${ctx}/info/">新闻资讯</a></li>
            <li<c:if test="${param.m=='3'}"> class="on"</c:if>><a target="_blank" href="${ctx}/apply/">加盟入驻</a></li>
            <li<c:if test="${param.m=='3'}"> class="on"</c:if>><a target="_blank" href="${ctx}/about/">关于我们</a></li>
        </ul>
        <div class="nav-move-item"></div>
    </div>
</div>
