<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/indexKey.jsp" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="brand ts-container" style="overflow: hidden;">
    <div class="brand-hd clearfix">
        <h4 class="fl">优选品牌</h4>
        <span class="fl">大牌时尚抢先体验，绝对不容错过</span>
        <%--<a href="javascript:;" class="fr js-change-brand"><i class="icon-change"></i>换一批再看看</a>--%>
    </div>
    <div class="brand-bd clearfix" id="change_brand" style="width: 1200px;">
        <c:set value="${fns:getAdvertPosit(advert_brand_preferred) }" var="posit"/>
        <c:if test="${posit != null && posit.viewNum > 0 }">
            <c:forEach items="${posit.subList}" var="list" begin="0" end="${posit.viewNum-1}">
                <a href="${list.urlAddress}" target="_blank" title="${list.adTitle}">
                    <img src="${res}${list.adLogo}" alt="${list.altMark}" />
                </a>
            </c:forEach>
        </c:if>
    </div>
    <%-- <div class="brand-bd clearfix" id="change_brand">
         <a href="" style="width: 197px;"><img src="${res}/images/fronts/index/temp/brand_01.jpg" alt="" /></a>
         <a href="" style="width: 395px;"><img src="${res}/images/fronts/index/temp/brand_02.jpg" alt="" /></a>
         <a href="" style="width: 196px;"><img src="${res}/images/fronts/index/temp/brand_03.jpg" alt="" /></a>
         <a href="" style="width: 197px;"><img src="${res}/images/fronts/index/temp/brand_01.jpg" alt="" /></a>
         <a href="" style="width: 197px;"><img src="${res}/images/fronts/index/temp/brand_03.jpg" alt="" /></a>

         <a href="" style="width: 197px;"><img src="${res}/images/fronts/index/temp/brand_01.jpg" alt="" /></a>
         <a href="" style="width: 196px;"><img src="${res}/images/fronts/index/temp/brand_03.jpg" alt="" /></a>
         <a href="" style="width: 197px;"><img src="${res}/images/fronts/index/temp/brand_01.jpg" alt="" /></a>
         <a href="" style="width: 395px;"><img src="${res}/images/fronts/index/temp/brand_02.jpg" alt="" /></a>
         <a href="" style="width: 197px;"><img src="${res}/images/fronts/index/temp/brand_03.jpg" alt="" /></a>
     </div>--%>
</div>