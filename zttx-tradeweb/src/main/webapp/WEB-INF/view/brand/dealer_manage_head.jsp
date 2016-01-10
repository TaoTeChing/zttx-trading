<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/include/taglib.jsp" %>
<div class="js_agency_menu clearfix">
	<ul class="clearfix">
		<%--<li <c:if test='${param.m==0}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/listDealerGroom">推荐的</a></li>--%>
        <li <c:if test='${param.m==1}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/invite">邀请中的</a></li>
        <li <c:if test='${param.m==2}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/listDealerApply">申请中的</a></li>
        <li <c:if test='${param.m==3}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/favorite">收藏的</a></li>
        <li <c:if test='${param.m==4}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/brandVisited">我浏览过的</a></li>
        <li <c:if test='${param.m==5}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/dealerVisited">浏览过我的</a></li>
        <li <c:if test='${param.m==6}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/listRefuse">我拒绝的</a></li>
        <li <c:if test='${param.m==7}'>class="selected"</c:if>><a href="${ctx}/brand/dealer/findDealer">寻找更多</a></li>
    </ul>
</div>